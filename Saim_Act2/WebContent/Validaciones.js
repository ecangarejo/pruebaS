


function lab_paciente(e){	
	

		var nombre,apellidos,telefono,numero,eps,direccion,edad,tipo,email,genero;
        var ti=e.tipodoc.selectedIndex;
		tipo=e.tipodoc.options[ti].text;
		
		var ge=e.cbgenero.selectedIndex;
		genero=e.cbgenero.options[ge].text;
		
		numero=e.txtnumdocu.value;
		//alert("listo");alert("listo");
		apellidos=e.txtapellidos.value;
	     
		nombre=e.txtnombres.value;
		edad="0";
		
		telefono=e.txtele.value;
		direccion=e.txtdireccion.value;
		
		fechanaci=e.txtfechanaci.value;
		 
		
		 for(i=0;i<direccion.length;i++){
		      direccion=direccion.replace('#','N�');
		      //alert(direccion);
		    }
		
		eps=e.txteps.value;
		email=e.txtemail.value;
		if(edad==""){
			edad="0";
			}
	 
        if((nombre=="")||(apellidos=="")||(numero=="")||(telefono=="")||(direccion=="")||(genero=="SELECCIONE...")||(fechanaci=="")){
        	alert("Falta LLenar Campos Requeridos...!");
        }else{
        	window.location.href="Controllab_Pac?fechana="+fechanaci+"&nom="+nombre+"&ape="+apellidos+"&ep="+eps+"&ced="+numero+"&eda="+edad+"&tele="+telefono+"&dire="+direccion+"&ema="+email+"&gene="+genero+"&ti="+tipo+"",true;
         		   alert("Ingreso Exitoso...!");
        }
        
}

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



//final de la admision

function finaladmision(e){	
	//alert("listo..ya llego a la actualizacion..");
	
	var numingreso,numcama,codpaciente,autopor,fecha;
	
	numingreso=e.txtnuming.value;
	numcama=e.txtnumcama.value;
	codpaciente=e.txtpac.value;
	autopor=e.txtauto.value;
	fecha=e.txtfecha1.value;
    
    window.location.href="ControlAdmiFinal?numingreso="+numingreso+"&numcama="+numcama+"&codpaciente="+codpaciente+"&autopor="+autopor+"&fecha="+fecha+"",true;
    
}


 function focus(h){
	 h.focus();	
 }
var emailfilter=/^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i
function checkmail(e){
var returnval=emailfilter.test(e.value)
if (returnval==false){
alert("Direccion de Correo Invalida");
e.value="";
e.focus();	

}
return returnval
}


function pulsar(e) {
	  tecla = (document.all) ? e.keyCode : e.which;
	  return (tecla != 13);
	}

function preingreso(e){	
	/*
	 * */
		var eps=document.getElementById("cbeps").value;		
		var tipo=document.getElementById("cbtipodoc").value;
		var nombre=document.getElementById("txtnombre").value;
		var papellido=document.getElementById("txtpriapellido").value;
		var sapellido=document.getElementById("txtseapellido").value;
		var cedula=document.getElementById("txtnumdocu").value;
		var fecha=document.getElementById("txtfecha").value;
		var hora=document.getElementById("txthora").value;
		var genero=document.getElementById("cmbGenero").value; 
		var FechaNacimiento=document.getElementById("txtFechaNacimiento").value;
		var td,ce,no,ap,ge,ep;
		
		td=document.getElementById('td');
		ce=document.getElementById('ce');
		no=document.getElementById('no');
		ap=document.getElementById('ap');
		ge=document.getElementById('ge');
		ep=document.getElementById('ep');
		
		td.style.display="none";
		ce.style.display="none";
		no.style.display="none";
		ap.style.display="none";
		ge.style.display="none";
		ep.style.display="none";
		
		
		
        if(nombre==""){
        	no.style.display="";
        }
        if(papellido==""){
        	ap.style.display="";
        }
        if(cedula==""){
        	ce.style.display="";
        }
        if(eps==""){
        	ep.style.display="";
        }
        if(tipo=="Seleccione"){
        	td.style.display="";
        }
        if(genero=="Seleccione"){
        	ge.style.display="";
        }
        if((nombre!="")&&(papellido!="")&&(cedula!="")&&(eps!="")&&(tipo!="Seleccione")&&(genero!="Seleccione")){
        	window.location.href="ControlPing?nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&ho="+hora+"&ti="+tipo+"&ep="+eps+"&genero="+genero+"&FechaNaci="+FechaNacimiento,true;
        	alert("Ingreso Exitoso...!");
        	window.location.href="Adm_Ping.jsp";
        }
}

function getCheckedValue(h) {
	//alert("listo");
	/*var kg;
	/if(!h.radio)
		return "";
	var radioLength = h.radio.length;
	//alert(radioLength);
	
	for(var i = 0; i < radioLength; i++) {
		if(h.radio[i].checked) {
			kg= h.radio[i].value;			
		}	
	}
	alert(kg);
	if(kg=="SI"){*/
	//alert(h.txtce.value);
	//alert(h.txttipo.value);
		 window.location.href="Adm_Nacido_Vivo.jsp?cedula="+h.txtce.value+"&tipo="+h.txttipo.value+"&nomadre="+h.txtape.value+"&primadre="+h.txtpape.value+"&semadre="+h.txtsap.value+"";
	/*}
	if(kg==null){
		// window.location.href="Adm_Ingreso.jsp?z="+1+"&cedula="+h.txtce.value+"&tipo="+h.txttipo.value+"&nom="+h.txtnom.value+"";
	}
	
	return "";*/
	
}
//Remision

function remision(e){	
	
	var nombre,papellido,sapellido,cedula,eps,fecha,hora,tipo,numauto,auto,cama1;
    //var ep=e.cbeps.selectedIndex;
	eps=e.cbeps.value;
	tipo=e.cbtipodoc.value;
	//tipo=e.cbtipodoc.options[ti].text;
	nombre=e.txtnombre.value;
	papellido=e.txtpriapellido.value;
	sapellido=e.txtseapellido.value;
    cedula=e.txtnumdocu.value;
    fecha=e.txtfecha.value;
    hora=e.txthora.value;
    numauto=e.numeroaut.value;
    auto=e.autorizacion.value;
    cama1=e.textfield13.value
    
    
    var no, ap, ce, na, au, ca, ep,td;
    
    no=document.getElementById('no');
    ap=document.getElementById('ap');
    ce=document.getElementById('ce');
    na=document.getElementById('na');
    au=document.getElementById('au');
    ca=document.getElementById('ca');
    ep=document.getElementById('ep');
    td=document.getElementById('td');
    
    no.style.display="none";
    ap.style.display="none";
    ce.style.display="none";
    na.style.display="none";
    au.style.display="none";
    ca.style.display="none";
    ep.style.display="none";
    td.style.display="none";

    if(nombre==""){
    	no.style.display="";
    	
    }if(papellido==""){
    	ap.style.display="";
    }if(cedula==""){
    	ce.style.display="";
    }if(numauto==""){
    	na.style.display="";
    }if(auto==""){
    	au.style.display="";
    }if(cama1==""){
    	ca.style.display="";
    }if(eps==""){
    	ep.style.display="";
    }
    if((nombre!="")&&(papellido!="")&&(cedula!="")&&(numauto!="")&&(auto!="")&&(cama1!="")&&(eps!="")){
    
    window.location.href="ControlRemision?nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&ho="+hora+"&ti="+tipo+"&ep="+eps+"&num="+numauto+"&aut="+auto+"&cama="+cama1+"",true;
 	alert("Ingreso Exitoso...!");
 	window.location.href="menuadm.jsp";
    }
    
}

//nacido vivo

function Nacido_Vivo(e){	
	
	var nombre,papellido,sapellido,cedula,fecha,tipo,afiliacion,nivel,genero,nacionalidad,direccion,telefijo,teleofi,telecel,ocupacion,empresa,zona,religion,estadocivil,raza,estrato,email,codimun,numcontrato;
	tipo=e.txttipo.value;
    afiliacion=e.txtafi.value;
	papellido=e.txtape.value;
	sapellido=e.txtsape.value;
	nombre=e.txtnombre.value;
	fecha=e.txtfecha.value;
	telefijo="0";
	teleofi="0";
	telecel="0";
	nivel="1";
	var ge=e.cbgenero.selectedIndex;
	genero=e.cbgenero.options[ge].text;
	nacionalidad="COLOMBIA";
	zona="0";
	estadocivil="0"
	estrato="0"
    cedula=e.txtnum.value;
    direccion="0";
    ocupacion="0";
    empresa="0";
    religion="0";
    raza="0";
    email="0";
    codimun="784";
    numcontrato="345";
  
   if((cedula=="")||(tipo=="")||(afiliacion=="")||(papellido=="")||(nombre=="")||(fecha=="")||(genero=="SELECCIONE...")){
	   alert("Falta LLenar Campos Requeridos...!");
   }else{
	 
	   window.location.href="ControlIngresoPac?nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&dir="+direccion+"&telefi="+telefijo+"&teleofici="+teleofi+"&telecel="+telecel+"&ocu="+ocupacion+"&emp="+empresa+"&re="+religion+"&ra="+raza+"&ema="+email+"&codmun="+codimun+"&numco="+numcontrato+"&ti="+tipo+"&afi="+afiliacion+"&ni="+nivel+"&ge="+genero+"&naci="+nacionalidad+"&zona="+zona+"&esta="+estadocivil+"&estra="+estrato+"",true;
	   alert("Ingreso Exitoso...!"); 
	   window.location.href="Adm_Ingreso.jsp";
	   //window.location.href="Adm_Confirmar.jsp";
	  // window.location.href="Adm_Confirmar.jsp?cedula="+cedula+"&tipo="+tipo+"&nomadre="+nombre+"&primadre="+papellido+"&semadre="+sapellido+"";

    }
    
}

/*
function demograficoSolo(e){
	var nombre,papellido,sapellido,cedula,fecha,tipo,afiliacion,nivel,genero,nacionalidad,direccion,telefijo,teleofi,telecel,ocupacion,empresa,zona,religion,estadocivil,raza,estrato,email,codimun,numcontrato,codcama;
	var ti=e.tipodoc.selectedIndex;
	tipo=e.tipodoc.options[ti].text;
	paciente=e.cbeps.value;
	var de=e.cbdep.selectedIndex;
	dep=e.cbdep.options[de].text;
	var mun=e.cbmun.selectedIndex;
	muni=e.cbmun.options[mun].text;
	var afi=e.select2.selectedIndex;
	afiliacion=e.select2.options[afi].text;
	var ni=e.cbnivel.selectedIndex;
	nivel=e.cbnivel.options[ni].text;
	var ge=e.select4.selectedIndex;
	genero=e.select4.options[ge].text;
	var na=e.cbnacionalidad.selectedIndex;
	nacionalidad=e.cbnacionalidad.options[na].text;
	var zo=e.cbzona.selectedIndex;
	zona=e.cbzona.options[zo].text;
	var es=e.select6.selectedIndex;
	estadocivil=e.select6.options[es].text;
	var est=e.select7.selectedIndex;
	estrato=e.select7.options[est].text;
	nombre=encodeURIComponent(e.txtnombre.value);
	papellido=encodeURIComponent(e.txtpapellido.value);
	sapellido=encodeURIComponent(e.txtsapellido.value);
    cedula=e.txtnumdocu.value;
    fecha=e.txtfechanaci.value;
    direccion=encodeURIComponent(e.txtdire.value);    
    
    /*var i;
    for(i=0;i<direccion.length;i++){
      direccion=direccion.replace('#','N�');
    }*/    
   /* telefijo=e.txtelefijo.value;
    teleofi=e.txteleoficina.value;
    telecel=e.txtcelular.value;   
    ocupacion="0"    	 
    empresa="No Aplica";
    religion=encodeURIComponent(e.txtreligion.value);
    raza=encodeURIComponent(e.txtraza.value);
    email=encodeURIComponent(e.txtemail.value);
    codimun=e.txtcodmun.value;
    numcontrato=e.txtcodentidad.value;
    
    
    if((nivel=="SELECCIONE...")){
    	nivel="";
    } 

    if((telefijo=="")||(teleofi=="")||(telecel=="")){
    	telefijo="0";
    	teleofi="0";
    	telecel="0";
    }    
   if(direccion==""){
	   alert("Falta Ingresar La Direccion.");
	 //  alert(nombre);
   }
   if(fecha.length!="10"){
	   alert("El Formato de la Fecha de Nacimiento es Erroneo.\nEl Formato Correcto Es DD/MM/AAAA ejemplo:14/09/1985")
   }
	   
   if(fecha==""){
	   alert("Falta Ingresar La Fecha De Nacimiento!!!");
   }
   if(cedula==""){
	   alert("Falta Ingresar El Numero De Identificacion!!!");
   }
   if(papellido==""){
	   alert("Falta Ingresar El Primer Apellido!!!");
   }
   if(nombre==""){
	   alert("Falta Ingresar El Nombre!!!");
   }
   if(afiliacion=="SELECCIONE..."){
	   alert("Falta Seleccionar El Tipo De Afiliacion!!!");
   }
   if(genero=="SELECCIONE..."){
	   alert("Falta Seleccionar El Genero!!!");
   }
   if(nacionalidad=="SELECCIONE..."){
	   alert("Falta Seleccionar La Nacionalidad!!!");
   }
   if(zona=="SELECCIONE..."){
	   alert("Falta Seleccionar Zona Residencia!!!");
   }
   if(estadocivil=="SELECCIONE..."){
	   alert("Falta Seleccionar El Estado Civil!!!");
   }
   if(estrato=="SELECCIONE..."){
	   alert("Falta Seleccionar El Estrato!!!");
   }
   if(codimun==""){
	   alert("Falta Seleccionar El Municipio!!!");
   }
   if(dep=="SELECCIONE..."){
	   alert("Falta Seleccionar El Departamento!!!");
   }
   if(muni=="SELECCIONE..."){
	   alert("Falta Seleccionar El Municipio!!!");
   }
   if(numcontrato==""){
	   alert("Falta Seleccionar La Eps!!!");
   }

   if((direccion!="")&&(fecha!="")&&(cedula!="")&&(papellido!="")&&(nombre!="")&&(afiliacion!="SELECCIONE...")&&(genero!="SELECCIONE...")&&(nacionalidad!="SELECCIONE...")&&(zona!="SELECCIONE...")&&(estadocivil!="SELECCIONE...")&&(estrato!="SELECCIONE...")&&(codimun!="")&&(dep!="SELECCIONE...")&&(muni!="SELECCIONE...")&&(numcontrato!="")&&(fecha.length=="10")){
	   window.location.href="ControlIngresoPac?nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&dir="+direccion+"&telefi="+telefijo+"&teleofici="+teleofi+"&telecel="+telecel+"&ocu="+ocupacion+"&emp="+empresa+"&re="+religion+"&ra="+raza+"&ema="+email+"&codmun="+codimun+"&numco="+numcontrato+"&ti="+tipo+"&afi="+afiliacion+"&ni="+nivel+"&ge="+genero+"&naci="+nacionalidad+"&zona="+zona+"&esta="+estadocivil+"&estra="+estrato,true;
	   alert("Ingreso Exitoso...!");
	   window.location.href="menu.jsp";     
    }
    
}*/



//Demografico

//////////////////////////////////////////////////////////////////////////////////////////////////////////
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




function demograficoSolo(e){	
//	alert("entro");
	var nombre,papellido,sapellido,cedula,fecha,tipo,afiliacion,nivel,genero,nacionalidad,direccion,telefijo,teleofi,telecel,ocupacion,empresa,zona,religion,estadocivil,raza,estrato,email,codimun,numcontrato,codcama;
	var ti=e.tipodoc.selectedIndex;
	tipo=e.tipodoc.options[ti].text;
	paciente=e.cbeps.value;
	var de=e.cbdep.selectedIndex;
	dep=e.cbdep.options[de].text;
	var mun=e.cbmun.selectedIndex;
	muni=e.cbmun.options[mun].text;
	var afi=e.select2.selectedIndex;
	afiliacion=e.select2.options[afi].text;
	var ni=e.cbnivel.selectedIndex;
	nivel=e.cbnivel.options[ni].text;
	var ge=e.select4.selectedIndex;
	genero=e.select4.options[ge].text;
	var na=e.cbnacionalidad.selectedIndex;
	nacionalidad=e.cbnacionalidad.options[na].text;
	var zo=e.cbzona.selectedIndex;
	zona=e.cbzona.options[zo].text;
	var es=e.select6.selectedIndex;
	estadocivil=e.select6.options[es].text;
	var est=e.select7.selectedIndex;
	estrato=e.select7.options[est].text;
	nombre=encodeURIComponent(e.txtnombre.value);
	papellido=encodeURIComponent(e.txtpapellido.value);
	sapellido=encodeURIComponent(e.txtsapellido.value);
    cedula=e.txtnumdocu.value;
    fecha=e.txtfechanaci.value;
    barrio=e.txtbarrio.value;
    var longitud=e.document.getElementById("txtlongitud").value;
    var latitud=e.document.getElementById("txtlatitud").value;
    //direccion=encodeURIComponent(e.txtdire.value);    
    direccion=e.txtdire.value;
   // alert("direccion"+direccion);
    /*var i;
    for(i=0;i<direccion.length;i++){
      direccion=direccion.replace('#','N�');
    }*/    
    telefijo=e.txtelefijo.value;
    teleofi=e.txteleoficina.value;
    telecel=e.txtcelular.value;   
    ocupacion=e.txtocupacion.value;   	 
    empresa="No Aplica";
    religion=encodeURIComponent(e.txtreligion.value);
    raza=encodeURIComponent(e.txtraza.value);
    email=encodeURIComponent(e.txtemail.value);
    codimun=e.txtcodmun.value;
    numcontrato=e.txtcodentidad.value;
    barrio=e.txtbarrio.value;
    //alert("nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&dir="+direccion+"&telefi="+telefijo+"&teleofici="+teleofi+"&telecel="+telecel+"&ocu="+ocupacion+"&emp="+empresa+"&re="+religion+"&ra="+raza+"&ema="+email+"&codmun="+codimun+"&numco="+numcontrato+"&ti="+tipo+"&afi="+afiliacion+"&ni="+nivel+"&ge="+genero+"&naci="+nacionalidad+"&zona="+zona+"&esta="+estadocivil+"&estra="+estrato+"&ocupacion1="+ocupacion); //Posting txtname to Servlet*/
    
    if((nivel=="SELECCIONE...")){
    	nivel="";
    } 

    if((telefijo=="")||(teleofi=="")||(telecel=="")){
    	telefijo="0";
    	teleofi="0";
    	telecel="0";
    }    
   if(direccion==""){
	   alert("Falta Ingresar La Direccion.");
	 //  alert(nombre);
   }
   if(fecha.length!="10"){
	   alert("El Formato de la Fecha de Nacimiento es Erroneo.\nEl Formato Correcto Es DD/MM/AAAA ejemplo:14/09/1985")
   }
	   
   if(fecha==""){
	   alert("Falta Ingresar La Fecha De Nacimiento!!!");
   }
   if(cedula==""){
	   alert("Falta Ingresar El Numero De Identificacion!!!");
   }
   if(papellido==""){
	   alert("Falta Ingresar El Primer Apellido!!!");
   }
   if(nombre==""){
	   alert("Falta Ingresar El Nombre!!!");
   }
   if(afiliacion=="SELECCIONE..."){
	   alert("Falta Seleccionar El Tipo De Afiliacion!!!");
   }
   if(genero=="SELECCIONE..."){
	   alert("Falta Seleccionar El Genero!!!");
   }
   if(nacionalidad=="SELECCIONE..."){
	   alert("Falta Seleccionar La Nacionalidad!!!");
   }
   if(zona=="SELECCIONE..."){
	   alert("Falta Seleccionar Zona Residencia!!!");
   }
   if(estadocivil=="SELECCIONE..."){
	   alert("Falta Seleccionar El Estado Civil!!!");
   }
   if(estrato=="SELECCIONE..."){
	   alert("Falta Seleccionar El Estrato!!!");
   }
   if(codimun==""){
	   alert("Falta Seleccionar El Municipio!!!");
   }
   if(dep=="SELECCIONE..."){
	   alert("Falta Seleccionar El Departamento!!!");
   }
   if(muni=="SELECCIONE..."){
	   alert("Falta Seleccionar El Municipio!!!");
   }
   if(numcontrato==""){
	   alert("Falta Seleccionar La Eps!!!");
   }
   if(zona=="SELECCIONE..."){
	   alert("Falta Seleccionar Zona Rural");
   }
   
   if((zona!="SELECCIONE...")&&(direccion!="")&&(fecha!="")&&(cedula!="")&&(papellido!="")&&(nombre!="")&&(afiliacion!="SELECCIONE...")&&(genero!="SELECCIONE...")&&(nacionalidad!="SELECCIONE...")&&(codimun!="")&&(dep!="SELECCIONE...")&&(muni!="SELECCIONE...")&&(numcontrato!="")&&(fecha.length=="10")){

		  ajax=getXMLObject();
			ajax.open("POST","ControlIngresoPac",true); //getname will be the servlet name
			ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {		
					alert("Ingreso Exitoso.");
					window.location.href="menu.jsp";  
					// codcama=e.txtcodcama.value;
					 // nombrecompletopa=nombre+" "+papellido+" "+sapellido; 					
					  //window.location.href="Adm_Ingreso.jsp?z=1&nom="+nombrecompletopa+"&cedula="+cedula+"&eps="+paciente+"&tipo="+tipo+"&codcama="+codcama+"&nomUsu="+nomUsu+"&id="+id+"&va=1&codigo=1&CodColaFin="+CodColaFin;
				}
			}    		
		 	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&dir="+direccion+"&telefi="+telefijo+"&teleofici="+teleofi+"&telecel="+telecel+"&ocu="+ocupacion+"&emp="+empresa+"&re="+religion+"&ra="+raza+"&ema="+email+"&codmun="+codimun+"&numco="+numcontrato+"&ti="+tipo+"&afi="+afiliacion+"&ni="+nivel+"&ge="+genero+"&naci="+nacionalidad+"&zona="+zona+"&esta="+estadocivil+"&estra="+estrato+"&ocupacion1="+ocupacion+"&long="+longitud+"&lati="+latitud+"&bar="+barrio); //Posting txtname to Servlet*/
    }

 }


// CONCATENA LA DIRECCION EN CUADRO DE TEXTO by: Greys Hernandez
/*function Concatenar(){
		
	var Dir1=document.getElementById("ComboDir1").value;
	var Dir2=document.getElementById("ComboDir2").value;
	var Dir3=document.getElementById("ComboDir3").value;
	var Dir4=document.getElementById("ComboDir4").value;
    var CajaDir1=document.getElementById("txtDirnum1").value;
	var CajaDir2=document.getElementById("txtDirnum2").value;
	var CajaDir3=document.getElementById("txtDirnum3").value;
	 
	if (Dir4==0){
		resultado= Dir1 +" "+ CajaDir1+ Dir2 +" "+ Dir3 +" "+ CajaDir2 +Dir4+"-"+ CajaDir3;
		document.getElementById('txtdire').value=resultado; 
	}else{
        resultado= Dir1 +" "+ CajaDir1+ Dir2 +" "+ Dir3 +" "+ CajaDir2 +" "+ Dir4 +" "+ CajaDir3;
		document.getElementById('txtdire').value=resultado; 	
    }	

}*/


/*function checknum2(){
	
	var e=document.getElementById("txtDirnum1").value;
	var e1=document.getElementById("txtDirnum2").value;
	var e2=document.getElementById("txtDirnum3").value;
    var Dir1= document.getElementById("ComboDir1").value;	
	var returnval=expreg.test(e);
	if (returnval==false){
		alert("El campo solo acepta valores n\xdamericos");
		document.getElementById("txtDirnum1").value ="";
		document.getElementById("txtDirnum1").value ="";
		document.getElementById("txtDirnum1").focus();
		resul=Dir1;
		document.getElementById("txtdire").value=resul;
	}
	return returnval
}
*/

function Link(){
	 //window.location.href="geo_vista_geografica.jsp";
	window.open("http://localhost:8080/Saim/geo_vista_geografica.jsp");
}

 function cambiar(celda){
	 celda.style.backgroundColor="#A9E2F3";
	}

	function cambiar2(celda){
	 celda.style.backgroundColor="#FFFFFF";
	}
//VALIDA Y CONCATENA LA DIRECCION BY: GREYS HERNANDEZ VARGAS 


/*function Concatenar(){

	var Dir1=document.getElementById("ComboDir1").value;
	var Dir2=document.getElementById("ComboDir2").value;
	var Dir3=document.getElementById("ComboDir3").value;
	var Dir4=document.getElementById("ComboDir4").value;
    var CajaDir1=document.getElementById("txtDirnum1").value;
	var CajaDir2=document.getElementById("txtDirnum2").value;
	var CajaDir3=document.getElementById("txtDirnum3").value;
    var CajaDir4=document.getElementById("txtDirnum4").value;
	var CajaDir5=document.getElementById("txtDirnum5").value;


	 var valcaja1=expreg.test(CajaDir1);
	 var valcaja2=expreg.test(CajaDir2);
	 var valcaja3=expreg.test(CajaDir3);
	 var valcaja4=expreg.test(CajaDir4);
	 var valcaja5=expreg.test(CajaDir5);

	 
	if((valcaja1==false)||(valcaja2==false)||(valcaja3==false)||(valcaja4==false)||(valcaja5==false)){
		if(valcaja1==false){
		    alert("El campo solo acepta valores n\xdamericos");
			document.getElementById("txtDirnum1").value="";
			document.getElementById("txtDirnum1").focus();
			  //alert(valcaja1);	
		}else{
			if(valcaja2==false){
			alert("El campo solo acepta valores n\xdamericos");
			document.getElementById("txtDirnum2").value ="";
			document.getElementById("txtDirnum2").focus();
			  //alert(valcaja2);	
		 }else{
			  if(valcaja3==false){
				  alert("El campo solo acepta valores n\xdamericos");
					document.getElementById("txtDirnum3").value ="";
					document.getElementById("txtDirnum3").focus(); 
				  
			  }else{
				  if(valcaja4==false){
					  alert("El campo solo acepta valores n\xdamericos");
						document.getElementById("txtDirnum4").value ="";
						document.getElementById("txtDirnum4").focus();
						
				  }else{
					   alert("El campo solo acepta valores n\xdamericos");
						document.getElementById("txtDirnum5").value ="";
						document.getElementById("txtDirnum5").focus();
				  }
			  }
		  }
	   }
	
	}else{
		if(CajaDir1>150){
		
			document.getElementById("txtDirnum1").value="";
		    resultado=Dir1+" ";
		    document.getElementById('txtdire').value=resultado; 
		    document.getElementById('txtdiregeorre').Value=resultado;
		    alert(" Dato Incorrecto");      
		    	    
		}else{
			
			if(CajaDir2>150){
				document.getElementById("txtDirnum2").value="";
		        resultado=Dir1+" "+CajaDir1+Dir2+" ";
		        resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3;
		        document.getElementById('txtdire').value=resultado;
		        document.getElementById('txtdiregeorre').value=resulgeorrefe;
				alert(" Dato Incorrecto");
			}else{
					
				if(CajaDir3>150){
					document.getElementById("txtDirnum3").value="";
					resultado=Dir1+" "+CajaDir1+Dir2+" "+CajaDir2+" "+Dir3;
					resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3;
			        document.getElementById('txtdire').value=resultado;		
					document.getElementById('txtdiregeorre').value=resulgeorrefe;
					alert(" dato incorrecto");
					
				}else{
					if(CajaDir4>150){
						document.getElementById("txtDirnum4").value="";
						resultado=Dir1+" "+CajaDir1+Dir2+" "+CajaDir2+" "+Dir3+" "+CajaDir3+Dir4;
						resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3;
				        document.getElementById('txtdire').value=resultado;		
						document.getElementById('txtdiregeorre').value=resulgeorrefe;
						alert(" dato incorrecto");
						
					}else{
					  
						if(CajaDir5>150){
							document.getElementById("txtDirnum5").value="";
							resultado=Dir1+" "+CajaDir1+Dir2+CajaDir2+Dir3+" "+CajaDir3+Dir4+CajaDir4+CajaDir5;
							resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3+CajaDir5;
					        document.getElementById('txtdire').value=resultado;		
							document.getElementById('txtdiregeorre').value=resulgeorrefe;
							alert(" dato incorrecto");
						
					 }else{		
						 
						  if((Dir4==0)&&(CajaDir4=="")){
						
							   resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3+"-"+CajaDir5;
							   resulgeorrefe= Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3+"-"+CajaDir5;
							   document.getElementById('txtdire').value=resultado; 
							   document.getElementById('txtdiregeorre').value=resulgeorrefe;
						  }else{
							  
						   if(CajaDir4==""){
							
							   resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3+Dir4+" "+CajaDir5;
							   resulgeorrefe= Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3+"-"+CajaDir5;
							   document.getElementById('txtdire').value=resultado; 
							   document.getElementById('txtdiregeorre').value=resulgeorrefe;
								
							}else{
								   if((Dir4==0)&&(CajaDir3=="")&&(CajaDir4=="")){
									
									   resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3;
									   resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3;
										document.getElementById('txtdire').value=resultado; 
										document.getElementById('txtdiregeorre').value=resulgeorrefe;
										
								   }else{
									   if(CajaDir5==""){
									
											resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3+Dir4+CajaDir4;
											resulgeorrefe=Dir1+" "+ CajaDir1+" "+ Dir3 +" "+ CajaDir3+"-"+CajaDir5;
											document.getElementById('txtdire').value=resultado; 	
											document.getElementById('txtdiregeorre').value=resulgeorrefe; 
									   }else{
										   
											
											resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3+Dir4+CajaDir4+"-"+CajaDir5;
											resulgeorrefe=Dir1+" "+ CajaDir1+" "+ Dir3 +" "+ CajaDir3+"-"+ CajaDir5;
											document.getElementById('txtdire').value=resultado; 	
											document.getElementById('txtdiregeorre').value=resulgeorrefe;
									   }
								   }
							   }		
						 }
				     } 
				   }
			    }
			
		   }
	    }
  }

}//FIN CONCATENAR
*/
var expreg=/^[0-9]*$/; 
var cadena=/SUR/i;
function Concatenar(){

	var Dir1=document.getElementById("ComboDir1").value;
	var Dir2=document.getElementById("ComboDir2").value;
	var Dir3=document.getElementById("ComboDir3").value;
	var Dir4=document.getElementById("ComboDir4").value;
    var CajaDir1=document.getElementById("txtDirnum1").value;
	var CajaDir2=document.getElementById("txtDirnum2").value;
	var CajaDir3=document.getElementById("txtDirnum3").value;
    var CajaDir4=document.getElementById("txtDirnum4").value;
	var CajaDir5=document.getElementById("txtDirnum5").value;


	 var valcaja1=expreg.test(CajaDir1);
	 var valcaja2=expreg.test(CajaDir2);
	 var valcaja3=expreg.test(CajaDir3);
	 var valcaja4=expreg.test(CajaDir4);
	 var valcaja5=expreg.test(CajaDir5);
	 
	 
	 var prueba=cadena.test(Dir2);
	 var prueba1=cadena.test(Dir4);
	// alert(prueba);
	 //alert("prueba  "+prueba1);

	 
	if((valcaja1==false)||(valcaja2==false)||(valcaja3==false)||(valcaja4==false)||(valcaja5==false)){
		if(valcaja1==false){
		    alert("El campo solo acepta valores n\xdamericos");
			document.getElementById("txtDirnum1").value="";
			document.getElementById("txtDirnum1").focus();
			  //alert(valcaja1);	
		}else{
			if(valcaja2==false){
			alert("El campo solo acepta valores n\xdamericos");
			document.getElementById("txtDirnum2").value ="";
			document.getElementById("txtDirnum2").focus();
			  //alert(valcaja2);	
		 }else{
			  if(valcaja3==false){
				  alert("El campo solo acepta valores n\xdamericos");
					document.getElementById("txtDirnum3").value ="";
					document.getElementById("txtDirnum3").focus(); 
				  
			  }else{
				  if(valcaja4==false){
					  alert("El campo solo acepta valores n\xdamericos");
						document.getElementById("txtDirnum4").value ="";
						document.getElementById("txtDirnum4").focus();
						
				  }else{
					   alert("El campo solo acepta valores n\xdamericos");
						document.getElementById("txtDirnum5").value ="";
						document.getElementById("txtDirnum5").focus();
				  }
			  }
		  }
	   }
	
	}else{
		if(CajaDir1>150){
			document.getElementById("txtDirnum1").value="";
		    alert(" Dato Incorrecto");     
			 resultado=Dir1+" ";
		 //if(prueba==true){
			  document.getElementById('txtdiregeorre').value=resultado;  
		 // }else{
			  document.getElementById('txtdire').value=resultado; 
	      //}  
		    		    	    
		}else{
			
			if(CajaDir2>150){
				alert(" Dato Incorrecto");
				document.getElementById("txtDirnum2").value="";
		        resultado=Dir1+" "+CajaDir1+Dir2+" ";
		        resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3;
		        document.getElementById('txtdire').value=resultado;
		     if(prueba==false){
				  document.getElementById('txtdiregeorre').value=resultado;  
			 }else{
			      document.getElementById('txtdiregeorre').value=resulgeorrefe;
		      } 
		     
			}else{
					
				if(CajaDir3>150){
					alert(" dato incorrecto");
					document.getElementById("txtDirnum3").value="";
					resultado=Dir1+" "+CajaDir1+Dir2+" "+CajaDir2+" "+Dir3;
					resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3;
			        document.getElementById('txtdire').value=resultado;		
	
					if(prueba==false){
						  document.getElementById('txtdiregeorre').value=resultado;  
					 }else{
					      document.getElementById('txtdiregeorre').value=resulgeorrefe;
				      } 
				}else{
					
					if(CajaDir4>150){
						alert(" dato incorrecto");
						document.getElementById("txtDirnum4").value="";
						resultado=Dir1+" "+CajaDir1+Dir2+" "+CajaDir2+" "+Dir3+" "+CajaDir3+Dir4;
						resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3;
				        document.getElementById('txtdire').value=resultado;		
					    if((prueba==false)||(prueba1==false)){
					    	 document.getElementById('txtdiregeorre').value=resultado;
					        }else{
					         document.getElementById('txtdiregeorre').value=resulgeorrefe;
					    }
					}else{
					  
						if(CajaDir5>150){
							alert(" dato incorrecto");
							document.getElementById("txtDirnum5").value="";
							resultado=Dir1+" "+CajaDir1+Dir2+CajaDir2+Dir3+" "+CajaDir3+Dir4+CajaDir4;
							resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3;
					        document.getElementById('txtdire').value=resultado;		
						
							if((prueba==false)||(prueba1==false)){
							    	 document.getElementById('txtdiregeorre').value=resultado;
							        }else{
							         document.getElementById('txtdiregeorre').value=resulgeorrefe;
							}
						
					 }else{		
						 
						  if((Dir4==0)&&(CajaDir4=="")){
							    resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3+"-"+CajaDir5;
							    resulgeorrefe= Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3+"-"+CajaDir5;
				                document.getElementById('txtdire').value=resultado; 
						        if(prueba==false){
						          document.getElementById('txtdiregeorre').value=resultado;	
						        }else{
						           document.getElementById('txtdiregeorre').value=resulgeorrefe;	
						        }
							  
							 
						  }else{
							  
						   if(CajaDir4==""){
							
							   resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3+Dir4+" "+CajaDir5;
							   resulgeorrefe= Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3+"-"+CajaDir5;
							   document.getElementById('txtdire').value=resultado; 
							    if((prueba==false)&&(prueba1==false)){
							    	 document.getElementById('txtdiregeorre').value=resultado;
							        }else{
							         document.getElementById('txtdiregeorre').value=resulgeorrefe;
							    }
								
							}else{
								   if((Dir4==0)&&(CajaDir3=="")&&(CajaDir4=="")){
									
									   resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3;
									   resulgeorrefe=Dir1+" "+CajaDir1+" "+Dir3+" "+CajaDir3;
										document.getElementById('txtdire').value=resultado; 
										document.getElementById('txtdiregeorre').value=resulgeorrefe;
										if(prueba==false){
											document.getElementById('txtdiregeorre').value=resultado;
									       }else{
									         document.getElementById('txtdiregeorre').value=resulgeorrefe;
									    }
										
								   }else{
									   if(CajaDir5==""){
									
											resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3+Dir4+CajaDir4;
											resulgeorrefe=Dir1+" "+ CajaDir1+" "+ Dir3 +" "+ CajaDir3+"-"+CajaDir5;
											document.getElementById('txtdire').value=resultado; 	
										    if((prueba==false)&&(prueba1==false)){
											    	 document.getElementById('txtdiregeorre').value=resultado;
											        }else{
											         document.getElementById('txtdiregeorre').value=resulgeorrefe;
											 }
											
									   }else{
										   
											
											resultado= Dir1+" "+CajaDir1+Dir2+CajaDir2+" "+Dir3+" "+CajaDir3+Dir4+CajaDir4+"-"+CajaDir5;
											resulgeorrefe=Dir1+" "+ CajaDir1+" "+ Dir3 +" "+ CajaDir3+"-"+ CajaDir5;
											document.getElementById('txtdire').value=resultado; 	
											   if((prueba==false)&&(prueba1==false)){
											        document.getElementById('txtdiregeorre').value=resultado;
											        }else{
											        document.getElementById('txtdiregeorre').value=resulgeorrefe;
											    }
											
									   }
								   }
							   }		
						 }
				     } 
				   }
			    }
			
		   }
	    }
  }

}//FIN CONCATENAR


// by: Greys Hernandez
function Convertidor(codigo,latitud,longitud){
	
   ajax=getXMLObject();

  ajax.open("POST","ControlDirecciones",true); //getname will be the servlet name
  ajax.onreadystatechange=function() {
 if (ajax.readyState==4){
 }                 
}
ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
//alert("op=2&Cod="+codigo+"&lat="+latitud+"&long="+longitud);
ajax.send("op=2&Cod="+codigo+"&lat="+latitud+"&long="+longitud);
} // fin funcion Convertidor

//by Greys Hernandez

function ActualizaDireccionAdmision(e){

 longitud=e.document.getElementById("txtlongitud").value;
 latitud=e.document.getElementById("txtlatitud").value;
 direccion=e.document.getElementById("txtdire").value;
 optTipo=e.document.getElementById("cbafiliacion").selectedIndex;
 tipo=e.document.getElementById("cbafiliacion").options[optTipo].text;
 cedula=document.getElementById("txtnumdoc").value;
 barrio=e.document.getElementById("txtbarrio").value;
 //pais=e.document.getElementById("cbnacionalidad").value;
 optmuni=e.document.getElementById("cbmun").selectedIndex;
 muni=e.document.getElementById("cbmun").options[optmuni].text;
 municipio=e.document.getElementById("txtcodmun").value; // codigo
 optdep=e.document.getElementById("cbdep").selectedIndex;
 departamento=e.document.getElementById("cbdep").options[optdep].text;
  //municipio=e.document.getElementById("cbmun").value;
 
 r= confirm(direccion+" "+barrio+" "+muni+" "+departamento+"  Confirmar Si Esta Es La Direccion Que Desea Actualizar");
 if(r){
	 ajax=getXMLObject();
	 ajax.open("POST","ControlAdmision",true);
	 ajax.onreadystatechange=function(){
		 if (ajax.readyState==4){
			 alert("Actualizacion Exitosa!!");
		 }
	 }
	 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 ajax.send("valor=2&tipo="+tipo+"&cedu="+cedula+"&lat="+latitud+"&long="+longitud+"&dire="+direccion+"&bar="+barrio+"&muni="+municipio);
	 
 }else{
	 
	 alert("Ingrese Nuevamente Su Direccion");
	 direccion.focus();
 }

 
 //alert("valor=2&tipo="+tipo+"&cedu="+cedula+"&lat="+latitud+"&long="+longitud+"&dire="+direccion+"&bar="+barrio);
	
}

function IgualAcompa(){
	var e=document.getElementById("chkPorAtender1");
	if(e.checked){
		//esta seleccionado el vino solo
				
		document.getElementById("txtnombre1").value=document.getElementById("txtnombre").value;
		document.getElementById("txtapellido1").value=document.getElementById("txtapellido").value;
		document.getElementById("txtcedula1").value=document.getElementById("txtcedula").value;
		document.getElementById("txtelefono1").value=document.getElementById("txtelefono").value;
		document.getElementById("txtdireccion1").value=document.getElementById("txtdireccion").value;
		document.getElementById("cbparentesco1").value=document.getElementById("cbparentesco").value;//combo
		
			

	}else{
		// no esta seleccionado el vino solo
	
		document.getElementById("txtnombre1").value="";
		document.getElementById("txtapellido1").value="";
		document.getElementById("txtcedula1").value="";
		document.getElementById("txtelefono1").value="";
		document.getElementById("txtdireccion1").value="";
		document.getElementById("cbparentesco1").value="Seleccione";//combo
		


	}

}

function VinoSoloDatos(){
	var e=document.getElementById("chkPorAtender");
	if(e.checked){
		//esta seleccionado el vino solo
		document.getElementById("txtnombre").value="VINO";
		document.getElementById("txtapellido").value="SOLO";
		document.getElementById("txtcedula").value="123456";
		document.getElementById("txtelefono").value="0";
		document.getElementById("txtdireccion").value="0";
		document.getElementById("cbparentesco").value="CONOCIDO";//combo
		document.getElementById("txtnombre1").value="VINO";
		document.getElementById("txtapellido1").value="SOLO";
		document.getElementById("txtcedula1").value="123456";
		document.getElementById("txtelefono1").value="0";
		document.getElementById("txtdireccion1").value="0";
		document.getElementById("cbparentesco1").value="CONOCIDO";//combo
		
			

	}else{
		// no esta seleccionado el vino solo
		document.getElementById("txtnombre").value="";
		document.getElementById("txtapellido").value="";
		document.getElementById("txtcedula").value="";
		document.getElementById("txtelefono").value="";
		document.getElementById("txtdireccion").value="";
		document.getElementById("cbparentesco").value="Seleccione";//combo
		document.getElementById("txtnombre1").value="";
		document.getElementById("txtapellido1").value="";
		document.getElementById("txtcedula1").value="";
		document.getElementById("txtelefono1").value="";
		document.getElementById("txtdireccion1").value="";
		document.getElementById("cbparentesco1").value="Seleccione";//combo
		


	}

}


 /*function Paciente(){
	 
	 Cedula=document.getElementById("txtpaciente").value;
	  ajax=getXMLObject();

	  ajax.open("POST","ControlDirecciones",true); //getname will be the servlet name
	/* ajax.onreadystatechange=function() {
	if (ajax.readyState==4){
		//window.location.href="geo_vista_geografica.jsp";
		//alert("Busqueda exitosa");
	 }               
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	//alert("op=2&Cod="+codigo+"&lat="+latitud+"&long="+longitud);
	ajax.send("op=3&Ced="+Cedula);
}*/ // fin funcion Convertidor


 function demografico(e){	
	
	var CodColaFin=document.getElementById("txtCodColaFin").value;
	var nombrecompletopa,nombre,papellido,sapellido,cedula,fecha,afiliacion,nivel,genero,nacionalidad,direccion,telefijo,teleofi,telecel,ocupacion,empresa,zona,religion,estadocivil,raza,estrato,email,codimun,numcontrato,codcama,barrio;
	var tipo=document.getElementById("tipodoc").value;
	var ocupacion1=document.getElementById("txtocupacion").value;
	//tipo=e.tipodoc.options[ti].text;
	paciente=e.cbeps.value;

	var de=e.cbdep.selectedIndex;
	dep=e.cbdep.options[de].text;
	
	var mun=e.cbmun.selectedIndex;
	muni=e.cbmun.options[mun].text;
	
	var afi=e.select2.selectedIndex;
	afiliacion=e.select2.options[afi].text;
	
	var ni=e.cbnivel.selectedIndex;
	nivel=e.cbnivel.options[ni].text;
	
	var ge=e.select4.selectedIndex;
	genero=e.select4.options[ge].text;
	
	var na=e.cbnacionalidad.selectedIndex;
	nacionalidad=e.cbnacionalidad.options[na].text;
	var zo=e.cbzona.selectedIndex;
	zona=e.cbzona.options[zo].text;
	var es=e.select6.selectedIndex;
	estadocivil=e.select6.options[es].text;
	var est=e.select7.selectedIndex;
	estrato=e.select7.options[est].text;
	nombre=encodeURIComponent(e.txtnombre.value);
	papellido=encodeURIComponent(e.txtpapellido.value);
	sapellido=encodeURIComponent(e.txtsapellido.value);
    cedula=e.txtnumdocu.value;
    fecha=e.txtfechanaci.value;
    direccion=encodeURIComponent(e.txtdire.value);
    telefijo=e.txtelefijo.value;
    teleofi=e.txteleoficina.value;
    telecel=e.txtcelular.value;   
    ocupacion="0"  
    empresa="No Aplica";
    religion=e.txtreligion.value;
    raza=e.txtraza.value;
    email=e.txtemail.value;
    codimun=e.txtcodmun.value;
    numcontrato=e.txtcodentidad.value;
    barrio=e.txtbarrio.value;
   //PARA QUE DIGITEN POR LO MENOS UNA CALLE Y UNA CARRERA
    var Dir1=document.getElementById("ComboDir1").selectedIndex;
    var CajaDir1=document.getElementById("txtDirnum1").value;
    var Dir3=document.getElementById("ComboDir3").selectedIndex;
    var CajaDir2=document.getElementById("txtDirnum2").value;
    var CajaDir3=document.getElementById("txtDirnum3").value;
    var longitud=document.getElementById("txtlongitud").value;
    var latitud=document.getElementById("txtlatitud").value;
       
   
   
    // BY: Greys Hernandez
    if((nivel=="SELECCIONE...")){
    	nivel="";
    }    
    nomUsu=e.txtnomusu.value;
    id=e.txturgvital.value;
    
    if(telefijo==""){
    	telefijo="0";
    }
    if(telecel==""){
    	telecel="0";
    }
    if(teleofi==""){
    	teleofi="0";
    }    
    
   if(fecha.length!="10"){
	   alert("El Formato de la Fecha de Nacimiento es Erroneo.\nEl Formato Correcto Es DD/MM/AAAA ejemplo:14/09/1985")
   }
   if(barrio==""){
	   alert("Falta Ingresar Barrio!!!");
   }
   if(cedula==""){
	   alert("Falta Ingresar El Numero De Identificacion!!!");
   }
   if(papellido==""){
	   alert("Falta Ingresar El Primer Apellido!!!");
   }
   if(nombre==""){
	   alert("Falta Ingresar El Nombre!!!");
   }
   if(afiliacion=="SELECCIONE..."){
	   alert("Falta Seleccionar El Tipo De Afiliacion!!!");
   }
   if(genero=="SELECCIONE..."){
	   alert("Falta Seleccionar El Genero!!!");
   }
   if(nacionalidad=="SELECCIONE..."){
	   alert("Falta Seleccionar La Nacionalidad!!!");
   }  
   if(estrato=="SELECCIONE..."){
	   estrato="0";
   }
   if(codimun==""){
	   alert("Falta Seleccionar El Municipio!!!");
   }
   if(dep=="SELECCIONE..."){
	   alert("Falta Seleccionar El Departamento!!!");
   }
   if(muni=="SELECCIONE..."){
	   alert("Falta Seleccionar El Municipio!!!");
   }
   if(numcontrato==""){
	   alert("Falta Seleccionar La Eps!!!");
   }
   if(zona=="SELECCIONE..."){
	   alert("Falta Seleccionar Zona Rural");
   }
   if ((longitud=="")&&(latitud=="")){
         direccion=encodeURIComponent("Calle 1 #1-1");
         longitud="-74.822559";
         latitud="10.933734";
   }
  
  	   if((zona!="SELECCIONE...")&&(barrio!="")&&(fecha!="")&&(cedula!="")&&(papellido!="")&&(nombre!="")&&(afiliacion!="SELECCIONE...")&&(genero!="SELECCIONE...")&&(nacionalidad!="SELECCIONE...")&&(codimun!="")&&(dep!="SELECCIONE...")&&(muni!="SELECCIONE...")&&(numcontrato!="")&&(fecha.length=="10")){

			  ajax=getXMLObject();
				ajax.open("POST","ControlIngresoPac",true); //getname will be the servlet name
				ajax.onreadystatechange=function() {
					if (ajax.readyState==4) {					
						alert("Ingreso Exitoso.");
						 codcama=e.txtcodcama.value;
						  nombrecompletopa=nombre+" "+papellido+" "+sapellido; 	
						  //alert(direccion+barrio);
						  window.location.href="Adm_Ingreso.jsp?z=1&nom="+nombrecompletopa+"&cedula="+cedula+"&eps="+paciente+"&tipo="+tipo+"&codcama="+codcama+"&nomUsu="+nomUsu+"&id="+id+"&va=1&codigo=1&CodColaFin="+CodColaFin+"&dire="+direccion+"&long="+longitud+"&lati="+latitud+"&bar="+barrio+"&muni="+muni+"&dep="+dep+"&pais="+nacionalidad;
					}
				}    		
			 	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&dir="+direccion+"&telefi="+telefijo+"&teleofici="+teleofi+"&telecel="+telecel+"&ocu="+ocupacion+"&emp="+empresa+"&re="+religion+"&ra="+raza+"&ema="+email+"&codmun="+codimun+"&numco="+numcontrato+"&ti="+tipo+"&afi="+afiliacion+"&ni="+nivel+"&ge="+genero+"&naci="+nacionalidad+"&zona="+zona+"&esta="+estadocivil+"&estra="+estrato+"&ocupacion1="+ocupacion1+"&long="+longitud+"&lati="+latitud+"&bar="+barrio); //Posting txtname to Servlet*/
				//alert("nom="+nombre+"&pape="+papellido+"&sape="+sapellido+"&ced="+cedula+"&fe="+fecha+"&dir="+direccion+"&telefi="+telefijo+"&teleofici="+teleofi+"&telecel="+telecel+"&ocu="+ocupacion+"&emp="+empresa+"&re="+religion+"&ra="+raza+"&ema="+email+"&codmun="+codimun+"&numco="+numcontrato+"&ti="+tipo+"&afi="+afiliacion+"&ni="+nivel+"&ge="+genero+"&naci="+nacionalidad+"&zona="+zona+"&esta="+estadocivil+"&estra="+estrato+"&ocupacion1="+ocupacion1+"&long="+longitud+"&lati="+latitud+"&bar="+barrio);
	   }
}


function vac(){	
	var nombre,apellido,cedula,telefono,parentesco;
	parentesco=document.getElementById("cbparentesco").value;
	nombre=document.getElementById("txtnombre").value;
	apellido=document.getElementById("txtapellido").value;
    cedula=document.getElementById("txtcedula").value;
    telefono=document.getElementById("txtelefono").value;
   var direccion=document.getElementById("txtdireccion").value;
   
   var nombre1,apellido1,cedula1,telefono1,parentesco1;
	parentesco1=document.getElementById("cbparentesco1").value;
	nombre1=document.getElementById("txtnombre1").value;
	apellido1=document.getElementById("txtapellido1").value;
   cedula1=document.getElementById("txtcedula1").value;
   telefono1=document.getElementById("txtelefono1").value;
  var direccion1=document.getElementById("txtdireccion1").value;


    var n,t,p,c;
    n=document.getElementById('no');
    p=document.getElementById('pa');
    t=document.getElementById('te');
    v=document.getElementById('va');
    
    var i;
    t.style.display='none';
    n.style.display='none';
    p.style.display='none';
    v.style.display='none';
    for(i=0;i<direccion.length;i++){
      direccion=direccion.replace('#','Nº');
    }
 
    if(telefono==""){
    	t.style.display='';
    	v.style.display='';
        }
    if(nombre==""){
		n.style.display='';
		v.style.display='';
        }
    /*if(cedula==""){
    	c.style.display='';
    	v.style.display='';
        }*/
    if(parentesco=="Seleccione"){
    	p.style.display='';
    	v.style.display='';
    }
    if((apellido="")||(telefono=="")||(nombre=="")||(parentesco=="Seleccione")||(apellido1="")||(telefono1=="")||(nombre1=="")||(parentesco1=="Seleccione")){
    	//window.location.href="ControlAcompanante?nom="+nombre+"&ape="+apellido+"&ced="+cedula+"&tele="+telefono+"&dire="+direccion+"&pare="+parentesco+"",true;
		alert("Llene Los Campos Requeridos");
    }else{

    ajax=getXMLObject();
	ajax.open("POST","ControlAcompanante",true); //getname will be the servlet name
	ajax.onreadystatechange=function() {
		if (ajax.readyState==4) {
			var Cod=document.getElementById("txtCodAcompa").value=ajax.responseText;
			contacto(Cod);
			window.close();
		}
	}    		
 	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1&nom="+nombre+"&ape="+apellido+"&ced="+cedula+"&tele="+telefono+"&dire="+direccion+"&pare="+parentesco+"&nom1="+nombre1+"&ape1="+apellido1+"&ced1="+cedula1+"&tele1="+telefono1+"&dire1="+direccion1+"&pare1="+parentesco1); //Posting txtname to Servlet*/
	} 
}
//Admision
function Admisiones(e){	
	document.getElementById("btingresar").disabled=true;
	//document.getElementById("btnPasarListaMedico").disabled=true;
	/*******************MEDICO TRATANTE*******************/
	var MedicoTratante=document.getElementById("cmbMedicoTratante").value;
	var vuet = document.getElementById("cmbMedicoTratante").selectedIndex;
	var NomMedico = document.getElementById("cmbMedicoTratante").options[vuet].text;
	var CodUsuario=document.getElementById("txtCodUsuario").value;
	/***************FIN MEDICO TRATANTE*******************/
	var CodColaFin=document.getElementById("txtCodColaFin").value;
	var medioauto,cedula,autorizadopor,registradopor,remitidode,numeroauto,fecha,hora,numcama,estadoafi,semanas,contacto,observacion,anexos,doc1,doc2,nacido,codcam;
    codcam="";
	var es=e.cbestadoa.selectedIndex;
	estadoafi=e.cbestadoa.options[es].text;
	var ti=e.cbafiliacion.selectedIndex;
	var tipo=document.getElementById("cbafiliacion").value;
	var id=e.txturgencia.value;
	var ep=e.txtentidad.value;
	var nombre=e.txtnombre.value;
	var usuario=e.txtUsuario.value;
	medioauto=e.txtmedio.value;
	cedula=e.txtnumdoc.value;
	numeroauto=e.txtnumauto.value;
	autorizadopor=e.txtautorizado.value;
	registradopor=e.txtregistro.value;
    remitidode=e.txtremitido.value;
    fecha=e.txtfecha.value;
    hora=e.txthora.value;
    numcama=e.textfield13.value;
    semanas=e.txtsemana.value;
    //contacto=e.textfield14.value;
    contacto=e.txtCodAcomp.value;
    observacion=e.txtobservacion.value;
    doc1=e.checkbox2.value;
    doc2=e.checkbox.value;    
    if ((doc1 ==null)||(doc2 ==null)){
		anexos = "no";
	}
	else{
		anexos = "si";	
	}
    nacido=e.txtcodna.value;
    if(nacido==""){
    	nacido="0";
    }
    codcam=e.txtcodcam.value;    
    var me, au, nc, ea, sc, co;    
    me=document.getElementById('me');
    au=document.getElementById('au');
    nc=document.getElementById('nc');
    ea=document.getElementById('ea');
    sc=document.getElementById('sc');
    co=document.getElementById('co');
    me.style.display="none";
    au.style.display="none";
    nc.style.display="none";
    ea.style.display="none";
    sc.style.display="none";
    co.style.display="none";
    if(medioauto==""){
    	me.style.display="";
    }
    if(autorizadopor==""){
    	au.style.display="";
    }
    if(registradopor==""){
    	alert("Falta Ingresar Quien Lo Registro!!!");
    }
    if(fecha==""){
    	alert("Falta Ingresar La Fecha!!!");
    }
    if(hora==""){
    	alert("Falta Ingresar La Hora!!!");
    }
    if(numcama==""){
    	nc.style.display="";
    }
    if(semanas==""){
    	sc.style.display="";
    }
    if(contacto==""){
    	co.style.display="";
    }
    if(estadoafi=="SELECCIONE..."){
    	ea.style.display="";
    }
    if((medioauto!="")&&(autorizadopor!="")&&(registradopor!="")&&(fecha!="")&&(hora!="")&&(numcama!="")&&(semanas!="")&&(contacto!="")&&(estadoafi!="")){
    	ajax=getXMLObject();
    	ajax.open("POST","ControlAdmision",true);
    	ajax.onreadystatechange = function(){
    		if(ajax.readyState == 4){	    			
    			//Sucede cuando la pagina se carg�
    			if(ajax.status==200){
    				//Todo OK
    				// contenedor.innerHTML = ajax.responseText;
    				var validar=ajax.responseText;
    				if(validar!=""){
    					//Reporte(fecha,hora,numcama,usuario);   
    					Reporte(validar);  
    					window.location.href='Adm_Ingreso.jsp'
    				}else{
    					alert("Error "+validar);
    				}
    			}else if(ajax.status==404){
    				//La pagina no existe
    				contenedor.innerHTML = "La p�gina no existe";
    			}else{
    				//Mostramos el posible error
    				contenedor.innerHTML = "Error:".ajax.status; 
    			}
    		}
    	}
    	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    	ajax.send("valor=1&CodUsuario="+CodUsuario+"&NomMedico="+NomMedico+"&CodMedTra="+MedicoTratante+"&tipo="+tipo+"&cedu="+cedula+"&medio="+medioauto+"&auto="+autorizadopor+"&regis="+registradopor+"&remi="+remitidode+"&fe="+fecha+"&ho="+hora+"&num="+numcama+"&se="+semanas+"&con="+contacto+"&ob="+observacion+"&ane="+anexos+"&esta="+estadoafi+"&nume="+numeroauto+"&codna="+nacido+"&id="+id+"&cod="+codcam+"&usuario="+usuario+"&CodColaFin="+CodColaFin);
    }
}

function Reporte(validar) {	
	pagina="adm_ReporteAdmision.jsp?cod="+validar;		
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);		
}

/*function Reporte(fecha,hora,numcama,usuario){	
	window.location.href="ControlAdmision?fe="+fecha+"&ho="+hora+"&num="+numcama+"&usuario="+usuario;
}*/

function ActAdm(e){	
	var medio,numauto,autorizado,fecha,registro,hora,remitido,semana,afiliacion,ingreso;
	var afi=e.cbafi.selectedIndex;
	afiliacion=e.cbafi.options[afi].text;	
    medio=e.txtmedio.value;
    numauto=e.txtnumauto.value;
    autorizado=e.txtautorizado.value;
    fecha=e.txtfecha.value;
    registro=e.txtregistro.value;
    hora=e.txthora.value;
    remitido=e.txtremitido.value;
    semana=e.txtsemana.value;    
    ingreso=e.txtingreso.value;
    var nombreacon,telefonoacon,parentescoacon,apellidosacon,direccionacon,codigoacon,ceduacon;
    var pare=e.cbparenacon.selectedIndex;
    parentescoacon=e.cbparenacon.options[pare].text;    
    nombreacon=e.txtnomco.value;
    telefonoacon=e.txtteleacon.value;
    apellidosacon=e.txtapeacon.value;
    direccionacon=e.txtdireacon.value;
    codigoacon=e.txtcodcon.value;
    ceduacon=e.txtceduacon.value;
    
    var i;
    for(i=0;i<direccionacon.length;i++){
    	direccionacon=direccionacon.replace('#','N�');
    }
    
   if((medio=="")||(autorizado=="")||(fecha=="")||(hora=="")||(registro=="")||(semana=="")||(afiliacion=="")||(ingreso=="")||(nombreacon=="")||(ceduacon=="")||(telefonoacon=="")||(parentescoacon=="")){
	   alert("Falta LLenar Campos Requeridos...!");  
    }
    else{
    	window.location.href="ControlActAd?medio="+medio+"&numauto="+numauto+"&autorizado="+autorizado+"&fecha="+fecha+"&registro="+registro+"&hora="+hora+"&remitido="+remitido+"&semana="+semana+"&afiliacion="+afiliacion+"&ingreso="+ingreso+"&nombreacon="+nombreacon+"&telefonoacon="+telefonoacon+"&parentescoacon="+parentescoacon+"&apellidosacon="+apellidosacon+"&direccionacon="+direccionacon+"&codigoacon="+codigoacon+"&ceduacon="+ceduacon+"",true;
 	   alert("Actualizacion De Admision Exitosa...!");
    }
}


function caracter(e){
	var letras1="abcdefghijklmnopqrstuvwxyz�ABCDEFGHIJKLMNOPQRSTUVWXYZ����������� ";
	var caracter="";
	var returnval=e.value;
	var code=e.KeyCode;
	
	for (var i=0; i < returnval.length; i++) {
		caracter = returnval.substring(i, i + 1);
		code=caracter.KeyCode;
		
		if((code==209)||(code==241)){
			
		}else{
			if(letras1.indexOf(caracter) != -1) {
			
		    }else{
		      alert('El Campo Solo Acepta Valores Alfabeticos.');
		      e.value="";
	          e.focus();
	       e.select();
		    }
		}

}


}	
function validarte(h){ 
	var s=h.value;
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
		h.value="";
		h.focus();
	}
	return false;
}

function fecha_ingreso(){
	  var time1 = new Date()
	  var anos = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((dia < 10) ? "0" : "") + dia
	  temp1 += ((mes < 10) ? "/0" : "/") + mes
	  temp1 += ((anos < 10) ? "/0" : "/") + anos
	  document.forms['form1'].txtfecha.value = temp1
	 id = setTimeout("fecha_ingreso()",1000)
	  
	 
	  }
function hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? ":0" : ":") + minute
	  temp += ((second < 10) ? ":0" : ":") + second
	  document.forms['form1'].txthora.value = temp
	  id = setTimeout("hora()",1000)
	  }

function datos(){
	if(form1.txtnombre.value=null){
		alert("Ingrese Nombre...");
	}
}

