var peticion1 = null;
var elementoSeleccionadop = -1;
var articulos12 = null;
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
	function autocompletaRefMD(idiv,itex,opcion) {    
		
		//Picture1.MousePointer = 99
		var texto = document.getElementById("ref"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	   
	////LIMPIAR FILA///
    	
    	document.getElementById("desct0").value="";
    	document.getElementById("serv0").length=0;
    	document.getElementById("serv0").innerHTML="";
    	document.getElementById("cant0").value="";
    	document.getElementById("med0").value="";
    	if(document.getElementById("medh01")!=null){
    		document.getElementById("medh01").value="";
    	}
    	document.getElementById("valorpyp0").value="";
    	borraLista12MD(idiv);
    	eliminarLineasMD();
    /////
	    
	    
	        if(tecla == 40) { // Flecha Abajo
	        	
	               if(elementoSeleccionadop+1 < articulos12.length) {
	                    elementoSeleccionadop++;
	                }
	        	
	            muestrasugerencias312MD(articulos12,idiv,itex);
	        }else{
	            if(tecla == 38) { // Flecha Arriba
	                    if(elementoSeleccionadop > 0) {
	                        elementoSeleccionadop--;
	                    }
	                muestrasugerencias312MD(articulos12, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	
	                	seleccionaElementoArticulo32MD(articulos12, idiv, itex,opcion);
	                }else {
	                	
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                	
	                    if(tecla == 8 && texto=="") {
	                    	
	                        borraLista312MD(articulos12);
	                        
	                    }
	                    if(cachesugerencias312[texto] == null ) {
	                    	
	                    
	                    	
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                        	
	                                    if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos12 = eval('('+peticion1.responseText+')');
	                                                if(articulos12.length == 0) {
	                                                    sinResultadosArticulo322MD(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	
	                                                    cachesugerencias312[texto] = articulos12;
	                                                    actualizasugerencias3112MD(articulos12,idiv,itex);
	                                                    
	                                                    
	                                                }
	                                                seleccionAutomaticaMD(idiv,itex,opcion);
	                                            }
	                                        }
	                                    };
	                                var ee="epsh0";
		                             var xx=document.getElementById(ee).value;
		                             
			                               
	                              // alert("mbase: "+xx);
	                                peticion1.open("POST","ControlMovimientos?valor=autoinv4&texto="+texto+"&xx="+xx,true);                                 
	     	                        peticion1.send("");
	     	                       
	                    } else {
	                        articulos12 = cachesugerencias312[texto];
	                        actualizasugerencias3112MD(articulos12,idiv,itex);
	                        seleccionAutomaticaMD(idiv,itex,opcion);
	                    }
	                }
	        	}
	        
		}else{
		
    		document.getElementById('desct0').focus();
    	
		}
	}
	
	function seleccionAutomaticaMD(idiv,itex,opcion){
	
        var temp_articulos12 = articulos12+"";
        temp_articulos12 = temp_articulos12.substring(0,temp_articulos12.length-1);
        temp_articulos12 = temp_articulos12.split(",");

        nSug=articulos12.length;
        var nlsug = 0;
		var lsug=null;
		for(i=0; i<nSug-1; i=i+1){
		  lsug=articulos12[i];
		  nlsug++;
		}
        if(nlsug==1){
        	elementoSeleccionadop++;
        	seleccionaElementoArticulo32MD(articulos12, idiv, itex,opcion);
        }
        
	}
	
	function eliminarLineasMD(){
		if(document.getElementById('ref1')!=null){
			
			while(document.getElementById('ref1')!=null){
				document.getElementById('lineasCreacion').childNodes[0].removeChild(document.getElementById('lineasCreacion').childNodes[0].lastChild);
			}
			document.getElementById('chkCarg').innerHTML="<div id='carga'  align='center'><a disabled='disabled' id='cargar'>Cargar</a></div>";
			var enca = document.getElementById("enca").value;
			Estanciash(enca);
			document.getElementById('accCarg').removeChild(document.getElementById('accCarg').lastChild);
			document.getElementById("pop").value = "1";
		}
		
	}
	
	function sinResultadosArticulo322MD(idiv) {
		var x="sugerencias321"+idiv;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}

	function actualizasugerencias3112MD(articulos12,idiv,itex) {
		elementoSeleccionadop = -1;
		muestrasugerencias312MD(articulos12,idiv,itex);
	}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias312MD(articulos12,idiv,itex) {
		    var zonasugerencias1 = document.getElementById("sugerencias321"+idiv);
		    zonasugerencias1.style.display='inline';
		    var mostrar={};
			y=articulos12.length;
			var kittys=null;
			for(x=0; x<y-1; x=x+1){
			  kittys=articulos12[x];
			  z=kittys.split("|");
			  mostrar[x]=z[0]+" - "+z[1]; 
			  
			 }
		    
		    zonasugerencias1.innerHTML = articulos12.formateaLista312MD(idiv,itex,mostrar);
	        zonasugerencias1.style.display = 'block';	
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista312MD = function(idiv,itex,mostrar) {
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
	function seleccionaElementoArticulo32MD(articulos12,idiv, itex,opcion) {
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
		    	codigo=z[0];
		    	nombre=z[1];
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
		//	document.getElementById("servich"+itex).value = venc;
		//	document.getElementById("serv"+itex).options[0].text = ca;
	//
    	    borraLista312MD(idiv);
		    //document.getElementById("serv"+itex).focus();
    	    serviciosPYPh(lot,ca,nombre,act,med,opcion);
    	    
    	    
    	    document.getElementById("cant0").focus();
    	    document.getElementById("cant0").select();
	    }
	}
	function borraLista312MD(idiv){
        document.getElementById("sugerencias321"+idiv).innerHTML = "";
        document.getElementById("sugerencias321"+idiv).style.display = "none";
	}

function confirmarArticulo32(cf,itex,idiv,es){		
	cf=articulos12[es];
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
		// document.getElementById("serv"+itex).options[0].value = venc;
		// document.getElementById("serv"+itex).options[0].text = ca;
		
		 borraLista312MD(idiv);
		// alert("autocompletar: "+act);
		 serviciosPYPh(lot,ca,nombre,act,med);
		 //document.getElementById("serv"+itex).focus();	
	}
	
	