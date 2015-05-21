var patrones = new Array(2,2,4);
var patrones2 = new Array(1,3,3,3,3)
function mascaNormal(d,sep,pat,nums){
	//alert();
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if(ini>31){
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
	//alert(ini);
	
	if(ini>12){
		alert("Mes No Valido!!!");
		val2=d.value="";
		d.focus();
		
	}
	
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

function Periodo() {//Seleccionar el Periodo
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			document.getElementById('MC').focus();
				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1"); // Posting txtname to Servlet
}


function vpc() {//Consulta si el periodo se generó y si no esta bloqueado
	var pc = document.getElementById("MC").value;
	var ac = document.getElementById("AC").value;
	ajax = getXMLObject();
	/*
	 * pc=periodo
	 * ac=año
	 * p=codigo periodo
	 * b=bloqueo contable
	 * bcxp=bloqueo contable*/
	//var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var detDatos = ajax.responseText.split("|");
			var p=detDatos[0];
			var b=detDatos[1];
			var bcxp=detDatos[2];
			if(p=="00"){
				alert("El periodo no ha sido se ha generado!!!");
			}else{
			//	if(b=="1"){
					//b=1 significa que el periodo esta bloqueado.
				//alert("El periodo esta Bloqueado... Solo estara activo para consultas!!!");
					if(bcxp=="1"){
						alert("El Periodo de Cuentas por Pagar esta bloqueado");						
						//vpc2(pc,ac,p,b);
						alert("Solo Consultas.");
					}
					if(bcxp=="0"){
						//alert("El Periodo de Cuentas por Pagar esta Desbloqueado");						
						vpc2(pc,ac,p,b);
					}
					
				}
				/*if(b=="0"){
					//b=0 significa que el periodo esta activo
					vpc2(pc,ac,p,b);
				}*/
			//}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&pc="+pc+"&ac="+ac); 
}

function AutoCompletarBancos(){
	var Parametro = document.getElementById("txtBanco").value;
	ajax = getXMLObject();
	var contenido = document.getElementById('AutoBanco');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText;
			var ConCB=document.getElementById("txtContaCB").value 
			if(ConCB==1){
				var CodBancoCB=document.getElementById("txtCodBancoCB").value;
				AsignarBanco(CodBancoCB);
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=cbancos&Parametro="+Parametro); 
}

function AsignarBanco(CodBancoCB){
	document.getElementById("txtBanco").value=CodBancoCB;
	document.getElementById('AutoBanco').innerHTML ='';
	
}

function VerSCentroc(){
	var codCCosto=document.getElementById("txtCCostoD").value;
	ajax = getXMLObject();
	var contenido = document.getElementById('ScostoD');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=scco&codCCosto="+codCCosto); 
}

function VerCentroC(){
	var codSucursal=document.getElementById("sucuc").value;
	ajax = getXMLObject();
	var contenido = document.getElementById('CcostoD');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=cco&codSucursal="+codSucursal); 
}

function DescuentoP(){
	var NetoD=document.getElementById("txtNetoD").value;
	if(NetoD!=""){
		document.getElementById("txtValDesD").value="";
		var PorDesD=document.getElementById("txtPorDesD").value;	
		var porc=0;	
		if(PorDesD==""){PorDesD=0;}
		porc=parseFloat(NetoD)*(parseFloat(PorDesD)/100);
		document.getElementById("txtValDesD").readonly='';
		document.getElementById("txtValDesD").value=porc;
		document.getElementById("txtSubTotD").value=parseFloat(NetoD)-parseFloat(porc);
		//RetefuenteV();
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtNetoD").focus();
		document.getElementById("txtValDesD").value="";
		document.getElementById("txtPorDesD").value="";
		
	}
}
function DescuentoV(){
	var NetoD=document.getElementById("txtNetoD").value;
	if(NetoD!=""){
		document.getElementById("txtPorDesD").value="";
		var ValDesD=document.getElementById("txtValDesD").value;	
		var porc=0;	
		var ValPor=0;
		//if(PorDesD==""){PorDesD=0;}
		//var ppp=0;
		var ppp=((parseFloat(ValDesD))/(parseFloat(NetoD)))*100;
		//alert(ppp);
		porc=(parseFloat(NetoD))- (parseFloat(ValDesD));		
		document.getElementById("txtPorDesD").value=ppp;
		document.getElementById("txtSubTotD").value=porc;
		//RetefuenteV();
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtNetoD").focus();
		document.getElementById("txtValDesD").value="";
		document.getElementById("txtPorDesD").value="";
	}
}

function IvaP(){
	var SubTotD=document.getElementById("txtSubTotD").value;
	if(SubTotD!=""){
		document.getElementById("txtValIvaD").value="";
		var PorDesD=document.getElementById("txtPorIvaD").value;	
		var porc=0;	
		if(PorDesD==""){PorDesD=0;}
		porc=parseFloat(SubTotD)*(parseFloat(PorDesD)/100);
		document.getElementById("txtValIvaD").readonly='';
		document.getElementById("txtValIvaD").value=porc;
		document.getElementById("txtValFacD").value=parseFloat(SubTotD)+parseFloat(porc);
		//RetefuenteV();
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtNetoD").focus();
		document.getElementById("txtValIvaD").value="";
		document.getElementById("txtPorIvaD").value="";
	}
}

function IvaV(){
	var SubTotD=document.getElementById("txtSubTotD").value;
	//var ValFactIva=document.getElementById("txtValFacD").value;
	if(SubTotD!=""){
		document.getElementById("txtPorIvaD").value="";
		var ValDesD=document.getElementById("txtValIvaD").value;	
		var porc=0;	
		var ValPor=0;
		porc=parseFloat(SubTotD)+parseFloat(ValDesD);	
		var ppp=0;
		ppp=(parseFloat(ValDesD)/parseFloat(SubTotD))*100;
		document.getElementById("txtPorIvaD").value=ppp;
		document.getElementById("txtValFacD").value=porc;
		//RetefuenteV();
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtNetoD").focus();
		document.getElementById("txtValIvaD").value="";
		document.getElementById("txtPorIvaD").value="";
	}
}

function RetefuenteP(){
	//var SubTotD=document.getElementById("txtValFacD").value;
	var SubTotD=document.getElementById("txtNetoD").value;
	var ValFactIva=document.getElementById("txtValFacD").value;
	if(SubTotD!=""){
		document.getElementById("txtValRetfD").value="";
		
		var ValDesD=document.getElementById("txtValRetfD").value;	
		var ValReIvD=document.getElementById("txtValReIvD").value;
		var ValRetfD=document.getElementById("txtValIcaD").value;
		if(ValDesD==""){ValDesD="0";}if(ValReIvD==""){ValReIvD="0";}if(ValRetfD==""){ValRetfD="0";}
	
		var PorDesD=document.getElementById("txtPorRetfD").value;	/**cambiar**/
		var porc=0;	
		var valor=0;
		if(PorDesD==""){PorDesD=0;}
		
		porc=parseFloat(SubTotD)*(parseFloat(PorDesD)/100);
		
		document.getElementById("txtValRetfD").readonly='';
		document.getElementById("txtValRetfD").value=porc;
		valor=Math.ceil(parseFloat(ValFactIva)-(parseFloat(ValDesD)+parseFloat(ValReIvD)+parseFloat(ValRetfD)));
		document.getElementById("txtValPagD").value=valor;
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtValFacD").focus();
		document.getElementById("txtValIvaD").value="";
		document.getElementById("txtPorRetfD").value="";
	}
}

function RetefuenteV(){
	//var SubTotD=document.getElementById("txtValFacD").value;
	var SubTotD=document.getElementById("txtNetoD").value;
	var ValFactIva=document.getElementById("txtValFacD").value;
	if(SubTotD!=""){
		document.getElementById("txtPorRetfD").value="";
		
		var ValRetfD=document.getElementById("txtValRetfD").value;
		var ValReIvD=document.getElementById("txtValReIvD").value;
		var ValReIcD=document.getElementById("txtValIcaD").value;
		
		if(ValReIcD==""){ValReIcD="0";}if(ValReIvD==""){ValReIvD="0";}if(ValRetfD==""){ValRetfD="0";}
		
		var porc=0;	
		var ValPor=0;
		porc=Math.ceil(parseFloat(ValFactIva)-(parseFloat(ValReIcD)+parseFloat(ValReIvD)+parseFloat(ValRetfD)));//		se resta con el resto de valor ica,reteiva,retefuente
		var ppp=0;
		ppp=(parseFloat(ValRetfD)/parseFloat(SubTotD))*100;
		document.getElementById("txtPorRetfD").value=ppp;
		document.getElementById("txtValPagD").value=porc;
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtValFacD").focus();
		document.getElementById("txtValRetfD").value="";
		document.getElementById("txtPorRetfD").value="";
	}
}

function ReteivaP(){
	//var SubTotD=document.getElementById("txtValFacD").value;
	//var SubTotD=document.getElementById("txtNetoD").value;
	var SubTotD=document.getElementById("txtValIvaD").value
	var ValFactIva=document.getElementById("txtValFacD").value;
	if(SubTotD!=""){
		document.getElementById("txtValReIvD").value="";
		
		var ValDesD=document.getElementById("txtValRetfD").value;	
		var ValReIvD=document.getElementById("txtValReIvD").value;
		var ValRetfD=document.getElementById("txtValIcaD").value;
		if(ValDesD==""){ValDesD="0";}if(ValReIvD==""){ValReIvD="0";}if(ValRetfD==""){ValRetfD="0";}
	
		var PorDesD=document.getElementById("txtPorreIvD").value;	/**cambiar**/
		var porc=0;	
		var valor=0;
		if(PorDesD==""){PorDesD=0;}
		porc=parseFloat(SubTotD)*(parseFloat(PorDesD)/100);
		
		document.getElementById("txtValRetfD").readonly='';
		document.getElementById("txtValReIvD").value=porc;
		valor=Math.ceil(parseFloat(ValFactIva)-(parseFloat(ValDesD)+parseFloat(ValReIvD)+parseFloat(ValRetfD)));
		document.getElementById("txtValPagD").value=valor;
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtValFacD").focus();
		document.getElementById("txtValIvaD").value="";
		document.getElementById("txtPorRetfD").value="";
	}
}

function ReteivaV(){
	//var SubTotD=document.getElementById("txtValFacD").value;
	var ValFactIva=document.getElementById("txtValFacD").value;
	var SubTotD=document.getElementById("txtValIvaD").value
	if(SubTotD!=""){
		document.getElementById("txtPorreIvD").value="";
		
		var ValRetfD=document.getElementById("txtValRetfD").value;
		var ValReIvD =document.getElementById("txtValReIvD").value;
		var ValReIcD=document.getElementById("txtValIcaD").value;
		
		if(ValReIcD==""){ValReIcD="0";}if(ValReIvD==""){ValReIvD="0";}if(ValRetfD==""){ValRetfD="0";}
		
		var porc=0;	
		var ValPor=0;
		
		porc=Math.ceil(parseFloat(ValFactIva)-(parseFloat(ValReIcD)+parseFloat(ValReIvD)+parseFloat(ValRetfD)));//		se resta con el resto de valor ica,reteiva,retefuente
		//alert(ValFactIva+"-("+ValReIcD+"+"+ValReIvD+"+"+ValRetfD+")="+porc)
		var ppp=0;
		ppp=(parseFloat(ValReIvD)/parseFloat(SubTotD))*100;
		//alert("ppp reteiva "+ppp);
		document.getElementById("txtPorreIvD").value=ppp;
		document.getElementById("txtValPagD").value=porc;
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtValFacD").focus();
		document.getElementById("txtValRetfD").value="";
		document.getElementById("txtPorRetfD").value="";
	}
}

function IcaP(){
	//var SubTotD=document.getElementById("txtValFacD").value;
	var ValFactIva=document.getElementById("txtValFacD").value;
	var SubTotD=document.getElementById("txtNetoD").value;
	if(SubTotD!=""){
		document.getElementById("txtValIcaD").value="";
		
		var ValDesD=document.getElementById("txtValRetfD").value;	
		var ValReIvD=document.getElementById("txtValReIvD").value;
		var ValRetfD=document.getElementById("txtValIcaD").value;
		if(ValDesD==""){ValDesD="0";}if(ValReIvD==""){ValReIvD="0";}if(ValRetfD==""){ValRetfD="0";}
	
		var PorDesD=document.getElementById("txtPorcIcaD").value;	/**cambiar**/
		var porc=0;	
		var valor=0;
		if(PorDesD==""){PorDesD=0;}
		porc=(parseFloat(SubTotD)*parseFloat(PorDesD))/1000;
		
		document.getElementById("txtValRetfD").readonly='';
		document.getElementById("txtValIcaD").value=porc;
		valor=Math.ceil(parseFloat(ValFactIva)-(parseFloat(ValDesD)+parseFloat(ValReIvD)+parseFloat(ValRetfD)));
		document.getElementById("txtValPagD").value=valor;
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtValFacD").focus();
		document.getElementById("txtValIvaD").value="";
		document.getElementById("txtPorRetfD").value="";
	}
}

function IcaV(){
	var ValFactIva=document.getElementById("txtValFacD").value;
	var SubTotD=document.getElementById("txtNetoD").value;
	if(SubTotD!=""){
		document.getElementById("txtPorcIcaD").value="";
		
		var ValRetfD=document.getElementById("txtValRetfD").value;
		var ValReIvD=document.getElementById("txtValReIvD").value;
		var ValReIcD =document.getElementById("txtValIcaD").value;
		
		if(ValReIcD==""){ValReIcD="0";}if(ValReIvD==""){ValReIvD="0";}if(ValRetfD==""){ValRetfD="0";}
		
		var porc=0;	
		var ValPor=0;
		porc=Math.ceil(parseFloat(ValFactIva)-(parseFloat(ValReIcD)+parseFloat(ValReIvD)+parseFloat(ValRetfD)));//		se resta con el resto de valor ica,reteiva,retefuente
		var ppp=0;
		ppp=(parseFloat(ValReIcD)/parseFloat(SubTotD))*1000;
		//alert("ppp ica "+ppp);
		document.getElementById("txtPorcIcaD").value=ppp;
		document.getElementById("txtValPagD").value=porc;
	}else{
		alert("Digite El Neto.");
		document.getElementById("txtValFacD").focus();
		document.getElementById("txtValRetfD").value="";
		document.getElementById("txtPorRetfD").value="";
	}
}

function TotalSaldoEA(){
	//alert();
	var ValorDoc=document.getElementById("txtValFacD").value;
	var ValorDes=document.getElementById("txtValDesD").value;
	if(ValorDes==""){ValorDes="0"}
	var ValorRetf=document.getElementById("txtValRetfD").value;
	if(ValorRetf==""){ValorRetf="0"}
	var ValorReIv=document.getElementById("txtValReIvD").value;
	if(ValorReIv==""){ValorReIv="0"}
	var ValorIca=document.getElementById("txtValIcaD").value;
	if(ValorIca==""){ValorIca="0"}
    var Total=parseInt(ValorDes)-(parseInt(ValorRetf)-(parseInt(ValorReIv)-(parseInt(ValorIca))));
	var Tot=parseInt(ValorDoc)-parseInt(Total);
	
	
	document.getElementById("txtValPagD").value=Tot;
	var ValDocEA=document.getElementById("txtValDocEA").value;
}



function vpc2(pc,ac,p,b) {//Radios para Crear o Consultar Documentos
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3&pc="+pc+"&ac="+ac+"&p="+p+"&b="+b); 

}

function RadioDoc(v,pc,ac,p,b) {//Se Crea o se Consulta el Documento	
	if(v=="2"){
		alert("Periodo BLOQUEADO!!!! No se pueden crear Documentos ");
	}else{	
		ajax = getXMLObject();
		//var contenido = document.getElementById('Opcion');
		var contenido = document.getElementById('ContenidoCxP');
		ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				contenido.innerHTML = ajax.responseText;	
				//document.getElementById("CuandoEgreso").style.display='none';
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4&v="+v+"&pc="+pc+"&ac="+ac+"&p="+p+"&b="+b); 
	}
}


function TipoDoc(va) {//Se unifica el tipo de documento con el nombre
	if(va=="0"){
		var cc = document.getElementById("cmbCodTipoDocumento").value;
		var lc = document.getElementById("cmbCodTipoDocumento").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("cmbNomTipoDocumento").options[i].value){
			indice=i;
			}
		}
		document.getElementById("cmbNomTipoDocumento").text=document.getElementById("cmbNomTipoDocumento").options[indice].text;
		document.getElementById("cmbNomTipoDocumento").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("cmbNomTipoDocumento").value;
		 lc = document.getElementById("cmbNomTipoDocumento").length;
		 ln=lc-1;
		 indice;
		document.getElementById("cmbCodTipoDocumento").text=cc;
		document.getElementById("cmbCodTipoDocumento").value=cc;
	}
//	alert("cc "+cc);
	
	if((cc=="160")){
		document.getElementById("txtFacturaProveedor").disabled=false;
		document.getElementById("txtDiasPlazo").disabled=false;
		document.getElementById("txtFechaRecibo").disabled=false;
		document.getElementById("txtFacturaProveedor").style.background="none";
		document.getElementById("txtDiasPlazo").style.background="none";
		document.getElementById("txtFechaRecibo").style.background="none";
		var pi="<table border='0' width='100%' > " +
		"<tr>" +
		"<td class='style12'><input type='hidden' id='txtTipInser' value='0' >Saldo del Anticipo &nbsp;&nbsp;&nbsp; <input name='txtValorAnticipo' type='text' id='txtValorAnticipo' > <input name='btnIngresarcxpA' type='button' id='btnIngresarcxpA' value='Ingresar' onclick='GuardarCxPANT()' > </td>" +
		"<td></td></tr></table>" ;
		document.getElementById("CuandoEgreso").innerHTML = (pi);
		document.getElementById("btnIngresarcxp").style.visibility  = 'hidden';
	}
	if((cc=="140")||(cc=="150")){
		var p="<table border='0' width='100%' > " +
		"<tr>" +
		"<td width='10%' class='style12'>Banco</td>" +
		"<td width='28%' class='style12'><input name='txtBanco' type='text' id='txtBanco' onkeyup='AutoCompletarBancos()'></td>" +
		"<td class='style12' width='8%'  >Indicador Pago </td>" +
		"<td width='13%' ><input type='hidden' id='txtTipInser' value='1' ><select id='cmbCheque' onchange='IndicadorPag()'><option value='Seleccione'>Seleccione</option><option value='CG'>Consignacion</option><option value='CH'>Cheque</option></select></td>" +
		"<td>&nbsp;</td>" +	
		"<td class='style12' id='TipoIndiPago'></td>" +
		"</tr>  " +					
	    "<tr>" +
	    "<td class='style12'></td>" +
	    "<td><div id='AutoBanco'></div></td>" +
	    "<td class='style12'></td>" +
	    "<td class='style12'></td>" +
	    "<td class='style12'></td>" +
	    "<td class='style12'><div id='Chequera'></div></td>" +
	    "<td></td>" +
	    "</tr></table>" ;		
		//alert(p);
		document.getElementById("CuandoEgreso").innerHTML = (p);
		document.getElementById("CuandoEgreso").style.display = "";
		document.getElementById("txtFacturaProveedor").disabled=true;
		document.getElementById("txtDiasPlazo").disabled=true;
		document.getElementById("txtFechaRecibo").disabled=true;
		//document.getElementById("txtTercero").disabled=true;
		
		document.getElementById("txtFacturaProveedor").style.background="#C0C0A8";
		document.getElementById("txtDiasPlazo").style.background="#C0C0A8";
		document.getElementById("txtFechaRecibo").style.background="#C0C0A8";
		//document.getElementById("txtTercero").style.background="#C0C0A8";
		document.getElementById("btnIngresarcxp").style.visibility  = '';
		
	}
	if((cc=="110")||(cc=="120")||(cc=="130")){
		
		document.getElementById("CuandoEgreso").innerHTML ="<input type='hidden' id='txtTipInser' value='0' >";
		document.getElementById("CuandoEgreso").style.display = "none";
		document.getElementById("txtFacturaProveedor").disabled=false;
		document.getElementById("txtDiasPlazo").disabled=false;
		document.getElementById("txtFechaRecibo").disabled=false;
		//document.getElementById("txtTercero").disabled=false;
		
		document.getElementById("txtFacturaProveedor").style.background="none";
		document.getElementById("txtDiasPlazo").style.background="none";
		document.getElementById("txtFechaRecibo").style.background="none";
		//document.getElementById("txtTercero").style.background="none";
		document.getElementById("btnIngresarcxp").style.visibility  = '';
		
	}
	numdoc(cc);
}

function GuardarCxPANT(){
	//alert();
	/***********************************************************/
	var TipoInsercion=document.getElementById("txtTipInser").value;
	var TipoDocumento= document.getElementById("cmbCodTipoDocumento").value;
	var FechaMovimientoCxP= document.getElementById("fd").value;
	var NumeroDocumentoCxP= document.getElementById("txtNumeroDocumentoCxP").value;
	var DetalleCxP= document.getElementById("txtDetalle").value;
	///var NumeroFacturaProveedor= document.getElementById("txtFacturaProveedor").value;
	var CodTercero= document.getElementById("txtCodTercero").value;
	var TipoAfectacionCxP= document.getElementById("txtTipoAfectacion").value;
	var DiasPlazo= document.getElementById("txtDiasPlazo").value;
	var FechaRecibo= document.getElementById("txtFechaRecibo").value;
	var pec=document.getElementById("txtpc").value;
	var anc =document.getElementById("txtac").value;
	var Usuario=document.getElementById("txtCodusuario").value;
	var ValorAnticipo=document.getElementById("txtValorAnticipo").value;
		if(TipoDocumento=="Seleccione"){
			alert("Seleccione el tipo de documento.");
			document.getElementById("cmbCodTipoDocumento").focus();
		}else{
			if(FechaMovimientoCxP==""){
				alert("Digite la fecha del documento.");
				document.getElementById("fd").focus();
			}else{
				if(DetalleCxP==""){
					alert("Escriba el detalle.");
					document.getElementById("txtDetalle").focus();
				}else{
					/*if(NumeroFacturaProveedor==""){
						alert("Escriba el numero de la factura.");
						document.getElementById("txtFacturaProveedor").focus();
					}else{*/
						if(CodTercero==""){
							alert("Seleccione el tercero.");
							document.getElementById("txtTercero").focus();
						}else{
							if(TipoAfectacionCxP==""){
								alert("Seleccione tipo afectacion.");
								document.getElementById("txtTipoAfectacion").focus();
							}else{
								if(DiasPlazo==""){
									alert("Escriba el plazo.");
									document.getElementById("txtDiasPlazo").focus();
								}else{
									
										/***********************************************************/
										var y=FechaMovimientoCxP.split("/").length;
										var z=FechaMovimientoCxP.split("/");	
										var AnioM="";
										var MesM="";
										var DiaM="";
										for(x=0; x<y-1; x=x+1)
										{  		
											DiaM=z[0];
											MesM=z[1];		
											AnioM=z[2];		
										}

										var yy=FechaRecibo.split("/").length;
										var zz=FechaRecibo.split("/");	
										var AnioMm="";
										var MesMm="";
										var DiaMm="";
										for(xx=0; xx<yy-1; xx=xx+1)
										{  		
											DiaMm=zz[0];
											MesMm=zz[1];		
											AnioMm=zz[2];		
										}

										FechaRecibo=AnioMm+"-"+MesMm+"-"+DiaMm;
										FechaMovimientoCxP=AnioM+"-"+MesM+"-"+DiaM;
											ajax=getXMLObject();
											ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
											ajax.onreadystatechange  = function(){
												if (ajax.readyState == 4) {
													//document.getElementById("TablaCxP").disabled=true;
													document.getElementById('TablaCxP').disabled = true;
													document.getElementById("txtTipInser").disabled = true;
													document.getElementById("cmbCodTipoDocumento").disabled = true;
													document.getElementById("fd").disabled = true;
													document.getElementById("txtNumeroDocumentoCxP").disabled = true;
													document.getElementById("txtDetalle").disabled = true;
													document.getElementById("txtFacturaProveedor").disabled = true;
													document.getElementById("txtTercero").disabled = true;
													document.getElementById("txtTipoAfectacion").disabled = true;
													document.getElementById("txtDiasPlazo").disabled = true;
													document.getElementById("txtFechaRecibo").disabled = true;
													document.getElementById("btnIngresarcxp").disabled = true;
													document.getElementById("DetalleCxP").innerHTML=ajax.responseText;
												}
											}
											ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
											ajax.send("valor=grcxpANT&TipoDocumento="+TipoDocumento+"&FechaMovimientoCxP="+FechaMovimientoCxP+
													"&NumeroDocumentoCxP="+NumeroDocumentoCxP+
													"&DetalleCxP="+DetalleCxP+
													"&ValorAnticipo="+ValorAnticipo+
													"&CodTercero="+CodTercero+
													"&TipoAfectacionCxP="+TipoAfectacionCxP+
													"&DiasPlazo="+DiasPlazo+
													"&FechaRecibo="+FechaRecibo+
													"&TipoInsercion="+TipoInsercion+
													"&pec="+pec+
													"&anc="+anc+
													"&Usuario="+Usuario); //Posting txtname to Servle
									}
								}
							//}
						//}
					}
				}
			}
		}
}
function AutoCompletaDocumentoEA(){
	document.getElementById("cmbTipoDocumentoEA").value;
	document.getElementById("txtNumeroDocumentoEA").value;
	
}
function AutoCompletaChequera(){
	var NombreChquera=document.getElementById("txtChequeConsig").value;
	var CodBanco=document.getElementById("txtBanco").value;
	if(NombreChquera!=""){
		if(CodBanco==""){
			alert("Seleccione un Banco.");
			document.getElementById("txtBanco").focus();
		}else{
			ajax=getXMLObject();
			ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {		
					document.getElementById("Chequera").innerHTML=ajax.responseText;
					var conac=document.getElementById("txtContadorCHQ").value;
						if(conac=="1"){
							var NomCheq=document.getElementById("txtNomCheq").value;
							var NumCheque=document.getElementById("txtNumCheque").value;
							var CodCheque=document.getElementById("txtCodigoCheq").value;
							AsignarChequera(NomCheq,NumCheque,CodCheque);
						}				
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=auch&NomChequera="+NombreChquera+"&CodBanco="+CodBanco); //Posting txtname to Servle
		}
	}	
}

function AsignarChequera(NomCheq,NumCheque,CodCheque){
	//alert("NomCheq "+NomCheq+" NumCheque "+NumCheque);
	document.getElementById("txtCodigoChequera").value=CodCheque;
	document.getElementById("txtNumeroCheque").value=NumCheque;
	document.getElementById("txtChequeConsig").value=NomCheq;
	document.getElementById("Chequera").innerHTML='';
}

function GuardarCxP(){
	/***********************************************************/
	var TipoInsercion=document.getElementById("txtTipInser").value;
	var TipoDocumento= document.getElementById("cmbCodTipoDocumento").value;
	var FechaMovimientoCxP= document.getElementById("fd").value;
	var NumeroDocumentoCxP= document.getElementById("txtNumeroDocumentoCxP").value;
	var DetalleCxP= document.getElementById("txtDetalle").value;
	var NumeroFacturaProveedor= document.getElementById("txtFacturaProveedor").value;
	var CodTercero= document.getElementById("txtCodTercero").value;
	var TipoAfectacionCxP= document.getElementById("txtTipoAfectacion").value;
	var DiasPlazo= document.getElementById("txtDiasPlazo").value;
	var FechaRecibo= document.getElementById("txtFechaRecibo").value;
	var pec=document.getElementById("txtpc").value;
	var anc =document.getElementById("txtac").value;
	var Usuario=document.getElementById("txtCodusuario").value;

	if(TipoInsercion=="0"){
		if(TipoDocumento=="Seleccione"){
			alert("Seleccione el tipo de documento.");
			document.getElementById("cmbCodTipoDocumento").focus();
		}else{
			if(FechaMovimientoCxP==""){
				alert("Digite la fecha del documento.");
				document.getElementById("fd").focus();
			}else{
				if(DetalleCxP==""){
					alert("Escriba el detalle.");
					document.getElementById("txtDetalle").focus();
				}else{
					if(NumeroFacturaProveedor==""){
						alert("Escriba el numero de la factura.");
						document.getElementById("txtFacturaProveedor").focus();
					}else{
						if(CodTercero==""){
							alert("Seleccione el tercero.");
							document.getElementById("txtTercero").focus();
						}else{
							if(TipoAfectacionCxP==""){
								alert("Seleccione tipo afectacion.");
								document.getElementById("txtTipoAfectacion").focus();
							}else{
								if(DiasPlazo==""){
									alert("Escriba el plazo.");
									document.getElementById("txtDiasPlazo").focus();
								}else{
									if(FechaRecibo==""){
										alert("Escriba la fecha de recibo.");
										document.getElementById("txtFechaRecibo").focus();									
									}else{
										/***********************************************************/
										var y=FechaMovimientoCxP.split("/").length;
										var z=FechaMovimientoCxP.split("/");	
										var AnioM="";
										var MesM="";
										var DiaM="";
										for(x=0; x<y-1; x=x+1)
										{  		
											DiaM=z[0];
											MesM=z[1];		
											AnioM=z[2];		
										}

										var yy=FechaRecibo.split("/").length;
										var zz=FechaRecibo.split("/");	
										var AnioMm="";
										var MesMm="";
										var DiaMm="";
										for(xx=0; xx<yy-1; xx=xx+1)
										{  		
											DiaMm=zz[0];
											MesMm=zz[1];		
											AnioMm=zz[2];		
										}

										FechaRecibo=AnioMm+"-"+MesMm+"-"+DiaMm;
										FechaMovimientoCxP=AnioM+"-"+MesM+"-"+DiaM;
											ajax=getXMLObject();
											ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
											ajax.onreadystatechange  = function(){
												if (ajax.readyState == 4) {
													//document.getElementById("TablaCxP").disabled=true;
													document.getElementById('TablaCxP').disabled = true;
													document.getElementById("txtTipInser").disabled = true;
													document.getElementById("cmbCodTipoDocumento").disabled = true;
													document.getElementById("fd").disabled = true;
													document.getElementById("txtNumeroDocumentoCxP").disabled = true;
													document.getElementById("txtDetalle").disabled = true;
													document.getElementById("txtFacturaProveedor").disabled = true;
													document.getElementById("txtTercero").disabled = true;
													document.getElementById("txtTipoAfectacion").disabled = true;
													document.getElementById("txtDiasPlazo").disabled = true;
													document.getElementById("txtFechaRecibo").disabled = true;
													document.getElementById("btnIngresarcxp").disabled = true;
													document.getElementById("DetalleCxP").innerHTML=ajax.responseText;
												}
											}
											ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
											ajax.send("valor=grcxp&TipoDocumento="+TipoDocumento+"&FechaMovimientoCxP="+FechaMovimientoCxP+
													"&NumeroDocumentoCxP="+NumeroDocumentoCxP+
													"&DetalleCxP="+DetalleCxP+
													"&NumeroFacturaProveedor="+NumeroFacturaProveedor+
													"&CodTercero="+CodTercero+
													"&TipoAfectacionCxP="+TipoAfectacionCxP+
													"&DiasPlazo="+DiasPlazo+
													"&FechaRecibo="+FechaRecibo+
													"&TipoInsercion="+TipoInsercion+
													"&pec="+pec+
													"&anc="+anc+
													"&Usuario="+Usuario); //Posting txtname to Servle
									}
								}
							}
						}
					}
				}
			}
		}
	}
	if(TipoInsercion=="1"){
		
		var CodBanco=document.getElementById("txtBanco").value;
		var IndicadorPago=document.getElementById("cmbCheque").value;// (CG,CH)
		var NumeroCheqCons=document.getElementById("txtChequeConsig").value;//(autocompletar muestra la chequera)
		
		
		if(IndicadorPago=="CG"){
			IndicadorPago="1";
		}
		if(IndicadorPago=="CH"){
			IndicadorPago="2";
			var NumeroCheque=document.getElementById("txtNumeroCheque").value;
			var CodigoChequera=document.getElementById("txtCodigoChequera").value;
			
		}
		//alert("FechaMovimientoCxP"+FechaMovimientoCxP)
		var y1=FechaMovimientoCxP.split("/").length;
		var z1=FechaMovimientoCxP.split("/");	
		var AnioM1="";
		var MesM1="";
		var DiaM1="";
		for(x=0; x<y1-1; x=x+1)
		{
			DiaM1=z1[0];
			MesM1=z1[1];		
			AnioM1=z1[2];		
		}
		FechaMovimientoCxP=AnioM1+"-"+MesM1+"-"+DiaM1;
		//alert("FechaMovimientoCxP "+FechaMovimientoCxP);
		ajax=getXMLObject();
		ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				//document.getElementById("TablaCxP").disabled=true;
				document.getElementById('TablaCxP').disabled = true;
				document.getElementById("txtTipInser").disabled = true;
				document.getElementById("cmbCodTipoDocumento").disabled = true;
				document.getElementById("fd").disabled = true;
				document.getElementById("txtNumeroDocumentoCxP").disabled = true;
				document.getElementById("txtDetalle").disabled = true;
				document.getElementById("txtFacturaProveedor").disabled = true;
				document.getElementById("txtTercero").disabled = true;
				document.getElementById("txtTipoAfectacion").disabled = true;
				document.getElementById("txtDiasPlazo").disabled = true;
				document.getElementById("txtFechaRecibo").disabled = true;
				document.getElementById("btnIngresarcxp").disabled = true;
				document.getElementById("DetalleCxP").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		if(IndicadorPago=="2"){
		ajax.send("valor=grcxp&TipoDocumento="+TipoDocumento+
				"&FechaMovimientoCxP="+FechaMovimientoCxP+
				"&NumeroDocumentoCxP="+NumeroDocumentoCxP+
				"&DetalleCxP="+DetalleCxP+
				"&TipoAfectacionCxP="+TipoAfectacionCxP+
				"&TipoInsercion="+TipoInsercion+
				"&pec="+pec+
				"&anc="+anc+
				"&Usuario="+Usuario+
				"&cod_banco_fk="+CodBanco+
				"&cod_hcheque_fk="+NumeroCheqCons+
				"&NumeroCheque="+NumeroCheque+
				"&indicador_pago="+IndicadorPago+
				"&CodigoChequera="+CodigoChequera); //Posting txtname to Servle*/	
		}
		if(IndicadorPago=="1"){
			ajax.send("valor=grcxp&TipoDocumento="+TipoDocumento+
					"&FechaMovimientoCxP="+FechaMovimientoCxP+
					"&NumeroDocumentoCxP="+NumeroDocumentoCxP+
					"&DetalleCxP="+DetalleCxP+
					"&TipoAfectacionCxP="+TipoAfectacionCxP+
					"&TipoInsercion="+TipoInsercion+
					"&pec="+pec+
					"&anc="+anc+
					"&Usuario="+Usuario+
					"&cod_banco_fk="+CodBanco+
					"&cod_hcheque_fk="+NumeroCheqCons+
					"&NumeroCheque="+NumeroCheque+
					"&indicador_pago="+IndicadorPago); //Posting txtname to Servle*/	
			}
		
	}
}


function AutoCompletaDocumentos(){
	var TipoDocumentoEA= document.getElementById("cmbDocumentoAE").value;
	var NumeroDocumentoEA=document.getElementById("txtNumeroDocumentoAE").value;
	var CodTercero=document.getElementById("txtCodTercero").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			alert(ajax.responseText);
			document.getElementById("AutoDocumentos").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AuDoc&TipoDocumentoEA="+TipoDocumentoEA+"&NumeroDocumentoEA="+NumeroDocumentoEA+"&CodTercero="+CodTercero); //Posting txtname to Servle

	
}

function SeleccionarDocumentoEA(NumeroDocumento,SaldoDocumento,SaldoPlano){
	//alert("NumeroDocumento "+NumeroDocumento+" SaldoDocumento "+SaldoDocumento);
	document.getElementById("txtNumeroDocumentoAE").value=NumeroDocumento;
	document.getElementById("AutoDocumentos").innerHTML="Saldo:\n"+SaldoDocumento+"<input type='hidden' id='txtValDocEA' value="+SaldoPlano+">";
}


function EliminarDetallecxPEA(CodDetCxP,CodDocCxP,DocumentoEA,ValorOmitir){
	alert("CodDetCxP "+CodDetCxP+" CodDocCxP= "+CodDocCxP+" DocumentoEA "+DocumentoEA+" ValorOmitir "+ValorOmitir);
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("DetalleDocCxP").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmDCxPEA&CodDetCxP="+CodDetCxP+"&CodDocumentoCxP="+CodDocCxP+"&DocumentoEA="+DocumentoEA+"&ValorOmitir="+ValorOmitir); //Posting txtname to Servle
}

function EliminarDetallecxP(CodDetCxP,CodDocCxP){
	//alert("CodDetCxP "+CodDetCxP+" CodDocCxP= "+CodDocCxP);
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("DetalleDocCxP").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmDCxP&CodDetCxP="+CodDetCxP+"&CodDocumentoCxP="+CodDocCxP); //Posting txtname to Servle
}

function FinalizarDocumentpCxP(){
	var CodDocumentoCxP =document.getElementById("txtCodCxP").value;
		ajax=getXMLObject();
		ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				//document.getElementById("Tercero").innerHTML=ajax.responseText;
				//alert("Documento Creado Exitosamente.");
				//imprimirReporteCxP(CodDocumentoCxP);
				//window.location.href="cont_DocumentosCxP.jsp";
				document.getElementById("DetalleCxP").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=FinCxP&CodDocumentoCxP="+CodDocumentoCxP); //Posting txtname to Servle
}

function imprimirReporteCxP(CodDocumento){
	
	pagina="cont_ReporteDocumentoCxP.jsp?CodDocumento="+CodDocumento;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}
/*********************************************************************************************************/
/*********************************************************************************************************/
/*********************************************************************************************************/
function SeleccionarCuenta(Tipo,CodigoCuenta){
	alert("Tipo="+Tipo+" CodigoCuenta="+CodigoCuenta);
}
function AutoCompletaCIR(){
	//alert("AutoCompletaCIR");
	var Numero_Cuenta =document.getElementById("txtIvaRetenido").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaIvaRete").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACIR&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle
	
	}
function AutoCompletaCIcaR(){
	//alert("AutoCompletaCIcaR");
	var Numero_Cuenta =document.getElementById("txtIcaRetenido").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaIcaRete").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACIcaR&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle	
}
function AutoCompletaCG(){
	//alert("AutoCompletaCG");
	var Numero_Cuenta =document.getElementById("txtGastos").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaGastos").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACG&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle

}
function AutoCompletaCIP(){
	//alert("AutoCompletaCIP");
	var Numero_Cuenta =document.getElementById("txtIvaPagado").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaIvaPagado").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACIP&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle
	}
function AutoCompletaCRet(){
	//alert("AutoCompletaCRet");
	var Numero_Cuenta =document.getElementById("txtRetencion").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaRetencion").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACRet&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle
	}
function AutoCompletaCDes(){
	//alert("AutoCompletaCDes");
	var Numero_Cuenta =document.getElementById("txtDescuentos").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaDescuento").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACDes&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle
	}
function AutoCompletaCIva(){
	//alert("AutoCompletaCIva");
	var Numero_Cuenta =document.getElementById("txtIva").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaIva").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACIva&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle
	}
function AutoCompletaCAnt(){
	//alert("AutoCompletaCAnt");
	var Numero_Cuenta =document.getElementById("txtAnticipos").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaAnticipos").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACAnt&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle
}
function AutoCompletaCPro(){
	var Numero_Cuenta =document.getElementById("txtProveedorIC").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			document.getElementById("CuentaProveedor").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ACPro&Numero_Cuenta="+Numero_Cuenta); //Posting txtname to Servle
	//alert("AutoCompletaCPro");
	}
/*********************************************************************************************************/
/*********************************************************************************************************/
/*********************************************************************************************************/
function GuardarDetalleCxPEA(){
//	alert();
	var CodDocumentoCxP =document.getElementById("txtCodCxP").value;
	var CodSucursal=document.getElementById("sucuc").value;
	var CodCCosto=document.getElementById("txtCCostoD").value;
	var CodScosto=document.getElementById("txtSCostoD").value;
	var InterfasContable=document.getElementById("txtIngConD").value;
	/**************************************************************/
	var ValorFactu=document.getElementById("txtValFacD").value;
	var ValDesc=document.getElementById("txtValDesD").value;
	var ValRetFue=document.getElementById("txtValRetfD").value;
	var ValRetIva=document.getElementById("txtValReIvD").value;
	var ValIca=document.getElementById("txtValIcaD").value;
	var ValPagar=document.getElementById("txtValPagD").value;
	
	/*************************************************************/
	var TipoDocumentoCFN=document.getElementById("cmbDocumentoAE").value;
	var NumeroDocumentoCFN=document.getElementById("txtNumeroDocumentoAE").value;
	var cmbNomTipoDocumento=document.getElementById("cmbNomTipoDocumento").value;
	if(NumeroDocumentoCFN!=""){
		var ValDocFCN=document.getElementById("txtValDocEA").value;
		if(parseInt(ValPagar) > parseInt(ValDocFCN)){
			alert("El valor no puede superar el del documento seleccionado.");
		}else{
			ajax=getXMLObject();
			ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					document.getElementById("DetalleDocCxP").innerHTML=ajax.responseText;	
					document.getElementById("txtValFacD").value="";
					document.getElementById("txtValDesD").value="";
					document.getElementById("txtValRetfD").value="";
					document.getElementById("txtValReIvD").value="";
					document.getElementById("txtValIcaD").value="";
					document.getElementById("txtValPagD").value="";
					document.getElementById("txtNumeroDocumentoAE").value="";
					document.getElementById("AutoDocumentos").innerHTML="";
					document.getElementById("txtNumeroDocumentoAE").focus();
					//CamposDocCxP();
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=GdcxpEA" +
					"&CodDocumentoCxP="+CodDocumentoCxP+
					"&CodSucursal="+CodSucursal+
					"&CodCCosto="+CodCCosto+
					"&CodScosto="+CodScosto+
					"&InterfasContable="+InterfasContable+
					"&ValDesc="+ValDesc+
					"&ValRetIva="+ValRetIva+
					"&ValorFactu="+ValorFactu+
					"&ValRetFue="+ValRetFue+
					"&ValIca="+ValIca+
					"&ValPagar="+ValPagar+			
					"&TipoDocumentoCFN="+TipoDocumentoCFN+
					"&NumeroDocumentoCFN="+NumeroDocumentoCFN+
					"&cmbNomTipoDocumento="+cmbNomTipoDocumento+
					"&ValDocFCN="+ValDocFCN); //Posting txtname to Servle
		}
	}else{
		alert("Seleccione Documento Afectar.");
		document.getElementById("txtNumeroDocumentoAE").focus();
	}
}



function GuardarDetalleCxP(){
//	alert();
	var CodDocumentoCxP =document.getElementById("txtCodCxP").value;
	var CodSucursal=document.getElementById("sucuc").value;
	var CodCCosto=document.getElementById("txtCCostoD").value;
	var CodScosto=document.getElementById("txtSCostoD").value;
	var InterfasContable=document.getElementById("txtIngConD").value;
	var Neto=document.getElementById("txtNetoD").value;
	var PorDesc=document.getElementById("txtPorDesD").value;
	var ValDesc=document.getElementById("txtValDesD").value;
	var Subtot=document.getElementById("txtSubTotD").value;
	var PorIva=document.getElementById("txtPorIvaD").value;
	var ValIva=document.getElementById("txtValIvaD").value;
	var ValorFactu=document.getElementById("txtValFacD").value;
	var PorRetFue=document.getElementById("txtPorRetfD").value;
	var ValRetFue=document.getElementById("txtValRetfD").value;
	var PorRetIva=document.getElementById("txtPorreIvD").value;
	var ValRetIva=document.getElementById("txtValReIvD").value;
	var PorIca=document.getElementById("txtPorcIcaD").value;
	var ValIca=document.getElementById("txtValIcaD").value;
	var ValPagar=document.getElementById("txtValPagD").value;
	var SalPagar=document.getElementById("txtSalPagaD").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("DetalleDocCxP").innerHTML=ajax.responseText;	
			document.getElementById("txtNetoD").value="";
			document.getElementById("txtPorDesD").value="";
			document.getElementById("txtValDesD").value="";
			document.getElementById("txtSubTotD").value="";
			document.getElementById("txtPorIvaD").value="";
			document.getElementById("txtValIvaD").value="";
			document.getElementById("txtValFacD").value="";
			document.getElementById("txtPorRetfD").value="";
			document.getElementById("txtValRetfD").value="";
			document.getElementById("txtPorreIvD").value="";
			document.getElementById("txtValReIvD").value="";
			document.getElementById("txtPorcIcaD").value="";
			document.getElementById("txtValIcaD").value="";
			document.getElementById("txtValPagD").value="";
			document.getElementById("txtSalPagaD").value="";
			//CamposDocCxP();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Gdcxp&CodDocumentoCxP="+CodDocumentoCxP+
			"&CodSucursal="+CodSucursal+
			"&CodCCosto="+CodCCosto+
			"&CodScosto="+CodScosto+
			"&InterfasContable="+InterfasContable+
			"&Neto="+Neto+
			"&PorDesc="+PorDesc+
			"&ValDesc="+ValDesc+
			"&Subtot="+Subtot+
			"&PorIva="+PorIva+
			"&ValIva="+ValIva+
			"&ValorFactu="+ValorFactu+
			"&PorRetFue="+PorRetFue+
			"&ValRetFue="+ValRetFue+
			"&PorRetIva="+PorRetIva+
			"&ValRetIva="+ValRetIva+
			"&PorIca="+PorIca+
			"&ValIca="+ValIca+
			"&ValPagar="+ValPagar+			
			"&SalPagar="+SalPagar); //Posting txtname to Servle

}

function IndicadorPag(){
	var TipoPago=document.getElementById("cmbCheque").value;
	var pr="";
	if(TipoPago=="CH"){
		pr="<td width='13%'>Chequera</td><td><input type='text' id='txtChequeConsig' onkeyup='AutoCompletaChequera()'><input id='txtNumeroCheque' disabled=true readonly=''  ><input id='txtCodigoChequera' type='hidden'></td>";
	}else{
		pr="<td width='13%'>Consignacion</td><td><input type='text' id='txtChequeConsig' ></td>";
	}
	document.getElementById("TipoIndiPago").innerHTML = (pr);
	
	
	
}

function AutocompletarTercero(){
	var parametro=document.getElementById("txtTercero").value;
	if(parametro!=""){
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				document.getElementById("Tercero").innerHTML=ajax.responseText;
				var conac=document.getElementById("txtContadorACT").value;
				if(conac=="1"){
					//alert("ultimo registro");
					var CodTercero=document.getElementById("txtCodTerceroACT").value;
					var DocumentoTercero=document.getElementById("txtDocumentoTerceroACT").value;
					var DV=document.getElementById("txtDV").value;
					var NombreTercero=document.getElementById("txtNombreTercero").value;
					AsignarTercero(CodTercero,DocumentoTercero,DV,NombreTercero);
				}
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=act1&parametro="+parametro); //Posting txtname to Servle
	}
}

function AsignarTercero(CodTercero,DocumentoTercero,DV,NombreTercero){
	document.getElementById("txtCodTercero").value=CodTercero;
	document.getElementById("txtTercero").value=DocumentoTercero+"-"+DV;
	document.getElementById("txtDiasPlazo").value=document.getElementById("txtDplazoACT").value
	document.getElementById("Tercero").innerHTML=NombreTercero;
	document.getElementById("txtTipoAfectacion").focus();
}

function AutocompletaIngDistContable(){
	var TipoAfectacion=document.getElementById("txtTipoAfectacion").value;
	if(TipoAfectacion!=""){
		ajax=getXMLObject();
		ajax.open("POST","ControlDocumentosCxP",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				//alert(ajax.responseText);
				document.getElementById("DisContable").innerHTML=ajax.responseText;
				var conac=document.getElementById("txtContadorDC").value;
				if(conac=="1"){
					//alert("ultimo registro");
					var CodDC=document.getElementById("txtCodDC").value;
					var NomDC=document.getElementById("txtNomDC").value;
				//  var DocumentoTercero=document.getElementById("txtDocumentoTerceroACT").value;
				//	var DPLazo=document.getElementById("txtDplazoACT").value;
				//	AsignarTercero(CodTercero,DocumentoTercero);
					document.getElementById("DisContable").innerHTML=NomDC;
					AsignarIngresoDisConta(CodDC);
				}
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=adc&TipoAfectacion="+TipoAfectacion); //Posting txtname to Servle

	}
}

function AsignarIngresoDisConta(CodIngDisCon){
	document.getElementById("txtTipoAfectacion").value=CodIngDisCon;
	
	//document.getElementById("DisContable").innerHTML='';
	document.getElementById("txtFechaRecibo").focus();
}

function numdoc(cc){//
	if(cc!="Seleccione"){
		//var pd = document.getElementById("Plantilla").value;
		ajax = getXMLObject();
		//var contenido = document.getElementById('pcont');
		ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
		// servlet name
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				document.getElementById("txtNumeroDocumentoCxP").value ="";
				val = ajax.responseText.split("|");
				var cons="";
				
				if(val[1].length==1){cons=("00000000"+val[1]);}
				if(val[1].length==2){cons=("0000000"+val[1]);}
				if(val[1].length==3){cons=("000000"+val[1]);}
				if(val[1].length==4){cons=("00000"+val[1]);}
				if(val[1].length==5){cons=("0000"+val[1]);}
				if(val[1].length==6){cons=("000"+val[1]);}
				if(val[1].length==7){cons=("00"+val[1]);}
				if(val[1].length==8){cons=("0"+val[1]);}
				if(val[1].length==9){cons=val[1];}
				if(val[1].length>9){alert("Consecutivo supera el registro!!!");}else{
					document.getElementById("txtNumeroDocumentoCxP").value =(val[0]+""+cons);
				}//contenido.innerHTML = ajax.responseText;	
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=9&cc="+cc); 
	}
}
/**********************************************************************************************/
/**********************************************************************************************/
/**********************************************************************************************/
/**********************************************************************************************/
/**********************************************************************************************/
/**********************************************************************************************/
/**********************************************************************************************/
/**********************************************************************************************/




function fpcont() {//Se verifica la plantilla y se montan los otros campos
	var pd = document.getElementById("Plantilla").value;
	ajax = getXMLObject();
	var contenido = document.getElementById('pcont');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;	
			document.getElementById('divtercero').innerHTML="";
			document.getElementById('Centrosc').innerHTML="";
			document.getElementById('SCentrosc').innerHTML="";
			document.getElementById('VBase').innerHTML="";
			
			if(pd!=0){div3();}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&pd="+pd); 

}




function vsucu(va) {//Se unifica el tipo de documento con el nombre
	if(va=="0"){
		var cc = document.getElementById("sucuc").value;
		var lc = document.getElementById("sucuc").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("sucun").options[i].value){
			indice=i;
			}
		}
		document.getElementById("sucun").text=document.getElementById("sucun").options[indice].text;
		document.getElementById("sucun").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("sucun").value;
		 lc = document.getElementById("sucun").length;
		 ln=lc-1;
		 indice;
		document.getElementById("sucuc").text=cc;
		document.getElementById("sucuc").value=cc;
	}
	vcentros(cc,"0");

}

function vcentros(su,cos) {//Se verifica la sucursal y se cargan los centros de costo y tambien sirve para verificar el centro de costo y cargar los sucentros
	ajax = getXMLObject();
	if(cos=="0"){
	var contenido = document.getElementById('Centrosc');
	}
	if(cos=="1"){
	contenido = document.getElementById('SCentrosc');
	}
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6&su="+su+"&cos="+cos); 

}


function vccosto(va) {//Se unifica el centro de costo con el nombre
	if(va=="0"){
		var cc = document.getElementById("ccostoc").value;
		var lc = document.getElementById("ccostoc").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("ccoston").options[i].value){
			indice=i;
			}
		}
		document.getElementById("ccoston").text=document.getElementById("ccoston").options[indice].text;
		document.getElementById("ccoston").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("ccoston").value;
		 lc = document.getElementById("ccoston").length;
		 ln=lc-1;
		 indice;
		document.getElementById("ccostoc").text=cc;
		document.getElementById("ccostoc").value=cc;
	}
	vcentros(cc,"1");
}


function vter(va) {//Se unifica el centro de costo con el nombre
	if(va=="0"){
		var cc = document.getElementById("tercero").value;
		var lc = document.getElementById("tercero").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("terceron").options[i].value){
			indice=i;
			}
		}
		document.getElementById("terceron").text=document.getElementById("terceron").options[indice].text;
		document.getElementById("terceron").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("terceron").value;
		 lc = document.getElementById("terceron").length;
		 ln=lc-1;
		 indice;
		document.getElementById("tercero").text=cc;
		document.getElementById("tercero").value=cc;
	}
}

function vsccosto(va) {//Se unifica el centro de costo con el nombre
	if(va=="0"){
		var cc = document.getElementById("sccostoc").value;
		var lc = document.getElementById("sccostoc").length;
		var ln=lc-1;
		var indice;
		for(i=0;i<=ln;i++){
			if(cc==document.getElementById("sccoston").options[i].value){
			indice=i;
			}
		}
		document.getElementById("sccoston").text=document.getElementById("sccoston").options[indice].text;
		document.getElementById("sccoston").value=cc;
	}
	if(va=="1"){
		 cc = document.getElementById("sccoston").value;
		 lc = document.getElementById("sccoston").length;
		 ln=lc-1;
		 indice;
		document.getElementById("sccostoc").text=cc;
		document.getElementById("sccostoc").value=cc;
	}
	VBases();
}

function VBases() {//Se carga el valor base de la plantilla
	ajax = getXMLObject();
	var contenido = document.getElementById('VBase');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;		
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7"); 

}


function div3() {//Se verifica la plantilla y se montan los otros campos
	//var pd = document.getElementById("Plantilla").value;
	ajax = getXMLObject();
	var contenido = document.getElementById('divtercero');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;		
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8"); 

}




function CrearDoc(pc,ac){//Se Inserta el nuevo Documento
	var td = document.getElementById("tipoDocC").value;
	var fd = document.getElementById("fd").value;
	var nd = document.getElementById("numdoc").value;
	var dd = document.getElementById("detdoc").value;
	var pd = document.getElementById("Plantilla").value;
	
	if(td=="Seleccione"){ alert("Debe seleccionar el tipo de Documento");}
	 else{
		 vfd();//valida la fecha
	  if(fd==""){ alert("Debe seleccionar la fecha del Documento");}
	   else{
		if(nd==""){ alert("Debe Digitar el numero del Documento");}
		 else{
		  if(dd==""){ alert("Debe Diligenciar el campo Detalle");}
		   else{
		   
			   /////////////Revisa si tiene plantilla////////////
			   var sw=0;
			   if(pd!=0){
				 var ter = document.getElementById("tercero").value;
			   	  if(ter=="0"){ alert("Debe seleccionar el Tercero");}
			   	   else{
			   		var teri = document.getElementById("tercero").selectedIndex;
			   		var tern = document.getElementById("tercero").options[teri].text;	
			   		
				    var sd = document.getElementById("sucuc").value;
				    if(sd=="0"){ alert("Debe seleccionar la sucursal");}
				    else{
				    	var sdi = document.getElementById("sucuc").selectedIndex;
				   		var sdn = document.getElementById("sucuc").options[sdi].text;	
				    	
					 var ccd = document.getElementById("ccostoc").value;
					 if(ccd=="0"){ alert("Debe seleccionar el Centro de Costo");}
					  else{
						  
						  var ccdi = document.getElementById("ccostoc").selectedIndex;
					   	  var ccdn = document.getElementById("ccostoc").options[ccdi].text;
					   		
						 var scd = document.getElementById("sccostoc").value;
						 if(scd=="0"){ alert("Debe seleccionar el Subcentro de Costo");}
						 else{
							 var scdi = document.getElementById("sccostoc").selectedIndex;
						   	  var scdn = document.getElementById("sccostoc").options[scdi].text;
							 
						    var bd = document.getElementById("base").value;
						    if(bd==""){ alert("Debe digitar el valor base de la operacion");}
						     else{
						       sw=1;
						     }
						 }
					  }
				    } 
			   	}
			   }else{sw=1;}
			   
			   
			   if(sw==1){

				   ajax = getXMLObject();
					var contenido = document.getElementById('Detalle');
					ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
					// servlet name
					ajax.onreadystatechange = function() {
						if (ajax.readyState == 4) {
							
							contenido.innerHTML = ajax.responseText;
							var docu = document.getElementById('docuh').value;
							 if(pd==0){
								 Totales(0,0,docu,"-1");
							 }else{
								 var ddt = document.getElementById("ddt").value;
								 var dct = document.getElementById("dct").value;
								 var lipla = document.getElementById("lipla").value;

								 Totales(ddt,dct,docu,lipla);
							 }
						}
					}
					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=10&td="+td+"&pc="+pc+"&ac="+ac+"&nd="+nd+"&fd="+fd+"&dd="+dd+"&pd="+pd+"&ter="+ter+"&sd="+sd+"&ccd="+ccd+"&scd="+scd+"&bd="+bd+"&tern="+tern+"&sdn="+sdn+"&ccdn="+ccdn+"&scdn="+scdn); 
					//ajax.send("valor=10");//debe borrarse
					document.getElementById('tdboton').innerHTML="";
					document.getElementById("fd").disabled = true;
					document.getElementById("numdoc").disabled = true;
					if(pd!=0){
					document.getElementById("base").disabled = true;
					}
					document.getElementById("detdoc").disabled = true;
					document.getElementById('enca').disabled = true;
					
					
					
					
					
			   }
			   
		   }
		 }
	   }
	 }
	
}

function limpiacta(idiv) {//Limpia el div de cta si no la selecciona
	 document.getElementById("dcta"+idiv).innerHTML = "";
     document.getElementById("dcta"+idiv).style.display = "none";
}

function limpiater(idiv) {//Limpia el div de cta si no la selecciona
	 document.getElementById("dter"+idiv).innerHTML = "";
     document.getElementById("dter"+idiv).style.display = "none";
}

function lcco(e) {//Generar nueva linea del detalle contable
	//alert(l);
	var su = document.getElementById("suc"+e).value;
	ajax = getXMLObject();
	var contenido = document.getElementById('ncco'+e);
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			//alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12&su="+su+"&id="+e); 

}

function lsco(e) {//Generar nueva linea del detalle contable
	//alert(l);
	var su = document.getElementById("cco"+e).value;
	ajax = getXMLObject();
	var contenido = document.getElementById('nsco'+e);
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			//alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=13&su="+su+"&id="+e); 

}


function Nuevalinea(x,doc) {//Generar nueva linea del detalle contable
	var Datos="";	
	var sw2=0;
	var sw=0;
	var debito=0;
	var credito=0;
	for(e=0;e<=x;e++){
		f=(e+1);
	var cta = document.getElementById("cta"+e).value;
	var ctah = document.getElementById("ctah"+e).value;
	var suc = document.getElementById("suc"+e).value;
	var suci = document.getElementById("suc"+e).selectedIndex;
	var suct = document.getElementById("suc"+e).options[suci].text;	
	var cco = document.getElementById("cco"+e).value;
	var ccoi = document.getElementById("cco"+e).selectedIndex;
	var ccot = document.getElementById("cco"+e).options[ccoi].text;	
	var scc = document.getElementById("scc"+e).value;
	var scci = document.getElementById("scc"+e).selectedIndex;
	var scct = document.getElementById("scc"+e).options[scci].text;	
	var ter = document.getElementById("ter"+e).value;
	var terh = document.getElementById("terh"+e).value;
	var des = document.getElementById("des"+e).value;
	var bas = document.getElementById("bas"+e).value;
	var ref = document.getElementById("ref"+e).value;
	var deb = document.getElementById("deb"+e).value;
	var cre = document.getElementById("cre"+e).value;
	var dif = document.getElementById("dif"+e).value;
	
	if(ref==""){ref="N/A"}
	
	if(ctah==""){alert("Debe seleccionar la Cuenta de la fila "+f);sw2=1;}
	else{
	 if(suc=="0"){alert("Debe seleccionar la Sucursal de la fila "+f);sw2=1;}
	 else{
		 if(cco=="0"){alert("Debe seleccionar el Centro de Costo de la fila "+f);sw2=1;}
		 else{
			 if(terh==""){alert("Debe seleccionar el Tercero de la fila "+f);sw2=1;}
				 else{
					 if(des==""){alert("Debe Digitar la Descripcion de la fila "+f);sw2=1;}
					 else{
						 if(bas==""){alert("Debe Digitar el valor Base de la fila "+f);sw2=1;}
						 else{
							 if(deb==""){alert("Debe Digitar el valor del Debito de la fila "+f);sw2=1;}
							 else{
								 if(cre==""){alert("Debe Digitar el valor del Credito de la fila "+f);sw2=1;}
								 else{
									
									 debito=debito+parseInt(deb);
									 credito=credito+parseInt(cre);
									 
									 Datos=Datos+cta;Datos=Datos+"|"
									 Datos=Datos+ctah;Datos=Datos+"|"
									 Datos=Datos+suc;Datos=Datos+"|"
									 Datos=Datos+suct;Datos=Datos+"|"
									 Datos=Datos+cco;Datos=Datos+"|"
									 Datos=Datos+ccot;Datos=Datos+"|"
									 Datos=Datos+scc;Datos=Datos+"|"
									 Datos=Datos+scct;Datos=Datos+"|"
									 Datos=Datos+ter;Datos=Datos+"|"
									 Datos=Datos+terh;Datos=Datos+"|"
									 Datos=Datos+des;Datos=Datos+"|"
									 Datos=Datos+bas;Datos=Datos+"|"
									 Datos=Datos+ref;Datos=Datos+"|"
									 Datos=Datos+deb;Datos=Datos+"|"
									 Datos=Datos+cre;Datos=Datos+"|"
									 Datos=Datos+dif;Datos=Datos+"|"
									 
									sw=1;
									// alert(Datos);
									// alert(Ndatos);
									 
								 	}
							 	}
						 	}
					 	}
				 	}
				}
			}
		}
		
	}
	
	if((sw==1)&&(sw2==0)){
	 var Ndatos=encodeURIComponent(Datos)
	ajax = getXMLObject();
	var contenido = document.getElementById('linea');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			document.getElementById("cta"+(x+1)).focus();
			document.getElementById("cta"+(x+1)).focus();
			
			Totales(debito,credito,doc,x);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11&l="+x+"&Ndatos="+Ndatos+"&docu="+doc); 
	}

}


function Totales(d,c,a,l) {//Se verifica la plantilla y se montan los otros campos
	ajax = getXMLObject();
	var contenido = document.getElementById('cabecera');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;		
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=14&vdebito="+d+"&vcredito="+c+"&docu="+a+"&ls="+l); 

}



function GuardarDetalle(doc,x,vd,vc) {//Se verifica la plantilla y se montan los otros campos
	
	if(vd==vc){
		var Datos="";	
		var sw2=0;
		var sw=0;
		var debito=0;
		var credito=0;
		for(e=0;e<=x;e++){
			f=(e+1);
		var cta = document.getElementById("cta"+e).value;
		var ctah = document.getElementById("ctah"+e).value;
		var suc = document.getElementById("suc"+e).value;
		var suci = document.getElementById("suc"+e).selectedIndex;
		var suct = document.getElementById("suc"+e).options[suci].text;	
		var cco = document.getElementById("cco"+e).value;
		var ccoi = document.getElementById("cco"+e).selectedIndex;
		var ccot = document.getElementById("cco"+e).options[ccoi].text;	
		var scc = document.getElementById("scc"+e).value;
		var scci = document.getElementById("scc"+e).selectedIndex;
		var scct = document.getElementById("scc"+e).options[scci].text;	
		var ter = document.getElementById("ter"+e).value;
		var terh = document.getElementById("terh"+e).value;
		var des = document.getElementById("des"+e).value;
		var bas = document.getElementById("bas"+e).value;
		var ref = document.getElementById("ref"+e).value;
		var deb = document.getElementById("deb"+e).value;
		var cre = document.getElementById("cre"+e).value;
		var dif = document.getElementById("dif"+e).value;
		
		if(ref==""){ref="N/A"}
		
		if(ctah==""){alert("Debe seleccionar la Cuenta de la fila "+f);sw2=1;}
		else{
		 if(suc=="0"){alert("Debe seleccionar la Sucursal de la fila "+f);sw2=1;}
		 else{
			 if(cco=="0"){alert("Debe seleccionar el Centro de Costo de la fila "+f);sw2=1;}
			 else{
				 if(terh==""){alert("Debe seleccionar el Tercero de la fila "+f);sw2=1;}
					 else{
						 if(des==""){alert("Debe Digitar la Descripcion de la fila "+f);sw2=1;}
						 else{
							 if(bas==""){alert("Debe Digitar el valor Base de la fila "+f);sw2=1;}
							 else{
								 if(deb==""){alert("Debe Digitar el valor del Debito de la fila "+f);sw2=1;}
								 else{
									 if(cre==""){alert("Debe Digitar el valor del Credito de la fila "+f);sw2=1;}
									 else{
										
										 debito=debito+parseInt(deb);
										 credito=credito+parseInt(cre);
										 
										 Datos=Datos+cta;Datos=Datos+"|"
										 Datos=Datos+ctah;Datos=Datos+"|"
										 Datos=Datos+suc;Datos=Datos+"|"
										 Datos=Datos+suct;Datos=Datos+"|"
										 Datos=Datos+cco;Datos=Datos+"|"
										 Datos=Datos+ccot;Datos=Datos+"|"
										 Datos=Datos+scc;Datos=Datos+"|"
										 Datos=Datos+scct;Datos=Datos+"|"
										 Datos=Datos+ter;Datos=Datos+"|"
										 Datos=Datos+terh;Datos=Datos+"|"
										 Datos=Datos+des;Datos=Datos+"|"
										 Datos=Datos+bas;Datos=Datos+"|"
										 Datos=Datos+ref;Datos=Datos+"|"
										 Datos=Datos+deb;Datos=Datos+"|"
										 Datos=Datos+cre;Datos=Datos+"|"
										 Datos=Datos+dif;Datos=Datos+"|"
										 
										sw=1;
										// alert(Datos);
										// alert(Ndatos);
										 
									 	}
								 	}
							 	}
						 	}
					 	}
					}
				}
			}
			
		}
		
		if((sw==1)&&(sw2==0)){
		 var Ndatos=encodeURIComponent(Datos)
		ajax = getXMLObject();
		var contenido = document.getElementById('linea');
		ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
				Periodo();
				ImprimirDocumento(doc);
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=15&l="+x+"&Ndatos="+Ndatos+"&docu="+doc+"&vdebito="+vd+"&vcredito="+vc); 
		}
	}else{
		alert("No puede Guardar un documento con diferencias entre el debito y el credito");
	}
}/////fin guardar detalle


function EliminarDetalle(doc){
	ajax = getXMLObject();
	var contenido = document.getElementById('linea');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			alert(ajax.responseText);
			Periodo();
			//ReporteDocumento(doc);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=16&docu="+doc); 
}



function ConsultarDocCxP(){
	var doc = document.getElementById("numdc").value;
	var ac = document.getElementById("anodc").value;
	var pc = document.getElementById("perdc").value;
	var numfact=document.getElementById("numfact").value;
	
	ajax = getXMLObject();
	var contenido = document.getElementById('consul');
	ajax.open("POST", "ControlDocumentosCxP", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
		//	alert("llegamos"+doc+"-"+ac+"+"+pc);
			contenido.innerHTML = ajax.responseText;	
			//alert(ajax.responseText);
			//Periodo();
			//ImprimirDocumento(doc);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=17&docu="+doc+"&ac="+ac+"&pc="+pc+"&numfact="+numfact); 
	//}
}

function Modificalinea(l) {//Se verifica la plantilla y se montan los otros campos
	confirm("quiere modificar la linea "+(l+1));
}

function ImprimirDocumento(CodDocumento){
	
	pagina="cont_ReporteDocumento.jsp?CodDocumento="+CodDocumento;	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
	
	
}



















//////////Funcion Validar Fechas/////////////

var patronp = new Array(2);
var patrona = new Array(4);

function periodocontable(d, pat, nums, op) {
	
	val = d.value;
	largo = val.length;
	
	if (nums) {
		for (z = 0; z < val.length; z++) {
			if (isNaN(val.charAt(z))) {
				letra = new RegExp(val.charAt(z), "g");
				d.value = val.replace(letra, "");
				val = d.value;
				largo = val.length;
			}
		}
	}
	
	if (d.valant != d.value) {
	if(op==0){
		if(largo>2){
			d.value=val.substring(0, 2);
		}
		
		ini = val.substring(0, 2);
		if ((ini > 13) || (ini == "00")) {
			d.value = "01";
		}
		
		if(largo==2){
			document.getElementById('AC').focus();
		}		
	}else{
		if(largo>4){
			d.value=val.substring(0, 4);
		}	
	}
	
	}
	
	var elEvento =  window.event;  // arguments[0] ||
    var tecla = elEvento.keyCode;
    if(tecla == 13) { 
    	vpc();
    }
}


///////////Funcion Validar Fecha///////////
var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function vfd() {

	var e = document.getElementById("fd").value;
	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha del documento es Invalida");
		document.getElementById("fd").value = "";
		document.getElementById('fd').focus();
		}
	return returnval
}


/////funcion formato de fecha//////////
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio, pc, ac) {
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
		
		if(ini.length==2){
			if(ini!=pc){
				alert("Mes fuera del periodo");
				mescj = val2.substring(0, 2);
				val2 = mescj;
			}
		}
		
		if(ano.length==4){ 
		if(ano!=ac){
			alert("A\u00f1o fuera del periodo");
			mescj = val2.substring(0, 4);
			val2 = mescj;
		}
		}

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
    		
    }
	
}

var emailfilterno = /^[0-9]*$/;
function vnb(c,t) {
	var cc="";
	if(t==1){cc = "bas" + c;}
	if(t==2){cc = "deb" + c;}
	if(t==3){cc = "cre" + c;}
	if(t==4){cc = "base";}
	
	var e = document.getElementById(cc).value;
	var returnval = emailfilterno.test(e)

	if(e.length>1){
	var x = e.substr(0, 1);
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}
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


function exluye(c,t) {
	if(t==1){cc = "deb" + c; dd="cre" + c;}
	if(t==2){cc = "cre" + c; dd="deb" + c;}
	
	var e = document.getElementById(cc).value;
	var f = document.getElementById(dd).value;
	if(e>0){
		document.getElementById(dd).value="0";
	}
}



