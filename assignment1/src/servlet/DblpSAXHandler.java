package servlet;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import servlet.DblpElement;

public class DblpSAXHandler extends DefaultHandler {

 private DblpElement currentElement = null;
 private List<DblpElement> elements = new ArrayList<DblpElement>();

 // boolean for all attributes
 // author|editor|title|booktitle|pages|year|address|
 // journal|volume|number|month|url|ee|cdrom|cite|publisher|note
 // crossref|isbn|series|school|chapter">
 private boolean bagency=false;
  private boolean bheadline=false;
  private boolean bpublish_date=false;
  private boolean bpublish_year=false;
  private boolean bcity=false;
  private boolean bstate=false;
  private boolean bcontent=false;
  private boolean bfooter_content=false;
  private boolean bcontact1=false;
  private boolean bcontact2=false;
  private boolean bdate_entered=false;
  private boolean bentered_by=false;
  private boolean bdate_last_modified=false;
  private boolean blast_modified_by=false;
  private boolean bcategory_tags=false;
  private boolean bcategory_tag1=false;
  private boolean bsubheadline=false;
  private boolean bcategory_tag2=false;
  private boolean bcontent2=false;
  private boolean bcategory_tag3=false;
  private boolean bcategory_tag4=false;
  private boolean bcategory_tag5=false;
  private boolean bcontent3=false;
  private boolean bcontent4=false;
  private boolean bcontact3=false;
  private boolean bcontact4=false;


 // PublicationTypes
 // private boolean bArticle = false;
 // private boolean bProceedings = false;
 // private boolean bBook = false;

 public List<DblpElement> getElements() {
  return this.elements;
 }
//this method is called every time the parser gets an open tag'<'
 //identifies which tag is being open at time by assigning an open flag
 @Override
 public void startElement(String uri, String localName, String qName,
         Attributes attributes) throws SAXException {
  //

  // reading new node
  // possible types:
  // article|inproceedings|proceedings|book|incollection
  // phdthesis|mastersthesis|
  //如果当前节点=article，就把type设置为article


  // reading attribtues
  // possible attributes
  // author|editor|title|booktitle|pages|year|address
  // journal|volume|number|month|url|ee|cdrom|cite|publisher|note
  // crossref|isbn|series|school|chapter
   if (qName.equalsIgnoreCase("row")) {
      currentElement = new DblpElement();
      currentElement.setType("row");
     }
     if (qName.equalsIgnoreCase("agency")) {
      bagency = true;
     } else if (qName.equalsIgnoreCase("headline")) {
      bheadline = true;
     } else if (qName.equalsIgnoreCase("publish_date")) {
      bpublish_date = true;
     } else if (qName.equalsIgnoreCase("publish_year")) {
      bpublish_year = true;
     } else if (qName.equalsIgnoreCase("city")) {
      bcity = true;
     } else if (qName.equalsIgnoreCase("state")) {
      bstate = true;
     } else if (qName.equalsIgnoreCase("content")) {
      bcontent = true;
     } else if (qName.equalsIgnoreCase("footer_content")) {
      bfooter_content = true;
     } else if (qName.equalsIgnoreCase("contact1")) {
      bcontact1 = true;
     } else if (qName.equalsIgnoreCase("contact2")) {
      bcontact2 = true;
     } else if (qName.equalsIgnoreCase("date_entered")) {
      bdate_entered = true;
     } else if (qName.equalsIgnoreCase("entered_by")) {
      bentered_by = true;
     } else if (qName.equalsIgnoreCase("date_last_modified")) {
      bdate_last_modified = true;
     } else if (qName.equalsIgnoreCase("last_modified_by")) {
      blast_modified_by = true;
     } else if (qName.equalsIgnoreCase("category_tags")) {
      bcategory_tags = true;
     } else if (qName.equalsIgnoreCase("category_tag1")) {
      bcategory_tag1 = true;
     } else if (qName.equalsIgnoreCase("subheadline")) {
      bsubheadline = true;
     } else if (qName.equalsIgnoreCase("category_tag2")) {
      bcategory_tag2 = true;
     } else if (qName.equalsIgnoreCase("content2")) {
      bcontent2 = true;
     } else if (qName.equalsIgnoreCase("category_tag3")) {
      bcategory_tag3 = true;
     } else if (qName.equalsIgnoreCase("category_tag4")) {
      bcategory_tag4 = true;
     } else if (qName.equalsIgnoreCase("category_tag5")) {
      bcategory_tag5 = true;
     }else if (qName.equalsIgnoreCase("content3")) {
      bcontent3 = true;
     }else if (qName.equalsIgnoreCase("content4")) {
      bcontent4 = true;
     }else if (qName.equalsIgnoreCase("contact3")) {
      bcontact3 = true;
     }else if (qName.equalsIgnoreCase("contact4")) {
      bcontact4 = true;
     }


 }
//this method is called every time the parser gets a closing tag'>'
 //分别将type插入一列，各个小的类别再插入给自类别的类
 @Override
 public void endElement(String uri, String localName, String qName)
         throws SAXException {

  // possible types:
  // article|inproceedings|proceedings|book|incollection
  // phdthesis|mastersthesis|www
  //insert type of the input stored between tags
  if (qName.equalsIgnoreCase("article")) {
   this.elements.add(currentElement);
   this.currentElement = null;
  } else if (qName.equalsIgnoreCase("proceedings")) {
   this.elements.add(currentElement);
   this.currentElement = null;
  } else if (qName.equalsIgnoreCase("book")) {
   this.elements.add(currentElement);
   this.currentElement = null;
  } else if (qName.equalsIgnoreCase("inproceedings")) {
   this.elements.add(currentElement);
   this.currentElement = null;
  } else if (qName.equalsIgnoreCase("incollection")) {
   this.elements.add(currentElement);
   this.currentElement = null;
  } else if (qName.equalsIgnoreCase("phdthesis")) {
   this.elements.add(currentElement);
   this.currentElement = null;
  } else if (qName.equalsIgnoreCase("masterthesis")) {
   this.elements.add(currentElement);
   this.currentElement = null;
  } else if (qName.equalsIgnoreCase("www")) {
   this.elements.add(currentElement);
   this.currentElement = null;
  }

 }

 @Override
 public void characters(char ch[], int start, int length)
         throws SAXException {

  String data = new String(ch, start, length);
//调用dblpelement，将当前节点数据插入相应对象的list，返回btitle为false
   if (bagency) {
      currentElement.setagency(data);
      bagency = false;
     
     } else if (bheadline) {
      currentElement.setheadline(data);
      bheadline = false;
     } else if (bpublish_date) {
      currentElement.setpublish_date(data);
      bpublish_date = false;
     } else if (bpublish_year) {
      currentElement.setpublish_year(data);
      bpublish_year = false;
     } else if (bcity) {
      currentElement.setcity(data);
      bcity = false;
     } else if (bstate) {
      currentElement.setstate(data);
      bstate = false;
     } else if (bcontent) {
      currentElement.setcontent(data);
      bcontent = false;
     } else if (bfooter_content) {
      currentElement.setfooter_content(data);
      bfooter_content = false;
     } else if (bcontact1) {
      currentElement.setcontact1(data);
      bcontact1 = false;
     } else if (bcontact2) {
      currentElement.setcontact2(data);
      bcontact2 = false;
     } else if (bdate_entered) {
      currentElement.setdate_entered(data);
      bdate_entered = false;
     } else if (bentered_by) {
      currentElement.setentered_by(data);
      bentered_by = false;
     } else if (bdate_last_modified) {
      currentElement.setdate_last_modified(data);
      bdate_last_modified = false;
     } else if (blast_modified_by) {
      currentElement.setlast_modified_by(data);
      blast_modified_by = false;
     } else if (bcategory_tags) {
      currentElement.setcategory_tags(data);
      bcategory_tags = false;
     } else if (bcategory_tag1) {
      currentElement.setcategory_tag1(data);
      bcategory_tag1 = false;
     } else if (bsubheadline) {
      currentElement.setsubheadline(data);
      bsubheadline = false;
     } else if (bcategory_tag2) {
      currentElement.setcategory_tag2(data);
      bcategory_tag2 = false;
     } else if (bcontent2) {
      currentElement.setcontent2(data);
      bcontent2 = false;
     } else if (bcategory_tag3) {
      currentElement.setcategory_tag3(data);
      bcategory_tag3 = false;
     } else if (bcategory_tag4) {
      currentElement.setcategory_tag4(data);
      bcategory_tag4 = false;
     }else if (bcategory_tag5) {
      currentElement.setcategory_tag5(data);
      bcategory_tag5 = false;
     }else if (bcontent3) {
      currentElement.setcontent3(data);
      bcontent3 = false;
     }else if (bcontent4) {
      currentElement.setcontent4(data);
      bcontent4 = false;
     }else if (bcontact3) {
      currentElement.setcontact3(data);
      bcontact3 = false;
     }else if (bcontact4) {
      currentElement.setcontact4(data);
      bcontact4 = false;
     }

    } 


}