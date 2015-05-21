function objetoAjax(){
	var xmlhttp=false;
	try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	} catch (e) {
		try {
		   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (E) {
			xmlhttp = false;
  		}
	}

	if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
		xmlhttp = new XMLHttpRequest();
	}
	return xmlhttp;
}
/////////////////////////////////////////////////////////////////////////////////
/*
EASY TABS 1.2 Produced and Copyright by Koller Juergen
www.kollermedia.at | www.austria-media.at
Need Help? http:/www.kollermedia.at/archive/2007/07/10/easy-tabs-12-now-with-autochange
You can use this Script for private and commercial Projects, but just leave the two credit lines, thank you.
*/

//EASY TABS 1.2 - MENU SETTINGS
//Set the id names of your tablinks (without a number at the end)
var tablink_idname = new Array("tablink","anotherlink")
//Set the id names of your tabcontentareas (without a number at the end)
var tabcontent_idname = new Array("tabcontent","anothercontent") 
//Set the number of your tabs in each menu
//en el primer digito se pones los tabactivos osea los que se le clic
var tabcount = new Array("7","7")
//Set the Tabs wich should load at start (In this Example:Menu 1 -> Tab 2 visible on load, Menu 2 -> Tab 5 visible on load)
var loadtabs = new Array("1","")  
//Set the Number of the Menu which should autochange (if you dont't want to have a change menu set it to 0)
//var autochangemenu = 2;
//the speed in seconds when the tabs should change
//var changespeed = 3;
//should the autochange stop if the user hover over a tab from the autochangemenu? 0=no 1=yes
//var stoponhover = 0;
//END MENU SETTINGS


/*Swich EasyTabs Functions - no need to edit something here*/
function easytabs(menunr, active) {	
	//alert("menunr "+menunr+" active "+active);
	//alert(active)
	menunr = menunr-1;
	for (i=1; i <= tabcount[menunr]; i++){
		document.getElementById(tablink_idname[menunr]+i).className='tab'+i;
		document.getElementById(tabcontent_idname[menunr]+i).style.display = 'none';
		}
	document.getElementById(tablink_idname[menunr]+active).className='tab'+active+' tabactive';
	document.getElementById(tabcontent_idname[menunr]+active).style.display = 'block';
	var CodigoPaciente="";
	/***********************OPCIONES DE LABORATORIO******************************************/
	if(active==1){
		DivValor=document.getElementById('tabcontent1');
		ajax=objetoAjax();
		    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=0"); //Posting txtname to Servlet
			
		}
	/**********************si es para ver el listado completo*********************/
	
	
	
	/**********************fin de ver el listado completo*********************/
	/***********************FIN DE OPCIONES DE LABORATORIO******************************************/

	if(active==2){
		DivValor=document.getElementById('tabcontent2');
		CodigoPaciente=document.getElementById("CodPac").value;
		ajax=objetoAjax();
		    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=4&CodPac="+CodigoPaciente); //Posting txtname to Servlet
		}
	//alert(imagenes)
	if(active==3){
		DivValor=document.getElementById('tabcontent3');
		ajax=objetoAjax();
		    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
		    		//alert(Contenido)
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=5"); //Posting txtname to Servlet
		//alert("Orden de Servicio");
		}
	if(active==4){
		DivValor=document.getElementById('tabcontent4');
		ajax=objetoAjax();
		    ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
		    ajax.onreadystatechange  = function(){
		    	if (ajax.readyState == 4) {				
		    		Contenido=ajax.responseText;
		    		//alert(Contenido)
					DivValor.innerHTML=Contenido;
		  	   	}
			  }
		  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		    ajax.send("valor=10"); //Posting txtname to Servlet
		    CargarFormatos();
		//alert("formatos");
		}
	if(active==5){
		//alert("antecedentes");
		}
	if(active==6){		
		//alert("en estudio");
		}
	if(active==7){
		//alert("en estudio");
		}
	
	}

function ComprobarAsignarFormato() {

	   if (xmlhttp.readyState == 4) {
		if(xmlhttp.status == 200) {
			var resultado=xmlhttp.responseText;

			document.getElementById('FormatosPaciente').innerHTML = resultado;
	     }
	     else {
	        alert("Error during AJAX call ComprobarAsignarFormato. Please try again");
	     }
	   }
	   //VerLaboraHistoria();
	}

function AsignarFormato(){
	var CodFormato=document.getElementById("txtCodFormato").value;
	var CodAdmision=document.getElementById("CodAdm").value;
	var CodPaciente=document.getElementById("CodPac").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	var Hora=document.getElementById("txtHora").value;
	var Fecha=document.getElementById("txtFecha").value;
	
	if((CodPaciente=="")&&(CodAdmision=="")){
		alert("No Se Ha Seleccionado Ningun Paciente!!!");
	}		
	if((CodPaciente!="")&&(CodAdmision!="")){
		ajax=objetoAjax();
		ajax.open("POST","ControlListarPacientes",true); //getname will be the servlet name
		ajax.onreadystatechange  = ComprobarAsignarFormato;
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&CodFormato="+CodFormato+"&CodAdmision="+CodAdmision+"&CodPaciente="+CodPaciente+"&CodUsuario="+CodUsuario+"&Hora="+Hora+"&Fecha="+Fecha); //Posting txtname to Servlet
	}
	CargarFormatos();
}

function CargarFormatos(){
	alert("Formatos Cargados Satisfactoriamente");	
	var CodPac=document.getElementById("CodPac").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodFormato=document.getElementById("txtCodFormato").value;
	ajax=objetoAjax();
	ajax.open("POST","ControlFormatosPestanas");
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('FormatosPaciente').innerHTML = ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11&CodAdmision="+CodAdm+"&CodPaciente="+CodPac);
}

function VerAreas(CodFormato,dia,mes,anio,horas,minutos,segundos){
	
	var Fecha=anio+"-"+mes+"-"+dia;
	var Hora=horas+":"+minutos+":"+segundos;
	ajax=objetoAjax();
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			document.getElementById('areas').innerHTML = ajax.responseText;
			var div =document.getElementById("formulario");
			div.style.display='none';
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=0&CodFormato="+CodFormato+"&HoraFormato="+Hora+"&FechaFormato="+Fecha); //Posting txtname to Servlet
		   //var x;
}

function ActualizarResultHistorias(Resul,CodResul){
	ajax=objetoAjax();	
		var hora=document.getElementById("HoraFormato").value;
		var fecha=document.getElementById("FechaFormato").value;
		ajax.open("POST","ControlIngresarResultados",true); //getname will be the servlet name
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1&Resul="+Resul+"&CodResul="+CodResul); //Posting txtname to Servlet    
}
function ActualizarResultados(){
	var c=document.getElementById('txtTotal').value;
	var oscar=c;
	if(c!=1){
		for(var i=0; i<=c-1; i++){
			Resul=document.form1.txtRespuesta[i].value;
			CodResul=document.form1.txtCodResultado[i].value;
			ActualizarResultHistorias(Resul,CodResul);
	      oscar--; 
	     }	
	 }else{	
		 Resul=document.form1.txtRespuesta.value;
		 CodResul=document.form1.txtCodResultado.value;
		 ActualizarResultHistorias(Resul,CodResul);
	 }
}

function mostarpreguntas(CodArea){
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;
	var FechaFormato=document.getElementById("FechaFormato").value;
	var HoraFormato=document.getElementById("HoraFormato").value;
	var CodUsuario=document.getElementById("txtCodusuario").value;
	ajax=objetoAjax();
	ajax.open("POST","ControlVerFormatos",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if(ajax.readyState == 4){
			div = document.getElementById('formulario');
			div.style.display = '';
			document.getElementById('formulario').innerHTML = ajax.responseText;
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&CodArea="+CodArea+"&CodAdm="+CodAdm+"&CodPac="+CodPac+"&FechaFormato="+FechaFormato+"&HoraFormato="+HoraFormato+"&CodUsuario="+CodUsuario); //Posting txtname to Servlet
	
}


function enviar(){
	
		var usuario=document.getElementById("txtCodusuario").value;  
		var codPaciente=document.getElementById("CodPac").value;	
		var codExamen=document.getElementById("txtcodexamen").value;
		var hora=document.getElementById("txtHora").value;
		var fecha=document.getElementById("txtFecha").value;	
		var datosClinico=document.getElementById('txtdatosClinicos').value;
		var portatil="";
		if(document.form1.chkportatil.checked){
			 portatil=document.form1.chkportatil.value;
			}
		if(codPaciente==""){
			alert("No se Ha Seleccionado Ningun Paciente");
			}
		if(codExamen==""){
			alert("No se Ha Seleccionado Ningun Examen");
			}
		if(hora==""){
			alert("No hay Hora Seleccionado");
			}
		if(fecha==""){
			alert("No hay Fecha Seleccionado");
			}
		if(datosClinico==""){
			alert("Los Datos Clinicos No Pueden Ir Vacios");
			}
		var e;
	    /*for(e=0;e<datosClinico.length;e++){
	    	datosClinico=datosClinico.replace('Ñ','@');
			datosClinico=datosClinico.replace('ñ','@');
	    	datosClinico=datosClinico.replace('Á','A');
	    	datosClinico=datosClinico.replace('É','E');
	    	datosClinico=datosClinico.replace('Í','I');
	    	datosClinico=datosClinico.replace('Ó','O');
	    	datosClinico=datosClinico.replace('Ú','U');
	    	datosClinico=datosClinico.replace('á','a');
	    	datosClinico=datosClinico.replace('é','e');
	    	datosClinico=datosClinico.replace('í','i');
	    	datosClinico=datosClinico.replace('ó','o');
	    	datosClinico=datosClinico.replace('ú','u');
	    }*/
		if((codPaciente!="")&&(codExamen!="")&&(hora!="")&&(fecha!="")&&(datosClinico!="")){
		ajax=objetoAjax();
	    ajax.open("POST","ControlCitasExamen",true); //getname will be the servlet name
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3&codigoExa_fk="+codExamen+"&codigoPac_fk="+codPaciente+"&estado=-1&usuario="+usuario+"&hora="+hora+"&fecha="+fecha+"&datosClinico="+datosClinico+"&portatil="+portatil); //Posting txtname to Servlet		
		codExamen="";
		alert("Examenes Asignados Correctamente!!!");
		var NomExamen=document.getElementById("txtnomexam").value="";
		document.form1.chkportatil.checked=false;
		NomExamen.focus();
		}
		//finalizar();	    
//	}	
}

function EnviarLaboratorios(CodEstudio,TipoEstu){
	var CodigoPac=document.getElementById("CodPac").value;
	var HoraPeticion=document.getElementById("txtHora").value;
	var FechaPeticion=document.getElementById("txtFecha").value;
	ajax=objetoAjax();
	ajax.open("POST","ControlFormatosPestanas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=9&CodEstudio="+CodEstudio+"&TipoEstu="+TipoEstu+"&CodigoPac="+CodigoPac+"&HoraPeticion="+HoraPeticion+"&FechaPeticion="+FechaPeticion);
}


function GuardarLaboratorios(){	
	var total=document.getElementById('yo').value;
	var cont=total;	
	if(total!=1){
		for(var h=0; h<=total-1; h++){			
			if(document.form1.ca[h].checked){
				CodEstudio=document.form1.ca[h].value;
				TipoEstu=document.form1.txtTipo[h].value;
				EnviarLaboratorios(CodEstudio,TipoEstu);
			}
			cont--;
		}
	}
	else{
		if(document.form1.ca.checked){
			CodEstudio=document.form1.ca.value;
			TipoEstu=document.form1.txtTipo.value;
			EnviarLaboratorios(CodEstudio,TipoEstu);
		}
	}
}

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
function VerExamenes(){	
	DivValor=document.getElementById('examenes');
	var CodArea=document.getElementById("cmbgrupos").value;
	ajax=objetoAjax();
	ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {				
			Contenido=ajax.responseText;
			DivValor.innerHTML=Contenido;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8&CodArea="+CodArea); //Posting txtname to Servle	
}

function Radio2() {	
	ajax=objetoAjax();
	var contenido=document.getElementById('opciones');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
	         ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	         ajax.onreadystatechange  = function(){
	        	 if (ajax.readyState == 4) {
	        		 contenido.innerHTML = ajax.responseText	
	        	 }
	         };
	         ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	         ajax.send("valor="+val); //Posting txtname to Servlet
		  }	     
    }
}

function Radio() {	
	ajax=objetoAjax();
	var contenido=document.getElementById('ContenidoLaboratorio');
	var radioButtons = document.getElementsByName("radiobutton");
	var CodigoPaciente=document.getElementById("CodPac").value;
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&CodPac="+CodigoPaciente); //Posting txtname to Servlet
		}	     
	}
}

function historial(CodExamen,CodPac){
	ajax=objetoAjax();
	var DivValor=document.getElementById("HistorialLaboratorios");
	 ajax.open("POST","ControlFormatosPestanas",true); //getname will be the servlet name
	    ajax.onreadystatechange  = function(){
	    	if (ajax.readyState == 4) {				
	    		Contenido=ajax.responseText;
				DivValor.innerHTML=Contenido;
	  	   	}
		  }
	  	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	    ajax.send("valor=3&CodPac="+CodPac+"&CodExamen="+CodExamen); //Posting txtname to Servlet
}
function mostrarHistorial(){
    var c=document.getElementById('txtContador').value;
    var CodPac=document.getElementById("CodPac").value;
	var oscar=c;
	if(c!=1){
		for(var i=0; i<=c-1; i++){	
			if (document.form1.chkExamenes[i].checked) {
				CodExamen=document.form1.chkExamenes[i].value;       	 
				historial(CodExamen,CodPac);
			}     	  
			oscar--; 
		}	
	} 
	else{
		if(document.form1.chkExamenes.checked){
			CodExamen=document.form1.chkExamenes.value;
			historial(CodExamen,CodPac);
		}
	}
}

function cargar(){
var menucount=loadtabs.length; 
var a = 0; 
var b = 1; 
do {
	easytabs(b, loadtabs[a]);  
	a++; 
	b++;
	}
while (b<=menucount);

}

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

function mostarexamenes (codIce,usuario) {		
	pagina="img_ReporteImg.jsp?codIce="+codIce+"&usuario="+usuario;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}