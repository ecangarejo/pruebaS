var peticionCuentas = null;
var elementoSeleccionadoCuenta = -1;
var SugerenciaCuenta = null;
var cacheSugerenciaCuenta = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function autocompletarCuentas() {

	    var elEvento = arguments[0] || window.event;
	
	                    var texto = document.getElementById("txtCuenta").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                        borraListaCuenta();
	                        return;
	                    }
	                    if(cacheSugerenciaCuenta[texto] == null) {
	                        peticionCuentas = inicializa_xhr();
	                        peticionCuentas.onreadystatechange = function() {
	                                    if(peticionCuentas.readyState == 4) {
	                                        if(peticionCuentas.status == 200) {
	                                        	SugerenciaCuenta = eval('('+peticionCuentas.responseText+')');
	                                                if(SugerenciaCuenta.length == 0) {
	                                                    sinResultadosCuenta();
	                                                }else {
	                                                    cacheSugerenciaCuenta[texto] = SugerenciaCuenta;
	                                                    actualizaSugerenciaCuenta();
	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="26";  
	                                peticionCuentas.open("GET","ControlCuentas?txtAccion="+txtAccion+"&Cad="+texto,true);                                 
	                                peticionCuentas.send("");
	                    } else {
	                    	SugerenciaCuenta = cacheSugerenciaCuenta[texto];
	                    	actualizaSugerenciaCuenta();
	                    }
	                }
	
function sinResultadosCuenta() {
	document.getElementById("Cuentas").innerHTML = "No existe registro";
	document.getElementById("Cuentas").style.display = "block";
}

function actualizaSugerenciaCuenta() {
	elementoSeleccionadoCuenta = -1;
	muestraSugerenciaCuenta();
}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugerenciaCuenta() {
	var zonaSugerenciaCuenta = document.getElementById("Cuentas");
	zonaSugerenciaCuenta.innerHTML = SugerenciaCuenta.formateaListaCuenta();
	zonaSugerenciaCuenta.style.display = 'block';     
}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaListaCuenta = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionadoCuenta) {
	                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
	            }else {
	                codigoHtml += '<li><a  href="#" onclick="confirmarCuenta(this.innerHTML)">'+this[i]+'</a><\/li>';
	            }
	            //if(i==1){
	            //alert("cantidad "+i);
	            //}
	        }
	    codigoHtml += "<\/ul>";
       divCuenta = document.getElementById('Cuentas');
       divCuenta.style.display='none';
	    return codigoHtml;	     
	};
function borraListaCuenta() {
	document.getElementById("Cuentas").innerHTML = "";
	document.getElementById("Cuentas").style.display = "none";
	var NomCu=document.getElementById("txtCuenta").value;
	if(NomCu==""){
		document.getElementById("txtCodigoCuenta").value ="";
	}
}

function confirmarCuenta(cf){
		 var codigo;
		 var nombre;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	nombre=z[0];
		    codigo=z[1];
		    }
	       document.getElementById("txtCuenta").value = nombre+" "+codigo;
	       document.getElementById("txtCodigoCuenta").value = codigo;
	        borraListaCuenta();
	}	