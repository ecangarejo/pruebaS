var peticionRela1 = null;
var elementoSeleccionadoRela1 = -1;
var SugeDiagnosticoRela1 = null;
var cacheSugeDiagnosticoRela1 = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
function inicializa_xhr() {
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}
	
	function autocompletarCIE10DiagRela1() {

	    var elEvento = arguments[0] || window.event;
	
	                    var texto = document.getElementById("txtNomDiagnosRela1").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                        borraListaRela1();
	                        return;
	                    }
	                    if(cacheSugeDiagnosticoRela1[texto] == null) {
	                        peticionRela1 = inicializa_xhr();
	                        peticionRela1.onreadystatechange = function() {
	                                    if(peticionRela1.readyState == 4) {
	                                        if(peticionRela1.status == 200) {
	                                        	SugeDiagnosticoRela1 = eval('('+peticionRela1.responseText+')');
	                                                if(SugeDiagnosticoRela1.length == 0) {
	                                                    sinResultadosRela1();
	                                                }else {
	                                                    cacheSugeDiagnosticoRela1[texto] = SugeDiagnosticoRela1;
	                                                    actualizaSugeDiagnosticoRela1();
	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="26";  
	                                //peticionRela1.open("GET","ControladorMedico?txtAccion="+txtAccion+"&codigo="+texto,true);                                 
	                                peticionRela1.open("POST","ControladorMedico?valor=26&codigo="+texto,true);
	                                peticionRela1.send("");
	                    } else {
	                    	SugeDiagnosticoRela1 = cacheSugeDiagnosticoRela1[texto];
	                        actualizaSugeDiagnosticoRela1();
	                    }
	                }
	
function sinResultadosRela1() {
	document.getElementById("SugeDiagnosticoRela1").innerHTML = "No existe registro";
	document.getElementById("SugeDiagnosticoRela1").style.display = "block";
}

function actualizaSugeDiagnosticoRela1() {
	elementoSeleccionadoRela1 = -1;
	muestraSugeDiagnosticoRela1();
}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugeDiagnosticoRela1() {
	var zonaSugeDiagnosticoRela1 = document.getElementById("SugeDiagnosticoRela1");
	zonaSugeDiagnosticoRela1.innerHTML = SugeDiagnosticoRela1.formateaListaRela1();
	zonaSugeDiagnosticoRela1.style.display = 'block';     
}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaListaRela1 = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionadoRela1) {
	                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
	            }else {
	                codigoHtml += '<li><a  href="#" onclick="confirmarRela1(this.innerHTML)">'+this[i]+'</a><\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       divRela1 = document.getElementById('SugeDiagnosticoRela1');
		divRela1.style.display='none';
	    return codigoHtml;	     
	};
function borraListaRela1() {
	document.getElementById("SugeDiagnosticoRela1").innerHTML = "";
	document.getElementById("SugeDiagnosticoRela1").style.display = "none";
}

function confirmarRela1(cf){		
		 var codigo;
		 var nombre;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	nombre=z[0];
		    codigo=z[1];
		    }
	       document.getElementById("txtNomDiagnosRela1").value = nombre;
	       document.getElementById("txtCodDiagnosticoRela1").value = codigo;
	        borraListaRela1();
	}	