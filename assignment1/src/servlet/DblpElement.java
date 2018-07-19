package servlet;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DblpElement
 */
@WebServlet("/DblpElement")
public class DblpElement {
 private static int COUNT = 0;
 private int id;
    /**
     * @see HttpServlet#HttpServlet()
     */
 //private String agency;
 private String agency;
 private String headline;
 private String publish_date;
 private String publish_year;
 private String city;
 private String state;
 private String content;
 private String footer_content;
 private String contact1;
 private String contact2;
 private String date_entered;
 private String entered_by;
 private String date_last_modified;
 private String last_modified_by;
 private String category_tags;
 private String category_tag1;
 private String subheadline;
 private String category_tag2;
 private String content2;
 private String category_tag3;
 private String category_tag4;
 private String category_tag5;
 private String content3;
 private String content4;
 private String contact3;
 private String contact4;
 private String type;
 
 
//public DblpElement() {
 // this.agency = new ArrayList<String>();
 // this.id = ++COUNT;
// }
 
//public List<String> getagency() {
// return agency;
//}

//public void setagency(List<String> agency) {
 //this.agency = agency;
//}

//put title in the list
 
 public String getagency() {
   return agency;
  }
 public void setagency(String agency) {
  this.agency = agency;
 }

 public String getheadline() {
  return headline;
 }

 public void setheadline(String headline) {
  this.headline = headline;
 }

 public String getpublish_date() {
  return publish_date;
 }

 public void setpublish_date(String publish_date) {
  this.publish_date = publish_date;
 }

 public String getpublish_year() {
  return publish_year;
 }

 public void setpublish_year(String publish_year) {
  this.publish_year = publish_year;
 }

 public String getcity() {
  return city;
 }

 public void setcity(String city) {
  this.city = city;
 }

 public String getcontact2() {
  return contact2;
 }
 public void setcontact2(String contact2) {
  this.contact2 = contact2;
 }
 public String getcontent() {
  return content;
 }
 public void setcontent(String content) {
  this.content = content;
 }
 public String getfooter_content() {
  return footer_content;
 }
 public void setfooter_content(String footer_content) {
  this.footer_content = footer_content;
 }
 public String getcontact1() {
  return contact1;
 }
 public void setcontact1(String contact1) {
  this.contact1 = contact1;
 }
 public String getdate_entered() {
  return date_entered;
 }
 public void setdate_entered(String date_entered) {
  this.date_entered = date_entered;
 }
 public String getentered_by() {
  return entered_by;
 }
 public void setentered_by(String entered_by) {
  this.entered_by = entered_by;
 }
 public String getdate_last_modified() {
  return date_last_modified;
 }
 public void setdate_last_modified(String date_last_modified) {
  this.date_last_modified = date_last_modified;
 }
 public String getsubheadline() {
  return subheadline;
 }
 public void setsubheadline(String subheadline) {
  this.subheadline = subheadline;
 }
 public String getcategory_tags() {
  return category_tags;
 }
 public void setcategory_tags(String category_tags) {
  this.category_tags = last_modified_by;
 }
 public String getcategory_tag1() {
  return category_tag1;
 }
 public void setcategory_tag1(String category_tag1) {
  this.category_tag1 = category_tag1;
 }
 public String getcategory_tag2() {
  return category_tag2;
 }
 public void setcategory_tag2(String category_tag2) {
  this.category_tag2 = category_tag2;
 }
 public String getcontent2() {
  return content2;
 }
 public void setcontent2(String content2) {
  this.content2 = content2;
 }
 public String getcontent3() {
  return content3;
 }
 public void setcontent3(String content3) {
  this.content3 = content3;
 }
 public String getcategory_tag4() {
  return category_tag4;
 }
 public void setcategory_tag4(String category_tag4) {
  this.category_tag4 = category_tag4;
 }
 public String getcategory_tag5() {
  return category_tag5;
 }
 public void setcategory_tag5(String category_tag5) {
  this.category_tag5 = category_tag5;
 }
 public String getcategory_tag3() {
  return category_tag3;
 }
 public void setcategory_tag3(String category_tag3) {
  this.category_tag3 = category_tag3;
 }
 public String getcontent4() {
  return content4;
 }
 public void setcontent4(String content4) {
  this.content4 = content4;
 }
 public String getcontact3() {
  return contact3;
 }
 public void setcontact3(String contact3) {
  this.contact3 = contact3;
 }
 public String getcontact4() {
  return contact4;
 }
 public void setcontact4(String contact4) {
  this.contact4 = contact4;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }
 //public String getType() {
 // return type;
// }
//set the type,eg:book,article,reference，插入类型list
// public void setType(String type) {
//.type = type;
// }
 public String getstate() {
  return state;
 }

 public void setstate(String state) {
  this.state = state;
 }
 public String getlast_modified_by() {
  return last_modified_by;
 }

 public void setlast_modified_by(String last_modified_by) {
  this.last_modified_by = last_modified_by;
 }


}