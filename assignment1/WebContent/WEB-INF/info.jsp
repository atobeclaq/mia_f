<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="servlet.DblpDao, servlet.DblpElement"%>
<%
 String id = request.getParameter("id");
 DblpElement element = DblpDao.getDblpElementById(Integer
         .parseInt(id));
%>

<%
 DblpDao.setXmlFilePath(request.getServletContext().getRealPath(
         "/WEB-INF/sample.xml"));
%>  

<div class="container">

 <h1>Detail info</h1>

 <%
  if (element == null) {
 %>
 <h3>Oops, no such id</h3>

 <%
  } else {
 %>

 <table class="table table-bordered">
  <tr>
   <td>City</td>
   <td><%=element.getcity() == null ? "" : element.getcity()%></td>
  </tr>
  <tr>
   <td>Agency</td>
   <td><%=element.getagency()%></td>
  </tr>

  <tr>
   <td>Headline</td>
   <td><%=element.getheadline() == null ? "" : element
            .getheadline()%></td>
  </tr>
  <tr>
   <td>Date</td>
   <td><%=element.getpublish_date() == null ? "" : element.getpublish_date()%></td>
  </tr>
 


 </table>

 <form action="do">
  <input type="hidden" name="servlet" value="onAdd" /> <input
   type="hidden" name="pick" value="<%=element.getId()%>" />
  <button class="btn btn-primary" type="submit">Add to Cart</button>
 </form>


 <%
  }
 %>
</div>