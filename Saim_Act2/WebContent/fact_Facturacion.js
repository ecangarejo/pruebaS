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

function ConfirmarDatosF(e,d,cta,nopc){//Seleccionar datos del autocompletar
	//alert("e- "+e+" d- "+d+" cta- "+cta+" nopc="+nopc);
	//alert("ConfirmarDatosF");
	var z=e.split("|");		     	

	if(nopc=="1"){//Buscar pacientes
		document.getElementById(cta).value = z[2];
		document.getElementById(cta).title=z[3]+" "+z[4];
	 
		document.getElementById("Pacienteh").value = z[5];
		document.getElementById("eps")[0].text = z[3];
		document.getElementById("adm").value = z[4];
		document.getElementById("eps")[0].value = z[6];
		document.getElementById("encabezado").value = z[0];
		
		document.getElementById(d).innerHTML = "";
		document.getElementById(d).style.display = "none";
		//cta.focus;
		DetalleFacturacionF(z[4],z[0]);
		//CargarEPS(z[6],z[3],z[4],z[0]);
	}//fin nopc 1
			
	if(nopc=="2"){//Buscar programas y servicios
		
		eliminarLineas();
		document.getElementById(cta).value = z[1]+" "+z[2];

		v10=cta.substring(3);

		document.getElementById("desct"+v10).value = z[2];
		document.getElementById("ref"+v10).value = z[1];
		document.getElementById("descch0").value = z[4];
		document.getElementById("valorpyp0").value = z[8];
		document.getElementById("cant0").value = "1";
		
		document.getElementById(d).innerHTML = "";
		document.getElementById(d).style.display = "none";
		
		document.getElementById('cant0').focus();
		
		//ServiciosyRequerimientos(z[0],z[4],z[1],z[2],z[9],z[10]);//z[9]requiere actoqx, z[10]requiere medico
	}// fin nopc 2



	if(nopc=="3"){//Buscar medicos en urgencias
		alert("medurg");
	/*	document.getElementById(cta).value = z[1];
		//
		
		v10=cta.substring(3);
		//alert(v10);
		document.getElementById("medh"+v10).value = z[0];
		
		document.getElementById(d).innerHTML = "";
		document.getElementById(d).style.display = "none";
		
		serviciosPYP(z[0],z[4],z[2],z[10]);
	*/	
	}//fin nopc 3



	}//fin ConfirmarDatos







function CambiarDatosFact(TipoFact,ValorFact,total,CodEnc,Anticipos,Copagos,Descuentos){
	if(TipoFact=="Entidad"){
		///hacer consulta en la base de datos con los datos que tiene ese encabezado.
		document.getElementById("txtValorEmpresa").value=total;
		document.getElementById("txtEfectivo").value="0";
		document.getElementById("txtSubtotal").value=total;	
		document.getElementById("txtAnticipos").value="0";
		document.getElementById("txtDescuentos").value="0";
		document.getElementById("txtCopago").value="0";
		document.getElementById("txtValorEmpresa").readOnly = true;
		document.getElementById("txtEfectivo").readOnly = true;
		document.getElementById("txtSubtotal").readOnly = true;	
		document.getElementById("txtAnticipos").readOnly = true;
		document.getElementById("txtDescuentos").readOnly = true;
		document.getElementById("txtCopago").readOnly = true;
	}
	if(TipoFact=="Compartido"){
		document.getElementById("txtValorEmpresa").value=total;
		document.getElementById("txtEfectivo").value="0";
		document.getElementById("txtSubtotal").value=total;	
		document.getElementById("txtAnticipos").value=Anticipos;
		document.getElementById("txtDescuentos").value=Descuentos;
		document.getElementById("txtCopago").value=Copagos;
		
		document.getElementById("txtValorEmpresa").readOnly = true;
		document.getElementById("txtEfectivo").readOnly = true;
		document.getElementById("txtSubtotal").readOnly = true;	
		document.getElementById("txtAnticipos").readOnly = false;
		document.getElementById("txtDescuentos").readOnly = false;
		document.getElementById("txtCopago").readOnly = false;
	}
	if(TipoFact=="Usuario"){
		document.getElementById("txtValorEmpresa").value="0";
		document.getElementById("txtEfectivo").value=total;
		document.getElementById("txtSubtotal").value=total;	
		document.getElementById("txtAnticipos").value=Anticipos;
		document.getElementById("txtDescuentos").value=Descuentos;
		document.getElementById("txtCopago").value="0";
		
		document.getElementById("txtValorEmpresa").readOnly = true;
		document.getElementById("txtEfectivo").readOnly = true;
		document.getElementById("txtSubtotal").readOnly = true;
		
		document.getElementById("txtAnticipos").readOnly = false;
		document.getElementById("txtDescuentos").readOnly = false;
		document.getElementById("txtCopago").readOnly = true;
	}	
}

function LiquidarFactura(CodEnc){
	var DireURL="0";
	var dir=document.URL;
	var dcf=dir;
	var y=dcf.split("?").length;
 	var z=dcf.split("?");		     	
 	for(x=0; x<y-1; x=x+1){ 
 		DireURL=z[0];   
    }
	document.getElementById("btnGenerarFact").disabled=true;
	var txtValorEmpresa=document.getElementById("txtValorEmpresa").value;
	var txtEfectivo=document.getElementById("txtEfectivo").value;
	var txtSubtotal=document.getElementById("txtSubtotal").value;	
	var txtAnticipos=document.getElementById("txtAnticipos").value;
	var txtDescuentos=document.getElementById("txtDescuentos").value;
	var txtCopago=document.getElementById("txtCopago").value;
	var txtCodusuario=document.getElementById("txtCodusuario").value;
	var CodEps = document.getElementById('eps').value;
	var CodAdm = document.getElementById('adm').value;
	var radioButtons = document.getElementsByName("radiobutton");	
	var TipoFactura="0";
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			TipoFactura=radioButtons[x].id;
		}
	}
	var subtl ="";
	if(TipoFactura=="Entidad"){
		var valorF=parseInt(txtValorEmpresa)-(parseInt(txtAnticipos)+parseInt(txtCopago)+parseInt(txtDescuentos));
		 subtl = letras(valorF+"");
	}
	if(TipoFactura=="Compartido"){
		valorF=parseInt(txtValorEmpresa)-(parseInt(txtAnticipos)+parseInt(txtCopago)+parseInt(txtDescuentos));
	 subtl = letras(valorF+"");
	}
	if(TipoFactura=="Usuario"){
		valorF=parseInt(txtEfectivo)-(parseInt(txtAnticipos)+parseInt(txtCopago)+parseInt(txtDescuentos));
	 subtl = letras(valorF+"");
	}
	if(TipoFactura=="0"){
		alert("Seleccione Tipo de Liquidacion.");
	}else{
		if(valorF<0){
			alert("El Valor da negativo. "+valorF);
			document.getElementById("btnGenerarFact").disabled=false;
		}else{		
		ajax = getXMLObject();
		ajax.open("POST", "ControlFacturacion", true); 
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var d=ajax.responseText;
				if(d=="F"){
					alert("Ya se Genero una Factura con estos Detalles. Intente Otra Vez");
				}else{
					if(DireURL=="0"){
						window.location.reload();
						ReporteFactura(CodEnc);
					}else{
						window.location.href=DireURL;
						ReporteFactura(CodEnc);
					}
				}
			}
		}
		ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GENEFAC&CodEncFactura="+CodEnc
				+"&txtValorEmpresa="+txtValorEmpresa
				+"&txtEfectivo="+txtEfectivo
				+"&txtAnticipos="+txtAnticipos
				+"&txtDescuentos="+txtDescuentos
				+"&txtCopago="+txtCopago
				+"&TipoFactura="+TipoFactura
				+"&subtl="+subtl
				+"&txtCodusuario="+txtCodusuario
				+"&CodEps="+CodEps
				+"&CodAdm="+CodAdm); // Posting/
		}	
	}
}
function ReporteFactura(CodEnca) {

	var txtCodusuario = document.getElementById("txtCodusuario").value;
	// alert("Entramos a generar pre factxxx "+venc+" user: "+txtCodusuario);
	pagina = "fact_ReporteFactura.jsp?venc=" + CodEnca;
	//pagina = "fact_ReporteFacturaAmbulatoria.jsp?venc=" + CodEnca;
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}

function DeduccirValor(){
	var radioButtons = document.getElementsByName("radiobutton");
	var TipoFactura="0";
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			TipoFactura=radioButtons[x].id;
		}
	}
	if(TipoFactura=="0"){
		alert("Seleccione el Tipo de Facturacion");
	}
	if(TipoFactura=="Entidad"){
	}
	if(TipoFactura=="Compartido"){
		var txtValorEmpresa=document.getElementById('txtValorEmpresa').value;
		var txtEfectivo=document.getElementById('txtEfectivo').value;
		var txtSubtotal=document.getElementById('txtSubtotal').value;
		var txtAnticipos=document.getElementById('txtAnticipos').value;
		var txtDescuentos=document.getElementById('txtDescuentos').value;
		var txtCopago=document.getElementById('txtCopago').value;
		var deducciones=parseInt(txtEfectivo)+parseInt(txtAnticipos)+parseInt(txtDescuentos)+parseInt(txtCopago);
		var Efectivo=parseInt(txtSubtotal)-parseInt(deducciones);
		document.getElementById('txtValorEmpresa').value=Efectivo;
		//alert("deducciones "+deducciones+" Efectivo="+Efectivo);
	}
	if(TipoFactura=="Usuario"){
		 txtValorEmpresa=document.getElementById('txtValorEmpresa').value;
		 txtEfectivo=document.getElementById('txtEfectivo').value;
		 txtSubtotal=document.getElementById('txtSubtotal').value;
		 txtAnticipos=document.getElementById('txtAnticipos').value;
		 txtDescuentos=document.getElementById('txtDescuentos').value;
		 txtCopago=document.getElementById('txtCopago').value;
		 var deduccionesU=parseInt(txtAnticipos)+parseInt(txtDescuentos);
		 var EfectivoU=parseInt(txtSubtotal)-parseInt(deduccionesU);
		 //document.getElementById('txtEfectivo').value=EfectivoU;
	}
	
}

function MoverDetalleFactura(CodDetMov){
	var CodEnc="0";
	var EncActivo = document.getElementById('txtEncActivo').value;
	var NumPrefact = document.getElementById('txtNumPrefacturaAc').value;
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			CodEnc=radioButtons[x].id;
		}
	}
	if(CodEnc=="0"){
		alert("Seleccione la Prefactura a la cual quiere asignar el movimiento.");
	}else{
		//alert("CodEnc="+CodEnc+" CodDetMov="+CodDetMov);
		//if (confirm("Desea Mover este Movimiento?")) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlFacturacion", true); 
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				 GenerarFacturaPrefactura(EncActivo,NumPrefact);
			}
		}
		ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=MDFN&CodDetalleFact="+CodDetMov+"&CodEnc="+CodEnc); // Posting/
		//}else{}
	}
}
function GenerarDatosFactura(CodEnc,ValorFactura,total,TipoRegimen,Anticipos,Copagos,Descuentos){
	var p1="<table width='100%' ><tr  BGCOLOR='#D3D3D3'><td align='center' >GENERACION DE FACTURA</td></tr></table>";
	document.getElementById("EnunciadoMovimientos").innerHTML= (p1);
	
	var p = "";
	if(TipoRegimen=='SubsidiadoS-'){
		///SOLO APLICA HOSPITAL NI�O JESUS.
		p = p+"  <table width='100%' border='1' class='style6' ><tr> " +
		"<td  align='center' colspan='3'><i><input name='radiobutton' type='radio' value='radiobutton'  id='Entidad'    onclick='CambiarDatosFact(&quot;Entidad&quot;,&quot;"+ValorFactura+"&quot;,"+total+","+CodEnc+")' >Entidad</i></td> " +
		
		"</tr></table>";
	}else{
	p = p+"  <table width='100%' border='1' class='style6' ><tr> " +
			"<td  align='center'><i><input name='radiobutton' type='radio' value='radiobutton'  id='Entidad'    onclick='CambiarDatosFact(&quot;Entidad&quot;,&quot;"+ValorFactura+"&quot;,"+total+","+CodEnc+","+Anticipos+","+Copagos+","+Descuentos+")' >Entidad</i></td> " +
			"<td  align='center'><i><input name='radiobutton' type='radio' value='radiobutton'  id='Compartido' onclick='CambiarDatosFact(&quot;Compartido&quot;,&quot;"+ValorFactura+"&quot;,"+total+","+CodEnc+","+Anticipos+","+Copagos+","+Descuentos+")' >Compartido</i></td> " +
			"<td  align='center'><i><input name='radiobutton' type='radio' value='radiobutton'  id='Usuario'    onclick='CambiarDatosFact(&quot;Usuario&quot;,&quot;"+ValorFactura+"&quot;,"+total+","+CodEnc+","+Anticipos+","+Copagos+","+Descuentos+")' >Usuario</i></td> " +
			"</tr></table>";
	}
	p = p+"<table width='100%' border='1' class='style6' ><tr> " +
			"<td>Credito Empresa</td><td><input type='text' id='txtValorEmpresa' ></td>"+
			"<td>Efectivo</td><td><input type='text' id='txtEfectivo'  ></td>"+
			"<td>Subtotal</td><td><input type='text' id='txtSubtotal' ></td></tr> " +
			"<tr> " +
			"<td>Recibido</td><td><input type='text' id='txtAnticipos'  ></td>"+
			"<td>Descuentos</td><td><input type='text' id='txtDescuentos' ></td>"+
			"<td>Copago</td><td><input type='text' id='txtCopago'  ></td></tr>  " +
			"</table> " +
			"<table  width='100%' border='1' ><tr> " +
			"<td align='right' >Valor Movimientos:"+ValorFactura+"</td> " +
					"<td align='center'><input type='button' id='btnGenerarFact' value='Generar Factura' onclick='LiquidarFactura("+CodEnc+")'></td> " +
					"<td align='center' ><input type='button' value='Generar Reporte Prefactura' onclick='GenerarReportePrefactura("+CodEnc+")'></td>"+
					"</tr></table>";
			document.getElementById("ListadoMovimientos").innerHTML = (p)
			
			var p2="<table width='100%' ><tr  BGCOLOR='#D3D3D3'></tr></table>";
			document.getElementById("CrearMovimientos").innerHTML= (p2);
}
function wait(nsegundos) {
	objetivo = (new Date()).getTime() + 1000 * Math.abs(nsegundos);
	while ( (new Date()).getTime() < objetivo );
	};
function CrearPrefactura(CodigoConcat,co,enca){
	var CodEps = document.getElementById('eps').value;
	var CodAdm = document.getElementById('adm').value;
	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true); 
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			DetalleFacturacionF(CodAdm,enca);

		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');	
	//alert("valor=CPF&CodDetalleFact="+CodigoConcat+"&Cant="+co+"&CodAdmision="+CodAdm+"&CodEnt="+CodEps+"&CodEnc="+enca); // Posting/
	ajax.send("valor=CPF&CodDetalleFact="+CodigoConcat+"&Cant="+co+"&CodAdmision="+CodAdm+"&CodEnt="+CodEps+"&CodEnc="+enca); // Posting/
	
}

function GenerarFacturaPrefacturaM(CodEncFactura){
	var p = "";
	p = p
	+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' > " +
			"<td width='10%' align='center'><i>No. Ref.</i></td> " +
			"<td width='50%' align='center'><i>Descripci\xf3n</i></td> " +
			"<td width='10%' align='center'><i>Fecha</i></td> " +
			"<td width='10%' align='center'><i>Cantidad</i></td> " +
			"<td width='10%' align='center'><i>Valor</i></td> " +
			"<td width='7%' align='center'><i><input type='checkbox'  name='all' id='all' onclick='checkAll();'  /></i></td> " +
			"</tr></table>";	
	document.getElementById("EnunciadoMovimientos").innerHTML= (p);
	
	var p1="<table width='100%' ><tr><td align='center' ><input type='button' value='Generar Prefactura' onclick='AsignarPrefactura("+CodEncFactura+")' ></td></tr></table>";
	document.getElementById("CrearMovimientos").innerHTML= (p1);
	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true); 
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//aler(ajax.responseText);
			document.getElementById("ListadoMovimientos").innerHTML = ajax.responseText;

		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2F&enca="+CodEncFactura); // Posting/
	
}


function GenerarFacturaPrefactura(CodEncFactura,NumeroPrefactura){
	var p="<table width='100%' ><tr  BGCOLOR='#D3D3D3'><td align='center' ><input type='hidden' id='txtEncActivo' value="+CodEncFactura+"><input type='hidden' id='txtNumPrefacturaAc' value="+NumeroPrefactura+">GENERACION DE FACTURA, PREFACTURA("+NumeroPrefactura+")</td></tr></table>";
	p=p+"<table width='100%' ><tr BGCOLOR='#D3D3D3'><td width='7%'>No Ref</td><td width='52%'>Descripcion</td><td width='7%'>Fecha</td><td width='7%'>Cantidad</td><td width='7%'>Valor</td><td width='10%'>Subtotal</td><td width='10%' >Accion</td></tr></table>";
	document.getElementById("EnunciadoMovimientos").innerHTML= (p);
	
	var p1="<table width='100%' ><tr  BGCOLOR='#D3D3D3'><td align='center' >-</td></tr></table>";
	document.getElementById("CrearMovimientos").innerHTML= (p1);
	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true); 
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//aler(ajax.responseText);
			document.getElementById("ListadoMovimientos").innerHTML = ajax.responseText;

		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BCPR&CodEncFactura="+CodEncFactura); // Posting/
	
}

function GenerarReportePrefactura(CodEnc){
	
	var txtValorEmpresa=document.getElementById("txtValorEmpresa").value;
	var txtEfectivo=document.getElementById("txtEfectivo").value;
	var txtSubtotal=document.getElementById("txtSubtotal").value;	
	var txtAnticipos=document.getElementById("txtAnticipos").value;
	var txtDescuentos=document.getElementById("txtDescuentos").value;
	var txtCopago=document.getElementById("txtCopago").value;
	var radioButtons = document.getElementsByName("radiobutton");
	
	var TipoFactura="0";
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			TipoFactura=radioButtons[x].id;
		}
	}
	var subtl ="";
	if(TipoFactura=="Entidad"){
		 subtl = letras(txtValorEmpresa+"");
	}
	if(TipoFactura=="Compartido"){
	 subtl = letras(txtValorEmpresa+"");
	}
	if(TipoFactura=="Usuario"){
	 subtl = letras(txtEfectivo+"");
	}
	//alert(subtl);
	if(TipoFactura=="0"){
		alert("Seleccione Tipo de Liquidacion.");
	}else{
		ajax = getXMLObject();
		ajax.open("POST", "ControlFacturacion", true); 
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				//aler(ajax.responseText);
				//document.getElementById("ListadoMovimientos").innerHTML = ajax.responseText;
				pagina = "fact_ReportePreFactura.jsp?venc=" + CodEnc;	
			 	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
				opciones = opciones
						+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
				window.open(pagina, "NUEVA", opciones);
				
			}
		}
		ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GDPREF&CodEncFactura="+CodEnc
				+"&txtValorEmpresa="+txtValorEmpresa
				+"&txtEfectivo="+txtEfectivo
				+"&txtAnticipos="+txtAnticipos
				+"&txtDescuentos="+txtDescuentos
				+"&txtCopago="+txtCopago
				+"&TipoFactura="+TipoFactura
				+"&subtl="+subtl); // Posting/
	
	}
		
}
function CargarResumenPref(CodAdmision,CodEnt){
	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true); 
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//aler(ajax.responseText);
			document.getElementById("FacturasPorGenerar").innerHTML = ajax.responseText;

		}
	}
	ajax.setRequestHeader('Content-Type',
		'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CRPF&CodAdmision="+CodAdmision+"&CodEnt="+CodEnt); // Posting/

}

function AsignarPrefactura(enca){	
    var c=document.getElementById('txtContador').value;
	var oscar=c;
	var CodigoConcat="";
 if(c!=1){
	for(var i=0; i<=c-1; i++){		
      if (form1.chkDetalleFact[i].checked) {
       	 d=form1.chkDetalleFact[i].value;
       	CodigoConcat=d+":"+CodigoConcat+":";
     }     	  
      oscar--; 
     }	
	//alert("CodigoConcat "+CodigoConcat);
	CrearPrefactura(CodigoConcat,"V",enca);
 } 
 else{
	 if(form1.chkDetalleFact.checked){
	 d=form1.chkDetalleFact.value;
	 CrearPrefactura(d,"S",enca);
	}
}
 }//fin de la funcion 

function checkAll() {
	 var i;
  var nodoCheck = document.getElementsByTagName("input");
  var varCheck = document.getElementById("all").checked;
  for (i=0; i<nodoCheck.length; i++){
      if (nodoCheck[i].type == "checkbox" && nodoCheck[i].name != "all" && nodoCheck[i].disabled == false) {
          nodoCheck[i].checked = varCheck;
      }
  } 
}

function DetalleFacturacionF(adm,enca) { // venc, lot,amb
	//alert("DetalleFacturacionF");
	var contenidos = document.getElementById('EnunciadoMovimientos');
	var contenido = document.getElementById('ListadoMovimientos');
	var CodEps = document.getElementById('eps').value;

	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var anchopag = document.body.clientWidth;
			/*var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div class='style11' id='cabecera2'><div style='width:20%; float:left; text-align:right'></div><div style='width:60%; float:left; text-align:center'><a href='#' style='color:#FFFFFF;' onclick='PreFactAdm("+venc+")'>Movimientos Cargados </a></div><div style='width:20%; float:right; text-align:right'><a href='#' style='color:#FFFFFF;' onclick='facturarips("+venc+")'> Verificar Rips</a></div></div></td></tr>";
				p2 = p2
				+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>No. Ref.</div></i></td><td width='43%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='6%'><i><div align='center'>Fecha</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='5%'><i><div align='center'>Valor</div></i></td><td width='7%'><a href='#' onclick='EliminarMCU("+enca+","+venc+", "+lot+", &quot;"+amb+"&quot;)'><i><div align='center'>Anular</div></i></a></td></tr>";
				*/
			var p2 = "";
			p2 = p2
			+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' > " +
					"<td width='10%' align='center'><i>No. Ref.</i></td> " +
					"<td width='50%' align='center'><i>Descripci\xf3n</i></td> " +
					"<td width='10%' align='center'><i>Fecha</i></td> " +
					"<td width='10%' align='center'><i>Cantidad</i></td> " +
					"<td width='10%' align='center'><i>Valor</i></td> " +
					"<td width='7%' align='center'><i><input type='checkbox'  name='all' id='all' onclick='checkAll();'  /></i></td> " +
					"</tr></table>";
			
			contenidos.innerHTML = (p2);
			contenido.innerHTML = ajax.responseText;
			var p3="<table width='100%' ><tr><td align='center' ><input type='button' value='Generar Prefactura' onclick='AsignarPrefactura("+enca+")' ></td></tr></table>";
			document.getElementById("CrearMovimientos").innerHTML= (p3);
					
			CargarResumenPref(adm,CodEps);
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=2F&enca="+enca+"&CodAdm="+adm+"&CodEps="+CodEps);
}//fin DetalleFacturacion








//////////////////NUEVOS CJ///////////////////////
function FFacturar(tipo,cp,np,ce,ne,a,e) {//Imprime el encabezado del cargue de facturar
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlFacturacion", true); 
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			//document.getElementById('desc0').focus();
			if(tipo=='1'){
				document.getElementById('cabecera2').innerHTML = 'Movimiento en Hospitalizacion';
			}		
			if(tipo=='2'){
				document.getElementById('cabecera2').innerHTML = 'Movimiento Ambulatorio';
			}
			if((cp!=null)&&(np!=null)&&(ce!=null)&&(ne!=null)&&(a!=null)&&(e!=null)){
				//alert("Movimientos: "+cp+" - "+np+" - "+ce+" - "+ne+" - "+a+" - "+e);
				
				document.getElementById("Paciente").value = np;
				document.getElementById("Pacienteh").value = cp;
				document.getElementById("eps")[0].text = ne;
				document.getElementById("adm").value = a;
				document.getElementById("eps")[0].value = ce;
				document.getElementById("encabezado").value = e;
				//ConfirmarDatosF
				//CargarEPS(ce,ne,a,e);
				var adm=document.getElementById("adm").value;
				var enc=document.getElementById("encabezado").value;
				DetalleFacturacionF(adm,enc);
			}//fin null
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1F&tipo="+tipo); 
}

function Movimientos(tipo,cp,np,ce,ne,a,e) {//Imprime el encabezado del cargue de movimientos
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlFacturacion", true); 
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			//document.getElementById('desc0').focus();
			if(tipo=='1'){
				document.getElementById('cabecera2').innerHTML = 'Movimiento en Hospitalizacion';
			}		
			if(tipo=='2'){
				document.getElementById('cabecera2').innerHTML = 'Movimiento Ambulatorio';
			}	
			
			if((cp!=null)&&(np!=null)&&(ce!=null)&&(ne!=null)&&(a!=null)&&(e!=null)){
				//alert("Movimientos: "+cp+" - "+np+" - "+ce+" - "+ne+" - "+a+" - "+e);
				
				document.getElementById("Paciente").value = np;
				document.getElementById("Pacienteh").value = cp;
				document.getElementById("eps")[0].text = ne;
				document.getElementById("adm").value = a;
				document.getElementById("eps")[0].value = ce;
				document.getElementById("encabezado").value = e;
				
				CargarEPS(ce,ne,a,e);
			}//fin null
			
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&tipo="+tipo); 
}



function ConfirmarDatos(e,d,cta,nopc,evt){//Seleccionar datos del autocompletar
//alert(e+" - "+d+" - "+cta);
var z=e.split("|");		     	

if(nopc=="1"){//Buscar pacientes
	document.getElementById(cta).value = z[2];
	document.getElementById(cta).title=z[3]+" "+z[4];
 
	document.getElementById("Pacienteh").value = z[5];
	document.getElementById("eps")[0].text = z[3];
	document.getElementById("adm").value = z[4];
	document.getElementById("eps")[0].value = z[6];
	document.getElementById("encabezado").value = z[0];
	
	document.getElementById(d).innerHTML = "";
	document.getElementById(d).style.display = "none";
	//cta.focus;
	if(document.getElementById("nautorizacion")){document.getElementById("nautorizacion").value=z[7];}
	
	CargarEPS(z[6],z[3],z[4],z[0]);
}//fin nopc 1
		
if(nopc=="2"){//Buscar programas y servicios
	
	eliminarLineas();
	document.getElementById(cta).value = z[1]+" "+z[2];

	v10=cta.substring(3);

	document.getElementById("desct"+v10).value = z[2];
	document.getElementById("ref"+v10).value = z[1];
	document.getElementById("descch0").value = z[4];
	document.getElementById("valorpyp0").value = z[8];
	document.getElementById("cant0").value = "1";
	
	document.getElementById(d).innerHTML = "";
	document.getElementById(d).style.display = "none";
	
	document.getElementById('cant0').focus();
	document.getElementById('cant0').select();
	
	ServiciosyRequerimientos(z[0],z[4],z[1],z[2],z[9],z[10]);//z[9]requiere actoqx, z[10]requiere medico
}// fin nopc 2



if(nopc=="3"){//Buscar medicos en urgencias
	//alert("medurg");
	document.getElementById(cta).value = z[1];
	
	
	v10=cta.substring(3);
	document.getElementById("medh"+v10).value = z[0];
	
	document.getElementById(d).innerHTML = "";
	document.getElementById(d).style.display = "none";

var v11=parseInt(v10)+parseInt(1);
		
	var tecla=evt.keyCode;
	//alert("tecla"+tecla);
    if(tecla == 13) { 
    	//alert("bajssssssssssso");
       	if(document.getElementById("cant"+v11)){
    		document.getElementById("cant"+v11).focus();
    		//alert("bajo");
    		document.getElementById("cant"+v11).select();
    	}else{
    		document.getElementById("cargar").focus();
    	}
    }



}//fin nopc 3



}//fin ConfirmarDatos



function eliminarLineas(){
	
	//alert("eliminarLineas"+document.getElementById('new1')+" - "+document.getElementById('cam1'));
	if(document.getElementById('new1')!=null){
		
		while((document.getElementById('new1')!=null)||(document.getElementById('cam1')!=null)){
			document.getElementById('lineasCreacion').childNodes[0].removeChild(document.getElementById('lineasCreacion').childNodes[0].lastChild);
		}
		
	
		//document.getElementById('chkCarg').innerHTML="<div id='carga'  align='center'><a disabled='disabled' id='cargar'>Cargar</a></div>";
		//var enca = document.getElementById("enca").value;
		//Estanciash(enca);
		//document.getElementById('raqx').removeChild(document.getElementById('raqx').lastChild);
		//document.getElementById("pop").value = "1";
	
	
	if(document.getElementById("chkCarg")!=null){
		//alert(document.getElementById("chkCarg")+" - "+document.getElementById("raqx0")+" - "+document.getElementById("rmed0")+" - "+document.getElementById("rporc0"));
		//document.getElementById('raqx0').removeChild(document.getElementById('raqx0').lastChild);
		//document.getElementById('rmed0').removeChild(document.getElementById('rmed0').lastChild);
		//document.getElementById('rporc0').removeChild(document.getElementById('rporc0').lastChild);
		var element = document.getElementById("chkCarg");
		var element2 = document.getElementById("tdultimo");
		
		var uno = document.getElementById('raqx0');
		var dos = document.getElementById('rmed0');
		var tres = document.getElementById('rporc0');
		var cuatro = document.getElementById('cargarc0');
		var cinco = document.getElementById('all');
		var seis = document.getElementById('pos');
		
		element.removeChild(uno);
		element.removeChild(dos);
		element.removeChild(tres);
		element.removeChild(cuatro);
		element2.removeChild(cinco);
		element.removeChild(seis);
		
		document.getElementById('j').value='1';
		//element.parentNode.removeChild("raqx0");
	    //element.parentNode.removeChild("rmed0");
	    //element.parentNode.removeChild("rporc0");
		}
	
	
	}
}


function CargarEPS(ceps,eps,adm,enca){
	
	var contenido = document.getElementById('deps');
	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true); // getname will be the
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			 //Detalle(venc,ceps);
			DetalleFacturacion(adm,enca)
		}
	}
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CargarEPS&codeps="+ceps+"&eps="+eps); // Posting
}


function CambiardeEntidad(ceps,eps){
		
	var indice =  document.getElementById("eps").selectedIndex;
	var cepsn = document.getElementById('eps').options[indice].value;
	var epsn = document.getElementById('eps').options[indice].text;
	var adm = document.getElementById('adm').value;
	var enca = document.getElementById('encabezado').value;
	
	if (confirm("Desea crear una factura con otra entidad?")) {
		//alert("Nueva entidad: "+cepsn+" - "+epsn+" mi anterior eps era:"+ceps+" - "+eps+" admision:"+adm);
		//document.getElementById('ListadoMovimientos').innerHTML="";
		ajax = getXMLObject();
		ajax.open("POST", "ControlFacturacion", true); 
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			// contenido.innerHTML = ajax.responseText;
				var en=ajax.responseText;
				//alert(en);
				CargarEPS(cepsn,epsn,adm,en);
			}
		}
		ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CambiarEPS&codneps="+cepsn+"&eps="+epsn+"&adm="+adm+"&codeps="+ceps); // Posting/
	}else{
		document.getElementById('eps').value=ceps;
		document.getElementById('eps').text=eps;
	}
}//fin CambiardeEntidad


function DetalleFacturacion(adm,enca,fecha) { // venc, lot,amb
	
	var contenidos = document.getElementById('EnunciadoMovimientos');
	var contenido = document.getElementById('ListadoMovimientos');

	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			var anchopag = document.body.clientWidth;
			/*var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div class='style11' id='cabecera2'><div style='width:20%; float:left; text-align:right'></div><div style='width:60%; float:left; text-align:center'><a href='#' style='color:#FFFFFF;' onclick='PreFactAdm("+venc+")'>Movimientos Cargados </a></div><div style='width:20%; float:right; text-align:right'><a href='#' style='color:#FFFFFF;' onclick='facturarips("+venc+")'> Verificar Rips</a></div></div></td></tr>";
				p2 = p2
				+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='10%'><i><div align='center'>No. Ref.</div></i></td><td width='50%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='10%'><i><div align='center'>Fecha</div></i></td><td width='10%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='10%'><i><div align='center'>Valor</div></i></td><td width='10%'><a href='#' onclick='EliminarMCU("+enca+","+venc+", "+lot+", &quot;"+amb+"&quot;)'><i><div align='center'>Anular</div></i></a></td></tr>";
				*/
			var p2 = "<table width='100%' class='style6' ><tr><td colspan='5'><div class='style11' id='cabecera2'><div style='width:20%; float:left; text-align:right'></div><div style='width:60%; float:left; text-align:center'><a href='#' style='color:#FFFFFF;' onclick='PreFactAdm("+adm+")'>Movimientos Cargados </a></div><div style='width:20%; float:right; text-align:right'><a href='#' style='color:#FFFFFF;' onclick='facturarips("+adm+")'> Verificar Rips</a></div></div></td></tr>";
			p2 = p2
			+ "  <table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='5%'><i><div align='center'>No. Ref.</div></i></td><td width='43%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='6%'><i><div align='center'>Fecha</div></i></td><td width='5%'><i><div align='center'>Cantidad</div></i></td></i></td><td width='5%'><i><div align='center'>Valor</div></i></td><td width='7%'><i><div align='center'>Anular</div></i></td></tr>";
			
			contenidos.innerHTML = (p2);
			contenido.innerHTML = ajax.responseText;
					
			CrearMovimientos(enca,fecha);
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=2&enca="+enca);
}//fin DetalleFacturacion


function PreFactAdm(venc) {
	//alert(venc);
 	if(confirm(" Desea ver el reporte agrupado por usuario?")){
 		pagina = "fact_ReportePreFacturaAdm.jsp?venc=" + venc;
	}else {
		pagina = "fact_ReportePreFacturaAdm2.jsp?venc=" + venc;
	}
 	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}//fin PreFactAdm

function facturarips(venc) {
	var codp = document.getElementById("Pacienteh").value;
 	pagina = "fact_VerificaRIPS.jsp?venc=" + venc + "&codp="+ codp;
	
	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=900, height=300, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
	
}//fin facturarips

function VR1(adm,codp){/***ESTE NO FUNCIONA AUN NO LO HGE REVISADO*/
	var usu = document.getElementById("txtCodusuario").value;
	 contenido = document.getElementById('Opcion');
	
		ajax = getXMLObject();
		ajax.open("POST", "ControlFacturacion", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			 contenido.innerHTML = ajax.responseText
			//	Detalle(venc, lot,opcion)
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=VR1&adm0="+adm+"&xx="+codp+"&lot="+usu);
}//fin VR1


function EliminarCargue(denca) {
	var fecha=document.getElementById('fechamo').value;
	var enca = document.getElementById('encabezado').value;
	var adm = document.getElementById('adm').value;
	if (confirm("Desea Eliminar el Cargue!!!")) {
		ajax = getXMLObject();
		ajax.open("POST", "ControlFacturacion", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
				DetalleFacturacion(adm,enca,fecha);
			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=3&denca=" + denca);
	}
}//fin EliminarCargue


function CrearMovimientos(enca,fecha) {
	if(fecha==undefined){fecha="";}
	var contenido = document.getElementById('CrearMovimientos');
	var ceps = document.getElementById("eps").value;

	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			if(fecha!=""){
				document.getElementById('fechamo').value=fecha;
				document.getElementById("new0").focus();
				document.getElementById("new0").focus();
			}
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=4&enca=" + enca+"&codeps="+ceps);
	//document.getElementById("desct0").focus();
	//document.getElementById("desct0").focus();
	//document.getElementById("desct"+v10)
}


function Estancias() {// Consultar la estancia del paciente
	
	var enca = document.getElementById('encabezado').value;
	
	var fechafilter = /^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
	var cc = "fechamo";
	var e = document.getElementById(cc).value;

	var returnval = fechafilter.test(e)
	if (returnval == false) {
		alert("La fecha es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	} else {
		ajax = getXMLObject();
		ajax.open("POST", "ControlFacturacion", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
			
				var respuesta = ajax.responseText;
				if (respuesta != "1") {
					alert(respuesta);
					document.getElementById(cc).value = "";
					document.getElementById(cc).focus();
				}

			}
		}
		ajax.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded; charset=UTF-8");
		ajax.send("valor=5&enca=" + enca + "&fechacargue=" + e);

	}
}


function CargarActoqx() {//Busca los actos quirurgicos segun el tipo de acto

	var taq = document.getElementById("taq").value;
	var eps = document.getElementById("eps").value;
	
	var contenido = document.getElementById('divaqx');

	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=6&taq=" + taq+ "&eps=" + eps);

}

function ActivarCampos(){
	if(document.getElementById('fechamo').value==""){
		document.getElementById('fechamo').focus();
	}else{
	document.getElementById('taq').disabled = false;
	document.getElementById('aq').disabled = false;
	document.getElementById('med0').disabled = false;
	}
}

function ServiciosyRequerimientos(pos,cpos,refpos,descpos,ract,rmed) {
	
	refpos =  encodeURIComponent(refpos);
	descpos = encodeURIComponent(descpos);
	
	var contenidos = document.getElementById('CrearMovimientos');
	var contenido = document.getElementById('dserv0');
	
	var enca = document.getElementById('encabezado').value;
	var codeps = document.getElementById("eps").value;
	var taqi = document.getElementById("taq").selectedIndex;
	var taq = document.getElementById("taq").options[taqi].text;
	var vtaq = document.getElementById("taq").options[taqi].value;
	var aqi = document.getElementById("aq").selectedIndex;
	var aq = document.getElementById("aq").options[aqi].text;
	var vaq = document.getElementById("aq").options[aqi].value;
	var fechacargue = document.getElementById('fechamo').value;
	
	

	
	if (ract == "0"){
		document.getElementById('taq').value='Seleccione';
		document.getElementById('taq').text='Seleccione';
		document.getElementById('aq').value='Seleccione';
		document.getElementById('aq').text='Seleccione';
		document.getElementById('taq').disabled = true;
		document.getElementById('aq').disabled = true;
	}
	if (rmed == "0"){
		document.getElementById('med0').value= "No es requerido";
		document.getElementById('med0').disabled = true;
	}
		
	
	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			
			var respCont = ajax.responseText.split("|");
			//contenidos.innerHTML = ajax.responseText;
			if (respCont[1]=='lt2' || respCont[1]=='hemodinamia'){
				contenidos.innerHTML = respCont[0];//cambiamos todo el div de crear movimientos
				document.getElementById('cant0').focus();						
			}else{//fin lt2 o hemodinamia
				if(respCont[1]=='lt1'){
					contenido.innerHTML = respCont[0];//escribimos solo en el div de servicio
					BuscarMedico();
				}
			}
			
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=7&pos="+pos+"&cpos="+cpos+"&refpos="+refpos+"&descpos="+descpos+"&ract="+ract+"&rmed="+rmed+"&enca="+enca+"&codeps="+codeps+"&taq="+taq+"&vtaq="+vtaq+"&aq="+aq+"&vaq="+vaq+"&fechacargue="+fechacargue);
}	



function BuscarMedico(){
	
	var rmed = document.getElementById("rmed0").value;
	if(rmed=="1"){
	var contenido = document.getElementById('dmed0');

	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
			document.getElementById('cant0').focus();
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=8");
	}
}


function Checkear() {
	
	var checkboxes = document.getElementById("form1").checkbox;
	if(document.getElementById('cargarc0')!=null){
		if(checkboxes.length!=undefined){
			var verifica=document.getElementById("all");
			for ( var x = 0; x < checkboxes.length; x++) {
				if(verifica.checked == 1){	checkboxes[x].checked = 1;}
				if(verifica.checked == 0){	checkboxes[x].checked = 0;}
			}
		}
	}
}


function CargarMovimientos(enca) {
	
	var contenido = document.getElementById('creacion');
	
	var adm = document.getElementById("adm").value;
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var fechamo = document.getElementById('fechamo').value;
	var j = document.getElementById("j").value;
	var movimientos="";
	
	var sw=0;
	var sw2=0;
	var sw3=0;
	var raqx="";
	var aq="";
	var rmed0="";
	var medh0="";
	var pos="";
	var rporc0="";
	var descch0 ="";
	var ref0 ="";
	var desct0 ="";
	var servh0 ="";
	var serv0 ="";
	var cant0 ="";
	var valorpyp0 ="";
	
	if(fechamo==""){
		alert("Debe seleccionar la fecha del movimiento que desea cargar");
	}else{
	
		if(j==1){
			
			descch0 = document.getElementById("descch0").value;
			servh0 = document.getElementById("serv0").value;
			cant0 = document.getElementById("cant0").value;
			valorpyp0 = document.getElementById("valorpyp0").value;
			
			//alert(descch0+" - "+servh0+" - "+cant0+" - "+valorpyp0);
			if(descch0=="" || servh0=="" || cant0=="" || cant0=="0" || valorpyp0==""){
				alert("Debe digitar todos los campos requeridos!!!");
				sw=1;
			}else{
			
			raqx = document.getElementById('raqx0').value;
			aq="Seleccione";
			if(raqx=="1"){
				aq = document.getElementById("aq").value;
				if(aq=="Seleccione"){alert("El acto quirurgico es requerido para este programa!!!");sw=1;}
			}
			
			rmed0 = document.getElementById('rmed0').value;
			medh0="0";
			if(rmed0=="1"){
				medh0 = document.getElementById("medh0").value;
				if(medh0==""){alert("El medico es requerido para este programa!!!");sw=1;}
			}
			
			pos = document.getElementById('pos').value;
			
			rporc0="0";
					
			ref0 = document.getElementById("ref0").value;
			desct0 = document.getElementById("desct0").value;
			serv0 = document.getElementById("serv0")[0].text;
			
			
			movimientos=movimientos+"|"+pos+"|"+descch0+"|"+ref0+"|"+desct0+"|"+servh0+"|"+serv0+"|"+fechamo+"|"+cant0+"|"+valorpyp0+"|"+txtCodusuario+"|"+enca+"|"+medh0+"|"+aq+"|"+rporc0;
			
		////////////////////////////////////////
			}
			
		}else{//si j>1 es decir servicios o hemodinamia
			
			var checkboxes = document.getElementById("form1").checkbox;
			var contadorcheck=0;
			
			for(i=0;i<j;i++){
				
				if (checkboxes[i].checked) {
					
					descch0 = document.getElementById("descch"+i).value;
					servh0 = document.getElementById("serv"+i).value;
					cant0 = document.getElementById("cant"+i).value;
					valorpyp0 = document.getElementById("valorpyp"+i).value;
					
					//alert(descch0+" - "+servh0+" - "+cant0+" - "+valorpyp0);
					if(descch0=="" || servh0=="" || cant0=="" || cant0=="0" || valorpyp0==""){
						alert("Debe digitar todos los campos requeridos!!!");
						sw=1; 
						i=j;
					}else{
						
						//raqx = document.getElementById('raqx'+i).value;
						raqx = "1";
						//aq="Seleccione";
						rporc0="0";
						//if(raqx=="1"){
							aq = document.getElementById("aq").value;
							rporc0 = document.getElementById('rporc'+i).value;
							if(aq=="Seleccione"){sw2=1;}	
						//}
						
						rmed0 = document.getElementById('rmed'+i).value;
						medh0="0";
						if(rmed0=="1"){
							medh0 = document.getElementById("medh"+i).value;
							if(medh0==""){sw3=1;}		
						}
						
						pos = document.getElementById('pos').value;
						pos="2";
						descch0 = document.getElementById("descch"+i).value;
						ref0 = document.getElementById("ref"+i).value;
						desct0 = document.getElementById("desct"+i).value;
						servh0 = document.getElementById("serv"+i).value;
						serv0 = document.getElementById("serv"+i)[0].text;
						cant0 = document.getElementById("cant"+i).value;
						valorpyp0 = document.getElementById("valorpyp"+i).value;
						
						movimientos=movimientos+"|"+pos+"|"+descch0+"|"+ref0+"|"+desct0+"|"+servh0+"|"+serv0+"|"+fechamo+"|"+cant0+"|"+valorpyp0+"|"+txtCodusuario+"|"+enca+"|"+medh0+"|"+aq+"|"+rporc0;
						
						contadorcheck++;
					}//fin requeridos
				}//fin checked
			}//fin for j
			

		}//fin si j>1 servicios o hemodinamia
		
		movimientos=movimientos+"|";
		movimientos=encodeURIComponent(movimientos);
		
		if(sw2==1){alert("El acto quirurgico es requerido para uno o mas programas!!!");sw=1;}
		if(sw3==1){alert("El medico es requerido para uno o mas programas!!!");sw=1;}
		if(contadorcheck==0){alert("Debe seleccionar al menos un programa a cargar!!!");sw=1;}
			/////////////////////////////////////////
		if(sw==0){
			var x1 = confirm("Desea Cargar el Movimiento?");
			if (x1) {
				ajax = getXMLObject();
				ajax.open("POST", "ControlFacturacion", true);
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						//contenido.innerHTML = ajax.responseText;
						DetalleFacturacion(adm,enca,fechamo);
						//document.getElementById('new0').focus();
						//document.getElementById('new0').focus();
					}
				}
				ajax.setRequestHeader("Content-type",
						"application/x-www-form-urlencoded; charset=UTF-8");
				ajax.send("valor=9&movimientos="+movimientos);
			}//fin comfirm
		}//fin sw=0
	}//fin si fecha==""
}//fin CargarMovimientos

function volveramovimientos(){
	//alert("volveramovimientos");
	var np=document.getElementById("Paciente").value;
	var cp=document.getElementById("Pacienteh").value;
	var ne=document.getElementById("eps")[0].text;
	var a=document.getElementById("adm").value;
	var ce=document.getElementById("eps")[0].value;
	var e=document.getElementById("encabezado").value;
	
	var tipomovimiento=document.getElementById("tipomovimiento").value;
	//alert("volveramovimientos: "+cp+" - "+np+" - "+ce+" - "+ne+" - "+a+" - "+e);
	
	if(tipomovimiento=="0"){window.location.href="fact_MUrgencias.jsp?cp="+cp+"&np='"+np+"'&ce="+ce+"&ne='"+ne+"'&a="+a+"&e="+e;}
	if(tipomovimiento=="1"){window.location.href="fact_MHospitalizacion.jsp?cp="+cp+"&np='"+np+"'&ce="+ce+"&ne='"+ne+"'&a="+a+"&e="+e;}
	if(tipomovimiento=="2"){window.location.href="fact_MAmbulatorio.jsp?cp="+cp+"&np='"+np+"'&ce="+ce+"&ne='"+ne+"'&a="+a+"&e="+e;}
	
}


function enviarafacturar(){
	//alert("enviarafacturar");
	var np=document.getElementById("Paciente").value;
	var cp=document.getElementById("Pacienteh").value;
	var ne=document.getElementById("eps")[0].text;
	var a=document.getElementById("adm").value;
	var ce=document.getElementById("eps")[0].value;
	var e=document.getElementById("encabezado").value;
	
	var tipomovimiento=document.getElementById("tipomovimiento").value;
	//alert("enviarafacturar: "+cp+" - "+np+" - "+ce+" - "+ne+" - "+a+" - "+e);
	
	if(tipomovimiento=="0"){window.location.href="fact_FUrgencias.jsp?cp="+cp+"&np='"+np+"'&ce="+ce+"&ne='"+ne+"'&a="+a+"&e="+e;}
	if(tipomovimiento=="1"){window.location.href="fact_FHospitalizacion.jsp?cp="+cp+"&np='"+np+"'&ce="+ce+"&ne='"+ne+"'&a="+a+"&e="+e;}
	if(tipomovimiento=="2"){window.location.href="fact_FAmbulatorio.jsp?cp="+cp+"&np='"+np+"'&ce="+ce+"&ne='"+ne+"'&a="+a+"&e="+e;}
	
}



function ActualizaAutorizacion() {//Busca los actos quirurgicos segun el tipo de acto

	var e=document.getElementById("encabezado").value;
	var na=document.getElementById("nautorizacion").value;
	
	ajax = getXMLObject();
	ajax.open("POST", "ControlFacturacion", true);
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//contenido.innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded; charset=UTF-8");
	ajax.send("valor=10&enca=" + e+ "&nautoriza=" + na);

}

var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio,evt) {//funcion para revisar la fecha
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

		//
		 // /////////////////////////////////////////////////// //Validar fecha
		 // mayor que la fecha actual
		 // 
		 // if(ano.length==4){ if (ano==annio) { if(ini==mes){
		 // if((dia==dias)||(dia<dias)){ alert("La fecha de Vencimiento no puede
		 // ser menor o igual a la fecha Actual"); val2=''; } }else{ if(ini<mes){
		// alert("La fecha de Vencimiento no puede ser menor o igual a la fecha
		// Actual"); val2=''; } } } if (ano<annio) { alert("La fecha de
		 // Vencimiento no puede ser menor o igual a la fecha Actual"); val2=''; } }
		 // 
		 // ///////////////////////////////////////////////////
		///
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
	
	var tecla=evt.keyCode;
	
	//var elEvento =  window.event;  // arguments[0] ||
   // var tecla = elEvento.keyCode;
    if(tecla == 13) { 
  		document.getElementById("new0").focus();
    		
    }
	
}




var emailfilternot = /^[0-9]*$/;
function checknumcantidad(c,evt,rm) {
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
		
		//alert("Este campo acepta solo valores n�mericos!!!");
		document.getElementById(cc).value = xx;
		document.getElementById(cc).focus();
	}

	var tecla=evt.keyCode;
    if(tecla == 13) { 
    	
    //	alert("rm: "+rm+" medico: "+document.getElementById("med"+rm).value);
  	//	document.getElementById("new0").focus();
    	
    	if(document.getElementById("med"+rm).value==""){
    		document.getElementById("med"+rm).focus();
		}else{
    	   	var rm1=rm+1;
    	   	if(document.getElementById("med"+rm1)){
    	   		document.getElementById("cant"+rm1).focus();
    	   	}else{
    	   		document.getElementById("cargar").focus();
    	   	}
		}
    		
    }


	return returnval
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






















		


