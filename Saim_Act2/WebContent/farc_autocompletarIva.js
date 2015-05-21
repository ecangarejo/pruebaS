var peticion = null;
var elementoSeleccionado = -1;
var Ivas = null;
var cachesugerencias2 = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletaIva(idiv,itex) {  
		var texto = document.getElementById("txtIva"+itex).value;
		if(texto!=""){
		//alert("por lo menos entramos"+idiv+itex);
		//Picture1.MousePointer = 99
		
		var elEvento =  window.event; //arguments[itex] //Verificar el arguments en otro navegador
		
		//alert(elEvento);
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < Ivas.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias2(Ivas,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias2(Ivas, idiv,itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                    seleccionaElementoIva(Ivas, idiv, itex);
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                   texto = document.getElementById("txtIva"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraLista2(Ivas);
	                        return;
	                    }
	                    if(cachesugerencias2[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                Ivas = eval('('+peticion.responseText+')');
	                                                if(Ivas.length == 0) {
	                                                    sinResultadosIva(idiv);
	                                                }else {
	                                                	cachesugerencias2[texto] = Ivas;
	                                                    actualizasugerencias2(Ivas,idiv,itex);
	                                                }
	                                            }
	                                        }
	                                    };
	                                peticion.open("POST","ControlEntradas?valor=autoiva&texto="+texto,true);                                 
	     	                        peticion.send("");
	                    } else {
	                        Ivas = cachesugerencias2[texto];
	                        actualizasugerencias2(Ivas,idiv,itex);
	                    }
	                }
		}
	}
		
	function sinResultadosIva(idiv) {
		var x="sugerencias2"+idiv;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}

	function actualizasugerencias2(Ivas,idiv,itex) {
		elementoSeleccionado = -1;
		muestrasugerencias2(Ivas,idiv,itex);
	}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias2(Ivas,idiv,itex) {
		    var zonasugerencias2 = document.getElementById("sugerencias2"+idiv);
	        zonasugerencias2.innerHTML = Ivas.formateaLista2(idiv,itex);
	        zonasugerencias2.style.display = 'block';	
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista2 = function(idiv,itex) {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarIva(this.innerHTML,'+itex+','+idiv+')">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoIva(Ivas,idiv, itex) {
	    if(Ivas[elementoSeleccionado]) {
	    	var kitty=Ivas[elementoSeleccionado];
		    var codigo;
		    var nombre;
		    var y=kitty.split("|").length;
		    var z=kitty.split("|");		     	
		    for(x=0; x<y-1; x=x+1)
		    { 
		    	codigo=z[1];
		    	nombre=z[0];
		    }
		    
		    document.getElementById("txtIvaH"+itex).value = codigo;
			document.getElementById("txtIva"+itex).value = nombre;

    	    borraLista2(idiv);
    	    document.getElementById("txtVunitario"+itex).focus();
		   
	    }
	}
	function borraLista2(idiv) {
        document.getElementById("sugerencias2"+idiv).innerHTML = "";
        document.getElementById("sugerencias2"+idiv).style.display = "none";
	}

function confirmarIva(cf,itex,idiv){		
	  
		 var codigo;
		 var nombre;
		 var y=cf.split("|").length;
		 var z=cf.split("|");		     	
		 for(x=0; x<y-1; x=x+1)
		 { 
			 codigo=z[1];
			 nombre=z[0];
		 }
		  
		 document.getElementById("txtIvaH"+itex).value = codigo;
		 document.getElementById("txtIva"+itex).value = nombre;
		 borraLista2(idiv);
		 document.getElementById("txtVunitario"+itex).focus();	
	}
	
	