<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script type="text/javascript">
  function confirmDelete(){
	  return confirm("Are you sure, want to delete this record?");
  }
</script>

</head>
<body>

<a href="loadForm">+ Add New Contact</a>
<h3>View All Contacts</h3>

${deleteMsg}

<table border="1">
  <thead>
    <tr>
       <th>Contact Id</th>
       <th>Contact Name</th>
       <th>Contact Email</th>
       <th>Contact Number</th>
       <th>Action</th>
    </tr>
  </thead>
  
  <tbody>
    <c:forEach items="${allContacts}" var="c">
        <tr>
           <td>${c.contactId}</td>
           <td>${c.contactName}</td>
           <td>${c.contactEmail}</td>
           <td>${c.contactNumber}</td>
           <td>
             <a href="edit?cid=${c.contactId}">Edit</a> &nbsp;&nbsp;
             <a href="delete?cid=${c.contactId}" onclick="return confirmDelete()">Delete</a>
           </td>
        </tr>
    </c:forEach>
  </tbody>
  
</table>


</body>
</html>