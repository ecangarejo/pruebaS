function getXMLObject() // XML OBJECT
{
	var xmlHttp = false;
	try {
		xmlHttp = new ActiveXObject("Msxml2.XMLHTTP") // For Old Microsoft
		// Browsers
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP") // For Microsoft
			// IE 6.0+
		} catch (e2) {
			xmlHttp = false // No Browser accepts the XMLHTTP Object then false
		}
	}
	if (!xmlHttp && typeof XMLHttpRequest != 'undefined') {
		xmlHttp = new XMLHttpRequest(); // For Mozilla, Opera Browsers
	}
	return xmlHttp; // Mandatory Statement returning the ajax object created
}
var xmlhttp = new getXMLObject(); // xmlhttp holds the ajax object



function limpiacta(idiv) {//Limpia el div de cta si no la selecciona
	 document.getElementById("dcta"+idiv).innerHTML = "";
     document.getElementById("dcta"+idiv).style.display = "none";
}

function limpiater(idiv) {//Limpia el div de cta si no la selecciona
	 document.getElementById("dter"+idiv).innerHTML = "";
     document.getElementById("dter"+idiv).style.display = "none";
}


////
function checkart(c){
	var ccH="hcta"+c;
	var cc="cta"+c;
	var su="dcta"+c;
	var e=document.getElementById(ccH).value;
	/*alert("ESTO ES LO Q PASA DEBE SER CERO!!! "+c);
	alert("ESTO debe ser CC!!! "+ccH);*/
	if(e==""){
		alert("Debe seleccionar un elemento de la lista");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	//document.getElementById(su).style.display = "none";
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

function checkiva(c){
	var ccH="hter"+c;
	var cc="ter"+c;
	var su="dter"+c;
	var e=document.getElementById(ccH).value;
	if(e==""){
		alert("Debe seleccionar un elemento de la lista");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();
	}
	//document.getElementById(su).style.display = "none";
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

var filtercara=/[^a-zA-Z0-9]/;    ///       /^[0-9]*$/; 
function checkcara(c){
	var cc="lote"+c;
	var e=document.getElementById(cc).value;
	var returnval=filtercara.test(e)
	if (returnval==true){
		alert("El campo no acepta caracteres especiales");
		document.getElementById(cc).value = "";
		document.getElementById(cc).focus();	
	}
return returnval
}

////



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


function Repetir(c){
	var emailfilter=/^[0-9]*$/;  
	var cc="txtCantidad"+c;
	var cc1="txtVunitario"+c;
	var cc2="ter"+c;
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

function compare_dates(cons,fecha1, yDay,yMonth,yYear){   
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




function Causa() {
	
	ajax = getXMLObject();
	var contenido = document.getElementById('Opcion');
	var user1=document.getElementById("txtCodusuario").value;
	ajax.open("POST", "ControlEntradas1", true);
	
	ajax.onreadystatechange = function() {
		if (ajax.readyState == 4) {
			contenido.innerHTML = ajax.responseText
			
			
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=50&user1="+user1); 
	
}




function referencia(x){
	
	document.getElementById('doc0').value=x.value;
}

var item=1;
var switem=0;
function newfile(p){
	
	if((p!=1)&&(switem==0)){
		item=p;
		switem=1;
	}

	
	
	var table = document.getElementById("asiento");
	var rowCount = table.rows.length;
	
	var row = table.insertRow(rowCount-2);
    
    row.setAttribute("id", "tr"+item);
    row.setAttribute("name", "tri");

    var cell0 = row.insertCell(0);
    var element0 = document.createElement("label");
    element0.setAttribute("id", "it"+item);
    element0.value=item;
   // element0.innerHTML =item;
    document.getElementById('lit').innerHTML=item+1;
    
   // if (element0.attachEvent) {element0.attachEvent ("onkeyup", function () {Eliminalinea(0)});}
   // if (element0.addEventListener) {element0.addEventListener("onkeyup", Eliminalinea(1), false);}

    element0.onclick = function() {Eliminalinea(this,item)};
    element0.innerHTML = item;
    cell0.appendChild(element0);
    

    var cell1 = row.insertCell(1);//Campo 
    var element1 = document.createElement("input");
    element1.type = "text";
    element1.setAttribute("id", "cta"+item);
    element1.setAttribute("name", "cta");
    element1.style.width='100%';
    element1.value=document.getElementById('cta0').value;
    element1.title=document.getElementById('cta0').title;
    document.getElementById('cta0').value="";
    document.getElementById('cta0').title="";
    //var x="dcta"+item;
   //  element1.onkeyup = function() {Divdectaytercero(this,51,2,1,0,"")};// Onkeyup='Completa(this,dcta,event,&quot;ControlDocumentos&quot;,&quot;51&quot;,2,1)'    
    element1.onkeyup = function(event) {
    	Divdectaytercero(this,51,2,1,0,event);
    }
 
    cell1.appendChild(element1);
         
    // var cell1 = row.insertCell(1);//Campo de cuenta hidden
    var element01 = document.createElement("input");
    element01.type = "text";
    element01.setAttribute("id", "hcta"+item);
    element01.setAttribute("name", "hcta");
   // element01.setAttribute("hidden", true);   
    element01.style.display= "none";
    element01.style.width='50%';
    element01.value=document.getElementById('hcta0').value;
    document.getElementById('hcta0').value="";
    cell1.appendChild(element01);
    
    /*
    var cell2 = row.insertCell(2);
    var element2 = document.createElement("input");
    element2.type = "text";
    element2.setAttribute("id", "lab"+item);
    element2.setAttribute("name", "lab");
    element2.style.width='100%';
    element2.title = "lab"+item;
    element2.value=document.getElementById('lab0').value;
    document.getElementById('lab0').value="";
    cell2.appendChild(element2);
    */
    
    var cell2 = row.insertCell(2);
    var element2 = document.createElement("select");
    element2 = document.getElementById("lab0").cloneNode(true);
    document.getElementById('lab0').parentNode.insertBefore(element2,document.getElementById('lab0'));	
    element2.setAttribute("id", "lab"+item);
    element2.setAttribute("name", "lab");
    element2.style.width='100%';
    element2.title = "lab"+item;
    element2.value=document.getElementById('lab0').value;
    document.getElementById('lab0').value="";
    cell2.appendChild(element2); 
    
    
    var cell3 = row.insertCell(3);
    var element3 = document.createElement("input");
    element3.type = "text";
    element3.setAttribute("id", "lote"+item);
    element3.setAttribute("name", "lote");
    element3.style.width='100%';
    element3.title = "lote"+item;
    element3.value=document.getElementById('lote0').value;
    document.getElementById('lote0').value="";
    cell3.appendChild(element3);
    
    
    var cell4 = row.insertCell(4);
    var element4 = document.createElement("input");
    element4.type = "text";
    element4.setAttribute("id", "txtInvima"+item);
    element4.setAttribute("name", "txtInvima");
    element4.style.width='100%';
    element4.title = "txtInvima"+item;
    element4.value=document.getElementById('txtInvima0').value;
    document.getElementById('txtInvima0').value="";
    cell4.appendChild(element4);
    
    var cell5 = row.insertCell(5);
    var element5 = document.createElement("input");
    element5.type = "text";
    element5.setAttribute("id", "txtVence"+item);
    element5.setAttribute("name", "txtVence");
    element5.style.width='100%';
    element5.title = "txtVence"+item;
    element5.value=document.getElementById('txtVence0').value;
    document.getElementById('txtVence0').value="";
    cell5.appendChild(element5);
    
    var cell6 = row.insertCell(6);
    var element6 = document.createElement("input");
    element6.type = "text";
    element6.setAttribute("id", "txtCantSol"+item);
    element6.setAttribute("name", "txtCantSol");
    element6.style.width='100%';
    element6.title = "txtCantSol"+item;
    element6.value=document.getElementById('txtCantSol0').value;
    document.getElementById('txtCantSol0').value="";
    cell6.appendChild(element6);
    
    
    var cell7 = row.insertCell(7);
    var element7 = document.createElement("input");
    element7.type = "text";
    element7.setAttribute("id", "txtCantidad"+item);
    element7.setAttribute("name", "txtCantidad");
    element7.style.width='100%';
    element7.title = "txtCantidad"+item;
    element7.value=document.getElementById('txtCantidad0').value;
    document.getElementById('txtCantidad0').value="";
    cell7.appendChild(element7);
    
    /*
   
    var cell8 = row.insertCell(8);
    var element8 = document.createElement("input");
    element8.type = "text";
    element8.setAttribute("id", "cmbEMB"+item);
    element8.setAttribute("name", "cmbEMB");
    element8.style.width='100%';
    element8.title = "cmbEMB"+item;
    element8.value=document.getElementById('cmbEMB0').value;
    document.getElementById('cmbEMB0').value="";
    cell8.appendChild(element8);
    */
    
    
    var cell8 = row.insertCell(8);
    var element8 = document.createElement("select");
    element8 = document.getElementById("cmbEMB0").cloneNode(true);
    document.getElementById('cmbEMB0').parentNode.insertBefore(element8,document.getElementById('cmbEMB0'));	
    element8.setAttribute("id", "cmbEMB"+item);
    element8.setAttribute("name", "cmbEMB");
    element8.style.width='100%';
    element8.title = "cmbEMB"+item;
    element8.value=document.getElementById('cmbEMB0').value;
    document.getElementById('cmbEMB0').value="";
    cell8.appendChild(element8);  
    
    /*
    var cell9 = row.insertCell(9);
    var element9 = document.createElement("input");
    element9.type = "text";
    element9.setAttribute("id", "cmbEMP"+item);
    element9.setAttribute("name", "cmbEMP");
    element9.style.width='100%';
    element9.title = "cmbEMP"+item;
    element9.value=document.getElementById('cmbEMP0').value;
    document.getElementById('cmbEMP0').value="";
    cell9.appendChild(element9);
    */
    
    var cell9 = row.insertCell(9);
    var element9 = document.createElement("select");
    element9 = document.getElementById("cmbEMP0").cloneNode(true);
    document.getElementById('cmbEMP0').parentNode.insertBefore(element9,document.getElementById('cmbEMP0'));	
    element9.setAttribute("id", "cmbEMP"+item);
    element9.setAttribute("name", "cmbEMP");
    element9.style.width='100%';
    element9.title = "cmbEMP"+item;
    element9.value=document.getElementById('cmbEMP0').value;
    document.getElementById('cmbEMP0').value="";
    cell9.appendChild(element9);
    
    
/*
    var cell10 = row.insertCell(10);
    var element10 = document.createElement("input");
    element10.type = "text";
    element10.setAttribute("id", "cmbEMV"+item);
    element10.setAttribute("name", "cmbEMV");
    element10.style.width='100%';
    element10.title = "cmbEMV"+item;
    element10.value=document.getElementById('cmbEMV0').value;
    document.getElementById('cmbEMV0').value="";
    cell10.appendChild(element10);
  */  
    
    var cell10 = row.insertCell(10);
    var element10 = document.createElement("select");
    element10 = document.getElementById("cmbEMV0").cloneNode(true);
    document.getElementById('cmbEMV0').parentNode.insertBefore(element10,document.getElementById('cmbEMV0'));	
    element10.setAttribute("id", "cmbEMV"+item);
    element10.setAttribute("name", "cmbEMV");
    element10.style.width='100%';
    element10.title = "cmbEMV"+item;
    element10.value=document.getElementById('cmbEMV0').value;
    document.getElementById('cmbEMV0').value="";
    cell10.appendChild(element10);

    
    var cell11 = row.insertCell(11);
    var element11 = document.createElement("input");
    element11.type = "text";
    element11.setAttribute("id", "txtT"+item);
    element11.setAttribute("name", "txtT");
    element11.style.width='100%';
    element11.title = "txtT"+item;
    element11.value=document.getElementById('txtT0').value;
    document.getElementById('txtT0').value="N/A";
    cell11.appendChild(element11);
    
/*
    var cell12 = row.insertCell(12);
    var element12 = document.createElement("input");
    element12.type = "text";
    element12.setAttribute("id", "cmbENVP"+item);
    element12.setAttribute("name", "cmbENVP");
    element12.style.width='100%';
    element12.title = "cmbENVP"+item;
    element12.value=document.getElementById('cmbENVP0').value;
    document.getElementById('cmbENVP0').value="";
    cell12.appendChild(element12);

*/
 /*
    var cell12 = row.insertCell(12);
    var element12 = document.createElement("select");
    element12.setAttribute("id", "cmbENVP"+item);
    element12.setAttribute("name", "cmbENVP");
    element12.style.width='100%';
    element12.title = "cmbENVP"+item;
    document.getElementById("cmbENVP0").appendChild(element12);
        
    var option = document.createElement("option");
    option.value = document.getElementById("cmbENVP0").value;
    option.innerHTML=document.getElementById("cmbENVP0").value;
    element12.appendChild(option);
    
    element12.value=document.getElementById("cmbENVP0").value;
    document.getElementById("cmbENVP0").value="";
    cell12.appendChild(element12);
   */
    
     
   /* 
    var cell12 = row.insertCell(12);
    var element12 = document.createElement("select");
    var combo = document.getElementById("cmbENVP0"); 
    var option = document.createElement("option");
    
    option.text = combo.text;
    option.value = combo.value;
     try {
        combo.add(option, null); //Standard 
    }catch(error) {
        combo.add(option); // IE only
    } 
    
    element12.appendChild(option);
    element12.value=combo.value;
    combo.value = "" ;
    element12.style.width='100%';   
    cell12.appendChild(element12);
    
    */
 
       
    
    var cell12 = row.insertCell(12);
    var element12 = document.createElement("select");
    element12 = document.getElementById("cmbENVP0").cloneNode(true);
    document.getElementById('cmbENVP0').parentNode.insertBefore(element12,document.getElementById('cmbENVP0'));	
    element12.setAttribute("id", "cmbENVP"+item);
    element12.setAttribute("name", "cmbENVP");
    element12.style.width='100%';
    element12.title = "cmbENVP"+item;
    element12.value=document.getElementById('cmbENVP0').value;
    document.getElementById('cmbENVP0').value="";
    cell12.appendChild(element12);


    var cell13 = row.insertCell(13);
    var element13 = document.createElement("input");
    element13.type = "text";
    element13.setAttribute("id", "txtCUM"+item);
    element13.setAttribute("name", "txtCUM");
    element13.style.width='100%';
    element13.title = "txtCUM"+item;
    element13.value=document.getElementById('txtCUM0').value;
    document.getElementById('txtCUM0').value="";
    cell13.appendChild(element13);
    
    
     /*
    var cell14 = row.insertCell(14);
    var element14 = document.createElement("input");
    element14.type = "text";
    element14.setAttribute("id", "cumple"+item);
    element14.setAttribute("name", "cumple");
    element14.style.width='100%';
    element14.title = "cumple"+item;
    element14.value=document.getElementById('cumple0').value;
    document.getElementById('cumple0').value="";
    cell14.appendChild(element14);
    */
    
    var cell14 = row.insertCell(14);
    var element14 = document.createElement("select");
    element14.setAttribute("id", "cumple"+item);
    element14.setAttribute("name", "cumple");
    element14.style.width='100%';
    element14.title = "cumple"+item;
    document.getElementById("cumple0").appendChild(element14);
    
    var option = document.createElement("option");
    option.value = "";
    option.appendChild(document.createTextNode(""));
    element14.appendChild(option);

    option = document.createElement("option");
    option.value = "1";
    option.appendChild(document.createTextNode("SI"));
    element14.appendChild(option);
    
    option = document.createElement("option");
    option.value = "0";
    option.appendChild(document.createTextNode("NO"));
    element14.appendChild(option);
    
    element14.value=document.getElementById("cumple0").value;
    document.getElementById("cumple0").value="";
    cell14.appendChild(element14);
    
   
    var cell15 = row.insertCell(15);
    var element15 = document.createElement("select");
    element15.setAttribute("id", "dona"+item);
    element15.setAttribute("name", "dona");
    element15.style.width='100%';
    element15.title = "dona"+item;
    document.getElementById("dona0").appendChild(element15);
    
    var option = document.createElement("option");
    option.value = "";
    option.appendChild(document.createTextNode(""));
    element15.appendChild(option);

    option = document.createElement("option");
    option.value = "1";
    option.appendChild(document.createTextNode('SI'));
    element15.appendChild(option);
    
    option = document.createElement('option');
    option.value = "0";
    option.appendChild(document.createTextNode('NO'));
    element15.appendChild(option);
    
    element15.value=document.getElementById('dona0').value;
    document.getElementById('dona0').value="";
    cell15.appendChild(element15);
    
   
      
    var cell16 = row.insertCell(16);
    var element16 = document.createElement("input");
    element16.type = "text";
    element16.setAttribute("id", "ter"+item);
    element16.setAttribute("name", "ter");
    element16.style.width='100%';
    element16.value=document.getElementById('ter0').value;
    element16.title=document.getElementById('ter0').title;
    document.getElementById('ter0').value="";
    document.getElementById('ter0').title="";
         
    element16.onkeyup = function(event) {
    	Divdectaytercero(this,52,2,2,1,event);
    }
    
    cell16.appendChild(element16);


    var element016 = document.createElement("input");
    element016.type = "text";
    element016.setAttribute("id", "hter"+item);
    element016.setAttribute("name", "hter");
    element016.style.display= "none";
    element016.style.width='100%';
    element016.value=document.getElementById('hter0').value;
    document.getElementById('hter0').value="";
    cell16.appendChild(element016);
    
       
    
    var cell17 = row.insertCell(17);
    var element17 = document.createElement("input");
    element17.type = "text";
    element17.setAttribute("id", "txtVunitario"+item);
    element17.setAttribute("name", "txtVunitario");
    element17.style.width='100%';
    element17.title = "txtVunitario"+item;
    element17.value=document.getElementById('txtVunitario0').value;
    document.getElementById('txtVunitario0').value="";
    cell17.appendChild(element17);
    
    var cell18 = row.insertCell(18);
    var element18 = document.createElement("input");
    element18.type = "text";
    element18.setAttribute("id", "txtTotal"+item);
    element18.setAttribute("name", "txtTotal");
    element18.style.width='97%';
    element18.title = "txtTotal"+item;
    element18.value=document.getElementById('txtTotal0').value;
    document.getElementById('txtTotal0').value="";
    cell18.appendChild(element18);
    
         
    var row2 = table.insertRow(rowCount-1);
    
    row2.setAttribute("id", "tr"+item);
    row2.setAttribute("name", "tri");
    
    var cell0v2 = row2.insertCell(0);
    
    var cell1v2 = row2.insertCell(1);
    var element0v2 = document.createElement("div");
    element0v2.setAttribute("id", "dcta"+item);
    element0v2.className="dlista";
   

    cell1v2.appendChild(element0v2);
   
    var cell2v2 = row2.insertCell(2);
    var element1v2 = document.createElement("div");
    element1v2.setAttribute("id", "dter"+item);
    element1v2.className="dlista";
    
    cell2v2.appendChild(element1v2);
    
    item++;
    document.getElementById('cta0').focus();

}


function Divdectaytercero(t,x,y,z,w,elEvento){

	var d=t.id;
	var dn=d.substring(6);
	
	//alert(t+" - "+x+" - "+y+" - "+z+" - "+w+" - "+elEvento);
	 var event = elEvento || window.event;
	 var code = (event.keyCode) ? event.keyCode : event.which;
	 var tecla=event.keyCode;
	
	
	//alert(d+" - "+dn+" - "+document.getElementById('dcta'+dn).value);
	
	if(w==0){
	di=document.getElementById('dcta'+dn);
	
	//d=document.getElementById('dcta'+dn);
	Completa(t,di,event,'ControlEntradas1',x,y,z);
	//alert("pruebaaaa "+d+" - "+d.id+" - "+d.value);
	}
	if(w==1){
	di=document.getElementById('dter'+dn);
	
	//d=document.getElementById('dter'+dn)
	Completa(t,di,event,'controlEntradas1',x,y,z);
	}
}


function ConfirmarDatos(e,d,cta,nopc,evt){//Seleccionar datos del autocompletar
	
	var z=e.split("|");	     	
	var h="h"+cta;
	//alert("ConfirmarDatos "+e+" - "+d+" - "+cta+" - "+nopc+" - "+evt+" - "+h);
	if(nopc=="1"){//Buscar cuentas
	 codigo=z[0];
	 numero=z[1];
	 nombre=z[2];
	 //sigla=z[3];
	 //fdescripcion=z[4];
	 ///mdescripcion=z[5];
	 
	 
	 
	 document.getElementById(cta).value = numero;
	 document.getElementById(cta).title=nombre;
	 //document.getElementById(cta).title=sigla;
	 //document.getElementById(cta).title=fdescripcion;
	 //document.getElementById(cta).title=mdescripcion;
	 
	 document.getElementById(h).value=codigo;
	 document.getElementById(d).innerHTML = "";
	     document.getElementById(d).style.display = "none";
	     //cta.focus;
	}//fin nopc 1

	
	if(nopc=="2"){//Buscar terceros
	 codigo=z[0];
	 numero=z[1];
	 nombre=z[2];
	 document.getElementById(cta).value = numero;
	 document.getElementById(cta).title=nombre;
	 document.getElementById(h).value=codigo;
	 document.getElementById(d).innerHTML = "";
	     document.getElementById(d).style.display = "none";
	}	
	
	//tambien hay que crear los dos campos text al lado de cta y ter que sean hidden para guardar los cod de cuentas
	//xq en los que estan vamos a mostrar son los numeros y nombres de terceros
}




function Causar() {

	var max = document.getElementsByTagName('label').length;
	
	var datos="";	
	
	var bodega=document.getElementById('cmbBodega').value;
	var proveedor=document.getElementById('cmbProveedor').value;
	var tipoM=document.getElementById('cmbTipoM').value;
	var factura=document.getElementById('txtFactura').value;
	var orden=document.getElementById('txtOrden').value;
	var tiempoE=document.getElementById('cmbTiemE').value;
	var recibe=document.getElementById('txtRecibe').value;
	var entrego=document.getElementById('txtEntrego').value;
	var verifico=document.getElementById('txtVerifico').value;
	var fechass=document.getElementById('txtFecha').value;
	var hora=document.getElementById('txtHora').value;
	
	var user = document.getElementById("txtCodusuario").value;
	var cont=0;
	var r7=0;
	
	for(var i=1;i<=max-1;i++){

	var r1=document.getElementById('hcta'+i).value;
	var r2=document.getElementById('lab'+i).value;
	var r3=document.getElementById('lote'+i).value;
	var r4=document.getElementById('txtInvima'+i).value;
	var r5=document.getElementById('txtVence'+i).value;
	var r6=document.getElementById('txtCantSol'+i).value;
	var r7=document.getElementById('txtCantidad'+i).value;
	var r8=document.getElementById('cmbEMB'+i).value;
	var r9=document.getElementById('cmbEMP'+i).value;
	var r10=document.getElementById('cmbEMV'+i).value;
	var r11=document.getElementById('txtT'+i).value;
	var r12=document.getElementById('cmbENVP'+i).value;
	var r13=document.getElementById('txtCUM'+i).value;
	var r14=document.getElementById('cumple'+i).value;
	var r15=document.getElementById('dona'+i).value;
	var r16=document.getElementById('hter'+i).value;
	var r17=document.getElementById('txtVunitario'+i).value;
	var r18=document.getElementById('txtTotal'+i).value;
	
	
	var f=r1+","+r2+","+r3+","+r4+","+r5+","+r6+","+r7+","+r8+","+r9+","+r10+","+r11+","+r12+","+r13+","+r14+","+r15+","+r16+","+r17+","+r18;
	datos=datos+f+"|";
		
	cont=parseInt(cont)+parseInt(r7);

	
	}
	///alert("max-1: "+(max-1));
	////alert( "cant "+cont);
	
	var movi=null;
	
	//var contenido=document.getElementById('Opcion');
	var contenid=document.getElementById('repetir');
	ajax=getXMLObject();
	ajax.open("POST","ControlEntradas1",true);
	ajax.onreadystatechange = function(){
		if(ajax.readyState == 4){
			movi=ajax.responseText;
			alert("movi: "+movi);
				alert("Ingreso Exitoso.");
				verReporte(movi);
				verActaRecepcion(movi);
				window.location.reload();
		}
	}
	ajax.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=UTF-8"); 
	////alert(datos);
	ajax.send("valor=53&Ndatos="+datos+"&bodega="+bodega+"&proveedor="+proveedor+"&tipoM="+tipoM+"&factura="+factura+"&orden="+orden+"&tiempoE="+tiempoE+"&recibe="+recibe+"&entrego="+entrego+"&verifico="+verifico+"&fechass="+fechass+"&hora="+hora+"&user="+user+"&cont="+cont+"&max="+max);
	
	
}

function verReporte (movi) {
	pagina="farc_ReporteEntradas1.jsp?movi="+movi;			
   	var opciones="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones =opciones+"scrollbars=si, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}


function verActaRecepcion (movi) {
	//alert("ver acta de recpcion "+movi);
	pagina="farc_ReporteActaRecepcion1.jsp?movi="+movi;			
   	var opciones2="toolbar=no, location=no, directories=no, status=no, menubar=no,";
 	opciones2 =opciones2+"scrollbars=yes, resizable=yes, width=1000, height=570, top=75,  left=150";
   	window.open(pagina,"NUEVA2",opciones2);
}



















