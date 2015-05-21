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

function ImprimirTodos(CodAdmision){
	 pagina="hic_TodosFormatosUnidos.jsp?CodAdmision="+CodAdmision;			
	    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
	    opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	    window.open(pagina,"NUEVA",opciones);
}

function ReloadSignosVitales(codAdm){
	 
	ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById("HistoriaPaciente2").innerHTML = ajax.responseText;
					document.getElementById("Krd").style.backgroundColor="#dddddd";
					document.getElementById("Ihp").style.backgroundColor="#dddddd";
					document.getElementById("Svt").style.backgroundColor="#2E64FE";
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VerVitales&CodAdmK="+codAdm);
}

function RadioForUSV(FechaKardex,CodAdm){
	 ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//alert(ajax.responseText)
					document.getElementById("CargSigVit").innerHTML = ajax.responseText;
					cargarSV(FechaKardex,CodAdm);
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CamSV&FechaSV="+FechaKardex+"&CodAdmSV="+CodAdm);
}
function GuardarSV(Hora){
		
	var txtTalla=document.getElementById("txtTalla"+Hora).value;
	var txtPeso=document.getElementById("txtPeso"+Hora).value;
	var txtTA=document.getElementById("txtTA"+Hora).value;
	var txtFC=document.getElementById("txtFC"+Hora).value;
	var txtFR=document.getElementById("txtFR"+Hora).value;
	var txtPulso=document.getElementById("txtPulso"+Hora).value;
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	if(txtTalla==""){
		alert("Digite la Talla.");
	}else{
		if(txtPeso==""){
			alert("Digite el Peso.");
		}else{
			if(txtTA==""){
				alert("Digite la Tension Arterial.");
			}else{
				if(txtFC==""){
					alert("Digite la Frecuencia Cardiaca.");
				}else{
					if(txtFR==""){
						alert("Digite Frecuencia Respiratoria.");
					}else{
						if(txtPulso==""){
							alert("Digite el Pulso.");
						}else{
							var co = confirm("Desea Guardar Los Siguientes Signos Vitales?\n Talla="+txtTalla+"\n Peso="+txtPeso+"\n T.A="+txtTA+"\n F.C="+txtFC+"\n F.R="+txtFR+"\n Pulso="+txtPulso);
							if(co){
							 ajax=getXMLObject();
							   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
							   	   ajax.onreadystatechange=function() {
										if (ajax.readyState==4) {
											alert("Ingreso Exitoso.")
											//document.getElementById("CargSigVit").innerHTML = ajax.responseText;
											ReloadSignosVitales(CodAdm);
										}
									}
									
							    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
								ajax.send("valor=GURSV&usuario="+usuario+"&CodAdm="+CodAdm+"&txtTalla="+txtTalla+"&txtPeso="+txtPeso+"&txtTA="+txtTA+"&txtFC="+txtFC+"&txtFR="+txtFR+"&txtPulso="+txtPulso+"&Hora="+Hora);
							}else{}
						}
					}
				}
			}
		}
	}
}


function GuardarDatosEmerg(HoraDE,TipoIn){

	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var FechaK=document.getElementById("txtFechaKardexActivo").value;
	if(TipoIn=="0"){
		//consultar
	   ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//document.getElementById("DatosEmerg").innerHTML = ajax.responseText;					
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    //alert("valor=MODE&HoraDE="+HoraDE+"&CodAdmDE="+CodAdm+"&CodUsuDE="+usuario+"&FechaK="+FechaK); //Posting txtname to Servlet
		ajax.send("valor=MODE&HoraDE="+HoraDE+"&CodAdmDE="+CodAdm+"&CodUsuDE="+usuario+"&FechaK="+FechaK); //Posting txtname to Servlet
	}
	if(TipoIn=="1"){
		//ingresar
		var tPeso=document.getElementById("txtPeso").value;
		var tTalla=document.getElementById("txtTalla").value;
		var tTA=document.getElementById("txtTA").value;
		var tFC=document.getElementById("txtFC").value;
		var tFR=document.getElementById("txtFR").value;
		var tPulso=document.getElementById("txtPulso").value;
		   ajax=getXMLObject();
		   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		   	   ajax.onreadystatechange=function() {
					if (ajax.readyState==4) {
						alert("Ingreso Exitoso");
						//document.getElementById("DatosEmerg").innerHTML = ajax.responseText;					
					}
				}			
		    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=GDE&HoraDE="+HoraDE+"&CodAdmDE="+CodAdm+"&CodUsuDE="+usuario+"&tPeso="+tPeso+
					"&tTalla="+tTalla+"&tTA="+tTA+"&tFC="+tFC+"&tFR="+tFR+"&tPulso="+tPulso); //Posting txtname to Servlet
		}
	//var p="<table border='1' width='30%'><tr><td colspan='2' >Peso:<input type='text' id='txtPeso' size='4' >(En Kg.)</td><td colspan='2'>Peso:<input type='text' id='txtTalla' size='4' >(En Mts.)</td></tr><tr><td>T.A:<input type='text' id='txtTA' size='4' ></td><td>F.C:<input type='text' id='txtFC' size='4' ></td><td>F.R:<input type='text' id='txtFR' size='4' ></td><td>Pulso:<input type='text' id='txtPulso' size='4' ><input name='btnFinalizar' type='button' id='btnFinalizar' value='+' onclick='GuardarDatosComp()' ></td></tr></table>";
	
	//var p="<table><tr><td>T.A:<input type='text' id='txtTA'></td><td>F.C:<input type='text' id='txtFC'></td><td>F.R:<input type='text' id='txtFR'></td><td>Pulso:<input type='text' id='txtPulso'></td></tr></table>";
	//document.getElementById('DatosEmerg').innerHTML =p;
}


function AutoMedicamentoCardex(){
	var NombreMedicamento=document.getElementById('txtMedicamento').value;
	   ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('MedCar').innerHTML = ajax.responseText;
					var Co=document.getElementById("txtContador").value;
					if(Co==1){
						document.getElementById('txtMedicamento').value=document.getElementById("txtNomMedi").value;
						document.getElementById('MedCar').innerHTML ='';
						var NomMC=(document.getElementById('txtMedicamento').value);
						LlenarFormaFarmaceutica(NomMC);
					}
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=AMC&NombreMedicamento="+NombreMedicamento); //Posting txtname to Servlet
}

function SelMedCard(NombreMedicamento){
	document.getElementById('txtMedicamento').value=NombreMedicamento;
	document.getElementById('MedCar').innerHTML ='';
	LlenarFormaFarmaceutica(NombreMedicamento);
}

function LlenarFormaFarmaceutica(NombreMedicamento){
	//document.getElementById('cmbFFarmaceutica').focus();
	//alert("NombreMedicamento "+NombreMedicamento);
	var NombreMediU=encodeURIComponent(NombreMedicamento);
	 ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//alert(ajax.responseText);
					document.getElementById('FF').innerHTML = ajax.responseText;
					document.getElementById('cmbFFarmaceutica').focus();
					var co=document.getElementById("txCont").value;
					if(co=="1"){
						BuscarConcentracion();
					}
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=LFF&NombreMedicamento="+NombreMediU); //Posting txtname to Servlet
}

function BuscarConcentracion(){
	var NombreMedicamento=encodeURIComponent(document.getElementById('txtMedicamento').value);
	var IdxFormaFarm=document.getElementById("cmbFFarmaceutica").selectedIndex;
	var FormaFarmaceutica=document.getElementById("cmbFFarmaceutica").options[IdxFormaFarm].text;
	var CodFormaFarmaceutica=document.getElementById("cmbFFarmaceutica").value;
	//alert(FormaFarma)
	 ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//alert(ajax.responseText);
					document.getElementById('CCN').innerHTML = ajax.responseText;
					document.getElementById('cmbConcentracion').focus();
					var conc=document.getElementById("txtcontc").value;
					if(conc==1){
						BuscarUnidad();
					}
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CCN&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica); //Posting txtname to Servlet

}

function BuscarUnidad(){
	//alert("entro");
	var NombreMedicamento=encodeURIComponent(document.getElementById('txtMedicamento').value);
	/*var IdxFormaFarm=document.getElementById("cmbFFarmaceutica").selectedIndex;
	var FormaFarmaceutica=document.getElementById("cmbFFarmaceutica").options[IdxFormaFarm].text;*/
	var CodFormaFarmaceutica=document.getElementById("cmbFFarmaceutica").value;
	var Concentracion=encodeURIComponent(document.getElementById("cmbConcentracion").value);
	
	//alert(Concentracion);
	//alert(FormaFarma)
	 ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//alert(ajax.responseText);
					//document.getElementById("InfusionY").innerHTML=ajax.responseText;
					document.getElementById('UNI').innerHTML = ajax.responseText;
					document.getElementById('cmbUnidad').focus();
					ValidacionInfusion();
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=UNI&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica+"&Concentracion="+Concentracion); //Posting txtname to Servlet

}
function ValidacionInfusion(){
	//alert("Entro validacion infusion");
	var FormaFarma=document.getElementById("txtCodFF").value;
	var TipoHtml="";
	if((FormaFarma==45)||(FormaFarma==9)||(FormaFarma==11)||(FormaFarma==15)||(FormaFarma==50)||(FormaFarma==30)||(FormaFarma==44)||(FormaFarma==3)){
		//var p="<span class='style12'><input type='checkbox' name='checkbox' value='checkbox'>Infusion Unica</span>"
		//var p="<label class='style12'><input type='hidden' id='cmbViaAdm' value='Infusion'><input name='radiobutton' type='radio' value='radiobutton' id='IFD' onclick='ValRadioBInf(1)'>Infusion Directa <input name='radiobutton' type='radio' value='radiobutton' id='IFV' onclick='ValRadioBInf(2)' >Infusion Variable</label>";
		//var p=""
		//document.getElementById("InfusionY").innerHTML=(p);
		TipoHtml="I";
	}else{
		TipoHtml="D";
	}
		//alert("Carga las diferentes vias de administracion");
		
		ajax=getXMLObject();
		   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		   	   ajax.onreadystatechange=function() {
					if (ajax.readyState==4) {
						//alert(ajax.responseText)
						document.getElementById("InfusionY").innerHTML=ajax.responseText;
					}
				}			
		    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');	   
			ajax.send("valor=VAD&TipoHtml="+TipoHtml)
	//}
}
function CargarTipoInfusion(){
	var TipoInfu=document.getElementById("cmbTipoInfusion").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById("TipoInfusion").innerHTML=ajax.responseText;
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');	   
		ajax.send("valor="+TipoInfu);
}
function ValRadioBInf(valInf){
	//alert("ValRadioBInf "+valInf);
	if(valInf=="1"){
		//document.getElementById("CargaInfu").innerHTML="";
	}
	if(valInf=="2"){
		
	}
	
	ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById("InfusionY").innerHTML=ajax.responseText;
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');	   
		ajax.send("valor=VDIN")
}
function BuscarCodMedicamento(){	
	var NombreMedicamento=encodeURIComponent(document.getElementById('txtMedicamento').value);
	var CodFormaFarmaceutica=document.getElementById("cmbFFarmaceutica").value;
	var Concentracion=encodeURIComponent(document.getElementById("cmbConcentracion").value);
	var Unidad=document.getElementById("cmbUnidad").value;
	 ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//document.getElementById("InfusionY").innerHTML=ajax.responseText;
					document.getElementById('CodMedTxt').innerHTML = ajax.responseText;
					ValidacionInfusion();
					//document.getElementById('cmbUnidad').focus();
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');	   
		ajax.send("valor=BCM&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica+"&Concentracion="+Concentracion+"&Unidad="+Unidad); //Posting txtname to Servlet
	
}
function Enfermeria(codAdm,fechaK){
	//alert("valor=VerKardex&FechaKardex="+fechaK+"&CodAdmK="+codAdm);
	ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById("HistoriaPaciente").innerHTML = ajax.responseText;
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VerEnfer&FechaKardex="+fechaK+"&CodAdmK="+codAdm);
}

/***********************MostrarInsumosHospitalarios***************/
function MostrarInsumosHospitalarios2(){
	//alert()
	DivValor=document.getElementById('HistoriaPaciente2');
	var usuario=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	ajax=getXMLObject();
	    ajax.open("POST","ControlFormatoHic",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
				document.getElementById("Krd").style.backgroundColor="#dddddd";
				document.getElementById("Ihp").style.backgroundColor="#2E64FE";
				document.getElementById("Svt").style.backgroundColor="#dddddd"; 
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=IM&CodAdm="+CodAdm+"&usuario="+usuario); //Posting txtname to Servlet
}
/*******************fin MostrarInsumosHospitalarios***************/

function SignosVitales(codAdm,fechaK){
	 
	ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById("HistoriaPaciente2").innerHTML = ajax.responseText;
					document.getElementById("Krd").style.backgroundColor="#dddddd";
					document.getElementById("Ihp").style.backgroundColor="#dddddd";
					document.getElementById("Svt").style.backgroundColor="#2E64FE";
					cargarSV(fechaK,codAdm);
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VerVitales&FechaKardex="+fechaK+"&CodAdmK="+codAdm);
}

//####################################3

function guardarHC(nomtext){
	//recibe como parametro(objeto) el text
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var fecha_kar=document.getElementById("txtFechaKardexActivo").value;
	var prb=nomtext.value;
	
	//permite saber el id del elemento
	var nom_text =nomtext.id;
	
	if(/temp/.test(nom_text)){
		if((prb.length!=0)&&  parseInt(prb)>45){
			nomtext.value="";
			alert("temperatura debe ser menor de 45 grados");
		}else{
			if((prb.length!=0)&&  parseInt(prb)<30){
				nomtext.value="";
				alert("temperatura debe ser mayor de 30 grados");
			}
		}
	  
 }
 
	if(/fc/.test(nom_text)){
		if((prb.length!=0)&&  parseInt(prb)>300){
			nomtext.value="";
			alert("frecuencia cardiaca debe ser menor de 300");
		}
	}
	
	
	if(/fr/.test(nom_text)){
		if((prb.length!=0)&&  parseInt(prb)>80){
			nomtext.value="";
			alert("fr debe ser menor de 80");
		}
	}
	
	if(/sat/.test(nom_text)){
		if((prb.length!=0)&&  parseInt(prb)>100){
			nomtext.value="";
			alert("saturacion debe ser menor de 100");
		}
	}
	
	
	if(/pvc/.test(nom_text)){
		if((prb.length!=0)&&  parseInt(prb)>40){
			nomtext.value="";
			alert("pvc debe ser menor de 40");
		}
	}
	
	if(/fio/.test(nom_text)){
		if((prb.length!=0)&&  parseInt(prb)>100){
			nomtext.value="";
			alert("fio2 debe ser menor de 100");
		}
	}
	
	if(prb.length==0){
		//alert("no tiene contenido "+nom_text);
	}else{
		
		ajax=getXMLObject();
		   ajax.open("POST","ControlLiquidos",true); //getname will be the servlet name
		   	   ajax.onreadystatechange=function() {
					if (ajax.readyState==4) {
						//alert(fecha_kar);
						
					}
				}
				
		    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=guardarHc&usuario="+usuario+"&codAdm="+CodAdm+"&nom_text="+nom_text
					+"&contenido_text="+prb+"&codPac="+CodPac+"&FechaKardex="+fecha_kar);
		
		
		
		
		
	}
	
	
}




function guardarDesocultar(nomtext){
	//recibe como parametro(objeto) el text
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var fecha_kar=document.getElementById("txtFechaKardexActivo").value;
	
	
		
		ajax=getXMLObject();
		   ajax.open("POST","ControlLiquidos",true); //getname will be the servlet name
		   	   ajax.onreadystatechange=function() {
					if (ajax.readyState==4) {
						//alert(fecha_kar);
						
					}
				}
				
		    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=guardarDesocultarSv&usuario="+usuario+"&codAdm="+CodAdm+"&desocultar="+nomtext
					+"&codPac="+CodPac+"&FechaKardex="+fecha_kar);
		
		
		
		
		
	
	
	
}



function calcular_tam(){
	
	//alert("");
	
	var tindices = new Array();
	tindices = new Array("7","8","9","10","11","12","13","14","15","16","17","18","19",
			"20","21","22","23","00","01","02","03","04","05","06");
	
	
	 for(var i =0; i < tindices.length; i++ ){
		 
		 var tas="tas"+tindices[i];
		 var tad ="tad"+tindices[i];
		 var tam ="tam"+tindices[i];
		 
		 suma1=document.getElementById(tas).value;
		 suma2=document.getElementById(tad).value; 
		 
		 if((suma1.length!=0)&&(suma2.length!=0)){
			 sumaEnt=  (parseInt(suma1)+2*( parseInt(suma2)))/3;
			 suma = parseInt(sumaEnt);
			 
			 document.getElementById(tam).value=suma; 
			 
		 }else{
			 document.getElementById(tam).value=""; 
		 }
			
			
		}
	
	
	
}


function desocultar_x(nomtext){
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var fecha_kar=document.getElementById("txtFechaKardexActivo").value;
	
	document.getElementById("ocsedacion").style.display = "table-row";
	document.getElementById("ocdolor").style.display = "table-row";
	document.getElementById("octam").style.display = "table-row";
	document.getElementById("ocpvc").style.display = "table-row";
	document.getElementById("ocis").style.display = "table-row";
	document.getElementById("ocesg").style.display = "table-row";
	document.getElementById("ocestc").style.display = "table-row";
	document.getElementById("ocx").style.display = "table-row";
	guardarDesocultar("x");
	
}

function ocultarX (){
	
	document.getElementById("ocsedacion").style.display = "none";
	document.getElementById("ocdolor").style.display = "none";
	document.getElementById("octam").style.display = "none";
	document.getElementById("ocpvc").style.display = "none";
	document.getElementById("ocis").style.display = "none";
	document.getElementById("ocesg").style.display = "none";
	document.getElementById("ocestc").style.display = "none";
	document.getElementById("ocx").style.display = "none";
	
}


function desocultar_pap(nomtext){
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var fecha_kar=document.getElementById("txtFechaKardexActivo").value;
	
	document.getElementById("ocdivpap").style.display = "table-row";
	document.getElementById("ocpaps").style.display = "table-row";
	document.getElementById("ocpapd").style.display = "table-row";
	document.getElementById("ocpapm").style.display = "table-row";
	document.getElementById("ocpapc").style.display = "table-row";
	guardarDesocultar("pap");
}


function ocultarPap(){
	
	document.getElementById("ocdivpap").style.display = "none";
	document.getElementById("ocpaps").style.display = "none";
	document.getElementById("ocpapd").style.display = "none";
	document.getElementById("ocpapm").style.display = "none";
	document.getElementById("ocpapc").style.display = "none";
	
}


function desocultar_pamhemo(nomtext){
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var fecha_kar=document.getElementById("txtFechaKardexActivo").value;
	
	document.getElementById("ocdivpamhemo").style.display = "table-row";
	document.getElementById("ocpwc").style.display = "table-row";
	document.getElementById("ocvvs").style.display = "table-row";
	document.getElementById("ocgc").style.display = "table-row";
	document.getElementById("ocic").style.display = "table-row";
	document.getElementById("ocirvs").style.display = "table-row";
	document.getElementById("ocirvp").style.display = "table-row";
	document.getElementById("ocitvi").style.display = "table-row";
	document.getElementById("ocitvd").style.display = "table-row";
	guardarDesocultar("pamhemo");
}

function ocultarPamhemo (){
	
	document.getElementById("ocdivpamhemo").style.display = "none";
	document.getElementById("ocpwc").style.display = "none";
	document.getElementById("ocvvs").style.display = "none";
	document.getElementById("ocgc").style.display = "none";
	document.getElementById("ocic").style.display = "none";
	document.getElementById("ocirvs").style.display = "none";
	document.getElementById("ocirvp").style.display = "none";
	document.getElementById("ocitvi").style.display = "none";
	document.getElementById("ocitvd").style.display = "none";
	
}


function desocultar_pulso(nomtext){
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var fecha_kar=document.getElementById("txtFechaKardexActivo").value;
	
	document.getElementById("ocdivpulso").style.display = "table-row";
	document.getElementById("ocpuld").style.display = "table-row";
	document.getElementById("ocpuli").style.display = "table-row";
	guardarDesocultar("pulso");
}

function ocultarPulso(){
	
	document.getElementById("ocdivpulso").style.display = "none";
	document.getElementById("ocpuld").style.display = "none";
	document.getElementById("ocpuli").style.display = "none";
	
}


function desocultar_pupilas(nomtext){
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodPac=document.getElementById("CodPac").value;
	var fecha_kar=document.getElementById("txtFechaKardexActivo").value;
	
	document.getElementById("ocdivpupilas").style.display = "table-row";
	document.getElementById("ocdivd").style.display = "table-row";
	document.getElementById("octamd").style.display = "table-row";
	document.getElementById("ocrccd").style.display = "table-row";
	document.getElementById("ocdivi").style.display = "table-row";
	document.getElementById("octami").style.display = "table-row";
	document.getElementById("ocrcci").style.display = "table-row";
	guardarDesocultar("pupilas");
	
}

function ocultarPupilas() {
	
	document.getElementById("ocdivpupilas").style.display = "none";
	document.getElementById("ocdivd").style.display = "none";
	document.getElementById("octamd").style.display = "none";
	document.getElementById("ocrccd").style.display = "none";
	document.getElementById("ocdivi").style.display = "none";
	document.getElementById("octami").style.display = "none";
	document.getElementById("ocrcci").style.display = "none";
	
}

function deshabilitarFilaSv (nombre){
	
	/*if (nombre==1){
		noma ="fr";
		//alert(nombre);
		var tindices = new Array();
		tindices = new Array("7","8","9","10","11","12","13","14","15","16","17","18","19",
				"20","21","22","23","00","01","02","03","04","05","06");
		
		for(i=0;i <tindices.length;i++){
			
			var nom = noma+tindices[i];
			document.getElementById(nom).disabled=true;
			
		}
	}else{*/
	
	noma =nombre.id;
	var tindices = new Array();
	tindices = new Array("7","8","9","10","11","12","13","14","15","16","17","18","19",
			"20","21","22","23","00","01","02","03","04","05","06");
	
	for(i=0;i <tindices.length;i++){
		
		var nom = noma+tindices[i];
		document.getElementById(nom).disabled=true;
		
	}
	//}
	
}

function soloNumerossv(nomtext){
	//alert(num);
	//alert(nomtext.id+" "+sumara);
	var prb=nomtext.value; 
	var nom_text =nomtext.id;
	var swval=false;
	
	
	
	
	if(prb.length > 0){
		swval=/^\d+$/.test(prb);
		if(swval == false){
			nomtext.value="";
			
		}
}
	
	calcular_tam();
		

}



function decimalsv(nomtext){
	//alert(num);
	//alert(nomtext.id+" "+sumara);
	var prb=nomtext.value; 
	var nom_text =nomtext.id;
	var swdec=false;
	
	
	
	 if((prb.length >0)){
			
	    	swdec=/^\d+\.?\d*$/.test(prb);
	   	         if(swdec == false){
	   	      	//alert("caracter invalido en el vtipa de la valvula mitral");
	   	        	nomtext.value="";

		        }
	   	         
	   	      if((prb.length >2)){
		   	    	 if(parseFloat(prb) <=0.0 ){
				        swdec=false;
				        nomtext.value="";
				   }
	
	
	   	      }

}
	 
	 
	 
}

function soloNumeros(nomtext, sumara,num){
	//alert(num);
	//alert(nomtext.id+" "+sumara);
	var prb=nomtext.value; 
	var nom_text =nomtext.id;
	var swval=false;
	
	
	
	
	if(prb.length > 0){
		swval=/^\d+$/.test(prb);
		if(swval == false){
			nomtext.value="";
			
		}else{
			swval=true;
		}
}
	
	
		sumarCampos();

}

function cargarSV(FechaKardex,CodAdm){
	//alert("ent sal");
	ajax = getXMLObject();
	ajax.open("POST", "ControlLiquidos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			
	var datos = ajax.responseText.split("&");
	//alert(datos[0]+" "+datos[1]);
	var i=0;
	if(datos.lenght!=0 ){
		cargarDesocultar(FechaKardex,CodAdm);
		
		for(i=0;i <datos.length;i++){
			//alert(datos[i]+" "+datos[i+1]);
			
			document.getElementById(datos[i]).value=datos[i+1];
			i=i+1;
			calcular_tam();
		}
		
		
	  }
	
	
	
	
	 }
	}
	
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=mostrarsv&FechaKardex="+FechaKardex+"&CodAdmK="+CodAdm);
	
	
	
		}


function cargarDesocultar(FechaKardex,CodAdm){
	//alert("cargar drenaje");
	ajax = getXMLObject();
	ajax.open("POST", "ControlLiquidos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			
			//alert(ajax.responseText);
	var datos = ajax.responseText.split("&");
	//alert(datos[0]+" "+datos[1]);
	var i=0;
	if(datos.lenght!=0 ){
		//cargarEntSal(FechaKardex,CodAdm);
		
		for(i=0;i <datos.length;i++){
			
			if(datos[i]=="x"){
				document.getElementById("ocsedacion").style.display = "table-row";
				document.getElementById("ocdolor").style.display = "table-row";
				document.getElementById("octam").style.display = "table-row";
				document.getElementById("ocpvc").style.display = "table-row";
				document.getElementById("ocis").style.display = "table-row";
				document.getElementById("ocesg").style.display = "table-row";
				document.getElementById("ocestc").style.display = "table-row";
				document.getElementById("ocx").style.display = "table-row";
				}
			
			if(datos[i]=="pap"){
				document.getElementById("ocdivpap").style.display = "table-row";
				document.getElementById("ocpaps").style.display = "table-row";
				document.getElementById("ocpapd").style.display = "table-row";
				document.getElementById("ocpapm").style.display = "table-row";
				document.getElementById("ocpapc").style.display = "table-row";
				
			}
			

			if(datos[i]=="pamhemo"){
				document.getElementById("ocdivpamhemo").style.display = "table-row";
				document.getElementById("ocpwc").style.display = "table-row";
				document.getElementById("ocvvs").style.display = "table-row";
				document.getElementById("ocgc").style.display = "table-row";
				document.getElementById("ocic").style.display = "table-row";
				document.getElementById("ocirvs").style.display = "table-row";
				document.getElementById("ocirvp").style.display = "table-row";
				document.getElementById("ocitvi").style.display = "table-row";
				document.getElementById("ocitvd").style.display = "table-row";
							
							
							}
			
			if(datos[i]=="pulso"){
				document.getElementById("ocdivpulso").style.display = "table-row";
				document.getElementById("ocpuld").style.display = "table-row";
				document.getElementById("ocpuli").style.display = "table-row";
				
			}
			
			if(datos[i]=="pupilas"){
				document.getElementById("ocdivpupilas").style.display = "table-row";
				document.getElementById("ocdivd").style.display = "table-row";
				document.getElementById("octamd").style.display = "table-row";
				document.getElementById("ocrccd").style.display = "table-row";
				document.getElementById("ocdivi").style.display = "table-row";
				document.getElementById("octami").style.display = "table-row";
				document.getElementById("ocrcci").style.display = "table-row";
				
			}
			
		}
		
	  }
	
	
	 }
	}
	
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=buscarDesocultar&FechaKardex="+FechaKardex+"&CodAdmK="+CodAdm);
		}



//######################################

function MostrarKardexK(codAdm,fechaK){
	//alert("valor=VerKardex&FechaKardex="+fechaK+"&CodAdmK="+codAdm);
	ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById("HistoriaPaciente2").innerHTML = ajax.responseText;
					document.getElementById("Krd").style.backgroundColor="#2E64FE";
					document.getElementById("Svt").style.backgroundColor="#dddddd"; 
					document.getElementById("Ihp").style.backgroundColor="#dddddd"; 
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VerKardex&FechaKardex="+fechaK+"&CodAdmK="+codAdm);
}
function ActualizarPT(){
	var CodAdmK=document.getElementById("CodAdm").value;
	var Peso=document.getElementById("txtPeso").value;
	var Talla=document.getElementById("txtTalla").value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			
		}
	}
		
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=APT&CodAdmK="+CodAdmK+"&PesoK="+Peso+"&TallaK="+Talla);
	
}

function CrearPT(){
	//alert("1");
	var CodAdmK=document.getElementById("CodAdm").value;
	//alert("valor=CPT&CodAdmK="+CodAdmK+"&PesoK=+Peso&TallaK=+Talla");
	var Peso=document.getElementById("txtPeso").value;
	//alert("valor=CPT&CodAdmK="+CodAdmK+"&PesoK="+Peso+"&TallaK=+Talla");
	var Talla=document.getElementById("txtTalla").value;
	//alert("valor=CPT&CodAdmK="+CodAdmK+"&PesoK="+Peso+"&TallaK="+Talla);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			
		}
	}
		
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CPT&CodAdmK="+CodAdmK+"&PesoK="+Peso+"&TallaK="+Talla);
	
}


function Reformular(CodDetalleFormulacion){
	alert("Reformular "+CodDetalleFormulacion);
	//alert("EliminarMK "+CodDetalleFormulacion);
	var usuario=document.getElementById("txtCodusuario").value;
	var co = confirm("Desea Reformular este Medicamento?");
	if(co){
		ajax=getXMLObject();
		ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
								
			}
		}	
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=RFFK&CodDetForK="+CodDetalleFormulacion+"&CodUsuK="+usuario);
	}else{
		
	}
	
}
function EliminarMK(CodDetalleFormulacion){
	//alert("EliminarMK "+CodDetalleFormulacion);
	var usuario=document.getElementById("txtCodusuario").value;
	var co = confirm("Desea Eliminar este medicamento de la formulacion?");
	if(co){
		ajax=getXMLObject();
		ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				//CargarKardex();
				//alert("="+ajax.responseText);
				var de=ajax.responseText;
				if(de=="Si"){
					alert("Medicamento Eliminado");
					CargarOtraVez();
				}
				if(de=="No"){
					alert("El Medicamento No Se Puede Eliminar, Ya Que Tiene Movimientos De Dispensacion y/o Cancelacion de Dosis.");
					CargarOtraVez();
				}
				
			}
		}	
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ELFK&CodDetForK="+CodDetalleFormulacion+"&CodUsuK="+usuario);
	}else{
		
	}
}
function SuspenderMK(CodDetalleFormulacion){
	//alert("EliminarMK "+CodDetalleFormulacion);
	var usuario=document.getElementById("txtCodusuario").value;
	var co = confirm("Desea Suspender este medicamento de la formulacion?");
	if(co){
		ajax=getXMLObject();
		ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				//CargarKardex();
				CargarOtraVez();
				
			}
		}	
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=EDFK&CodDetForK="+CodDetalleFormulacion+"&CodUsuK="+usuario);
	}else{
		
	}
}

function GuardarMedicamentoKardesSolvente(){
	/******************************************************************************************/
	var CodPac=document.getElementById("CodPac").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodCama=document.getElementById("txtCodCama").value;
	var CodArea=document.getElementById("txtCodArea").value;
	var CodSubarea=document.getElementById("txtCodSubarea").value;
	//var ObservacionMedKard=document.getElementById("txtObservacionMedKard").value;
	/******************************************************************************************/
	var NombreMedicamento=encodeURIComponent(document.getElementById('txtMedicamento').value);
	var CodFormaFarmaceutica=document.getElementById("cmbFFarmaceutica").value;	
	var Concentracion=encodeURIComponent(document.getElementById("cmbConcentracion").value);
	var Unidad=document.getElementById('cmbUnidad').value;
	var ViaAdm="Via Infusion";
	var LapsoMed=document.getElementById("cmbLapsoMed").value;
	var SelectUnidad=document.getElementById("cmbUnidad").selectedIndex;
	var NomUnidad=document.getElementById("cmbUnidad").options[SelectUnidad].text;
	/*****************************************************************************************/
	var cmbSolvente=document.getElementById("cmbSolvente").value;
	var SelectUnidadInf=document.getElementById("cmbUnidadInfu").selectedIndex;
	var NomUnidadInf=document.getElementById("cmbUnidadInfu").options[SelectUnidadInf].text;
	var txtTiempoInfusion=document.getElementById("txtTiempoInfusion").value;
	/******************************************************************************************/
	if((cmbSolvente=="Seleccione")||(NomUnidadInf=="Seleccione")||(txtTiempoInfusion=="")){
		alert("Falta Diligenciar algun dato de la infusion");
	}else{
		if(NombreMedicamento==""){
			alert("Escriba el Nombre del Medicamento.");
		}else{
			if(CodFormaFarmaceutica=="Seleccione"){
				alert("Seleccione Forma Farmaceutica.");
			}else{
				if(Concentracion=="Seleccione"){
					alert("Seleccione Concentracion.");
				}else{
					if(Unidad=="Seleccione"){
						alert("Seleccione Unidad.");
					}else{
						if(ViaAdm=="Seleccione"){
							alert("Seleccione Via Administracion.");
						}else{
							if(LapsoMed=="Seleccione"){
								alert("Seleccione Lapso.");
							}else{
								var y=LapsoMed.split(" ").length;
								var z=LapsoMed.split(" ");		     	
								for(var x=0; x<y-1; x=x+1){
									Tiempo=z[0];
									Hora=z[1];
								}
								var Ti=24 / Tiempo; 	
								var TotalConcentracion=Concentracion * Ti;	
								var CantidadMedicamento=TotalConcentracion / Concentracion;	
								var DosisK=document.getElementById("txtDosis").value;
								var CantMed=DosisK / Concentracion;
								var ObserInfu=" ";
									/*if(ViaAdm=="Via Infusion"){
										var txtRangoI=document.getElementById("txtRangoI").value;
										var txtRangoF=document.getElementById("txtRangoF").value;
										var txtRazon=document.getElementById("txtRazon").value;
										var cmbUnidadInfu=document.getElementById("cmbUnidadInfu").value;
									
										ObserInfu="De "+txtRangoI+" hasta "+txtRangoF+" a razon de "+txtRazon+"-"+cmbUnidadInfu;
									}*/
									//alert("Ti="+Ti+" Tiempo="+Tiempo+" CantidadMedicamento="+CantidadMedicamento);
									/********************************************************/
									/********************************************************/
									//alert("valor=GMK&NomUnidad="+NomUnidad+"&FrecuenciaK="+Tiempo+"&DosisK="+DosisK+"&CodSubareaK="+CodSubarea+"&CodAreaK="+CodArea+"&CodCamaK="+CodCama+"&CodUsuK="+usuario+"&CodAdmK="+CodAdm+"&CodPacK="+CodPac+"&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica+"&Concentracion="+Concentracion+"&Unidad="+Unidad+"&ViaAdm="+ViaAdm+"&LapsoMed="+LapsoMed+"&CantidadMedicamento="+Ti);
									if(DosisK!="NaN"){
										ajax=getXMLObject();
										ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
										ajax.onreadystatechange=function() {
											if (ajax.readyState==4) {
												document.getElementById("DetalleKardex").innerHTML = ajax.responseText;
												document.getElementById('txtMedicamento').value='';
												document.getElementById("txtDosis").value='';
												document.getElementById('txtMedicamento').focus();											
												//CargarKardex();
												CargarOtraVez();
											}
										}
										
										ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
										//alert("valor=GMK&NomUnidad="+NomUnidad+"&FrecuenciaK="+Tiempo+"&DosisK="+DosisK+"&CodSubareaK="+CodSubarea+"&CodAreaK="+CodArea+"&CodCamaK="+CodCama+"&CodUsuK="+usuario+"&CodAdmK="+CodAdm+"&CodPacK="+CodPac+"&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica+"&Concentracion="+Concentracion+"&Unidad="+Unidad+"&ViaAdm="+ViaAdm+"&LapsoMed="+LapsoMed+"&CantidadMedicamento="+Ti+"&ObserInfu="+ObserInfu);
										ajax.send("valor=GMKI&txtTiempoInfusion="+txtTiempoInfusion+"&cmbSolvente="+cmbSolvente+"&NomUnidadInf="+NomUnidadInf+"&NomUnidad="+NomUnidad+"&FrecuenciaK="+Tiempo+"&DosisK="+DosisK+"&CodSubareaK="+CodSubarea+"&CodAreaK="+CodArea+"&CodCamaK="+CodCama+"&CodUsuK="+usuario+"&CodAdmK="+CodAdm+"&CodPacK="+CodPac+"&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica+"&Concentracion="+Concentracion+"&Unidad="+Unidad+"&ViaAdm="+ViaAdm+"&LapsoMed="+LapsoMed+"&CantidadMedicamento="+Ti+"&ObserInfu="+ObserInfu);
									}else{
										alert("Escriba la Dosis.");
										document.getElementById("txtDosis").value='';
										document.getElementById("txtDosis").focus();
									}
									/********************************************************/
									/********************************************************/
							}
						}
					}
				}
			}
		}
	}
	/******************************************************************************************/
}


function GuardarMedicamentoKardes(){
	/******************************************************************************************/
	var CodPac=document.getElementById("CodPac").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodCama=document.getElementById("txtCodCama").value;
	var CodArea=document.getElementById("txtCodArea").value;
	var CodSubarea=document.getElementById("txtCodSubarea").value;
	var ObservacionMedKard=document.getElementById("txtObservacionMedKard").value;
	/******************************************************************************************/
	var NombreMedicamento=encodeURIComponent(document.getElementById('txtMedicamento').value);
	var CodFormaFarmaceutica=document.getElementById("cmbFFarmaceutica").value;	
	var Concentracion=encodeURIComponent(document.getElementById("cmbConcentracion").value);
	var Unidad=document.getElementById('cmbUnidad').value;
	var ViaAdm=document.getElementById("cmbViaAdm").value;
	var LapsoMed=document.getElementById("cmbLapsoMed").value;
	var SelectUnidad=document.getElementById("cmbUnidad").selectedIndex;
	var NomUnidad=document.getElementById("cmbUnidad").options[SelectUnidad].text;
	/*****************************************************************************************/
	if(NombreMedicamento==""){
		alert("Escriba el Nombre del Medicamento.");
	}else{
		if(CodFormaFarmaceutica=="Seleccione"){
			alert("Seleccione Forma Farmaceutica.");
		}else{
			if(Concentracion=="Seleccione"){
				alert("Seleccione Concentracion.");
			}else{
				if(Unidad=="Seleccione"){
					alert("Seleccione Unidad.");
				}else{
					if(ViaAdm=="Seleccione"){
						alert("Seleccione Via Administracion.");
					}else{
						if(LapsoMed=="Seleccione"){
							alert("Seleccione Lapso.");
						}else{
								var y=LapsoMed.split(" ").length;
								var z=LapsoMed.split(" ");		     	
								for(var x=0; x<y-1; x=x+1){
									Tiempo=z[0];
									Hora=z[1];
								}
								var Ti=24 / Tiempo; 	
								var TotalConcentracion=Concentracion * Ti;	
								var CantidadMedicamento=TotalConcentracion / Concentracion;	
								var DosisK=document.getElementById("txtDosis").value;
								var CantMed=DosisK / Concentracion;
								var ObserInfu=" ";
								if(ViaAdm=="Via Infusion"){
									var txtRangoI=document.getElementById("txtRangoI").value;
									var txtRangoF=document.getElementById("txtRangoF").value;
									var txtRazon=document.getElementById("txtRazon").value;
									var cmbUnidadInfu=document.getElementById("cmbUnidadInfu").value;
									
									ObserInfu="De "+txtRangoI+" hasta "+txtRangoF+" a razon de "+txtRazon+"-"+cmbUnidadInfu;
								}
								//alert("Ti="+Ti+" Tiempo="+Tiempo+" CantidadMedicamento="+CantidadMedicamento);
								/********************************************************/
								/********************************************************/
								//alert("valor=GMK&NomUnidad="+NomUnidad+"&FrecuenciaK="+Tiempo+"&DosisK="+DosisK+"&CodSubareaK="+CodSubarea+"&CodAreaK="+CodArea+"&CodCamaK="+CodCama+"&CodUsuK="+usuario+"&CodAdmK="+CodAdm+"&CodPacK="+CodPac+"&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica+"&Concentracion="+Concentracion+"&Unidad="+Unidad+"&ViaAdm="+ViaAdm+"&LapsoMed="+LapsoMed+"&CantidadMedicamento="+Ti);
								if(DosisK!="NaN"){
									ajax=getXMLObject();
									ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
									ajax.onreadystatechange=function() {
										if (ajax.readyState==4) {
											document.getElementById("DetalleKardex").innerHTML = ajax.responseText;
											document.getElementById('txtMedicamento').value='';
											document.getElementById("txtDosis").value='';
											document.getElementById('txtMedicamento').focus();											
											//CargarKardex();
											CargarOtraVez();
										}
									}
										
									ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
									//alert("valor=GMK&NomUnidad="+NomUnidad+"&FrecuenciaK="+Tiempo+"&DosisK="+DosisK+"&CodSubareaK="+CodSubarea+"&CodAreaK="+CodArea+"&CodCamaK="+CodCama+"&CodUsuK="+usuario+"&CodAdmK="+CodAdm+"&CodPacK="+CodPac+"&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica+"&Concentracion="+Concentracion+"&Unidad="+Unidad+"&ViaAdm="+ViaAdm+"&LapsoMed="+LapsoMed+"&CantidadMedicamento="+Ti+"&ObserInfu="+ObserInfu);
									ajax.send("valor=GMK&NomUnidad="+NomUnidad+"&FrecuenciaK="+Tiempo+"&DosisK="+DosisK+"&CodSubareaK="+CodSubarea+"&CodAreaK="+CodArea+"&CodCamaK="+CodCama+"&CodUsuK="+usuario+"&CodAdmK="+CodAdm+"&CodPacK="+CodPac+"&NombreMedicamento="+NombreMedicamento+"&CodFormaFarmaceutica="+CodFormaFarmaceutica+"&Concentracion="+Concentracion+"&Unidad="+Unidad+"&ViaAdm="+ViaAdm+"&LapsoMed="+LapsoMed+"&CantidadMedicamento="+Ti+"&ObserInfu="+ObserInfu+"&ObservacionMedKard="+ObservacionMedKard);
								}else{
									alert("Escriba la Dosis.");
									document.getElementById("txtDosis").value='';
									document.getElementById("txtDosis").focus();
								}
								/********************************************************/
								/********************************************************/
						}
					}
				}
			}
		}
	}
	/******************************************************************************************/
}
function CalcularDosis(){
	var Concentracion=document.getElementById("cmbConcentracion").value;
	var LapsoMed=document.getElementById("cmbLapsoMed").value;
	var y=LapsoMed.split(" ").length;
	var z=LapsoMed.split(" ");		     	
	for(var x=0; x<y-1; x=x+1){
		Tiempo=z[0];
		Hora=z[1];
	}
	var Ti=24 / Tiempo; 	
	var TotalConcentracion=Concentracion * Ti;	
	var CantidadMedicamento=TotalConcentracion / Concentracion;	
	document.getElementById("txtDosis").value=TotalConcentracion;
}
function CargarKardex(){
	var CodAdm=document.getElementById("CodAdm").value;
	//alert("valor=MostrarKar&CodAdmK="+CodAdm);
	 ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById("CarguesDiaKardex").innerHTML = ajax.responseText;
					
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=MostrarKar&CodAdmK="+CodAdm);

}

function RadioForU(FechaKardex,CodAdm){
	//alert("RadioForU "+FechaKardex+" CodAdm "+CodAdm);
	var Perfil=document.getElementById("txtPerfil").value;
	 ajax=getXMLObject();
	   ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//alert(ajax.responseText)
					document.getElementById("CarguesDiaKardex").innerHTML = ajax.responseText;
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=CamKar&FechaKardex="+FechaKardex+"&CodAdmK="+CodAdm+"&Perfil="+Perfil);

}
function fr(CodHoraDisp,horaD){
	//alert("CodHoraDisp "+CodHoraDisp+" horaD= "+horaD);
	var no = confirm("Desea Cambiar La Hora de esta dosis?");
	if(no){
		var p="<table width='100%' border='1' bordercolor='#FF0000' cellspacing='0' cellpadding='0'><tr>" +
				"<td>Seleccione Nueva Hora</td><td><select id='CmbHoraNueDis'><option value='HH'>HH</option>" +
				"<option value='7'>07 AM</option>"+
				"<option value='8'>08 AM</option>"+
				"<option value='9'>09 AM</option>"+
				"<option value='10'>10 AM</option>"+
				"<option value='11'>11 AM</option>"+
				"<option value='12'>12 PM</option>"+
				"<option value='13'>01 PM</option>"+
				"<option value='14'>02 PM</option>"+
				"<option value='15'>03 PM</option>"+
				"<option value='16'>04 PM</option>"+
				"<option value='17'>05 PM</option>"+
				"<option value='18'>06 PM</option>"+
				"<option value='19'>07 PM</option>"+
				"<option value='20'>08 PM</option>"+
				"<option value='21'>09 PM</option>"+
				"<option value='22'>10 PM</option>"+
				"<option value='23'>11 PM</option>"+
				"<option value='0'>12 AM</option>"+
				"<option value='1'>01 AM</option>"+
				"<option value='2'>02 AM</option>"+
				"<option value='3'>03 AM</option>"+
				"<option value='4'>04 AM</option>"+
				"<option value='5'>05 AM</option>"+
				"<option value='6'>06 AM</option>"+
				"</select></td><td>"+
				"<input name='button' type='button' onClick='CambiarDosis("+CodHoraDisp+","+horaD+")' value='Cambiar' /></td></tr></table>";
		document.getElementById("DatosEmerg").innerHTML=(p);
	}
	else{
	}
}

function CambiarDosis(CodHoraDisp,horaD){
	var CmbHoraNueDis=document.getElementById("CmbHoraNueDis").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlVerFormatos",true);
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			var valida=ajax.responseText;
			if(valida=="NO"){
				alert("No se Puede Cambiar la Hora de esta dosis.\n Ya esta hora esta ocupada.");
			}else{
				document.getElementById("DatosEmerg").innerHTML = '';
				var txtFechaActuKard=document.getElementById("txtFechaActuKard").value;
				var txtAdmisionCKR=document.getElementById("txtAdmisionCKR").value;
				MostrarKardexK(txtAdmisionCKR,txtFechaActuKard);
			}
		}
	}			
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=CDO&CodHoraDisp="+CodHoraDisp+"&horaD="+horaD+"&CmbHoraNueDis="+CmbHoraNueDis);
}

function Dispensar(CodHoraDisp){
	var co = confirm("Desea dispensar esta dosis?");
	if(co){
		var CodUsu=document.getElementById("txtCodusuario").value;
		ajax=getXMLObject();
		ajax.open("POST","ControlVerFormatos",true);
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				var txtFechaActuKard=document.getElementById("txtFechaActuKard").value;
				var txtAdmisionCKR=document.getElementById("txtAdmisionCKR").value;
				MostrarKardexK(txtAdmisionCKR,txtFechaActuKard);
			}
		}			
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Di&CodUsu="+CodUsu+"&CodHoraDisp="+CodHoraDisp);
	}else{
		var no = confirm("Desea anular esta dosis?");
		if(no){
			var p="<table width='100%' border='1' bordercolor='#FF0000' cellspacing='0' cellpadding='0'><tr><td>Motivo Anulacion<input id='txtObservacionCancelar' type='text' ><input name='button' type='button' onClick='Anulardosis("+CodHoraDisp+")' value='Anular' /></td></tr></table>";
			document.getElementById("DatosEmerg").innerHTML=(p);
		}
		else{
			document.getElementById("ca"+CodHoraDisp).checked=false;
		}
	}
}

function Anulardosis(CodHoraDisp){
	ajax=getXMLObject();
	ajax.open("POST","ControlVerFormatos",true);
	var CodUsu=document.getElementById("txtCodusuario").value;
	var Observacion=document.getElementById("txtObservacionCancelar").value;
	if(Observacion==""){
		alert("Escriba el motivo de la cancelacion de la dosis.");
	}else{
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				//window.close();				
				document.getElementById("DatosEmerg").innerHTML = '';
				var txtFechaActuKard=document.getElementById("txtFechaActuKard").value;
				var txtAdmisionCKR=document.getElementById("txtAdmisionCKR").value;
				MostrarKardexK(txtAdmisionCKR,txtFechaActuKard);
			}
		}			
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Cd&CodUsu="+CodUsu+"&CodHoraDisp="+CodHoraDisp+"&Observacion="+Observacion);
	}
}
function DispensarAntes(CodHoraDisp){
	var CodUsu=document.getElementById("txtCodusuario").value;
	//alert("CodHoraDisp="+CodHoraDisp);
    pagina="hic_OpcionesDispensacion.jsp?CodHoraDisp="+CodHoraDisp+"&CodUsu="+CodUsu;			
    var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
    opciones =opciones+"scrollbars=no, resizable=no, width=600, height=175, top=280,  left=280";
    window.open(pagina,"NUEVA",opciones);

	
}
function CargarOtraVez(){
	//alert();
	ajax=getXMLObject();
	var CodAdm=document.getElementById("CodAdm").value;
	var usuario=document.getElementById("txtCodusuario").value;
	var CodigoPaciente=document.getElementById("CodPac").value;
	var contenido=document.getElementById('opciones');
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8&CodPac="+CodigoPaciente+"&codAdm="+CodAdm+"&usuario="+usuario); 
}


function redirect(){
	window.location.href="hic_SeleccionarPacientes.jsp";
}
function DispensarDosis(){
	//alert("1");
	var CodUsu=document.getElementById("txtCodUsu").value;
	//alert("2");
	var CodHoraDisp=document.getElementById("txtCodHoraDisp").value;
	//alert("3");
	ajax=getXMLObject();
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			//("4");
			var val=radioButtons[x].id;
			//alert("5="+val);
			if(val=="Di"){
				//alert(val)
				//alert("6");
				//1)cambiar estado en la tabla hic_horas_dosificacion
				ajax.onreadystatechange=function() {
					if (ajax.readyState==4) {
						//alert("dispensar");
						//document.getElementById("CarguesDiaKardex").innerHTML = ajax.responseText;
						//CargarOtraVez();
						//window.opener.document.form1.textfield13.value=x;
						//window.opener.document
						window.opener.document.location.reload();
						window.close();
						
						//CargarKardex();
					}
				}			
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=Di&CodUsu="+CodUsu+"&CodHoraDisp="+CodHoraDisp);
			}
			
			if(val=="Chm"){
				//alert(val) 
				//3)cambiar la hora y por ende el resto de dispensacion
				}
			if(val=="Cd"){
				//alert(val)
				//alert("7");
				//2) cancelar la dispensacion
				var Observacion=document.getElementById("txtObservacionCancelar").value;
				//alert("8");
				if(Observacion==""){
					alert("Escriba el motivo de la cancelacion de la dosis.");
				}else{
					//alert("9");
					ajax.onreadystatechange=function() {
						if (ajax.readyState==4) {
							//alert("anular");
							//CargarKardex();
							window.close();
							//document.getElementById("CarguesDiaKardex").innerHTML = ajax.responseText;
						}
					}			
					ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
					ajax.send("valor=Cd&CodUsu="+CodUsu+"&CodHoraDisp="+CodHoraDisp+"&Observacion="+Observacion);
				}
			}
		}
	}
	//CargarOtraVez();
}

/*
function GuardarEpicrisis(){
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	var ProcEst=document.getElementById("txtProcEst").value;
	var Manejo=document.getElementById("txtManejo").value;
	var Resumen=document.getElementById("txtResumen").value;
	var codusu=document.getElementById("txtCodusuario").value;
	var Servicio=document.getElementById("cmbServicio").value;
	ProcEst=encodeURIComponent(ProcEst);
	Manejo=encodeURIComponent(Manejo);
	Resumen=encodeURIComponent(Resumen);
	if(Servicio=="Seleccione"){
		alert("Seleccione Servicio de Epicrisis.");
	}else{
		if(ProcEst==""){
			alert("Falta Diligenciar Los Procedimientos y Estudios Realizados.");
			document.getElementById("txtProcEst").focus();
		}else{
			if(Manejo==""){
				alert("Falta Diligenciar El Manejo.");
				document.getElementById("txtManejo").focus();
			}else{
				if(Resumen==""){
					alert("Falta Diligenciar El Ordenamiento.");
					document.getElementById("txtResumen").focus();
				}
			}
		}
	}
	if((Servicio!="Seleccione")&&(Manejo!="")&&(ProcEst!="")&&(Resumen!="")){
	 ajax=getXMLObject();
	   ajax.open("POST","ControlMultiplePacientes",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					//alert(ajax.responseText);
					alert("PARA CONSULTAR LA EPICRISIS, DIRIGIRSE AL MENU DE ATENCIONES ANTERIORES.\nSELECCIONAR LA OPCION DE EPICRISIS.");
					//MostrarEpicrisis();
					MostrarAtenciones();
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ep2&CodAdm="+CodAdm+"&CodPac="+CodPac+"&ProcEst="+ProcEst+"&Manejo="+Manejo+"&Resumen="+Resumen+"&codusu="+codusu+"&Servicio="+Servicio);
	}
}*/