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
			
		}
		
		ini = val.substring(0, 2);
		if ((ini > 13) || (ini == "00")) {
			d.value = "01";
		}
		
		if(largo==2){
		//var caja=document.getElementById('cta0').value;
			document.form1.cta0.select();
		//document.getElementById('cta0').focus();
		//document.getElementById('cta0').value="TODAS";
		//document.getElementById('cta0').SelectAll();
		//document.getElementById('cta0').focus();
		}	
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
    	if((document.getElementById('AC').value!="")&&(document.getElementById('MC').value!="")){
    	//alert("entrando");
    	var ano=document.getElementById('AC').value;
    	var periodo=document.getElementById('MC').value;
    	//var RC1=document.getElementById('cta0').value;
    	
    	
    	}else{alert("Debe seleccionar tanto el A\u00f1o como el Periodo");
    		if(document.getElementById('MC').value==""){document.getElementById('MC').focus();}
    		if(document.getElementById('AC').value==""){document.getElementById('AC').focus();}
    	}
    }
}


function valAnoPeriodoSuc(d, pat, nums, op) {
	
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
		//var caja=document.getElementById('cta0').value;
			document.form1.suc.select();
		//document.getElementById('cta0').focus();
		//document.getElementById('cta0').value="TODAS";
		//document.getElementById('cta0').SelectAll();
		//document.getElementById('cta0').focus();
		}	
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
    	if((document.getElementById('AC').value!="")&&(document.getElementById('MC').value!="")){
    	//alert("entrando");
    	var ano=document.getElementById('AC').value;
    	var periodo=document.getElementById('MC').value;
    	//var RC1=document.getElementById('cta0').value;
    	
    	
    	}else{alert("Debe seleccionar tanto el A\u00f1o como el Periodo");
    		if(document.getElementById('MC').value==""){document.getElementById('MC').focus();}
    		if(document.getElementById('AC').value==""){document.getElementById('AC').focus();}
    	}
    }
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


function ConsultarRepAuxiliarSucursalyCcosto(tiporepor){
	ajax=getXMLObject();
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var MC2=document.getElementById('MC2').value;
	var TDOC=document.getElementById('TDOC').value;
	var Suc="";
	var ccosto="";
	if((RC1!="")&&(RC2!="")&&(AC!="")&&(MC!="")&&(MC2!="")&&(TDOC!="")){
		if((RC1=="TODAS")&&(RC2=="TODAS")){
			if(MC<=MC2){
				if(tiporepor==1){
					Suc=document.getElementById('Suc').value;
					pagina="cont_ReporAuxiliaSucyCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC+"&Suc="+Suc+"&tiporepor="+tiporepor;
					var opciones3="toolbar=yes, menubar=yes , location=yes ";
					opciones3=opciones3+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
					window.open(pagina,"NUEVA",opciones3);
				}else{
					Suc=document.getElementById('Suc').value;
					ccosto=document.getElementById('ccosto').value;
					pagina="cont_ReporAuxiliaSucyCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC+"&Suc="+Suc+"&tiporepor="+tiporepor+"&ccosto="+ccosto;
					//alert("cont_ReporAuxiliaSucyCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC+"&Suc="+Suc+"&tiporepor="+tiporepor+"&ccosto="+ccosto);
					var opciones5="toolbar=yes, menubar=yes , location=yes ";
					opciones5=opciones5+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
					window.open(pagina,"NUEVA",opciones5);
					
				}
			}else{
				alert("El rango de periodos deber ser de menor a mayor Ej: Periodo 01 al 04 ");
			}
	}else{
		if((RC1!="TODAS")&&(RC2!="TODAS")){
			//alert("VALOR DE"+RC1);
			//alert("VALOR DE "+RC2);
			if(RC1<=RC2){
				if(MC<=MC2){
					if(tiporepor==1){
						Suc=document.getElementById('Suc').value;
					pagina="cont_ReporAuxiliaSucyCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC+"&Suc="+Suc+"&tiporepor="+tiporepor;
					var opciones4="toolbar=yes, menubar=yes , location=yes ";
					opciones4=opciones4+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
					window.open(pagina,"NUEVA",opciones4);
					}else{
						Suc=document.getElementById('Suc').value;
						ccosto=document.getElementById('ccosto').value;
						pagina="cont_ReporAuxiliaSucyCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC+"&Suc="+Suc+"&tiporepor="+tiporepor+"&ccosto="+ccosto;
						//alert("cont_ReporAuxiliaSucyCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+TDOC+"&Suc="+Suc+"&tiporepor="+tiporepor+"&ccosto="+ccosto);
						var opciones6="toolbar=yes, menubar=yes , location=yes ";
						opciones6=opciones6+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
						window.open(pagina,"NUEVA",opciones6);
					}
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
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepAuxiliarGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&MC2="+MC2+"&TDOC="+TDOC);
}


function vistaRepAuxiliarSucyCCosto(RC1, RC2, MC, AC,MC2,TDOC,Suc, tiporepor,ccosto){
	ajax=getXMLObject();
	//alert("vistaRepAuxiliarSucyCCosto");
	var contenido=document.getElementById('reporte');
	//alert("valor=RepAuxiliarGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC);
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	//alert("valor=RepAuxiliarSucyCcosto&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&MC2="+MC2+"&TDOC="+TDOC+"&Suc="+Suc+"&tiporepor="+tiporepor+"&ccosto="+ccosto);
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepAuxiliarSucyCcosto&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&MC2="+MC2+"&TDOC="+TDOC+"&Suc="+Suc+"&tiporepor="+tiporepor+"&ccosto="+ccosto);
}

var elementoSeleccionado = -1;

function AutocompletarCuenta(){
	//alert("entrando");
	var Codigo=document.getElementById("RC1").value;
	var lista=document.getElementById("dcta1");
	var elEvento =  window.event;  //arguments[0] ||
    var tecla = elEvento.keyCode;
    if(elementoSeleccionado+1 < articulos1.length) {
        elementoSeleccionado++;
    }
	ajax=getXMLObject();
	ajax.open("POST","ControlReportes",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			lista.innerHTML=ajax.responseText;
			lista.style.display = 'block';
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AutoCuenta&Codigo="+Codigo);
	
}

function vistaRepAuxiliarCT(RC1, RC2, MC, AC,MC2,TDOC,TERC){
	ajax=getXMLObject();
	//alert("vistaRepAuxiliarCT");
	//alert("valor=RepAuxiliarCT&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&MC2="+MC2+"&TDOC="+TDOC+"&TERC="+TERC);
	var contenido=document.getElementById('reporte');
	//alert("valor=RepAuxiliarGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC);
	ajax.open("POST","ControlReportes",true);
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
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=menupg&pg="+pg);
}

function TipoCprbp(){
	ajax=getXMLObject();
	var TipoCprbp=document.getElementById('tipoCprbp').value;
	var contenido=document.getElementById('menucprbp');
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=menucprbp&TipoCprbp="+TipoCprbp);
}

function TipoCpraux(){
	ajax=getXMLObject();
	var TipoCpraux=document.getElementById('tipoCpraux').value;
	var contenido=document.getElementById('menucpraux');
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=menucpraux&TipoCpraux="+TipoCpraux);
}

function ConsultarPG(pg){
	//alert(pg);
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


function ConsultarRepBalancePruebaSucursal(){
	ajax=getXMLObject();
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var Suc=document.getElementById('Suc').value;
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
							nivelSeleccionado="1_2_3_4_5_";
							//document.getElementById('Creaciones').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
							//alert("cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado);
							if((RC1=="TODAS")&&(RC2=="TODAS")){
									
									pagina="cont_ReporBalancePruebaSuc.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&Suc="+Suc;		
											window.open(pagina,"NUEVA",opciones3);
							}else{
								if((RC1!="TODAS")&&(RC2!="TODAS")){
									if(RC1<=RC2){
									pagina="cont_ReporBalancePruebaSuc.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&Suc="+Suc;
																
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
							pagina="cont_ReporBalancePruebaSuc.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&Suc="+Suc;
							
							
									window.open(pagina,"NUEVA",opciones3);
					}else{
						if((RC1!="TODAS")&&(RC2!="TODAS")){
							if(RC1<=RC2){
							pagina="cont_ReporBalancePruebaSuc.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&Suc="+Suc;
							
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


function ConsultarRepBalancePruebaCentroCosto(){
	ajax=getXMLObject();
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var ccosto=document.getElementById('ccosto').value;
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
							nivelSeleccionado="1_2_3_4_5_";
							//document.getElementById('Creaciones').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
							//alert("cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado);
							if((RC1=="TODAS")&&(RC2=="TODAS")){
									pagina="cont_ReporBalancePruebaCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto;		
											window.open(pagina,"NUEVA",opciones3);
							}else{
								if((RC1!="TODAS")&&(RC2!="TODAS")){
									if(RC1<=RC2){
									pagina="cont_ReporBalancePruebaCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto;		
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
							pagina="cont_ReporBalancePruebaCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto;
							window.open(pagina,"NUEVA",opciones3);
					}else{
						if((RC1!="TODAS")&&(RC2!="TODAS")){
							if(RC1<=RC2){
							pagina="cont_ReporBalancePruebaCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto;
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


function ConsultarRepBalancePruebaSucCentroCosto(){
	ajax=getXMLObject();
	var RC1=document.getElementById('cta0').value;
	var RC2=document.getElementById('RCC0').value;
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var Suc=document.getElementById('Suc').value;
	var ccosto=document.getElementById('ccosto').value;
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
							nivelSeleccionado="1_2_3_4_5_";
							//document.getElementById('Creaciones').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
							//alert("cont_ReporBalanceGeneral.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado);
							if((RC1=="TODAS")&&(RC2=="TODAS")){
									pagina="cont_ReporBalancePruebaSucCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto+"&Suc="+Suc;		
											window.open(pagina,"NUEVA",opciones3);
							}else{
								if((RC1!="TODAS")&&(RC2!="TODAS")){
									if(RC1<=RC2){
									pagina="cont_ReporBalancePruebaSucCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto+"&Suc="+Suc;		
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
							pagina="cont_ReporBalancePruebaSucCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto+"&Suc="+Suc;
							window.open(pagina,"NUEVA",opciones3);
					}else{
						if((RC1!="TODAS")&&(RC2!="TODAS")){
							if(RC1<=RC2){
							pagina="cont_ReporBalancePruebaSucCcosto.jsp?RC1="+RC1+"&RC2="+RC2+"&AC="+AC+"&MC="+MC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto+"&Suc="+Suc;
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

function ConsultarDiario(){
	ajax=getXMLObject();
	var AC=document.getElementById('AC').value;
	var MC=document.getElementById('MC').value;
	var rdia1=document.getElementById('rdia1').value;
	var rdia2=document.getElementById('rdia2').value;
	var tdoc=document.getElementById('tdoc').value;
	//alert("entrando AC"+AC+" MC"+MC+" MC2"+MC2+" TDOC"+tdoc);
	if((AC=="")||(MC=="")||(rdia1=="-")||(rdia2=="-")){
			 alert("Debe digitar el Año, el periodo y un rango de dias para cumplir con los parametros de Busqueda");
			}else{
				//alert("cont_RepComprobanteDiario.jsp?AC="+AC+"&MC="+MC+"&MC2="+MC2+"&TDOC="+tdoc);
				if(rdia1>rdia2){
				 alert("El rango escogido de dias debe ser de menor a mayor. Ej: Dia 1 al 15 ");
				}else{
				//alert("cont_RepComprobanteDiario.jsp?AC="+AC+"&MC="+MC+"&rdia1="+rdia1+"&rdia2="+rdia2+"&TDOC="+tdoc);
				pagina="cont_RepComprobanteDiario.jsp?AC="+AC+"&MC="+MC+"&rdia1="+rdia1+"&rdia2="+rdia2+"&TDOC="+tdoc;
				var opciones="toolbar=yes, menubar=yes , location=yes ";
				opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
				window.open(pagina,"NUEVA",opciones);
				}
			}
		
	
}


function vistaRepBalanceGeneral(RC1, RC2, MC, AC, nivelSeleccionado){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepBalanceGeneral&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&nivelSeleccionado="+nivelSeleccionado);
}

function vistaRepBalancePruebaSuc(RC1, RC2, MC, AC, nivelSeleccionado,Suc){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReporteBalancePruebaSucursal&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&nivelSeleccionado="+nivelSeleccionado+"&Suc="+Suc);
}


function vistaRepBalancePruebaCcosto(RC1, RC2, MC, AC, nivelSeleccionado,ccosto){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReporteBalancePruebaCcosto&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto);
}

function vistaRepBalancePruebaSucCcosto(RC1, RC2, MC, AC, nivelSeleccionado,ccosto,Suc){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ReporteBalancePruebaSucCcosto&RC1="+RC1+"&RC2="+RC2+"&MC="+MC+"&AC="+AC+"&nivelSeleccionado="+nivelSeleccionado+"&ccosto="+ccosto+"&Suc="+Suc);
}

function vistaReporteBalanceGeneral(RC1, RC2, MC, AC, nivelSeleccionado){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlReportes",true);
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
	ajax.open("POST","ControlReportes",true);
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
	ajax.open("POST","ControlReportes",true);
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

function vistaRepCompDiario(MC,AC,TDOC,RDiaU,RDiaD){
	ajax=getXMLObject();
	//alert(nivelSeleccionado);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlReportes",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	//alert("valor=CompDiario&MC="+MC+"&AC="+AC+"&TDOC="+TDOC+"&RDiaU="+RDiaU+"&RDiaD="+RDiaD);
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CompDiario&MC="+MC+"&AC="+AC+"&TDOC="+TDOC+"&RDiaU="+RDiaU+"&RDiaD="+RDiaD);
}


function Radio() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('Creaciones');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;   
			document.getElementById('Creaciones').innerHTML='<p>Cargando Modulo...</p><img src="Imagenes/ani.gif">';
			ajax.open("POST","ControlReportes",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					document.getElementById("Actualizaciones").innerHTML='';
					contenido.innerHTML = ajax.responseText	;
					//VerActualizaciones(val);
					
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}















	
