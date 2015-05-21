
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<LINK REL="SHORTCUT ICON" HREF="Imagenes/IconO.ico"> 
<script language="javascript" type="text/javascript" src="clickderecho.js"></script>
<title>MODIFICACION DE PERMISOS</title>

<style type="text/css">
#sugerencias li:hover{Cursor : pointer;background-color: #cccccc; }
#sugerencias {Cursor : pointer; color:#215b8b; width:200px; border:1px solid black; display:none; margin-left: 400px;}
#sugerencias ul {list-style: none; margin: 0; padding: 0; font-size:.95em;}
#sugerencias ul li {padding: .2em; border-bottom: 1px solid silver;}

.seleccionado {font-weight:bold; background-color: #cccccc;j=1}
</style>

<script language=javascript src="ajaxseguridad.js"></script>
<script type="text/javascript">
 
function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}


var peticion = null;
var elementoSeleccionado = -1;
var sugerencias = null;
var cacheSugerencias = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompleta() {

		div = document.getElementById('yosi');
		r = document.getElementById('Eliminar');
		c= document.getElementById('Insertar');
		div.style.display='none';
		r.style.display='none';
		c.style.display='none';
	    var elEvento = arguments[0] || window.event;	   
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
		       
	               if(elementoSeleccionado+1 < sugerencias.length) {
	                    elementoSeleccionado++;
	                }
	            muestraSugerencias();
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestraSugerencias();
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                   
	                    seleccionaElemento();
	                }else {
	                    var texto = document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraLista();
	                        return;
	                    }
	                    if(cacheSugerencias[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                sugerencias = eval('('+peticion.responseText+')');
	                                                if(sugerencias.length == 0) {
	                                                    sinResultados();
	                                                }else {
	                                                    cacheSugerencias[texto] = sugerencias;
	                                                    actualizaSugerencias();
	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="26";  
	                                peticion.open("POST","ControlBuscarUsu?txtAccion="+txtAccion+"&codigo="+texto+"",true);                                 
	     	                       peticion.send("");
	                    } else {
	                        sugerencias = cacheSugerencias[texto];
	                        actualizaSugerencias();
	                    }
	                }
	}
	function sinResultados() {
	    document.getElementById("sugerencias").innerHTML = "No existe usuario";
	    document.getElementById("sugerencias").style.display = "block";
	}
		function actualizaSugerencias() {
		elementoSeleccionado = -1;
		muestraSugerencias();
		}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
	function muestraSugerencias() {
	    var zonaSugerencias = document.getElementById("sugerencias");
	        zonaSugerencias.innerHTML = sugerencias.formateaLista();
	        zonaSugerencias.style.display = 'block';
	}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaLista = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="clicksugerencia(this.innerHTML);">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
	    return codigoHtml;
	};
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias*/
	function seleccionaElemento() {
	    if(sugerencias[elementoSeleccionado]) {
		    var kitty=sugerencias[elementoSeleccionado];
		   var resul;
	     	var y=kitty.split("|").length;
	     	var z=kitty.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    }
	    	
	       document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value = resul;
	        borraLista();	      
	    }
	}
	function borraLista() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
}


	function checkAll() {
		 var i;
	    var nodoCheck = document.getElementsByTagName("input");
	    var varCheck = document.getElementById("all").checked;
	    for (i=0; i<nodoCheck.length; i++){
	        if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
	            nodoCheck[i].checked = varCheck;
	        }
	    }
	}

	var co=0;
	var codigoapli= new Array();
	var usu=new Array();
	var codigo="";
	var codigousu="";	
	function eliminar() {
		co=0;
		var i;
		var c;
		c=form1.yo.value;
		var cc=c;			
			if(c!=1){
				for(i=0;i<c;i++){			
			     if (form1.ca[i].checked) {
			          codigo=form1.fe[i].value;
			          codigousu=form1.fu[i].value; 
			          codigoapli[co]=codigo; 
			          usu[co]=codigousu;			          
			          co=co+1; 
				   }else{
				   }
			     }
			 josi();  
			}else{
				codigo=form1.fe.value;
				codigousu=form1.fu.value;
				window.location.href="EliminarPermisos?codigo="+codigo+"&codusu="+codigousu; 
				co=1;
			}
	      	 
    }
	 function josi(){
		for(j=0; j<co; j++){			 
	   	   window.location.href="EliminarPermisos?codigo="+codigoapli[j]+"&codusu="+usu[j];
	   	 
		 }
	 }	
var i;
var j=0;
function clicksugerencia(choice){	
                var resul;
		     	var y=choice.split("|").length;
		     	var z=choice.split("|");		     	
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			    resul=z[0];
			    }
	 document.getElementById("txtCodigoRefernciaPresupuestoBuscar").value = resul; 
	 borraLista();
	 }
	 
</script>
</head>

<body >
<table width="100%">
<tr>
<td>
<div id="cabecera1" align="center" class="linkmenu"><b><a href="menu.jsp">Menu Principal</a>-<a href="seguridad.jsp">Seguridad</a>-Modificar Permisos</b></div>
</td>
</tr>

<tr id="principal1">
<td>
<center><div >
			<table width="100%" >  
				<tr>
		 
		
					<td width="100%">
								
									<form name="form1" id="form1" onkeypress = "return pulsar(event);">
													<table width="100%" class="style6">  
															  <tr id="cabecera2"  class="style11">
																<td colspan="4"><div align="center">MODIFICACION DE PERMISOS </div></td>
																</tr>
									 							<tr colspan="3" width="100%" align="center" > 
																<td >Identificacion <input type="text" size="30" name="txtCodigoRefernciaPresupuestoBuscar" id="txtCodigoRefernciaPresupuestoBuscar" onkeyup="autocompleta();" /> <input type="button"  class="boton4" name="buscar"  value="Buscar"  onclick="mostrar_tabla(form1);"   /></td>
   															  </tr>
													</table>

													<div id="sugerencias" class="scrollbox" align="center" ></div>
													<div id="yosi" class="scrollbox1" ></div>
 
													<table width="100%" >

														<tr align="center"> 
																     <td>
																      <input name="Eliminar" type="button" class="boton4" value="Eliminar Permisos"  style="display:none" onclick="eliminar();"/>
																	  <input type="button" name="Insertar" class="boton4" value="Insertar Permisos" style="display:none" onclick="ingresar();" />
																	  <input type="button" name="Cambiar"  class="boton4" value="Autorizar Formatos" style="display:none" onclick="AutorizaFormatos();" />	
																    </td>
																    
													    </tr> 
													    <tr align="center">  
													    	<td><input type="button" class="boton4" name="salir" value="-Finalizar-" onclick="salir_p();" /></td>
													  	</tr>  
													</table>
									 </form>
						</td>
				</tr>
		</table>
</div>
</center>
</td>
</tr>
</table>
</body>
</html>