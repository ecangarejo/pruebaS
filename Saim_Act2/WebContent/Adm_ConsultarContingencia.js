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
/****************************DEVOLVER ALTA PACIENTE*********************************/

function omitirDetalle(CodDetalle){
	var txtCodFormula=document.getElementById("txtCodFormula").value;
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearPregunta",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			document.getElementById('DetalleFormula').innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ODF" +
			"&CodDetalle="+CodDetalle+"&txtCodFormula="+txtCodFormula); //Posting txtname to Servlet
}

function CrearFormulacion(){
	var cmbCodAdm=document.getElementById("cmbCodAdm").value;
	var CodUsuario=document.getElementById("CodUsuario").value;
	var TipoFormato=document.getElementById("TipoFormato").value;
	var txtFecha=document.getElementById("txtFecha").value;
	var cmbHora=document.getElementById("cmbHora").value;
	var cmbMinuto=document.getElementById("cmbMinuto").value;
	var txtCodPac=document.getElementById("txtCodPac").value;
	var txtCodFormula=document.getElementById("txtCodFormula").value;
/**************************/
	var txtObservacion=document.getElementById("txtObservacion").value;
	var cmbMedicamento=document.getElementById("cmbMedicamento").value;
	var txtCantidad=document.getElementById("txtCantidad").value;
	var txtDosis=document.getElementById("txtDosis").value;
	var cmbUnidad=document.getElementById("cmbUnidad").value;
	var cmbViaAdm=document.getElementById("cmbViaAdm").value;
	var cmbLapso=document.getElementById("cmbLapso").value;	
	/*************************/
	var HoraFormato=cmbHora+":"+cmbMinuto+":00";
	var y=txtFecha.split("/").length;
	var z=txtFecha.split("/");		     	
	for(x=0; x<y-1; x=x+1){ 
		Dia=z[0];Mes=z[1];Ano=z[2];					   
	}
	var FechaSql=Ano+"-"+Mes+"-"+Dia;
	if((cmbMedicamento=="Seleccione")||(txtCantidad=="")||(txtDosis=="")||(cmbUnidad=="Sel")||(cmbViaAdm=="Sel")||(cmbLapso=="Sel")){
		alert("Diligencie Todos lod Campos");
	}else{
		ajax=getXMLObject();
		ajax.open("POST","ControlCrearPregunta",true); //getname will be the servlet name
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) {
				document.getElementById('DetalleFormula').innerHTML = ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GFR" +
			"&cmbCodAdm="+cmbCodAdm+
			"&CodUsuario="+CodUsuario+
			"&TipoFormato="+TipoFormato+
			"&txtFecha="+FechaSql+
			"&HoraFormato="+HoraFormato+
			"&txtCodPac="+txtCodPac+
			"&txtCodFormula="+txtCodFormula+
			"&txtObservacion="+txtObservacion+
			"&cmbMedicamento="+cmbMedicamento+
			"&txtCantidad="+txtCantidad+
			"&txtDosis="+txtDosis+
			"&cmbUnidad="+cmbUnidad+
			"&cmbViaAdm="+cmbViaAdm+
			"&cmbLapso="+cmbLapso); //Posting txtname to Servlet
	}
}

function GuardarFormatoHCC(){
	var cmbHora=document.getElementById("cmbHora").value;
	var cmbMinuto=document.getElementById("cmbMinuto").value;
	var hora=cmbHora+":"+cmbMinuto+":00";
	var CodAdm=document.getElementById("cmbCodAdm").value;
	var txtFecha=document.getElementById("txtFecha").value;
	var y=txtFecha.split("/").length;
 	var z=txtFecha.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    Dia=z[0];
    Mes=z[1];
    Ano=z[2];
   
    }
 	var FechaFormato=Ano+"-"+Mes+"-"+Dia;
 	var opcion=confirm("Desea Guardar Este Formato?");
	if(opcion){		
		ajax=getXMLObject();	
		ajax.open("POST","ControlCrearPregunta",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if(ajax.readyState == 4){
				//var validar=ajax.responseText;
				BuscarPacienteContingencia();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GFCC&FechaFormato="+FechaFormato+"&HoraFormato="+hora+"&CodAdmision="+CodAdm); //Posting txtname to Servlet
		}else{}
}

function IngresarDiagnosticoSolo(){

	ajax=getXMLObject();	
	var CodResul=document.getElementById("txtCodResultado5").value;
	var Resul=document.getElementById("txtNomDiagnos").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var cmbHora=document.getElementById("cmbHora").value;
	var cmbMinuto=document.getElementById("cmbMinuto").value;
	var hora=cmbHora+":"+cmbMinuto+":00";
	//var hora=document.getElementById("txtHora").value;
	var txtFecha=document.getElementById("txtFecha").value;
	var y=txtFecha.split("/").length;
 	var z=txtFecha.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    Dia=z[0];
    Mes=z[1];
    Ano=z[2];
   
    }
 	var FechaFormato=Ano+"-"+Mes+"-"+Dia;
	var usuario=document.getElementById("CodUsuario").value;
	var CodAdm=document.getElementById("cmbCodAdm").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var Resul1=document.getElementById("txtResult1").value; 
	
		Resul=Resul1+"-->"+Resul;
		//alert("Resul "+Resul+" Resul1 "+Resul1);
	if(CodDiagnostico==""){alert("Este Codigo no Existe En El CIE10.");}
	if(CodDiagnostico!=""){
		ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			//alert(ajax.responseText);
			document.getElementById("DiagnosticoIniciales").innerHTML=ajax.responseText;
			Resul1=document.getElementById("txtResult1").value=Resul+"-"+CodDiagnostico;
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("txtNomDiagnos").value="";
			
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1.1&Resul="+Resul+"&CodResul="+CodResul+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+FechaFormato+"&usuario="+usuario+"&CodAdm="+CodAdm+"&CodDiagnostico="+CodDiagnostico); //Posting txtname to Servlet
	}
}


function IngresarDiagnosticoInicial(){
	ajax=getXMLObject();	
	var Resul=document.getElementById("txtNomDiagnos").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var cmbHora=document.getElementById("cmbHora").value;
	var cmbMinuto=document.getElementById("cmbMinuto").value;
	var hora=cmbHora+":"+cmbMinuto+":00";
	//var hora=document.getElementById("txtHora").value;
	var txtFecha=document.getElementById("txtFecha").value;
	var y=txtFecha.split("/").length;
 	var z=txtFecha.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    Dia=z[0];
    Mes=z[1];
    Ano=z[2];
   
    }
 	var FechaFormato=Ano+"-"+Mes+"-"+Dia;
	var usuario=document.getElementById("CodUsuario").value;
	var CodAdm=document.getElementById("cmbCodAdm").value;
	var CodDiagnostico=document.getElementById("txtCodDiagnostico").value;
	var Resul1=document.getElementById("txtResult1").value; 
	var CodResul=document.getElementById("txtCodResultado").value;
	
		Resul=Resul1+"-->"+Resul;
		//alert("Resul "+Resul+" Resul1 "+Resul1);
	if(CodDiagnostico==""){alert("Este Codigo no Existe En El CIE10.");}
	if(CodDiagnostico!=""){
		ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			//alert(ajax.responseText);
			document.getElementById("DiagnosticoIniciales").innerHTML=ajax.responseText;
			Resul1=document.getElementById("txtResult1").value=Resul+"-"+CodDiagnostico;
			document.getElementById("txtCodDiagnostico").value="";
			document.getElementById("txtNomDiagnos").value="";
			
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&Resul="+Resul+"&CodResul="+CodResul+"&CodPac="+CodPac+"&hora="+hora+"&fecha="+FechaFormato+"&usuario="+usuario+"&CodAdm="+CodAdm+"&CodDiagnostico="+CodDiagnostico); //Posting txtname to Servlet
	}
}

function ActualizarResultHistorias(Resul,CodResul){
	
	//alert(Resul);
		ajax=getXMLObject();	
		var CodAdm=document.getElementById("cmbCodAdm").value;
		var Codi=encodeURIComponent(Resul)
		//alert(Codi)
		ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
		//alert("valor=1&Resul="+Codi+"&CodResul="+CodResul+"&CodAdm="+CodAdm); //Posting txtname to Servlet
		ajax.send("valor=1&Resul="+Codi+"&CodResul="+CodResul+"&CodAdm="+CodAdm); //Posting txtname to Servlet    
}

function ActualizarResultados(){
	var c=document.getElementById('txtTotal').value;
	var oscar=c;
	var a;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			CodResul=document.form1.txtCodResultado[i].value;
			Resul=document.form1.txtRespuesta[i].value;					
			ActualizarResultHistorias(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{
		 Resul=document.getElementById("txtRespuesta").value;	
		 CodResul=document.getElementById("txtCodResultado").value;
		 ActualizarResultHistorias(Resul,CodResul);
	 }
}

function MostrarPreguntasPH(CodArea,con){	
	var CodAdm=document.getElementById("cmbCodAdm").value;
	var CodPac=document.getElementById("txtCodPac").value;
	var txtFecha=document.getElementById("txtFecha").value;
	var cmbHora=document.getElementById("cmbHora").value;
	var cmbMinuto=document.getElementById("cmbMinuto").value;
	var HoraFormato=cmbHora+":"+cmbMinuto+":00";
	var CodUsuario=document.getElementById("CodUsuario").value;	

	var y=txtFecha.split("/").length;
 	var z=txtFecha.split("/");		     	
 	for(x=0; x<y-1; x=x+1)
 	{ 
    Dia=z[0];
    Mes=z[1];
    Ano=z[2];
   
    }
 	var FechaFormato=Ano+"-"+Mes+"-"+Dia;
	
	
	ajax=getXMLObject();
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			
			var resul=ajax.responseText;
			//alert(resul);
			document.getElementById('formu').innerHTML = resul;
			var pesta = document.getElementById('pestanas').childNodes;
			for (i=1;i<=pesta.length;i++){
				document.getElementById("ar"+i).className='style11 cabecera2gris';
			}
			document.getElementById("ar"+con).className='style11 cabecera2';	
		}
	};
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
	//alert("valor=1&CodArea="+CodArea+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&FechaFormato="+FechaFormato+"&HoraFormato="+HoraFormato+"&CodUsuario="+CodUsuario); //Posting txtname to Servlet
	ajax.send("valor=1&CodArea="+CodArea+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&FechaFormato="+FechaFormato+"&HoraFormato="+HoraFormato+"&CodUsuario="+CodUsuario); //Posting txtname to Servlet
	
}

function CrearImagenesContingencia(){
	 var NumeroDocumento=document.getElementById("txtnumdoc").value; 
	 var txtFecha=document.getElementById("txtFecha").value;
	 var cmbExamen=document.getElementById("cmbExamen").value;
	 var txtResultadoImg=document.getElementById("txtResultadoImg").value;
	 var txtCodPac=document.getElementById("txtCodPac").value;
	 var y=txtFecha.split("/").length;
  	var z=txtFecha.split("/");		     	
  	for(x=0; x<y-1; x=x+1)
  	{ 
	    Dia=z[0]; Mes=z[1];  Ano=z[2];
	   
	    }
  	var FechaSql=Ano+"-"+Mes+"-"+Dia;
	 ajax=getXMLObject();
	 ajax.open("POST","ControlCrearPregunta",true); //getname will be the servlet name
	 ajax.onreadystatechange=function() {
		 if (ajax.readyState==4) {
			 alert("Datos Ingresados Correctamente.");
			 document.getElementById("txtResultadoImg").value="";
			 document.getElementById("txtFecha").value="";
			 //document.getElementById('Admisiones').innerHTML = ajax.responseText;
		 }
	 }
	 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 ajax.send("valor=GBPCI&txtResultadoImg="+encodeURIComponent(txtResultadoImg)+"&NumeroDocumento="+NumeroDocumento+"&FechaSql="+FechaSql+"&cmbExamen="+cmbExamen+"&txtCodPac="+txtCodPac); //Posting txtname to Servlet

	 
}
function BuscarPacienteImgContingencia(){
	 var TipoDocumento=document.getElementById("cbafiliacion").value; 
	 var NumeroDocumento=document.getElementById("txtnumdoc").value; 
	 if(NumeroDocumento==""){
		alert("Escriba Numero Documento."); 
	 }else{
		 ajax=getXMLObject();
		 ajax.open("POST","ControlCrearPregunta",true); //getname will be the servlet name
		 ajax.onreadystatechange=function() {
			 if (ajax.readyState==4) {
				 document.getElementById('Admisiones').innerHTML = ajax.responseText;
			 }
		 }
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=BPCI&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
	 }
}


function BuscarPacienteContingencia(){
	 var TipoDocumento=document.getElementById("cbafiliacion").value; 
	 var NumeroDocumento=document.getElementById("txtnumdoc").value; 
	 if(NumeroDocumento==""){
		alert("Escriba Numero Documento."); 
	 }else{
		 ajax=getXMLObject();
		 ajax.open("POST","ControlCrearPregunta",true); //getname will be the servlet name
		 ajax.onreadystatechange=function() {
			 if (ajax.readyState==4) {
				 document.getElementById('Admisiones').innerHTML = ajax.responseText;
				 document.getElementById('Reportes').innerHTML ='';
			 }
		 }
		 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		 ajax.send("valor=BPC&TipoDocumento="+TipoDocumento+"&NumeroDocumento="+NumeroDocumento); //Posting txtname to Servlet
	 }
}

function CrearFormatoContingencia(){
	var cmbCodAdm=document.getElementById("cmbCodAdm").value;
	var CodUsuario=document.getElementById("CodUsuario").value;
	var TipoFormato=document.getElementById("TipoFormato").value;
	var txtFecha=document.getElementById("txtFecha").value;
	var cmbHora=document.getElementById("cmbHora").value;
	var cmbMinuto=document.getElementById("cmbMinuto").value;
	var txtCodPac=document.getElementById("txtCodPac").value;
	
	if(cmbCodAdm=="Seleccione"){
		alert("Seleccione Fecha de Admision");
	}else{
		if(CodUsuario=="Seleccione"){
			alert("Seleccione el Usuario");
		}else{
			if(TipoFormato=="Seleccione"){
				alert("Seleccione el Formato a Realizar");
			}else{
				if(txtFecha==""){
					alert("Digite Fecha Formato.");
				}else{
					if(cmbHora=="HH"||cmbMinuto=="MM"){
						alert("Seleccione Hora/Minuto ");
					}else{
						var HoraFormato=cmbHora+":"+cmbMinuto+":00";
						var y=txtFecha.split("/").length;
				     	var z=txtFecha.split("/");		     	
				     	for(x=0; x<y-1; x=x+1)
				     	{ 
					    Dia=z[0];
					    Mes=z[1];
					    Ano=z[2];
					   
					    }
				     	var FechaSql=Ano+"-"+Mes+"-"+Dia;
						 ajax=getXMLObject();
						 ajax.open("POST","ControlCrearPregunta",true); //getname will be the servlet name
						 ajax.onreadystatechange=function() {
							 if (ajax.readyState==4) {
								 document.getElementById('Reportes').innerHTML = ajax.responseText;
							 }
						 }
						 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
						/* alert("valor=GPC" +
							 		"&cmbCodAdm="+cmbCodAdm+
							 		"&CodUsuario="+CodUsuario+
							 		"&TipoFormato="+TipoFormato+
							 		"&txtFecha="+FechaSql+
							 		"&HoraFormato="+HoraFormato); */
						 
						 ajax.send("valor=GPC" +
						 		"&cmbCodAdm="+cmbCodAdm+
						 		"&CodUsuario="+CodUsuario+
						 		"&TipoFormato="+TipoFormato+
						 		"&txtFecha="+FechaSql+
						 		"&HoraFormato="+HoraFormato+
						 		"&txtCodPac="+txtCodPac); //Posting txtname to Servlet

						
					}
				}
			}
		}
	}
}

