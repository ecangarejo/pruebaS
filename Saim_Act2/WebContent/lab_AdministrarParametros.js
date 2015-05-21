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
function CambiarParametros(){
	var valor=document.getElementById('cmbOpcionesParametros').value;
	document.getElementById('Formulario').innerHTML='<p>En Proceso...No Cierre La Ventana..</p><img src="Imagenes/ani.gif">';
	   ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('Formulario').innerHTML = ajax.responseText;
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor="+valor); //Posting txtname to Servlet
}
/*************************Actualizar Nombre Area********************************/
function VerNombreArea(){
	var CodigoArea=document.getElementById("cmbArea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('texto').innerHTML = ajax.responseText;
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1.1&CodigoArea="+CodigoArea); //Posting txtname to Servlet
}

function ActualizarArea(){
	var CodigoArea=document.getElementById("cmbArea").value;
	var NombreArea=document.getElementById("txtNuevaArea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert(ajax.responseText);
					window.location.reload();
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=1.2&CodigoArea="+CodigoArea+"&NombreArea="+NombreArea); //Posting txtname to Servlet
}
/*************************Fin Actualizar Nombre Area****************************/
/*************************Actualizar Nombre Subarea*****************************/
function BuscarSubarea(){
	var CodigoArea=document.getElementById("cmbArea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('subarea').innerHTML = ajax.responseText;
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2.1&CodigoArea="+CodigoArea); //Posting txtname to Servlet
}

function VerNombreSubarea(){
	var CodigoSubarea=document.getElementById("cmbSubarea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('texto').innerHTML = ajax.responseText;
				}
			}
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2.2&CodigoSubarea="+CodigoSubarea); //Posting txtname to Servlet
}

function ActualizarSubarea(){
	var CodigoSubarea=document.getElementById("cmbSubarea").value;
	var NombreSubarea=document.getElementById("txtNuevaSubarea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert(ajax.responseText);
					window.location.reload();
				}
			}
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=2.3&CodigoSubarea="+CodigoSubarea+"&NombreSubarea="+NombreSubarea); //Posting txtname to Servlet
}
/*************************Fin Actualizar Nombre Subarea*************************/
/*************************Actualizar Nombre de un Examen************************/
function getRadio() {	
	ajax=getXMLObject();
	var opcion=document.getElementById('opcion');
	var radioButtons = document.getElementsByName("radiobutton");
	for (var x = 0; x < radioButtons.length; x ++) {
		if (radioButtons[x].checked) {
			var val=radioButtons[x].id;    
			ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
			ajax.onreadystatechange  = function(){
				if (ajax.readyState == 4) {
					opcion.innerHTML = ajax.responseText	
				}
			};
			ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
			ajax.send("valor="+val); //Posting txtname to Servlet
		}	     
	}
}
/*************************Si examen pertenece a un area*************************/
function VerExamenArea(){
	var CodigoArea=document.getElementById("cmbArea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('examen').innerHTML = ajax.responseText;
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3.11&CodigoArea="+CodigoArea); //Posting txtname to Servlet

}

function VerNombreExamen(){
	var CodigoExamen=document.getElementById("cmbExamen").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('texto').innerHTML = ajax.responseText;
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3.12&CodigoExamen="+CodigoExamen); //Posting txtname to Servlet

}

function ActualizarExamen(){
	var CodigoExamen=document.getElementById("cmbExamen").value;
	var NombreExamen=document.getElementById("txtNuevoExamen").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert(ajax.responseText);
					window.location.reload();
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3.13&CodigoExamen="+CodigoExamen+"&NombreExamen="+NombreExamen); //Posting txtname to Servlet

}


/*************************Fin de examen pertenece a un area*********************/
/*************************Si examen pertenece a un subarea**********************/
function VerSubarea(){
	var CodigoArea=document.getElementById("cmbArea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('subarea').innerHTML = ajax.responseText;
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3.21&CodigoArea="+CodigoArea); //Posting txtname to Servlet

}

function VerExamenesSubarea(){
	var CodigoSubarea=document.getElementById("cmbSubarea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('examen').innerHTML = ajax.responseText;
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=3.22&CodigoSubarea="+CodigoSubarea); //Posting txtname to Servlet

}

/*************************Fin de examen pertenece a un subarea******************/
/*************************Fin Actualizar Nombre de un Examen********************/
/*************************Actualizar Unidades***********************************/
function VerUnidad(){
	var CodigoUnidad=document.getElementById("cmbUnidad").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					document.getElementById('texto').innerHTML = ajax.responseText;
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4.1&CodigoUnidad="+CodigoUnidad); //Posting txtname to Servlet

}

function ActualizarUnidad(){
	var CodigoUnidad=document.getElementById("cmbUnidad").value;
	var NombreUnidad=document.getElementById("txtNuevaUnidad").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert(ajax.responseText);
					window.location.reload();
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=4.2&CodigoUnidad="+CodigoUnidad+"&NombreUnidad="+NombreUnidad); //Posting txtname to Servlet

}
/*************************Fin Actualizar Unidades*******************************/
/*************************Eliminar Area*****************************************/
function EliminarArea(){
	var CodigoArea=document.getElementById("cmbArea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert(ajax.responseText);
					window.location.reload();
				}
			}
			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=5.1&CodigoArea="+CodigoArea); //Posting txtname to Servlet

}
/*************************Fin Eliminar Area*************************************/
/*************************Eliminar Subarea**************************************/
function EliminarSubarea(){
	var CodigoSubarea=document.getElementById("cmbSubarea").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert(ajax.responseText);
					window.location.reload();
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=6.1&CodigoSubarea="+CodigoSubarea); //Posting txtname to Servlet

}
/*************************Fin Eliminar Subarea**********************************/
/*************************Eliminar Examen***************************************/
function EliminarExamen(){
	var CodigoExamen=document.getElementById("cmbExamen").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert(ajax.responseText);
					window.location.reload();
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=7.3&CodigoExamen="+CodigoExamen); //Posting txtname to Servlet

}

/*************************Fin Eliminar Examen***********************************/
/*************************Crear Backup******************************************/
function CrearBackup(){
	//alert("Entro");
	var Fecha=document.getElementById("txtFecha").value;
	var Hora=document.getElementById("txtHora").value;
	var CodUsuario=document.getElementById("txtCodUsuario").value;
	ajax=getXMLObject();
	   ajax.open("POST","ControlAdministrarParametros",true); //getname will be the servlet name
	   	   ajax.onreadystatechange=function() {
				if (ajax.readyState==4) {
					alert(ajax.responseText);
					//window.location.reload();
				}
			}			
	    ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
		ajax.send("valor=8.1&Fecha="+Fecha+"&Hora="+Hora+"&CodUsuario="+CodUsuario); //Posting txtname to Servlet

}

function Fecha(){
	  var time1 = new Date()
	  var año = time1.getFullYear()
	  var mes = time1.getMonth()
	  mes=mes+1;
	  
	  var dia = time1.getDate()

	  var temp1 = "" + ((año < 10) ? "0" : "") + año
	  temp1 += ((mes < 10) ? "-0" : "-") + mes
	  temp1 += ((dia < 10) ? "-0" : "-") + dia
	 document.getElementById('txtFecha').value = temp1
	 id = setTimeout("Fecha()",1000)
	  }

function Hora(){
	  var time = new Date()
	  var hour = time.getHours()
	  var minute = time.getMinutes()
	  var second = time.getSeconds()
	  var temp = "" + ((hour < 10) ? "0" : "") + hour
	  temp += ((minute < 10) ? "-0" : "-") + minute
	  temp += ((second < 10) ? "-0" : "-") + second
	  document.getElementById('txtHora').value = temp;
	  id = setTimeout("Hora()",1000)
	  }
/*************************Fin Crear Backup**************************************/