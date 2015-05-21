var peticion1 = null;
var elementoSeleccionado = -1;
var articulos1 = null;
var cachesugerencias1 = {};
var textoCompleto = "";
var tecla;
String.prototype.trim = function() {
	return this.replace(/^\s+|\s+$/g,"");
}


function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	function autocompletaPActivos(idiv,itex) {
		var textoAux = document.getElementById("nomMedi").value;
		textoAux = textoAux.replace(/[+]/gi,'_');
		
		detTextoAux = textoAux.split("_");
		
		var texto = "";
		if(detTextoAux.length>1){
			texto = detTextoAux[detTextoAux.length-1];
			if(texto==""){
				borraLista1(idiv);
				document.getElementById('sugerencias0').scrollTop=0;
			}
			
		}else{
			if(textoCompleto==""){
				texto = document.getElementById("nomMedi").value;
			}
		}
		
		document.getElementById("nomMedi").value = document.getElementById("nomMedi").value.toUpperCase();
		if(texto!=""&&texto!=" "){
		var elEvento =  window.event;  //arguments[0] ||
	    tecla = elEvento.keyCode;
	    if(tecla != 32){
	        if(tecla == 40) { // Flecha Abajo
	               if(elementoSeleccionado+2 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	        	
	            muestrasugerencias1(articulos1,idiv,itex);
	            if(elementoSeleccionado>0){
	            	document.getElementById('sugerencias0').scrollTop += 13;
	            }
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	            	
	                muestrasugerencias1(articulos1, idiv, itex);
	                document.getElementById('sugerencias0').scrollTop -= 13;
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                    seleccionaElementoArticulo(articulos1, idiv, itex);
	                }else {	                	
	                    if(tecla == 8 && texto == "") {
	                       
	                    }
	                    if(cachesugerencias1[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                        	
	                                    if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length != 0) {
	                                                    cachesugerencias1[texto] = articulos1;
	                                                    actualizasugerencias11(articulos1,idiv,itex);
	                                                }else{
	                                                	borraLista1(idiv);
	                                                }
	                                            }
	                                        }
	                                    };

	                                peticion1.open("POST","ControlCrearMedicamentos?valor=sugestPA&texto="+texto,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias1[texto];
	                        actualizasugerencias11(articulos1,idiv,itex);
	                    }
	                }
	    	}
		}
		
		
	}

	function actualizasugerencias11(articulos1,idiv,itex) {
		elementoSeleccionado = -1;
		muestrasugerencias1(articulos1,idiv,itex);
	}
	function muestrasugerencias1(articulos1,idiv,itex) {
		    var zonasugerencias1 = document.getElementById("sugerencias"+idiv);
		    var mostrar={};
			y=articulos1.length;
			var kittys=null;
			for(x=0; x<y-1; x=x+1){
			  kittys=articulos1[x];
			  z=kittys.split("|");
			  mostrar[x]=z[0]; 
			 }
	        zonasugerencias1.innerHTML = articulos1.formateaLista1(idiv,itex,mostrar);
	        zonasugerencias1.style.display = 'block';	
	}

	Array.prototype.formateaLista1 = function(idiv,itex,mostrar) {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li>'+mostrar[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "</ul>";
	    return codigoHtml;
	};
	function seleccionaElementoArticulo(articulos1,idiv, itex) {
	    if(articulos1[elementoSeleccionado]) {
	    	var kitty=articulos1[elementoSeleccionado];
		    var codigo;
		    var nombre;
		    var lot;
		    var inv;
		    var venc;
			var ca;
			 
		    var y=kitty.split("|").length;
		    var z=kitty.split("|");		     	
		    for(x=0; x<y-1; x=x+1)
		    { 
		    	codigo=z[1];
		    	nombre=z[0];
		    	lot=z[2];
		    	inv=z[3];
		    	venc=z[4];
		    }
		    textoCompleto += nombre.trim();
		   
		    document.getElementById("nomMedi").value = textoCompleto;
		    
		    textoCompleto += "+";
    	    borraLista1(idiv);
	    }
	}
	function borraLista1(idiv) {
        document.getElementById("sugerencias"+idiv).innerHTML = "";
        document.getElementById("sugerencias"+idiv).style.display = "none";
	}

