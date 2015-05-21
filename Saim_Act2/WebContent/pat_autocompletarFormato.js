var peticion = null;
var elementoSeleccionado = -1;
var sugerenciasFormato = null;
var cachesugerenciasFormato = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletarFormato() {      
		//alert();
		//Picture1.MousePointer = 99
	    var elEvento = arguments[0] || window.event;
	   
	   /* var tecla = elEvento.keyCode;
	    
	    
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
	                }else {*/
	                    var texto = document.getElementById("txtFormato").value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(texto == "") {
	                        borraListaFormato();
	                        return;
	                    }
	                    if(cachesugerenciasFormato[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                sugerenciasFormato = eval('('+peticion.responseText+')');
	                                                if(sugerenciasFormato.length == 0) {
	                                                	sinResultadosFormato();
	                                                }else {
	                                                    cachesugerenciasFormato[texto] = sugerenciasFormato;
	                                                    actualizasugerenciasFormato();
	                                                }
	                                            }
	                                        }
	                                    };
	                              //  var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/ControlBuscarUsu";
	                                //peticion.open("POST",cad, true); 
	                                var txtAccion="26";  
	                                peticion.open("GET","ControlVerFormatos?txtAccion="+txtAccion+"&codigo="+texto,true);                                 
	                        //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet', true);
	                       // peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	                        //var querys = "txtAccion="+encodeURIComponent(26)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
	                       // peticion.send(querys);
	     	                       peticion.send("");
	                    } else {
	                        sugerenciasFormato = cachesugerenciasFormato[texto];
	                        actualizasugerenciasFormato();
	                    }
	                }
	
	function sinResultadosFormato() {
	    document.getElementById("sugerenciasFormato").innerHTML = "No existe registro";
	    document.getElementById("sugerenciasFormato").style.display = "block";
	    //document.getElementById("bttnAdicionarFila").disabled = true;
	}

	function actualizasugerenciasFormato() {
		elementoSeleccionado = -1;
		muestrasugerenciasFormato();
		
		  
		}

	/*función que muestra las sugerenciasFormato de codigos de referencia de presupuestos*/
	function muestrasugerenciasFormato() {
	    var zonasugerenciasFormato = document.getElementById("sugerenciasFormato");
	        zonasugerenciasFormato.innerHTML = sugerenciasFormato.formateaListaFormato();
	        zonasugerenciasFormato.style.display = 'block';
// div = document.getElementById('resultado');
			
		//	div.style.display='none';
	       
	       
	}

	/*prototypo de la lista quesale en el div de sugerenciasFormato*/
	Array.prototype.formateaListaFormato = function() {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";
	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+this[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarFormato(this.innerHTML)">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('sugerenciasFormato');
		div.style.display='none';
	    return codigoHtml;
	     
	};

	function borraListaFormato() {
        document.getElementById("sugerenciasFormato").innerHTML = "";
        document.getElementById("sugerenciasFormato").style.display = "none";
}

function confirmarFormato(cf){
		
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
	     //	alert(codigo)
	       document.getElementById("txtCodFormato").value = codigo;
	       document.getElementById("txtFormato").value = nombre;
	       borraListaFormato();

	        //document.getElementById("txtFormato").focus();	


	}
	
	