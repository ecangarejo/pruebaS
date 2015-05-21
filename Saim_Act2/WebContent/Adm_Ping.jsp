
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language=javascript src="Validaciones.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script languaje="javascript" src="Adm_Ping.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Pre-Ingreso Urgencia</title>
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>

<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">

<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:342px; cursor:pointer; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}

</style>

<script type="text/javascript">
/*
	
//------------------------------------------------------------------------
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
	   
	

	
	function ajaxFunction2() {
		 
		  if(xmlhttp) { 	 
		  // var txtname = h.txtnombre.value;//document.getElementById("txtnombre");
		    xmlhttp.open("POST","ControlPing",true); //getname will be the servlet name
		    xmlhttp.onreadystatechange  = ComprobarPeticion2;
		    xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		    xmlhttp.send(""); //Posting txtname to Servlet
			  }
		}
*/
/*	function ComprobarPeticion2() {
		
		   if (xmlhttp.readyState == 4) {
			if(xmlhttp.status == 200) {
		     	
		     	var a=xmlhttp.responseText;
		     	var y=a.split("_").length;
		     	var z=a.split("_");

		     	document.form1.cbeps.length=y-1;
		      
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
		     		document.form1.cbeps.options[x].text=z[x];
			    }
		    		
		    	 // document.form1.message.innerHTML=xmlhttp.responseText; //Update the HTML Form element 

		     }
		     else {
		        alert("Error during AJAX call. Please try again");
		     }
		   }
		}*/

 
	
	</script>

</head>
<body onLoad="" id="back"> 
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

function A(tecla,e){
	var k=null;	tecla =   e.keyCode;  e.which;
	if(tecla == 13){BuscarPaciente();}
}

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
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
				rs2.getStatement().close();
				
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				rs4.getStatement().close();
				con.cerrar();
	       	%>

<%
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>
<table width="100%">
<tr >
<td>
	 <table width="100%">
			<tr>
			<td>
			<div > <a  href="mensajes.jsp?TB_iframe=true&height=520&width=700"  class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin %></b></font></a> <%if (madmin>0){ %><bgsound src="Imagenes/INNERMK.WAV" loop="2"><img src="Imagenes/sobr0001.gif" /><%}%></div>
			</td>
			<td align="right">
			<div align="right" id="usuario" align="right" class="style11">Bienvenido,<b><%=idu%></b> |<a href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou %>" style="color: white">--Cerrar Session--</a></div>
			</td>
			
		</tr>
	</table>
</td>
</tr>


<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Pre-Ingreso-Urgencia</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>
 
	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div style="margin-top:-16px;" id="cabecera2" class="style11" align="center">MODULO ADMISIONES</div>
					<br/><br/>
								<%@include file="CopyAdm.jsp"%>     
			</td>

			<td width="100%">   
					<br />

<form id="form1" name="form1" style="margin-top:-14px;"  onkeypress = "return pulsar(event);" >
<table width="100%" border="0" cellspacing="0" class="style6">
  <tr>
    <td colspan="4" id="cabecera2" class="style11" ><div align="center">PRE-INGRESO</div></td>
    </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td  align="right"> TIPO DOCUMENTO<span class="style1"><span class="style8">*</td>
    <td>
      <select name="cbtipodoc" id="cbtipodoc" >
        <% 
 ResultSet rs3=null;
 Statement st3 = null;  
 try{
 	Conexion con2=new Conexion();
 	st3 = con2.conn.createStatement();
 	rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");%>
 	<%while(rs3.next()){%>
	<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)%></option>
 	<%}
 	rs3.getStatement().close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
      </select>
    <b style="display:none" id="td" class="style8">(x)</b>   </td>
    <td align="right">NUMERO DOCUMENTO <span class="style8">*</span></td>
    <td><label>
      <input name="txtnumdocu" type="text" id="txtnumdocu" onkeydown="A(this,event)" />
    </label><b style="display:none" id="ce" class="style8">(x)</b></td>
  </tr>

  <tr>
    <td  colspan="4"><div id='cargaAmbu'></div></td>
   
  </tr>
<input name="CodUsu" type="hidden" id="CodUsu" value="<%=codigou%>" />
</table>
 </form>

</td></tr></table>
</div>
</td></tr></table>
<%}%>
</body>
</html>