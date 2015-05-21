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
/*
function N(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=00");

}*/


function OpcionN() {
	var contenido=document.getElementById('contenido');
	var val="";
		var radioButtons = document.getElementsByName("radiobutton");
		for (var x = 0; x < radioButtons.length; x++) {
			if (radioButtons[x].checked) {
				val=radioButtons[x].id;  
				
			}	     
		}
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true); // getname will be the
													// servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			
			contenido.innerHTML = ajax.responseText;	
		}
	};
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor="+val); // Posting txtname to Servlet	
}

function DBN(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1");

}


function crardbn(){
	var contenido=document.getElementById('contenido');

	var anio=document.getElementById('anio').value;
	var dbn1=document.getElementById('dbn1').value;
	var dbn2=document.getElementById('dbn2').value;
	var dbn3=document.getElementById('dbn3').value;
	var dbn4=document.getElementById('dbn4').value;
	var dbn5=document.getElementById('dbn5').value;
	var dbn6=document.getElementById('dbn6').value;
	var dbn7=document.getElementById('dbn7').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	if((anio!="")&&(dbn1!="")&&(dbn2!="")&&(dbn3!="")&&(dbn4!="")&&(dbn5!="")&&(dbn6!="")&&(dbn7!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1.1&usu="+usu+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&anio="+anio);
	}else{
		alert("Debe llenar todos los campos basicos a parametrizar");
	}
}


function actualizardbn(){
	var contenido=document.getElementById('contenido');

	var anio=document.getElementById('anio').value;
	var dbn1=document.getElementById('dbn1').value;
	var dbn2=document.getElementById('dbn2').value;
	var dbn3=document.getElementById('dbn3').value;
	var dbn4=document.getElementById('dbn4').value;
	var dbn5=document.getElementById('dbn5').value;
	var dbn6=document.getElementById('dbn6').value;
	var dbn7=document.getElementById('dbn7').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	if((anio!="")&&(dbn1!="")&&(dbn2!="")&&(dbn3!="")&&(dbn4!="")&&(dbn5!="")&&(dbn6!="")&&(dbn7!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1.2&usu="+usu+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&anio="+anio);
	}else{
		alert("Debe llenar todos los campos basicos a actualizar");
	}
}


function crarliqidacion(){
	var contenido=document.getElementById('contenido');

	var anio=document.getElementById('anio').value;
	var dbn1=document.getElementById('dbn1').value;
	var dbn2=document.getElementById('dbn2').value;
	var dbn3=document.getElementById('dbn3').value;
	var dbn4=document.getElementById('dbn4').value;
	var dbn5=document.getElementById('dbn5').value;
	var dbn6=document.getElementById('dbn6').value;
	var dbn7=document.getElementById('dbn7').value;
	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	var dbn10=document.getElementById('dbn10').value;
	var dbn11=document.getElementById('dbn11').value;
	var dbn12=document.getElementById('dbn12').value;
	var dbn13=document.getElementById('dbn13').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	if((anio!="")&&(dbn1!="")&&(dbn2!="")&&(dbn3!="")&&(dbn4!="")&&(dbn5!="")&&(dbn6!="")&&(dbn7!="")&&(dbn8!="")&&(dbn9!="")&&(dbn10!="")&&(dbn11!="")&&(dbn12!="")&&(dbn13!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6.1&usu="+usu+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+dbn10+"&dbn11="+dbn11+"&dbn12="+dbn12+"&dbn13="+dbn13+"&anio="+anio);
	}else{
		alert("Debe llenar todos los campos basicos a parametrizar");
	}
}

function actualizarliquidacion(){
	var contenido=document.getElementById('contenido');

	var anio=document.getElementById('anio').value;
	var dbn1=document.getElementById('dbn1').value;
	var dbn2=document.getElementById('dbn2').value;
	var dbn3=document.getElementById('dbn3').value;
	var dbn4=document.getElementById('dbn4').value;
	var dbn5=document.getElementById('dbn5').value;
	var dbn6=document.getElementById('dbn6').value;
	var dbn7=document.getElementById('dbn7').value;
	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	var dbn10=document.getElementById('dbn10').value;
	var dbn11=document.getElementById('dbn11').value;
	var dbn12=document.getElementById('dbn12').value;
	var dbn13=document.getElementById('dbn13').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	if((anio!="")&&(dbn1!="")&&(dbn2!="")&&(dbn3!="")&&(dbn4!="")&&(dbn5!="")&&(dbn6!="")&&(dbn7!="")&&(dbn8!="")&&(dbn9!="")&&(dbn10!="")&&(dbn11!="")&&(dbn12!="")&&(dbn13!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=6.2&usu="+usu+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+dbn10+"&dbn11="+dbn11+"&dbn12="+dbn12+"&dbn13="+dbn13+"&anio="+anio);
	}else{
		alert("Debe llenar todos los campos basicos a parametrizar");
	}
}

/*
function Creartc(){
	var contenido=document.getElementById('contenido');

	var nom=document.getElementById('desc').value;
	var sig=document.getElementById('sigla').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.1&usu="+usu+"&nom="+nom+"&sig="+sig);

}
*/

function Crearc(){
	var contenido=document.getElementById('contenido');

	var nom=document.getElementById('desc').value;
	//var sig=document.getElementById('sigla').value;
	var v22 = document.getElementById("tc").selectedIndex;
	var tc = document.getElementById("tc").options[v22].value;
	
	var usu=document.getElementById('txtCodusuario').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=2.1&usu="+usu+"&nom="+nom+"&tc="+tc);

}


function Crearsubc(){
	var contenido=document.getElementById('contenido');

	var v22 = document.getElementById("tc").selectedIndex;
	var tc = document.getElementById("tc").options[v22].value;
	var nom=document.getElementById('desc').value;
	var sig=document.getElementById('sigla').value;
		
	var dbn11 = document.getElementById("dbn1").selectedIndex;
	var dbn1 = document.getElementById("dbn1").options[dbn11].value;
	
	var dbn22 = document.getElementById("dbn2").selectedIndex;
	var dbn2 = document.getElementById("dbn2").options[dbn22].value;
	
	var dbn3=document.getElementById('dbn3').value;
	var dbn4=document.getElementById('dbn4').value;
	var dbn5=document.getElementById('dbn5').value;
	
	var dbn6=document.getElementById('dbn6');
	if(dbn6.checked){dbn6="1";}else{dbn6="0";}
	
	var dbn7=document.getElementById('dbn7');
	if(dbn7.checked){dbn7="1";}else{dbn7="0";}
	
	var dbn8=document.getElementById('dbn8');
	if(dbn8.checked){dbn8="1";}else{dbn8="0";}

	var usu=document.getElementById('txtCodusuario').value;
	
	if((nom!="")&&(sig!="")&&(tc!="Seleccione")&&(dbn2!="Seleccione")&&(dbn3!="")&&(dbn4!="")&&(dbn5!="")){
		
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=3.1&usu="+usu+"&nom="+nom+"&tc="+tc+"&sig="+sig+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&dbn8="+dbn8);
	}else{
		alert("Debe llenar todos los campos basicos a crear");
	}
}

function Creartp(){
	var contenido=document.getElementById('contenido');

	var nom=document.getElementById('desc').value;
	var sig=document.getElementById('sigla');
	
	if(sig.checked){
		sig="1";
	}else{sig="0";}
	
	var usu=document.getElementById('txtCodusuario').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=4.1&usu="+usu+"&nom="+nom+"&sig="+sig);

}


function validanit(){
	//alert("cesar");
	var contenido=document.getElementById('contenido');

	var sig=document.getElementById('nit').value;
	
	var tc=document.getElementById('tc').value;
	var tc1=document.getElementById('tc').selectedIndex;
	var tc2=document.getElementById('tc')[tc1].text;
	
//	alert(tc+" "+tc2);
	if(tc2=="NI"){
		
		var returnval = emailfilternot.test(sig)
		if (returnval == false) {
		alert("Los numeros de documentos NIT solo aceptan valores númericos!!!");
		document.getElementById('nit').value = "";
		document.getElementById('nit').focus();
		document.getElementById('nit').focus();
		}else{
	
	//alert(sig.length);
	if(sig.length==14)sig="0"+sig;
	if(sig.length==13)sig="00"+sig;
	if(sig.length==12)sig="000"+sig;
	if(sig.length==11)sig="0000"+sig;
	if(sig.length==10)sig="00000"+sig;
	if(sig.length==9)sig="000000"+sig;
	if(sig.length==8)sig="0000000"+sig;
	if(sig.length==7)sig="00000000"+sig;
	if(sig.length==6)sig="000000000"+sig;
	if(sig.length==5)sig="0000000000"+sig;
	if(sig.length==4)sig="00000000000"+sig;
	if(sig.length==3)sig="000000000000"+sig;
	if(sig.length==2)sig="0000000000000"+sig;
	if(sig.length==1)sig="00000000000000"+sig;
	//alert(sig);
	if(sig.length<1){
		alert("El numero de documento es incorrecto");
	}else{
		
		var a=sig.substring(0,1);
		var b=sig.substring(1,2);
		var c=sig.substring(2,3);
		var d=sig.substring(3,4);
		var e=sig.substring(4,5);
		var f=sig.substring(5,6);
		var g=sig.substring(6,7);
		var h=sig.substring(7,8);
		var i=sig.substring(8,9);
		var j=sig.substring(9,10);
		var k=sig.substring(10,11);
		var l=sig.substring(11,12);
		var m=sig.substring(12,13);
		var n=sig.substring(13,14);
		var o=sig.substring(14,15);
		
	 var suma=a*71+b*67+c*59+d*53+e*47+f*43+g*41+h*37+i*29+j*23+k*19+l*17+m*13+n*7+o*3;
	 var r=suma%11;
	 var dv=0;
	 if(r==0){
	  dv=0;
	 }else{
	  if(r==1){
	  dv=1;
	  }else{
	   dv=11-r;
	  }
	 }
	 
	//document.getElementById("contenido").innerHTML;//=aviso;
	document.getElementById("dv").value=dv;
		
	 
	 //suma=suma+x*75;
	// alert("a: "+a+" b:"+b+" c:"+c+" d:"+d+" e:"+e+" f:"+f+" g:"+g+" h:"+h+" i:"+i+" j:"+j+" k:"+k+" l:"+l+" m:"+m+" n:"+n+" o:"+o+" SUMA: "+suma+" RESIDUO: "+r+" DV"+dv);
	
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			//OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	//ajax.send("valor=5.1&usu="+usu+"&nom="+nom+"&sig="+sig);
	}
	}//solo numeros
	}//tc2=ni
}



function Crearfeps(){
	var contenido=document.getElementById('contenido');

	//var nom=document.getElementById('desc').value;
	//var sig=document.getElementById('sigla');
	

	var dbn1=document.getElementById('dbn1').value;
	var dbn2=document.getElementById('dbn2').value;
	var dbn3=document.getElementById('dbn3').value;
	var dbn4=document.getElementById('dbn4').value;
	var dbn5=document.getElementById('dbn5').value;
	var dbn6=document.getElementById('dbn6').value;
	var dbn7=document.getElementById('dbn7').value;
	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	var dbn10=document.getElementById("nit").value;
	var dbn11=document.getElementById("dv").value;
	
	var tc=document.getElementById('tc').value;
	var tc1=document.getElementById('tc').selectedIndex;
	var tc2=document.getElementById('tc')[tc1].text;
	
	
	var usu=document.getElementById('txtCodusuario').value;
	
	if((tc2=="NI")&&(dbn11=="")){
		alert("Error en el NIT");
		document.getElementById('nit').value = "";
		document.getElementById('nit').focus();
		document.getElementById('nit').focus();
		
	}else{
	
	if((dbn1!="")&&(dbn2!="")&&(dbn3!="")&&(dbn4!="")&&(dbn5!="")&&(dbn6!="")&&(dbn7!="")&&(dbn8!="")&&(dbn9!="")&&(dbn10!="")){

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	//ajax.send("valor=4.1&usu="+usu+"&nom="+nom+"&sig="+sig);
	ajax.send("valor=5.1&usu="+usu+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+dbn10+"&dbn11="+dbn11+"&tc="+tc);
	}else{
		alert("Debe llenar todos los campos basicos a parametrizar");
	}
 }
}




var emailfilterno = /[^0-9\.]/g; // cualquier cosa que no sea numero y punto;
//this.value = this.value.replace(pattern, '');

//var emailfilterno = /^[0-9](\.[0-9])?$/;
function checknumt(c) {
	var cc="dbn"+c;
	var e = document.getElementById(cc).value;
	var returnval = emailfilterno.test(e)

	/*var x = e.substr(0, 1);
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}*/

	if (returnval == true) {
		if (e != "") {
			alert("Formato incorrectoj!!!");
			document.getElementById(cc).value = "";
			document.getElementById(cc).focus();
		}
	}
	return returnval
}



var emailfilternot = /^[0-9]*$/;
function checknum(c) {
	var cc = "valor0" + c;
	var e = document.getElementById(cc).value;
	var returnval = emailfilternot.test(e)

	var x = e.substr(0, 1);
	if (x == 0) {
		var y = e.substr(1);
		document.getElementById(cc).value = y;
	}

	if (returnval == false) {
		alert("Formato incorrecto!!!");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	return returnval
}


function validaannio(annio,ac){   
	a=annio.value;
	var returnval = emailfilternot.test(a);
	if (returnval == false) {
		alert("Solo se aceptan caracteres númericos!!!");
		annio.value = "";
	}
	//alert(a.length);
 if((a.length==4)&&(a<ac)){
 alert("El año de la nomina a parametrizar no puede ser menor que el año actual");
 annio.value = "";
 }else{
	 if (a.length>4){
		 //alert(annio.id);
		 an = a.substring(0, 4);
		 annio.value = an;
	 }
 }	 
} 

function compare_dates(fecha, fecha2)   
{   
   var xMonth=fecha.substring(3, 5);   
   var xDay=fecha.substring(0, 2);   
   var xYear=fecha.substring(6,10);   
   var yMonth=fecha2.substring(3, 5);   
   var yDay=fecha2.substring(0, 2);   
   var yYear=fecha2.substring(6,10);   
   if (xYear> yYear)   
   {   
       return(true)   
   }   
   else  
   {   
     if (xYear == yYear)   
     {    
       if (xMonth> yMonth)   
       {   
           return(true)   
      }   
      else  
       {    
         if (xMonth == yMonth)   
         {   
           if (xDay>= yDay)   
             return(true);   
           else  
             return(false);   
         }   
         else  
           return(false);   
       }   
     }   
     else  
       return(false);   
   }   
} 



/////funcion formato de fecha//////////
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

		/*
		 * /////////////////////////////////////////////////// //Validar fecha
		 * mayor que la fecha actual
		 * 
		 * if(ano.length==4){ if (ano==annio) { if(ini==mes){
		 * if((dia==dias)||(dia<dias)){ alert("La fecha de Vencimiento no puede
		 * ser menor o igual a la fecha Actual"); val2=''; } }else{ if(ini<mes){
		 * alert("La fecha de Vencimiento no puede ser menor o igual a la fecha
		 * Actual"); val2=''; } } } if (ano<annio) { alert("La fecha de
		 * Vencimiento no puede ser menor o igual a la fecha Actual"); val2=''; } }
		 * 
		 * ///////////////////////////////////////////////////
		 */
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
    		document.getElementById("ref0").focus();
    		
    }
	
}//FIN MASCA



function Empleados(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7");

}

function CEmpleados(){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7.1");

}

function CEmpleadosi(i){
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7.1&indice="+i);

}


function CEmpleadosy(y){
	var contenido=document.getElementById('contenido');
	
	//var tdn=document.getElementById('tc').value;
	/*var ndn=document.getElementById('nit').value;
	
	var pan=document.getElementById('dbn2').value;
	var pnn=document.getElementById('dbn4').value;
	*/
	if(y==1){
	var ndn=prompt("Numero de Docmuento a consultar","");
	}
	if(y==2){
	var pan=prompt("Digite el primer apellido","");
	}
	if(y==3){
	var pnn=prompt("Digite el primer nombre","");
	}
			
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7.1&ndn="+ndn+"&pan="+pan+"&pnn="+pnn+"&disp="+y);

}


function ActualizaEmpleados(e){
	var contenido=document.getElementById('contenido');
	
	if(e.id=="dbn8"){
		document.getElementById('dbn9').focus;
		if(document.getElementById('dbn9').value=="Seleccione"){
			alert("Debe seleccionar el nuevo subcentro de costo");
		}
	}
	
	var dbn1=e.id;
	var dbn2=e.value;
	
	if(e.id=="dbn32"){
		var dbn32=document.getElementById('dbn32');//AuxilioTransporte
		if(dbn32.checked){
			dbn2="1";
		}else{dbn2="0";}
	}
		
	//alert(dbn1+" - "+dbn2);
	
	var dbn3=document.getElementById('ce1').value;
	var dbn4=document.getElementById('ce2').value;
	var dbn5=document.getElementById('ce3').value;
	var dbn6=document.getElementById('ce4').value;
	
	var usu=document.getElementById('txtCodusuario').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7.5&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&usu="+usu);

}

function subcentros(){
	var contenido=document.getElementById('cco');

	var dbn8=document.getElementById('dbn8').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7.2&dbn8="+dbn8);

}

function subcentros2(){
	var contenido=document.getElementById('cco');

	var dbn8=document.getElementById('dbn8').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=7.4&dbn8="+dbn8);

}

function CrearEmpleado(){
	var contenido=document.getElementById('contenido');
	
	var tc=document.getElementById('tc').value;
	//var tc1=document.getElementById('tc').selectedIndex;
	//var tc2=document.getElementById('tc')[tc1].text;

	var nit=document.getElementById('nit').value;
	var dbn1=document.getElementById('dbn1').value;
	var dbn2=document.getElementById('dbn2').value;
	var dbn3=document.getElementById('dbn3').value;
	var dbn4=document.getElementById('dbn4').value;
	var dbn5=document.getElementById('dbn5').value;
	var dbn6=document.getElementById('dbn6').value;
	var dbn7=document.getElementById('dbn7').value;
	var dbn8=document.getElementById('dbn8').value;//cc
	var dbn9=document.getElementById('dbn9').value;//scc
	var dbn10=document.getElementById('dbn10').value;
	var dbn11=document.getElementById('dbn11').value;
	var dbn12=document.getElementById('dbn12').value;
	var dbn13=document.getElementById('dbn13').value;//estado
	var dbn14=document.getElementById('dbn14').value;
	var dbn15=document.getElementById('dbn15').value;//claseempleado
	var dbn16=document.getElementById('dbn16').value;//contrato
	var dbn17=document.getElementById('dbn17').value;
	var dbn18=document.getElementById('dbn18').value;//tipocuenta
	var dbn19=document.getElementById('dbn19').value;
	var dbn20=document.getElementById('dbn20').value;//formadepago
	var dbn21=document.getElementById('dbn21').value;
	var dbn22=document.getElementById('dbn22').value;
	var dbn23=document.getElementById('dbn23').value;
	var dbn24=document.getElementById('dbn24').value;
	var dbn25=document.getElementById('dbn25').value;
	var dbn26=document.getElementById('dbn26').value;
	var dbn27=document.getElementById('dbn27').value;
	var dbn28=document.getElementById('dbn28').value;
	var dbn29=document.getElementById('dbn29').value;
	var dbn30=document.getElementById('dbn30').value;
	var dbn31=document.getElementById('dbn31').value;
	var dbn32=document.getElementById('dbn32');//AuxilioTransporte
	var dbn33=document.getElementById('dbn33').value;//escolaridad
	var dbn34=document.getElementById('dbn34').value;//gruposanguineo
	var dbn35=document.getElementById('dbn35').value;
	var dbn36=document.getElementById('dbn36').value;
	var dbn37=document.getElementById('dbn37').value;
	var dbn38=document.getElementById('dbn38').value;
	var dbn39=document.getElementById('dbn39').value;

	if(dbn32.checked){
		dbn32="1";
	}else{dbn32="0";}
	
	
	var usu=document.getElementById('txtCodusuario').value;
	
	if((nit!="")&&(dbn2!="")&&(dbn4!="")){

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Empleado Creado exitosamente!!!");
			Empleados();
			//contenido.innerHTML = ajax.responseText	
			//OpcionN();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	//ajax.send("valor=4.1&usu="+usu+"&nom="+nom+"&sig="+sig);
	ajax.send("valor=7.3&usu="+usu+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+dbn10+"&dbn11="+dbn11+"&dbn12="+dbn12+"&dbn13="+dbn13+"&dbn14="+dbn14+"&dbn15="+dbn15+"&dbn16="+dbn16+"&dbn17="+dbn17+"&dbn18="+dbn18+"&dbn19="+dbn19+"&dbn20="+dbn20+"&dbn21="+dbn21+"&dbn22="+dbn22+"&dbn23="+dbn23+"&dbn24="+dbn24+"&dbn25="+dbn25+"&dbn26="+dbn26+"&dbn27="+dbn27+"&dbn28="+dbn28+"&dbn29="+dbn29+"&dbn30="+dbn30+"&dbn31="+dbn31+"&dbn32="+dbn32+"&dbn33="+dbn33+"&dbn34="+dbn34+"&dbn35="+dbn35+"&dbn36="+dbn36+"&dbn37="+dbn37+"&dbn38="+dbn38+"&dbn39="+dbn39+"&tc="+tc+"&nit="+nit);
	}else{
		alert("Debe llenar los campos basicos a crear");
	}

}


function Movimientos(m){
	document.getElementById('Opcion').innerHTML = "";
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			//Periodo();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	if(m==0){
	ajax.send("valor=8&dbn1="+m);
	}else{
	ajax.send("valor=8&dbn1="+m);
	}
}

function Periodo(){
	var contenido=document.getElementById('contenido');
	
	var tc=document.getElementById('tc').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			document.getElementById('dbn1').value=ajax.responseText;
			document.getElementById('Opcion').innerHTML = "";
			document.getElementById('code').value="";
			document.getElementById("desc0").value = "";
			document.getElementById("dbn3").value = ""; 
			//contenido.innerHTML = 	
			//Periodo();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=8.1&tc="+tc);


}


function vtotal(cj,pd,cm,t,sig,dod){
	
	
	
	var contenido=document.getElementById('contenido');
	
	var tc=document.getElementById('valor0'+cj).value;
	var sal=document.getElementById('sal').value;
	var salt=0;
	var vt=0;
	if(t=="1"){
		 salt=1;//*tc;
		}
	if(t=="2"){
		var w=1;
		
		 salt=(((parseFloat(sal)*30)/240)*pd);//*tc;
		 var salt2=((parseInt(sal)*30)/240);
		 //salt=salt*parseFloat(w);
		 alert("sal "+sal+" sal*30/240= "+salt2+" *"+pd+" = "+salt);
		 vt=salt*tc;
		}
	if(t=="3"){
		 salt=((parseInt(sal))*parseInt(pd));//*tc;
		 vt=salt*tc;
		}
	//me traigo el porcentaje y calculo el valor total y lo asigno al controlador me traigo el codigo y lo inserto a la db
	//alert(tc+" pd "+pd+" sal "+sal+" salt "+salt);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			document.getElementById('vt'+cj).value=salt*parseInt(tc);
			alert(tc+"total= "+salt*parseInt(tc));
			//document.getElementById('dbn1').value=ajax.responseText;
			//contenido.innerHTML = 	
			//Periodo();
		}
	}
	//alert(tc+" - "+salt+" - "+t+" - "+sal+" - "+pd);
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	if(t=="1"){
	ajax.send("valor=9.1&tc="+tc+"&dbn1="+cm+"&dbn2="+tc+"&sig="+sig+"&dod="+dod+"&dbn3="+tc);//paso el codigo del moviemeineto  o del detalle y actualizo y luego la pagina
	}
	if(t=="2"){
		ajax.send("valor=9.1&tc="+tc+"&dbn1="+cm+"&dbn2="+salt+"&sig="+sig+"&dod="+dod+"&dbn3="+vt);//paso el codigo del moviemeineto  o del detalle y actualizo y luego la pagina
		}
	if(t=="3"){
		ajax.send("valor=9.1&tc="+tc+"&dbn1="+cm+"&dbn2="+salt+"&sig="+sig+"&dod="+dod+"&dbn3="+vt);//paso el codigo del moviemeineto  o del detalle y actualizo y luego la pagina
		}
	
}

function DedDev(m){
	//alert("mmmmmmmm "+m);
	var contenido=document.getElementById('Opcion');

	var tc=document.getElementById('tc').value;
	var code=document.getElementById('code').value;
	var usu=document.getElementById('txtCodusuario').value;
	
//	var tc1=document.getElementById('tc').selectedIndex;
//	var tc2=document.getElementById('tc')[tc1].text;
	
	//alert("TC "+tc);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			if(ajax.responseText=="1"){
				DedDev(m);
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=9&usu="+usu+"&tc="+tc+"&code="+code+"&dbn1="+m);
}


function dt(at,sd,codm){
	var contenido=document.getElementById('Opcion');
   // alert(at+" "+sd);
	var valor050=document.getElementById('valor050').value;
	document.getElementById('valor00').value=parseInt(sd)*parseInt(valor050);
	document.getElementById('vt0').value=parseInt(sd)*parseInt(valor050);
	document.getElementById('valor01').value=parseInt(at)*parseInt(valor050);
	document.getElementById('vt1').value=parseInt(at)*parseInt(valor050);
	
	ActualizaFijo(at,sd,codm);
}


function ActualizaFijo(at,sd,sig){
	
	//alert("Auxt "+at+" SueldoDiario "+sd+" numero de lineas "+x+" sig "+sig);
	var contenido=document.getElementById('Opcion');
	var valor050=document.getElementById('valor050').value;

	at=parseInt(at)*parseInt(valor050);
	sd=parseInt(sd)*parseInt(valor050);
	var ss=document.getElementById('codm0').value;
	var aa=document.getElementById('codm1').value;
	///ss=ss-1;
	//aa=aa-1;
	
	if(valor050!=""){
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//alert("este seria el total "+ajax.responseText);
			document.getElementById('valor02').value=ajax.responseText;
			document.getElementById('vt2').value=ajax.responseText;
			document.getElementById('valor03').value=ajax.responseText;
			document.getElementById('vt3').value=ajax.responseText;
		//	contenido.innerHTML = ajax.responseText	
		//	if(ajax.responseText=="1"){
				//DedDev();
		//	}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=9.2&dbn1="+sd+"&dbn2="+at+"&dbn3="+ss+"&dbn4="+aa+"&sig="+sig+"&dbn5="+valor050);
	}
}


function limpiarmovimientos(){

	document.getElementById('Opcion').innerHTML = "";
}



function liquidarn(){
	//document.getElementById('Opcion').innerHTML = "";
	var contenido=document.getElementById('contenido');

	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			//Periodo();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=10&dbn1="+contenido);
}

function subcentrosnomina(){
	var contenido=document.getElementById('cco');

	var dbn8=document.getElementById('dbn8').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=10.1&dbn8="+dbn8);
}

function Pacientesxcentro(){
	var contenido=document.getElementById('Opcion');

	var tc=document.getElementById('tc').value;
	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=10.2&dbn8="+dbn8+"&dbn9="+dbn9+"&tc="+tc);
}


function LiquidaN(dbn8,dbn9,tc){
	var contenido=document.getElementById('Opcion');
	//alert("te toca llenar la tabla de provisiones ya aqui tienes todos los valores solo son procentajes establecidos y listo");

	//var tc=document.getElementById('tc').value;
	//var dbn8=document.getElementById('dbn8').value;
	//var dbn9=document.getElementById('dbn9').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=10.3&dbn8="+dbn8+"&dbn9="+dbn9+"&tc="+tc);
}

function InterfazN(){
	
	var contenido=document.getElementById('contenido');

	var dbn8=document.getElementById('Opcion');

	//var tc=document.getElementById('tc').value;
	//var dbn8=document.getElementById('dbn8').value;
	//var dbn9=document.getElementById('dbn9').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11&dbn8="+dbn8);
}

function subcentrosinomina(){
	var contenido=document.getElementById('cco');

	var dbn8=document.getElementById('dbn8').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11.1&dbn8="+dbn8);
}

function InterfazNomina(){
	
	var contenido=document.getElementById('Opcion');

	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11.2&dbn8="+dbn8+"&dbn9="+dbn9);
}

function creainterfazdenomina(w,c){
	
	//var contenido=document.getElementById('Opcion');

	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	//alert(dbn8+" "+dbn9+" "+w+" "+c);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=11.3&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+w+"&dbn11="+c+"&usu="+usu);
}

function InterfazPN(){
	
	var contenido=document.getElementById('contenido');

	var dbn8=document.getElementById('Opcion');

	//var tc=document.getElementById('tc').value;
	//var dbn8=document.getElementById('dbn8').value;
	//var dbn9=document.getElementById('dbn9').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12&dbn8="+dbn8);
}

function subcentrosinomina2(){
	var contenido=document.getElementById('cco');

	var dbn8=document.getElementById('dbn8').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12.1&dbn8="+dbn8);
}

function InterfazProvisiones(){
	
	var contenido=document.getElementById('Opcion');

	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12.2&dbn8="+dbn8+"&dbn9="+dbn9);
}

function creainterfazdeprovisiones(w,c,i){
	
	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	//alert(dbn8+" "+dbn9+" "+w+" "+c);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=12.3&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+w+"&dbn11="+c+"&usu="+usu+"&indice="+i);
}

function InterfazF(){
	
	var contenido=document.getElementById('contenido');

	var dbn8=document.getElementById('Opcion');

	//var tc=document.getElementById('tc').value;
	//var dbn8=document.getElementById('dbn8').value;
	//var dbn9=document.getElementById('dbn9').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=13&dbn8="+dbn8);
}

function subcentrosinomina3(){
	var contenido=document.getElementById('cco');

	var dbn8=document.getElementById('dbn8').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=13.1&dbn8="+dbn8);
}

function InterfazFacturacion(){
	 document.getElementById('Opcion').innerHTML='<p>Consultando Programas... No Cierre La Ventana...</p><img src="Imagenes/load.gif">';
		
	var contenido=document.getElementById('Opcion');

	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=13.2&dbn8="+dbn8+"&dbn9="+dbn9);
}


function creainterfazdefacturacion(w,c,x){
	
	//var contenido=document.getElementById('Opcion');

	var dbn8=document.getElementById('dbn8').value;
	var dbn9=document.getElementById('dbn9').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	//alert(dbn8+" "+dbn9+" "+w+" "+c);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=13.3&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+w+"&dbn11="+c+"&usu="+usu+"&dbn12="+x);
}

function A(x){
//alert("AAAAAAAAAAAA "+x);
	 document.getElementById('Opcion').innerHTML='<p>Consultando Programas... No Cierre La Ventana...</p><img src="Imagenes/load.gif">';
		
var contenido=document.getElementById('Opcion');

var dbn8=document.getElementById('dbn8').value;
var dbn9=document.getElementById('dbn9').value;

ajax=getXMLObject();
ajax.open("POST","ControlNomina",true);
ajax.onreadystatechange = function(){
	if(ajax.readyState == 4){
		contenido.innerHTML = ajax.responseText	
	}
}
ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
ajax.send("valor=13.2&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+x);
}



function DatosI(){
	var contenido=document.getElementById('contenido');

	var dbn8=5;//document.getElementById('dbn8').value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=15&dbn8="+dbn8);
}

function crardbi(){
	var contenido=document.getElementById('contenido');

	var anio=document.getElementById('cta1').value;
	var dbn1=document.getElementById('cta2').value;
	var dbn2=document.getElementById('cta3').value;
	var dbn3=document.getElementById('cta4').value;
	var dbn4=document.getElementById('ncta1').value;
	var dbn5=document.getElementById('ncta2').value;
	var dbn6=document.getElementById('ncta3').value;
	var dbn7=document.getElementById('ncta4').value;
	var dbn8=document.getElementById('hcta1').value;
	var dbn9=document.getElementById('hcta2').value;
	var dbn10=document.getElementById('hcta3').value;
	var dbn11=document.getElementById('hcta4').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	if((anio!="")&&(dbn1!="")&&(dbn2!="")&&(dbn3!="")&&(dbn4!="")&&(dbn5!="")&&(dbn6!="")&&(dbn7!="")&&(dbn8!="")&&(dbn9!="")&&(dbn10!="")&&(dbn11!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			alert("Parametros iniciales creados exitosamente!!!");
			DatosI();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=15.1&usu="+usu+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+dbn10+"&dbn11="+dbn11+"&anio="+anio);
	}else{
		alert("Debe llenar todos los campos basicos a parametrizar");
	}
}


function actualizardbi(){
	var contenido=document.getElementById('contenido');


	var anio=document.getElementById('cta1').value;
	var dbn1=document.getElementById('cta2').value;
	var dbn2=document.getElementById('cta3').value;
	var dbn3=document.getElementById('cta4').value;
	var dbn4=document.getElementById('ncta1').value;
	var dbn5=document.getElementById('ncta2').value;
	var dbn6=document.getElementById('ncta3').value;
	var dbn7=document.getElementById('ncta4').value;
	var dbn8=document.getElementById('hcta1').value;
	var dbn9=document.getElementById('hcta2').value;
	var dbn10=document.getElementById('hcta3').value;
	var dbn11=document.getElementById('hcta4').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	if((anio!="")&&(dbn1!="")&&(dbn2!="")&&(dbn3!="")&&(dbn4!="")&&(dbn5!="")&&(dbn6!="")&&(dbn7!="")&&(dbn8!="")&&(dbn9!="")&&(dbn10!="")&&(dbn11!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlNomina",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			alert("Parametros iniciales actualizados exitosamente!!!");
			DatosI();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=15.2&usu="+usu+"&dbn1="+dbn1+"&dbn2="+dbn2+"&dbn3="+dbn3+"&dbn4="+dbn4+"&dbn5="+dbn5+"&dbn6="+dbn6+"&dbn7="+dbn7+"&dbn8="+dbn8+"&dbn9="+dbn9+"&dbn10="+dbn10+"&dbn11="+dbn11+"&anio="+anio);
	}else{
		alert("Debe llenar todos los campos basicos a actualizar");
	}
}
