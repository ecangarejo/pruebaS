var peticion1 = null;
var elementoSeleccionado = -1;
var articulos1 = null;
var cachesugerencias1 = {};
var selPaciente = false;
var selPac = false;
var para=0;
//var movnf=0;
/*función utilizada para realizar la petición asincrona al servidor(AJAX)*/
	function inicializa_xhr() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	/*función para autocompletar cunado se digite el codigo del presupuesto*/
	function autocompletarCuentas(idiv,cs,icg,ppa) {   
		
		if(ppa!=undefined){para=ppa;}

		
	//	movnf=movnfn;
			//Picture1.MousePointer = 99
		var texto="";
		if(cs=="1"){ texto = document.getElementById("cta"+idiv).value;}
		if(cs=="2"){ texto = document.getElementById("cta"+idiv).value;}
		if(cs=="3"){ texto = document.getElementById("ctag"+idiv).value;}
		if(cs=="4"){
			if(icg=="1"){texto = document.getElementById("cta"+idiv).value;}
			if(icg=="2"){texto = document.getElementById("ctac"+idiv).value;}
			if(icg=="3"){texto = document.getElementById("ctag"+idiv).value;}
		}
		
		
	//alert("identificador "+cs+" idiv "+idiv+" texto "+texto+" icg "+icg);
		
		if(texto!=""){
		var elEvento =  window.event;  //arguments[0] ||
	    var tecla = elEvento.keyCode;
	        if(tecla == 40) { // Flecha Abajo
	        	//alert("presiono abajo");
	               if(elementoSeleccionado+1 < articulos1.length) {
	                    elementoSeleccionado++;
	                }
	            muestrasugerencias1(articulos1,idiv,cs,icg);
	        }else 
	            if(tecla == 38) { // Flecha Arriba
	                //alert("presiono arriba");
	                    if(elementoSeleccionado > 0) {
	                        elementoSeleccionado--;
	                    }
	                muestrasugerencias1(articulos1, idiv,cs,icg);
	            }else
	                if(tecla == 13) { // ENTER o Intro
	                	if(selPaciente){
	                		selPac = true;
	                	}
	                    seleccionaElementoArticulo(articulos1, idiv, cs,icg);
	                	//clicksugerencia(choice);
	                }else {
	                	//var texto = txtTipoMe;
	                	//alert("txtTipoMe"+txtTipoMe);
	                    //texto = document.getElementById("cta"+idiv).value;
	                    if(cs=="1"){ texto = document.getElementById("cta"+idiv).value;}
	            		if(cs=="2"){ texto = document.getElementById("cta"+idiv).value;}
	            		if(cs=="3"){ texto = document.getElementById("ctag"+idiv).value;}
	            		if(cs=="4"){
	            			if(icg=="1"){texto = document.getElementById("cta"+idiv).value;}
	            			if(icg=="2"){texto = document.getElementById("ctac"+idiv).value;}
	            			if(icg=="3"){texto = document.getElementById("ctag"+idiv).value;}
	            		}
	                    // Si es la tecla de borrado y el texto es vacío, ocultar la lista
	                    if(tecla == 8 && texto == "") {
	                    	selPaciente = false;
	                        borraLista1(articulos1,cs);
	                        return;
	                    }
	                    if(cachesugerencias1[texto] == null) {
	                    	
	                        peticion1 = inicializa_xhr();
	                        peticion1.onreadystatechange = function() {
	                        	
	                                    if(peticion1.readyState == 4) {	                                    	
	                                        if(peticion1.status == 200) {
	                                        	//alert("Cuentas "+articulos1);
	                                        	 articulos1 = eval('('+peticion1.responseText+')');
	                                                if(articulos1.length == 0) {
	                                                    sinResultadosArticulo(idiv,cs,icg);
	                                                   
	                                                   
	                                                }else {
	                                                    cachesugerencias1[texto] = articulos1;
	                                                    actualizasugerencias11(articulos1,idiv,cs,icg);
	                                                }
	                                            }
	                                        }
	                                    };
	                                //var ee="epsh0";
	                               // var xx=document.getElementById(ee).value;  
	                               // alert("mbase: "+xx);
	                                peticion1.open("POST","ControlNomina?valor=autoinv2&texto="+texto,true);                                 
	     	                        peticion1.send("");
	                    } else {
	                        articulos1 = cachesugerencias1[texto];
	                        actualizasugerencias11(articulos1,idiv,cs,icg);
	                    }
	                }
		}
	}
	
	function sinResultadosArticulo(idiv,cs,icg) {
		var x="";
		if(cs=="1"){ x="Cuentas"+idiv;}
		if(cs=="2"){ x="Cuentas"+idiv;}
		if(cs=="3"){ x="CuentasG"+idiv;}
		if(cs=="4"){ 
			if(icg=="1"){ x="Cuentas"+idiv;}
			if(icg=="2"){ x="Cuentasc"+idiv;}
			if(icg=="3"){ x="Cuentasg"+idiv;}
		}
		
	    document.getElementById(x).innerHTML = "No existe registro";
	    document.getElementById(x).style.display = "block";
	}

	function actualizasugerencias11(articulos1,idiv,cs,icg) {
		elementoSeleccionado = -1;
		muestrasugerencias1(articulos1,idiv,cs,icg);
	}

	/*función que muestra las sugerencias1 de codigos de referencia de presupuestos*/
	function muestrasugerencias1(articulos1,idiv,cs,icg) {
		 var zonasugerencias1="";
		if(cs=="1"){  zonasugerencias1 = document.getElementById("Cuentas"+idiv);}
		if(cs=="2"){  zonasugerencias1 = document.getElementById("Cuentas"+idiv);}
		if(cs=="3"){  zonasugerencias1 = document.getElementById("CuentasG"+idiv);}
		if(cs=="4"){  
			if(icg=="1"){ zonasugerencias1 = document.getElementById("Cuentas"+idiv);}
			if(icg=="2"){ zonasugerencias1 = document.getElementById("Cuentasc"+idiv);}
			if(icg=="3"){ zonasugerencias1 = document.getElementById("Cuentasg"+idiv);}
			
		}
				
		    var mostrar={};
			y=articulos1.length;
			var kittys=null;
			for(x=0; x<y-1; x=x+1){
			  kittys=articulos1[x];
			  z=kittys.split("|");
			  mostrar[x]=z[1]+" - "+z[2]; //&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;       
			 }
	        zonasugerencias1.innerHTML = articulos1.formateaLista1(idiv,mostrar,cs,icg);
	        zonasugerencias1.style.display = 'block';	
	}

	/*prototypo de la lista quesale en el div de sugerencias1*/
	Array.prototype.formateaLista1 = function(idiv,mostrar,cs,icg) {
		var codigoHtml = "";
	    codigoHtml = "<ul>";	    
	        for(var i=0; i<this.length; i++) {
	            if(i == elementoSeleccionado) {
	                codigoHtml += '<li class= \"seleccionado\">'+mostrar[i]+'<\/li>';
	            }else {
	                codigoHtml += "<li onclick='confirmarArticulo(this.innerHTML,"+idiv+","+i+",&quot;"+articulos1[i]+"&quot;,"+cs+","+icg+")'>"+mostrar[i]+"<\/li>";
	            }
	        }
	    codigoHtml += "<\/ul>";
	    //div = document.getElementById('sugerencias1');
		//div.style.display='none';
	    return codigoHtml;
	};
	
	/*función en el cual se le asigna a la caja de texto la opción selccionada de las sugerencias1*/
	function seleccionaElementoArticulo(articulos1,idiv,cs,icg) {
		//alert(elementoSeleccionado);
	    if(articulos1[elementoSeleccionado]) {
	    	var kitty=articulos1[elementoSeleccionado];
	    	//alert("kitty: "+kitty);
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
		    	lot=z[2];
		    }
		    
		   
		     if(para==0){
		    var w=document.getElementById("hsub"+idiv).value
			
		   
		    
		    if(cs=="1"){
		      document.getElementById("cta"+idiv).value = nombre;
		      document.getElementById("ncta"+idiv).value = lot;
		      creainterfazdenomina(w,codigo);
		    }
		    
		    if(cs=="2"){
			  document.getElementById("cta"+idiv).value = nombre;
			  document.getElementById("ncta"+idiv).value = lot;
			  creainterfazdeprovisiones(w,codigo,"2");
			}
		    
		    if(cs=="3"){
			  document.getElementById("ctag"+idiv).value = nombre;
			  document.getElementById("nctag"+idiv).value = lot;
			  creainterfazdeprovisiones(w,codigo,"3");
			}
		    
		    if(cs=="4"){
		    	if(icg=="1"){
		    		document.getElementById("cta"+idiv).value = nombre;
		    		creainterfazdefacturacion(w,codigo,"1");
		    	}
		    	if(icg=="2"){
		    		document.getElementById("ctac"+idiv).value = nombre;
		    		creainterfazdefacturacion(w,codigo,"2");
		    	}
		    	if(icg=="3"){
		    		document.getElementById("ctag"+idiv).value = nombre;
		    		creainterfazdefacturacion(w,codigo,"3");
		    	}
			 }
		    
		    }else{
		    	document.getElementById("cta"+idiv).value = nombre;
			    document.getElementById("ncta"+idiv).value = lot;
			    document.getElementById("hcta"+idiv).value = codigo;
			    //creainterfazdenomina(w,codigo);
		    }

    	    borraLista1(idiv,cs,icg);
		    selPaciente = true;
	    }
	}
	
	function borraLista1(idiv,cs,icg) {
		
		if(cs=="1"){  document.getElementById("Cuentas"+idiv).innerHTML = ""; document.getElementById("Cuentas"+idiv).style.display = "none";}
		if(cs=="2"){  document.getElementById("Cuentas"+idiv).innerHTML = ""; document.getElementById("Cuentas"+idiv).style.display = "none";}
		if(cs=="3"){  document.getElementById("CuentasG"+idiv).innerHTML = ""; document.getElementById("CuentasG"+idiv).style.display = "none";}
		if(cs=="4"){ 
			if(icg=="1"){ document.getElementById("Cuentas"+idiv).innerHTML = ""; document.getElementById("Cuentas"+idiv).style.display = "none"; }
			if(icg=="2"){ document.getElementById("Cuentasc"+idiv).innerHTML = ""; document.getElementById("Cuentasc"+idiv).style.display = "none"; }
			if(icg=="3"){ document.getElementById("Cuentasg"+idiv).innerHTML = ""; document.getElementById("Cuentasg"+idiv).style.display = "none"; }
		}
	}

function confirmarArticulo(cf,idiv,r,rf,cs,icg){	

		 var codigo;
		 var nombre;
		 var lot;
		 var inv;
		 var venc;
		 var ca;
		
		 var y=rf.split("|").length;
		 var z=rf.split("|");		     	
		 for(x=0; x<y-1; x=x+1)
		 { 
			 codigo=z[0];
			 nombre=z[1];
			 lot=z[2];
		 }
		
		 
		
		  if(para==0){
			 
		 var w=document.getElementById("hsub"+idiv).value
			  
		
		 
		 if(cs=="1"){
		   document.getElementById("cta"+idiv).value = nombre;
		   document.getElementById("ncta"+idiv).value = lot;
		   creainterfazdenomina(w,codigo);
		 }
		 
		 if(cs=="2"){
		   document.getElementById("cta"+idiv).value = nombre;
		   document.getElementById("ncta"+idiv).value = lot;
		   creainterfazdeprovisiones(w,codigo,"2");
		 } 
		 
		 if(cs=="3"){
		   document.getElementById("ctag"+idiv).value = nombre;
		   document.getElementById("nctag"+idiv).value = lot;
		   creainterfazdeprovisiones(w,codigo,"3");
		 }
		    
		 if(cs=="4"){
			 	if(icg=="1"){
		    		document.getElementById("cta"+idiv).value = nombre;
		    		creainterfazdefacturacion(w,codigo,"1");
		    	}
		    	if(icg=="2"){
		    		document.getElementById("ctac"+idiv).value = nombre;
		    		creainterfazdefacturacion(w,codigo,"2");
		    	}
		    	if(icg=="3"){
		    		document.getElementById("ctag"+idiv).value = nombre;
		    		creainterfazdefacturacion(w,codigo,"3");
		    	}
		 }
		 
	}else{
	document.getElementById("cta"+idiv).value = nombre;
    document.getElementById("ncta"+idiv).value = lot;
    document.getElementById("hcta"+idiv).value = codigo;
    //creainterfazdenomina(w,codigo);
	}
		 
	borraLista1(idiv,cs,icg);
}	
	

