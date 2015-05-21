function getXMLObject() { // XML OBJECT
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft IE 6.0+
		} catch (e2) {
			xmlHttp = false; // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object
/** *********************************************** */

// busca el nombre del producto
function buscar() {
	var nombre = document.getElementById("nombre").value;

	if (nombre == "") {
		alert("Escriba el Nombre del Producto.");
		document.getElementById('nombre').focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var datos = ajax.responseText;
				if (datos == 1) {
					alert("El producto ya se encuentra creado");
					document.getElementById("nombre").value = "";
					document.getElementById('nombre').focus();
				}
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&nombre=" + nombre);
	}
}


// Autocompleta el nombre del proveedor
function AutoCompleta() {
	var nomproveedor = document.getElementById("nomproveedor").value;

	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); 
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("AutoProveedor").innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&nomproveedor=" + nomproveedor); 
}

function Seleccion(cod, diag) {
	document.getElementById("nomproveedor").value = diag;
	document.getElementById("proveedor").value = cod;
	document.getElementById("AutoProveedor").innerHTML = "";
}


// Ingresar los datos del proveedor
function Crear_Producto(op) {
	var subgrupo ="0";
	var grupo=document.getElementById("grupo").value;
	var nombre = document.getElementById("nombre").value;
	var proveedor = document.getElementById("proveedor").value;
	var precio = document.getElementById("precio").value;
	var iva = document.getElementById("iva").value;
	var Descripcion = document.getElementById("Descripcion").value;
	var usuario = document.getElementById("usua").value;

	if(op=="2"){
		subgrupo = document.getElementById("subgrupos").value;
	}
	
	
	if ((nombre == "") || (proveedor == "") || (precio == "") || (iva == "")|| (Descripcion == "")|| (grupo == "selected")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Ingreso Exitoso.");
				window.location.href = "OrdenCompra.jsp";
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&nombre=" + nombre + "&proveedor=" + proveedor
				+ "&precio=" + precio + "&iva=" + iva + "&Descripcion="+ Descripcion+"&grupo="+grupo+"&subgrupo="+subgrupo+"&usuario="+usuario);
	}
}

//Ingresar los datos del proveedor
function CrearNuevaDescripcion() {
	var producto = document.getElementById("producto").value;
	var proveedor = document.getElementById("proveedor").value;
	var precio = document.getElementById("precio").value;
	var iva = document.getElementById("iva").value;
	var Descripcion = document.getElementById("Descripcion").value;
	//var grupo = document.getElementById("grupo").value;
	var usuario = document.getElementById("usua").value;

	if ((producto == "") || (proveedor == "") || (precio == "") || (iva == "")|| (Descripcion == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Ingreso Exitoso.");
				window.location.href = "OrdenCompra.jsp";
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=30&producto=" + producto + "&proveedor=" + proveedor
				+ "&precio=" + precio + "&iva=" + iva + "&Descripcion="+ Descripcion+"&usuario="+usuario);
	}
}



//generar orden
function Generar() {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("detalle_producto").innerHTML = ajax.responseText;
				
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4");
}


//Autocompleta el nombre del producto
function AutoCompleta3() {
	var nomproducto = document.getElementById("nomproducto").value;
	var proveedor = document.getElementById("proveedor").value;

	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("AutoProducto").innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&nomproducto=" + nomproducto + "&proveedor="	+ proveedor); // Posting txtname to Servle
}


function Seleccion3(codproducto, precio, iva, nombre) {
	document.getElementById("nomproducto").value = nombre;
	document.getElementById("producto").value = codproducto;
	document.getElementById("AutoProducto").innerHTML = "";
	if (iva == "1") {
		document.getElementById("iva").value = "0";
	} else {
		document.getElementById("iva").value = "16";
	}
	document.getElementById("precio").value = precio;
	 document.getElementById("descuento").value="0";
	document.getElementById('cantidad').focus();
}


//guardar detalle de la orden
function Guardar_detalle() {
	var proveedor = document.getElementById("proveedor").value;
	var nomproveedor = document.getElementById("nomproveedor").value;
	var fec_entrega = document.getElementById("fec_entrega").value;
	var plazo = document.getElementById("plazo").value;
	var usuario = document.getElementById("usu").value;
	var producto = document.getElementById("producto").value;
	var nomproducto = document.getElementById("nomproducto").value;
	var cantidad = document.getElementById("cantidad").value;
	var valor_unitario = document.getElementById("precio").value;
	var descuento = document.getElementById("descuento").value;
	var iva = document.getElementById("iva").value;

	if ((proveedor == "") || (nomproveedor == "") || (fec_entrega == "")|| (plazo == "")|| (producto == "") || (cantidad == "")|| (valor_unitario == "") || (descuento == "") || (iva == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("producto").value = "";
				document.getElementById("cantidad").value = "";
				document.getElementById("precio").value = "";
				document.getElementById("descuento").value = "";
				document.getElementById("iva").value = "";
				document.getElementById("nomproducto").value = "";
				document.getElementById("ord").innerHTML=ajax.responseText;
				document.getElementById("detalle_producto").innerHTML="";
				document.getElementById("nomproveedor").disabled=true;
				document.getElementById("fec_entrega").disabled=true;
				document.getElementById("plazo").disabled=true;
				Refresh()
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=6&proveedor=" + proveedor + "&nomproveedor="+ nomproveedor + "&fec_entrega=" + fec_entrega + "&plazo="
				+ plazo + "&usuario=" + usuario+"&producto=" + producto+"&nomproducto=" + nomproducto
				+ "&cantidad=" + cantidad + "&valor_unitario=" + valor_unitario
				+ "&descuento=" + descuento + "&iva=" + iva);
	}
}

//guardar detalle de la orden
function Guardar_det() {
	var codorden = document.getElementById("orden").value;
	var usuario = document.getElementById("usu").value;
	var nomproducto = document.getElementById("nomproducto").value;
	var producto = document.getElementById("producto").value;
	var cantidad = document.getElementById("cantidad").value;
	var valor_unitario = document.getElementById("precio").value;
	var descuento = document.getElementById("descuento").value;
	var iva = document.getElementById("iva").value;

	if ( (producto == "") || (cantidad == "")|| (valor_unitario == "") || (descuento == "") || (iva == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("producto").value = "";
				document.getElementById("cantidad").value = "";
				document.getElementById("precio").value = "";
				document.getElementById("descuento").value = "";
				document.getElementById("iva").value = "";
				document.getElementById("nomproducto").value = "";
				Refresh()
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=6.5&codorden=" + codorden+"&usuario=" + usuario+"&producto=" + producto+"&nomproducto=" + nomproducto
				+ "&cantidad=" + cantidad + "&valor_unitario=" + valor_unitario
				+ "&descuento=" + descuento + "&iva=" + iva);
	}
}


//refrescar detalles de la orden
function Refresh() {
	var codorden = document.getElementById("orden").value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			
			document.getElementById("detalle_producto_ver").innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7&codorden=" + codorden);
}


//actualizar detalle orden
function Actualizar_Detalle(cod_detalle) {
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("actualizar1").innerHTML = ajax.responseText;
			document.getElementById("detalle_producto_ver").innerHTML = "";
			oculta("detalle_producto");
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8&cod_detalle=" + cod_detalle);
}


//actualizar detalle de la orden
function Update_Detalle(cod_detalle) {
	var usuario = document.getElementById("usu").value;
	var codorden = document.getElementById("orden").value;
	var cantidad = document.getElementById("cantidad1").value;
	var descuento = document.getElementById("descuento1").value;
	var valor_unitario = document.getElementById("valor_unitario1").value;
	var iva = document.getElementById("iva1").value;

	if ((cantidad == "") || (descuento == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Actualizacion Exitosa.");
				document.getElementById("cantidad1").value = "";
				document.getElementById("descuento1").value = "";
				document.getElementById("iva1").value = "";
				document.getElementById("valor_unitario1").value = "";
				aparecer("detalle_producto");
				Refresh()
				document.getElementById("actualizar1").innerHTML = "";
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=9&cod_detalle=" + cod_detalle + "&cantidad="+ cantidad + "&descuento=" + descuento + "&valor_unitario="
				+ valor_unitario + "&iva=" + iva + "&codorden=" + codorden+"&usuario="+usuario);
	}
}



//eliminar detalle de una orden
function Eliminar_Detalle(cod_detalle) {
	if (confirm("¿Quieres eliminar este registro?")) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Eliminado exitosamente");
				Refresh()
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=10&cod_detalle=" + cod_detalle);
	}
}


//muestra observacion
function Finalizar_Orden() {
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("observacion").innerHTML = ajax.responseText;
			oculta("detalle_producto");
			oculta("detalle_producto_ver");
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11");
}


//finalizar orden
function Finalizar() {
	var codorden = document.getElementById("orden").value;
	var Observacion = document.getElementById("Observacion").value;
	var vtotal = document.getElementById("vtotal").value;

	if ((vtotal == "") || (codorden == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Orden Finalizada Exitosamente.");
				window.location.href = "OrdenCompra.jsp";
				Reporte(codorden);
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=12&codorden=" + codorden + "&Observacion="+ Observacion + "&vtotal=" + vtotal);
	}
}

//finalizar orden
function cancelar(codorden) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Orden Cancelada Exitosamente.");
				window.location.href = "OrdenCompra.jsp";
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=13&codorden=" + codorden);
}


//Genera el reporte
function Reporte(codorden) {
	pagina = "Orden_ReporteEnEstudio.jsp?codorden=" + codorden;
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones	+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}


// Autocompleta el nombre del producto
function AutoCompleta2() {
	var nomproducto = document.getElementById("nomproducto").value;

	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("AutoProducto").innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=14&nomproducto=" + nomproducto); // Posting txtname to Servle
}


function Seleccion2(cod, diag) {
	document.getElementById("nomproducto").value = diag;
	document.getElementById("producto").value = cod;
	document.getElementById("AutoProducto").innerHTML = "";
}


// Ingresar los datos del proveedor
function Asignar() {
	var proveedor = document.getElementById("proveedor").value;
	var producto = document.getElementById("producto").value;

	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the servlet
												// name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("detalle").innerHTML = ajax.responseText;
			oculta("Asignar");
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=15&proveedor=" + proveedor + "&producto=" + producto); 
}

// Ingresar los datos del proveedor
function Asignar_Producto(linea,vble) {
	var usuario = document.getElementById("usu").value;
	var proveedor = document.getElementById("proveedor").value;
	var producto = document.getElementById("Prod"+linea).value;
	var descripcion = document.getElementById("desc"+linea).value;
	var precio = document.getElementById("prec"+linea).value;
	var iva = document.getElementById("iva"+linea).value;

	if ((producto == "") || (precio == "")|| (iva == "Seleccione")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Ingreso Exitoso.");
				PPP();
				//window.location.href = "OrdenCompra.jsp";
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		if (vble == "1") {
			ajax.send("valor=16&proveedor="+proveedor+"&producto=" + producto + "&Descripcion="+ descripcion + "&precio=" + precio + "&iva=" + iva+"&linea="+linea+"&usuario="+usuario);
		} else if (vble == "2") {
			ajax.send("valor=17&proveedor="+proveedor+"&producto=" + producto + "&Descripcion="+ descripcion + "&precio=" + precio + "&iva=" + iva+"&linea="+linea+"&usuario="+usuario);
		}

	}
}


//opciones de consulta
function Pro(val) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be
															// the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("inf").innerHTML = ajax.responseText;
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=18&val=" + val);
}


function buscarsubgrupo() {
	var grupo = document.getElementById("grupo").value;
		
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be
															// the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				if(ajax.responseText){
					document.getElementById("subgrupo").innerHTML = ajax.responseText;
					document.getElementById("btguardar").innerHTML = "<input id='guardar' type='button' value='Guardar' onClick='Crear_Producto(&quot;2&quot;)' class='styletxt'>";
				}else{
					document.getElementById("subgrupo").innerHTML = "";
					document.getElementById("btguardar").innerHTML = "<input id='guardar' type='button' value='Guardar' onClick='Crear_Producto(&quot;1&quot;)' class='styletxt'>";
				}
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=31&grupo=" + grupo);
}

function Grupo(val) {
	if(val == "1"){
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be
															// the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("cuenta").innerHTML = ajax.responseText;
				cuentas()
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=23");
	}else{
		document.getElementById("cuentas").innerHTML = "";
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be
															// the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("cuenta").innerHTML = ajax.responseText;
			//	cuentas()
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=28");
	}
	
	
	
}


function Crear_subgrupo() {
	var grupo = document.getElementById("grupo").value;
	var nom_grupo = document.getElementById("nom_grupo").value;
	var cuenta = document.getElementById("cuenta").value;
	var usu = document.getElementById("usu").value;
	var cuentac = document.getElementById("cuentac").value;
	
	if ((grupo == "Seleccione")&&(nom_grupo == "")&&(cuenta == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Ingreso Exitoso");
			document.getElementById("nom_grupo").value="";
			document.getElementById("cuenta").value=""
			document.getElementById("cuentab").value = "";
			document.getElementById("cuentac").value = "";
		//	cuentas()
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=29&grupo="+grupo+"&nom_grupo=" + nom_grupo+"&cuenta="+cuenta+"&cuentac="+cuentac+"&usuario="+usu); // Posting txtname to Servle
	}
}



function CPP() {
	var proveedor = document.getElementById("proveedor").value;
	var nompro = document.getElementById("nompro").value;
	if ((proveedor == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("resultadosf").innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=19&proveedor=" + proveedor+"&producto="+nompro); // Posting txtname to Servle
	}
}


//opciones de consulta
function PP(cod, estado) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrear", true); // getname will be
															// the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				CPP();
			}
		};
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=20&cod=" + cod+"&estado="+estado);
}



function cargar() {
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be
														// the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("detalle").innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=21");
}

function PPP() {
	var proveedor = document.getElementById("proveedor").value;
	var nompro = document.getElementById("nompro").value;
	if ((proveedor == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("resultadosf").innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=22&proveedor=" + proveedor+"&producto="+nompro); // Posting txtname to Servle
	}
}

function cuentas() {
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be
														// the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("cuentas").innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=24");
}

function Crear_grupo() {
	var nom_grupo = document.getElementById("nom_grupo").value;
	var cuenta = document.getElementById("cuenta").value;
	var cuentac = document.getElementById("cuentac").value;
	var usu = document.getElementById("usu").value;
	
	if ((nom_grupo == "")&&(cuenta == "")) {
		alert("Por favor llenar todos los campos requeridos.");
	} else {
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Ingreso Exitoso");
			document.getElementById("nom_grupo").value="";
			document.getElementById("cuenta").value=""
			document.getElementById("cuentab").value = "";
			cuentas()
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=25&nom_grupo=" + nom_grupo+"&cuenta="+cuenta+"&usuario="+usu+"&cuentac="+cuentac); // Posting txtname to Servle
	}
}

function verificar() {
	var nom_grupo = document.getElementById("nom_grupo").value;
		
	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if(ajax.responseText=="1"){
				alert("El nombre del grupo ya se encuentra");
				document.getElementById("nom_grupo").value="";
				document.getElementById("nom_grupo").focus();
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=26&nom_grupo=" + nom_grupo); // Posting txtname to Servle
}
function AutoCompletas() {
	var cuentab = document.getElementById("cuentab").value;

	ajax = getXMLObject();
	ajax.open("POST", "ControlCrear", true); 
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById("AutoCuenta").innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=27&cuenta=" + cuentab); 
}

function Seleccionc(c, cod, nom) {
	alert("c: "+c+" ,cod: "+cod+" ,nom: "+nom);
	document.getElementById("cuentab").value = cod +" - "+nom;
	document.getElementById("cuenta").value = cod;
	document.getElementById("cuentac").value = c;
	document.getElementById("AutoCuenta").innerHTML = "";
}







/*validaciones */
function validarEmail() {
	var email = document.getElementById("email").value;
	expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if (!expr.test(email)) {
		alert("Error: La dirección de correo " + email + " es incorrecta.");
		document.getElementById('email').focus();
	}
}

// Función que permite solo Números
function ValidaSoloNumeros() {
	if ((event.keyCode < 48) || (event.keyCode > 57))
		event.returnValue = false;
}

// Función que permite solo texto
function txNombres() {
	if ((event.keyCode != 32) && (event.keyCode < 65) || (event.keyCode > 90)
			&& (event.keyCode < 97) || (event.keyCode > 122))
		event.returnValue = false;
}

// oculta el td con el identificador
function oculta(id) {
	var elDiv = document.getElementById(id);
	elDiv.style.display = 'none';
}

// oculta el td con el identificador
function aparecer(id) {
	var elDiv = document.getElementById(id);
	elDiv.style.display = 'inline';
}

// funcion de calendario en español
$( function($) {
	$.datepicker.regional['es'] = {
		closeText :'Cerrar',
		prevText :'<Ant',
		nextText :'Sig>',
		currentText :'Hoy',
		monthNames : [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio',
				'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre',
				'Diciembre' ],
		monthNamesShort : [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul',
				'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
		dayNames : [ 'Domingo', 'Lunes', 'Martes', 'Miercoles', 'Jueves',
				'Viernes', 'Sabado' ],
		dayNamesShort : [ 'Dom', 'Lun', 'Mar', 'Mie', 'Juv', 'Vie', 'Sab' ],
		dayNamesMin : [ 'Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa' ],
		weekHeader :'Sem',
		dateFormat :'dd-mm-yy',
		showAnim :'clip',
		changeMonth :true,
		changeYear :true,
		showWeek :true,
		firstDay :1,
		isRTL :false,
		showMonthAfterYear :false,
		yearSuffix :''
	};
	$.datepicker.setDefaults($.datepicker.regional['es']);
});

$( function() {
	$("#fec_entrega").datepicker( {
		minDate :"+0D"
	});
});

// funci de la fecha
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio) {
	var sep = "-";
	if (d.valant != d.value) {
		val = d.value
		largo = val.length
		ini = val.substring(0, 2);
		if ((ini > 31) || (ini == "00")) {
			val = d.value = "01";
		}
		val = val.split(sep);

		val2 = ''
		for (r = 0; r < val.length; r++) {
			val2 += val[r]
		}
		ini = val2.substring(2, 4);
		if ((ini == "04") || (ini == "06") || (ini == "09") || (ini == "11")) {
			x = val2.substring(0, 2);
			if (x == "31") {
				val2 = "30";
				val2 = val2 + ini;
			}
		}
		if ((ini > 12) || (ini == "00")) {
			mescj = val2.substring(0, 2);
			val2 = mescj + "01";
		}

		ano = val2.substring(4, 8);
		dia = val2.substring(0, 2);

		if (ini == "02") {
			if ((dia == "30") || (dia == "31") || (dia == "29")) {
				if (ano.length == 4) {
					if ((ano % 100 != 0)
							&& ((ano % 4 == 0) || (ano % 400 == 0))) {
						val2 = "29";
						val2 = val2 + ini;
					} else {
						val2 = "28";
						val2 = val2 + ini;
					}
					val2 = val2 + ano;
				}
			}
		}

		/*
		 * /////////////////////////////////////////////////// //Validar fecha
		 * mayor que la fecha actual
		 * 
		 * if(ano.length==4){ if (ano==annio) { if(ini==mes){
		 * if((dia==dias)||(dia<dias)){ alert("La fecha de Vencimiento no puede
		 * ser menor o igual a la fecha Actual"); val2=''; } }else{ if(ini<mes){
		 * alert("La fecha de Vencimiento no puede ser menor o igual a la fecha
		 * Actual"); val2=''; } } } if (ano<annio) { alert("La fecha de
		 * Vencimiento no puede ser menor o igual a la fecha Actual"); val2=''; } }
		 * 
		 * ///////////////////////////////////////////////////
		 */
		if (nums) {
			for (z = 0; z < val2.length; z++) {
				if (isNaN(val2.charAt(z))) {
					letra = new RegExp(val2.charAt(z), "g")
					val2 = val2.replace(letra, "")

				}
			}
		}

		val = ''
		val3 = new Array()
		for (s = 0; s < pat.length; s++) {
			val3[s] = val2.substring(0, pat[s])
			val2 = val2.substring(pat[s])
		}

		for (q = 0; q < val3.length; q++) {
			if (q == 0) {
				val = val3[q]
			} else {
				if (val3[q] != "") {
					val += sep + val3[q]
				}
			}
		}
		d.value = val
		d.valant = val
	}
	var elEvento = window.event; // arguments[0] ||
	var tecla = elEvento.keyCode;
	if (tecla == 13) {
		document.getElementById("ref0").focus();

	}

}

// /////////////////
var fechafilter = /^\d{2,2}\-\d{2,2}\-\d{4,4}$/;
function vfi() {

	var e = document.getElementById("FI").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha inicial es Invalida");
		document.getElementById("FI").value = "";
	}
	return returnval
}

function vff() {

	var e = document.getElementById("FF").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha final es Invalida");
		document.getElementById("FF").value = "";
	}
	return returnval
}
// ////////////////////////
var fechafilter1 = /^\d{2,2}\-\d{2,2}\-\d{4,4}$/;
function checkfecha(c) {
	var cc = "fechai" + c;
	var e = document.getElementById(cc).value;
	var returnval = fechafilter1.test(e)
	if (returnval == false) {
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	return returnval
}

var fechafilters = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function checkfechaf(c) {
	var cc = "fechaf" + c;
	var e = document.getElementById(cc).value;
	var returnval = fechafilters.test(e)
	if (returnval == false) {
		alert("La fecha final es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	return returnval
}