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
var patron = new Array(2,2,4);
var patron2 = new Array(1,3,3,3,3)
function masca(d,sep,pat,nums){
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

function fechaFija(){
	  var time1 = new Date()
	  var anio = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((dia < 10) ? "0" : "") + dia
	  temp1 += ((mes < 10) ? "/0" : "/") + mes
	  temp1 += ((anio < 10) ? "0" : "/") + anio 
	  document.getElementById('txtFechaFija').value = temp1
	  id = setTimeout("fechaFija()",1000)
	  }

function fecha(){
	  var time1 = new Date()
	  var anio = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  var dia = time1.getDate()
	  var temp1 = "" + ((anio < 10) ? "0" : "") + anio
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	  document.getElementById('txtFecha').value = temp1
	  id = setTimeout("fecha()",1000)
	  fechaFija();
	  }

function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("hora()",1000)
	  }
function SoloNUm(){ 
	var s=document.getElementById("txtValorNota").value;
	var n=0;
	var i;
	for(i=0;i<s.length;i++){
	   valor = parseInt(s.charAt(i));
		if(isNaN(valor)){
			n++;
		}
	}
	if(n>0){
		window.alert('El Campo Solo Acepta Valores Numericos...!');
		document.getElementById("txtValorNota").value="";
		document.getElementById("txtValorNota").focus();
	}
	return false;
}

function ReporteNotaDB(CodNota){
	pagina="cont_ReporteNotaDB.jsp?CodNota="+CodNota;	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}


function GuardarND(CodFactura){
	var CodUsu=document.getElementById("txtCodigoUsuario").value;
	var ConceptoND=document.getElementById("txtConceptoND").value;
	var ValorNota=document.getElementById("txtValorNota").value;
	if(ConceptoND==""){
		alert("Escriba el Concepto");
	}else{
		if(ValorNota==""){
			alert("Digite Valor Nota");
		}else{
			ajax=getXMLObject();
			ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					window.location.reload();
					//alert(ajax.responseText);			
					//Contenido.innerHTML=ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			//alert("valor=GNDB&CodFacturaND="+CodFactura+"&CodUsu="+CodUsu+"&ConceptoND="+ConceptoND+"&ValorNota="+ValorNota);
			ajax.send("valor=GNDB&CodFacturaND="+CodFactura+"&CodUsu="+CodUsu+"&ConceptoND="+encodeURIComponent(ConceptoND)+"&ValorNota="+ValorNota);
		}
	}
}

function AutoCompletaFact(){
	var Factura=document.getElementById("txtFactura").value;
	var CodEntidad=document.getElementById("cmbEntidad").value;
	var Contenido=document.getElementById("autoCompletaFactu");
	var BusAgru="";
	if(document.getElementById("agrupado").checked){BusAgru="1";}else{BusAgru="0";}
	//alert("Factura "+Factura+" CodEntidad "+CodEntidad);
	//alert("BusAgru "+BusAgru);
	if(CodEntidad!="Seleccione"){
		
		ajax=getXMLObject();
		ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
			    //alert(ajax.responseText);
				
				Contenido.innerHTML=ajax.responseText;
				var Contador=document.getElementById("txtConta").value;			
				if(Contador==1){
					var NF=document.getElementById("txtNF1").value;
					var VF=document.getElementById("txtVF1").value;
					var CF=document.getElementById("txtCF1").value;
					var AB=document.getElementById("txtAB1").value;
					//alert("Contador "+Contador);
					document.getElementById("txtConta").value="0";
					
					document.getElementById("txtFactura").value=NF;
					document.getElementById("txtCodFactura").value=CF;					
					document.getElementById("autoCompletaFactu").innerHTML="";
					
					document.getElementById("txtAbono").value=AB;
					document.getElementById("txtSaldo").value=AB;
					document.getElementById("btnIngresar").focus();
					//SeleccionFactura(NF,VF,CF)
					
					
				}
				
				//alert(ajax.responseText);
				//window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=auf&Factura="+Factura+"&CodEntidad="+CodEntidad+"&BusAgru="+BusAgru); //Posting txtname to Servle
	}else{
		alert("Seleccione la Entidad");
	}	
}

function SeleccionFactura(NumFactura,ValorFactura,CodigoFactura){
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("txtFactura").value=NumFactura;
			document.getElementById("txtCodFactura").value=CodigoFactura;
			document.getElementById("txtSaldo").value=ajax.responseText;
			document.getElementById("autoCompletaFactu").innerHTML="";
			document.getElementById("txtAbono").value=ajax.responseText;
			document.getElementById("btnIngresar").focus();	
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=slf&Factura="+NumFactura); //Posting txtname to Servle
	
}

function SinTerminar(){
	var CodUsu=document.getElementById("txtCodusuario").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			var resu=ajax.responseText;
			if(resu!=""){
				var y=resu.split("|").length;
			 	var z=resu.split("|");		     	
			 	for(x=0; x<y-1; x=x+1)
			 	{ 
			    CodRC=z[0];
			    Concepto=z[1];
			    Entidad=z[2];
			    Observacion=z[3];
			    FechaRc=z[4];
			    CodEnt=z[5];
			    }
			 	
			 	var yy=FechaRc.split("-").length;
			 	var zz=FechaRc.split("-");	
			 	for(xx=0; xx<yy-1; xx=xx+1)
			 	{ 
			    An=zz[0];
			    Me=zz[1];
			    Di=zz[2];
			    }
			 	FechaP=Di+"/"+Me+"/"+An;
				document.getElementById("txtCodReciboCaja").value=CodRC;
				document.getElementById("txtFechaPago").value=FechaP;
				
				indice1=document.getElementById("cmbEntidad").selectedIndex;
				document.getElementById("cmbEntidad").options[indice1].text=Entidad;
				document.getElementById("cmbEntidad").value=CodEnt;
				
				indice=document.getElementById("cmbConcepto").selectedIndex;
				document.getElementById("cmbConcepto").options[indice].text=Concepto;
				
				document.getElementById("txtObservacionRC").value=Observacion;
				LlenarReciboCaja(CodRC);
			}else{
				document.getElementById("txtCodReciboCaja").value='';
				
				indice1=document.getElementById("cmbEntidad").selectedIndex;
				document.getElementById("cmbEntidad").options[indice1].text="Seleccione";
				document.getElementById("cmbEntidad").value="Seleccione";
				
				indice=document.getElementById("cmbConcepto").selectedIndex;
				document.getElementById("cmbConcepto").options[indice].text="Seleccione";
				document.getElementById("cmbConcepto").value="Seleccione";
				
				document.getElementById("txtObservacionRC").value='';
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ST&CodUsu="+CodUsu); //Posting txtname to Servle
}

function LlenarReciboCaja(CodRC){
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			document.getElementById("DocAfectados").innerHTML=ajax.responseText;
			Deducciones();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=LLRC&CodReciboCaja="+CodRC);

}

function Deducciones(){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("Dedu").innerHTML=ajax.responseText;			
			ResumenReciboCaja();
			}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=DEDUC&CodReciboCaja="+CodReciboCaja); //Posting txtname to Servle

}
function GuardarReciboCajaFactu(){
	var CodUsu=document.getElementById("txtCodusuario").value;
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	var CodFactura=document.getElementById("txtCodFactura").value;
	var NumFactura=document.getElementById("txtFactura").value;
	var Saldo=document.getElementById("txtSaldo").value;
	var CodEntidad=document.getElementById("cmbEntidad").value;
	var Concepto=document.getElementById("cmbConcepto").value;
	var Tipo_Pago=document.getElementById("cmbFormaPago").value;
	var Abono=document.getElementById("txtAbono").value;
	var FechaPago=document.getElementById("txtFechaPago").value;
	var ObservacionRC=document.getElementById("txtObservacionRC").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true);
	var y=FechaPago.split("/").length;
 	var z=FechaPago.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    Dia=z[0];
    Mes=z[1];
    Anio=z[2];
    // form1.cbArea.value();
    }
 	FechaPago=Anio+"-"+Mes+"-"+Dia
 //	alert("1");
	if(CodReciboCaja==""){	
	//	alert("2");
	//	if(Tipo_Pago=="Seleccione"){
			Tipo_Pago="0";
	//		alert("3");
		//}else{
			if(Abono==""){
				alert("Digite el Abono.");
			}else{
				if(parseInt(Abono) > parseInt(Saldo)){
					alert("El Abono Es Mayor al Saldo.");
				}else{
				//alert("4");
		     	//alert(FechaPago);
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {	
					//	alert(ajax.responseText);
						var ex=ajax.responseText
						if(ex=="1"){
							alert("Ya Existe Esta Factura En el Recibo de Caja");
							document.getElementById("txtFactura").value="";
							document.getElementById("txtCodFactura").value="";
							document.getElementById("txtSaldo").value="";
							document.getElementById("txtAbono").value="";
							document.getElementById("txtFactura").focus();
						}else{
							document.getElementById("DocAfectados").innerHTML=ajax.responseText;
							ResumenReciboCaja();
						}
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=grc&Factura="+NumFactura+"&CodUsu="+CodUsu+"&CodEntidad="+CodEntidad+
						"&Concepto="+Concepto+"&Tipo_Pago="+Tipo_Pago+"&CodFactura="+CodFactura+
						"&valor_Factura="+Saldo+"&Abono="+Abono+"&FechaPago="+FechaPago+"&ObservacionRC="+ObservacionRC); //Posting txtname to Servle
			}
			
		}	
	
	}else{
		if(parseInt(Abono)>parseInt(Saldo)){
			alert("El Abono Es Mayor al Saldo.");
		}else{
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {		
					var ex=ajax.responseText
					if(ex=="1"){
						alert("Ya Existe Esta Factura En el Recibo de Caja");
						document.getElementById("txtFactura").value="";
						document.getElementById("txtCodFactura").value="";
						document.getElementById("txtSaldo").value="";
						document.getElementById("txtAbono").value="";
						document.getElementById("txtFactura").focus();
					}else{
						document.getElementById("DocAfectados").innerHTML=ajax.responseText;
						ResumenReciboCaja();
					}
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=grc1&Factura="+NumFactura+"&CodUsu="+CodUsu+"&CodEntidad="+CodEntidad+
					"&Concepto="+Concepto+"&Tipo_Pago="+Tipo_Pago+"&CodFactura="+CodFactura+
					"&valor_Factura="+Saldo+"&Abono="+Abono+"&CodReciboCaja="+CodReciboCaja+"&ObservacionRC="+ObservacionRC+"&FechaPago="+FechaPago);
		}
	}	
}

function OmitirDetReciboCaja(CodDetCaja,ValorAbono,CodFactura){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			//window.location.reload();
			document.getElementById("DocAfectados").innerHTML=ajax.responseText;
			ResumenReciboCaja();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmRc&CodDetCaja="+CodDetCaja+"&CodReciboCaja="+CodReciboCaja+
			"&ValorAbono="+ValorAbono+"&CodFactura="+CodFactura); //Posting txtname to Servle

}
function IngresarDeduccion(){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	var ValorDeduccion=document.getElementById("txtValorDeduccion").value;
	var Deduccion=document.getElementById("cmbDeducciones").value;
	
	if(CodReciboCaja==""){
		alert("No Hay recibo de Caja Activo.");
	}else{
		if(ValorDeduccion==""){
			alert("Digite el Valor de la Deduccion");
		}else{
			if(Deduccion=="Seleccione"){
				alert("Seleccione una Deduccion");
			}else{
				
			//	alert("CodReciboCaja "+CodReciboCaja+" ValorDeduccion "+ValorDeduccion+" Deduccion "+Deduccion);
			ajax=getXMLObject();
				ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {
						//alert(ajax.responseText);
						//window.location.reload();
						document.getElementById("Dedu").innerHTML=ajax.responseText;
						//MostrarDeducciones();
						ResumenReciboCaja();
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=ValDe&ValorDeduccion="+ValorDeduccion+"&Deduccion="+Deduccion+"&CodReciboCaja="+CodReciboCaja); //Posting txtname to Servle

			}
		}
	}
	
}




function ResumenReciboCaja(){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	//alert(CodReciboCaja);
	var TotalValorDeduccion=document.getElementById("txtTotalValorDeduccion").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			//window.location.reload();
			document.getElementById("txtFactura").value="";
			document.getElementById("txtFactura").focus();
			//document.getElementById("txtCodFactura").value=CodigoFactura;
			document.getElementById("txtSaldo").value="";
			document.getElementById("autoCompletaFactu").innerHTML="";
			document.getElementById("txtAbono").value="";

			document.getElementById("ResumenCaja").innerHTML=ajax.responseText;
			MostarValorPagado();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ResRC&CodReciboCaja="+CodReciboCaja+"&TotalValorDeduccion="+TotalValorDeduccion); //Posting txtname to Servle
}

function AsignarTipoPago(){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	var ValorPagado=document.getElementById("txtValorPagado").value;
	var FormaPago=document.getElementById("cmbFormaPago").value;
	var Banco=document.getElementById("cmbBanco").value;
	if(FormaPago=="Seleccione"){
		alert("Seleccione Forma de Pago.");
	}else{
		if(ValorPagado==""){
			alert("Digite Valor");
			document.getElementById("txtValorPagado").focus();
		}else{
//		txtValorPagado
			ajax=getXMLObject();
			ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					//alert(ajax.responseText);
					//window.location.reload();
					document.getElementById("FormaPago").innerHTML=ajax.responseText;
			
					MostarValorPagado();
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=ValPa&ValorPagado="+ValorPagado+"&FormaPago="+FormaPago+"&CodReciboCaja="+CodReciboCaja+"&Banco="+Banco); //Posting txtname to Servle
		}
	}
}


function MostarValorPagado(){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	var ValoraPagar=document.getElementById("txtValorTotalPagar").value;
	var ValorPagadoR=document.getElementById("txtValorPagadoR").value;
	var ValorPagado=document.getElementById("txtValorPagado").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			//window.location.reload();
			
			document.getElementById("ValPagado").innerHTML=ajax.responseText;
			//alert("ValoraPagar "+ValoraPagar+" ValorPagadoR "+ValorPagadoR+" ValorPagado "+ValorPagado);
			document.getElementById("txtValorPagado").value=ValoraPagar-ValorPagadoR;
			var ValoPag=document.getElementById("txtValorPagado").value;
			if(ValoPag==0){
				document.getElementById("txtValorPagado").disabled=true;
			}else{
				document.getElementById("txtValorPagado").disabled=false;
			}
			//alert(ValorPagado);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ValorPa&CodReciboCaja="+CodReciboCaja); //Posting txtname to Servle
}

function OmitirDetallePago(CodigoDetalle){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("FormaPago").innerHTML=ajax.responseText;			
			MostarValorPagado();
			}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmDt&CodigoDetalle="+CodigoDetalle+"&CodReciboCaja="+CodReciboCaja); //Posting txtname to Servle
}


function OmitirDeduccion(CodigoDetalle){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			document.getElementById("Dedu").innerHTML=ajax.responseText;			
			ResumenReciboCaja();
			}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmDed&CodigoDetalle="+CodigoDetalle+"&CodReciboCaja="+CodReciboCaja); //Posting txtname to Servle
}

function FinalizarRC(){
	var CodReciboCaja=document.getElementById("txtCodReciboCaja").value;
	var ValorPagadoR=document.getElementById("txtValorPagadoR").value;
	var ValoPag=document.getElementById("txtValorPagado").value;
	var TotalValorDeduccion=document.getElementById("txtTotalValorDeduccion").value;
	var ValorTotalCartera=document.getElementById("txtValorTotalCartera").value;
	var ConceptoRC=document.getElementById("txtObservacionRC").value;
	var CodUsuRCC=document.getElementById("txtCodusuario").value;
	//alert("ValorPagadoR "+ValorPagadoR+" ValoPag "+ValoPag+" TotalValorDeduccion "+TotalValorDeduccion+" ValorTotalCartera "+ValorTotalCartera);
	if(TotalValorDeduccion==0){
		var x = confirm("Desea Generar Recibo de Caja Sin de Deducciones ?");
		if (x) {			
			if(ValoPag==0){
				ajax=getXMLObject();
				ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {
						ImprimirReciboCaja(ajax.responseText);
						window.location.reload();
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=FinRC&CodReciboCaja="+CodReciboCaja+"&ValorPagadoR="+ValorPagadoR+"&ValorTotalCartera="+ValorTotalCartera+"&ConceptoRC="+ConceptoRC+"&CodUsuRCC="+CodUsuRCC); //Posting txtname to Servle

			}else{
				alert("Tiene Valor Pendiente Por Asignar de "+ValoPag);
				document.getElementById("txtValorPagado").focus();
			}
		}
	}else{
		if(ValoPag==0){
			ajax=getXMLObject();
			ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					ImprimirReciboCaja(ajax.responseText);
					window.location.reload();
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=FinRC&CodReciboCaja="+CodReciboCaja+"&ValorPagadoR="+ValorPagadoR+"&ValorTotalCartera="+ValorTotalCartera+"&ConceptoRC="+ConceptoRC+"&CodUsuRCC="+CodUsuRCC); //Posting txtname to Servle

		}else{
			alert("Tiene Valor Pendiente Por Asignar de "+ValoPag);
			document.getElementById("txtValorPagado").focus();
		}
	}
}


function ImprimirReciboCaja(CodRecCaj){
	
	pagina="cont_ReporteRecibosCaja.jsp?CodRecCaj="+CodRecCaj;	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}


function GuardarFacturaPagar(){	
	var cod_cuenta_fk=document.getElementById("txtCodigoCuenta").value;
	var fecha_factura=encodeURIComponent(document.getElementById("txtFechFactura").value);	
	var Retiva=encodeURIComponent(document.getElementById("txtRetIva").value);
	var numero_factura=encodeURIComponent(document.getElementById("txtNumFact").value);
	var observacion=encodeURIComponent(document.getElementById("txtObservacionFact").value);
	var precio_factura=encodeURIComponent(document.getElementById("txtPrecioFact").value);
	var ret_ica=encodeURIComponent(document.getElementById("txtRetIca").value);	
	var usuario_insercion=encodeURIComponent(document.getElementById("txtCodUsuario").value);
	var fecha_insercion=encodeURIComponent(document.getElementById("txtFecha").value);
	var hora_insercion=encodeURIComponent(document.getElementById("txtHora").value);
	var tipo=document.getElementById("txtTipoCuenta").value;
	var CentroCosto=document.getElementById("cmbCentroCosto").value;
	var RetFuente=document.getElementById("txtRetFuente").value;
	//var tipo="";
	//var estado=encodeURIComponent(document.getElementById("porelcontrolador").value);
	
	if(tipo=="Credito"){
		tipo="0";
	}
	
	/*alert("valor=1&cod_cuenta_fk="+cod_cuenta_fk+"&estado=0&fecha_factura="+fecha_factura+
			"&fecha_insercion="+fecha_insercion+"&hora_insercion="+hora_insercion+"&Retiva="+Retiva+
			"&numero_factura="+numero_factura+"&observacion="+observacion+"&precio_factura="+precio_factura+"&ret_ica="+ret_ica
			+"&tipo="+tipo+"&usuario_insercion="+usuario_insercion+"&CentroCosto="+CentroCosto+"&RetFuente="+RetFuente);
	*/
	//var tipo=encodeURIComponent(document.getElementById("automatico").value);
	
	//
	//if((autoretenedor!="Seleccione")&&(tipo_regimen!="Seleccione")&&(nombre_razonsoc!="")&&(CodigoCuenta!="")&&(NombreCuenta!="")&&(TipoCuenta!="Seleccione")&&(NaturalezaCuenta!="Seleccione")&&(estado!="Seleccione")){
		ajax=getXMLObject();
		ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&cod_cuenta_fk="+cod_cuenta_fk+"&estado=0&fecha_factura="+fecha_factura+
				"&fecha_insercion="+fecha_insercion+"&hora_insercion="+hora_insercion+"&Retiva="+Retiva+
				"&numero_factura="+numero_factura+"&observacion="+observacion+"&precio_factura="+precio_factura+"&ret_ica="+ret_ica
				+"&tipo="+tipo+"&usuario_insercion="+usuario_insercion+"&CentroCosto="+CentroCosto+"&RetFuente="+RetFuente); //Posting txtname to Servle
	//}
}

function GuardarFactura(){	
	var cod_cuenta_fk=document.getElementById("txtCodigoCuenta").value;
	var fecha_factura=document.getElementById("txtFechFactura").value;	
	var iva=encodeURIComponent(document.getElementById("cmbIva").value);
	var numero_factura=encodeURIComponent(document.getElementById("txtNumFact").value);
	var observacion=encodeURIComponent(document.getElementById("txtObservacionFact").value);
	var precio_factura=encodeURIComponent(document.getElementById("txtPrecioFact").value);
	var ret_ica=encodeURIComponent(document.getElementById("txtRetIca").value);	
	var usuario_insercion=encodeURIComponent(document.getElementById("txtCodUsuario").value);
	var fecha_insercion=encodeURIComponent(document.getElementById("txtFecha").value);
	var hora_insercion=encodeURIComponent(document.getElementById("txtHora").value);
	var tipo=document.getElementById("txtTipoCuenta").value;
	//var tipo="";
	//var estado=encodeURIComponent(document.getElementById("porelcontrolador").value);
	if(tipo=="Debito"){
		tipo="1";
	}
	if(tipo=="Credito"){
		tipo="0";
	}
	//alert(fecha_factura);
	var Dia;var Mes;var Anos;
	var y=fecha_factura.split("/").length;
 	var z=fecha_factura.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    Dia=z[0];
    Mes=z[1];
    Anos=z[2];
    // form1.cbArea.value();
    }
 	fecha_factura=Anos+"-"+Mes+"-"+Dia
	//var tipo=encodeURIComponent(document.getElementById("automatico").value);
	
	//
	//if((autoretenedor!="Seleccione")&&(tipo_regimen!="Seleccione")&&(nombre_razonsoc!="")&&(CodigoCuenta!="")&&(NombreCuenta!="")&&(TipoCuenta!="Seleccione")&&(NaturalezaCuenta!="Seleccione")&&(estado!="Seleccione")){
		ajax=getXMLObject();
		ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
				window.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&cod_cuenta_fk="+cod_cuenta_fk+"&estado=0&fecha_factura="+fecha_factura+
				"&fecha_insercion="+fecha_insercion+"&hora_insercion="+hora_insercion+"&iva="+iva+
				"&numero_factura="+numero_factura+"&observacion="+observacion+"&precio_factura="+precio_factura+"&ret_ica="+ret_ica
				+"&tipo="+tipo+"&usuario_insercion="+usuario_insercion); //Posting txtname to Servle
	//}
}
function BuscarFacturaCobrar(){
	var Contenido=document.getElementById("DatosCuenta");
	var NumFactura=document.getElementById("txtBusqueda").value;
	var TipoCuenta=document.getElementById("txtTipoCuenta").value;
	if(NumFactura==""){
		alert("Digite Numero de la Factura");
	}else{
	document.getElementById('DatosCuenta').innerHTML='<p>Buscando Factura...'+NumFactura+' No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
		ajax=getXMLObject();
		ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				//Conte.innerHTML=ajax.responseText;
				Contenido.innerHTML=ajax.responseText;
				//document.getElementById("DatosCuenta2").innerHTML=ajax.responseText;
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2.11&tipo="+TipoCuenta+"&NumFactura="+NumFactura);
	}
}

function VerReporteDetalleCobro(CodDetFact,CodFact){
	pagina="cont_ReporteRecibos.jsp?CodFact="+CodFact+"&CodDetFact="+CodDetFact;		
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}

function VerReporteDetallePago(CodDetFact,CodFact){
	pagina="cont_ReporteRecibosPagar.jsp?CodFact="+CodFact+"&CodDetFact="+CodDetFact;		
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}


function Cargar(){
	var Contenido=document.getElementById("contenido");
	var TipoCuenta=document.getElementById("txtTipoCuenta").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			Contenido.innerHTML=ajax.responseText;
			//document.getElementById("txtBusqueda").focus();
			//document.getElementById("contenido").innerHTML=ajax.responseText;
			//window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2&tipo="+TipoCuenta);
}

function VerEstadoCuenta(CodCuenta){
	var Contenido=document.getElementById("contenido");
	var TipoCuenta=document.getElementById("txtTipoCuenta").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			Contenido.innerHTML=ajax.responseText;
			//document.getElementById("txtBusqueda").focus();
			//document.getElementById("contenido").innerHTML=ajax.responseText;
			//window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.2&CodCuenta="+CodCuenta+"&tipo="+TipoCuenta);
}
function DetalleFacturaND(CodFactura){
	//alert("DetalleFacturaND");
	var Contenido=document.getElementById("DetFact");
	var FechaFija=document.getElementById("txtFechaFija").value;
	
	//alert(FechaFija)
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			Contenido.innerHTML=ajax.responseText;
			//document.getElementById("txtBusqueda").focus();
			//document.getElementById("contenido").innerHTML=ajax.responseText;
			//window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.3ND&CodFactura="+CodFactura+"&FechaFija="+FechaFija);
}
function VerEstadoFactura(CodFactura,tipo){
	
	pagina="cont_Factura.jsp?CodFactura="+CodFactura+"&tipo="+tipo;
	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}

function VerEstadoFacturaND(CodFactura){
	
	pagina="cont_FacturaND.jsp?CodFactura="+CodFactura;
	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}


function DetalleFactura(CodFactura,tipo){
	var Contenido=document.getElementById("DetFact");
	var FechaFija=document.getElementById("txtFechaFija").value;
	
	//alert(FechaFija)
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			Contenido.innerHTML=ajax.responseText;
			//document.getElementById("txtBusqueda").focus();
			//document.getElementById("contenido").innerHTML=ajax.responseText;
			//window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.3&CodFactura="+CodFactura+"&tipo="+tipo+"&FechaFija="+FechaFija);
}

function GuardarPorCobrar(CodFactura,tipo){
	//alert("CodFactura "+CodFactura+" tipo "+tipo);
	
	var cod_cuenta_fk=document.getElementById("txtCodigoCuenta").value;
	var cantidad=document.getElementById("txtcantidad").value;
	var Pendi=document.getElementById("txtPendiente2").value;
	var fechaPA=document.getElementById("txtFechaPA").value;
	var NumSoporte=document.getElementById("txtNunSoporte").value;
	var Observacion=encodeURIComponent(document.getElementById("txtObervacion").value);
	var usuario_insercion=document.getElementById("txtCodigoUsuario").value;
	var CodCuentaBanco=document.getElementById("cmbBanco").value;
	var TipoPago=document.getElementById("cmbTipoPago").value;
	var y=fechaPA.split("/").length;
 	var z=fechaPA.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
 		Dia=z[0];
 		Mes=z[1];
 		Anio=z[2];
 		// form1.cbArea.value();
 	}
 	fechaPA=Anio+"-"+Mes+"-"+Dia;
 	if(Observacion==""){
 		alert("Falta el Concepto.");
 		document.getElementById("txtObervacion").focus();
 	}else{
 		if(parseInt(cantidad)>parseInt(Pendi)){
 			//alert("Cantidad="+cantidad+" es mayor a Pendi="+Pendi);
 			alert("Valor de la Nota es Mayor al Valor Pendiente de la Factura.\nIntente Nuevamente.")
 		}else{
 			if(TipoPago=="Seleccione"){
 				alert("Seleccione el Tipo de Movimiento.");
 				document.getElementById("cmbTipoPago").focus(); 			
 			}else{
 				if(cantidad==""){
 					alert("Digite la Cantidad de la Nota Credito");
 					document.getElementById("txtcantidad").focus();
 				}else{
 					document.getElementById("BotonH").innerHTML="";
 					ajax=getXMLObject();
 					ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
 					ajax.onreadystatechange  = function(){
 						if (ajax.readyState == 4) {
 							var a=ajax.responseText;
 							if(a!=""){
 								alert(a);
 							}else{
 								window.location.reload();
 							}
 							//document.getElementById("txtBusqueda").focus();
 							//document.getElementById("contenido").innerHTML=ajax.responseText;
				
 						}
 					}
 					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 					ajax.send("valor=2.4&CodFactura="+CodFactura+"&tipo="+tipo+"&cod_cuenta_fk="+cod_cuenta_fk
 							+"&cantidad="+cantidad+"&fechaPA="+fechaPA+"&NumSoporte="+NumSoporte+"&Observacion="+Observacion
 							+"&usuario_insercion="+usuario_insercion+"&TipoPago="+TipoPago+"&CodCuentaBanco="+CodCuentaBanco);
 				}
 			}
 		}
 	}
}

function GuardarPorPagar(CodFactura,tipo){
	//alert("CodFactura "+CodFactura+" tipo "+tipo);
	//var Contenido=document.getElementById("DetFact");
	var cod_cuenta_fk=document.getElementById("txtCodigoCuenta").value;
	var cantidad=document.getElementById("txtcantidad").value;
	var fechaPA=document.getElementById("txtFechaPA").value;
	var NumSoporte=document.getElementById("txtNunSoporte").value;
	var Observacion=encodeURIComponent(document.getElementById("txtObervacion").value);
	var usuario_insercion=document.getElementById("txtCodigoUsuario").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			var a=ajax.responseText;
			if(a!=""){
				alert(a);
			}else{
				window.location.reload();
			}
			//document.getElementById("txtBusqueda").focus();
			//document.getElementById("contenido").innerHTML=ajax.responseText;
			
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.4&CodFactura="+CodFactura+"&tipo="+tipo+"&cod_cuenta_fk="+cod_cuenta_fk
			+"&cantidad="+cantidad+"&fechaPA="+fechaPA+"&NumSoporte="+NumSoporte+"&Observacion="+Observacion
			+"&usuario_insercion="+usuario_insercion);
}

function AutoCompletaCuenta(){
	var Conte=document.getElementById("DatosCuenta");
	var Busqueda=document.getElementById("txtBusqueda").value;
	var TipoCuenta=document.getElementById("txtTipoCuenta").value;
	Conte.style.display='';
	if(Busqueda!=""){
		ajax=getXMLObject();
		ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				document.getElementById("DatosCuenta").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2.1&tipo="+TipoCuenta+"&Busqueda="+Busqueda);
	}else{
		Conte.style.display='none';
		document.getElementById("txtBusqueda").focus();
	}
}

function Borrar(){document.getElementById("txtBusqueda").value=""}