var peticion = null;
var elementoSeleccionado = -1;
var articulos = null;
var cachesugerencias1 = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletaArticulo(idiv,itex) {     
		//Picture1.MousePointer = 99
		var texto = document.getElementById("txtTipoMe"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias1(articulos,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias1(articulos, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                    seleccionaElementoArticulo(articulos, idiv, itex);
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("txtTipoMe"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraLista1(articulos);
	                        return;
	                    }
	                    if(cachesugerencias1[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                articulos = eval('('+peticion.responseText+')');
	                                                if(articulos.length == 0) {
	                                                    sinResultadosArticulo(idiv);
	                                                }else {
	                                                    cachesugerencias1[texto] = articulos;
	                                                    actualizasugerencias1(articulos,idiv,itex);
	                                                }
	                                            }
	                                        }
	                                    };
	                                peticion.open("POST","ControlEntradas?valor=autoart&texto="+texto,true);                                 
	     	                        peticion.send("");
	                    } else {
	                        articulos = cachesugerencias1[texto];
	                        actualizasugerencias1(articulos,idiv,itex);
	                    }
	                }
		}
	}
	function sinResultadosArticulo(idiv) {
		var x="sugerencias1"+idiv;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}

	function actualizasugerencias1(articulos,idiv,itex) {
		elementoSeleccionado = -1;
		muestrasugerencias1(articulos,idiv,itex);
	}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias1(articulos,idiv,itex) {
		    var zonasugerencias1 = document.getElementById("sugerencias1"+idiv);
	        zonasugerencias1.innerHTML = articulos.formateaLista1(idiv,itex);
	        zonasugerencias1.style.display = 'block';	
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista1 = function(idiv,itex) {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarArticulo(this.innerHTML,'+itex+','+idiv+')">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoArticulo(articulos,idiv, itex) {
	    if(articulos[elementoSeleccionado]) {
	    	var kitty=articulos[elementoSeleccionado];
		    var codigo;
		    var nombre;
		    var y=kitty.split("|").length;
		    var z=kitty.split("|");		     	
		    for(x=0; x<y-1; x=x+1)
		    { 
		    	codigo=z[1];
		    	nombre=z[0];
		    }
		    
		    document.getElementById("txtTipoMeH"+itex).value = codigo;
			document.getElementById("txtTipoMe"+itex).value = nombre;

    	    borraLista1(idiv);
		    document.getElementById("txtLote"+itex).focus();
		   
	    }
	}
	function borraLista1(idiv) {
        document.getElementById("sugerencias1"+idiv).innerHTML = "";
        document.getElementById("sugerencias1"+idiv).style.display = "none";
	}

function confirmarArticulo(cf,itex,idiv){		
	  
		 var codigo;
		 var nombre;
		 var y=cf.split("|").length;
		 var z=cf.split("|");		     	
		 for(x=0; x<y-1; x=x+1)
		 { 
			 codigo=z[1];
			 nombre=z[0];
		 }
		  
		 document.getElementById("txtTipoMeH"+itex).value = codigo;
		 document.getElementById("txtTipoMe"+itex).value = nombre;
		 borraLista1(idiv);
		 document.getElementById("txtLote"+itex).focus();	
	}
	
	