

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>

<title>Consultas de Examenes</title>
<script language=javascript src="Laboratorio.js"></script>
<script language=javascript src="autocompletar.js"></script>
<script language=javascript src="autocompletar_nombre.js"></script>


<style type="text/css">
#sugerencias  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias { border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;}
.scrollbox {height : 90px ; altura: 90px; desbordamiento: auto; overflow:scroll}

</style>



<script language="javascript">
function Abrir(ano,mes,dia,hora,minuto,segundo,codpa,codexa,codgenero) {
	
	var fecha=ano+"-"+mes+"-"+dia
	var horas=hora+":"+minuto+":"+segundo

			
   pagina="Lab_Reporte_Grupo.jsp?hora="+horas+"&fecha="+fecha+"&subarea="+codexa+"&codpac="+codpa+"&codge="+codgenero+"";
			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}

function Abrir_ventana (ano,mes,dia,hora,minuto,segundo,tipo,exa,codpa,codres,valorini,valorfinal) {

	fecha=ano+"-"+mes+"-"+dia
	horas=hora+":"+minuto+":"+segundo
		
	if(tipo=="2"){
    pagina="Lab_Reporte_Rango.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"&valorini="+valorini+"&valorfi="+valorfinal+"";
			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
	}else{
		  pagina="Lab_Reporte_Texto.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"";
		
          var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
          opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
          window.open(pagina,"NUEVA",opciones);
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

function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

function validarcom(h,tecla, e){ 

	
    
	opc = false;
	var s=h.cedula.value;
	var n=0;
	var pos=0;
	/*for(i=0;i<s.length;i++){
	   valor = parseInt(s.charAt(i));
		if(isNaN(valor)){
			n++;
		}
	}
	if(n>0)
	{
		window.alert('El campo solo acepta valores Numericos');
		h.cedula.value="";
		h.cedula.focus();
		return false;
	}
	*/
	tecla =   e.keyCode;  e.which;
if(tecla == 13){ 
       var tipo,cedula;
        var gen=h.cbtipo.selectedIndex;
		tipo=h.cbtipo.options[gen].text;
        cedula=h.cedula.value;
        //alert();
        if(cedula==""||tipo==""){
	        alert("Digite cedula...!");
        }else{
        	
    		                                
        	yosi();	
        	
        }
	} 
}
function yosi() {
	getXMLObject();
	 
	  if(xmlhttp) {
	//alert("okgghghg");
		
		
		
		  var tipo,cedula;
		  document.getElementById('resultado').innerHTML='<p>Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
		  div = document.getElementById('resultado');
			
			div.style.display="";
	        var gen=document.getElementById('cbtipo').selectedIndex;
			tipo=document.getElementById('cbtipo').options[gen].text;
			//alert(""+tipo);
	        cedula=document.getElementById('cedula').value;   
		xmlhttp.open("POST","lab_BusPac?z="+2+"&ced="+cedula+"&ti="+tipo+"",true); //getname will be the servlet name
		
		xmlhttp.onreadystatechange  = Resultado;
	   xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	xmlhttp.send(""); //Posting txtname to Servlet
	
			 
	  }
	   var x;
	}
function Resultado() {
		
	
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			
			document.getElementById('resultado').innerHTML = xmlhttp.responseText
			div = document.getElementById('resultado');
			
			div.style.display="";
			 r = document.getElementById('sugerencias');
				r.style.display='none';
	     	 
	     }
	     else {
	       // alert("Error during AJAX call. Please try again");
	     }
	   }
	
	 
	}
</script>

</head>
<body id="back">    
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
<div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>

	<div id="cabecera1" align="center"  class="linkmenu" ><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Consultas-Examenes</b></div>
</td>
</tr>

<tr  id="principal1">  
<td>

<div>  

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
	<br/>
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					<br/><br/>
								<%@include file="Copiamenu.jsp"%>
			</td>

			<td width="100%" class="style6">                
	<br/>                   
						<form style="margin-top:-19px;" name="form1" id="form1" onkeypress = "return pulsar(event);">
							<table width="100%" align="center" class="margen1"> 
							  <tr>
							    <td colspan="4"><div align="center" id="cabecera2" class="style11">LABORATORIOS</div></td>
							  </tr>

							  <tr>  
							    <td>&nbsp;</td>
							  </tr>
  
							  <tr>
							    <td colspan="2"><div align="center" id="cabecera2" class="style11">BUSCAR PACIENTE </div></td>
							    <td>&nbsp;</td>
							    <td>&nbsp;</td>
							  </tr> 
							  <tr>
							    <td bordercolor="1">
							  <input name="miradio" type="radio" value="radio1" onclick="Nombre()" />
							    </label>
							      NOMBRE</td>
							    <td bordercolor="1"><label>
				 			      <input name="miradio" type="radio" value="radio2" onclick="Cedula()" />
							    </label>
							      CEDULA</td>
							    <td></td>
							    <td><div id="busqueda"></div></td>
							<td></td>
							  </tr>
							
							  <tr>
							  <td><label></label></td>
							  <td></td>
							   <td>&nbsp;&nbsp;</td>
							    <td  width="390"><div id='sugerencias' ></div>
							      <div align="center"></div></td>
							
							  </tr>
							  <tr>
							    <td colspan="4"><div id="resultado" style="display:none" ></div></td>
							    </tr>
							 
							</table>
					</form>
			</td> 
	</tr>
</table>
</div> 
</td></tr></table>
<%}%>
</body>
</html>
