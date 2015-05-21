var peticionEgreso = null;
var elementoSeleccionadoEgreso = -1;
var SugeDiagnosticoEgreso = null;
var cacheSugeDiagnosticoEgreso = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function autocompletarCIE10Egreso() {

	    var elEvento = arguments[0] || window.event;
	
	                    var texto = document.getElementById("txtNomDiagnosRelaEgreso").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                        borraListaEgreso();
	                        return;
	                    }
	                    if(cacheSugeDiagnosticoEgreso[texto] == null) {
	                        peticionEgreso = inicializa_xhr();
	                        peticionEgreso.onreadystatechange = function() {
	                                    if(peticionEgreso.readyState == 4) {
	                                        if(peticionEgreso.status == 200) {
	                                        	SugeDiagnosticoEgreso = eval('('+peticionEgreso.responseText+')');
	                                                if(SugeDiagnosticoEgreso.length == 0) {
	                                                    sinResultadosEgreso();
	                                                }else {
	                                                    cacheSugeDiagnosticoEgreso[texto] = SugeDiagnosticoEgreso;
	                                                    actualizaSugeDiagnosticoEgreso();
	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="26";  
	                               // peticionEgreso.open("POST","ControladorMedico?txtAccion="+txtAccion+"&codigo="+texto,true);
	                                peticionEgreso.open("POST","ControladorMedico?valor=26&codigo="+texto,true);
	                                peticionEgreso.send("");
	                    } else {
	                    	SugeDiagnosticoEgreso = cacheSugeDiagnosticoEgreso[texto];
	                        actualizaSugeDiagnosticoEgreso();
	                    }
	                }
	
function sinResultadosEgreso() {
	document.getElementById("SugeDiagnosticoEgreso").innerHTML = "No existe registro";
	document.getElementById("SugeDiagnosticoEgreso").style.display = "block";
}

function actualizaSugeDiagnosticoEgreso() {
	elementoSeleccionadoEgreso = -1;
	muestraSugeDiagnosticoEgreso();
}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugeDiagnosticoEgreso() {
	var zonaSugeDiagnosticoEgreso = document.getElementById("SugeDiagnosticoEgreso");
	zonaSugeDiagnosticoEgreso.innerHTML = SugeDiagnosticoEgreso.formateaListaEgreso();
	zonaSugeDiagnosticoEgreso.style.display = 'block';     
}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaListaEgreso = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionadoEgreso) {
	                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
	            }else {
	                codigoHtml += '<li><a  href="#" onclick="confirmarEgreso(this.innerHTML)">'+this[i]+'</a><\/li>';
	            }
	            //if(i==1){
	            //alert("cantidad "+i);
	            //}
	        }
	    codigoHtml += "<\/ul>";
       divEgreso = document.getElementById('SugeDiagnosticoEgreso');
	   divEgreso.style.display='none';
	    return codigoHtml;	     
	};
function borraListaEgreso() {
	document.getElementById("SugeDiagnosticoEgreso").innerHTML = "";
	document.getElementById("SugeDiagnosticoEgreso").style.display = "none";
}

function confirmarEgreso(cf){
		 var codigo;
		 var nombre;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	nombre=z[0];
		    codigo=z[1];
		    }
	       document.getElementById("txtNomDiagnosRelaEgreso").value = nombre;
	       document.getElementById("txtCodDiagnosticoEgreso").value = codigo;
	        borraListaEgreso();
	}	