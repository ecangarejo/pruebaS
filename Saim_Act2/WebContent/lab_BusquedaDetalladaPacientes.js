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
//////////////////////////////////////////////////////////////////////////////////////////////////////////

function BusquedaEspecifica(){
	var Nombre=document.getElementById("txtNombre").value;
	var PrimerApellido=document.getElementById("txtPrimerApellido").value;
	var SegundoApellido=document.getElementById("txtSegundoApellido").value;
	var Resultado=document.getElementById("ResultadoBusqueda");
		ajax=getXMLObject();
		ajax.open("POST","lab_ExaBuscar",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				Resultado.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		
		if((Nombre!="")&&(PrimerApellido!="")&&(SegundoApellido!="")){	
			//ConsultaNombrePapellidoSapellido
			ajax.send("op=1&z=4&Nombre="+Nombre+"&PrimerApellido="+PrimerApellido+"&SegundoApellido="+SegundoApellido);
		}
		
		if((Nombre!="")&&(PrimerApellido!="")&&(SegundoApellido=="")){	
			//ConsultaNombrePapellido
			ajax.send("op=2&z=4&Nombre="+Nombre+"&PrimerApellido="+PrimerApellido);
		}
		
		if((Nombre!="")&&(PrimerApellido=="")&&(SegundoApellido!="")){	
			//ConsultaNombreSapellido
			ajax.send("op=3&z=4&Nombre="+Nombre+"&SegundoApellido="+SegundoApellido);
		}
		
		if((Nombre=="")&&(PrimerApellido!="")&&(SegundoApellido!="")){	
			//ConsultaPapellidoSapellido
			ajax.send("op=4&z=4&PrimerApellido="+PrimerApellido+"&SegundoApellido="+SegundoApellido);
		}
		
		if((Nombre!="")&&(PrimerApellido=="")&&(SegundoApellido=="")){	
			//ConsultaNombre
			ajax.send("op=5&z=4&Nombre="+Nombre);
		}
		
		if((Nombre=="")&&(PrimerApellido!="")&&(SegundoApellido=="")){		
			//ConsultaPapellido
			ajax.send("op=6&z=4&PrimerApellido="+PrimerApellido);
		}
		
		if((Nombre=="")&&(PrimerApellido=="")&&(SegundoApellido!="")){	
			//ConsultaSapellido
			ajax.send("op=7&z=4&SegundoApellido="+SegundoApellido);
		}
}

function VerLaboratoriosPaciente(CodPac){
	//alert(CodPac)
	var Resultado=document.getElementById("ResultadoBusqueda");
	ajax=getXMLObject();
	ajax.open("POST","lab_ExaBuscar",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			Resultado.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("z=5&CodPac="+CodPac);
}

/***********************ABRIR LOS PDF**************************/
function Abrir(ano,mes,dia,hora,minuto,segundo,codpa,codexa,codgenero) {	
	var fecha=ano+"-"+mes+"-"+dia
	var horas=hora+":"+minuto+":"+segundo			
   pagina="Lab_Reporte_Grupo.jsp?hora="+horas+"&fecha="+fecha+"&subarea="+codexa+"&codpac="+codpa+"&codge="+codgenero+"";			
   var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
   opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
}

function Abrir_ventana (ano,mes,dia,hora,minuto,segundo,tipo,exa,codpa,codres,valorini,valorfinal) {

	fecha=ano+"-"+mes+"-"+dia
	horas=hora+":"+minuto+":"+segundo		
	if(tipo=="2"){
   pagina="Lab_Reporte_Rango.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"&valorini="+valorini+"&valorfi="+valorfinal+"";			
   var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
   opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   window.open(pagina,"NUEVA",opciones);
	}else{
		  pagina="Lab_Reporte_Texto.jsp?hora="+horas+"&fecha="+fecha+"&exa="+exa+"&codpac="+codpa+"&codre="+codres+"";
         var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
         opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
         window.open(pagina,"NUEVA",opciones);
	}	
}