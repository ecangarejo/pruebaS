var peticion1 = null;
var elementoSeleccionado1 = -1;
var sugerencias1 = null;
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
	function autocompletaNombrePaciente() {   
		//Picture1.MousePointer = 99
	    var elEvento = arguments[0] || window.event;	   
	    var tecla = elEvento.keyCode;  
	        if(tecla == 40) { // Flecha Abajo		       
	               if(elementoSeleccionado1+1 < sugerencias1.length) {
	                    elementoSeleccionado1++;
	                }
	            muestrasugerencias1();
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado1 > 0) {
	                        elementoSeleccionado1--;
	                    }
	                muestrasugerencias1();
	            }else
	                if(tecla == 13) { // ENTER o Intro	                   
	                    seleccionaElemento();
	                	//clicksugerencia(choice);
	                }else {
	                    var texto = document.getElementById("txtnombre").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraListaPaci();
	                        return;
	                    }
	                    if(cachesugerencias1[texto] == null) {
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                                    if(peticion1.readyState == 4) {
	                                        if(peticion1.status == 200) {
	                                                sugerencias1 = eval('('+peticion1.responseText+')');
	                                                if(sugerencias1.length == 0) {
	                                                    sinResultadosPaci();

	                                                }else {
	                                                    cachesugerencias1[texto] = sugerencias1;
	                                                    actualizasugerencias1Paci();

	                                                }
	                                            }
	                                        }
	                                    };
	                                var txtAccion="27";  
	                                peticion1.open("GET","Pat_ControlCrearPaciente?txtAccion="+txtAccion+"&codigo="+texto,true);                                 
	     	                       peticion1.send("");
	                    } else {
	                        sugerencias1 = cachesugerencias1[texto];
	                        actualizasugerencias1Paci();
	                    }
	                }
	}
	function sinResultadosPaci() {
	    document.getElementById("sugerencias1").innerHTML = "No existe registro";
	    document.getElementById("sugerencias1").style.display = "block";
	}

	function actualizasugerencias1Paci() {
		elementoSeleccionado1 = -1;
		muestrasugerencias1();
		
		  
		}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias1() {
	    var zonasugerencias1 = document.getElementById("sugerencias1");
	        zonasugerencias1.innerHTML = sugerencias1.formateaLista1();
	        zonasugerencias1.style.display = 'block';
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista1 = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado1) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarPaci(this.innerHTML)">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('sugerencias1');
		div.style.display='none';
	    return codigoHtml;
	     
	};

	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElemento() {
	
	
	
	    if(sugerencias1[elementoSeleccionado1]) {
		    var kitty=sugerencias1[elementoSeleccionado1];
              
            
		   var resul;
	     	var y=kitty.split("|").length;
	     	var z=kitty.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    // form1.cbArea.value();
		    }
	    	
	       //document.getElementById("txtnumdoc").value = resul;
	        borraListaPaci();
	        

	       
	       // document.getElementById("txtCodigoRefernciaPresupuestoBuscar").disabled = true;
	        //document.getElementById("bttnBuscarPresupuesto").disabled = false;
	        //activarBotones();

	        //document.getElementById("bttnBuscar").disabled = false;
	        //document.getElementById("bttnCancelar").disabled=;
	        //buscarNombreCuenta();
	        //activaBotonAdicionarFila();
	    }
	}
	function borraListaPaci() {
        document.getElementById("sugerencias1").innerHTML = "";
        document.getElementById("sugerencias1").style.display = "none";
        //document.getElementById("bttnBuscarPresupuesto").disabled = true;
        //document.getElementById("bttnAdicionarFila").disabled = true;
        //limpiarTodosLosCampos();
}

	function confirmarPaci(cf){
		
		//alert("ok"+cf);
		 var result;
		 var nombre;
		 var tipodoc;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     	tipodoc=z[0];
		    result=z[1];
		    nombre=z[2];
		    // form1.cbArea.value();
		    }
	    	//alert(nombre)
	       document.getElementById("txtnumdoc").value = result;
	       document.getElementById("txtnombre").value = nombre;
	       document.getElementById("cbafiliacion").value = tipodoc;
	       
	        borraListaPaci();

	        document.getElementById("txtnombre").focus();	


	}
	