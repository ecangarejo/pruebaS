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
//////////////////////////////////////////////////////////////////////////////////////////////////////////
function GuardarLaboratorio(){
	var txtNombreLaboratorio=document.getElementById("txtNombreLaboratorio").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			//document.getElementById("ActualizaMedica").innerHTML = ajax.responseText;
			window.location.reload();

		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CRLAB&txtNombreLaboratorio="+txtNombreLaboratorio);
}

function ActualizarMedicamentoHNJ(){
	var cmbMedicaMentoActu=document.getElementById("cmbMedicaMentoActu").value;
	var txtNombreArticulo=document.getElementById("txtNombreArticulo").value;
	var txtCodigoCUM=document.getElementById("txtCodigoCUM").value;
	var txtCodigoATC=document.getElementById("txtCodigoATC").value;
	var cmbGrupo=document.getElementById("cmbGrupo").value;//medicamento, insumo
	var txtConcentracion=document.getElementById("txtConcentracion").value;
	var cmbUnidad=document.getElementById("cmbUnidad").value;
	var cmbFFarmaceutica=document.getElementById("cmbFFarmaceutica").value;
	var cmbControl=document.getElementById("cmbControl").value;// si o no
	var cmbClasificacion=document.getElementById("cmbClasificacion").value;
	var cmbTipo=document.getElementById("cmbTipo").value;// generico o comercial
	var txtCodProgramaMed=document.getElementById("txtCodProgramaMed").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//document.getElementById("ActualizaMedica").innerHTML = ajax.responseText;
			window.location.reload();

		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACEAC&cmbMedicaMentoActu="+cmbMedicaMentoActu+"&txtNombreArticulo="+encodeURIComponent(txtNombreArticulo)+
			"&txtCodigoCUM="+txtCodigoCUM+"&txtCodigoATC="+txtCodigoATC+"&cmbGrupo="+cmbGrupo+"&txtConcentracion="+txtConcentracion+
			"&cmbUnidad="+cmbUnidad+"&cmbFFarmaceutica="+cmbFFarmaceutica+"&cmbControl="+cmbControl+"&cmbClasificacion="+cmbClasificacion+
			"&cmbTipo="+cmbTipo+"&txtCodProgramaMed="+txtCodProgramaMed);

}

function ActualizarMedicamento(){
	//var txtCodusuario=document.getElementById("txtCodusuario").value;
	var cmbMedicaMentoActu=document.getElementById("cmbMedicaMentoActu").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById("ActualizaMedica").innerHTML = ajax.responseText;	

		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MMEAC&cmbMedicaMentoActu="+cmbMedicaMentoActu);
	
}
function GuardarInventarioHNJ(){
	var cmbArticulo=document.getElementById("cmbArticulo").value;
	var txtFechaVenci=document.getElementById("txtFechaVenci").value;
	var txtLote=document.getElementById("txtLote").value;
	var txtCantidad=document.getElementById("txtCantidad").value;
	var txtInvima=document.getElementById("txtInvima").value;
	var cmbBodega=document.getElementById("cmbBodega").value;
	
	if(cmbArticulo=="Seleccione"){
		alert("Seleccione Articulo");
	}else{
		if(txtFechaVenci==""){
			alert("Escriba la Fecha de Vencimiento.");
		}else{
			if(txtLote==""){
				alert("Escriba Lote.");
			}else{
				if(txtCantidad==""){
					alert("Escriba Cantidad.");
				}else{
					if(txtInvima==""){
						alert("Escriba Invima");
					}else{
						ajax=getXMLObject();
						ajax.open("POST","ControlCrearArticulo",true);
						ajax.onreadystatechange = function(){
							if(ajax.readyState == 4){
								alert("Ingreso Exitoso.")
									window.location.reload();
							}
						}
						ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=GIR&cmbArticulo="+cmbArticulo+
								"&txtFechaVenci="+txtFechaVenci+"&txtLote="+txtLote+"&txtCantidad="+txtCantidad+
								"&txtInvima="+txtInvima+"&cmbBodega="+cmbBodega);
					}
				}
			}
		}
	}
	
}
function GuardarArticuloPrecio(){
	var txtNombreArticulo=document.getElementById('txtNombreArticulo').value;
	var txtCodigoATC=document.getElementById('txtCodigoATC').value;
	var txtCodigoCUM=document.getElementById('txtCodigoCUM').value;
	var cmbGrupo=document.getElementById('cmbGrupo').value;
	var txtConcentracion=document.getElementById('txtConcentracion').value;
	var cmbUnidad=document.getElementById('cmbUnidad').value;
	var cmbFFarmaceutica=document.getElementById('cmbFFarmaceutica').value;
	var cmbControl=document.getElementById('cmbControl').value;
	var cmbClasificacion=document.getElementById('cmbClasificacion').value;
	var cmbTipo=document.getElementById('cmbTipo').value;
	var txtValorArticulo=document.getElementById('txtValorArticulo').value;
	var txtCodusuario=document.getElementById("txtCodusuario").value;
	if(txtNombreArticulo==""){
		alert("Diligencie en nombre del articulo.");
	}else{
		if((txtCodigoATC=="")||(txtCodigoCUM=="")){
			alert("Diligencie el codigo ATC o el CUM");
		}else{
			if(cmbGrupo=="Seleccione"){
				alert("Seleccione el Grupo");
			}else{
				if(txtConcentracion==""){
					alert("Diligencie la concentracion");
				}else{
					if(cmbUnidad=="Seleccione"){
						alert("Seleccione la Unidad");
					}else{
						if(cmbFFarmaceutica=="Seleccione"){
							alert("Seleccione Forma Farmaceutica");
						}else{
							if(cmbControl=="Seleccione"){
								alert("Seleccione control");
							}else{
								if(cmbClasificacion=="Seleccione"){
									alert("Seleccione Clasificacion");
								}else{
									if(cmbTipo==""){
										alert("Seleccione Tipo");
									}else{
										if(txtValorArticulo==""){
											alert("Dilgencie el valor.");
										}else{
											var uni = document.form1.cmbUnidad.selectedIndex;
											var nomuni=document.form1.cmbUnidad.options[uni].text;
											
											var FF = document.form1.cmbFFarmaceutica.selectedIndex;
											var nomFF=document.form1.cmbFFarmaceutica.options[FF].text;
											
											var NombreCompletoArticulo=txtNombreArticulo+" "+txtConcentracion+" "+nomuni+" "+nomFF;
											ajax=getXMLObject();
											ajax.open("POST","ControlCrearArticulo",true);
											ajax.onreadystatechange = function(){
												if(ajax.readyState == 4){
														alert(ajax.responseText);
														window.location.reload();
												}
											}
											ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
											ajax.send("valor=CAV&txtNombreArticulo="+encodeURIComponent(NombreCompletoArticulo)+
													"&txtCodigoATC="+txtCodigoATC+"&cmbGrupo="+cmbGrupo+"&txtConcentracion="+encodeURIComponent(txtConcentracion)+
													"&cmbUnidad="+cmbUnidad+"&cmbFFarmaceutica="+cmbFFarmaceutica+
													"&cmbControl="+cmbControl+"&cmbClasificacion="+cmbClasificacion+
													"&cmbTipo="+cmbTipo+"&txtValorArticulo="+txtValorArticulo+"&txtCodusuario="+txtCodusuario+"&txtCodigoCUM="+txtCodigoCUM);

										}
									}
								}
							}
						}
					}					
				}
			}
		}
	}
}


function Formulario(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=0");

}

function MProducto(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MP");

}


function GFarmacologico(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GFarmacologico");

}

function CorteProduccion(usuario){
	var contenido=document.getElementById('contenido');
	//alert(usuario);
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CorteProduccion&usuario="+usuario);

}

function ModGF(codGrupo){
	var contenido=document.getElementById('contenido');
	//alert("ModGF "+codGrupo);
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MGFarm&codGrupo="+codGrupo);

}

function CrearGFarmacologico(){
	
	var descripcion=document.getElementById('txtDescripcion').value;
	var Iriesgo=document.getElementById('txtIRiesgo').value;
	
	if(descripcion==""){
		alert("No ha digitado una descripcion");
	}else{
		if(Iriesgo==""){
		alert("No ha digitado un Identificador de Riesgo");
		}
	}
	
	if((descripcion!="")&&(Iriesgo!="")){
		
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Creacion Exitosa");
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CGrupo&desc="+descripcion+"&Iriesgo="+Iriesgo);
 }
}


function CrearCorte(usuario){
	
	var Ph=document.getElementById('Ph').value;
	var Sh=document.getElementById('Sh').value;
	
	if((Ph=="invalido")||(Sh=="invalido")){
		alert("No ha digitado un rango adecuado de Hora ");
	}
		
	
	
	if((Ph!="invalido")&&(Sh!="invalido")){
		
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Creacion Exitosa");
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CHora&Ph="+Ph+"&Sh="+Sh+"&usuario="+usuario);
 }
}

function ModGrupoF(CodGrupo){
	//var contenido=document.getElementById('contenido');
	//alert("Modificacion de Grupo "+CodGrupo);
	
	var opcion=confirm("Desea Modificar este Grupo Farmacologico");
	if(opcion){
	window.location="farc_ModGrupoFarmacologico.jsp?CodGr="+CodGrupo;
	//TB_show('Modificacion de Grupo Farmacologico', "farc_ModGrupoFarmacologico.jsp?TB_iframe=true&height=400&width=800", null); 
	}else{
	}
	
}

function ModGFarmacologico(codGrupo){
	//alert("ModGFarmacologico"+codGrupo);
	var descripcion=document.getElementById('txtDescripcion').value;
	var Iriesgo=document.getElementById('txtIRiesgo').value;
	
	if(descripcion==""){
		alert("No ha digitado una descripcion");
	}else{
		if(Iriesgo==""){
		alert("No ha digitado un Identificador de Riesgo");
		}
	}
	//alert("DESCRIPCION modgfarmacologico"+descripcion);
	//alert("Iriesgo"+Iriesgo);
	if((descripcion!="")&&(Iriesgo!="")){
		
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Modificacion Exitosa");
			window.location="farc_CrearGrupoFarmacologico.jsp";
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	//alert("valor=MGrupo&codGrupo="+codGrupo+"&d="+descripcion+"&Iriesgo="+Iriesgo);
	ajax.send("valor=MGrupo&codGrupo="+codGrupo+"&d="+descripcion+"&Iriesgo="+Iriesgo);
 }
}


function CProducto(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CP");

}

function Bodegas(){
	var contenido=document.getElementById('contenido');
	var radio = document.getElementsByName("radiobutton");
	for (var x = 0; x < radio.length; x ++) {
	 if (radio[x].checked) {
		var val=radio[x].id; 
	 }
	}
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearBodega",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor="+val);
}


function Mbodest(){
	var contenido=document.getElementById('contenido');
	//var radio = document.getElementsByName("radiobutton");
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearBodega",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Mbodest");
}

function Iva(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearIvaGrupoUnidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Iva");

}

function Grupo(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearIvaGrupoUnidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Grupo");

}

function Unidad(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearIvaGrupoUnidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Unidad");

}

function Forma(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearIvaGrupoUnidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Forma");

}

function Tipos(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Tipos");

}

function Proveedor(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Proveedor");

}

function MProveedor(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MProveedor");

}



function IngresarArticulo(){
	
	var grupo=document.getElementById('cmbGrupo').value;
	//var nombre=document.getElementById('txtNombreArticulo').value;	
	var nombre=encodeURIComponent(document.getElementById("txtNombreArticulo").value);
	
	var observacion=document.getElementById('txtDescripcion').value;
	var tipo=document.getElementById('cmbTipo').value;
	var user=document.getElementById('txtCodusuario').value;
	
	if(grupo=="Seleccione"){
		alert("Seleccione El grupo al q pertenece el articulo que desea crear.");
	}
	
	if(grupo==1){
	
	var codigoArticulo=document.getElementById('txtCodigoArticulo').value;
	var concentracion=document.getElementById('txtConcentracion').value;
	var cod_formaFK=document.getElementById('cmbFormaFarmaceutica').value;
	var cod_unidadFK=document.getElementById('cmbUnidad').value;
	var tipoArticulo=document.getElementById('cmbTipoA').value;
	var nombreGenerico=document.getElementById('txtNombreGenerico').value;
	var control=document.getElementById('cmbControl').value;

	
	var paso="";
	
	if(nombre==""){
		alert("Digite el Nombre del Articulo.");
	}else{
		if(grupo=="Seleccione"){
			alert("Seleccione El grupo al q pertenece.");
		}else{
    	if(concentracion==""){
    		alert("Digite La concentraci\xf3n.");
	    }else{
	    	if(cod_formaFK=="Seleccione"){
	    		alert("Seleccione la Forma Farmaceutica.");
	    	}else{
	    		if(cod_unidadFK=="Seleccione"){
	    			alert("Seleccione la Unidad de Medida.");
	    		}else{
	    			if(tipoArticulo=="Seleccione"){
	    				alert("Seleccione El tipo de Articulo.");
	    			}else{
	    				if(nombreGenerico==""){
	    					alert("Digite el nombre Generico.");
	    				}else{
	    					if(tipo=="Seleccione"){
	    						alert("Seleccione la Clasificaci\xf3n.");
	    					}else{
	    						
	    							if(control=="Seleccione"){
	    								alert("Seleccione Si es o no de control especial.");
	    							}else{
	    								if((tipo=="Pos")&&(codigoArticulo=="")){
	    									alert("El Codigo ATC es Obligatorio para los POS");
	    									paso="x";
	    								}
	    							}
	    						}
	    					}
	    				}			
	    			}
	    		}
	    	}
	    }
	}
	
	
	if((nombre!="")&&(concentracion!="")&&(cod_formaFK!="Seleccione")&&(cod_unidadFK!="Seleccione")&&(tipoArticulo!="Seleccione")&&(nombreGenerico!="")&&(tipo!="Seleccione")&&(grupo!="Seleccione")&&(control!="Seleccione")&&(paso=="")){
		
		ajax=getXMLObject();
		ajax.open("POST","ControlCrearArticulo",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert(ajax.responseText);
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		
		ajax.send("valor=1&codigoArticulo="+codigoArticulo+"&nombre="+nombre+"&concentracion="+concentracion+"&cod_formaFK="+cod_formaFK+"&cod_unidadFK="+cod_unidadFK+"&tipoArticulo="+tipoArticulo+"&nombreGenerico="+nombreGenerico+"&tipo="+tipo+"&grupo="+grupo+"&control="+control+"&observacion="+observacion+"&user="+user);
	   }
	
	
	}else{
				
		codigoArticulo="";
		concentracion="";
		cod_formaFK="1";
		cod_unidadFK="1";
		tipoArticulo="";
		nombreGenerico="";
		control="No";
		
		if(nombre==""){
				alert("Digite el Nombre del Articulo.");
			}else{
				if(grupo=="Seleccione"){
					alert("Seleccione El grupo al q pertenece.");
				}else{
					if(tipo=="Seleccione"){
						alert("Seleccione la Clasificaci\xf3n.");
					}
				}
			}
	
	
	//alert("valor=1&codigoArticulo="+codigoArticulo+"&nombre="+nombre+"&concentracion="+concentracion+"&cod_formaFK="+cod_formaFK+"&cod_unidadFK="+cod_unidadFK+"&tipoArticulo="+tipoArticulo+"&nombreGenerico="+nombreGenerico+"&tipo="+tipo+"&grupo="+grupo+"&control="+control+"&observacion="+observacion+"&user="+user);
	if((nombre!="")&&(tipo!="Seleccione")&&(grupo!="Seleccione")){
		
		ajax=getXMLObject();
		ajax.open("POST","ControlCrearArticulo",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				alert(ajax.responseText);
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		
		ajax.send("valor=1&codigoArticulo="+codigoArticulo+"&nombre="+nombre+"&concentracion="+concentracion+"&cod_formaFK="+cod_formaFK+"&cod_unidadFK="+cod_unidadFK+"&tipoArticulo="+tipoArticulo+"&nombreGenerico="+nombreGenerico+"&tipo="+tipo+"&grupo="+grupo+"&control="+control+"&observacion="+observacion+"&user="+user);
	   }  
	
	}//if grupo=1
	
	//alert(nombre);
}	


function CrearBodega(){
	
	var nombreBodega=document.getElementById('txtNombreBodega').value;
	var descripcion=document.getElementById('txtDescripcion').value;

	if(nombreBodega==""){
		alert("Digite el Nombre de la Bodega.");
	}
	if(nombreBodega!=""){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearBodega",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			var x=ajax.responseText;	
			if(x!=""){
				alert(x);
				}else{
			alert("La Bodega se ha creado Exitosamente.");	
			nombreBodega.value="";
			descripcion.value="";
			Bodegas();
			//window.location.reload();
		}
	  }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&nombreBodega="+nombreBodega+"&descripcion="+descripcion);
	}
}


function ModificarBodest(boe,code){
	var sw=0;
	if(boe==1){
	//alert("bodega");
	var nombreBodega=document.getElementById('txtNombreBodega').value;
	var descripcion=document.getElementById('txtDescripcion').value;
	if(nombreBodega==""){alert("Digite el Nombre de la Bodega.");sw=1;}
	}else{	
	//alert("estante");
	var nombreEstante=document.getElementById('txtNombreEstante').value;
	var observacion=document.getElementById('txtObservacion').value;
	if(nombreEstante==""){alert("Digite el Nombre del Estante.");sw=1;}
	}
	
	if(sw==0){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearBodega",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			var x=ajax.responseText;	
			if(x!=""){
				alert(x);
				}else{
			alert("Modificacion Exitosa!!!.");	
			
			Opcionboe(boe);
			
		}
	  }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Modificarboe&nombreBodega="+nombreBodega+"&descripcion="+descripcion+"&nombreEstante="+nombreEstante+"&observacion="+observacion+"&code="+code+"&boe="+boe);
	}
}

function CrearEstante(){
	
	var nombreEstante=document.getElementById('txtNombreEstante').value;
	var observacion=document.getElementById('txtObservacion').value;

	if(nombreEstante==""){
		alert("Digite el Nombre del Estante.");
	}
	if(nombreEstante!=""){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearBodega",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			var x=ajax.responseText;	
			if(x!=""){
			alert(x);
			}else{
				alert("El Estante se ha creado Exitosamente.");
			//window.location.reload();
				nombreEstante.value="";
				observacion.value="";
				Bodegas();
				
			}
	  }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&nombreEstante="+nombreEstante+"&observacion="+observacion);
	}
}

function CrearIva(){
	
	var nombreIva=document.getElementById('txtNombreIva').value;
	var valor=document.getElementById('txtValor').value;
	var descripcion=document.getElementById('txtDescripcion').value;

	if(nombreIva==""){
		alert("Digite el Nombre del tipo de IVA.");
	}
	for(i=0;i<nombreIva.length;i++){
	      nombreIva=nombreIva.replace('%','@');
	    }
	if(valor==""){
		alert("Digite el Valor del tipo de IVA.");
	}
	for(i=0;i<valor.length;i++){
		if(valor.charAt(i)=="%"){
		valor="";
		alert("El valor debe ser solo n\xdamerico sin %.");
		}
	}
	if((nombreIva!="")&&(valor!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearIvaGrupoUnidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			var x=ajax.responseText;	
			if(x!=""){
				alert(x);
				}else{
			alert("El tipo de IVA se ha creado Exitosamente.");		
			window.location.reload();
		}
	  }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&nombreIva="+nombreIva+"&valor1="+valor+"&descripcion="+descripcion);
	}
}

function CrearGrupo(){
	
	var nombreGrupo=document.getElementById('txtNombreGrupo').value;
	var observacion=document.getElementById('txtObservacion').value;
	var tipog=document.getElementById('cmbTg').value;
	
	//alert("El tipo es: "+tipog);
	
	
	if(nombreGrupo==""){
		alert("Digite el Nombre del Grupo.");
	}
	
	if(nombreGrupo!=""){
		if(tipog=="Seleccione"){
			alert("Seleccione el tipo de Grupo a crear.");
		}else{
		
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearIvaGrupoUnidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			var x=ajax.responseText;	
			if(x!=""){
				alert(x);
				}else{
			alert("El Grupo se ha creado Exitosamente.");		
			window.location.reload();
		}
	  }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&nombreGrupo="+nombreGrupo+"&descripcion="+observacion+"&tipog="+tipog);
	
	}
	}
	
}

function CrearUnidad(){
	
	var nombreUnidad=document.getElementById('txtNombreUnidad').value;
	var sigla=document.getElementById('txtSigla').value;

	if(nombreUnidad==""){
		alert("Digite el Nombre de la Unidad de medida.");
	}
	if(sigla==""){
		alert("Digite la sigla representativa de la Unidad de medida.");
	}
	for(i=0;i<sigla.length;i++){
	     sigla=sigla.replace('%','@');
	}

	for(i=0;i<nombreUnidad.length;i++){
		if(nombreUnidad.charAt(i)=="%"){
		nombreUnidad="";
		alert("El nombre de la Unidad no debe llevar el caracter %.");
		}
	}
	
	if((nombreUnidad!="")&&(sigla!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearIvaGrupoUnidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			var x=ajax.responseText;	
			if(x!=""){
				alert(x);
				}else{
			alert("La Unidad de Medida se ha creado Exitosamente.");		
			window.location.reload();
		}
	  }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&nombreUnidad="+nombreUnidad+"&sigla="+sigla);
	}
}

function CrearForma(){
	
	var nombreForma=document.getElementById('txtNombreForma').value;
	var siglaF=document.getElementById('txtSiglaF').value;

	if(nombreForma==""){
		alert("Digite el Nombre de la Forma Farmaceutica.");
	}
	if(siglaF==""){
		alert("Digite la sigla representativa de la Forma Farmaceutica.");
	}
	if((nombreForma!="")&&(siglaF!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearIvaGrupoUnidad",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			var x=ajax.responseText;	
			if(x!=""){
				alert(x);
				}else{
			alert("La Forma Farmaceutica se ha creado Exitosamente.");		
			window.location.reload();
		}
	  }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4&nombreForma="+nombreForma+"&siglaF="+siglaF);
	}
}



function CrearProveedor(){
	
	var nit=document.getElementById('txtNit').value;
	var pais=document.getElementById('cmbPais').value;
	var ciudad=document.getElementById('cmbDpto').value;
	var muni=document.getElementById('cmbMuni').value;
	var telefono=document.getElementById('txtTelefono').value;
	var fax=document.getElementById('txtFax').value;
	var email=document.getElementById('txtEmail').value;
	var contacto=document.getElementById('txtContacto').value;
	var clase=document.getElementById('cmbClase').value;
	var razon=document.getElementById('txtRazon').value;
	var direccion=document.getElementById('txtDireccion').value;
	var observacion=document.getElementById('txtDescripcion').value;
	
	if(razon==""){
		alert("Debe Digitar la Razon Social.");
	}else{
	 if(nit==""){
		alert("Digite el NIT del Proveedor.");
	 }else{
	   if(telefono==""){
		alert("Debe Digitar El telefono del Proveedor.");
      }else{
    	  if(pais=="Seleccione"){
    		alert("Debe Escoger la Nacionalidad del Proveedor.");
	   }else{
		   if(ciudad=="Seleccione"){
				alert("Debe Escoger la Ciudad.");
	    }else{
	    	if(muni=="Seleccione"){
	    		alert("Debe Escoger El Municipio.");
	     }else{
	      if(contacto==""){
	    	alert("Debe Digitar El Nombre del Contacto.");
	      }else{
	       if(clase=="Seleccione"){
		    alert("Debe Escoger la Clase Contribuyente.");
	       }
	      }
	     }
	    }
	   }
      }
     }
	}
	
	if((nit!="")&&(razon!="")&&(telefono!="")&&(contacto!="")&&(pais!="Seleccione")&&(ciudad!="Seleccione")&&(muni!="Seleccione")&&(clase!="Seleccione")){
		
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&nit="+nit+"&pais="+pais+"&ciudad="+ciudad+"&muni="+muni+"&telefono="+telefono+"&fax="+fax+"&email="+email+"&contacto="+contacto+"&clase="+clase+"&razon="+razon+"&direccion="+direccion+"&observacion="+observacion);
 }
}


function CrearTipo(){
	
	var nombreTipo=document.getElementById('txtNombreTipo').value;
	var Operacion=document.getElementById('cmbOperacion').value;

	if(nombreTipo==""){
		alert("Digite el Nombre del tipo de Movimiento.");
	}
	if(Operacion=="Seleccione"){
		alert("Debe Escoger el tipo de Operación relacionada.");
	}
	
	if((nombreTipo!="")&&(Operacion!="Seleccione")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			var x=ajax.responseText;	
			if(x!=""){
				alert(x);
				}else{
			alert("El Tipo de Movimiento ha creado Exitosamente.");		
			window.location.reload();
		}
	  }
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4&nombreTipo="+nombreTipo+"&Operacion="+Operacion);
	}
}


function BuscarRelacion(){
	var contenido=document.getElementById('rel');
	//var xx=document.getElementById("rel");
	var cmbBodega=document.getElementById("cmbBodega").value;
	 if(cmbBodega!="Seleccione"){
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearBodega",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					//alert(aviso);
					//document.getElementById("contenido").innerHTML;//=aviso;
					//document.getElementById("cmbBodega").value="Seleccione";
					//document.getElementById("cmbBodega").focus="";
				    //document.getElementById("cmbEstante").value="Seleccione";		    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=4&cmbBodega="+cmbBodega); //Posting txtname to Servlet
		 
		}	
}


function BuscarOpcion(){
	//var contenido=document.getElementById('rel');
	var contenido=document.getElementById('contenido');
	//var xx=document.getElementById("rel");
	var cmbBodega=document.getElementById("cmbOpcion").value;
	 if(cmbBodega!="Seleccione"){
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearBodega",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					//alert(aviso);
					//document.getElementById("contenido").innerHTML;//=aviso;
					//document.getElementById("cmbBodega").value="Seleccione";
					//document.getElementById("cmbBodega").focus="";
				    //document.getElementById("cmbEstante").value="Seleccione";		    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=Mostrar&cmbBodega="+cmbBodega); //Posting txtname to Servlet
		 
		}
}


function Opcionboe(c){
	var contenido=document.getElementById('contenido');

		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearBodega",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					//alert(aviso);
					//document.getElementById("contenido").innerHTML;//=aviso;
					//document.getElementById("cmbBodega").value="Seleccione";
					//document.getElementById("cmbBodega").focus="";
				    //document.getElementById("cmbEstante").value="Seleccione";		    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=Mostrar&cmbBodega="+c); //Posting txtname to Servlet

}

function estante(code){
	var contenido=document.getElementById('rel');
	//var xx=document.getElementById("rel");
	var cmbBodega=document.getElementById("cmbBodega").value;
	//alert("El cod del estante es "+code);
	 if(cmbBodega!="Seleccione"){
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearBodega",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
		    		alert("Actualización exitosa!!!"); 
		    		BuscarRelacion();
			  	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=5&code="+code); //Posting txtname to Servlet
		 
		}	
}

function Mbode(cod, boe){
	//alert("una mas cod: "+cod+" boe: "+boe);
	var contenido=document.getElementById('contenido');
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearBodega",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
		    		//alert("Actualización exitosa!!!"); 
		    		//BuscarRelacion();
			  	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=MBOE&code="+cod+"&boe="+boe); //Posting txtname to Servlet
}

function Asignar(){
	var xx=document.getElementById("rel");
	var cmbBodega=document.getElementById("cmbBodega").value;
	var cmbEstante=document.getElementById("cmbEstante").value;
	if(cmbBodega=="Seleccione"){alert("Seleccione la Bodega!!!");}
	if(cmbEstante=="Seleccione"){alert("Seleccione el Estante!!!");}
	if((cmbBodega!="Seleccione")&&(cmbEstante!="Seleccione")){
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearBodega",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;			
					//alert(aviso);
		    		if(aviso==""){
		    		alert("Este estante ya ha sido asignado");	
		    		}else{
					xx.innerHTML = aviso;	
					//document.getElementById("contenido").innerHTML;//=aviso;
					//document.getElementById("cmbBodega").value="Seleccione";
					//document.getElementById("cmbBodega").focus="";
					//document.getElementById("cmbEstante").value="Seleccione";		
		    		}
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=3&cmbBodega="+cmbBodega+"&cmbEstante="+cmbEstante); //Posting txtname to Servlet
		 
		}	
}



function Finalizar(){
	alert("Asignaciones creadas Exitosamente");
	window.location.reload();
}


function medoins(){
	var contenido=document.getElementById('contenido');
	var moi=document.getElementById('cmbGrupo').value;
	var cmbTipoA=document.getElementById("cmbTipoA").value;
	
	if(moi=="2"){
	 document.getElementById('txtConcentracion').value="";
	 document.getElementById('txtConcentracion').disabled=true;
	 document.getElementById('cmbFormaFarmaceutica').value="Seleccione";
	 document.getElementById('cmbFormaFarmaceutica').disabled=true;
	 document.getElementById('cmbUnidad').value="Seleccione";
	 document.getElementById('cmbUnidad').disabled=true;
	 document.getElementById('cmbTipoA').value="Seleccione"; 
	 document.getElementById('cmbTipoA').disabled=true; 
	 Comercial(moi);
	}else{
		 document.getElementById('txtConcentracion').disabled=false;
		 document.getElementById('cmbFormaFarmaceutica').disabled=false;
		 document.getElementById('cmbUnidad').disabled=false;
		 document.getElementById('cmbTipoA').disabled=false; 
	 Comercial(moi);
	}
	
}


function Comercial(moi){
	//alert("Comerciallllllllll"+moi);
	var contenido=document.getElementById('imagina');
	var ff=document.getElementById('cmbFormaFarmaceutica').value;
	var cmbTipoA=document.getElementById("cmbTipoA").value;
	var txtNombreArticulo=encodeURIComponent(document.getElementById("txtNombreArticulo").value);
	
	//alert(cmbTipoA);
	if(moi=="1"){
	if(cmbTipoA=="Comercial"){
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearArticulo",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=2&gen="+txtNombreArticulo); //Posting txtname to Servlet
	}	
	
	if(cmbTipoA=="Generico"){
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearArticulo",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=3&gen="+txtNombreArticulo); //Posting txtname to Servlet
	}
	
	if(cmbTipoA=="Seleccione"){
		//alert("entro aqui");
		ajax=getXMLObject();
	    ajax.open("POST","ControlCrearArticulo",true); //getname will be the servlet name
	     ajax.onreadystatechange  = function(){
	    	 if(ajax.readyState == 4){
	    		var aviso=ajax.responseText;		
	    		contenido.innerHTML = aviso;
				    		
	    	}		    	
	    }
	    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=2&gen="+txtNombreArticulo); //Posting txtname to Servlet
	}
	}else{
		ajax=getXMLObject();
	    ajax.open("POST","ControlCrearArticulo",true); //getname will be the servlet name
	     ajax.onreadystatechange  = function(){
	    	 if(ajax.readyState == 4){
	    		var aviso=ajax.responseText;		
	    		contenido.innerHTML = aviso;
				    		
	    	}		    	
	    }
	    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=ins&gen="+txtNombreArticulo); //Posting txtname to Servlet		
	}
	
}

function Dpto(){
	
	var contenido=document.getElementById('dpto');
	var pais=document.getElementById("cmbPais").value;
	
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearProveedor",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=1&pais="+pais); //Posting txtname to Servlet
	
}

function Muni(){
	
	var contenido=document.getElementById('Muni');
	var ciudad=document.getElementById("cmbDpto").value;
	
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearProveedor",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;
					    		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=2&ciudad="+ciudad); //Posting txtname to Servlet
	
}
function atc(){
	
	var contenido=document.getElementById('imagina');
	var atc=document.getElementById("cmbTipo").value;
	var codigoArticulo=document.getElementById('txtCodigoArticulo').value;
	var gen=document.getElementById('txtnombreGenerico').value;
	var tipo=document.getElementById("cmbTipo").value;
	
	if(atc=="Pos"){
		if(codigoArticulo==""){
		 alert("El Codigo ATC es Obligatorio para los POS");
		document.getElementById('txtCodigoArticulo').focus();
		}
	}
	/*		ajax=getXMLObject();
			    ajax.open("POST","ControlCrearArticulo",true); //getname will be the servlet name
			     ajax.onreadystatechange  = function(){
			    	 if(ajax.readyState == 4){
			    		var aviso=ajax.responseText;		
			    		contenido.innerHTML = aviso;
						    		
			    	}		    	
			    }
			    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			    ajax.send("valor=4&atc="+atc+"&tipo="+tipo+"&gen="+gen); //Posting txtname to Servlet
		*/	
}

function Actualizarp(ni){

	var contenido=document.getElementById('contenido');
	//var ciudad=document.getElementById("cmbDpto").value;
	//alert("El nit es "+ni);
		ajax=getXMLObject();
		    ajax.open("POST","ControlCrearProveedor",true); //getname will be the servlet name
		     ajax.onreadystatechange  = function(){
		    	 if(ajax.readyState == 4){
		    		var aviso=ajax.responseText;		
		    		contenido.innerHTML = aviso;   		
		    	}		    	
		    }
		    ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=Actualizarp&ni="+ni); //Posting txtname to Servlet
	
}


function ModificarProveedor(codpp){
	
	var nit=document.getElementById('txtNit').value;
	var pais=document.getElementById('cmbPais').value;
	var ciudad=document.getElementById('cmbDpto').value;
	var muni=document.getElementById('cmbMuni').value;
	var telefono=document.getElementById('txtTelefono').value;
	var fax=document.getElementById('txtFax').value;
	var email=document.getElementById('txtEmail').value;
	var contacto=document.getElementById('txtContacto').value;
	var clase=document.getElementById('cmbClase').value;
	var razon=document.getElementById('txtRazon').value;
	var direccion=document.getElementById('txtDireccion').value;
	var observacion=document.getElementById('txtDescripcion').value;
	
	//alert("nit: "+nit+" codpp:"+codpp);
	
	if(razon==""){
		alert("Debe Digitar la Razon Social.");
	}else{
	 if(nit==""){
		alert("Digite el NIT del Proveedor.");
	 }else{
	   if(telefono==""){
		alert("Debe Digitar El telefono del Proveedor.");
      }else{
    	  if(pais=="Seleccione"){
    		alert("Debe Escoger la Nacionalidad del Proveedor.");
	   }else{
		   if(ciudad=="Seleccione"){
				alert("Debe Escoger la Ciudad.");
	    }else{
	    	if(muni=="Seleccione"){
	    		alert("Debe Escoger El Municipio.");
	     }else{
	      if(contacto==""){
	    	alert("Debe Digitar El Nombre del Contacto.");
	      }else{
	       if(clase=="Seleccione"){
		    alert("Debe Escoger la Clase Contribuyente.");
	       }
	      }
	     }
	    }
	   }
      }
     }
	}
	
	if((nit!="")&&(razon!="")&&(telefono!="")&&(contacto!="")&&(pais!="Seleccione")&&(ciudad!="Seleccione")&&(muni!="Seleccione")&&(clase!="Seleccione")){
		
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MP&nit="+nit+"&pais="+pais+"&ciudad="+ciudad+"&muni="+muni+"&telefono="+telefono+"&fax="+fax+"&email="+email+"&contacto="+contacto+"&clase="+clase+"&razon="+razon+"&direccion="+direccion+"&observacion="+observacion+"&codpp="+codpp);
 }
}


function MP(){
	var cm=document.getElementById('txtTipoMeH0').value;
	var grupop=document.getElementById('grupo').value;
	//alert("grupo: "+grupo);
	var cmm=encodeURIComponent(cm);
	var contenido=document.getElementById('contenido');
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=MP2&cart="+cmm+"&grupop="+grupop);
}


function MPA(cart){
	
	var codigoArticulo=document.getElementById('txtCodigoArticulo').value;
	var nombrem=document.getElementById('txtNombreArticulo').value;
	var nombre=encodeURIComponent(nombrem);
	var concentracion=document.getElementById('txtConcentracion').value;
	var cod_formaFK=document.getElementById('cmbFormaFarmaceutica').value;
	var cod_unidadFK=document.getElementById('cmbUnidad').value;
	var tipoArticulo=document.getElementById('cmbTipoA').value;
	var nombreGenerico=document.getElementById('txtNombreGenerico').value;
	var tipo=document.getElementById('cmbTipo').value;
	var grupo=document.getElementById('cmbGrupo').value;
	var control=document.getElementById('cmbControl').value;
	var observacion=document.getElementById('txtDescripcion').value;
	var user=document.getElementById('txtCodusuario').value;
	
	var paso="";
	
	if(nombre==""){
		alert("Digite el Nombre del Articulo.");
	}else{
    	if(concentracion==""){
    		alert("Digite La concentraci\xf3n.");
	    }else{
	    	if(cod_formaFK=="Seleccione"){
	    		alert("Seleccione la Forma Farmaceutica.");
	    	}else{
	    		if(cod_unidadFK=="Seleccione"){
	    			alert("Seleccione la Unidad de Medida.");
	    		}else{
	    			if(tipoArticulo=="Seleccione"){
	    				alert("Seleccione El tipo de Articulo.");
	    			}else{
	    				if(nombreGenerico==""){
	    					alert("Digite el nombre Generico.");
	    				}else{
	    					if(tipo=="Seleccione"){
	    						alert("Seleccione la Clasificaci\xf3n.");
	    					}else{
	    						if(grupo=="Seleccione"){
	    							alert("Seleccione El grupo al q pertenece.");
	    						}else{
	    							if(control=="Seleccione"){
	    								alert("Seleccione Si es o no de control especial.");
	    							}else{
	    								if((tipo=="Pos")&&(codigoArticulo=="")){
	    									alert("El Codigo ATC es Obligatorio para los POS");
	    									paso="x";
	    								}
	    							}
	    						}
	    					}
	    				}			
	    			}
	    		}
	    	}
	    }
	}
	
	
	if((nombre!="")&&(concentracion!="")&&(cod_formaFK!="Seleccione")&&(cod_unidadFK!="Seleccione")&&(tipoArticulo!="Seleccione")&&(nombreGenerico!="")&&(tipo!="Seleccione")&&(grupo!="Seleccione")&&(control!="Seleccione")&&(paso=="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location.reload();
			MProducto();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=100&codigoArticulo="+codigoArticulo+"&nombre="+nombre+"&concentracion="+concentracion+"&cod_formaFK="+cod_formaFK+"&cod_unidadFK="+cod_unidadFK+"&tipoArticulo="+tipoArticulo+"&nombreGenerico="+nombreGenerico+"&tipo="+tipo+"&grupo="+grupo+"&control="+control+"&observacion="+observacion+"&cart="+cart+"&user="+user);
   }
}	


function MPA2(cart){
	
	codigoArticulo="";
	concentracion="";
	cod_formaFK="1";
	cod_unidadFK="1";
	tipoArticulo="";
	nombreGenerico="";
	control="No";
	
	var nombrem=document.getElementById('txtNombreArticulo').value;
	var nombre=encodeURIComponent(nombrem);
	var tipo=document.getElementById('cmbTipo').value;
	var grupo=document.getElementById('cmbGrupo').value;
	var observacion=document.getElementById('txtDescripcion').value;
	var user=document.getElementById('txtCodusuario').value;

	
	if(nombre==""){
		alert("Digite el Nombre del Articulo.");
	}else{
     if(tipo=="Seleccione"){
	    alert("Seleccione la Clasificaci\xf3n.");
	   }
	 }
	  
	if((nombre!="")&&(tipo!="Seleccione")&&(grupo!="Seleccione")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location.reload();
			MProducto();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=100&codigoArticulo="+codigoArticulo+"&nombre="+nombre+"&concentracion="+concentracion+"&cod_formaFK="+cod_formaFK+"&cod_unidadFK="+cod_unidadFK+"&tipoArticulo="+tipoArticulo+"&nombreGenerico="+nombreGenerico+"&tipo="+tipo+"&grupo="+grupo+"&control="+control+"&observacion="+observacion+"&cart="+cart+"&user="+user);
   }
}	


function CP(){
	var cm=document.getElementById('txtTipoMe0').value;
	//alert("Listo modificado "+cm);
	var contenido=document.getElementById('contenido');
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearArticulo",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CP2&cart="+cm);
}
