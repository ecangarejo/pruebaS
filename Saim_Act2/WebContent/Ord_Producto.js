function getXMLObject()  //XML OBJECT
{
   var xmlHttp = false;
   try {
     xmlHttp = new ActiveXObject("Msxml2.XMLHTTP")  // For Old Microsoft Browsers
   }
   catch (e) {
     try {
       xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")  // For Microsoft IE 6.0+
     }
     catch (e2) {
       xmlHttp = false   // No Browser accepts the XMLHTTP Object then false
     }
   }
   if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
     xmlHttp = new XMLHttpRequest();        //For Mozilla, Opera Browsers
   }
   return xmlHttp;  // Mandatory Statement returning the ajax object created
}
 
var xmlhttp = new getXMLObject(); //xmlhttp holds the ajax object
///////////////////////////////////////////////////////////////////////////////////////


function SaveG(user){
	ajax=getXMLObject();
	var nameg=document.getElementById('nameg').value;
	var estg=document.getElementById('estg').value;
	var contenido=document.getElementById("grupos");
	//alert("valor=GuardarG&nameg="+nameg+"&estg="+estg+"&user="+user);
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var iden=ajax.responseText;
			if(iden==2){
			
				alert("GRUPO CREADO EXITOSAMENTE, RECUERDE COMUNICARSE CON EL DPTO DE CONTABILIDAD PARA QUE SEA ASIGANADA UNA CUENTA AUXILIAR")
				window.location.reload();
				//alert(ajax.responseText);
			}else{
				if(iden==1){
			    alert("YA EXISTE UN GRUPO CON ESTE NOMBRE ");
			    //alert(ajax.responseText);
				}else{
				 alert("HA OCURRIDO UN ERROR Y NO SE CREO EL GRUPO");
				}
			}
				
			//alert(ajax.responseText);
			//contenido.innerHTML=ajax.responseText;
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarG&nameg="+nameg+"&estg="+estg+"&user="+user);
}

function Grupos(){
	//alert();
	ajax=getXMLObject();
	var contenido=document.getElementById("grupos");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MostrarG");
}


function SaveTP(){
	ajax=getXMLObject();
	var nameTP=document.getElementById('nameTP').value;
	var user=document.getElementById('user').value;
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var iden=ajax.responseText;
			if(iden==2){
			    alert("TIPO DE PRODUCTO CREADO EXITOSAMENTE");
			    window.location.reload();
			}else{
				if(iden==1){
				  alert("YA EXISTE UN TIPO DE PRODUCTO CREADO CON ESTE NOMBRE")
				}else{
					alert("HA OCURRIDO UN ERROR Y NO SE GUARDO EL TIPO DE PRODUCTO ");
				
				}
			}
		//alert(ajax.reponseText);
		window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuardarTP&nameTP="+nameTP+"&user="+user);
	
}


function Tipos(){
	//alert();
	ajax=getXMLObject();
	var contenido=document.getElementById("grupos");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MostrarT");
}

function SaveP(){
	ajax=getXMLObject();
	var nameP=document.getElementById('namep').value;
	var codref=document.getElementById('codref').value;
	var user=document.getElementById('user').value;
	var tipop=document.getElementById('tipop').value;
	//alert("valor=GuardarP&nameP="+nameP+"&codref="+codref+"&user"+user+"&tprod="+tipop);
	if(tipop=='---'){
		alert("DEBE SELECCIONAR UN TIPO DE PRODUCTO ");
	}else{
		if((tipop==1)||(tipop==2)){//la tabla ord_tipoproducto los dos primeros registro siempre deben ser igual a los de farc_grupo
			 alert(" NO PUEDE CREAR PRODUCTO DE TIPO MEDICAMENTO O INSUMOS MEDICOS, ESTO DEBE SER CREADO POR EL AREA DE SERVICIO FARMACEUTICO");
		}else{
			ajax.open("POST","ControlProductos",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					var id=ajax.responseText;
					if(id==2){
						alert("PRODUCTO CREADO EXITOSAMENTE, RECUERDE ASOCIARLE UN GRUPO Y PROVEEDOR ");
						window.location.reload();
					}else{
						if(id==1){
							
							alert("YA EXISTE UN PRODUCTO CON ESTE NOMBRE");
						}else{
						  alert("HA SUCEDIDO UN ERROR, NO SE LOGRO GUARDAR EXITOSAMENTE");
						}
					}
					
					window.location.reload();
				
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=GuardarP&nameP="+nameP+"&codref="+codref+"&user="+user+"&tprod="+tipop);
		}
	}
}



function Radio() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('asignaciones');
	var user=document.getElementById('user').value;
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlProductos",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&user"+user); //Posting txtname to Servlet
		}	     
	}
}


function autocompletaProd(ident,cprovee){
	ajax=getXMLObject();
	var texto=document.getElementById("prod").value;
	var contenido=document.getElementById("vp");
	
	//contenido.innerHTML="PROBANDO !!!";
	//alert("prod "+texto+" ident "+ident+" cprovee"+cprovee);
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AutoProd&texto="+texto+"&ident="+ident+"&cprovee="+cprovee);
}

function Producto(codprod){
	ajax=getXMLObject();
	//alert("entrando");
	document.getElementById("vp").innerHTML="";
	document.getElementById("codpro").value=codprod;
	//var nompro=document.getElementById("nprod").value;
	
	//var tp=document.getElementById("tiprod").value;
	/*var codref=document.getElementById("codref").value;
	document.getElementById("prod").value=nompro;
	var producto=nompro+" - "+codref+" - "+tp;
	document.getElementById("desc").value=producto;
	document.getElementById("asig").focus();*/
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			var cadena=ajax.responseText;
			var elem=cadena.split('_');
			//alert(Rangos);
			var cp=elem[0];
			var cref=elem[1];
			var np=elem[2];
			var tp=elem[3];
			//alert("CADENA DISCRIMINADA "+cp+"-"+cref+"-"+np+"-"+tp);
			var producto=np+" | "+cref+" | "+tp;
			document.getElementById("desc").innerHTML=producto;
			document.getElementById("prod").value=np;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=visual&codprod="+codprod);
}


function GAsignacion(){
	ajax=getXMLObject();
	var opc=confirm("¿Esta seguro que desea asignar este grupo al producto seleccionado ?");
	if(opc){
		var codprod=document.getElementById("codpro").value;
		var codgrupo=document.getElementById("bgrupo").value;
		var user=document.getElementById("user").value;
		if(codprod==""){
		 alert("NO HA SELECCIONADO UN PRODUCTO");
		}else{
			if(codgrupo=="---"){
				alert("NO SELECCIONADO UN GRUPO");
			}else{
				
				ajax.open("POST","ControlProductos",true);
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
						var id=ajax.responseText;
						if(id==2){
							alert("GRUPO ASIGNADO CORRECTAMENTE");
							window.location.reload();
						}else{
							if(id==1){
							 alert("ESTE PRODUCTO YA TIENE ASIGANDO UN GRUPO ");
							}else{
							 alert("HA OCURRIDO UN ERROR ");
							}
						}
					}
				}
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=SaveAsigG&codprod="+codprod+"&codgrupo="+codgrupo+"&user="+user);
			}
	  }
	}else{
		
	}
	
}

function autocompletaProvee(){
	ajax=getXMLObject();
	//alert("entrando a autocompleta proveedor");
	var texto=document.getElementById("provee").value;
	var contenido=document.getElementById("vistapro");
	//contenido.innerHTML="PROBANDO !!!";
	//alert("prod "+texto);
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AutoProvee&texto="+texto);
	
}


function Proveedor(codprove){
	ajax=getXMLObject();
	//alert("entrando "+codprove);
	document.getElementById("vistapro").innerHTML="";
	//document.getElementById("codprov").value=codprove;
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			var cadena=ajax.responseText;
			var elem=cadena.split('_');
			//alert(Rangos);
			var cprove=elem[0];
			var nit=elem[1];
			var nprove=elem[2];
		
			//alert("CADENA DISCRIMINADA "+cp+"-"+cref+"-"+np+"-"+tp);
			var provee=nit+" | "+nprove;
			document.getElementById("dpro").innerHTML=provee;
			document.getElementById("cprove").value=cprove;
			document.getElementById("provee").value=nprove;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=visualprove&codprove="+codprove);
}

function GAsigProvee(){
	
	ajax=getXMLObject();
	var opc=confirm("¿Esta seguro que desea asignar el producto seleccionado al proveedor escogido ?");
	if(opc){
		var cprove=document.getElementById("cprove").value;
		var cproducto=document.getElementById("codpro").value;
		var user=document.getElementById("user").value;
		if(cproducto==""){
		 alert("NO HA SELECCIONADO UN PRODUCTO");
		}else{
			if(cprove==""){
				alert("NO SELECCIONADO UN PROVEEDOR");
			}else{
				ajax.open("POST","ControlProductos",true);
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
						var id=ajax.responseText;
						if(id==2){
							alert("ASIGNACION REALIZADA SATISFACTORIAMENTE");
							window.location.reload();
						}else{
							if(id==1){
							 alert("ESTE PRODUCTO YA ESTA ASIGNADO A ESTE PROVEEDOR");
							}else{
							 alert("HA OCURRIDO UN ERROR ");
							}
						}
					}
				}
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=SaveAsigProve&cprove="+cprove+"&cproducto="+cproducto+"&user="+user);
			}
	  }
	}else{
		
	}
	
}

function RadioTarifa(){
	ajax=getXMLObject();
	var contenido=document.getElementById('vistradios');
	var provee=document.getElementById('lprove').value;
	var user=document.getElementById('user').value;
			ajax.open("POST","ControlProductos",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=RadioTarifa&user="+user+"&provee="+provee); //Posting txtname to Servlet
			     
	
}


function RadioT(provee) {	
	ajax=getXMLObject();
	var contenido=document.getElementById('vistproduc');
	var user=document.getElementById('user').value;
	var radioButtons = document.getElementsByName("radiobuttonu");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlProductos",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
				}
			}
			//alert("valor="+val+"&user"+user+"&cprove="+provee);
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&user"+user+"&cprove="+provee); //Posting txtname to Servlet
		}	     
	}
}

function lproduc(){
	ajax=getXMLObject();
	var cprove=document.getElementById("lprove").value;
	var contenido=document.getElementById("vistproduc");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Lprod&cprove="+cprove);
}

function AsigTarifa(cprove,cprodu,i){
	ajax=getXMLObject();
	var user=document.getElementById("user").value;
	var tarif=document.getElementById("tarifa"+i).value;
	var civa=document.getElementById("codiva"+i).value;
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Asignacion Exitosa !");
			lpro(cprove);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=SaveTarifa&cprove="+cprove+"&cprodu="+cprodu+"&user="+user+"&tarif="+tarif+"&civa="+civa);
}

function lpro(cprove){
	ajax=getXMLObject();
	var contenido=document.getElementById("vistproduc");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Lprod&cprove="+cprove);
}

function AsT(cprove){
	ajax=getXMLObject();
	//alert("entrando ast");
	var tarif=document.getElementById("tarifa").value;
	var cprodu=document.getElementById("codpro").value;
	var user=document.getElementById("user").value;
	var civa=document.getElementById("codiva").value;
	if(tarif==""){
			alert("NO HA DIGITADO UN TARIFA ");
	}else{
		if(cprodu==""){
			alert("NO HA ESCOGIDO UN PRODUCTO ");
		}else{
			
			ajax.open("POST","ControlProductos",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert("Asignacion Exitosa !");
					lasigt(cprove);
					
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=SaveTProd&cprove="+cprove+"&cprodu="+cprodu+"&user="+user+"&tarif="+tarif+"&civa="+civa);
		}
	}
}


function lasigt(cprove){
	ajax=getXMLObject();
	var contenido=document.getElementById("vistproduc");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=PTarifar&cprove="+cprove);
	
}
function checknu(){
	ajax=getXMLObject();
	var num=document.getElementById("tarifa").value;
	if((num==0)||((num>0)&&(num<999999999999999999999999999999999999999999))){
	
	}else{
	   alert("Este campo solo admite digitos numericos ");
	   document.getElementById("tarifa").value="";
	}
	document.getElementById("desc").focus();
	
}

function ElimTarifa(cprove,cprodu,codtarifa){
	
	ajax=getXMLObject();
	var user=document.getElementById("user").value;
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		  alert("Eliminacion Exitosa !! ");
		  VTarifas(cprove);
		}
	}
	
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ElimTarifa&cprove="+cprove+"&cprodu="+cprodu+"&user="+user+"&codtarifa="+codtarifa);
}

function VTarifas(cprove){
	ajax=getXMLObject();
	var contenido=document.getElementById("vistproduc");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ProductosT&cprove="+cprove);
}

/*  ORDEN DE COMPRA */
function AutoProveOrden(){
	ajax=getXMLObject();
	var texto=document.getElementById("proveorden").value;
	var cont=document.getElementById("vporden");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BusquedaProve&texto="+texto);
	
}

function ProvOrd(codprove){
	
	var cont=document.getElementById("ord");
	var user=document.getElementById("user").value;
	
	document.getElementById("ord").innerHTML="";
	
	ajax1=getXMLObject();
	ajax1.open("POST","ControlProductos",true);
	ajax1.onreadystatechange = function(){
		if(ajax1.readyState == 4){
			var cadena=ajax1.responseText;
			var elem=cadena.split('_');
			//alert(Rangos);
			var cpv=elem[0];
			var nomp=elem[1];
			document.getElementById("vporden").innerHTML="";
			document.getElementById("proveorden").value=nomp;
			document.getElementById("codprovo").value=cpv;
			
		}
	}
	ajax1.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax1.send("valor=O&cod="+codprove);
	
	ajax3=getXMLObject();
	ajax3.open("POST","ControlProductos",true);
	ajax3.onreadystatechange = function(){
		if(ajax3.readyState == 4){
		  var vali=ajax3.responseText;
		  //alert(vali);
			  if(vali==1){
				  var opc=confirm("Tiene Ordenes pendientes por finalizar a este Proveedor. ¿Desea realizar una nueva orden de compra para este proveedor?");
					if(opc){ 
						  	
						ajax4=getXMLObject();
						ajax4.open("POST","ControlProductos",true);
						ajax4.onreadystatechange = function(){
							if(ajax4.readyState == 4){
								cont.innerHTML=ajax4.responseText;
							}
						}
						ajax4.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax4.send("valor=Orden&codprove="+codprove);
							
					}else{
						ajax2=getXMLObject();
						ajax2.open("POST","ControlProductos",true);
						ajax2.onreadystatechange = function(){
							if(ajax2.readyState == 4){
								cont.innerHTML=ajax2.responseText;
							}
						}
						ajax2.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax2.send("valor=OO&cod="+codprove+"&user="+user);
							
					} 
			}else{
					ajax=getXMLObject();
					ajax.open("POST","ControlProductos",true);
					ajax.onreadystatechange = function(){
						if(ajax.readyState == 4){
							cont.innerHTML=ajax.responseText;
						}
					}
					ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=Orden&codprove="+codprove);
			}
	}	
  }
	ajax3.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax3.send("valor=ValiO&cod="+codprove);
}

function VerPreOrden(codprove,codpreorden){
	ajax=getXMLObject();
	var cont=document.getElementById("ord");
	document.getElementById("codigOrden").value=codpreorden;
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OO.1&codprove="+codprove+"&codpreorden="+codpreorden);
}


function BuscarProducto(codprove, iden){
	ajax=getXMLObject();
	//alert("entrando a BuscarProducto");
	var texto="";
		var cont="";
	if(iden==1){
		texto=document.getElementById("codref").value;
		cont=document.getElementById("vcodref");
	}else{
		texto=document.getElementById("descrip").value;
		cont=document.getElementById("vproduct");
	}
	//alert("valor=Bp&codprove="+codprove+"&texto="+texto+"&iden="+iden);
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Bp&codprove="+codprove+"&texto="+texto+"&iden="+iden+"&xx=1");
	
}

function BuscarProduc(codprove, iden){ //Es copia de buscar producto solo que este lo utilizo para modificar porque no se pueden tener los mismo id
	ajax=getXMLObject();
	//alert("entrando a BuscarProduc");
	var texto="";
	var cont="";
	if(iden==1){
		texto=document.getElementById("codreff").value;
		//alert("texto"+texto);
		cont=document.getElementById("vcodreff");
	}else{
		texto=document.getElementById("descripp").value;
		cont=document.getElementById("vproductt");
	}
	//alert("valor=Bp&codprove="+codprove+"&texto="+texto+"&iden="+iden);
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
		cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Bp&codprove="+codprove+"&texto="+texto+"&iden="+iden+"&xx=2");
	
}

function Bprod(codprove,codtarifa,iden,xx){
	ajax=getXMLObject();
	//alert(" codprove"+codprove+" codtarifa "+codtarifa+" iden"+iden+" xx"+xx);
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
	      if(xx==1){
				  var cade=ajax.responseText;
				  // alert(cade);
				   var elem=cade.split('_');
				   var cpro=elem[0];
				   var cref=elem[1];
				   var nprod=elem[2];
				   var ctarif=elem[4];
				   var vtarif=elem[5];
				   var codiva=elem[6];
				   var viva=elem[7];
				   
				   document.getElementById("codref").value=cref;
				   document.getElementById("descrip").value=nprod;
				   document.getElementById("punit").value=vtarif;
				   document.getElementById("iva").value=viva;
				   document.getElementById("codiiva").value=codiva;
				   if(iden==1){
				   document.getElementById("vcodref").innerHTML="";
				   }else{
					   document.getElementById("vproduct").innerHTML="";
				   }
				   document.getElementById("codprodu").value=cpro;
				   document.getElementById("codtarifa").value=ctarif;	
				   document.getElementById("cant").focus();
	      }else{
	      
	    	  var cadee=ajax.responseText;
			  // alert(cadee);
			   var elemm=cadee.split('_');
			   var cproo=elemm[0];
			   var creff=elemm[1];
			   var nprodd=elemm[2];
			   var ctariff=elemm[4];
			   var vtariff=elemm[5];
			   var codivaa=elemm[6];
			   var vivaa=elemm[7];
			   
			   document.getElementById("codreff").value=creff;
			   document.getElementById("descripp").value=nprodd;
			   document.getElementById("punitt").value=vtariff;
			   document.getElementById("ivaa").value=vivaa;
			   document.getElementById("codiivaa").value=codivaa;
			   if(iden==1){
			   document.getElementById("vcodreff").innerHTML="";
			   }else{
				   document.getElementById("vproductt").innerHTML="";
			   }
			   document.getElementById("codproduu").value=cproo;
			   document.getElementById("codtarifaa").value=ctariff;	
			   document.getElementById("cantt").focus();
	    	  
	      }
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Bproduc&codprove="+codprove+"&codtarifa="+codtarifa);
}

function checkiva(iden, i){
	ajax=getXMLObject();
	var texto="";
	var cont="";
	if(iden==1){
		texto=document.getElementById("iv").value;
		cont=document.getElementById("vistiva");
	}else{
	   texto=document.getElementById("iv"+i).value;
	   cont=document.getElementById("vistiva"+i);
	}
	//alert(" checkiva"+texto +"valor de i"+i);
	 //cont=document.getElementById("vistiva"+i).value;
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Vistiva&texto="+texto+"&i="+i+"&iden="+iden);
	
}

function Asiva(valor, ind, cod, i){
	ajax=getXMLObject();

	
	if(ind==1){
		document.getElementById("vistiva").innerHTML="";
		document.getElementById("codiva").value=cod;
		document.getElementById("iv").value=valor;
		document.getElementById("asigt").focus();
	}else{
	 if(ind==2){
		 document.getElementById("vistiva"+i).innerHTML="";
			document.getElementById("codiva"+i).value=cod;
		 document.getElementById("iv"+i).value=valor;
		 document.getElementById("asignt"+i).focus();
	 
	 	}
	}
}


function CalcularTotal(i){
	ajax=getXMLObject();
	var cant=document.getElementById("cant").value;
	var precio=document.getElementById("punit").value;
	var iva=document.getElementById("iva").value;
	var desc=document.getElementById("desc").value;
	var vproducto=cant*precio;
	var valordesc=(vproducto*((desc)/100));
	//alert(valordesc);
	var subtotal=vproducto-valordesc;
	var valoriva="";
	if(iva==0){
	 valoriva=0;
	}else{
		valoriva=(subtotal*((iva)/100));
	}
    var total=subtotal+valoriva;
    document.getElementById("subtotal").value=subtotal;
	document.getElementById("ptotal").value=total;
	if(i==1){
		document.getElementById("desc").focus();
	}else{
	   if(i==2){
		   document.getElementById("iva").focus();
	   }else{
		   if(i==3){
		    document.getElementById("Oa").focus();
		   }
	   }
	}
	
}


function CalcularTotall(i){
	ajax=getXMLObject();
	var cant=document.getElementById("cantt").value;
	var precio=document.getElementById("punitt").value;
	var iva=document.getElementById("ivaa").value;
	var desc=document.getElementById("descc").value;
	var vproducto=cant*precio;
	var valordesc=(vproducto*((desc)/100));
	//alert(valordesc);
	var subtotal=vproducto-valordesc;
	var valoriva="";
	if(iva==0){
	 valoriva=0;
	}else{
		valoriva=(subtotal*((iva)/100));
	}
    var total=subtotal+valoriva;
    document.getElementById("subtotall").value=subtotal;
	document.getElementById("ptotall").value=total;
	if(i==1){
		document.getElementById("descc").focus();
	}else{
	   if(i==2){
		   document.getElementById("ivaa").focus();
	   }else{
		   if(i==3){
		    document.getElementById("Oaa").focus();
		   }
	   }
	}
	
}



function suc(){
	ajax=getXMLObject();
	var suc=document.getElementById("suc").value;
	var conte=document.getElementById("centrocosto");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			conte.innerHTML=ajax.responseText;
			document.getElementById("codsuc").value=suc;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Vistcentrocosto&suc="+suc);
}

function ccosto(suc){
	ajax=getXMLObject();
	var ccosto=document.getElementById("ccosto").value;
	var visual=document.getElementById("succosto");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		 visual.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Vistsubcosto&ccosto="+ccosto+"&suc="+suc);
}

function ccysc(codcosto,suc){
	ajax=getXMLObject();
	var subcc=document.getElementById("subccosto").value;
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			 var codigos=ajax.responseText;
			   var elem=codigos.split('_');
			   var codcentrosubc=elem[0];
			   var codsucycc=elem[1];
		 document.getElementById("codcentrosubc").value=codcentrosubc;
		 document.getElementById("codsucycc").value=codsucycc;
		 document.getElementById("codsuc").value=suc;
		 document.getElementById("codcc").value=codcosto;
		 document.getElementById("codsubc").value=subcc;
		 //alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ccysc&codcosto="+codcosto+"&subcc="+subcc+"&suc="+suc);
}

function BuscarIva(){
	ajax=getXMLObject();
	var texto=document.getElementById("iva").value;
	//alert("BuscarIva "+texto);
	var vistiva=document.getElementById("vistiva");
	ajax.open("POST","ControlProductos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			vistiva.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Biva&texto="+texto);
	
}

function Biv(cod, valor){
	//alert();
	document.getElementById("iva").value=valor;
	document.getElementById("vistiva").innerHTML="";
	document.getElementById("codiiva").value=cod;
	CalcularTotal(3);
	//document.getElementById("Oa").focus();
}

function AsigOrden(codprove){
	ajax=getXMLObject();
	var codre=document.getElementById("codref").value;
	var codref=encodeURIComponent(codre);
	var nombr=document.getElementById("descrip").value;
	var nombre=encodeURIComponent(nombr);
	var codprodu=document.getElementById("codprodu").value;
	var cant=document.getElementById("cant").value;
	var punit=document.getElementById("punit").value;
	var subtotal=document.getElementById("subtotal").value;
	var iva=document.getElementById("iva").value;
	var ptotal=document.getElementById("ptotal").value;
	var codiiva=document.getElementById("codiiva").value;
	var codtarifa=document.getElementById("codtarifa").value;
	var desc=document.getElementById("desc").value;
	var user=document.getElementById("user").value;
	var fentrega=document.getElementById("fentre").value;
	var conce=document.getElementById("concepto").value;
	var concep=encodeURIComponent(conce);
	var suc=document.getElementById("codsuc").value;
	var ccosto=document.getElementById("codcc").value;
	var subcc=document.getElementById("codsubc").value;
	var codsuccosto=document.getElementById("codsucycc").value;
	var codcensubcc=document.getElementById("codcentrosubc").value;
	var contenido=document.getElementById("detalleo");
	var formapago=document.getElementById("fpago").value;
	var codigOrden=document.getElementById("codigOrden").value;
	//alert("codigOrden "+codigOrden);
	var fechaentrega="";
	if(fentrega!=""){
		var elem=fentrega.split('/');
		var dia=elem[0];
		var mes=elem[1];
		var ano=elem[2];
		fechaentrega=ano+"-"+mes+"-"+dia;
	}
	if(codigOrden==""){
				if(fentrega==""){
					alert("Debe digitar una fecha de entrega valida ");
				}else{
				//alert("valor=DetOrden&codprove="+codprove+"&codref="+codref+"&nombre="+nombre+"&codprodu="+codprodu+"&cant="+cant+"&punit="+punit+"&subtotal="+subtotal+"&iva="+iva+"&ptotal="+ptotal+"&codiiva="+codiiva+"&codtarifa="+codtarifa+"&desc="+desc+"&user="+user+"&fechaentrega="+fechaentrega+"&concep="+concep+"&suc="+suc+"&ccosto="+ccosto+"&subcc="+subcc+"&codsuccosto="+codsuccosto+"&codcensubcc="+codcensubcc);
				ajax.open("POST","ControlProductos",true);
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
								  
						 document.getElementById("codref").value="";
						 document.getElementById("descrip").value="";
						 document.getElementById("codprodu").value="";
						 document.getElementById("cant").value="";
						 document.getElementById("punit").value="";
					     document.getElementById("subtotal").value="";
						 document.getElementById("iva").value="0";
					     document.getElementById("ptotal").value="";
					     document.getElementById("codiiva").value="";
					     document.getElementById("codtarifa").value="";
						 document.getElementById("desc").value="0";
						 document.getElementById("vistiva").innerHTML="";
						 var codigOrden=ajax.responseText;
					//	 alert("primer ajax "+codigOrden);
						 document.getElementById("codigOrden").value=codigOrden;
						 if(codigOrden==0){
							 alert("Esta tratando de crear un nueva orden de compra con datos iguales a una ya existente sin emitir. ");
							 window.location.reload();
						 }else{
							 ajax2=getXMLObject();
							  ajax2.open("POST","ControlProductos",true);
								ajax2.onreadystatechange = function(){
									if(ajax2.readyState == 4){
										//alert("segundo ajax"+ajax2.responseText);
										contenido.innerHTML=ajax2.responseText;
										AsignacioncodOrde();
									}
								}
								ajax2.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
								ajax2.send("valor=DetOrdenD&codprove="+codprove+"&codref="+codref+"&nombre="+nombre+"&codprodu="+codprodu+"&cant="+cant+"&punit="+punit+"&subtotal="+subtotal+"&iva="+iva+"&ptotal="+ptotal+"&codiiva="+codiiva+"&codtarifa="+codtarifa+"&desc="+desc+"&user="+user+"&fechaentrega="+fechaentrega+"&concep="+concep+"&suc="+suc+"&ccosto="+ccosto+"&subcc="+subcc+"&codsuccosto="+codsuccosto+"&codcensubcc="+codcensubcc+"&formapago="+formapago+"&codorden="+codigOrden);
						 }
					}
				}
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=DetOrden&codprove="+codprove+"&codref="+codref+"&nombre="+nombre+"&codprodu="+codprodu+"&cant="+cant+"&punit="+punit+"&subtotal="+subtotal+"&iva="+iva+"&ptotal="+ptotal+"&codiiva="+codiiva+"&codtarifa="+codtarifa+"&desc="+desc+"&user="+user+"&fechaentrega="+fechaentrega+"&concep="+concep+"&suc="+suc+"&ccosto="+ccosto+"&subcc="+subcc+"&codsuccosto="+codsuccosto+"&codcensubcc="+codcensubcc+"&formapago="+formapago);
			}//cierre de validacion de fecha de entrega
	}else{
		if(fentrega==""){
			alert("Debe digitar una fecha de entrega valida ");
		}else{
			ajax1=getXMLObject();
			ajax1.open("POST","ControlProductos",true);
			ajax1.onreadystatechange = function(){
				if(ajax1.readyState == 4){
					document.getElementById("codref").value="";
					 document.getElementById("descrip").value="";
					 document.getElementById("codprodu").value="";
					 document.getElementById("cant").value="";
					 document.getElementById("punit").value="";
				     document.getElementById("subtotal").value="";
					 document.getElementById("iva").value="0";
				     document.getElementById("ptotal").value="";
				     document.getElementById("codiiva").value="";
				     document.getElementById("codtarifa").value="";
					 document.getElementById("desc").value="0";
					 document.getElementById("vistiva").innerHTML="";
					 contenido.innerHTML=ajax1.responseText;
					// alert("coigOrden"+codigOrden);
					 document.getElementById("codorden").value=codigOrden;
					 AsignacioncodOrde();
				}
			}
			//alert("valor=DetOrdenD&codprove="+codprove+"&codref="+codref+"&nombre="+nombre+"&codprodu="+codprodu+"&cant="+cant+"&punit="+punit+"&subtotal="+subtotal+"&iva="+iva+"&ptotal="+ptotal+"&codiiva="+codiiva+"&codtarifa="+codtarifa+"&desc="+desc+"&user="+user+"&fechaentrega="+fechaentrega+"&concep="+concep+"&suc="+suc+"&ccosto="+ccosto+"&subcc="+subcc+"&codsuccosto="+codsuccosto+"&codcensubcc="+codcensubcc+"&formapago="+formapago+"&codorden="+codigOrden);
			ajax1.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax1.send("valor=DetOrdenD&codprove="+codprove+"&codref="+codref+"&nombre="+nombre+"&codprodu="+codprodu+"&cant="+cant+"&punit="+punit+"&subtotal="+subtotal+"&iva="+iva+"&ptotal="+ptotal+"&codiiva="+codiiva+"&codtarifa="+codtarifa+"&desc="+desc+"&user="+user+"&fechaentrega="+fechaentrega+"&concep="+concep+"&suc="+suc+"&ccosto="+ccosto+"&subcc="+subcc+"&codsuccosto="+codsuccosto+"&codcensubcc="+codcensubcc+"&formapago="+formapago+"&codorden="+codigOrden);
			//alert(codigOrden);
		}
	}
	
}

function AsignacioncodOrde(){
	var codorden=document.getElementById("codorden").value;
	//alert("codorden "+codorden);
	document.getElementById("codigOrden").value=codorden;
	
}

function RadioDet(ident) {	
	ajax=getXMLObject();
	//alert(ident);
	var contenido=document.getElementById('detalleo');
	var user=document.getElementById('user').value;
	var j=ident;
	
	var codorde=document.getElementById("cdet"+j).value;
	var radioButtons = document.getElementsByName("radiobuttonn");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			//alert("valor es "+val);
			//alert("Radio Det valor="+val+"&user"+user+"&ident="+ident+"&codord="&codorde);
			ajax.open("POST","ControlProductos",true); 
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					if(val=="Elim"){
						alert("Eliminacion Exitosa !");
						var cod=ajax.responseText;
						ActLista(cod);
					}else{
						contenido.innerHTML = ajax.responseText;
					}
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&user"+user+"&ident="+ident+"&codord="+codorde); //Posting txtname to Servlet
		}	     
	}
}

function ActLista(codorden){
	ajax=getXMLObject();
	//alert("ActLista"+codorden);
	var contenido=document.getElementById('detalleo');
	ajax.open("POST","ControlProductos",true); 
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ActLista&codorden="+codorden);
}

function ActDetOrd(coddetord,codord){
	ajax=getXMLObject();
	var codre=document.getElementById("codreff").value;
	var codref=encodeURIComponent(codre);
	var nombr=document.getElementById("descripp").value;
	var nombre=encodeURIComponent(nombr);
	var codprodu=document.getElementById("codproduu").value;
	var cant=document.getElementById("cantt").value;
	var punit=document.getElementById("punitt").value;
	var subtotal=document.getElementById("subtotall").value;
	var iva=document.getElementById("ivaa").value;
	var ptotal=document.getElementById("ptotall").value;
	var codiiva=document.getElementById("codiivaa").value;
	var codtarifa=document.getElementById("codtarifaa").value;
	var desc=document.getElementById("descc").value;
	var user=document.getElementById("user").value;
	var contenido=document.getElementById("detalleo");
	var codigOrden=document.getElementById("codigOrden").value;
	
	ajax.open("POST","ControlProductos",true); 
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			alert("Modificacion Exitosa !");
			alert(codord);
			ActLista(codord);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ActDetOrd&codorden="+codord+"&coddetorden="+coddetord+"&codref="+codref+"&nombre="+nombre+"&codprodu="+codprodu+"&cant="+cant+"&punit="+punit+"&subtotal="+subtotal+"&iva="+iva+"&ptotal="+ptotal+"&codiiva="+codiiva+"&codtarifa="+codtarifa+"&desc="+desc+"&user="+user);
}

function FinalizarOr(codord){
	ajax=getXMLObject();
	alert("Entrando a FinalizarOr");
	var conce=document.getElementById("concepto").value;
	var concep=encodeURIComponent(conce);
	var user=document.getElementById("user").value;
	ajax.open("POST","ControlProductos",true); 
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			alert("Orden Emitida Exitosamente ! ");
			pagina="ord_ReporteOrdenCompra.jsp?codigo="+codord;			
		   	var opciones="toolbar=no, location=no, directories=no, status=yes, menubar=yes,";
		 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
		   	window.open(pagina,"NUEVA",opciones);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=EmitOrd&codord="+codord+"&concep="+concep+"&user="+user);
	
}



var fechafilter=/^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function checkfecha(c){
	var cc="fentre";
	var e=document.getElementById(cc).value;
	var returnval=fechafilter.test(e)
	if (returnval==false){
		alert("La fecha es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}


var patron = new Array(2,2,4);
function masca(d,pat,nums,dias,mes,annio){
var sep="/";
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if((ini>31)||(ini=="00")){
		alert("Dia No Valido!!!");
		val=d.value="";
		d.focus();
	}
	val = val.split(sep);

	val2 = ''
	for(r=0;r<val.length;r++){
		val2 += val[r]
	}
	ini=val2.substring(2,4);
	if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
		x=val2.substring(0,2);
		if(x=="31"){
			val2="30";
			val2=val2+ini;
		}
	}
	if((ini>12)||(ini=="00")){
		alert("Mes No Valido!!!");
		val2=d.value="";
		d.focus();
	}
	
	ano=val2.substring(4,8);
	dia=val2.substring(0,2);
	if(ini=="02"){
		if((dia=="30")||(dia=="31")||(dia=="29")){
		if(ano.length==4){
			if ( ( ano % 100 != 0) && ((ano % 4 == 0) || (ano % 400 == 0))) {
				val2="29";
				val2=val2+ini;
			}else{
				val2="28";
				val2=val2+ini;
			}
			val2=val2+ano;
		}
	  }
	}
	///////////////////////////////////////////////////
	//Validar fecha mayor que la fecha actual
		
	if(ano.length==4){
		if (ano==annio) {
			if(ini==mes){
				if((dia==dias)||(dia<dias)){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
				val2='';
				}
			}else{
				if(ini<mes){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
					val2='';
				}
			}
		}
		if (ano<annio) {
			alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
			val2='';
		}
		
		document.getElementById("concepto").focus();
	}
	
	///////////////////////////////////////////////////
	
	if(nums){
		for(z=0;z<val2.length;z++){
			if(isNaN(val2.charAt(z))){
				letra = new RegExp(val2.charAt(z),"g")
				val2 = val2.replace(letra,"")
				
			}
		}
	}
		
	val = ''
	val3 = new Array()
	for(s=0; s<pat.length; s++){
 		val3[s] = val2.substring(0,pat[s])
 		val2 = val2.substring(pat[s])
	}
	
	for(q=0;q<val3.length; q++){
		if(q ==0){
			val = val3[q]         
		}
		else{
			if(val3[q] != ""){
				val += sep + val3[q]
			}
		}
	}
	d.value = val
	d.valant = val
	}
}







	
