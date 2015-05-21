

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<title>Ingreso de Pacientes</title>
<script language=javascript src="1.js"></script>
<script language=javascript src="Validaciones.js"></script>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>

</script>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script language="javascript">
function lab_paciente(e){	
	var nombre,apellidos,telefono,numero,eps,direccion,edad,tipo,email,genero;
    var ti=e.tipodoc.selectedIndex;
	tipo=e.tipodoc.options[ti].text;
	
	edad="";
	
	var ge=e.cbgenero.selectedIndex;
	genero=e.cbgenero.options[ge].text;
	
	edad=e.txtedad.value;
	
	  var time1 = new Date()
	  var acos = time1.getFullYear();
	
	numero=e.txtnumdocu.value;
	//alert("listo");alert("listo");
	apellidos=e.txtapellidos.value;

	nombre=e.txtnombres.value;
	//edad="0";
	
	telefono=e.txtele.value;
	direccion=e.txtdireccion.value;
	
	fechanaci=e.txtfechanaci.value;
	
	 for(i=0;i<direccion.length;i++){
	      direccion=direccion.replace('#','N');
	    }
	 
	 
	
	eps=e.txteps.value;

	email="ss";
	if(edad==""){
		edad="0";
		}
	
	if(fechanaci==""){
		 var ano=acos-edad;
		 
		fechanaci="01/01/"+ano;
		}
 
    if((nombre=="")||(apellidos=="")||(numero=="")||(telefono=="")||(genero=="SELECCIONE...")||(fechanaci=="")){
    	alert("Falta LLenar Campos Requeridos...!");
    }else{
    	//ajax=getXMLObject();
    	/*for(k=0;k<nombre.length;k++){
			 nombre=nombre.replace('Ñ','|');  
		    }
    	for(h=0;h<apellidos.length;h++){
    		apellidos=apellidos.replace('Ñ','|');  
		    }*/
    	nombre=encodeURIComponent(nombre)
    	apellidos=encodeURIComponent(apellidos)
		// alert("nombre  josimar  "+nombre);
    	/*ajax.open("GET", "Controllab_Pac");
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				if(ajax.status == 200) {
					alert("Ingreso Exitoso...!");
				}
			}
		}
		//como hacemos uso del metodo POST
		ajax.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
		//enviando el codigo del empleado
		ajax.send("fechana="+fechanaci+"&nom="+nombre+"&ape="+apellidos+"&ep="+eps+"&ced="+numero+"&eda="+edad+"&tele="+telefono+"&dire="+direccion+"&ema="+email+"&gene="+genero+"&ti="+tipo+"");*/
    	
    	window.location.href="Controllab_Pac?fechana="+fechanaci+"&nom="+nombre+"&ape="+apellidos+"&ep="+eps+"&ced="+numero+"&eda="+edad+"&tele="+telefono+"&dire="+direccion+"&ema="+email+"&gene="+genero+"&ti="+tipo+"",true;
    	alert("Ingreso Exitoso...!");	   
    }
    
}

</script>

</head>
<body onLoad="ajaxNacionalidad(),focus(form2.txtnumdocu);" id="back">
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
				//con.cerrar();
	       	%>


<table width="100%">
<tr align="right">
<td>
	  <div align="right" id="usuario" align="right" class="style11">Bienvenido, <b ><%=idu %></b> | <a href="Seguridad_login?accion=cerrar"   style="color: white"> --Cerrar Session--</a></div>
</td>
</tr>
<tr>
<td>
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="Laboratorio.jsp">Laboratorio</a>-Datos Demograficos-Ingreso Paciente</b></div>
</td>
</tr>

<tr  id="principal1">
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

			<td width="100%">                
					<br />
									<form id="form2" style="margin-top:-15px;"name="form2" onkeypress = "return pulsar(event);" class="margen1">
									<%
									String id=request.getParameter("id");
									String tip=request.getParameter("tipo");
									if(id==null){
										id="";
									}
									if(tip==null){
										tip="";
									}
									%> 
									<table width="100%"> 
									  <tr>
									    <td colspan="4" id="cabecera2" align="center"> <div class="style11">DATOS DEMOGRAFICOS</div> </td>
									  </tr>

<tr><td>&nbsp;</td></tr>
									  <tr class="style6">
									    <td ><span class="Estilo8"> Tipo Documento</span></td>
									    <td align="left"><label>
									      <select name="tipodoc" size="1" id="tipodoc">
									              <option selected="selected"><%=tip%></option>
									       
											 <%if(tip.equals("CC")){ %>
									        <option>TI</option>
									        <option>RC</option>
									        <option>Nacido Vivo</option>
									         <%} %>
									       <%if(tip.equals("TI")){ %>
									        <option>CC</option>
									        <option>RC</option>
									        <option>Nacido Vivo</option>
									         <%} %>
									         <%if(tip.equals("RC")){ %>
									        <option>TI</option>
									        <option>CC</option>
									        <option>Nacido Vivo</option>
									         <%} %>
									        <%if(tip.equals("Nacido Vivo")){ %>
									        <option>TI</option>
									        <option>RC</option>
									        <option>CC</option>
									         <%} %>
									       
									           <%if(tip.equals("")){ %>
									        <option selected>CC</option>
									        <option>TI</option>
									        <option>RC</option>
									      <option>Nacido Vivo</option>
									         <%} %>
									        </select>
									      <span class="style1"><span class="style8">*</span><br>
									      </span></label></td>
									    <td><span class="Estilo8">Nº Identificacion</span></td>
									    <td><label>
									 <input name="txtnumdocu" type="text" id="txtnumdocu" onKeyUp="validarte(form2.txtnumdocu);" onChange="datos();"  maxlength="12" value="<%=id %>"/>
									    <span class="style8">*</span></label></td>
									  </tr>

									  <tr class="style6">
									    <td><span class="Estilo8"> Apellidos</span></td>
									    <td><label>
									      <input name="txtapellidos" type="text" id="txtapellidos" onKeyUp="this.value=this.value.toUpperCase();"  maxlength="50" value="" />
									      <span class="style8">*</span></label></td> 
									    <td><span class="Estilo8">Nombres</span></td>
									    <td><label>
									       <input name="txtnombres" type="text" id="txtnombres" onKeyUp="this.value=this.value.toUpperCase();"   maxlength="50" value=""/>
									       <span class="style1"><span class="style8">*</span><br>
									       </span></label></td>
									  </tr>

									<tr class="style6">
									    <td><span class="Estilo8">Edad</span></td>
									    <td><label>
									      <input name="txtedad" type="text" id="txtedad" onKeyUp="validarte(form2.txtedad);" onChange=""  maxlength="100"/>
									    <span class="style8">*</span></label></td> 
									    <td><span class="Estilo8">Telefono</span></td>
									    <td><label>
									 <input name="txtele" type="text" id="txtele" onKeyUp="validarte(form2.txtele);"  maxlength="11" value="" />
									      <span class="style1"><span class="style8">*</span><br>
									      </span></label></td>
									  </tr>  

									   <tr class="style6">
									    <td><span class="Estilo8">Direccion</span></td>
									    <td><label>
									        <input  name="txtdireccion"  type="text" id="txtdireccion"/>
									    </label></td>
									    <td><span class="Estilo8">EPS</span></td>
									    <td><label>
									      <input name="txteps" type="text" id="txteps"  onKeyUp="this.value=this.value.toUpperCase(),caracter(form2.txteps);"value="" maxlength="50"/>
									      <br>
									    </label></td>
									  </tr>

									    <tr class="style6">
									      <td><span class="Estilo8">Fecha Nacimiento(ddmmaaaa)</span></td>
									      <td><span class="style4">
									        <input type="text" name="txtfechanaci" id="txtfechanaci"   onKeyUp="masca(this,'/',patron,true)" maxlength="10"/>
									      </span></td>
									      <td><span class="Estilo8">Genero</span></td>
									      <td><select name="cbgenero" id="cbgenero" >
									        <option selected="selected">SELECCIONE...</option>
									        <option>Masculino</option>
									        <option>Femenino</option>
									      </select>
									        <span class="style8">*</span></td>
									    </tr>
									  <td>
 										<td>&nbsp;</td> 
									  </tr>

									  <trclass="style6">
									    <td><span class="style8">Campos Requeridos (*)</span></td>
									    <td align="right"><input type="button" class="boton4" name="btingresar" id="btingresar" value="     Ingresar    " onClick="lab_paciente(form2);" /></td>
									    
									    <td>&nbsp;</td> 
									  </tr>
									   
									</table>
									  
										
									</form>
						</td>
					</tr>
			</table>

</div>
</td></tr></table>
	
<%} %>
</body>
</html>