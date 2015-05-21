
<%@  page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!--  Los import -->
<%@  page session="true"%>

<%@  page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>

<!--Para poder convertir la direccion-->
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;language=es"></script>
<!------------------------------------------------------------------------------------------------------------ by: Greys Hernandez  -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

<title>Datos Demograficos</title>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language=javascript src="1.js"></script>
<script language=javascript src="Validaciones.js"></script>
<script languaje="javascript" src="Adm_Ping.js"></script>
<script language="javascript"></script>

<style type="text/css">
#buscar { background:url(Imagenes/Buscar.jpg) no-repeat; border:4; width:76px; height:30px; }
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {width:500px; Cursor : pointer; border:1px solid black; display:none; margin-left: 0px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}
.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
.style5 {color: #FF0000}
</style>
<script src="Scripts/AC_RunActiveContent.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 


<!-- by: Greys Hernandez -->

<script type="text/javascript">
// CONVIERTE LA DIRECCION EN LONGITUD Y LATITUD PARA PODER REALIZAR EL PROCESO DE GEOREFERENCIACION
var geocoder;
var map;

 function initialize() {
  geocoder = new google.maps.Geocoder();
  var latlng = new google.maps.LatLng(11.001723, -74.813172);
  var mapOptions = {
    zoom: 16,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  }
  map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

}
  // convierte la direccion en longitud y latitud
 function codeAddress(){
 	optCiudad=document.getElementById("cbmun").selectedIndex;
	ciudad=document.getElementById("cbmun").options[optCiudad].text;
	    
	optDepartamento=document.getElementById("cbdep").selectedIndex;   
	departamento=document.getElementById("cbdep").options[optDepartamento].text;

	optPais=document.getElementById("cbnacionalidad").selectedIndex; 
	pais=document.getElementById("cbnacionalidad").options[optPais].text;
	
	direccion=document.getElementById("txtdiregeorre").value;	
	
    var address=direccion+" "+ciudad+"-"+departamento+" "+pais;

    nombre=document.getElementById("txtnombre").value;
    Papellido=document.getElementById("txtpapellido").value;
    nombrecompleto=nombre+" "+Papellido;
 geocoder.geocode( { 'address': address}, function(results, status) {

     if (status == google.maps.GeocoderStatus.OK) {
		                
		    map.setCenter(results[0].geometry.location);  
		   	   		    
		    var marker = new google.maps.Marker({
	            map: map,
	            position: new google.maps.LatLng(results[0].geometry.location.lat(),results[0].geometry.location.lng()),
	            title: nombrecompleto
	           //icon: 'http://gmaps-samples.googlecode.com/svn/trunk/markers/green/blank.png'
            });    
		    updatePosition(results[0].geometry.location);    
         		     
 	 } else {
 	 	 
 	   	if (status==google.maps.GeocoderStatus.ZERO_RESULTS){
 	   		   alert("Geocode no tuvo éxito por las siguientes razones: la codificación geográfica se ha realizado correctamente pero no ha devuelto ningún resultado. Esto puede ocurrir si en la codificación geográfica se incluye una dirección (address) inexistente");
  	     }else{
  	    	 if (status==google.maps.GeocoderStatus.OVER_QUERY_LIMIT){
                   alert("Geocode no tuvo éxito por las siguientes razones: se ha excedido el cupo de solicitudes");
             }else{
                   
              	 if(status==google.maps.GeocoderStatus.REQUEST_DENIED){
                       alert("Geocode no tuvo éxito por las siguientes razones: se ha denegado la solicitud por algún motivo");
                 }else{
                  	 if(status==google.maps.GeocoderStatus.INVALID_REQUEST){
                           alert("Geocode no tuvo éxito por las siguientes razones: no se ha especificado la solicitud (address)");
                     }
                 }

            }
        }
  	     //  alert("Geocode no tuvo éxito por las siguientes razones: " + status);
    }
    
  });

}
 //actualiza mis cajas ocultas con la longitud y la latitud
 function updatePosition(latLng){
	document.getElementById("txtlongitud").value=latLng.lng();    
    document.getElementById("txtlatitud").value=latLng.lat();    
 }
 function mapa(){
	 initialize();
	 codeAddress();
 }

</script>


<script type="text/javascript">
//domina el div de la direccion by Greys Hernandez 
function aparece(){
    div = document.getElementById("efecto");
	    if (div.style.display=="none"){
			   div.style.display=""; 
	    } 
 }
	 
 function desaparece(){
	div = document.getElementById("efecto");
		if (div.style.display!="none"){
			   div.style.display="none";
			   limpiar();
		} 
	 
 }


 
 function ComboMunicipio(){
	var valorM=document.getElementById("cbmun").selectedIndex;
	var valorD=document.getElementById("cbdep").selectedIndex;
	if((valorM!=0)&&(valorD!=0)){
	   aparece();
	}else{
       desaparece();
	}
 }

function limpiar(){
	 document.getElementById("txtDirnum1").value="";
	 document.getElementById("txtDirnum2").value="";
	 document.getElementById("txtDirnum3").value="";
	 document.getElementById("txtdire").value="";
	 document.getElementById("ComboDir1").selectedIndex=0;
	 document.getElementById("ComboDir2").selectedIndex=0;
	 document.getElementById("ComboDir3").selectedIndex=0;
	 document.getElementById("ComboDir4").selectedIndex=0;
	 initialize();
	 
}


 
</script>
<!-------------------------------------------------------------------------- -->


</head>
<body onLoad="ajaxNacionalidad(),focus(form2.txtnumdocu),initialize();" id="back">
<script> 
/*if (history.forward(1)){ 
location.replace(history.forward(-1)) 

} */

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
				con.cerrar();
	       	%>

<%

if(codigou==null){
	codigou="";
}

if(codigou.equals("")){%>
<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
window.location.href="Seg_login.jsp";
</script>
<%}else{%>
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-Ingresar Demografico </b></div>
</td>
</tr>

<tr id="principal1">
<td>
<form id="form2" name="form2" onkeypress = "return pulsar(event);" style="margin-top:-16px;">

<div>

	<table width="100%">        

	<tr>
      <td width="20%" id="lateral4" class="style6">
      <br/>
      <br/>
	   
        <div id="cabecera2" style="margin-top:-16px;"  class="style11" align="center">GEOREFERENCIACION</div>
        <br/>
        <br/>

<!-- Muestra mapita -->

 <center><hr width="90%" size="2" class="style6"/></center>
 <div id="map_canvas" style="width: 320px; height: 250px;"></div>


<!-- Muestra la direccion -->

<label>
<input name="txtdire" type="text" id="txtdire"  style="border:0px" readonly maxlength="100" class="simpletext">
</label>
 <center><hr width="90%" size="2" class="style6"/></center>
 &nbsp;&nbsp;&nbsp;&nbsp;<input type="image" src="Imagenes\MapaPuntos.jpg" width="50" height="50" onclick="Link()">
          
     </td>
  <td width="100%">         
   	  <br/>  
	   <table width="100%" class="style6">
  <tr>
    <td colspan="4" id="cabecera2"><div align="center" class="style11"> DATOS DEMOGRAFICOS </div></td>
  </tr>

  <tr>
    <td width="128">&nbsp;</td>
    <td width="186">&nbsp;</td>
    <td width="186">&nbsp;</td>
    <td width="245">&nbsp;</td>
  </tr>

    <tr>
    <td><span class="Estilo8"> Tipo Documento</span></td>
    <td><label>
      <select name="tipodoc" size="1" id="tipodoc">   
  
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

      </select>
      <span class="style8">*</span></label></td>
    <td><span class="Estilo8">Nº Identificacion</span></td>
    <td><label>
      <input name="txtnumdocu" type="text" id="txtnumdocu" onKeyUp="validarte(form2.txtnumdocu);" onChange="datos();"  maxlength="12"/>
      <span class="style8">*</span></label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Tipo Afiliacion</span></td>
    <td><label>
      <select name="select2" id="select2" onChange="ajaxEps1(form2)">
        <option selected="selected">SELECCIONE...</option>
        <option>Cotizante</option>
        <option>Particular</option>
        <option>Beneficiario</option>
		<option>Desplazado</option>
      </select>
      <span class="style8">*</span><br />
    </label></td>
    <td><span class="Estilo8">Nivel Cotizante</span></td>
    <td><label>
      <select name="cbnivel" id="cbnivel">
        <option selected="selected">SELECCIONE...</option>
        <option>I</option>
        <option>II</option>
        <option>III</option>
      </select>
    </label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Primer Apellido</span></td>
    <td><label>
      <input name="txtpapellido" type="text" id="txtpapellido" onKeyUp="this.value=this.value.toUpperCase()" onKeyPress="ajaxEps()" maxlength="20" />
      <span class="style8">*</span></label></td>
    <td><span class="Estilo8">Segundo Apellido</span></td>
    <td><label>
      <input name="txtsapellido" type="text" id="txtsapellido" onKeyUp="this.value=this.value.toUpperCase()" onChange="datos();"  maxlength="20"/>
    </label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Nombres</span></td>
    <td><label>
      <input name="txtnombre" type="text" id="txtnombre" onKeyUp="this.value=this.value.toUpperCase()" />
      <span class="style8">*</span></label></td>
    <td><span class="Estilo8">Fecha Nacimiento(ddmmaaaa)</span></td>
    <td><label>
      <input type="text" name="txtfechanaci" id="txtfechanaci"   onKeyUp="masca(this,'/',patron,true)" maxlength="10"/>
      <span class="style8">*</span></label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Nacionalidad</span></td>
    <td><label>
      <select name="cbnacionalidad" size="1" id="cbnacionalidad" onChange="ajaxDep(form2)"  >
        <option selected="selected">SELECCIONE...</option>
      </select>
      <span class="style8">*</span></label></td>
    <td><span class="Estilo8">Departamento</span></td>
    <td><label>
      <select name="cbdep" size="1" id="cbdep" onChange="ajaxxMun(form2),ComboMunicipio()">
        <option selected="selected">SELECCIONE...</option>
      </select>
      <span class="style8">*</span></label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Municipio</span></td>
    <td><label>
      <select name="cbmun" size="1" id="cbmun" onChange="CodigoMun(form2),ComboMunicipio()">
        <option selected="selected">SELECCIONE...</option>
      </select>
      <span class="style5">*</span></label></td>

     <td><span class="Estilo8">Religion</span></td>
    <td><label>
      <input name="txtreligion" type="text" id="txtreligion" onKeyUp="this.value=this.value.toUpperCase();" onChange="datos();"  maxlength="20"/>
    </label></td>
  </tr>
<tr id="efecto" style="display:none">

<td><span class="Estilo8">Direccion</span></td>
<td colspan="2">
 <table>
     <tr class="style6"><td>EJ: CARRERA</td><td><center>73</center></td><td><center>B</center></td><td><center>2</center></td><td><center>#</center></td><td><center>85</center></td><td><center>A</center></td><td><center>1</center></td><td><center>80</center></td></tr>
     <tr><td>
        
        <label>
              <select name="ComboDir1" id="ComboDir1" onChange="Concatenar();">
			  <option selected="selected">--</option>
			 <% 
			 ResultSet rst=null;
			 Statement st0 = null;  
			 try{
			 	Conexion con2=new Conexion();
			 	st0 = con2.conn.createStatement();
			 	rst=st0.executeQuery("SELECT descripcion FROM adm_combo1_direccion");%>
			 	<%while(rst.next()){%>
				<option value="<%=rst.getString(1)%>"><%=rst.getString(1)%></option>
			 	<%}
			 	rst.getStatement().close();
			 	con2.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
			      </select>
			</label></td>
            <td>
			<input name="txtDirnum1" type="text" id="txtDirnum1"  maxlength="3" size="2" onKeyUp="Concatenar();" /> </td>
			 <td><label>
			  <select name="ComboDir2" id="ComboDir2" onChange="Concatenar();">
			  <option selected="selected">--</option>
			 <% 
			 ResultSet r=null;
			 Statement sta= null;  
			 try{
			 	Conexion con2=new Conexion();
			 	sta= con2.conn.createStatement();
			 	r=sta.executeQuery("SELECT descripcion FROM adm_combo2_direccion");%>
			 	<%while(r.next()){%>
				<option value="<%=r.getString(1)%>"><%=r.getString(1)%></option>
			 	<%}
			 	r.getStatement().close();
			 	con2.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
			</select>
			</label> </td>
             <td>
			<input name="txtDirnum2" type="text" id="txtDirnum2"  maxlength="3" size="2" onKeyUp="Concatenar();" /> </td>
			 <td><label>
			  <select name="ComboDir3" id="ComboDir3" onChange="Concatenar();">
			  <option selected="selected">--</option>
			 <% 
			 ResultSet r1=null;
			 Statement s1= null;  
			 try{
			 	Conexion con2=new Conexion();
			 	s1= con2.conn.createStatement();
			 	r1=s1.executeQuery("SELECT Descripcion FROM adm_combo3_direccion");%>
			 	<%while(r1.next()){%>
				<option value="<%=r1.getString(1)%>"><%=r1.getString(1)%></option>
			 	<%}
			 	r1.getStatement().close();
			 	con2.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
			      </select>
			</label></td>
             <td>
             <label>
			  <input name="txtDirnum3" type="text" id="txtDirnum3"  maxlength="3" size="2"  onKeyUp="Concatenar(),mapa()" />
			      </label> </td>
	         <td><label>
			  <select name="ComboDir4" id="ComboDir4" onChange="Concatenar();">
			  <option selected="selected">--</option>
			 <% 
			 ResultSet r2=null;
			 Statement s2= null;  
			 try{
			 	Conexion con2=new Conexion();
			 	s2= con2.conn.createStatement();
			 	r2=s2.executeQuery("SELECT descripcion FROM adm_combo2_direccion");%>
			 	<%while(r2.next()){%>
				<option value="<%=r2.getString(1)%>"><%=r2.getString(1)%></option>
			 	<%}
			 	r2.getStatement().close();
			 	con2.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
			      </select>
			</label> </td>
              <td>
             <label>
			  <input name="txtDirnum4" type="text" id="txtDirnum4"  maxlength="3" size="2"  onKeyUp="Concatenar()" />
			      </label> </td>
		
			 <td>
			<input name="txtDirnum5" type="text" id="txtDirnum5"  maxlength="3" size="2"   onkeyUp="Concatenar(),mapa()">
             </td>


      </tr></table>
</td>
</tr>
  <tr>
    <td><span class="Estilo8">Telefono Fijo</span></td>
    <td><label>
      <input name="txtelefijo" type="text" id="txtelefijo" onKeyUp="validarte(form2.txtelefijo);" onChange="datos();"  maxlength="12"/>
    </label></td>
    <td><span class="Estilo8">Barrio</span></td>
    <td><label>
      <input name="txtbarrio" type="text" id="txtbarrio" onKeyUp="this.value=this.value.toUpperCase();" maxlength="20"/>
    </label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Email</span></td>
    <td><input name="txtemail" type="text" id="txtemail" onChange="checkmail(form2.txtemail);" maxlength="30"/></td>
    <td><span class="Estilo8">Genero</span></td>
    <td><label>
      <select name="select4" id="select4" >
        <option selected="selected">SELECCIONE...</option>
        <option>Masculino</option>
        <option>Femenino</option>
      </select>
      <span class="style8">*</span></label></td>
  </tr>
  <tr>
    <td width="128"><span class="Estilo8">Telefono Oficina</span></td>
    <td width="186"><label>
      <input name="txteleoficina" type="text" id="txteleoficina" onKeyUp="validarte(form2.txteleoficina);" onChange="datos();"  maxlength="12"/>
</label></td>
   <td><span class="Estilo8">Telefono Celular</span></td>
    <td><input name="txtcelular" type="text" id="txtcelular" onKeyUp="validarte(form2.txtcelular);" onChange="datos();"  maxlength="12"/></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Estrato</span></td>
    <td><label>
      <select name="select7" id="select7">
        <option selected="selected">SELECCIONE...</option>
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        <option>6</option>
      </select>
      <span class="style8">*</span></label></td>
   <td width="186"><span class="Estilo8">Estado Civil</span></td>
    <td width="245"><label></label>
        <label>
        <select name="select6" id="select6">
          <option selected="selected">SELECCIONE...</option>
          <option>Soltero(a)</option>
          <option>Casado(a)</option>
          <option>Viudo(a)</option>
        </select>
        <span class="style8">*</span></label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Raza</span></td>
    <td><label>
      <input name="txtraza" type="text" id="txtraza" onKeyUp="this.value=this.value.toUpperCase(),caracter(form2.txtraza);" onChange="datos();"  maxlength="20"/>
    </label></td>
    <td><span class="Estilo8">Zona Residencial</span></td>
    <td><label>
      <select name="cbzona" id="cbzona">
        <option selected="selected">SELECCIONE...</option>
        <option>Rural</option>
        <option>Urbana</option>
      </select>
      <span class="style8">*</span></label></td>
  </tr>
    <tr>
    <td>Ocupacion</td>
    <td><input name="txtocupacion" onKeyUp="this.value=this.value.toUpperCase();" type="text" id="txtocupacion"></td>
     <td><span class="Estilo8">Religion</span></td>
    <td><label>
      <input name="txtreligion" type="text" id="txtreligion" onKeyUp="this.value=this.value.toUpperCase();" onChange="datos();"  maxlength="20"/>
    </label></td>
  </tr>
  <tr>
    <td><span class="Estilo8">Nombre Entidad </span></td>
    <td colspan="3"><label></label>
        <label>
        <input name="cbeps" type="text" id="cbeps" size="80" maxlength="300" onKeyUp="autocompleta_nombre()">
        <span class="style8">*</span></label></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td colspan="3"><div id='sugerencias'></div></td>
  </tr>
  
  <tr>
    <td colspan="2" class="style8">Campos Requeridos* </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><label>
      <input name="txtcodentidad" type="hidden" id="txtcodentidad" />
      <input name="txtcodmun" type="hidden" id="txtcodmun" />
   <!--<input name="txtocupacion" type="text" id="txtocupacion" value="0"  style="visibility:hidden" /> -->
    </label></td>
    <td><label>
      <input type="button" name="btingresar" class="boton4" id="btingresar" value="     Ingresar    " onClick="demograficoSolo(form2);" />
    </label></td>
  </tr>
  <tr>
	<td><input name="txtlongitud" type="hidden" id="txtlongitud" /></td>
    <td><input name="txtlatitud" type="hidden" id="txtlatitud" /></td>
    <td><input name="txtdiregeorre" type="hidden" id="txtdiregeorre" /></td>
  </tr>
</table>
</form></td></tr></table>
</div>
</td></tr></table>
<%}%>

</body>
</html>