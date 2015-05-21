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


function Entradas(){
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('contenido');
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Entradas&user="+user);

}

function Salidas(){
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('contenido');
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlSalidas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Salidas&user="+user);

}

function Traslados(){
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('contenido');
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlTraslado",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Traslados&user="+user);

}


function Formulas(){
	//alert("formulasjs");
	var contenido=document.getElementById('contenido');
	//var contenid=document.getElementById('repetir');
	var contenidos=document.getElementById('formul');
	var contenidoss=document.getElementById('disp');
	document.getElementById('contenido').innerHTML='<div align="center"><p>Cargando Resultados...</p><img src="Imagenes/load.gif"></div>';

	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			contenidos.innerHTML ="";
			contenidoss.innerHTML ="";
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Formulas");

}


function Devolucion(){
	var contenido=document.getElementById('contenido');
	//var contenid=document.getElementById('repetir');
	//var contenidos=document.getElementById('formul');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			//contenidos.innerHTML ="";
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Devolucion");

}

function AnularE(){
	var contenido=document.getElementById('contenido');
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=AnularE");

}


function Rips(){
	var contenido=document.getElementById('contenido');
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","Rips",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Ripss");

}

//funci de la fecha de nacimiento
var patron = new Array(2,2,4);
function masca(d,pat,nums,dias,mes,annio){
var sep="/";
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if((ini>31)||(ini=="00")){
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
	if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
		x=val2.substring(0,2);
		if(x=="31"){
			val2="30";
			val2=val2+ini;
		}
	}
	if((ini>12)||(ini=="00")){
		alert("Mes No Valido!!!");
		val2=d.value="";
		d.focus();
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
	///////////////////////////////////////////////////
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

var numerofiltro=/^[0-9]*$/;  
var patronh = new Array(2, 2);
function checkhora(){
	var cc="txtHora";
	var e=document.getElementById(cc).value;
	largo = e.length
	//alert(e);
	var	val = e.replace(":","");
	//alert(val);
	var returnval=numerofiltro.test(val)
	if (returnval==false){
		y=e.substring(0,largo-1);
		document.getElementById(cc).value = y;
	}
	
	if((largo==3)&&(e.substring(2)!=":")){
		y=e.substring(0,2);
		z=e.substring(2);
		y=y+":"+z;
	document.getElementById(cc).value = y;
	}
	

	
	if(largo==2){
		//y=e.substring(0,5);
		if(e>24)
		document.getElementById(cc).value = "00";
	}
	
	if(largo==5){
		y=e.substring(3,5);
		z=e.substring(0,2);
		//alert(y);
		if(y>59)
		document.getElementById(cc).value = z+":00";
	}
	
	if(largo>5){
		y=e.substring(0,5);
		document.getElementById(cc).value = y;
	}

}


//var patron = new Array(2,2,4);
function mascas(d,pat,nums,dias,mes,annio){
var sep="/";
if(d.valant != d.value){
	val = d.value
	largo = val.length
	ini=val.substring(0,2);
	if((ini>31)||(ini=="00")){
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
	if((ini=="04")||(ini=="06")||(ini=="09")||(ini=="11")){
		x=val2.substring(0,2);
		if(x=="31"){
			val2="30";
			val2=val2+ini;
		}
	}
	if((ini>12)||(ini=="00")){
		alert("Mes No Valido!!!");
		val2=d.value="";
		d.focus();
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
	///////////////////////////////////////////////////
	//Validar fecha mayor que la fecha actual
		
	if(ano.length==4){
		if (ano==annio) {
			if(ini==mes){
				if(dia>dias){
					alert("La fecha no puede ser mayor a la fecha Actual");
				val2='';
				}
			}else{
				if(ini>mes){
					alert("La fecha no puede ser mayor a la fecha Actual");
					val2='';
				}
			}
		}
		if (ano>annio) {
			alert("La fecha no puede ser mayor a la fecha Actual");
			val2='';
		}
	}
	
	///////////////////////////////////////////////////
	
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

/////////////////////////

var fechafilter=/^\d{2,2}\/\d{2,2}\/\d{4,4}$/;
function checkfecha(c){
	var cc="txtVence"+c;
	var e=document.getElementById(cc).value;
	var returnval=fechafilter.test(e)
	if (returnval==false){
		alert("La fecha es Invalida");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}

function checkart(c){
	var ccH="txtTipoMeH"+c;
	var cc="txtTipoMe"+c;
	var su="sugerencias1"+c;
	var e=document.getElementById(ccH).value;
	/*alert("ESTO ES LO Q PASA DEBE SER CERO!!! "+c);
	alert("ESTO debe ser CC!!! "+ccH);*/
	if(e==""){
		alert("Debe seleccionar un elemento de la lista");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	document.getElementById(su).style.display = "none";
}

function checkiva(c){
	var ccH="txtIvaH"+c;
	var cc="txtIva"+c;
	var su="sugerencias2"+c;
	var e=document.getElementById(ccH).value;
	if(e==""){
		alert("Debe seleccionar un elemento de la lista");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	document.getElementById(su).style.display = "none";
}

function Repetir(c){
	var emailfilter=/^[0-9]*$/;  
	var cc="txtCantidad"+c;
	var cc1="txtVunitario"+c;
	var cc2="txtIva"+c;
	var cc3="txtTotal"+c;

	var contenid=document.getElementById('repetir');
	var cant=document.getElementById(cc).value;
	var vu=document.getElementById(cc1).value;
	var iva=document.getElementById(cc2).value;
	
	if(iva==0){	
		var e=cant*vu;
		var returnval=emailfilter.test(e)
		if (returnval==false){
				document.getElementById(cc3).value = "";
		}else{
				document.getElementById(cc3).value = e;	
		}
	}else{
		var resultado=cant*vu*((iva/100)+1);

		var result=Math.round(resultado*100)/100 ;
		if (returnval==false){
			document.getElementById(cc3).value = "";
		}else{
			document.getElementById(cc3).value = result;
		}
	}

	if((cant!="")&&(iva!="")){
	}else{
		document.getElementById(cc1).value = "";
		document.getElementById(cc3).value = "";
		if(cant==""){alert("Seleccione La Cantidad"); document.getElementById(cc).focus();}
		}	
	Verificacant(c);
}

function Verificacant(c){
	  // alert("cesar");
		var cc="txtCantidad"+c;
		var cc1="txtCantSol"+c;
		var cant=document.getElementById(cc).value;
		var vu=document.getElementById(cc1).value;
		
		//alert("cant"+cant+"vu"+vu);
		if (parseInt(cant)>parseInt(vu)) { 
			alert("La cantidad recibida no puede ser mayor que la cantidad solicitada");
			document.getElementById(cc).value="";
			document.getElementById(cc).focus();
		}
	
}


function Repetir2N(cont,c,f){
	
	//hay q probar copiando toda la funcion Repetir2 aqui para ver si actualiza los datos antes de ingresar y listo!!!
	//alert("Repetir2N");
	
	///////////////////////////
	var Ndatos2="";
	for(i=0;i<=c;i++){
		var p0="txtTipoMe"+i;
		var p1="txtTipoMeH"+i;
		var p2="txtLote"+i;
		var p3="txtInvima"+i;
		var p4="txtVence"+i;
		var p5="txtCantidad"+i;
		var p6="txtIva"+i;
		var p7="txtIvaH"+i;
		var p8="txtVunitario"+i;
		var p9="txtTotal"+i;
				
		var p0ar="cmbLab"+i;//ar
		var p1ar="txtCantSol"+i;//ar
		var p2ar="cmbEMB"+i;//ar
		var p3ar="cmbEMP"+i;//ar
		var p4ar="cmbEMV"+i;//ar
		var p5ar="txtT"+i;//ar
		var p6ar="cmbENVP"+i;//ar
		var p7ar="cmbEMPS"+i;//ar
		var p8ar="cumple"+i;//ar
		var p9ar="dona"+i;//ar
		
		var r0=document.getElementById(p0).value;
		var r1=document.getElementById(p1).value;
		var r2=document.getElementById(p2).value;
		var r3=document.getElementById(p3).value;
		var r4=document.getElementById(p4).value;
		var r5=document.getElementById(p5).value;
		var r6=document.getElementById(p6).value;
		var r7=document.getElementById(p7).value;
		var r8=document.getElementById(p8).value;
		var r9=document.getElementById(p9).value;
		
		
		
		var r00ar=document.getElementById(p0ar).selectedIndex;
		var r0ar=document.getElementById(p0ar).options[r00ar].value;
		var r1ar=document.getElementById(p1ar).value;
		
		
		var r22ar=document.getElementById(p2ar).selectedIndex;
		var r2ar=document.getElementById(p2ar).options[r22ar].value;
		
		var r33ar=document.getElementById(p3ar).selectedIndex;
		var r3ar=document.getElementById(p3ar).options[r33ar].value;
		
		var r44ar=document.getElementById(p4ar).selectedIndex;
		var r4ar=document.getElementById(p4ar).options[r44ar].value;
		
		var r5ar=document.getElementById(p5ar).value;
		
		var r66ar=document.getElementById(p6ar).selectedIndex;
		var r6ar=document.getElementById(p6ar).options[r66ar].value;
		
		var r77ar=document.getElementById(p7ar).selectedIndex;
		var r7ar=document.getElementById(p7ar).options[r77ar].value;
		
		var r8ar=document.getElementById(p8ar);
		var r9ar=document.getElementById(p9ar);
		if (r8ar.checked){r8ar="1";}else{r8ar="0";}
		if (r9ar.checked){r9ar="1";}else{r9ar="0";}
		
	
		
		
		
		
		
		
		for(a=0;a<r0.length;a++){
			r0=r0.replace('%','@');
			//Resul=Resul.replace('ñ','@');				
		}
		
		Ndatos2=Ndatos2+r0;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r1;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r2;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r3;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r4;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r5;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r6;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r7;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r8;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r9;Ndatos2=Ndatos2+"|";
		
		
		Ndatos2=Ndatos2+r0ar;Ndatos2=Ndatos2+"|";r0ar
		Ndatos2=Ndatos2+r1ar;Ndatos2=Ndatos2+"|";r1ar
		Ndatos2=Ndatos2+r2ar;Ndatos2=Ndatos2+"|";r2ar
		Ndatos2=Ndatos2+r3ar;Ndatos2=Ndatos2+"|";r3ar
		Ndatos2=Ndatos2+r4ar;Ndatos2=Ndatos2+"|";r4ar
		Ndatos2=Ndatos2+r5ar;Ndatos2=Ndatos2+"|";r5ar
		Ndatos2=Ndatos2+r6ar;Ndatos2=Ndatos2+"|";r6ar
		Ndatos2=Ndatos2+r7ar;Ndatos2=Ndatos2+"|";r7ar
		Ndatos2=Ndatos2+r8ar;Ndatos2=Ndatos2+"|";r8ar
		Ndatos2=Ndatos2+r9ar;Ndatos2=Ndatos2+"|";r9ar
		
		
	}
	var Ndatos=Ndatos2//encodeURIComponent(Ndatos2.value)
	//alert("Datos actualizados: "+Ndatos);
	//////////////////////////////////	
	//alert("ccccc: "+c);
	//Repetir(c);	
	//////////////////////////
	CrearInv(f,Ndatos);
}


function Repetir2(cont,c,f){
	 //alert("Repetir2");
		//alert("contador: "+cont);
		//alert("c: "+c+" f:"+f);
		
		var elementos=f.elements;
		var nE = elementos.length; 
		
		
		//alert("elementos: "+elementos);
		//alert("contador: "+cont+" nE: "+nE+" c: "+c+" f:"+f);
		
	/////////////////////////////////////		
			
	var Ndatos2="";
	for(i=0;i<=c;i++){
		var p0="txtTipoMe"+i;
		var p1="txtTipoMeH"+i;
		var p0ar="cmbLab"+i;//ar
		var p2="txtLote"+i;
		var p3="txtInvima"+i;
		var p4="txtVence"+i;
		var p1ar="txtCantSol"+i;//ar
		var p5="txtCantidad"+i;
		var p2ar="cmbEMB"+i;//ar
		var p3ar="cmbEMP"+i;//ar
		var p4ar="cmbEMV"+i;//ar
		var p5ar="txtT"+i;//ar
		var p6ar="cmbENVP"+i;//ar
		var p7ar="cmbEMPS"+i;//ar
		var p8ar="cumple"+i;//ar
		var p9ar="dona"+i;//ar
		var p6="txtIva"+i;
		var p7="txtIvaH"+i;
		var p8="txtVunitario"+i;
		var p9="txtTotal"+i;
		
		var r0=document.getElementById(p0).value;
		var r1=document.getElementById(p1).value;
		
		var r00ar=document.getElementById(p0ar).selectedIndex;
		var r0ar=document.getElementById(p0ar).options[r00ar].value;
		
		var r2=document.getElementById(p2).value;
		var r3=document.getElementById(p3).value;
		var r4=document.getElementById(p4).value;
		var r1ar=document.getElementById(p1ar).value;
		var r5=document.getElementById(p5).value;
		
		var r22ar=document.getElementById(p2ar).selectedIndex;
		var r2ar=document.getElementById(p2ar).options[r22ar].value;
		
		var r33ar=document.getElementById(p3ar).selectedIndex;
		var r3ar=document.getElementById(p3ar).options[r33ar].value;
		
		var r44ar=document.getElementById(p4ar).selectedIndex;
		var r4ar=document.getElementById(p4ar).options[r44ar].value;
		
		var r5ar=document.getElementById(p5ar).value;
		if(r5ar==""){
			r5ar="N/A";
		}
		
		var r66ar=document.getElementById(p6ar).selectedIndex;
		var r6ar=document.getElementById(p6ar).options[r66ar].value;
		
		var r77ar=document.getElementById(p7ar).selectedIndex;
		var r7ar=document.getElementById(p7ar).options[r77ar].value;
		
		var r8ar=document.getElementById(p8ar);
		var r9ar=document.getElementById(p9ar);
		if (r8ar.checked){r8ar="1";}else{r8ar="0";}
		if (r9ar.checked){r9ar="1";}else{r9ar="0";}
		
		var r6=document.getElementById(p6).value;
		var r7=document.getElementById(p7).value;
		var r8=document.getElementById(p8).value;
		var r9=document.getElementById(p9).value;
		
		for(a=0;a<r0.length;a++){
			r0=r0.replace('%','@');
			//Resul=Resul.replace('ñ','@');				
		}
		
		//alert("r0ar: "+r0ar+" r1ar: "+r1ar+" r2ar: "+r2ar+" r3ar: "+r3ar+" r4ar: "+r4ar+" r5ar: "+r5ar+" r6ar: "+r6ar+" r7ar: "+r7ar+" r8ar: "+r8ar+" r9ar: "+r9ar);
		
		Ndatos2=Ndatos2+r0;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r1;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r2;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r3;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r4;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r5;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r6;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r7;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r8;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r9;Ndatos2=Ndatos2+"|";
		
		Ndatos2=Ndatos2+r0ar;Ndatos2=Ndatos2+"|";r0ar
		Ndatos2=Ndatos2+r1ar;Ndatos2=Ndatos2+"|";r1ar
		Ndatos2=Ndatos2+r2ar;Ndatos2=Ndatos2+"|";r2ar
		Ndatos2=Ndatos2+r3ar;Ndatos2=Ndatos2+"|";r3ar
		Ndatos2=Ndatos2+r4ar;Ndatos2=Ndatos2+"|";r4ar
		Ndatos2=Ndatos2+r5ar;Ndatos2=Ndatos2+"|";r5ar
		Ndatos2=Ndatos2+r6ar;Ndatos2=Ndatos2+"|";r6ar
		Ndatos2=Ndatos2+r7ar;Ndatos2=Ndatos2+"|";r7ar
		Ndatos2=Ndatos2+r8ar;Ndatos2=Ndatos2+"|";r8ar
		Ndatos2=Ndatos2+r9ar;Ndatos2=Ndatos2+"|";r9ar
		
	}
	var Ndatos=encodeURIComponent(Ndatos2)
	//alert("Datos actualizados: "+Ndatos);
	//////////////////////////////////	
		
	if((cont==0)&&(nE>36)){
		
	}else{
		
		if(cont==c){
		var cc0="txtTipoMe"+c;
		var cc00="txtIva"+c;
		var cc1="txtInvima"+c;
		var cc2="txtTipoMeH"+c;
		var cc3="txtLote"+c;
		var cc4="txtVence"+c;
		var cc5="txtCantidad"+c;
		var cc6="txtIvaH"+c;
		var cc7="txtVunitario"+c;
		var cc8="txtTotal"+c;
			

		
		
		if(c>0){
		var datos22="txtM"+c;
		}
		var contenid=document.getElementById('repetir');
		var a0=document.getElementById(cc0).value;
		
		for(a=0;a<a0.length;a++){
			a0=a0.replace('%','@');
			//Resul=Resul.replace('ñ','@');				
		}
		
		var a00=document.getElementById(cc00).value;
		var a1=document.getElementById(cc1).value;
		var a2=document.getElementById(cc2).value;
		var a3=document.getElementById(cc3).value;
		var a4=document.getElementById(cc4).value;
		var a5=document.getElementById(cc5).value;
		var a6=document.getElementById(cc6).value;
		var a7=document.getElementById(cc7).value;
		var a8=document.getElementById(cc8).value;
		if(c>0){
		var datos5=document.getElementById(datos22);
		var datos2=encodeURIComponent(datos5.value)
		//alert("primer datos2: "+datos2);
		}
		//alert(" a00: "+a00+" a0: "+a0+" a1: "+a1+" a2: "+a2+" a3: "+a3+" a4: "+a4+" a5: "+a5+" a6: "+a6+" a7: "+a7+" a8: "+a8+" datos2: "+datos2);
		
		
		
																									
		
		if((a1!="")&&(a2!="")&&(a3!="")&&(a4!="")&&(a5!="")&&(a6!="")&&(a7!="")&&(a8!="")&&(r0ar!="Seleccione")&&(r1ar!="")&&(r2ar!="0")&&(r3ar!="0")&&(r4ar!="0")&&(r6ar!="0")&&(r7ar!="0")){
				
	   	cont++;
	   	if(cont==7){alert("Ultimo movimiento de la transacci\xf3n")}
		ajax=getXMLObject();
		ajax.open("POST","ControlEntradas",true);
		ajax.onreadystatechange = function(){
			if(ajax.readyState == 4){
				var v= ajax.responseText;
				contenid.innerHTML =v;
				
				if(c==0){
					//alert(f.elements[16].value);
				f.elements[34].value="";}else{//antes era 16
				f.elements[34+(21*c)].value="";//antes era 16+(11*c)
				}
				
				var cc11=c+1;
				var cc10="txtTipoMe"+cc11;
				
				if(c<9){
				//alert(c);
				document.form1.document.getElementById(cc10).focus();
				document.getElementById(cc10).focus();
				}
			
			}
		}
		//ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
		
		ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
		//alert();
		ajax.send("valor=EntradasRepetidas&cont="+cont+"&a0="+a0+"&a2="+a2+"&a3="+a3+"&a1="+a1+"&a4="+a4+"&a5="+a5+"&a00="+a00+"&a6="+a6+"&a7="+a7+"&a8="+a8+"&datos2="+datos2+"&Ndatos="+Ndatos+"&r0ar="+r0ar+"&r1ar="+r1ar+"&r2ar="+r2ar+"&r3ar="+r3ar+"&r4ar="+r4ar+"&r5ar="+r5ar+"&r6ar="+r6ar+"&r7ar="+r7ar+"&r8ar="+r8ar+"&r9ar="+r9ar);//+"&vu="+vu+"&cant="+cant
		}else{alert("Debe digitar todos los campos para continuar");}
		}
		
	}
	}
	///////////////////////////
function Repetirs(cont,c,f){

	//alert("ccccc: "+c);
	
	var elementos=f.elements;
	var nE = elementos.length; 
	var contenid=document.getElementById('repetir');
	
	//////////////////////////////
	var Ndatos2="";
	for(i=0;i<=c;i++){
		
		var p0="txtTipoMe"+i;
		var p1="txtTipoMeH"+i;
		var p2="txtLote"+i;
		var p3="txtInvima"+i;
		var p4="txtVence"+i;
		var p5="txtCantidad"+i;
		var p6="txtVunitario"+i;
		var p7="txtProvee"+i;
		var p8="txtInv"+i;
		var p9="txtCa"+i;
		var r0=document.getElementById(p0).value;
		var r1=document.getElementById(p1).value;
		var r2=document.getElementById(p2).value;
		var r3=document.getElementById(p3).value;
		var r4=document.getElementById(p4).value;
		var r5=document.getElementById(p5).value;
		var r6=document.getElementById(p6).value;
		var r7=document.getElementById(p7).value;
		var r8=document.getElementById(p8).value;
		var r9=document.getElementById(p9).value;
		
		for(a=0;a<r0.length;a++){
			r0=r0.replace('%','@');
			//Resul=Resul.replace('ñ','@');				
		}
		
		Ndatos2=Ndatos2+r0;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r1;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r2;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r3;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r4;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r5;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r6;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r7;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r8;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r9;Ndatos2=Ndatos2+"|";
	}
	var Ndatos=encodeURIComponent(Ndatos2);
	///////////////////////////////////
	//alert("rrrr9: "+r9)
	//alert("cont: "+cont+" nE: "+nE);
	
	//alert("r5: "+r5+" r9: "+r9);
	var aa15=parseInt(r5);
	var aa16=parseInt(r9);
	if(aa15<aa16){
		alert("La cantidad no debe ser mayor que las existencias!!!");
		//document.form1.document.getElementById(cc9).focus();
		document.getElementById(p9).value="";
		document.getElementById(p9).focus();
	}
	
	
if((cont==0)&&(nE>17)){
	
}else{
	//alert("Entro al sino");
	
	
	if(cont==c){
	var cc0="txtTipoMe"+c;
	var cc1="txtTipoMeH"+c;
	var cc2="txtLote"+c;
	var cc3="txtInvima"+c;
	var cc4="txtVence"+c;
	var cc5="txtCantidad"+c;
	var cc6="txtVunitario"+c;
	var cc7="txtProvee"+c;
	var cc8="txtInv"+c;
	var cc9="txtCa"+c;
	if(c>0){
	var datos22="txtM"+c;
	}
	
	
	
	var a0=document.getElementById(cc0).value;
	for(a=0;a<a0.length;a++){
		a0=a0.replace('%','@');
	 }
	var a1=document.getElementById(cc1).value;
	var a2=document.getElementById(cc2).value;
	var a3=document.getElementById(cc3).value;
	var a4=document.getElementById(cc4).value;
	var a5=document.getElementById(cc5).value;
	var a6=document.getElementById(cc6).value;
	var a7=document.getElementById(cc7).value;
	var a8=document.getElementById(cc8).value;
	var a9=document.getElementById(cc9).value;
	if(c>0){
	var datos5=document.getElementById(datos22);
	var datos2=encodeURIComponent(datos5.value)
	}
	/////////////////////
	//alert("aaa999: "+a9);
		var emailfilterr=/^[0-9]*$/;  
		var returnval=emailfilterr.test(a9)
		if (returnval==false){
			//alert("El campo solo acepta valores numericos");
			document.getElementById(cc9).value = "";
			document.getElementById(cc9).focus();	
		}else{

	//////////////////////
	
	
	var a15=parseInt(a5);
	var a16=parseInt(a9);
	if(a15<a16){
		//alert("La cantidad no debe ser mayor que las existencias!!!");
		//document.form1.document.getElementById(cc9).focus();
		//document.getElementById(cc9).value="";
		//document.getElementById(cc9).focus();
	}else{
	cont++;
	if(cont==7){alert("Ultimo movimiento de la transacci\xf3n")}
	//alert("aaa0: "+a1);
	if((a1!="")&&(a2!="")&&(a3!="")&&(a4!="")&&(a5!="")&&(a6!="")&&(a7!="")&&(a8!="")&&(a9!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlSalidas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var v= ajax.responseText;
			contenid.innerHTML =v;
			
		/*	if(c==0){
			f.elements[16].value="";}else{
			f.elements[16+(11*c)].value="";
			}
		*/
			if(c<9){
				var cc11=c+1;
				var cc10="txtTipoMe"+cc11;
				//alert("el proximo es "+cc10);
				document.form1.document.getElementById(cc10).focus();
				document.getElementById(cc10).focus();
			}
		}
	}
	
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=EntradasRepetidas&cont="+cont+"&a0="+a0+"&a1="+a1+"&a2="+a2+"&a3="+a3+"&a4="+a4+"&a5="+a5+"&a6="+a6+"&a7="+a7+"&a8="+a8+"&a9="+a9+"&datos2="+datos2+"&Ndatos="+Ndatos);//+"&vu="+vu+"&cant="+cant
	}else{alert("Debe digitar todos los campos para continuar");}
	}
  }//fin else campo cantidad no es numerica
 }
}//si la cantidad es mayor q la s existencias
}

///////////////////////////////////////////

function Repetirt(cont,c,f){

	var elementos=f.elements;
	var nE = elementos.length; 
	var contenid=document.getElementById('repetir');

if((cont==0)&&(nE>17)){
	
}else{
	
	if(cont==c){
	var cc0="txtTipoMe"+c;
	var cc1="txtTipoMeH"+c;
	var cc2="txtLote"+c;
	var cc3="txtInvima"+c;
	var cc4="txtVence"+c;
	var cc5="txtCantidad"+c;
	var cc6="txtVunitario"+c;
	var cc7="txtProvee"+c;
	var cc8="txtInv"+c;
	var cc9="txtCa"+c;
	if(c>0){
	var datos22="txtM"+c;
	}
	
	
	
	var a0=document.getElementById(cc0).value;
	for(a=0;a<a0.length;a++){
		a0=a0.replace('%','@');
	 }
	var a1=document.getElementById(cc1).value;
	var a2=document.getElementById(cc2).value;
	var a3=document.getElementById(cc3).value;
	var a4=document.getElementById(cc4).value;
	var a5=document.getElementById(cc5).value;
	var a6=document.getElementById(cc6).value;
	var a7=document.getElementById(cc7).value;
	var a8=document.getElementById(cc8).value;
	var a9=document.getElementById(cc9).value;
	if(c>0){
	var datos5=document.getElementById(datos22);
	var datos2=encodeURIComponent(datos5.value)
	}
	/////////////////////
	
		var emailfilterr=/^[0-9]*$/;  
		var returnval=emailfilterr.test(a9)
		if (returnval==false){
			//alert("El campo solo acepta valores numericos");
			document.getElementById(cc9).value = "";
			document.getElementById(cc9).focus();	
		}else{

	//////////////////////
	
	
	var a15=parseInt(a5);
	var a16=parseInt(a9);
	if(a15<a16){
		alert("La cantidad no debe ser mayor que las existencias!!!");
		//document.form1.document.getElementById(cc9).focus();
		document.getElementById(cc9).value="";
		document.getElementById(cc9).focus();
	}else{
	cont++;
	if(cont==7){alert("Ultimo movimiento de la transacci\xf3n")}
	//alert("aaa0: "+a1);
	if((a1!="")&&(a2!="")&&(a3!="")&&(a4!="")&&(a5!="")&&(a6!="")&&(a7!="")&&(a8!="")&&(a9!="")){
	
	ajax=getXMLObject();
	ajax.open("POST","ControlTraslado",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var v= ajax.responseText;
			contenid.innerHTML =v;
			
		//	if(c==0){
		//	f.elements[16].value="";}else{
			//f.elements[16+(11*c)].value="";
		//	}
		
			if(c<9){
				var cc11=c+1;
				var cc10="txtTipoMe"+cc11;
				//alert("el proximo es "+cc10);
				document.form1.document.getElementById(cc10).focus();
				document.getElementById(cc10).focus();
			}
		}
	}
	
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=EntradasRepetidas&cont="+cont+"&a0="+a0+"&a1="+a1+"&a2="+a2+"&a3="+a3+"&a4="+a4+"&a5="+a5+"&a6="+a6+"&a7="+a7+"&a8="+a8+"&a9="+a9+"&datos2="+datos2);//+"&vu="+vu+"&cant="+cant
	}else{alert("Debe digitar todos los campos para continuar");}
	}
  }//fin else campo cantidad no es numerica
 }
}//si la cantidad es mayor q la s existencias
}


///////////////////////////////////////////


//cesrar  falta solo insertar las salidas y empezar trasladosss

function anterior(c){

	var cc0="txtTipoMe"+c;
	var cc1="txtTipoMeH"+c;
	var cc2="txtLote"+c;
	var cc3="txtInvima"+c;
	var cc4="txtVence"+c;
	var cc5="txtCantidad"+c;
	var cc6="txtVunitario"+c;
	var cc7="txtProvee"+c;
	var cc8="txtInv"+c;
	var cc9="txtCa"+c;
	var datos22="txtM"+c;
	
	var a8=document.getElementById(cc8).value;
	
	
	var datos5=document.getElementById(datos22);
	var datos2=encodeURIComponent(datos5.value);
	
	//recuerda a8 campo de inventario para no repetir
	//alert("El valor de c: "+c+ " el valor del campo "+a8);
	for(y=0; y<c; y++){
		var vs="txtInv"+y;
		var vss=document.getElementById(vs).value;
		if(vss==a8){
			alert("Solo puede generar 1 movimiento del mismo articulo!!!");
			document.getElementById(cc0).value="";
			document.getElementById(cc1).value="";
			document.getElementById(cc2).value="";
			document.getElementById(cc3).value="";
			document.getElementById(cc4).value="";
			document.getElementById(cc5).value="";
			document.getElementById(cc6).value="";
			document.getElementById(cc7).value="";
			document.getElementById(cc8).value="";
			document.getElementById(cc9).value="";
			document.getElementById(cc0).focus();
		}
		//alert("lo hice "+(y+1)+" vez");
	}	
}

///////////////////////////
function CrearInv(fo,ND){
	//alert("crear invetario");
	var elementos=fo.elements;
	var nE = elementos.length; 
	//alert("numero de elementos "+nE);
	
	var bodega=document.getElementById("cmbBodega").value;
	var proveedor=document.getElementById("cmbProveedor").value;
	var factura=document.getElementById("txtFactura").value;
	var fechas=document.getElementById("txtFecha").value;
	var concepto=document.getElementById("txtConcepto").value;
	var movimiento=document.getElementById("cmbTipoM").value;
	var user=document.getElementById("txtCodusuario").value;
	
	var orden=document.getElementById("txtOrden").value;
	var hora=document.getElementById("txtHora").value;
	var recibe=document.getElementById("txtRecibe").value;
	var entrega=document.getElementById("txtEntrego").value;
	var verifica=document.getElementById("txtVerifico").value;
	var observacion=document.getElementById("txtObservacion").value;
	var tentrega=document.getElementById("cmbTiemE").value;
	var tiempo=document.getElementById("txtTiempo").value;
	
	
	//alert("bod "+bodega+" provee "+proveedor+" fact "+factura+" fecha "+fecha+" concep "+concepto+" mov "+movimiento);
	
	if(bodega=="Seleccione"||proveedor=="Seleccione"||factura==""||concepto==""||movimiento=="Seleccione"||orden==""||hora==""||recibe==""||entrega==""||verifica==""||observacion==""||tentrega=="Seleccion"||tiempo==""){alert("Debe Ingresar los datos Principales");}else{
	if(nE==36){alert("Debe Ingresar al menos un movimiento")}
	else{
	var datos22="txtM1";
	//var datos2=document.getElementById(datos22);
	var datos2=ND;
	var datos223=encodeURIComponent(datos2);//.value);	
	var movi=null;
	//alert("Datos2: "+datos223);

	var contenido=document.getElementById('repetir');
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			movi=ajax.responseText;
			//contenido.innerHTML = movi;
			
		//	alert("viene del controlador: "+movi);
				alert("Ingreso Exitoso.");
				verReporte(movi);
				verActaRecepcion(movi);
				window.location.reload();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	//alert("valor=Ingreso&ing="+datos223+"&nE="+nE+"&bodega="+bodega+"&proveedor="+proveedor+"&factura="+factura+"&fechas="+fechas+"&concepto="+concepto+"&movimiento="+movimiento+"&user="+user+"&orden="+orden+"&hora="+hora+"&recibe="+recibe+"&entrega="+entrega+"&verifica="+verifica+"&observacion="+observacion+"&tentrega="+tentrega+"&tiempo="+tiempo);
	ajax.send("valor=Ingreso&ing="+datos223+"&nE="+nE+"&bodega="+bodega+"&proveedor="+proveedor+"&factura="+factura+"&fechas="+fechas+"&concepto="+concepto+"&movimiento="+movimiento+"&user="+user+"&orden="+orden+"&hora="+hora+"&recibe="+recibe+"&entrega="+entrega+"&verifica="+verifica+"&observacion="+observacion+"&tentrega="+tentrega+"&tiempo="+tiempo);
	}
  }
}

function verReporte (movi) {
	pagina="farc_ReporteEntradas1.jsp?movi="+movi;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}


function verActaRecepcion (movi) {
	//alert("ver acta de recpcion ");
	pagina="farc_ReporteActaRecepcion1.jsp?movi="+movi;			
   	var opciones2="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones2 =opciones2+"scrollbars=yes, resizable=yes, width=1000, height=570, top=75,  left=150";
   	window.open(pagina,"NUEVA2",opciones2);
}

function Repetir2NS(cont,c,f){
	var Ndatos2="";
	for(i=0;i<c;i++){
		
		var p0="txtTipoMe"+i;
		var p1="txtTipoMeH"+i;
		var p2="txtLote"+i;
		var p3="txtInvima"+i;
		var p4="txtVence"+i;
		var p5="txtCantidad"+i;
		var p6="txtVunitario"+i;
		var p7="txtProvee"+i;
		var p8="txtInv"+i;
		var p9="txtCa"+i;
		var r0=document.getElementById(p0).value;
		var r1=document.getElementById(p1).value;
		var r2=document.getElementById(p2).value;
		var r3=document.getElementById(p3).value;
		var r4=document.getElementById(p4).value;
		var r5=document.getElementById(p5).value;
		var r6=document.getElementById(p6).value;
		var r7=document.getElementById(p7).value;
		var r8=document.getElementById(p8).value;
		var r9=document.getElementById(p9).value;
		
		for(a=0;a<r0.length;a++){
			r0=r0.replace('%','@');
			//Resul=Resul.replace('ñ','@');				
		}
		
		Ndatos2=Ndatos2+r0;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r1;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r2;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r3;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r4;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r5;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r6;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r7;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r8;Ndatos2=Ndatos2+"|";
		Ndatos2=Ndatos2+r9;Ndatos2=Ndatos2+"|";
	}
	
	//alert("Ndatos Antes: "+Ndatos2);
	//var Ndatos=encodeURIComponent(Ndatos2);
	//alert("Ndatos despues: "+Ndatos);
	CrearSal(f,Ndatos2);
	
}

function CrearSal(fo,ND){
	// Repetirs(1,1,fo)
	var elementos=fo.elements;
	var nE = elementos.length; 
	//alert("numero de elementos "+nE);
	
	var bodega=document.getElementById("cmbBodega").value;
	//var proveedor=document.getElementById("cmbProveedor").value;
	var factura=document.getElementById("txtFactura").value;
	var fechas=document.getElementById("txtFecha").value;
	var concepto=document.getElementById("txtConcepto").value;
	var movimiento=document.getElementById("cmbTipoM").value;
	var user=document.getElementById("txtCodusuario").value;
	var movi=null;
	
	//alert("bod "+bodega+" provee "+proveedor+" fact "+factura+" fecha "+fecha+" concep "+concepto+" mov "+movimiento);
	//alert("Bodega "+bodega+" Factura "+factura+" Concepto "+concepto);
	if(bodega=="Seleccione"||factura==""||movimiento=="Seleccione"||concepto==""){alert("Debe Ingresar los datos Principales");}else{
	if(nE==16){alert("Debe Ingresar al menos un movimiento")}
	else{
	var datos22="txtM1";
	var datos5=document.getElementById(datos22);
	var datos2=ND;
	//var datos223=encodeURIComponent(datos2);
	var datos223=datos2;
	//alert("Datos2: "+datos223+"                   datos5: "+datos5.value);
	

	var contenido=document.getElementById('repetir');
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlSalidas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			movi=ajax.responseText;
			//contenido.innerHTML = ajax.responseText	
			alert("Ingreso exitoso!!!");
			verReportes(movi);
			window.location.reload();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=Ingreso&ing="+datos223+"&nE="+nE+"&bodega="+bodega+"&factura="+factura+"&fechas="+fechas+"&concepto="+concepto+"&movimiento="+movimiento+"&user="+user);
	}
	}
}


function verReportes (movi) {
	pagina="farc_ReporteSalidas.jsp?movi="+movi;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function CrearTras(fo){
	
	var elementos=fo.elements;
	var nE = elementos.length; 
	
	var bodegao=document.getElementById("cmbBodegao").value;
	var bodegad=document.getElementById("cmbBodegad").value;
	var factura=document.getElementById("txtFactura").value;
	var fechas=document.getElementById("txtFecha").value;
	var concepto=document.getElementById("txtConcepto").value;
	var user=document.getElementById("txtCodusuario").value;
	var movi=null;
	
	//alert("bod "+bodega+" provee "+proveedor+" fact "+factura+" fecha "+fecha+" concep "+concepto+" mov "+movimiento);
	//alert("Bodega "+bodega+" Factura "+factura+" Concepto "+concepto);
	if(bodegao=="Seleccione"||bodegad=="Seleccione"||factura==""||concepto==""){alert("Debe Ingresar los datos Principales");}else{
	if(nE==17){alert("Debe Ingresar al menos un movimiento")}
	else{
	var datos22="txtM1";
	var datos2=document.getElementById(datos22);
	var datos223=encodeURIComponent(datos2.value);	
	//alert("Datos2: "+datos223);

	var contenido=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlTraslado",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			movi=ajax.responseText;
			//contenido.innerHTML = ajax.responseText	
			alert("Ingreso exitoso!!!");
			verReportet(movi);
			window.location.reload();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=Ingreso&ing="+datos223+"&nE="+nE+"&bodegao="+bodegao+"&bodegad="+bodegad+"&factura="+factura+"&fechas="+fechas+"&concepto="+concepto+"&user="+user);
	//alert("valor=Ingreso&ing="+datos223+"&nE="+nE+"&bodegao="+bodegao+"&bodegad="+bodegad+"&factura="+factura+"&fechas="+fechas+"&concepto="+concepto+"&user="+user);
	}
  }
}

function verReportet (movi) {
	pagina="farc_ReporteTraslados.jsp?movi="+movi;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}


function enbodega(){
	var cc="cmbBodega";
	var e=document.getElementById(cc).value;
	if(e=="Seleccione"){
		alert("Debe selecionar una bodega para empezar!!!");
		document.getElementById(cc).focus();
	}
}

var emailfilter=/^[0-9]*$/;  
function checknum(c){
	var cc="txtCantidad"+c;
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


var emailfilterd=/^[0-9]*$/;  
function checknumsdisp(){
	var cc="txtcant";
	var e=document.getElementById(cc).value;
	var returnval=emailfilterd.test(e)
	
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

var emailfilterr=/^[0-9]*$/;  
function checknum3(c){
	var cc="txtCa"+c;
	var e=document.getElementById(cc).value;
	var returnval=emailfilterr.test(e)
	
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

function checknum2(c){
	var cc="txtVunitario"+c;
	var cd="txtTotal"+c;
	var e=document.getElementById(cc).value;
	var returnval=emailfilter.test(e)
	if (returnval==false){
		alert("El campo solo acepta valores n\xdamericos");
		document.getElementById(cc).value = "";
		document.getElementById(cd).value = "";
		document.getElementById(cc).focus();	
	}
	return returnval
}

var filtercara=/[^a-zA-Z0-9]/;    ///       /^[0-9]*$/; 
function checkcara(c){
	var cc="txtLote"+c;
	var e=document.getElementById(cc).value;
	var returnval=filtercara.test(e)
	if (returnval==true){
		alert("El campo no acepta caracteres especiales");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}


var filtercaras=/[^a-zA-Z0-9]/;    ///       /^[0-9]*$/; 
function checkcaras(c){
	var cc="txtInvima"+c;
	var e=document.getElementById(cc).value;
	var returnval=filtercaras.test(e)
	if (returnval==true){
		alert("El campo no acepta caracteres especiales");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}


var emailfilters=/^[0-9]*$/;  
function checknums(){
	var e=document.getElementById("txtDocumento").value;
	var returnval=emailfilters.test(e)
	if (returnval==false){
		alert("El campo solo acepta valores n\xfamericos");
		document.getElementById("txtDocumento").value = "";
		document.getElementById("txtDocumento").focus();	
	}
return returnval
}

function vbodega(){
	var cc="cmbBodegao";
	var e=document.getElementById(cc).value;
	if(e=="Seleccione"){
		alert("Debe selecionar una bodega de origen para empezar!!!");
		document.getElementById(cc).focus();
	}
}


var emailfiltersdevo=/^[0-9]*$/;  
function checknumsdevo(){
	var e=document.getElementById("txtDevolver").value;
	var returnval=emailfiltersdevo.test(e)
	
	var x=e.substr(0,1);
	if(x==0){
	var y=e.substr(1);
	document.getElementById("txtDevolver").value = y;
	}
	
	if (returnval==false){
		alert("El campo solo acepta valores n\xfamericos");
		document.getElementById("txtDevolver").value = "";
		document.getElementById("txtDevolver").focus();	
	}
return returnval
}

function vbodegad(){
	var user=document.getElementById("txtCodusuario").value;
	var contenido=document.getElementById('contenido');
	var c="cmbBodegao";
	var f=document.getElementById(c).value;
	//alert("Entramos y este es f: "+f);
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlTraslado",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			//window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Traslados2&bo="+f+"&user="+user);//"valor=Traslados");
}

function entregar(formula,sw1,adm,cp){
	var contenidos=document.getElementById('contenido');
	var conte=document.getElementById('disp');
	document.getElementById('contenido').innerHTML='<div align="center"><p>Cargando Resultados...</p><img src="Imagenes/load.gif"></div>';

	//var sw=document.getElementById('txtsw').value;
	//alert("la formula es "+formula);
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenidos.innerHTML = ajax.responseText	
			conte.innerHTML ="";
			//mostrarb(formula,sw1);	
			devolver();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Medicamentos1&formula="+formula+"&sw1="+sw1+"&admp="+adm+"&cp="+cp);
}




function devolver(){
	
	//var contenido=document.getElementById('formul');
	var conte=document.getElementById('formul');
	//alert("sw1mostrarb "+sw);
	//alert("la formula es "+formula);
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			conte.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Mostrarbd");
}

function Movi(formula,sw1,movi,adm,cp){
	//var contenido=document.getElementById('formul');
	var contenidos=document.getElementById('contenido');
	var movis=document.getElementById('cmbMovi').value;
	document.getElementById('contenido').innerHTML='<div align="center"><p>Cargando Resultados...</p><img src="Imagenes/load.gif"></div>';

	//alert("movis"+movis);
	//var conte=document.getElementById('disp');
	//var sw=document.getElementById('txtsw').value;
	//alert("la formula es "+formula);
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenidos.innerHTML = ajax.responseText	
			mostrarb(formula,sw1,adm,cp);	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Medicamentos&formula="+formula+"&sw1="+sw1+"&movi="+movis+"&admp="+adm+"&cp="+cp);
}


function mostrarb(formula,sw1,adm,cp){
	//var contenido=document.getElementById('formul');
	var conte=document.getElementById('formul');
	var sw=document.getElementById('txtsw').value;
	//alert(" mostrarbsw: "+sw);
	//alert("sw1mostrarb "+sw);
	//alert("la formula es "+formula);
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			conte.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Mostrarb&formula="+formula+"&sw1="+sw+"&admp="+adm+"&cp="+cp);
}

function Dispensa(codm,formula,cf,detalle,diss,sw1,adm,cp){
	//alert("Dispensa "+adm);
	//alert("cod medica"+codm);
	var contenidos=document.getElementById('contenido');
	var contenido=document.getElementById('disp');
	var user=document.getElementById("txtCodusuario").value;
	//var datos=document.getElementById('txtM').value;
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Dispensacion&codm="+codm+"&formula="+formula+"&cf="+cf+"&detalle="+detalle+"&diss="+diss+"&sw1="+sw1+"&admp="+adm+"&cp="+cp+"&usu="+user);
}

function Lotes(codm,formula,cf,detalle,diss,sw1,adm,cp){
	//alert("cesar es un teso");
	//alert("Entramos a lotes");
	//alert("cont lotes"+cont);
	//var datos=document.getElementById('txtM').value;
	var contenido=document.getElementById('disp');
	var bodega=document.getElementById("cmbBodega").value;
	//alert("La bodega es: "+bodega);
	//alert("El cod del med es: "+codm);
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Dispensacion2&codm="+codm+"&bode="+bodega+"&formula="+formula+"&cf="+cf+"&detalle="+detalle+"&diss="+diss+"&sw1="+sw1+"&admp="+adm+"&cp="+cp);
}



function ValidaBodega(codm,formula,cf,detalle,diss,sw1,adm,cp){
	var bodega=document.getElementById("cmbBodega").value;
	var usu=document.getElementById('txtCodusuario').value;
	//alert(codm+" - "+formula+" - "+cf+" - "+detalle+" - "+diss+" - "+sw1+" - "+adm+" - "+cp+" y bodega"+bodega+" y usuario: "+usu);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			var x = ajax.responseText;
			if(x==0){
				alert("No esta autorizado para realizar operaciones sobre esta bodega");
				document.getElementById("cmbLotes").value="Seleccione";
				document.getElementById("cmbLotes").text="Seleccione";
			}else{
			
			cantidades(codm,formula,cf,detalle,diss,sw1,adm,cp);
			}
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ValidaBodegaC&bode="+bodega+"&usu="+usu);
}


function cantidades(codm,formula,cf,detalle,diss,sw1,adm,cp){
	//	var datos=document.getElementById('txtM').value;
	var contenido=document.getElementById('disp');
	var bodega=document.getElementById("cmbBodega").value;
	var inv=document.getElementById("cmbLotes").value;
	//alert(codm+" "+bodega+"  "+inv);
	//
	//alert("cont cantidades"+cont);
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText;
			document.getElementById("txtcant").focus();
			document.getElementById("txtcant").focus();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Dispensacion3&codm="+codm+"&bode="+bodega+"&inv="+inv+"&formula="+formula+"&cf="+cf+"&detalle="+detalle+"&diss="+diss+"&sw1="+sw1+"&admp="+adm+"&cp="+cp);
}


function Enviar(){
		alert("Debe Seleccionar primero el Medicamento");
		document.getElementById("cmbLotes").focus();
}


function validamed(){
	var contenido=document.getElementById('disp');
	var inventario=document.getElementById("cmbLotes").value;
	if(inventario=="Seleccione"){
		alert("Debe Seleccionar primero el Medicamento");
		document.getElementById("cmbLotes").focus();
	}
}



function exis(c, cf){
	var contenido=document.getElementById('disp');
	var ca=document.getElementById("txtcant").value;
	if(c<ca){
	alert("La Cantidad a dispensar supera las existencias");
	document.getElementById("txtcant").value="";
	document.getElementById("cmbLotes").focus();
	document.getElementById("txtcant").focus();
	}
	if(cf<ca){
		alert("La Cantidad a dispensar supera las formuladas");
		document.getElementById("txtcant").value="";
		document.getElementById("cmbLotes").focus();
		document.getElementById("txtcant").focus();
	}
}

function Dispensar(inv,codm,formula,cf,detalle,diss,sw1,adm,cp){
	//alert("Esto es dispensar: "+cp);
	//var datos=document.getElementById('txtM').value;
	//var contenidoss=document.getElementById('disp');
	
	var contenido=document.getElementById('formul');
	var ca=document.getElementById("txtcant").value;
	var movis=document.getElementById('cmbMovi').value;
	var bode=document.getElementById('cmbBodega').value;
	var usu=document.getElementById('txtCodusuario').value;
	var disca=parseInt(diss)+parseInt(ca)
	if(ca==""){
		alert("Debe seleccionar la cantidad a dispensar!!!");
		}else{
	if((disca)>cf){
	alert("Dispensar "+ca+" Sobrepasa la cantidad pendiente!!!");
	}else{
	//alert("Nos vamos a dispensar");
		document.getElementById('disp').innerHTML='<div align="center"><p>Cargando Resultados...</p><img src="Imagenes/load.gif"></div>';

	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			Dispensars(formula,sw1,adm,cp);
			//contenidos.innerHTML = ""	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Actualizarf&formula="+formula+"&ca="+ca+"&inv="+inv+"&cf="+cf+"&detalle="+detalle+"&&diss="+diss+"&sw1="+sw1+"&movi="+movis+"&bode="+bode+"&usu="+usu+"&admp="+adm+"&cp="+cp+"&codm="+codm);
}
}
}

function Dispensars(formula,sw1,adm,cp){
	//alert("ultimo "+adm);
	var contenido=document.getElementById('contenido');
	var contenidos=document.getElementById('disp');
	var movis=document.getElementById('cmbMovi').value;
	//alert("cont dispensarrrr++");
	//alert(formula+"--"+sw1+"--"+movis+"--");
	//alert("El cod del med es: "+codm);
	//var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			contenidos.innerHTML = ""	
		    mostrarb(formula,sw1,adm,cp);	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Medicamentos&formula="+formula+"&sw1="+sw1+"&movi="+movis+"&admp="+adm+"&cp="+cp);
}

function DispensarMov(formula, adm,cp){
	//var contenido=document.getElementById('contenido');
	var contenidos=document.getElementById('formul');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenidos.innerHTML = ajax.responseText	
			movi=ajax.responseText;
			//alert("miren esto "+movi);
			verReporteDM(movi);
			//window.location.reload();
			Formulas();
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Movimientos&formula="+formula+"&admp="+adm+"&cp="+cp);
}

function verReporteDM (movi) {
	pagina="farc_ReporteDispensa.jsp?movi="+movi;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function Noexiste(cod,formula,sw1,movi){
	//var contenido=document.getElementById('contenido');
	//alert(cod);
	var contenidos=document.getElementById('formul');
	var contenido=document.getElementById('disp');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenidos.innerHTML = ajax.responseText
			contenido.innerHTML = ""
			Movi(formula,sw1,movi);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Eliminare&Ne="+cod);
}

function Nodispensa(cod,formula,sw1,movi){
	//var contenido=document.getElementById('contenido');
	//alert(cod);
	var contenidos=document.getElementById('formul');
	var contenido=document.getElementById('disp');
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenidos.innerHTML = ajax.responseText
			contenido.innerHTML = ""
			Movi(formula,sw1,movi);
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Eliminaren&Ne="+cod);
}


function BuscarP(){
	var contenido=document.getElementById('Admisiones');
	var D=document.getElementById("cmbD").value;
	var N=document.getElementById("txtDocumento").value;
	//alert(D);
	//alert(N);
	//alert("El cod del med es: "+codm);
	//var contenid=document.getElementById('repetir');
	document.getElementById('Admisiones').innerHTML='<div align="center"><p>Cargando Resultados...</p><img src="Imagenes/load.gif"></div>';
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Devolucion2&D="+D+"&N="+N);
}

function medica(formula,codpa){
	var contenido=document.getElementById('Medicas');
	document.getElementById('Medicas').innerHTML='<div align="center"><p>Cargando Resultados...</p><img src="Imagenes/load.gif"></div>';
	ajax=getXMLObject();
	//alert(formula);
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Devomed&formula="+formula+"&codpa="+codpa);
}


function devolmed(cdf,cfo,cdisp,codpa){
	//alert("Entramos devolmed cdisp: "+cdisp);
	var contenido=document.getElementById('Medicas');
	//alert("Entramos esta es x: "+x);
	document.getElementById('Medicas').innerHTML='<div align="center"><p>Cargando Resultados...</p><img src="Imagenes/load.gif"></div>';
	
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Mostrarmed&cdf="+cdf+"&cfo="+cfo+"&cdisp="+cdisp+"&codpa="+codpa);

}

function devolmedica(cdf,inv,cfo,cdisp,codpa,codmovdis){
	//alert("cdf:"+cdf+"  inv: "+inv+"  cfo: "+cfo+"  cdisp: "+cdisp+"  codpa: "+codpa+"  codmovdis: "+codmovdis);
	var contenido=document.getElementById('Medicas');
	//alert("Entramos el inventario : "+inv);
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
		contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Mostrarmed2&cdf="+cdf+"&inv="+inv+"&cfo="+cfo+"&cdisp="+cdisp+"&codpa="+codpa+"&codmovdis="+codmovdis);

}

function Dispensada(){
	alert("EL medicamento fue devuelto en su totalidad");
}

function devol(cdisp,invd, cdevueltas, dispen, cdf, cfo,codpa,codmovdis){
	//alert("codmovdis: "+codmovdis);
	var contenido=document.getElementById('Medicas');
	var cantd=document.getElementById('txtDevolver').value;
	var usu=document.getElementById('txtCodusuario').value;
	
	var teri = document.getElementById("con").selectedIndex;
	var tert = document.getElementById("con").options[teri].text;	
	var concep = document.getElementById("con").options[teri].value;	
	
	if((cantd=="")||(cantd=="0")){
		alert("Debe digitar la cantidad a devolver");
	}else{
	var cantidades=parseInt(cantd)+parseInt(cdevueltas);
	if(cantidades>dispen){	
	alert("La cantidad maxima a devolver de ese lote es "+dispen);	
	}else{	
		if(concep=="Seleccione"){alert("Debe seleccionar una causa de devolucion de medicamento");}
		else{
		
	ajax=getXMLObject();
	ajax.open("POST","ControlFormulas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//var hid=document.getElementById('txtHid').value;
			
			movi=ajax.responseText;
			//contenido.innerHTML = ajax.responseText	
			alert("Devoluci\xf3n Exitosa!!!");
			//window.location.reload();
			ReporteDev(movi);
			//alert("aqui se pierde: "+codpa);
			BuscarP();
			//devolmed(cdf,cfo,cdisp,codpa);
			
		}
	}
	ajax.setRequestHeader('Content-Type','application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Devototal&cdisp="+cdisp+"&cantd="+cantd+"&usu="+usu+"&invd="+invd+"&concep="+concep+"&codpa="+codpa+"&codmovdis="+codmovdis);
}
}
}
}



function ReporteDev(movi) {
	pagina="farc_ReporteDevolucion.jsp?movi="+movi;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}



function ConsultarEA(){
	
	//var elementos=fo.elements;
	//var nE = elementos.length; 
	//alert("numero de elementos "+nE);
	
	var nmov=document.getElementById("txtMov").value;
	var fechas=document.getElementById("txtVence0").value;
	var documento=document.getElementById("txtDoc").value;
	var R=document.getElementById("txtR").value;
	var tmov=document.getElementById("cmbTipoM").value;
	
	
	//alert("nmov "+nmov+" fechas "+fechas+" documento "+documento+" R "+R+" tmov "+tmov);
	var contenido=document.getElementById('mova');
	if(nmov=="" && fechas=="" && documento=="" && R=="" && tmov=="Seleccione"){alert("Debe Ingresar al menos un Criterio de Busqueda"); contenido.innerHTML =""; }else{
    
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=AnularE2&nmov="+nmov+"&fechas="+fechas+"&documento="+documento+"&R="+R+"&tmov="+tmov);
	
  }
}

function veranular(movi){
verReporte(movi);
verActaRecepcion(movi);
}

function anular(nmov){
	
	var contenido=document.getElementById('mova');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//var sw=document.getElementById("txtSw").value;
			//alert(sw);
			contenido.innerHTML = ajax.responseText	
			anular2(nmov)
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=AnularE3&nmov="+nmov);
}

function anular2(nmov){
	
	var contenido=document.getElementById('mova');
	var sw=document.getElementById("txtSw").value;
	if(sw=="1"){alert("No se puede anular!!! Ya existen movimientos");
	ConsultarEA();
	}else{
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=AnularE4&nmov="+nmov+"&sw="+sw);
	}
}


function AnularEA(nmov,tmov){
	//alert("estas seguro que deseas anular!!!");
	var contenido=document.getElementById('mova');
	var user=document.getElementById("txtCodusuario").value;
	var canul=document.getElementById("txtCanul").value;
	//alert(canul);
	if(canul==""){alert("Debe digitar el concepto de Anulaci\xf3n!!!");}
	else{
	//alert(user);
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			alert("Anulaci\xf3n Exitosa!!!");
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=AnularE5&nmov="+nmov+"&user="+user+"&tmov="+tmov+"&canul="+canul);
	}
}




function Radioh(){
	var contenido = document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=1h");
}







function RadiohA(op,venc,lot,enca) {
	// alert(venc);
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	ajax.open("POST", "ControlMovimientos", true); // getname will be the
	// servlet name
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			document.getElementById('desc0').focus();
			if(op=="0"){
				ajax1 = getXMLObject();
				ajax1.open("POST", "ControlMovimientos", true); // getname will
																// be the
				ajax1.onreadystatechange = function() {
					if (ajax1.readyState == 4) {
						var datos = ajax1.responseText;
						var detDatos = datos.split("|");
						document.getElementById("desch0").value = detDatos[2];
						document.getElementById("desc0").value = detDatos[1];
						document.getElementById("epsh0")[0].text = detDatos[0];
						document.getElementById("adm0").value = venc;
						document.getElementById("epsh0")[0].value = lot;
						fepssh(lot,detDatos[0],venc,enca,"1")
						//Detalleh(venc, lot);
					}
				};
				ajax1.setRequestHeader('Content-Type',
						'application/x-www-form-urlencoded;charset=utf-8');
				ajax1.send("valor=dtPac&enca="+enca);
				
			}
		}
	};
	ajax.setRequestHeader('Content-Type',
			'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=1hA"); // Posting txtname to Servlet
}



function Detalleh(venc,lot){
	//alert(venc+"-"+lot);
	var contenido = document.getElementById('resul2');
	var contenidos = document.getElementById('ttoo');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			//contenidos.innerHTML = "cesararasdtafstfaysgyagshuasuas";
			Detalleh2(venc,lot);
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=2h&cont="+venc);

}

function Detalleh2(venc,lot){
	//alert(venc+"-"+lot);
	//var contenido = document.getElementById('resul2');
	var contenidos = document.getElementById('resul');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenidos.innerHTML = ajax.responseText	
			Detalleh3(venc,lot);
			//contenidos.innerHTML = "cesararasdtafstfaysgyagshuasuas";
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=3h&cont="+venc);

}

function Detalleh3(venc,lot){
	//alert(venc+"-"+lot);
	//var contenido = document.getElementById('resul2');
	var contenidos = document.getElementById('resulcrea');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenidos.innerHTML = ajax.responseText	
			//contenidos.innerHTML = "cesararasdtafstfaysgyagshuasuas";
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=4h&cont="+venc);

}


function estaopac(e,venc){
	
	//var contenido = document.getElementById('resul2');
	var i = document.getElementById("cmbE"+e).selectedIndex;
	var t = document.getElementById("cmbE"+e).options[i].text;	
	var v = document.getElementById("cmbE"+e).options[i].value;	
	var x;
	if(t=="A"){x=0;}
	if(t=="C"){x=1;}
	if(t=="S"){x=2;}
	//alert(v+" - "+t);
	
	//var contenidos = document.getElementById('resulcrea');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			//contenidos.innerHTML = ajax.responseText	
			//contenidos.innerHTML = "cesararasdtafstfaysgyagshuasuas";
			Detalleh2(venc);
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=5h&cont="+venc+"&v="+v+"&x="+x);

}

function Intervencion(venc){
		
	var v = document.getElementById("observacion").value;	
	//var contenidos = document.getElementById('resulcrea');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			
			alert(ajax.responseText);	
			//Detalleh2(venc);
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=6h&cont="+venc+"&v="+v);

}



function Radiox(cood,a){
	var contenido = document.getElementById('Opcion');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			
			var op = document.getElementById('op').value;
						
			if(op==1){
				var opp = document.getElementById('opp').value;
				//alert(opp);
			Radiox2(opp,cood,a);
			}
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=1x");
}


function Radiox2(opp,cood,a){
	var contenido = document.getElementById('detalle');
document.getElementById('detalle').innerHTML='<div align="center"><p>Cargando Orden de Produccion...</p><img src="Imagenes/load.gif"></div>';
	

	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText	
			Radiox3(opp,cood,a);
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=2x");
}

function Radiox3(opp,codd,a){
	var contenido = document.getElementById('aprobar');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			contenido.innerHTML = ajax.responseText;
			if(a=="1"){Avf(codd);}
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=3x&opp="+opp);
}

function volumen(codd,vdd){
//	var contenido = document.getElementById('aprobar');
	var cmbVF = document.getElementById('cmbVF'+codd).value;
	
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=5x&cmbVF="+cmbVF+"&codd="+codd+"&vdd="+vdd);
}

function VerificarO(opp){
	var contenido = document.getElementById('aprobar');
	var resp = document.getElementById('responsable').value;
	var aux = document.getElementById('auxiliar').value;
	var user = document.getElementById('txtCodusuario').value;
	
	if(resp==""){alert("Debe digitar el responsable de la produccion");
	}else{
		if(aux==""){alert("Debe digitar el auxiliar de produccion");
		}else{
			document.getElementById('aprobar').innerHTML="";
			document.getElementById('detalle').innerHTML='<div align="center"><p>Finalizando Orden de Produccion...</p><img src="Imagenes/load.gif"></div>';
			
			ajax=getXMLObject();
			ajax.open("POST","ControlEntradas",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					alert("Verificacion de Orden Exitosa!!!");
					//LLAMAR A REPORTE YULIIIIIII
					ReporteOrdenP(opp);
					ReporteEtiquetas(opp);//Etiquetas de produccion
					Radiox();
				}
			}
			ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
			ajax.send("valor=4x&resp="+resp+"&aux="+aux+"&opp="+opp+"&user="+user);
		}
	}
}


function ReporteOrdenP(codop) {
	pagina="farc_ReporteOrdenProduccion.jsp?codop="+codop;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}

function ReporteEtiquetas(codop) {
	pagina2="farc_ReporteEtiquetas.jsp?codop="+codop;			
   	var opciones2="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones2 =opciones2+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina2,"NUEVA2",opciones2);
}


function EliminarMed(coddetalle, med, pac){
	var user = document.getElementById('txtCodusuario').value;
	if (confirm("Desea eliminar "+med+" del paciente "+pac)) {
		var ca = prompt("Digite la causa de la anulacion: ","");
		//alert(ca);
		if(ca!=null){
		var can=encodeURIComponent(ca);	
		if(can==""){
			alert("Debe digitar una causa valida para poder anular el medicamento");
		}else{
			
			ajax=getXMLObject();
			ajax.open("POST","ControlEntradas",true);
			ajax.onreadystatechange = function(){
				if(ajax.readyState == 4){
					Radiox();
					
				}
			}
			ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
			ajax.send("valor=6x&coddetalle="+coddetalle+"&can="+can+"&user="+user);
			
		}
		}
	}
}



function compare_dates(cons,fecha1, yDay,yMonth,yYear)   
{   
	//alert(cons+" - "+fecha.value+" - "+yDay+"/"+yMonth+"/"+yYear);
   var fecha=fecha1.value
   var xMonth=fecha.substring(3, 5);   
   var xDay=fecha.substring(0, 2);   
   var xYear=fecha.substring(6,10);   
   
   if (xYear == yYear){
	   if (xMonth == yMonth){
		   alert("Se sugiere adquirir medicamentos con fecha de vencimiento superior a 6 meses");
	   }else{
		   if (xMonth > yMonth){
			   var dif=xMonth-yMonth;
			   if(dif<7){
				   alert("Se sugiere adquirir medicamentos con fecha de vencimiento superior a 6 meses");
			   }
		   }
	   }
   }
   if (xYear > yYear){
	   var dify=xYear-yYear;
	   if(dify>5){
		   alert("Verifique la fecha de vencimiento no es usual medicamentos con fechas de vencimiento mayor a 5 años");
	   }
   }
}  




function CDosis(codd){

	
	var vdd = document.getElementById('d'+codd).value;
	//alert(vdd+"del codigo"+codd);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			Radiox(codd,1);
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=7x&codd="+codd+"&vdd="+vdd);
	/**/
}




function Avf(codd){

	
	var VF = document.getElementById('cmbVF'+codd).selectedIndex;
	var cmbVF = document.getElementById('cmbVF'+codd).options[VF].text;
	//alert(cmbVF);
	//alert(vdd+"del codigo"+codd);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			//Radiox();
			//Avf(codd);
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=8x&cmbVF="+cmbVF+"&codd="+codd);
	/**/
}

function CambEst(codd){

	
	//var VF = document.getElementById('cmbVF'+codd).selectedIndex;
	//var cmbVF = document.getElementById('cmbVF'+codd).options[VF].text;
	//alert(codd);
	//alert(vdd+"del codigo"+codd);
	
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			//contenido.innerHTML = ajax.responseText	
			Radiox(codd,1);
			//Avf(codd);
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	ajax.send("valor=9x&codd="+codd);
	/**/
}