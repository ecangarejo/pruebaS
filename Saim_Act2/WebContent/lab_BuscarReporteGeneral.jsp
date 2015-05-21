

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="logica.Conexion" errorPage="" %>
<!-- Los import -->
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
<title>Ingreso De Paciente</title>
<link rel="shortcut icon" href="/Imagenes/favicon.ico">

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }

#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:200px; border:1px solid black; display:none; margin-left: 83px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}

.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
</style>

<script language=javascript src="Laboratorio_General.js"></script>
<script language=javascript src="autocompletar.js"></script>
<script language=javascript src="autocompletar_nombre.js"></script>
<script type="text/javascript" src="dtree.js"></script>
<script language="javascript">
function Abrir(ano,mes,dia,hora,minuto,segundo,codpa,codexa,edad,codgenero) {
	
	var fecha=ano+"-"+mes+"-"+dia
	var horas=hora+":"+minuto+":"+segundo

			
   pagina="Lab_Reporte_Grupo.jsp?hora="+horas+"&fecha="+fecha+"&subarea="+codexa+"&codpac="+codpa+"&edad="+edad+"&codge="+codgenero+""
			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}

function Abrir_ventana (ano,mes,dia,hora,minuto,segundo,tipo,exa,codpa,codres,valorini,valorfinal) {
		
	fecha=ano+"-"+mes+"-"+dia
	horas=hora+":"+minuto+":"+segundo
		
	if(tipo=="2"){
    pagina="Lab_Reporte_Rango.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"&valorini="+valorini+"&valorfi="+valorfinal+""
			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
	}else{
		  pagina="Lab_Reporte_Texto.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+""
		
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

function validarcom_General(h,tecla, e){ 

	
    
	opc = false;
	var s=h.cedula.value;
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
		window.alert('El campo telefono solo acepta valores Numericos');
		h.cedula.value="";
		h.cedula.focus();
		return false;
	}
	
	tecla =   e.keyCode;  e.which;
if(tecla == 13){ 
       var tipo,cedula;
        var gen=h.cbtipo.selectedIndex;
		tipo=h.cbtipo.options[gen].text;
        cedula=h.cedula.value;
        if(cedula==""||tipo==""){
	        alert("Digite cedula...!");
        }else{
        	
    		                                
        	yosi_General();	
        	
        }
	}
}
function yosi_General() {
	getXMLObject();
	 
	  if(xmlhttp) {
		//alert("ok");
		
		
		  var tipo,cedula;
	        var gen=document.getElementById('cbtipo').selectedIndex;
			tipo=document.getElementById('cbtipo').options[gen].text;
			//alert(""+tipo);
	        cedula=document.getElementById('cedula').value;
		xmlhttp.open("POST","lab_General?z="+2+"&ced="+cedula+"&ti="+tipo+"",true); //getname will be the servlet name
		
		xmlhttp.onreadystatechange  = Resultado;
	   xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	xmlhttp.send(""); //Posting txtname to Servlet
	
			 
	  }
	   var x;
	}
function Resultado() {
	
	if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			
			document.getElementById('menu').innerHTML = xmlhttp.responseText
			//div = document.getElementById('resultado');
			
			//div.style.display="";
			// r = document.getElementById('sugerencias');
			//	r.style.display='none';
	     	 
	     }
	     else {
	       // alert("Error during AJAX call. Please try again");
	     }
	   }
	
	 
	}
</script>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onload="fecha_ingreso(),hora();">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 
<%String codigou =(String)session.getAttribute("codusuario");
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
<div id="contenedor">

		<div id="cabecera">
         <img src="Imagenes/Laboratorio.jpg" />
	</div>
	<div id="cabecera2">
	  <div align="right" class="style2">Usted Ingreso al Sistema Como:</div>
	</div>

				<div id="lateral">
                <a href="Seguridad_login?accion=cerrar" class="texto" style="text-decoration:blink" style="color:#FF0000"> --Cerrar Session--</a><br><br>
                <a href="Laboratorio.jsp" class="style3">Volver al Menu</a>
              <br><br>

<div id="menu" >

			  </div>
		</div>
		<div id="principal">
<form name="form1" id="form1" onkeypress = "return pulsar(event);">
<table width="763" border="0" align="center">
  <tr>
    <td colspan="4" bordercolor="#0033FF" bgcolor="#01426E"><div align="center" class="style1">LABORATORIOS</div></td>
  </tr>
  <tr>
    <td width="100">&nbsp;</td>
    <td width="91">&nbsp;</td>
    <td width="1">&nbsp;</td>
    <td width="590">&nbsp;</td>
  </tr>
  <tr bgcolor="#0066CC">
    <td colspan="2" bordercolor="1" bgcolor="#01426E"><div align="center" class="style1">BUSCAR PACIENTE </div></td>
    <td bgcolor="#FFFFFF">&nbsp;</td>
    <td bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <tr>
    <td bordercolor="1">
  <input name="miradio" type="radio" value="radio1" onclick="Nombre()" />
    </label>
      NOMBRE</td>
    <td bordercolor="1"><label>
      <input name="miradio" type="radio" value="radio2" onclick="Cedula_General()" />
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
    <td><div id='sugerencias'></div></td>

  </tr>
  <tr>
    <td colspan="4"><div id="resultado" style="display:none" ></div></td>
    </tr>

</table>

</form>
</div>
</div>
    <%} %>
</body>
</html>
