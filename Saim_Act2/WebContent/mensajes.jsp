<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="adm_logica.Conexion" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Mensajes</title>
<link rel="STYLESHEET" type="text/css" href="estilo_lab1.css">
<script language="javascript" type="text/javascript" src="ajaxseguridad.js"></script>
<link rel="StyleSheet" href="dtree.css" type="text/css" />
<script type="text/javascript" src="dtree.js"></script>

<script language="javascript" type="text/javascript" src="clickderecho.js"></script>


<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript" src="thickbox.js"></script>
<link rel="stylesheet" href="thickbox.css" type="text/css" />

<style type="text/css">

#bubble {
    list-style: none;
    margin: 0px;
    padding: 0px;
}
#bubble li {
    display: inline-block;
    margin: 0px;
    padding: 0px;

}

#bubble li a.icon {
    background: url(Imagenes/sprite.jpg) no-repeat;
    border: none;
    display: block;
    width: 40px;
    height: 35px;
   
}

#bubble li a.Inicio {
    background-position: -1px -1px;
}
#bubble li a.Inicio:hover {
    background-position: -55px -1px;
}

#bubble li a.NuevoMens {
	background-position: -102px -1px;
	}
#bubble li a.NuevoMens:hover{
	background-position: -150px -1px;
	}
	
#bubble li a.Contacto{
	background-position: -198px -1px;
	}
#bubble li a.Contacto:hover{
	background-position: -235px -1px;
	}
	
#bubble  li a.Historial{
	background-position: -280px -1px;
	}
#bubble li a.Historial:hover{
	background-position: -340px -1px;
	}
	
</style>

<%
String codigou =(String)session.getAttribute("codusuario");
int  val=1;
if(codigou==null){
	codigou="";
	
}
%>
<script language="javascript" type="text/javascript" >
function mensajes(){
	var CodUsuario2=<%=codigou%>;
	MostrarListMen(CodUsuario2);
	
}


</script>
</head>

<body id="back" onload="mensajes()">

			<%
			if(codigou.equals("")){%>
			<script language="javascript">alert("Usted No Tiene Permiso Para ver esta Pagina....");
			window.location.href="Seg_login.jsp";
			</script>

			<%}else{
				Conexion con=new Conexion();
				ResultSet rs2;
				Statement st2 = null;
				String idu="";          	
				st2 = con.conn.createStatement();
				rs2=st2.executeQuery("select usuario from seg_usuario  where usu_codigo="+codigou+"");
				while(rs2.next()){	
					idu=rs2.getString(1);
					}
					rs2.getStatement().close();
								//con.cerrar();
					
			%>

								
					<table width="100%" align="center">
					<tr>
					<td>
					<table width="100%">
					<tr>
					<td align="center">
								<img src='Imagenes/encmens.JPG' width='50%'>
										</td>
										<td class="style66">
											Servicio de Mensajeria SAIM
										</td>
										<td align="right" class="style11"  id="usuario">
												<div align="right"> Bienvenido,<b><%=idu %></b> </div>	
										</td>
										</tr>
										</table>
									</td>
									</tr>		
									
									<tr>
									<td>
											<br>
											<ul id="bubble">
												
										      <li>
										        <a class="icon Inicio" href="mensajes.jsp" title="Inicio"></a>
										      </li>
										      <li>
										        <a class="icon NuevoMens" onclick="NuevoMen('<%=codigou %>')" title="Nuevo Mensaje"></a>
										      </li>
										      <li>
													
										        <a class="icon Contacto" onclick="MostrarListUsu('<%=codigou%>','<%=val%>')" title="Usuarios Conectados"></a>
										      </li>
												<li>
													
										        <a class="icon Historial" onclick="HistorialChat('<%=codigou%>')" title="Historial Chats Recibidos"></a>
										      </li>
										      </ul>	
									</td>
									</tr>
									<tr>
										<td>
										<table  width="100%">
												
												<tr>
												<td>
												
												<div id="Listausuarios" class="styleMensaje" width="30%"></div>
												<div id="VistaChat" class="styleMensaje" width="30%" ></div>
												</td>
												<td>
												<div id=oculto width="30%" ></div>
												<div id="ListaMensajes" class="styleMensaje" width="80%" ></div>
												<div id="VistaMensajes" width="80%"></div>
												</td>
												<td>
												<div id=ChatNuevo class="styleMensaje" width="30%"></div>
												<div id=Historial class="styleMensaje" width="30%"></div>
												<div id=VistaHist class="styleMensaje" width="30%"></div>
												</td>
											</tr>
										</table>
																						
										</td>
									</tr>
									<tr width="60%">
									<td width="60%">
									
									</td>
									</tr>
						</table>
							
							<%	
          
 }	%>
		
	
</body>
</html>