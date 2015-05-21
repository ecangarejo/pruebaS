<%@page import="java.util.Vector"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	import="adm_logica.Conexion" import="java.sql.*"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ page language="java"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<link rel="SHORTCUT ICON" href="Imagenes/IconO.ico">
<link rel="stylesheet" href="thickbox.css" type="text/css" />
<link rel="stylesheet" href="estilos/jquery-ui.css" type="text/css" />
<title>Informe de Ecocardiograma</title>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type="text/javascript" src="jquery.js"></script>
<script lang="javascript" type="text/javascript" src="thickbox.js"></script>
<script lang="javascript" type="text/javascript" src="clickderecho.js"></script>
<script lang="javascript" type='text/javascript' src='Browser.js'></script>
<script lang="javascript" type="text/javascript" src="eco_analisis_ecocardiograma.js"></script>
<script lang="javascript" type="text/javascript" src="jquery-1.8.3.js"></script>
<script lang="javascript" type="text/javascript" src="jquery-ui.js"></script>
  
 

  <script>
  $(function() {
    $( "#formulario_general" ).tabs();
  });
  </script>
  <script>
  $(function() {
    $( "#formulario_aorta" ).tabs();
  });
  </script>
  
  <script>
  $(function() {
    $( "#valvulas" ).tabs({
    });
  });
  </script>
  <script>
  $(function() {
    $( "#valvula_aorta" ).tabs({
   	collapsible: true
    }
    );
  });
 </script>
  <script>
  $(function() {
    $( "#valvula_mitral" ).tabs({
    	collapsible: true
    });
  });
  </script>
  <script>
  $(function() {
    $( "#valvula_tricuspidea" ).tabs({
    	collapsible: true
    });
  });
  </script>
  
  <script>
  $(function() {
    $( "#valvula_pulmonal" ).tabs({
    	collapsible: true
    });
  });
  </script>
  
    <script>
  $(function() {
    $( "#formulario_funcion_sistolica_diastolica" ).tabs({
    	collapsible: true
    });
  });
  </script>
 
  <script>
   $(function() {
    var spinner = $( "#spinner" ).spinner();
    spinner.spinner( "value", 60 );
   });
  </script>
  <script>
  $(function() {
  $( "#spinner" ).spinner({ max: 75 });
  $( "#spinner" ).spinner({ min: 5 });
  $( "#spinner").on( "spinstop", function( event, ui ) {
   ventriculo_izquierdo_generar_informe();
	  
  } );
  
  });
  </script>
 
  <script lang="javascript">
	function A(e) {
		tecla = e.keyCode;
		e.which;
		if (tecla == 13) {
			BuscarPaciente();
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

<body onload="document.getElementById('txtnumdoc').focus();" onKeyDown="Verificar(event)">
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
	        href="Ecocardiologia.jsp">Cardiolog&iacute;a</a>-Realizar diagn&oacute;stico
	        Ecocardiograma</b>
	        </div>
	    </td>
	</tr>
    
    <tr>
	         <td id="cabecera2" class="style11" align="center">
		     REALIZAR INFORME ECOCARDIOGRAMA
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
				    <input name="btnBuscarPac" type="button" id="btnBuscarPac" value="Buscar" onClick="BuscarPaciente()">
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
     <td id="informe_formulario_general" style="display: none">
     
     <div id="formulario_general">
  <ul>
    <li><a href="#formulario_encabezado">Encabezado</a></li>
    <li><a href="#formulario_aorta">Aorta</a></li>
    <li><a href="#formulario_valvulas">V&aacute;lvulas</a></li>
    <li><a href="#formulario_ventriculoizquierdo" onclick="cargar_generalidades_ventriculo_izquierdo()">Ventr&iacute;culo Izquierdo</a></li>
    <li><a href="#formulario_ventriculoderecho">Ventr&iacute;culo Derecho</a></li>
    <li><a href="#formulario_auriculaizquierda">Aur&iacute;cula Izquierda</a></li>
    <li><a href="#formulario_auriculaderecha">Aur&iacute;cula Derecha</a></li>
    <li><a href="#formulario_venacava">Vena Cava</a></li>
    <li><a href="#formulario_pericardio">Pericardio</a></li>
    <li><a href="#formulario_vistaprevia">Vista Previa</a></li>
 </ul>
  
  <div id="formulario_encabezado">
  <div align="center">

          <table border="1">
          <tr>
          
            <td width="50%"> <div align="center">Indicaci&oacute;n </div></td>
               <td> <div align="center">
                     <select id="indicacion_informe" onchange="vistapreviaFormularios()">
                      <%
                        rs = null;
	                    st = null;
	                    try {
	                    con = new Conexion();
	                    st = con.conn.createStatement();
                        rs=st.executeQuery("SELECT * FROM eco_indicaciones ORDER BY id_indicacion DESC");
                         while (rs.next()){
                      %>	
                           
                         <option value='<%=rs.getString(1)%>'><%= rs.getString(2)%></option>
                      <%
                         }
                         rs.getStatement().close();
		                  con.cerrar();
	                    } catch (SQLException e) {
	                     	System.out.println(e);
		                   out.println("no se pudo realizar la conexion!<br/>");
		                 }
                      %>
                     </select>
                     </div>
                </td>
               
            </tr>
                
            <tr>
                <td colspan="2" align="center"> 
                 Justificaci&oacute;n<br>
                 <textarea id="justificacion" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                </td>
          </tr>
          
          
          
          </table>
          
                </div>
          
          
   
  
  </div> <!--fin div formulario Encabezado  -->
  
  <div id="formulario_aorta">
  <div align="center">
  
   <table border="1">
          <tr>
          
            <td width="50%"> <div align="center">Aspecto </div></td>
               <td> 
                        <div align="center">
                     <select id="analisis_aorta_aspecto" onchange="aorta_control_formularios(1)">
                 
                   <option  value="1" selected="selected">normal</option>
                   <option  value="2" >anormal</option>
                     </select>
                     </div>
                </td>
               
            </tr>
                
            <tr>
                <td colspan="2" align="center"> 
                <div align="center">
                      Observaciones Aorta<br>
                                <textarea id="analisis_aorta_observaciones" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                      <br>
                      </div>
<div id="formulario_aorta">
  <ul>
    <li><a href="#anillo" onclick="aorta_control(1)">Anillo</a></li>
    <li><a href="#senosvalsalva" onclick="aorta_control(2)">Senos de Valsalva</a></li>
    <li><a href="#unionsinotubular" onclick="aorta_control(3)">Uni&oacute;n Sinotubular</a></li>
    <li><a href="#aortaascendente" onclick="aorta_control(4)">Aorta Ascendente</a></li>
    <li><a href="#arcoaortico" onclick="aorta_control(5)">Arco A&oacute;rtico</a></li>
  </ul>
  <div id="anillo">
     <table border="1">
       <tr>
          <td>Medici&oacute;n</td>
          <td ><div align='center'><input id='analisis_aorta_anillo_medicion' type='text' maxlength='2' size='2' onkeyup="aorta_control_formularios(2)"/> mm</div></td>
       </tr>
     </table>
  </div>

  <div id="senosvalsalva">
     <table>
        <tr>
           <td >
           <div align="center">
           Medici&oacute;n<input id='analisis_aorta_senos_valsalva_medicion' type='text' maxlength='2' size='2' onkeyup="aorta_control_formularios(2)"/> mm
             </div>
           </td>
         
        </tr>
        
        <tr>
        
        <td id="patologias_senos_valsalva" style="display: none" >
        <div align="center">
        <table border="1" width="60%" >
             <tr>
				                   <td>tiene Aneurisma</td>
				                   <td>si <input type="radio" name="analisis_aorta_aneurisma_senos_valsalva" onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_aneurisma_senos_valsalva" onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                  <tr>
				                   <td>tiene Disecci&oacute;n</td>
				                   <td>si <input type="radio" name="analisis_aorta_diseccion_senos_valsalva"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_diseccion_senos_valsalva"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                   <tr>
				                   <td>tiene &uacute;lcera</td>
				                   <td>si <input type="radio" name="analisis_aorta_ulcera_senos_valsalva"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_ulcera_senos_valsalva"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				              
				                   <tr>
				                   <td>tiene Trombo</td>
				                   <td>si <input type="radio" name="analisis_aorta_trombo_senos_valsalva"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_trombo_senos_valsalva"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
				                   <tr>
				                   <td>tiene Hematoma</td>
				                   <td>si <input type="radio" name="analisis_aorta_hematoma_senos_valsalva"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_hematoma_senos_valsalva"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                 
        </table>
         </div>
        </td>
</tr>
       
        
                             
        
        
    </table>
  </div>
  <div id="unionsinotubular">
    <table>
        <tr>
           <td >
                    <div align="center">
           Medici&oacute;n<input id='analisis_aorta_union_sinotubular_medicion' type='text' maxlength='2' size='2' onkeyup="aorta_control_formularios(2)"/> mm
           </div>
           </td>
           
        </tr>
        
        <tr>
        <td id="patologias_unionsinotubular" style="display: none">
         <div align="center">
        <table border="1" width="60%" >
             <tr>
				                   <td>tiene Aneurisma</td>
				                   <td>si <input type="radio" name="analisis_aorta_aneurisma_union_sinotubular"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_aneurisma_union_sinotubular"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                  <tr>
				                   <td>tiene Disecci&oacute;n</td>
				                   <td>si <input type="radio" name="analisis_aorta_diseccion_union_sinotubular"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_diseccion_union_sinotubular"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                   <tr>
				                   <td>tiene &Uacute;lcera</td>
				                   <td>si <input type="radio" name="analisis_aorta_ulcera_union_sinotubular"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_ulcera_union_sinotubular"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				              
				                   <tr>
				                   <td>tiene Trombo</td>
				                   <td>si <input type="radio" name="analisis_aorta_trombo_union_sinotubular"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_trombo_union_sinotubular"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
				                   <tr>
				                   <td>tiene Hematoma</td>
				                   <td>si <input type="radio" name="analisis_aorta_hematoma_union_sinotubular"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_hematoma_union_sinotubular"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                 
        </table>
</div>
        </td>

        </tr>
                             
        
        
    </table> </div>
  <div id="aortaascendente"  style="display: none" >
   <table>
        <tr>
           <td >         <div align="center">
           Medici&oacute;n<input id='analisis_aorta_aorta_ascendente_medicion' type='text' maxlength='2' size='2' onkeyup="aorta_control_formularios(2)"/> mm
           </div>
           </td>
           
        </tr>
        
        <tr>
        <td id="patologia_aortaascendente" style="display: none">
         <div align="center">
        <table border="1" width="60%">
             <tr>
				                   <td>tiene Aneurisma</td>
				                   <td>si <input type="radio" name="analisis_aorta_aneurisma_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_aneurisma_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                  <tr>
				                   <td>tiene Disecci&oacute;n</td>
				                   <td>si <input type="radio" name="analisis_aorta_diseccion_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_diseccion_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                   <tr>
				                   <td>tiene &uacute;lcera</td>
				                   <td>si <input type="radio" name="analisis_aorta_ulcera_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_ulcera_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				              
				                   <tr>
				                   <td>tiene Trombo</td>
				                   <td>si <input type="radio" name="analisis_aorta_trombo_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_trombo_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
				                   <tr>
				                   <td>tiene Hematoma</td>
				                   <td>si <input type="radio" name="analisis_aorta_hematoma_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_hematoma_aorta_ascendente"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
        </table>
        </div>
        

        </td>
        

                             
        
        
    </table></div>
  <div id="arcoaortico" >
     <table>
        <tr>
           <td >
                    <div align="center">
           Medici&oacute;n<input id='analisis_aorta_arco_aortico_medicion' type='text' maxlength='2' size='2' onkeyup="aorta_control_formularios(2)" /> mm
           
           </div>
           </td>
           
        </tr>
        
        <tr>
        <td id="patologias_arcoaortico" style="display: none" >
         <div align="center">
        <table border="1" width="60%" >
             <tr>
				                   <td>tiene Aneurisma</td>
				                   <td>si <input type="radio" name="analisis_aorta_aneurisma_arco_aortico"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_aneurisma_arco_aortico"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                  <tr>
				                   <td>tiene Disecci&oacute;n</td>
				                   <td>si <input type="radio" name="analisis_aorta_diseccion_arco_aortico"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_diseccion_arco_aortico"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                   <tr>
				                   <td>tiene &uacute;lcera</td>
				                   <td>si <input type="radio" name="analisis_aorta_ulcera_arco_aortico"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_ulcera_arco_aortico"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				              
				                   <tr>
				                   <td>tiene Trombo</td>
				                   <td>si <input type="radio" name="analisis_aorta_trombo_arco_aortico"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_trombo_arco_aortico"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
				                   <tr>
				                   <td>tiene Hematoma</td>
				                   <td>si <input type="radio" name="analisis_aorta_hematoma_arco_aortico"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_hematoma_arco_aortico"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                    <tr>
				                   <td>tiene Cuartaci&oacute;n</td>
				                   <td>si <input type="radio" name="analisis_aorta_cuartacion_arco_aortico"  onclick="aorta_control_formularios(3)" value="1"> </td>
				                   <td>no <input type="radio" name="analisis_aorta_cuartacion_arco_aortico"  onclick="aorta_control_formularios(3)" value="0" checked="checked"></td>
				                 </tr>
				                    
        </table>
        </div>
      </td>
   </tr>

   
  </table>  
  
  </div>
</div>
</td>
</tr>
</table>
  </div>
  
  </div> <!--fin div formulario Aorta  -->
  
  <div id="formulario_valvulas">
  <div align="center">
          
           <table border="1" width="60%" >
          <tr>
          <td>
          
          
          <div align="center">
                 <div id="valvulas">
                    <ul>
                        <li><a href="#aorta" onclick="valvulas_control(1)">Aorta</a></li>
                        <li><a href="#mitral" onclick="valvulas_control(2)">Mitral</a></li>
                        <li><a href="#tricuspidea"  onclick="valvulas_control(3)" >Tric&uacute;spide</a></li>
                        <li><a href="#pulmonal"  onclick="valvulas_control(4)">Pulmonar</a></li>
                    </ul>
            
    <div id="aorta">

                       
                       <div align="center">
                       <table width="100%">
                       <tr><!--valvula aortica normal  -->
                       <td id="analisis_valvula_aorta_normal">
                                <div align="center">
                                               Observaciones<br>
                              <textarea id="analisis_observaciones_aorta" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                        
                         </div>
                         
                         
                         
                         
                        <div id="valvula_aorta">
                            <ul>
                               <li><a href="#aspecto_valvula_aorta">Aspecto</a></li>
                               <li><a href="#insuficiencia_valvula_aorta">Funci&oacute;n Insuficiencia</a></li>
                               <li><a href="#estenosis_valvula_aorta">Funci&oacute;n Estenosis</a></li>
                              <li><div id ="analisis_valvula_aorta_protesica" style="display: none"><a href="#protesis_valvula_aorta">Pr&oacute;tesis</a></div></li>
                               
                           </ul>

<div id="aspecto_valvula_aorta">
  <div align=center>
    <table width='60%' border="1">
        <tr>
           <td>Aspecto 
           <select id="tipo_aspecto_valvula_aorta" onchange="valvulas_control_formulario_aspecto(1)" >
           <option value="1" selected="selected">normal</option>
           <option value="2">anormal</option>
           
           </select>
           
          </td>
           
        </tr>
        
        <tr>
        <td id="aspectos_valvula_aorta" style="display: none">
        <table border="1" width="100%" >
             <tr>
				                   <td>Esclerosis</td>
				                   <td>si <input type="radio" name="aspecto_esclerosis_valvula_aorta" value="1" onclick="valvulas_control_formulario_aspecto(2)"> </td>
				                   <td>no <input type="radio" name="aspecto_esclerosis_valvula_aorta" value="0" onclick="valvulas_control_formulario_aspecto(2)" checked="checked" ></td>
				                 </tr>
				                  <tr>
				                   <td>Calcificaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_calcificacion_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_calcificacion_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                   <tr>
				                   <td>Esclerocalcificaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_esclerocalcificacion_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_esclerocalcificacion_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				              
				                   <tr>
				                   <td>Engrosamiento</td>
				                   <td>si <input type="radio" name="aspecto_engrosamiento_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_engrosamiento_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
				                   <tr>
				                   <td>Perforaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_perforacion_valvula_aorta"  onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_perforacion_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                    <tr>
				                   <td>En domo</td>
				                   <td>si <input type="radio" name="aspecto_endomo_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_endomo_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                 
				                    <tr>
				                   <td>Restricci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_restriccion_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_restriccion_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                    <tr>
				                   <td>Plastia</td>
				                   <td>si <input type="radio" name="aspecto_plastia_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_plastia_valvula_aorta" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                 <tr>
				                   <td>Pr&oacute;tesis</td>
				                   <td>si <input type="radio" name="aspecto_protesis_valvula_aorta" onclick="valvulas_control_formulario_aspecto(3)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_protesis_valvula_aorta" onclick="valvulas_control_formulario_aspecto(3)" value="0" checked="checked"></td>
				                 </tr>


                                 <tr>
				                   <td>Otros</td>
				                   <td>si <input type="radio" name="otros_protesis_valvula_aorta"  value="1"> </td>
				                   <td>no <input type="radio" name="otros_protesis_valvula_aorta" value="0" checked="checked"></td>
				                 </tr>

				                 
				                 
				                 </table>
      </td>
   </tr>
  </table>  
  </div>
  
  
  
  
  </div>
  
  <div id="insuficiencia_valvula_aorta">
  
     <div align="center">

       <table border="1" width="60%">
       <tr>
       <td>
       <div align="center">
       Jet Central LVOT
       </div>
       
       </td>
          <td>
       <div align="center">
        <select id="insuficiencia_jetcentrallvot_valvula_aorta" onchange="valvulas_control_formulario_insuficiencia_aorta(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    	 "SELECT "+
				                    	 "jet.intervalo,"+
				                    	 "gra.id_grado_severidad"+
				                    	 " FROM "+
				                    	 "eco_jet_central_valvula_aorta jet,"+
				                    	 "eco_grado_severidad gra"+
				                    	 " WHERE "+
				                    	 " jet.id_grado_severidad_fk = gra.id_grado_severidad");
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
	 </select>
       </div>
       
          </td>
       </tr>
       
         <tr>
       <td>
       <div align="center">
       Vena Contracta
       </div>
       
       </td>
          <td>
       <div align="center">
        <select id="insuficiencia_venacontracta_valvula_aorta" onchange="valvulas_control_formulario_insuficiencia_aorta(1)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    	 "SELECT "+
                                         "vena.intervalo,"+
                                         "gra.id_grado_severidad "+
                                         "FROM  "+
                                         "eco_vena_contracta_valvula_aorta vena, "+
                                         "eco_grado_severidad gra"+
                                         " WHERE "+
                                         " vena.id_grado_severidad_fk = gra.id_grado_severidad");
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
       
       
          </td>
       </tr>
       
       
          <tr>
       <td>
       <div align="center">
       Reversi&oacute;n de flujo diast&oacute;lico en Aorta
       </div>
       
       </td>
          <td>
       <div align="center">
        <select id="insuficiencia_revflujodiastolico_valvula_aorta" onchange="valvulas_control_formulario_insuficiencia_aorta(2)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"flu.descripcion, "+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_reversion_flujo_diastolico_valvula_aorta flu,"+
				                    		"eco_grado_severidad gra "+
				                    		"WHERE "+
				                    		"flu.id_grado_severidad_fk = gra.id_grado_severidad");
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
       
       
          </td>
       </tr>
       
       
       
          <tr>
       <td>
       <div align="center">
       Tiempo de hemipresi&oacute;n
       </div>
       
       </td>
          <td>
       <div align="center">
        <select id="insuficiencia_tiempohemipresion_valvula_aorta" onchange="valvulas_control_formulario_insuficiencia_aorta(3)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		" tie.intervalo, "+
				                    		" gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_tiempo_hemipresion_valvula_aorta tie,"+
				                    		"eco_grado_severidad gra "+
				                    		"WHERE "+
				                    		"tie.id_grado_severidad_fk = gra.id_grado_severidad");
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
       
       
          </td>
       </tr>
              <tr>
       
       <td colspan="2">
       <div align="center">
       
     <b>  C&aacute;lculo de la EROA </b>
       </div>
       
       
       </td>
       </tr>
       
       
        <tr>
       <td>
       
       <div align="center">
       
       Radio PISA
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_radiopisa_valvula_aorta' type='text' maxlength='3' size='2' onkeyup="convertir_cm(1)" />cm  <input id='insuficiencia_radiopisa_valvula_aorta_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(1)" />mm </div>
       
       </td>
       
       
       </tr>
       
       
        <tr>
       <td>
       
       <div align="center">
       
       Velocidad de Aliasing
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_velaliasing_valvula_aorta' type='text' maxlength='3' size='2' onkeyup="convertir_cm_m(1)"/>cm/s <input id='insuficiencia_velaliasing_valvula_aorta_m' type='text' maxlength='3' size='2' onkeyup="convertir_m_cm(1)"/>m/s </div>
       
       </td>
       
       
       </tr>
       
         
        <tr>
       <td>
       
       <div align="center">
       
       Vel pico del jet de regurgitaci&oacute;n
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_velpicojetregurgitacion_valvula_aorta' type='text' maxlength='3' size='2' onkeyup="convertir_cm_m(2)"/>cm/s <input id='insuficiencia_velpicojetregurgitacion_valvula_aorta_m' type='text' maxlength='3' size='2' onkeyup="convertir_m_cm(2)"/>m/s </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
       EROA
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_eroa_valvula_aorta' type='text' maxlength='2' size='2' disabled="disabled" />cm<sup>2</sup></div>
       
       </td>
       
       
       </tr>
       
              <tr>
       
       <td colspan="2">
       <div align="center">
       
       <b>C&aacute;lculo del Volumen y Fracci&oacute;n Regurgitante</b>
       </div>
       
       
       </td>
       </tr>
       
      
        <tr>
       <td>
       
       <div align="center">
       
       LVOTd (Di&aacute;metro Tracto de Salida)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_lvotd_valvula_aorta' type='text' maxlength='3' size='2' onkeyup="convertir_cm(2)"  />cms <input id='insuficiencia_lvotd_valvula_aorta_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(2)"  />mm </div>
       
       </td>
       
       
       </tr>
       
       
            <tr>
       <td>
       
       <div align="center">
       
       VTI LVOT (Integral vel tracto de salida)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_vtilvot_valvula_aorta' type='text' maxlength='3' size='2' onkeyup="convertir_cm(3);" />cms <input id='insuficiencia_vtilvot_valvula_aorta_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(3);" />mm  </div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        MAD (Di&aacute;metro anillo mitral/pulmonal)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_mad_valvula_aorta' type='text' maxlength='3' size='2' onkeyup="convertir_cm(4)" />cms <input id='insuficiencia_mad_valvula_aorta_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(4)" />mm </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        VTI ma (Integral anillo pulmonal)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_vtima_valvula_aorta' type='text' maxlength='3' size='2' onkeyup="convertir_cm(5);"  />cms <input id='insuficiencia_vtima_valvula_aorta_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(5);"  />mm </div>
       
       </td>
       
       
       </tr>
       
            <tr>
       <td>
       
       <div align="center">
       Volumen Regurgitante
        
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_rvol_valvula_aorta' type='text' maxlength='2' size='2'  disabled="disabled"/>ml </div>
       
       </td>
       
       
       </tr>
       
            <tr>
       <td>
       
       <div align="center">
       
        Fracci&oacute;n Regurgitante
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_fr_valvula_aorta' type='text' maxlength='2' size='2' disabled="disabled"  />% </div>
       
       </td>
       
       
       </tr>
       
       </table>



     </div>



  </div>


  <div id="estenosis_valvula_aorta">
  <div align="center">
   <table border="1" >
   <tr>
       <td width="70%">
       
       <div align="center">
       
        Gradiente pico
       </div>
       
       </td>
       <td width="30%">
       
       <div align='center'><input id='estenosis_gradientepico_valvula_aorta' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_aorta(0)" />mmHg </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td >
       
       <div align="center">
       
        Gradiente medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_gradientemedio_valvula_aorta' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_aorta(1)"  />mmHg </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Velocidad pico
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velocidadpico_valvula_aorta' type='text' maxlength='3' size='2' onkeyup="valvulas_control_formulario_estenosis_aorta(2)" />m/s </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Velocidad Medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velocidadmedio_valvula_aorta' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_aorta(3)" />m/s </div>
       
       </td>
       
       
       </tr>
         <tr>
       <td>
       
       <div align="center">
       
        VTI
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_vti_valvula_aorta' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_aorta(4)"/>cms </div>
       
       </td>
       
       
       </tr>
         <tr>
       <td>
       
       <div align="center">
       
        THP
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_thp_valvula_aorta' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_aorta(5)"/>m/s </div>
       
       </td>
       
       
       </tr>
       <tr>
       <td colspan="2">
       C&aacute;lculo de la AVA
       
       </td>
       
       </tr>
         <tr>
       <td>
       
       <div align="center">
       
        Di&aacute;metro Tracto De Salida Ventr&iacute;culo Izquierdo (TSVI)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_tsvi_valvula_aorta' type='text' maxlength='3' size='2'  onkeyup="convertir_cm(6)"/>cms <input id='estenosis_tsvi_valvula_aorta_mm' type='text' maxlength='3' size='2'  onkeyup="convertir_mm(6)"/>mm </div>
       
       </td>
       
       
       </tr>
       
       <tr>
       <td>
       
       <div align="center">
       
        VTI TSVI
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_vtitsvi_valvula_aorta' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_aorta(4)"/>cms </div>
       
       </td>
       
       
       </tr>
       
       <tr>
       <td>
       
       <div align="center">
       
        AVA
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_ava_valvula_aorta' type='text' maxlength='3' size='2' disabled="disabled" /></div>
       
       </td>
       
       
       </tr>
       
           <tr>
       <td>
       
       <div align="center">
       
        AVA Indexado
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_avaindexado_valvula_aorta' type='text' maxlength='4' size='2' disabled="disabled" /> cm<sup>2</sup>/m<sup>2</sup></div>
       
       </td>
       
       
       </tr>
         <tr>
       <td colspan="2">
       C&aacute;lculo de la Vel Radio
       
       </td>
       
       </tr>
       
          <tr>
       <td>
       
       <div align="center">
       
        Velocidad pico TSVI
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velpicotsvi_valvula_aorta' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_aorta(2)"/>m/s</div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        Velocidad radio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velradio_valvula_aorta' type='text' maxlength='2' size='2' disabled="disabled"/>m/s</div>
       
       </td>
       
       
       </tr>
       
       </table>
  
  </div>
 
  </div>


<div id="protesis_valvula_aorta" align="center">
  <div id ="analisis_valvula_aorta_protesica2" style="display: none">
   <table border="1" >
                          <tr><!-- valvula_aorta_protesica -->

                       <td >
                       <div id="valvula_aorta_protesica" align="center">
                       
                                               Observaciones Aorta Prot&eacute;sica<br>
                              <textarea id="analisis_observaciones_aorta_protesica" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                       <table width="60%" border="1">
                       <tr>
                       <td>
                       <div align="center">
                       Tipo de Pr&oacute;tesis
                       </div>
                       </td>
                       
                       <td>  
                         <select id="protesis_valvula_protesica_aorta" onchange="valvula_aorta_protesica_control_formulario(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		" id_protesis_valvulas,"+
				                    		" descripcion "+
				                    		"FROM eco_protesis_valvulas"
                                            );
				                    
				                    
				              int i =0;
				                 %>
				
		                    	 <%  while (rs.next()) {
		                    	 
		                    		 if(i == 1){
				                    	    %>  <option value="<%=rs.getString(1)%>" selected="selected"><%=rs.getString(2)%></option>
			                    	<%    }else{
			                    		
			                    		%>
			                    		  <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
			                    		<%
			                    	}
		                    	 
		                    	 %>
		                    	     
		                    	   
				                    
				                 
				                 
				                 <%i++;
				            
		                    	 
		                    	 }
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
                       
                       
                         </td>
                       </tr>
                         <tr>
                       <td>
                       <div align="center">
                       Regurgitacion paravalvular
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="regurgitacion_paravalvular_valvula_aorta_protesica" value="1" onclick="valvulas_protesicas_generar_informe()"/>
               NO <input type="radio" name="regurgitacion_paravalvular_valvula_aorta_protesica" value="0" onclick="valvulas_protesicas_generar_informe()" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
                       
                         <tr>
                       <td>
                       <div align="center">
                       Contorno del Jet
                       </div>
                       </td>
                       
                       <td>  
                         <select id="contorno_jet_valvula_aorta_protesica" onchange="valvula_aorta_protesica_control_formulario(1)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		" id_contorno_jet_valvula_aorta_protesica,"+
				                    		" descripcion "+
				                    		" FROM eco_contorno_jet_valvula_aorta_protesica"
                                            );
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
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
                       </tr>
                         <tr>
                       <td>
                       <div align="center">
                       Tiempo de Aceleraci&oacute;n
                       </div>
                       </td>
                       
                       <td>  
                         <select id="tiempo_de_aceleracion_valvula_aorta_protesica" onchange="valvula_aorta_protesica_control_formulario(2)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"  id_tiempo_aceleracion_valvula_aorta_protesica,"+
				                    		"  descripcion "+
				                    		"FROM eco_tiempo_aceleracion_valvula_aorta_protesica"
                                            );
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
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
                       </tr>
                         <tr>
       <td>
       
       <div align="center">
       
        Velocidad Pico
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='velocidad_pico_valvula_aorta_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_aorta_protesica_control_formulario(3)"/>m/s</div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        Gradiente Medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='gradiente_medio_valvula_aorta_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_aorta_protesica_control_formulario(4)"/>mmHg</div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        VTI TS
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='vtits_valvula_aorta_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_aorta_protesica_control_formulario(5)"/></div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        VTI 
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='vti_valvula_aorta_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_aorta_protesica_control_formulario(5)"/></div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        Radio
       </div>
       
       </td>
       
       
       
       <td>
       
       <div align='center'><input id='radio_valvula_aorta_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_aorta_protesica_control_formulario(5)"/>cm</div>
       
       </td>
       
       
       </tr>
       
              
        <tr>
       <td>
       
       <div align="center">
       
        DVI
       </div>
       
       </td>
       
       
       
       <td>
       
       <div align='center'><input id='dvi_valvula_aorta_protesica' type='text' maxlength='4' size='3' onkeyup="" disabled="disabled" readonly="readonly"/></div>
       
       </td>
       
       
       </tr>
       
       
              
        <tr>
       <td>
       
       <div align="center">
       
        CSA
       </div>
       
       </td>
       
       
       
       <td>
       
       <div align='center'><input id='csa_valvula_aorta_protesica' type='text' maxlength='4' size='2' onkeyup="" disabled="disabled" readonly="readonly"/>cm<sub>2</sub></div>
       
       </td>
       
       
       </tr>
                   
        <tr>
       <td>
       
       <div align="center">
       
        &Aacute;rea Efectiva del Orificio
       </div>
       
       </td>
       
       
       
       <td>
       
       <div align='center'><input id='area_efectiva_valvula_aorta_protesica' type='text' maxlength='4' size='2' onkeyup="" disabled="disabled" readonly="readonly"/>cm<sub>2</sub></div>
       
       </td>
       
       
       </tr>
                                              
                       </table> 
                       
                       
                       </div>



                       </td>
                       </tr>
</table>
</div>
</div>



   </div>
                       </td>
                       </tr>


   </table>
         </div>
                  
</div>
  
 

  
  <div id="mitral">

  <div align="center">
  <table width=100%>
  <tr>
  <td id="analisis_valvula_mitral_normal"> <!--valvula mitral normal  -->
  
           <div align="center">
   Observaciones<br>
    <textarea id="analisis_observaciones_mitral" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                                             </div>
                        <div id="valvula_mitral">
                            <ul>
                               <li><a href="#aspecto_valvula_mitral">Aspecto</a></li>
                               <li><a href="#insuficiencia_valvula_mitral">Funci&oacute;n Insuficiencia</a></li>
                                   <li><a href="#estenosis_valvula_mitral">Funci&oacute;n Estenosis</a></li>
                                   <li><div id="analisis_valvula_mitral_protesica" style="display: none">
                                     <a href="#protesis_valvula_mitral">Pr&oacute;tesis</a></div></li>
                           </ul>

<div id="aspecto_valvula_mitral" align="center">

  
    <table width='60%' border="1">
        <tr>
           <td>Aspecto 
           <select id="tipo_aspecto_valvula_mitral" onchange="valvulas_control_formulario_aspecto(1)">
           <option value="1" selected="selected">normal</option>
           <option value="2"> anormal</option>
           
           </select>
           
          </td>
           
        </tr>
        
        <tr>
        <td id="aspectos_valvula_mitral"  style="display: none">
        <table border="1" width="100%" >
             <tr>
				                   <td>Esclerosis</td>
				                   <td>si <input type="radio" name="aspecto_esclerosis_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_esclerosis_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                  <tr>
				                   <td>Calcificaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_calcificacion_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_calcificacion_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                   <tr>
				                   <td>Esclerocalcificaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_esclerocalcificacion_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_esclerocalcificacion_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				              
				                   <tr>
				                   <td>Engrosamiento</td>
				                   <td>si <input type="radio" name="aspecto_engrosamiento_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_engrosamiento_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
				                   <tr>
				                   <td>Perforaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_perforacion_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_perforacion_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                    <tr>
				                   <td>En domo</td>
				                   <td>si <input type="radio" name="aspecto_endomo_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_endomo_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                 
				                    <tr>
				                   <td>Restricci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_restriccion_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_restriccion_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                    <tr>
				                   <td>Plastia</td>
				                   <td>si <input type="radio" name="aspecto_plastia_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_plastia_valvula_mitral" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                   <tr>
				                   <td>Pr&oacute;tesis</td>
				                   <td>si <input type="radio" name="aspecto_protesis_valvula_mitral" onclick="valvulas_control_formulario_aspecto(3)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_protesis_valvula_mitral" onclick="valvulas_control_formulario_aspecto(3)" value="0" checked="checked"></td>
				                 </tr>
                          
                                   <tr>
				                   <td>Otros</td>
				                   <td>si <input type="radio" name="otros_protesis_valvula_mitral"  value="1"> </td>
				                   <td>no <input type="radio" name="otros_protesis_valvula_mitral"  value="0" checked="checked"></td>
				                 </tr>

			                 </table>
      </td>
   </tr>
  </table>  

  
  
  </div>
  
  <div id="insuficiencia_valvula_mitral" align="center">
  

  
  <table border="1">
  <tr>
  <td>
  Vena Contracta
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_venacontracta_valvula_mitral" onchange="valvulas_control_formulario_insuficiencia_mitral(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"vena.vena_contracta, "+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_vena_contracta_valvula_mitral vena, "+
				                    		"eco_grado_severidad gra "+
				                    		"WHERE "+
				                    		"vena.id_grado_severidad_fk = gra.id_grado_severidad "
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  <tr>
  <td>
  Jet Central
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_jetcentral_valvula_mitral" onchange="valvulas_control_formulario_insuficiencia_mitral(1)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"jet.area_jet, "+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_jet_central_valvula_mitral jet, "+
				                    		"eco_grado_severidad gra "+
				                    		"WHERE "+
				                    		"jet.id_grado_severidad_fk = gra.id_grado_severidad"

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  <tr>
  <td>
  Prolapso de Valva
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_prolapso_valvula_mitral" onchange="valvulas_control_formulario_insuficiencia_mitral(2)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"pro.existencia, "+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_prolapso_de_valva_valvula_mitral pro,"+
				                    		"eco_grado_severidad gra "+
				                    		"WHERE "+
				                    		"pro.id_grado_severidad_fk = gra.id_grado_severidad"

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  
    <tr>
  <td>
  Flujo de Venas Pulmonares
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_flujovenas_valvula_mitral" onchange="valvulas_control_formulario_insuficiencia_mitral(3)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"flu.flujo_venas_pulmonales, "+
				                    		"gra.id_grado_severidad "+
                                       		"FROM  "+
				                    		"eco_flujo_venas_pulmonales_valvula_mitral flu, "+
				                    		"eco_grado_severidad gra "+
				                    		"WHERE "+
				                    		"flu.id_grado_severidad_fk = gra.id_grado_severidad"


);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  
  <tr>
  <td>
  Flujo Transmitral
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_flujotrans_valvula_mitral" onchange="valvulas_control_formulario_insuficiencia_mitral(4)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"trans.descripcion,"+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_flujo_trasmitral_valvula_mitral trans, "+
				                    		"eco_grado_severidad gra "+
    			                    		"WHERE "+
				                    		"trans.id_grado_severidad_fk = gra.id_grado_severidad"

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  <tr>
  <td>
  Densidad (Cw Doppler)
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_densidadcwdoppler_valvula_mitral" onchange="valvulas_control_formulario_insuficiencia_mitral(5)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                            "den.densidad, "+
				              	            "gra.id_grado_severidad "+
				                         	"FROM "+
				                           	"eco_densidad_valvula_mitral den, "+
				                           	"eco_grado_severidad gra "+
				                          	"WHERE "+
				                         	"den.id_grado_severidad_fk = gra.id_grado_severidad "

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  <tr>
  <td>
  Forma Jet (Cw Doppler)
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_formajetcwdoppler_valvula_mitral" onchange="valvulas_control_formulario_insuficiencia_mitral(6)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(

				                    		"SELECT "+
				                    	    "jet.forma,"+
				                    		"gra.id_grado_severidad"+ 
				                    	    " FROM "+
				                    		"eco_forma_jet_valvula_mitral jet, "+
				                    		"eco_grado_severidad gra "+
				                    	  	"WHERE "+
				                    		"jet.id_grado_severidad_fk = gra.id_grado_severidad"

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  <tr>
  <td>
Tamaño Ventr&iacute;culo
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_tamventriculo_valvula_mitral" onchange="valvulas_control_formulario_insuficiencia_mitral(7)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
		                    		"SELECT "+
    	                            "tam.tamano_ventriculo, "+
	    		              	    "gra.id_grado_severidad "+
				                  	"FROM "+
				              	    "eco_tamano_ventriculo_valvula_mitral tam,"+
				              	    "eco_grado_severidad gra "+
				                  	"WHERE "+
				              	    "tam.id_grado_severidad_fk = gra.id_grado_severidad"

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
   <tr>
       
       <td colspan="2">
       <div align="center">
       
     <b>  C&aacute;lculo de la EROA </b>
       </div>
       
       
       </td>
       </tr>
       
       
        <tr>
       <td>
       
       <div align="center">
       
       Radio PISA
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_radiopisa_valvula_mitral' type='text' maxlength='3' size='2' onkeyup="convertir_cm(7)"/>cm  <input id='insuficiencia_radiopisa_valvula_mitral_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(7)"/>mm </div>
       
       </td>
       
       
       </tr>
       
       
        <tr>
       <td>
       
       <div align="center">
       
       Velocidad de Aliasing
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_velaliasing_valvula_mitral' type='text' maxlength='3' size='2' onkeyup="convertir_cm_m(3)" />cm/s  <input id='insuficiencia_velaliasing_valvula_mitral_m' type='text' maxlength='3' size='2' onkeyup="convertir_m_cm(3)" />m/s </div>
       
       </td>
       
       
       </tr>
       
         
        <tr>
       <td>
       
       <div align="center">
       
       Vel pico del jet de regurgitaci&oacute;n
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_velpicojetregurgitacion_valvula_mitral' type='text' maxlength='3' size='2' onkeyup="convertir_cm_m(4)" />cm/s <input id='insuficiencia_velpicojetregurgitacion_valvula_mitral_m' type='text' maxlength='3' size='2' onkeyup="convertir_m_cm(4)" />m/s </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
       EROA
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_eroa_valvula_mitral' type='text' maxlength='2' size='2' disabled="disabled" />cm<sup>2</sup> </div>
       
       </td>
       
       
       </tr>
       
              <tr>
       
       <td colspan="2">
       <div align="center">
       
       <b>C&aacute;lculo del Volumen y Fracci&oacute;n Regurgitante</b>
       </div>
       
       
       </td>
       </tr>
       
       
       <tr>
       <td>
       
       <div align="center">
       
       MAd (Di&aacute;metro anillo Mitral)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_mad_valvula_mitral' type='text' maxlength='3' size='2' onkeyup="convertir_cm(8)" />cms <input id='insuficiencia_mad_valvula_mitral_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(8)" />mm </div>
       
       </td>
       
       
       </tr>
       

       
       
        <tr>
       <td>
       
       <div align="center">
       
       VTIMA (Integral Valvula Mitral)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_vtima_valvula_mitral' type='text' maxlength='3' size='2' onkeyup="convertir_cm(9)"  />cms <input id='insuficiencia_vtima_valvula_mitral_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(9)"  />mm </div>
       
       </td>
       
       
       </tr>
       
       
            <tr>
       <td>
       
       <div align="center">
       
       PAD (Di&aacute;metro Arteria Pulmonal)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_pad_valvula_mitral' type='text' maxlength='3' size='2' onkeyup="convertir_cm(10)" />cms <input id='insuficiencia_pad_valvula_mitral_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(10)" />mm </div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        VTI pa (Integral de Arteria Pulmonal)
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_vtipa_valvula_mitral' type='text' maxlength='3' size='2' onkeyup="convertir_cm(11)" />cms <input id='insuficiencia_vtipa_valvula_mitral_mm' type='text' maxlength='3' size='2' onkeyup="convertir_mm(11)" />mm </div>
       
       </td>
       
       
       </tr>
       
       <tr>
       <td>
       
       <div align="center">
       Volumen Regurgitante
        
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_rvol_valvula_mitral' type='text' maxlength='2' size='2'  disabled="disabled"/>ml </div>
       
       </td>
       
       
       </tr>
       
            <tr>
       <td>
       
       <div align="center">
       
        Fracci&oacute;n Regurgitante
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_fr_valvula_mitral' type='text' maxlength='2' size='2' disabled="disabled"  />% </div>
       
       </td>
       
       
       </tr>
  
  
  </table>  
  
  </div>
  
  
  
  



<div id="estenosis_valvula_mitral" >
  
  
<div align="center">
<table border="1" >
   <tr>
       <td width="70%">
       
       <div align="center">
       
        Gradiente pico
       </div>
       
       </td>
       <td width="30%">
       
       <div align='center'><input id='estenosis_gradientepico_valvula_mitral' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_mitral(0)"  />mmHg </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td >
       
       <div align="center">
       
        Gradiente medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_gradientemedio_valvula_mitral' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_mitral(1)" />mmHg </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Velocidad pico
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velocidadpico_valvula_mitral' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_mitral(2)"  />m/s </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Velocidad Medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velocidadmedio_valvula_mitral' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_mitral(3)"  />m/s </div>
       
       </td>
       
       
       </tr>
         <tr>
       <td>
       
       <div align="center">
       
        VTI
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_vti_valvula_mitral' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_mitral(4)"/>cms </div>
       
       </td>
       
       
       </tr>
         <tr>
       <td>
       
       <div align="center">
       
        THP
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_thp_valvula_mitral' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_mitral(5)" />ml/s </div>
       
       </td>
       
       
       </tr>
       
               <tr>
       <td>
       
       <div align="center">
       
        &Aacute;rea V&aacute;lvula Mitral
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_area_valvula_mitral' type='text' maxlength='4' size='3' disabled="disabled" /></div>
       
       </td>
       
       
       </tr>
     <!--      <tr>
       <td>
       
       <div align="center">
       
       Velocidad de Regurgitacion Tricuspidea
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velregurgitaciontricuspidea_valvula_mitral' type='text' maxlength='2' size='2'  /></div>
       
       </td>
       
       
       </tr>
       
              <tr>
       <td>
       
       <div align="center">
       
       PAP
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_pap_valvula_mitral' type='text' maxlength='2' size='2' disabled="disabled"  /></div>
       
       </td>
       
       
       </tr>
     

        --> 






       </table>

</div>


  </div>     


<div id="protesis_valvula_mitral" align="center" >
  <div id="analisis_valvula_mitral_protesica2" style="display: none">
   <table border="1" >
   <tr > <!-- valvula mitral protesica -->
  <td >
  <div id="valvula_mitral_protesica" align="center" >
                       
                                               Observaciones Mitral Prot&eacute;sica<br>
                              <textarea id="analisis_observaciones_mitral_protesica" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                       <table width="60%" border="1">
                       <tr>
                       <td>
                       <div align="center">
                       Tipo de Pr&oacute;tesis
                       </div>
                       </td>
                       
                       <td>  
                         <select id="protesis_valvula_protesica_mitral" onchange="valvula_mitral_protesica_control_formulario(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		" id_protesis_valvulas,"+
				                    		" descripcion "+
				                    		"FROM eco_protesis_valvulas"
                                            );
				                    
				                    
				              int i =0;
				                 %>
				
		                    	 <%  while (rs.next()) {
		                    	 
		                    		 if(i == 1){
				                    	    %>  <option value="<%=rs.getString(1)%>" selected="selected"><%=rs.getString(2)%></option>
			                    	<%    }else{
			                    		%>
			                    		  <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
			                    		
			                    		<% 
			                    	}
		                    	 
		                    	 %>
		                    	     
		                    	   
				                    
				                 
				                 
				                 <%i++;
				            
		                    	 
		                    	 }
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
                       
                       
                         </td>
                       </tr>
                         <tr>
                       <td>
                       <div align="center">
                       Regurgitaci&oacute;n paravalvular
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="regurgitacion_paravalvular_valvula_mitral_protesica" value="1" onclick="valvulas_protesicas_generar_informe()"/>
               NO <input type="radio" name="regurgitacion_paravalvular_valvula_mitral_protesica" value="0" onclick="valvulas_protesicas_generar_informe()" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
          
                         <tr>
                       <td>
                       <div align="center">
                       Tiempo de Hemipresi&oacute;n
                       </div>
                       </td>
                       
                       <td>  
                         <select id="tiempo_de_hemipresion_valvula_mitral_protesica" onchange="valvula_mitral_protesica_control_formulario(1)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		" id_tiempo_hemipresion_valvula_mitral_protesica,"+
				                    		" descripcion "+
				                    		" FROM eco_tiempo_hemipresion_valvula_mitral_protesica"

                                            );
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
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
                       </tr>
                         <tr>
       <td>
       
       <div align="center">
       
        Velocidad Pico
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='velocidad_pico_valvula_mitral_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_mitral_protesica_control_formulario(2)"/>m/s</div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        Gradiente Medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='gradiente_medio_valvula_mitral_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_mitral_protesica_control_formulario(3)"/>mmHg</div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        VTI TS
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='vtits_valvula_mitral_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_mitral_protesica_control_formulario(4)"/></div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        VTI 
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='vti_valvula_mitral_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_mitral_protesica_control_formulario(4)"/></div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        Radio
       </div>
       
       </td>
       
       
       
       <td>
       
       <div align='center'><input id='radio_valvula_mitral_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_mitral_protesica_control_formulario(4)"/>cm</div>
       
       </td>
       
       
       </tr>
       
              
        <tr>
       <td>
       
       <div align="center">
       
        DVI
       </div>
       
       </td>
       
       
       
       <td>
       
       <div align='center'><input id='dvi_valvula_mitral_protesica' type='text' maxlength='4' size='2' onkeyup="" disabled="disabled" readonly="readonly"/></div>
       
       </td>
       
       
       </tr>
       
       
              
        <tr>
       <td>
       
       <div align="center">
       
        CSA
       </div>
       
       </td>
       
       
       
       <td>
       
       <div align='center'><input id='csa_valvula_mitral_protesica' type='text' maxlength='4' size='2' onkeyup="" disabled="disabled" readonly="readonly"/>cm<sub>2</sub></div>
       
       </td>
       
       
       </tr>
                   
        <tr>
       <td>
       
       <div align="center">
       
        EOA
       </div>
       
       </td>
       
       
       
       <td>
       
       <div align='center'><input id='eoa_valvula_mitral_protesica' type='text' maxlength='4' size='2' onkeyup="" disabled="disabled" readonly="readonly"/>cm<sub>2</sub></div>
       
       </td>
       
       
       </tr>
                                              
                       </table> 
                       
                       
                       </div>
  </td>
  
  </tr>
</table>
</div>
</div>



  </td>
  
  </tr>

  
  </table>
  </div>
  
  
  


   </div>
   

 
   
   
   
   
   
   
   
   
   
  <div id="tricuspidea">

  <div align="center">
  <table width="100%">
  <tr>
        
  <td id="analisis_valvula_tricuspidea_normal"><!-- analisis valvula tricuspidea normal -->
     <div align="center">
    Observaciones<br>
    <textarea id="analisis_observaciones_tricuspidea" cols="50" rows="5" onkeyup="vistapreviaFormularios()"> </textarea>
    </div>
    
       <div id="valvula_tricuspidea">
                            <ul>
                               <li><a href="#aspecto_valvula_tricuspidea">Aspecto</a></li>
                               <li><a href="#insuficiencia_valvula_tricuspidea">Funci&oacute;n Insuficiencia</a></li>
                               <li><a href="#estenosis_valvula_tricuspidea">Funci&oacute;n Estenosis</a></li>
                               <li><div id ="analisis_valvula_tricuspidea_protesica" style="display: none">
                                <a href="#protesis_valvula_tricuspidea">Pr&oacute;tesis</a></div></li>
                           </ul>

<div id="aspecto_valvula_tricuspidea" align="center">
  
  
    <table width='60%' border="1">
        <tr>
           <td>Aspecto 
           <select id="tipo_aspecto_valvula_tricuspidea" onchange="valvulas_control_formulario_aspecto(1)">
           <option value="1" selected="selected">normal</option>
           <option value="2"> anormal</option>
           
           </select>
           
          </td>
           
        </tr>
        
        <tr>
        <td id="aspectos_valvula_tricuspidea" style="display: none">
        <table border="1" width="100%" >
             <tr>
				                   <td>Esclerosis</td>
				                   <td>si <input type="radio" name="aspecto_esclerosis_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_esclerosis_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                  <tr>
				                   <td>Calcificaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_calcificacion_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_calcificacion_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                   <tr>
				                   <td>Esclerocalcificaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_esclerocalcificacion_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_esclerocalcificacion_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				              
				                   <tr>
				                   <td>Engrosamiento</td>
				                   <td>si <input type="radio" name="aspecto_engrosamiento_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_engrosamiento_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
				                   <tr>
				                   <td>Perforaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_perforacion_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_perforacion_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                    <tr>
				                   <td>En domo</td>
				                   <td>si <input type="radio" name="aspecto_endomo_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_endomo_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                 
				                    <tr>
				                   <td>Restricci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_restriccion_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_restriccion_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                    <tr>
				                   <td>Plastia</td>
				                   <td>si <input type="radio" name="aspecto_plastia_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_plastia_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                 <tr>
				                   <td>Pr&oacute;tesis</td>
				                   <td>si <input type="radio" name="aspecto_protesis_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(3)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_protesis_valvula_tricuspidea" onclick="valvulas_control_formulario_aspecto(3)" value="0" checked="checked"></td>
				                 </tr>

				                 <tr>
				                   <td>Otros</td>
				                   <td>si <input type="radio" name="otros_protesis_valvula_tricuspidea" value="1"> </td>
				                   <td>no <input type="radio" name="otros_protesis_valvula_tricuspidea" value="0" checked="checked"></td>
				                 </tr>
   
				                 </table>
      </td>
   </tr>
  </table>  
  
  
  
  </div>
  
  <div id="insuficiencia_valvula_tricuspidea" align="center">
  
  <div align="center">
  <table border="1">
  <tr>
  <td>
  <div align="center">
  Morfolog&iacute;a
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_morfologia_valvula_tricuspidea" onchange="valvulas_control_formulario_insuficiencia_tricuspidea(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
    		                    	        "mor.tipo_morfologia, "+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_morfologia_valvula_tricuspidea mor, "+
				                    		"eco_grado_severidad gra "+
			                    	    	"WHERE "+
				                    		"mor.id_grado_severidad_fk = gra.id_grado_severidad"

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
    <tr>
  <td>
  <div align="center">
  Tamaño de cavidades derechas
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_tamanocavidades_valvula_tricuspidea" onchange="valvulas_control_formulario_insuficiencia_tricuspidea(1)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
     		                    	        "tam.tamano_cavidades,"+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_tamano_cavidades_derechas_valvula_tricuspidea tam,"+ 
				                    		"eco_grado_severidad gra "+
			                    	    	"WHERE "+
				                    		"tam.id_grado_severidad_fk = gra.id_grado_severidad"

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
   <tr>
  <td>
  <div align="center">
  &Aacute;rea Jet Central
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_areajetcentral_valvula_tricuspidea" onchange="valvulas_control_formulario_insuficiencia_tricuspidea(2)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    	    "ar.tipo_de_area, "+
				                    		"gra.id_grado_severidad "+
				                    		" FROM "+
				                    		"eco_area_jet_central_valvula_tricuspidea ar,"+ 
				                    		"eco_grado_severidad gra"+
				                    	    " WHERE "+
				                    		"ar.id_grado_severidad_fk = gra.id_grado_severidad"
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
     <tr>
  <td>
  <div align="center">
 Densidad del Jet
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_densidadjet_valvula_tricuspidea" onchange="valvulas_control_formulario_insuficiencia_tricuspidea(3)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    	    "den.densidad,"+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_densidad_jet_valvula_tricuspidea den, "+
				                    		"eco_grado_severidad gra "+
				                    	    "WHERE "+
				                    		"den.id_grado_severidad_fk = gra.id_grado_severidad"
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
   <tr>
  <td>
  <div align="center">
Contorno del jet
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_contornojet_valvula_tricuspidea" onchange="valvulas_control_formulario_insuficiencia_tricuspidea(4)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    	    "con.contorno,"+
				                    	    "gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_contorno_jet_valvula_tricuspidea con,"+ 
				                    		"eco_grado_severidad gra "+
				                    	    "WHERE "+ 
				                    		"con.id_grado_severidad_fk = gra.id_grado_severidad"
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
     <tr>
  <td>
  <div align="center">
Di&aacute;metro Vena Contracta
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_diavenacontracta_valvula_tricuspidea" onchange="valvulas_control_formulario_insuficiencia_tricuspidea(5)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    "SELECT "+
				                    "dia.diametro,"+
				                	"gra.id_grado_severidad "+
				                	"FROM "+
				                	"eco_diametro_vena_contracta_valvula_tricuspidea dia, "+
				                	"eco_grado_severidad gra "+
				                    " WHERE "+
				                	"dia.id_grado_severidad_fk = gra.id_grado_severidad"
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
 <tr>
  <td>
  <div align="center">
Radio PISA
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_radiopisa_valvula_tricuspidea" onchange="valvulas_control_formulario_insuficiencia_tricuspidea(6)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                            "rad.radio,"+
				                	        "gra.id_grado_severidad "+
				                	        "FROM "+
				                           	" eco_radio_pisa_valvula_tricuspidea rad, "+
				                        	" eco_grado_severidad gra "+
				                    	    " WHERE "+
				                	        " rad.id_grado_severidad_fk = gra.id_grado_severidad"
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
   <tr>
  <td>
  <div align="center">
Flujo Hep&aacute;tico
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_flujohepatico_valvula_tricuspidea" onchange="valvulas_control_formulario_insuficiencia_tricuspidea(7)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    	    "flu.flujo,"+
				                    		"gra.id_grado_severidad "+ 
				                    		"FROM "+
				                    		"eco_flujo_hepatico_valvula_tricuspidea flu,"+
				                    		"eco_grado_severidad gra "+
				                    	    "WHERE "+
				                    		"flu.id_grado_severidad_fk = gra.id_grado_severidad");

				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>


<tr>
       <td >
       
       <div align="center">
       
        Velocidad de regurgitaci&oacute;n
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='insuficiencia_velregurgitacion_valvula_tricuspidea' type='text' maxlength='5' size='4' onkeyup="calcular_psap_venacava2()" />m/s </div>
       
       </td>
       
       
       </tr>

<tr>
       <td >
       
       <div align="center">
       
        PSAP
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='psap_valvula_tricuspidea' type='text' maxlength='5' size='4' disabled='disabled' /> </div>
       
       </td>
       
       
       </tr>
       
         <tr>


  </table>
  
  
  </div>
  
  
  
  

  

  </div>


 <div id="estenosis_valvula_tricuspidea" align="center"  >

<table border="1" >
   <tr>
       <td width="70%">
       
       <div align="center">
       
        Gradiente pico
       </div>
       
       </td>
       <td width="30%">
       
       <div align='center'><input id='estenosis_gradientepico_valvula_tricuspidea' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_tricuspidea(0)" />mmHg </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td >
       
       <div align="center">
       
        Gradiente medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_gradientemedio_valvula_tricuspidea' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_tricuspidea(1)" />mmHg </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Velocidad pico
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velocidadpico_valvula_tricuspidea' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_tricuspidea(2)" />m/s </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Velocidad Medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velocidadmedio_valvula_tricuspidea' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_tricuspidea(3)" />m/s </div>
       
       </td>
       
       
       </tr>
      
         <tr>
       <td>
       
       <div align="center">
       
        THP
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_thp_valvula_tricuspidea' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_tricuspidea(4)" />m/s </div>
       
       </td>
       
       
       </tr>
           <tr>
           <td colspan="2">
           C&aacute;lculo &Aacute;rea Valvular
           
           </td>
           </tr>
           
               <tr>
       <td>
       
       <div align="center">
       
        VTI pulmonar
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_vtipulmonal_valvula_tricuspidea' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_tricuspidea(5)" />m/s </div>
       
       </td>
       
       
       </tr>
       
                <tr>
               
       <td>
       
       <div align="center">
       
        Di&aacute;metro Pulmonar
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_diametropulmonal_valvula_tricuspidea' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_tricuspidea(5)" />cm </div>
       
       </td>
       
       
       </tr>
          <tr>
       <td>
       
       <div align="center">
       
        VTI TRICUSPIDEA
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_vti_valvula_tricuspidea' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_tricuspidea(5)"/>cms </div>
       
       </td>
       
       
       </tr>
       <tr>
       <td>
       
       <div align="center">
       
        &Aacute;rea Valvular
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_areavalvular_valvula_tricuspidea' type='text' maxlength='2' size='2' disabled="disabled" />cm <sub>2</sub> </div>
       
       </td>
       
       
       </tr>
          </table> 
          
        
          
  </div>
<div id="protesis_valvula_tricuspidea" align="center">
  <div id ="analisis_valvula_tricuspidea_protesica2" style="display: none">
   <table border="1" >
    <tr>
  <td  ><!-- analisis valvula tricuspidea protesica -->
  <div id="valvula_tricuspidea_protesica" align="center">
                       
                                               Observaciones Tric&uacute;spide Prot&eacute;sica<br>
                              <textarea id="analisis_observaciones_tricuspidea_protesica" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                       <table width="60%" border="1">
                       <tr>
                       <td>
                       <div align="center">
                       Tipo de Pr&oacute;tesis
                       </div>
                       </td>
                       
                       <td>  
                         <select id="protesis_valvula_protesica_tricuspidea" onchange="valvula_tricuspidea_protesica_control_formulario(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		" id_protesis_valvulas,"+
				                    		" descripcion "+
				                    		"FROM eco_protesis_valvulas"
                                            );
				                    
				                    
				              int i =0;
				                 %>
				
		                    	 <%  while (rs.next()) {
		                    	 
		                    		 if(i == 1){
				                    	    %>  <option value="<%=rs.getString(1)%>" selected="selected"><%=rs.getString(2)%></option>
			                    	<%    }else{
			                    		
			                    		%>
			                    		  <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
			                    		<%
			                    	}
		                    	 
		                    	 %>
		                    	     
		                    	   
				                    
				                 
				                 
				                 <%i++;
				            
		                    	 
		                    	 }
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
                       
                       
                         </td>
                       </tr>
                         <tr>
                       <td>
                       <div align="center">
                       Regurgitaci&oacute;n paravalvular
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="regurgitacion_paravalvular_valvula_tricuspidea_protesica" value="1" onclick="valvulas_protesicas_generar_informe()"/>
               NO <input type="radio" name="regurgitacion_paravalvular_valvula_tricuspidea_protesica" value="0" onclick="valvulas_protesicas_generar_informe()" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
                        <tr>
       <td>
       
       <div align="center">
       
        Velocidad Pico
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='velocidad_pico_valvula_tricuspidea_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_tricuspidea_protesica_control_formulario(1)"/>m/s</div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        Gradiente Medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='gradiente_medio_valvula_tricuspidea_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_tricuspidea_protesica_control_formulario(2)"/>mmHg</div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
       Tiempo Hemipresi&oacute;n
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='tiempo_de_hemipresion_valvula_tricuspidea_protesica' type='text' maxlength='3' size='2' onkeyup="valvula_tricuspidea_protesica_control_formulario(3)"/>ms</div>
       
       </td>
       
       
       </tr>
       
                       
                       
                       </table>
  </div>
  </td>
  </tr>
</table>
</div>
</div>

   </div>            
  </td>
  </tr>
  
   
  </table>
          </div>
                            
                      







   </div>
  <div id="pulmonal" align="center">
  
  
  
   <div align="center">
   <table width="100%">
   <tr>
   <td id="analisis_valvula_pulmonal_normal" ><!-- analisis valvula pulmonar normal -->
    
       <div align="center">Observaciones<br>
    <textarea id="analisis_observaciones_pulmonal" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                             </div>
                             
                                <div id="valvula_pulmonal">
                            <ul>
                               <li><a href="#aspecto_valvula_pulmonal">Aspecto</a></li>
                               <li><a href="#insuficiencia_valvula_pulmonal">Funci&oacute;n Insuficiencia</a></li>
                               <li><a href="#estenosis_valvula_pulmonal">Funci&oacute;n Estenosis</a></li>
                               <li> <div id ="analisis_valvula_pulmonal_protesica" style="display:none" >
                                  <a href="#protesis_valvula_pulmonal">Pr&oacute;tesis</a></div></li>
                           </ul>

<div id="aspecto_valvula_pulmonal" align="center">
  
  
    <table width='60%' border="1">
        <tr>
           <td>Aspecto 
           <select id="tipo_aspecto_valvula_pulmonal" onchange="valvulas_control_formulario_aspecto(1)">
           <option value="1" selected="selected">normal</option>
           <option value="2"> anormal</option>
           
           </select>
           
          </td>
           
        </tr>
        
        <tr>
        <td id="aspectos_valvula_pulmonal" style="display: none">
        <table border="1" width="100%" >
             <tr>
				                   <td>Esclerosis</td>
				                   <td>si <input type="radio" name="aspecto_esclerosis_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_esclerosis_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked" ></td>
				                 </tr>
				                  <tr>
				                   <td>Calcificaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_calcificacion_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_calcificacion_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                   <tr>
				                   <td>Esclerocalcificaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_esclerocalcificacion_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_esclerocalcificacion_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				              
				                   <tr>
				                   <td>Engrosamiento</td>
				                   <td>si <input type="radio" name="aspecto_engrosamiento_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_engrosamiento_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                
				                   <tr>
				                   <td>Perforaci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_perforacion_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_perforacion_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                    <tr>
				                   <td>En domo</td>
				                   <td>si <input type="radio" name="aspecto_endomo_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_endomo_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                 
				                    <tr>
				                   <td>Restricci&oacute;n</td>
				                   <td>si <input type="radio" name="aspecto_restriccion_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)"value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_restriccion_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                 
				                    <tr>
				                   <td>Plastia</td>
				                   <td>si <input type="radio" name="aspecto_plastia_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_plastia_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(2)" value="0" checked="checked"></td>
				                 </tr>
				                          <tr>
				                   <td>Pr&oacute;tesis</td>
				                   <td>si <input type="radio" name="aspecto_protesis_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(3)" value="1"> </td>
				                   <td>no <input type="radio" name="aspecto_protesis_valvula_pulmonal" onclick="valvulas_control_formulario_aspecto(3)" value="0" checked="checked"></td>
				                 </tr>

				                          <tr>
				                   <td>Otros</td>
				                   <td>si <input type="radio" name="otros_protesis_valvula_pulmonal"  value="1"> </td>
				                   <td>no <input type="radio" name="otros_protesis_valvula_pulmonal"  value="0" checked="checked"></td>
				                 </tr>
				                 
				                 
				                 </table>
      </td>
   </tr>
  </table>  
  
  
  
  </div>
  
  <div id="insuficiencia_valvula_pulmonal">
  
  
  <div align="center">

<table border="1">

  <tr>
  <td>
  <div align="center">
Morfolog&iacute;a
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_morfologia_valvula_pulmonal" onchange="valvulas_control_formulario_insuficiencia_pulmonal(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
  			                    	        "mor.tipo_morfologia,"+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_morfologia_valvula_pulmonar mor, "+
				                    		"eco_grado_severidad gra "+
				                    	   	"WHERE "+
				                    		"mor.id_grado_severidad_fk = gra.id_grado_severidad "
);

				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>

  <tr>
  <td>
  <div align="center">
Tamaño Ventr&iacute;culo Derecho
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_tamventriculoderecho_valvula_pulmonal" onchange="valvulas_control_formulario_insuficiencia_pulmonal(1)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    	    "tam.tamano, "+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_tamano_ventriculo_derecho_valvula_pulmonar tam,"+ 
				                    		"eco_grado_severidad gra "+
				                    	    "WHERE "+
				                    		"tam.id_grado_severidad_fk = gra.id_grado_severidad "

);

				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
    <tr>
  <td>
  <div align="center">
Tamaño del Jet
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_tamjet_valvula_pulmonal" onchange="valvulas_control_formulario_insuficiencia_pulmonal(2)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    	    "tam.tamano,"+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_tamano_jet_valvula_pulmonar tam, "+
				                    		"eco_grado_severidad gra" +
				                    	    " WHERE "+
				                    		"tam.id_grado_severidad_fk = gra.id_grado_severidad"
);

				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  
  </tr>
    <tr>
  <td>
  <div align="center">
Densidad del jet
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_densidadjet_valvula_pulmonal" onchange="valvulas_control_formulario_insuficiencia_pulmonal(3)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
 			                    	        "den.tipo_densidad,"+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_densidad_jet_valvula_pulmonar den, "+
				                    		"eco_grado_severidad gra "+
				                    	    "WHERE "+
				                    		"den.id_grado_severidad_fk = gra.id_grado_severidad"
);

				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  
    <tr>
  <td>
  <div align="center">
Flujo pulmonar comparado con flujo sist&eacute;mico
  </div>
  </td>
  <td>
  
    <div align="center">
        <select id="insuficiencia_flupulcomflusis_valvula_pulmonal" onchange="valvulas_control_formulario_insuficiencia_pulmonal(4)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
 			                    	        "flu.descripcion_flujo, "+
				                    		"gra.id_grado_severidad "+
				                    		"FROM "+
				                    		"eco_flujo_pulmonar_flujo_sistemico_valvula_pulmonar flu, "+
				                    		"eco_grado_severidad gra "+
				                    	    "WHERE "+
				                    		"flu.id_grado_severidad_fk = gra.id_grado_severidad"
);

				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(2)%>"><%=rs.getString(1)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
       
       </div>
  
  </td>
  </tr>
  
  
   </table>



</div>  
  
  

  </div>


 <div id="estenosis_valvula_pulmonal" >
 <div align="center">
<table border="1" >
   <tr>
       <td width="70%">
       
       <div align="center">
       
        Gradiente pico
       </div>
       
       </td>
       <td width="30%">
       
       <div align='center'><input id='estenosis_gradientepico_valvula_pulmonal' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_pulmonal(0)"/>mmHg </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td >
       
       <div align="center">
       
        Gradiente medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_gradientemedio_valvula_pulmonal' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_pulmonal(1)" />mmHg </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Velocidad pico
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velocidadpico_valvula_pulmonal' type='text' maxlength='3' size='3' onkeyup="valvulas_control_formulario_estenosis_pulmonal(2)" />m/s </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Velocidad Medio
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_velocidadmedio_valvula_pulmonal' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_pulmonal(3)"/>m/s </div>
       
       </td>
       
       
       </tr>
         <tr>
       <td>
       
       <div align="center">
       
        VTI
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_vti_valvula_pulmonal' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_pulmonal(4)"/>cms </div>
       
       </td>
       
       
       </tr>
         <tr>
       <td>
       
       <div align="center">
       
        THP
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='estenosis_thp_valvula_pulmonal' type='text' maxlength='3' size='3'  onkeyup="valvulas_control_formulario_estenosis_pulmonal(5)"/>m/s </div>
       
       </td>
       
       
       </tr>
       </table>
       </div>
</div>

<div id="protesis_valvula_pulmonal" align="center">
  <div id ="analisis_valvula_pulmonal_protesica2" style="display:none" >
   <table border="1" >
   <tr>
   <td ><!-- analisis valvula pulmonar protesica -->
    <div id="valvula_pulmonal_protesica" align="center">
                       
                                               Observaciones V&aacute;lvula Pulmonar Prot&eacute;sica<br>
                              <textarea id="analisis_observaciones_pulmonal_protesica" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                       <table width="60%" border="1">
                       <tr>
                       <td>
                       <div align="center">
                       Tipo de Pr&oacute;tesis
                       </div>
                       </td>
                       
                       <td>  
                         <select id="protesis_valvula_protesica_pulmonal" onchange="valvula_pulmonal_protesica_control_formulario(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		" id_protesis_valvulas,"+
				                    		" descripcion "+
				                    		"FROM eco_protesis_valvulas"
                                            );
				                    
				                    
				              int i =0;
				                 %>
				
		                    	 <%  while (rs.next()) {
		                    	 
		                    		 if(i == 1){
				                    	    %>  <option value="<%=rs.getString(1)%>" selected="selected"><%=rs.getString(2)%></option>
			                    	<%    }else{
			                    		
			                    		%>
			                    		  <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
			                    		<%
			                    	}
		                    	 
		                    	 %>
		                    	     
		                    	   
				                    
				                 
				                 
				                 <%i++;
				            
		                    	 
		                    	 }
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
                       
                       
                         </td>
                       </tr>
                         <tr>
                       <td>
                       <div align="center">
                       Regurgitaci&oacute;n paravalvular
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="regurgitacion_paravalvular_valvula_pulmonal_protesica" value="1" onclick="valvulas_protesicas_generar_informe()"/>
               NO <input type="radio" name="regurgitacion_paravalvular_valvula_pulmonal_protesica" value="0" onclick="valvulas_protesicas_generar_informe()" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
                        <tr>
       <td>
       
       <div align="center">
       
        Velocidad Pico
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='velocidad_pico_valvula_pulmonal_protesica' type='text' maxlength='3' size='3' onkeyup="valvula_pulmonal_protesica_control_formulario(1)"/>m/s</div>
       
       </td>
       
       
       </tr>
          <tr>
                       <td>
                       <div align="center">
                       V&aacute;lvula Engrosada o Inm&oacute;vil
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="engrosado_valvula_pulmonal_protesica" value="1" onclick="valvula_pulmonal_protesica_control_formulario(2)"/>
               NO <input type="radio" name="engrosado_valvula_pulmonal_protesica" value="0" onclick="valvula_pulmonal_protesica_control_formulario(2)" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
                       
                       
                          <tr>
                       <td>
                       <div align="center">
                     Estrechamiento mapa color
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="estrechamiento_valvula_pulmonal_protesica" value="1" onclick="valvula_pulmonal_protesica_control_formulario(3)"/>
               NO <input type="radio" name="estrechamiento_valvula_pulmonal_protesica" value="0" onclick="valvula_pulmonal_protesica_control_formulario(3)" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
                       
                       
                          <tr>
                       <td>
                       <div align="center">
                       Aumento del pico estudios seriados
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="estudios_seriados_valvula_pulmonal_protesica" value="1" onclick="valvula_pulmonal_protesica_control_formulario(4)"/>
               NO <input type="radio" name="estudios_seriados_valvula_pulmonal_protesica" value="0" onclick="valvula_pulmonal_protesica_control_formulario(4)" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
                       
                       
                          <tr>
                       <td>
                       <div align="center">
                       Disfunci&oacute;n Ventricular Derecha
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="disfuncion_seriados_valvula_pulmonal_protesica" value="1" onclick="valvula_pulmonal_protesica_control_formulario(5)"/>
               NO <input type="radio" name="disfuncion_seriados_valvula_pulmonal_protesica" value="0" onclick="valvula_pulmonal_protesica_control_formulario(5)" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
        
       
                       
                       
                       </table>
  </div>
   </td>
   </tr>

</table>
</div>
</div>


   </div>         
   </td>
   </tr>
 
   
   </table>
   
   
        </div>
                                

    </div>
</div>
       </div>
        </td>
        </tr>
       </table>
       </div>
  </div> <!--fin div formulario valvulas  -->
  
  <div id="formulario_ventriculoizquierdo">
  <div align="center">
  <table border ="1" width="60%">
  <tr>
  <td align="center">
             
                <div align="center"> Observaciones:</div> 
                 <textarea id="observaciones_ventriculo_izquierdo" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>


  </td>
  
  </tr>
  
  
  <tr>
  <td>
  <div id="formulario_funcion_sistolica_diastolica">
   <ul>
    <li><a href="#funcion_sistolica">Funci&oacute;n Sist&oacute;lica</a></li>
    <li><a href="#funcion_diastolica">Funci&oacute;n Diast&oacute;lica</a></li>
    </ul>
  
  <div id="funcion_sistolica">
  
  <div align="center">
  
    <table border="1" >
          <tr>
          
            <td width="50%"> <div align="center">Forma </div></td>
               <td> 
                       <select id="forma_ventriculo_izquierdo" onchange="ventriculo_izquierdo_generar_informe()">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"id_forma_ventriculo_izquierdo," +
				                    		"forma "+
				                    		"FROM saim.eco_forma_ventriculo_izquierdo "

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
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
               
            </tr>
  
 <tr>

       <td>
       
       <div align="center">
       
       VFD
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='vfd_diastole' type='text' maxlength='3' size='2' onkeyup="ventriculo_izquierdo_control_formulario(0)" />cc </div>
       
       </td>
       
       
       </tr>
       

     <tr>

       <td>
       
       <div align="center">
       
        VFS
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='vfs_sistole' type='text' maxlength='3' size='2' onkeyup="ventriculo_izquierdo_control_formulario(0)" />cc </div>
       
       </td>
       
       
       </tr>
       


            
           <tr>
          
            <td width="50%"> <div align="center">FEVI </div></td>
               <td> 
                     <input id="spinner" name="value"  size='2' />%  <input id='fevi' type='text' maxlength='4' size='5' onkeyup="ventriculo_izquierdo_control_formulario(0)" />cc 
                     
               </td>
               
            </tr>
           
           
           <tr>
           <td colspan="2">
           <div align="center">
           Segmentaci&oacute;n Ventricular Izquierda
           </div>
           </td>
           
           </tr>
             <tr>
           <td colspan="2">
           <div align="center">
           <canvas id="segmentacion_ventricular_izquierda" width="280" height="280" onclick="click_segmentacion_ventricular_izquierda('segmentacion_ventricular_izquierda',1,event)">Tu navegador no soporta canvas.</canvas>
              
           </div>
           
           </td>
           
           </tr>
           
           <tr>
           <td colspan="2" id="asignacion_tipos_generalidades" style="display:none">
           <div align="center">
           <div id="nombre_segmentacion_vi">
           </div>
           
              <select id="tipos_generalidades"  onchange="guardar_generalidad()">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		" SELECT "+
				                    		"  id_descripcion_segmentacion_ventriculo_izquierdo,"+
				                    		"  descripcion"+
				                    		"  FROM eco_descripcion_segmentacion_ventriculo_izquierdo"
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
 
           </div>
           </td>
           </tr>
           
           
           <tr>
           <td colspan="2">
           <div align="center">
           Medidas Di&aacute;stole
           </div>
           </td>
           </tr>
            
             <tr>

       <td>
       
       <div align="center">
       
        Grosor Septum
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='grosor_septum_diastole' type='text' maxlength='3' size='2' onkeyup="ventriculo_izquierdo_control_formulario(0)" />mm </div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        Di&aacute;metro Ventr&iacute;culo Izquierdo
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='diametro_ventriculo_izquierdo_diastole' type='text' maxlength='3' size='2' onkeyup="ventriculo_izquierdo_control_formulario(0)" />mm </div>
       
       </td>
       
       
       </tr>
       
         <tr>
       <td>
       
       <div align="center">
       
        Di&aacute;metro Ventr&iacute;culo Derecho
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='diametro_ventriculo_derecho_diastole' type='text' maxlength='3' size='2' />mm </div>
       
       </td>
       
       
       </tr>
       
       <tr>

       <td>
       
       <div align="center">
       
        Grosor Pared Posterior
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='grosor_pared_posterior_diastole' type='text' maxlength='3' size='2' onkeyup="ventriculo_izquierdo_control_formulario(0)"  />mm </div>
       
       </td>
       
       
       </tr>
       
                  <tr>
           <td colspan="2">
           <div align="center">
           Medidas S&iacute;stole
           </div>
           </td>
           </tr>
            
             <tr>

       <td>
       
       <div align="center">
       
        Grosor Septum
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='grosor_septum_sistole' type='text' maxlength='3' size='2'  />mm </div>
       
       </td>
       
       
       </tr>
       
        <tr>
       <td>
       
       <div align="center">
       
        Di&aacute;metro Ventr&iacute;culo Izquierdo
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='diametro_ventriculo_izquierdo_sistole' type='text' maxlength='3' size='2'  />mm </div>
       
       </td>
       
       
       </tr>
        <tr>
       <td>
       
       <div align="center">
       
        Di&aacute;metro Ventr&iacute;culo Derecho
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='diametro_ventriculo_derecho_sistole' type='text' maxlength='3' size='2'  />mm </div>
       
       </td>
       
       
       </tr>
       <tr>

       <td>
       
       <div align="center">
       
        Grosor Pared Posterior
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='grosor_pared_posterior_sistole' type='text' maxlength='3' size='2'  />mm </div>
       
       </td>
       
       
       </tr>
                 <tr>
           <td colspan="2">
           <div align="center">
           C&aacute;lculo Hipertrofia
           </div>
           </td>
           </tr>
           
           
              <tr>
       <td>
       
       <div align="center">
       
        lvmass
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='lvmass' type='text' maxlength='4' size='5' onkeyup="ventriculo_izquierdo_control_formulario(1)" />gr/m<sub>2</sub></div>
       
       </td>
       
       
       </tr>
        <tr>
        
        
       <td>
       
       <div align="center">
       
        grosor relativo
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='grosorrelativo' type='text' maxlength='4' size='2'  onkeyup="ventriculo_izquierdo_control_formulario(1)"/>cms </div>
       
       </td>
       
       
       </tr>
          
           
       
              <tr>
          
               <td width="38%"> <div align="center">Hipertrofia</div></td>
               <td> 
               <div align="center">
           
                       <select id="hipertrofia_ventriculo_izquierdo">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		" id_hipertrofia_ventriculo_izquierdo,"+
				                    		" descripcion "+
				                    		"FROM eco_hipertrofia_ventriculo_izquierdo"

);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
             
               
               </div>
                      
                </td>
               
            </tr>
       
        
                
        
          </table>
  </div>
  
  
  
     
      
        
          </div>
          
          <div id="funcion_diastolica">
  <div align="center">
  <table border="1" width="38%">
          <tr>
          
            <td width="38%"> <div align="center">E´ Septal</div></td>
               <td> 
               <div align="center">
               
               <input id='eprima_septal_funciondiastolica' type='text' maxlength='2' size='2' onkeyup=" funcion_diastolica_control(0)"/>cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
            
                      <tr>
          
            <td width="38%"> <div align="center">E´ Lateral </div></td>
               <td> 
               <div align="center">
               
               <input id='eprima_lateral_funciondiastolica' type='text' maxlength='2' size='2' onkeyup="funcion_diastolica_control(0)"/>cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
            
            
              <tr>
          
            <td width="38%"> <div align="center">vol aur&iacute;cula izquierda </div></td>
               <td> 
               <div align="center">
               
               <input id='volauricula_funciondiastolica' type='text' maxlength='2' size='2' onkeyup="funcion_diastolica_control(0)"/>cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
            <tr>
            <td colspan="2" id="gradofunciondiastolica"  style="display: none">

               <table border="1" width="100%">
                 
           
                  <tr>
          
            <td width="60%"> <div align="center">E</div></td>
               <td> 
               <div align="center">
               
               <input id='e_funciondiastolica' type='text' maxlength='1' size='2' onkeyup=" funcion_diastolica_control(1)"/>cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
            <tr>
          
            <td width="60%"> <div align="center">A</div></td>
               <td> 
               <div align="center">
               
               <input id='a_funciondiastolica' type='text' maxlength='1' size='2' onkeyup=" funcion_diastolica_control(1)"/>cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
            
             <tr>
          
            <td width="60%"> <div align="center">Tiempo de Desaceleraci&oacute;n</div></td>
               <td> 
               <div align="center">
               
               <input id='dt_funciondiastolica' type='text' maxlength='3' size='2' onkeyup=" funcion_diastolica_control(1)"/>cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
                
            </table>
          
          </td>
          </tr>

            
          
            </table>
  
  
  
  
  
  </div>
  
          </div>
  
  </div>
  </td>
  </tr>
  
  
  </table>
  </div>
  
  
  
          
          </div> <!--fin div formulario Ventriculo Izquierdo  -->
  
  <div id="formulario_ventriculoderecho">
  <div align="center">
    <table border="1" width="38%">
          <tr>
          
            <td width="38%"> <div align="center">Di&aacute;metro basal </div></td>
               <td> 
               <div align="center">
               
               <input id='diametro_basal_ventriculo_derecho' type='text' maxlength='3' size='2'onkeyup="ventriculo_derecho_control_formulario(2)" />mm
               
               </div>
                      
                </td>
               
            </tr>
             <tr>
          
            <td width="38%"> <div align="center">Di&aacute;metro medio </div></td>
               <td> 
               <div align="center">
               
               <input id='diametro_medio_ventriculo_derecho' type='text' maxlength='3' size='2' onkeyup="ventriculo_derecho_control_formulario(3)"/>mm
               
               </div>
                      
                </td>
               
            </tr>
              <tr>
          
            <td width="38%"> <div align="center">Longitud base a apex </div></td>
               <td> 
               <div align="center">
               
               <input id='longitud_base_apex_ventriculo_derecho' type='text' maxlength='3' size='2' onkeyup="ventriculo_derecho_control_formulario(4)" />mm
               
               </div>
                      
                </td>
               
            </tr>
            
               <tr>
          
               <td width="38%"> <div align="center">Di&aacute;metro Arteria pulmonar </div></td>
               <td> 
               <div align="center">
               
               <input id='diametro_arteria_pulmonal_ventriculo_derecho' type='text' maxlength='3' size='2' onkeyup="ventriculo_derecho_control_formulario(5)"/>mm
               
               </div>
                      
                </td>
               
            </tr>
            
                
            
               <tr>
          
               <td width="38%"> <div align="center">Di&aacute;metro V&aacute;lvula pulmonar </div></td>
               <td> 
               <div align="center">
               
               <input id='diametro_valvula_pulmonal_ventriculo_derecho' type='text' maxlength='3' size='2' onkeyup="ventriculo_derecho_control_formulario(6)"/>mm
               
               </div>
                      
                </td>
               
            </tr>
            
             <tr>
          
               <td width="38%"> <div align="center">Di&aacute;metro tracto de salida ventr&iacute;culo derecho </div></td>
               <td> 
               <div align="center">
               
               <input id='diametro_tracto_salida_ventriculo_derecho_ventriculo_derecho' type='text' maxlength='3' size='2' onkeyup="ventriculo_derecho_control_formulario(7)"/>mm
               
               </div>
                      
                </td>
               
            </tr>
            
                  <tr>
          
               <td width="38%"> <div align="center">TAPSE (Funci&oacute;n Sist&oacute;lica)</div></td>
               <td> 
               <div align="center">
               
               <input id='tapse_ventriculo_derecho' type='text' maxlength='2' size='2' onkeyup="ventriculo_derecho_control_formulario(1)"  />mm
               
               </div>
                      
                </td>
               
            </tr>
            
      
            
            
                 <tr>
                <td colspan="2" align="center"> 
                 Observaciones<br>
                 <textarea id="observaciones_ventriculo_derecho" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                </td>
          </tr>
            
            </table>
  
  </div>
  
  </div> <!--fin div formulario Ventriculo Derecho  -->
  
  
    <div id="formulario_auriculaizquierda">
  <div align="center">
  <table border="1" width="38%">
          <tr>
          
            <td width="38%"> <div align="center">&Aacute;rea en 4C </div></td>
               <td> 
               <div align="center">
               
               <input id='area_auricula_izquierda_4C' type='text' maxlength='2' size='2' onkeyup="auricula_izquierda_control(0)" />cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
            
                <tr>
          
            <td width="38%"> <div align="center">&Aacute;rea en 2C </div></td>
               <td> 
               <div align="center">
               
               <input id='area_auricula_izquierda_2C' type='text' maxlength='2' size='2' onkeyup="auricula_izquierda_control(0)" />cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
                <tr>
          
            <td width="38%"> <div align="center">Longitud en 4C </div></td>
               <td> 
               <div align="center">
               
               <input id='longitud_auricula_izquierda_4C' type='text' maxlength='2' size='2' onkeyup="auricula_izquierda_control(0)" />m<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
            
                <tr>
          
            <td width="38%"> <div align="center">Longitud en 2C </div></td>
               <td> 
               <div align="center">
               
               <input id='longitud_auricula_izquierda_2C' type='text' maxlength='2' size='2' onkeyup="auricula_izquierda_control(0)" />m<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
             <tr>
          
            <td width="38%"> <div align="center">Volumen Indexado </div></td>
               <td> 
               <div align="center">
               
               <input id='volumen_auricula_izquierda' type='text' maxlength='2' size='2' onkeyup="auricula_izquierda_control(1)"/>ml/m<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
             <tr>
          
            <td width="38%"> <div align="center">Di&aacute;metro Anteroposterior (DAP) </div></td>
               <td> 
               <div align="center">
               
               <input id='dap_auricula_izquierda' type='text' maxlength='3' size='2' onkeyup="auricula_izquierda_control(0)"/>mm
               
               </div>
                      
                </td>
               
            </tr>
            
               <tr>
                <td colspan="2" align="center"> 
                 Observaciones<br>
                 <textarea id="observaciones_auricula_izquierda" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                </td>
          </tr>
            </table>
  
  </div>
  
  </div> <!--fin div formulario auricula izquierda  -->
  
  
    <div id="formulario_auriculaderecha">
  <div align="center">
   <table border="1" width="38%">
          <tr>
          
            <td width="38%"> <div align="center">&Aacute;rea </div></td>
               <td> 
               <div align="center">
               
               <input id='area_auricula_derecha' type='text' maxlength='2' size='2' onkeyup="auricula_derecha_control(0)"/>cms<sup>2</sup>
               
               </div>
                      
                </td>
               
            </tr>
            
               <tr>
                <td colspan="2" align="center"> 
                 Observaciones<br>
                 <textarea id="observaciones_auricula_derecha" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                </td>
          </tr>
            </table>
  
  </div>
  
  </div> <!--fin div formulario auricula derecha  -->
  
  
  <div id="formulario_venacava">
  <div align="center">
 <table border="1" width="38%">
          <tr>
          
            <td width="38%"> <div align="center">Di&aacute;metro de la vena cava inferior </div></td>
               <td> 
               <div align="center">
               
               <select id="diametro_venacava" onchange="control_vena_cava(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"  id_diametro_vena_cava_inferior, "+
				                    		"  descripcion "+
				                    		" FROM eco_diametro_vena_cava_inferior "



);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
               </div>
                      
                </td>
               
            </tr>
            
            <tr>
          
            <td width="38%"> <div align="center">Colapso Respiratorio </div></td>
               <td> 
               <div align="center">
               
               <select id="colpso_venacava" onchange="control_vena_cava(0)">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"  id_colapso_respiratorio, "+
				                       		"  descripcion "+
				                    		" FROM eco_colapso_respiratorio "


);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
               </div>
                      
                </td>
               </tr>

<tr>
       <td >
       
       <div align="center">
       
        Velocidad de regurgitaci&oacute;n
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='velregurgitacion_venacava' type='text' maxlength='5' size='4' onkeyup="calcular_psap_venacava()" />m/s </div>
       
       </td>
       
       
       </tr>

<tr>
       <td >
       
       <div align="center">
       
        PSAP
       </div>
       
       </td>
       <td>
       
       <div align='center'><input id='psap_venacava' type='text' maxlength='5' size='4' disabled='disabled' /> </div>
       
       </td>
       
       
       </tr>

               <tr>
                <td colspan="2" align="center"> 
                 Observaciones<br>
                 <textarea id="observaciones_venacava" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                </td>
          </tr>
            </table>
  
  </div>
  
  </div> <!--fin div formulario venacava  -->
 
    <div id="formulario_pericardio">
  <div align="center">
 <table border="1" width="38%">
          <tr>
          
            <td width="38%"> <div align="center">Grosor </div></td>
               <td> 
               <div align="center">
               
               <select id="grosor_pericardio" onchange="pericardio_generar_informe()">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    		"SELECT "+
				                    		"  id_tipo_grosor_pericardio,"+
				                    		"  tipo "+
				                    		"FROM eco_tipo_grosor_pericardio"
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
               </div>
                      
                </td>
               
            </tr>
            
            <tr>
          
            <td width="38%"> <div align="center">Apariencia </div></td>
               <td> 
               <div align="center">
               
               <select id="apariencia_pericardio" onchange="pericardio_generar_informe()">
				                 <%
				                    rs = null;
				                    st = null;
				                    try {
				                    con = new Conexion();
				                    st = con.conn.createStatement();
				                    rs = st.executeQuery(
				                    	"SELECT "+
				                    	" id_tipo_apariencia_pericardio,"+
				                    	" apariencia"+
				                    	" FROM eco_tipo_apariencia_pericardio"
);
				                 %>
				
		                    	 <%  while (rs.next()) {%>
				                      <option value="<%=rs.getString(1)%>"><%=rs.getString(2)%></option>
				                 <%}
				                  rs.getStatement().close();
				                  con.cerrar();
     			                 } catch (SQLException e) {
			                     	System.out.println(e);
				                   out.println("no se pudo realizar la conexion!<br/>");
	    		                 }
				                 %>
			            </select>
               </div>
                      
                </td>
               
            </tr>
              <tr>
                       <td>
                       <div align="center">
                       Efusi&oacute;n
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="efusion_pericardio" value="1" onclick="control_pericardio(0)"/>
               NO <input type="radio" name="efusion_pericardio" value="0" onclick="control_pericardio(0)" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
            
          
                 <tr id="analisis_loculada_pericardio" style="display: none">
                       <td >
              <div align="center">
                       Loculada
                       </div>
                       </td>
                       <td>
                               <div align="center">
               SI <input type="radio" name="loculada_pericardio" value="1" onclick="control_pericardio(1)"/>
               NO <input type="radio" name="loculada_pericardio" value="0" onclick="control_pericardio(1)" checked="checked"/>
                                    </div>
                       </td>
                       
                       </tr>
                       
                            <tr id="analisis_loculada_opcionsi_pericardio" style="display: none">
                       <td colspan="2" >
                       <div align="center">
						<table border="1" width="100%">
						<tr>
						<td width="38%">
						Localizaci&oacute;n
						</td>
						<td>
						  <input id='localizacion_loculado_pericardio' type='text'  onkeyup="pericardio_generar_informe()"/>
						</td>
						</tr>
						
						<tr>
						<td width="38%">
						Grosor
						</td>
						<td>
						  <input id='grosor_loculado_pericardio' type='text' onkeyup="pericardio_generar_informe()"/>
						</td>
						</tr>
						</table>
                       </div>
                       </td>
                       
                       </tr>
                       
                       <tr id="analisis_loculada_opcionno_pericardio" style="display: none">
						<td>
						Medici&oacute;n
						</td>
						<td>
						  <input id='medicion_pericardio' maxlength="3" size="2" type='text'  onkeyup="control_pericardio(2)"/> cms
						</td>
						</tr>
              
               <tr>
                <td colspan="2" align="center"> 
                 Observaciones<br>
                 <textarea id="observaciones_pericardio" cols="50" rows="5" onkeyup="vistapreviaFormularios()"></textarea>
                </td>
          </tr>
            </table>
  
  </div>
  
  </div> <!--fin div pericardio  -->
  
  
  <div id="formulario_vistaprevia">
  <div align="center">
   <table border="1" width="50%">

            
               <tr>
                <td colspan="2" align="center"> 
                 Vista Previa Informe Eco<br>
                 <textarea id="vista_previa" cols="100" rows="10" readonly="readonly"></textarea>
                </td>
               </tr>
          
              <tr>
                <td colspan="2" align="center"> 
                 Conclusiones<br>
                 <textarea id="conclusiones" cols="50" rows="5" ></textarea>
                </td>
               </tr>
          
          
          
             <tr>
             
             <tr>
                <td colspan="2" align="center"> 
                 Recomendaciones<br>
                 <textarea id="recomendaciones" cols="50" rows="5" ></textarea>
                </td>
               </tr>
          
          
          
             <tr>
                <td align="center"> 
                <input type="button" id="envio" value="Enviar Datos" onclick="enviar_data()"/>
                </td>
                <td align="center"> 
                   <input type="button" id="anular" value="Anular Informe" onclick=""/>
                </td>
             </tr>
            </table>
  
  </div>
  
  </div> <!--fin div vista previa  -->
  </div> <!--fin div formulario General  -->
     
     
     
     </td>
     </tr>
     
     </table>
     
     <%}%>
     </body>
     </html>