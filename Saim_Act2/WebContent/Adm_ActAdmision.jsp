
<%@ page language="java" contentType="text/html; charset=utf-8"
import="adm_logica.Conexion" import="java.sql.*" pageEncoding="utf-8"%>
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
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<title>Actualizar Admision</title><script language="javascript">
function enter(h,tecla, e)
	{
	
		tecla =   e.keyCode;  e.which;
	if(tecla == 13)//Codigo Ascii de la tecla ENTER evento si se presiona enter
      {
	       var tipo,cedula,codti;
	       tipo=h.cbafiliacion.value;
	       cedula=h.txtnumdoc.value;
	       if(tipo==""){
		       alert("Seleccione Tipo Documento.")
		       }
	       if(cedula==""){
		        alert("Digite cedula.");
	        }
	        
	        if((cedula!="")&&(tipo!="")){
	         window.location.href="ControlSelAct?tipo="+tipo+"&ced="+cedula+"",true;
				
	        }
	      
	       
		}
	}
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}
</script>

<script language=javascript src="Validaciones.js"></script>

<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
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
				
				
				ResultSet rs4;
				Statement st4=null;
				st4=con.conn.createStatement();
				rs4=st4.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
				int madmin=0;
				if(rs4.next()){
					madmin=rs4.getInt(1);
				}
				rs4.getStatement().close();
				
				con.conn.close();
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
<tr>
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
	<div id="cabecera1" align="center" class="style6"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Actualizacion-Actualizar Admision</b></div>
</td>
</tr>

<tr  id="principal1">
<td>

<div>

	<table width="100%"> 
		<tr>
			<td width="100%" id="lateral4" class="style6">
<br />
					<div id="cabecera2" style="margin-top:-16px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/><br/>
								<%@include file="CopyAdm.jsp"%>
			</td>

			<td width="100%">   
					<br />
<%
String r=request.getParameter("r");
String cedu=request.getParameter("cedu");
String tipo=request.getParameter("tipo");
String nom=request.getParameter("nom");
String ingreso=request.getParameter("ingreso");
String numeauto=request.getParameter("numeauto");
String medioauto=request.getParameter("medioauto");
String auto=request.getParameter("auto");
String registro=request.getParameter("registro");
String fecha=request.getParameter("fecha");
String hora=request.getParameter("hora");
String estado=request.getParameter("estado");
String remision=request.getParameter("remision");
String semana=request.getParameter("semana");
//contacto
String codigocon=request.getParameter("codigocon");
String nomaco=request.getParameter("nomaco");
String apeaco=request.getParameter("apeaco");
String direaco=request.getParameter("direaco");
String teleaco=request.getParameter("teleaco");
String ceduaco=request.getParameter("ceduaco");
String parenaco=request.getParameter("parenaco");
//System.out.println("Este es el codigo del contacto: "+direaco);

if(r==null){
	r="";
}
if(tipo==null){
	tipo="";
}
if(cedu==null){
	cedu="";
}
if(ingreso==null){
	ingreso="";
}
if(numeauto==null){
	numeauto="";
}
if(medioauto==null){
	medioauto="";
}
if(auto==null){
	auto="";
}
if(registro==null){
	registro="";
}
if(fecha==null){
	fecha="";
}
if(hora==null){
	hora="";
}
if(estado==null){
	estado="";
}
if(remision==null){
	remision="";
}
if(semana==null){
	semana="";
}
if(nom==null){
	nom="";
}
//contacto
if(codigocon==null){
	codigocon="";
}
if(nomaco==null){
	nomaco="";
}
if(apeaco==null){
	apeaco="";
}
if(direaco==null){
	direaco="";
}
if(teleaco==null){
	teleaco="";
}
if(ceduaco==null){
	ceduaco="";
}
if(parenaco==null){
	parenaco="";
}

%>
<%
if(r.equals("1")){%>
	<script>alert("Admision No Encontrado...y/o esta Cerrada!!!!");</script>
<%} %>

<form id="form1" style="margin-top:-16px;" name="form1"  onkeypress = "return pulsar(event);">
  <table width="100%" class="style6" align="center">
    <tr> 
      <td colspan="6" id="cabecera2"><div align="center">
        <p class="style11">ACTUALIZAR ADMISION </p>
      </div></td>
    </tr>
    <tr>
      <td width="133" align="right">Tipo de Documento</td>
      <td width="104"><label>
        <select name="cbafiliacion" size="1" id="cbafiliacion">
		<option value='<%=tipo%>' ><%=tipo%></option>
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
 	//st3.close();
 	rs3.getStatement().close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
          
        </select>
        </label>
      </td>
      <td width="162" align="right">Numero de Documento</td>
      <td width="155"><label>
        <input type="text" name="txtnumdoc" id="txtnumdoc" onKeyUp="enter(form1,this,event),validarte(form1.txtnumdoc);"  value="<%=cedu %>"/>
      </label></td>
      <input type="hidden" name="txturgencia" id="txturgencia"  />
      <td width="49">&nbsp;</td>
      <td width="195"><label></label></td>
    </tr>
  </table>
  <table width="100%" class="style6" align="center">
<tr><td>&nbsp;</td></tr>
    <tr >
      <%if(r.equals("0")){ %>
      <td colspan="6"><label>Nombre Del Paciente: <b class="style9"><%=nom%></b></label></td>
    </tr>
    <tr>
 <tr><td>&nbsp;</td></tr>
      <td colspan="6" id="cabecera2"><div align="center">
          <p class="style11">DATOS A CAMBIAR </p>
      </div></td>
    </tr>

  </table>

  <table width="100%" class="style6" align="center"> 
<tr><td>&nbsp;</td></tr>
    <tr>
      <td width="161">Medio de Autorizacion</td>
      <td width="203"><label>
        <input type="text" name="txtmedio" id="txtmedio" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtmedio);" maxlength="20" value="<%=medioauto %>" />
        <span class="style8">* </span></label></td>
      <td width="160">Numero de Autorizacion</td>
      <td width="233"><label>
        <input type="text" name="txtnumauto" id="txtnumauto" maxlength="20" value="<%=numeauto %>"/>
      </label></td>
    </tr>
    <tr>
      <td>Autorizado Por:</td>
      <td><label>
        <input type="text" name="txtautorizado" id="txtautorizado" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtautorizado);" maxlength="20" value="<%=auto %>" />
        <span class="style8">*</span></label></td>
      <td>Fecha de Registro</td>
      <td><label>
        <input type="text" name="txtfecha" id="txtfecha" value="<%=fecha %>"/>
        <span class="style8">*</span></label></td>
    </tr>
    <tr>
      <td>Registrado Por:</td>
      <td><label>
        <input type="text" name="txtregistro" id="txtregistro" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtregistro);" maxlength="20" value="<%=registro %>" readonly=""/>
        <span class="style8">*</span></label></td>
      <td>Hora De Registro</td>
      <td><label>
        <input type="text" name="txthora" id="txthora" value="<%=hora %>" />
        <span class="style8">*</span></label></td>
    </tr>
    <tr>
      <td>Remitido De</td>
      <td><input type="text" name="txtremitido" id="txtremitido" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtremitido);" maxlength="20" value="<%=remision %>" /></td>
      <td>Semanas Cotizadas</td>
      <td><input type="text" name="txtsemana"  id="txtsemana" onKeyUp="validarte(form1.txtsemana);" value="<%=semana %>" />
      <span class="style4">*</span></td>
    </tr>
    <tr>
      <td>Estado de la Afiliacion</td>
      <td><label>
      <select name="cbafi" size="1" id="cbafi">
       <option selected><%=estado%></option>
        <%if(estado.equals("ACTIVO")){ %>
        <option>INACTIVO</option>
         <%} %>
        <%if(estado.equals("INACTIVO")){ %>
        <option>ACTIVO</option>
         <%} %>
      </select>
    
      <span class="style8">*</span></label></td>
      <td>&nbsp;</td>
      <td><label>
      <input name="txtingreso" type="text" id="txtingreso" value="<%=ingreso%>" style="visibility:hidden" />
      </label></td>
    </tr>

<tr><td>&nbsp;</td></tr>
  </table>
  <table width="100%" class="style6" align="center">
    <tr>
      <td colspan="4" id="cabecera2"><div align="center" class="style11">DATOS DEL ACOMPA&Ntilde;ANTE </div></td>
    </tr>
    <tr>
<tr><td>&nbsp;</td></tr>
      <td width="165">Nombres</td>
      <td width="208"><label>
        <input type="text" name="txtnomco" id="txtnomco" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtnomco);" maxlength="20" value="<%=nomaco%>" />
        <span class="style8">* </span></label></td>
      <td width="164">Apellidos</td>
      <td width="245"><label>
        <input type="text" name="txtapeacon" id="txtapeacon" maxlength="20" value="<%=apeaco%>" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtapeacon);" />
      </label></td>
    </tr>
    <tr>
      <td>Cedula</td>
      <td><label>
        <input type="text" name="txtceduacon" id="txtceduacon" onKeyUp="this.value=this.value.toUpperCase(),validarte(form1.txtceduacon);" maxlength="20" value="<%=ceduaco%>" />
        <span class="style8">*</span></label></td>
      <td>Telefono</td>
      <td><label>
        <input type="text" name="txtteleacon" id="txtteleacon" onKeyUp="validarte(form1.txtteleacon);" value="<%=teleaco%>" />
        <span class="style8">*</span></label></td>
    </tr>
    <tr>
      <td>Direccion</td>
      <td><input type="text" name="txtdireacon" id="txtdireacon" onKeyUp="this.value=this.value.toUpperCase();" maxlength="30" value="<%=direaco%>" /></td>
      <td>Parentesco</td>
      <td><label>
        <select name="cbparenacon" id="cbparenacon">
		  <option>ESPOSO(A)</option>
          <option>PADRES</option>
          <option>ABUELO(a)</option>
          <option>HIJO</option>
          <option>HERMANO(a)</option>
          <option>TIO(a)</option>
          <option>SOBRINO(a)</option>
          <option>PRIMO(a)</option>
          <option>AMIGO(a)</option>
          <option>CONOCIDO</option>

         
        </select>
        <span class="style8">*</span></label></td>
    </tr>
    <tr>
      <td class="style8">Campos Requeridos * </td>
      <td>&nbsp;</td>
      <td><label>
        <input name="txtcodcon" type="text" id="txtcodcon" value="<%=codigocon%>" style="visibility:hidden" />
      </label></td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><label>
      <input type="button" name="Act" class="boton4" value="Actualizar" onClick="ActAdm(form1);" />
      </label></td>
      <td><label></label></td>
      <td><label></label></td>
    </tr>
  </table>
<%} %>

</form>
</td></tr></table>
</div>
</td></tr></table>

<%} %>
	
</body>
</html>