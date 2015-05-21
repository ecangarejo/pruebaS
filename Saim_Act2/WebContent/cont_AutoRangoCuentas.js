var peticion1 = null;
var elementoSeleccionado = -1;
var elementoSeleccionado4= -1;
var articulos1 = null;
var cachesugerencias1 = {};
var cachesugerencias2 = {};
var cachesugerencias3   = {};
var cachesugerencias4 = {};
var cachesugerencias5 = {};
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

	
	
	
///COMIENZA AUTOCOMPLETAR QUE SE UTILIZA PARA LOS BALANCES GENERALES//////	
	function autocompletaCta2(idiv,itex) {  
		//alert("autocompletaCta"+idiv+" - "+itex);
		//Picture1.MousePointer = 99
		
		var texto = document.getElementById("cta"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias2(articulos1,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias2(articulos1, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo1(articulos1, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("cta"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista2(articulos1);
	                        return;
	                    }
	                    if(cachesugerencias2[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                    	      if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo1(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos1);
	                                                    cachesugerencias2[texto] = articulos1;
	                                                    actualizasugerencias2(articulos1,idiv,itex);
	                                                }
	                                           //   seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                  //  alert("autocompletaCta2","ControlProveedor?valor=autoinv1&texto="+texto+"&opc="+1,true);
	                                peticion1.open("POST","ControlProveedor?valor=autoinv1&texto="+texto+"&opc="+1,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias2[texto];
	                        actualizasugerencias2(articulos1,idiv,itex);
	                       //   seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	function autocompletaCta3(idiv,itex) {  
		//alert("autocompletaCta"+idiv+" - "+itex);
		//Picture1.MousePointer = 99
		var texto = document.getElementById("RCC"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias3(articulos1,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias3(articulos1, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo2(articulos1, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("RCC"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista3(articulos1);
	                        return;
	                    }
	                    if(cachesugerencias2[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                    	      if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo2(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos1);
	                                                    cachesugerencias2[texto] = articulos1;
	                                                    actualizasugerencias3(articulos1,idiv,itex);
	                                                }
	                                           //   seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                  // alert("autocompletaCta3","ControlProveedor?valor=autoinv1&texto="+texto+"&opc="+1,true);       
	                                peticion1.open("POST","ControlReportes?valor=autoinv1&texto="+texto+"&opc="+1,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias2[texto];
	                        actualizasugerencias3(articulos1,idiv,itex);
	                       //   seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	function autocompletaCta4(idiv,itex) {  
		//alert("autocompletaCta"+idiv+" - "+itex);
		//Picture1.MousePointer = 99
		var texto = document.getElementById("cta"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias2(articulos1,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias2(articulos1, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo1(articulos1, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("cta"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista2(articulos1);
	                        limpcta(idiv);
	                        return;
	                    }
	                    if(cachesugerencias3[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                    	      if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo1(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos1);
	                                                    cachesugerencias3[texto] = articulos1;
	                                                    actualizasugerencias2(articulos1,idiv,itex);
	                                                }
	                                           //   seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                 
	                                    var opc=2;
	                                   
	                                peticion1.open("POST","ControlReportes?valor=autoinv1&texto="+texto+"&opc="+opc,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias3[texto];
	                        actualizasugerencias2(articulos1,idiv,itex);
	                       //   seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	function autocompletaCta5(idiv,itex) {  
		//alert("autocompletaCta"+idiv+" - "+itex);
		//Picture1.MousePointer = 99
		var texto = document.getElementById("RCC"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias3(articulos1,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias3(articulos1, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo2(articulos1, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("RCC"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista3(articulos1);
	                        return;
	                    }
	                    if(cachesugerencias3[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                    	      if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo2(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos1);
	                                                    cachesugerencias3[texto] = articulos1;
	                                                    actualizasugerencias3(articulos1,idiv,itex);
	                                                }
	                                           //   seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                    var opc=2;
	                                peticion1.open("POST","ControlReportes?valor=autoinv1&texto="+texto+"&opc="+opc,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias3[texto];
	                        actualizasugerencias3(articulos1,idiv,itex);
	                       //   seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	
	function autocompletaCta6(idiv,itex) {  
		//alert("autocompletaCta"+idiv+" - "+itex);
		//Picture1.MousePointer = 99
		var texto = document.getElementById("cta"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias2(articulos1,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias2(articulos1, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo1(articulos1, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("cta"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista2(articulos1);
	                        return;
	                    }
	                    if(cachesugerencias1[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                    	      if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo1(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos1);
	                                                    cachesugerencias1[texto] = articulos1;
	                                                    actualizasugerencias2(articulos1,idiv,itex);
	                                                }
	                                           //   seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                 
	                                    opc=3;
	                                   
	                                peticion1.open("POST","ControlReportes?valor=autoinv1&texto="+texto+"&opc="+opc,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias1[texto];
	                        actualizasugerencias2(articulos1,idiv,itex);
	                       //   seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	function autocompletaCta7(idiv,itex) {  
		//alert("autocompletaCta"+idiv+" - "+itex);
		//Picture1.MousePointer = 99
		var texto = document.getElementById("RCC"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias3(articulos1,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias3(articulos1, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo2(articulos1, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("RCC"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista3(articulos1);
	                        return;
	                    }
	                    if(cachesugerencias1[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                    	      if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo2(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos1);
	                                                    cachesugerencias1[texto] = articulos1;
	                                                    actualizasugerencias3(articulos1,idiv,itex);
	                                                }
	                                           //   seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                    var opc=3;
	                                peticion1.open("POST","ControlReportes?valor=autoinv1&texto="+texto+"&opc="+opc,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias1[texto];
	                        actualizasugerencias3(articulos1,idiv,itex);
	                       //   seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	function autocompletaCta8(idiv,itex) {  
		//alert("autocompletaCta"+idiv+" - "+itex);
		//Picture1.MousePointer = 99
		var texto = document.getElementById("cta"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias2(articulos1,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias2(articulos1, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo1(articulos1, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("cta"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista2(articulos1);
	                        return;
	                    }
	                    if(cachesugerencias5[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                    	      if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo1(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos1);
	                                                    cachesugerencias5[texto] = articulos1;
	                                                    actualizasugerencias2(articulos1,idiv,itex);
	                                                }
	                                           //   seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                 
	                                
	                                   
	                                    var opc=4;
		                                peticion1.open("POST","ControlReportes?valor=autoinv1&texto="+texto+"&opc="+opc,true);                                
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias5[texto];
	                        actualizasugerencias2(articulos1,idiv,itex);
	                       //   seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	function autocompletaCta9(idiv,itex) {  
		//alert("autocompletaCta"+idiv+" - "+itex);
		//Picture1.MousePointer = 99
		var texto = document.getElementById("RCC"+itex).value;
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias3(articulos1,idiv,itex);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias3(articulos1, idiv, itex);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo2(articulos1, idiv, itex);
	                	
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    texto = document.getElementById("RCC"+itex).value;
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista3(articulos1);
	                        return;
	                    }
	                    if(cachesugerencias5[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                    	      if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {	                                        	
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo2(idiv);
	                                                   
	                                                   
	                                                }else {
	                                                	//alert(articulos1);
	                                                    cachesugerencias5[texto] = articulos1;
	                                                    actualizasugerencias3(articulos1,idiv,itex);
	                                                }
	                                           //   seleccionAutomatica(idiv,itex);
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                   
	                                    var opc=4;
		                                peticion1.open("POST","ControlReportes?valor=autoinv1&texto="+texto+"&opc="+opc,true);                           
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias5[texto];
	                        actualizasugerencias3(articulos1,idiv,itex);
	                       //   seleccionAutomatica(idiv,itex);
	                    }
	                }
		}
	}
	
	function muestrasugerencias2(articulos1,idiv,itex) {
	    var zonasugerencias1 = document.getElementById("dcta"+idiv);
	    var mostrar={};
		y=articulos1.length;
		var kittys=null;
		for(x=0; x<y-1; x=x+1){
		  kittys=articulos1[x];
		  z=kittys.split("|");
		  mostrar[x]=(z[1]+" - "+z[2]); 
		 }
        zonasugerencias1.innerHTML = articulos1.formateaLista2(idiv,itex,mostrar);
        zonasugerencias1.style.display = 'block';	
}
	
	
	function muestrasugerencias3(articulos1,idiv,itex) {
	    var zonasugerencias1 = document.getElementById("dcta"+idiv);
	    var mostrar={};
		y=articulos1.length;
		var kittys=null;
		for(x=0; x<y-1; x=x+1){
		  kittys=articulos1[x];
		  z=kittys.split("|");
		  mostrar[x]=(z[1]+" - "+z[2]); 
		 }
        zonasugerencias1.innerHTML = articulos1.formateaLista3(idiv,itex,mostrar);
        zonasugerencias1.style.display = 'block';	
}
	
	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista2 = function(idiv,itex,mostrar) {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'<\/li>';
	            }else {
	                codigoHtml += '<li onclick="confirmarArticulo1(this.innerHTML,'+itex+','+idiv+','+i+')">'+mostrar[i]+'<\/li>';
	            }
	        }
	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	
	Array.prototype.formateaLista3 = function(idiv,itex,mostrar) {
	    var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionado) {
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
	function seleccionaElementoArticulo1(articulos1,idiv, itex) {
	    if(articulos1[elementoSeleccionado]) {
	    	var kitty=articulos1[elementoSeleccionado];
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
		    
		    document.getElementById("ctah"+itex).value = codigo;
		   // alert(itex);
			document.getElementById("cta"+itex).value = nombre;
			//cachesugerencias1 = "";
			document.getElementById("RCC"+itex).focus();
			//Detalleh(venc,lot);
	
    	    borraLista2(idiv);
    	    limpcta(idiv);
    	    selPaciente = true;
	    }
	}
	
	
	
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoArticulo2(articulos1,idiv, itex) {
	    if(articulos1[elementoSeleccionado]) {
	    	var kitty=articulos1[elementoSeleccionado];
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
		    
		    document.getElementById("ctahh"+itex).value = codigo;
		    //alert(itex);
			document.getElementById("RCC"+itex).value = nombre;
			//cachesugerencias1 = "";
			document.getElementById("Bot"+itex).focus();
			//Detalleh(venc,lot);
	
    	    borraLista3(idiv);

    	    selPaciente = true;
	    }
	}
	
	
	function borraLista2(idiv) {
        document.getElementById("dcta"+idiv).innerHTML = "";
        document.getElementById("dcta"+idiv).style.display = "none";
	}
	
	function borraLista3(idiv) {
        document.getElementById("dcta"+idiv).innerHTML = "";
        document.getElementById("dcta"+idiv).style.display = "none";
	}
	
	function actualizasugerencias2(articulos1,idiv,itex) {
		elementoSeleccionado = -1;
		muestrasugerencias2(articulos1,idiv,itex);
	}
	
	function actualizasugerencias3(articulos1,idiv,itex) {
		elementoSeleccionado = -1;
		muestrasugerencias3(articulos1,idiv,itex);
	}
	
	function sinResultadosArticulo1(idiv) {
		var x="dcta"+idiv;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}
	
	function sinResultadosArticulo2(idiv) {
		var x="dcta"+idiv;
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}
	
	
	function confirmarArticulo1(cf,itex,idiv){		
		  
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
		  
		document.getElementById("ctah"+itex).value = codigo;
		document.getElementById("cta"+itex).value = nombre;
		cachesugerencias1 = "";
		document.getElementById("RCC"+itex).focus();
		
		//Detalleh(venc,lot);
		borraLista2(idiv);
	}
	
	function confirmarArticulo2(cf,itex,idiv){		
		  
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
		  
		document.getElementById("ctahh"+itex).value = codigo;
		document.getElementById("RCC"+itex).value = nombre;
		cachesugerencias5 = "";
		document.getElementById("Bot"+itex).focus();
		
		//Detalleh(venc,lot);
		borraLista3(idiv);
	}
	
	
	function limpcta(idiv) {//Limpia el div de cta si no la selecciona
		 document.getElementById("dcta"+idiv).innerHTML = "";
	     document.getElementById("dcta"+idiv).style.display = "none";
	}
	
	//////////TEMINA AUTOCOMPLETAR PARA LOS BALANCES GENERALES///////
	
	
	
	
	
	