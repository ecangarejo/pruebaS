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
function Radio() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlCrearTarifas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.style.width='100%';
					contenido.className='';
					contenido.innerHTML = ajax.responseText;
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=0"); //Posting txtname to Servlet
}


function RadiosEnvioFact() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlCrearTarifas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=envioFact"); //Posting txtname to Servlet
}


function Radios() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');

	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlCrearTarifas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.style.width='100%';
					contenido.innerHTML = ajax.responseText;
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}


function OpcionRadiosEnvioFact() {
	ajax=getXMLObject();
	var contenido=document.getElementById('Opcion');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlCrearTarifas",true); //getname will be the servlet name
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


function uno() {
	ajax=getXMLObject();
	var mtar=document.getElementById("mtar").value;
	var desch0=document.getElementById("desch0").value;
	var crit=document.getElementById("crit").value;
	var mbase0=document.getElementById("mbase0").value;
	
	var contenido=document.getElementById('Opcion');
			ajax.open("POST","ControlCrearTarifas",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText
					ConsultarP();
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=1&mtarp="+mtar+"&desch0p="+desch0+"&critp="+crit+"&mbase0p="+mbase0); //Posting txtname to Servlet
			
}


function ConsultarP(){
	var contenidos=document.getElementById('resul2');	
	var contenido=document.getElementById('resul');	
	
	var mtar=document.getElementById("mtar").value;
	var desch0=document.getElementById("desch0").value;
	var crit=document.getElementById("crit").value;
	var mbase0=document.getElementById("mbase0").value;

	if(mtar=="Seleccione" && desch0=="" && crit=="" ){}else{
		document.getElementById('resul').innerHTML='<p class="style6"><div align="center" >Cargando Resultados...</p>';//<img src="Imagenes/cargando.gif">';
		
    ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			var p="<table width='100%' border='1' class='style6' ><tr BGCOLOR='#D3D3D3' ><td width='20%'><i><div align='center'>Manual Tarifario</div></i></td><td width='44%'><i><div align='center'>Descripci\xf3n</div></i></td><td width='9%'><i><div align='center'>Fecha Inicial</div></i></td><td width='9%'><i><div align='center'>Fecha Final</div></i></td></i></td><td width='5%'><i><div align='center'>Valor</div></i></td><td width='6%'><i><div align='center'>Acci\xf3n</div></i></td></tr>";
					
			var myArray = new Array();
			myArray = ajax.responseText;
			var result = myArray.split("|");
			var elev = parseInt(parseInt(result.length)/8); 
			for(var i=0; i<elev; i++){
				p=p+"<tr><td><label><select  style='width:100%;' name='mbasec"+i+"' id='mbasec"+i+"' ><option value='"+result[1+(i*8)]+"'>"+result[2+(i*8)]+"</option></select>"; 
				p=p+"<td><input type=text id='desc"+i+"' size='70%' value='"+result[3+(i*8)]+"' readonly='readonly'>"; 
				p=p+"<input name='desch"+i+"' type='hidden' id='desch"+i+"'  value='"+result[4+(i*8)]+"' /></td>"; 
				p=p+"<td><input type=text id='fechai"+i+"' style='width:100%;' value='"+result[5+(i*8)]+"' onKeyup='masca(this,patron,true,01,01,2010)' onBlur='checkfecha("+i+");ModificarTExistente("+result[0+(i*8)]+","+i+",3)'></td>"; 
				p=p+"<td><input type=text id='fechaf"+i+"' style='width:100%;' value='"+result[6+(i*8)]+"' onKeyup='masca(this,patron,true,01,01,2010)' onBlur='checkfechaf("+i+");ModificarTExistente("+result[0+(i*8)]+","+i+",4)'></td>"; 
				p=p+"<td><input type=text id='valor"+i+"' style='text-align:right' size='10%' value='"+result[7+(i*8)]+"' onBlur='ModificarTExistente("+result[0+(i*8)]+","+i+",5)'></td>";
				p=p+"<td><div align='center'><a  href='#'onclick='EliminarT("+result[0]+")'>Eliminar</a></div></td>";
			}
			var p2="<table width='100%' class='style6' ><tr><td colspan='5'><div align='center' class='style11' id='cabecera2'>Resultados de la Busqueda</div></td></tr>";
			
			contenidos.innerHTML =(p2);
			contenido.innerHTML =(p);
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=2&mtarp="+mtar+"&desch0p="+desch0+"&critp="+crit+"&mbase0p="+mbase0);
  }
}


function CrearT(){
	
	var contenido=document.getElementById('creacion');	
	var mbasec=document.getElementById("mbasec").value;
	var descch0=document.getElementById("descch0").value;
	var fechai0=document.getElementById("fechaii0").value;
	var fechaf0=document.getElementById("fechaff0").value;
	var valor0=document.getElementById("valorr0").value;
	
	var fechafilter=/^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
	var cc="fechaii0";
	var e=document.getElementById(cc).value;
	var returnval=fechafilter.test(e)
	if (returnval==false){
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}else{
	
	var ccf="fechaff0";
	var ef=document.getElementById(ccf).value;
	var returnvalf=fechafilter.test(ef)
	if (returnvalf==false){
		alert("La fecha final es Invalida");
		document.getElementById(ccf).value = "";
		document.getElementById(ccf).focus();	
	}else{
		var sdi=e.substring(0,2);
		var smi=e.substring(3,5);
		var sai=e.substring(6);
		var sdf=ef.substring(0,2);
		var smf=ef.substring(3,5);
		var saf=ef.substring(6);
		var di=parseInt(sdi);
		var mi=parseInt(smi);
		var ai=parseInt(sai);
		var df=parseInt(sdf);
		var mf=parseInt(smf);
		var af=parseInt(saf);
		
		var val2=0;
			if (ai==af){
				if(mi==mf){
					if(di<df){
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
					val2=1;
					}
				}else{
					if(mi<mf){
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						val2=1;
					}
				}
			}
			if (ai>af) {
				alert("La fecha de Inicial no puede ser mayor que la fecha final");
				val2=1;
			}
	
	if(val2==0){
	
	if(mbasec=="Seleccione" || descch0=="" || fechai0=="" || fechaf0=="" || valor0=="" ){alert("Debe Digitar todos los campos a ingresar");}else{
    
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);	
			uno();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=3&mbasec="+mbasec+"&descch0="+descch0+"&fechai0="+fechai0+"&fechaf0="+fechaf0+"&valor0="+valor0);
  }
 }//fecha final mayor q inicial val2=0
 }//fecha inicial
}//fecha final
}


function EliminarT(ct){
	var contenido=document.getElementById('creacion');	
	
	var x=confirm("Desea Eliminar la Tarifa seleccionada?");
	if (x){
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);
			uno();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=5&ct="+ct);
 }
}


function GenerarT(){
	var contenido=document.getElementById('creacion');	
	
	var mbasec=document.getElementById("mtarn").value;
	var fechai0=document.getElementById("fechai0").value;
	var fechaf0=document.getElementById("fechaf0").value;
	var valor0=document.getElementById("valor0").value;
	
	var mtarp=document.getElementById("mtara").value;
	var descch0=document.getElementById("factor").value;
	
	var fechafilter=/^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
	var cc="fechai0";
	var e=document.getElementById(cc).value;
	var returnval=fechafilter.test(e)
	if (returnval==false){
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}else{
	
	var ccf="fechaf0";
	var ef=document.getElementById(ccf).value;
	var returnvalf=fechafilter.test(ef)
	if (returnvalf==false){
		alert("La fecha final es Invalida");
		document.getElementById(ccf).value = "";
		document.getElementById(ccf).focus();	
	}else{
		var sdi=e.substring(0,2);
		var smi=e.substring(3,5);
		var sai=e.substring(6);
		var sdf=ef.substring(0,2);
		var smf=ef.substring(3,5);
		var saf=ef.substring(6);
		var di=parseInt(sdi);
		var mi=parseInt(smi);
		var ai=parseInt(sai);
		var df=parseInt(sdf);
		var mf=parseInt(smf);
		var af=parseInt(saf);
		
		var val2=0;
			if (ai==af){
				if(mi==mf){
					if(di<df){
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						document.getElementById(cc).focus();	
						val2=1;
					}
				}else{
					if(mi<mf){
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						document.getElementById(cc).focus();	
						val2=1;
					}
				}
			}
			if (ai>af) {
				alert("La fecha de Inicial no puede ser mayor que la fecha final");
				document.getElementById(cc).focus();	
				val2=1;
			}
	
	if(val2==0){
	

	if(mbasec=="Seleccione" || mtarp=="Seleccione" || fechai0=="" || fechaf0=="" || valor0=="" || descch0=="Seleccione"){alert("Debe Digitar todos los campos para generar la nueva tarifa");}else{
    	this.form1.elements['btnConsultarPq'].disabled=true;
		contenido.innerHTML='<p class="style6"><div align="center" >Cargando Resultados...</p>';
				
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);	
			Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=7&mbasec="+mbasec+"&descch0="+descch0+"&fechai0="+fechai0+"&fechaf0="+fechaf0+"&valor0="+valor0+"&mtarp="+mtarp);
	
  }
	 }//fecha final mayor q inicial val2=0
	 }//fecha inicial
	}//fecha final
}


///////////////////Decremento de Tarifas///////////////////
function GenerarTd(){
	var contenido=document.getElementById('creacion');	
	
	var mbasec=document.getElementById("mtarn").value;
	var fechai0=document.getElementById("fechai0").value;
	var fechaf0=document.getElementById("fechaf0").value;
	var valor0=document.getElementById("valor0").value;
	
	var mtarp=document.getElementById("mtara").value;
	var descch0=document.getElementById("factor").value;

	
	var fechafilter=/^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
	var cc="fechai0";
	var e=document.getElementById(cc).value;
	var returnval=fechafilter.test(e)
	if (returnval==false){
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}else{
	
	var ccf="fechaf0";
	var ef=document.getElementById(ccf).value;
	var returnvalf=fechafilter.test(ef)
	if (returnvalf==false){
		alert("La fecha final es Invalida");
		document.getElementById(ccf).value = "";
		document.getElementById(ccf).focus();	
	}else{
		var sdi=e.substring(0,2);
		var smi=e.substring(3,5);
		var sai=e.substring(6);
		var sdf=ef.substring(0,2);
		var smf=ef.substring(3,5);
		var saf=ef.substring(6);
		var di=parseInt(sdi);
		var mi=parseInt(smi);
		var ai=parseInt(sai);
		var df=parseInt(sdf);
		var mf=parseInt(smf);
		var af=parseInt(saf);
		
		var val2=0;
			if (ai==af){
				if(mi==mf){
					if(di<df){
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						document.getElementById(cc).focus();	
						val2=1;
					}
				}else{
					if(mi<mf){
						alert("La fecha de Inicial no puede ser mayor que la fecha final");
						document.getElementById(cc).focus();	
						val2=1;
					}
				}
			}
			if (ai>af) {
				alert("La fecha de Inicial no puede ser mayor que la fecha final");
				document.getElementById(cc).focus();	
				val2=1;
			}
	
	if(val2==0){

	if(mbasec=="Seleccione" || mtarp=="Seleccione" || fechai0=="" || fechaf0=="" || valor0=="" || descch0=="Seleccione"){alert("Debe Digitar todos los campos para generar la nueva tarifa");}else{
    
		this.form1.elements['btnConsultarPq'].disabled=true;
		contenido.innerHTML='<p class="style6"><div align="center" >Cargando Resultados...</p>';
			
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert(ajax.responseText);	
			Radio();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=7d&mbasec="+mbasec+"&descch0="+descch0+"&fechai0="+fechai0+"&fechaf0="+fechaf0+"&valor0="+valor0+"&mtarp="+mtarp);
	
  }
	 }//fecha final mayor q inicial val2=0
	 }//fecha inicial
	}//fecha final
}


///////////////////

function ModificarTExistente(ct,v,y){
	var contenido=document.getElementById('resul');	
	if(y==1){
		var mbasec=document.getElementById("mbasec"+v).value;
		var descch0=document.getElementById("desch"+v).value;
	}
	if(y==2){
		var comp=document.getElementById("comp"+v).value;
	}
	if(y==3){
		var fechai=document.getElementById("fechai"+v).value;
	}
	if(y==4){
		var fechaf=document.getElementById("fechaf"+v).value;
	}
	if(y==5){
		var valor0=document.getElementById("valor"+v).value;
	}
	 
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			if(ajax.responseText!=""){
			alert(ajax.responseText);
			}
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	
	
	if(y==1){
		ajax.send("valor=4&mbasec="+mbasec+"&y="+y+"&v="+v+"&ct="+ct+"&descch0="+descch0);
	}
	if(y==2){
		ajax.send("valor=6&valor0="+valor0+"&y="+y+"&v="+v+"&ct="+ct);
	}
	if(y==3){
		ajax.send("valor=4&fechai0="+fechai+"&y="+y+"&v="+v+"&ct="+ct);
	}
	if(y==4){
		ajax.send("valor=4&fechaf0="+fechaf+"&y="+y+"&v="+v+"&ct="+ct);
	}
	if(y==5){
		ajax.send("valor=4&valor0="+valor0+"&y="+y+"&v="+v+"&ct="+ct);
	}
}

//////////////^[0-9]*$/;  

var emailfilterno=/^[0-9]{1,3}(\.[0-9]{0,2})?$/;
function checknumt(c){
	var cc="valor"+c;
	var e=document.getElementById(cc).value;
	var returnval=emailfilterno.test(e)
	
		var x=e.substr(0,1);
		if(x==0){
		var y=e.substr(1);
		document.getElementById(cc).value = y;
		}
	
	if (returnval==false){
		if(e!=""){
		alert("Formato incorrectoj!!!");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
		}
	}
return returnval
}


var emailfilternot=/^[0-9]*$/;  
function checknum(c){
	var cc="valorr"+c;
	var e=document.getElementById(cc).value;
	var returnval=emailfilternot.test(e)
	
		var x=e.substr(0,1);
		if(x==0){
		var y=e.substr(1);
		document.getElementById(cc).value = y;
		}
	
	if (returnval==false){
		alert("Formato incorrecto!!!");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}



//funci de la fecha 
var patron = new Array(2,2,4);
function masca(d,pat,nums,dias,mes,annio){
var sep="/";
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if((ini>31)||(ini=="00")){
		val=d.value="01";
	}
	val = val.split(sep);

	val2 = ''
	for(r=0;r<val.length;r++){
		val2 += val[r]
	}
	ini=val2.substring(2,4);
	if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
		x=val2.substring(0,2);
		if(x=="31"){
			val2="30";
			val2=val2+ini;
		}
	}
	if((ini>12)||(ini=="00")){
		mescj=val2.substring(0,2);
		val2=mescj+"01";
	}
	
	ano=val2.substring(4,8);
	dia=val2.substring(0,2);
	
	
	
	if(ini=="02"){
		if((dia=="30")||(dia=="31")||(dia=="29")){
		if(ano.length==4){
			if ( ( ano % 100 != 0) && ((ano % 4 == 0) || (ano % 400 == 0))) {
				val2="29";
				val2=val2+ini;
			}else{
				val2="28";
				val2=val2+ini;
			}
			val2=val2+ano;
		}
	  }
	}
/*	///////////////////////////////////////////////////
	//Validar fecha mayor que la fecha actual
		
	if(ano.length==4){
		if (ano==annio) {
			if(ini==mes){
				if((dia==dias)||(dia<dias)){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
				val2='';
				}
			}else{
				if(ini<mes){
					alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
					val2='';
				}
			}
		}
		if (ano<annio) {
			alert("La fecha de Vencimiento no puede ser menor o igual a la fecha Actual");
			val2='';
		}
	}
	
	///////////////////////////////////////////////////
*/	
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

//////////////////////////
var fechafilter=/^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function checkfecha(c){
	var cc="fechai"+c;
	var e=document.getElementById(cc).value;
	var returnval=fechafilter.test(e)
	if (returnval==false){
		alert("La fecha inicial es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}

var fechafilters=/^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function checkfechaf(c){
	var cc="fechaf"+c;
	var e=document.getElementById(cc).value;
	var returnval=fechafilters.test(e)
	if (returnval==false){
		alert("La fecha final es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}
//////////////////////////////////////////////////////////////////////////////////////////////////////////

function CrearManualesTarifarios(){
	var NombreManual=document.getElementById("txtNomManuTari").value;	
	ajax=getXMLObject();
	if(NombreManual==""){
		alert("El Nombre del Manual Tarifario No Puede Ir Vacio.\n Intente Otravez.");
	}
	if(NombreManual!=""){
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			//alert(validar);
			if(validar!=""){
				alert(validar);
			}
			CargarManualesTarifarios()
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1.1&NombreManual="+NombreManual);
	}
}
function CargarManualesTarifarios(){
	var contenido=document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1");
}

function VerTipoTarifas(){
	var cod_ManTarFk=document.getElementById("cmbManuTari").value;
	var contenido=document.getElementById('TipTar');
	if(cod_ManTarFk=="Seleccione"){
		alert("Seleccione Manual Tarifario.");
	}
	if(cod_ManTarFk!="Seleccione"){
		ajax=getXMLObject();
		ajax.open("POST","ControlCrearTarifas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				contenido.innerHTML = ajax.responseText	
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2.1&cod_ManTarFk="+cod_ManTarFk);
	}
}

function CrearTipoTarifa(){
	var cod_ManTarFk=document.getElementById("cmbManuTari").value;
	var NombreTipoTarifa=document.getElementById("txtTipTar").value;
	if(cod_ManTarFk=="Seleccione"){alert("Seleccione El Manual Tarifario.");}
	if(NombreTipoTarifa==""){alert("Escribar El Nombre de La Tarifa.");}	

	if((NombreTipoTarifa!="")&&(cod_ManTarFk!="Seleccione")){
		ajax=getXMLObject();
		ajax.open("POST","ControlCrearTarifas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var validar=ajax.responseText;
				if(validar!=""){
					alert(validar);
				}
				VerTipoTarifas();
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2.2&cod_ManTarFk="+cod_ManTarFk+"&NombreTipoTarifa="+NombreTipoTarifa);
	}
}


function CrearClaseServ(){	
	var ClaseServicio=document.getElementById("txtClaseServicio").value;
	ajax=getXMLObject();
	if(ClaseServicio==""){
		alert("La Clase de Servicio No Puede Ir Vacio.\n Intente Otravez.");
	}
	if(ClaseServicio!=""){
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar!=""){
				alert(validar);
			}
			CargarClaseServicio()
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.1&ClaseServicio="+ClaseServicio);
	}
}


function CargarClaseServicio(){
	var contenido=document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3");
}


function CrearCentroCosto(){	
	var NombreCentroCosto=document.getElementById("txtCentroCosto").value;
	var Abreviado=document.getElementById("txtAbreviatura").value;
	ajax=getXMLObject();
	if(NombreCentroCosto==""){
		alert("El Centro de Costo No Puede Ir Vacio.\n Intente Otravez.");
	}
	if(Abreviado==""){
		alert("La Abreviatura No Puede Ir Vacio.\n Intente Otravez.");
	}
	
	if((NombreCentroCosto!="")&&(Abreviado!="")){
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar!=""){
				alert(validar);
			}
			CargarCentroCosto()
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4.1&NombreCentroCosto="+NombreCentroCosto+"&Abreviado="+Abreviado);
	}
}


function CargarCentroCosto(){
	var contenido=document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4");
}


function CrearNivelComplejidad(){
	var nivel=document.getElementById("txtNivelComplejidad").value;
	ajax=getXMLObject();
	if(nivel==""){
		alert("El Nivel de Complejidad No Puede Ir Vacio.\n Intente Otravez.");
	}
	if(nivel!=""){
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var validar=ajax.responseText;
			if(validar!=""){
				alert(validar);
			}
			CargarNivelComplejidad()
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5.1&nivel="+nivel);
	}
}


function CargarNivelComplejidad(){
	var contenido=document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5");
}

function cargarProgramasManualTarifario(){
	
	var mTarifario = document.getElementById('manualT').value;
	var descripcion = document.getElementById('descrPT').value;
	
	if(mTarifario!=0){
		document.getElementById('DivPRT').innerHTML='<div align="center">Cargando...</div>';
		
		ajax.open("POST","ControlCrearTarifas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				document.getElementById('DivPRT').innerHTML=ajax.responseText;		
			}
		}
		ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=progManualTarifario&mtarifario="+mTarifario+"&desc="+descripcion);
	}else{
		alert("Seleccione el Manual Tarifario");
		document.getElementById('DivPRT').innerHTML="";
	}
}


function asignarTarifa(id,fila){
	var codProg = document.getElementById('prog'+id).value;
	var FechaIni = document.getElementById('FI'+id).value;
	var FechaFin = document.getElementById('FF'+id).value;
	var Valor = document.getElementById('V'+id).value;
	var manualT = document.getElementById('manualT').value;
	
	if(FechaIni!=""&&FechaFin!=""&&Valor!=""){
		if(validarFechas(FechaIni,FechaFin)){
			ajax.open("POST","ControlCrearTarifas",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					if(document.getElementById('DivPRT').innerHTML!=""){
						cargarProgramasManualTarifario();
					}else{
						EliminarFilaPrograma(fila);
						alert("Tarifa creada con exito");
					}
				}
			}
			ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=crearTarifa&FechaIni="+FechaIni+"&FechaFin="+FechaFin+"&Valor="+Valor+"&manualT="+manualT+"&codProg="+codProg);
		}else{
			alert("La fecha inicial no puede ser mayor a la final");
		}
	}else{
		alert("Faltan campos por llenar");
	}
}

function validarFechas(fechaI,fechaF){
	var detFechaI = fechaI.split("/");
	var detFechaF = fechaF.split("/");

	    var anio = parseInt(detFechaI[2]);
	    var mes = detFechaI[1];
	    var dia = detFechaI[0];
	    var c_anio = parseInt(detFechaF[2]);
	    var c_mes = detFechaF[1];
	    var c_dia = detFechaF[0];
	
	
	if(c_anio > anio)
        return(true);
    else{
        if (c_anio == anio){
            if(c_mes > mes)
                return(true);
            if(c_mes == mes)
                if(c_dia >= dia)
                    return(true);
                else
                    return(false);
            else
                return(false);
        }else
            return(false);
    }
}


function EliminarFilaPrograma(t){
    var td = t.parentNode;
    var tr = td.parentNode;
    var table = tr.parentNode;
    table.removeChild(tr);
}


function EliminarTarifa(id,fila){
	var codProg = document.getElementById('codT'+id).value;
	ajax.open("POST","ControlCrearTarifas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			cargarProgramasManualTarifario();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=5&ct="+codProg);
}


function formatMoneda(valor){
	var temp2="";
	var temp1="";
	var ud=1;
	var logCad = valor.length;
	
	for (var i=logCad-1;i>=0;i--){
		
		temp2=valor.substring(i,i+1);

		temp2+=temp1;
		if(ud==3){
			if(i!=0){
				temp1="."+temp2;
			}else{
				temp1=temp2;
			}
			ud=0;
		}else{
			temp1=temp2;
		}
		ud++;
	}
	temp1="$ "+temp1;
	return temp1;
} 

function scrollBottom(object)  {
	var h = object.scrollHeight; 
	var c = object.clientHeight; 
	var scrollBottom = h - c; // offset of scroll from bottom
	return scrollBottom;
}
