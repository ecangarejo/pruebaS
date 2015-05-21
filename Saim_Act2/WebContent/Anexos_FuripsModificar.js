function getXMLObject(){ // XML OBJECT
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP"); // For Old Microsoft
		// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP"); // For Microsoft
			// IE 6.0+
		} catch (e2) {
			xmlHttp = false; // No Browser accepts the XMLHTTP Object then
			// false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}

var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object
/** *********************************************** */

// Guarda todos los datos de sitio del evento, accidente de transito, remision, transporte y amparo.
function ActualizarDatos() {
	var usuario = document.getElementById("txtUsuario").value;
	var Codigo = document.getElementById("Codigo").value;
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var condicion = document.getElementById("condicion").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;

	//datos accidente
	var aseguramiento = document.getElementById("aseguramiento").value;
	
	
	//datos conductor
	var NDconductor = document.getElementById("NDconductor").value;
	var PAConductor = document.getElementById("PAConductor").value;
	var SAConductor = document.getElementById("SAConductor").value;
	var PNConductor = document.getElementById("PNConductor").value;
	var SNConductor = document.getElementById("SNConductor").value;
	var DirConductor = document.getElementById("DirConductor").value;
	var TelConductor = document.getElementById("TelConductor").value;
	var dp = document.getElementById("dp").value;
	var mun = document.getElementById("mun").value;
	// Radio Autoridad, captura si hubo o no intervencion de la autoridad
	var Autoridad = "";
	var radioAutoridad = document.getElementsByName("Autoridad");
	for ( var xy = 0; xy < radioAutoridad.length; xy++) {
		if (radioAutoridad[xy].checked) {
			Autoridad = radioAutoridad[xy].id;
		}
	}
	// Radio Excedente, captura si hubo o no cobro de excedente de poliza
	var Excedente = "";
	var radioExcedente = document.getElementsByName("ExcPoliza");
	for ( var y = 0; y < radioExcedente.length; y++) {
		if (radioExcedente[y].checked) {
			Excedente = radioExcedente[y].id;
		}
	}
	//datos remision
	var Remision = document.getElementById("Remision").value;
	var fec_remision=document.getElementById("fec_remision").value;
	var hora_remision=document.getElementById("hora_remision").value;
	var PrestadorRemite=document.getElementById("PrestadorRemite").value;
	var CodInsRemite=document.getElementById("CodInsRemite").value;
	var ProfesionalRemite=document.getElementById("ProfesionalRemite").value;
	var CargoRemite=document.getElementById("CargoRemite").value;
	var fec_aceptacion=document.getElementById("fec_aceptacion").value;
	var hora_aceptacion=document.getElementById("hora_aceptacion").value;
	var PrestadorRecibe=document.getElementById("PrestadorRecibe").value;
	var CodInsRecibe=document.getElementById("CodInsRecibe").value;
	var ProfesionalRecibe=document.getElementById("ProfesionalRecibe").value;
	var CargoRecibe=document.getElementById("CargoRecibe").value;
	//datos amparo transporte
	var Transporte = document.getElementById("Transporte").value;
	var placatrans=document.getElementById("placatrans").value;
	var transdesde=document.getElementById("transdesde").value;
	var transhasta=document.getElementById("transhasta").value;
	var transporte=document.getElementById("transporte").value;	
	var lugarvictima=document.getElementById("lugarvictima").value;
	//datos certificacion medica
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
		
	// Radio referencia, captura si hubo o no 
	var Referencia = "";
	var radioReferencia = document.getElementsByName("referencia");
	for ( var t = 0; t< radioReferencia.length; t++) {
		if (radioReferencia[t].checked) {
			Referencia = radioReferencia[t].id;
		}
	}
	if((aseguramiento=="Vehiculo Fantasma")||(aseguramiento=="Vehiculo Fuga")){
		ajax = getXMLObject();
		ajax.open("POST", "ControlFurips", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Registro Actualizado Exitosamente.");
			
				window.close();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=20a&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
			       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
			       "&factura="+ factura + "&condicion=" + condicion +
			       "&dir_ocurrencia=" + dir_ocurrencia +
			       "&fec_evento=" + fec_evento + "&hora=" + hora +
			       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
			       "&zona=" + zona + "&descripcion=" + descripcion +
			       "&aseguramiento=" + aseguramiento + 
			       "&NDconductor=" + NDconductor + "&PAConductor=" + PAConductor +
			       "&SAConductor=" + SAConductor + "&PNConductor=" + PNConductor +
			       "&SNConductor=" + SNConductor + "&DirConductor=" + DirConductor +
			       "&TelConductor=" + TelConductor + "&dp=" + dp +
			       "&mun=" + mun + "&Autoridad=" + Autoridad +
			       "&Excedente=" + Excedente + 
			       "&Remision="+Remision+ "&fec_remision=" + fec_remision +
			       "&hora_remision=" + hora_remision + "&PrestadorRemite=" + PrestadorRemite +
			       "&CodInsRemite=" + CodInsRemite + "&ProfesionalRemite=" + ProfesionalRemite +
			       "&CargoRemite=" + CargoRemite + "&fec_aceptacion=" + fec_aceptacion +
			       "&hora_aceptacion=" + hora_aceptacion + "&PrestadorRecibe=" + PrestadorRecibe +
			       "&CodInsRecibe=" + CodInsRecibe + "&ProfesionalRecibe=" + ProfesionalRecibe +
			       "&CargoRecibe=" + CargoRecibe + 
			       "&Transporte"+Transporte+"&placatrans=" + placatrans+
			       "&transdesde=" + transdesde + "&transhasta=" + transhasta+
			       "&transporte=" + transporte + "&lugarvictima=" + lugarvictima+
			       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
			       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
			       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
			       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
			       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
			       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
			       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
			       "&NDMedico=" + NDMedico +
			       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
			       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
			       "&GastoTransFosyga=" + GastoTransFosyga + "Referencia=" + Referencia+ "&usuario=" + usuario );
	}else{
		var marca = document.getElementById("marca").value;
		var placa = document.getElementById("placa").value;
		var servicio = document.getElementById("servicio").value;
		var CodAseguradora = document.getElementById("CodAseguradora").value;
		var Numpoliza = document.getElementById("Numpoliza").value;
		var desde = document.getElementById("desde").value;
		var hasta = document.getElementById("hasta").value;
		
		//datos propietario
		var numpropietario = document.getElementById("NumDocumento").value;
		var PrimerApellido = document.getElementById("PrimerApellido").value;
		var SegundoApellido = document.getElementById("SegundoApellido").value;
		var PrimerNombre = document.getElementById("PrimerNombre").value;
		var SegundoNombre = document.getElementById("SegundoNombre").value;
		var DirPropietario = document.getElementById("DirPropietario").value;
		var DptoPropietario = document.getElementById("dpto").value;
		var MunPropietario = document.getElementById("municipio").value;
		var TelPropietario = document.getElementById("TelPropietario").value;
	if((mun_evento == "Seleccione" )||(MunPropietario == "Seleccione" )||(mun == "Seleccione")){
		alert("Seleccione un municipio");
	}else{
	ajax = getXMLObject();
	ajax.open("POST", "ControlFurips", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Registro Actualizado Exitosamente.");
			window.close();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=20&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
		       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
		       "&factura="+ factura + "&condicion=" + condicion +
		       "&dir_ocurrencia=" + dir_ocurrencia +
		       "&fec_evento=" + fec_evento + "&hora=" + hora +
		       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
		       "&zona=" + zona + "&descripcion=" + descripcion +
		       "&aseguramiento=" + aseguramiento +
		       "&marca=" + marca + "&placa=" + placa +
		       "&servicio=" + servicio + "&CodAseguradora=" + CodAseguradora +
		       "&NumPoliza=" + Numpoliza + "&desde=" + desde +
		       "&hasta=" + hasta + 
		       "&numpropietario=" + numpropietario + "&PrimerApellido=" + PrimerApellido +
		       "&SegundoApellido=" + SegundoApellido + "&PrimerNombre=" + PrimerNombre +
		       "&SegundoNombre=" + SegundoNombre + "&DirPropietario=" + DirPropietario +
		       "&DptoPropietario=" + DptoPropietario + "&MunPropietario=" + MunPropietario +
		       "&TelPropietario=" + TelPropietario + 
		       "&NDconductor=" + NDconductor + "&PAConductor=" + PAConductor +
		       "&SAConductor=" + SAConductor + "&PNConductor=" + PNConductor +
		       "&SNConductor=" + SNConductor + "&DirConductor=" + DirConductor +
		       "&TelConductor=" + TelConductor + "&dp=" + dp +
		       "&mun=" + mun + "&Autoridad=" + Autoridad +
		       "&Excedente=" + Excedente + 
		       "&Remision="+Remision+ "&fec_remision=" + fec_remision +
		       "&hora_remision=" + hora_remision + "&PrestadorRemite=" + PrestadorRemite +
		       "&CodInsRemite=" + CodInsRemite + "&ProfesionalRemite=" + ProfesionalRemite +
		       "&CargoRemite=" + CargoRemite + "&fec_aceptacion=" + fec_aceptacion +
		       "&hora_aceptacion=" + hora_aceptacion + "&PrestadorRecibe=" + PrestadorRecibe +
		       "&CodInsRecibe=" + CodInsRecibe + "&ProfesionalRecibe=" + ProfesionalRecibe +
		       "&CargoRecibe=" + CargoRecibe + 
		       "&Transporte"+Transporte+"&placatrans=" + placatrans+
		       "&transdesde=" + transdesde + "&transhasta=" + transhasta+
		       "&transporte=" + transporte + "&lugarvictima=" + lugarvictima+
		       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
		       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
		       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
		       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
		       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
		       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
		       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
		       "&NDMedico=" + NDMedico +
		       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
		       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
		       "&GastoTransFosyga=" + GastoTransFosyga + "Referencia=" + Referencia+ "&usuario=" + usuario );
	}
	}
}// function ActualizarDatos

//Guarda todos los datos de sitio del evento, accidente de transito, remision y amparo.
function ActualizarDatos2() {
	var usuario = document.getElementById("txtUsuario").value;
	var Codigo = document.getElementById("Codigo").value;
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var condicion = document.getElementById("condicion").value;
	var evento = document.getElementById("eventonaturaleza").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;
	
	//datos accidente
	var aseguramiento = document.getElementById("aseguramiento").value;
	
	
	//datos conductor
	var NDconductor = document.getElementById("NDconductor").value;
	var PAConductor = document.getElementById("PAConductor").value;
	var SAConductor = document.getElementById("SAConductor").value;
	var PNConductor = document.getElementById("PNConductor").value;
	var SNConductor = document.getElementById("SNConductor").value;
	var DirConductor = document.getElementById("DirConductor").value;
	var TelConductor = document.getElementById("TelConductor").value;
	var dp = document.getElementById("dp").value;
	var mun = document.getElementById("mun").value;
	// Radio Autoridad, captura si hubo o no intervencion de la autoridad
	var Autoridad = "";
	var radioAutoridad = document.getElementsByName("Autoridad");
	for ( var xy = 0; xy < radioAutoridad.length; xy++) {
		if (radioAutoridad[xy].checked) {
			Autoridad = radioAutoridad[xy].id;
		}
	}
	// Radio Excedente, captura si hubo o no cobro de excedente de poliza
	var Excedente = "";
	var radioExcedente = document.getElementsByName("ExcPoliza");
	for ( var y = 0; y < radioExcedente.length; y++) {
		if (radioExcedente[y].checked) {
			Excedente = radioExcedente[y].id;
		}
	}
	//datos remision
	var Remision = document.getElementById("Remision").value;
	var fec_remision=document.getElementById("fec_remision").value;
	var hora_remision=document.getElementById("hora_remision").value;
	var PrestadorRemite=document.getElementById("PrestadorRemite").value;
	var CodInsRemite=document.getElementById("CodInsRemite").value;
	var ProfesionalRemite=document.getElementById("ProfesionalRemite").value;
	var CargoRemite=document.getElementById("CargoRemite").value;
	var fec_aceptacion=document.getElementById("fec_aceptacion").value;
	var hora_aceptacion=document.getElementById("hora_aceptacion").value;
	var PrestadorRecibe=document.getElementById("PrestadorRecibe").value;
	var CodInsRecibe=document.getElementById("CodInsRecibe").value;
	var ProfesionalRecibe=document.getElementById("ProfesionalRecibe").value;
	var CargoRecibe=document.getElementById("CargoRecibe").value;
	
	var Transporte = document.getElementById("Transporte").value;
	//datos certificacion medica
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
		
	// Radio referencia, captura si hubo o no 
	var Referencia = "";
	var radioReferencia = document.getElementsByName("referencia");
	for ( var t = 0; t< radioReferencia.length; t++) {
		if (radioReferencia[t].checked) {
			Referencia = radioReferencia[t].id;
		}
	}
	if((aseguramiento=="Vehiculo Fantasma")||(aseguramiento=="Vehiculo Fuga")){
		ajax = getXMLObject();
		ajax.open("POST", "ControlFurips", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Registro Actualizado Exitosamente.");
				window.close();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=21a&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
			       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
			       "&factura="+ factura + "&condicion=" + condicion +
			       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
			       "&fec_evento=" + fec_evento + "&hora=" + hora +
			       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
			       "&zona=" + zona + "&descripcion=" + descripcion +
			       "&condicion=" + condicion + "&aseguramiento=" + aseguramiento +
			       "&NDconductor=" + NDconductor + "&PAConductor=" + PAConductor +
			       "&SAConductor=" + SAConductor + "&PNConductor=" + PNConductor +
			       "&SNConductor=" + SNConductor + "&DirConductor=" + DirConductor +
			       "&TelConductor=" + TelConductor + "&dp=" + dp +
			       "&mun=" + mun + "&Autoridad=" + Autoridad +
			       "&Excedente=" + Excedente + 
			       "&Remision="+Remision+"&fec_remision=" + fec_remision +
			       "&hora_remision=" + hora_remision + "&PrestadorRemite=" + PrestadorRemite +
			       "&CodInsRemite=" + CodInsRemite + "&ProfesionalRemite=" + ProfesionalRemite +
			       "&CargoRemite=" + CargoRemite + "&fec_aceptacion=" + fec_aceptacion +
			       "&hora_aceptacion=" + hora_aceptacion + "&PrestadorRecibe=" + PrestadorRecibe +
			       "&CodInsRecibe=" + CodInsRecibe + "&ProfesionalRecibe=" + ProfesionalRecibe +
			       "&CargoRecibe=" + CargoRecibe + "&Transporte="+Transporte+
			       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
			       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
			       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
			       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
			       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
			       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
			       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
			       "&NDMedico=" + NDMedico +
			       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
			       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
			       "&GastoTransFosyga=" + GastoTransFosyga + "&Referencia=" + Referencia + "&usuario=" + usuario);
	}else{
		var marca = document.getElementById("marca").value;
		var placa = document.getElementById("placa").value;
		var servicio = document.getElementById("servicio").value;
		var CodAseguradora = document.getElementById("CodAseguradora").value;
		var Numpoliza = document.getElementById("Numpoliza").value;
		var desde = document.getElementById("desde").value;
		var hasta = document.getElementById("hasta").value;
		
		//datos propietario
		var numpropietario = document.getElementById("NumDocumento").value;
		var PrimerApellido = document.getElementById("PrimerApellido").value;
		var SegundoApellido = document.getElementById("SegundoApellido").value;
		var PrimerNombre = document.getElementById("PrimerNombre").value;
		var SegundoNombre = document.getElementById("SegundoNombre").value;
		var DirPropietario = document.getElementById("DirPropietario").value;
		var DptoPropietario = document.getElementById("dpto").value;
		var MunPropietario = document.getElementById("municipio").value;
		var TelPropietario = document.getElementById("TelPropietario").value;
		
	if((mun_evento == "Seleccione" )||(MunPropietario == "Seleccione" )||(mun == "Seleccione")){
		alert("Seleccione un municipio");
	}else{
	ajax = getXMLObject();
	ajax.open("POST", "ControlFurips", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Registro Actualizado Exitosamente.");
			window.close();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=21&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
		       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
		       "&factura="+ factura + "&condicion=" + condicion +
		       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
		       "&fec_evento=" + fec_evento + "&hora=" + hora +
		       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
		       "&zona=" + zona + "&descripcion=" + descripcion +
		       "&condicion=" + condicion + "&aseguramiento=" + aseguramiento +
		       "&marca=" + marca + "&placa=" + placa +
		       "&servicio=" + servicio + "&CodAseguradora=" + CodAseguradora +
		       "&NumPoliza=" + Numpoliza + "&desde=" + desde +
		       "&hasta=" + hasta + 
		       "&numpropietario=" + numpropietario + "&PrimerApellido=" + PrimerApellido +
		       "&SegundoApellido=" + SegundoApellido + "&PrimerNombre=" + PrimerNombre +
		       "&SegundoNombre=" + SegundoNombre + "&DirPropietario=" + DirPropietario +
		       "&DptoPropietario=" + DptoPropietario + "&MunPropietario=" + MunPropietario +
		       "&TelPropietario=" + TelPropietario + 
		       "&NDconductor=" + NDconductor + "&PAConductor=" + PAConductor +
		       "&SAConductor=" + SAConductor + "&PNConductor=" + PNConductor +
		       "&SNConductor=" + SNConductor + "&DirConductor=" + DirConductor +
		       "&TelConductor=" + TelConductor + "&dp=" + dp +
		       "&mun=" + mun + "&Autoridad=" + Autoridad +
		       "&Excedente=" + Excedente + 
		       "&Remision="+Remision+"&fec_remision=" + fec_remision +
		       "&hora_remision=" + hora_remision + "&PrestadorRemite=" + PrestadorRemite +
		       "&CodInsRemite=" + CodInsRemite + "&ProfesionalRemite=" + ProfesionalRemite +
		       "&CargoRemite=" + CargoRemite + "&fec_aceptacion=" + fec_aceptacion +
		       "&hora_aceptacion=" + hora_aceptacion + "&PrestadorRecibe=" + PrestadorRecibe +
		       "&CodInsRecibe=" + CodInsRecibe + "&ProfesionalRecibe=" + ProfesionalRecibe +
		       "&CargoRecibe=" + CargoRecibe + "&Transporte="+Transporte+
		       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
		       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
		       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
		       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
		       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
		       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
		       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
		       "&NDMedico=" + NDMedico +
		       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
		       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
		       "&GastoTransFosyga=" + GastoTransFosyga + "&Referencia=" + Referencia + "&usuario=" + usuario);
	}
	}
}

//Guarda todos los datos de sitio del evento, accidente de transito,transporte y amparo.
function ActualizarDatos3() {
	var usuario = document.getElementById("txtUsuario").value;
	var Codigo = document.getElementById("Codigo").value;
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var condicion = document.getElementById("condicion").value;
	var evento = document.getElementById("eventonaturaleza").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;
	
	//datos accidente
	var aseguramiento = document.getElementById("aseguramiento").value;
	
	//datos conductor
	var NDconductor = document.getElementById("NDconductor").value;
	var PAConductor = document.getElementById("PAConductor").value;
	var SAConductor = document.getElementById("SAConductor").value;
	var PNConductor = document.getElementById("PNConductor").value;
	var SNConductor = document.getElementById("SNConductor").value;
	var DirConductor = document.getElementById("DirConductor").value;
	var TelConductor = document.getElementById("TelConductor").value;
	var dp = document.getElementById("dp").value;
	var mun = document.getElementById("mun").value;
	// Radio Autoridad, captura si hubo o no intervencion de la autoridad
	var Autoridad = "";
	var radioAutoridad = document.getElementsByName("Autoridad");
	for ( var xy = 0; xy < radioAutoridad.length; xy++) {
		if (radioAutoridad[xy].checked) {
			Autoridad = radioAutoridad[xy].id;
		}
	}
	// Radio Excedente, captura si hubo o no cobro de excedente de poliza
	var Excedente = "";
	var radioExcedente = document.getElementsByName("ExcPoliza");
	for ( var y = 0; y < radioExcedente.length; y++) {
		if (radioExcedente[y].checked) {
			Excedente = radioExcedente[y].id;
		}
	}
	
	var Remision = document.getElementById("Remision").value;
	//datos amparo transporte
	var Transporte = document.getElementById("Transporte").value;
	var placatrans=document.getElementById("placatrans").value;
	var transdesde=document.getElementById("transdesde").value;
	var transhasta=document.getElementById("transhasta").value;
	var transporte=document.getElementById("transporte").value;	
	var lugarvictima=document.getElementById("lugarvictima").value;
	//datos certificacion medica
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
	
	if((aseguramiento=="Vehiculo Fantasma")||(aseguramiento=="Vehiculo Fuga")){
		ajax = getXMLObject();
		ajax.open("POST", "ControlFurips", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Registro Actualizado Exitosamente.");
				window.close();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=22a&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
			       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
			       "&factura="+ factura + "&condicion=" + condicion +
			       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
			       "&fec_evento=" + fec_evento + "&hora=" + hora +
			       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
			       "&zona=" + zona + "&descripcion=" + descripcion +
			       "&condicion=" + condicion + "&aseguramiento=" + aseguramiento +
			       "&NDconductor=" + NDconductor + "&PAConductor=" + PAConductor +
			       "&SAConductor=" + SAConductor + "&PNConductor=" + PNConductor +
			       "&SNConductor=" + SNConductor + "&DirConductor=" + DirConductor +
			       "&TelConductor=" + TelConductor + "&dp=" + dp +
			       "&mun=" + mun + "&Autoridad=" + Autoridad +
			       "&Excedente=" + Excedente + "&Remision="+Remision+
			       "&Transporte="+Transporte+"&placatrans=" + placatrans+
			       "&transdesde=" + transdesde + "&transhasta=" + transhasta+
			       "&transporte=" + transporte + "&lugarvictima=" + lugarvictima+
			       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
			       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
			       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
			       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
			       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
			       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
			       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
			       "&NDMedico=" + NDMedico +
			       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
			       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
			       "&GastoTransFosyga=" + GastoTransFosyga + "&usuario=" + usuario );
	}else{
		var marca = document.getElementById("marca").value;
		var placa = document.getElementById("placa").value;
		var servicio = document.getElementById("servicio").value;
		var CodAseguradora = document.getElementById("CodAseguradora").value;
		var Numpoliza = document.getElementById("Numpoliza").value;
		var desde = document.getElementById("desde").value;
		var hasta = document.getElementById("hasta").value;
		
		//datos propietario
		var numpropietario = document.getElementById("NumDocumento").value;
		var PrimerApellido = document.getElementById("PrimerApellido").value;
		var SegundoApellido = document.getElementById("SegundoApellido").value;
		var PrimerNombre = document.getElementById("PrimerNombre").value;
		var SegundoNombre = document.getElementById("SegundoNombre").value;
		var DirPropietario = document.getElementById("DirPropietario").value;
		var DptoPropietario = document.getElementById("dpto").value;
		var MunPropietario = document.getElementById("municipio").value;
		var TelPropietario = document.getElementById("TelPropietario").value;
		
	
	
	if((mun_evento == "Seleccione" )||(MunPropietario == "Seleccione" )||(mun == "Seleccione")){
		alert("Seleccione un municipio");
	}else{
	ajax = getXMLObject();
	ajax.open("POST", "ControlFurips", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Registro Actualizado Exitosamente.");
			window.close();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=22&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
		       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
		       "&factura="+ factura + "&condicion=" + condicion +
		       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
		       "&fec_evento=" + fec_evento + "&hora=" + hora +
		       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
		       "&zona=" + zona + "&descripcion=" + descripcion +
		       "&condicion=" + condicion + "&aseguramiento=" + aseguramiento +
		       "&marca=" + marca + "&placa=" + placa +
		       "&servicio=" + servicio + "&CodAseguradora=" + CodAseguradora +
		       "&NumPoliza=" + Numpoliza + "&desde=" + desde +
		       "&hasta=" + hasta + 
		       "&numpropietario=" + numpropietario + "&PrimerApellido=" + PrimerApellido +
		       "&SegundoApellido=" + SegundoApellido + "&PrimerNombre=" + PrimerNombre +
		       "&SegundoNombre=" + SegundoNombre + "&DirPropietario=" + DirPropietario +
		       "&DptoPropietario=" + DptoPropietario + "&MunPropietario=" + MunPropietario +
		       "&TelPropietario=" + TelPropietario + 
		       "&NDconductor=" + NDconductor + "&PAConductor=" + PAConductor +
		       "&SAConductor=" + SAConductor + "&PNConductor=" + PNConductor +
		       "&SNConductor=" + SNConductor + "&DirConductor=" + DirConductor +
		       "&TelConductor=" + TelConductor + "&dp=" + dp +
		       "&mun=" + mun + "&Autoridad=" + Autoridad +
		       "&Excedente=" + Excedente + "&Remision="+Remision+
		       "&Transporte="+Transporte+"&placatrans=" + placatrans+
		       "&transdesde=" + transdesde + "&transhasta=" + transhasta+
		       "&transporte=" + transporte + "&lugarvictima=" + lugarvictima+
		       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
		       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
		       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
		       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
		       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
		       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
		       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
		       "&NDMedico=" + NDMedico +
		       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
		       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
		       "&GastoTransFosyga=" + GastoTransFosyga + "&usuario=" + usuario );
	}
	}
}

//Guarda todos los datos de sitio del evento, accidente de transito y amparo.
function ActualizarDatos4() {
	var usuario = document.getElementById("txtUsuario").value;
	var Codigo = document.getElementById("Codigo").value;
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var condicion = document.getElementById("condicion").value;
	var evento = document.getElementById("eventonaturaleza").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;
	
	//datos accidente
	var aseguramiento = document.getElementById("aseguramiento").value;
	
	// Radio Autoridad, captura si hubo o no intervencion de la autoridad
	var Autoridad = "";
	var radioAutoridad = document.getElementsByName("Autoridad");
	for ( var xy = 0; xy < radioAutoridad.length; xy++) {
		if (radioAutoridad[xy].checked) {
			Autoridad = radioAutoridad[xy].id;
		}
	}
	// Radio Excedente, captura si hubo o no cobro de excedente de poliza
	var Excedente = "";
	var radioExcedente = document.getElementsByName("ExcPoliza");
	for ( var y = 0; y < radioExcedente.length; y++) {
		if (radioExcedente[y].checked) {
			Excedente = radioExcedente[y].id;
		}
	}
	
	//datos conductor
	var NDconductor = document.getElementById("NDconductor").value;
	var PAConductor = document.getElementById("PAConductor").value;
	var SAConductor = document.getElementById("SAConductor").value;
	var PNConductor = document.getElementById("PNConductor").value;
	var SNConductor = document.getElementById("SNConductor").value;
	var DirConductor = document.getElementById("DirConductor").value;
	var TelConductor = document.getElementById("TelConductor").value;
	var dp = document.getElementById("dp").value;
	var mun = document.getElementById("mun").value;
	
	
	
	var Remision = document.getElementById("Remision").value;
	var Transporte = document.getElementById("Transporte").value;
	//datos certificacion medica
	
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
	
	
	if((aseguramiento=="Vehiculo Fantasma")||(aseguramiento=="Vehiculo Fuga")){
		ajax = getXMLObject();
		ajax.open("POST", "ControlFurips", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert("Registro Actualizado Exitosamente.");
				window.close();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=23a&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
			       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
			       "&factura="+ factura + "&condicion=" + condicion +
			       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
			       "&fec_evento=" + fec_evento + "&hora=" + hora +
			       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
			       "&zona=" + zona + "&descripcion=" + descripcion +
			       "&condicion=" + condicion + "&aseguramiento=" + aseguramiento +
			       "&NDconductor=" + NDconductor + "&PAConductor=" + PAConductor +
			       "&SAConductor=" + SAConductor + "&PNConductor=" + PNConductor +
			       "&SNConductor=" + SNConductor + "&DirConductor=" + DirConductor +
			       "&TelConductor=" + TelConductor + "&dp=" + dp +
			       "&mun=" + mun + "&Autoridad=" + Autoridad +
			       "&Excedente=" + Excedente + 
			       "&Remision="+Remision +"&Transporte="+Transporte+
			       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
			       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
			       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
			       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
			       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
			       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
			       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
			       "&NDMedico=" + NDMedico +
			       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
			       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
			       "&GastoTransFosyga=" + GastoTransFosyga + "&usuario=" + usuario);
	}else {
			
		
		var marca = document.getElementById("marca").value;
		var placa = document.getElementById("placa").value;
		var servicio = document.getElementById("servicio").value;
		var CodAseguradora = document.getElementById("CodAseguradora").value;
		var Numpoliza = document.getElementById("Numpoliza").value;
		var desde = document.getElementById("desde").value;
		var hasta = document.getElementById("hasta").value;
		
		//datos propietario
		var numpropietario = document.getElementById("NumDocumento").value;
		var PrimerApellido = document.getElementById("PrimerApellido").value;
		var SegundoApellido = document.getElementById("SegundoApellido").value;
		var PrimerNombre = document.getElementById("PrimerNombre").value;
		var SegundoNombre = document.getElementById("SegundoNombre").value;
		var DirPropietario = document.getElementById("DirPropietario").value;
		var DptoPropietario = document.getElementById("dpto").value;
		var MunPropietario = document.getElementById("municipio").value;
		var TelPropietario = document.getElementById("TelPropietario").value;
		
	
		
	ajax = getXMLObject();
	ajax.open("POST", "ControlFurips", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Registro Actualizado Exitosamente.");
			window.close();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=23&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
		       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
		       "&factura="+ factura + "&condicion=" + condicion +
		       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
		       "&fec_evento=" + fec_evento + "&hora=" + hora +
		       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
		       "&zona=" + zona + "&descripcion=" + descripcion +
		       "&condicion=" + condicion + "&aseguramiento=" + aseguramiento +
		       "&marca=" + marca + "&placa=" + placa +
		       "&servicio=" + servicio + "&CodAseguradora=" + CodAseguradora +
		       "&NumPoliza=" + Numpoliza + "&desde=" + desde +
		       "&hasta=" + hasta + 
		       "&numpropietario=" + numpropietario + "&PrimerApellido=" + PrimerApellido +
		       "&SegundoApellido=" + SegundoApellido + "&PrimerNombre=" + PrimerNombre +
		       "&SegundoNombre=" + SegundoNombre + "&DirPropietario=" + DirPropietario +
		       "&DptoPropietario=" + DptoPropietario + "&MunPropietario=" + MunPropietario +
		       "&TelPropietario=" + TelPropietario + 
		       "&NDconductor=" + NDconductor + "&PAConductor=" + PAConductor +
		       "&SAConductor=" + SAConductor + "&PNConductor=" + PNConductor +
		       "&SNConductor=" + SNConductor + "&DirConductor=" + DirConductor +
		       "&TelConductor=" + TelConductor + "&dp=" + dp +
		       "&mun=" + mun + "&Autoridad=" + Autoridad +
		       "&Excedente=" + Excedente + 
		       "&Remision="+Remision +"&Transporte="+Transporte+
		       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
		       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
		       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
		       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
		       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
		       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
		       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
		       "&NDMedico=" + NDMedico +
		       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
		       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
		       "&GastoTransFosyga=" + GastoTransFosyga + "&usuario=" + usuario);
	}
}



//Guarda todos los datos de sitio del evento, remision, transporte y amparo.
function ActualizarDatos5() {
	var usuario = document.getElementById("txtUsuario").value;
	var Codigo = document.getElementById("Codigo").value;
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var condicion = document.getElementById("condicion").value;
	var evento = document.getElementById("eventonaturaleza").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;
	
	//datos remision
	var Remision = document.getElementById("Remision").value;
	var fec_remision=document.getElementById("fec_remision").value;
	var hora_remision=document.getElementById("hora_remision").value;
	var PrestadorRemite=document.getElementById("PrestadorRemite").value;
	var CodInsRemite=document.getElementById("CodInsRemite").value;
	var ProfesionalRemite=document.getElementById("ProfesionalRemite").value;
	var CargoRemite=document.getElementById("CargoRemite").value;
	var fec_aceptacion=document.getElementById("fec_aceptacion").value;
	var hora_aceptacion=document.getElementById("hora_aceptacion").value;
	var PrestadorRecibe=document.getElementById("PrestadorRecibe").value;
	var CodInsRecibe=document.getElementById("CodInsRecibe").value;
	var ProfesionalRecibe=document.getElementById("ProfesionalRecibe").value;
	var CargoRecibe=document.getElementById("CargoRecibe").value;
	//datos amparo transporte
	var Transporte = document.getElementById("Transporte").value;
	var placatrans=document.getElementById("placatrans").value;
	var transdesde=document.getElementById("transdesde").value;
	var transhasta=document.getElementById("transhasta").value;
	var transporte=document.getElementById("transporte").value;	
	var lugarvictima=document.getElementById("lugarvictima").value;
	//datos certificacion medica
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
		
	// Radio referencia, captura si hubo o no 
	var Referencia = "";
	var radioReferencia = document.getElementsByName("referencia");
	for ( var t = 0; t< radioReferencia.length; t++) {
		if (radioReferencia[t].checked) {
			Referencia = radioReferencia[t].id;
		}
	}
	
	
	ajax = getXMLObject();
	ajax.open("POST", "ControlFurips", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Registro Actualizado Exitosamente.");
			window.close();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=24&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
		       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
		       "&factura="+ factura + "&condicion=" + condicion +
		       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
		       "&fec_evento=" + fec_evento + "&hora=" + hora +
		       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
		       "&zona=" + zona + "&descripcion=" + descripcion +
		       "&condicion=" + condicion +  
		       "&Remision="+Remision+"&fec_remision=" + fec_remision +
		       "&hora_remision=" + hora_remision + "&PrestadorRemite=" + PrestadorRemite +
		       "&CodInsRemite=" + CodInsRemite + "&ProfesionalRemite=" + ProfesionalRemite +
		       "&CargoRemite=" + CargoRemite + "&fec_aceptacion=" + fec_aceptacion +
		       "&hora_aceptacion=" + hora_aceptacion + "&PrestadorRecibe=" + PrestadorRecibe +
		       "&CodInsRecibe=" + CodInsRecibe + "&ProfesionalRecibe=" + ProfesionalRecibe +
		       "&CargoRecibe=" + CargoRecibe + 
		       "&Transporte="+Transporte+"&placatrans=" + placatrans+
		       "&transdesde=" + transdesde + "&transhasta=" + transhasta+
		       "&transporte=" + transporte + "&lugarvictima=" + lugarvictima+
		       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
		       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
		       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
		       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
		       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
		       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
		       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
		       "&NDMedico=" + NDMedico +
		       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
		       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
		       "&GastoTransFosyga=" + GastoTransFosyga + "&Referencia=" + Referencia+ "&usuario=" + usuario );
}

//Guarda todos los datos de sitio del evento, remision y amparo.
function ActualizarDatos6() {
	var usuario = document.getElementById("txtUsuario").value;
	var Codigo = document.getElementById("Codigo").value;
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var condicion = document.getElementById("condicion").value;
	var evento = document.getElementById("eventonaturaleza").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;
	
	//datos remision
	var Remision = document.getElementById("Remision").value;
	var fec_remision=document.getElementById("fec_remision").value;
	var hora_remision=document.getElementById("hora_remision").value;
	var PrestadorRemite=document.getElementById("PrestadorRemite").value;
	var CodInsRemite=document.getElementById("CodInsRemite").value;
	var ProfesionalRemite=document.getElementById("ProfesionalRemite").value;
	var CargoRemite=document.getElementById("CargoRemite").value;
	var fec_aceptacion=document.getElementById("fec_aceptacion").value;
	var hora_aceptacion=document.getElementById("hora_aceptacion").value;
	var PrestadorRecibe=document.getElementById("PrestadorRecibe").value;
	var CodInsRecibe=document.getElementById("CodInsRecibe").value;
	var ProfesionalRecibe=document.getElementById("ProfesionalRecibe").value;
	var CargoRecibe=document.getElementById("CargoRecibe").value;
	var Transporte = document.getElementById("Transporte").value;
	//datos certificacion medica
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
		
	// Radio referencia, captura si hubo o no 
	var Referencia = "";
	var radioReferencia = document.getElementsByName("referencia");
	for ( var t = 0; t< radioReferencia.length; t++) {
		if (radioReferencia[t].checked) {
			Referencia = radioReferencia[t].id;
		}
	}
	
	
	ajax = getXMLObject();
	ajax.open("POST", "ControlFurips", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Registro Actualizado Exitosamente.");
			window.close();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=25&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
		       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
		       "&factura="+ factura + "&condicion=" + condicion +
		       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
		       "&fec_evento=" + fec_evento + "&hora=" + hora +
		       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
		       "&zona=" + zona + "&descripcion=" + descripcion +
		       "&condicion=" + condicion +  
		       "&Remision="+Remision+"&fec_remision=" + fec_remision +
		       "&hora_remision=" + hora_remision + "&PrestadorRemite=" + PrestadorRemite +
		       "&CodInsRemite=" + CodInsRemite + "&ProfesionalRemite=" + ProfesionalRemite +
		       "&CargoRemite=" + CargoRemite + "&fec_aceptacion=" + fec_aceptacion +
		       "&hora_aceptacion=" + hora_aceptacion + "&PrestadorRecibe=" + PrestadorRecibe +
		       "&CodInsRecibe=" + CodInsRecibe + "&ProfesionalRecibe=" + ProfesionalRecibe +
		       "&CargoRecibe=" + CargoRecibe + "&Transporte="+Transporte+
		       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
		       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
		       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
		       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
		       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
		       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
		       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
		       "&NDMedico=" + NDMedico +
		       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
		       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
		       "&GastoTransFosyga=" + GastoTransFosyga + "&Referencia=" + Referencia+ "&usuario=" + usuario );
}

//Guarda todos los datos de sitio del evento, transporte y amparo.
function ActualizarDatos7() {
	var usuario = document.getElementById("txtUsuario").value;
	var Codigo = document.getElementById("Codigo").value;
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var condicion = document.getElementById("condicion").value;
	var evento = document.getElementById("eventonaturaleza").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;
	var Remision = document.getElementById("Remision").value;
	//datos amparo transporte
	var Transporte = document.getElementById("Transporte").value;
	var placatrans=document.getElementById("placatrans").value;
	var transdesde=document.getElementById("transdesde").value;
	var transhasta=document.getElementById("transhasta").value;
	var transporte=document.getElementById("transporte").value;	
	var lugarvictima=document.getElementById("lugarvictima").value;
	//datos certificacion medica
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
		
	ajax = getXMLObject();
	ajax.open("POST", "ControlFurips", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Registro Actualizado Exitosamente.");
			window.close();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=26&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
		       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
		       "&factura="+ factura + "&condicion=" + condicion +
		       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
		       "&fec_evento=" + fec_evento + "&hora=" + hora +
		       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
		       "&zona=" + zona + "&descripcion=" + descripcion +
		       "&condicion=" + condicion + "&Remision="+Remision+  
		       "&Transporte="+Transporte+"&placatrans=" + placatrans+
		       "&transdesde=" + transdesde + "&transhasta=" + transhasta+
		       "&transporte=" + transporte + "&lugarvictima=" + lugarvictima+
		       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
		       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
		       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
		       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
		       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
		       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
		       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
		       "&NDMedico=" + NDMedico +
		       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
		       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
		       "&GastoTransFosyga=" + GastoTransFosyga+ "&usuario=" + usuario );
}

//Guarda todos los datos de sitio del evento y amparo.
function ActualizarDatos8() {
	var usuario = document.getElementById("txtUsuario").value;
	var Codigo = document.getElementById("Codigo").value;
	var fec_radicado = document.getElementById("fec_radicado").value;
	var num_radicado = document.getElementById("radicado").value;
	var ant_radicado = document.getElementById("antradicado").value;
	var factura = document.getElementById("factura").value;
	var condicion = document.getElementById("condicion").value;
	var evento = document.getElementById("eventonaturaleza").value;
	var dir_ocurrencia = document.getElementById("DirOcurrencia").value;
	var fec_evento = document.getElementById("fec_evento").value;
	var hora = document.getElementById("hora").value;
	var dpto_evento = document.getElementById("cbdep").value;
	var mun_evento = document.getElementById("cbmun").value;
	var zona = document.getElementById("zona").value;
	var descripcion = document.getElementById("Descripcion").value;
	var Remision = document.getElementById("Remision").value;
	var Transporte = document.getElementById("Transporte").value;
	//datos certificacion medica
	var fec_Ingreso=document.getElementById("fec_Ingreso").value;
	var hora_ingreso=document.getElementById("hora_ingreso").value;
	var fec_Egreso=document.getElementById("fec_Egreso").value;
	var hora_egreso=document.getElementById("hora_egreso").value;
	var DiagPrincipalIngreso=document.getElementById("DiagPrincipalIngreso").value;
	var DiagPrincipalEgreso=document.getElementById("DiagPrincipalEgreso").value;
	var OtroDiagIngreso=document.getElementById("OtroDiagIngreso").value;
	var OtroDiagEgreso=document.getElementById("OtroDiagEgreso").value;
	var OtroDiagIngreso2=document.getElementById("OtroDiagIngreso2").value;
	var OtroDiagEgreso2=document.getElementById("OtroDiagEgreso2").value;
	//datos medico
	var PAMedico=document.getElementById("PAMedico").value;
	var SAMedico=document.getElementById("SAMedico").value;
	var PNMedico=document.getElementById("PNMedico").value;
	var SNMedico=document.getElementById("SNMedico").value;
	var NDMedico=document.getElementById("NDMedico").value;
	var RegistroMedico=document.getElementById("RegistroMedico").value;
	//amparo que reclama
	var GastoMedicoFacturado=document.getElementById("GastoMedicoFacturado").value;
	var GastoMedicoFosyga=document.getElementById("GastoMedicoFosyga").value;
	var GastoTransFacturado=document.getElementById("GastoTransFacturado").value;
	var GastoTransFosyga=document.getElementById("GastoTransFosyga").value;
		
	ajax = getXMLObject();
	ajax.open("POST", "ControlFurips", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert("Registro Actualizado Exitosamente.");
			window.close();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=27&Codigo=" +Codigo +"&fec_radicado="+fec_radicado + 
		       "&num_radicado=" + num_radicado + "&ant_radicado=" + ant_radicado + 
		       "&factura="+ factura + "&condicion=" + condicion +
		       "&evento=" + evento + "&dir_ocurrencia=" + dir_ocurrencia +
		       "&fec_evento=" + fec_evento + "&hora=" + hora +
		       "&dpto_evento=" + dpto_evento + "&mun_evento=" + mun_evento +
		       "&zona=" + zona + "&descripcion=" + descripcion +
		       "&condicion=" + condicion +  
		       "&Remision="+Remision +"&Transporte="+Transporte+
		       "&fec_Ingreso=" + fec_Ingreso + "&hora_ingreso=" + hora_ingreso+
		       "&fec_Egreso=" + fec_Egreso + "&hora_egreso=" + hora_egreso +
		       "&DiagPrincipalIngreso=" + DiagPrincipalIngreso + "&DiagPrincipalEgreso=" + DiagPrincipalEgreso +
		       "&OtroDiagIngreso=" + OtroDiagIngreso + "&OtroDiagEgreso=" + OtroDiagEgreso +
		       "&OtroDiagIngreso2=" + OtroDiagIngreso2 + "&OtroDiagEgreso2=" + OtroDiagEgreso2 +
		       "&PAMedico=" + PAMedico + "&SAMedico=" + SAMedico +
		       "&PNMedico=" + PNMedico + "&SNMedico=" + SNMedico +
		       "&NDMedico=" + NDMedico +
		       "&RegistroMedico=" + RegistroMedico + "&GastoMedicoFacturado=" + GastoMedicoFacturado +
		       "&GastoMedicoFosyga=" + GastoMedicoFosyga + "&GastoTransFacturado=" + GastoTransFacturado +
		       "&GastoTransFosyga=" + GastoTransFosyga+ "&usuario=" + usuario);
}

function AutoCompleta2(vble){
	var diag=document.getElementById("diagini").value;
	var diagegre=document.getElementById("diagegre").value;
	var Otrodiaging=document.getElementById("Otrodiaging").value;
	var Otrodiagegre=document.getElementById("Otrodiagegre").value;
	var Otrodiaging2=document.getElementById("Otrodiaging2").value;
	var Otrodiagegre2=document.getElementById("Otrodiagegre2").value;
		
		ajax=getXMLObject();
		ajax.open("POST","ControlFurips",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				if(vble==1){
					document.getElementById("DiagPrincipalIng").innerHTML=ajax.responseText;
				}else if (vble==2) {
					document.getElementById("DiagPrincipalEgr").innerHTML=ajax.responseText;
				}else if (vble==3) {
					document.getElementById("OtroDiagIng").innerHTML=ajax.responseText;
				}else if (vble==4) {
					document.getElementById("OtroDiagEgr").innerHTML=ajax.responseText;
				}else if (vble==5) {
					document.getElementById("OtroDiagIng2").innerHTML=ajax.responseText;
				}else if (vble==6) {
					document.getElementById("OtroDiagEgr2").innerHTML=ajax.responseText;
				}
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		if(vble==1){
			ajax.send("valor=28&diag="+diag+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==2) {
			ajax.send("valor=28&diag="+diagegre+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==3) {
			ajax.send("valor=28&diag="+Otrodiaging+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==4) {
			ajax.send("valor=28&diag="+Otrodiagegre+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==5) {
			ajax.send("valor=28&diag="+Otrodiaging2+"&vble="+vble); //Posting txtname to Servle	
		}else if (vble==6) {
			ajax.send("valor=28&diag="+Otrodiagegre2+"&vble="+vble); //Posting txtname to Servle	
		}
}

function Seleccion(cod,diag){
	document.getElementById("diagini").value=diag;
	document.getElementById("DiagPrincipalIngreso").value=cod;
	document.getElementById("DiagPrincipalIng").innerHTML="";	
}

function Seleccion2(cod,diag){
	document.getElementById("diagegre").value=diag;
	document.getElementById("DiagPrincipalEgreso").value=cod;
	document.getElementById("DiagPrincipalEgr").innerHTML="";	
}
function Seleccion3(cod,diag){
	document.getElementById("Otrodiaging").value=diag;
	document.getElementById("OtroDiagIngreso").value=cod;
	document.getElementById("OtroDiagIng").innerHTML="";	
}

function Seleccion4(cod,diag){
	document.getElementById("Otrodiagegre").value=diag;
	document.getElementById("OtroDiagEgreso").value=cod;
	document.getElementById("OtroDiagEgr").innerHTML="";	
}
function Seleccion5(cod,diag){
	document.getElementById("Otrodiaging2").value=diag;
	document.getElementById("OtroDiagIngreso2").value=cod;
	document.getElementById("OtroDiagIng2").innerHTML="";	
}

function Seleccion6(cod,diag){
	document.getElementById("Otrodiagegre2").value=diag;
	document.getElementById("OtroDiagEgreso2").value=cod;
	document.getElementById("OtroDiagEgr2").innerHTML="";	
}