<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="servlet.DblpDao, servlet.DblpElement"%>

<%
 DblpDao.setXmlFilePath(request.getServletContext().getRealPath(
         "/WEB-INF/sample.xml"));
%> 

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="container">
 <h1>Welcome</h1>
 <div class="row">
     <div class="col-md-12"></div>
 </div>

 <div class="row">
  <form class="" action="do">
   <input type="hidden" name="servlet" value="onSimpleSearch" />
   <h4>Search</h4>
   <div class="col-md-4">
    <label for="keywords">Keywords</label> <input type="text"
     class="form-control" id="keywords" name="keywords"
     placeholder="Enter keywords">
   </div>

   <div class="form-group col-sm-4">
    <label for="publicationtype">Publication Type</label> <select
     class="form-control" name="publicationtype" id="publicationtype">
     <option value="hundred">100</option>
     <option value="thousand">1000</option>
     
    </select>
   </div>

   
  </form>

 </div>


 <h3>Random 10</h3>
 <table class="table table-bordered">
  <tr>
   <th>Title</th>
   <th>Author</th>

  </tr>


  <%
   for (DblpElement element : DblpDao.getRandomTen()) {
  %>
  <tr>
   <td><a href=" >"><%=element.getheadline()%></a ></td>
   <td><%=element.getpublish_date()%></td>
   <td><%=element.getcity()%></td>
 
  </tr>
  <%
   }
  %>
 
  
 </table>
</div>