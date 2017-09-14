<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html> 

<body> 

<script type="text/javascript"> 
var message ='${msg}';
var url ='${pageContext.request.contextPath}${url}'; 
window.alert(message); 
document.location.href =url; 
</script></body></html> 