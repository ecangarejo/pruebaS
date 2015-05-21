var peticion = null;
var elementoSeleccionado = -1;
var sugerencias = null;
var cacheSugerencias = {};
/*funci�n utilizada para realizar la petici�n asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*funci�n para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletarUsuarios() {

	    var elEvento = arguments[0] || window.event;
	   
	    var tecla = elEvento.keyCode;
	    
	    
	        if(tecla == 40) { // Flecha Abajo
		       
	               if(elementoSeleccionado+1 < sugerencias.length) {
	                    elementoSeleccionado++;
	                }
	            muestraSugerencias();
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestraSugerencias();
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                   
	                    seleccionaElemento();
	                	//clicksugerencia(choice);
	                }else {
	                    var texto = document.getElementById("txtnumdoc").value;
	                    // Si es la tecla de borrado y el texto es vac�o, ocultar la lista
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

	/*funci�n que muestra las sugerencias de codigos de referencia de presupuestos*/
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

	/*funci�n en el cual se le asigna a la caja de texto la opci�n selccionada de las sugerencias*/
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
	       document.getElementById("txtnumdoc").value = resul;
	        borraLista();
	      
	    }
	}
	function clicksugerencia(choice){		
	                var resul;
			     	var y=choice.split("|").length;
			     	var z=choice.split("|");		     	
			     	for(x=0; x<y-1; x=x+1)
			     	{ 
				    resul=z[0];
				    }
			    		
		
		
		 document.getElementById("txtnumdoc").value = resul; 
		 borraLista();
		 }
	function borraLista() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
}