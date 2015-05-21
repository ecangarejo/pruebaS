var peticion = null;
var elementoSeleccionado = -1;
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
	function autocompletaPaciente() {      
		
		//Picture1.MousePointer = 99
	    var elEvento = arguments[0] || window.event;
	   
	    var tecla = elEvento.keyCode;
	    
	    
	        if(tecla == 40) { // Flecha Abajo
		       
	               if(elementoSeleccionado+1 < sugerencias1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias1();
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
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
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                sugerencias1 = eval('('+peticion.responseText+')');
	                                                if(sugerencias1.length == 0) {
	                                                    sinResultadosPaci();
	                                                }else {
	                                                    cachesugerencias1[texto] = sugerencias1;
	                                                    actualizasugerencias1Paci();
	                                                }
	                                            }
	                                        }
	                                    };
	                              //  var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/ControlBuscarUsu";
	                                //peticion.open("POST",cad, true); 
	                                var txtAccion="26";  
	                                peticion.open("GET","ControlPacientesPendientes?txtAccion="+txtAccion+"&codigo="+texto,true);                                 
	                        //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet', true);
	                       // peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	                        //var querys = "txtAccion="+encodeURIComponent(26)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
	                       // peticion.send(querys);
	     	                       peticion.send("");
	                    } else {
	                        sugerencias1 = cachesugerencias1[texto];
	                        actualizasugerencias1Paci();
	                    }
	                }
	}
	function sinResultadosPaci() {
	    document.getElementById("sugerencias1").innerHTML = "No existe registro";
	    document.getElementById("sugerencias1").style.display = "block";
	    //document.getElementById("bttnAdicionarFila").disabled = true;
	}

	function actualizasugerencias1Paci() {
		elementoSeleccionado = -1;
		muestrasugerencias1();
		
		  
		}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias1() {
	    var zonasugerencias1 = document.getElementById("sugerencias1");
	        zonasugerencias1.innerHTML = sugerencias1.formateaLista();
	        zonasugerencias1.style.display = 'block';
// div = document.getElementById('resultado');
			
		//	div.style.display='none';
	       
	       
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";
	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarPaci(this.innerHTML)">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('resultado');
		div.style.display='none';
	    return codigoHtml;
	     
	};

	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElemento() {
	
	
	
	    if(sugerencias1[elementoSeleccionado]) {
		    var kitty=sugerencias1[elementoSeleccionado];
              
            
		   var resul;
	     	var y=kitty.split("|").length;
	     	var z=kitty.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    // form1.cbArea.value();
		    }
	    	
	       document.getElementById("txtnumdoc").value = resul;
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
	       document.getElementById("txttipodoc").value = tipodoc;
	       
	        borraListaPaci();

	        document.getElementById("txtnombre").focus();	


	}
	