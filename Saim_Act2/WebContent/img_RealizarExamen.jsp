
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@page pageEncoding="UTF-8"%>
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
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script src="Imaginologia.js" type="text/javascript"></script>
<script languaje="javascript" src="img_BuscarExamPaciente.js"></script>
<script languaje="javascript" src="img_autocompletaPaciente.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Realizar de Examen</title>

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:300px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias {height : 100px ; Cursor : pointer; overflow : scroll ; desbordamiento: auto;}
</style>
<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }
#sugerencias1 li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias1 {width:350px; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias1 ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias1 ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
#sugerencias1 {height : 100px ; overflow : scroll ; desbordamiento: auto; Cursor : pointer;}
</style>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}%>
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

function Mostrar() {	
	if (xmlhttp.readyState == 4) {		
		if(xmlhttp.status == 200) {		
			document.getElementById('cambio').innerHTML = xmlhttp.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,getCheckedRadio");
	     }
	   }
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
	 id = setTimeout("fecha()",1000)
	  
	 
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
	  id = setTimeout("hora()",1000)
	  }
function validar(h){
		if (h.txtnumdoc.value.length==0){
			window.alert('Debe Digitar la Cedula');
			h.nom.focus();
			return false;
		}
	h.submit();
	}
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
function validarcom(tecla, e){ 
	    var i;
		opc = false;
		var s=document.getElementById('txtnumdoc').value;
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
			window.alert('El campo Numero Documento solo acepta valores Numericos');
			document.getElementById('txtnumdoc').value="";
			return false;
		}
		tecla =   e.keyCode;  e.which;
	if(tecla == 13)//Codigo Ascii de la tecla ENTER evento si se presiona enter
      {
		var tipo=document.getElementById('cbafiliacion').value;		
	    var cedula=document.getElementById('txtnumdoc').value;
	        if(cedula==""){
		        alert("Digite cedula...!");
	        }else{
	        	DivValor=document.getElementById('examenes')
	        	ajax=objetoAjax();
	        	//if(xmlhttp) { 
		        	//alert(cedula)		 	 
	        	    ajax.open("POST","ControlCitasExamen",true); //getname will be the servlet name
	        	    ajax.onreadystatechange  =function(){
	        			if (ajax.readyState == 4) {
	        				PatronNormal=ajax.responseText;
	        				var g;
	        				/* for(g=0;g<PatronNormal.length;g++){
	        				    	PatronNormal=PatronNormal.replace('@','Ñ');
	        				    }*/
	        	  			DivValor.innerHTML=PatronNormal;
	        	  	   	}  	
	        		}
	        	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	        	    ajax.send("valor=4&cedula="+cedula); //Posting txtname to Servlet
	        		//  }
	        	//window.location.href="ControlExamenImag?tipo="+tipo+"&ced="+cedula+"",true;
	        }
		}
	}
////////////////////////////////////////////////////////////////////////
function getCheckedRadio() {
	ajax=objetoAjax();
		 var radioButtons = document.getElementsByName("radiobutton");
	      for (var x = 0; x < radioButtons.length; x ++) {
	        if (radioButtons[x].checked) {
		         var val=radioButtons[x].id;    
		    ajax.open("POST","ControlPacientesPendientes",true); //getname will be the servlet name
		    ajax.onreadystatechange  = Mostrar;
		    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor="+val); //Posting txtname to Servlet
			  }	     
	    }
}
function Mostrar() {	
	if (ajax.readyState == 4) {		
		if(ajax.status == 200) {		
			document.getElementById('cambio').innerHTML = ajax.responseText
	     }
	     else {
	        alert("Error during AJAX call. Please try again,getCheckedRadio");
	     }
	   }
}
////////////////////////////////////////////////////////////////////////
function buscar(){
    var cedula=document.getElementById('txtnumdoc').value;
    var tipodoc=document.getElementById('txttipodoc').value;
        if(cedula==""){
	        alert("Digite cedula...!");
        }else{
        	DivValor=document.getElementById('examenes')
        	ajax=objetoAjax();
        	//if(xmlhttp) { 
	        	//alert(cedula)		 	 
        	    ajax.open("POST","ControlCitasExamen",true); //getname will be the servlet name
        	    ajax.onreadystatechange  =function(){
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
        	    ajax.send("valor=4&cedula="+cedula+"&tipodoc="+tipodoc); //Posting txtname to Servlet
        		//  }
        	//window.location.href="ControlExamenImag?tipo="+tipo+"&ced="+cedula+"",true;
        }
	
}
////////////////////////////////////////////////////////////////////////
function enviar(d,Resultado,Diagnostico,hora,fecha,c){
	var i=-1;
	var j=0;
	var usuario="";
	usuario=<%=codigou%>;	
	ajax=objetoAjax();
	Resultado=encodeURIComponent(Resultado)
	Diagnostico=encodeURIComponent(Diagnostico)
	ajax.open("POST","ControlCitasExamen",true);
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&codExaCita="+d+"&Resultado="+Resultado+"&hora="+hora+"&fecha="+fecha+"&usuario="+usuario+"&Diagnostico="+Diagnostico);
	
}
////////////////////////////////////////////////////////////////////////
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
		    	Resultado=Resultado.replace('Á','A');
		    	Resultado=Resultado.replace('É','E');
		    	Resultado=Resultado.replace('Í','I');
		    	Resultado=Resultado.replace('Ó','O');
		    	Resultado=Resultado.replace('Ú','U');
		    	Resultado=Resultado.replace('ñ','@');
		    	Resultado=Resultado.replace('á','a');
		    	Resultado=Resultado.replace('é','é');
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
		oscar--; 
     }
 } 
 if(c=1){
	 hora=document.form1.txthora.value;
	 fecha= document.form1.txtfecha.value;
	 d=form1.codExaCita.value;
	 Diagnostico=document.form1.txtDiagnostico.value;  
	 Resultado=document.form1.txtdescripcion.value; 
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

///////////////////////////////////////////////////////////
function finalizar(){
	alert("Examenes Realizados Satisfactoriamente!!!!");
	window.location.href="Imagenologia.jsp";
}
function mostarexamenes (cedula,codigoIce) {
	window.location.href="img_RealExamPacPen.jsp?cedula="+cedula+"&codigoIce="+codigoIce;
}
function omitir(codigo){
	if(xmlhttp) { 		 	  
		xmlhttp.open("POST","ControlPacientesPendientes?valor=3&codigo="+codigo,true); //getname will be the servlet name
		xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		xmlhttp.send(""); //Posting txtname to Servlet
	} 
	window.location.reload();

}
</script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
</head>
<body onload="fecha_gru(),hora_gru();" id="back">
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 
</script> 
<%
if(codigou.equals("")){%>

<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");window.location.href="Seg_login.jsp";</script>
<%}else{
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Imagenologia.jsp">Imagenologia</a>-Realizar Examen</b></div>   
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
String id=request.getParameter("id");String nombre=request.getParameter("nombre");String numero=request.getParameter("numero");String tip=request.getParameter("tipo");String z=request.getParameter("z");String cod=request.getParameter("cod");
String gene=request.getParameter("gene");
String edad=request.getParameter("edad");
if(id==null){
	id="";
};if(nombre==null){nombre="";};if(numero==null){numero="";};if(tip==null){tip="";};if(z==null){z="";};if(cod==null){cod="";};
if(edad==null){
	edad="";
}
%>
				
				
				 <form id="form1" style="margin-top:-15px;" class="margen1"  name="form1" onkeypress = "return pulsar(event);" >
				
				   <table width="100%" class="style6"  align="center">
				
				     <tr id="cabecera2" class="style11">
				       <td colspan="4"><div align="center" class="style4">DATOS DEL PACIENTE </div></td>
				     </tr>
				
				     <tr>
				       <td>&nbsp;</td>
				       <td><label>
				         <input name="txtfecha" type="hidden" id="txtfecha" />
				       </label></td>
				       <td width="161">&nbsp;</td>
				       <td width="382"><label>
				         <input name="txthora" type="hidden" id="txthora" />
				       </label></td>
				     </tr>
				     <tr>
				       <td ><input name="radiobutton" type="radio" value="4" id="4" onclick="getCheckedRadio()"/>NOMBRE</td>
				       <td><input name="radiobutton" type="radio" value="5" id="5" onclick="getCheckedRadio()"  />IDENTIFICACION</td>
				       <td colspan="2"><label><div id='cambio'></div></label></td>
				       </tr>
				     <tr>
				       <td><label></label></td>
				       <td><label></label></td>
				       <td colspan="2"><div id='sugerencias'></div>         </td>
				       </tr>
					 <tr>
				    <td colspan="4"><div id="resultado" style="display:none" ></div></td>
				    </tr>
				   </table>
				<input name="txttipodoc" type="hidden" id="txttipodoc" />
				<div id='examenes'></div>
				     </form>
</td></tr></table>
</div>
</td></tr></table> 
	 <%} %>
 
</body>
</html>
