var peticion11 = null;
var elementoSeleccionado11 = -1;
var articulos11 = null;
var cachesugerencias11 = {};
var selPaciente = false;
var selPac = false;
var hoa =null;
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletarTercero(idiv,itex) {  
		//alert("Tercero");
		//Picture1.MousePointer = 99
		var texto = document.getElementById("ter"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado11+1 < articulos11.length) {
	                    elementoSeleccionado11++;
	                }
	            muestrasugerencias11(articulos11,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado11 > 0) {
	                        elementoSeleccionado11--;
	                    }
	                muestrasugerencias11(articulos11, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticuloS(articulos11, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("ter"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista11(articulos11);
	                        return;
	                    }
	                    if(cachesugerencias11[texto] == null) {
	                    	
	                        peticion11 = inicializa_xhr();
	                        peticion11.onreadystatechange = function() {
	                        	
	                                    if(peticion11.readyState == 4) {	                                    	
	                                        if(peticion11.status == 200) {	                                        	
	                                        	 articulos11 = eval('('+peticion11.responseText+')');
	                                                if(articulos11.length == 0) {
	                                                    sinResultadosArticulo11(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos11);
	                                                    cachesugerencias11[texto] = articulos11;
	                                                    actualizasugerencias11(articulos11,idiv,itex);
	                                                }
	                                          //    seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                peticion11.open("POST","ControlDocumentos?valor=autoinv2&texto="+texto,true);                                 
	     	                        peticion11.send("");
	                    } else {
	                        articulos11 = cachesugerencias11[texto];
	                        actualizasugerencias11(articulos11,idiv,itex);
	                      //    seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	
	/*function seleccionAutomatica(idiv,itex){
	
    var temp_articulos112 = articulos11+"";
    temp_articulos112 = temp_articulos112.substring(0,temp_articulos112.length-1);
    temp_articulos112 = temp_articulos112.split(",");

    nSug=articulos11.length;
    var nlsug = 0;
	var lsug=null;
	for(i=0; i<nSug-1; i=i+1){
	  lsug=articulos11[i];
	  nlsug++;
	}
    if(nlsug==1){
    	elementoSeleccionado11++;
    	seleccionaElementoArticuloS(articulos11, idiv, itex);
    }
    
}*/

///COMIENZO AUTO COMPLETAR TERCERO DE LOS REPORTES DE DE CONTABILIDAD /////

	function autocompletarTercero1(idiv,itex) {  
		//alert("Tercero");
		//Picture1.MousePointer = 99
		var texto = document.getElementById("ter"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado11+1 < articulos11.length) {
	                    elementoSeleccionado11++;
	                }
	            muestrasugerenciasTerc11(articulos11,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado11 > 0) {
	                        elementoSeleccionado11--;
	                    }
	                muestrasugerenciasTerc11(articulos11, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticuloSTerc(articulos11, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("ter"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista11(articulos11);
	                        return;
	                    }
	                    if(cachesugerencias11[texto] == null) {
	                    	
	                        peticion11 = inicializa_xhr();
	                        peticion11.onreadystatechange = function() {
	                        	
	                                    if(peticion11.readyState == 4) {	                                    	
	                                        if(peticion11.status == 200) {	                                        	
	                                        	 articulos11 = eval('('+peticion11.responseText+')');
	                                                if(articulos11.length == 0) {
	                                                    sinResultadosArticulo11(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos11);
	                                                    cachesugerencias11[texto] = articulos11;
	                                                    actualizasugerencias11Terc(articulos11,idiv,itex);
	                                                }
	                                          //    seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                peticion11.open("POST","ControlDocumentos?valor=autoinv2&texto="+texto,true);                                 
	     	                        peticion11.send("");
	                    } else {
	                        articulos11 = cachesugerencias11[texto];
	                        actualizasugerencias11Terc(articulos11,idiv,itex);
	                      //    seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	
	function muestrasugerenciasTerc11(articulos11,idiv,itex) {
	    var zonasugerencias11 = document.getElementById("dter"+idiv);
	    var mostrar={};
		y=articulos11.length;
		var kittys=null;
		for(x=0; x<y-1; x=x+1){
		  kittys=articulos11[x];
		  z=kittys.split("|");
		  mostrar[x]=(z[1]+" - "+z[2]); 
		 }
        zonasugerencias11.innerHTML = articulos11.formateaLista11(idiv,itex,mostrar);
        zonasugerencias11.style.display = 'block';	
}


	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaListaTerc11 = function(idiv,itex,mostrar) {
		var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionado11) {
	                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarArticuloSTerc(this.innerHTML,'+itex+','+idiv+','+i+')">'+mostrar[i]+'<\/li>';
	            }
	        }
	 	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	
	function confirmarArticuloSTerc(cf,itex,idiv){	
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
		 }
		  
		document.getElementById("terh"+itex).value = codigo;
		document.getElementById("ter"+itex).value = nombre;
		document.getElementById("cta"+itex).focus();
		
		//Detalleh(venc,lot);
		borraLista11(idiv);
	}
	
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoArticuloSTerc(articulos11,idiv, itex) {
		//alert("entrando");
	    if(articulos11[elementoSeleccionado11]) {
	    	var kitty=articulos11[elementoSeleccionado11];
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
		    	codigo=z[0];
		    	nombre=z[1];
		    }
		    
		    document.getElementById("terh"+itex).value = codigo;
			document.getElementById("ter"+itex).value = nombre;
			document.getElementById("cta"+itex).focus();
			//Detalleh(venc,lot);
	
    	    borraLista11(idiv);

    	    selPaciente = true;
	    }
	}
	
	
	function actualizasugerencias11Terc(articulos11,idiv,itex) {
		elementoSeleccionado11 = -1;
		muestrasugerenciasTerc11(articulos11,idiv,itex);
	}
	
	
	///////FINALIZA EL AUTOCOMPLETAR DE TERCEROS PARA LOS RESPORTES/////
	
	function sinResultadosArticulo11(idiv) {
		var x="dter"+idiv;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}

	function actualizasugerencias11(articulos11,idiv,itex) {
		elementoSeleccionado11 = -1;
		muestrasugerencias11(articulos11,idiv,itex);
	}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias11(articulos11,idiv,itex) {
		    var zonasugerencias11 = document.getElementById("dter"+idiv);
		    var mostrar={};
			y=articulos11.length;
			var kittys=null;
			for(x=0; x<y-1; x=x+1){
			  kittys=articulos11[x];
			  z=kittys.split("|");
			  mostrar[x]=(z[1]+" - "+z[2]); 
			 }
	        zonasugerencias11.innerHTML = articulos11.formateaLista11(idiv,itex,mostrar,articulos11);
	        zonasugerencias11.style.display = 'block';	
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista11 = function(idiv,itex,mostrar,articulos11) {
		var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado11) {
	                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'<\/li>';
	            }else {
	                codigoHtml += "<li onclick='confirmarArticuloS(this.innerHTML,"+itex+","+idiv+","+i+",&quot;"+articulos11[i]+"&quot;)'>"+mostrar[i]+"<\/li>";
	            }
	        }
	 	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoArticuloS(articulos11,idiv, itex) {
	    if(articulos11[elementoSeleccionado11]) {
	    	var kitty=articulos11[elementoSeleccionado11];
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
		    	codigo=z[0];
		    	nombre=z[1];
		    }
		    
		    document.getElementById("terh"+itex).value = codigo;
			document.getElementById("ter"+itex).value = nombre;
			document.getElementById("des"+itex).focus();
			//Detalleh(venc,lot);
	
    	    borraLista11(idiv);

    	    selPaciente = true;
	    }
	}
	function borraLista11(idiv) {
        document.getElementById("dter"+idiv).innerHTML = "";
        document.getElementById("dter"+idiv).style.display = "none";
	}

function confirmarArticuloS(cf,itex,idiv,f,fr){	
		 var codigo;
		 var nombre;
		 var lot;
		 var inv;
		 var venc;
		 var ca;
		
		 var y=fr.split("|").length;
		 var z=fr.split("|");		     	
		 for(x=0; x<y-1; x=x+1)
		 { 
			 codigo=z[0];
		    nombre=z[1];
		 }
		  
		document.getElementById("terh"+itex).value = codigo;
		document.getElementById("ter"+itex).value = nombre;
		document.getElementById("des"+itex).focus();
		
		//Detalleh(venc,lot);
		borraLista11(idiv);
	}
	
	
	