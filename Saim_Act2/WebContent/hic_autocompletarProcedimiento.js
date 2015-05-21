var peticion = null;
var elementoSeleccionado = -1;
var SugerenciaMed = null;
var SugerenciaMedFormula=null;
var SugerenciaMedCE = null;
var SugerenciaMedTr = null;
var SugerenciaMedFormulaCE=null;
var SugerenciaMedFormulaTr=null;
var SugerenciaCx = null;
var cacheSugerenciaMed = {};
var cacheSugerenciaMedFormula={};
var cacheSugerenciaMedCE = {};
var cacheSugerenciaMedFormulaCE={};
var cacheSugerenciaMedTr = {};
var cacheSugerenciaMedFormulaTr={};
var cacheSugerenciaCx = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	function autocompletarQx() {     
		
	    var elEvento = arguments[0] || window.event;	 
	                    var texto = document.getElementById("txtCirujia").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                        borraListaQx();
	                        return;
	                    }
	                    if(cacheSugerenciaCx[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                SugerenciaCx = eval('('+peticion.responseText+')');
	                                               // alert(SugerenciaCx);
	                                                if(SugerenciaCx.length == 0) {
	                                                	sinResultadosQx();
	                                                	//alert("Dentro Si "+SugerenciaCx);
	                                                }else {
	                                                    cacheSugerenciaCx[texto] = SugerenciaCx;
	                                                    actualizaSugerenciaCx();
	                                                   // alert("Dentro else "+SugerenciaCx);
	                                                }
	                                            }
	                                        }
	                                    };
	                                    
	                                var txtAccion="26";  
	                                peticion.open("GET","ControlMultiplePacientes?txtAccion="+txtAccion+"&codigo="+texto,true);                                 
	     	                       peticion.send("");
	                    } else {
	                        SugerenciaCx = cacheSugerenciaCx[texto];
	                        actualizaSugerenciaCx();
	                    }
	                }
	function sinResultadosQx() {
	    document.getElementById("SugerenciaCx").innerHTML = "No existe registro";
	    document.getElementById("SugerenciaCx").style.display = "block";
	}

	function actualizaSugerenciaCx() {
		elementoSeleccionado = -1;
		muestraSugerenciaCx();
		
		  
		}

	/*función que muestra las SugerenciaCx de codigos de referencia de presupuestos*/
	function muestraSugerenciaCx() {
	    var zonaSugerenciaCx = document.getElementById("SugerenciaCx");
	        zonaSugerenciaCx.innerHTML = SugerenciaCx.formateaListaQx();
	        zonaSugerenciaCx.style.display = 'block';  
	       
	}

	/*prototypo de la lista quesale en el div de SugerenciaCx*/
	Array.prototype.formateaListaQx = function() {
		var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
	            }else {
	                codigoHtml += '<li><a  href="#" onclick="confirmarQx(this.innerHTML)">'+this[i]+'</a><\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('SugerenciaCx');
		div.style.display='none';
	    return codigoHtml;
	     
	};

	function borraListaQx() {
        document.getElementById("SugerenciaCx").innerHTML = "";
        document.getElementById("SugerenciaCx").style.display = "none";
}

function confirmarQx(cf){
		
		//alert("ok"+cf);
		 var codigo;
		 var nombre;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    codigo=z[1];
		    nombre=z[0];
		    // form1.cbArea.value();
		    }
	    	//alert(nombre)
	     //	alert(codigo)
	       document.getElementById("txtCodigoQx").value = codigo;
	       document.getElementById("txtCirujia").value = nombre;
	       borraListaQx();

	        //document.getElementById("txtFormato").focus();	


	}
	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletarMedicamento () {    
	    var elEvento = arguments[0] || window.event;	
	                    var texto = document.getElementById("txtMedicamento").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                        borraListaMed();
	                        return;
	                    }
	                    if(cacheSugerenciaMed[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                        	SugerenciaMed = eval('('+peticion.responseText+')');
	                                                if(SugerenciaMed.length == 0) {
	                                                    sinResultadosMed();
	                                                }else {
	                                                    cacheSugerenciaMed[texto] = SugerenciaMed;
	                                                    actualizaSugerenciaMed();
	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="27";  
	                                peticion.open("GET","ControlMultiplePacientes?txtAccion="+txtAccion+"&codigo="+texto,true);                                 
	                                peticion.send("");
	                    } else {
	                    	SugerenciaMed = cacheSugerenciaMed[texto];
	                        actualizaSugerenciaMed();
	                    }
	                }
	
function sinResultadosMed() {
	document.getElementById("SugerenciaMed").innerHTML = "No existe registro";
	document.getElementById("SugerenciaMed").style.display = "block";
}

function actualizaSugerenciaMed() {
	elementoSeleccionado = -1;
	muestraSugerenciaMed();
}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugerenciaMed() {
	var zonaSugerenciaMed = document.getElementById("SugerenciaMed");
	zonaSugerenciaMed.innerHTML = SugerenciaMed.formateaListaMed();
	zonaSugerenciaMed.style.display = 'block';     
}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaListaMed = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
	            }else {
	                codigoHtml += '<li><a  href="#" onclick="confirmarMed(this.innerHTML)">'+this[i]+'</a><\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('SugerenciaMed');
		div.style.display='none';
	    return codigoHtml;	     
	};
function borraListaMed() {
	document.getElementById("SugerenciaMed").innerHTML = "";
	document.getElementById("SugerenciaMed").style.display = "none";
}

function confirmarMed(cf){		
		 var codigo;
		 var nombre;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	nombre=z[0];
		    codigo=z[1];
		    }
	       document.getElementById("txtMedicamento").value = nombre;
	       document.getElementById("txtCodigoMed").value = codigo;
	        borraListaMed();
	}	

/****************************************************************************************/
/***************************************AUTOCOMPLETAR MEDICAMENTO FORMULACION**************/
function autocompletarMedicamentoFormula () {     

    var elEvento = arguments[0] || window.event;

                    var texto = document.getElementById("txtMedicamento").value;
                    var tipo = document.getElementById("txtTipo").value;
                   // alert(tipo)
                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
                    if(texto == "") {
                        borraListaMedFormula();
                        return;
                    }
                    if(texto!=" "){
                    if(cacheSugerenciaMedFormula[texto] == null) {
                        peticion = inicializa_xhr();
                        peticion.onreadystatechange = function() {
                                    if(peticion.readyState == 4) {
                                        if(peticion.status == 200) {
                                        	SugerenciaMed = eval('('+peticion.responseText+')');
                                                if(SugerenciaMed.length == 0) {
                                                    sinResultadosMedFormula();
                                                }else {
                                                    cacheSugerenciaMedFormula[texto] = SugerenciaMedFormula;
                                                    actualizaSugerenciaMedFormula();
                                                }
                                            }
                                        }
                                    };
                                var txtAccion="27";  
                                peticion.open("GET","ControlMultiplePacientes?txtAccion="+txtAccion+"&codigo="+texto+"&tipo="+tipo,true);                                 
                                peticion.send("");
                    } else {
                    	SugerenciaMedFormula = cacheSugerenciaMedFormula[texto];
                        actualizaSugerenciaMedFormula();
                    }
}
                }

function sinResultadosMedFormula() {
document.getElementById("SugerenciaMedFormula").innerHTML = "No existe registro";
document.getElementById("SugerenciaMedFormula").style.display = "block";
}

function actualizaSugerenciaMedFormula() {
elementoSeleccionado = -1;
muestraSugerenciaMedFormula();
}

/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugerenciaMedFormula() {
var zonaSugerenciaMedFormula = document.getElementById("SugerenciaMedFormula");
zonaSugerenciaMedFormula.innerHTML = SugerenciaMed.formateaListaMedFormula();
zonaSugerenciaMedFormula.style.display = 'block';     
}

/*prototypo de la lista quesale en el div de sugerencias*/
Array.prototype.formateaListaMedFormula = function() {
    var codigoHtml = "";
    codigoHtml = "<ul>";	    
        for(var i=0; i<this.length-1; i++) {
            if(i == elementoSeleccionado) {
                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
            }else {
                codigoHtml += '<li><a  href="#" onclick="confirmarMedFormula(this.innerHTML)">'+this[i]+'</a><\/li>';
            }
        }
    codigoHtml += "<\/ul>";
   div = document.getElementById('SugerenciaMedFormula');
	div.style.display='none';
    return codigoHtml;	     
};
function borraListaMedFormula() {
document.getElementById("SugerenciaMedFormula").innerHTML = "";
document.getElementById("SugerenciaMedFormula").style.display = "none";
}

function confirmarMedFormula(cf){		
	 var codigo;
	 var nombre;
     	var y=cf.split("|").length;
     	var z=cf.split("|");		     	
     	for(x=0; x<y-1; x=x+1)
     	{ 
     	nombre=z[0];
	    codigo=z[1];
	    }
       document.getElementById("txtMedicamento").value = nombre;
       document.getElementById("txtCodigoMed").value = codigo;
        borraListaMedFormula();
        BuscarTipoMedi();
}	


/***************************************AUTOCOMPLETAR MEDICAMENTO FORMULACION**************/
function autocompletarMedicamentoFormulaCE () {     

    var elEvento = arguments[0] || window.event;

                    var texto = document.getElementById("txtMedicamento").value;
                    var tipo = document.getElementById("txtTipo").value;
                   // alert(tipo)
                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
                    if(texto == "") {
                        borraListaMedFormulaCE();
                        return;
                    }
                    if(texto!=" "){
                    if(cacheSugerenciaMedFormulaCE[texto] == null) {
                        peticion = inicializa_xhr();
                        peticion.onreadystatechange = function() {
                                    if(peticion.readyState == 4) {
                                        if(peticion.status == 200) {
                                        	SugerenciaMedCE = eval('('+peticion.responseText+')');
                                                if(SugerenciaMedCE.length == 0) {
                                                    sinResultadosMedFormulaCE();
                                                }else {
                                                    cacheSugerenciaMedFormulaCE[texto] = SugerenciaMedFormulaCE;
                                                    actualizaSugerenciaMedFormulaCE();
                                                }
                                            }
                                        }
                                    };
                                var txtAccion="27";  
                                peticion.open("GET","ControlMultiplePacientes?txtAccion="+txtAccion+"&codigo="+texto+"&tipo="+tipo,true);                                 
                                peticion.send("");
                    } else {
                    	SugerenciaMedFormulaCE = cacheSugerenciaMedFormulaCE[texto];
                        actualizaSugerenciaMedFormulaCE();
                    }
}
                }

function sinResultadosMedFormulaCE() {
document.getElementById("SugerenciaMedFormulaCE").innerHTML = "No existe registro";
document.getElementById("SugerenciaMedFormulaCE").style.display = "block";
}

function actualizaSugerenciaMedFormulaCE() {
elementoSeleccionado = -1;
muestraSugerenciaMedFormulaCE();
}

/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugerenciaMedFormulaCE() {
var zonaSugerenciaMedFormulaCE = document.getElementById("SugerenciaMedFormulaCE");
zonaSugerenciaMedFormulaCE.innerHTML = SugerenciaMedCE.formateaListaMedFormulaCE();
zonaSugerenciaMedFormulaCE.style.display = 'block';     
}

/*prototypo de la lista quesale en el div de sugerencias*/
Array.prototype.formateaListaMedFormulaCE = function() {
    var codigoHtml = "";
    codigoHtml = "<ul>";	    
        for(var i=0; i<this.length-1; i++) {
            if(i == elementoSeleccionado) {
                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
            }else {
                codigoHtml += '<li><a  href="#" onclick="confirmarMedFormulaCE(this.innerHTML)">'+this[i]+'</a><\/li>';
            }
        }
    codigoHtml += "<\/ul>";
   div = document.getElementById('SugerenciaMedFormulaCE');
	div.style.display='none';
    return codigoHtml;	     
};
function borraListaMedFormulaCE() {
document.getElementById("SugerenciaMedFormulaCE").innerHTML = "";
document.getElementById("SugerenciaMedFormulaCE").style.display = "none";
}

function confirmarMedFormulaCE(cf){	
	//alert();
	 var codigo;
	 var nombre;
     	var y=cf.split("|").length;
     	var z=cf.split("|");		     	
     	for(x=0; x<y-1; x=x+1)
     	{ 
     	nombre=z[0];
	    codigo=z[1];
	    }
       document.getElementById("txtMedicamento").value = nombre;
       document.getElementById("txtCodigoMed").value = codigo;
        borraListaMedFormulaCE();
        BuscarTipoMediCE();
}





/***************************************AUTOCOMPLETAR MEDICAMENTO FORMULACION TRIAGE**************/
function autocompletarMedicamentoFormulaTr () {     

    var elEvento = arguments[0] || window.event;

                    var texto = document.getElementById("txtMedicamento").value;
                    var tipo = document.getElementById("txtTipo").value;
                   // alert(tipo)
                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
                    if(texto == "") {
                        borraListaMedFormulaTr();
                        return;
                    }
                    if(texto!=" "){
                    if(cacheSugerenciaMedFormulaTr[texto] == null) {
                        peticion = inicializa_xhr();
                        peticion.onreadystatechange = function() {
                                    if(peticion.readyState == 4) {
                                        if(peticion.status == 200) {
                                        	SugerenciaMedTr = eval('('+peticion.responseText+')');
                                                if(SugerenciaMedTr.length == 0) {
                                                    sinResultadosMedFormulaTr();
                                                }else {
                                                    cacheSugerenciaMedFormulaTr[texto] = SugerenciaMedFormulaTr;
                                                    actualizaSugerenciaMedFormulaTr();
                                                }
                                            }
                                        }
                                    };
                                var txtAccion="27";  
                                peticion.open("GET","ControlMultiplePacientes?txtAccion="+txtAccion+"&codigo="+texto+"&tipo="+tipo,true);                                 
                                peticion.send("");
                    } else {
                    	SugerenciaMedFormulaTr = cacheSugerenciaMedFormulaTr[texto];
                        actualizaSugerenciaMedFormulaTr();
                    }
}
                }

function sinResultadosMedFormulaTr() {
document.getElementById("SugerenciaMedFormulaTr").innerHTML = "No existe registro";
document.getElementById("SugerenciaMedFormulaTr").style.display = "block";
}

function actualizaSugerenciaMedFormulaTr() {
elementoSeleccionado = -1;
muestraSugerenciaMedFormulaTr();
}

/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
function muestraSugerenciaMedFormulaTr() {
var zonaSugerenciaMedFormulaTr = document.getElementById("SugerenciaMedFormulaTr");
zonaSugerenciaMedFormulaTr.innerHTML = SugerenciaMedTr.formateaListaMedFormulaTr();
zonaSugerenciaMedFormulaTr.style.display = 'block';     
}

/*prototypo de la lista quesale en el div de sugerencias*/
Array.prototype.formateaListaMedFormulaTr = function() {
    var codigoHtml = "";
    codigoHtml = "<ul>";	    
        for(var i=0; i<this.length-1; i++) {
            if(i == elementoSeleccionado) {
                codigoHtml += '<li class= \"seleccionado\"><a >'+this[i]+'</a><\/li>';
            }else {
                codigoHtml += '<li><a  href="#" onclick="confirmarMedFormulaTr(this.innerHTML)">'+this[i]+'</a><\/li>';
            }
        }
    codigoHtml += "<\/ul>";
   div = document.getElementById('SugerenciaMedFormulaTr');
	div.style.display='none';
    return codigoHtml;	     
};
function borraListaMedFormulaTr() {
document.getElementById("SugerenciaMedFormulaTr").innerHTML = "";
document.getElementById("SugerenciaMedFormulaTr").style.display = "none";
}

function confirmarMedFormulaTr(cf){	

	 var codigo;
	 var nombre;
     	var y=cf.split("|").length;
     	var z=cf.split("|");		     	
     	for(x=0; x<y-1; x=x+1)
     	{ 
     	nombre=z[0];
	    codigo=z[1];
	    }
    	//alert("nombre "+nombre+" codigo "+codigo);
       document.getElementById("txtMedicamento").value = nombre;
       document.getElementById("txtCodigoMed").value = codigo;
        borraListaMedFormulaTr();
        BuscarTipoMediTr();
}