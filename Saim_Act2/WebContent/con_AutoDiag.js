var peticion = null;
var elementoSeleccionado = -1;
var SugeDiagnostico = null;
var sugerenciasFormato = null;
var cacheSugeDiagnostico = {};
var cachesugerenciasFormato = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
 

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletadiag() {     

	    var elEvento = arguments[0] || window.event;
	
	                    var texto = document.getElementById("txtNomDiagnos").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                        borraLista();
	                        return;
	                    }
	                    //alert(texto);
	                    if(cacheSugeDiagnostico[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                        	SugeDiagnostico = eval('('+peticion.responseText+')');
	                                                if(SugeDiagnostico.length == 0) {
	                                                    sinResultados();
	                                                }else {
	                                                    cacheSugeDiagnostico[texto] = SugeDiagnostico;
	                                                    actualizaSugeDiagnostico();
	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="26";  
	                               // peticion.open("POST","ControladorMedico?txtAccion="+txtAccion+"&codigo="+texto,true);
	                                peticion.open("POST","ControladorMedico?valor=26&codigo="+texto,true);
	                                peticion.send("");
	                    } else {
	                    	SugeDiagnostico = cacheSugeDiagnostico[texto];
	                        actualizaSugeDiagnostico();
	                    }
	                }
	
function sinResultados() {
	document.getElementById("SugeDiagnostico").innerHTML = "No existe registro";
	document.getElementById("SugeDiagnostico").style.display = "block";
}

function actualizaSugeDiagnostico() {
	elementoSeleccionado = -1;
	muestraSugeDiagnostico();
}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugeDiagnostico() {
	var zonaSugeDiagnostico = document.getElementById("SugeDiagnostico");
	zonaSugeDiagnostico.innerHTML = SugeDiagnostico.formateaLista();
	zonaSugeDiagnostico.style.display = 'block';     
}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaLista = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
	            }else {
	                codigoHtml += '<li><a  href="#" onclick="confirmar(this.innerHTML)">'+this[i]+'</a><\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('SugeDiagnostico');
		div.style.display='none';
	    return codigoHtml;	     
	};
function borraLista() {
	document.getElementById("SugeDiagnostico").innerHTML = "";
	document.getElementById("SugeDiagnostico").style.display = "none";
}

function confirmar(cf){		
		 var codigo;
		 var nombre;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	nombre=z[0];
		    codigo=z[1];
		    }
	       document.getElementById("txtNomDiagnos").value = nombre;
	       document.getElementById("txtCodDiagnostico").value = codigo;
	        borraLista();
	}	