<%-- 
    Document   : index
    Created on : 27/08/2012, 05:13:40 PM
    Author     : Desarrollo 2
--%>


<%@page import="java.util.Vector"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
import="adm_logica.Conexion" import="java.sql.*" pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page language="java"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Analisis Odontologico</title>
        <script src="odont_AnalisisOdontologico_canvas.js"></script>
        <link rel="stylesheet" href="odontologiaBotones.css" type="text/css" />
    <link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
    <link rel="SHORTCUT ICON" href="Imagenes/IconO.ico">
    <link rel="stylesheet" href="thickbox.css" type="text/css" />
   <script lang="javascript" type='text/javascript' src='Browser.js'></script>
    <!--[if IE]><script type="text/javascript" src="excanvas.js"></script><![endif]-->
    <script lang="javascript">
	function A(e) {
		tecla = e.keyCode;
		e.which;
		if (tecla == 13) {
			pacienteActivo();
		}
	}
	function pulsar(e) {
		tecla = (document.all) ? e.keyCode : e.which;
		return (tecla != 13);
	}
</script>
<script lang="javascript">
function Verificar(e)
{
	
	var minavegador = new Browser();
	if (minavegador.name == 'firefox'){
	    if(e) 
		document.onkeypress = function(){return true;} ;
		var evt = e?e:event; 
		if(evt.keyCode==116) 
		{ 
		if(e) 
		document.onkeypress = function(){return false;} ;
		else 
		{ 
		evt.keyCode = 0; 
		evt.returnValue = false; 
		} 
		} 
	}
}
</script>
    
    
    
    
    </head>





<body onload="document.getElementById('txtnumdoc').focus();">
<%
		String codigou =(String)session.getAttribute("codusuario");
	%>
	<%
		Conexion con=new Conexion();
		ResultSet rs;
		Statement st = null;
		String idu="";         
		st = con.conn.createStatement();
		rs=st.executeQuery("select usuario from seg_usuario where usu_codigo="+codigou);
	%>
	<%
		while(rs.next()){
		idu=rs.getString(1);
		}
		rs.getStatement().close();
		
		
		st=null;
		st=con.conn.createStatement();
		rs=st.executeQuery("SELECT COUNT(estado) FROM seg_mensajes WHERE estado=0 AND remitente="+codigou+"");
		int madmin=0;
		if(rs.next()){
			madmin=rs.getInt(1);
		}
		rs.getStatement().close();
		con.cerrar();
	%>
	<%
		String cod=request.getParameter("codigo");
	if(codigou==null){
		codigou="";
	}
	if(codigou.equals("")){ //seguridad
	%>
	<script lang="javascript">
		alert("Usted No Tiene Permiso Para ver esta Pagina....");
		window.location.href = "Seg_login.jsp";
	</script>
	<%
		} else {
	%>

<table width="100%">
    <tr>
	    <td>
 	        <table width="100%">
		        <tr>
		            <td>
		                <div>
		                    <a href="mensajes.jsp?TB_iframe=true&height=520&width=700" class="thickbox"> Mensajes <font color=red size=medium><b><%=madmin%></b></font></a>
		                    <%if (madmin > 0) {%>
		                    <bgsound src="Imagenes/INNERMK.WAV" loop="2">
		                    <img src="Imagenes/sobr0001.gif" />
		                    <%}%>
		                </div>
		            </td>
		            <td align="right">
		                <div align="right" id="usuario" align="right" class="style11">
		                Bienvenido,<b><%=idu%></b> |<a
		                href="Seguridad_login?accion=cerrar&CodUsu=<%=codigou%>"
		                style="color: white">--Cerrar Session--</a>
		                </div>
		            </td>
		        </tr>
		    </table>
		</td>
	</tr>

	<tr>
	    <td>
	        <div id="cabecera1" align="center" class="linkmenu">
	        <b><a href="menu.jsp">Men&uacute; Principal</a>-<a
	        href="Odontologia.jsp">Odontologia</a>-Realizar diagn&oacute;stico
	        odontologico</b>
	        </div>
	    </td>
	</tr>
    
    <tr>
	         <td id="cabecera2" class="style11" align="center">
		     TRATAMIENTO ODONTOLOGICO
			 </td>
	</tr>

	<tr>
	    <td>
            <table width="100%">
		        <tr>
		            <td align="center">Tipo de Documento</td>
		            <td>
		                <select name="cbafiliacion" size="1"
			            id="cbafiliacion" onchange="document.getElementById('txtnumdoc').focus();">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery("SELECT " + "sigla, " + "descripcion "
				                    + "FROM " + "adm_tipodocumento");
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
		            </td>
			        <td align="center">N&uacute;mero de Documento</td>
				    <td>
 				        <form id="form1" name="form1" onkeypress="return pulsar(event);">
				        <input type="text" name="txtnumdoc" id="txtnumdoc" onkeyup="A(event)" /> 
				        <input type="hidden" name="txtFecha" id="txtFecha" />
				        <input type="hidden" name="txtHora" id="txtHora" /> 
				        <input type="hidden" name="txtUsuario" id="txtUsuario" value="<%=codigou%>" />
				        </form>
				    </td>	
				    <td>
				    <input name="btnBuscarPac" type="button" id="btnBuscarPac" value="Buscar" onClick="pacienteActivo()">
				    </td>
				  
	     	    </tr>
	     	  
	     	    
	        </table>
	    </td>
	</tr>
	
	  <tr>
                   <td >
                      <div id="datos_ingreso_paciente">
                      </div>
                   </td>
     </tr>
     
	 
	 
	 <tr>
	 
	 
	    <td id="odontograma" style="display: none">
	
	    <table border ="1" width="100%">
	      <tr>
		      <td id="cabecera2" class="style11" colspan="2" align="center">ODONTOGRAMA</td>
   	       </tr>
   	       
	      <tr>
	      <td width="70%" align="center">
	       
	          
	          
	          
	           <table border ="1" >
        
        <tr>
          <td><canvas width="30" height="30" id="canvas_dsi8" onclick="localizacionCanvas(event,'canvas_dsi8')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsi7" onclick="localizacionCanvas(event,'canvas_dsi7')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsi6" onclick="localizacionCanvas(event,'canvas_dsi6')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsi5" onclick="localizacionCanvas(event,'canvas_dsi5')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsi4" onclick="localizacionCanvas(event,'canvas_dsi4')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsi3" onclick="localizacionCanvas(event,'canvas_dsi3')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsi2" onclick="localizacionCanvas(event,'canvas_dsi2')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsi1" onclick="localizacionCanvas(event,'canvas_dsi1')"> Tu navegador no soporta canvas. </canvas></td>
          <td width="30">&nbsp;</td>
          <td><canvas width="30" height="30" id="canvas_dsd1"  onclick="localizacionCanvas(event,'canvas_dsd1')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsd2"  onclick="localizacionCanvas(event,'canvas_dsd2')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsd3"  onclick="localizacionCanvas(event,'canvas_dsd3')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsd4"  onclick="localizacionCanvas(event,'canvas_dsd4')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsd5"  onclick="localizacionCanvas(event,'canvas_dsd5')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsd6"  onclick="localizacionCanvas(event,'canvas_dsd6')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsd7"  onclick="localizacionCanvas(event,'canvas_dsd7')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dsd8"  onclick="localizacionCanvas(event,'canvas_dsd8')"> Tu navegador no soporta canvas. </canvas></td>
        </tr>
        
          <tr>
          <td><div align="center"><font size="-2">18</font></div></td>
          <td><div align="center"><font size="-2">17</font></div></td>
          <td><div align="center"><font size="-2">16</font></div></td>
          <td><div align="center"><font size="-2">15</font></div></td>
          <td><div align="center"><font size="-2">14</font></div></td>
          <td><div align="center"><font size="-2">13</font></div></td>
          <td><div align="center"><font size="-2">12</font></div></td>
          <td><div align="center"><font size="-2">11</font></div></td>
          <td>&nbsp;</td>
          <td><div align="center"><font size="-2">21</font></div></td>
          <td><div align="center"><font size="-2">22</font></div></td>
          <td><div align="center"><font size="-2">23</font></div></td>
          <td><div align="center"><font size="-2">24</font></div></td>
          <td><div align="center"><font size="-2">25</font></div></td>
          <td><div align="center"><font size="-2">26</font></div></td>
          <td><div align="center"><font size="-2">27</font></div></td>
          <td><div align="center"><font size="-2">28</font></div></td>
        </tr>
        
          <tr>
          <td colspan="3">&nbsp;</td>
          <td><canvas width="30" height="30" id="canvas_dcsi5" onclick="localizacionCanvas(event,'canvas_dcsi5')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcsi4" onclick="localizacionCanvas(event,'canvas_dcsi4')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcsi3" onclick="localizacionCanvas(event,'canvas_dcsi3')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcsi2" onclick="localizacionCanvas(event,'canvas_dcsi2')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcsi1" onclick="localizacionCanvas(event,'canvas_dcsi1')"> Tu navegador no soporta canvas. </canvas></td>
          <td >&nbsp;</td>
          <td><canvas width="30" height="30" id="canvas_dcsd1" onclick="localizacionCanvas(event,'canvas_dcsd1')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcsd2" onclick="localizacionCanvas(event,'canvas_dcsd2')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcsd3" onclick="localizacionCanvas(event,'canvas_dcsd3')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcsd4" onclick="localizacionCanvas(event,'canvas_dcsd4')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcsd5" onclick="localizacionCanvas(event,'canvas_dcsd5')"> Tu navegador no soporta canvas. </canvas></td>
          <td colspan="3">&nbsp;</td>
       </tr>
        
          <tr>
          <td colspan="3"><div align="left">DER.</div></td>
          <td><div align="center"><font size="-2">55</font></div></td>
          <td><div align="center"><font size="-2">54</font></div></td>
          <td><div align="center"><font size="-2">53</font></div></td>
          <td><div align="center"><font size="-2">52</font></div></td>
          <td><div align="center"><font size="-2">51</font></div></td>
          <td>&nbsp;</td>
          <td><div align="center"><font size="-2">61</font></div></td>
          <td><div align="center"><font size="-2">62</font></div></td>
          <td><div align="center"><font size="-2">63</font></div></td>
          <td><div align="center"><font size="-2">64</font></div></td>
          <td><div align="center"><font size="-2">65</font></div></td>
          <td colspan="3"><div align="right">IZQ.</div></td>
       </tr>
       
     
       
       
         
         <tr>
          <td colspan="3">&nbsp;</td>
          <td><canvas width="30" height="30" id="canvas_dcii5" onclick="localizacionCanvas(event,'canvas_dcii5')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcii4" onclick="localizacionCanvas(event,'canvas_dcii4')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcii3" onclick="localizacionCanvas(event,'canvas_dcii3')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcii2" onclick="localizacionCanvas(event,'canvas_dcii2')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcii1" onclick="localizacionCanvas(event,'canvas_dcii1')"> Tu navegador no soporta canvas. </canvas></td>
          <td>&nbsp;</td>
          <td><canvas width="30" height="30" id="canvas_dcid1" onclick="localizacionCanvas(event,'canvas_dcid1')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcid2" onclick="localizacionCanvas(event,'canvas_dcid2')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcid3" onclick="localizacionCanvas(event,'canvas_dcid3')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcid4" onclick="localizacionCanvas(event,'canvas_dcid4')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dcid5" onclick="localizacionCanvas(event,'canvas_dcid5')"> Tu navegador no soporta canvas. </canvas></td>
          <td colspan="3" >&nbsp;</td>
        </tr>
        
           <tr>
          <td colspan="3">&nbsp;</td>
          <td><div align="center"><font size="-2">85</font></div></td>
          <td><div align="center"><font size="-2">84</font></div></td>
          <td><div align="center"><font size="-2">83</font></div></td>
          <td><div align="center"><font size="-2">82</font></div></td>
          <td><div align="center"><font size="-2">81</font></div></td>
          <td>&nbsp;</td>
          <td><div align="center"><font size="-2">71</font></div></td>
          <td><div align="center"><font size="-2">72</font></div></td>
          <td><div align="center"><font size="-2">73</font></div></td>
          <td><div align="center"><font size="-2">74</font></div></td>
          <td><div align="center"><font size="-2">75</font></div></td>
          <td colspan="3">&nbsp;</td>
       </tr>
        <tr>
          <td><canvas width="30" height="30" id="canvas_dii8" onclick="localizacionCanvas(event,'canvas_dii8')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dii7" onclick="localizacionCanvas(event,'canvas_dii7')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dii6" onclick="localizacionCanvas(event,'canvas_dii6')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dii5" onclick="localizacionCanvas(event,'canvas_dii5')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dii4" onclick="localizacionCanvas(event,'canvas_dii4')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dii3" onclick="localizacionCanvas(event,'canvas_dii3')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dii2" onclick="localizacionCanvas(event,'canvas_dii2')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_dii1" onclick="localizacionCanvas(event,'canvas_dii1')"> Tu navegador no soporta canvas. </canvas></td>
          <td>&nbsp;</td>
          <td><canvas width="30" height="30" id="canvas_did1" onclick="localizacionCanvas(event,'canvas_did1')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_did2" onclick="localizacionCanvas(event,'canvas_did2')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_did3" onclick="localizacionCanvas(event,'canvas_did3')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_did4" onclick="localizacionCanvas(event,'canvas_did4')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_did5" onclick="localizacionCanvas(event,'canvas_did5')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_did6" onclick="localizacionCanvas(event,'canvas_did6')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_did7" onclick="localizacionCanvas(event,'canvas_did7')"> Tu navegador no soporta canvas. </canvas></td>
          <td><canvas width="30" height="30" id="canvas_did8" onclick="localizacionCanvas(event,'canvas_did8')"> Tu navegador no soporta canvas. </canvas></td>
        </tr>
        <tr>
          <td><div align="center"><font size="-2">48</font></div></td>
          <td><div align="center"><font size="-2">47</font></div></td>
          <td><div align="center"><font size="-2">46</font></div></td>
          <td><div align="center"><font size="-2">45</font></div></td>
          <td><div align="center"><font size="-2">44</font></div></td>
          <td><div align="center"><font size="-2">43</font></div></td>
          <td><div align="center"><font size="-2">42</font></div></td>
          <td><div align="center"><font size="-2">41</font></div></td>
          <td>&nbsp;</td>
          <td><div align="center"><font size="-2">31</font></div></td>
          <td><div align="center"><font size="-2">32</font></div></td>
          <td><div align="center"><font size="-2">33</font></div></td>
          <td><div align="center"><font size="-2">34</font></div></td>
          <td><div align="center"><font size="-2">35</font></div></td>
          <td><div align="center"><font size="-2">36</font></div></td>
          <td><div align="center"><font size="-2">37</font></div></td>
          <td><div align="center"><font size="-2">38</font></div></td>
        </tr>
        
     </table>
          </td>
          
          <td align="center">
<table border="1">
<tr>
<td colspan="2" align="center">HERRAMIENTAS</td>
</tr>
  <tr>
  <td  height="40" align="center">
  <input  type="button" id="boton_caries" onclick="seleccion(1)" class="caries_boton_normal" title="CARIES" value="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "/>
  </td>
  <td height="40" align="center"><input id="boton_no_erupcionado" onclick="seleccion(2)" type="button" class="no_erupcionado_boton_normal" title="NO ERUPCIONADO" value="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "/>  </tr>
  <tr>
  <td  height="40" align="center"><input id="boton_radicina" onclick="seleccion(3)" type="button" class="radicina_boton_normal" title="Caries Recidiva" value="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " /></td>
  <td height="40" align="center"><input id="boton_sellante_ausente" onclick="seleccion(4)" type="button" class="sellante_ausente_boton_normal" title="SELLANTE AUSENTE" value="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " /></td>
  </tr>
  <tr>
  <td height="40" align="center"><input id="boton_ausencia_normal" onclick="seleccion(5)" type="button" class="ausencia_boton_normal" title="DIENTE AUSENTE" value="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " /></td>
  <td height="40" align="center"><input id="boton_desmineralizacion" onclick="seleccion(6)" type="button" class="desmineralizacion_boton_normal" title="DESMINERALIZACION" value="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp "/>
  </td>
  
</tr>
  <tr>
  <td height="40" align="center" ><input id="boton_obturacion" onclick="seleccion(7)" type="button" class="obturacion_boton_normal" title="OBTURACION" value="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " /></td>
  <td height="40" align="center" ><input id="boton_restaurar" onclick="seleccion(8)" type="button" class="restaurar_diente_boton_normal" title="RESTAURAR DIENTE" value="&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp " /></td>
  </tr>
</table>

</td>
     
	
	     
	      
	      </tr>
	    
	       
	    
	    
	    
	    </table>
	    
	    
	    <table  border="1" width="100%">
	    
<tr>
<td id="cabecera2" class="style11" colspan="9" align="center">EVOLUCI&Oacute;N</td>
</tr>

<tr>

   <td colspan="9"  align="center">
   
    <div id="evoluciones_previas">
    
    </div>
    </td>
</tr>

<tr>
<td colspan="9" align="center"><textarea class="textarea"  id="evolucion" name="evolucion" cols="100" rows="5"></textarea></td>

</tr>
	    
<tr>
<td id="cabecera2" class="style11" colspan="9" align="center">MOTIVO DE LA CONSULTA</td>
</tr>
<tr>
<td colspan="9" align="center"><textarea class="textarea"  id="motivo_consulta" name="motivo_consulta" cols="100" rows="5"></textarea></td>

</tr>
<tr>
<td id="cabecera2" class="style11" colspan="9" align="center">ANTECEDENTES M&Eacute;DICOS Y ODONTOLOGICOS</td>
</tr>

<tr>
<td width="204">&nbsp;</td>
<td width="20">SI</td>
<td width="24">NO</td>
<td width="173">&nbsp;</td>
<td width="20">SI</td>
<td width="24">NO</td>
<td width="302">&nbsp;</td>
<td width="20">SI</td>
<td width="24">NO</td>
</tr>
<tr>
<td width="204">1. Alergias</td>
<td width="20"><input type="radio" name="Alergias" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Alergias" value="0"></td>
<td width="173">7. Hepatitis</td>
<td width="20"><input type="radio" name="Hepatitis" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Hepatitis" value="0"></td>
<td width="302">13. Uso de protesis dental o aparatologia oral</td>
<td width="20"><input type="radio" name="Aparatologia" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Aparatologia" value="0"></td>
</tr>
<tr>
<td width="204">2. Hemorragias</td>
<td width="20"><input type="radio" name="Hemorragias" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Hemorragias" value="0"></td>
<td width="173">8. Transtornos gastricos</td>
<td width="20"><input type="radio" name="Transtornos_Gastricos" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Transtornos_Gastricos" value="0"></td>
<td width="302">14. HIV</td>
<td width="20"><input type="radio" name="HIV" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="HIV" value="0"></td>
</tr>

<tr>
<td width="204">3. Enfermedades Respiratorias</td>
<td width="20"><input type="radio" name="Enfermedades_Respiratorias" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Enfermedades_Respiratorias" value="0"></td>
<td width="173">9. Tension Arterial</td>
<td width="20"><input type="radio" name="Tension_Arterial" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Tension_Arterial" value="0"></td>
<td width="302">15. Extracciones dentales</td>
<td width="20"><input type="radio" name="Extracciones_Dentales" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Extracciones_Dentales" value="0"></td>
</tr>

<tr>
<td width="204">4. CardioPatias</td>
<td width="20"><input type="radio" name="Cardiopatias" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Cardiopatias" value="0"></td>
<td width="173">10. Diabetes</td>
<td width="20"><input type="radio" name="Diabetes" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Diabetes" value="0"></td>
<td width="302">16. Enfermedades orales</td>
<td width="20"><input type="radio" name="Enfermedades_Orales" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Enfermedades_Orales" value="0"></td>
</tr>

<tr>
<td width="204">5. Fiebre Reumatica</td>
<td width="20"><input type="radio" name="Fiebre_Reumatica" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Fiebre_Reumatica" value="0"></td>
<td width="173">11. Ingesta Medicamentos</td>
<td width="20"><input type="radio" name="Ingesta_Medicamentos" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Ingesta_Medicamentos" value="0"></td>
<td width="302">17. Antecedentes Familiares</td>
<td width="20"><input type="radio" name="Antecedentes_Familiares" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Antecedentes_Familiares" value="0"></td>
</tr>

<tr>
<td width="204">6. Enfermedades Renales</td>
<td width="20"><input type="radio" name="Enfermedades_Renales" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Enfermedades_Renales" value="0"></td>
<td width="173">12. Cirugias (incluso orales)</td>
<td width="20"><input type="radio" name="Cirugias" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Cirugias" value="0"></td>
<td width="302">18. Otros</td>
<td width="20"><input type="radio" name="Otros" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Otros" value="0"></td>
</tr>

<!--<tr align="center" valign="top"><td colspan="6"></td><td height="29" align="center" colspan="3"></tr>
  -->
<tr>
<tr>
<td id="cabecera2" class="style11" colspan="9" align="center" >EXAMEN CL&Iacute;NICO</td>
</tr>
<tr>
<td width="204"  >&nbsp;</td>
<td width="20">SI</td>
<td width="25">NO</td>
<td width="172">&nbsp;</td>
<td width="20">SI</td>
<td width="24">NO</td>
<td width="302">&nbsp;</td>
<td width="20">SI</td>
<td width="24">NO</td>
</tr>
<tr>
<td width="204">1. Lengua</td>
<td width="20"><input type="radio" name="Lengua" value="1"></td>
<td width="25"><input checked="checked" type="radio" name="Lengua" value="0"></td>
<td width="172">9. Macrognasia</td>
<td width="20"><input type="radio" name="Macrognasia" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Macrognasia" value="0"></td>
<td width="302">17. Hipodoncia</td>
<td width="20"><input type="radio" name="Hipodoncia" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Hipodoncia" value="0"></td>
</tr>
<tr>
<td width="204">2. Carrillos</td>
<td width="20"><input type="radio" name="Carrillos" value="1"></td>
<td width="25"><input checked="checked" type="radio" name="Carrillos" value="0"></td>
<td width="172">10. Desviacion Linea Media</td>
<td width="20"><input type="radio" name="Desviacion" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Desviacion" value="0"></td>
<td width="302">18. Fracturas</td>
<td width="20"><input type="radio" name="Fracturas" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Fracturas" value="0"></td>
</tr>

<tr>
<td width="204">3. Abrasion</td>
<td width="20"><input type="radio" name="Abrasion" value="1"></td>
<td width="25"><input checked="checked" type="radio" name="Abrasion" value="0"></td>
<td width="172">11. Mal Posicion</td>
<td width="20"><input type="radio" name="Mal_Posicion" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Mal_Posicion" value="0"></td>
<td width="302">19. Periodontitis</td>
<td width="20"><input type="radio" name="Periodontitis" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Periodontitis" value="0"></td>
</tr>

<tr>
<td width="204">4. Atricion</td>
<td width="20"><input type="radio" name="Atricion" value="1"></td>
<td width="25"><input checked="checked" type="radio" name="Atricion" value="0"></td>
<td width="172">12. ATM</td>
<td width="20"><input type="radio" name="ATM" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="ATM" value="0"></td>
<td width="302">20. Retracciones</td>
<td width="20"><input type="radio" name="Retracciones" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Retracciones" value="0"></td>
</tr>

<tr>
<td width="204">5. Hipoplasias</td>
<td width="20"><input type="radio" name="Hipoplasias" value="1"></td>
<td width="25"><input checked="checked" type="radio" name="Hipoplasias" value="0"></td>
<td width="172">13. Habitos Orales</td>
<td width="20"><input type="radio" name="Habitos_Orales" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Habitos_Orales" value="0"></td>
<td width="302">21. Torus</td>
<td width="20"><input type="radio" name="Torus" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Torus" value="0"></td>
</tr>
<tr>
<td width="204">6. Gingivitis Marginal</td>
<td width="20"><input type="radio" name="Gingivitis_Marginal" value="1"></td>
<td width="25"><input checked="checked" type="radio" name="Gingivitis_Marginal" value="0"></td>
<td width="172">14. Paladar Blando</td>
<td width="20"><input type="radio" name="Paladar_Blando" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Paladar_Blando" value="0"></td>
<td width="302">22. Sobremordida Vertical</td>
<td width="20"><input type="radio" name="Sobremordida_Vertical" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Sobremordida_Vertical" value="0"></td>
</tr>


<tr>
<td width="204">7. Gingivitis Difusa</td>
<td width="20"><input type="radio" name="Gingivitis_Difusa" value="1"></td>
<td width="25"><input checked="checked" type="radio" name="Gingivitis_Difusa" value="0"></td>
<td width="172">15. Piso de Boca</td>
<td width="20"><input type="radio" name="Piso_De_Boca" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Piso_De_Boca" value="0"></td>
<td width="302">23. Sobremordida Horizontal</td>
<td width="20"><input type="radio" name="Sobremordida_Horizontal" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Sobremordida_Horizontal" value="0"></td>
</tr>


<tr>
<td width="204">8. Micrognasia</td>
<td width="20"><input type="radio" name="Micrognasia" value="1"></td>
<td width="25"><input checked="checked" type="radio" name="Micrognasia" value="0"></td>
<td width="172">16.Supernumerarios</td>
<td width="20"><input type="radio" name="Supernumerarios" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Supernumerarios" value="0"></td>
<td width="302">24. Otros</td>
<td width="20"><input type="radio" name="Otros2" value="1"></td>
<td width="24"><input checked="checked" type="radio" name="Otros2" value="0"></td>
<tr>
</tr>
<tr>
<td id="cabecera2" class="style11" colspan="9" align="center">PLAN DE TRATAMIENTO Y PRESUPUESTO:</td>

</tr>
<tr>
<td colspan="9" align="center"><textarea class="textarea"  id="plan_tratamiento" name="plan_tratamiento" cols="100" rows="5"></textarea></td>

</tr>

<tr>
<td id="cabecera2" class="style11" colspan="9" align="center">OBSERVACIONES:</td>

</tr>
<tr>
<td colspan="9" align="center"><textarea class="textarea"  id="observaciones" name="observaciones" cols="100" rows="5"></textarea></td>

</tr>





<tr>

<td colspan="9" align="center">
<input id="enviar" type="button" value="Guardar Historia Odontologica" onclick="enviarDataOdontologia()" >
<input id="anular" type="button" value="Anular Historia Odontologica" onclick="anularDataOdontologia()" >
<input id="terminartratamiento" type="button" value="Terminar Tratamiento" onclick="terminarTratamiento()">

</td>
</tr>
</table>
	</td>
	</tr>	
   
</table>
<%} %>



</body>
</html>