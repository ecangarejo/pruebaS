
var peticion = null;
var elementoSeleccionado = -1;
var sugerenciasRe = null;
var cachesugerenciasRe = {};
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}

	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompleta_realizar() {
      
		
		//Picture1.MousePointer = 99
	    var elEvento = arguments[0] || window.event;
	   
	    var tecla = elEvento.keyCode;
	    
	    
	        /*if(tecla == 40) { // Flecha Abajo
		       
	               if(elementoSeleccionado+1 < sugerenciasRe.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerenciasRe();
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerenciasRe();
	            }else*/
	                if(tecla == 13) { // ENTER o Intro
	                   
	                    seleccionaElemento();
	                	//clicksugerencia(choice);
	                }else {
	                    var texto = document.getElementById("txtnumdoc").value;
	                   
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                        borraLista();
	                        return;
	                    }
	                    if(cachesugerenciasRe[texto] == null) {
	                        peticion = inicializa_xhr();
	                        peticion.onreadystatechange = function() {
	                                    if(peticion.readyState == 4) {
	                                        if(peticion.status == 200) {
	                                                sugerenciasRe = eval('('+peticion.responseText+')');
	                                                if(sugerenciasRe.length == 0) {
	                                                    sinResultados();
	                                                }else {
	                                                    cachesugerenciasRe[texto] = sugerenciasRe;
	                                                    actualizasugerenciasRe();
	                                                }
	                                            }
	                                        }
	                                    }

	                              //  var cad=protocolo+direccionServidor+":"+puertoDeEscuchaServidor+"/"+rutaAplicacion+"/ControlBuscarUsu";
	                                //peticion.open("POST",cad, true); 
	                                var txtAccion="26";  
	                                var tipo = document.form1.cbafiliacion.selectedIndex;
	                     		    tipodocu=document.form1.cbafiliacion.options[tipo].text;

	                                peticion.open("GET","lab_General?txtAccion="+txtAccion+"&codigo="+texto+"&tipo="+tipodocu+"",true);                                 
	                        //peticion.open('POST', 'http://127.0.0.1:8988/Modulo_Presupuesto%28Cuentas%29-ModuloPresupuesto-context-root/pre_presupuesto_servlet', true);
	                       // peticion.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	                        //var querys = "txtAccion="+encodeURIComponent(26)+"&txtCodigoReferencia="+encodeURIComponent(texto)+"&nocache="+Math.random();
	                       // peticion.send(querys);
	     	                       peticion.send("");
	                    } else {
	                        sugerenciasRe = cachesugerenciasRe[texto];
	                        actualizasugerenciasRe();
	                    }
	                }
	}
	function sinResultados() {
	    document.getElementById("sugerenciasRe").innerHTML = "No existe usuario";
	    document.getElementById("sugerenciasRe").style.display = "block";
	    //document.getElementById("bttnAdicionarFila").disabled = true;
	}

	function actualizasugerenciasRe() {
		elementoSeleccionado = -1;
		muestrasugerenciasRe();
		
		  
		}

	/*función que muestra las sugerenciasRe de codigos de referencia de presupuestos*/
	function muestrasugerenciasRe() {
	    var zonasugerenciasRe = document.getElementById("sugerenciasRe");
	        zonasugerenciasRe.innerHTML = sugerenciasRe.formateaLista();
	        zonasugerenciasRe.style.display = 'block';
// div = document.getElementById('resultado');
			
		//	div.style.display='none';
	       
	       
	}

	/*prototypo de la lista quesale en el div de sugerenciasRe*/
	Array.prototype.formateaLista = function() {
	    var codigoHtml = "";
	    codigoHtml ="<ul  class =\"scrollbox\" size='80'>"; 
	    
	        for(var i=0; i<this.length-1; i++) {
	            if(i == elementoSeleccionado) {     
	                codigoHtml += '<li  id=\"sugerenciasRe\" class= \"seleccionado\">'+this[i]+'<\/li>'; 
	            }else {
	                codigoHtml += '<li onclick="confirmar(this.innerHTML)">'+this[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
       div = document.getElementById('sugerenciasRe');
		div.style.display='none';
	    return codigoHtml;
	     
	};

	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerenciasRe*/
	function seleccionaElemento() {
	
	
	
	    if(sugerenciasRe[elementoSeleccionado]) {
		    var kitty=sugerenciasRe[elementoSeleccionado];
              
            
		   var resul;
	     	var y=kitty.split("|").length;
	     	var z=kitty.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    // form1.cbArea.value();
		    }
	    	
	       document.getElementById("txtnumdoc").value = resul;
	        borraLista();
	        

	       
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
        document.getElementById("sugerenciasRe").innerHTML = "";
        document.getElementById("sugerenciasRe").style.display = "none";
        //document.getElementById("bttnBuscarPresupuesto").disabled = true;
        //document.getElementById("bttnAdicionarFila").disabled = true;
        //limpiarTodosLosCampos();
}

	function confirmar(cf){
		
		//alert("ok"+cf);
		 var resul;
	     	var y=cf.split("|").length;
	     	var z=cf.split("|");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
		    resul=z[0];
		    // form1.cbArea.value();
		    }
	    	
	       document.getElementById("txtnumdoc").value = resul;
	        borraLista();

	       document.getElementById("txtnumdoc").focus();	


	}
	