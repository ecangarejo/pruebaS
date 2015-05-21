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
// ////////////////////////////////////////////////////////////////////////////////////////////////////////

function Casos(){
	
	var dia=document.getElementById('Idia').value;
	var mes=document.getElementById('Imes').value;
	var ano=document.getElementById('Iano').value;
	
	var fdia=document.getElementById('Fdia').value;
	var fmes=document.getElementById('Fmes').value;
	var fano=document.getElementById('Fano').value;
	
	var fechai=ano+"-"+mes+"-"+dia;
	var fechaf=fano+"-"+fmes+"-"+fdia;
	
	var Ent=document.getElementById('Ent').value;
	var tcaso=document.getElementById('tcaso').value;
	
	 ajax=getXMLObject();
	 ajax.open("POST","ControlConsultas",true); 
	 ajax.onreadystatechange  = function(){
			if (ajax.readyState == 4) {	
				var Rangos=ajax.responseText;
				var elem=Rangos.split('_');
				//alert(Rangos);
				var ranp=elem[0];
				var rans=elem[1];
				//alert("ranp "+ranp+" rans"+rans);	
				//alert(Ent);
				if(Ent=="todas"){
					Ent="GROUP BY aa.adm_numero_ingreso, hdi.codDiagnostico ";
				}else{
				  var e=Ent;
				  Ent="AND ae.ent_nit="+e+" GROUP BY aa.adm_numero_ingreso, hdi.codDiagnostico "
				}
				pagina2="cons_repcatendidos.jsp?fechai="+fechai+"&fechaf="+fechaf+"&Ent="+Ent+"&ranp="+ranp+"&rans="+rans;
				var opciones="toolbar=yes, menubar=yes , location=yes ";
				opciones=opciones+"scrollbars=yes, resizable=yes, width=480, height=520, top=85,  left=140";
				window.open(pagina2,"NUEVA",opciones);
			}
	 }
	 ajax.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8');
	 ajax.send("valor=casos&tcaso="+tcaso);	
}