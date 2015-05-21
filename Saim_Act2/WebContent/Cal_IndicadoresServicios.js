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

/////////////////////////////////////////////////////////////////////////////////
	
	
	function ValDatInd(){
		ajax=getXMLObject();
		var Serv=document.getElementById('Serv').value;
		var tanos=document.getElementById('Iano').value;
		var tdia=document.getElementById('Idia').value;
		var tmes=document.getElementById('Imes').value;
		var Ent=document.getElementById('Ent').value;
		var ftanos=document.getElementById('Fano').value;
		var ftdia=document.getElementById('Fdia').value;
		var ftmes=document.getElementById('Fmes').value;
		//alert("entrado");
		//alert("valor=RepIndicadores&tanos="+tanos+"&tmeses="+tmes+"&tdia="+tdia+"&ftanos="+ftanos+"&ftmeses="+ftmes+"&ftdia="+ftdia+"&ftent="+Ent+"&Serv="+Serv);
		var contenido=document.getElementById("vista");
		var opciones="toolbar=yes, menubar=yes , location=yes ";
		opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
		if((tanos==40)||(tdia==40)||(tmes==40)||(ftanos==40)||(ftdia==40)||(ftmes==40)||(Serv==40)||(Ent==40)){
			alert("No ha seleccionado todos los Datos ");
		}else{
			if((Serv==1)||(Serv=="todas")||(Serv=="adiag")){
				GenerarInd(Serv,tanos,tdia,tmes,Ent,ftanos,ftdia,ftmes,0);
			}else{
					if((Serv!=40)){
						var area=document.getElementById('area').value;
						//alert("area "+area);
						GenerarInd(Serv,tanos,tdia,tmes,Ent,ftanos,ftdia,ftmes,area);
						
				
				}
		
		
			}
		}
	}
	
	
	function GenerarInd(Serv,tanos,tdia,tmes,Ent,ftanos,ftdia,ftmes,area){
		
		ajax=getXMLObject();
		if((tanos==40)||(tdia==40)||(tmes==40)||(ftanos==40)||(ftdia==40)||(ftmes==40)){
			alert("No ha seleccionado todos los Datos ");
		}else{
				var opciones="toolbar=yes, menubar=yes , location=yes ";
				opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
				//alert("Cal_ReporteGenIndicadores.jsp?Serv="+Serv+"&tano="+tanos+"&tdia="+tdia+"&tmes="+tmes+"&ftent="+Ent+"&ftano="+ftanos+"&ftdia="+ftdia+"&ftmes="+ftmes+"&area="+area);
				var pagina="Cal_ReporteGenIndicadores.jsp?Serv="+Serv+"&tano="+tanos+"&tdia="+tdia+"&tmes="+tmes+"&ftent="+Ent+"&ftano="+ftanos+"&ftdia="+ftdia+"&ftmes="+ftmes+"&area="+area;
				ajax.open("POST","ControlIndicadores",true);
				ajax.onreadystatechange = function(){
					if(ajax.readyState == 4){
						//alert(ajax.reposenText);
						//contenido.innerHTML=ajax.responseText;
						window.open(pagina,"NUEVA",opciones);
						}
					}		
				ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=datos");
		}
	}
	
	
	function ReporteGeneralIndicadores(Serv,ftent,area,tdia,tmes,tano,ftdia,ftmes,ftano){

		ajax=getXMLObject();
		//alert("ReporteGeneralIndicadores");
		var contenido=document.getElementById("reporte");
		var opciones="toolbar=yes, menubar=yes , location=yes ";
		document.getElementById('reporte').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				
				contenido.innerHTML=ajax.responseText;
				//window.open(pagina,"NUEVA",opciones);
			}
		}		
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=RepInd&tanos="+tano+"&tmeses="+tmes+"&tdia="+tdia+"&ftanos="+ftano+"&ftmeses="+ftmes+"&ftdia="+ftdia+"&ftent="+ftent+"&Serv="+Serv+"&area="+area);	
	}
	
	/*function ReporteGeneralIndicadores(Serv,ftent,area,tdia,tmes,tano,ftdia,ftmes,ftano){
		ajax=getXMLObject();
		document.getElementById('reporte').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
		var contenido=document.getElementById("reporte");
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.reposenText);
				contenido.innerHTML=ajax.responseText;
				}
			}		
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=RepInd&tanos="+tano+"&tmeses="+tmes+"&tdia="+tdia+"&ftanos="+ftano+"&ftmeses="+ftmes+"&ftdia="+ftdia+"&ftent="+ftent+"&Serv="+Serv+"&area="+area);	
	}*/
	
	
	function tipoarea(){
		ajax=getXMLObject();
		var serv=document.getElementById('Serv').value;
		//	alert(serv);
		//alert("pruebaa de onchange"+serv);
		var contenido=document.getElementById('subarea');
		if((serv=='todas')||(serv==1)||(serv=='adiag')){
			contenido.innerHTML="";
			//alert("entrando a inner en blanco");
		}else{
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.resposeText);
				//alert("valor=VistaSubArea&serv="+serv);
				contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VistaSubArea&serv="+serv);
		}
		
	}
	
	function Tipodiag(tdia,tmes,tano,ftdia,ftmes,ftano,ftent){
		ajax=getXMLObject();
		//alert("Tipodiag");
		
		var tadiag=document.getElementById('tadiag').value;
		//alert("valor=Tipodiag&tadiag="+tadiag+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent);
		var cont=document.getElementById('discriminado');
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.reponseText);
				cont.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Tipodiag&tadiag="+tadiag+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&ftdia="+ftdia);
		
	}
	
	function TImg(tdia,tmes,tano,ftdia,ftmes,ftano,ftent){
		ajax=getXMLObject();
		var Img=document.getElementById('Img').value;
		//alert("valor=TImg&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Img="+Img);
		var conte=document.getElementById('repImg');
		document.getElementById('repImg').innerHTML='<p>Cargando Datos.....</p><img src="Imagenes/ani.gif">';
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				conte.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=TImg&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Img="+Img);
		
	}
	
	
	function TLab(tdia,tmes,tano,ftdia,ftmes,ftano,ftent){
		ajax=getXMLObject();
		var Lab=document.getElementById('Lab').value;
		//alert("valor=TImg&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Img="+Img);
		var conte=document.getElementById('repLab');
		document.getElementById('repLab').innerHTML='<p>Cargando Datos.....</p><img src="Imagenes/ani.gif">';
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				conte.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=TLab&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Lab="+Lab);
		
	}

	
	function TPat(tdia,tmes,tano,ftdia,ftmes,ftano,ftent){
		ajax=getXMLObject();
		var Pat=document.getElementById('Pat').value;
		//alert("valor=TImg&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Img="+Img);
		var conte=document.getElementById('repPat');
		document.getElementById('repPat').innerHTML='<p>Cargando Datos.....</p><img src="Imagenes/ani.gif">';
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				conte.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=TPat&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent+"&Pat="+Pat);
		
	}
	
	
	function BuscarPacVDiasHosp(tdia,tmes,tano,ftdia,ftmes,ftano,ftent,area){
		ajax=getXMLObject();
		alert("entrandoPac20Dias&area="+area+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent);
		var contenido=document.getElementById("vistarep");
		//alert("Reporte detalle de Genero Fechai Serv"+Serv+" ftent"+ftent+" area"+area+" tdia"+tdia+" tmes"+tmes+" tano"+tano+" ftdia"+ftdia+" ftmes"+ftmes+" ftano"+ftano);
		document.getElementById('vistarep').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.responseText);
				contenido.innerHTML=ajax.responseText;
			}
			}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=PacVeindiasH&area="+area+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent);
	}
	
	
	
	function BuscarEgVivosHosp(tdia,tmes,tano,ftdia,ftmes,ftano,ftent,area){
		ajax=getXMLObject();
		//alert("entrandoEgVivosHosp&area="+area+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent);
		var contenido=document.getElementById("vistarep");
		//alert("Reporte detalle de Genero Fechai Serv"+Serv+" ftent"+ftent+" area"+area+" tdia"+tdia+" tmes"+tmes+" tano"+tano+" ftdia"+ftdia+" ftmes"+ftmes+" ftano"+ftano);
		document.getElementById('vistarep').innerHTML='<p>Cargando Reporte.....</p><img src="Imagenes/ani.gif">';
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				//alert(ajax.responseText);
				contenido.innerHTML=ajax.responseText;
				}
			}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=EgVHosp&area="+area+"&tdia="+tdia+"&tmes="+tmes+"&tano="+tano+"&ftdia="+ftdia+"&ftmes="+ftmes+"&ftano="+ftano+"&ftent="+ftent);
	}
	
	function RadioCA(){		
		//alert();
		ajax=getXMLObject();
		var contenido=document.getElementById('Creaciones');
		var radioButtons = document.getElementsByName("radiobutton");
		for (var x = 0; x < radioButtons.length; x ++) {
			if (radioButtons[x].checked) {
				var val=radioButtons[x].id;			
				var p=" ";
				if(val=="rm"){
						p=p+"<table><tr><td colspan='2'>CONSULTA DE MORBILIDAD </td></tr> ";
				}
				if(val=="cp"){
					p=p+"<table><tr><td colspan='2'>CONSULTA DE PRODUCCION </td></tr> ";
				}
				if(val=="2193"){
					p=p+"<table><tr><td colspan='2'>DATOS PARA 2193</td></tr> ";
				}
				p=p+"<tr> " +
				"<td>Fecha Inicial <input name='txtFechaIN' type='text' id='txtFechaIN' onKeyUp='masca(this,&quot;/&quot;,patron,true)'/></td> " +
				"<td>Fecha Final <input name='txtFechaFI' type='text' id='txtFechaFI' onKeyUp='masca(this,&quot;/&quot;,patron,true)'/></td> " ;
				if(val=="rm"){
					p=p+"<td><select id='cmbTipo'><option value='Seleccione'>Seleccione</option><option value='Urg'>Urgencia</option><option value='Hosp'>Hospitalizacion</option><option value='CEX'>Consulta Externa</option><option value='CONS'>Consolidado</option></select><td><td><input type='button' value='     Buscar     ' onclick='GenerarConsultaRM()' ></td> ";
				}
				if(val=="cp"){
					p=p+"<td><select id='cmbTipo'><option value='Seleccione'>Seleccione</option><option value='Pro'>Programas</option><option value='Ser'>Servicios</option><option value='SerC'>Servicios Consolidados</option></select><td><td><input type='button' value='     Buscar     ' onclick='GenerarConsultaCP()' ></td> ";
				}
				if(val=="2193"){
					p=p+"<td><input type='button' value='     Buscar     ' onclick='GenerarConsulta2193()' ></td> ";
				}
				"</tr></table>";
				document.getElementById('Creaciones').innerHTML = (p);
			}	     
		}
	}
	
	function GenerarConsulta2193(){
		//alert();
		var FechaIN=document.getElementById("txtFechaIN").value;
		var FechaFI=document.getElementById("txtFechaFI").value;
		//var cmbTipo=document.getElementById("cmbTipo").value;
		//if(cmbTipo!="Seleccione"){
		document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..Este Reporte Puede Demorar un Poco</p><img src="Imagenes/ani.gif">';
		ajax=getXMLObject();
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {	
				document.getElementById("FormTo").innerHTML=ajax.responseText;				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2193&FechaIN="+FechaIN+"&FechaFI="+FechaFI);
		/*}else{
			alert("Seleccione Tipo de reporte");
		}*/
	}
	
	
	function GenerarConsultaRM(){
		//alert();
		var FechaIN=document.getElementById("txtFechaIN").value;
		var FechaFI=document.getElementById("txtFechaFI").value;
		var cmbTipo=document.getElementById("cmbTipo").value;
		if(cmbTipo!="Seleccione"){
		document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
		ajax=getXMLObject();
		ajax.open("POST","ControlIndicadores",true);
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {	
				document.getElementById("FormTo").innerHTML=ajax.responseText;				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=RM&FechaIN="+FechaIN+"&FechaFI="+FechaFI+"&cmbTipo="+cmbTipo);
		}else{
			alert("Seleccione Tipo de reporte");
		}
	}
	
	function GenerarConsultaCP(){
		//alert();
		var FechaIN=document.getElementById("txtFechaIN").value;
		var FechaFI=document.getElementById("txtFechaFI").value;
		var cmbTipo=document.getElementById("cmbTipo").value;
		if(cmbTipo!="Seleccione"){
			document.getElementById('FormTo').innerHTML='<p class="style6">Cargando...Resultados..</p><img src="Imagenes/ani.gif">';
			ajax=getXMLObject();
			ajax.open("POST","ControlIndicadores",true);
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {	
					document.getElementById("FormTo").innerHTML=ajax.responseText;				
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=CP&FechaIN="+FechaIN+"&FechaFI="+FechaFI+"&cmbTipo="+cmbTipo);
			//alert("valor=CP&FechaIN="+FechaIN+"&FechaFI="+FechaFI+"&cmbTipo="+cmbTipo);
		}else{
			alert("Seleccione Tipo de reporte");
		}
	}
	