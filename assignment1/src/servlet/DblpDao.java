package servlet;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import servlet.DblpElement;
public class DblpDao {
 // singleton
  public static List<DblpElement> allElements;

  // /Users/NewFolder/Desktop/sample.xml
  public static String xmlFilePath;

  public static void setXmlFilePath(String path) {

   xmlFilePath = "/Users/lichen/Downloads/rows.xml";
  }

  public static List<DblpElement> getAllElements() {
   
   System.out.println("aaaa");
   
   //if (allElements == null) 
   {

    try {
     File inputFile = new File(xmlFilePath);
     SAXParserFactory factory = SAXParserFactory.newInstance();//obtain and configure a sqx based parser
     SAXParser saxParser = factory.newSAXParser();//obtain object for sax parser
     /*
     default handlers
     all methods are written in handler's body*/
     DblpSAXHandler userhandler = new DblpSAXHandler();
     //调用要读取的xml文件
     saxParser.parse(inputFile, userhandler);

     allElements = userhandler.getElements();
        System.out.println(allElements);
     
     // System.out.print(allElements.size());
    } catch (Exception e) {
     e.printStackTrace();
    }
   }

   return allElements;
  }
 public static DblpElement getDblpElementById(int id) {
  for (DblpElement element : getAllElements()) {
   if (element.getId() == id) {
    return element;
   }
  }
  return null;
 }
    /**
     * @see HttpServlet#HttpServlet()
     */
  
 public static List<DblpElement> getRandomTen() {
  List<DblpElement> list = getAllElements();
  System.out.print("total entries" + list.size() + "\n");
  Collections.shuffle(list);
  if (list.size() > 10) {
   return list.subList(0, 10);
  } else {
   return list;
  }
 }

}