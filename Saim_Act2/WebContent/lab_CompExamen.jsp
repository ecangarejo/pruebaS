

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Seguimiento de Resultados</title> 
<script language=javascript src="Laboratorio.js"></script>
<style type="text/css">
#sugerencias  li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias { border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.scrol {height : 363px ; altura: 90px; desbordamiento: auto; overflow:scroll}


</style>

<script language=javascript>
function mostrardiv() {
div = document.getElementById('combo');
div.style.display = '';
}
</script>
<script language=javascript src="autocompletar.js"></script>
</head>
<body onload="Cedulacomp()" id="back">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 
} 
</script> 
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

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
function valid(tecla, e)
{ 
    
	opc = false;
	var s=document.getElementById('cedula').value;
	var n=0;
	var pos=0;
	for(i=0;i<s.length;i++){
	   valor = parseInt(s.charAt(i));
		if(isNaN(valor)){
			n++;
		}
	}
	if(n>0)
	{
		window.alert('El campo  solo acepta valores Numericos');
		document.getElementById('cedula').value="";
		document.getElementById('cedula').focus();
		return false;
	}
	
	tecla =   e.keyCode;  e.which;
if(tecla == 13)
  {
	var gen=document.getElementById('cbtipo').selectedIndex;
	tipo=document.getElementById('cbtipo').options[gen].text;
        cedula=document.getElementById('cedula').value;
        if(cedula==""){
	        alert("Digite cedula...!");
        }else{
          
            mostrar(tipo,cedula);
        }	
	}
}
function mostrar(tipo,cedula){
   ajax=getXMLObject();
   ajax.open("POST","lab_BusPac",true); //getname will be the servlet name
   	   ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				if(ajax.status == 200) {
					su = eval('('+ajax.responseText+')');
                    if(su.length == 0) {
                        alert("No hay examenes para este paciente!");
                    }else {
                    	gerencias();
                    }
				}
			}
		}
		
    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("z="+10+"&tipo="+tipo+"&cedula="+cedula+""); //Posting txtname to Servlet
}
function gerencias() {
	apro = document.getElementById('combo');
        apro.innerHTML = su.Lista();   
}

/*prototypo de la lista quesale en el div de sugerencias*/
Array.prototype.Lista = function() {
	var codi = "";
   	if(tecla == 13){
	   document.getElementById('combo').innerHTML='<p>Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	   codi  = '<center>[<a href="javascript:mosmenu();"> Volver </a> ]</center><br/>'
	   codi += '<div id="cabecera2" align="center" class="style11">EXAMENES REALIZADOS</div><br>';
	   codi +=  "<ul class =\"scrol\"\"seleccionado\" size='80'>";
	        for(var i=0; i<this.length-1; i++){
                codi += '<li ><input type="checkbox" name="ca" id="ca" value="'+this[i]+'" >'+this[i]+'<\/li>';    
	         }             
	    codi += '<\/ul>';
	    codi += '<p align="center"><input name="exa" type="button" class="boton4" id="exa" value="Ver Estudio" onclick="va('+i+')"  /></p><br>';
	    div = document.getElementById('combo');
	    div.style.display = '';
	    div1 = document.getElementById('men');
	    div1.style.display = 'none';
	    	    
	    return codi;
   }else{
   } 	    
};

function mosmenu(){
    div = document.getElementById('combo');
    div.style.display = 'none';

    div0 = document.getElementById('mostrarcomp');
    div0.style.display = 'none';

    div2 = document.getElementById('resultado');
    div2.style.display = 'none';
    
    div1 = document.getElementById('men');
    div1.style.display = '';

}


function va(c) {
 if(c!=1){
	 var cadena ="";
	 var k=0;
	for(var i=0; i<c; i++){	 
       if (form1.ca[i].checked) {
    	 	cadena = cadena+form1.ca[i].value;
           	cadena = cadena +"|";
			k=k+1;
		}
       }
	if(k<=5){
	BuscarExamencomp(cadena,k);
	}else{
		alert("Solo se puede realizar el estudio maximo de 5 examenes");
	}
     }else{
	 if(form1.ca[0].checked){
	 }
 }   
};
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
				//con.cerrar();
	       	%>
<%
String va=request.getParameter("va");
//System.out.println("va.."+va);
if(va==null){va="";%>
<script type="text/javascript"> 
	url = document.location.href ; 
	// División en trozos con la barra como delimitador. 
	partes = url.split('/'); 
	 
	var a=partes[partes.length-1];
	
	window.location.href="PermisosPagina?va=1&pa="+a+"&codusu="+<%=codigou%>+""; 
	
</script>
<%} %>
<%
String cod=request.getParameter("codigo");
//System.out.println("cod"+cod);
//System.out.println("cod_usuario yosimar"+codigou);
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}

if(codigou.equals("")||cod.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>

<table width="100%">


<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white" > --Cerrar Session--</a></div>
</td>
</tr>

<tr>  
<td>
	<div id="cabecera1" align="center" class="linkmenu" ><b><a href="menu.jsp" >Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Consultas-Comportamiento de Examen</b></div>
</td>
</tr>

<tr id="principal1">
<td>

<div >
	<form name="form1"  id="form1" onkeypress = "return pulsar(event);">
	<table width="100%"> 
		<tr>
			<td width="130%" id="lateral4" class="style6">
<br/>       
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
				
	<br/>
						<br/>
						<div id="men"><%@include file="Copiamenu.jsp"%></div>
						<div id="combo" class="margen3" style="display:none"></div>	
			</td>

			<td width="100%">                
					<br />
						
							<table width="100%" align="center"  style="margin-top:-19px;">     
							  <tr>
							    <td colspan="4" ><div align="center" id="cabecera2" class="style11">LABORATORIOS</div></td>
							  </tr>
							  
							  <tr>
							  
							    <td><div id="busquedacomp"></div></td>
							</tr>
							</table>
							
							<table width="100%" border="0" align="center">
							<tr>
							     <td></td>
							  
							  
							    <td>&nbsp;</td>
							    <br/>
							    <td>&nbsp;</td>
							    <td>&nbsp;</td>
							  </tr>
							<tr align="center">
							     <td><div id="mostrarcomp"></div></td>
							  
							  
							    <td>&nbsp;</td>
							    <br>
							    <td>&nbsp;</td>
							    <td>&nbsp;</td>
							  </tr>
							  <tr >
							    <td colspan="4"><div id="resultado"></div></td>
							    </tr>
							</table>
							
				</td>
			</tr>
	</table>
</div>
</td></tr></table>
<%} %>
</body>
</html>
