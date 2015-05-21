
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" import="adm_logica.Conexion" errorPage="" %>
<!-- Los import -->
<!-- Los import -->
<%@ page session="true"%>
<%@ page language="java" %>
<%@ page import = "java.sql.Connection"%>
<%@ page import = "java.sql.DriverManager"%>
<%@ page import = "java.sql.ResultSet"%> 
<%@ page import = "java.sql.Statement"%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>

<!--Para poder convertir la direccion-->
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?sensor=false&amp;language=es"></script>
<!------------------------------------------------------------------------------------------------------------ by: Greys Hernandez  -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<script language=javascript src="1.js"></script>
<script language=javascript src="Validaciones.js"></script>

<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<title>Ingreso De Paciente</title>
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico">
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" /> 
<script type="text/javascript">
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

//convierte la direccion en longitud y latitud by: Greys Hernandez
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
     geocoder.geocode( { 'address': address}, function(results, status) {

     if (status == google.maps.GeocoderStatus.OK) {
		                
		  //  map.setCenter(results[0].geometry.location);  
		   	   		    
		    var marker = new google.maps.Marker({
	            map: map,
	            position: new google.maps.LatLng(results[0].geometry.location.lat(),results[0].geometry.location.lng()),
	            title: nombre
	           //icon: 'http://gmaps-samples.googlecode.com/svn/trunk/markers/green/blank.png'
            });    
		    updatePosition(results[0].geometry.location);  
		    map.setCenter(marker.getPosition());
         		     
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
 /*function aparece(){
     div = document.getElementById("efecto");
     input= document.getElementById("btnActualizar");
     divs= document.getElementById("prueba");
        if(div.style.display=="none"){
 			   div.style.display=""; 
 			   divs.style.display="none";
 			  input.style.display="none";
 	    } 
  }*/
  
 function aparece(){
     div = document.getElementById("efecto");
     input= document.getElementById("btnActualizar");
    // divs= document.getElementById("prueba");
        if(div.style.display=="none"){
 			   div.style.display=""; 
 			  // divs.style.display="";
 			  input.style.display="none";
 	    } 
  }
 function aparecerboton(){
    input= document.getElementById("btnActualizar");
    div = document.getElementById("efecto");
        if((input.style.display=="none")&&(div.style.display=="none")){
        	input.style.display=""; 
 	    } 
  }


/* function desaparece(){
	div = document.getElementById("efecto");
		if (div.style.display!="none"){
			   div.style.display="none";
			  
	    }  
 }*/
 
 // ubicar punto
 function punto(){
	 longitud=document.getElementById("txtlongitud").value;
	 latitud=document.getElementById("txtlatitud").value;
	 nombre=document.getElementById("txtnombre").value;
    
	 var marker = new google.maps.Marker({
         map: map,
         position: new google.maps.LatLng(latitud,longitud),
         title: nombre
        //icon: 'http://gmaps-samples.googlecode.com/svn/trunk/markers/green/blank.png'
     });    
     map.setCenter(marker.getPosition());
}
 </script>
<script language="javascript">

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


function Abrir_ventana (pagina) {
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=no, resizable=yes, width=600, height=300, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}

function validarcomboton(h,e){

		 var CodColaFin=h.txtCodColaFin.value;
	       var tipo,cedula,codurg;
	       tipo=h.cbafiliacion.value;
			codurg=h.txturgencia.value;
			
	        cedula=h.txtnumdoc.value;
	        if(cedula==""||tipo==""){
		        alert("Digite cedula...!");
	        }else{	        
	        	//alert();	 
	        	window.location.href="ControlSeleccion?tipo="+tipo+"&ced="+cedula+"&id="+codurg+"&CodColaFin="+CodColaFin,true;
	        }
}

function validarcom(h,tecla, e)
	{	 
	   
		opc = false;
		var s=h.txtnumdoc.value;
		var n=0;
		var pos=0;
		for(i=0;i<s.length;i++){
		   valor = parseInt(s.charAt(i));
			if(isNaN(valor)){
				n++;
			}
		}	
		tecla =   e.keyCode;  e.which;
	if(tecla == 13)//Codigo Ascii de la tecla ENTER evento si se presiona enter
      {
	       var CodColaFin=h.txtCodColaFin.value;
	       var tipo,cedula,codurg;
	       tipo=h.cbafiliacion.value;
			codurg=h.txturgencia.value;
			
	        cedula=h.txtnumdoc.value;
	        if(cedula==""||tipo==""){
		        alert("Digite cedula...!");
	        }else{	        
	        	//alert();	 
	        	window.location.href="ControlSeleccion?tipo="+tipo+"&ced="+cedula+"&id="+codurg+"&CodColaFin="+CodColaFin,true;
	        }
			
			
		
		}

	}

function nuevoIngreso(){
	window.location.href='Adm_Ingreso.jsp'
}

function Abrir_Cama (pagina) {
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones =opciones+"scrollbars=no, resizable=yes, width=450, height=250, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}
//mensaje
function goAway(h) { 
	var codcama=h.textfield13.value;
	if(codcama==""){ 
		Abrir_Cama('Preingreso.jsp');
		}else{
if (confirm('Cama previamente Reservada para este Paciente... desea Cambiarla?')) 
//return true; 
	//window.location.href="Abrir_ventana('Preingreso.jsp','Ayuda','width=500,height=300')",true;
	Abrir_Cama('Preingreso.jsp');

else { 
 
return false;}}}


function validarsema(h,tecla, e)
{ 
    
	opc = false;
	var s=h.txtsemana.value;
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
		window.alert('El campo  solo acepta valores Numericos');
		h.txtsemana.value="";
		h.txtsemana.focus();
		return false;
	}
	

}
function focustxt(){
	//alert()
	//document.get
	//document.getElemetById("txtnumdoc").focus();
	document.getElementById("txtnumdoc").focus();
}
	
</script>
<%String x=(String)session.getAttribute("cama"); 
%>



</head>
<body onload="ajaxNacionalidad1(),focustxt(),fecha_ingreso(),hora(),initialize(),punto();" id="back"> 
<script> 
/*if (history.forward(1)){ 
//location.replace(history.forward(-1)) 

}*/ 

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
String Noeps=request.getParameter("Noeps");
if(Noeps==null){
	Noeps="";
}
String cod=request.getParameter("codigo");
if(codigou==null){
	codigou="";
}
if(cod==null){
	cod="";
}
if(Noeps.equals("nc")){%>
<script language="javascript">alert("La Entidad Del Usuario No Tiene Convenio Activo.\n Favor Comunicarse al Departamento de Facturacion.");
</script>
<%}
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
	<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="menuadm.jsp">Admisiones</a>-Admision-Admision</b></div>
</td>
</tr>

<tr id="principal1">
<td>
<form id="form1"  style="margin-top:-41px;" name="form1"  onkeypress = "return pulsar(event);">
<div >

	<table width="100%" >           
		<tr>
			<td width="100%" id="lateral4" class="style6">
              <br/>
       <br/>
       <br/>
       <br/>
   					<div id="cabecera2" style="margin-top:-16px;" class="style11" align="center">MODULO ADMISIONES</div>
					<br/>     
								<%@include file="CopyAdm.jsp"%>
                 
		         <!-- Muestra mapita -->
			<center><hr width="90%" size="2" class="style6"/></center>
			<div id="map_canvas" style="width: 320px; height: 250px;"></div>
			<center><hr width="90%" size="2" class="style6"/></center>
			</td>
  
			<td width="100%" >         
					  <br/> 
<%
String nomusu=request.getParameter("nomUsu");
String codigocama=request.getParameter("codcama");
String boton="";
if(codigocama==null||codigocama.equals("")){
	codigocama="";
	boton="1";
	
}else{
	boton="0";
}

//String y=request.getParameter("y");
String codna=request.getParameter("cod");
String z=request.getParameter("z");
 if(z==null){
	 z="";
 } 
 String s=request.getParameter("s");

 
 
 if(codna==null){
	 codna="";
 } 

String eps=request.getParameter("eps");
String cedula=request.getParameter("cedula");
String id=request.getParameter("id");
String tip=request.getParameter("tipo");
String nom=request.getParameter("nom");
String CodColaFin=request.getParameter("CodColaFin");
String direccion=request.getParameter("dire");
//if(direccion==null){direccion="";}
String municipio=request.getParameter("muni");
String departamento=request.getParameter("dep");
String pais=request.getParameter("pais");
String longitud=request.getParameter("long");
String latitud=request.getParameter("lati");
String barrio=request.getParameter("bar");
String codigomuni=request.getParameter("codmuni");
//String direccion=(String)request.getParameter("dire");
//System.setProperty("java.nio.charset" , "UTF-8");
//String direccion = new String(request.getParameter("dire").getBytes("ISO-8859-1"), "UTF-8");
//String req=request.getParameter("dire");
//String direccion=java.net.URLEncoder.encode(req);
//String direccion=java.net.URLDecoder.decode(request.getParameter("dire"), "utf-8");




if(nom==null){
	nom="";
	
}

//System.out.print("nom en jsp "+nom);

int i;
 
 for(i=0;i<nom.length();i++){
	 nom=nom.replace('9','ñ');
	 nom=nom.replace('8','Ñ');
   //alert(direccion);
 }

if(CodColaFin==null){
	CodColaFin="";
}
if(eps==null){
	
	eps="";
}
if(id==null){
	
	id="0";
}
//System.out.println("urgencia en jsp"+id);

if(tip==null){
	tip="";
}
if(cedula==null){
	cedula="";
}

%>
<%
if(s==null){
	 s="0";
}else{%>
	 <script>alert("Falta Admitir a la Madre...");</script>
<%}
String men=request.getParameter("men");
if(men==null){
	men="";
}else{%> 
	<script>alert("El Paciente Ya Tiene Una Admision...");</script>
<%} %>
	 
<table width="100%" class="style6" align="center"> 
  <tr>
    <td colspan="6" id="cabecera2" class="style11"><div align="center">  
      <p ><strong>SOLICITUD DE INGRESO</strong></p>
    </div></td>
 </tr>

  <tr>
    <td width="141" align="right">Tipo de Documento</td>
    <td width="104"> 
      <label>
        <select name="cbafiliacion" size="1" id="cbafiliacion" class="style3">
 <% 
 ResultSet rs3=null;
 Statement st3 = null;  
 try{
 	Conexion con2=new Conexion();
 	st3 = con2.conn.createStatement();
 	rs3=st3.executeQuery("SELECT sigla,descripcion FROM adm_tipodocumento");%>
 	<%while(rs3.next()){%>
	<option value="<%=rs3.getString(1)%>"><%=rs3.getString(1)+":"%><%=rs3.getString(2) %></option>
 	<%}
 	rs3.getStatement().close();
 	con2.cerrar();
}catch(SQLException e){
	 
	 System.out.println(e);
out.println("no se pudo realizar la conexion!<br/>"); 
}%>
	<%if(z.equals("1")){%>
		<OPTION value="<%=tip%>" selected="selected"><%=tip%></OPTION>
	            
    <%}%>
        </select>
      </label>    </td>
    <td width="154" align="right">Numero de Documento</td>
    <td width="155"><label>
      <input type="text" name="txtnumdoc" id="txtnumdoc" onkeyup="validarcom(form1,this,event);" value="<%=cedula%>" />
    </label></td>

 <input  name="txturgencia" id="txturgencia" value="<%=id%>" style="visibility:hidden" />
	<td width="25"></td>
    <td width="49" ><input type="button"  onclick="validarcomboton(form1,event);" value="Buscar" /> <br/>
    </td>
    
    <td width="164"><label>
   </label></td><input type="hidden" name="txtCodColaFin" id="txtCodColaFin"  value="<%=CodColaFin%>"/>
  </tr>
</table>
<%if(z.equals("1")){ %>
<table width="100%" class="style6" border="0" align="center">
  <tr>
    <td height="21" colspan="6" bgcolor="#FFFFFF"> 
        <label>Nombre Del Paciente: <b class="style9"><%=nom%></b></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>Entidad: <b class="style9"><%=eps %></b></label>&nbsp;&nbsp;&nbsp;&nbsp;
		<label><a>Direccion: </a><input name="txtdire" type="text" id="txtdire"  style="border:0px" readonly maxlength="100"  class="style9" style="Cursor: pointer" title="Click Para Actualizar Direccion" onclick="aparece()" value="<%=direccion%>"></label>
        <label><a>Barrio: </a><input type="text" name="txtbarrio" id="txtbarrio" onclick="aparecerboton()" value="<%=barrio%>" class="style9" onKeyUp="this.value=this.value.toUpperCase()"  onclick="aparecerboton()" size="13"/>
		       
		 <input type="button"  name="btnActualizar" class="boton4" id="btnActualizar" value="Actualizar" onClick="ActualizaDireccionAdmision(form1);" style='width:70px; height:25px' style="display:none"/>
		
        <div id="efecto" style="display:none">
       <table class="style6"> 
       <tr><td><span class="Estilo8">Nacionalidad</span></td> <td><span class="Estilo8">Departamento</span></td><td><span class="Estilo8">Municipio</span></td><td>EJ: CARRERA</td><td><center>73</center></td><td><center>B</center></td><td><center>2</center></td><td><center>#</center></td><td><center>85</center></td><td><center>A</center></td><td><center>1</center></td><td><center>80</center></td></tr>
       <tr>
      <td><select name="cbnacionalidad" size="1" id="cbnacionalidad" onchange="ajaxDepCopia(form1)">
      <option selected="selected"><%=pais%></option>
      </select></td>
 <td><select name="cbdep" size="1" id="cbdep" onchange="ajaxxMunCopia(form1)" style="width:120px" >
      <option selected="selected"><%=departamento%></option>
     <% 
        String codpais=request.getParameter("CodPais");
       
			 ResultSet rr1=null;
			 Statement ss= null;  
			 try{
			 	Conexion con6=new Conexion();
			 	ss= con6.conn.createStatement();
			 	rr1=ss.executeQuery("SELECT nombre FROM adm_departamento WHERE pais_codigo_fk='"+codpais+"' ORDER BY nombre ");%>
			 	<%while(rr1.next()){%>
				<option title="<%=rr1.getString(1)%>" value="<%=rr1.getString(1)%>"><%=rr1.getString(1)%></option>
			 	<%}
			 	rr1.getStatement().close();
			 	con6.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
    </select>
     </td>
      <td><select name="cbmun" size="1" id="cbmun" onchange="CodigoMunCopia(form1)" style="width:150px">
      <option selected="selected"><%=municipio%></option>
       <% 
               String codepart=request.getParameter("CodDep");
       
			 ResultSet rr3=null;
			 Statement ss3= null;  
			 try{
			 	Conexion con6=new Conexion();
			 	ss3= con6.conn.createStatement();
			 	rr3=ss3.executeQuery("SELECT nombre FROM adm_municipio WHERE dept_codigo_fk='"+codepart+"' ORDER BY nombre ");%>
			 	<%while(rr3.next()){%>
               	<option title="<%=rr3.getString(1)%>" value="<%=rr3.getString(1)%>"><%=rr3.getString(1)%></option>
			 	<%}
			 	rr3.getStatement().close();
			 	con6.cerrar();
			}catch(SQLException e){
				 
				 System.out.println(e);
			out.println("no se pudo realizar la conexion!<br/>"); 
			}%>
    </select></td>
         
          <td>
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
            	
		     </tr>
		      <tr>
		             
               <td>
              <label>
			   <input type="button"  name="btnActualizar" class="boton4" id="btnActualizar" value="Actualizar" onClick="ActualizaDireccionAdmision(form1);" />
				</label> 
		       </td> 
		       </tr>
       </table>	
          </div>
       
        </td>
  </tr>
  <tr>
    <td  colspan="4" id="cabecera2" ><p align="center" class="style11">INGRESO</p></td>
  </tr>
<tr><td>&nbsp;</td></tr>  
  <tr>
    <td width="161">Medio de Autorizacion</td>
    <td width="203"><label>
      <input type="text" name="txtmedio" id="txtmedio" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtmedio);" maxlength="20"  />
      <span class="style8">*</span></label>
<b style="display:none" id="me" class="style8">(x)</b>
</td>
    <td width="160">Numero de Autorizacion</td>
    <td width="233"><label>
      <input type="text" name="txtnumauto" id="txtnumauto" maxlength="20" onKeyUp="this.value=this.value.toUpperCase();" />
    </label></td>
  </tr>
  <tr>
    <td>Autorizado Por:</td>
    <td><label>
      <input type="text" name="txtautorizado" id="txtautorizado" onKeyUp="this.value=this.value.toUpperCase(),caracter(form1.txtautorizado);" maxlength="20" />
    </label>
      <span class="style8">*</span>
<b style="display:none" id="au" class="style8">(x)</b>
</td>
    <td>Remitido De:</td>
    <td><label>
      <input type="text" name="txtremitido" id="txtremitido" onkeyup="this.value=this.value.toUpperCase(),caracter(form1.txtremitido);" maxlength="20" />
    </label></td>
  </tr>
  <tr>
    <td>Numero de la Cama:</td>
    <td><label>
      <input type="text" size="14" name="textfield13" value="<%=codigocama %>" id="textfield13" readonly=""/>
      <input type="button" name="button" id="button" class="boton4" value=". . ." onclick="goAway(form1)"/>
      <span class="style8">*</span></label>
  <b style="display:none" id="nc" class="style8">(x)</b>
</td>
    <td>Medico Tratante</td>
  <td><label>
   <select id='cmbMedicoTratante' >


		    <% 
		 ResultSet rs6=null;
		 Statement st6 = null;  
		 try{
		 	Conexion con6=new Conexion();
		 	st6 = con6.conn.createStatement();
		 	rs6=st6.executeQuery("SELECT '74',nombre,'','' FROM empresa UNION SELECT su.usu_codigo,sdt.nombre,sdt.apellido,sdt.ocupacion FROM seg_usuario su, seg_datos_personales sdt WHERE su.dat_codigo_fk=sdt.dat_codigo AND sdt.profesion='Especialista' ");%>
		 	<%while(rs6.next()){
		 	String NombreMedico=rs6.getString(2)+" "+rs6.getString(3)+"-"+rs6.getString(4);
		 	%>
			<option value="<%=rs6.getString(1)%>"><%=NombreMedico%></option>
		 	<%}
		 	rs6.getStatement().close();
		 	st6.close();
		 	con6.cerrar();
		}catch(SQLException e){
			 
			 System.out.println(e);
		out.println("no se pudo realizar la conexion!<br/>"); 
		}%>
  </select>
  </label></td>
  </tr>
</table>
<table width="100%" class="style6" height="196" align="center">
  <tr>
    <td colspan="4" id="cabecera2" class="style11"><div align="center">DATOS DE AFILIACION</div></td>
    </tr>
  <tr>
    <td width="127" height="45">Estado de la Afiliacion</td>
    <td width="207"><label>
      <select name="cbestadoa" id="cbestadoa">
	  <option selected="selected">SELECCIONE...</option>
	  <option>ACTIVO</option>
      <option>INACTIVO</option> 
      </select>
      <span class="style8">*</span></label>
<b style="display:none" id="ea" class="style8">(x)</b></td>
    <td width="410"><div id="layer">
      <table width="412" border="0" class="style6">
        <tr>
          <td colspan="4" id="cabecera2" ><div align="center" class="style11"><em>Documentos Anexos</em></div></td>
        </tr>
        <tr>
          <td width="21"><input type="checkbox" name="checkbox" id="checkbox" /></td>
          <td width="200">Fotocopia Documento Identidad</td>
          <td width="29"><label>
            <input type="checkbox" name="checkbox2" id="checkbox2" />
          </label></td>
          <td width="144">Fotocopia del Carné</td>
        </tr>
      </table>
    </div></td>
    <td width="1"><label></label></td>
  </tr>
  <tr>
    <td>Semanas Cotizadas</td>
    <td><label>
      <input type="text" name="txtsemana" id="txtsemana" onkeyup="validarsema(form1,this,event);"/>
      <span class="style8">*</span></label>
<b style="display:none" id="sc" class="style8">(x)</b></td>
    <td colspan="2" rowspan="3" align="left"><div id="layer2">
      <table width="412" border="0">
        <tr>
          <td width="412" id="cabecera2"><div align="center" class="style11">Observaciones</div></td>
        </tr>
        <tr>
          <td align="center"><label>
            <textarea name="txtobservacion" id="txtobservacion" cols="47" rows="4" onKeyUp="this.value=this.value.toUpperCase();" ></textarea>
          </label></td>
        </tr>
      </table>
    </div></td>
    </tr>
  <tr>
    <td>Contacto </td>
    <td><label>
	  <input type="hidden" size="14" name="txtCodUsuario" id="txtCodUsuario" value="<%=codigou %>"; />
      <input type="hidden" size="14" name="txtCodAcomp" id="txtCodAcomp" readonly="" />
      <input type="text" size="14" name="textfield14" id="textfield14" readonly="" />
      <input type="button" name="buttonn" class="boton4" value=". . ." onclick="Abrir_ventana('Adm_Contacto.jsp?va=1','Ayuda','width=3000,height=800')" />
      <span class="style8">*</span></label>
<b style="display:none" id="co" class="style8">(x)</b></td>
    </tr>
<tr><td>&nbsp;</td></tr>  
  <tr>
    <td height="36" colspan="2"><label class="style8"> Campos Requeridos * </label></td>
    </tr>
  <tr>

    <td>&nbsp;</td>
    <td><input type="button"  name="btingresar"  id="btingresar" value="Ingresar"  onclick="Admisiones(form1);" /></td>
    <td><input type="button"  name="btnNuevo" id="btnNuevo" value="Nuevo Ingreso" onclick="nuevoIngreso()" /></td>
    </tr>
    <tr>
     <td>
    
        <input name="txtdiregeorre" type="text" id="txtdiregeorre" style="visibility:hidden" >
     </td>
     <td>
	    <input name="txtlongitud" type="text" id="txtlongitud" style="visibility:hidden"  value="<%=longitud%>"/>
	    <input name="txtlatitud" type="text" id="txtlatitud"  style="visibility:hidden"  value="<%=latitud%>"/>
    </td>
    <td><input name="txtcodna" type="text" id="txtcodna" style="visibility:hidden" value="<%=codna%>" size="10"/>
	    <input name="txtnombre" type="text" id="txtnombre" style="visibility:hidden"   value="<%=nom%>"/>
	    <input name="txtentidad" type="text" id="txtentidad" style="visibility:hidden"   value="<%=eps%>"/>
   </td>
    <input name="txtcodcam" type="text" id="txtcodcam" style="visibility:hidden"   value="<%=codigocama %>" size="10" >
    <input type="hidden" name="txtregistro" id="txtregistro" onkeyup="this.value=this.value.toUpperCase(),caracter(form1.txtregistro);" maxlength="20" value="<%=idu %>" readonly="" size="10"/>
    <input type="text" name="txthora" id="txthora" style="visibility:hidden"  readonly="" />
    <input type="text" name="txtfecha" id="txtfecha" style="visibility:hidden"  readonly="" />
	<input type="text" name="txtUsuario" id="txtUsuario"  style="visibility:hidden"  value="<%=codigou%>"/>
    <input type="text" name="txtcodmun" id="txtcodmun" value="<%=codigomuni%>" style="visibility:hidden"/>   
</tr>
  
</table>

<%
}
%>
</form>
</td></tr></table>
</div>
</td></tr></table> 
<%}%>
</body>
</html>