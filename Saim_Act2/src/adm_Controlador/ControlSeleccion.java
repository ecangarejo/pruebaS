/**
 * Control:ControlSeleccion
 * Realiza una busquedad segun la ubicacion del paciente el tipo de paciente.
 * Desarrollado:yosimar valega
 */

package adm_Controlador;
import java.sql.*;
import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoAdmision;
import adm_logica.MetodoPaciente;
import adm_logica.MetodoPreingreso;
import adm_logica.MetodoRemision;
import adm_logica.MetodoUsuario;

import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
//import bean.Urgencia_vital;

/**
 * Servlet implementation class ControlSeleccion
 * 
 * 
 */
public class ControlSeleccion extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Se obtiene los datos de la cedula  tipo, codigo de urgencia si hay de validarcom/Adm_ingreso.jsp
	 * Se obtiene el nombre del ususario segune el codigo obtenerNomUsuario/MetodoUsuario
	 * Se busca en cada una de las tablas de la base de datos (adm_cola,adm_preadmision,adm_pacientes) de 
	 * SQLEPS/MetodoPreingreso,SQLEPS/MetodoRemision,SQLEntidad/MetodoPaciente
	 * Si es un nacido vivo se busca los datos de la madre.Si la madre no existe se informa q tiene q ingresar primero a la madre
	 * Si esta se redirecciona a ingresar los datos del niï¿½o
	 * Si es paciente no esta se redirecciona a la pagina Adm_Demograficos
	 * Si el paciente tiene ya los datos demograficos se procede a mostrar los demas datos para ingresar la admision.
	 * 
	 */
		
	protected void doGet(HttpServletRequest re, HttpServletResponse res) throws ServletException, IOException{
		String ced,tipodoc,x,nombrepac = null,papellidos = null,sapellidos = null,y=null,direccion=null,municipio=null,departamento=null,pais=null,longitud=null,latitud=null,barrio=null,codepart=null,codpais=null,codigomuni=null;
		x="";
		String nomd="";
		String prid="";
		String sed="";
		String codCon="";
		String estado="";
		String codigocama2="";
		String codigocama="";		
		//capturar variable de session codigo del usuario
		res.setContentType("text/html;charset=utf-8");
		MetodoAdmision ad =new MetodoAdmision();
		String codigou =(String)re.getSession().getAttribute("codusuario");
		MetodoUsuario mu = new MetodoUsuario();
		ResultSet rscodigou=null;
		ResultSet rsconv=null;
		String nomusu="";
		try{
			rscodigou=mu.obtenerNomUsuario(codigou);
			if(rscodigou.next()){
				nomusu=rscodigou.getString(1);
			}			
			rscodigou.getStatement().close();
		}catch(Exception ex){
   			ex.getMessage();
   		}		
		java.sql.ResultSet rs=null;
		ced = re.getParameter("ced");
		tipodoc=re.getParameter("tipo");
		String CodColaFin=re.getParameter("CodColaFin");
		
		
		 prid=re.getParameter("txtpapellido");
		 sed=re.getParameter("txtsapellido");
		 nomd=re.getParameter("txtnombre");
		String txtfechanaci=re.getParameter("txtfechanaci");
		String txtgs=re.getParameter("txtgs");
		String sexo=re.getParameter("sexo");
		
    	
    	System.out.println("1111111111111 "+prid+" - "+sed+" - "+nomd+" - "+txtfechanaci);
		
		
		String id=null;
		id=re.getParameter("id");
		MetodoPaciente pa1 =new MetodoPaciente();
		try{
			MetodoPreingreso pr=new MetodoPreingreso();
			rs=pr.SQLEPS(ced, tipodoc);
            if (rs.next()){
			   x=rs.getString(1);
			   nomd=rs.getString(9);
			   prid=rs.getString(7);
			   sed=rs.getString(8);
			   try{
       			ResultSet res2=null;
       			res2=pa1.SQLCodConv(x);
       			while(res2.next()){
       				codCon=res2.getString(1);
       			}//fin del while
       			res2.getStatement().close();
       		}//fin try
       		catch(Exception ex){
       			ex.getMessage();
       		}  
       		rs.getStatement().close();
            }
               if(x==""){
            	   MetodoRemision remi=new MetodoRemision();
        			  rs=remi.SQLEPS(ced, tipodoc);
        			  if (rs.next()){
        				  x=rs.getString(1);        				 
        		          nomd=rs.getString(3);        		         
        		          prid=rs.getString(4);        		        
        		          sed=rs.getString(6);        		          
        		          codigocama2=rs.getString(10);        		          
        		          ResultSet rscodcam=null;
        		          rscodcam=remi.codigocama(codigocama2);
        		          if(rscodcam.next()){
        		        	  codigocama=rscodcam.getString(1);
        		          }
        		          rscodcam.getStatement().getConnection().close(); 
        		          try{        		       			
        		       			ResultSet res2=null;
        		       			res2=pa1.SQLCodConv(x);
        		       			while(res2.next()){
        		       				codCon=res2.getString(1);        		       											
        		       			}//fin del while
        		       			res2.getStatement().close();
        		       		}//fin try
        		       		catch(Exception ex){
        		       			ex.getMessage();
        		       		}         		       		
                      }
        			  rs.getStatement().close();
               }              
              	  MetodoPaciente pa=new MetodoPaciente();
     			  rs=pa.SQLEntidad(ced, tipodoc);     			 
                  if (rs.next()){
                	  y=rs.getString(1);
                	  nombrepac=rs.getString(2);
                	  papellidos=rs.getString(3);
                	  sapellidos=rs.getString(4);
                	  direccion=rs.getString(5);
                	  municipio=rs.getString(6);
                	  departamento=rs.getString(7);
                	  pais=rs.getString(8);
                	  longitud=rs.getString(9);
                	  latitud=rs.getString(10);
                	  barrio=rs.getString(11);
                	  codepart=rs.getString(12);
    				  codpais=rs.getString(13);
    				  codigomuni=rs.getString(14);
                	 // byte[] aux = rs.getBytes(5);
                	  //direccion = new String(aux,"utf-8"); 
                	  
     		      }
                  rs.getStatement().close();   
                  
            if(y==null){
            	//y="";
            	System.out.println("222222222 "+prid+" - "+sed+" - "+nomd+" - "+txtfechanaci);
        		
            	res.sendRedirect("Adm_Demografico.jsp?cedula="+ced+"&nombre="+nomd+"&primerape="+prid+"&segundoape="+sed+"&eps="+x+"&codconv="+codCon+"&tipo="+tipodoc+"&codcama="+codigocama+"&nomUsu="+nomusu+"&id="+id+"&CodColaFin="+CodColaFin+"&txtfechanaci="+txtfechanaci+"&txtgs="+txtgs+"&sexo="+sexo);
            }
            else{
            	/*if(tipodoc.equals("Nacido Vivo")){            		
            		String nomadre="",primadre="",semadre="";
            		MetodoPaciente ma= new MetodoPaciente();
            		try{            			
            			ResultSet resul=null;
            			resul=ma.SQLPacMadre(ced);
            			while(resul.next()){
            				nomadre=resul.getString(1);
            				primadre=resul.getString(2);
            				semadre=resul.getString(3);            											
            			}//fin del while
            			resul.getStatement().close();	
            		}//fin try
            		catch(Exception ex){
            			ex.getMessage();
            		}            		
                  	if(nomadre.equals("")){                  		
                  		 res.sendRedirect("Adm_Ingreso.jsp?s="+1+"&va=1&codigo=1");
                  	}else{
                  		res.sendRedirect("Adm_Nacido_Vivo.jsp?cedula="+ced+"&tipo="+tipodoc+"&nomadre="+nomadre+"&primadre="+primadre+"&semadre="+semadre+"");
                  	}            		
            	}*//*else{            		
            		if(x.equals("")){
            			x="SELECCIONE...";
            		}            		
            		 int i;            		    
            		    for(i=0;i<prid.length();i++){
            		      prid=prid.replace('ï¿½','9');
            		      prid=prid.replace('ï¿½','8');       		     
            		    }  
            		    for(i=0;i<sed.length();i++){
            		    	 sed=sed.replace('ï¿½','9');
                 		      sed=sed.replace('ï¿½','8');
              		    }            		   
            		    for(i=0;i<nomd.length();i++){
            		    	nomd=nomd.replace('ï¿½','9');
                		      nomd=nomd.replace('ï¿½','8');
             		    }            		   
            	    res.sendRedirect("Adm_Demografico.jsp?cedula="+ced+"&nombre="+nomd+"&primerape="+prid+"&segundoape="+sed+"&eps="+x+"&codconv="+codCon+"&tipo="+tipodoc+"&codcama="+codigocama+"&nomUsu="+nomusu+"&id="+id);
            	}            		
             }*///else{
            	/* if(tipodoc.equals("Nacido Vivo")){            		 
            		 String nombre=nombrepac+" "+papellidos+" "+sapellidos;           		 
             		String nomadre="",primadre="",semadre="";
             		MetodoPaciente ma= new MetodoPaciente();
             		try{             			
             			ResultSet resul=null;
             			resul=ma.SQLPacMadre(ced);
             			while(resul.next()){
             				nomadre=resul.getString(1);
             				primadre=resul.getString(2);
             				semadre=resul.getString(3);             											
             			}//fin del while
             			resul.getStatement().close();
             		}//fin try
             		catch(Exception ex){
             			ex.getMessage();
             		}            		 
            		 res.sendRedirect("ControlNacido?cedula="+ced+"&tipo="+tipodoc+"&nomadre="+nomadre+"&primadre="+primadre+"&semadre="+semadre+"");
            	 }*///else{
            		 String nombrecompleto=nombrepac+" "+papellidos+" "+sapellidos;
            		 int i;         		 
         		    for(i=0;i<nombrecompleto.length();i++){
         		    	nombrecompleto=nombrecompleto.replace('ñ','9');
         		    	nombrecompleto=nombrecompleto.replace('Ñ','8');         		     
         		    }
            		ResultSet rspac=null;          		    
            		rspac=pa.SQLPacPa(ced, tipodoc);
            		String codpac="";
         		    if(rspac.next()){
         		    	codpac=rspac.getString(1);         		    	
         		    	ResultSet rsadmi=null;
         		    
         		    	rsadmi=ad.comprobaradmision(codpac);
         		    	if(rsadmi.next()){         		    	
         		    		estado=rsadmi.getString(1);         		    		
         		    	}
         		    	rspac.getStatement().close();
         		    	rsadmi.getStatement().close();
         		    }
         		    if(estado.equals("0")){
         		    	// estado de la admision activa
         		    	res.sendRedirect("Adm_Ingreso.jsp?eps="+y+"&cedula="+ced+"&z="+0+"&men="+1+"&va=1&codigo=1&CodColaFin="+CodColaFin);
         		    }else{  
         		    	//no tiene admision.
         		    	/**aqui va la consulta q valida si el paciente tiene convenio**/
         		    	rsconv=ad.ComprobarConvenioEntidadPaciente(codpac);
         		    	if(rsconv.next()){
         		    		//System.out.println("DATO"+" "+direccion);
         		    		//String cesar = URLDecoder.decode(direccion, "UTF-8");	
         		          //  String s = "%C3%A4";		
         		   		 //   //String utf8Decode = URLDecoder.decode(s, "UTF-8");
         		    	//	 + Server.UrlEncode(res.Url.ToString()));
         		    	
         		    	//	java.net.URLEncoder.encode( str, "UTF-8");
         		    		
         		    		//response.sendRedirect("/path/index.jsp?type=" + URLEncoder.encode(e.getType(), "UTF-8")
         		    		//	    + "&message=" + URLEncoder.encode(e.getMessage(), "UTF-8"));
         		   		     //System.out.println(utf8Decode);
         		    		System.out.println("valor codigo "+codigomuni);
         		    		res.sendRedirect("Adm_Ingreso.jsp?eps="+y+"&cedula="+ced+"&tipo="+tipodoc+"&nom="+nombrecompleto+"&z="+1+"&id="+id+"&codcama="+codigocama+"&nomUsu="+nomusu+"&va=1&codigo=1&CodColaFin="+CodColaFin+"&dire="+java.net.URLEncoder.encode( direccion, "UTF-8")+"&muni="+municipio+"&dep="+departamento+"&pais="+pais+"&long="+longitud+"&lati="+latitud+"&bar="+barrio+"&CodDep="+codepart+"&CodPais="+codpais+"&codmuni="+codigomuni);
         		    		System.out.println("Adm_Ingreso.jsp?eps="+y+"&cedula="+ced+"&tipo="+tipodoc+"&nom="+nombrecompleto+"&z="+1+"&id="+id+"&codcama="+codigocama+"&nomUsu="+nomusu+"&va=1&codigo=1&CodColaFin="+CodColaFin+"&dire="+java.net.URLEncoder.encode( direccion, "UTF-8")+"&muni="+municipio+"&dep="+departamento+"&pais="+pais+"&long="+longitud+"&lati="+latitud+"&bar="+barrio+"&CodDep="+codepart+"&CodPais="+codpais+"&codmuni="+codigomuni);
         		    		
         		    	}else{
         		    		res.sendRedirect("Adm_Ingreso.jsp?Noeps=nc");
         		    	}
         		    	rsconv.getStatement().close();
                     	
         		    }
         		   // }
		     }
            }		 
		catch(SQLException e){           
			
		}			
	}
}