function getXMLObject() // XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP") // For Old Microsoft
		// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") // For Microsoft
			// IE 6.0+
		} catch (e2) {
			xmlHttp = false // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object
// ////////////////////////////////////////////////////////////////////////////////////////////////////////
function DevolverFacturaRadEmi(){
	var numeroFactura=document.getElementById("txtNumFactura").value;	
	var Motivo=document.getElementById("cmbMotivoDev").value;
	var txtCodusuario=document.getElementById("txtCodusuario").value;
	var exa = document.form1.cmbMotivoDev.selectedIndex;
	var NombreMotivo=document.form1.cmbMotivoDev.options[exa].text;
	if(numeroFactura==""){
		alert("Digite el Numero Factura");
	}else{
		if(Motivo=="Seleccione"){
			alert("Seleccione Motivo de Devolucion.");
		}else{
			var opcion=confirm("Desea devolver la factura "+numeroFactura+" de radicada a emitida?");	
			if(opcion){
				ajax=getXMLObject();
				ajax.open("POST","ControlDevolucion",true); // getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {
						alert(ajax.responseText);
						window.location.reload();
					}
				};
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=DFRE&numeroFactura="+numeroFactura+"&Motivo="+Motivo+"&txtCodusuario="+txtCodusuario+"&NombreMotivo="+NombreMotivo); // Posting txtname to Servlet
			}else{
				
			}
			//alert("numeroFactura="+numeroFactura+" Motivo="+Motivo );
		}
	}	
}


function OpcionEnvioFact(val, opcion) {
	var contenido=document.getElementById('Opcion');
	if(val==null){
		
		var radioButtons = document.getElementsByName("radiobutton");
		for (var x = 0; x < radioButtons.length; x++) {
			if (radioButtons[x].checked) {
				var val=radioButtons[x].id;  
				
			}	     
		}
	}
	
	if(opcion==null){
		opcion="";
	}
	
	ajax=getXMLObject();
	ajax.open("POST","ControlDevolucion",true); // getname will be the
													// servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			
			contenido.innerHTML = ajax.responseText;	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor="+val+"&opcion="+opcion); // Posting txtname to Servlet
	
}


function RadiosDevFact() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlDevolucion",true); // getname will be
															// the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=00D"); // Posting txtname to Servlet
}


function BuscarCuentaDevolucion(ri) {
	var contenido = document.getElementById('resultadosf');
	//alert("entrado a BuscarCuentaDevolucion valor ri "+ri);
	var v1 = document.getElementById("txtFact").value;
	var v22 = document.getElementById("Ent").selectedIndex;
	var v2 = document.getElementById("Ent").options[v22].value;
	var v33 = document.getElementById("tdoc").selectedIndex;
	var v3 = document.getElementById("tdoc").options[v33].text;
	var v4 = document.getElementById("txtDoc").value;
	var v5 = document.getElementById("txtNom").value;
	var v6 = document.getElementById("txtPa").value;
	var v7 = document.getElementById("txtSa").value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;
	var v10 = document.getElementById("CDC").value;
	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
			&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "") && (v10 == "")) {
		alert("Debe Seleccionar al menos un criterio de busqueda!!!");
	} else {
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlDevolucion", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showCuentaDev&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9 +"&v10=" + v10 + "&ri=" +ri); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
	}
}


function mostrarDetDev(consCTA,tipo){
	//alert("consCTA: "+consCTA);
	//alert("opcion :"+opcion);
	pagina = "fact_ReporteCtaDevolucion.jsp?venc="+consCTA;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
	if(tipo==1){
	BuscarFactDev(1)
	}else{ 
	BuscarCuentaDevolucion(1);
	}
	
}


function devFact(ri){
	//alert("entramos a devolver factura ");
	var opcion=confirm("Esta seguro de devolver la factura a su estado anterior?" );
	if(opcion){
			var frad = "";
			var no=0;
			var eqEntidad = true;
			var childTablaFact = document.getElementById('listFact').childNodes[0].childNodes;
			var factSeleccionadas = "";
			var totalFact = 0;
			var val=0;
			var valornc="";
			
			var EntParaComp = "";
			for (i=0;i<childTablaFact.length-1;i++){
				if(document.getElementById('chkEF'+i).checked){
					factSeleccionadas+=document.getElementById('chkEF'+i).value+"_";
					totalFact+=parseInt(document.getElementById('vEF'+i).childNodes[0].nodeValue);
					EntParaComp+=document.getElementById('ent'+i).childNodes[0].nodeValue+"_";
					valornc+=document.getElementById('NC'+i).childNodes[0].nodeValue+"_";
					frad+=encodeURIComponent(document.getElementById('frad'+i).value+"_");
					if (document.getElementById('frad'+i).value==""){
						//alert("valor de frad "+frad+"Valor de val"+val);
						if(frad!="_"){
								if(no!=1){val=1;}
						}else{
							no=no+1;
							val=0;
						}
					}else{no=0;
					val=0;}
								
				}
		
			}
			
			
			factSeleccionadas=factSeleccionadas.substring(0, factSeleccionadas.length-1);
			valornc=valornc.substring(0, valornc.length-1);
			EntParaComp=EntParaComp.substring(0, EntParaComp.length-1);
			var detEntidades = EntParaComp.split("_");
			for (j=1;j<detEntidades.length;j++){
				if(detEntidades[0]!=detEntidades[j]){
					eqEntidad = false;
					j = detEntidades.length
				}
			}
		
			var valorLetras = letras(totalFact+"");
			
			if(factSeleccionadas!=""&&eqEntidad){
				//alert("factSeleccionadas: "+factSeleccionadas+"  totalFact: "+totalFact+"  EntParaComp: "+EntParaComp);
			// if(no==0){
				var usuario = document.getElementById('txtCodusuario').value;
				var motivo=document.getElementById('motivo').value;
				if(motivo!=("seleccione")){
				ajax = getXMLObject();
				ajax.open("POST", "ControlDevolucion", true); // getname will be the
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						// OpcionEnvioFact('sfe');
						var cta = ajax.responseText;
						//alert ("Aqui me traje el consecutivo de la cuenta: "+cta);
						
						alert("Proceso exitoso");
						
						
						if(ri==1){
							mostrarDetDev(cta,1);
							//alert(cta+"ctaaaaa");
							//EliminarRegCta(factSeleccionadas,cta);
						
							
						}
						
					}
				}
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				//alert("valor=devFact&facturas="+factSeleccionadas+"&vFacturas="+totalFact+"&vlFacturas="+valorLetras+"&usuario="+usuario+"&frad="+frad+"&ri="+ri+"&motivo="+motivo);
				//alert("valor de no "+no+"valor de val"+val);
				if(val!=0){
					alert("Le hace falta una observacion por digitar");
				}else{
					if(no!=0){
						ajax.send("valor=devFact&facturas="+factSeleccionadas+"&vFacturas="+totalFact+"&vlFacturas="+valorLetras+"&usuario="+usuario+"&frad="+0+"&ri="+ri+"&motivo="+motivo+"&NC="+valornc);
						}else{
							ajax.send("valor=devFact&facturas="+factSeleccionadas+"&vFacturas="+totalFact+"&vlFacturas="+valorLetras+"&usuario="+usuario+"&frad="+frad+"&ri="+ri+"&motivo="+motivo+"&NC="+valornc);
						}
				}
				//}else{alert("Debe escribir el motivo de la devolucion de la factura");}
				}else{alert("Debe escoger un motivo de devolucion");}
			 }else{
				if(factSeleccionadas==""){
					alert("Debe seleccionar por lo menos una factura");
				}
				if(!eqEntidad){
					alert("Para generar el consolidado de las facturas a enviar \n" +
						  "tienen que pertenecer todas las facturas a la misma entidad");
				}
				
			}
	}else{		
	}
	
}
 
function EliminarRegCta(factSeleccionadas,cta){
	ajax=getXMLObject();
	//alert("entrando a EliminarRegCta"+factSeleccionadas);
	var contenido = document.getElementById('resultadosf');
	var opcion=confirm("Desea Eliminar las facturas seleccionadas del registro de cta Cobros ?");
	if(opcion){
		ajax.open("POST", "ControlDevolucion", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				contenido.innerHTML = ajax.responseText;
				
				document.getElementById('btnEnvFact').innerHTML="<table><tr><td colspan='10' align='center'><input type='button' value='Eliminar Registros' onclick='EliminarReg(1,"+cta+")'/></td></tr></table>";
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=EliminarRegCta&facturas="+factSeleccionadas);
	}else{
		mostrarDetDev(cta,1);
		//BuscarFactDev(1);
		
	}
}

function EliminarReg(ri,cta){
	ajax=getXMLObject();
	var childTablaFact = document.getElementById('ListElim').childNodes[0].childNodes;
	var factSeleccionadas = "";
	var consecutivo="";
	var usuario = document.getElementById('txtCodusuario').value;
	for (i=0;i<childTablaFact.length-1;i++){
		if(document.getElementById('chkDevF'+i).checked){
			factSeleccionadas+=document.getElementById('chkDevF'+i).value+"_";
			consecutivo+=document.getElementById('cons'+i).childNodes[0].nodeValue+"_";			
		}
		if(factSeleccionadas!=""){
		ajax.open("POST", "ControlDevolucion", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				//alert(ajax.reponseText);
				alert("Eliminacion Exitosa");
				BuscarFactDev(ri);
				mostrarDetDev(cta,1);
		}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=EliminarReg&factSeleccionadas="+factSeleccionadas+"&consecutivo="+consecutivo+"&usuario="+usuario);
		}else{
		 alert("Debe seleccionar al menos una factura");
		}
	}
}

function BuscarFactDev(ri) {

	var contenido = document.getElementById('resultadosf');
	//alert("entrado a BuscarFactDev valor ri "+ri);
	var v1 = document.getElementById("txtFact").value;
	var v22 = document.getElementById("Ent").selectedIndex;
	var v2 = document.getElementById("Ent").options[v22].value;
	var v33 = document.getElementById("tdoc").selectedIndex;
	var v3 = document.getElementById("tdoc").options[v33].text;
	var v4 = document.getElementById("txtDoc").value;
	var v5 = document.getElementById("txtNom").value;
	var v6 = document.getElementById("txtPa").value;
	var v7 = document.getElementById("txtSa").value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;
	var v10 =document.getElementById("CDC").value;

	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
			&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "") && (v10=="")) {
		alert("Debe Seleccionar al menos un criterio de busqueda!!!");
	} else {
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				document.getElementById('resultadosf').innerHTML='<p>Cargando Lista de Facturas... No Cierre La Ventana...</p><img src="Imagenes/ani.gif">';
				ajax.open("POST", "ControlDevolucion", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						if(ri==1){
							document.getElementById('btnEnvFact').innerHTML="<table><tr><td colspan='10' align='center'><input type='button' value='Devolver' onclick='devFact(1)'/></td></tr></table>";
						}	// medicosPYP();
					}
				}
				/*alert("valor=showFactD&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9 + "&ri=" + ri + "&v10="+ v10);*/
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showFactD&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
				+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
				+ "&v8=" + v8 + "&v9=" + v9 + "&ri=" + ri + "&v10="+ v10);
			}
		}
	}
}



function vistarepfactDev(cons){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("vista rep de nota credito"+ftent);
	//alert("valor=reportenc&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"ftent=&quot;"+ftent+"&quot;");
	ajax.open("POST","ControlDevolucion",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=reportedev&cons="+cons);
}



function letras(valor) {
	var convertida = "";
	UNIDADES = [ "", "UN ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ",
			"SIETE ", "OCHO ", "NUEVE ", "DIEZ ", "ONCE ", "DOCE ", "TRECE ",
			"CATORCE ", "QUINCE ", "DIECISEIS", "DIECISIETE", "DIECIOCHO",
			"DIECINUEVE", "VEINTE" ];
	DECENAS = [ "VENTI", "TREINTA ", "CUARENTA ", "CINCUENTA ", "SESENTA ",
			"SETENTA ", "OCHENTA ", "NOVENTA ", "CIEN " ];
	CENTENAS = [ "CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ",
			"QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ",
			"NOVECIENTOS " ];

	if (valor > 999999999) {
		alert("El numero es mayor de 999.999.999, No es posible convertirlo");
	} else {
		var sw = 0;
		// Descomponemos en millones
		var millon = "";
		if (valor.length > 6) {
			if (valor.length == 7) {
				millon = valor.substring(0, 1);
				valor = valor.substring(1, 9);
			} else {
				if (valor.length == 8) {
					millon = valor.substring(0, 2);
					valor = valor.substring(2, 9);
				} else {
					if (valor.length == 9) {
						millon = valor.substring(0, 3);
						valor = valor.substring(3, 9);
					}
				}
			}
			if (millon == 1)
				convertida = convertida + " UN MILLON ";
			if (millon > 1) {

				var millon1 = letranumero(millon) + " MILLONES ";
				convertida = convertida + millon1;
			}
			sw = 1;
		}

		// Descomponemos los miles
		var miles = "";
		if (valor.length > 3) {
			if (valor.length == 4) {
				miles = valor.substring(0, 1);
				valor = valor.substring(1, 6);
			} else {
				if (valor.length == 5) {
					miles = valor.substring(0, 2);
					valor = valor.substring(2, 6);
				} else {
					if (valor.length == 6) {
						miles = valor.substring(0, 3);
						valor = valor.substring(3, 6);
					}
				}
			}
			// alert("miles"+miles);
			if ((miles == 1) && (sw == 0))
				convertida = convertida + " MIL ";

			if ((miles == 1) && (sw == 1))
				convertida = convertida + "UN MIL ";

			if (miles > 1) {

				var miles1 = letranumero(miles) + " MIL ";
				convertida = convertida + miles1;
			}
		}

		// Descomponemos unidades
		var cientos = "";
		if (valor.length > 0) {
			cientos = valor.substring(0, 1)
			if (valor.length > 1) {
				cientos = valor.substring(0, 2)
			}
			if (valor.length > 2) {
				cientos = valor.substring(0, 3)
			}

			// alert("cientos"+cientos);
			if (cientos == 1)
				convertida = convertida + " UN ";
			if (cientos > 1) {

				var cientos1 = letranumero(cientos) + " ";
				convertida = convertida + cientos1;
			}
		}

		if (millon + miles + cientos == 0) {
			convertida = convertida + " CERO ";
		}

		convertida = convertida + "PESOS M/L";

		// alert("convertida "+convertida);

		return (convertida);

	}
}

function letranumero(numero) {

	// alert("Entramos a letranumero: "+numero+" longitud: "+numero.length
	// udc: "+udc);

	if (numero.length > 3)
		alert("La longitud maxima debe ser 3 digitos");

	var retornar = "";

	/** ******************* en letras************************ */
	if (numero.length > 0) {// if1
		// alert("ya viste");
		var sw = 0;
		if (numero.length > 2) {
			var k = numero.substring(0, 1);
			if (k != 0)
				retornar = CENTENAS[k - 1];

			if (numero == 100) {
				retornar = "CIEN";
				sw = 1;
			}

			numero = numero.substring(1, 3);
		}

		if (sw == 0) {
			if (numero.length > 1) {
				var k2 = numero.substring(0, 1);
				// alert("k2 "+k2);
				if (k2 == 0) {
					var k3 = numero.substring(1, 2); // verifica si hay un
					// cero antes
				} else {
					var k3 = numero.substring(0, 2);
				}// para llamar a la unidad y no comparar con 08 x ejemplo
				// sino con 8
				// alert("k3 "+k3);
				if (k3 > 20) {
					var y = numero.substring(0, 1);
					var z = numero.substring(1, 2);
					if ((k3 > 30) && (z != 0)) {
						retornar = retornar + DECENAS[y - 2] + "Y "
								+ UNIDADES[z];
					} else {
						retornar = retornar + DECENAS[y - 2] + UNIDADES[z];
					}
				} else {
					retornar = retornar + UNIDADES[k3];
				}
			}

			if (numero.length == 1) {
				var k4 = numero.substring(0, 1);
				retornar = retornar + UNIDADES[k4];
			}
		}// fin if sw

	}// finif1

	return retornar;
}