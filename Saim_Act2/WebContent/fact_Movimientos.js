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
// ////////////////////////////////////////////////////////////////Glosas(x) ////////////////////////////////////////
function checknumcantidad(c) {
	//alert("checknumcantidad");
	var cc = "cant" + c;
	var e = document.getElementById(cc).value;
	var returnval = emailfilternot.test(e)

	var x = e.substr(0, 1); 
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}

	if (returnval == false) {
		var l = e.length;
		var xx = e.substr(0, l-1);
		//alert("l: "+l+"x: "+x);
		
		//alert("Este campo acepta solo valores númericos!!!");
		document.getElementById(cc).value = xx;
		document.getElementById(cc).focus();
	}
	return returnval
}


function ReporteACPF() {//////////////////////////////
	var ssql="";
	//hacer el reporte de anulacion de factura
	//necesito pasar los parametros y la consulta creada como un sql para complementar la consulta del jasper
	var vue = document.getElementById("cmbuser").value;
	var vuet = document.getElementById("cmbuser").selectedIndex;
	var vuett = document.getElementById("cmbuser").options[vuet].text;
	var v8e = document.getElementById("FI").value;
	var v9e = document.getElementById("FF").value;
	//alert(vuet+"-"+vuett);
	
	if((v8e != "")&&(v9e != "")){
		var dv8=v8e.substring(0, 2);
		var mv8=v8e.substring(3, 5);
		var av8=v8e.substring(6, 10);
		var fv8=av8+"-"+mv8+"-"+dv8;
		
		var dv9=v9e.substring(0, 2);
		var mv9=v9e.substring(3, 5);
		var av9=v9e.substring(6, 10);
		var fv9=av9+"-"+mv9+"-"+dv9;
		
	}
	
	var v22e = document.getElementById("Ent").selectedIndex;
	var v2e = document.getElementById("Ent").options[v22e].value;
	if(v2e != "Seleccione"){ssql=ssql+" AND ef.cod_eps='"+v2e+"'";}
	
	if(v2e != "Seleccione"){v2e="";}
	//alert (ssql);
	if((v8e!="")&&(v9e!="")&&(vue!="Seleccione")){
	//alert(venc);
	pagina = "fact_ReporteCentrosPF.jsp?centro=" + vue+"&fi="+fv8+"&ff="+fv9+"&eps="+ssql+"&ct="+vuett;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);


	}
	}




function AuditarCPF(){
	// alert("consultarFAct");
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=30CPF"); // Posting txtname to Servlet
}




function CFactCPF() {
//alert(CFactCPF);
	var contenido = document.getElementById('resultadosf');
	var vu = document.getElementById("cmbuser").value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;
	var v22 = document.getElementById("Ent").selectedIndex;
	var v2 = document.getElementById("Ent").options[v22].value;
//	alert(v8+" - "+v9);
	if ((v8 == "") || (v9 == "")) {
		alert("Debe seleccionar el rango de fechas!!!");
	} else {
					
	if (vu == "Seleccione") {
		alert("Debe Selccionar un Centro Principal!!!");
	} else {
			ajax = getXMLObject();
				ajax.open("POST", "ControlMovimientos", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText
						// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=31CPF&vu=" + vu+ "&v8=" + v8 + "&v9=" + v9+ "&v2=" + v2); // Posting txtname to

		}
	}
}



function limita(maximoCaracteres,elemento) {
	var key=window.event.keyCode;
	if (key < 48 || key > 57){
		window.event.keyCode=0;
	}else{

		if(elemento.value.length >= maximoCaracteres ) {
            return false;
		}else{
            return true;
		}
    }
}

//ce : opcion para consultar externa
function Radio(ce) {
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			document.getElementById('desc0').focus();
			if(ce=='CE'){
				document.getElementById('cabecera2').innerHTML = 'Movimiento Diario';
			}		
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1"); // Posting txtname to Servlet
}


/*

function Radioh(op,venc,lot) {
	// alert(venc);
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			document.getElementById('desc0').focus();
			if(op=="0"){
				ajax1 = getXMLObject();
				ajax1.open("POST", "ControlMovimientos", true); // getname will
																// be the
				ajax1.onreadystatechange = function() {
					if (ajax1.readyState == 4) {
						var datos = ajax1.responseText;
						var detDatos = datos.split("|");
						document.getElementById("desch0").value = detDatos[2];
						document.getElementById("desc0").value = detDatos[1];
						document.getElementById("eps0").value = detDatos[0];
						document.getElementById("adm0").value = venc;
						document.getElementById("epsh0").value = lot;
						Detalleh(venc, lot);
					}
				};
				ajax1.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax1.send("valor=dtPac&venc="+venc);
				
			}
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1h"); // Posting txtname to Servlet
}

*/


function Radioh(op,venc,lot,enca) {
	//alert("Radioh "+venc);
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	//alert("Radioh venc "+venc+" lot"+lot+" enca "+enca);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			document.getElementById('desc0').focus();
			if(op=="0"){
				ajax1 = getXMLObject();
				ajax1.open("POST", "ControlMovimientos", true); // getname will
					//alert(" Radioh 2 "+venc+" enca"+enca);											// be the
				ajax1.onreadystatechange = function() {
					if (ajax1.readyState == 4) {
						var datos = ajax1.responseText;
						var detDatos = datos.split("|");
						document.getElementById("desch0").value = detDatos[2];
						document.getElementById("desc0").value = detDatos[1];
						document.getElementById("epsh0")[0].text = detDatos[0]; //Hay q buscar el value en el select epsh0no existe
						document.getElementById("adm0").value = venc;
						document.getElementById("epsh0")[0].value = lot;
						if(detDatos[4]=="null"){detDatos[4]="Activo";}
						document.getElementById("FechaIngreso").value= detDatos[3];
						document.getElementById("FechaEgreso").value= detDatos[4];
						document.getElementById("NumAutoriza").value= detDatos[5];
						fepssh(lot,detDatos[0],venc,enca,"0")
						//Detalleh(venc, lot);
					}
				};
				ajax1.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax1.send("valor=dtPac&enca="+enca);
				
			}
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1h"); // Posting txtname to Servlet
}



function fepss(ceps,eps,venc){
	var contenido = document.getElementById('depss');
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc)
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			 contenido.innerHTML = ajax.responseText;
			 Detalle(venc,ceps);
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1eps&ceps="+ceps+"&eps="+eps); // Posting
}



function CambiardeEntidad(ceps,eps){
	
	var indice =  document.getElementById("epsh0").selectedIndex;
	var cepsn = document.getElementById('epsh0').options[indice].value;
	var epsn = document.getElementById('epsh0').options[indice].text;
	var adm = document.getElementById('adm0').value;
	
	if (confirm("Desea crear una factura con otra entidad?")) {
	//alert("Nueva entidad: "+cepsn+" - "+epsn+" mi anterior eps era:"+ceps+" - "+eps+" admision:"+adm);
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			// contenido.innerHTML = ajax.responseText;
			fepss(cepsn,epsn,adm);
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2eps&ceps="+cepsn+"&eps="+epsn+"&adm0="+adm); // Posting*/
	
	

	}else{
		document.getElementById('epsh0').value=ceps;
		document.getElementById('epsh0').text=eps;
	}
	//var contenido = document.getElementById('depss');
	
}

function limpiarmovimientos(){
	var adm0 = document.getElementById('adm0').value;
	if(adm0!=""){
		//Radio();
		window.location.reload();
	}
}

function fepssh(ceps,eps,venc,ca,hoa){
	var contenido = document.getElementById('depssh');
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	//alert("valor=1heps&ceps="+ceps+"&eps="+eps);
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			 contenido.innerHTML = ajax.responseText;
			 Detalleh(venc,ceps);
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1heps&ceps="+ceps+"&eps="+eps); // Posting
}



function CambiardeEntidadh(ceps,eps){
	
	var indice =  document.getElementById("epsh0").selectedIndex;
	var cepsn = document.getElementById('epsh0').options[indice].value;
	var epsn = document.getElementById('epsh0').options[indice].text;
	var adm = document.getElementById('adm0').value;
	
	if (confirm("Desea crear una factura con otra entidad?")) {
	//alert("Nueva entidad: "+cepsn+" - "+epsn+" mi anterior eps era:"+ceps+" - "+eps+" admision:"+adm);
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			// contenido.innerHTML = ajax.responseText;
			fepssh(cepsn,epsn,adm);
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2heps&ceps="+cepsn+"&eps="+epsn+"&adm0="+adm); // Posting*/
	
	

	}else{
		document.getElementById('epsh0').value=ceps;
		document.getElementById('epsh0').text=eps;
	}
	//var contenido = document.getElementById('depss');
	
}


/*
function RadiohA(op,venc,lot) {
	// alert(venc);
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			document.getElementById('desc0').focus();
			if(op=="0"){
				ajax1 = getXMLObject();
				ajax1.open("POST", "ControlMovimientos", true); // getname will
																// be the
				ajax1.onreadystatechange = function() {
					if (ajax1.readyState == 4) {
						var datos = ajax1.responseText;
						var detDatos = datos.split("|");
						document.getElementById("desch0").value = detDatos[2];
						document.getElementById("desc0").value = detDatos[1];
						document.getElementById("eps0").value = detDatos[0];
						document.getElementById("adm0").value = venc;
						document.getElementById("epsh0").value = lot;
						fepssh(lot,detDatos[0],venc,enca,"1");
					}
				};
				ajax1.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax1.send("valor=dtPac&venc="+venc);
				
			}
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1hA"); // Posting txtname to Servlet
}

*/


function RadiohA(op,venc,lot,enca) {
	// alert(venc);
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			document.getElementById('desc0').focus();
			if(op=="0"){
				ajax1 = getXMLObject();
				ajax1.open("POST", "ControlMovimientos", true); // getname will
																// be the
				ajax1.onreadystatechange = function() {
					if (ajax1.readyState == 4) {
						var datos = ajax1.responseText;
						var detDatos = datos.split("|");
						document.getElementById("desch0").value = detDatos[2];
						document.getElementById("desc0").value = detDatos[1];
						document.getElementById("epsh0")[0].text = detDatos[0];
						document.getElementById("adm0").value = venc;
						document.getElementById("epsh0")[0].value = lot;
						
						if(detDatos[4]=="null"){detDatos[4]="Activo";}
						document.getElementById("FechaIngreso").value= detDatos[3];
						document.getElementById("FechaEgreso").value= detDatos[4];
						document.getElementById("NumAutoriza").value= detDatos[5];
						
						fepssh(lot,detDatos[0],venc,enca,"1")
						//Detalleh(venc, lot);
					}
				};
				ajax1.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax1.send("valor=dtPac&enca="+enca);
				
			}
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1hA"); // Posting txtname to Servlet
}


function Radioce() {
	// alert("cesar");
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1ce"); // Posting txtname to Servlet
}

function Radio2() {
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8"); // Posting txtname to Servlet
}



function Radio3(op,venc,lot,enca) {
	//alert("Radio 3 "+op+" - "+venc+" - "+lot+" - "+enca);
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			if(op=="0"){
				ajax1 = getXMLObject();
				ajax1.open("POST", "ControlMovimientos", true); // getname will
																// be the
				ajax1.onreadystatechange = function() {
					if (ajax1.readyState == 4) {
						var datos = ajax1.responseText;
						var detDatos = datos.split("|");
						document.getElementById("desch0").value = detDatos[2];
						document.getElementById("desc0").value = detDatos[1];
						document.getElementById("eps0").value = detDatos[0];
						document.getElementById("adm0").value = venc;
						document.getElementById("epsh0").value = lot;
						if(detDatos[4]=="null"){detDatos[4]="Activo";}
						document.getElementById("FechaIngreso").value= detDatos[3];
						document.getElementById("FechaEgreso").value= detDatos[4];
						document.getElementById("NumAutoriza").value= detDatos[5];
						Factura(venc, lot, enca);
					}
				};
				ajax1.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax1.send("valor=dtPac&enca="+enca);
				
			}
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8h"); // Posting txtname to Servlet
}



function Radio3A(op,venc,lot,enca) {
	
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			if(op=="0"){
				ajax1 = getXMLObject();
				ajax1.open("POST", "ControlMovimientos", true); // getname will
																// be the
				ajax1.onreadystatechange = function() {
					if (ajax1.readyState == 4) {
						var datos = ajax1.responseText;
						var detDatos = datos.split("|");
						document.getElementById("desch0").value = detDatos[2];
						document.getElementById("desc0").value = detDatos[1];
						document.getElementById("eps0").value = detDatos[0];
						document.getElementById("adm0").value = venc;
						document.getElementById("epsh0").value = lot;
						
						if(detDatos[4]=="null"){detDatos[4]="Activo";}
						document.getElementById("FechaIngreso").value= detDatos[3];
						document.getElementById("FechaEgreso").value= detDatos[4];
						document.getElementById("NumAutoriza").value= detDatos[5];
						Factura(venc, lot, enca);
					}
				};
				ajax1.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax1.send("valor=dtPac&enca="+enca);
				
			}
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8hA"); // Posting txtname to Servlet
}

function Radio4() {
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8ce"); // Posting txtname to Servlet
}

function Radios(venc, lot, enca,object,opcion,tipoFact,amb) {
	// alert(object);
	ajax=getXMLObject();
	var contenido=document.getElementById('resul');

   
			ajax.open("POST","ControlMovimientos",true); // getname will be
															// the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					var resp = ajax.responseText;
					var detResp = resp.split("|");
					contenido.innerHTML = detResp[0];
					var vh=document.getElementById('vh').value;
					var subtl=letras(vh);
					// alert(subtl);
					Radios5(venc, lot, enca, subtl,detResp[1],object,opcion,tipoFact,amb);
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=23&venc="+venc+"&lot="+lot+"&enca="+enca); // Posting
																		// txtname
																		// to
																		// Servlet
			
}

function Radios5(venc, lot, enca, subtl,estAdm,object,opcion,tipoFact,amb) {
	// alert(lot);
	// tipoFact: 0 Hospitalizacion, 1 Urgencias
	// alert("Entramos a funcion Detalle venc "+venc+" y lot: "+lot);
	ajax=getXMLObject();
	// if(document.getElementById('conResul')==[object]){
	if(tipoFact==0){
		document.getElementById('conResul').style.display='inline';
	}
	// }
	// alert(object);
	var contenido=document.getElementById('resul');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlMovimientos",true); // getname will be
															// the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					// alert(ajax.responseText);
					// document.getElementById(object).style.display='inline';
					document.getElementById(object).innerHTML = ajax.responseText;	
					// document.getElementById('conResul').appendChild(document.getElementById(object));
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			//alert("valor="+val+"&venc="+venc+"&lot="+lot+"&enca="+enca+"&subtl="+subtl+"&estAdm="+estAdm+"&opcion="+opcion+"&tipoFact="+tipoFact+"&amb="+amb); // Posting
			ajax.send("valor="+val+"&venc="+venc+"&lot="+lot+"&enca="+enca+"&subtl="+subtl+"&estAdm="+estAdm+"&opcion="+opcion+"&tipoFact="+tipoFact+"&amb="+amb); // Posting
																																						// txtname
																																						// to
																																						// Servlet
		}	     
	}
}

function formatNumber(num, prefix) {
	num = Math.round(parseFloat(num) * Math.pow(10, 2)) / Math.pow(10, 2)
	prefix = prefix || "";
	num = num + "";
	var splitStr = num.split(".");
	var splitLeft = splitStr[0];
	var splitRight = splitStr.length > 1 ? '.' + splitStr[1] : "";
	splitRight = splitRight + "";
	splitRight = splitRight.substr(0, 3);
	var regx = /(\d+)(\d{3})/;
	while (regx.test(splitLeft)) {
		splitLeft = splitLeft.replace(regx, "$1" + "." + "$2");
	}
	return prefix + splitLeft + splitRight;
}

function Empre(tot, venc, lot, enca) {
	ajax = getXMLObject();
	var contenido = document.getElementById('divtot');
	var efect = document.getElementById("Compartida3").value;
	var empre = document.getElementById("Compartida1").value;

	if (efect == "")
		efect = "0";
	if (empre == "")
		empre = "0";

	var peso = empre.substring(0, 1);
	if (peso == "$") {
		peso = empre.substring(1);
		empre = peso;
	}

	var long = empre.length;
	for ( var i = 0; i <= long; i++) {
		empre = empre.replace(".", "");
	}

	if (parseInt(empre) > parseInt(tot)) {
		alert("El valor no puede ser mayor que el total");
		document.getElementById("Compartida1").value = "";
		document.getElementById("Compartida1").focus();
	} else {
		document.getElementById("Compartida3").value = formatNumber(
				(parseInt(tot) - parseInt(empre)), '$');
		document.getElementById("Compartida6").value = formatNumber(
				(parseInt(tot) - parseInt(empre)), '$');
		document.getElementById("Compartida1").value = formatNumber(empre, '$');
		document.getElementById("Compartida5").value = formatNumber(empre, '$');

		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				// contenido.innerHTML = ajax.responseText
				Copag(tot, venc, lot,enca);
			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=12"); // Posting txtname to Servlet
	}
}

function Efect(obj,tot, venc, lot, enca,opcion,tipoFact) {
	
	ajax = getXMLObject();
	var contenido = document.getElementById('divtot');
	var efect = document.getElementById("Compartida6").value;
	var empre = document.getElementById("Compartida1").value;
	
	var anticipo = document.getElementById("Compartida2").value;
	var moderacion = document.getElementById("Compartida4").value;

	if (efect == "" || efect == "$") efect = 0;
	if (empre == "" || empre == "$") empre = 0;
	if (anticipo == "" || anticipo == "$") anticipo = 0;
	if (moderacion == "" || moderacion == "$") moderacion = 0;
	
	// para copago
	if(efect!=0){
		peso = efect.substring(0, 1);
		if (peso == "$") {
			peso = efect.substring(1);
			efect = peso;
		}
	}
	
	// para anticipos
	if(anticipo!=0){
		peso = anticipo.substring(0, 1);
		if (peso == "$") {
			peso = anticipo.substring(1);
			anticipo = peso;
		}
	}
	// para moderacion
	if(moderacion!=0){
		peso = moderacion.substring(0, 1);
		if (peso == "$") {
			peso = moderacion.substring(1);
			moderacion = peso;
		}
	}
	
	// para copago
	long = efect.length;
	for ( var i = 0; i <= long; i++) {
		efect = efect.replace(".", "");
	}
	// para anticipos
	long = anticipo.length;
	for ( var j = 0; j <= long; j++) {
		anticipo = anticipo.replace(".", "");
	}
	// para moderacion
	long = moderacion.length;
	for ( var k = 0; k <= long; k++) {
		moderacion = moderacion.replace(".", "");
	}

	if ((parseInt(efect)+parseInt(anticipo)) > parseInt(tot)) {
		alert("El valor no puede ser mayor que el total");
		obj.value = "";
		obj.focus();
	} else {
		document.getElementById("Compartida1").value = formatNumber((parseInt(tot) - (parseInt(efect)+parseInt(anticipo))), '$');
		document.getElementById("Compartida6").value = formatNumber(efect, '$');
		document.getElementById("Compartida3").value = formatNumber((parseInt(efect)+parseInt(anticipo)), '$');
		document.getElementById("Compartida5").value = formatNumber((parseInt(tot) - (parseInt(efect)+parseInt(anticipo))), '$');
		document.getElementById("Compartida4").value = formatNumber(moderacion, '$');
		document.getElementById("Compartida2").value = formatNumber(anticipo, '$');
		
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				// contenido.innerHTML = ajax.responseText
				Copag(tot, venc, lot,enca,efect,(parseInt(efect)+parseInt(anticipo)),(parseInt(tot) - (parseInt(efect)+parseInt(anticipo))),anticipo,moderacion);
			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=12&opcion="+opcion+"&tipoFact="+tipoFact); // Posting
																	// txtname
																	// to
																	// Servlet
	}
}

function Anticip(tot, venc, lot, enca,opcion,tipoFact) {
	ajax = getXMLObject();
	var contenido = document.getElementById('divtot');
	var anticipo = document.getElementById("Compartida2").value;
	var copag = document.getElementById("Compartida6").value;
	var efectivo = document.getElementById("Compartida3").value;
	// alert(total);

	if (anticipo == "")
		efect = 0;

	var peso = anticipo.substring(0, 1);
	if (peso == "$") {
		peso = anticipo.substring(1);
		anticipo = peso;
	}

	var long = anticipo.length;
	for ( var i = 0; i <= long; i++) {
		anticipo = anticipo.replace(".", "");
	}

	if (parseInt(anticipo) > parseInt(tot)) {
		alert("El valor no puede ser mayor que el total");
		document.getElementById("Compartida2").value = "";
		document.getElementById("Compartida2").focus();
	} else {
		// document.getElementById("Compartida1").value=formatNumber((parseInt(tot)-parseInt(efect)),'$');
		document.getElementById("Compartida2").value = formatNumber(anticipo,
				'$');

		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				checknumg(2);
			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax
				.send("valor=20&anti=" + anticipo + "&venc=" + venc + "&lot="
						+ lot+ "&enca=" + enca); // Posting txtname to
													// Servlet
	}
}

function Modera(tot, venc, lot, enca) {
	ajax = getXMLObject();
	var contenido = document.getElementById('divtot');
	var modera = document.getElementById("Compartida4").value;

	// alert(total);

	if (modera == "")
		efect = 0;

	var peso = modera.substring(0, 1);
	if (peso == "$") {
		peso = modera.substring(1);
		modera = peso;
	}

	var long = modera.length;
	for ( var i = 0; i <= long; i++) {
		modera = modera.replace(".", "");
	}

	if (parseInt(modera) > parseInt(tot)) {
		alert("El valor no puede ser mayor que el total");
		document.getElementById("Compartida4").value = "";
		document.getElementById("Compartida4").focus();
	} else {
		// document.getElementById("Compartida1").value=formatNumber((parseInt(tot)-parseInt(efect)),'$');
		document.getElementById("Compartida4").value = formatNumber(modera, '$');

		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				// contenido.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax
				.send("valor=21&modera=" + modera + "&venc=" + venc + "&lot="
						+ lot+ "&enca=" + enca); // Posting txtname to
													// Servlet
	}
}

function Copag(tot, venc, lot, enca,copag,efectivo,subtotal,anticipos,moderacion) {
	var subtl = letras(subtotal+"");
	subt = subtotal;
	ajax = getXMLObject();
	
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				// contenido.innerHTML = ajax.responseText
			}
		};
		ajax.setRequestHeader('Content-Type',
				'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=22&copag=" + copag + "&venc=" + venc + "&lot=" + lot+ "&subt=" + subt + "&subtl=" + subtl+ "&enca=" + enca+"&efectivo=" + efectivo+"&anticipos=" + anticipos+"&moderacion=" + moderacion); // Posting
																																																					// txtname
																																																					// to
	
}


function Detalle(venc, lot,amb) {
	
	// alert("Entramos a funcion Detalle venc "+venc+" y lot: "+lot);
	var contenido = document.getElementById('resul');
	var contenidos = document.getElementById('resul2');

	var estancia = document.getElementById('fechamo');
	if (estancia != null)
		estancia = estancia.value;
	else
		estancia = "";

	// alert("vence: "+venc+" lot: "+lot)

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var anchopag = document.body.clientWidth;
			contenido.innerHTML = ajax.responseText
			var enca = document.getElementById("enca").value;
			var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div class='style11' id='cabecera2'><div style='width:20%; float:left; text-align:right'></div><div style='width:60%; float:left; text-align:center'><a href='#' style='color:#FFFFFF;' onclick='PreFactAdm("+venc+")'>Movimientos Cargados </a></div><div style='width:20%; float:right; text-align:right'><a href='#' style='color:#FFFFFF;' onclick='facturarips("+venc+")'> Verificar Rips</a></div></div></td></tr>";
			p2 = p2
					+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>No. Ref.</div></i></td><td width='43%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='6%'><i><div align='center'>Fecha</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='5%'><i><div align='center'>Valor</div></i></td><td width='7%'><a href='#' onclick='EliminarMCU("+enca+","+venc+", "+lot+", &quot;"+amb+"&quot;)'><i><div align='center'>Anular</div></i></a></td></tr>";
			contenidos.innerHTML = (p2);

			CrearDetalle(enca, estancia,amb);
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=2&venc=" + venc + "&lot=" + lot+ "&opcion=" + amb);

}

function EliminarMCU(enca,venc,lot,opcion) {
	// alert(enca);
	
	if (confirm("Desea eliminar todos los cargues?")) {
	var contenido = document.getElementById('resul2');
// ffffffffffffffffffffffff
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
		// contenido.innerHTML = ajax.responseText
			Detalle(venc, lot,opcion)
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=EliminarTM&enca="+enca);
}
	}


var mensajeDetEnca2 = false;

function ventSelReFact(noFact,conFact,codAdm,codEnc){
	fondoDetEnca = document.createElement('div');
	mensajeDetEnca2 = document.createElement('div');
	mensajeDetEnca2.setAttribute('id','mensajeDetEnca1');
	fondoDetEnca.setAttribute('id','fondoDetEnca');
	document.getElementsByTagName('body')[0].appendChild(fondoDetEnca);
	document.getElementsByTagName('body')[0].appendChild(mensajeDetEnca2);
	mensajeDetEnca2.innerHTML = "<div style='font-weight: bold;background: #d3d3d3;margin-bottom:25px;margin-top:10px;' align='center'>Seleccionar Reporte Factura</div> " +
			                    "<div align='center' style='margin-bottom:30px;'>Factura No: <a style='font-weight: bold;'>"+noFact+"</a></div> " +
			                    "<div align='center' style='margin-bottom:40px;font-weight: bold;font-size: 16px;'> " +
			                    "<a href='#' onclick='CFacturas(&quot;"+conFact+"&quot;)'>Factura consolidada</a> " +
			                    "<a href='#' onclick='Pref("+codAdm+","+codEnc+")' style='margin-left:130px;margin-right:130px;'>Factura detallada</a> " +
			                    "<a href='#' onclick='PreFactAdm("+codAdm+")'>Movimientos factura</a> " +
			                    "</div> " +
			                    "<div align='center' style='margin-bottom:20px;'><input type='button' value='Cerrar' onclick='cerrarVentReFact()'/></div>";
	window.scroll (0,0) ; 
	document.body.style.overflow="hidden";
}

function cerrarVentReFact(){
	
	if(mensajeDetEnca2!=false){
		document.getElementsByTagName('body')[0].removeChild(mensajeDetEnca2);
		document.getElementsByTagName('body')[0].removeChild(fondoDetEnca);
		mensajeDetEnca2=false;
		fondoDetEnca=false;
	}

}

function Detallesincrear(venc, lot) {
	// alert("Entramos a funcion Detalle venc "+venc+" y lot: "+lot);
	var contenido = document.getElementById('resul');
	var contenidos = document.getElementById('resul2');

	var estancia = document.getElementById('fechamo');
	if (estancia != null)
		estancia = estancia.value;
	else
		estancia = "";
	// alert("vence: "+venc+" lot: "+lot)

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimientos Cargados NOVASOFT</div></td></tr>";
			p2 = p2
					+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>No. Ref.</div></i></td><td width='43%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='6%'><i><div align='center'>Fecha</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='5%'><i><div align='center'>Valor</div></i></td><td width='7%'><i><div align='center'>Anular Todo</div></i></td></tr>";
			contenidos.innerHTML = (p2);
			var enca = document.getElementById("enca").value;
			// alert("enca: "+enca);

			// CrearDetalle(enca,estancia);
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=2&venc=" + venc + "&lot=" + lot);

}

function Detalleh(venc, lot,amb) {

	
	
	//alert("xxxxxxxEntramos a funcion Detalle venc "+venc+" y lot: "+lot+" amb "+amb+" hoa "+hoa);
	var contenido = document.getElementById('resul');
	var contenidos = document.getElementById('resul2');

	// alert("vence: "+venc+" lot: "+lot)
	var estancia = document.getElementById('fechamo');
	if (estancia != null)
		estancia = estancia.value;
	else
		estancia = "";

	var taq = document.getElementById("taq0");
	if (taq != null) {
		var taq1 = document.getElementById("taq0").selectedIndex;
		var taq2 = document.getElementById("taq0").options[taq1].value;
		var taq3 = document.getElementById("taq0").options[taq1].text;
		// alert("taq1: "+taq1+" taq2: "+taq1+" taq3: "+taq3);
	}

	ajax = getXMLObject();
	
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var resp = ajax.responseText;
			var detResp = resp.split("|");
			contenido.innerHTML = detResp[0];
			var enca = document.getElementById("enca").value;
			
			var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div class='style11' id='cabecera2'><div style='width:20%; float:left; text-align:right'></div><div style='width:60%; float:left; text-align:center'><a href='#' style='color:#FFFFFF;' onclick='PreFactAdm("+venc+")'>Movimientos Cargados </a></div><div style='width:20%; float:right; text-align:right'><a href='#' style='color:#FFFFFF;' onclick='facturarips("+venc+")'> Verificar Rips</a></div></div></td></tr>";
			
			
			//var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'><a href='#' style='color:#FFFFFF;' onclick='PreFactAdm("+venc+")'>Movimientos Cargados</a></div></td></tr>";
			p2 = p2
					+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>No. Ref.</div></i></td><td width='43%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='6%'><i><div align='center'>Fecha</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='5%'><i><div align='center'>Valor</div></i></td><td width='7%'><a href='#' onclick='EliminarMCH("+enca+","+venc+","+lot+",&quot;"+amb+"&quot;)'><i><div align='center'>Anular</div></i></a></td></tr>";
			contenidos.innerHTML = (p2);
			
			//alert("entrando a Detalleh");
			var hoa = document.getElementById('hoa').value;
		//	alert("hoa: "+hoa);
			 

			CrearDetalleh(enca, estancia, taq2, taq3,amb);
			//
			//alert("ssssamb: "+amb+" hoa: "+hoa);
			//if(amb=="1"){alert("Ambula");}
			if(amb!="amb"||amb==undefined){
				//alert("222222222");
				if(detResp[1]>0){
					//alert("33333333333333"+" hoa: "+hoa);
					document.getElementById('iraFact').disabled=false;
					if(hoa=="0"){
						//alert("000000000");
						document.getElementById('iraFact').attachEvent('onclick',FacturarM);}
					if(hoa=="1"){document.getElementById('iraFact').attachEvent('onclick',FacturarMA); }
				}else{
					//alert("444444444");
					document.getElementById('iraFact').disabled=true;
				}
			}
			//alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=2h&venc=" + venc + "&lot=" + lot+ "&opcion=" + amb+ "&hoa=" + hoa);

}


function facturarips(venc) {
	var codp = document.getElementById("desch0").value;
 	pagina = "fact_VerificaRIPS.jsp?venc=" + venc + "&codp="+ codp;
	
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=900, height=300, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
	
}

function VR1(adm,codp){
	
	var usu = document.getElementById("txtCodusuario").value;
	
	//alert("ssss: "+adm+" - "+codp+" - "+usu);
	 contenido = document.getElementById('Opcion');
	
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			 contenido.innerHTML = ajax.responseText;
			//	Detalle(venc, lot,opcion)
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=VR1&adm0="+adm+"&xx="+codp+"&lot="+usu);

}


function IngresaDxie(adm,codp,usu,t){
	//alert(adm+" - "+codp+" - "+usu);admision,codpaciente,usuario
	if(t==1){var lotil=document.getElementById('txtCodDiagnostico').value;}
	if(t==2){var lotil=document.getElementById('txtCodDiagnosticoEgreso').value;}
//	contenido = document.getElementById('Opcion');
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			window.location.reload();
			// contenido.innerHTML = ajax.responseText
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=VR2&adm0="+adm+"&xx="+codp+"&lot="+usu+"&lotil="+lotil+"&nivel="+t);
}



function IngresaDestino(adm,codp,usu){
	//alert(adm+" - "+codp+" - "+usu);admision,codpaciente,usuario
	var lotil=document.getElementById('dp').value;
	var t=document.getElementById('es').value;
	var Abreviado=document.getElementById('fc').value;
	var texto=document.getElementById('ce').value;
	var n=document.getElementById('td').value;
	
	//alert(lotil+" - "+t+" - "+Abreviado+" - "+texto+" - "+n);
	if(lotil=="Seleccione"){alert("Debe Seleccionar el destino del paciente!!");}
	else{
//	contenido = document.getElementById('Opcion');
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			window.location.reload();
			// contenido.innerHTML = ajax.responseText
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=VR3&adm0="+adm+"&xx="+codp+"&lot="+usu+"&lotil="+lotil+"&nivel="+t+"&Abreviado="+Abreviado+"&texto="+texto+"&n="+n);
 }
}

function EliminarMCH(enca,venc,lot,opcion) {
	// alert(enca);
	
	if (confirm("Desea eliminar todos los cargues?")) {
	var contenido = document.getElementById('resul2');
// ffffffffffffffffffffffff
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
		// contenido.innerHTML = ajax.responseText
			Detalleh(venc, lot,opcion);
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=EliminarTM&enca="+enca);
}
	}

function DetalleCE(venc, lot, me, fe) {
	// alert("detalle "+fe);
	// alert("Entramos a funcion Detalle venc "+venc+" y looooioot: "+lot);
	var contenido = document.getElementById('resul');
	var contenidos = document.getElementById('resul2');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Movimientos Cargados SAIM</div></td></tr>";
			p2 = p2
					+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>No. Ref.</div></i></td><td width='43%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='6%'><i><div align='center'>Fecha</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='5%'><i><div align='center'>Valor</div></i></td><td width='7%'><a href='#' onclick='Eliminart("+venc+", "+lot+")'><i><div align='center'>Anular</div></i></a></td></tr>";
			contenidos.innerHTML = (p2);
			var enca = document.getElementById("enca").value;

			CrearDetallece(enca, me, fe);
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=2&venc=" + venc + "&lot=" + lot);
}

function CrearDetalle(enca, estancia,amb) {
	// alert("Entramos a funcion Detalle venc "+venc+" y lot: "+lot);
	var contenido = document.getElementById('resulcrea');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			var f = document.getElementById('fechamo').value;
			// alert("esto es f: "+f);
			if (f == "") {
				// document.getElementById('fechamo').focus();
			} else {
				document.getElementById('desct0').focus();
				document.getElementById('desct0').focus();
				document.getElementById('carga').innerHTML="<td><div id='carga' align='center'><a  href='#'onclick='CargarFact("+enca+",0)' id='cargar'>Cargar</a></div></td>";
				
			}
			if(selPac){
				document.getElementById('fechamo').focus();
				selPac = false;
			}
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=3&enca=" + enca + "&estancia=" + estancia+ "&amb=" + amb);

}

function CrearDetalleh(enca, estancia, taq2, taq3,opcion) {
	var contenido = document.getElementById('resulcrea');
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			var factqx = document.getElementById('taq0').childNodes;
			for(var i=0;i<factqx.length;i++){
				if(factqx[i].value==taq2){
					document.getElementById('taq0').options[i].selected=true;
					i=factqx.length;
				}
			}
			var f = document.getElementById('fechamo').value;
			if (f == "") {
				// document.getElementById('fechamo').focus();
			} else {
				document.getElementById('ref0').focus();
				document.getElementById('ref0').focus();
				document.getElementById('carga').innerHTML="<td><div id='carga' align='center'><a  href='#'onclick='CargarFacth("+enca+",0)' id='cargar'>Cargar</a></div></td>";
				
			}
			if(selPac){
				document.getElementById('fechamo').focus();
				selPac = false;
			}
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=3h&enca=" + enca + "&estancia=" + estancia + "&taq2="
			+ taq2 + "&taq3=" + taq3+ "&opcion=" + opcion);
}

function CrearDetallece(enca, me, fe) {
	// alert("creardetalle "+fe);
	// alert("Entramos a funcion Detalle venc "+venc+" y lot: "+lot);
	var contenido = document.getElementById('resulcrea');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=3CE&enca=" + enca + "&me=" + me + "&fe=" + fe);

}

function ActoQx() {

	var taq = document.getElementById("taq").value;
	// alert(taq);

	var contenido = document.getElementById('divaqx');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=3h1&taq=" + taq);

}

function Factura(venc, lot, enca) {
   // alert("Entramos a funcion Detalle venc "+venc+" y lot: "+lot+" enca"+enca);
	// var contenido=document.getElementById('resul');
	// var contenidos=document.getElementById('resul2');
	var contenidos = document.getElementById('resultodos');
	var contenido = document.getElementById('BM');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenidos.innerHTML = ajax.responseText;
			contenido.innerHTML = "<table width='100%' class='style6' ><tr><td><div style='margin-left:420px;'><input  style='float:left; margin_right=10px'type='button' name='btnPre' id='btnPre' value='Facturar' onClick='factDetallada(&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)'><input type='button' name='btnPre' id='btnPre' value='Movimientos' onClick='pruebaChevel("+venc+","+lot+","+enca+")'  ><div></td></tr></table>";
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	
	ajax.send("valor=9&venc=" + venc + "&lot=" + lot + "&enca=" + enca);

}

function FacturaU(venc,lot,enca,amb) {
	// alert("Entramos a funcion Detalle venc "+venc+" y lot: "+lot);
	var contenido=document.getElementById('resul');	
	var contenidos=document.getElementById('resul2');	
		
	ajax=getXMLObject();
	ajax.open("POST","ControlMovimientos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenidos.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=9.1&venc="+venc+"&lot="+lot+"&enca="+enca+"&amb="+amb);

	}

function NumerarF(enca, venc, tipoFact,lot,amb) {
	// alert(tipoFact);
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			// devolver vista
			//alert("Factura Generada.\nPresione Aceptar Para Generar El Reporte.");
			var Srv=ajax.responseText;
			//alert(Srv);
			if(Srv=="0"){
				FacturaAmbulatoria(enca,enca);//detallada.
				//ImprimirReporteFactura(enca,enca);//consolidada.
				
			}else{
				//ImprimirReporteFactura(enca,txtCodusuario);//consolidada.
				FacturaAmbulatoria(enca,enca);//detallada.
			}
			//ImprimirReporteFactura(enca,txtCodusuario);
			if(tipoFact==0){
				// window.location.reload();
				cerrarVentDetEnca(venc, enca, lot);				
			}else{
				if(tipoFact==1){
					//window.location.reload();
					window.location.href="fact_FacturarH.jsp";
				}
				if(tipoFact==2){
					//window.location.reload();fact_FacturarA.jsp
					window.location.href="fact_FacturarA.jsp";
				}
			}
		}	
	}
	
	
	//alert("valor=100&enca=" + enca + "&Abreviado=" + txtCodusuario+ "&venc=" + venc+ "&tipoFact=" + tipoFact+ "&amb=" + amb);
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=100&enca=" + enca + "&Abreviado=" + txtCodusuario
			+ "&venc=" + venc+ "&tipoFact=" + tipoFact+ "&amb=" + amb+ "&lot=" + lot);

}

function Fact(venc, lot, enca, tipoFact,amb) {
	var radiobutton = document.getElementsByName("radiobutton");
	var TipoPago="";
	for (var x = 0; x < radiobutton.length; x ++) {
		if (radiobutton[x].checked) {
			TipoPago=radiobutton[x].id;
		}
	}	
	if(TipoPago=="10"){
		//10 la empresa
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var al=ajax.responseText;
				if(al==""){
					NumerarF(enca, venc, tipoFact,lot,amb);
					}else{
						//alert(al);
						NumerarF(enca, venc, tipoFact,lot,amb);
					}
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');	
		ajax.send("valor=VAF&CodAdm="+venc);
		
	}
	if(TipoPago=="11"){
		//11 si es particular
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var al=ajax.responseText;
				if(al==""){
					NumerarF(enca, venc, tipoFact,lot,amb);
					}else{
						//alert(al);
						NumerarF(enca, venc, tipoFact,lot,amb);
					}
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');	
		ajax.send("valor=VAF&CodAdm="+venc);
		
	}
	if(TipoPago=="12"){
		//12 si es compartido
		var Compartida6=document.getElementById("Compartida6").value;		
		if(Compartida6=="$0"){
			alert("Seleccionó Pago Compartido.\n Escriba el Valor del Copago.");
		}else{
			ajax = getXMLObject();
			ajax.open("POST", "ControlMovimientos", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					var al=ajax.responseText;
					if(al==""){
						NumerarF(enca, venc, tipoFact,lot,amb);
						}else{
							//alert(al);
							NumerarF(enca, venc, tipoFact,lot,amb);
						}
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');	
			ajax.send("valor=VAF&CodAdm="+venc);
		}
		
	}
	//alert("venc "+venc+" lot="+lot+" enca "+enca+" tipoFact "+tipoFact+" amb "+amb+" TipoPago "+TipoPago);
	//
}
function FacturaA(venc, lot, enca) {
	// alert("Entramos faccccfccccvenc "+venc+" y lot: "+lot+" y el enca "+enca);
	// var contenido=document.getElementById('resul') ;
	// var contenidos=document.getElementById('resul2');
	var contenidos = document.getElementById('resultodos');
	var contenido = document.getElementById('BM');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenidos.innerHTML = ajax.responseText;
			contenido.innerHTML = "<table width='100%' class='style6' ><tr><td><div style='margin-left:420px;'><input  style='float:left; margin_right=10px'type='button' name='btnPre' id='btnPre' value='Facturar' onClick='factDetallada(&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)'><input type='button' name='btnPre' id='btnPre' value='Movimientos' onClick='pruebaChevelA("+venc+","+lot+","+enca+")'  ><div></td></tr></table>";
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	
	ajax.send("valor=9&venc=" + venc + "&lot=" + lot + "&enca=" + enca);

}

function ImprimirReporteFactura(enca,txtCodusuario){
	pagina = "fact_ReporteFactura.jsp?venc=" + enca + "&txtCodusuario="
	+ txtCodusuario;
var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
opciones = opciones
	+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
window.open(pagina, "NUEVA", opciones);

}

function Pref(venc,enca) {

	var txtCodusuario = document.getElementById("txtCodusuario").value;
	// alert("Entramos a generar pre factxxx "+venc+" user: "+txtCodusuario);
	pagina = "fact_ReportePreFactura.jsp?venc=" + enca + "&txtCodusuario="
			+ txtCodusuario;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}

function FacturaAmbulatoria(venc,enca) {

	var txtCodusuario = document.getElementById("txtCodusuario").value;
	// alert("Entramos a generar pre factxxx "+venc+" user: "+txtCodusuario);
	pagina = "fact_ReporteFacturaAmbulatoria.jsp?venc=" + enca + "&txtCodusuario="
			+ txtCodusuario;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}

function EliminarCargue(enca, venc, lot,opcion) {

	if (confirm("Desea Eliminar el Cargue!!!")) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				if (ajax.responseText != "uno") {
					alert(ajax.responseText);
					Detalle(venc, lot,opcion);
				} else {

					if (confirm("Al eliminar este movimiento ya no podra seguir\n"
							+ "cargandole mas movimientos al paciente\n\n"
							+ "\xbfDesea eliminarlo?")) {
						ajax1 = getXMLObject();
						ajax1.open("POST", "ControlMovimientos", true);
						ajax1.onreadystatechange = function() {
							if (ajax1.readyState == 4) {
								alert(ajax1.responseText);
								window.location.reload();
							}
						}
						ajax1
								.setRequestHeader("Content-type",
										"application/x-www-form-urlencoded; charset=UTF-8");
						ajax1.send("valor=7a&enca=" + enca + "&venc=" + venc
								+ "&lot=" + lot);

					}

				}
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=7&enca=" + enca + "&venc=" + venc + "&lot=" + lot);
	}

}

function EliminarCargueh(enca, venc, lot,opcion) {
	// alert("Entramos a funcion Detalle venc "+venc+" y lot: "+lot);
	// var contenido=document.getElementById('resulcrea');
	// alert(enca);
	if (confirm("Desea Eliminar el Cargue!!!")) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				// alert(ajax.responseText);
				Detalleh(venc, lot,opcion);
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=7&enca=" + enca);
	}
}

function uno() {
	ajax = getXMLObject();
	var mtar = document.getElementById("mtar").value;
	var desch0 = document.getElementById("desch0").value;
	var crit = document.getElementById("crit").value;
	var mbase0 = document.getElementById("mbase0").value;

	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlCrearTarifas", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			ConsultarP();
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&mtarp=" + mtar + "&desch0p=" + desch0 + "&critp=" + crit
			+ "&mbase0p=" + mbase0); // Posting txtname to Servlet

}

function ConsultarP() {

	var contenidos = document.getElementById('resul2');
	var contenido = document.getElementById('resul');

	var mtar = document.getElementById("mtar").value;
	var desch0 = document.getElementById("desch0").value;
	var crit = document.getElementById("crit").value;
	var mbase0 = document.getElementById("mbase0").value;

	// alert("mtar "+mtar+" desch0 "+desch0+" crit "+crit+" mbase0 "+mbase0);
	document.getElementById('resul').innerHTML = '<p class="style6"><div align="center" >Cargando Resultados...</p>';// <img
	// src="Imagenes/cargando.gif">';
	// alert("sss");
	if (mtar == "Seleccione" && desch0 == "" && crit == "") {
		alert("Debe Ingresar al menos un Criterio de Busqueda");
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlCrearTarifas", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {

				var p = "<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='20%'><i><div align='center'>Manual Tarifario</div></i></td><td width='44%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='9%'><i><div align='center'>Fecha Inicial</div></i></td><td width='9%'><i><div align='center'>Fecha Final</div></i></td></i></td><td width='5%'><i><div align='center'>Valor</div></i></td><td width='6%'><i><div align='center'>Acci\xf3n</div></i></td></tr>";

				var myArray = new Array();
				myArray = ajax.responseText;
				var result = myArray.split("|");
				var elev = parseInt(parseInt(result.length) / 8);
				// alert("tama\xf1overdadero: "+elev);
				for ( var i = 0; i < elev; i++) {
					p = p
							+ "<tr><td><label><select  style='width:100%;' name='mbasec"
							+ i + "' id='mbasec" + i + "' ><option value='"
							+ result[1 + (i * 8)] + "'>" + result[2 + (i * 8)]
							+ "</option></select>";
					p = p + "<td><input type=text id='desc" + i
							+ "' size='70%' value='" + result[3 + (i * 8)]
							+ "' readonly='readonly'>";
					p = p + "<input name='desch" + i
							+ "' type='hidden' id='desch" + i + "'  value='"
							+ result[4 + (i * 8)] + "' /></td>";
					p = p
							+ "<td><input type=text id='fechai"
							+ i
							+ "' style='width:100%;' value='"
							+ result[5 + (i * 8)]
							+ "' onKeyup='masca(this,patron,true,01,01,2010)' onBlur='checkfecha("
							+ i + ");ModificarTExistente("
							+ result[0 + (i * 8)] + "," + i + ",3)'></td>";
					p = p
							+ "<td><input type=text id='fechaf"
							+ i
							+ "' style='width:100%;' value='"
							+ result[6 + (i * 8)]
							+ "' onKeyup='masca(this,patron,true,01,01,2010)' onBlur='checkfechaf("
							+ i + ");ModificarTExistente("
							+ result[0 + (i * 8)] + "," + i + ",4)'></td>";
					p = p + "<td><input type=text id='valor" + i
							+ "' style='text-align:right' size='10%' value='"
							+ result[7 + (i * 8)]
							+ "' onBlur='ModificarTExistente("
							+ result[0 + (i * 8)] + "," + i + ",5)'></td>";
					p = p
							+ "<td><div align='center'><a  href='#'onclick='EliminarT("
							+ result[0] + ")'>Eliminar</a></div></td>";
				}
				var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>";

				// out.print("<table width='100%' class='style6'><tr><td
				// colspan='5'><div align='center' class='style11'
				// id='cabecera2'>Resultados de la Busqueda</div></td></tr>");

				contenidos.innerHTML = (p2);
				contenido.innerHTML = (p);

				// contenido.innerHTML = ajax.responseText

			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=2&mtarp=" + mtar + "&desch0p=" + desch0 + "&critp="
				+ crit + "&mbase0p=" + mbase0);

	}
}

function serviciosPYP(lot, ca, n,med) {
	
	ca = encodeURIComponent(ca);
	n = encodeURIComponent(n);
	// alert("servicios pyp: "+n);
	var contenidos = document.getElementById('resulcrea');
	// alert("hay fecha: "+ca);
	var estancia = document.getElementById("fechamo").value;
	var enca = document.getElementById("enca").value;
	var eps = document.getElementById("epsh0").value;
	// alert("este es un servicio: "+lot);
	// document.getElementById("serv0").value="cesar";
	var contenido = document.getElementById('servic');
	// var contenido=document.getElementById('Opcion');
	if(med==0){
		document.getElementById('med0').disabled=true;
	}
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (lot == 1)
				contenido.innerHTML = ajax.responseText;

			if (lot == 2)
				contenidos.innerHTML = ajax.responseText;
				
			
			
			serviciosPYP2(med);
			document.getElementById("cant0").focus();
			document.getElementById("cant0").select();
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4&mtarp=" + lot + "&critp=" + ca + "&n=" + n
			+ "&estancia=" + estancia + "&enca=" + enca+"&eps="+eps); // Posting
																		// txtname
	// to Servlet
}

function serviciosPYPh(lot, ca, n, act, med,opcion) {
	ca = encodeURIComponent(ca);
	n = encodeURIComponent(n);
	var contenidos = document.getElementById('resulcrea');
	var enca = document.getElementById("enca").value;
	var estancia = document.getElementById("fechamo").value;
	var eps = document.getElementById("epsh0").value;
	var taq = document.getElementById("taq0").value;
	if (taq != "Seleccione") {
		var taq2 = document.getElementById("taq0").selectedIndex;
		var taq3 = document.getElementById("taq0").options[taq2].text;
	} else
		taq3 = "Seleccione";
	var aq = document.getElementById("actqx0").value;
	var aq3 = "Seleccione";
	if (aq != "Seleccione") {
		for(i=0,cantACQ = document.getElementById('acQx').childNodes;i<cantACQ.length;i++){
			if(cantACQ[i].value==aq){
				aq3 = cantACQ[i].childNodes[0].nodeValue;
				i=cantACQ.length;
			}
		}
	} 
	document.getElementById('actqx0').disabled = false;
	document.getElementById('taq0').disabled = false;
	document.getElementById('med0').disabled = false;

	if ((lot == "1") && (act == "0")) {
		document.getElementById('pr').disabled = true;
		document.getElementById('actqx0').disabled = true;
	}
	if ((lot == "1") && (med == "0")) {
		document.getElementById('med0').disabled = true;
	}

	contenido = document.getElementById('servic');
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var respCont = ajax.responseText.split("|");			
			if (respCont[1]=='lt2' || respCont[1]=='hemodinamia'){
				contenidos.innerHTML = respCont[0];
				var checkboxes = document.getElementById("form1").checkbox;
				for ( var x = 0; x < checkboxes.length; x++) {
					document.getElementById('med' + x).disabled = false;
					if (lot == "2" || taq=='6') {
						var raqx = document.getElementById('raqx' + x).value;
						var rmed = document.getElementById('rmed' + x).value;
						if (rmed == "0") {
							document.getElementById('med' + x).disabled = true;
						}
					}
				}
			}else{
				if(respCont[1]=='lt1'){
					contenido.innerHTML = respCont[0];
				}
			}
			
			
			
			if(respCont[1]=='hemodinamia'){
				serviciosPYP2h(lot, med, act,"hemodinamia");
				
			}else{
				serviciosPYP2h(lot, med, act);
			}
			
			
		}
	}
	
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4h&mtarp=" + lot + "&critp=" + ca + "&n=" + n + "&act="
			+ act + "&med=" + med + "&estancia=" + estancia + "&enca=" + enca
			+ "&taq=" + taq + "&taq3=" + taq3 + "&aq=" + aq + "&aq3=" + aq3+"&eps="+eps+"&opcion="+opcion); // Posting
}

function serviciosPYP2(med) {
	//alert(med);
	var ca = document.getElementById("serv0").value;
	var eps = document.getElementById("epsh0").value;
	var caa = document.getElementById("serv0").selectedIndex;

	var caad = document.getElementById("serv0").options[caa].text;
	caad = encodeURIComponent(caad);
	var contenido = document.getElementById('vv');
	// var contenido=document.getElementById('Opcion');
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			// document.getElementById("valorpyp0").value="cj"
			// var ss=document.getElementById("vxh0").value;
			// alert(ss);
			if(med==1){
				medicosPYP();
			}
		}
		
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4a&critp=" + ca + "&ep=" + eps + "&caad=" + caad); // Posting
	// txtname
	// to
	// Servlet

}

function serviciosPYP2h(lot, med, act,tipoPaq) {
	// alert("serviciosPYP2h");
	var valorPriPro = document.getElementById('valorpyp0').value;
	var ca = document.getElementById("serv0").value;
	var eps = document.getElementById("epsh0").value;
	var caa = document.getElementById("serv0").selectedIndex;
	var caad = document.getElementById("serv0").options[caa].text;

	caad = encodeURIComponent(caad);
	if (lot == "2") {

		document.getElementById('actqx0').disabled = false;
		document.getElementById('taq0').disabled = false;
		document.getElementById('med0').disabled = false;
	}

	var contenido = document.getElementById('vv');
	// var contenido=document.getElementById('Opcion');
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			// alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			if(tipoPaq=="hemodinamia"){
				document.getElementById('valorpyp0').value = valorPriPro; 
				document.getElementById('pop').value = 2; 
				document.getElementById('refhi').value = document.getElementById('ref0').value;
			}
			if (lot == "2") {
				var raqx = document.getElementById('raqx0').value;
				var rmed = document.getElementById('rmed0').value;
				// alert("raqx: "+raqx);
				if (raqx == "0") {
					document.getElementById('actqx0').disabled = true;
					// document.getElementById('taq0').disabled=true;
				}
				if (rmed == "0") {
					document.getElementById('med0').disabled = true;
				}

			}

			// alert("lot: "+lot+" med: "+med+" estamos es servicios pyp2h");

			if ((lot == "2") && (rmed == "1")) {
				medicosPYP();
			}

			if ((lot == "1") && (med == "1")) {
				medicosPYP();
			}
			document.getElementById("cant0").focus();
			document.getElementById("cant0").select();
			vcarga();
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4ah&critp=" + ca + "&ep=" + eps + "&caad=" + caad
			+ "&mtarp=" + lot); // Posting txtname to Servlet

}

function medicosPYP() {
	// alert("entramos a medicos");

	var contenido = document.getElementById('medic0');
	// var contenido=document.getElementById('Opcion');
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			
			
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5"); // Posting txtname to Servlet

}

function Estancias(enca,opcion) {// la idea es llegar d aqui al cargue de facturas o
	// no

	// alert("estancias");
	var adm0 = document.getElementById("adm0").value;

	var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
	var cc = "fechamo";
	var e = document.getElementById(cc).value;
	// alert(e);
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	} else {

		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {

				var cesar = ajax.responseText;
				if (cesar == "1") {
					Estancias3(enca,opcion);
				} else {
					alert(cesar);
					document.getElementById(cc).value = "";
					document.getElementById(cc).focus();
					Estancias2(enca);
				}
				//
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=51&enca=" + enca + "&adm0=" + adm0 + "&e=" + e+"&opcion=" + opcion);

	}
}

function Estanciash(enca,opcion) {// la idea es llegar d aqui al cargue de facturas o
	// no

	// alert("estanciashhhh");
	var adm0 = document.getElementById("adm0").value;

	var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
	var cc = "fechamo";
	var e = document.getElementById(cc).value;
	// alert(e);
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	} else {

		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {

				var cesar = ajax.responseText;
				if (cesar == "1") {
					Estancias3h(enca,opcion);
				} else {
					alert(cesar);
					document.getElementById(cc).value = "";
					document.getElementById(cc).focus();
					Estancias2(enca);
				}
				//
			}
		}
		//alert(adm0);
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=51h&enca=" + enca + "&adm0=" + adm0 + "&e=" + e+ "&opcion=" + opcion);

	}
}

function Estancias2(enca) {
	var contenido = document.getElementById('carga');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {

			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=52&enca=" + enca);

}

function Estancias3(enca,opcion) {
	var contenido1 = document.getElementById('carga');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {

			contenido1.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=53&enca=" + enca+"&opcion="+opcion);

}

function Estancias3h(enca,opcion) {
	
	if(document.getElementById('carga')!=null){
		var contenido2 = document.getElementById('carga');
	
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
	
				contenido2.innerHTML = ajax.responseText
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=53h&enca=" + enca+"&opcion="+opcion);
	}

}

function tab(object,id){
	var elEvento =  window.event;  // arguments[0] ||
    var tecla = elEvento.keyCode;
    if(tecla==13){
		if(object.value!=""){
			if(document.getElementById('med'+id).disabled!=true){
				document.getElementById('med'+id).focus();
			}else{
				if(id<4&&document.getElementById('cant'+(id+1))!=null){
					document.getElementById('cant'+(id+1)).focus();
					document.getElementById('cant'+(id+1)).select();
				}else{
					document.getElementById('cargar').focus();
				}
				
			}
		}
    }
}


function vcarga() {
	var checkboxes = document.getElementById("form1").checkbox;
	if(document.getElementById('cargaCesar0')!=null){
		if(checkboxes.length!=undefined){
			for ( var x = 0; x < checkboxes.length; x++) {
					checkboxes[x].checked = 1;
			}
		}
	}
}

function CargarFactS(enca) {
//alert("CargarFactS");
	var checkboxes = document.getElementById("form1").checkbox;

	for ( var x = 0; x < checkboxes.length; x++) {
		if (checkboxes[x].checked) {
			j = checkboxes[x].value
			// ///////////////////

			var contenido = document.getElementById('creacion');
			var desct0 = document.getElementById("desct" + j).value;
			var ref0 = document.getElementById("ref" + j).value;
			var descth0 = document.getElementById("descch" + j).value;
			var servich0 = document.getElementById("servich" + j).value;
			var serv0 = document.getElementById("serv" + j).value;
			var cant0 = document.getElementById("cant" + j).value;
			var med0 = document.getElementById("med" + j).value;
			//alert("CargarFactS: "+med0);
			var valorpyp0 = document.getElementById("valorpyp" + j).value;
			var txtCodusuario = document.getElementById("txtCodusuario").value;

			if (med0 == "Seleccione") {
				med0 = 0;
			}

			var cc = "fechamo";
			var e = document.getElementById(cc).value;
			// alert(med0);
			// ,("contenido "+contenido+" desct0 "+desct0+" ref0 "+ref0+"
			// descth0 "+descth0+" servich0 "+servich0+" serv0 "+serv0+" cant0
			// "+cant0+" med0 "+med0+" valorpyp0 "+valorpyp0+" txtCodusuario
			// "+txtCodusuario+" pop "+pop+" refhi "+refhi);

			if (descth0 == "" || serv0 == "" || desct0 == ""
					|| servich0 == "" || cant0 == "" || cant0 == "0" || e == "") {
				alert("Debe Digitar todos los campos a ingresar cfs");
			} else {
				if (valorpyp0 == "") {
					alert("El programa no tiene tarifa ");
				} else {
					var x1 = confirm("Desea Cargar el Movimiento");
					if (x1) {
					var pop = document.getElementById("pop").value;
					var refhi = document.getElementById("refhi").value;
					var cse = document.getElementById("cse").value;
					var subc = document.getElementById("subc").value;

					ajax = getXMLObject();
					ajax.open("POST", "ControlMovimientos", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {

						//	alert("Cargue Exitoso.");

						}
					}
					ajax.setRequestHeader("Content-type",
							"application/x-www-form-urlencoded; charset=UTF-8");
					ajax.send("valor=6&pop=" + pop + "&serv0=" + serv0
							+ "&refhi=" + refhi + "&mtarpp=" + e + "&nivel="
							+ ref0 + "&texto=" + descth0 + "&xx=" + desct0
							+ "&mbase0p=" + servich0 + "&desch0p=" + cant0
							+ "&venc=" + med0 + "&critp=" + valorpyp0 + "&lot="
							+ enca + "&Abreviado=" + txtCodusuario + "&cse="
							+ cse + "&subc=" + subc);
					}
				}// sino if de la tarifa o el valor
			}

			// /////////////////
		}
	}
	var lotil = document.getElementById("lotil").value;
	var encas = document.getElementById("encas").value;
	Detalle(encas, lotil);
	// alert("enca: "+enca+" jjj: "+j);
	// alert("s: "+s);
	/*
	 */
}

///JS PARA PROCESO DE DEVOLUCION

function RadiosDevFact() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlMovimientos",true); // getname will be
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
				ajax.open("POST", "ControlMovimientos", true); // getname will
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
			
			var EntParaComp = "";
			for (i=0;i<childTablaFact.length-1;i++){
				if(document.getElementById('chkEF'+i).checked){
					factSeleccionadas+=document.getElementById('chkEF'+i).value+"_";
					totalFact+=parseInt(document.getElementById('vEF'+i).childNodes[0].nodeValue);
					EntParaComp+=document.getElementById('ent'+i).childNodes[0].nodeValue+"_";
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
				ajax.open("POST", "ControlMovimientos", true); // getname will be the
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
						ajax.send("valor=devFact&facturas="+factSeleccionadas+"&vFacturas="+totalFact+"&vlFacturas="+valorLetras+"&usuario="+usuario+"&frad="+0+"&ri="+ri+"&motivo="+motivo);
						}else{
							ajax.send("valor=devFact&facturas="+factSeleccionadas+"&vFacturas="+totalFact+"&vlFacturas="+valorLetras+"&usuario="+usuario+"&frad="+frad+"&ri="+ri+"&motivo="+motivo);
						}
				}
				//}else{alert("Debe escribir el motivo de la devolucion de la factura");}
				}else{alert("Debe escoger un motivo de devolucion");}
			 }else{
				if(factSeleccionadas==""){
					alert("Debe seleccionar por lo menos una factura");
				}
				if(!eqEntidad){
					alert("Para generar el consolidado de las facturas a devolver \n" +
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
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
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
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
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
				ajax.open("POST", "ControlMovimientos", true); // getname will
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
	ajax.open("POST","ControlMovimientos",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=reportedev&cons="+cons);
}





///FIN PROCESO

function CargarFact(enca, j,amb) {
	// alert("CargarFact");
	// alert("enca: "+enca+" jjj: "+j);
	// alert("s: "+s);
	var contenido = document.getElementById('creacion');
	var desct0 = document.getElementById("desct" + j).value;
	var ref0 = document.getElementById("ref" + j).value;
	var descth0 = document.getElementById("descch" + j).value;
	var servich0 = document.getElementById("servich" + j).value;
	var serv0 = document.getElementById("serv" + j).value;
	var cant0 = document.getElementById("cant" + j).value;
	var med0 = 0;
	if(document.getElementById("med0").disabled!=true){
		med0 = document.getElementById("medh" + j).value;
	}
	
	// alert("CargarFact: "+med0);
	var valorpyp0 = document.getElementById("valorpyp" + j).value;
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	
	servich0 = encodeURIComponent(servich0);
	
	// alert("1."+contenido+", 2."+desct0+", 3."+ref0+", 4."+descth0+",
	// 5."+servich0+", 6."+serv0+", 7."+cant0+", 8."+med0+", 9."+valorpyp0+",
	// 10."+txtCodusuario);

	

	var cc = "fechamo";
	var e = document.getElementById(cc).value;
	// alert(med0);
	// alert("contenido "+contenido+" desct0 "+desct0+" ref0 "+ref0+" descth0
	// "+descth0+" servich0 "+servich0+" serv0 "+serv0+" cant0 "+cant0+" med0
	// "+med0+" valorpyp0 "+valorpyp0+" txtCodusuario "+txtCodusuario+" pop
	// "+pop+" refhi "+refhi);

	if (descth0 == "" || serv0 == "" || desct0 == ""
			|| servich0 == "" || cant0 == "" || cant0 == "0" || e == "") {
		alert("Debe Digitar todos los campos a ingresar cf");
	} else {
		if (valorpyp0 == "") {
			alert("El programa no tiene tarifa ");
		} else {
			var x = confirm("Desea Cargar el Movimiento");
			if (x) {
			var pop = document.getElementById("pop").value;
			var refhi = document.getElementById("refhi").value;
			var cse = document.getElementById("cse").value;
			var subc = document.getElementById("subc").value;

			ajax = getXMLObject();
			ajax.open("POST", "ControlMovimientos", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					var lotil = document.getElementById("lotil").value;
					var encas = document.getElementById("encas").value;
					// alert(lotil+encas)

					Detalle(encas, lotil,amb);
					//alert("Cargue Exitoso.");
				}
			}
			ajax.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			ajax.send("valor=6&pop=" + pop + "&serv0=" + serv0 + "&refhi="
					+ refhi + "&mtarpp=" + e + "&nivel=" + ref0 + "&texto="
					+ descth0 + "&xx=" + desct0 + "&mbase0p=" + servich0
					+ "&desch0p=" + cant0 + "&venc=" + med0 + "&critp="
					+ valorpyp0 + "&lot=" + enca + "&Abreviado="
					+ txtCodusuario + "&cse=" + cse + "&subc=" + subc);
			}		
			}// sino if de la tarifa o el valor
	}

}

function CargarFactSh(enca,opcion) {
	//alert("cargar facturaSSSSSSSh");
	// alert("CargarFactSh");
	// alert("cargarfactturash ");
	var checkboxes = document.getElementById("form1").checkbox;

	var actqx = document.getElementById("actqx0").value;

	if (actqx == "Seleccione") {
		alert("El acto Quirurgico es requerido para este programa!!!")
	} else {
		var contenido = document.getElementById('creacion');
		// alert(checkboxes.length);
		var movimientos="";
		var sw=0;
		var sw2=0;
		var sw3=0;
		
		// alert(checkboxes.length);
		if(checkboxes.length!=undefined){
			for ( var x = 0; x < checkboxes.length; x++) {
	
				if (checkboxes[x].checked) {
					j = checkboxes[x].value
					// ///////////////////
					var desct0 = document.getElementById("desct" + j).value;
					var ref0 = document.getElementById("ref" + j).value;
					var descth0 = document.getElementById("descch" + j).value;
					var servich0 = document.getElementById("servich" + j).value;
					var serv0 = document.getElementById("serv" + j).value;
					var cant0 = document.getElementById("cant" + j).value;
					k = parseInt(j) + 1;
					
					var med0 = document.getElementById("medh"+j+k).value;
					
					if(med0==""){
						med0='null'
					}
					
					
					var valorpyp0 = document.getElementById("valorpyp" + j).value;
					var cse = document.getElementById("cse" + j).value;
					var rporc = document.getElementById("rporc" + j).value;
				
	
					var txtCodusuario = document.getElementById("txtCodusuario").value;
					
					var rmed = document.getElementById("rmed" + j).value;
	
					// alert("medo: "+med0);
					if (med0 == "Seleccione") {
						med0 = 0;
					}
	
					var cc = "fechamo";
					var e = document.getElementById(cc).value;
					
					if (descth0 == "" || serv0 == "" || desct0 == ""
							|| servich0 == "" || cant0 == "" || cant0 == "0"
							|| e == "") {
						// alert("Debe Digitar todos los campos a ingresar");
					} else {
						if ((rmed == "1") && (med0 == "null")) {
							sw2=1;
							
							sw=1;
						} else {
	
							if (valorpyp0 == "") {
								
								sw3=1;
								sw=1;
							} else {
	
								var pop = document.getElementById("pop").value;
								var refhi = document.getElementById("refhi").value;
								//alert(refhi);
								if(refhi==""){
									refhi="na";
								}
								var subc = document.getElementById("subc").value;
	
								
								movimientos=movimientos+"|"+pop+"|"+serv0+"|"+refhi+"|"+e+"|"+ref0+"|"+descth0+"|"+desct0+"|"+servich0+"|"+cant0+"|"+med0+"|"+valorpyp0+"|"+enca+"|"+txtCodusuario+"|"+cse+"|"+subc+"|"+actqx+"|"+rporc;
								
								// aqui va
								
								
							}// sino if de la tarifa o el valor
						}// sino else medico
	
					}
					
				
				}
			}
		}else{
			// ////
			
			if (document.getElementById("form1").checkbox.checked) {
				
				// ///////////////////
				var desct0 = document.getElementById("desct0").value;
				var ref0 = document.getElementById("ref0").value;
				var descth0 = document.getElementById("descch0").value;
				var servich0 = document.getElementById("servich0").value;
				var serv0 = document.getElementById("serv0").value;
				var cant0 = document.getElementById("cant0").value;
				var med0 = document.getElementById("med0").value;
				var valorpyp0 = document.getElementById("valorpyp0").value;
				var cse = document.getElementById("cse0").value;
				var rporc = document.getElementById("rporc0").value;
				

				var txtCodusuario = document.getElementById("txtCodusuario").value;
				
				var rmed = document.getElementById("rmed0").value;

				// alert("medo: "+med0);
				if (med0 == "Seleccione") {
					med0 = 0;
				}

				var cc = "fechamo";
				var e = document.getElementById(cc).value;
				
				// alert("viste: "+raqx+" y med: "+actqx);
				if (descth0 == "" || serv0 == "" || desct0 == ""
						|| servich0 == "" || cant0 == "" || cant0 == "0"
						|| e == "") {
					// alert("Debe Digitar todos los campos a ingresar");
				} else {
					if ((rmed == "1") && (med0 == "null")) {
						sw2=1;
						
						sw=1;
					} else {

						if (valorpyp0 == "") {
							
							sw3=1;
							sw=1;
						} else {

							var pop = document.getElementById("pop").value;
							var refhi = document.getElementById("refhi").value;
							if(refhi==""){
								refhi="na";
							}
							
							var subc = document.getElementById("subc").value;
							
							
							if(actqx==""){
								actqx=null;
							}
							if(rporc==""){
								rporc=null;
							}
							movimientos=movimientos+"|"+pop+"|"+serv0+"|"+refhi+"|"+e+"|"+ref0+"|"+descth0+"|"+desct0+"|"+servich0+"|"+cant0+"|"+med0+"|"+valorpyp0+"|"+enca+"|"+txtCodusuario+"|"+cse+"|"+subc+"|"+actqx+"|"+rporc;
							
										
							
							
						}// sino if de la tarifa o el valor
					}// sino else medico

				}
				
			
			}
			
			// //
		}
			
		// alert("sw="+sw);
// ///////////////////////////////////////////////
		if(sw2!=0){	
		alert("El Medico es requerido para este programa!!!")
		}
		if(sw3!=0){	
		alert("El programa no tiene tarifa ");
		}
		
	if(sw==0){	
		//alert("sw:=0");
		//alert("movi"+movimientos);
		
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var lotil = document
						.getElementById("lotil").value;
				var encas = document
						.getElementById("encas").value;

				if (x == checkboxes.length)
					Detalleh(encas, lotil,opcion)
					
					//alert("Cargue Exitoso3!!!");
			}
		}
		ajax.setRequestHeader("Content-type",
							"application/x-www-form-urlencoded; charset=UTF-8");
		
		
		movimientos=movimientos+"|";
		movimientos=encodeURIComponent(movimientos);
		//alert("encode"+movimientos);
		ajax.send("valor=6h&movh=" + movimientos);
		
	}
	// ///////////////////////////////////////////////////
		
		
		
	}// sino acto qx
}

function CargarFacth(enca,opcion) {
	// alert("CargarFacth");
	// alert("cargando hospitaliacsddfnkljita");
	var contenido = document.getElementById('creacion');
	var desct0 = document.getElementById("desct0").value;
	var ref0 = document.getElementById("ref0").value;
	var descth0 = document.getElementById("descth0").value;
	var servich0 = document.getElementById("servich0").value;
	var serv0 = document.getElementById("serv0").value;
	var cant0 = document.getElementById("cant0").value;
	var med0=0;
	if(document.getElementById("medh0")!=null){
		if(document.getElementById("medh0").value!=""){
			med0 = document.getElementById("medh0").value;
		}
		
	}
	// alert("CargarFacth: "+med0);
	var valorpyp0 = document.getElementById("valorpyp0").value;
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var actqx = document.getElementById("actqx0").value;
	var taq = document.getElementById("taq0").value;
	// var rporc=document.getElementById("rporc"+j).value;
	var movimientos = "";
	// alert("taq: "+taq);
	var raqx = document.getElementById("raqx0").value;
	var rmed = document.getElementById("rmed0").value;

	//
	// servich0 = encodeURIComponent(servich0);

	// alert("2225222: "+actqx+" 8888: "+raqx);

	
	
	
	// alert(med0);

	// si acto quirurgico es = seleccione ponerle un valor q acepte la dbn
	var cc = "fechamo";
	var e = document.getElementById(cc).value;
	// alert(valorpyp0);alert(med0);
	// alert(" servich0 "+servich0);
	// "+med0+" valorpyp0 "+valorpyp0+" txtCodusuario "+txtCodusuario+" pop
	// "+pop+" refhi "+refhi);

	// alert("visterrrr: "+rmed+" y med: "+med0);
	if (descth0 == "" || serv0 == "" || desct0 == ""
			|| servich0 == "" || cant0 == "" || cant0 == "0" || e == "") {
		alert("Debe Digitar todos los campos a ingresar cfh");
	} else {
		if ((raqx == "1") && (actqx == "Seleccione")) {
			alert("El acto Quirurgico es requerido para este programa!!!")
		} else {
			if ((rmed == "1") && (med0 == "0")) {
				alert("El Medico es requerido para este programa!!!")
			} else {

				if (actqx == "Seleccione"||document.getElementById('actqx0').disabled==true)
					actqx = "null";
				
				if (taq == "Seleccione")
					taq = "null";

				if (valorpyp0 == "") {
					alert("El programa no tiene tarifa ");
				} else {
					var x = confirm("Desea Cargar el Movimiento");
					if (x) {
					var pop = document.getElementById("pop").value;
					var refhi = document.getElementById("refhi").value;
					var cse = document.getElementById("cse0").value;
					var subc = document.getElementById("subc").value;

					// /
					movimientos=movimientos+"|"+pop+"|"+serv0+"|"+refhi+"|"+e+"|"+ref0+"|"+descth0+"|"+desct0+"|"+servich0+"|"+cant0+"|"+med0+"|"+valorpyp0+"|"+enca+"|"+txtCodusuario+"|"+cse+"|"+subc+"|"+actqx+"|"+taq;
					// alert(movimientos);
					// /
					ajax = getXMLObject();
					ajax.open("POST", "ControlMovimientos", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							var lotil = document.getElementById("lotil").value;
							var encas = document.getElementById("encas").value;

							Detalleh(encas, lotil,opcion);
						//	alert("Cargue Exitoso.");
						}
					}
					movimientos=movimientos+"|";

					movimientos = encodeURIComponent(movimientos);
					ajax.setRequestHeader("Content-type",
							"application/x-www-form-urlencoded; charset=UTF-8");
					ajax.send("valor=6h&movh=" + movimientos);
				}
				}// sino if de la tarifa o el valor
			}// sino acto qx
		}// sino else medico
	}

}

function CargarFactce(enca, me, fe) {
	// alert("CargarFactce");
	var contenido = document.getElementById('creacion');
	var desct0 = document.getElementById("desct0").value;
	var ref0 = document.getElementById("ref0").value;
	var descth0 = document.getElementById("descth0").value;
	var servich0 = document.getElementById("servich0").value;
	var serv0 = document.getElementById("serv0").value;
	var cant0 = document.getElementById("cant0").value;
	var med0 = document.getElementById("med0").value;
	//alert("CargarFactce: "+med0);
	var valorpyp0 = document.getElementById("valorpyp0").value;
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	// alert(med0);
	// if (med0=="Seleccione"){med0=null;}

	// alert(med0);
	var cc = "fechamo";
	var e = document.getElementById(cc).value;

	// alert(valorpyp0);alert(med0);
	// alert("contenido "+contenido+" desct0 "+desct0+" ref0 "+ref0+" descth0
	// "+descth0+" servich0 "+servich0+" serv0 "+serv0+" cant0 "+cant0+" med0
	// "+med0+" valorpyp0 "+valorpyp0+" txtCodusuario "+txtCodusuario+" pop
	// "+pop+" refhi "+refhi);

	if (descth0 == "" || serv0 == "" || desct0 == ""
			|| servich0 == "" || cant0 == "" || med0 == "Seleccione"
			|| cant0 == "0" || e == "") {
		alert("Debe Digitar todos los campos a ingresar cfce");
	} else {
		if (valorpyp0 == "") {
			alert("El programa no tiene tarifa ");
		} else {
			var x = confirm("Desea Cargar el Movimiento");
			if (x) {

			var pop = document.getElementById("pop").value;
			var refhi = document.getElementById("refhi").value;
			var cse = document.getElementById("cse").value;
			var subc = document.getElementById("subc").value;

			ajax = getXMLObject();
			ajax.open("POST", "ControlMovimientos", true);
			ajax.onreadystatechange = function() {
				if (ajax.readyState == 4) {
					var lotil = document.getElementById("lotil").value;
					var encas = document.getElementById("encas").value;
					// alert(me+" : "+fe)

					var dias = e.substring(0, 2);
					var meses = e.substring(3, 5);
					var anos = e.substring(6, 10);
					var efes = anos + "-" + meses + "-" + dias;

					DetalleCE(encas, lotil, me, efes);
					//alert("Cargue Exitoso.");
				}
			}
			ajax.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded; charset=UTF-8");
			ajax.send("valor=6&pop=" + pop + "&serv0=" + serv0 + "&refhi="
					+ refhi + "&mtarpp=" + e + "&nivel=" + ref0 + "&texto="
					+ descth0 + "&xx=" + desct0 + "&mbase0p=" + servich0
					+ "&desch0p=" + cant0 + "&venc=" + med0 + "&critp="
					+ valorpyp0 + "&lot=" + enca + "&Abreviado="
					+ txtCodusuario + "&cse=" + cse + "&subc=" + subc);
		}
		}// sino if de la tarifa o el valor
	}

}

function CrearT() {

	var contenido = document.getElementById('creacion');
	var mbasec = document.getElementById("mbasec").value;
	var descch0 = document.getElementById("descch0").value;
	var fechai0 = document.getElementById("fechai0").value;
	var fechaf0 = document.getElementById("fechaf0").value;
	var valor0 = document.getElementById("valor0").value;

	// alert("mbasec "+mbasec+" descch0 "+descch0+" fechai0 "+fechai0+" fechaf0
	// "+fechaf0+" valor0 "+valor0);

	var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
	var cc = "fechai0";
	var e = document.getElementById(cc).value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	} else {

		var ccf = "fechaf0";
		var ef = document.getElementById(ccf).value;
		var returnvalf = fechafilter.test(ef)
		if (returnvalf == false) {
			alert("La fecha final es Invalida");
			document.getElementById(ccf).value = "";
			document.getElementById(ccf).focus();
		} else {
			var sdi = e.substring(0, 2);
			var smi = e.substring(3, 5);
			var sai = e.substring(6);
			var sdf = ef.substring(0, 2);
			var smf = ef.substring(3, 5);
			var saf = ef.substring(6);
			var di = parseInt(sdi);
			var mi = parseInt(smi);
			var ai = parseInt(sai);
			var df = parseInt(sdf);
			var mf = parseInt(smf);
			var af = parseInt(saf);

			// alert("di "+di+" mi "+mi+" ai "+ai+" df "+df+" mf "+mf+" af
			// "+af);
			var val2 = 0;
			if (ai == af) {
				// a=parseFloat(prompt("Escribe el primer nmero",""));
				if (mi == mf) {
					if (di < df) {
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						val2 = 1;
					}
				} else {
					if (mi < mf) {
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						val2 = 1;
					}
				}
			}
			if (ai > af) {
				alert("La fecha de Inicial no puede ser mayor que la fecha final");
				val2 = 1;
			}

			if (val2 == 0) {

				if (mbasec == "Seleccione" || descch0 == "" || fechai0 == ""
						|| fechaf0 == "" || valor0 == "") {
					alert("Debe Digitar todos los campos a ingresar");
				} else {

					ajax = getXMLObject();
					ajax.open("POST", "ControlCrearTarifas", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {

							alert(ajax.responseText);
							// alert("Ingreso exitoso!!!");
							// window.location.reload();
							// contenido.innerHTML ="";
							uno();

						}
					}
					ajax.setRequestHeader("Content-type",
							"application/x-www-form-urlencoded; charset=UTF-8");
					ajax.send("valor=3&mbasec=" + mbasec + "&descch0="
							+ descch0 + "&fechai0=" + fechai0 + "&fechaf0="
							+ fechaf0 + "&valor0=" + valor0);

				}
			}// fecha final mayor q inicial val2=0
		}// fecha inicial
	}// fecha final
}

function EliminarT(ct) {

	var contenido = document.getElementById('creacion');

	var x = confirm("Desea Eliminar la Tarifa seleccionada?");
	if (x) {

		ajax = getXMLObject();
		ajax.open("POST", "ControlCrearTarifas", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {

				alert(ajax.responseText);
				uno();

			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=5&ct=" + ct);
	}
}

function GenerarT() {

	var contenido = document.getElementById('creacion');

	var mbasec = document.getElementById("mtarn").value;
	var fechai0 = document.getElementById("fechai0").value;
	var fechaf0 = document.getElementById("fechaf0").value;
	var valor0 = document.getElementById("valor0").value;

	var mtarp = document.getElementById("mtara").value;
	var descch0 = document.getElementById("factor").value;

	var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
	var cc = "fechai0";
	var e = document.getElementById(cc).value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	} else {

		var ccf = "fechaf0";
		var ef = document.getElementById(ccf).value;
		var returnvalf = fechafilter.test(ef)
		if (returnvalf == false) {
			alert("La fecha final es Invalida");
			document.getElementById(ccf).value = "";
			document.getElementById(ccf).focus();
		} else {
			var sdi = e.substring(0, 2);
			var smi = e.substring(3, 5);
			var sai = e.substring(6);
			var sdf = ef.substring(0, 2);
			var smf = ef.substring(3, 5);
			var saf = ef.substring(6);
			var di = parseInt(sdi);
			var mi = parseInt(smi);
			var ai = parseInt(sai);
			var df = parseInt(sdf);
			var mf = parseInt(smf);
			var af = parseInt(saf);

			// alert("di "+di+" mi "+mi+" ai "+ai+" df "+df+" mf "+mf+" af
			// "+af);
			var val2 = 0;
			if (ai == af) {
				// a=parseFloat(prompt("Escribe el primer nmero",""));
				if (mi == mf) {
					if (di < df) {
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						document.getElementById(cc).focus();
						val2 = 1;
					}
				} else {
					if (mi < mf) {
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						document.getElementById(cc).focus();
						val2 = 1;
					}
				}
			}
			if (ai > af) {
				alert("La fecha de Inicial no puede ser mayor que la fecha final");
				document.getElementById(cc).focus();
				val2 = 1;
			}

			if (val2 == 0) {

				// alert("mbasec "+mbasec+" descch0 "+descch0+" fechai0
				// "+fechai0+" fechaf0 "+fechaf0+" valor0 "+valor0);

				if (mbasec == "Seleccione" || mtarp == "Seleccione"
						|| fechai0 == "" || fechaf0 == "" || valor0 == ""
						|| descch0 == "Seleccione") {
					alert("Debe Digitar todos los campos para generar la nueva tarifa");
				} else {

					this.form1.elements['btnConsultarPq'].disabled = true;
					contenido.innerHTML = '<p class="style6"><div align="center" >Cargando Resultados...</p><img src="Imagenes/cargando.gif">';

					ajax = getXMLObject();
					ajax.open("POST", "ControlCrearTarifas", true);
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							// alert("Ya lo realiz");
							alert(ajax.responseText);
							Radio();
						}
					}
					ajax.setRequestHeader("Content-type",
							"application/x-www-form-urlencoded; charset=UTF-8");
					ajax
							.send("valor=7&mbasec=" + mbasec + "&descch0="
									+ descch0 + "&fechai0=" + fechai0
									+ "&fechaf0=" + fechaf0 + "&valor0="
									+ valor0 + "&mtarp=" + mtarp);

				}
			}// fecha final mayor q inicial val2=0
		}// fecha inicial
	}// fecha final
}

// /////////////////
// ***Consultar s***/

function ConsultarF() {
	// alert("consultarFAct");
	ajax = getXMLObject();
	var contenido1 = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido1.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=30"); // Posting txtname to Servlet
}


function AuditarF() {
	// alert("consultarFAct");
	ajax = getXMLObject();
	var contenido1 = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido1.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=30A"); // Posting txtname to Servlet
}
// //////////////////////
function CFactA() {

	var contenido = document.getElementById('resultadosf');
	var vu = document.getElementById("cmbuser").value;
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

	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
			&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "")&& (vu == "Seleccione")) {
		alert("Debe Selccionar al menos un criterio de busqueda!!!");
	} else {
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlMovimientos", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText
						// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=31A&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9+ "&vu=" + vu); // Posting txtname to
				// Servlet
			}
		}
	}
}


function ReporteA() {//////////////////////////////
	var ssql="";
	//hacer el reporte de anulacion de factura
	//necesito pasar los parametros y la consulta creada como un sql para complementar la consulta del jasper
	var vue = document.getElementById("cmbuser").value;
	var v1e = document.getElementById("txtFact").value;
	var v22e = document.getElementById("Ent").selectedIndex;
	var v2e = document.getElementById("Ent").options[v22e].value;
	var v33e = document.getElementById("tdoc").selectedIndex;
	var v3e = document.getElementById("tdoc").options[v33e].text;
	var v4e = document.getElementById("txtDoc").value;
	var v5e = document.getElementById("txtNom").value;
	var v6e = document.getElementById("txtPa").value;
	var v7e = document.getElementById("txtSa").value;
	var v8e = document.getElementById("FI").value;
	var v9e = document.getElementById("FF").value;

	if(vue != "Seleccione"){ssql=ssql+" AND ef.cod_usuario_fk='"+vue+"'";}
	if(v1e != ""){ssql=ssql+" AND n.consecutivo='"+v1e+"'";}
	if(v2e != "Seleccione"){ssql=ssql+" AND ef.cod_eps='"+v2e+"'";}
	if(v4e != ""){ssql=ssql+" AND p.tipo_documento='"+v3e+"' AND p.numero_documento='"+v4e+"'";}
	if(v5e != ""){ssql=ssql+" AND p.nombre like '"+v5e+"%'";}
	if(v6e != ""){ssql=ssql+" AND p.primer_apellido like '"+v6e+"%'";}
	if(v7e != ""){ssql=ssql+" AND p.segundo_apellido like '"+v7e+"%'";}


	if((v8e != "")&&(v9e != "")){
		var dv8=v8e.substring(0, 2);
		var mv8=v8e.substring(3, 5);
		var av8=v8e.substring(6, 10);
		var fv8=av8+"-"+mv8+"-"+dv8;
		
		var dv9=v9e.substring(0, 2);
		var mv9=v9e.substring(3, 5);
		var av9=v9e.substring(6, 10);
		var fv9=av9+"-"+mv9+"-"+dv9;
		
	ssql=ssql+" AND ef.fecha BETWEEN '"+fv8+"' AND '"+fv9+"' ";
	}


		

	//alert(venc);
	pagina = "fact_ReporteAudita.jsp?ssql=" + ssql;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);



	}////////////////////////////////


// //////////////////////




function RadiosEnvioFact() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlMovimientos",true); // getname will be
															// the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=0"); // Posting txtname to Servlet
}


function RadiosRadicaFact() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlMovimientos",true); // getname will be
															// the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=00"); // Posting txtname to Servlet
}
// /////////////////////
function RadiosRadicaFactI() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlMovimientos",true); // getname will be
															// the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=00I"); // Posting txtname to Servlet
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
	ajax.open("POST","ControlMovimientos",true); // getname will be the
													// servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			
			contenido.innerHTML = ajax.responseText;	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor="+val+"&opcion="+opcion); // Posting txtname to Servlet
	
}

// //////////////////////
function ModificarTExistente(ct, v, y) {

	var contenido = document.getElementById('resul');
	// alert(ct+" - "+v+" - "+y);
	if (y == 1) {
		var mbasec = document.getElementById("mbasec" + v).value;
		var descch0 = document.getElementById("desch" + v).value;
		// alert(mbasec);
	}
	if (y == 2) {
		var comp = document.getElementById("comp" + v).value;
		// alert(comp);
	}
	if (y == 3) {
		var fechai = document.getElementById("fechai" + v).value;
		// alert(fechai);
	}
	if (y == 4) {
		var fechaf = document.getElementById("fechaf" + v).value;
		// alert(fechaf);
	}
	if (y == 5) {
		var valor0 = document.getElementById("valor" + v).value;
		// alert(valor0);
	}

	// alert("mbase "+mbase+" comp "+comp+" cref "+cref+" cups "+cups+" desc
	// "+desc+" espe "+espe+" serv "+serv+" subc "+subc+" rips "+rips);

	ajax = getXMLObject();
	ajax.open("POST", "ControlCrearTarifas", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if (ajax.responseText != "") {
				alert(ajax.responseText);
			}
			// contenido.innerHTML = ajax.responseText
			// Radio();
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");

	// ajax.send("valor=6&mbase="+mbase+"&comp="+comp+"&cref="+cref+"&cups="+cups+"&desc="+desc+"&espe="+espe+"&serv="+serv+"&subc="+subc+"&rips="+rips);

	if (y == 1) {
		ajax.send("valor=4&mbasec=" + mbasec + "&y=" + y + "&v=" + v + "&ct="
				+ ct + "&descch0=" + descch0);
	}
	if (y == 2) {
		ajax.send("valor=6&valor0=" + valor0 + "&y=" + y + "&v=" + v + "&ct="
				+ ct);
	}
	if (y == 3) {
		ajax.send("valor=4&fechai0=" + fechai + "&y=" + y + "&v=" + v + "&ct="
				+ ct);
	}
	if (y == 4) {
		ajax.send("valor=4&fechaf0=" + fechaf + "&y=" + y + "&v=" + v + "&ct="
				+ ct);
	}
	if (y == 5) {
		ajax.send("valor=4&valor0=" + valor0 + "&y=" + y + "&v=" + v + "&ct="
				+ ct);
	}
}
// ////////////

function CFacturas(venc) {

	pagina = "fact_ReporteFacturaCopia.jsp?venc=" + venc;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}
// ////////////^[0-9]*$/;

var emailfilterno = /^[0-9]{1,3}(\.[0-9]{0,2})?$/;
function checknumt(c) {
	var cc = "valor" + c;
	var e = document.getElementById(cc).value;
	var returnval = emailfilterno.test(e)

	var x = e.substr(0, 1);
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}

	if (returnval == false) {
		if (e != "") {
			alert("Formato incorrectoj!!!");
			document.getElementById(cc).value = "";
			document.getElementById(cc).focus();
		}
	}
	return returnval
}

var emailfilternot = /^[0-9]*$/;
function checknum(c) {
	var cc = "valor" + c;
	var e = document.getElementById(cc).value;
	var returnval = emailfilternot.test(e)

	var x = e.substr(0, 1);
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}

	if (returnval == false) {
		alert("Formato incorrecto!!!");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	return returnval
}

var emailfilterg = /^[0-9]*$/;
function checknumg(c) {
	var cc = "Compartida" + c;
	var e = document.getElementById(cc).value;
	var peso = e.substring(0, 1);
	if (peso == "$") {
		peso = e.substring(1);
		e = peso;
	}

	var long = e.length;
	for ( var i = 0; i <= long; i++) {
		e = e.replace(".", "");
	}

	var returnval = emailfilterg.test(e)

	var x = e.substr(0, 1);
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}

	if (returnval == false) {
		alert("Formato incorrecto!!!");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	
	return returnval
}

// funci de la fecha
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio) {
	var sep = "/";
	if (d.valant != d.value) {
		val = d.value
		largo = val.length
		ini = val.substring(0, 2);
		if ((ini > 31) || (ini == "00")) {
			// alert("Dia No Valido!!!");
			val = d.value = "01";
			// d.focus();
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
			// alert("Mes No Valido!!!");
			mescj = val2.substring(0, 2);
			val2 = mescj + "01";
			// d.focus();
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
	var elEvento =  window.event;  // arguments[0] ||
    var tecla = elEvento.keyCode;
    if(tecla == 13) { 
    		//document.getElementById("ref0").focus();
    	document.getElementById("desct0").focus();
    		
    }
	
}

// /////////////////
var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function vfi() {

	var e = document.getElementById("FI").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha inicial es Invalida");
		document.getElementById("FI").value = "";
		// document.getElementById("FI").focus();
	}
	return returnval
}

function vff() {

	var e = document.getElementById("FF").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha final es Invalida");
		document.getElementById("FF").value = "";
		// document.getElementById("FF").focus();
	}
	return returnval
}
// ////////////////////////
var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function checkfecha(c) {
	var cc = "fechai" + c;
	var e = document.getElementById(cc).value;
	var returnval = fechafilter.test(e)
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
// ////////////////////////////////////////////////////////////////////////////////////////////////////////

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

function CFact() {

	var contenido = document.getElementById('resultadosf');
	
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

	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
			&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "")) {
		alert("Debe Selccionar al menos un criterio de busqueda!!!");
	} else {
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlMovimientos", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText
						// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=31&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9); // Posting txtname to
				// Servlet
			}
		}
	}
}

function BuscarFactPEnviar(ea) {

	var contenido = document.getElementById('resultadosf');

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

	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
			&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "")) {
		alert("Debe Selccionar al menos un criterio de busqueda!!!");
	} else {
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlMovimientos", true); // getname will
				// be the
				// servlet name
				document.getElementById('resultadosf').innerHTML='<p>Cargando Lista de Facturas... No Cierre La Ventana...</p><img src="Imagenes/ani.gif">';
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText
						if(ea==0){
							//alert("btnEnvFact");
						document.getElementById('btnEnvFact').innerHTML="<table><tr><td colspan='10' align='center'><input type='button' value='Enviar' onclick='enviarFact(0)'/></td></tr></table>";
						}if(ea==1){
							document.getElementById('btnEnvFact').innerHTML="<table><tr><td colspan='10' align='center'><input type='button' value='Enviar' onclick='enviarFact(1)'/></td></tr></table>";
						}	// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showFact&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9+ "&ea=" + ea); // Posting txtname to
				// Servlet
			}
		}
	}
}


function BuscarFactPRadicar(ri) {
	//alert("cesar"+ri);
		var contenido = document.getElementById('resultadosf');

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
		
		//alert(v10);
		//v10=v10.substring(6);
		
		
		if(ri=="0"){
			VR=v10.substring(0,6);
			//alert(VR);
			if(VR=="CTA000"){v10=v10.substring(6);}
			//solo hay q quitar este if que sigue y queda ammarada al envio y modificar la consulta GeneraSQLSelFactR
			//if(VR!=""){v10=""; alert("Radicacion directa no depende de envio!!!")}
			
			}
			if(ri=="1"){
			VR=v10.substring(0,6);
			//alert(VR);
			if(VR=="REL000"){v10=v10.substring(6);}
			//else{v10="";}
			}
		
		
		
		//alert(v10);
		// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+" - "+v9+" - "+v10);
		if ((v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
				&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "") && (v10 == "")){
			alert("Debe Selccionar al menos un criterio de busqueda!!!");
		} else {
			if ((v8 != "") && (v9 == "")) {
				alert("Debe seleccionar el rango de fecha final!!!");
			} else {
				if ((v9 != "") && (v8 == "")) {
					alert("Debe seleccionar el rango de fecha inicial!!!");
				} else {
					ajax = getXMLObject();
					ajax.open("POST", "ControlMovimientos", true); // getname will
					// be the
					// servlet name
					document.getElementById('resultadosf').innerHTML='<p>Cargando Lista de Facturas... No Cierre La Ventana...</p><img src="Imagenes/ani.gif">';
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							contenido.innerHTML = ajax.responseText
							document.getElementById('btnEnvFact').innerHTML="<table><tr><td colspan='10' align='center'><input type='button' value='Radicar' onclick='radicarFact("+ri+")'/></td></tr></table>";
							// medicosPYP();
						}
					}
					ajax.setRequestHeader('Content-Type',
							'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=showFactR&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
							+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
							+ "&v8=" + v8 + "&v9=" + v9 + "&v10=" + v10+ "&ri=" + ri); // Posting txtname to
					// Servlet
				}
			}
		}
	}



function repitefecha(){
	var zz = document.getElementById('fradt').value;
  
	var fechast = document.getElementsByName("frad");
	for (var x = 0; x < fechast.length; x++) {
		//alert(x);
		fechast[x].value=zz;     
	}
  

}

function todoscheck(){
	//alert("");
	var principal = document.getElementsByName("radicatodo");
	var radioButtons = document.getElementsByName("radiorad");
	
	if (principal[0].checked) {
		for (var x = 0; x < radioButtons.length; x++) {
			radioButtons[x].checked=true;
		}
	}else{
		for (var x = 0; x < radioButtons.length; x++) {
			radioButtons[x].checked=false;
		}
	}

}


function autoChecked(object, criterio,venc,enca,lot) {
	desmarcarEnca(venc,enca,lot);
	if (object.checked) {
		var hijos = document.getElementById('numLineas').value;
	
		for ( var i = 0; i < hijos; i++) {
			var valor = document.getElementById('check' + i).value;

			var detValor = valor.split("-");
			if (detValor[1] == criterio) {
				document.getElementById('check' + i).checked = true;
			}
			if (criterio == "TODOS") {
				document.getElementById('check' + i).checked = true;
			}
		}
	} else {
		var hijos = document.getElementById('numLineas').value;
		for ( var i = 0; i < hijos; i++) {
			var valor = document.getElementById('check' + i).value;

			var detValor = valor.split("-");
			if (detValor[1] == criterio) {
				document.getElementById('check' + i).checked = false;
			}
			if (criterio == "TODOS") {
				document.getElementById('check' + i).checked = false;
			}
		}
	}
}

function autoCheckedCorte(object){
/*
 * var objAll = document.getElementById('chkAll'); var objPos =
 * document.getElementById('chkPos'); var objNPos =
 * document.getElementById('chkNPos'); var objMat =
 * document.getElementById('chkMat'); var objLab =
 * document.getElementById('chkLab'); var objImg =
 * document.getElementById('chkImg');
 * 
 * if (object.checked) {
 * //if(objAll.checked==false&&objPos.checked=false&&objNPos.checked=false&&objMat.checked=false&&objLab.checked=false&&objImg.checked=false){
 * //} } else {
 *  }
 */
}

function factDetallada(codAdmision,enca,lot) {
	//alert("encabezado     ssss "+codAdmision+" - "+enca+" - "+lot);
	var hijos = document.getElementById('numLineas').value;
	var movSinMarcar = hijos;
	//
	var contenidos = document.getElementById('resultodos');
	var contenido = document.getElementById('BM');
	//
	var movimientos = "";
	for ( var i = 0; i < hijos; i++) {
		if (document.getElementById('check' + i).checked) {
			var infoCheck = document.getElementById('check' + i).value;
			var detalle = infoCheck.split("|");
			movimientos += detalle[0] +","+ detalle[2] +"_";
			movSinMarcar--;
		}
	}
	
	if(movSinMarcar!=hijos){
		movimientos = movimientos.substring(0, movimientos.length - 1);
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
					contenidos.innerHTML=ajax.responseText;
					contenido.style.display='none';
			}
		}
		//alert(movimientos);
		//movimientos=EncodeURIComponent(movimientos);
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		//alert("valor=32&movimientos=" + movimientos + "&codAdmision="+ codAdmision+ "&enca="+ enca+ "&lot="+ lot);
		ajax.send("valor=32&movimientos=" + movimientos + "&codAdmision="+ codAdmision+ "&enca="+ enca+ "&lot="+ lot);
	}else{
		alert("No ha seleccionado ningun movimiento");
	}
}

function verFact(enca,codAdmision,lot,opcion){
	// alert(enca+" "+codAdmision+" "+lot+" "+opcion);
	ventDetEnca(enca,codAdmision,lot,opcion);
}

function moverMovA(object,venc,eps){
	
	var hijos = document.getElementById('numLineas').value;
	var movSinMarcar = hijos;
	var enca = object.value;
	for ( var i = 0; i < hijos; i++) {
		if (document.getElementById('check' + i).checked) {
			movSinMarcar--;
		}
	}
	
	if(hijos!=movSinMarcar){	
		var contenido = document.getElementById('BM');
		contenido.innerHTML = "<table width='100%' class='style6' ><tr><td colspan='6'  width='10%'><div align='center'><input type='button' name='btnPre' id='btnPre' value='Mover' onClick='moverA(&quot;"+enca+"&quot;,&quot;"+venc+"&quot;,&quot;"+eps+"&quot;)'></div></td></tr></table>";
	}	
}

function moverA(enca,venc,eps){
	
	var hijos = document.getElementById('numLineas').value;
	var movSinMarcar = hijos;
	//
	var contenidos = document.getElementById('resultodos');
	var contenido = document.getElementById('BM');
	//
	var movimientos = "";
	for ( var i = 0; i < hijos; i++) {
		if (document.getElementById('check' + i).checked) {
			var infoCheck = document.getElementById('check' + i).value;
			var detalle = infoCheck.split("|");
			movimientos += detalle[0] +","+ detalle[2] +"_";
			movSinMarcar--;
		}
	}
	
	if(movSinMarcar!=hijos){
		
		movimientos = movimientos.substring(0, movimientos.length - 1);
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Traslado de movimiento exitoso");
				contenidos.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=33&movimientos=" + movimientos + "&enca="+ enca+ "&venc="+ venc+ "&lot="+ eps);
	}else{
		alert("No ha seleccionado ningun movimiento");
	}
}


function GF(venc,enca,lot) {
	var contenido = document.getElementById('mensajeDetEnca');

	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=35&enca=" + enca+"&venc=" + venc+"&lot=" + lot+"&opcion=");

}


function desmarcarEnca(venc,enca,lot){
	//alert("desmarcarEnca")
	var numEnca = document.getElementById('numEnca').value;
	for (var i=1;i<numEnca;i++){
		if(document.getElementById('rad'+i).checked){
			document.getElementById('rad'+i).checked=false;
		}
	}
	
	var contenido = document.getElementById('BM');
	contenido.innerHTML = "<table width='100%' class='style6' ><tr><td><div style='margin-left:420px;'><input  style='float:left; margin_right=10px'type='button' name='btnPre' id='btnPre' value='Facturar' onClick='factDetallada(&quot;"+venc+"&quot;,&quot;"+enca+"&quot;,&quot;"+lot+"&quot;)'><input type='button' name='btnPre' id='btnPre' value='Movimientos' onClick='pruebaChevel("+venc+","+lot+","+enca+")'  ></div></td></tr></table>";
		
}


var fondoDetEnca = false; 
var mensajeDetEnca = false;
// var res = false;

function ventDetEnca(enca,codAdmision,lot,opcion){
	// alert(enca+" "+codAdmision+" "+lot);
	fondoDetEnca = document.createElement('div');
	mensajeDetEnca = document.createElement('div');
	// res = document.createElement('div');
	mensajeDetEnca.setAttribute('id','mensajeDetEnca');
	fondoDetEnca.setAttribute('id','fondoDetEnca');
	// res.setAttribute('id','resul');
	document.getElementsByTagName('body')[0].appendChild(fondoDetEnca);
	document.getElementsByTagName('body')[0].appendChild(mensajeDetEnca);
	// document.getElementsByTagName('body')[0].appendChild(res);

	window.scroll (0,0) ; 
	document.body.style.overflow="hidden";
	cargarMensVent(enca,codAdmision,lot,opcion);
}

function cargarMensVent(enca,codAdmision,lot,opcion){
	// alert(opcion);
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			// alert(ajax.responseText);
			mensajeDetEnca.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=34&enca="+ enca+"&venc="+codAdmision+"&lot="+lot+"&opcion="+opcion);
}

function cerrarVentDetEnca(venc,enca,lot){
	
	// opcion 0:ventana inicial 1:ventana emergente
	// alert(venc+" "+enca);
	if(mensajeDetEnca!=false){
		document.getElementsByTagName('body')[0].removeChild(mensajeDetEnca);
		document.getElementsByTagName('body')[0].removeChild(fondoDetEnca);
		mensajeDetEnca=false;
		fondoDetEnca=false;
	}
	
	
	var contenidos = document.getElementById('resultodos');
	// alert(document.getElementById('resultodos').value);
	// alert(contenidos);
	
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			// alert(ajax.responseText);
			if (ajax.responseText!="actualizar"){
				// alert(ajax.responseText);
				contenidos.innerHTML=ajax.responseText;
				document.getElementById('BM').style.display='inline';
				document.getElementById('conResul').style.display='none';
			}else{
				window.location.href="fact_FacturarH.jsp";
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=33&movimientos=&enca="+ enca+ "&venc="+ venc+ "&lot="+ lot);
}


function anularMovFact(codMov,enca0,enca,venc,lot,horaMov){
	//alert(" anularMovFact"+codMov+" "+enca0+" "+enca+" "+venc+" "+lot+" "+horaMov);
	codMov += ","+horaMov;
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			if(ajax.responseText!="anulado"){
				var mensajeDetEnca = document.getElementById(mensajeDetEnca);
				cargarMensVent(enca,venc,lot,0);
			}else{
				cerrarVentDetEnca2(venc, enca, lot);
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=36&enca0="+enca0+"&codMov="+codMov+"&enca="+enca+"&venc="+venc);
}

function cerrarVentDetEnca2(venc,enca,lot){
	//alert("3: "+lot);
	// opcion 0:ventana inicial 1:ventana emergente
	// alert(venc+" "+enca);
	if(mensajeDetEnca!=false){
		document.getElementsByTagName('body')[0].removeChild(mensajeDetEnca);
		document.getElementsByTagName('body')[0].removeChild(fondoDetEnca);
		mensajeDetEnca=false;
		fondoDetEnca=false;
	}
	
	
	var contenidos = document.getElementById('resultodos');
	// alert(document.getElementById('resultodos').value);
	// alert(contenidos);
	
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			// alert(ajax.responseText);
			if (ajax.responseText!="actualizar"){
				// alert(ajax.responseText);
				contenidos.innerHTML=ajax.responseText;
				document.getElementById('BM').style.display='inline';
				document.getElementById('conResul').style.display='none';
			}else{
				window.location.href="fact_FacturarH.jsp?op=0&venc="+venc+"&lot="+lot+"&enca="+enca;
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=33&movimientos=&enca="+ enca+ "&venc="+ venc+ "&lot="+ lot);
}

function pruebaChevel(venc,lot,enca){
	//alert("fact_MovimientosH.jsp?op=0&venc="+venc+"&lot="+lot+"&enca="+enca);
	window.location.href="fact_MovimientosH.jsp?op=0&venc="+venc+"&lot="+lot+"&enca="+enca;
}

function pruebaChevelA(venc,lot,enca){
	window.location.href="fact_MovimientosA.jsp?op=0&venc="+venc+"&lot="+lot+"&enca="+enca;
}
function FacturarM(){

	enca = document.getElementById("enca").value;
	venc =	document.getElementById("adm0").value;
	lot =	document.getElementById("epsh0").value;
	//alert(" Facturar M  enca "+enca+" venc "+venc+" lot "+lot);
	window.location.href="fact_FacturarH.jsp?op=0&venc="+venc+"&lot="+lot+"&enca="+enca;
}

function FacturarMA(){

	enca = document.getElementById("enca").value;
	venc =	document.getElementById("adm0").value;
	lot =	document.getElementById("epsh0").value;
	// alert(enca+" "+venc+" "+lot);
	window.location.href="fact_FacturarA.jsp?op=0&venc="+venc+"&lot="+lot+"&enca="+enca;
}

function PreFactAdm(venc) {
	// alert(venc);

 	if(confirm(" Desea ver el reporte agrupado por usuario?")){
		   //alert("X Usuario");
		   pagina = "fact_ReportePreFacturaAdm.jsp?venc=" + venc;
		  // return false;
	}else {
		//	alert("X Fecha");
		   pagina = "fact_ReportePreFacturaAdm2.jsp?venc=" + venc;
	      // return false;
        }

	

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}


function enviarFact(ea){
	
	//alert("enviarFact "+ea);
	document.getElementById('btnEnvFact').innerHTML="";
	var eqEntidad = true;
	var childTablaFact = document.getElementById('listFact').childNodes[0].childNodes;
	var factSeleccionadas = "";
	var totalFact = 0;
	var EntParaComp = "";
	var VALORNC = "";
	for (i=0;i<childTablaFact.length-1;i++){
		if(document.getElementById('chkEF'+i).checked){
			factSeleccionadas+=document.getElementById('chkEF'+i).value+"_";
			totalFact+=parseInt(document.getElementById('vEF'+i).childNodes[0].nodeValue);
			VALORNC+=document.getElementById('NC'+i).childNodes[0].nodeValue+"_";
			EntParaComp+=document.getElementById('ent'+i).childNodes[0].nodeValue+"_";
			
		}
	}
	
	factSeleccionadas=factSeleccionadas.substring(0, factSeleccionadas.length-1);
	VALORNC=VALORNC.substring(0, VALORNC.length-1);
	EntParaComp=EntParaComp.substring(0, EntParaComp.length-1);
	//alert(VALORNC);
	var detEntidades = EntParaComp.split("_");
	for (j=1;j<detEntidades.length;j++){
		if(detEntidades[0]!=detEntidades[j]){
			eqEntidad = false;
			j = detEntidades.length
		}
	}

	var valorLetras = letras(totalFact+"");
	
	if(factSeleccionadas!=""&&eqEntidad){
		var usuario = document.getElementById('txtCodusuario').value;
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				// OpcionEnvioFact('sfe');
				var cta = ajax.responseText;
				//alert ("Aqui me traje el consecutivo de la cuenta: "+cta);
				
				//error***********
				BuscarFactPEnviar(ea);
				
				alert("Proceso exitoso");
				if(ea==0){
				mostrarDetCTA(cta,"");
				}
				if(ea==1){
					//alert(cta+"ctaaaaa")
					mostrarDetCTAA(cta,"");
				}
				
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=envFact&facturas="+factSeleccionadas+"&vFacturas="+totalFact+"&vlFacturas="+valorLetras+"&usuario="+usuario+"&ea="+ea+"&NC="+VALORNC);
	}else{
		if(factSeleccionadas==""){
			alert("Debe seleccionar por lo menos una factura");
		}
		if(!eqEntidad){
			alert("Para generar el consolidado de las facturas a enviar \n" +
				  "tienen que pertenecer todas las facturas a la misma entidad");
		}
		
	}
		
}


function radicarFact(ri){//Hay q definir bn este metodo que es una copia de enviar facturas
	//alert("entramos a radicar: "+fcj);
	document.getElementById('btnEnvFact').innerHTML="";
	
	var frad = "";
	var no=0;
	var eqEntidad = true;
	var childTablaFact = document.getElementById('listFact').childNodes[0].childNodes;
	var factSeleccionadas = "";
	var totalFact = 0;
	var EntParaComp = "";
	var VALORNC="";
	
	for (i=0;i<childTablaFact.length-1;i++){
		if(document.getElementById('chkEF'+i).checked){
			factSeleccionadas+=document.getElementById('chkEF'+i).value+"_";
			totalFact+=parseInt(document.getElementById('vEF'+i).childNodes[0].nodeValue);
			VALORNC+=document.getElementById('NC'+i).childNodes[0].nodeValue+"_";
			EntParaComp+=document.getElementById('ent'+i).childNodes[0].nodeValue+"_";
			//alert("YA: "+document.getElementById('frad'+i).value);
			if (document.getElementById('frad'+i).value!=""){
				fcj=document.getElementById('ffr'+i).value;
				f1=(document.getElementById('frad'+i).value);
				f2=(fcj);

				//alert("esta es la validacion: "+f1+" - "+f2);
				a=compare_dates(f1,f2);
				/*if(compare_dates(f1,f2)){
				alert(f1+" es mayor que "+f2);
				}else{alert(f1+" es menor que "+f2);}
			*/
			if(a==false){ no=1; document.getElementById('frad'+i).value=""; }
			frad+=document.getElementById('frad'+i).value+"_";
			}
			if (document.getElementById('frad'+i).value==""){no=1;}
						
		}

	}

	factSeleccionadas=factSeleccionadas.substring(0, factSeleccionadas.length-1);
	VALORNC=VALORNC.substring(0, VALORNC.length-1);
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
	 if(no==0){
		var usuario = document.getElementById('txtCodusuario').value;
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				// OpcionEnvioFact('sfe');
				var cta = ajax.responseText;
				//alert ("Aqui me traje el consecutivo de la cuenta: "+cta);
				BuscarFactPRadicar(ri);
				alert("Proceso exitoso");
				
				if(ri==0){
					//alert(ri+"ri rad deberamos");
					mostrarDetRAD(cta,"");
				}
				if(ri==1){
					//alert(ri+"ri radi");
					mostrarDetRADI(cta,"");
				}
				
			}
		}
	
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=radFact&facturas="+factSeleccionadas+"&vFacturas="+totalFact+"&vlFacturas="+valorLetras+"&usuario="+usuario+"&frad="+frad+"&ri="+ri+"&NC="+VALORNC);
	 }else{alert("Debe escribir la fecha de radicacion de cada una de las facturas seleccionadas y asegurarse que esta fecha sea mayor que la fecha de la factura");
	 document.getElementById('btnEnvFact').innerHTML="<table><tr><td colspan='10' align='center'><input type='button' value='Radicar' onclick='radicarFact("+ri+")'/></td></tr></table>";
		
	 }
	 }else{
        	if(factSeleccionadas==""){
			alert("Debe seleccionar al menos una factura para radicar");
			document.getElementById('btnEnvFact').innerHTML="<table><tr><td colspan='10' align='center'><input type='button' value='Radicar' onclick='radicarFact("+ri+")'/></td></tr></table>";
			
		}
		if(!eqEntidad){
			alert("Para generar el consolidado de las facturas a enviar \n" +
				  "tienen que pertenecer todas las facturas a la misma entidad");
			document.getElementById('btnEnvFact').innerHTML="<table><tr><td colspan='10' align='center'><input type='button' value='Radicar' onclick='radicarFact("+ri+")'/></td></tr></table>";
			
		}
		
	}
		
}



function compare_dates(fecha, fecha2)   
{   
   var xMonth=fecha.substring(3, 5);   
   var xDay=fecha.substring(0, 2);   
   var xYear=fecha.substring(6,10);   
   var yMonth=fecha2.substring(3, 5);   
   var yDay=fecha2.substring(0, 2);   
   var yYear=fecha2.substring(6,10);   
   if (xYear> yYear)   
   {   
       return(true)   
   }   
   else  
   {   
     if (xYear == yYear)   
     {    
       if (xMonth> yMonth)   
       {   
           return(true)   
      }   
      else  
       {    
         if (xMonth == yMonth)   
         {   
           if (xDay>= yDay)   
             return(true);   
           else  
             return(false);   
         }   
         else  
           return(false);   
       }   
     }   
     else  
       return(false);   
   }   
} 










function BuscarCuentaDeCobro(opcion) {

	var contenido = document.getElementById('resultadosf');
	var v11 = document.getElementById("txtCdC").value;
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

	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v11 == "") &&(v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
			&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "")) {
		alert("Debe Selccionar al menos un criterio de busqueda!!!");
	} else {
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlMovimientos", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText
						// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showCC&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9+ "&v11=" + v11+ "&opcion=" +opcion); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
	}
}


function BuscarCuentaDeRadicado(opcion,ra) {
	//alert(ra);
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');
	//var v11 = document.getElementById("txtCdC").value;
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
	// alert("v10: "+v10);
	
	if(ra=="0"){
	VR=v10.substring(0,4);
	//alert(VR);
	if(VR=="RAD0"){v10=v10.substring(4);}
	//else{v10="";}
	}
	if(ra=="1"){
	VR=v10.substring(0,6);
	//alert(VR);
	if(VR=="REL000"){v10=v10.substring(6);}
	//else{v10="";}
	}
	if(ra=="2"){
		VR=v10.substring(0,5);
		//alert(VR);
		if(VR=="RADI0"){v10=v10.substring(5);}
		//else{v10="";}
	}
	
	//alert("v10: "+v10);
	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
			&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "") && (v10 == "")) {
		alert("Debe Selccionar al menos un criterio de busqueda!!!");
	} else {
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlMovimientos", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showCR&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9 + "&v10=" + v10 + "&opcion=" +opcion+ "&ra=" +ra); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
	}
}


function mostrarDetCTAA(consCTA,opcion){

	//alert("FALTA CREAR ESTA JSP MAS UN JASPER MAS LAS 2 TABLAS consCTA: "+consCTA);
	pagina = "fact_ReporteCtaCobro.jsp?venc=" + consCTA;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
	mostrarCartaCTA(consCTA);

}


function mostrarCartaCTA(consCTA){
	pagina= "fact_ReporteCartaCta.jsp?venc=" + consCTA;
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA2", opciones);
	if(confirm(" Desea imprimir las facturas de la cuenta de cobro?")){
		mostrarFacturasCTA(consCTA);
	}
	

}

function mostrarFacturasCTA(consCTA){
	pagina= "fact_FacturasCTA.jsp?venc=" + consCTA;
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA3", opciones);

}


function mostrarDetCTA(consCTA,opcion){
	//alert("consCTA: "+consCTA);
	pagina = "fact_ReporteCtaCobroA.jsp?venc=" + consCTA;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
	if(confirm(" Desea ver la carta remisoria?")){
		mostrarCartaCTA(consCTA);
	}else {
		
     }
	
/*


// alert(consCTA+" "+opcion);
fondoDetEnca = document.createElement('div');
mensajeDetEnca = document.createElement('div');
// res = document.createElement('div');
mensajeDetEnca.setAttribute('id','mensajeDetEnca');
fondoDetEnca.setAttribute('id','fondoDetEnca');
// res.setAttribute('id','resul');
document.getElementsByTagName('body')[0].appendChild(fondoDetEnca);
document.getElementsByTagName('body')[0].appendChild(mensajeDetEnca);
// document.getElementsByTagName('body')[0].appendChild(res);

window.scroll (0,0) ; 
document.body.style.overflow="hidden";
cargarDetCTA(consCTA,opcion,document.getElementById('mensajeDetEnca'));

*/
}


function cargarDetCTA(consCTA,opcion,contenido){
	/*ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); 
	
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=detCTA&consCTA="+consCTA+"&opcion="+opcion);
*/
}

function cerrarVentdetCTA(opcion){/*
	document.getElementsByTagName('body')[0].removeChild(mensajeDetEnca);
	document.getElementsByTagName('body')[0].removeChild(fondoDetEnca);
	mensajeDetEnca=false;
	fondoDetEnca=false;
	BuscarCuentaDeCobro(opcion);	
	*/
}

function mostrarDetRADI(consCTA,opcion){
	//alert("consCTA: "+consCTA);
	pagina = "fact_ReporteCtaRadicadaI.jsp?venc=" + consCTA;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);

}

function mostrarDetRAD(consCTA,opcion){
	//alert("consCTA: "+consCTA);
	pagina = "fact_ReporteCtaRadicada.jsp?venc=" + consCTA;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
	/*
	// alert(consCTA+" "+opcion);
	fondoDetEnca = document.createElement('div');
	mensajeDetEnca = document.createElement('div');
	// res = document.createElement('div');
	mensajeDetEnca.setAttribute('id','mensajeDetEnca');
	fondoDetEnca.setAttribute('id','fondoDetEnca');
	// res.setAttribute('id','resul');
	document.getElementsByTagName('body')[0].appendChild(fondoDetEnca);
	document.getElementsByTagName('body')[0].appendChild(mensajeDetEnca);
	// document.getElementsByTagName('body')[0].appendChild(res);

	window.scroll (0,0) ; 
	document.body.style.overflow="hidden";
	cargarDetRAD(consCTA,opcion,document.getElementById('mensajeDetEnca'));
	*/
}

function cargarDetRAD(consCTA,opcion,contenido){
	/*
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); 
	
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=detRAD&consCTA="+consCTA+"&opcion="+opcion);
	*/
}

function cerrarVentdetRAD(opcion){
	/*
	document.getElementsByTagName('body')[0].removeChild(mensajeDetEnca);
	document.getElementsByTagName('body')[0].removeChild(fondoDetEnca);
	mensajeDetEnca=false;
	fondoDetEnca=false;
	BuscarCuentaDeRadicado(opcion);	
	*/
}

function Imprime(){
	
	if (window.print)
	window.print()
	else
	alert("Para imprimir presione Crtl+P.");
	
}


function ConsultarEstadoFactura(opcion) {

	var contenido = document.getElementById('resultadosf');
	//var v11 = document.getElementById("txtCdC").value;
	var v1 = document.getElementById("txtFact").value;
	var v22 = document.getElementById("Ent").selectedIndex;
	var v2 = document.getElementById("Ent").options[v22].value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;

	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v1 == "") && (v2 == "Seleccione") && (v8 == "") && (v9 == "")) {
		alert("Debe Seleccionar al menos un criterio de busqueda!!!");
	} else {
		if((v2 != "Seleccione")&&(v8 == "") && (v9 == "")){
			alert("Debe seleccionar el rango de fecha!!!");			
		}else{
			if ((v8 != "") && (v9 == "")) {
				alert("Debe seleccionar el rango de fecha final!!!");
			} else {
				if ((v9 != "") && (v8 == "")) {
					alert("Debe seleccionar el rango de fecha inicial!!!");
				} else {
					ajax = getXMLObject();
					ajax.open("POST", "ControlMovimientos", true); // getname
																	// will
					// be the
					// servlet name
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							contenido.innerHTML = ajax.responseText
							// medicosPYP();
						}
					}
					ajax.setRequestHeader('Content-Type',
							'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=showEF&v1=" + v1 + "&v2=" + v2 + 
							"&v8=" + v8 + "&v9=" + v9+ "&opcion=" +opcion); // Posting
																							// txtname
																							// to
					// Servlet
				}
			}
		}
	}
}

function mostrarDetMovFact(consFact,opcion){
	// alert(consCTA+" "+opcion);
	fondoDetEnca = document.createElement('div');
	mensajeDetEnca = document.createElement('div');
	// res = document.createElement('div');
	mensajeDetEnca.setAttribute('id','mensajeDetEnca1');
	fondoDetEnca.setAttribute('id','fondoDetEnca');
	// res.setAttribute('id','resul');
	document.getElementsByTagName('body')[0].appendChild(fondoDetEnca);
	document.getElementsByTagName('body')[0].appendChild(mensajeDetEnca);
	// document.getElementsByTagName('body')[0].appendChild(res);

	window.scroll (0,0) ; 
	document.body.style.overflow="hidden";
	cargarDetMovFact(consFact,opcion,document.getElementById('mensajeDetEnca1'));
}

function cargarDetMovFact(consFact,opcion,contenido){
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); 
	
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=showDMF&consFact="+consFact+"&opcion="+opcion);
}

function cerrarVentdetMOV(opcion){
	document.getElementsByTagName('body')[0].removeChild(mensajeDetEnca1);
	document.getElementsByTagName('body')[0].removeChild(fondoDetEnca);
	mensajeDetEnca=false;
	fondoDetEnca=false;
	ConsultarEstadoFactura(opcion);	
}

function ConsultarAdmSinFacturar() {
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=admsinfact"); // Posting txtname to Servlet
}


function ConsultarAdmSnFacturar(opcion) {

	var contenido = document.getElementById('resultadosf');
	
	var v22 = document.getElementById("Ent").selectedIndex;
	var v2 = document.getElementById("Ent").options[v22].value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;

	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if ((v2 == "Seleccione") && (v8 == "") && (v9 == "")) {
		alert("Debe Selccionar al menos un criterio de busqueda!!!");
	} else {
		if((v2 != "Seleccione")&&(v8 == "") && (v9 == "")){
			alert("Debe seleccionar el rango de fecha!!!");			
		}else{
			
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlMovimientos", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText
						// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showADMsnFact&v2=" + v2 + 
						"&v8=" + v8 + "&v9=" + v9+ "&opcion=" +opcion); // Posting
																		// txtname
																		// to
				// Servlet
			}
			
		}
	}
}

function mostrarActoQx(){
	var tipoActoQx = document.getElementById('taq0').value;
	var eps = document.getElementById('epsh0').value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			document.getElementById('mensajeDetEnca4').innerHTML = ajax.responseText;
			var acQx = document.getElementById('mensajeDetEnca4').childNodes[0];
			if(tipoActoQx!='Seleccione'){
				var hijo_acQx = acQx.childNodes[1];
				if(hijo_acQx.childNodes[0].nodeValue.length<18){
					document.getElementById('mensajeDetEnca4').className="mensajeDetEnca4c";
				}else{
					if(hijo_acQx.childNodes[0].nodeValue.length>=18&&hijo_acQx.childNodes[0].nodeValue.length<=35){
						document.getElementById('mensajeDetEnca4').className="mensajeDetEnca4a";
					}else{
						if(hijo_acQx.childNodes[0].nodeValue.length>35){
							document.getElementById('mensajeDetEnca4').className="mensajeDetEnca4b";
						}
					}
				}
			}
				
			
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=3h1&taq=" + tipoActoQx+"&eps="+eps);		
}

document.onclick = function (e) {
	e = e || event
	var target = e.target || e.srcElement
	var elemento = document.getElementById('mensajeDetEnca4');
	if(elemento!=null){
		do{
			if(elemento == target){
				return;
			}
			target = target.parentNode;
		}while (target){
			elemento.className="mensajeDetEnca4";
		}
	}
}

function lIn(object){
	object.className='lOut';
}

function lOut(object){
	object.className='lIn';
}

function rolloverOut(){
	document.getElementById('pr').className = 'rout';
}
function rolloverIn(){
	document.getElementById('pr').className = 'rin';
}

function seleccionarli(object){
	var texto = object.childNodes[0].nodeValue;
	document.getElementById('pr').value = texto.substring(0,14);
	document.getElementById('actqx0').value = object.value;
	document.getElementById('mensajeDetEnca4').className="mensajeDetEnca4";
}

function resetPr(){
	document.getElementById('pr').value = 'Seleccione';
}

function RadiosAuditaConvenios() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlMovimientos",true); // getname will be
															// the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=ac01"); // Posting txtname to Servlet
}

function OpcionConvenio(val, opcion) {
	
	var contenido=document.getElementById('resultadoa');
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
	ajax.open("POST","ControlMovimientos",true); // getname will be the
													// servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			
			contenido.innerHTML = ajax.responseText;	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	//alert("valor="+val+"&opcion="+opcion); // Posting txtname to Servlet
	ajax.send("valor="+val+"&opcion="+opcion); // Posting txtname to Servlet
	
}




/////////////////////////////////////////////////////////////

////////////////////////PACIENTES CAPITADOS//////////////////

function Capita(op,venc,lot,enca) {
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); 
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;		
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1C");
}


function ConsultarPctesCapitados(ceps,eps,venc,ca,hoa){
	var indice =  document.getElementById("Ent").selectedIndex;
	var eps = document.getElementById('Ent').options[indice].value;
	var fi=document.getElementById('FI').value;
	var ff=document.getElementById('FF').value;
	var swc=0;
	var swc2=0;
	var tipoFact=0;
	
	var urg=0;
	var hos=0;
	var amb=0;
	var cex=0;
	var pyp=0;
	
	if(eps=="Seleccione"){
	 alert("Debe seleccionar una Entidad como criterio de consulta ");
	}else{//1
		if(fi==""){
			 alert("Debe seleccionar la fecha inicial a consultar ");
		}else{//2
			if(ff==""){
				 alert("Debe seleccionar la fecha final a consultar ");
			}else{//3
				a=compare_dates(fi,ff);
				if((a==false)||(fi==ff)){ //3.2
					if(document.getElementById('urg').checked){
						urg=1; tipoFact=1;
					}	
					if(document.getElementById('hos').checked){
						hos=1; tipoFact=0; if((urg==1)){ swc2=1; swc=1;}
					}
					if(document.getElementById('amb').checked){
						amb=1; tipoFact=2; if((urg==1)||(hos==1)){swc2=1; swc=1;}
					}
					if(document.getElementById('cex').checked){
						cex=1; tipoFact=2; if((urg==1)||(hos==1)||(amb==1)){swc2=1; swc=1;}
					}
					if(document.getElementById('pyp').checked){
						pyp=1; tipoFact=2; if((urg==1)||(hos==1)||(amb==1)||(cex==1)){swc2=1; swc=1;}
					}
					if((urg==0)&&(hos==0)&&(amb==0)&&(cex==0)&&(pyp==0)){
						alert("Debe seleccionar al menos un tipo de convenio a consultar");
						swc=1;
					}
				}else{
					alert("La fecha final no puede ser menor que la fecha inicial!!"); 
					swc=1; 
				}//3.2
			}//3
		}//2
	}//1	
		
	if((swc==0)&&(eps!="Seleccione")&&(fi!="")&&(ff!="")){
		var contenido = document.getElementById('Resultados');
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				contenido.innerHTML = ajax.responseText;
				var vtc=document.getElementById('vtc').value;
				FactCapita(eps,vtc,tipoFact);
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2C&eps="+eps+"&fi="+fi+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
	}
}


function FactCapita(eps,vtc,tipoFact){
	var contenido = document.getElementById('ResultadosC2');
	var fi=document.getElementById('FI').value;
	var ff=document.getElementById('FF').value;
	var xx=document.getElementById('vtc').value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2C2&eps="+eps+"&vtc="+vtc+"&fi="+fi+"&ff="+ff+"&xx="+xx+"&tipoFact="+tipoFact);
}

var emailfilternot = /^[0-9]*$/;
function checknumcapita(c,v) {
	var cc = "valor" + c;
	var e = document.getElementById(cc).value;
	var returnval = emailfilternot.test(e)

	var x = e.substr(0, 1);
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}

	if (returnval == false) {
		alert("Formato incorrecto!!!");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
		document.getElementById("diferencia").innerHTML="";
	}else{
		var f=parseInt(document.getElementById(cc).value)-parseInt(v);
		if (f < 0) {
		document.getElementById("diferencia").innerHTML="<FONT COLOR='#FF0000'>"+f;
		}else{
			document.getElementById("diferencia").innerHTML=f;
				
		}
	}
	
	return returnval
}



function FactC(eps,tipoFact){
	
	hsql=document.getElementById("sql1").value;
	encau=document.getElementById("encau").value;
	admiu=document.getElementById("admiu").value;
	
	cfc=document.getElementById("cfce").value;
	vfc=document.getElementById("valor1").value;
	var usuario = document.getElementById('txtCodusuario').value;
	var fi=document.getElementById('FI').value;
	var ff=document.getElementById('FF').value;
	
	var subtl = letras(vfc+""); 
	
	var contenido = document.getElementById('Opcion');
	
	ajax = getXMLObject();
	document.getElementById('ResultadosC2').innerHTML='<p>Generando Factura... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var enca= ajax.responseText;
			FacturaAmbulatoria(eps,enca);
			PacientesAgrupados(enca);
			window.location.href="fact_FacturarCapita.jsp";
				
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3C&hsql="+hsql+"&encau="+encau+"&admiu="+admiu+"&cfc="+cfc+"&vfc="+vfc+"&usuario="+usuario+"&fi="+fi+"&ff="+ff+"&eps="+eps+"&subtl="+subtl+"&tipoFact="+tipoFact); // Posting
}



function CargarP(ceps,eps,venc,ca,hoa){
	var contenido = document.getElementById('Opcion');
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			 contenido.innerHTML = ajax.responseText;
			 //alert(contenido);
			 //Detalleh(venc,ceps);
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1PC&ceps="+ceps+"&eps="+eps); // Posting
}




function Subir(ceps,eps,venc,ca,hoa){
	
	//alert(document.getElementById('archivo').value);
	
	var rut=document.getElementById("archivo").value;
	var fso = new ActiveXObject("Scripting.FileSystemObject");
	
	x=rut.length;
	y=rut.substring(x-3);
	
	if((y=="txt")||(y=="TXT")){

	if (fso.FileExists(rut))
	 {
		// alert("El archivo existe");
		 
		 var arch = fso.OpenTextFile(rut,1,false); 
		 //arch.WriteLine(ajax.responseText);
		 sReadLine = arch.ReadAll();
		 
		 alert(sReadLine);
		 
		 var detDatos = sReadLine.split("\n");///Dividimos las lineas y con comas (,) los campos
		 
		 alert("valor 2:  "+detDatos[2]);
		 
		 arch.Close();	
		 
	/*	 objTextFile = objFSO.OpenTextFile("c:\\HowToDemoFile.txt", ForReading);

		// Use different methods to read contents of file.
		sReadLine = objTextFile.ReadLine();
		sRead = objTextFile.Read(4);
		sReadAll = objTextFile.ReadAll();
*/
	 }else{
		 alert("NO Existe el archivo seleccionado!!!");
	 }
	}else{alert("Tipo de archivo no valido... Solo se aceptan extensiones .txt");}
/*	
	var contenido = document.getElementById('Opcion');
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			 contenido.innerHTML = ajax.responseText;
			 alert(contenido);
			 //Detalleh(venc,ceps);
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1PC&ceps="+ceps+"&eps="+eps); // Posting
	*/
}



function whatFile() {
	alert("cesar");

		var frm = document.frmAnadir;
		var archivo = frm.archivo.value;
		nom = archivo.substring(archivo.lastIndexOf("\\")+1,archivo.length-4);
		frm.nombre.value=nom;
		alert(nom);

	
	//window.location= 'file://' + document.cmuds.value;
}



///////////////////////////////////////////////////FACTURACION AGRUPADA/////////////////////////////////////////

function Agrupacion(op,venc,lot,enca) {
	//alert("CAPITA "+op+" - "+venc+" - "+lot+" - "+enca);
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); //
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;		
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1A"); // 
}



function ConsultarPctesAgrupados(ceps,eps,venc,ca,hoa){
	
	var indice =  document.getElementById("Ent").selectedIndex;
	var eps = document.getElementById('Ent').options[indice].value;
	//var epsn = document.getElementById('epsh0').options[indice].text;
	var fi=document.getElementById('FI').value;
	var ff=document.getElementById('FF').value;
	var swc=0;
	var swc2=0;
	var tipoFact=0;
	
	var urg=0;
	var hos=0;
	var amb=0;
	var cex=0;
	var pyp=0;
	
	if(eps=="Seleccione"){
	 alert("Debe seleccionar una Entidad como criterio de consulta ");
	}else{//1
		if(fi==""){
			 alert("Debe seleccionar la fecha inicial a consultar ");
		}else{//2
			if(ff==""){
				 alert("Debe seleccionar la fecha final a consultar ");
			}else{//3
				
			
			
			a=compare_dates(fi,ff);
			if((a==false)||(fi==ff)){ //3.2
				
				if(document.getElementById('urg').checked){
					urg=1; tipoFact=1;
				}
				
					if(document.getElementById('hos').checked){
						hos=1; tipoFact=0; if((urg==1)){ swc2=1; swc=1;}
					}
						
					
						if(document.getElementById('amb').checked){
							amb=1; tipoFact=2; if((urg==1)||(hos==1)){swc2=1; swc=1;}
						}
					
					
							if(document.getElementById('cex').checked){
								cex=1; tipoFact=2; if((urg==1)||(hos==1)||(amb==1)){swc2=1; swc=1;}
							}
								
								if(document.getElementById('pyp').checked){
									pyp=1; tipoFact=7; if((urg==1)||(hos==1)||(amb==1)||(cex==1)){swc2=1; swc=1;}
								}
								
				
				if((urg==0)&&(hos==0)&&(amb==0)&&(cex==0)&&(pyp==0)){
					alert("Debe seleccionar al menos un tipo de convenio a consultar");
				swc=1;
				}
				
				if(swc2==1){alert("Debe seleccionar solo un tipo de ingreso a facturar");}
				
				
			}else{
				alert("La fecha final no puede ser menor que la fecha inicial!!"); 
			 	swc=1; 
			 }//3.2
			}//3
		}//2
	}//1	
		
	if((swc==0)&&(eps!="Seleccione")&&(fi!="")&&(ff!="")){
		//alert(swc+" "+eps+" "+fi+" "+ff);
		var contenido = document.getElementById('Resultados');
		ajax = getXMLObject();
		//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
		ajax.open("POST", "ControlMovimientos", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				contenido.innerHTML = ajax.responseText;
				var vtc=document.getElementById('vtc').value;
				FactAgrupada(eps,vtc,tipoFact);
				
			}
		}
		ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2A&eps="+eps+"&fi="+fi+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
	}
}




function FactAgrupada(eps,vtc,tipoFact){
	var contenido = document.getElementById('ResultadosC2');
	var fi=document.getElementById('FI').value;
	var ff=document.getElementById('FF').value;
	var xx=document.getElementById('vtc').value;
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			//var vtc=document.getElementById('vtc').value;
			//FactCapita(eps,vtc);
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2A2&eps="+eps+"&vtc="+vtc+"&fi="+fi+"&ff="+ff+"&xx="+xx+"&tipoFact="+tipoFact);//+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
}



function CheckedoNo(object,criterio) {
	//desmarcarEnca(venc,enca,lot);
	if (object.checked) {
		var hijos = document.getElementById('cc').value;
	
		for ( var i = 1; i <= hijos; i++) {
			var valor = document.getElementById('check' + i).value;

			var detValor = valor.split("-");
			if (detValor[1] == criterio) {
				document.getElementById('check' + i).checked = true;
			}
			if (criterio == "TODOS") {
				document.getElementById('check' + i).checked = true;
			}
		}
	} else {
		var hijos = document.getElementById('cc').value;
		for ( var i = 1; i <= hijos; i++) {
			var valor = document.getElementById('check' + i).value;

			var detValor = valor.split("-");
			if (detValor[1] == criterio) {
				document.getElementById('check' + i).checked = false;
			}
			if (criterio == "TODOS") {
				document.getElementById('check' + i).checked = false;
			}
		}
	}
	Seleccionacheck(object);
}




function FactA(eps,tipoFact){
	
	var texto="";
	var hijos = document.getElementById('cc').value;
	
	for ( var i = 1; i <= hijos; i++) {
		var ch = document.getElementById('check' + i);
		if(ch.checked == true){
			texto=texto+ch.value+"|";
		}
	}
	
	//alert("Estos son los encabezados chequeados: "+texto);
	
	hsql=document.getElementById("sql1").value;
	encau=document.getElementById("encau").value;
	admiu=document.getElementById("admiu").value;
	copag=document.getElementById("copago").value;
	
	cfc=document.getElementById("cfce").value;
	vfc=document.getElementById("vtc").value;
	var usuario = document.getElementById('txtCodusuario').value;
	var fi=document.getElementById('FI').value;
	var ff=document.getElementById('FF').value;
	
	var subtl = letras(vfc+""); 
	
	
	//alert("Entramos a facturar "+eps+" - "+fi+" - "+ff+" - hsql: "+hsql);
	var contenido = document.getElementById('Opcion');
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			
			var enca= ajax.responseText;
			FacturaAmbulatoria(eps,enca);
			PacientesAgrupados(enca);
				window.location.href="fact_FacturaAgrupada.jsp";
				
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3A&hsql="+hsql+"&encau="+encau+"&admiu="+admiu+"&cfc="+cfc+"&vfc="+vfc+"&usuario="+usuario+"&fi="+fi+"&ff="+ff+"&eps="+eps+"&subtl="+subtl+"&texto="+texto+"&copag="+copag+"&tipoFact="+tipoFact); // Posting
}

function PacientesAgrupados(enca) {
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	if(confirm(" Desea ver el reporte de consulta externa?")){
		pagina = "fact_ReportePacientesAgrupados.jsp?venc=" + enca + "&txtCodusuario="+ txtCodusuario + "&tipo="+ 1;
	}else {
		pagina = "fact_ReportePacientesAgrupados.jsp?venc=" + enca + "&txtCodusuario="+ txtCodusuario + "&tipo="+ 2;
    }
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones	+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA2", opciones);
}


/*
function Seleccionacheck(object){
	
	var nivel="";
	var hijos = document.getElementById('cc').value;
	for ( var i = 1; i <= hijos; i++) {
		var ch = document.getElementById('check' + i);
		if(ch.checked == true){
			nivel=nivel+ch.value+"|";
		}
	}
	
	//recojo los totales y los actualizo x ajax
	//recuerda actualizar los hidden
	
	//var contenido = document.getElementById('ResultadosC2');
	//var xx=document.getElementById('vtc').value;
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+object+" - "+object.value+" - "+object.id+" - "+object.name);
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
					
			var detalle = ajax.responseText.split("|");
			
			document.getElementById('vtc').value=detalle[2];
			document.getElementById('copago').value=detalle[1];
			
			document.getElementById('subtpc').innerHTML=detalle[0];
			document.getElementById('copagpc').innerHTML=detalle[1];
			document.getElementById('totalpc').innerHTML=detalle[2];
			
			//alert(detalle[2]);
			if(parseInt(detalle[2])==0){
			document.getElementById('botonagrupado').disabled=true;
			}else{
				document.getElementById('botonagrupado').disabled=false;
			}
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4A&nivel="+nivel);//+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
}
*/


function Seleccionacheck(object){
	
	var nivel="";
	var hijos = document.getElementById('cc').value;
	for ( var i = 1; i <= hijos; i++) {
		var ch = document.getElementById('check' + i);
		if(ch.checked == true){
			nivel=nivel+ch.value+"|";
		}
	}
	
	//recojo los totales y los actualizo x ajax
	//recuerda actualizar los hidden
	
	//var contenido = document.getElementById('ResultadosC2');
	//var xx=document.getElementById('vtc').value;
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+object+" - "+object.value+" - "+object.id+" - "+object.name);
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	ajax.onreadystatechange = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
					
			var detalle = ajax.responseText.split("|");
			
			document.getElementById('vtc').value=detalle[2];
			document.getElementById('copago').value=detalle[1];
			
			
			var temp2="";
			var temp1="";
			var ud=1;
			var logCad = detalle[0].length;		
			for (var i=logCad-1;i>=0;i--){			
				temp2=detalle[0].substring(i,parseInt(i)+1);
				temp2+=temp1;
				if(parseInt(ud)==3){
					if(parseInt(i)!=0){temp1="."+temp2;}else{temp1=temp2;}ud=0;
				}else{temp1=temp2;}
				//parseInt(ud)=parseInt(ud)+1;
				ud++;
			}
			temp1="$ "+temp1;
			
			document.getElementById('subtpc').innerHTML=temp1;
			
			var temp2="";
			var temp1="";
			var ud=1;
			var logCad = detalle[1].length;		
			for (var i=logCad-1;i>=0;i--){			
				temp2=detalle[1].substring(i,parseInt(i)+1);
				temp2+=temp1;
				if(parseInt(ud)==3){
					if(parseInt(i)!=0){temp1="."+temp2;}else{temp1=temp2;}ud=0;
				}else{temp1=temp2;}
				//parseInt(ud)=parseInt(ud)+1;
				ud++;
			}
			temp1="$ "+temp1;
			
			
			document.getElementById('copagpc').innerHTML=temp1;
			
			
			var temp2="";
			var temp1="";
			var ud=1;
			var logCad = detalle[2].length;		
			for (var i=logCad-1;i>=0;i--){			
				temp2=detalle[2].substring(i,parseInt(i)+1);
				temp2+=temp1;
				if(parseInt(ud)==3){
					if(parseInt(i)!=0){temp1="."+temp2;}else{temp1=temp2;}ud=0;
				}else{temp1=temp2;}
				//parseInt(ud)=parseInt(ud)+1;
				ud++;
			}
			temp1="$ "+temp1;
			
			document.getElementById('totalpc').innerHTML=temp1;
			
			
			if(parseInt(detalle[2])==0){
			document.getElementById('botonagrupado').disabled=true;
			}else{
				document.getElementById('botonagrupado').disabled=false;
			}
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4A&nivel="+nivel);//+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
}

function P(){
	var a;
}


	

function Anexos(){
	var contenido = document.getElementById('Opcion');
	//var fi=document.getElementById('FI').value;
	//var ff=document.getElementById('FF').value;
	//var xx=document.getElementById('vtc').value;
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			//var vtc=document.getElementById('vtc').value;
			//FactCapita(eps,vtc);
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AT");//&eps="+eps+"&vtc="+vtc+"&fi="+fi+"&ff="+ff+"&xx="+xx+"&tipoFact="+tipoFact);//+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
}

function RevisarAnexo(adm){
	//alert(adm);
	var contenido = document.getElementById('Opcion');
	//var fi=document.getElementById('FI').value;
	//var ff=document.getElementById('FF').value;
	//var xx=document.getElementById('vtc').value;
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			//var vtc=document.getElementById('vtc').value;
			//FactCapita(eps,vtc);
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AT1&adm0="+adm);
			//&eps="+eps+"&vtc="+vtc+"&fi="+fi+"&ff="+ff+"&xx="+xx+"&tipoFact="+tipoFact);//+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
}

function remitido(){
	//alert("ddd");
	var contenido = document.getElementById('remitediv');
	
	var indice =  document.getElementById("vr").selectedIndex;
	var sn = document.getElementById('vr').options[indice].value;
	
	if(sn==1){
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AT2");
	}else{
		document.getElementById('remitediv').innerHTML="";
	}
}



function anexomun(){
	//alert("ddd");
	var contenido = document.getElementById('muni');
	var indice =  document.getElementById("anexdep").selectedIndex;
	var subt = document.getElementById('anexdep').options[indice].value;
	
	
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AT3&subt="+subt);
}


function GuardarAnexo(adm){
	//alert("Guardar "+adm);
	var aa = document.getElementById('FI').value;
	var ab = document.getElementById('hramy').value;
	var iac= document.getElementById('oatenc').selectedIndex;
	var ac = document.getElementById('oatenc').options[iac].text;
	//var ac = document.getElementById('oatenc').value;
	var iad = document.getElementById('tri').selectedIndex;
	var ad = document.getElementById('tri').options[iad].text;
	var iae= document.getElementById('vr').selectedIndex;
	var vae = document.getElementById('vr').options[iae].value;
	var ae = document.getElementById('vr').options[iae].text;
	if (vae==1){
	var af = document.getElementById('entr').value;
	var ag = document.getElementById('coder').value;
	var iah = document.getElementById('anexdep').selectedIndex;
	var ah = document.getElementById('anexdep').options[iah].value;
	var ai = document.getElementById('anexdep').options[iah].text;
	var iaj = document.getElementById('sccostoc').selectedIndex;
	var aj = document.getElementById('sccostoc').options[iaj].value;
	var ak = document.getElementById('sccostoc').options[iaj].text;
	}else{
		var af = "";
		var ag = "";
		var ah = "";
		var ai = "";
		var aj = "";
		var ak = "";
	}
	var al = document.getElementById('eac').value;
	var iam = document.getElementById('dpac').selectedIndex;
	var am = document.getElementById('dpac').options[iam].text;
	
	var an = document.getElementById('dxr').value;
	var ao = "";
	var aod = "";
	var ap = "";
	var apd = "";
	var aq = "";
	var aqd = "";
	var ar = "";
	var ard = "";
	
	if(an==4){
		 ao = document.getElementById('dxr'+1).value;
		 aod = document.getElementById('dsrd'+1).value;
		 ap = document.getElementById('dxr'+2).value;
		 apd = document.getElementById('dsrd'+2).value;
		 aq = document.getElementById('dxr'+3).value;
		 aqd = document.getElementById('dsrd'+3).value;
		 ar = document.getElementById('dxr'+4).value;
		 ard = document.getElementById('dsrd'+4).value;
	}
	if(an==3){
		 ao = document.getElementById('dxr'+1).value;
		 aod = document.getElementById('dsrd'+1).value;
		 ap = document.getElementById('dxr'+2).value;
		 apd = document.getElementById('dsrd'+2).value;
		 aq = document.getElementById('dxr'+3).value;
		 aqd = document.getElementById('dsrd'+3).value;
		 ar = "";
		 ard = "";
	}
	if(an==2){
		 ao = document.getElementById('dxr'+1).value;
		 aod = document.getElementById('dsrd'+1).value;
		 ap = document.getElementById('dxr'+2).value;
		 apd = document.getElementById('dsrd'+2).value;
		 aq = "";
		 aqd = "";
		 ar = "";
		 ard = "";
	}
	if(an==1){
		 ao = document.getElementById('dxr'+1).value;
		 aod = document.getElementById('dsrd'+1).value;
		 ap = "";
		 apd = "";
		 aq = "";
		 aqd = "";
		 ar = "";
		 ard = "";
	}
	
	al = encodeURIComponent(al);
	
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//contenido.innerHTML = ajax.responseText;
			ImprimirAnexo(adm);
			Anexos();
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AT4&adm0="+adm+"&aa="+aa+"&ab="+ab+"&ac="+ac+"&ad="+ad+"&ae="+ae+"&af="+af+"&ag="+ag+"&ah="+ah+"&ai="+ai+"&aj="+aj+"&ak="+ak+"&al="+al+"&am="+am+"&ao="+ao+"&aod="+aod+"&ap="+ap+"&apd="+apd+"&aq="+aq+"&aqd="+aqd+"&ar="+ar+"&ard="+ard);		
	}


//ImprimirAnexo
function ImprimirAnexo(adm){
	pagina = "fact_ReporteAnexoTecnico2.jsp?venc=" + adm;
var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
opciones = opciones
	+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
window.open(pagina, "NUEVA", opciones);

}



function Anexo3(adm,cama){
	//alert(adm+" - "+cama);
	if(adm==null){
	window.close();
	}else{
	var contenido = document.getElementById('Opcion');
	//var NumDoc=document.getElementById("doc").value;
	//var fi=document.getElementById('FI').value;
	//var ff=document.getElementById('FF').value;
	//var xx=document.getElementById('vtc').value;
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			document.getElementById('cant').readonly=false;
			document.getElementById('cant').removeAttribute('readonly','readonly');
			//var vtc=document.getElementById('vtc').value;
			//FactCapita(eps,vtc);
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ATT&adm0="+adm+"&cama="+cama);//&eps="+eps+"&vtc="+vtc+"&fi="+fi+"&ff="+ff+"&xx="+xx+"&tipoFact="+tipoFact);//+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
  }
}



function AdicionarCUPS(adm){
	var contenido = document.getElementById('cups');
		 
	var aa=document.getElementById('refh').value;
	var ab=document.getElementById('ref0').value;
	var ac=document.getElementById('ref1').value;
	var ad=document.getElementById('cant').value;
	
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			//var vtc=document.getElementById('vtc').value;
			//FactCapita(eps,vtc);
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ATT1&adm0="+adm+"&aa="+aa+"&ab="+ab+"&ac="+encodeURIComponent(ac)+"&ad="+ad);//&eps="+eps+"&vtc="+vtc+"&fi="+fi+"&ff="+ff+"&xx="+xx+"&tipoFact="+tipoFact);//+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
  }


function EliminarCUPS(aa,adm){
	var contenido = document.getElementById('cups');
		 
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			//var vtc=document.getElementById('vtc').value;
			//FactCapita(eps,vtc);
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ATT3&adm0="+adm+"&aa="+aa);//&eps="+eps+"&vtc="+vtc+"&fi="+fi+"&ff="+ff+"&xx="+xx+"&tipoFact="+tipoFact);//+"&ff="+ff+"&urg="+urg+"&hos="+hos+"&amb="+amb+"&cex="+cex+"&pyp="+pyp); // Posting
  }


function GuardarAnexo3(adm,admr){
	
	//alert("GuardarAnexo3 "+adm);
	var aa = document.getElementById('FI').value;
	var ab = document.getElementById('hramy').value;
	//var iac= document.getElementById('Ent').selectedIndex;
	//var ac = document.getElementById('Ent').options[iac].text;
	var iac=document.getElementsByName("oatenc")[0].selectedIndex;
	var ac=document.getElementsByName("oatenc").item(0).options[iac].text;

	var iad = document.getElementById('tri').selectedIndex;
	var ad = document.getElementById('tri').options[iad].text;
	var iae= document.getElementById('vr').selectedIndex;
	var vae = document.getElementById('vr').options[iae].value;
	var ae = document.getElementById('vr').options[iae].text;
	var iah = document.getElementById('espe').selectedIndex;
	var ah = document.getElementById('espe').options[iah].value;
	var ai = document.getElementById('espe').options[iah].text;
	var al = document.getElementById('eac').value;
		
	al = encodeURIComponent(al);
	
	ajax = getXMLObject();
	//alert("epsssssssssssssssssssssssssssssssssssssssssssss "+ceps+" - "+eps+" - "+venc+" - "+ca)
	ajax.open("POST", "ControlProgramas", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//contenido.innerHTML = ajax.responseText;
			ImprimirAnexo3(adm);
			//Anexos();
			//window.close();
			window.location.href="fact_Anexo3Consulta.jsp";
			
		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ATT2&adm0="+adm+"&aa="+aa+"&ab="+ab+"&ac="+ac+"&ad="+ad+"&ae="+ae+"&ai="+ai+"&al="+al);		

}

//ImprimirAnexo
function ImprimirAnexo3(adm){
	pagina = "fact_ReporteAnexoTecnico3.jsp?venc=" + adm;
var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
opciones = opciones
	+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
window.open(pagina, "NUEVA", opciones);

}

function ConsultarAnexos3(adm){
	
	 contenido = document.getElementById('Opcion');
	
		ajax = getXMLObject();
		ajax.open("POST", "ControlProgramas", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			 contenido.innerHTML = ajax.responseText
			//	Detalle(venc, lot,opcion)
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=ATT4&adm0="+adm);

}

function ConsultarPcteAgrup() {
	ajax = getXMLObject();
	var contenido1 = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos2", true); // getname will be the servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido1.innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=PA"); // Posting txtname to Servlet
}

function CFactAgrupados() {
	var contenido = document.getElementById('resultadosf');
	var v1 = document.getElementById("txtFact").value;
	var v22 = document.getElementById("Ent").selectedIndex;
	var v2 = document.getElementById("Ent").options[v22].value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;

	if ((v1 == "") && (v2 == "Seleccione") && (v8 == "") && (v9 == "")) {
		alert("Debe Selccionar al menos un criterio de busqueda!!!");
	} else {
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlMovimientos2", true); // getname will be the servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=PA1&v1=" + v1 + "&v2=" + v2 + "&v8=" + v8 + "&v9=" + v9); // Posting txtname to Servlet
			}
		}
	}
}




































////////////***********************GLOSAS************************************//////////////
function Glosas(x) {
	if(x==1){x="ag";}
	if(x==2){x="cg";}
	if(x==3){x="cgr";}
	if(x==4){x="cgf";}
	if(x==5){x="cgre";}
	if(x==6){x="cgpc";}
	if(x==7){x="cgrn";}
	if(x==8){x="cgren";}
	if(x==9){x="cgrc";}
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlRips",true); // getname will be
															// the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
					if((x=="cg")||(x=="cgr")||(x=="cgf")||(x=="cgre")||(x=="cgpc")||(x=="cgrn")||(x=="cgren")||(x=="cgrc")){
						document.getElementById("revisar").style.display ='none';
					}
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+x); // Posting txtname to Servlet
}




function OpcionEnvioFactG() {
	var contenido=document.getElementById('Opcion');
	if(val==null){
		
		var radioButtons = document.getElementsByName("radiobutton");
		for (var x = 0; x < radioButtons.length; x++) {
			if (radioButtons[x].checked) {
				var val=radioButtons[x].id;  
				
			}	     
		}
	}
	
	ajax=getXMLObject();
	ajax.open("POST","ControlRips",true); // getname will be the
													// servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			
			contenido.innerHTML = ajax.responseText;	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor="+val); // Posting txtname to Servlet
	
}



function BuscarFactGlosas() {
	//alert(ra);
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');
	//var v11 = document.getElementById("txtCdC").value;
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
//	var v10 = document.getElementById("CDC").value;
	// alert("v10: "+v10);
	
	
	//VR=v10.substring(0,4);
	//alert(VR);
	//if(VR=="RAD0"){v10=v10.substring(4);}
	//if(VR=="rad0"){v10=v10.substring(4);}
	//else{v10="";}

	if ((v1 == "") && (v2 == "Seleccione") && (v4 == "") && (v5 == "")
			&& (v6 == "") && (v7 == "") && (v8 == "") && (v9 == "") ) {
		alert("Debe Selccionar al menos un criterio de busqueda!!!");
	} else {
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosas&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
		
	}
}

function Notificar(v1,v2,v3,v4) {
	//alert ("Notificar: "+v1+" - "+v2+" - "+v3);
	//alert(v4);
	if(v4==undefined){
		if(confirm(" Desea Notificar glosa a la factura "+v2+"?")){
	 		
	 		ajax=getXMLObject();
	 		var contenido=document.getElementById('Opciones');
	 				ajax.open("POST","ControlRips",true); // getname will be
	 																// the servlet name
	 				ajax.onreadystatechange  = function(){
	 					if (ajax.readyState == 4) {
	 						document.getElementById("Opcion").style.display = "none";
	 						document.getElementById("Notifica").style.display ='none';
	 						document.getElementById("Opciones").style.display ='block';
	 						contenido.innerHTML = ajax.responseText;	
	 					}
	 				};
	 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 				ajax.send("valor=agf&v1=" + v1 + "&v2=" + v2+ "&v3=" + v3); // Posting txtname to Servlet
	 		
	 		
		}//fin glosa notificada
	}else{
		ajax=getXMLObject();
 		var contenido=document.getElementById('Opciones');
 				ajax.open("POST","ControlRips",true); // getname will be
 																// the servlet name
 				ajax.onreadystatechange  = function(){
 					if (ajax.readyState == 4) {
 						document.getElementById("Opcion").style.display = "none";
 						document.getElementById("Notifica").style.display ='none';
 						document.getElementById("Opciones").style.display ='block';
 						contenido.innerHTML = ajax.responseText;	
 					}
 				};
 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 				ajax.send("valor=agf&v1=" + v1 + "&v2=" + v2+ "&v3=" + v3); // Posting txtname to Servlet
	}
		
 	
}


function NotificarCargue(v1,v2,v3,v4) {
	//alert(v1+" - "+v2+" - "+v3+" - "+v4);
		ajax=getXMLObject();
 		var contenido=document.getElementById('Notifica');
 				ajax.open("POST","ControlRips",true); // getname will be
 																// the servlet name
 				ajax.onreadystatechange  = function(){
 					if (ajax.readyState == 4) {
 						document.getElementById("Notifica").style.display ='block';
 						contenido.innerHTML = ajax.responseText;	
 					}
 				};
 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 				ajax.send("valor=AplicarGlosa2&v1=" + v1 + "&v2=" + v2+ "&v3=" + v3+ "&v4=" + v4); // Posting txtname to Servlet
}


function RevisarNotificacion(v1,v2,v3,v4) {
	
	//alert("alert revision: "+v1+" - "+v2+" - "+v3+" - "+v4);
		
		ajax=getXMLObject();
 		var contenido=document.getElementById('Notifica');
 				ajax.open("POST","ControlRips",true); // getname will be
 																// the servlet name
 				ajax.onreadystatechange  = function(){
 					if (ajax.readyState == 4) {
 						document.getElementById("Notifica").style.display ='block';
 						contenido.innerHTML = ajax.responseText;	
 					}
 				};
 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 				ajax.send("valor=RevisarGlosa2&v1=" + v1 + "&v2=" + v2+ "&v3=" + v3+ "&v4=" + v4); // Posting txtname to Servlet
}



function EliminarNotificacion(v1,v2,v3,v4){
	//alert("eliminar cargue "+v4+" con codigos: "+v1+" - "+v2+" - "+v3);
	var usu = document.getElementById("txtCodusuario").value;
	
	if(confirm(" Desea Anular esta notificación?")){
			ajax=getXMLObject();
 		var contenido=document.getElementById('Opcion');
 				ajax.open("POST","ControlRips",true); // getname will be
 																// the servlet name
 				ajax.onreadystatechange  = function(){
 					if (ajax.readyState == 4) {
 						//contenido.innerHTML = ajax.responseText;	
 						alert("Notificacion Anulada exitosamente!!!");
 						Notificar(v1,v2,v3,v4);
 					}
 				};
 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 				ajax.send("valor=EliminarNotificacion&v1=" + v4 + "&usu=" + usu); // Posting txtname to Servlet
 		
 		
	}//fin glosa notificada
}



function comboge() {
	
	var v33 = document.getElementById("cgg").selectedIndex;
	var v1 = document.getElementById("cgg").options[v33].text;
	var v2 = document.getElementById("cgg").options[v33].value;
	
	//alert ("cge: "+v2)
	
		ajax=getXMLObject();
 		var contenido=document.getElementById('cmbcge');
 				ajax.open("POST","ControlRips",true); // getname will be
 																// the servlet name
 				ajax.onreadystatechange  = function(){
 					if (ajax.readyState == 4) {
 						contenido.innerHTML = ajax.responseText;	
 					}
 				};
 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 				ajax.send("valor=cge&v1=" + v1 + "&v2=" + v2); // Posting txtname to Servlet
}


var numeglo = /^[0-9]*$/;
function cvg(vt) {
		
	var v3 = document.getElementById("vaplica").value;
	var v2 = vt;
	
	var returnval = numeglo.test(v3)
	
	if (returnval == false) {
		alert("Formato incorrecto!!!");
		document.getElementById("vaplica").value=0;
		document.getElementById("vaplica").focus();
	}
	
	if(parseInt(v3)>parseInt(v2)){
		alert("El valor que intenta aplicar supera el valor del cargue!!");
		document.getElementById("vaplica").value=0;
	}
	
	var v1=parseInt(v2)-parseInt(v3);
	document.getElementById("vtota").value=v1;

	//return returnval
}







function AplicaGxC(v1,v2,v3,v4,v5) {
	
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	
	//alert ("Aplicando: "+v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5);
	
	var v6=document.getElementById("vaplica").value;
		
	var v77 = document.getElementById("cgg").selectedIndex;
	var v7 = document.getElementById("cgg").options[v77].value;
	var v8 = document.getElementById("cgg").options[v77].text;
	
	var v99 = document.getElementById("cge").selectedIndex;
	var v9 = document.getElementById("cge").options[v99].value;
	var v10 = document.getElementById("cge").options[v99].text;
	
	var v11=document.getElementById("txtDescripcion").value;
	
	if ((v7 == "Seleccione")||(v9 == "Seleccione")||(v6 == "0")||(v6 == "")) {
		alert("Debe Diligenciar todos los datos para aplicar la glosa!!!");
	} else {
	
		ajax=getXMLObject();
 		var contenido=document.getElementById('cmbcge');
 				ajax.open("POST","ControlRips",true); // getname will be
 																// the servlet name
 				ajax.onreadystatechange  = function(){
 					if (ajax.readyState == 4) {
 						//contenido.innerHTML = ajax.responseText;	
 						Notificar(v1,v2,v3,0);
 					}
 				};
 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 				ajax.send("valor=ig&v1="+v1+"&v2="+v2+"&v3="+v3+"&v4="+v4+"&v5="+v5+"&v6="+v6+"&v7="+v7+"&v8="+v8+"&v9="+v9+"&v10="+v10+"&v11="+v11+"&v12="+txtCodusuario); // Posting txtname to Servlet
 				
 }
}




function BuscarFactNotificadas() {
	//alert(ra);
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');

	//var v11 = document.getElementById("txtCdC").value;
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
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						document.getElementById("revisar").style.display ='block';
						// medicosPYP();
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosasNotificadas&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
}


function BuscarFactNotificadasr() {
	//alert("ra");
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');

	//var v11 = document.getElementById("txtCdC").value;
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
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						document.getElementById("revisar").style.display ='block';
						// medicosPYP();
						//cesarrrrrrr
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosasNotificadasr&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
}


function BuscarReportesc() {
	//alert("ra");
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');

	//var v11 = document.getElementById("txtCdC").value;
	var v1 = document.getElementById("txtFact").value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						document.getElementById("revisar").style.display ='block';
						// medicosPYP();
						//cesarrrrrrr
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosasReportesc&v1=" + v1
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
}



function BuscarFactNotificadasf() {
	//alert("ra");
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');

	//var v11 = document.getElementById("txtCdC").value;
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
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						document.getElementById("revisar").style.display ='block';
						// medicosPYP();
						//cesarrrrrrr
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosasNotificadasf&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
}


function BuscarReportes() {
	//alert("ra");
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');

	//var v11 = document.getElementById("txtCdC").value;
	var v1 = document.getElementById("txtFact").value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						document.getElementById("revisar").style.display ='block';
						// medicosPYP();
						//cesarrrrrrr
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosasReportes&v1=" + v1
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
}

function BuscarReportesn() {
	//alert("ra");
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');

	//var v11 = document.getElementById("txtCdC").value;
	var v1 = document.getElementById("txtFact").value;
	var v8 = document.getElementById("FI").value;
	var v9 = document.getElementById("FF").value;
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						document.getElementById("revisar").style.display ='block';
						// medicosPYP();
						//cesarrrrrrr
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosasReportesn&v1=" + v1
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
}

function BuscarFactNotificadasPC() {
	//alert("ra");
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');

	//var v11 = document.getElementById("txtCdC").value;
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
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						document.getElementById("revisar").style.display ='block';
						// medicosPYP();
						//cesarrrrrrr
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosasNotificadasPC&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
}


function BuscarFactNotificadasrn() {
	//alert("ra");
	//alert("BuscarCuentaDeCobro --- i Opcion: "+opcion);
	var contenido = document.getElementById('resultadosf');

	//var v11 = document.getElementById("txtCdC").value;
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
		
		if ((v8 != "") && (v9 == "")) {
			alert("Debe seleccionar el rango de fecha final!!!");
		} else {
			if ((v9 != "") && (v8 == "")) {
				alert("Debe seleccionar el rango de fecha inicial!!!");
			} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlRips", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText;
						document.getElementById("revisar").style.display ='block';
						// medicosPYP();
						//cesarrrrrrr
					}
				}
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=showGlosasNotificadasrn&v1=" + v1 + "&v2=" + v2 + "&v3=" + v3
						+ "&v4=" + v4 + "&v5=" + v5 + "&v6=" + v6 + "&v7=" + v7
						+ "&v8=" + v8 + "&v9=" + v9  ); // Posting
																							// txtname
																							// to
				// Servlet
			}
		}
}

function todos(){
	var principal = document.getElementsByName("all");
	var radioButtons = document.getElementsByName("checkbox");
	
	if (principal[0].checked) {
		for (var x = 0; x < radioButtons.length; x++) {
			radioButtons[x].checked=true;
		}
	}else{
		for (var x = 0; x < radioButtons.length; x++) {
			radioButtons[x].checked=false;
		}
	}

}


function todosr(){
	var principal = document.getElementsByName("all");
	var radioButtons = document.getElementsByName("checkg");
	
	if (principal[0].checked) {
		for (var x = 0; x < radioButtons.length; x++) {
			radioButtons[x].checked=true;
		}
	}else{
		for (var x = 0; x < radioButtons.length; x++) {
			radioButtons[x].checked=false;
		}
	}

}


function ResponderNotificacion() {
	 
	var radioButtons = document.getElementsByName("checkbox");
	var l=radioButtons.length;
	var ele="";
	for (var x = 0; x < l;  x++) {
		if(radioButtons[x].checked==true){
		 ele=ele+radioButtons[x].value+"|";
		}
	}
	
	var contenido=document.getElementById('Opciones');
	//alert("ele: "+ele);
		 ajax=getXMLObject();
		 ajax.open("POST","ControlRips",true); // getname will be
		 																// the servlet name
		 ajax.onreadystatechange  = function(){
			 if (ajax.readyState == 4) {
				 contenido.innerHTML = ajax.responseText;	
				
				 document.getElementById("Opcion").style.display = "none";
				//	document.getElementById("Notifica").style.display ='block';
					document.getElementById("Opciones").style.display ='block';
				 
			 }
		 };
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=cg2&v1=" + ele ); // Posting txtname to Servlet
	}



function RevisarNotificacion2(v1,v4) {
	
	//alert("alert revision: "+v1+" - "+v4);
		
		ajax=getXMLObject();
 		var contenido=document.getElementById('Notifica');
 				ajax.open("POST","ControlRips",true); // getname will be
 																// the servlet name
 				ajax.onreadystatechange  = function(){
 					if (ajax.readyState == 4) {
 						document.getElementById("Notifica").style.display ='block';
 						contenido.innerHTML = ajax.responseText;	
 						document.getElementById("vc").style.display ='none';
 						document.getElementById("vcn").style.display ='none';	
 					}
 				};
 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 				ajax.send("valor=RevisarGlosa3&v1=" + v1 + "&v4=" + v4); // Posting txtname to Servlet
}


function EliminarRespuesta(v4,v1){
	//alert("eliminar cargue "+v4+" con codigos: "+v1+" - "+v2+" - "+v3);
	var usu = document.getElementById("txtCodusuario").value;
	
	if(confirm(" Desea Anular esta respuesta?")){
			ajax=getXMLObject();
 		var contenido=document.getElementById('Opcion');
 				ajax.open("POST","ControlRips",true); // getname will be
 																// the servlet name
 				ajax.onreadystatechange  = function(){
 					if (ajax.readyState == 4) {
 						//contenido.innerHTML = ajax.responseText;	
 						alert("Respuesta Anulada exitosamente!!!");
 						//Notificar(v1,v2,v3,v4);
 						ResponderNotificaciondespuesdeEliminar(v1);
 					}
 				};
 				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 				ajax.send("valor=EliminarRespuesta&v1=" + v4 + "&usu=" + usu); // Posting txtname to Servlet
 		
 		
	}//fin glosa notificada
}


function ResponderNotificaciondespuesdeEliminar(ele) {
	 
	var contenido=document.getElementById('Opciones');
	//alert("ele: "+ele);
		 ajax=getXMLObject();
		 ajax.open("POST","ControlRips",true); // getname will be
		 																// the servlet name
		 ajax.onreadystatechange  = function(){
			 if (ajax.readyState == 4) {
				 contenido.innerHTML = ajax.responseText;	
				
				 document.getElementById("Opcion").style.display = "none";
					document.getElementById("Notifica").style.display ='none';
					document.getElementById("Opciones").style.display ='block';
				 
			 }
		 };
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=cg2&v1=" + ele ); // Posting txtname to Servlet
	}


function FProcesoG() {
	var usu = document.getElementById("txtCodusuario").value;
	var radioButtons = document.getElementsByName("checkbox");
	var l=radioButtons.length;
	var ele="";
	var val="";
	var q="";
	for (var x = 0; x < l;  x++) {
		if(radioButtons[x].checked==true){
		 ele=ele+radioButtons[x].value+"|";
		 q=radioButtons[x].id;
		 val=val+document.getElementById("vaplica"+q).value+"|";
		// 
		}
	}

	var contenido=document.getElementById('Opciones');
	//alert("ele: "+ele);
		 ajax=getXMLObject();
		 ajax.open("POST","ControlRips",true); // getname will be
		 																// the servlet name
		 ajax.onreadystatechange  = function(){
			 if (ajax.readyState == 4) {
				 contenido.innerHTML = ajax.responseText;	
				
				 document.getElementById("Opcion").style.display = "none";
				//	document.getElementById("Notifica").style.display ='block';
					document.getElementById("Opciones").style.display ='block';
				 
			 }
		 };
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=cgf2&v1=" + ele+ "&v2=" + usu+ "&v3=" + val ); // Posting txtname to Servlet
	}



function Revisatd(y,z) {
	var tdddd=document.getElementsByName('tdd');
	for(var x=0; x<tdddd.length; x++){
		tdddd[x].style.backgroundColor = "#fff";
	}
	y.style.backgroundColor = "#81BEF7";
}



function vconcilia(z) {
	//alert(z.value);
	if(z.value=="5"){
		document.getElementById("vc").style.display ='block';
		document.getElementById("vcn").style.display ='block';	
	}else{
		document.getElementById("vc").style.display ='none';
		document.getElementById("vcn").style.display ='none';	
	}
}


var numeglo = /^[0-9]*$/;
function verificaconci(vt) {
		
	var v3 = document.getElementById("vaplica").value;
	var v2 = vt.value;
	//alert(v2+" : "+v3);
	var returnval = numeglo.test(v2)
	
	if (returnval == false) {
		alert("Formato incorrecto!!!");
		document.getElementById("valorconcilia").value=0;
		document.getElementById("valorconcilia").focus();
	}
	
	if(parseInt(v2)>parseInt(v3)){
		alert("El valor que intenta conciliar supera el valor glosado!!");
		document.getElementById("valorconcilia").value=0;
	}
	
}


function ResponderGlosas(x,v1,v2,v7){
	
	//alert(x+" - "+v1+" - "+v2);
	var i = x.selectedIndex;
	var y = x.options[i].value;
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var res = document.getElementById('arearespon').value;
	var vcn = document.getElementById('valorconcilia').value;
	var v3 = document.getElementById("vaplica").value;
	
	var sw=0;
	if(confirm(" Desea "+x.options[i].text+" la notificacion seleccionada?")){
		if(x.options[i].value==5){
			if(vcn==0){
				alert("Debe digitar el valor conciliado");
				sw=1;
			}else{
				v3=vcn;
			}
		}
		if(sw==0){
			if(res==""){
			 alert("Debe digitar la respuesta de la opcion seleccionada");	
			 document.getElementById('arearespon').focus();
			}else{
				
				 ajax=getXMLObject();
				 var contenido=document.getElementById('resultadosf');
				 ajax.open("POST","ControlRips",true); // getname will be
				 																// the servlet name
				 ajax.onreadystatechange  = function(){
					 if (ajax.readyState == 4) {
						 //contenido.innerHTML = ajax.responseText;
						// ConsultaNotificacion(v1);
						 ResponderNotificacionNueva(v1)
					 }
				 };
				 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				 ajax.send("valor=Responder&v1="+v1+"&v2="+v2+"&v3="+v3+"&v4="+res+"&v5="+txtCodusuario+"&v6="+y+"&v7="+v7); // Posting txtname to Servlet
			}
		}
	}//else{x.options[0].selected = true}
}


function ResponderNotificacionNueva(ele) {

	var contenido=document.getElementById('Opciones');
	//alert("ele: "+ele);
		 ajax=getXMLObject();
		 ajax.open("POST","ControlRips",true); // getname will be
		 																// the servlet name
		 ajax.onreadystatechange  = function(){
			 if (ajax.readyState == 4) {
				 contenido.innerHTML = ajax.responseText;	
				
				 document.getElementById("Opcion").style.display = "none";
				 document.getElementById("Notifica").style.display ='none'
					document.getElementById("Opciones").style.display ='block';
				 
			 }
		 };
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=cg2&v1=" + ele ); // Posting txtname to Servlet
	}


function ReportedeGlosa(x){
	//alert("ReporteGlosa"+x);
	var radioButtons = document.getElementsByName("checkg");
	var l=radioButtons.length;
	var ele="";
	for (var x = 0; x < l;  x++) {
		if(radioButtons[x].checked==true){
		 ele=ele+radioButtons[x].value+"|";	
		}
	}
	//alert("l: "+l+" ele: "+ele);
	var contenido=document.getElementById('Opciones');
	var contenidos=document.getElementById('Notifica');
	//alert("ele: "+ele);
		 ajax=getXMLObject();
		 ajax.open("POST","ControlRips",true); // getname will be
		 																// the servlet name
		 ajax.onreadystatechange  = function(){
			 if (ajax.readyState == 4) {
				 contenido.innerHTML = ajax.responseText;	
				
				 document.getElementById("Opcion").style.display = "none";
				  document.getElementById("Opciones").style.display ='block';
				 document.getElementById("Notifica").style.display ='block'
				 contenidos.innerHTML = "<table width='100%' class='style6' ><tr><td><div align='center'><input type='button' id='btnPre' value='Generar Reporte' onClick='GenerarReporteGlosa(&quot;"+x+"&quot;,&quot;"+x+"&quot;,&quot;"+x+"&quot;)'></div></td></tr></table>";
					
			 }
		 };
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=GenerarReporteG&v1=" + ele ); // Posting txtname to Servlet
}


function GenerarReporteGlosa(x,y,z){
//	alert(x+" - "+y+" - "+z);
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var radioButtons = document.getElementsByName("checkgn");
	var l=radioButtons.length;
	var ele="";
	var eps1="";
	var cs=0;
	sw=0;
	for (var x = 0; x < l;  x++) {
		if(radioButtons[x].checked==true){
		 ele=ele+radioButtons[x].value+"|";	
		// alert("eps: "+radioButtons[x].id);
		 if(cs==0){
		 eps1=radioButtons[x].id;
		 }
		 
		 if(eps1!=radioButtons[x].id){
		  sw=1;
		 }
		 cs++;
		}
	}
	
	//verificar si son la misma entidad
	if(sw==1){
		alert("Las facturas deben pertenecer a la misma entidad");
	}else{
	 ajax=getXMLObject();
	 ajax.open("POST","ControlRips",true); // getname will be
	 									
	 ajax.onreadystatechange  = function(){
		 if (ajax.readyState == 4) {
			 var w = ajax.responseText;	
			 ImprimirGlosareport(w);
			 window.location.reload();
					
		 }
	 };
	 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 ajax.send("valor=ImprimirReporteG&v1=" + ele+"&v2="+txtCodusuario+"&v3="+eps1); // Posting txtname to Servlet
	}
}


function ReportedeNotificacion(){
	//alert("ReporteGlosa"+x);
	
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var radioButtons = document.getElementsByName("checkg");
	var l=radioButtons.length;
	var ele="";
	for (var x = 0; x < l;  x++) {
		if(radioButtons[x].checked==true){
		 ele=ele+radioButtons[x].value+"|";	
		}
	}
	//alert("l: "+l+" ele: "+ele);
	var contenido=document.getElementById('Opciones');
	var contenidos=document.getElementById('Notifica');
	//alert("ele: "+ele);
	if(ele==""){alert("Debe seleccionar al menos una factura");}else{
		 ajax=getXMLObject();
		 ajax.open("POST","ControlRips",true); // getname will be
		 																// the servlet name
		 ajax.onreadystatechange  = function(){
			 if (ajax.readyState == 4) {
				 var w = ajax.responseText;	
				 ImprimirGlosareportN(w);
				 window.location.reload();
			 }
		 };
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=GenerarReporteN&v1=" + ele+"&v2="+txtCodusuario ); // Posting txtname to Servlet
		}
	}


//ImprimirReportedeGlosa
function ImprimirGlosareport(adm){
	pagina = "fact_ReporteGlosas.jsp?venc=" + adm;
var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
opciones = opciones
	+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
window.open(pagina, "NUEVA", opciones);

}


function ImprimirGlosareportN(adm){
	pagina = "fact_ReporteGlosasN.jsp?venc=" + adm;
var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
opciones = opciones
	+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
window.open(pagina, "NUEVA", opciones);

}

function porcg(x,c){
	
	//x porcentaje  vaplicaporcentaje+c
	//c contador fila
	//vfact+c valor factura
	var y=document.getElementById('vfact'+c).value
	var z=x.value;
	var sw=0;
	var radioButtons = document.getElementsByName("checkbox");
	for (var w = 0; w < radioButtons.length; w++) {
			var p=w+1;
		if(radioButtons[w].checked==true){
			
			
			
		//	alert("ccccc"+qp);
			y=document.getElementById('vfact'+p).value
			var qp=(Math.floor(y*z/100));
			document.getElementById('vaplica'+p).value=qp;
			document.getElementById('vaplicaporcentaje'+p).value=z;	
			sw=1;
		}
	}
	
	if(sw==0){
		var qp=(Math.floor(y*z/100));
		document.getElementById('vaplica'+c).value=qp;
	}
	//alert("x: "+x.value+" y: "+y);		
}


function FProcesoCG(){
	//alert("FProcesoCG");
	
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var radioButtons = document.getElementsByName("checkbox");
	var l=radioButtons.length;
	var ele="";
	var val="";
	for (var x = 0; x < l;  x++) {
		var p=x+1;
		if(radioButtons[x].checked==true){
		 ele=ele+radioButtons[x].value+"|";	
		 val=val+document.getElementById('vaplica'+p).value+"|";
		}
	}
	//alert("l: "+l+" ele: "+ele+" val: "+val);
	
	
	if(ele==""){alert("Debe seleccionar al menos una factura");}else{
		 ajax=getXMLObject();
		 ajax.open("POST","ControlRips",true); // getname will be
		 																// the servlet name
		 ajax.onreadystatechange  = function(){
			 if (ajax.readyState == 4) {
				 var w = ajax.responseText;	
				 ImprimirGlosareportC(w);
				 //aqui debe generar un reporte de conciliacion 
				 window.location.reload();
			 }
		 };
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=GenerarConciliacion&v1=" + ele+"&v2="+txtCodusuario+"&v3="+val ); // Posting txtname to Servlet
		}

}



function ImprimirGlosareportC(adm){
	pagina = "fact_ReporteGlosasC.jsp?venc=" + adm;
var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
opciones = opciones
	+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
window.open(pagina, "NUEVA", opciones);

}






function repitefecha(){
	
	var zz=document.getElementById('fradt').value;
	var fechast=document.getElementsByName('frad');
	//alert("ceess"+fechast.length);
	for(var x=0; x<fechast.length; x++){
		//alert(x)
	fechast[x].value=zz;
	}
} 

function todoscheck(){
	//alert("");
	var principal = document.getElementsByName("radicatodo");
	var radioButtons = document.getElementsByName("radiorad");
	
	if (principal[0].checked) {
		for (var x = 0; x < radioButtons.length; x++) {
			radioButtons[x].checked=true;
		}
	}else{
		for (var x = 0; x < radioButtons.length; x++) {
			radioButtons[x].checked=false;
		}
	}

}



