var peticionRela2 = null;
var elementoSeleccionadoRela2 = -1;
var SugeDiagnosticoRela2 = null;
var cacheSugeDiagnosticoRela2 = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function autocompletarCIE10DiagRela2() {

	    var elEvento = arguments[0] || window.event;
	
	                    var texto = document.getElementById("txtNomDiagnosRela2").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                        borraListaRela2();
	                        return;
	                    }
	                    if(cacheSugeDiagnosticoRela2[texto] == null) {
	                        peticionRela2 = inicializa_xhr();
	                        peticionRela2.onreadystatechange = function() {
	                                    if(peticionRela2.readyState == 4) {
	                                        if(peticionRela2.status == 200) {
	                                        	SugeDiagnosticoRela2 = eval('('+peticionRela2.responseText+')');
	                                                if(SugeDiagnosticoRela2.length == 0) {
	                                                    sinResultadosRela2();
	                                                }else {
	                                                    cacheSugeDiagnosticoRela2[texto] = SugeDiagnosticoRela2;
	                                                    actualizaSugeDiagnosticoRela2();
	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="26";  
	                               // peticionRela2.open("POST","ControladorMedico?txtAccion="+txtAccion+"&codigo="+texto,true);
	                                peticionRela2.open("POST","ControladorMedico?valor=26&codigo="+texto,true);
	                                peticionRela2.send("");
	                    } else {
	                    	SugeDiagnosticoRela2 = cacheSugeDiagnosticoRela2[texto];
	                        actualizaSugeDiagnosticoRela2();
	                    }
	                }
	
function sinResultadosRela2() {
	document.getElementById("SugeDiagnosticoRela2").innerHTML = "No existe registro";
	document.getElementById("SugeDiagnosticoRela2").style.display = "block";
}

function actualizaSugeDiagnosticoRela2() {
	elementoSeleccionadoRela2 = -1;
	muestraSugeDiagnosticoRela2();
}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugeDiagnosticoRela2() {
	var zonaSugeDiagnosticoRela2 = document.getElementById("SugeDiagnosticoRela2");
	zonaSugeDiagnosticoRela2.innerHTML = SugeDiagnosticoRela2.formateaListaRela2();
	zonaSugeDiagnosticoRela2.style.display = 'block';     
}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaListaRela2 = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionadoRela2) {
	                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
	            }else {
	                codigoHtml += '<li><a  href="#" onclick="confirmarRela2(this.innerHTML)">'+this[i]+'</a><\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       divRela2 = document.getElementById('SugeDiagnosticoRela2');
		divRela2.style.display='none';
	    return codigoHtml;	     
	};
function borraListaRela2() {
	document.getElementById("SugeDiagnosticoRela2").innerHTML = "";
	document.getElementById("SugeDiagnosticoRela2").style.display = "none";
}

function confirmarRela2(cf){		
		 var codigo;
		 var nombre;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	nombre=z[0];
		    codigo=z[1];
		    }
	       document.getElementById("txtNomDiagnosRela2").value = nombre;
	       document.getElementById("txtCodDiagnosticoRela2").value = codigo;
	        borraListaRela2();
	}	