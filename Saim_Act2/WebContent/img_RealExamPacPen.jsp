
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
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
<%
String numero=request.getParameter("cedula");
if(numero==null){
	numero="";
}

String codigoIce=request.getParameter("codigoIce");
if(codigoIce==null){
	codigoIce="";
}
%> 
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script languaje="javascript" src="img_autocompletaexamen.js"></script>
<title>Realizar Examen</title>

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:200px; Cursor : pointer; border:1px solid black; display:none; margin-left: 83px; Cursor : pointer;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
</style>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">
function objetoAjax(){
	var xmlhttp=false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
  		}
	}

	if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}
/////////////////////////////////////////////////////////////////////////////////
function fecha_gru(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? ":0" : ":") + mes
	  temp1 += ((dia < 10) ? ":0" : ":") + dia
	 document.getElementById('txtfecha').value = temp1
	 id = setTimeout("fecha_gru()",1000)	 
}
function hora_gru(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txthora').value = temp;
	  id = setTimeout("hora_gru()",1000)
}
/////////////////////////////////////////////////////////////////////////////
function img_grupo() {
	var cedula =<%=numero%>;
	var codigoIce=<%=codigoIce%>
	DivValor=document.getElementById('examenes')
	ajax=objetoAjax();
	    ajax.open("POST","ControlPacientesPendientes",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		PatronNormal=ajax.responseText;
				/*var g;
				 for(g=0;g<PatronNormal.length;g++){
				    	PatronNormal=PatronNormal.replace('@','Ñ');
				    }*/
	  			DivValor.innerHTML=PatronNormal;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=2&cedula="+cedula+"&codigoIce="+codigoIce); //Posting txtname to Servlet
	  
	}
/////////////////////////////////////////////////////////////////
function finalizar(){
	Actualizar1();
alert("Examenes Realizados Satisfactoriamente!!!!");
window.location.href="img_RealizarExamen.jsp";
}
function asignacion(){
	alert("Examen Asignado Correctamente!!!");	
}
////////////////////////////////////////////////////////////////////
function Actualizar1(){		
    var c=document.getElementById('yo').value;
    var oscar=c;
    var Resultado="";
    var Diagnostico="";
    var hora="";
    var fecha="";
 if(c!=1){
	  hora=document.form1.txthora.value;
	  fecha= document.form1.txtfecha.value;
	for(var i=0; i<=c-1; i++){	
		if(form1.codExaCita[i].value!=""){
			d=form1.codExaCita[i].value;
			var e;
			var o;
			Resultado=document.form1.txtdescripcion[i].value; 
			Diagnostico=document.form1.txtDiagnostico[i].value;
		   /* for(e=0;e<Resultado.length;e++){
		    	Resultado=Resultado.replace('Ñ','@');
		    	Resultado=Resultado.replace('ñ','@');
		    	Resultado=Resultado.replace('Á','A');
		    	Resultado=Resultado.replace('É','E');
		    	Resultado=Resultado.replace('Í','I');
		    	Resultado=Resultado.replace('Ó','O');
		    	Resultado=Resultado.replace('Ú','U');
		    	Resultado=Resultado.replace('á','a');
		    	Resultado=Resultado.replace('é','e');
		    	Resultado=Resultado.replace('í','i');
		    	Resultado=Resultado.replace('ó','o');
		    	Resultado=Resultado.replace('ú','u');
		    }
		    for(o=0;o<Diagnostico.length;o++){
		    	Diagnostico=Diagnostico.replace('Ñ','@');
		    	Diagnostico=Diagnostico.replace('ñ','@');
		    	Diagnostico=Diagnostico.replace('Á','A');
		    	Diagnostico=Diagnostico.replace('É','E');
		    	Diagnostico=Diagnostico.replace('Í','I');
		    	Diagnostico=Diagnostico.replace('Ó','O');
		    	Diagnostico=Diagnostico.replace('Ú','U');
		    	Diagnostico=Diagnostico.replace('á','a');
		    	Diagnostico=Diagnostico.replace('é','e');
		    	Diagnostico=Diagnostico.replace('í','i');
		    	Diagnostico=Diagnostico.replace('ó','o');
		    	Diagnostico=Diagnostico.replace('ú','u');
		    	
		    }*/
		    
            enviar(d,Resultado,Diagnostico,hora,fecha,c);
			}
		oscar--; 
     }
 } 
 if(c=1){
	 hora=document.form1.txthora.value;
	 fecha= document.form1.txtfecha.value;
	 d=form1.codExaCita.value;
	 Resultado=document.form1.txtdescripcion.value;
	 Diagnostico=document.form1.txtDiagnostico.value;
	   /* for(e=0;e<Resultado.length;e++){
	    	Resultado=Resultado.replace('Ñ','@');
	    	Resultado=Resultado.replace('Á','A');
	    	Resultado=Resultado.replace('É','E');
	    	Resultado=Resultado.replace('Í','I');
	    	Resultado=Resultado.replace('Ó','O');
	    	Resultado=Resultado.replace('Ú','U');
	    	Resultado=Resultado.replace('ñ','@');
	    	Resultado=Resultado.replace('á','a');
	    	Resultado=Resultado.replace('é','e');
	    	Resultado=Resultado.replace('í','i');
	    	Resultado=Resultado.replace('ó','o');
	    	Resultado=Resultado.replace('ú','u');	    	
	    }
	    for(o=0;o<Diagnostico.length;o++){
	    	Diagnostico=Diagnostico.replace('Ñ','@');
	    	Diagnostico=Diagnostico.replace('Á','A');
	    	Diagnostico=Diagnostico.replace('É','E');
	    	Diagnostico=Diagnostico.replace('Í','I');
	    	Diagnostico=Diagnostico.replace('Ó','O');
	    	Diagnostico=Diagnostico.replace('Ú','U');
	    	Diagnostico=Diagnostico.replace('ñ','@');
	    	Diagnostico=Diagnostico.replace('á','a');
	    	Diagnostico=Diagnostico.replace('é','e');
	    	Diagnostico=Diagnostico.replace('í','i');
	    	Diagnostico=Diagnostico.replace('ó','o');
	    	Diagnostico=Diagnostico.replace('ú','u');
	    }*/
	  
	 
	 enviar(d,Resultado,Diagnostico,hora,fecha,c);
}
 }//fin de la funcion 
function enviar(d,Resultado,Diagnostico,hora,fecha,c){
	var i=-1;
	var j=0;
	var usuario="";
	usuario=<%=codigou%>;	
	ajax=objetoAjax();
	Resultado=encodeURIComponent(Resultado);
	Diagnostico=encodeURIComponent(Diagnostico);
	ajax.open("POST","ControlCitasExamen",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&codExaCita="+d+"&Resultado="+Resultado+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Diagnostico="+Diagnostico);
	/*if(c=1){
		alert("Examenes Realizados Satisfactoriamente!!!!");
		window.location.href="Imagenologia.jsp";
		}*/
}
</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
</head>
<body onload="img_grupo(),fecha_gru(),hora_gru();" id="back">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
</script> 
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Realizar Examenes-Examen Pendiente</b></div> 
</td>
</tr>

<tr id="principal1">
<td >

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO IMAGENOLOGIA</div>
					<br/><br/>  
						<%@include file="copymenyhi.jsp"%>		
			</td>
    
			<td width="100%">       
					<br />
 <%
String id=request.getParameter("id");String nombre=request.getParameter("nombre");String tip=request.getParameter("tipo");String z=request.getParameter("z");String cod=request.getParameter("cod");
String gene=request.getParameter("gene");
String edad=request.getParameter("edad");
if(id==null){
	id="";
};if(nombre==null){nombre="";};if(numero==null){numero="";};if(tip==null){tip="";};if(z==null){z="";};if(cod==null){cod="";};
if(edad==null){
	edad="";
}
%>
		
			
			 <form id="form1" style="margin-top:-17px;" name="form1" >  
			   <div id='examenes'></div>
					<input name="txthora" type="hidden" id="txthora" />
					<input name="txtfecha" type="hidden" id="txtfecha" />
			     </form>
</td></tr></table>
</div>
</td></tr></table>
</body> 
</html>
