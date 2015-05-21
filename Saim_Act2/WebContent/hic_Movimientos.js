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
function MostrarDQx(adm,enca){
	
	//alert(enca);
	
	var user=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;

	var contenido=document.getElementById('HistoriaPaciente');

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=1&user="+user+"&adm="+adm+"&enca="+enca);
}



function Dx(enca){
	
	var user=document.getElementById("txtCodusuario").value;
	var CodAdm=document.getElementById("CodAdm").value;
	var CodPac=document.getElementById("CodPac").value;

	var fechamo=document.getElementById("fechamo").value;
	var h0=document.getElementById("h0").value;
	var h1=document.getElementById("h1").value;
	var anes=document.getElementById("anes").value;
	//var ayuda=document.getElementById("ayuda").value;
	var instru=document.getElementById("instru").value;
	var tejido=document.getElementById("tejido").value;
	
	var contenido=document.getElementById('dx');

	if((fechamo=="")||(h0=="")||(h1=="")||(anes=="Seleccione")||(instru=="")){alert("Debe digitar todos los datos para continuar con los Diagnosticos");}
	else{
	
		ajax=getXMLObject();
		ajax.open("POST","ControlCargue",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//contenido.innerHTML = ajax.responseText	
				MostrarDQx(CodAdm,enca);
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1.1&user="+user+"&adm="+CodAdm+"&enca="+enca+"&codp="+CodPac+"&fechamo="+fechamo+"&h0="+h0+"&h1="+h1+"&anes="+anes+"&instru="+instru+"&tejido="+tejido);
	}
}


function ADx(tdx,adm,enca){
	
	var DQX=document.getElementById("DQX").value;
	
	var sw=0;
	
	if(tdx==1){
	var cdpre=document.getElementById("txtCodDiagnosticoRela1").value;
	var ndpre=document.getElementById("txtNomDiagnosRela1").value;
	if((cdpre=="")||(ndpre=="")){sw=1;}
	//alert(tdx+" -- "+adm+" -- "+enca+" -- "+cdpre+" -- "+ndpre);
	}
	if(tdx==2){
	var cdpost=document.getElementById("txtCodDiagnosticoRela2").value;
	var ndpost=document.getElementById("txtNomDiagnosRela2").value;
	if((cdpost=="")||(ndpost=="")){sw=1;}
	//alert(tdx+" -- "+adm+" -- "+enca+" -- "+cdpost+" -- "+ndpost);
	}
	
	if(sw==1){alert("Debe seleccionar algun Diagnostico");}else{
	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			//MostrarDQx(adm,enca);
			DG(adm,1);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=1.2&tdx="+tdx+"&DQX="+DQX+"&cdpre="+cdpre+"&ndpre="+ndpre+"&cdpost="+cdpost+"&ndpost="+ndpost);
	}
}


function ACx(tdx,adm,enca){
	
	var DQX=document.getElementById("DQX").value;
	var sw=0;
	
	if(tdx==1){
		var indice =  document.getElementById("ccx").selectedIndex;
		var ccx = document.getElementById('ccx').options[indice].value;
		var ncx = document.getElementById('ccx').options[indice].text;
		//alert(tdx+" -- "+adm+" -- "+enca+" -- "+ccx+" -- "+ncx);
		if(ccx=="Seleccione"){sw=1;}
	}
	if(tdx==2){
		var indice2 =  document.getElementById("can").selectedIndex;
		var can = document.getElementById('can').options[indice2].value;
		var nan = document.getElementById('can').options[indice2].text;
		//alert(tdx+" -- "+adm+" -- "+enca+" -- "+can+" -- "+nan);
		if(can=="Seleccione"){sw=1;}
	}
		
	if(sw==1){alert("Debe seleccionar algun Especialista");}else{
	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			//MostrarDQx(adm,enca);
			PQ(adm,enca,1);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=1.3&tdx="+tdx+"&DQX="+DQX+"&cdpre="+ccx+"&ndpre="+ncx+"&cdpost="+can+"&ndpost="+nan);
	}

}


function DP(adm){
	
	//var fm=document.getElementById("fechamo").value;
	//alert(fm);
	if (document.getElementById("fechamo"))
	   sw=1;
	else
	   sw=2;
	
	
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('dDP');

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			if(sw==2){contenido.innerHTML = ajax.responseText;}
			if(sw==1){contenido.innerHTML = "";}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=0.1&user="+user+"&adm="+adm);
}


function DG(adm,x){
	
	//var fm=document.getElementById("fechamo").value;
	//alert(fm);
	if (document.getElementById("SugeDiagnosticoRela1"))
	   sw=1;
	else
	   sw=2;
	
	if(x==1){sw=2;}
	
	
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('dDG');

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			if(sw==2){contenido.innerHTML = ajax.responseText;}
			if(sw==1){contenido.innerHTML = "";}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=0.2&user="+user+"&adm="+adm);
}


function PQ(adm,enca,x){
	
	var DQX=document.getElementById("DQX").value;
	//alert(fm);
	if (document.getElementById("ref0"))
	   sw=1;
	else
	   sw=2;
	
	if(x==1){sw=2;}	
	
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('dPQ');

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			if(sw==2){contenido.innerHTML = ajax.responseText;}
			if(sw==1){contenido.innerHTML = "";}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=0.3&user="+user+"&adm="+adm+"&enca="+enca+"&DQX="+DQX);
}


function HD(adm,enca){
	
	//var fm=document.getElementById("fechamo").value;
	//alert(fm);
	if (document.getElementById("hallazgos"))
	   sw=1;
	else
	   sw=2;
	
	
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('dHD');

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			if(sw==2){contenido.innerHTML = ajax.responseText;}
			if(sw==1){contenido.innerHTML = "";}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=0.4&user="+user+"&adm="+adm+"&enca="+enca);
}

function validamedicos(DQX){
	

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var valor = ajax.responseText
			//alert(valor);
			if(valor==0){
				document.getElementById("ccx").focus();
				alert("Debe asignar un Cirujano y un Anestesiologo para el procedimiento que desea seleccionar");
			}
			if(valor==1){
				document.getElementById("ccx").focus();
				alert("Debe asignar un Cirujano para el procedimiento que desea seleccionar");
			}
			if(valor==2){
				document.getElementById("can").focus();
				alert("Debe asignar un Anestesiologo para el procedimiento que desea seleccionar");
			}
	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=6&DQX="+DQX);
    
}

function ECX(codcx,nmed,tma,adm,enca){
	//alert(codcx);
	
	if(tma==1){ntma="Ciruano";}
	if(tma==2){ntma="Anestesiologo";}
	
	var x = confirm("Desea eliminar el "+ntma+" "+nmed+"?");
	if (x) {
	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var valor = ajax.responseText
			//alert(valor);
			PQ(adm,enca,1);
	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=7&ccx="+codcx);
	}
}

function EPROC(codcx,nmed,adm,enca){
	//alert(codcx);
		
	var x = confirm("Desea eliminar el procedimiento: "+nmed+"?");
	if (x) {
	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var valor = ajax.responseText
			//alert(valor);
			PQ(adm,enca,1);
	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=9&ccx="+codcx);
	}
}

function EDX(codcx,nmed,tma,adm,enca){
	//alert(codcx);
	
	if(tma==1){ntma="Dx Preoperatorio: ";}
	if(tma==2){ntma="Dx Postoperatorio: ";}
	
	var x = confirm("Desea eliminar el "+ntma+" "+nmed+"?");
	if (x) {
	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var valor = ajax.responseText
			//alert(valor);
			DG(adm,1);
	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=8&ccx="+codcx);
	}
}

function actoqx(){
	
	var taq=document.getElementById("taq").value;

	var contenido=document.getElementById('divaqx');

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=2&taqx="+taq);
}


function Cargarpop(adm,enca){
	//Verifico medico cirujano principal
	var DQX=document.getElementById("DQX").value;
	//alert("Cargando "+adm+" enca "+enca+" DQX"+DQX);

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			var cx = ajax.responseText;
			Cx2(adm,enca,cx);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=2.1&DQX="+DQX);
}

function Cx2(adm,enca,cx){
	//Verifico medicos cirujanos secundarios
	var DQX=document.getElementById("DQX").value;
	//alert("Cargando "+adm+" enca "+enca+" DQX"+DQX);

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var cx2="";
			cx2 = ajax.responseText;
			Ane(adm,enca,cx,cx2);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=2.2&DQX="+DQX);
}

function Ane(adm,enca,cx,cx2){
	//Verifico medicos cirujanos secundarios
	var DQX=document.getElementById("DQX").value;
	//alert("Cargando "+adm+" enca "+enca+" DQX"+DQX);

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var ane = ajax.responseText;
			Cargar(adm,enca,cx,cx2,ane);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=2.3&DQX="+DQX);
}



function Cargar(adm,enca,cx,cx2,ane){

   // alert("CESAR: "+adm+" - "+enca+" - "+cx+" - "+cx2+" - "+ane);
    if(cx2==""){cx2="0";}
    
	if((cx=="")||(ane=="")){alert("Debe Asignar el Cirujano Principal y el Anestesiologo");}else{
		
    
    var DQX=document.getElementById("DQX").value;
	var pop = document.getElementById("pop").value;
	//alert("popopopoo: "+pop);
	if(pop=="1"){
	var clases = document.getElementById("clases").value;
	var valorp = document.getElementById("valorp").value;
	var ra = document.getElementById("ra").value;
	}else{
	ra = "1";
	}
	
	//
	//alert(pop+" - "+clases+" - "+valorp+" - "+ra);
	var fechamo = document.getElementById("frdqx").value;
	var codr = document.getElementById("ref0").value;
	var nomp = document.getElementById("desct0").value;
	var codp = document.getElementById("descth0").value;
	var ccx = document.getElementById("ccx").value;
	var scc = document.getElementById("scc").value;
	var user=document.getElementById("txtCodusuario").value;
	var ent=document.getElementById("txtCodEntidad").value;
	
	nomp = encodeURIComponent(nomp);
	
	//alert(fechamo+" - "+codr+" - "+nomp+" - "+codp+" - "+ccx+" - "+scc+" - "+user+" - "+ent);
	
	
	var taq = document.getElementById("taq").value;
	var aq = document.getElementById("aq").value;
	
	if ((ra=="1")&&(taq == "Seleccione" )) {
		alert("El tipo de acto Quirurgico es requerido para este programa!!!")
	} else {
		if ((ra=="1")&&(aq == "Seleccione" )) {
			alert("El acto Quirurgico es requerido para este programa!!!")
		} else {
		
			//alert(ref0+"-"+desct0+"-"+descth0+"-"+taq+"-"+aq);
			ajax=getXMLObject();
			ajax.open("POST","ControlCargue",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					//contenido.innerHTML = ajax.responseText	
					// MostrarDQx(adm,enca);
					 PQ(adm,enca,1);
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			if(pop=="1"){
			 ajax.send("valor=3&enca="+enca+"&pop="+pop+"&codp="+codp+"&nomp="+nomp+"&codr="+codr+"&clases="+clases+"&fechamo="+fechamo+"&user="+user+"&aq="+aq+"&valorp="+valorp+"&scc="+scc+"&ccx="+ccx+"&ent="+ent+"&DQX="+DQX+"&cx="+cx+"&cx2="+cx2+"&ane="+ane);
			}else{
			 ajax.send("valor=4&enca="+enca+"&pop="+pop+"&codp="+codp+"&nomp="+nomp+"&codr="+codr+"&fechamo="+fechamo+"&user="+user+"&ccx="+ccx+"&aq="+aq+"&scc="+scc+"&ent="+ent+"&DQX="+DQX+"&cx="+cx+"&cx2="+cx2+"&ane="+ane);	
			}
		}
	}
	
  }//fin cx y anestesio
}



function GuardarDQx(CodAdm,enca){
	
	var hallazgos=document.getElementById("hallazgos").value;
	var descripcion=document.getElementById("descripcion").value;
	var DQX=document.getElementById("DQX").value;
	 
	if ((hallazgos=="")||(descripcion=="")) {
		alert("Debe digitar los hallazgos y el detalle de la descripcion!!!")
	} else {
	
	hallazgos = encodeURIComponent(hallazgos);
	descripcion = encodeURIComponent(descripcion);

	var contenido=document.getElementById('divaqx');

	ajax=getXMLObject();
	ajax.open("POST","ControlCargue",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var x= ajax.responseText;
			//alert(x);
			if(x==0){alert("Descripcion Quirurgica finalizada exitosamente!!!"); MostrarDQx(CodAdm,enca);}
			if(x==1){alert("No ha seleccionado ningun diagnostico preoperatorio");}
			if(x==2){alert("No ha seleccionado ningun diagnostico postoperatorio");}
			if(x==3){alert("No ha ingresado ningun procedimiento");}
			if(x==12){alert("No ha seleccionado ningun diagnostico preoperatorio ni postoperatorio");}
			if(x==13){alert("No ha seleccionado ningun diagnostico preoperatorio ni procedimientos");}
			if(x==123){alert("No ha seleccionado ningun diagnostico preoperatorio ni postoperatorio ni procedimientos");}
			if(x==23){alert("No ha seleccionado ningun diagnostico postoperatorio ni procedimientos");}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=5&hallazgos="+hallazgos+"&descripcion="+descripcion+"&DQX="+DQX);
  }
}




function MDQA(venc) {
	pagina = "hic_ReporteDescripcionQx.jsp?venc=" + venc;

	var opciones = "toolbar=no, location=no, directories=no, status=no, menubar=no,";
	opciones = opciones
			+ "scrollbars=si, resizable=yes, width=800, height=600, top=85,  left=140";
	window.open(pagina, "NUEVA", opciones);
}



/////////////////FUNCION DE HORA/////////
var patronh = new Array(2, 2, 2);
function hor(d, pat, nums) {
	val2="";
		val = d.value
		largo = val.length
	
		if(largo>6){
			t=val.substring(6);
		//	alert(val+"---"+t);
		}
		
		if(largo>2){
			val = val.replace(":", "");
			if (nums) {
				for (z = 0; z < val.length; z++) {
					if (isNaN(val.charAt(z))) {
						letra = new RegExp(val.charAt(z), "g")
						val = val.replace(letra, "")

					}
				}
			}
		}
		
	//	alert(largo+"////"+val);
		
	if (nums) {
		for (z = 0; z < val.length; z++) {
			if (isNaN(val.charAt(z))) {
				letra = new RegExp(val.charAt(z), "g")
				val = val.replace(letra, "")

			}
		}
	}
	
		
		
	if(largo<=2){
		val2=val;
		if(val2>12){val2="01";}
	}
	
	if(largo>2){
	h = val.substring(0, 2);
	if(h=="00"){h="01";}
	m = val.substring(2);
	if(m>59){m="00";}
	val2=h+":"+m;
	}
	
	if(largo==5){
		h = val.substring(0, 2);
		if(h=="00"){h="01";}
		m = val.substring(2);
		if(m>59){m="00";}
		val2=h+":"+m+" ";
		}
	//alert(largo+"---"+val+"----"+val2);
	if(largo>6){
		val2=val2+t;
	}
	
	if(largo>8){
		val2=val2.substring(0,8);
	}
	
	if(largo>5){
	if(val2.substring(5,6)!=" "){
		val2=val2.substring(0,5)+" ";
	}
	}
	
	
	var sw=0;
	var sw2=0;
	if((val2.substring(6,7)=="a")||(val2.substring(6,7)=="A")||(val2.substring(6,7)=="p")||(val2.substring(6,7)=="P")){
	 sw=1;
	}
	
	if(val2.substring(6,7)=="a"){
		val2=val2.substring(0,6)+"A";
	}
	
	if(val2.substring(6,7)=="p"){
		val2=val2.substring(0,6)+"P";
	}
	
	if((val2.substring(7,8)=="m")||(val2.substring(7,8)=="M")){
		 sw2=1;
	}
	
	if(val2.substring(7,8)=="m"){
		val2=val2.substring(0,7)+"M";
	}
	
	if(sw==0){
		 val2=val2.substring(0,6);
	}
	
	if(sw2==0){
		 val2=val2.substring(0,7);
	}
	/*if(val2.substring(7)){
		val2=val2.substring(0,8);
	}*/
	
 d.value = val2;
}


function vhor(d) {
	val = d.value;
	if(val.substring(0, 2)>12){alert("Hora errada"); d.value=''; }else{
		if(val.substring(3, 5)>59){alert("Hora errada"); d.value='';}else{
			if((val.substring(6, 8)=="AM" )||(val.substring(6, 8)=="PM")){val=val;}else{alert("Hora errada"); d.value='';}
		}
	}
}
/////////////////FUNCION DE FECHAS///////////////////
//funci de la fecha
var patron = new Array(2, 2, 4);
function masca(d, pat, nums, dias, mes, annio) {
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

		
		  /////////////////////////////////////////////////// //Validar fecha mayor que la fecha actual
		//alert(ano+"igual"+annio);
		 if(ano.length==4){ 
			 if (ano==annio) {	 
				 //alert("igual");
				 if(ini==mes){
					 if(dia>dias){ 
						 alert("La fecha del procedimiento no puede ser mayor a la fecha Actual"); 
						 val2=''; 
					 } 
				 }else{ 
					 if(ini>mes){
						 alert("La fecha del procedimiento no puede ser mayor a la fecha Actual"); 
						 val2=''; 
					 } 
				 } 
			 } if (ano>annio) { 
				 alert("La fecha del procedimiento no puede ser mayor a la fecha Actual"); 
				 val2=''; 
			 } 
			}
		 ///////////////////////////////////////////////////
		
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
    		document.getElementById("fechamo").focus();
    		
    }
	
}












/*
function CargarFactSh(enca,opcion) {
	//alert("cargar facturaSSSSSSSh");
	// alert("CargarFactSh");
	// alert("cargarfactturash ");
	var checkboxes = document.getElementById("form1").checkbox;

	var actqx = document.getElementById("actqx0").value;

	if (actqx == "Seleccione") {
		alert("El acto Quirurgico es requerido para este programa!!!")
	} else {
		var contenido = document.getElementById('creacion');
		// alert(checkboxes.length);
		var movimientos="";
		var sw=0;
		var sw2=0;
		var sw3=0;
		
		// alert(checkboxes.length);
		if(checkboxes.length!=undefined){
			for ( var x = 0; x < checkboxes.length; x++) {
	
				if (checkboxes[x].checked) {
					j = checkboxes[x].value
					// ///////////////////
					var desct0 = document.getElementById("desct" + j).value;
					var ref0 = document.getElementById("ref" + j).value;
					var descth0 = document.getElementById("descch" + j).value;
					var servich0 = document.getElementById("servich" + j).value;
					var serv0 = document.getElementById("serv" + j).value;
					var cant0 = document.getElementById("cant" + j).value;
					k = parseInt(j) + 1;
					
					var med0 = document.getElementById("medh"+j+k).value;
					
					if(med0==""){
						med0='null'
					}
					
					
					var valorpyp0 = document.getElementById("valorpyp" + j).value;
					var cse = document.getElementById("cse" + j).value;
					var rporc = document.getElementById("rporc" + j).value;
				
	
					var txtCodusuario = document.getElementById("txtCodusuario").value;
					
					var rmed = document.getElementById("rmed" + j).value;
	
					// alert("medo: "+med0);
					if (med0 == "Seleccione") {
						med0 = 0;
					}
	
					var cc = "fechamo";
					var e = document.getElementById(cc).value;
					
					if (descth0 == "" || serv0 == "" || desct0 == ""
							|| servich0 == "" || cant0 == "" || cant0 == "0"
							|| e == "") {
						// alert("Debe Digitar todos los campos a ingresar");
					} else {
						if ((rmed == "1") && (med0 == "null")) {
							sw2=1;
							
							sw=1;
						} else {
	
							if (valorpyp0 == "") {
								
								sw3=1;
								sw=1;
							} else {
	
								var pop = document.getElementById("pop").value;
								var refhi = document.getElementById("refhi").value;
								//alert(refhi);
								if(refhi==""){
									refhi="na";
								}
								var subc = document.getElementById("subc").value;
	
								
								movimientos=movimientos+"|"+pop+"|"+serv0+"|"+refhi+"|"+e+"|"+ref0+"|"+descth0+"|"+desct0+"|"+servich0+"|"+cant0+"|"+med0+"|"+valorpyp0+"|"+enca+"|"+txtCodusuario+"|"+cse+"|"+subc+"|"+actqx+"|"+rporc;
								
								// aqui va
								
								
							}// sino if de la tarifa o el valor
						}// sino else medico
	
					}
					
				
				}
			}
		}else{
			// ////
			
			if (document.getElementById("form1").checkbox.checked) {
				
				// ///////////////////
				var desct0 = document.getElementById("desct0").value;
				var ref0 = document.getElementById("ref0").value;
				var descth0 = document.getElementById("descch0").value;
				var servich0 = document.getElementById("servich0").value;
				var serv0 = document.getElementById("serv0").value;
				var cant0 = document.getElementById("cant0").value;
				var med0 = document.getElementById("med0").value;
				var valorpyp0 = document.getElementById("valorpyp0").value;
				var cse = document.getElementById("cse0").value;
				var rporc = document.getElementById("rporc0").value;
				

				var txtCodusuario = document.getElementById("txtCodusuario").value;
				
				var rmed = document.getElementById("rmed0").value;

				// alert("medo: "+med0);
				if (med0 == "Seleccione") {
					med0 = 0;
				}

				var cc = "fechamo";
				var e = document.getElementById(cc).value;
				
				// alert("viste: "+raqx+" y med: "+actqx);
				if (descth0 == "" || serv0 == "" || desct0 == ""
						|| servich0 == "" || cant0 == "" || cant0 == "0"
						|| e == "") {
					// alert("Debe Digitar todos los campos a ingresar");
				} else {
					if ((rmed == "1") && (med0 == "null")) {
						sw2=1;
						
						sw=1;
					} else {

						if (valorpyp0 == "") {
							
							sw3=1;
							sw=1;
						} else {

							var pop = document.getElementById("pop").value;
							var refhi = document.getElementById("refhi").value;
							if(refhi==""){
								refhi="na";
							}
							
							var subc = document.getElementById("subc").value;
							
							
							if(actqx==""){
								actqx=null;
							}
							if(rporc==""){
								rporc=null;
							}
							movimientos=movimientos+"|"+pop+"|"+serv0+"|"+refhi+"|"+e+"|"+ref0+"|"+descth0+"|"+desct0+"|"+servich0+"|"+cant0+"|"+med0+"|"+valorpyp0+"|"+enca+"|"+txtCodusuario+"|"+cse+"|"+subc+"|"+actqx+"|"+rporc;
							
										
							
							
						}// sino if de la tarifa o el valor
					}// sino else medico

				}
				
			
			}
			
			// //
		}
			
		// alert("sw="+sw);
// ///////////////////////////////////////////////
		if(sw2!=0){	
		alert("El Medico es requerido para este programa!!!")
		}
		if(sw3!=0){	
		alert("El programa no tiene tarifa ");
		}
		
	if(sw==0){	
		//alert("sw:=0");
		//alert("movi"+movimientos);
		
		ajax = getXMLObject();
		ajax.open("POST", "ControlMovimientos", true);
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var lotil = document
						.getElementById("lotil").value;
				var encas = document
						.getElementById("encas").value;

				if (x == checkboxes.length)
					Detalleh(encas, lotil,opcion)
					
					//alert("Cargue Exitoso3!!!");
			}
		}
		ajax.setRequestHeader("Content-type",
							"application/x-www-form-urlencoded; charset=UTF-8");
		
		
		movimientos=movimientos+"|";
		movimientos=encodeURIComponent(movimientos);
		//alert("encode"+movimientos);
		ajax.send("valor=6h&movh=" + movimientos);
		
	}
	// ///////////////////////////////////////////////////
		
		
		
	}// sino acto qx
}
*/
