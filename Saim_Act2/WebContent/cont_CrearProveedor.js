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

////INICIO JS  PARA REPORTES DE CONTABILIDAD/////


var patronp = new Array(2);
var patrona = new Array(4);

function valAnoPeriodo(d, pat, nums, op) {
	
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
			/*document.getElementById('RC1').value="";
			document.getElementById('RC1').focus();*/
		}
		
		ini = val.substring(0, 2);
		if ((ini > 13) || (ini == "00")) {
			d.value = "01";
		}
		
	/*	if(largo==2){
			document.getElementById('AC').value="";
			document.getElementById('AC').focus();
		}	*/	
	}else{
		
			if(largo==4){
				document.getElementById('MC').value="";
				document.getElementById('MC').focus();
				//d.value=val.substring(0, 4);
			}
		
	}
	
	}
	
	var elEvento =  window.event;  // arguments[0] ||
    var tecla = elEvento.keyCode;
    if(tecla == 13) { 
    	if((document.getElementById('AC').value!="")&(document.getElementById('MC').value!="")&(document.getElementById('RC1').value!="Seleccione")){
    	//alert("entrando");
    	var ano=document.getElementById('AC').value;
    	var periodo=document.getElementById('MC').value;
    	var RC1=document.getElementById('RC1').value;
    	//alert("RC1 "+RC1);
    	
    	}else{alert("Debe seleccionar tanto el A\u00f1o como el Periodo");
    		if(document.getElementById('MC').value==""){document.getElementById('MC').focus();}
    		if(document.getElementById('AC').value==""){document.getElementById('AC').focus();}
    	}
    }
}

function RadioGR(){
	var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((dia < 10) ? "0" : "") + dia
	  temp1 += ((mes < 10) ? "/0" : "/") + mes
	  temp1 += ((año < 10) ? "0" : "/") + año
	// document.getElementById('txtfecha').value = temp1
	 id = setTimeout("fecha_gru()",1000)	
	 
	var p="<table border='0' >"+					
		"<tr><td>Fecha Emision</td><td><input type='text' id='txtFechaEmision' onKeyUp='masca(this,&quot;/&quot;,patron,true)' value='"+temp1+"' ></td><td>Tipo Reporte <select id='TipoReporte'><option value='Seleccione'>Seleccione</option><option value='Emitida'>Emitida</option><option value='Radicada'>Radicada</option></select></td></tr>"+
		"<tr><td colspan='2' align='center'><input type='button' onclick='GenerarCarteraConsolidadaz()' value='Generar Reporte'></td></tr></table>";
		//"<tr><td colspan='2' align='center'><input type='button' onclick='VerReporteCarteraRadicadaConsolidada()' value='Generar Reporte'></td></tr></table>";
	
	document.getElementById('Creaciones').innerHTML = (p);
}

function RadioCRT(){
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	

			document.getElementById('Creaciones').innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=BTRC"); //Posting txtname to Servle

}

function GenerarCarteraConsolidadaz(){
	var TipoReporte=document.getElementById("TipoReporte").value;
	var FechaE=document.getElementById("txtFechaEmision").value;
	var y=FechaE.split("/").length;
 	var z=FechaE.split("/");		     	
 	for(x=0; x<y-1; x=x+1){ FecSql=z[2]+"-"+z[1]+"-"+z[0];}
 	document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	

			document.getElementById('FormTo').innerHTML = ajax.responseText;
			//window.location.href="cont_HistoricoCarteraConsolidada.jsp?TipoReporte="+TipoReporte+"&FechaE="+FechaE;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=gcc&TipoReporte="+TipoReporte+"&FechaE="+FecSql); //Posting txtname to Servle

}

function GenerarReporteCartera(){
	var FechaI=document.getElementById("txtFechaInicial").value;
	var FechaF=document.getElementById("txtFechaFinal").value;
	var Entidad=document.getElementById("cmbEntidad").value;
	var yi=FechaI.split("/").length;
 	var zi=FechaI.split("/");		     	
 	for(xi=0; xi<yi-1; xi=xi+1){ FechaInicial=zi[2]+"-"+zi[1]+"-"+zi[0];}
 	
 	var yf=FechaF.split("/").length;
 	var zf=FechaF.split("/");		     	
 	for(xf=0; xf<yf-1; xf=xf+1){ FechaFinal=zf[2]+"-"+zf[1]+"-"+zf[0];}
 	if((FechaI=="")||(FechaF=="")){
 		alert("Debe seleccionar, al menos, un rango de fechas");
 	}else{
 	 	document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
 		ajax=getXMLObject();
 		ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
 		ajax.onreadystatechange  = function(){
 			if (ajax.readyState == 4) {
 				document.getElementById('FormTo').innerHTML = ajax.responseText;
 			}
 		}
 		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 		ajax.send("valor=GRCN&FechaInicial="+FechaInicial+"&FechaFinal="+FechaFinal+"&Entidad="+Entidad); //Posting txtname to Servle

 	}
}

function GenerarCarteraConsolidada(){
	var TipoReporte=document.getElementById("TipoReporte").value;
	var FechaE=document.getElementById("txtFechaEmision").value;
	var y=FechaE.split("/").length;
 	var z=FechaE.split("/");		     	
 	for(x=0; x<y-1; x=x+1){ FecSql=z[2]+"-"+z[1]+"-"+z[0];}
 	window.location.href="cont_HistoricoCarteraConsolidada.jsp?TipoReporte="+TipoReporte+"&FechaE="+FecSql;
}

function VerReporteCarteraRadicadaConsolidada(){
	var FechaE=document.getElementById("txtFechaEmision").value;
	var y=FechaE.split("/").length;
 	var z=FechaE.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
 		FecSql=z[2]+"-"+z[1]+"-"+z[0];
    }
 	/*var opcion=confirm("Desea Mostrar el Reporte en Excel?");
	if(opcion){*/
		//en excel
		pagina="cont_CarteraGenRadExcel.jsp?FechaE="+FecSql;		
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	/*}else{
		//en pdf
		pagina="cont_ReporteCarteraGeneral.jsp?FechaE="+FecSql;		
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}*/
}

function ConsultarRepAuxiliarGeneral(){
	ajax=getXMLObject();
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var MC2=document.getElementById('MC2').value;
	var TDOC=document.getElementById('TDOC').value;
	if((RC1!="")&&(RC2!="")&&(AC!="")&&(MC!="")&&(MC2!="")&&(TDOC!="")){
		if((RC1=="TODAS")&&(RC2=="TODAS")){
			if(MC<=MC2){
			pagina="cont_ReporAuxiliarGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC;
			var opciones3="toolbar=yes, menubar=yes , location=yes ";
			opciones3=opciones3+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
			window.open(pagina,"NUEVA",opciones3);
			}else{
				alert("El rango de periodos deber ser de menor a mayor Ej: Periodo 01 al 04 ");
			}
	}else{
		if((RC1!="TODAS")&&(RC2!="TODAS")){
			//alert("VALOR DE"+RC1);
			//alert("VALOR DE "+RC2);
			if(RC1<=RC2){
				if(MC<=MC2){
					pagina="cont_ReporAuxiliarGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC;
					var opciones4="toolbar=yes, menubar=yes , location=yes ";
					opciones4=opciones4+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
					window.open(pagina,"NUEVA",opciones4);
				}else{
					alert("El rango de periodos deber ser de menor a mayor Ej: Periodo 01 al 04 ");
				}
			}else{
				alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
			}
		}else{
			alert("El rango de Cuentas no es correcto");
		}
	}
	}else{
		alert("No ha seleccionado todos los datos para generar el reporte");
	}
}

function ConsultarRepAuxiliarCuentaTercero(){
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var MC2=document.getElementById('MC2').value;
	var TDOC=document.getElementById('TDOC').value;
	var TERC=document.getElementById('terh0').value;
	if((RC1!='Seleccione')&&(RC2!='Seleccione')&&(AC!="")&&(MC!="")&&(MC2!="")&&(TDOC!="")){
		if((RC1=="TODAS")&&(RC2=="TODAS")){
			if(MC<=MC2){
			pagina="cont_AuxiliarCuentaTercero.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC+"&TERC="+TERC;
			var opciones3="toolbar=yes, menubar=yes , location=yes ";
			opciones3=opciones3+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
			window.open(pagina,"NUEVA",opciones3);
			}else{
				alert("El rango de periodos deber ser de menor a mayor Ej: Periodo 01 al 04 ");
			}
	}else{
		if((RC1!="TODAS")&&(RC2!="TODAS")){
			//alert("VALOR DE"+RC1);
			//alert("VALOR DE "+RC2);
			if(RC1<=RC2){
				if(MC<=MC2){
					pagina="cont_AuxiliarCuentaTercero.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC+"&TERC="+TERC;
					var opciones4="toolbar=yes, menubar=yes , location=yes ";
					opciones4=opciones4+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
					window.open(pagina,"NUEVA",opciones4);
				}else{
					alert("El rango de periodos deber ser de menor a mayor Ej: Periodo 01 al 04 ");
				}
			}else{
				alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
			}
		}else{
			alert("El rango de Cuentas no es correcto");
		}
	}
	}else{
		alert("No ha seleccionado todos los datos para generar el reporte");
	}
}

function vistaRepAuxiliarGeneral(RC1, RC2, MC, AC,MC2,TDOC){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	//alert("valor=RepAuxiliarGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC);
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepAuxiliarGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&MC2="+MC2+"&TDOC="+TDOC);
}

var elementoSeleccionado = -1;


function vistaRepAuxiliarCT(RC1, RC2, MC, AC,MC2,TDOC,TERC){
	ajax=getXMLObject();
	//alert("vistaRepAuxiliarCT");
	//alert("valor=RepAuxiliarCT&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&MC2="+MC2+"&TDOC="+TDOC+"&TERC="+TERC);
	var contenido=document.getElementById('reporte');
	//alert("valor=RepAuxiliarGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC);
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepAuxiliarCT&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&MC2="+MC2+"&TDOC="+TDOC+"&TERC="+TERC);
}

function ConsBalanceCuentaTercero(){
	ajax=getXMLObject();
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var TERC=document.getElementById('terh0').value;
	//alert(TERC);
	var opciones3="toolbar=yes, menubar=yes , location=yes ";
	opciones3=opciones3+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
	if(((RC1!='Seleccione')&&(RC2!='Seleccione')&&(AC!="")&&(MC!=""))){
		if((RC1=="TODAS")&&(RC2=="TODAS")){
			 pagina="cont_BalanceCuentaTercero.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&TERC="+TERC;
			 window.open(pagina,"NUEVA",opciones3);
		}else{
			if((RC1!="TODAS")&&(RC2!="TODAS")){
					if(RC1<=RC2){
						 pagina="cont_BalanceCuentaTercero.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&TERC="+TERC;
						 window.open(pagina,"NUEVA",opciones3);
					}else{
						alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
					}
			}else{
				alert("El rango de Cuentas no es correcto");
			}
		
		}
	}else{
		alert("No ha Digitado o Seleccionado los datos necesarios para realizar la consulta");	
}
}


function ConsultarBalanceGeneral(){
	ajax=getXMLObject();
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var childTablaNivel = document.getElementById('balance').childNodes[0].childNodes;
	var nivelSeleccionado="";
	var opciones3="toolbar=yes, menubar=yes , location=yes ";
	opciones3=opciones3+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
	for (i=1;i<childTablaNivel.length;i++){
		if(document.getElementById('chkEF'+i).checked){
			nivelSeleccionado+=document.getElementById('chkEF'+i).value+"_";				
		}
	}
	if((RC1!='Seleccione')&&(RC2!='Seleccione')&&(AC!="")&&(MC!="")){
		if(nivelSeleccionado==""){
					var opc=confirm("Desea ver todos los niveles del rango de cuentas seleccionado?");
						if(opc){
							nivelSeleccionado="1_2_3_4_5_"
								//document.getElementById('Creaciones').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
								//alert("cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado);
								if((RC1=="TODAS")&&(RC2=="TODAS")){
										pagina="cont_ReporteBalanceGeneral.jsp?RC1="+1+"&RC2="+3+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado;
																	
												window.open(pagina,"NUEVA",opciones3);
								}else{
									if((RC1!="TODAS")&&(RC2!="TODAS")){
										if(RC1<=RC2){
										pagina="cont_ReporteBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado;
																	
												window.open(pagina,"NUEVA",opciones3);
										}else{
											alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
										}
									}else{
										alert("El rango de Cuentas no es correcto");
									}
								}
						}else{}
			}else{
					if((RC1=="TODAS")&&(RC2=="TODAS")){
						pagina="cont_ReporteBalanceGeneral.jsp?RC1="+1+"&RC2="+3+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado;
								window.open(pagina,"NUEVA",opciones3);
				}else{
					if((RC1!="TODAS")&&(RC2!="TODAS")){
						if(RC1<=RC2){
						pagina="cont_ReporteBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado;
						
						window.open(pagina,"NUEVA",opciones3);
						}else{
							alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
						}
					}else{
						alert("El rango de Cuentas no es correcto");
					}
				}
			
			
			
		}
		
	}else{
		alert("No ha digitado todos los campos para generar la consulta");
	}
	
}

function Tpg(){
	ajax=getXMLObject();
	var pg=document.getElementById('Tpg').value;
	var contenido=document.getElementById('menupg');
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=menupg&pg="+pg);
}

function ConsultarPG(pg){
	alert(pg);
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var childTablaNivel = document.getElementById('nivelespg').childNodes[0].childNodes;
	var nivelSeleccionado="";
	var opciones3="toolbar=yes, menubar=yes , location=yes ";
	opciones3=opciones3+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
	for (i=1;i<childTablaNivel.length;i++){
		if(document.getElementById('chkEF'+i).checked){
			nivelSeleccionado+=document.getElementById('chkEF'+i).value+"_";				
		}
	}
	
	if((RC1!='Seleccione')&&(RC2!='Seleccione')&&(AC!="")&&(MC!="")){
		if(nivelSeleccionado==""){
					var opc=confirm("Desea ver todos los niveles del rango de cuentas seleccionado?");
						if(opc){
								nivelSeleccionado="1_2_3_4_5_"
								//document.getElementById('Creaciones').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
								//alert("cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado);
								if((RC1=="TODAS")&&(RC2=="TODAS")){
										pagina="cont_ReportePG.jsp?RC1="+4+"&RC2="+7+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&pg="+pg;		
												window.open(pagina,"NUEVA",opciones3);
								}else{
									if((RC1!="TODAS")&&(RC2!="TODAS")){
										if(RC1<=RC2){
										pagina="cont_ReportePG.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&pg="+pg;
																	
												window.open(pagina,"NUEVA",opciones3);
										}else{
											alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
										}
									}else{
										alert("El rango de Cuentas no es correcto");
									}
								}		
					}else{}
			}else{
						if((RC1=="TODAS")&&(RC2=="TODAS")){
								pagina="cont_ReportePG.jsp?RC1="+4+"&RC2="+7+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&pg="+pg;
								window.open(pagina,"NUEVA",opciones3);
						}else{
							if((RC1!="TODAS")&&(RC2!="TODAS")){
								if(RC1<=RC2){
								pagina="cont_ReportePG.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&pg="+pg;
								
								window.open(pagina,"NUEVA",opciones3);
								}else{
									alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
								}
							}else{
								alert("El rango de Cuentas no es correcto");
							}
						}
			}
		}else{
			alert("No ha digitado todos los campos para generar la consulta");
			}
	
}

function ConsultarRepBalanceGeneral(){
	ajax=getXMLObject();
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	//alert(MC);
	var childTablaNivel = document.getElementById('balance').childNodes[0].childNodes;
	//alert(childTablaNivel)
	var nivelSeleccionado="";
	var opciones3="toolbar=yes, menubar=yes , location=yes ";
	opciones3=opciones3+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
	//var i=0;
	for (i=1;i<childTablaNivel.length;i++){
		if(document.getElementById('chkEF'+i).checked){
			nivelSeleccionado+=document.getElementById('chkEF'+i).value+"_";				
		}
	}
	//alert("cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado);
	if((RC1!='Seleccione')&&(RC2!='Seleccione')&&(AC!="")&&(MC!="")){
	if(nivelSeleccionado==""){
				var opc=confirm("Desea ver todos los niveles del rango de cuentas seleccionado?");
					if(opc){
							nivelSeleccionado="1_2_3_4_5_"
							//document.getElementById('Creaciones').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
							//alert("cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado);
							if((RC1=="TODAS")&&(RC2=="TODAS")){
									pagina="cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado;		
											window.open(pagina,"NUEVA",opciones3);
							}else{
								if((RC1!="TODAS")&&(RC2!="TODAS")){
									if(RC1<=RC2){
									pagina="cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado;
																
											window.open(pagina,"NUEVA",opciones3);
									}else{
										alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
									}
								}else{
									alert("El rango de Cuentas no es correcto");
								}
							}
							
						
				}else{}
		}else{
					if((RC1=="TODAS")&&(RC2=="TODAS")){
							pagina="cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado;
							
							
									window.open(pagina,"NUEVA",opciones3);
					}else{
						if((RC1!="TODAS")&&(RC2!="TODAS")){
							if(RC1<=RC2){
							pagina="cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado;
							
							window.open(pagina,"NUEVA",opciones3);
							}else{
								alert("El rango de cuentas seleccionado debe ser de menor a mayor Ej: Cuenta 1 a la Cuenta 3");
							}
						}else{
							alert("El rango de Cuentas no es correcto");
						}
					}
		}
	}else{
		alert("No ha digitado todos los campos para generar la consulta");
		}
	
}

function vistaRepBalanceGeneral(RC1, RC2, MC, AC, nivelSeleccionado){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepBalanceGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&nivelSeleccionado="+nivelSeleccionado);
}

function vistaReporteBalanceGeneral(RC1, RC2, MC, AC, nivelSeleccionado){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReporteBalanceGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&nivelSeleccionado="+nivelSeleccionado);
}

function vistaReportePG(RC1, RC2, MC, AC, nivelSeleccionado,pg){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReportePG&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&nivelSeleccionado="+nivelSeleccionado+"&pg="+pg);
}

function vistaRepBalanceTercero(RC1, RC2, MC, AC, TERC){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepBalanceTercero&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&TERC="+TERC);
}

function verrep1(RC1,RC2,AC,MC,nivel,PreSumaSAD,PreSumaDebitoC1,PreSumaCreditoC1,PreSumaDebitoC2,PreSumaCreditoC2,PreSumaSAC,dif1,dif2,ident,Fecha,Hora){
	
	if((ident==0)&&(RC1=="todas")&&(RC2=="todas")){
	
	}else{
		if((ident==0)&&(RC1!="todas")&&(RC2!="todas")){
			
		}else{
			if((ident==1)&&(RC1=="todas")&&(RC2=="todas")){
				
			}else{
				if((ident==1)&&(RC1!="todas")&&(RC2!="todas")){
					pagina="cont_ReporteBG1.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivel="+nivel+"&PreSumaSAD="+PreSumaSAD+"&PreSumaDebitoC1="+PreSumaDebitoC1+"&PreSumaCreditoC1="+PreSumaCreditoC1+"&PreSumaDebitoC2="+PreSumaDebitoC2+"&PreSumaCreditoC2="+PreSumaCreditoC2+"&PreSumaSAC="+PreSumaSAC+"&dif1="+dif1+"&dif2="+dif2+"&ident="+ident+"&Fecha="+Fecha+"&Hora="+Hora;			
				   	var opciones="toolbar=yes, location=no, directories=no, status=no, menubar=yes,";
				 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
				   	window.open(pagina,"NUEVA",opciones);
				}
			}
	}
}
}


///FIN DE JS PARA REPORTES DE CONTABILIDAD/////
function GenerarConsultaCCC(){
	var txtFechaIN=document.getElementById("txtFechaIN").value;
	var txtFechaFI=document.getElementById("txtFechaFI").value;	
	document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			document.getElementById("FormTo").innerHTML=ajax.responseText;				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CCC&txtFechaIN="+txtFechaIN+"&txtFechaFI="+txtFechaFI);
	
}
function GenerarConsultaCPE(){
	
	var txtFechaIN=document.getElementById("txtFechaIN").value;
	var txtFechaFI=document.getElementById("txtFechaFI").value;	
	document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			document.getElementById("FormTo").innerHTML=ajax.responseText;				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CPE&txtFechaIN="+txtFechaIN+"&txtFechaFI="+txtFechaFI);
	
}

function GenerarConsultaDCC(){
	
	var txtFechaIN=document.getElementById("txtFechaIN").value;
	var txtFechaFI=document.getElementById("txtFechaFI").value;	
	document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			document.getElementById("FormTo").innerHTML=ajax.responseText;				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=DCC&txtFechaIN="+txtFechaIN+"&txtFechaFI="+txtFechaFI);
	
}
function RadioCO(){
	ajax=getXMLObject();
	var contenido=document.getElementById('Creaciones');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;			
			var p=" ";
			if(val=="CCC"){
					p=p+"<table><tr><td colspan='2'>CONSULTA DE FACTURACION POR CENTROS DE COSTO </td></tr> ";
			}
			if(val=="CPE"){
				p=p+"<table><tr><td colspan='2'>CONSULTA DE FACTURACION CONSOLIDADO </td></tr> ";
			}
			if(val=="DCC"){
				p=p+"<table><tr><td colspan='2'>DETALLADO DE FACTURACION POR CENTROS DE COSTO </td></tr> ";
			}
			p=p+"<tr> " +
			"<td>Fecha Inicial <input name='txtFechaIN' type='text' id='txtFechaIN' onKeyUp='masca(this,&quot;/&quot;,patron,true)'/></td> " +
			"<td>Fecha Inicial <input name='txtFechaFI' type='text' id='txtFechaFI' onKeyUp='masca(this,&quot;/&quot;,patron,true)'/></td> " ;
			if(val=="CCC"){
				p=p+"<td><input type='button' value='     Buscar     ' onclick='GenerarConsultaCCC()' ></td> ";
			}
			if(val=="CPE"){
				p=p+"<td><input type='button' value='     Buscar     ' onclick='GenerarConsultaCPE()' ></td> ";
			}
			if(val=="DCC"){
				p=p+"<td><input type='button' value='     Buscar     ' onclick='GenerarConsultaDCC()' ></td> ";
			}
			"</tr></table>";
			document.getElementById('Creaciones').innerHTML = (p);
		}	     
	}
}

function Radio() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('Creaciones');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					document.getElementById("Actualizaciones").innerHTML='';
					contenido.innerHTML = ajax.responseText	
					VerActualizaciones(val);
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}

function GuardarMovimientoCredito(){
	var CodCuenta=document.getElementById("CodCuentaBanco").value;
	var Descripcion=document.getElementById("txtDescripcion").value;
	ajax=getXMLObject();
	var contenido=document.getElementById('Contenido');
	if(Descripcion==""){
		alert("Escriba el movimiento");
		document.getElementById("txtDescripcion").focus();
	}else{
		if(CodCuenta==""){
			alert("Seleccione una cuenta.");
			document.getElementById("txtCuentaBanco").focus();
		}else{
			ajax.open("POST","ControlProveedor",true);
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {	
					Radio();
					//contenido.innerHTML = ajax.responseText;				
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=GMC&CodCuenta="+CodCuenta+"&Descripcion="+Descripcion);

		}
	}
}
function ListarTerceros(){
	ajax=getXMLObject();
	var contenido=document.getElementById('Contenido');
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			contenido.innerHTML = ajax.responseText;				
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=LT");	
}

function AutocompletarListarTerceros(){
	var tipo_identificacion=document.getElementById("cmbTipoDoc").value;
	var numero_documento=document.getElementById("txtNumDoc").value
	ajax=getXMLObject();
	if(tipo_identificacion!="Seleccione"){
		var contenido=document.getElementById('Contenido');
		ajax.open("POST","ControlProveedor",true);
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {	
				contenido.innerHTML = ajax.responseText;				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ALT&tipo_identificacion="+tipo_identificacion+"&numero_documento="+numero_documento);
	}else{
		alert("Seleccione Un Tipo de documento.");
		document.getElementById("cmbTipoDoc").focus();
	}
}

function MostrarTercero(TipoDoc,NumDoc){
	document.getElementById("cmbTipoDoc").value=TipoDoc;
	document.getElementById("txtNumDoc").value=NumDoc;
	
	BuscarProveedor();
	
	
}

/*******************************************************************/
function AutocompletaCuentaDiferido(){
	var parametro=document.getElementById("txtCuentaDiferido").value;
	if(parametro!=""){
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				document.getElementById("CuentaDiferido").innerHTML=ajax.responseText;
				var cond=document.getElementById("txtContadorACD").value;
				if(cond=="1"){
					//alert("ultimo registro");
					var CodCuenta=document.getElementById("txtCodCuentaACD").value;
					var NumeroCuenta=document.getElementById("txtNumeroCuentaACD").value;
					var NombreCuenta=document.getElementById("txtNombreCuentaACD").value;
					
					AsignarCuentaDiferido(CodCuenta,NumeroCuenta,NombreCuenta);
				}
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=acd&parametro="+parametro); //Posting txtname to Servle
	}
}

function ValidarCuentaCreada(){
	var CodigoCuenta=document.getElementById("txtCodigoCuenta").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			var re=ajax.responseText;
			//alert(re);
			if(re==""){
				//alert("Todo BN, Existe la Cuenta: "+CodigoCuenta);
				
			}else{
				alert("No Existe La Cuenta"+CodigoCuenta+".\nPara Crear una Cuenta Siguiente Primero Debe Crear Esta.");
				document.getElementById("txtCodigoCuenta").disabled=true;
				BuscarCuenta();
			}
		}
	}
	var dosvar="";
	if(CodigoCuenta.length==2){
		dosvar=CodigoCuenta.substr(0,2);
		//alert("CodigoCuenta=2 "+dosvar);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VCC&CodigoCuenta="+CodigoCuenta);
	}
	if(CodigoCuenta.length==4){
		dosvar=CodigoCuenta.substr(0,4);
		//alert("CodigoCuenta=4 "+dosvar);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VCC&CodigoCuenta="+CodigoCuenta);
	}
	if(CodigoCuenta.length==6){
		dosvar=CodigoCuenta.substr(0,6);
		//alert("CodigoCuenta=6 "+dosvar);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VCC&CodigoCuenta="+CodigoCuenta);
	}
	if(CodigoCuenta.length==8){
		dosvar=CodigoCuenta.substr(0,8);
	//	alert("CodigoCuenta=8 "+dosvar);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VCC&CodigoCuenta="+CodigoCuenta);
	}
	if(CodigoCuenta.length==10){
		dosvar=CodigoCuenta.substr(0,10);
		//alert("CodigoCuenta=10 "+dosvar);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VCC&CodigoCuenta="+CodigoCuenta);
	}
	if(CodigoCuenta.length==12){
		dosvar=CodigoCuenta.substr(0,8);
		//alert("CodigoCuenta=12 "+dosvar);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VCC&CodigoCuenta="+CodigoCuenta);
	}
	if(CodigoCuenta.length>12){
		//alert("Cantidad de Caracteres "+CodigoCuenta.length)
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VCC&CodigoCuenta="+CodigoCuenta);
	}
	
	//alert(dosvar);
	/*ajax.open("POST","ControlProveedor",true);
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			var re=ajax.responseText;
			alert(re);
			if(re==""){
				alert("Todo BN, Existe la Cuenta: "+CodigoCuenta);
				
			}else{
				alert("No Existe La Cuenta"+CodigoCuenta);
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VCC&CodigoCuenta="+CodigoCuenta);*/

}

function AsignarCuentaDiferido(CodCuenta,NumeroCuenta,NombreCuenta){
	document.getElementById("CodCuentaDife").value=CodCuenta;
	document.getElementById("txtCuentaDiferido").value=NumeroCuenta;
	document.getElementById("CuentaDiferido").innerHTML='';
	document.getElementById("txtCuentaGasto").focus();
}

function RadioSincronizacion(){
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			alert(ajax.responseText)
			///var cong=document.getElementById("txtContadorACG").value;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=sw"); //Posting txtname to Servle

}

function RadioSincronizacionFechaRadicacion(){
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			alert(ajax.responseText)
			///var cong=document.getElementById("txtContadorACG").value;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=swr"); //Posting txtname to Servle

}

function SincronizarContFactura_CarteraPlazo(){
	var CodCuentaEmpresa=document.getElementById("cmbCuentaEmpresa").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	document.getElementById('Creaciones').innerHTML='<p>Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	//document.getElementById('Creaciones').innerHTML='<p>Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		

			document.getElementById("Creaciones").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=SinCar&CodCuentaEmpresa="+CodCuentaEmpresa); //Posting txtname to Servle
}

function SincronizarContFactura_CarteraPlazo1 (){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		

			document.getElementById("Creaciones").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=SinCar1"); //Posting txtname to Servle
}

function RadioSincroFactuFaltantes (){
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	document.getElementById('Creaciones').innerHTML='<p>Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		

			document.getElementById("Creaciones").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=rdff"); //Posting txtname to Servle
}

function GuardarTipoPago(){
	var Descripcion=document.getElementById("txtDescripcion").value;
	var Comision=document.getElementById("txtComision").value;
	var Retefuente=document.getElementById("txtRetefuente").value;
	var Iva=document.getElementById("txtIva").value;
	var Reteiva=document.getElementById("txtReteiva").value;
	var ReteIca=document.getElementById("txtReteica").value;
	var CodCuenta=document.getElementById("CodCuentaBanco").value;
	if(Descripcion==""){
		alert("Digite la Descripcion")
	}else{
		if(CodCuenta==""){
			alert("Digite una Cuenta");
		}else{
			ajax=getXMLObject();
			ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {				
					alert(ajax.responseText);
					VerTiposPago();
				} 
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=gtp&Descripcion="+Descripcion+"&Comision="+Comision+"&Retefuente="+Retefuente+"&Iva="+Iva+"&Reteiva="+Reteiva+"&ReteIca="+ReteIca+"&CodCuenta="+CodCuenta); //Posting txtname to Servle
			
		}
	}
}

function VerBancos(){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("ToBanc").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Conccb"); //Posting txtname to Servle

}

function VerTiposPago(){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("CTiPago").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=cgtp"); //Posting txtname to Servle

}
function AutocompletaCuentaBanco(){
	var parametro=document.getElementById("txtCuentaBanco").value;	
	if(parametro!=""){
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				document.getElementById("CuentaBanco").innerHTML=ajax.responseText;
				var cong=document.getElementById("txtContadorACG").value;
				if(cong=="1"){
					//alert("ultimo registro");
					var CodCuenta=document.getElementById("txtCodCuentaACG").value;
					var NumeroCuenta=document.getElementById("txtNumeroCuentaACG").value;
					var NombreCuenta=document.getElementById("txtNombreCuentaACG").value;
					//alert("CodCuenta="+CodCuenta+" NumeroCuenta="+NumeroCuenta+" NombreCuenta="+NombreCuenta);
					AsignarCuentaBanco(CodCuenta,NumeroCuenta,NombreCuenta);
				}
				
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=acgb&parametro="+parametro); //Posting txtname to Servle
	}
}
function AsignarCuentaBanco(CodCuenta,NumeroCuenta,NombreCuenta){
	//alert("CodCuenta="+CodCuenta+" NumeroCuenta="+NumeroCuenta+" NombreCuenta="+NombreCuenta);
	document.getElementById("CodCuentaBanco").value=CodCuenta;
	document.getElementById("txtCuentaBanco").value=NumeroCuenta;
	document.getElementById("CuentaBanco").innerHTML=NombreCuenta;
	//document.getElementById("txtSiglaBanco").focus();
}

function GuardarBanco(){
	var CuentaBanco=document.getElementById("txtCuentaBanco").value;	
	var CodigoCuentaBanco=document.getElementById("CodCuentaBanco").value;
	var SiglaBanco=document.getElementById("txtSiglaBanco").value;
	var NombreBanco=document.getElementById("txtNomBanco").value;
	
	var Descripcion=document.getElementById("txtDescripcion").value;
	var Tipo=document.getElementById("cmbTipo").value;
	var Estado=document.getElementById("cmbEstado").value;
	var Observacion=document.getElementById("txtObservacion").value;
	if(NombreBanco==""){
		alert("Escriba el Nombre del Banco.");
		document.getElementById("txtNomBanco").focus();
	}else{
		if(CodigoCuentaBanco==""){
			alert("Escriba la Cuenta.");
			document.getElementById("txtCuentaBanco").focus();
		}else{
			if(Tipo=="Seleccione"){
				alert("Seleccione Tipo Banco.");
				document.getElementById("cmbTipo").focus();
			}else{
				if(Estado=="Seleccione"){
					alert("Seleccione Estado.");
					document.getElementById("cmbEstado").focus();
				}else{
					
					ajax=getXMLObject();
					ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
					ajax.onreadystatechange  = function(){
						if (ajax.readyState == 4) {		
							alert(ajax.responseText);
							document.getElementById("txtCuentaBanco").value="";	
							document.getElementById("CodCuentaBanco").value="";
							document.getElementById("txtSiglaBanco").value="";
							document.getElementById("txtNomBanco").value="";						
							document.getElementById("txtDescripcion").value="";
							document.getElementById("txtObservacion").value="";
							document.getElementById("CuentaBanco").innerHTML="";
							VerBancos();
							//document.getElementById("Actualizaciones").innerHTML=ajax.responseText;
							//document.getElementById("TabDife").disabled=true;
							//VerReporteDetalleCartera(CodEnt,FecSql);
						}
					}
					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=gccb&CuentaBanco="+CuentaBanco+"&CodigoCuentaBanco="+CodigoCuentaBanco+
							"&SiglaBanco="+SiglaBanco+"&NombreBanco="+NombreBanco+"&Descripcion="+Descripcion+"&Tipo="+Tipo+
							"&Estado="+Estado+"&Observacion="+Observacion); //Posting txtname to Servle
				}
			}
		}
	}
}

function AutocompletaCuentaGasto(){
	var parametro=document.getElementById("txtCuentaGasto").value;	
	if(parametro!=""){
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				document.getElementById("CuentaGastoIng").innerHTML=ajax.responseText;
				var cong=document.getElementById("txtContadorACG").value;
				if(cong=="1"){
					//alert("ultimo registro");
					var CodCuenta=document.getElementById("txtCodCuentaACG").value;
					var NumeroCuenta=document.getElementById("txtNumeroCuentaACG").value;
					var NombreCuenta=document.getElementById("txtNombreCuentaACG").value;
					
					AsignarCuentaGasto(CodCuenta,NumeroCuenta,NombreCuenta);
				}
				
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=acg&parametro="+parametro); //Posting txtname to Servle
	}
}

function AsignarCuentaGasto(CodCuenta,NumeroCuenta,NombreCuenta){
	document.getElementById("CodCuentaGasto").value=CodCuenta;
	document.getElementById("txtCuentaGasto").value=NumeroCuenta;
	document.getElementById("CuentaGastoIng").innerHTML='';
	document.getElementById("txtTercero").focus();
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
					
					AsignarTercero(CodTercero,DocumentoTercero);
				}
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=act&parametro="+parametro); //Posting txtname to Servle
	}
}

function AsignarTercero(CodTercero,DocumentoTercero){
	document.getElementById("txtCodTercero").value=CodTercero;
	document.getElementById("txtTercero").value=DocumentoTercero;
	document.getElementById("Tercero").innerHTML='';
	document.getElementById("txtFechaInicial").focus();
}

function GuardarDiferidos(){
	var CodCuentaDife=document.getElementById("CodCuentaDife").value
	
	var CuentaDiferido=document.getElementById("txtCuentaDiferido").value
	var CuentaGasto=document.getElementById("txtCuentaGasto").value
	var Tercero=document.getElementById("txtTercero").value
	
	var CodTercero=document.getElementById("txtCodTercero").value
	var CodCuentaGasto=document.getElementById("CodCuentaGasto").value
	var FechaInicial=document.getElementById("txtFechaInicial").value;
	var FechaFinal=document.getElementById("txtFechaFinal").value;
	var Monto=document.getElementById("txtMonto").value;
	var Descripcion=document.getElementById("txtDescripcion").value;
	if(CodCuentaDife==""){
		alert("Esciba una Cuenta Valida.");
		document.getElementById("txtCuentaDiferido").focus();
	}else{
		if(CodTercero==""){
			alert("Escriba un Tercero Valido.");
			document.getElementById("txtTercero").focus();
		}else{
			if(CodCuentaGasto==""){
				alert("Escriba una Cuenta Valida");
				document.getElementById("txtCuentaGasto").focus();
			}else{
				if(FechaInicial==""){
					alert("Escriba la Fecha Inicial");
					document.getElementById("txtFechaInicial").focus();
				}else{
					if(FechaFinal==""){
						alert("Escriba la Fecha Final");
						document.getElementById("txtFechaFinal").focus();
					}else{
						if(Monto==""){
							alert("Digite Monto");
							document.getElementById("txtMonto").focus();
						}else{
							if(FechaFinal<FechaInicial){
								alert("La Fecha Inicial No Puede ser Mayor a la Fecha Final.");
							}else{
								var y=FechaInicial.split("/").length;
								var z=FechaInicial.split("/");		     	
								for(x=0; x<y-1; x=x+1)
								{ 
									FechaInicial=z[2]+"-"+z[1]+"-"+z[0];
								}
					     	
								var w=FechaFinal.split("/").length;
								var q=FechaFinal.split("/");		     	
								for(x=0; x<w-1; x=x+1)
								{ 
									FechaFinal=q[2]+"-"+q[1]+"-"+q[0];
								}
								ajax=getXMLObject();
								ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
								ajax.onreadystatechange  = function(){
									if (ajax.readyState == 4) {		
										alert(ajax.responseText);
										//document.getElementById("Creaciones").innerHTML=ajax.responseText;
										//VerReporteDetalleCartera(CodEnt,FecSql);
										Radio();
									}
								}
								ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
								ajax.send("valor=gd&CodCuentaDife="+CodCuentaDife+
										"&CodTercero="+CodTercero+
										"&CodCuentaGasto="+CodCuentaGasto+
										"&FechaInicial="+FechaInicial+
										"&FechaFinal="+FechaFinal+
										"&Monto="+Monto+
										"&Descripcion="+Descripcion+
										"&CuentaDiferido="+CuentaDiferido+
										"&CuentaGasto="+CuentaGasto+
										"&Tercero="+Tercero); //Posting txtname to Servle
							}
						}
					}
				}
			}
		}
	}
}

function ListarDiferidos(){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Actualizaciones").innerHTML=ajax.responseText;
			//document.getElementById("TabDife").disabled=true;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ldif"); //Posting txtname to Servle

}
function ListarAnioPeriodo(){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Actualizaciones").innerHTML=ajax.responseText;
			//document.getElementById("TabDife").disabled=true;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=bap"); //Posting txtname to Servle

}

function MostrarAnioPeriodo(CodAP){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Creaciones").innerHTML=ajax.responseText;
			//document.getElementById("TabDife").disabled=true;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=map&CodAP="+CodAP); //Posting txtname to Servle
}

function ActualizarAP(CodAP){
	var Bloqueo=document.getElementById("cmbBloqueo").value;
	var BloqueoCxP=document.getElementById("cmbBloqueoCxP").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			alert(ajax.responseText);
			//document.getElementById("Creaciones").innerHTML=ajax.responseText;
			//document.getElementById("TabDife").disabled=true;
			//VerReporteDetalleCartera(CodEnt,FecSql);
			Radio();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=acap&CodAP="+CodAP+"&Bloqueo="+Bloqueo+"&BloqueoCxP="+BloqueoCxP); //Posting txtname to Servle

}

/*******************************************************************/
//////////////////////////////////////////////////////////////////////////////////////
function VerActualizaciones(Accion){
	if(Accion=="cco"){ListarCentrosCosto()}
	if(Accion=="sco"){ListarSubCentrosCosto()}
	if(Accion=="gc"){ListarGrupoCuenta()}
	if(Accion=="dif"){ListarDiferidos()}
	if(Accion=="ap"){ListarAnioPeriodo()}
	if(Accion=="ccb"){ListarBancos()}
	//if(Accion==""){}
}

function ListarBancos(){
	
}

function ActualizarGruCuenta(CodGrupoCuentas){
	var NombreGruCuenta=document.getElementById("txtNomGrupoCuenta").value;
	if(NombreGruCuenta==""){
		alert("Escriba Nombre Grupo Cuenta");
	}else{		

		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				//alert(ajax.responseText);
				//document.getElementById("Creaciones").innerHTML=ajax.responseText;
				//VerReporteDetalleCartera(CodEnt,FecSql);
				Radio();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=lgc1a&NombreGruCuenta="+NombreGruCuenta+"&CodGrupoCuentas="+CodGrupoCuentas); //Posting txtname to Servle
	}
}

function ActualizarGrupoCuenta(CodGrupoCuenta){
//	alert(CodGrupoCuenta);
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Creaciones").innerHTML=ajax.responseText;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		//	Radio();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=lgc1&CodGrupoCuentas="+CodGrupoCuenta); //Posting txtname to Servle
}
function CrearGrupoCuenta(){
	var NombreGruCuenta=document.getElementById("txtNomGrupoCuenta").value;
	if(NombreGruCuenta==""){
		alert("Escriba Nombre Grupo Cuenta");
	}else{		

		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				alert(ajax.responseText);
				//document.getElementById("Creaciones").innerHTML=ajax.responseText;
				//VerReporteDetalleCartera(CodEnt,FecSql);
				Radio();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=cgc&NombreGruCuenta="+NombreGruCuenta); //Posting txtname to Servle
	}
}


function GenerarGerencialF(){
	//var Anio = document.getElementById("txtAnio").value;
	
	//var Meses=document.getElementById('MesSeleccionado').value;
	var FechaInicial=document.getElementById("txtFechaInicial").value;
	var FechaFinal=document.getElementById("txtFechaFinal").value;
	
	ajax1=getXMLObject();
	ajax1.open("POST","ControlCuentas",true); //getname will be the servlet name
	document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax1.onreadystatechange  = function(){
		if (ajax1.readyState == 4) {
			
			document.getElementById("FormTo").innerHTML=ajax1.responseText;
		}
	}
	//alert("valor=UCRN&CodEnt="+CodEnt1+"&Fecha="+FecSql); //Posting txtname to Servle
	ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	//ajax1.send("valor=GRG&Anio="+Anio+"&Meses="+Meses); //Posting txtname to Servle
	ajax1.send("valor=GRGF&FechaInicial="+FechaInicial+"&FechaFinal="+FechaFinal); //Posting txtname to Servle

}

function GenerarGerencial(){
	var Anio = document.getElementById("txtAnio").value;
	
	var Meses=document.getElementById('MesSeleccionado').value;
	//var FechaInicial=document.getElementById("txtFechaInicial").value;
	//var FechaFinal=document.getElementById("txtFechaFinal").value;
	
	ajax1=getXMLObject();
	ajax1.open("POST","ControlCuentas",true); //getname will be the servlet name
	document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
	ajax1.onreadystatechange  = function(){
		if (ajax1.readyState == 4) {
			
			document.getElementById("FormTo").innerHTML=ajax1.responseText;
		}
	}
	//alert("valor=UCRN&CodEnt="+CodEnt1+"&Fecha="+FecSql); //Posting txtname to Servle
	ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax1.send("valor=GRG&Anio="+Anio+"&Meses="+Meses); //Posting txtname to Servle
	//ajax1.send("valor=GRG&FechaInicial="+FechaInicial+"&FechaFinal="+FechaFinal); //Posting txtname to Servle

}

function MesSele(){	
    var c=document.getElementById('txtCont').value;
	var oscar=c;
	var CodigoConcat="";
 if(c!=1){
	for(var i=0; i<=c-1; i++){		
      if (form1.checkbo[i].checked) {
       	 d=form1.checkbo[i].id;
       	CodigoConcat=CodigoConcat+":"+d;
     }     	  
      oscar--; 
     }	
	document.getElementById('MesSeleccionado').value=CodigoConcat;
 } 

}
 //fin de la funcion 

function RadioRGE(){	 
	var p="<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr bgcolor='grey' ><td colspan='8'  align='center' class='styleFont' >REPORTE GERENCIAL POR MES </td></tr> " +
			"<tr><td class='style6'>SELECCIONE LOS MESES A GENERAR </td> " +
			"<td class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='01' value='checkbox' />Enero</label></td> " +
			"<td class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='02' value='checkbox' />Febrero</label></td> " +
			"<td class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='03' value='checkbox' />Marzo</label></td> " +
			"<td class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='04' value='checkbox' />Abril</label></td> " +
			"<td class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='05' value='checkbox' />Mayo</label></td>" +
			"<td class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='06' value='checkbox' />Junio</label></td>" +
			"<td>&nbsp;</td></tr> " +
			"<tr><td>&nbsp;</td>" +
			"<td width='10%' class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='07' value='checkbox' />Julio</label></td>" +
			"<td width='11%' class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='08' value='checkbox' />Agosto</label></td>" +
			"<td width='10%' class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='09' value='checkbox' />Septiembre</label></td>" +
			"<td width='8%' class='style6'><label><input type='checkbox'  onclick='MesSele()' name='checkbo' id='10' value='checkbox' />Octubre</label></td>" +
			"<td width='10%' class='style6'><label><input type='checkbox' onclick='MesSele()' name='checkbo' id='11' value='checkbox' />Noviembre</label></td>" +
			"<td width='9%' class='style6'><label><input type='checkbox'  onclick='MesSele()' name='checkbo' id='12' value='checkbox' />Diciembre</label></td><td width='15%'>&nbsp;</td></tr> " +
			"<tr><td class='style6'>SELECCIONE EL A\u00d1O </td><td colspan='7'><input name='txtAnio' type='text' id='txtAnio' maxlength='4' value='2014' /><input name='MesSeleccionado' type='hidden' id='MesSeleccionado' /><input name='txtCont' type='hidden' id='txtCont' value='12' /><input type='button' name='Button' onclick='GenerarGerencial()' value='          Generar          ' /></td></tr> " +
			"<tr><td colspan='8'></td></tr></table>";
	p=p+"<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr bgcolor='grey' ><td colspan='8'  align='center' class='styleFont' >REPORTE GERENCIAL POR RANGO DE FECHAS </td></tr> ";
	 p=p+"<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr class='style6'><td>FECHA INICIAL:<input name='txtFechaInicial' type='text' id='txtFechaInicial' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10' /></td><td>FECHA FINAL: <input name='txtFechaInicial' type='text' id='txtFechaFinal' onkeyup='masca(this,&quot;/&quot;,patron,true)' maxlength='10' /></td><td><input type='button' name='Button' onclick='GenerarGerencialF()' value='          Generar          ' /></td></tr></table>";
	//"<tr><td colspan='2' align='center'><input type='button' onclick='VerReporteCarteraRadicadaConsolidada()' value='Generar Reporte'></td></tr></table>";

document.getElementById('Creaciones').innerHTML = (p);
}
function ListarCentrosCosto(){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Actualizaciones").innerHTML=ajax.responseText;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=lcco"); //Posting txtname to Servle

}

function ActualizarCCosto(CodCCosto){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Creaciones").innerHTML=ajax.responseText;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=lcco1&CodCCosto="+CodCCosto); //Posting txtname to Servle

}

function ActualizarSubCCosto(CodSubCCosto){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Creaciones").innerHTML=ajax.responseText;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=lsco1&CodSubCCosto="+CodSubCCosto); //Posting txtname to Servle

}
function ActualizarCentroCosto(CodCCosto){
	var CentroCosto=document.getElementById("txtNomCentroCosto").value;
	if(CentroCosto==""){
		alert("Escriba Nombre Centro Costo");
	}else{		

		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				//alert(ajax.responseText);
				//document.getElementById("Creaciones").innerHTML=ajax.responseText;
				//VerReporteDetalleCartera(CodEnt,FecSql);
				Radio();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=lcco1a&CodCCosto="+CodCCosto+"&NomCentroCosto="+CentroCosto); //Posting txtname to Servle
	}
}

function ActualizarSubCentroCosto(CodSubCCosto){
	var SubcentroCosto=document.getElementById("txtNomSubCentroCosto").value;
	if(SubcentroCosto==""){
		alert("Escriba Nombre Subentro Costo");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				Radio()
				//document.getElementById("Muni").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=lsco1a&SubcentroCosto="+SubcentroCosto+"&CodSubCCosto="+CodSubCCosto); //Posting txtname to Servle

	}
}

//function

function ListarSubCentrosCosto(){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Actualizaciones").innerHTML=ajax.responseText;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=lsco"); //Posting txtname to Servle
}

function ListarGrupoCuenta(){
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("Actualizaciones").innerHTML=ajax.responseText;
			//VerReporteDetalleCartera(CodEnt,FecSql);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=lgc"); //Posting txtname to Servle
}

function GuardarCentroSubcentre(){
	var CodCCosto=document.getElementById("cmbCentroCosto").value;
	var CodSubCCosto=document.getElementById("cmbSubCentroCosto").value;
	if(CodCCosto=="Seleccione"){
		alert("Seleccione Centro de Costo");
		document.getElementById("cmbCentroCosto").focus();
	}else{
		if(CodSubCCosto=="Seleccione"){
			alert("Seleccione Subcentro de Costo");
			document.getElementById("cmbSubCentroCosto").focus();
		}else{
			ajax=getXMLObject();
			ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {		
					alert(ajax.responseText);
					//document.getElementById("resultado").innerHTML=ajax.responseText;
					//VerReporteDetalleCartera(CodEnt,FecSql);
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=rcsG&CodCCosto="+CodCCosto+"&CodSubCCosto="+CodSubCCosto); //Posting txtname to Servle

		}
	}
}

function GuardarCentroSucursal(){
	var CodCCosto=document.getElementById("cmbCentroCosto").value;
	var CodSucursal=document.getElementById("cmbSucursal").value;
	if(CodCCosto=="Seleccione"){
		alert("Seleccione Centro de Costo");
		document.getElementById("cmbCentroCosto").focus();
	}else{
		if(CodSucursal=="Seleccione"){
			alert("Seleccione Sucursal");
			document.getElementById("cmbSucursal").focus();
		}else{
			ajax=getXMLObject();
			ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {		
					alert(ajax.responseText);
					//document.getElementById("resultado").innerHTML=ajax.responseText;
					//VerReporteDetalleCartera(CodEnt,FecSql);
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=rsccG&CodCCosto="+CodCCosto+"&CodSucursal="+CodSucursal); //Posting txtname to Servle

		}
	}
}

function GenerarCarteraRadicadaDetallada(){
	var FechaE=document.getElementById("txtFechaEmision").value;
	var CodEnt=document.getElementById("cmbEntidades").value;
	var FecSql="";
	if(CodEnt=="Seleccione"){
		alert("Seleccione Entidad.");
		document.getElementById("cmbEntidades").focus();
	}else{
		if(FechaE==""){
			alert("Escriba Fecha.");
			document.getElementById("cmbEntidades").focus();
		}else{
			var y=FechaE.split("/").length;
	     	var z=FechaE.split("/");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		FecSql=z[2]+"-"+z[1]+"-"+z[0];
		    }
	     	
			ajax=getXMLObject();
			ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
			if(document.getElementById("chkNit").checked){Ch="1";}else{Ch="0";}
			if(Ch=="0"){
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {		
						//	alert("FecSql "+FecSql+" CodEnt "+CodEnt);
						//alert(ajax.responseText);
						//document.getElementById("resultado").innerHTML=ajax.responseText;
						VerReporteDetalleRadicadoCartera(CodEnt,FecSql);
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=gcd&CodEnt="+CodEnt+"&FechaE="+FecSql); //Posting txtname to Servle
			}
			
			if(Ch=="1"){
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {		
						var CodEnt1=ajax.responseText;
						VerReporteDetalleRadicadoCarteraAgr(CodEnt1,FecSql);
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=obn&CodEnt="+CodEnt+"&FechaE="+FecSql); //Posting txtname to Servle
			}
			
		}
	}
}

function GenerarCarteraHistorico(){
	var FechaE=document.getElementById("txtFechaEmision").value;
	var CodEnt=document.getElementById("cmbEntidades").value;
	var TipoReporte=document.getElementById("TipoReporte").value;
	var exa = document.form1.cmbEntidades.selectedIndex;
    var NombreEntidad=document.form1.cmbEntidades.options[exa].text;
	var FecSql="";
	if(CodEnt=="Seleccione"){
		alert("Seleccione Entidad.");
		document.getElementById("cmbEntidades").focus();
	}else{
		if(FechaE==""){
			alert("Escriba Fecha.");
			document.getElementById("cmbEntidades").focus();
		}else{
			var y=FechaE.split("/").length;
	     	var z=FechaE.split("/");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		FecSql=z[2]+"-"+z[1]+"-"+z[0];
		    }
	     	
	     	ajax=getXMLObject();
	     	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	     	if(document.getElementById("chkNit").checked){
	     		//Ch="1";	     		
	     		ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {		
						var CodEnt1=ajax.responseText;
						//alert(CodEnt1);
						if(TipoReporte=="Emitida"){
							window.location.href="cont_HistoricoCarteraUnido.jsp?Nit="+CodEnt1+"&Fecha="+FecSql;
						}
						if(TipoReporte=="Radicada"){
							//alert("Radicada Unida");
							ajax1=getXMLObject();
							ajax1.open("POST","ControlCuentas",true); //getname will be the servlet name
							document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
							ajax1.onreadystatechange  = function(){
								if (ajax1.readyState == 4) {
									//*
									document.getElementById("FormTo").innerHTML=ajax1.responseText;
								}
							}
							//alert("valor=UCRN&CodEnt="+CodEnt1+"&Fecha="+FecSql); //Posting txtname to Servle
							ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
							ajax1.send("valor=UCRN&CodEnt="+CodEnt1+"&Fecha="+FecSql); //Posting txtname to Servle
						}
						
						//VerReporteDetalleCarteraAgrupar(CodEnt,FecSql);
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=obn&CodEnt="+CodEnt); //Posting txtname to Servle
	     		
	     	}else{
	     		//Ch="0";
	     		if(TipoReporte=="Emitida"){
	     			//window.location.href="cont_HistoricoCartera.jsp?Nit="+CodEnt+"&Fecha="+FecSql;
	     			document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
					ajax.onreadystatechange  = function(){
						if (ajax.readyState == 4) {
							document.getElementById("FormTo").innerHTML=ajax.responseText;
						}
					}
					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=CEMN&CodEnt="+CodEnt+"&Fecha="+FecSql+"&NombreEntidad="+NombreEntidad); //Posting txtname to Servle
	     		}
	     		if(TipoReporte=="Radicada"){
					//alert("Radicada Independiente ");
					document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';

					ajax.onreadystatechange  = function(){
						if (ajax.readyState == 4) {
							//*
							document.getElementById("FormTo").innerHTML=ajax.responseText;
						}
					}
					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=CRN&CodEnt="+CodEnt+"&Fecha="+FecSql+"&NombreEntidad="+NombreEntidad); //Posting txtname to Servle
	     		}
	     		
	     	}
	     	
				//ajax.send("valor=ch&CodEnt="+CodEnt+"&FechaE="+FecSql); //Posting txtname to Servle
		}
	}
}

//////////////////////////////////////////////////////////////////////////////////////
function GenerarCarteraDetallada(){
	var FechaE=document.getElementById("txtFechaEmision").value;
	var CodEnt=document.getElementById("cmbEntidades").value;
	var FecSql="";
	if(CodEnt=="Seleccione"){
		alert("Seleccione Entidad.");
		document.getElementById("cmbEntidades").focus();
	}else{
		if(FechaE==""){
			alert("Escriba Fecha.");
			document.getElementById("cmbEntidades").focus();
		}else{
			var y=FechaE.split("/").length;
	     	var z=FechaE.split("/");		     	
	     	for(x=0; x<y-1; x=x+1)
	     	{ 
	     		FecSql=z[2]+"-"+z[1]+"-"+z[0];
		    }
	     	
	     	ajax=getXMLObject();
			ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
			
	     	var Ch="";
	     	if(document.getElementById("chkNit").checked){Ch="1";}else{Ch="0";}
	     	if(Ch=="1"){
	     		//agrupados
	     		ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {		
						var CodEnt1=ajax.responseText;
						VerReporteDetalleCarteraAgrupar(CodEnt1,FecSql);
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=obn&CodEnt="+CodEnt+"&FechaE="+FecSql); //Posting txtname to Servle
	     	}
	     	
			
			if(Ch=="0"){
				//sin agrupar
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {		
						//	alert("FecSql "+FecSql+" CodEnt "+CodEnt);
						//alert(ajax.responseText);
						//document.getElementById("resultado").innerHTML=ajax.responseText;
						VerReporteDetalleCartera(CodEnt,FecSql);
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=gcd&CodEnt="+CodEnt+"&FechaE="+FecSql); //Posting txtname to Servle
			}
			
		}
	}
}
/*
function GenerarCarteraGeneral(){
	var CodEnt=document.getElementById("cmbEntidades").value;
	if(CodEnt=="Seleccione"){
		alert("Seleccione Entidad.");
		document.getElementById("cmbEntidades").focus();
	}else{			
		ajax=getXMLObject();
		ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				alert("CodEnt "+CodEnt);
				//alert(ajax.responseText);
				//document.getElementById("resultado").innerHTML=ajax.responseText;
				VerReporteCarteraGeneral();
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=gc2d&CodEnt="+CodEnt); //Posting txtname to Servle
			
	}
}*/

function SincronizarCarteraGeneral(){
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			alert(ajax.responseText)
			//alert("Cartera Sincronizada.");
			//alert(ajax.responseText);
			document.getElementById("Creaciones").innerHTML=ajax.responseText;
			//VerReporteCarteraGeneral();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ENCU"); //Posting txtname to Servle
}

function DepurarCarteraEntidad(){
	var CodEps=document.getElementById("cmbEmpresas").value;
	//alert(CodEps)
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			alert("Cartera Sincronizada.");
			//alert(ajax.responseText);
			//document.getElementById("resultado").innerHTML=ajax.responseText;
			//VerReporteCarteraGeneral();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=DCE&CodEnt="+CodEps); //Posting txtname to Servle
}

function SincronizarCarteraGeneralxx(){
	ajax=getXMLObject();
	ajax.open("POST","ControlCuentas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			alert("Cartera Sincronizada.");
			//alert(ajax.responseText);
			//document.getElementById("resultado").innerHTML=ajax.responseText;
			//VerReporteCarteraGeneral();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=SCG"); //Posting txtname to Servle
}

function VerReporteCarteraGeneral(){
	var FechaE=document.getElementById("txtFechaEmision").value;
	var y=FechaE.split("/").length;
 	var z=FechaE.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
 		FecSql=z[2]+"-"+z[1]+"-"+z[0];
    }
 	var opcion=confirm("Desea Mostrar el Reporte en Excel?");
	if(opcion){
		//en excel
		pagina="cont_ReporteCarteraGeneralExcel.jsp?FechaE="+FecSql;		
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	}else{
		//en pdf
		pagina="cont_ReporteCarteraGeneral.jsp?FechaE="+FecSql;		
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}
}

function VerReporteDetalleCarteraAgrupar(CodEnt,FecSql){
	
		//en excel
		pagina="cont_ReporteCarteraDetalladaExcelAgr.jsp?CodEnt="+CodEnt+"&FechaE="+FecSql;		
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);		

}

function VerReporteDetalleCartera(CodEnt,FecSql){
	var opcion=confirm("Desea Mostrar el Reporte en Excel?");
	if(opcion){
		//en excel
		pagina="cont_ReporteCarteraDetalladaExcel.jsp?CodEnt="+CodEnt+"&FechaE="+FecSql;		
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);		
	}else{
		// en pdf
		pagina="cont_ReporteCarteraDetallada.jsp?CodEnt="+CodEnt+"&FechaE="+FecSql;		
		var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
		opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
		window.open(pagina,"NUEVA",opciones);
	}
}

function VerReporteDetalleRadicadoCarteraAgr(CodEnt,FecSql){	
		//en excel
		pagina="cont_ReporteCarteraDetalladaRadicadaExcelA.jsp?CodEnt="+CodEnt+"&FechaE="+FecSql;		
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
}

function VerReporteDetalleRadicadoCartera(CodEnt,FecSql){
	var opcion=confirm("Desea Mostrar el Reporte en Excel?");
	if(opcion){
		//en excel
		pagina="cont_ReporteCarteraDetalladaRadicadaExcel.jsp?CodEnt="+CodEnt+"&FechaE="+FecSql;		
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
	}else{
		//en pdf
	pagina="cont_ReporteCarteraDetalladaRadicada.jsp?CodEnt="+CodEnt+"&FechaE="+FecSql;		
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
	}
}
//////////////////////////////////////////////////////////////////////////////////////
function BuscarReciboCaja(){
	var Parametro=document.getElementById("txtParametro").value;
	if(Parametro==""){
		alert("Digite el Parametro");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				//alert(ajax.responseText);
				document.getElementById("resultado").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CoRC&Parametro="+Parametro); //Posting txtname to Servle

	}
}

function OmitirReciboDeCaja(CodRecCaja){
	//alert("CodRecCaja 1 "+CodRecCaja);
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("AnularRCObsrv").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=OmRCT1&CodRecCaja="+CodRecCaja); //Posting txtname to Servle

}

function OmitirReciboDeCajaTotal(CodRecCaja){
	var MotivoAnulacion=document.getElementById("txtMotivoAnulacion").value;
	var CodUsuario=document.getElementById("CodUsuario").value;
	//alert("CodRecCaja "+CodRecCaja);
	if(MotivoAnulacion==""){
		alert("Escriba el Motivo de la Anulacion.");
		document.getElementById("txtMotivoAnulacion").focus();
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				alert(ajax.responseText);
				//document.getElementById("AnularRCObsrv").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=OmRCT&CodRecCaja="+CodRecCaja+"&MotivoAnulacion="+MotivoAnulacion+"&CodUsuario="+CodUsuario); //Posting txtname to Servle
	}
}

function OmitirNotaCredito(CodDetalleFactura){
	var CodUsuario=document.getElementById("CodUsuario").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
			//alert(ajax.responseText);
			document.getElementById("AnularRCObsrv").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ONC1&CodDetalleFactura="+CodDetalleFactura+"&CodUsuario="+CodUsuario); //Posting txtname to Servle

}
function OmitirNotaCreditoTotal(CodDetalleFactura){
	var MotivoAnulacion=document.getElementById("txtMotivoAnulacion").value;
	var CodUsuario=document.getElementById("CodUsuario").value;
	if(MotivoAnulacion==""){
		alert("Escriba el Motivo de la Anulacion.");
		document.getElementById("txtMotivoAnulacion").focus();
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				alert(ajax.responseText);
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ONC&CodDetalleFactura="+CodDetalleFactura+"&CodUsuario="+CodUsuario+"&MotivoAnulacion="+MotivoAnulacion); //Posting txtname to Servle
	}

}
function BuscarNotaCredito1(){
	//alert();
	var Parametro=document.getElementById("txtParametro").value;
	var NumeroNotaCredito="";
	var NumeroFactura="";
	if(document.getElementById("nnc").checked){NumeroNotaCredito="0";}else{NumeroNotaCredito="1";}
	if(document.getElementById("nf").checked){NumeroFactura="0";}else{NumeroFactura="1";}
	if((NumeroNotaCredito=="0") && (NumeroFactura=="0")){
		alert("Seleccione Una Sola Opcion.");
	}else{
		if(Parametro==""){
			alert("Digite el Parametro");
			document.getElementById("txtParametro").focus();
		}else{
			if((NumeroNotaCredito=="1") && (NumeroFactura=="1")){
				alert("Seleccione El Tipo de Parametro.");
			}else{				
				ajax=getXMLObject();
				ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {		
						//alert(ajax.responseText);
						document.getElementById("resultado").innerHTML=ajax.responseText;
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=CoNT&Parametro="+Parametro+"&NumeroNotaCredito="+NumeroNotaCredito+
						"&NumeroFactura="+NumeroFactura); //Posting txtname to Servle
			}
		}
	}	

}

function VerReporteDetalleCobro(CodDetFact,CodFact){
	pagina="cont_ReporteRecibos.jsp?CodFact="+CodFact+"&CodDetFact="+CodDetFact;		
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
    window.open(pagina,"NUEVA",opciones);
}

function ImprimirReciboCaja(CodRecCaj){
	
	pagina="cont_ReporteRecibosCaja.jsp?CodRecCaj="+CodRecCaj;	
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}

//////////////////////////////////////////////////////////////////////////////////////
function CrearSucursal(){
	var NombreSucursal=document.getElementById("txtNombreSu").value;
	var DireSucursal=document.getElementById("txtDireSuc").value;
	var Departamento=document.getElementById("cmbDepartamento").selectedIndex;
	Departamento=document.getElementById("cmbDepartamento").options[Departamento].text
	
	var Municipio=document.getElementById("cmbMunicipio").selectedIndex;
	Municipio=document.getElementById("cmbMunicipio").options[Municipio].text

	var Telefono1=document.getElementById("txtTelefono1").value;
	var Telefono2=document.getElementById("txtTelefono2").value;
	if(NombreSucursal==""){
		alert("Digite Sucursal");
		document.getElementById("txtNombreSu").focus();
	}else{
		if(DireSucursal==""){
			alert("Digite Direccion Sucursal");
			document.getElementById("txtDireSuc").focus();
		}else{
			if(Departamento=="Seleccione"){
				alert("Seleccione Departamento");
				document.getElementById("cmbDepartamento").focus();
			}else{
				if(Municipio=="Seleccione"){
					alert("Seleccione Municipio");
					document.getElementById("cmbMunicipio").focus();
				}else{
					if(Telefono1==""){
						alert("Digite Telefono ");
						document.getElementById("txtTelefono1").focus();
					}else{
						ajax=getXMLObject();
						ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {		
								alert(ajax.responseText);
								//document.getElementById("Muni").innerHTML=ajax.responseText;
							}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=csu&NombreSucursal="+NombreSucursal+"&DireSucursal="+DireSucursal+
								"&Departamento="+Departamento+"&Municipio="+Municipio+
								"&Telefono1="+Telefono1+"&Telefono2="+Telefono2); //Posting txtname to Servle

					}
				}
			}
		}
	}
	
}

function CrearAPeriodo(){
	var Anio=document.getElementById("txtAnio").value;
	var Periodo=document.getElementById("txtPeriodo").value;
	var Bloqueo=document.getElementById("cmbBloqueo").value;
	var BloqueoCxP=document.getElementById("cmbBloqueoCxP").value;
	var NombreAPeriodo=document.getElementById("txtNombreAPeriodo").value;
	if(Anio==""){
		alert("Digite el Año.");
		document.getElementById("txtAnio").focus();
	}else{
		if(Periodo==""){
			alert("Digite el Periodo");
			document.getElementById("txtPeriodo").focus();
		}else{
			if(Bloqueo=="Seleccione"){
				alert("Seleccione Bloqueo Contable.");
				document.getElementById("cmbBloqueo").focus();
			}else{
				if(NombreAPeriodo==""){
					alert("Digite Nombre del Periodo.");
					document.getElementById("txtNombreAPeriodo").focus();
				}else{
					if(BloqueoCxP=="Seleccione"){
						alert("Seleccione Bloqueo CxP.");
					}else{
						ajax=getXMLObject();
						ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
						ajax.onreadystatechange  = function(){
							if (ajax.readyState == 4) {		
								alert(ajax.responseText);
								//document.getElementById("Muni").innerHTML=ajax.responseText;
							}
						}
						ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
						ajax.send("valor=cap&Anio="+Anio+"&Periodo="+Periodo+
								"&Bloqueo="+Bloqueo+"&NombreAPeriodo="+NombreAPeriodo+"&BloqueoCxP="+BloqueoCxP); //Posting txtname to Servle

					}
				}
			}
		}
	}
}

function CrearCentroCosto(){
	var CentroCosto=document.getElementById("txtNomCentroCosto").value;
	if(CentroCosto==""){
		alert("Escriba Nombre Centro Costo");
	}else{		
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {		
				alert(ajax.responseText);
				Radio();
				//document.getElementById("Muni").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ccco&NomCentroCosto="+CentroCosto); //Posting txtname to Servle

	}	
}

function CrearSubCentroCosto(){
	var SubcentroCosto=document.getElementById("txtNomSubCentroCosto").value;
	if(SubcentroCosto==""){
		alert("Escriba Nombre Subentro Costo");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
				Radio();
				//document.getElementById("Muni").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=csco&SubcentroCosto="+SubcentroCosto); //Posting txtname to Servle

	}	
}
//

function Municipio(){
	var CodDept=document.getElementById("cmbDepartamento").value;
	var Municipios=document.getElementById("Muni");
	///alert(CodDept);
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {		
		//	alert(ajax.responseText);
			document.getElementById("Muni").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Mu&CodDept="+CodDept); //Posting txtname to Servle
}

function BuscarProveedor(){
	var tipo_identificacion=document.getElementById("cmbTipoDoc").value;
	var numero_documento=document.getElementById("txtNumDoc").value;
	if(tipo_identificacion=="Seleccione"){
		alert("Seleccione Tipo De Documento.");
	}else{
		if(numero_documento==""){
			alert("Digite Numero De Identificacion.");
		}
	}
	if((tipo_identificacion!="Seleccione")&&(numero_documento!="")){
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {				
				document.getElementById("Contenido").innerHTML=ajax.responseText;
				document.getElementById("txtNombre1").focus();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&tipo_identificacion="+tipo_identificacion+"&numero_documento="+numero_documento); //Posting txtname to Servle
	}
}

function NombreRazonSocial (){
	var Nombre1=document.getElementById("txtNombre1").value;//
	var Nombre2=document.getElementById("txtNombre2").value;//
	var Apellido1=document.getElementById("txtApellido1").value;//
	var Apellido2=document.getElementById("txtApellido2").value;//
	var Union=Nombre1+" "+Nombre2+" "+Apellido1+" "+Apellido2;
	
	var RazonNombre=document.getElementById("txtRazonNombre").value=Union;
	
}

function autocompletaBanco(){
	var NomBanco=document.getElementById("txtBanco").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("banco").innerHTML=ajax.responseText;
			var Contador=document.getElementById("txtCont").value;
			if(Contador=="1"){
				var CodBanco=document.getElementById("txtCodBanco").value;
				var NombrBanco=document.getElementById("txtNombBanco").value;
				SeleccionarBanco(CodBanco,NombrBanco)
				//NomBanco=document.getElementById("txtNombBanco").value;
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=abc&NomBanco="+NomBanco); //Posting txtname to Servle

}

function SeleccionarBanco(CodBanco,NombrBanco){
	document.getElementById("txtBanco").value=NombrBanco;
	document.getElementById("txtCodBanco").value=CodBanco;
	document.getElementById("banco").style.display = "none";
}

function ActualizarProveedor(CodTercero){
	var tipo_identificacion=document.getElementById("cmbTipoDoc").value;//
	var numero_documento=encodeURIComponent(document.getElementById("txtNumDoc").value);//
	var nombre_razonsoc=encodeURIComponent(document.getElementById("txtRazonNombre").value);//
	var Nombre1=encodeURIComponent(document.getElementById("txtNombre1").value);//
	var Nombre2=encodeURIComponent(document.getElementById("txtNombre2").value);//
	var Apellido1=encodeURIComponent(document.getElementById("txtApellido1").value);//
	var Apellido2=encodeURIComponent(document.getElementById("txtApellido2").value);//
	var RazonSocialDian=encodeURIComponent(document.getElementById("txtRazonSocialDian").value);//
	var OperExt=document.getElementById("cmbOpExt").value;//
	var direccion=encodeURIComponent(document.getElementById("txtDireccion").value);//
	var telefono=encodeURIComponent(document.getElementById("txtTelefono").value);//
	var telcontacto=encodeURIComponent(document.getElementById("txtTelefonoContacto").value);//
	var emailcontac=encodeURIComponent(document.getElementById("txtEmailContac").value);
	
	/*******************************************************************************/
	var txtPorcretIva=document.getElementById("txtPorcretIva").value;
	var txtPorcretIca=document.getElementById("txtPorcretIca").value;
	var txtDiasPlazo=document.getElementById("txtDiasPlazo").value;
	var cmbEstado=document.getElementById("cmbEstado").value;
	var cmbAutoRetenedor=document.getElementById("cmbAutoRetenedor").value;
	var txtFechaIngreso=document.getElementById("txtFechaIngreso").value;
	var txtPagWeb=document.getElementById("txtPagWeb").value;
	var txtLineaProducto=document.getElementById("txtLineaProducto").value;
	var txtNumCuenta=document.getElementById("txtNumCuenta").value;
	var txtBanco=document.getElementById("txtCodBanco").value;
	var cmbTipoCuenta=document.getElementById("cmbTipoCuenta").value;
	/*******************************************************************************/
	var IndCliente="";
	var IndProveedor="";
	var IndEmpleado="";
	//var Departamento=h.cmbarea.selectedIndex;
	 // ;
	var Departamento=document.getElementById("cmbDepartamento").selectedIndex;
	Departamento=document.getElementById("cmbDepartamento").options[Departamento].text
	
	var Municipio=document.getElementById("cmbMunicipio").selectedIndex;
	Municipio=document.getElementById("cmbMunicipio").options[Municipio].text
	
	if(document.getElementById("chk").checked){IndCliente="0";}else{IndCliente="1";}
	if(document.getElementById("chk2").checked){IndProveedor="0";}else{IndProveedor="1";}
	if(document.getElementById("chk3").checked){IndEmpleado="0";}else{IndEmpleado="1";}
	//alert("Departamento "+Departamento+" Municipio "+Municipio);
	//if((nombre_razonsoc!="")){
	if(Nombre1==""){
		alert("Digite Nombre");
		document.getElementById("txtNombre1").focus();
	}else{
		if(Apellido1==""){
			alert("Digite Apellido");
			document.getElementById("txtApellido1").focus();
		}else{
			if(RazonSocialDian==""){
				alert("Digite Razon Social Dian");
				document.getElementById("txtRazonSocialDian").focus();
			}else{
				if(direccion==""){
					alert("Digite Direccion");
					document.getElementById("txtDireccion").focus();
				}else{
					if(telefono==""){
						alert("Digite Telefono");
						document.getElementById("txtTelefono").focus();
					}else{
						if(Departamento=="Seleccione"){
							alert("Seleccione Departamento");
							document.getElementById("cmbDepartamento").focus();
						}else{
							if(Municipio=="Seleccione"){
								alert("Seleccione Municipio");
								document.getElementById("cmbMunicipio").focus();
							}else{
								ajax=getXMLObject();
								ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
								ajax.onreadystatechange  = function(){
									if (ajax.readyState == 4) {				
										alert(ajax.responseText);
									}
								}
								ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
								ajax.send("valor=1.11&tipo_identificacion="+tipo_identificacion+
										"&numero_documento="+numero_documento+
										"&nombre_razonsoc="+nombre_razonsoc+
										"&Nombre1="+Nombre1+
										"&Nombre2="+Nombre2+
										"&Apellido1="+Apellido1+
										"&Apellido2="+Apellido2+				
										"&RazonSocialDian="+RazonSocialDian+
										"&OperExt="+OperExt+
										"&direccion="+direccion+
										"&telefono="+telefono+
										"&telcontacto="+telcontacto+
										"&emailcontac="+emailcontac+
										"&IndCliente="+IndCliente+
										"&IndProveedor="+IndProveedor+
										"&IndEmpleado="+IndEmpleado+
										"&Departamento="+Departamento+
										"&Municipio="+Municipio+
										"&CodTercero="+CodTercero+
										"&txtBanco="+txtBanco+
										"&cmbTipoCuenta="+cmbTipoCuenta+
										"&cmbEstado="+cmbEstado+
										"&txtPorcretIva="+txtPorcretIva+
										"&txtFechaIngreso="+txtFechaIngreso+
										"&txtPorcretIca="+txtPorcretIca+
										"&txtPagWeb="+txtPagWeb+
										"&txtDiasPlazo="+txtDiasPlazo+
										"&txtLineaProducto="+txtLineaProducto+
										"&cmbAutoRetenedor="+cmbAutoRetenedor+
										"&txtNumCuenta="+txtNumCuenta); //Posting txtname to Servle
							}
						}
					}
				}
			}
		}
	}

}

function GuardarProveedor(){	
	var tipo_identificacion=document.getElementById("cmbTipoDoc").value;//
	var numero_documento=encodeURIComponent(document.getElementById("txtNumDoc").value);//
	var nombre_razonsoc=encodeURIComponent(document.getElementById("txtRazonNombre").value);//
	var Nombre1=encodeURIComponent(document.getElementById("txtNombre1").value);//
	var Nombre2=encodeURIComponent(document.getElementById("txtNombre2").value);//
	var Apellido1=encodeURIComponent(document.getElementById("txtApellido1").value);//
	var Apellido2=encodeURIComponent(document.getElementById("txtApellido2").value);//
	var RazonSocialDian=encodeURIComponent(document.getElementById("txtRazonSocialDian").value);//
	var OperExt=document.getElementById("cmbOpExt").value;//
	var direccion=encodeURIComponent(document.getElementById("txtDireccion").value);//
	var telefono=encodeURIComponent(document.getElementById("txtTelefono").value);//
	var telcontacto=encodeURIComponent(document.getElementById("txtTelefonoContacto").value);//
	var emailcontac=encodeURIComponent(document.getElementById("txtEmailContac").value);
	/*********************************************************************************/

	
	var txtBanco=document.getElementById("txtCodBanco").value
	var cmbTipoCuenta=document.getElementById("cmbTipoCuenta").value
	var cmbEstado=document.getElementById("cmbEstado").value
	var txtPorcretIva=document.getElementById("txtPorcretIva").value
	var txtFechaIngreso=document.getElementById("txtFechaIngreso").value
	var txtPorcretIca=document.getElementById("txtPorcretIca").value
	var txtPagWeb=document.getElementById("txtPagWeb").value
	var txtDiasPlazo=document.getElementById("txtDiasPlazo").value
	var txtLineaProducto=document.getElementById("txtLineaProducto").value
	var cmbAutoRetenedor=document.getElementById("cmbAutoRetenedor").value
	var txtNumCuenta=document.getElementById("txtNumCuenta").value
	/*********************************************************************************/
	var IndCliente="";
	var IndProveedor="";
	var IndEmpleado="";
	//var Departamento=h.cmbarea.selectedIndex;
	 // ;
	var Departamento=document.getElementById("cmbDepartamento").selectedIndex;
	Departamento=document.getElementById("cmbDepartamento").options[Departamento].text
	
	var Municipio=document.getElementById("cmbMunicipio").selectedIndex;
	Municipio=document.getElementById("cmbMunicipio").options[Municipio].text
	
	if(document.getElementById("chk").checked){IndCliente="0";}else{IndCliente="1";}
	if(document.getElementById("chk2").checked){IndProveedor="0";}else{IndProveedor="1";}
	if(document.getElementById("chk3").checked){IndEmpleado="0";}else{IndEmpleado="1";}
	//alert("Departamento "+Departamento+" Municipio "+Municipio);
	//if((nombre_razonsoc!="")){
	if(Nombre1==""){
		alert("Digite Nombre");
		document.getElementById("txtNombre1").focus();
	}else{
		if(Apellido1==""){
			alert("Digite Apellido");
			document.getElementById("txtApellido1").focus();
		}else{
			if(RazonSocialDian==""){
				alert("Digite Razon Social Dian");
				document.getElementById("txtRazonSocialDian").focus();
			}else{
				if(direccion==""){
					alert("Digite Direccion");
					document.getElementById("txtDireccion").focus();
				}else{
					if(telefono==""){
						alert("Digite Telefono");
						document.getElementById("txtTelefono").focus();
					}else{
						if(Departamento=="Seleccione"){
							alert("Seleccione Departamento");
							document.getElementById("cmbDepartamento").focus();
						}else{
							if(Municipio=="Seleccione"){
								alert("Seleccione Municipio");
								document.getElementById("cmbMunicipio").focus();
							}else{
								ajax=getXMLObject();
								ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
								ajax.onreadystatechange  = function(){
									if (ajax.readyState == 4) {				
										alert(ajax.responseText);
									}
								}
								ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
								ajax.send("valor=1.1&tipo_identificacion="+tipo_identificacion+
										"&numero_documento="+numero_documento+
										"&nombre_razonsoc="+nombre_razonsoc+
										"&Nombre1="+Nombre1+
										"&Nombre2="+Nombre2+
										"&Apellido1="+Apellido1+
										"&Apellido2="+Apellido2+				
										"&RazonSocialDian="+RazonSocialDian+
										"&OperExt="+OperExt+
										"&direccion="+direccion+
										"&telefono="+telefono+
										"&telcontacto="+telcontacto+
										"&emailcontac="+emailcontac+
										"&IndCliente="+IndCliente+
										"&IndProveedor="+IndProveedor+
										"&IndEmpleado="+IndEmpleado+
										"&Departamento="+Departamento+
										"&Municipio="+Municipio+
										"&txtBanco="+txtBanco+
										"&cmbTipoCuenta="+cmbTipoCuenta+
										"&cmbEstado="+cmbEstado+
										"&txtPorcretIva="+txtPorcretIva+
										"&txtFechaIngreso="+txtFechaIngreso+
										"&txtPorcretIca="+txtPorcretIca+
										"&txtPagWeb="+txtPagWeb+
										"&txtDiasPlazo="+txtDiasPlazo+
										"&txtLineaProducto="+txtLineaProducto+
										"&cmbAutoRetenedor="+cmbAutoRetenedor+
										"&txtNumCuenta="+txtNumCuenta); //Posting txtname to Servle
							}
						}
					}
				}
			}
		}
	}
//	}
}

function RepetirNombre(){
	var nombre_razonsoc=document.getElementById("txtRazonNombre").value;
	document.getElementById("txtNombreCuenta").value=nombre_razonsoc;	
}


/*function ActualizarProveedor(){
	var tipo_identificacion=document.getElementById("cmbTipoDoc").value;
	var numero_documento=encodeURIComponent(document.getElementById("txtNumDoc").value);
	var nombre_razonsoc=encodeURIComponent(document.getElementById("txtRazonNombre").value);
	//var autoretenedor=encodeURIComponent(document.getElementById("cmbTipoAutoRetenedor").value);
	//var tipo_regimen=encodeURIComponent(document.getElementById("cmbTipoRegimen").value);
	var direccion=encodeURIComponent(document.getElementById("txtDireccion").value);
	var telefono=encodeURIComponent(document.getElementById("txtTelefono").value);
	var contacto=encodeURIComponent(document.getElementById("txtContacto").value);
	var telcontacto=encodeURIComponent(document.getElementById("txtTelefonoContacto").value);
	var emailcontac=encodeURIComponent(document.getElementById("txtEmailContac").value);
	var CodProv=document.getElementById("txtCodProv").value;
	var CodigoCuenta=encodeURIComponent(document.getElementById("txtCodigoCuenta").value);
	var NombreCuenta=encodeURIComponent(document.getElementById("txtNombreCuenta").value);
	var TipoCuenta=encodeURIComponent(document.getElementById("cmbTipoCuenta").value);
	var NaturalezaCuenta=encodeURIComponent(document.getElementById("cmbNaturalezaCuenta").value);
	var Nivel=encodeURIComponent(document.getElementById("cmbNivelCuenta").value);
	var estado=encodeURIComponent(document.getElementById("cmbEstado").value);
	var CodCuenta=document.getElementById("txtCodCuenta").value;
	
	//alert(nombre_razonsoc)
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1.2&tipo_identificacion="+tipo_identificacion+"&numero_documento="+numero_documento+"&nombre_razonsoc="+nombre_razonsoc+
			"&autoretenedor="+autoretenedor+"&tipo_regimen="+tipo_regimen+"&direccion="+direccion+
			"&telefono="+telefono+"&contacto="+contacto+"&telcontacto="+telcontacto+"&emailcontac="+emailcontac+
			"&CodigoCuenta="+CodigoCuenta+"&NombreCuenta="+NombreCuenta+"&TipoCuenta="+TipoCuenta+
			"&NaturalezaCuenta="+NaturalezaCuenta+"&Nivel="+Nivel+"&estado="+estado+
			"&CodCuenta="+CodCuenta+"&CodProv="+CodProv); //Posting txtname to Servle	
}*/

function GuardarCuenta(){
	var CodigoCuenta=encodeURIComponent(document.getElementById("txtCodigoCuenta").value);
	var NombreCuenta=encodeURIComponent(document.getElementById("txtNombreCuenta").value);
	var TipoCuenta=encodeURIComponent(document.getElementById("cmbTipoCuenta").value);
	var NaturalezaCuenta=encodeURIComponent(document.getElementById("cmbNaturalezaCuenta").value);
	var Nivel=encodeURIComponent(document.getElementById("cmbNivelCuenta").value);
	var estado=encodeURIComponent(document.getElementById("cmbEstado").value);
	
	var IndDiferido=document.getElementById("cmbIndDiferido").value;
	var IndiBase=document.getElementById("cmbIndiBase").value;
	var IndiCCosto=document.getElementById("cmbIndiCCosto").value;
	var IndTercero=document.getElementById("cmbIndTercero").value;
	var PorcBase=document.getElementById("txtPorcBase").value;
	
	if(PorcBase==""){
		PorcBase="0";
	}
	
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			var a=ajax.responseText;
			if(a==1){
				alert("Cuenta Creada Con Exito.");
				window.location.reload();
			}else{
				alert(a);
			}
			
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.1&CodigoCuenta="+CodigoCuenta+"&NombreCuenta="+NombreCuenta+"&TipoCuenta="+TipoCuenta+
			"&NaturalezaCuenta="+NaturalezaCuenta+"&Nivel="+Nivel+"&estado="+estado+
			"&IndDiferido="+IndDiferido+"&IndiBase="+IndiBase+"&IndiCCosto="+IndiCCosto+
			"&IndTercero="+IndTercero+"&PorcBase="+PorcBase); //Posting txtname to Servle	
	
}

function BuscarCuenta(){
	var CodigoCuenta=document.getElementById("txtCodigoCuenta").value;
	if(CodigoCuenta==""){
		alert("Digite Codigo de la Cuenta.");
	}
	if((CodigoCuenta!="")){
		ajax=getXMLObject();
		ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {				
				document.getElementById("Contenido").innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2&CodigoCuenta="+CodigoCuenta); //Posting txtname to Servle
	}
}

function PorcentajeBase(){
	var IndiBase=document.getElementById("cmbIndiBase").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			document.getElementById("IB").innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=IB&IndiBase="+IndiBase); //Posting txtname to Servle

}

function ActualizarCuenta(){
	var CodigoCuenta=encodeURIComponent(document.getElementById("txtCodigoCuenta").value);
	var NombreCuenta=encodeURIComponent(document.getElementById("txtNombreCuenta").value);
	var TipoCuenta=encodeURIComponent(document.getElementById("cmbTipoCuenta").value);
	var NaturalezaCuenta=encodeURIComponent(document.getElementById("cmbNaturalezaCuenta").value);
	var Nivel=encodeURIComponent(document.getElementById("cmbNivelCuenta").value);
	var estado=encodeURIComponent(document.getElementById("cmbEstado").value);
	var CodCuenta=document.getElementById("txtCodCuenta").value;
	var IndDiferido=document.getElementById("cmbIndDiferido").value;
	var IndiBase=document.getElementById("cmbIndiBase").value;
	var IndiCCosto=document.getElementById("cmbIndiCCosto").value;
	var IndTercero=document.getElementById("cmbIndTercero").value;
	var PorcBase=document.getElementById("txtPorcBase").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProveedor",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			var a=ajax.responseText;		
				alert(a);
				window.location.reload();
			
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.2&CodigoCuenta="+CodigoCuenta+"&NombreCuenta="+NombreCuenta+"&TipoCuenta="+TipoCuenta+
			"&NaturalezaCuenta="+NaturalezaCuenta+"&Nivel="+Nivel+"&estado="+estado+"&CodCuenta="+CodCuenta+
			"&IndDiferido="+IndDiferido+"&IndiBase="+IndiBase+"&IndiCCosto="+IndiCCosto+
			"&IndTercero="+IndTercero+"&PorcBase="+PorcBase);
}



function ConsultarF() {
	// alert("consultarFAct");
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlProveedor", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3"); // Posting txtname to Servlet
}


function CFact() {
	var contenido = document.getElementById('resultadosf');
	var v1 = document.getElementById("txtFact").value;
	// alert(v1+" - "+v2+" - "+v3+" - "+v4+" - "+v5+" - "+v6+" - "+v7+" - "+v8+"
	// - "+v9);
	if (v1 == "")  {
		alert("Debe Seleccionar el Numero de Documento!!!");
	} else {
				ajax = getXMLObject();
				ajax.open("POST", "ControlProveedor", true); // getname will
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
				ajax.send("valor=4&v1=" + v1 ); // Posting txtname to
				// Servlet
	}
}

function AnularFact(nfact,enca,factn,CodAdm) {
	//alert("Entramos");
	var contenido = document.getElementById('resultadosf');
	var refacturar="";
	var txtCodusuario = document.getElementById("txtCodusuario").value;
	var obs = document.getElementById("taObservacion").value;
	
	// alert(nfact+" - "+enca+" - "+factn+" - "+txtCodusuario+" - "+obs);
	if (obs == "" ) {
		alert("Debe Indicar un motivo de Anulación del Documento!!!");
	} else {
		if (confirm("Una vez Anulada no podrá deshacer la acción. ¿Desea Continuar?")) {
			if (confirm("¿Desea refacturar esta factura ?")) {
				refacturar="si";
			}else{
				refacturar="no";
			}
				ajax = getXMLObject();
				ajax.open("POST", "ControlProveedor", true); // getname will
				// be the
				// servlet name
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						contenido.innerHTML = ajax.responseText
						// medicosPYP();
					}
				}
				//alert("valor=5&txtCodusuario="+txtCodusuario+"&obs="+obs+"&nfact="+nfact+"&enca="+enca+"&factn="+factn+"&CodAdm="+CodAdm+"&refacturar="+refacturar );
				ajax.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=5&txtCodusuario="+txtCodusuario+"&obs="+obs+"&nfact="+nfact+"&enca="+enca+"&factn="+factn+"&CodAdm="+CodAdm+"&refacturar="+refacturar ); // Posting txtname to
		}
	}
}

/******************************************************************************************************/
//funci de la fecha de nacimiento
var patron = new Array(2,2,4);
var patron2 = new Array(1,3,3,3,3)
function masca(d,sep,pat,nums){
	


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
	
