

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=utf8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>Realizacion de Examen</title>

<style type="text/css">
#sugerencias  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerencias { border:1px solid black; display:none; margin-left:0.8px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
#sugerenciasRe  li:hover{Cursor : pointer; background-color: #cccccc; }
#sugerenciasRe { border:1px solid black; display:none; margin-left:0.8px;}
#sugerenciasRe ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerenciasRe ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:auto; background-color: #cccccc;j=1 ; desbordamiento: auto;} 
.scrollbox {height : 90px ; overflow:scroll}
</style>

<script language=javascript src="1.js"></script>
<script language=javascript src="Laboratorio.js"></script>
<script language=javascript src="Laboratorio1.js"></script>
<script language=javascript src="autocompletar_exa.js"></script>
<script language=javascript src="lab_autoRealizarExamen.js"></script>
<script language=javascript src="Validaciones.js"></script>
<script language="javascript">
function fecha_gru(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	 document.getElementById('fechagru').value = temp1
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
	  document.getElementById('horagru').value = temp;
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
function validarcom(tecla, e)
	{ 
	    
		opc = false;
		var s=document.getElementById('txtnumdoc').value;
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
			window.alert('El campo  solo acepta valores Numericos');
			document.getElementById('txtnumdoc').value="";
			document.getElementById('txtnumdoc').focus();
			return false;
		}*/
		
		tecla =   e.keyCode;  e.which;
	if(tecla == 13)
      {
		var gen=document.getElementById('cbafiliacion').selectedIndex;
		tipo=document.getElementById('cbafiliacion').options[gen].text;
	        cedula=document.getElementById('txtnumdoc').value;
	        if(cedula==""){
		        alert("Digite cedula...!");
	        }else{
	        	
	        	window.location.href="ControlExamen?tipo="+tipo+"&ced="+cedula+"",true;
				
	        }	
		}
	}

function Abrir_ventana_yosi (pagina) {

	
	var tipo="";
	 tipo=document.form1.tipo2.value;
   var exa=document.form1.nombre.value;
	var cedu=document.form1.txtnumdoc.value;
	var docu = document.form1.cbafiliacion.selectedIndex;
	var documento=document.form1.cbafiliacion.options[docu].text;
	
	
	var area=document.form1.area.value;
	 var seleccion=document.form1.miradio;
	 var usu=document.form1.txtusu.value;

	for(i=0;i<seleccion.length;i++){
	        if(seleccion[i].checked){
		      var valor= seleccion[i].value;
	        }
	 }
      
	
	if(tipo=="0"){
		
		pagina="lab_Examen_texto.jsp?area="+area+"&valor="+valor+"&exa="+exa+"&ced="+cedu+"&documento="+documento+"&usu="+usu+""
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=no, width=450, height=240, top=225,  left=500";
   window.open(pagina,"NUEVA",opciones);  
	}else{
	if(tipo=="1"){            
		var edad=document.form1.txtedad.value;
		var gene=document.form1.txtsexo.value;  
		pagina="lab_Examen_rango.jsp?area="+area+"&valor="+valor+"&exa="+exa+"&ced="+cedu+"&documento="+documento+"&edad="+edad+"&gene="+gene+"&usu="+usu+""
		    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
			opciones =opciones+"scrollbars=si, resizable=no, width=465, height=240, top=225,  left=500";
		   window.open(pagina,"NUEVA",opciones);
	}else{
		//alert("Seleccione Examen...");
		}
	}
}
function Abrir_ventana (pagina) {
	var tipo="";
	 tipo=document.form1.tipo.value;
	var exa=document.form1.examen.value;
	var cedu=document.form1.txtnumdoc.value;
	var docu = document.form1.cbafiliacion.selectedIndex;
	var documento=document.form1.cbafiliacion.options[docu].text;
	var area=document.form1.area.value;

	var usu=document.form1.txtusu.value;
	
	 var seleccion=document.form1.miradio;


	 for(i=0;i<seleccion.length;i++){
	        if(seleccion[i].checked){
		      var valor= seleccion[i].value;
	        }
	 }
      
	 
	if(tipo=="0"){
		
		pagina="lab_Examen_texto.jsp?area="+area+"&valor="+valor+"&exa="+exa+"&ced="+cedu+"&documento="+documento+"&usu="+usu+""
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=si, resizable=no, width=450, height=240, top=225,  left=500";
   window.open(pagina,"NUEVA",opciones);
	}else{
	if(tipo=="1"){
		var edad=document.form1.txtedad.value;
		var gene=document.form1.txtsexo.value;  
		pagina="lab_Examen_rango.jsp?area="+area+"&valor="+valor+"&exa="+exa+"&ced="+cedu+"&documento="+documento+"&edad="+edad+"&gene="+gene+"&usu="+usu+""
		    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
			opciones =opciones+"scrollbars=si, resizable=no, width=465, height=240, top=225,  left=500";
		   window.open(pagina,"NUEVA",opciones);
	}else{
		alert("Seleccione Examen...");
		}
	}
}
</script>


</head>
<body onload="lab_area(),fecha_gru(),hora_gru();" id="back" >
<script> 
if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} 

</script> 
<%
String codigou =(String)session.getAttribute("codusuario");
if(codigou==null){
	codigou="";
}
//System.out.println("codigo"+codigou);
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
			//	con.cerrar();
	       	%>

<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Realizacion de Examenes-Realizar Examen</b></div>
</td>
</tr>

<tr  id="principal1" >
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-15px;" class="style11" align="center">MODULO LABORATORIO</div>
					<br/><br/>
								<%@include file="Copiamenu.jsp"%>
			</td>
 <%
String id=request.getParameter("id");
 String nombre=request.getParameter("nombre");
 String numero=request.getParameter("numero");
 String tip=request.getParameter("tipo");
 String z=request.getParameter("z");
 String cod=request.getParameter("cod");
String gene=request.getParameter("gene");
String edad=request.getParameter("edad");
if(id==null){
	id="";
};if(nombre==null){nombre="";};if(numero==null){numero="";};if(tip==null){tip="";};if(z==null){z="";};if(cod==null){cod="";};
if(edad==null){
	edad="";
}
%>
					<td width="100%">
						<br> 
						 <form id="form1" style="margin-top:-17px;" name="form1" onkeypress = "return pulsar(event);" >
						
						   <table width="100%"  align="center" class="style6" >   
						   
						     <tr id="cabecera2">
						       <td colspan="5"><div align="center" class="style11">DATOS DEL PACIENTE </div></td>
						     </tr>
						
						     <tr>
						       <td>&nbsp;</td>
						       <td>&nbsp;</td>
						       <td width="65">&nbsp;</td>
						       <td width="328">&nbsp;</td>
						     </tr>
						     <tr>
						       <td>Tipo de Documento</td>
						       <td><select name="cbafiliacion" size="1" id="cbafiliacion">
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
	<%if(z.equals("1")){%>
		<OPTION selected="selected"><%=tip%></OPTION>
	            
    <%}%>
						       </select></td>
						       <td>Numero</td>
						       <td><input type="text" name="txtnumdoc" id="txtnumdoc" onkeyup="validarcom(this,event),autocompleta_realizar();" maxlength="12" value="<%=numero %>" /></td>
						     </tr>
						     <tr>
						       <td width="177">&nbsp;</td>
						       <td width="110"> <label></label>  </td>
						       <td colspan="2"><div id="sugerenciasRe"></div></td>   
						       </tr>
						<%
						if(id.equals("1")){
						%>                                         
						     <tr>
						       <td height="38" colspan="4"> <label>Nombre Del Paciente: <b class="style9"><%=nombre%></b>
						    </label></td><td> <input type="hidden" name="cod" id="cod" value="<%=cod %>"/></td>
						     </tr>
						   </table>
						     <table width="100%" align="center" >
						     <tr>
						       <td colspan="5"> <div align="center" id ="cabecera2" class="style11">REALIZAR EXAMEN </div></td>
						       </tr>
						     <tr> 
						       <td>&nbsp;</td>
						       <td>&nbsp;</td>
						       <td colspan="3">&nbsp;</td>
						     </tr>
						     <tr>
						       <td width="20%" class="style6">Elija Grupo </td> 
						       <td width="156"> <label></label>  <label>
						         <select name="cblabarea" size="1" id="cblabarea" onChange="ocultar();"  >
						          <option selected="selected">SELECCIONE...</option>
						         </select>
						       </label></td>
						       <td colspan="3" id="cabecera2">
						         <div align="center" class="style11">Clasificacion de Examen </div>
						       </td>
						     </tr>
						     <tr>
						       <td>&nbsp;</td>
						       <td>&nbsp;</td>
						       <td width="126"><label>
						       <div align="center" class="style6">
						         <input name="miradio" type="radio" value="1"  onclick="tu(form1);" /> 
						         Individual</div>
						       </label></td>
						       <td width="97"><div align="center" class="style6"><br> 
						         <input name="miradio" type="radio" value="2" onclick="grupo(form1);" />
						         Grupo </div></td>
						       <td width="278"><label>
						         <div align="center" class="style6"> 
						           <input name="miradio" type="radio" value="3" onclick="lista(form1);" />
						           Lista General </div>
						         </label></td>
						     </tr>
						   </table>
						<table width="100%" align="center" class="style6">
						          <tr>
						            <td colspan="3"><div id="de"  style="display:none"></div>
						 		  </td>

						          </tr>
						          <tr><td><div id="examen" style="display:none" ></div></td><td width="633"><input name="exa" type="button" class="boton4" id="exa" value="Realizar" onclick="Abrir_ventana('yo','Ayuda','width=2000,height=500')" style="display:none"/></td><td width="56"><div id="tipo"></div></td>
						          </tr>
						           <tr><td></td></tr>
						   </table>
<div class="scrollbox4">
						      <table width="100%" border="0">
						        <tr>
						          <td width="149"></td>
						          <td width="265"><div id="dios" style="display:none"></div></td>
						         
						        </tr>
						        <tr>
						          <td><label>
						           <input name="tipo2" type="hidden" id="tipo2" />
						            <input name="area" type="hidden" id="area" />
						            <input type="hidden" name="txtsexo" id="txtsexo" value="<%=gene %>"/>
									<input type="hidden" name="txtedad" id="txtedad" value="<%=edad %>"/>
						            <input type="hidden" name="txtusu" id="txtusu" value="<%=codigou %>"/>
									<input type="hidden" name="txtCodPac" id="txtCodPac" value="<%=cod %>"/>
						          </label></td>
						          <td><input name="fechagru" type="hidden" id="fechagru"  /></td>
						          <td width="268"><input name="horagru" type="hidden" id="horagru"  /></td>
						        </tr>
						      </table>
						      <p>
						        <%}%>
						      </p>
						</div>
						 </form>
					</td>
				</tr>
		</table>
</div>
</td></tr></table>
    <%}%>
</body>
</html>
