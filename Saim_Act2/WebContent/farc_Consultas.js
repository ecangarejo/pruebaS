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

//////////////////////////////////////////////////////////////////////////////////////////////////////////

function Existencias(){
	var user=document.getElementById("txtCodusuario").value;
	//alert(user);
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=Existencias&user="+user);
}


function ConOrdenProduccion(){
	//var user=document.getElementById("txtCodusuario").value;
	var idia=document.getElementById("Idia").value;
	var imes=document.getElementById("Imes").value;
	var iano=document.getElementById("Iano").value;
	var Fdia=document.getElementById("Fdia").value;
	var Fmes=document.getElementById("Fmes").value;
	var Fano=document.getElementById("Fano").value;
	//alert(user);
	var contenido=document.getElementById('vista');

	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=ConOrdenProduccion&idia="+idia+"&imes="+imes+"&iano="+iano+"&Fdia="+Fdia+"&Fmes="+Fmes+"&Fano="+Fano);
}

function AgreCteV(codmed){
	var cte=document.getElementById("CteV").value;
	var desc=document.getElementById("Desci").value;
	if(desc!="Seleccione"){
	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=GuarCteV&codmed="+codmed+"&cte="+cte+"&desc="+desc);
	}else{
		alert("No ha escogido una descripcion valida");
	}
}

function VerMedSinConstantes(){
	
	//alert("prueba");
	var contenido=document.getElementById('Creados');
	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VerMSC");
}

function BuscarEt(codop){
	pagina="farc_ReporteEtiquetas.jsp?codop="+codop;
	var opciones="toolbar=yes, menubar=yes , location=yes,scrollbars=yes, resizable=yes";
	window.open(pagina,"NUEVA",opciones);
}

function BuscarOp(codop){
	var opcion=confirm("Desea Abrir en Excel?");
	if(opcion){
		pagina="farc_ReporteOrdenProduccionExcel.jsp?codop="+codop;
		var opciones="toolbar=yes, menubar=yes , location=yes,scrollbars=yes, resizable=yes";
		window.open(pagina,"NUEVA",opciones);
	}else{
		pagina="farc_ReporteOrdenProduccion.jsp?codop="+codop;
		opciones="toolbar=yes, menubar=yes , location=yes,scrollbars=yes, resizable=yes";
		window.open(pagina,"NUEVA",opciones);
	}
}


function BuscarDosisAdecuar(codop){
	pagina="farc_ReporteNoDosisA.jsp?codop="+codop;
	var opciones="toolbar=yes, menubar=yes , location=yes,scrollbars=yes, resizable=yes";
	window.open(pagina,"NUEVA",opciones);
}

function BuscarDosisporVolumen(codop){
	pagina="farc_ReporteNoDosisPorVolum.jsp?codop="+codop;
	var opciones="toolbar=yes, menubar=yes , location=yes,scrollbars=yes, resizable=yes";
	window.open(pagina,"NUEVA6",opciones);
}

function vistarep(codop){
	//alert(codop);
	ajax=getXMLObject();
	//alert("codop "+codop);
	var contenido=document.getElementById('reporte');
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=repcod&codop="+codop);
}


function vrep(codop){
	ajax=getXMLObject();
	//alert("codop "+codop);
	var contenido=document.getElementById('reporte1');
	var tipo=document.getElementById('tipo').value;
	//alert("tipo"+tipo);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	if(tipo!='seleccione'){
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=repvol&codop="+codop+"&tipo="+tipo);
	}else{
		alert("No ha escogido un tipo de Presentacion a consultar");
	}
}


function MedOrden(){
	var user=document.getElementById("txtCodusuario").value;
	//alert(user);
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			contenido.innerHTML = ajax.responseText;
			Creados();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=MedOrden&user="+user);
}

function Creados(){
	var user=document.getElementById("txtCodusuario").value;
	//alert(user);
	var contenido=document.getElementById('Creados');

	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=Creados&user="+user);
}

function HabMed(){
	var user=document.getElementById("txtCodusuario").value;
	//alert(user);
	var contenido2=document.getElementById('ConstVolumen');
	var cm=document.getElementById('txtTipoMeH0').value;
	var concen=document.getElementById('txtCon').value;
	var diluy=document.getElementById('diluy').value;
	var codi=document.getElementById('txtCodI').value;
	var grupof=document.getElementById('grupof').value;
	var VR=document.getElementById('txtVR').value;
	var Nconst=document.getElementById('Nconst').value;
	var VAdm=document.getElementById('VAdm').value;
	var Est=document.getElementById('Estabilidad').value;
	//alert(Est);
	if((cm=="")||(concen=="")||(diluy=="Seleccione")||(grupof=="Seleccione")||(VR=="")||(VAdm=="Seleccione")||(Est=="")){
		alert("No ha llenado todos los datos correspondientes para habilitar el medicamento");
	}else{
		if((cm!="")&&(concen!="")&&(diluy!="Seleccione")&&(grupof!="Seleccione")&&(Nconst!=0)&&(VAdm!="Seleccione")&&(Est!="")){
		
	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido2.innerHTML = ajax.responseText;	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=HabMed&cm="+cm+"&concen="+concen+"&diluy="+diluy+"&grupof="+grupof+"&Nconst="+Nconst+"&user="+user+"&codi="+codi+"&VR="+VR+"&VAdm="+VAdm+"&Est="+Est);
		}else{
			if((Nconst==0)){
			alert("No ha colocado el numero de constantes de volumen a asignar minimo debe escoger 1");}
		}
}
}

function ModHabMed(CodmedH,CodCteV){
	var user=document.getElementById("txtCodusuario").value;
	//alert(user);
	var contenido=document.getElementById('contenido');
	var cm=document.getElementById('txtTipoMeH0').value;
	var concen=document.getElementById('txtCon').value;
	var diluy=document.getElementById('diluy').value;
	var codi=document.getElementById('txtCodI').value;
	var grupof=document.getElementById('grupof').value;
	var VR=document.getElementById('txtVR').value;
	
	var VAdm=document.getElementById('VAdm').value;
	var Est=document.getElementById('Estabilidad').value;
	var Val=document.getElementById('Val').value;
	var Desc=document.getElementById('Desc').value;
	// alert("valor=ModHabMed&cm="+cm+"&concen="+concen+"&diluy="+diluy+"&grupof="+grupof+"&user="+user+"&codi="+codi+"&VR="+VR+"&VAdm="+VAdm+"&Est="+Est+"&CodmedH="+CodmedH+"&CodCteV="+CodCteV);
	//alert(Est);
	if((cm=="")||(concen=="")||(diluy=="Seleccione")||(grupof=="Seleccione")||(VR=="")||(VAdm=="Seleccione")||(Est=="")||(Val=="")||(Desc=="")){
		alert("No ha llenado todos los datos correspondientes para modificar el medicamento");
	}else{
		if((cm!="")&&(concen!="")&&(diluy!="Seleccione")&&(grupof!="Seleccione")&&(VAdm!="Seleccione")&&(Est!="")&&(Val!="")&&(Desc!="")){
		
	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			window.location="farc_Med_Orden.jsp";
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=ModHabMed&cm="+cm+"&concen="+concen+"&diluy="+diluy+"&grupof="+grupof+"&user="+user+"&codi="+codi+"&VR="+VR+"&VAdm="+VAdm+"&Est="+Est+"&CodmedH="+CodmedH+"&CodCteV="+CodCteV+"&Val="+Val+"&Desc="+Desc);
		
}
}
}
var emailfilter=/^[0-9]*$/;  
function checknum(){
	var cc="txtCon";
	var e=document.getElementById(cc).value;
	var returnval=emailfilter.test(e)
	
		var x=e.substr(0,1);
		if(x==0){
		var y=e.substr(1);
		document.getElementById(cc).value = y;
		}
	
	if (returnval==false){
		alert("El campo solo acepta valores n\xdamericos");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}

function checknumvr(){
	var cc="txtVR";
	var e=document.getElementById(cc).value;
	var returnval=emailfilter.test(e)
	
		var x=e.substr(0,1);
		if(x==0){
		var y=e.substr(1);
		document.getElementById(cc).value = y;
		}
	
	if (returnval==false){
		alert("El campo solo acepta valores n\xdamericos");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}

function checknumest(){
	var cc="Estabilidad";
	var e=document.getElementById(cc).value;
	var returnval=emailfilter.test(e)
	
		var x=e.substr(0,1);
		if(x==0){
		var y=e.substr(1);
		document.getElementById(cc).value = y;
		}
	
	if (returnval==false){
		alert("El campo solo acepta valores n\xdamericos");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}



function ModMedH(CodmedH,CodCteV){
	//var contenido=document.getElementById('contenido');
	//alert("Modificacion de Grupo "+CodGrupo);
	//alert("entrando a ModMedH");
	var opcion=confirm("Desea Modificar este Medicamento?");
	if(opcion){
	window.location="farc_ModMedOrdenP.jsp?CodmedH="+CodmedH+"&CodCteV="+CodCteV;
	//TB_show('Modificacion de Grupo Farmacologico', "farc_ModGrupoFarmacologico.jsp?TB_iframe=true&height=400&width=800", null); 
	}else{
	}
	
}


function ModificaMedH(CodmedH,CodCteV){
	var contenido=document.getElementById('contenido');
	// alert("Modifica");
	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ModMedH&CodmedH="+CodmedH+"&CodCteV="+CodCteV);

}

function FinHab(entero,codigo){
	//alert("entrandop a FinHab"+entero);
	/*var cm=document.getElementById('txtTipoMeH0').value;
	var concen=document.getElementById('txtCon').value;
	var diluy=document.getElementById('diluy').value;
	var grupof=document.getElementById('grupof').value;*/
	var Nconst=document.getElementById('Nconst').value;
	//alert(Nconst);
	if(Nconst!=0){
		var i=0;
		for(i=0;i<entero;i++){
			var valor=document.getElementById('Val'+i).value;
			var desc=document.getElementById('Desc'+i).value;
			//alert("fin hab "+valor);
			GuardarVolumen(codigo,valor,desc,i,Nconst);	
			//alert(valor);
		}
		
		//alert("Habilitacion Exitosa");
				//window.location.reload();
		
	}
	
}

function GuardarVolumen(codigo,valor,desc,i, Nconst){
	//alert("GuardarVolumen"+valor);
	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			if(i==(Nconst-1)){
			alert(ajax.responseText);	
			window.location.reload();
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=GMedOrd&codigo="+codigo+"&valor1="+valor+"&desc="+desc);
}


function Vencimiento(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=Vencimiento");
}

function Seleccione(){
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('contenido');
	var radio = document.getElementsByName("radiobutton");
	for (var x = 0; x < radio.length; x ++) {
	 if (radio[x].checked) {
		var val=radio[x].id; 
	 }
	}
	//alert(val);
	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor="+val+"&user="+user);
}

function BuscarInfSisV(){
	var FechaI=document.getElementById("txtFechaInicial").value;
	var FechaF=document.getElementById("txtFechaFinal").value;
	var yi=FechaI.split("/").length;
 	var zi=FechaI.split("/");		     	
 	for(xi=0; xi<yi-1; xi=xi+1){ FechaInicial=zi[2]+"-"+zi[1]+"-"+zi[0];}
 	
 	var yf=FechaF.split("/").length;
 	var zf=FechaF.split("/");		     	
 	for(xf=0; xf<yf-1; xf=xf+1){ FechaFinal=zf[2]+"-"+zf[1]+"-"+zf[0];}
 	if((FechaI=="")||(FechaF=="")){
 		alert("Debe seleccionar, al menos, un rango de fechas");
 	}else{
 	 	document.getElementById('contenido').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
 		ajax=getXMLObject();
 		ajax.open("POST","ControlExistencias",true); //getname will be the servlet name
 		ajax.onreadystatechange  = function(){
 			if (ajax.readyState == 4) {
 				document.getElementById('contenido').innerHTML = ajax.responseText;
 			}
 		}
 		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 		ajax.send("valor=BInfSisV&FechaInicial="+FechaInicial+"&FechaFinal="+FechaFinal); //Posting txtname to Servle

 	}
}

function BuscarInfSis(){
	var FechaI=document.getElementById("txtFechaInicial").value;
	var FechaF=document.getElementById("txtFechaFinal").value;
	var yi=FechaI.split("/").length;
 	var zi=FechaI.split("/");		     	
 	for(xi=0; xi<yi-1; xi=xi+1){ FechaInicial=zi[2]+"-"+zi[1]+"-"+zi[0];}
 	
 	var yf=FechaF.split("/").length;
 	var zf=FechaF.split("/");		     	
 	for(xf=0; xf<yf-1; xf=xf+1){ FechaFinal=zf[2]+"-"+zf[1]+"-"+zf[0];}
 	if((FechaI=="")||(FechaF=="")){
 		alert("Debe seleccionar, al menos, un rango de fechas");
 	}else{
 	 	document.getElementById('contenido').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
 		ajax=getXMLObject();
 		ajax.open("POST","ControlExistencias",true); //getname will be the servlet name
 		ajax.onreadystatechange  = function(){
 			if (ajax.readyState == 4) {
 				document.getElementById('contenido').innerHTML = ajax.responseText;
 			}
 		}
 		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 		ajax.send("valor=BInfSis&FechaInicial="+FechaInicial+"&FechaFinal="+FechaFinal); //Posting txtname to Servle

 	}
}

function BuscarMedDx(){
	var FechaI=document.getElementById("txtFechaInicial").value;
	var FechaF=document.getElementById("txtFechaFinal").value;
	var yi=FechaI.split("/").length;
 	var zi=FechaI.split("/");		     	
 	for(xi=0; xi<yi-1; xi=xi+1){ FechaInicial=zi[2]+"-"+zi[1]+"-"+zi[0];}
 	
 	var yf=FechaF.split("/").length;
 	var zf=FechaF.split("/");		     	
 	for(xf=0; xf<yf-1; xf=xf+1){ FechaFinal=zf[2]+"-"+zf[1]+"-"+zf[0];}
 	if((FechaI=="")||(FechaF=="")){
 		alert("Debe seleccionar, al menos, un rango de fechas");
 	}else{
 	 	document.getElementById('contenido').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
 		ajax=getXMLObject();
 		ajax.open("POST","ControlExistencias",true); //getname will be the servlet name
 		ajax.onreadystatechange  = function(){
 			if (ajax.readyState == 4) {
 				document.getElementById('contenido').innerHTML = ajax.responseText;
 			}
 		}
 		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 		ajax.send("valor=BMedDx&FechaInicial="+FechaInicial+"&FechaFinal="+FechaFinal); //Posting txtname to Servle

 	}
}

function BuscarRepSal(){
	var FechaI=document.getElementById("txtFechaInicial").value;
	var FechaF=document.getElementById("txtFechaFinal").value;
	var yi=FechaI.split("/").length;
 	var zi=FechaI.split("/");		     	
 	for(xi=0; xi<yi-1; xi=xi+1){ FechaInicial=zi[2]+"-"+zi[1]+"-"+zi[0];}
 	
 	var yf=FechaF.split("/").length;
 	var zf=FechaF.split("/");		     	
 	for(xf=0; xf<yf-1; xf=xf+1){ FechaFinal=zf[2]+"-"+zf[1]+"-"+zf[0];}
 	if((FechaI=="")||(FechaF=="")){
 		alert("Debe seleccionar, al menos, un rango de fechas");
 	}else{
 	 	document.getElementById('contenido').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
 		ajax=getXMLObject();
 		ajax.open("POST","ControlExistencias",true); //getname will be the servlet name
 		ajax.onreadystatechange  = function(){
 			if (ajax.readyState == 4) {
 				document.getElementById('contenido').innerHTML = ajax.responseText;
 			}
 		}
 		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
 		ajax.send("valor=BRepSal&FechaInicial="+FechaInicial+"&FechaFinal="+FechaFinal); //Posting txtname to Servle

 	}
}

function Seleccionev(){
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('contenido');
	var radio = document.getElementsByName("radiobuttonv");
	for (var x = 0; x < radio.length; x ++) {
	 if (radio[x].checked) {
		var val=radio[x].id; 
	 }
	}
	//alert(val);
	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor="+val+"&user="+user);
}

function ConsultarEG(){
	
	var contenido=document.getElementById('contenido');
	var bodega=document.getElementById('cmbBodega').value;
	var gen=form1.cmbBodega.selectedIndex;
	var bodi=form1.cmbBodega.options[gen].text;
	
   	if(bodega=="Seleccione"){
	  alert("Seleccione la Bodega a Consultar.");
   	}
	
	if(bodega!="Seleccione"){
		var sql="";
		var p="TODAS LAS BODEGAS";
		if(bodega=="todas"){
			//sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre as empresa, e.nit, e.logo from medicamentos m, farc_inventario i, farc_iva iv, empresa e where i.cantidad>0 and m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK order by m.nombre";
			verReporteeg(sql,p);
		}else{
			//alert(bodega);
			//alert(bodi);
			//sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre as empresa, e.nit, e.logo from medicamentos m, farc_inventario i, farc_iva iv, empresa e, farc_bodegas b where i.cantidad>0 and m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and i.cod_bodegaFk='"+bodega+"' and i.cod_bodegaFk=b.codigo order by m.nombre";
			verReporteeg3(bodega,bodi);
		}
   }
}



function Consumo(){
	var user=document.getElementById("txtCodusuario").value;
	//alert(user);
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
    ajax.send("valor=Consumo&user="+user);
}


function verRepGenDisp (movi,bode,fi,ff) {
	pagina="farc_RepDisGen.jsp?movi="+movi+"&bode="+bode+"&fi="+fi+"&ff="+ff;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
    //	window.open(pagina,"NUEVA",opciones);
	//var opciones="fullscreen=0, toolbar=0, location=0, status=0, menubar=1, scrollbars=1, resizable=1";	
   	window.open(pagina,"Existencias",opciones); 


}


function ConsultarCons(){
	
	var contenido=document.getElementById('contenido');
	var bodega=document.getElementById('cmbBodega').value;
	var producto=document.getElementById('cmdproducto').value;
	var Idia=document.getElementById('Idia').value;
	var Imes=document.getElementById('Imes').value;
	var Iano=document.getElementById('Iano').value;
	var Fdia=document.getElementById('Fdia').value;
	var Fmes=document.getElementById('Fmes').value;
	var Fano=document.getElementById('Fano').value;
	var fi=Iano+"-"+Imes+"-"+Idia;
	var ff=Fano+"-"+Fmes+"-"+Fdia;
	//var agru=document.getElementById('cmbAgru').value;
	var gen=form1.cmbBodega.selectedIndex;
	var bodi=form1.cmbBodega.options[gen].text;
	
   	if(bodega=="Seleccione"){
	  alert("Seleccione la Bodega a Consultar.");
   	}
   	if(producto=="Seleccione"){
   		alert("Seleccione el producto a Consultar");
   	}
   	if((Imes=="40")||(Idia=="40")||(Iano=="40")||(Fdia=="40")||(Fmes=="40")||(Fano=="40")){
   	alert("El rango de fechas escogido no esta correcto");
   	}
	
	//alert("bodiiiii "+bodi);
   	//alert(bodega);
	//alert(agru);
		
	if((bodega!="Seleccione")&&(producto!="Seleccione")&&(Imes!="40")&&(Idia!="40")&&(Iano!="40")&&(Fdia!="40")&&(Fmes!="40")&&(Fano!="40")){
		var sqls="";
		var sql="";
		var p=" ";
		var opciones="toolbar=yes, location=no, directories=no, status=no, menubar=yes,";
	 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
		if((bodega=="todas")&&((producto=="4")||(producto=="1")||(producto=="2")||(producto=="3"))){
			pagina="farc_repConsGW.jsp?Iano="+Iano+"&Imes="+Imes+"&Idia="+Idia+"&Fano="+Fano+"&Fmes="+Fmes+"&Fdia="+Fdia+"&producto="+producto;			
		   	window.open(pagina,"ConsumoGeneral",opciones);
			//verReporteGeneral(fi,ff);
		}else{ 
			if ((bodega!="todas")&&((producto=="4")||(producto=="1")||(producto=="2")||(producto=="3"))){
				pagina="farc_repConsGBodW.jsp?Iano="+Iano+"&Imes="+Imes+"&Idia="+Idia+"&Fano="+Fano+"&Fmes="+Fmes+"&Fdia="+Fdia+"&bodega="+bodega+"&producto="+producto;			
			   	window.open(pagina,"ConsumoGeneralPorBodegas",opciones);
				//verReporteGeneralB(bodega,fi,ff);
			}else{
				("Los datos a consultar no estan correctamente Digitados");/*else{
				/*if((bodega=="todas")&&((producto=="1")||(producto=="2"))){
					//verReporteMedGen(producto,fi,ff);
					}else{
						if((bodega!="todas")&&(producto=="1")||(producto=="2")){
						verReporteMedGenB(bodega,producto,fi,ff);
						}else{*/
						/*	if((bodega=="todas")&&(producto=="3")){
							verReporteMedControl(fi,ff);
							}else{
								if((bodega!="todas")&&(producto=="3")){
									verReporteMedControlB(bodega,fi,ff);
									}
							}
					}
			}
		*/
  
  }
}
}
}

function ConsultarDisp(){
	
	var contenido=document.getElementById('contenido');
	var bodega=document.getElementById('cmbBodega').value;
	//var producto=document.getElementById('cmdproducto').value;
	var Idia=document.getElementById('Idia').value;
	var Imes=document.getElementById('Imes').value;
	var Iano=document.getElementById('Iano').value;
	var Fdia=document.getElementById('Fdia').value;
	var Fmes=document.getElementById('Fmes').value;
	var Fano=document.getElementById('Fano').value;
	var fi=Iano+"-"+Imes+"-"+Idia;
	var ff=Fano+"-"+Fmes+"-"+Fdia;
	//var agru=document.getElementById('cmbAgru').value;
	var gen=form1.cmbBodega.selectedIndex;
	var bodi=form1.cmbBodega.options[gen].text;
	//alert("Consultar Disp");
   	if(bodega=="Seleccione"){
	  alert("Seleccione la Bodega a Consultar.");
   	}
  
   	if((Imes=="40")||(Idia=="40")||(Iano=="40")||(Fdia=="40")||(Fmes=="40")||(Fano=="40")){
   	alert("El rango de fechas escogido no esta correcto");
   	}
	
	//alert("bodiiiii "+bodi);
   	//alert(bodega);
	//alert(agru);
		
	if((bodega!="Seleccione")&&(Imes!="40")&&(Idia!="40")&&(Iano!="40")&&(Fdia!="40")&&(Fmes!="40")&&(Fano!="40")){
		var sqls="";
		var sql="";
		var p=" ";
		var opciones="toolbar=yes, location=no, directories=no, status=no, menubar=yes,";
	 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
			pagina="farc_repDisPacW.jsp?Iano="+Iano+"&Imes="+Imes+"&Idia="+Idia+"&Fano="+Fano+"&Fmes="+Fmes+"&Fdia="+Fdia+"&bodega="+bodega;			
		   	window.open(pagina,"Dispensacion_Pacientes",opciones);
		
		
		
  }else{
  ("Los datos a consultar no estan correctamente Digitados");
  }
}


function ConsultarMov(){
	
	var contenido=document.getElementById('contenido');
	var bodega=document.getElementById('cmbBodega').value;
	var mov=document.getElementById('cmdmov').value;
	var Idia=document.getElementById('Idia').value;
	var Imes=document.getElementById('Imes').value;
	var Iano=document.getElementById('Iano').value;
	var Fdia=document.getElementById('Fdia').value;
	var Fmes=document.getElementById('Fmes').value;
	var Fano=document.getElementById('Fano').value;
	var fi=Iano+"-"+Imes+"-"+Idia;
	var ff=Fano+"-"+Fmes+"-"+Fdia;
	//var agru=document.getElementById('cmbAgru').value;
	var gen=form1.cmbBodega.selectedIndex;
	var bodi=form1.cmbBodega.options[gen].text;
	
   	if(bodega=="Seleccione"){
	  alert("Seleccione la Bodega a Consultar.");
   	}
   	if(mov=="Seleccione"){
   	 alert("Seleccione  el movimiento a Consultar.");
   	}
  
   	if((Imes=="40")||(Idia=="40")||(Iano=="40")||(Fdia=="40")||(Fmes=="40")||(Fano=="40")){
   	alert("El rango de fechas escogido no esta correcto");
   	}
	
	//alert("bodiiiii "+bodi);
   	//alert(bodega);
	//alert(agru);
		
	if((bodega!="Seleccione")&&(Imes!="40")&&(Idia!="40")&&(Iano!="40")&&(Fdia!="40")&&(Fmes!="40")&&(Fano!="40")){
		var sqls="";
		var sql="";
		var p=" ";
		var opciones="toolbar=yes, location=no, directories=no, status=no, menubar=yes,";
	 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
		pagina="farc_repTipoMov.jsp?Iano="+Iano+"&Imes="+Imes+"&Idia="+Idia+"&Fano="+Fano+"&Fmes="+Fmes+"&Fdia="+Fdia+"&bodega="+bodega+"&mov="+mov;			
		window.open(pagina,"ReportePorMovimiento",opciones);
			//verRepEnt(fi,ff);
  }else{
  ("Los datos a consultar no estan correctamente Digitados");
  }
}

function ConsultarOrd(){
	//alert();
	
	var tunidad=document.getElementById('tunidad').value;
	//alert(tunidad);
	var Idia=document.getElementById('Idia').value;
	var Imes=document.getElementById('Imes').value;
	var Iano=document.getElementById('Iano').value;
	var Fdia=document.getElementById('Fdia').value;
	var Fmes=document.getElementById('Fmes').value;
	var Fano=document.getElementById('Fano').value;
	var fi=Iano+"-"+Imes+"-"+Idia;
	var ff=Fano+"-"+Fmes+"-"+Fdia;
	//var agru=document.getElementById('cmbAgru').value;
	//var gen=form1.cmbBodega.selectedIndex;
	//var bodi=form1.cmbBodega.options[gen].text;
	
   	if(tunidad=="Seleccione"){
   	 alert("Seleccione  la unidad a Consultar.");
   	}
  
   	if((Imes=="40")||(Idia=="40")||(Iano=="40")||(Fdia=="40")||(Fmes=="40")||(Fano=="40")){
   	alert("El rango de fechas escogido no esta correcto");
   	}
	
	//alert("bodiiiii "+bodi);
   	//alert(bodega);
	//alert(agru);
		
	if((tunidad!="Seleccione")&&(Imes!="40")&&(Idia!="40")&&(Iano!="40")&&(Fdia!="40")&&(Fmes!="40")&&(Fano!="40")){
		var sqls="";
		var sql="";
		var p=" ";
		var opciones="toolbar=yes, location=no, directories=no, status=no, menubar=yes,";
	 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
		pagina="farc_repOrdenesFarm.jsp?Iano="+Iano+"&Imes="+Imes+"&Idia="+Idia+"&Fano="+Fano+"&Fmes="+Fmes+"&Fdia="+Fdia+"&tunidad="+tunidad;			
		window.open(pagina,"ReporteDeOrdenes",opciones);
			//verRepEnt(fi,ff);
  }else{
  ("Los datos a consultar no estan correctamente Digitados");
  }
}

function verReporteGeneral(tano, tmeses, tdia, ftanos, ftmeses, ftdia) {
	//alert(tano, tmeses, tdia, ftanos, ftmeses, ftdia);
	var fi=tano+"-"+tmeses+"-"+tdia;
	var ff=ftanos+"-"+ftmeses+"-"+ftdia;
	pagina="farc_ReporteConsumoGen.jsp?fi="+fi+"&ff="+ff;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}
function verReporteGeneralB( tano, tmeses, tdia, ftanos, ftmeses, ftdia,bodega) {
	var fi=tano+"-"+tmeses+"-"+tdia;
	var ff=ftanos+"-"+ftmeses+"-"+ftdia;
	pagina="farc_ReporteConsumoGenBod.jsp?bodega="+bodega+"&fi="+fi+"&ff="+ff;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function verReporteMedGen(producto,tano, tmeses, tdia, ftanos, ftmeses, ftdia) {
	var fi=tano+"-"+tmeses+"-"+tdia;
	var ff=ftanos+"-"+ftmeses+"-"+ftdia;
	pagina="farc_ReporteConsumo.jsp?producto="+producto+"&fi="+fi+"&ff="+ff;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function verReporteMedGenB(tano, tmeses, tdia, ftanos, ftmeses, ftdia,bodega,producto) {
	var fi=tano+"-"+tmeses+"-"+tdia;
	var ff=ftanos+"-"+ftmeses+"-"+ftdia;
	pagina="farc_ReporteConsumoB.jsp?bodega="+bodega+"&producto="+producto+"&fi="+fi+"&ff="+ff;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function verReporteMedControl(tano, tmeses, tdia, ftanos, ftmeses, ftdia,bodega) {
	var fi=tano+"-"+tmeses+"-"+tdia;
	var ff=ftanos+"-"+ftmeses+"-"+ftdia;
	pagina="farc_ReporteConsumoMedControl.jsp?&fi="+fi+"&ff="+ff;	
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function verRepDisG(tano, tmeses, tdia, ftanos, ftmeses, ftdia) {
	//alert(fi);
	//alert(ff);
	var fi=tano+"-"+tmeses+"-"+tdia;
	var ff=ftanos+"-"+ftmeses+"-"+ftdia;
	pagina="farc_ReporteDispPac.jsp?fi="+fi+"&ff="+ff;		
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function verRepDisGB(tano, tmeses, tdia, ftanos, ftmeses, ftdia,bodega) {
	//alert(fi);
//	alert(ff);
	var fi=tano+"-"+tmeses+"-"+tdia;
	var ff=ftanos+"-"+ftmeses+"-"+ftdia;
	pagina="farc_ReporteDispPacB.jsp?bodega="+bodega+"&fi="+fi+"&ff="+ff;		
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function verReporteMedControlB(tano, tmeses, tdia, ftanos, ftmeses, ftdia,bodega) {
	var fi=tano+"-"+tmeses+"-"+tdia;
	var ff=ftanos+"-"+ftmeses+"-"+ftdia;
	pagina="farc_ReporteConsumoMedControlB.jsp?bodega="+bodega+"&fi="+fi+"&ff="+ff;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function verRepEnt(fi, ff) {
	pagina="farc_RepMovEnt.jsp?fi="+fi+"&ff="+ff;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function verRepEntBod(bodega,fi, ff) {
	pagina="farc_RepMovEntB.jsp?bodega="+bodega+"&fi="+fi+"&ff="+ff;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function VistarepConsGen(tano,tmeses,tdia,ftano,ftmeses,ftdia){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("valor=RepConGen&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepConGen&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
}

function VistaOrdenesFarm(tano,tmeses,tdia,ftanos,ftmeses,ftdia,tunidad){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("valor=RepConGen&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepOrdenes&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&tunidad="+tunidad);
}
function VistarepDispPac(tano,tmeses,tdia,ftano,ftmeses,ftdia){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("valor=VistarepDispPac&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	var rep=1;
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepDispPac&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&rep="+rep);
}

function VistarepTipoMovT(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepTipoMovT&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega);
}

function VistarepTipoEnt_Tras(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega,mov){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepTipoEnt_Tras&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega+"&mov="+mov);
}

function VistarepSal(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega,mov){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepTipoMovSal&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega+"&mov="+mov);
}

function VistaEntProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepEntProd&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega1+"&cm="+cm);
}

function VistaSalProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepSalProd&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega1+"&cm="+cm);
}


function VistaTrasProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepTrasProd&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega1+"&cm="+cm);
}



function VistaDevProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepDevProd&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega1+"&cm="+cm);
}

function VistaDispPacProd(tano,tmeses,tdia,ftano,ftmeses,ftdia,cm){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	var rep=3;
	//alert("valor=RepDispPac&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&rep="+rep+"&med="+cm);

	//alert("valor=RepConGen&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepDispPac&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&rep="+rep+"&cm="+cm);
}

function VistaDispPacProdB(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega1,cm){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	var rep=4;
	//alert(rep);
	//alert("valor=RepConGen&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepDispPacB&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&rep="+rep+"&bodega="+bodega1+"&cm="+cm);
}

function VistarepDispPacBod(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	var rep=2;
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	//alert("valor=RepConGen&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepDispPacB&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega+"&rep="+rep);
}
function VistarepConsMedCont(tano,tmeses,tdia,ftano,ftmeses,ftdia){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("valor=RepConGen&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepConGenMedCont&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
}

function VistarepConsMedContBod(tano,tmeses,tdia,ftano,ftmeses,ftdia, bodega){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("valor=RepConGen&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepConGenMedContBod&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega);
}

function VistarepConsMed(tano,tmeses,tdia,ftano,ftmeses,ftdia,producto1){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("valor=VistarepConsMed&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&producto="+producto1);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepConGenProd1&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&producto="+producto1);
}

function VistarepConsGenBod(tano,tmeses,tdia,ftano,ftmeses,ftdia,bodega){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("valor=RepConGenBod&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepConGenBod&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega);
}

function VistarepConsMedBod(tano,tmeses,tdia,ftanos,ftmeses,ftdia,producto1, bodega){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("valor=RepConGenBod&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega);
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepConGenBodMed&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&bodega="+bodega+"&producto="+producto1);
}

function ConsultarProd(){
	var cm=document.getElementById('txtTipoMeH0').value;
	var Idia=document.getElementById('Idia').value;
	var Imes=document.getElementById('Imes').value;
	var Iano=document.getElementById('Iano').value;
	var Fdia=document.getElementById('Fdia').value;
	var Fmes=document.getElementById('Fmes').value;
	var Fano=document.getElementById('Fano').value;
	var treporte=document.getElementById('treporte').value;
	var bodega=document.getElementById('cmbBodega').value;
	//alert("farc_repConsProdW.jsp?cm="+cm+"&Iano="+Iano+"&Imes="+Imes+"&Idia="+Idia+"&Fano="+Fano+"&Fmes="+Fmes+"&Fdia="+Fdia+"&treporte="+treporte+"&bodega="+bodega);
	var fi=Iano+"-"+Imes+"-"+Idia;
	var ff=Fano+"-"+Fmes+"-"+Fdia;
	//alert("Listo modificado "+cm);
	//verReportebp(cm);
	if(bodega=="Seleccione"){
		  alert("Seleccione la Bodega a Consultar.");
	   	}
	   	if(treporte=="Seleccione"){
	   		alert("Seleccione el tipo de reporte a consultar");
	   	}
	   	if((Imes=="40")||(Idia=="40")||(Iano=="40")||(Fdia=="40")||(Fmes=="40")||(Fano=="40")){
	   	alert("El rango de fechas escogido no esta correcto");
	   	}
	   	
	   	if((bodega!="Seleccione")&&(treporte!="Seleccione")&&(Imes!="40")&&(Idia!="40")&&(Iano!="40")&&(Fdia!="40")&&(Fmes!="40")&&(Fano!="40")){
	   		var opciones="toolbar=yes, location=no, directories=no, status=no, menubar=yes,";
	   	 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
	   	 
	   	 	pagina="farc_repConsProdW.jsp?cm="+cm+"&Iano="+Iano+"&Imes="+Imes+"&Idia="+Idia+"&Fano="+Fano+"&Fmes="+Fmes+"&Fdia="+Fdia+"&treporte="+treporte+"&bodega="+bodega;			
	   	 	window.open(pagina,"ReporteProducto",opciones); 
	   	 	

	   	}
}

function VistarepConsProd(tano, tmeses, tdia, ftano, ftmeses, ftdia,cm){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepConsProd&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&cm="+cm);
}

function VistarepConsProdBod(tano, tmeses, tdia, ftano, ftmeses, ftdia,cm, bodega1){
	ajax=getXMLObject();
	var contenido=document.getElementById('reporte');
	//alert("vistarep=reportefac&tanos="+tanos+"&tmeses="+tmeses+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmeses+"&ftdia="+ftdia);
	document.getElementById('reporte').innerHTML='<p>Cargando Reporte... No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	ajax.open("POST","ControlExistencias",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=RepConsProdBod&tano="+tano+"&tmeses="+tmeses+"&tdia="+tdia+"&ftano="+ftano+"&ftmeses="+ftmeses+"&ftdia="+ftdia+"&cm="+cm+"&bodega="+bodega1);
}



function ConsultarD(){
	
	var contenido=document.getElementById('contenido');
	var bodega=document.getElementById('cmbBodega').value;
	var fi=document.getElementById('tfin').value;
	var ff=document.getElementById('tffin').value;
	alert("fi"+fi);
	alert("ff"+ff);
	a=compare_dates(fi,ff);
	var gen=form1.cmbBodega.selectedIndex;
	var bodi=form1.cmbBodega.options[gen].text;
	//alert("ConsultarD en Pdf")
   	if(bodega=="Seleccione"){
	  alert("Seleccione la Bodega a Consultar.");
   	}
	
	if((a==true)||(fi=="")||(ff=="")){ alert("No ha escogido un rango de Fechas adecuado");}
	alert("valor de a "+a);
	if((bodega!="Seleccione")&&(a!=true)&&(fi!="")&&(ff!="")){
		var sql="";
		var p="TODAS LAS BODEGAS";
		if(bodega=="todas"){
			//sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre as empresa, e.nit, e.logo from medicamentos m, farc_inventario i, farc_iva iv, empresa e where i.cantidad>0 and m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK order by m.nombre";
			verRepGenDisp(sql,p,fi,ff);
			alert(sql);
		}else{
			//alert(bodega);
			//alert(bodi);
			//sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre as empresa, e.nit, e.logo from medicamentos m, farc_inventario i, farc_iva iv, empresa e, farc_bodegas b where i.cantidad>0 and m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and i.cod_bodegaFk='"+bodega+"' and i.cod_bodegaFk=b.codigo order by m.nombre";
			verReporteeg3(bodega,bodi);
		}
   }
}





function verReporteeg (movi,bode) {
	pagina="farc_ReporteExistencias.jsp?movi="+movi+"&bode="+bode;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
    //	window.open(pagina,"NUEVA",opciones);
	//var opciones="fullscreen=0, toolbar=0, location=0, status=0, menubar=1, scrollbars=1, resizable=1";	
   	window.open(pagina,"Existencias",opciones); 


}


function verReporteeg3 (movi,bode) {
	pagina="farc_ReporteExistencias3.jsp?movi="+movi+"&bode="+bode;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function ConsultarE(){
	
	var contenido=document.getElementById('contenido');
	var bodega=document.getElementById('cmbBodega').value;
	var agru=document.getElementById('cmbAgru').value;
	var gen=form1.cmbBodega.selectedIndex;
	var bodi=form1.cmbBodega.options[gen].text;
	
   	if(bodega=="Seleccione"){
	  alert("Seleccione la Bodega a Consultar.");
   		}else{
   			if(agru=="Seleccione"){
   				alert("Seleccione el modo de Agrupaci\xf3n");			
   			}
   		}
	
	//alert("bodiiiii "+bodi);
   	//alert(bodega);
	//alert(agru);
		
	if((bodega!="Seleccione")&&(agru!="Seleccione")){
		var sqls="";
		var sql="";
		var p=" ";
		//alert(agru);
		
		if(agru=="1"){
			sql="select g.descripcion as control, e.nombre as empresa, e.nit, e.logo, CURRENT_TIMESTAMP AS horaactual from medicamentos m, farc_inventario i, empresa e, farc_grupo g where m.codigo=i.cod_medFK and g.codigo=m.cod_grupoFk group by g.descripcion";
		if(bodega=="todas"){
			sqls="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor,  IF(iv.valor = 16, ((i.cantidad*i.vunitario)*1.16), (i.cantidad*i.vunitario)) AS vtotal  from medicamentos m, farc_inventario i, farc_iva iv, farc_grupo g where i.cantidad>0 and i.cantidad>0 and m.codigo=i.cod_medFK and g.codigo=m.cod_grupoFk and iv.codigo=i.cod_ivaFK and g.descripcion=$P{pepe} order by m.nombre";
			//alert("todas: "+sqls);
			verReporteea(sql, sqls, bodi);
		}else{
			sqls="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor,  IF(iv.valor = 16, ((i.cantidad*i.vunitario)*1.16), (i.cantidad*i.vunitario)) AS vtotal from medicamentos m, farc_inventario i, farc_iva iv, farc_grupo g where i.cantidad>0 and m.codigo=i.cod_medFK and g.codigo=m.cod_grupoFk and iv.codigo=i.cod_ivaFK and g.descripcion=$P{pepe} and i.cod_bodegaFk='"+bodega+"' order by m.nombre";
			//alert(sqls);
			verReporteea(sql, sqls, bodi);
		}
		}//if agru==1
		
		if(agru=="2"){
				sql="select m.clasificacion as control, e.nombre as empresa, e.nit, e.logo, CURRENT_TIMESTAMP AS horaactual from medicamentos m, farc_inventario i, empresa e where m.codigo=i.cod_medFK group by m.clasificacion desc";
			if(bodega=="todas"){
				sqls="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor,  (i.cantidad*i.vunitario) AS vtotal from medicamentos m, farc_inventario i, farc_iva iv where  i.cantidad>0 and m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and m.clasificacion=$P{pepe} order by m.nombre";
				verReporteea(sql, sqls, bodi);
			}else{
				sqls="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, (i.cantidad*i.vunitario) AS vtotal from medicamentos m, farc_inventario i, farc_iva iv where  i.cantidad>0 and m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and m.clasificacion=$P{pepe} and i.cod_bodegaFk='"+bodega+"' order by m.nombre";
				verReporteea(sql, sqls, bodi);
			}
			}//if agru==2
		
		if(agru=="3"){
		sql="select m.control, e.nombre as empresa, e.nit, e.logo, CURRENT_TIMESTAMP AS horaactual from medicamentos m, farc_inventario i, empresa e where m.codigo=i.cod_medFK and m.control='si' group by m.control desc";
		if(bodega=="todas"){
			sqls="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, (i.cantidad*i.vunitario) AS vtotal from medicamentos m, farc_inventario i, farc_iva iv where  i.cantidad>0 and m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and m.control=$P{pepe} order by m.nombre";
			verReporteea(sql, sqls, bodi);
		}else{
			sqls="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, (i.cantidad*i.vunitario) AS vtotal from medicamentos m, farc_inventario i, farc_iva iv where  i.cantidad>0 and m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and m.control=$P{pepe} and i.cod_bodegaFk='"+bodega+"' order by m.nombre";
			verReporteea(sql, sqls, bodi);
		}
		}//if agru==3
		
		if(agru=="4"){
     		sql="select f.descripcion as control, e.nombre as empresa, e.nit, e.logo, CURRENT_TIMESTAMP AS horaactual  from medicamentos m, farc_inventario i, empresa e, farc_formafarmaceutica f where m.codigo=i.cod_medFK and f.codigo=m.cod_formaFk group by f.descripcion";
			if(bodega=="todas"){
				sqls="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, (i.cantidad*i.vunitario) AS vtotal from medicamentos m, farc_inventario i, farc_iva iv, farc_formafarmaceutica f where  i.cantidad>0 and m.codigo=i.cod_medFK and f.codigo=m.cod_formaFk and iv.codigo=i.cod_ivaFK and f.descripcion=$P{pepe} order by m.nombre";
				verReporteea(sql, sqls, bodi);
			}else{
				sqls="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, (i.cantidad*i.vunitario) AS vtotal from medicamentos m, farc_inventario i, farc_iva iv, farc_formafarmaceutica f where  i.cantidad>0 and m.codigo=i.cod_medFK and f.codigo=m.cod_formaFk and iv.codigo=i.cod_ivaFK and f.descripcion=$P{pepe} and i.cod_bodegaFk='"+bodega+"' order by m.nombre";
				verReporteea(sql, sqls, bodi);
			}
			}//if agru==3
		
		
  }//if si bodega y agrupacion estan en blanco
}


function verReporteea (movi, sqls, bodi) {
	//alert(sqls);
	pagina="farc_ReporteExistenciasa.jsp?movi="+movi+"&movis="+sqls+"&bodi="+bodi;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}


function MP(){
	var cm=document.getElementById('txtTipoMeH0').value;
	//alert("Listo modificado "+cm);
	//verReportebp(cm);
	pagina="farc_ReporteExistenciasP.jsp?movi="+cm;			
	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"Existencias",opciones); 

}



function Consultarxv(){
	
	var contenido=document.getElementById('contenido');
	var bodega=document.getElementById('cmbBodegav').value;
	var gen=form1.cmbBodegav.selectedIndex;
	var bodi=form1.cmbBodegav.options[gen].text;
	
   	if(bodega=="Seleccione"){
	  alert("Seleccione la Bodega a Consultar.");
   	}
	
	if(bodega!="Seleccione"){
		var sql="";
		if(bodega=="todas"){
			sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor,  IF(iv.valor = 16,  ((i.cantidad*i.vunitario)*1.16), (i.cantidad*i.vunitario)) AS vtotal, e.nombre as empresa, e.nit, e.logo, CURRENT_TIMESTAMP AS horaactual  from medicamentos m, farc_inventario i, farc_iva iv, empresa e where m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and i.cantidad>0 and i.vencimiento > curdate() order by i.vencimiento";
			verReportexv(sql,bodi);
		}else{
			sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor,  IF(iv.valor = 16, ((i.cantidad*i.vunitario)*1.16), (i.cantidad*i.vunitario)) AS vtotal, e.nombre as empresa, e.nit, e.logo, CURRENT_TIMESTAMP AS horaactual  from medicamentos m, farc_inventario i, farc_iva iv, empresa e, farc_bodegas b where m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and i.cantidad>0 and i.vencimiento > curdate() and i.cod_bodegaFk='"+bodega+"' and i.cod_bodegaFk=b.codigo order by i.vencimiento";
			verReportexv(sql,bodi);
		}
   }
}


function verReportexv (movi,bode) {
	pagina="farc_ReporteVencimiento.jsp?movi="+movi+"&bode="+bode;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function Consultarv(){
	
	var contenido=document.getElementById('contenido');
	var bodega=document.getElementById('cmbBodegav').value;
	var gen=form1.cmbBodegav.selectedIndex;
	var bodi=form1.cmbBodegav.options[gen].text;
	
   	if(bodega=="Seleccione"){
	  alert("Seleccione la Bodega a Consultar.");
   	}
	
	if(bodega!="Seleccione"){
		var sql="";
		if(bodega=="todas"){
			sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre as empresa, e.nit, e.logo, CURRENT_TIMESTAMP AS horaactual from medicamentos m, farc_inventario i, farc_iva iv, empresa e where m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and i.cantidad>0 and i.vencimiento <= curdate() order by i.vencimiento";
			verReportev(sql,bodi);
		}else{
			sql="select m.nombre, m.concentracion, i.lote, i.vencimiento, i.cantidad, i.vunitario, iv.valor, i.vtotal, e.nombre as empresa, e.nit, e.logo, CURRENT_TIMESTAMP AS horaactual from medicamentos m, farc_inventario i, farc_iva iv, empresa e, farc_bodegas b where m.codigo=i.cod_medFK and iv.codigo=i.cod_ivaFK and i.cantidad>0 and i.vencimiento <= curdate() and i.cod_bodegaFk='"+bodega+"' and i.cod_bodegaFk=b.codigo order by i.vencimiento";
			verReportev(sql,bodi);
		}
   }
}


function verReportev (movi,bode) {
	pagina="farc_ReporteVencimiento.jsp?movi="+movi+"&bode="+bode;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

































