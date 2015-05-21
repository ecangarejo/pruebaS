var peticion1 = null;
var elementoSeleccionadop = -1;
var articulos412 = null;
var cachesugerencias312 = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletaMed(idiv,itex,idenSug) {    
		//Picture1.MousePointer = 99
		
		var texto = document.getElementById("med"+(idenSug-1)).value;
		if(texto!=""){
			//alert(texto);
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionadop+1 < articulos412.length) {
	                    elementoSeleccionadop++;
	                }
	            muestrasugerencias4312(articulos412,idiv,itex,idenSug);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionadop > 0) {
	                        elementoSeleccionadop--;
	                    }
	                muestrasugerencias4312(articulos412, idiv, itex,idenSug);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                    seleccionaElementoArticulo432(articulos412, idiv, itex,idenSug);
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("med"+(idenSug-1)).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraLista4312(articulos412);
	                        return;
	                    }
	                    if(cachesugerencias312[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                        	
	                                    if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos412 = eval('('+peticion1.responseText+')');
	                                                if(articulos412.length == 0) {
	                                                    sinResultadosArticulo4322(idiv,idenSug);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(idenSug);
	                                                    cachesugerencias312[texto] = articulos412;
	                                                    actualizasugerencias43112(articulos412,idiv,itex,idenSug);
	                                                }
	                                            }
	                                        }
	                                    };
	                               // var ee="epsh0";
		                            // var xx=document.getElementById(ee).value;  
			                               
	                              // alert("mbase: "+texto);
	                                peticion1.open("POST","ControlMovimientos?valor=automed&texto="+texto,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos412 = cachesugerencias312[texto];
	                        actualizasugerencias43112(articulos412,idiv,itex,idenSug);
	                    }
	                }
		}
	}
	function sinResultadosArticulo4322(idiv,idenSug) {
		var x="sugerencias4321"+idiv+idenSug;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}

	function actualizasugerencias43112(articulos412,idiv,itex,idenSug) {
		
		elementoSeleccionadop = -1;
		muestrasugerencias4312(articulos412,idiv,itex,idenSug);
	}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias4312(articulos412,idiv,itex,idenSug) {
			
		    var zonasugerencias1 = document.getElementById("sugerencias4321"+idiv+idenSug);
		    zonasugerencias1.style.display='inline';
		    var mostrar={};
			y=articulos412.length;
			var kittys=null;
			for(x=0; x<y-1; x=x+1){
			  kittys=articulos412[x];
			  z=kittys.split("|");
			  mostrar[x]=z[0]+" - "+z[1]; 
			 }
		    
		    zonasugerencias1.innerHTML = articulos412.formateaLista312(idiv,itex,mostrar);
	        zonasugerencias1.style.display = 'block';	
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista312 = function(idiv,itex,mostrar) {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionadop) {
	                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarArticulo32(this.innerHTML,'+itex+','+idiv+','+i+')">'+mostrar[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoArticulo432(articulos412,idiv, itex,idenSug) {
	    if(articulos412[elementoSeleccionadop]) {
	    	var kitty=articulos412[elementoSeleccionadop];
		    var codigo;
		    var nombre;
		    var lot;
		    var inv;
		    var venc;
			var ca;
			//alert(kitty);
		    var y=kitty.split("|").length;
		    var z=kitty.split("|");		     	
		    for(x=0; x<y-1; x=x+1)
		    { 
		    	codigo=z[0];
		    	nombre=z[1];
		    	lot=z[2];
		    
		    	
		    //	alert(codigo+" - "+nombre+" - "+lot);
		   
		    }
		    
		    document.getElementById("med"+(idenSug-1)).value = codigo;
		    
			document.getElementById("medh"+(idenSug-1)+idenSug).value = lot;
			


    	    borraLista4312(idiv,idenSug);
    	    
    	  
    	    if(document.getElementById("cant"+(idenSug))==null){
    	    	document.getElementById("cargar").focus();
    	    }else{
    	    	document.getElementById("cant"+(idenSug)).focus();
    	    	document.getElementById("cant"+(idenSug)).select();
    	    	    	    	
    	    }
    	    
		   
	    }
	}
	function borraLista4312(idiv,idenSug) {
        document.getElementById("sugerencias4321"+idiv+idenSug).innerHTML = "";
        document.getElementById("sugerencias4321"+idiv+idenSug).style.display = "none";
	}

function confirmarArticulo32(cf,itex,idiv,es){		
	cf=articulos412[es];
		 var codigo;
		 var nombre;
		 var lot;
		 var inv;
		 var venc;
		 var ca;
		
		 var y=cf.split("|").length;
		 var z=cf.split("|");		     	
		 for(x=0; x<y-1; x=x+1)
		 { 
			 codigo=z[0];
		    	nombre=z[1];
		    	lot=z[2];
		    	
		    //	alert(codigo+" - "+nombre+" - "+lot);
		 }
		 
		 document.getElementById("med"+itex).value = codigo;
		 document.getElementById("medh"+itex).value = lot;

		
		 borraLista4312(idiv);

	}
	
	