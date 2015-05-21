<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Modificacion de Permisos</title>




<script language=javascript src="ajaxseguridad.js"></script>
<script language="javascript">

function validar() {
	
    var c=form1.yo.value;
	var codigo=0;
	var nombre= new Array();

	var codusu="";
	var d="";
	t=0;
	
	var k=0;
	form1.areas.length=c;
	form1.areas.options[k].text="";
			
	 var gk=0;
 if(c!=1){
	 
	for(var i=0; i<c; i++){	
		   
       if (form1.ca[i].checked) {
    	 
          
    	 gk=gk+1;
    	 t=t+1;
         form1.areas.length=gk;
    	 d=form1.ca[i].value;
    	 
    	  josi(form1.fe[i].value);
    	 nombre[t]=d;
    	
         form1.areas.options[k].text=nombre[t];
        
   	     
   	    k=k+1; 
       }
   
	
     }
 }else{
	 if(form1.ca[0].checked){
	    	 gk=gk+1;
	    	t=t+1;
	    	form1.areas.length=gk;
	    	 d=form1.ca[0].value;
	    	 josi(form1.fe[0].value);
	    	nombre[t]=d;
	       form1.areas.options[0].text=nombre[t];
	     
	   	       
	   	    k=k+1; 
		 }
 }
 
   	 
 }//fin de la funcion
 function checkAll() {
	 var i;
    var nodoCheck = document.getElementsByTagName("input");
    var varCheck = document.getElementById("all").checked;
    for (i=0; i<nodoCheck.length; i++){
        if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
            nodoCheck[i].checked = varCheck;
        }
    } 
}

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
 
 function josi(vu){
	 var codusu;
	 //getXMLObject();
	  getXMLObject();
		  var xmlhttp = new getXMLObject();
	  if(xmlhttp) { 
			//alert(vu);
			
		codusu=form1.ced.value; 
	  
	    xmlhttp.open("POST","IngresoPermiso?codigo="+vu+"&codusu="+codusu,true); //getname will be the servlet name
	    xmlhttp.onreadystatechange  = PERMISOS;
	    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    xmlhttp.send(""); //Posting txtname to Servlet
	    
	  //var x;
	   
	}
	  
 }

 function PERMISOS() {
		
		if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
				
		     	
				var a = xmlhttp.responseText
		     	
		    		//alert(a);
		    	 // document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
 }
 function salir() {
		
	 window.location.href="Seg_login.jsp";
}
 

 
</script>

</head>
<body onLoad="ajaxmodulo(),borrar_tabla();" id="back">
<%
String cedula=request.getParameter("ced");

if(cedula==null){
	cedula="";
	//cedula="1129572970";
}%>
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script>
<%String codigou =(String)session.getAttribute("codusuario");
%>
<%
Conexion con=new Conexion();
	ResultSet rs2;
	Statement st2 = null;
	   String idu="";         
	      	
	       	st2 = con.conn.createStatement();
	       	
	       	rs2=st2.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");%>
				<%while(rs2.next()){
					
		        idu=rs2.getString(1);
				}
				rs2.close();
	       	%>

<%

if(codigou==null){
	codigou="";
}


if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>

<form  name="form1" id="form1" >
<div id="contenedor">

  <table width="100%" border="0">
<tr>
<td colspan="2">
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-Seguridad-Modificar Permisos-Permisos</b></div>
</td>
</tr>
    <tr id="cabecera2">
      <td colspan="2"><div align="center"><span class="style11">SELECCIONE MODULO </span></div></td>
    </tr>
    <tr id="cabecera2" align="center">
      <td ><span class="style11">MENU</span></td>
      <td><span class="style11">OPCIONES DE MENU </span></td>
    </tr>
    <tr bgcolor="#BED9ED" align="center">
      <td width="50%"><select name="cbarea" size="5" multiple class="style5" id="cbarea" onChange="cambiar(form1);"  >
      </select></td>
      <td width="50%"><label>
        <select name="disponible" size="5" multiple class="style5" id="disponible" onChange="cambiarmodulo(form1);" >
        </select>
      </label></td>
      </tr>
  </table>

<div id="principal"  > 
<div id="cuadro_texto" >
<div id="yosimar" > 
</div>
<div align="center"><!-- cierra el div cuadro_texto-->

<input name="siguiente" type="button"  class="boton4" onclick='validar();'  value="-PERMITIR-"  />
</div>
</div>
 </div> 
</div>
</div>

<table width="100%" border="0" align="left">
  <tr id="cabecera2">
    <td><div align="center" class="style11">PERMISOS EFECTUADOS </div></td>
    </tr>
  <tr align="center"> 
    <td><select name="areas" size="5" multiple class="style5" id="areas">
    </select></td>
</tr>
<tr>
<td><div align="center"><input name="siguiente" class="boton4" type="button" class="boton" onclick='salir();'  value="-FINALIZAR-"  /></div></td>
  </tr>
</table>

 <p>
   <input  name="ced" type="hidden"   value="<%=cedula %>" >
  </p>
</form>
	
    <%} %>
</body>
</html>