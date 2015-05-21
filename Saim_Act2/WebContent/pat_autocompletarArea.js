var peticion = null;
var elementoSeleccionado = -1;
var sugerencias = null;
var cacheSugerencias = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletaArea() {      
		
		//Picture1.MousePointer = 99
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
	                    var texto = document.getElementById("txtArea").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
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
	                              //  var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/ControlBuscarUsu";
	                                //peticion.open("POST",cad, true); 
	                                var txtAccion="26";  
	                                peticion.open("GET","Pat_ControlFormatoHic?txtAccion="+txtAccion+"&codigo="+texto,true);                                 
	                        //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet', true);
	                       // peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	                        //var querys = "txtAccion="+encodeURIComponent(26)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
	                       // peticion.send(querys);
	     	                       peticion.send("");
	                    } else {
	                        sugerencias = cacheSugerencias[texto];
	                        actualizaSugerencias();
	                    }
	                }
	}
	function sinResultados() {
	    document.getElementById("sugerencias").innerHTML = "No existe registro";
	    document.getElementById("sugerencias").style.display = "block";
	    //document.getElementById("bttnAdicionarFila").disabled = true;
	}

	function actualizaSugerencias() {
		elementoSeleccionado = -1;
		muestraSugerencias();
		
		  
		}

	/*función que muestra las sugerencias de codigos de referencia de presupuestos*/
	function muestraSugerencias() {
	    var zonaSugerencias = document.getElementById("sugerencias");
	        zonaSugerencias.innerHTML = sugerencias.formateaLista();
	        zonaSugerencias.style.display = 'block';
// div = document.getElementById('resultado');
			
		//	div.style.display='none';
	       
	       
	}

	/*prototypo de la lista quesale en el div de sugerencias*/
	Array.prototype.formateaLista = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";
	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmar(this.innerHTML)">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('sugerencias');
		div.style.display='none';
	    return codigoHtml;
	     
	};

	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias*/
	function seleccionaElemento() {
	
	
	
	    if(sugerencias[elementoSeleccionado]) {
		    var kitty=sugerencias[elementoSeleccionado];
              
            
		    var codigo;
			 var nombre;
		     	var y=kitty.split("|").length;
		     	var z=kitty.split("|");		     	
		     	for(x=0; x<y-1; x=x+1)
		     	{ 
			    codigo=z[1];
			    nombre=z[0];
			    // form1.cbArea.value();
			    }
		    	//alert(nombre)
		       document.getElementById("txtCodArea").value = codigo;
		       document.getElementById("txtArea").value = nombre;
		        borraLista();

		        document.getElementById("txtArea").focus();
	        

	       
	       // document.getElementById("txtCodigoRefernciaPresupuestoBuscar").disabled = true;
	        //document.getElementById("bttnBuscarPresupuesto").disabled = false;
	        //activarBotones();

	        //document.getElementById("bttnBuscar").disabled = false;
	        //document.getElementById("bttnCancelar").disabled=;
	        //buscarNombreCuenta();
	        //activaBotonAdicionarFila();
	    }
	}
	function borraLista() {
        document.getElementById("sugerencias").innerHTML = "";
        document.getElementById("sugerencias").style.display = "none";
        //document.getElementById("bttnBuscarPresupuesto").disabled = true;
        //document.getElementById("bttnAdicionarFila").disabled = true;
        //limpiarTodosLosCampos();
}

function confirmar(cf){
		
		//alert("ok"+cf);
		 var codigo;
		 var nombre;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    codigo=z[1];
		    nombre=z[0];
		    // form1.cbArea.value();
		    }
	    	//alert(nombre)
	       document.getElementById("txtCodArea").value = codigo;
	       document.getElementById("txtArea").value = nombre;
	        borraLista();

	        document.getElementById("txtArea").focus();	


	}
	
	