var peticion1 = null;
var elementoSeleccionado = -1;
var articulos10 = null;
var cachesugerencias10 = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletaTarifac(idiv,itex) {    
		//Picture1.MousePointer = 99
		
		var mbasec=document.getElementById("mbasec").value;
		if(mbasec!="Seleccione"){
		
		var texto = document.getElementById("descc"+itex).value;
		
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos10.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias10(articulos10,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias10(articulos10, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                    seleccionaElementoArticulo0(articulos10, idiv, itex);
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("descc"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraLista10(articulos10);
	                        return;
	                    }
	                    if(cachesugerencias10[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                        	
	                                    if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos10 = eval('('+peticion1.responseText+')');
	                                                if(articulos10.length == 0) {
	                                                    sinResultadosArticulo0(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                    cachesugerencias10[texto] = articulos10;
	                                                    actualizasugerencias110(articulos10,idiv,itex);
	                                                }
	                                            }
	                                        }
	                                    };
	                              //  var ee="mbasec";
	                              //  var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                peticion1.open("GET","ControlCrearTarifas?valor=autoinv&texto="+texto+"&xx="+mbasec,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos10 = cachesugerencias10[texto];
	                        actualizasugerencias110(articulos10,idiv,itex);
	                    }
	                }
		}else{borraLista10(0);}
		
	  }else{alert("Debe seleccionar un Manual Tarifario!!!");
	  document.getElementById("descc"+itex).value="";}	
	}
	function sinResultadosArticulo0(idiv) {
		var x="sugerencias1"+idiv;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}

	function actualizasugerencias110(articulos10,idiv,itex) {
		elementoSeleccionado = -1;
		muestrasugerencias10(articulos10,idiv,itex);
	}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias10(articulos10,idiv,itex) {
		    var zonasugerencias10 = document.getElementById("sugerencias1"+idiv);
	        zonasugerencias10.innerHTML = articulos10.formateaLista10(idiv,itex);
	        zonasugerencias10.style.display = 'block';	
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista10 = function(idiv,itex) {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarArticulo0(this.innerHTML,'+itex+','+idiv+')">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoArticulo0(articulos10,idiv, itex) {
	    if(articulos10[elementoSeleccionado]) {
	    	var kitty=articulos10[elementoSeleccionado];
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
		    /*	lot=z[2];
		    	inv=z[3];
		    	venc=z[4];
		    	ca=z[5];*/
		    }
		    
		    document.getElementById("descch"+itex).value = codigo;
			document.getElementById("descc"+itex).value = nombre;
			/*document.getElementById("espe"+itex).options[0].value = lot;
			document.getElementById("espe"+itex).options[0].text = inv;
			document.getElementById("serv"+itex).options[0].value = venc;
			document.getElementById("serv"+itex).options[0].text = ca;*/
	
    	    borraLista10(idiv);
		    document.getElementById("fechaii"+itex).focus();
		   
	    }
	}
	function borraLista10(idiv) {
		//document.getElementById("descch0").value="";
		//document.getElementById("descc0").value="";
        document.getElementById("sugerencias1"+idiv).innerHTML = "";
        document.getElementById("sugerencias1"+idiv).style.display = "none";
        cachesugerencias10 = {};
	}

function confirmarArticulo0(cf,itex,idiv){		
	  
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
			/* lot=z[2];
		     inv=z[3];
		     venc=z[4];
		     ca=z[5];*/
		 }
		  
		 document.getElementById("descch"+itex).value = codigo;
		 document.getElementById("descc"+itex).value = nombre;
		/* document.getElementById("espe"+itex).options[0].value = lot;
		 document.getElementById("espe"+itex).options[0].text = inv;
		 document.getElementById("serv"+itex).options[0].value = venc;
		 document.getElementById("serv"+itex).options[0].text = ca;*/
		
		 borraLista10(idiv);
		 document.getElementById("fechaii"+itex).focus();	
	}
	
	