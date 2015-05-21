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

function Radio() {	
	ajax=getXMLObject();
	var contenido=document.getElementById('vista');
	var user=document.getElementById('user').value;
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;   
			if(val=="pimp"){
				document.getElementById("prove").innerHTML="";
			}
			ajax.open("POST","ControlProduc",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&user="+user); //Posting txtname to Servlet
		}	     
	}
}


function autocompletaCtaAux(ind,tipo,numcuenta){
		// Tipo 1 Grupo 
		// Tipo 3 Producto 
		//alert("ind "+ind+" tipo "+tipo+" numcuenta"+numcuenta);
	   var texto=document.getElementById("aux"+ind).value;
	   //alert(texto);
	   if(texto==""){
		   document.getElementById("daux"+1+ind).innerHTML="";
	   }else{
		   ajax=getXMLObject();
			ajax.open("POST","ControlProduc",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {	
					//alert(ajax.responseText);
					document.getElementById("daux"+1+ind).innerHTML=ajax.responseText;
					
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=busaux&texto="+texto+"&ind="+ind+"&tipo="+tipo+"&numcuenta="+numcuenta);
	   }
	}

function AsignacionCtaAux(ind, cta, codcta){
	//alert("ind "+ind+" cta "+cta+" codcta"+codcta);
	document.getElementById("aux"+ind).value=cta;
	document.getElementById("auxcod"+ind).value=codcta;
	document.getElementById("daux"+1+ind).innerHTML="";
	document.getElementById("Boto"+ind).focus();
	  ajax=getXMLObject();

}

function GuardarCta(ind,codgrupo){
	var user=document.getElementById("user").value;
	var ctaaux=document.getElementById("aux"+ind).value;
	var codcta=document.getElementById("auxcod"+ind).value;
	//alert("valor=guardarcta&ctaaux="+ctaaux+"&codgrupo="+codgrupo+"&user="+user+"&codcta="+codcta);
	if(codcta!=""){
	ajax=getXMLObject();
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			alert(ajax.responseText);
			window.location.reload();
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=guardarcta&ctaaux="+ctaaux+"&codgrupo="+codgrupo+"&user="+user+"&codcta="+codcta);
	}else{
		alert("EL DATO DE CUENTA NO SE ENCUENTRA CORRECTO");
	}
}

function GuardarCtaProd(ind,codprod){
	var user=document.getElementById("user").value;
	var ctaaux=document.getElementById("aux"+ind).value;
	var codcta=document.getElementById("auxcod"+ind).value;
	var opc=confirm("Desea asignar esta cuenta ?");
	if(opc){
	if(codcta!=""){
		ajax=getXMLObject();
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {	
				alert(ajax.responseText);
				ProductosxParametrizar();
				//windows.location.reload();
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=guardarctaprod&ctaaux="+ctaaux+"&codpro="+codprod+"&user="+user+"&codcta="+codcta);
		}else{
			alert("EL DATO DE CUENTA NO SE ENCUENTRA CORRECTO");
		}
	}else{ /*no hace nada */ }
}

function ProductosxParametrizar(){
	ajax=getXMLObject();
	var contenido=document.getElementById('vista');
	var user=document.getElementById('user').value;   
			ajax.open("POST","ControlProduc",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=pp&user="+user); //Posting txtname to Servlet	     
	
	
}

function BusProduc(){
	ajax=getXMLObject();
	var texto=document.getElementById("produc").value;
	var contenido=document.getElementById('vprod');
	if(texto==""){
		contenido.innerHTML ="";
	}else{
	
			ajax.open("POST","ControlProduc",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=BProd&texto="+texto); //Posting txtname to Servlet	
	}
}
function SelProd(codprod, cuenta){
	ajax=getXMLObject();
	//var contenido=document.getElementById("vistacuentap");
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			var datos = ajax.responseText;
			var elem=datos.split("_");
			var ref=elem[0];
			var nombre=elem[1];
			document.getElementById("produc").value=ref+"-"+nombre;
			document.getElementById("produc").disabled=true;
			document.getElementById("vprod").innerHTML="";
			VistaModi(codprod,cuenta);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=txt&codprod="+codprod+"&cuenta="+cuenta); //Posting txtname to Servlet	   
}

function VistaModi(codprod,cuenta){
	ajax=getXMLObject();
	var contenido=document.getElementById("vistacuentap");

	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VistaModif&codprod="+codprod+"&cuenta="+cuenta); 
}

function VerLista(tipo){
	ajax=getXMLObject();
	pagina="cont_Listas.jsp?tipo="+tipo;			
   	var opciones="toolbar=no, location=no, directories=no, status=yes, menubar=yes,";
 	opciones =opciones+"scrollbars=yes, resizable=yes, width=1000, height=570, top=85,  left=140";
   	window.open(pagina,"NUEVA",opciones);
}


function Listado(tipo){
	ajax=getXMLObject();
	var contenido=document.getElementById("Listado");
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Lista&tipo="+tipo);
}

function AutoCompleCta(ctagrup, codprod){
	ajax=getXMLObject();
	var texto=document.getElementById("ctaprod").value;
	var contenido=document.getElementById("cuentas");
	if(texto==""){
		contenido.innerHTML="";
	}else{
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VerCtas&ctagrup="+ctagrup+"&codprod="+codprod+"&texto="+texto); 
	}
}

function AutoCompleCtaG(codgrup){
	ajax=getXMLObject();
	var texto=document.getElementById("ctagrup").value;
	var contenido=document.getElementById("cuentasg");
	if(texto==""){
		contenido.innerHTML="";
	}else{
		
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=VerCtasG&codgrup="+codgrup+"&texto="+texto); 
	}
}


function BusGrup(){
	var texto=document.getElementById("grup").value;
	var contenido=document.getElementById("vgrupo")
	if(texto==""){
		contenido.innerHTML="";
	}else{
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=BGrup&texto="+texto); 
	}
}


function SelGrup(codgrup){
	ajax=getXMLObject();
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			var datos = ajax.responseText;
			document.getElementById("grup").value=datos;
			document.getElementById("grup").disabled=true;
			document.getElementById("vgrupo").innerHTML="";
			VistaModig(codgrup);
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=txtg&codgrup="+codgrup); //Posting txtname to Servlet	   
	
}

function VistaModig(codgrup){
	ajax=getXMLObject();
	//alert("VistaModig"+codgrup);
	var contenido=document.getElementById("vistacuentag");
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VistaModig&codgrup="+codgrup); 
}

function ActCuentaG(codcuenta,vali,codgrup){
	
	if(vali==1){
		var opc=confirm("Este Grupo tiene productos asociados y parametrizados,al solicitar el cambio de cuenta al grupo, usted debera parametrizar de nuevo todos los productos asociados a este grupo. ¿Desea Hacerlo?")
		if(opc){
			ajax=getXMLObject();
			var cont=document.getElementById("ListaP");
			var user=document.getElementById("user").value;
			ajax.open("POST","ControlProduc",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					document.getElementById("cuentasg").innerHTML="";
					document.getElementById("ctagrup").disabled=true;
					cont.innerHTML=ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=AsigCtaGP&codgrup="+codgrup+"&codcuenta="+codcuenta+"&user="+user); 
		}else{
			alert("Cambio Cancelado");
			document.getElementById("cuentasg").innerHTML="";
		}
	}else{
		//alert("cuando no hay productos asociados con cuentas");
		ajax1=getXMLObject();
		var userr=document.getElementById("user").value;
		ajax1.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax1.onreadystatechange  = function(){
			if (ajax1.readyState == 4) {
				document.getElementById("cuentasg").innerHTML="";
				alert("Parametrizacion Exitosa !");
				window.location.reload();
			}
		}
		ajax1.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax1.send("valor=AsigCtaG&codgrup="+codgrup+"&codcuenta="+codcuenta+"&user="+userr); 
	}
}


function AutoCompCtaGP(ident, ctaaux){
	ajax=getXMLObject();
	var texto=document.getElementById("ctaP"+ident).value;
	if(texto==""){
		document.getElementById("cc"+ident).innerHTML="";
	}else{
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				//alert(ajax.responseText);
				document.getElementById("cc"+ident).innerHTML="";
				document.getElementById("cc"+ident).innerHTML=ajax.responseText;
				
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=Visualtxt&texto="+texto+"&ctaaux="+ctaaux+"&ident="+ident);
	}
}

function ACtaP(codcuenta, numcuenta, ident){
	document.getElementById("ctaP"+ident).value=numcuenta;
	document.getElementById("codcta"+ident).value=codcuenta;
	document.getElementById("cc"+ident).innerHTML="";
}

function GuardarActG(i, codgrup,codcuenta,ctaaux){
	var cuenta="";
	var cuentas=" ";
	var prod=""
	var productos=" ";
	//alert("valor de i "+i);
	for(j=1;j<i;j++){
		cuenta=document.getElementById("codcta"+j).value;
		cuentas=cuenta+"_"+cuentas;
		prod=document.getElementById("codpro"+j).value;
		productos=prod+"_"+productos;
	}
	//alert(" cuentas"+cuentas);
	//alert(" productos"+productos);
	if(i!=1){
		var user=document.getElementById("user").value;
		var cont=document.getElementById("ListaP");
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				var datos =ajax.responseText;
				if(datos==1){
					alert("Parametrizacion exitosa !!");
					window.location.reload();
				}else{
					
					cont.innerHTML=ajax.responseText;
					/*document.getElementById("ListaP").innerHTML="";
					document.getElementById("ListaP").innerHTML="YULY";
					document.getElementById("ListaP").innerHTML=datos;*/
				}
			}
		}
		alert("valor=GuardarActG&i="+i+"&codgrup="+codgrup+"&user="+user+"&cuentas="+cuentas+"&productos="+productos+"&codcta="+codcuenta+"&ctaaux="+ctaaux); 
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GuardarActG&i="+i+"&codgrup="+codgrup+"&user="+user+"&cuentas="+cuentas+"&productos="+productos+"&codcta="+codcuenta+"&ctaaux="+ctaaux); 
	}else{
		alert("No ha digitado todas las cuentas");
	}
	
}

function ActCuenta(codcuenta, codprod){
	ajax=getXMLObject();
	var opc=confirm("Desea Actualizar la cuenta asociada  al producto ? Al realizar esta accion actualizara los datos de los presupuestos de inventario por emitir  ");
	if(opc){
		var user=document.getElementById("user").value;
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				alert(ajax.responseText);
			}
		}
		//alert("valor=GuardarActP&codcuenta="+codcuenta+"&codprod="+codprod+"&user="+user);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GuardarActP&codcuenta="+codcuenta+"&codprod="+codprod+"&user="+user); 
		
		
	}else{
	
	}
	
}
function ProvexCta(){
	ajax=getXMLObject();
	var contenido=document.getElementById("prove");
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			contenido.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=ProvexCta");
}

function AutoCta(){
	ajax=getXMLObject();
	var texto=document.getElementById("cta").value;
	var contenido=document.getElementById("vcta");
	if(texto==""){
		contenido.innerHTML="";
	}else{
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {	
				//alert(ajax.responseText);
				//contenido.innerHTML="yulY";
				contenido.innerHTML=ajax.responseText;
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=AutoCta&texto="+texto);
	}
	
}

function AsigCta(codigo, codcta){
	ajax=getXMLObject();
	//alert("codigo ="+codigo+" codigocuenta="+codcta);
	document.getElementById("cta").value=codcta;
	document.getElementById("ccta").value=codigo;
	document.getElementById("vcta").innerHTML="";
}

function AsigG(){
	var codcta=document.getElementById('ccta').value;
	var cuenta=document.getElementById("cta").value;
	var user=document.getElementById("user").value;
	//alert("valor=AsigCtaProveG&codcta="+codcta+"&user="+user);
	if(codcta==""){
		//alert(codcta);
		alert("No ha seleccionado una Cuenta  valida ");
	}else{
		//alert(codcta);
		var opc=confirm("Los proveedores parametrizados anteriormente seran reemplazados por la Cta. "+cuenta+" ¿DESEA ASIGNAR LA CUENTA "+cuenta+" A TODOS LOS PROVEEDORES EXISTENTES ?");
		if(opc){
			ajax.open("POST","ControlProduc",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {	
					alert("Parametrizacion Exitosa !");
					window.location.reload();
				}
			}
			//alert("valor=AsigCtaProveG&codcta="+codcta+"&user="+user);
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor=AsigCtaProveI&codcta="+codcta+"&user="+user);
		}else{
			alert("Parametrizacion Cancelada ");
		}
	}
}

function AsigI(){
	var codcta=document.getElementById("ccta").value;
	var cuenta=document.getElementById("cta").value;
	var user=document.getElementById("user").value;
	var codprove=document.getElementById("prove").value;
	if(codcta==""){
		alert("No ha seleccionado una Cuenta  valida ");
	}else{
		if(codprove==""){
			alert("No ha seleccionado un proveedor ");
			
		}else{
			var opc=confirm("¿DESEA ASIGNAR LA CUENTA "+cuenta+" AL PROVEEDOR SELECCIONADO ?");
			if(opc){
				ajax.open("POST","ControlProduc",true); //getname will be the servlet name
				ajax.onreadystatechange  = function(){
					if (ajax.readyState == 4) {	
						alert("Parametrizacion Exitosa !");
						window.location.reload();
					}
				}
				ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
				ajax.send("valor=AsigCtaProveI&codcta="+codcta+"&codprove="+codprove+"&user="+user);
			}else{
				alert("Parametrizacion Cancelada ");
			}
		}
	
	
}
}


function Valivalor(Tipo, Ind,reg){
	/*Tipo 1: Iva, 2: Rfte, 3: Ica , 4: cre */
	//alert("valivalor");
	var cc="";
	var desc="";
	if(Tipo==1){
		cc="valiva";
		desc="IVA";
	}else{
		if(Tipo==2){
			cc="valrfte";
			desc="RETEFUENTE";
		}else{
			if(Tipo==3){
				cc="valica";
				desc="ICA";
			}else{
				if(Tipo==4){
					cc="valcre";
					desc="CRE";
				}
			}
		}
	}
	ajax=getXMLObject();
	var dato=document.getElementById(cc+Ind).value;
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {	
			var datos=ajax.responseText;
			var elem=datos.split("_");
			var verif=elem[0];
			var texto=elem[1];
			if(verif==1){
				alert(texto);
				document.getElementById(cc+Ind).focus();
			}
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Valivalor&Tip="+Tipo+"&dato="+dato+"&Ind="+Ind+"&desc="+desc+"&reg="+reg);
}

function ModImpuesto(Tipo, Ind){
	/*Tipo 1: Iva, 2: Rfte, 3: Ica , 4: cre */
	var cant="";
	var desc="";
	var base="";
	var uvt="";
	var codcta="";
	var tabla="";
	var codreg="";
	if(Tipo==1){
		cant="valiva";
		desc="diva";
		codcta="codcta";
		codreg="codiva"
		tabla="ord_iva";
	}else{
		if(Tipo==2){
			cant="valrfte";
			base="brfte";
			uvt="urfte";
			desc="drfte";
			codcta="codctarfte";
			codreg="codrfte"
			tabla="ord_retefuente";
		}else{
			if(Tipo==3){
				cant="valica";
				desc="dica";
				codcta="codctaica";
				codreg="codica";
				tabla="ord_ica";
			}else{
				if(Tipo==4){
					cant="valcre";
					desc="dcre";
					codcta="codctacre";
					codreg="codcre";
					tabla="ord_cre";
				}
			}
		}
	}
	var dat_cant="";
	var dat_desc="";
	var dat_base="";
	var dat_uvt="";
	var dat_codreg="";
	var dat_codcta="";
	var datos_cant="";
	var datos_desc="";
	var datos_base="";
	var datos_uvt="";
	var datos_codcta="";
	var datos_codreg="";
	var val=0;
	for(i=1;i<Ind;i++){
		dat_cant=document.getElementById(cant+i).value;
		dat_desc=document.getElementById(desc+i).value;
		dat_codcta=document.getElementById(codcta+i).value;
		dat_codreg=document.getElementById(codreg+i).value;
		//alert("cod_reg"+dat_codreg);
		if((dat_cant=="")||(dat_desc=="")||(dat_codcta=="")||(dat_codreg=="")){
			val=1;
		}
		
		if(Tipo==2){
		dat_base=document.getElementById(base+i).value;
		dat_uvt=document.getElementById(uvt+i).value;
			if((dat_base=="")||(dat_uvt=="")){
				val=1;
			}
		datos_uvt=dat_uvt+"_"+datos_uvt;
		datos_base=dat_base+"_"+datos_base;
		}
		
		datos_cant=dat_cant+"_"+datos_cant;
		datos_desc=dat_desc+"_"+datos_desc;
		datos_codcta=dat_codcta+"_"+datos_codcta;
		datos_codreg=dat_codreg+"_"+datos_codreg;
	}
	
	//alert("Ind"+Ind);
	if(val==1){
		alert("No ha registrado todos los datos ");
	}else{
		var user=document.getElementById("user").value;
		ajax=getXMLObject();
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {	
				alert("Modificacion exitosa ");
				Cargar(user,"pimp");
			}
		}
		//alert("VALORES !! &Tipo="+Tipo+"&datos_cant="+datos_cant+"&datos_desc="+datos_desc+"&datos_codcta="+datos_codcta+"&datos_uvt="+datos_uvt+"&datos_base="+datos_base+"&Ind="+Ind+"&tabla="+tabla+"&datos_codreg="+datos_codreg+"&user="+user);
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=ModImp&Tipo="+Tipo+"&datos_cant="+datos_cant+"&datos_desc="+datos_desc+"&datos_codcta="+datos_codcta+"&datos_uvt="+datos_uvt+"&datos_base="+datos_base+"&Ind="+Ind+"&tabla="+tabla+"&datos_codreg="+datos_codreg+"&user="+user);
	}
}

function Acuenta(Tipo,Ind){
	
	var cc="";
	var texto="";
	var vista="";
	var descp="";
	if(Tipo==1){
		cc="codcta";
		vista="vctaiva";
		texto="ccta";
		descp="dcta";
	}else{
		if(Tipo==2){
			cc="codctarfte";
			vista="vctarfte";
			texto="ctarfte";
			 descp="dcrfte";
		}else{
			if(Tipo==3){
				cc="codctaica";
				vista="vctaica";
				texto="ctaica";
				descp="dctaica";
			}else{
				if(Tipo==4){
					cc="codctacre";
					vista="vctacre";
					texto="ctacre";
					descp="dctacre";
				}
			}
		}
	}
	var cont=document.getElementById(vista+Ind);
	var datos=document.getElementById(texto+Ind).value;
	ajax=getXMLObject();
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			//alert(ajax.responseText);
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=VCtas&datos="+datos+"&cc="+cc+"&vista="+vista+"&texto="+texto+"&Ind="+Ind+"&descp="+descp);
	
	
}

function Cargar(user,val) {	
	ajax=getXMLObject();
	var contenido=document.getElementById('vista');

			if(val=="pimp"){
				document.getElementById("prove").innerHTML="";
			}
			ajax.open("POST","ControlProduc",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					contenido.innerHTML = ajax.responseText;
				}
			}
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val+"&user="+user); //Posting txtname to Servlet
}	 

function AsCtaImp(i,codcta,Ind){
	
	var nombrecta=document.getElementById("nombcta"+i).value;
	var numcta=document.getElementById("numcta"+i).value;
	var cc=document.getElementById("cc").value;
	var vista=document.getElementById("vist").value;
	var desc=document.getElementById("descp").value;
	var texto=document.getElementById("texto").value;
	//alert("AsCtaImp"+i+","+codcta+","+cc+","+vista);
	if(cc=="codcta"){
		document.getElementById("vctaiva"+Ind).innerHTML="";
	}else{
		if(cc=="codctarfte"){
			document.getElementById("vctarfte"+Ind).innerHTML="";
		}else{
			if(cc=="codctaica"){
				document.getElementById("vctaica"+Ind).innerHTML="";
			}else{
				if(cc=="codctacre"){
					document.getElementById("vctacre"+Ind).innerHTML="";
				}
			}
		}
	}
	
	document.getElementById(cc+Ind).value=codcta;
	document.getElementById(texto+Ind).value=numcta;
	document.getElementById(desc+Ind).value=nombrecta;
	
	
}

function NuevoImpuesto(Tipo){
	
	var cc="";
	var bt="";
	if(Tipo==1){
		cc="nvoiva";
		bt="bn1";
		document.getElementById("nvrfte").innerHTML="";
		document.getElementById("nvoica").innerHMTL="";
		document.getElementById("nvocre").innerHTML="";
		document.getElementById("bn2").disabled=false;
		document.getElementById("bn3").disabled=false;
		document.getElementById("bn4").disabled=false;
	}else{
		if(Tipo==2){
			cc="nvrfte";
			bt="bn2";
			document.getElementById("nvoiva").innerHTML="";
			document.getElementById("nvoica").innerHMTL="";
			document.getElementById("nvocre").innerHTML="";
			document.getElementById("bn1").disabled=false;
			document.getElementById("bn3").disabled=false;
			document.getElementById("bn4").disabled=false;
		}else{
			if(Tipo==3){
				cc="nvoica";
				bt="bn3";
				document.getElementById("nvrfte").innerHTML="";
				document.getElementById("nvoiva").innerHMTL="";
				document.getElementById("nvocre").innerHTML="";
				document.getElementById("bn2").disabled=false;
				document.getElementById("bn1").disabled=false;
				document.getElementById("bn4").disabled=false;
			}else{
				if(Tipo==4){
					cc="nvocre";
					bt="bn4";
					document.getElementById("nvrfte").innerHTML="";
					document.getElementById("nvoica").innerHMTL="";
					document.getElementById("nvoiva").innerHTML="";
					document.getElementById("bn2").disabled=false;
					document.getElementById("bn3").disabled=false;
					document.getElementById("bn1").disabled=false;
				}
			}
		}
	}
	
	var cont=document.getElementById(cc);
	document.getElementById(bt).disabled=true;
	ajax=getXMLObject();
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=NvoImp&Tipo="+Tipo); 
}

function Gimp(Tipo){
	var base="";
	var uvt="";
	
	if(Tipo==2){
		base=document.getElementById("bas").value;
		uvt=document.getElementById("uv").value;
	}
	var valor=document.getElementById("nval").value;
	var descrip=document.getElementById("de").value;
	var codcta=document.getElementById("nuevocodcta").value;
	if((valor=="")||(descrip=="")||(codcta=="")){
		alert("No ha diligenciado todos los datos ");
	}else{
		var user=document.getElementById("user").value;
		ajax=getXMLObject();
		ajax.open("POST","ControlProduc",true); //getname will be the servlet name
		ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {
				document.getElementById("nvrfte").innerHTML="";
				document.getElementById("nvoica").innerHMTL="";
				document.getElementById("nvoiva").innerHTML="";
				alert("Insercion Exitosa");
				Cargar(user,"pimp");
			}
		}
		ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=GImpu&cant="+valor+"&descrip="+descrip+"&codcta="+codcta+"&base="+base+"&uvt="+uvt+"&user="+user+"&Tipo="+Tipo); 
	}
}

function ncta(){
	var texto=document.getElementById("ncta").value;
	var cont=document.getElementById("nvistacta");
	ajax=getXMLObject();
	ajax.open("POST","ControlProduc",true); //getname will be the servlet name
	ajax.onreadystatechange  = function(){
		if (ajax.readyState == 4) {
			cont.innerHTML=ajax.responseText;
		}
	}
	ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	ajax.send("valor=Vistancta&texto="+texto); 
}

function nasicta(i,codcta){
	
	var nombre=document.getElementById("nnombre"+i).value;
	var numcuenta=document.getElementById("ncodcta"+i).value;
	//alert(codcta);
	document.getElementById("ncta").value=numcuenta;
	document.getElementById("decta").value=nombre;
	document.getElementById("nvistacta").innerHTML="";
	document.getElementById("nuevocodcta").value=codcta;
	
}





	
