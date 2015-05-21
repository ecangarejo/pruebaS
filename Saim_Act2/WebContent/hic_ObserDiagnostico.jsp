<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%
String CodDiag=request.getParameter("CodDiag");
if(CodDiag==null){
	CodDiag="";
}
%>


<script language="javascript">
function getXMLObject()  //XML OBJECT
{
   var xmlHttp = false;
   try {
     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
   }
   catch (e) {
     try {
       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
     }
     catch (e2) {
       xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
     }
   }
   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
   }
   return xmlHttp;  // Mandatory Statement returning the ajax object created
}
 
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object

function CargarObservacion(){
	var Diagnos=<%=CodDiag%>;
	   ajax=getXMLObject();
	   ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('Obser').innerHTML = ajax.responseText;
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ObsDia&Diagnos="+Diagnos); //Posting txtname to Servlet
	
}


function ActualizarAntePerso(){
	var Diagnos=document.getElementById("txtCodDiagnostico").value;
	var observacion=document.getElementById("txtObservacion").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText	;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ActuObser&Diagnos="+Diagnos+"&observacion="+observacion); //Posting txtname to Servlet
	
}
</script>
</head>
<body onload="CargarObservacion()">
<form id="form1" name="form1">
<div id="Obser"></div>

</form>
</body>
</html>