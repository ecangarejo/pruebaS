var peticion1 = null;
var elementoSeleccionadop = -1;
var articulos12 = null;
var cachesugerencias12 = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletaPYSMD(idiv,itex,opcion) {
		//Picture1.MousePointer = 99
		var texto = document.getElementById("desct"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	    
	////LIMPIAR FILA///
	    
    	
    	
    	document.getElementById("ref0").value="";
    	document.getElementById("serv0").length=0;
    	document.getElementById("serv0").innerHTML="";
    	document.getElementById("cant0").value="";
    	document.getElementById("med0").value="";
    	if(document.getElementById("medh01")!=null){
    		document.getElementById("medh01").value="";
    	}
    	document.getElementById("valorpyp0").value="";
    	
    	borraLista312MD(idiv);
    	eliminarLineas()
    
    /////

	    
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionadop+1 < articulos12.length) {
	                    elementoSeleccionadop++;
	                }
	            muestrasugerencias12MD(articulos12,idiv,itex);
	        }else{ 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionadop > 0) {
	                        elementoSeleccionadop--;
	                    }
	                muestrasugerencias12(articulos12, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                    seleccionaElementoArticulo2MD(articulos12, idiv, itex,opcion);
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("desct"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraLista12(articulos12);
	                        return;
	                    }
	                    if(cachesugerencias12[texto] == null ) {
	                    	
	                    
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                        	
	                                    if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	
	                                        	//alert(peticion1.responseText);
	                                        	 articulos12 = eval('('+peticion1.responseText+')');
	                                                if(articulos12.length == 0) {
	                                                    sinResultadosArticulo22MD(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                    cachesugerencias12[texto] = articulos12;
	                                                    actualizasugerencias112MD(articulos12,idiv,itex);
	                                                }
	                                            }
	                                        }
	                                    };
	                                var ee="epsh0";
	                                var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                peticion1.open("POST","ControlMovimientos?valor=autoinv3&texto="+texto+"&xx="+xx,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos12 = cachesugerencias12[texto];
	                        actualizasugerencias112MD(articulos12,idiv,itex);
	                    }
	                }
	        	}
	        
	        
		}
	}
	function sinResultadosArticulo22MD(idiv) {
		var x="sugerencias21"+idiv;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}

	function actualizasugerencias112MD(articulos12,idiv,itex) {
		elementoSeleccionadop = -1;
		muestrasugerencias12(articulos12,idiv,itex);
	}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	var z=null;
	function muestrasugerencias12MD(articulos12,idiv,itex) {
		var mostrar={};   
		var zonasugerencias1 = document.getElementById("sugerencias21"+idiv);
	        
		y=articulos12.length;
		var kittys=null;
		for(x=0; x<y-1; x=x+1){
		  kittys=articulos12[x];
		  z=kittys.split("|");
		  mostrar[x]=z[1]+" - "+z[0]; 
		 }
		
		zonasugerencias1.innerHTML = articulos12.formateaLista12MD(idiv,itex,mostrar);
	        zonasugerencias1.style.display = 'block';	
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista12MD = function(idiv,itex,mostrar) {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionadop) {
	                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarArticulo2(this.innerHTML,'+itex+','+idiv+','+i+')">'+mostrar[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoArticulo2MD(articulos12,idiv, itex,opcion) {
	    if(articulos12[elementoSeleccionadop]) {
	    	var kitty=articulos12[elementoSeleccionadop];
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
		    	codigo=z[1];
		    	nombre=z[0];
		    	lot=z[2];
		    	inv=z[8];
		    	act=z[9];
		    	med=z[10];
		    	venc=z[5];
		    	ca=z[4];
		    }
		    
		    document.getElementById("descth"+itex).value = ca;
			 document.getElementById("desct"+itex).value = nombre;
			 document.getElementById("ref"+itex).value = codigo;
			 document.getElementById("valorpyp"+itex).value = inv;
			document.getElementById("cant"+itex).value = "1";
			
		
    	    borraLista12MD(idiv);
		    //document.getElementById("serv"+itex).focus();
    	    serviciosPYPh(lot,ca,nombre,act,med,opcion);
		   
	    }
	}
	function borraLista12MD(idiv) {
        document.getElementById("sugerencias21"+idiv).innerHTML = "";
        document.getElementById("sugerencias21"+idiv).style.display = "none";
	}

function confirmarArticulo2MD(cf,itex,idiv,es){	
	//alert("es: "+es);
	cf=articulos12[es];
	//alert(cf);
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
			 codigo=z[1];
			 nombre=z[0];
			 lot=z[2];
		     inv=z[8];
		     act=z[9];
		     med=z[10];
		     venc=z[5];
		     ca=z[4];
		 }
		  
		 document.getElementById("descth"+itex).value = ca;
		 document.getElementById("desct"+itex).value = nombre;
		 document.getElementById("ref"+itex).value = codigo;
		 document.getElementById("valorpyp"+itex).value = inv;
		 document.getElementById("cant"+itex).value = "1";
		// document.getElementById("servich"+itex).value = venc;
		// document.getElementById("serv"+itex).options[0].text = ca;
		 
			
		 
		 borraLista12MD(idiv);
		 serviciosPYPh(lot,ca,nombre,act,med);
		 //document.getElementById("serv"+itex).focus();	
	}
	
	