/**
 * Control:ControlIngresoPac
 * Ingreso de los datos demograficos del paciente
 * Desarrollado:yosimar valega
 */

package adm_Controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import adm_logica.MetodoPaciente;
import adm_logica.MetodoUsuario;





public class ControlIngresoPac extends HttpServlet {
	
	/**
	 * Se obtiene el nombre del usuario con el procedimiento obtenerNomUsuario/MetodoUsuario
	 * Se obtiene los datos del paciente de demografico de la js Validaciones
	 * Se ingresa los datos en la tabla paciente.
	 */
	protected void doPost(HttpServletRequest re, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		String tip=re.getParameter("ti");
		String ced=re.getParameter("ced");
		String afiliacion=re.getParameter("afi");
		String nivel=re.getParameter("ni");
		String pape=re.getParameter("pape");
		String sape=re.getParameter("sape");
		String nom=re.getParameter("nom");
		String gene=re.getParameter("ge");
		String nacionali=re.getParameter("naci");
		String dir=re.getParameter("dir");
		String tel=re.getParameter("telefi");
		String telofi=re.getParameter("teleofici");
		String telcel=re.getParameter("telecel");
		String ocu=re.getParameter("ocu");
		String emp=re.getParameter("emp");
		String zona=re.getParameter("zona");
		String reli=re.getParameter("re");
		String estadoci=re.getParameter("esta"); 
		String ra=re.getParameter("ra");
		String estra=re.getParameter("estra");
		String ema=re.getParameter("ema");
		String mun_cod=re.getParameter("codmun");
		String fechanaci=re.getParameter("fe");
		String numcontra=re.getParameter("numco");
		String ocupacion1=re.getParameter("ocupacion1");
		String longitud=re.getParameter("long");
		String latitud=re.getParameter("lati");
		String barrio=re.getParameter("bar");
//////////////////////////////////////////////////////////////////////////////////
		
		//System.out.println(" EN POSSSSSSSTTTTT pape "+pape+" sape "+sape+" nom "+nom);
		String codigou =(String)re.getSession().getAttribute("codusuario");
		MetodoUsuario mu = new MetodoUsuario();
		MetodoPaciente pa=new MetodoPaciente();
		ResultSet rscodigou=null;
		//ResultSet rsocu=null;
		ResultSet rs=null;
		String nomusu="";
		//String codOcu="";
		String conve="";
		rs=pa.SQLCodConv(numcontra);
		try {
			
			while(rs.next()){
				conve=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
			
		} catch (SQLException e) {
			System.out.println("Error en ControlIngresoPac Nombre ResultSet=rs :: "+e);
			e.printStackTrace();
		}
		//////////////////////////////////////////////////////////////
		/*try{
			rscodigou=mu.obtenerNomUsuario(codigou);
			if(rscodigou.next()){
				nomusu=rscodigou.getString(1);
			}
			rscodigou.getStatement().getConnection().close();
			
		}catch(Exception ex){
			System.out.println("Error en ControlIngresoPac Nombre ResultSet=rscodigou :: "+ex);
   			ex.getMessage();
   		}*/
		/////////////////////////////////////////////////////////////////
		/*try{
			rsocu=mu.obtenerCodOcupacion(ocu);
			if(rsocu.next()){
				codOcu=rsocu.getString(1);
			}
			rsocu.getStatement().getConnection().close();
		}
		catch(Exception ex){
			System.out.println("Error en ControlIngresoPac Nombre ResultSet=rsocu :: "+ex);
		}*/
			MetodoPaciente pac=new MetodoPaciente();
			//pac.insertar(tip, ced, afiliacion, nivel, pape, sape, nom, gene, nacionali, dir, tel, telofi, telcel, codOcu, emp, zona, reli, estadoci, ra, estra, ema, mun_cod, fechanaci, conve);
			pac.insertar(tip, ced, afiliacion, nivel, pape, sape, nom, gene, nacionali, dir, tel, telofi, telcel, ocu, emp, zona, reli, estadoci, ra, estra, ema, mun_cod, fechanaci, conve,ocupacion1,longitud,latitud,barrio);
			
		
		
	}
	
	protected void doGet(HttpServletRequest re, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=UTF-8");
		String tip=re.getParameter("ti");
		String ced=re.getParameter("ced");
		String afiliacion=re.getParameter("afi");
		String nivel=re.getParameter("ni");
		String pape=re.getParameter("pape");
		String sape=re.getParameter("sape");
		String nom=re.getParameter("nom");
		String gene=re.getParameter("ge");
		String nacionali=re.getParameter("naci");
		String dir=re.getParameter("dir");
		String tel=re.getParameter("telefi");
		String telofi=re.getParameter("teleofici");
		String telcel=re.getParameter("telecel");
		String ocu=re.getParameter("ocu");
		String emp=re.getParameter("emp");
		String zona=re.getParameter("zona");
		String reli=re.getParameter("re");
		String estadoci=re.getParameter("esta"); 
		String ra=re.getParameter("ra");
		String estra=re.getParameter("estra");
		String ema=re.getParameter("ema");
		String mun_cod=re.getParameter("codmun");
		String fechanaci=re.getParameter("fe");
		String numcontra=re.getParameter("numco");
		String ocupacion1=re.getParameter("ocupacion1");
		String longitud=re.getParameter("long");
		String latitud=re.getParameter("lati");
		String barrio=re.getParameter("bar");
		
		System.out.println("pape "+pape+" sape "+sape+" nom "+nom);
//////////////////////////////////////////////////////////////////////////////////
		String codigou =(String)re.getSession().getAttribute("codusuario");
		MetodoUsuario mu = new MetodoUsuario();
		MetodoPaciente pa=new MetodoPaciente();
		ResultSet rscodigou=null;
		//ResultSet rsocu=null;
		ResultSet rs=null;
		String nomusu="";
		//String codOcu="";
		String conve="";
		rs=pa.SQLCodConv(numcontra);
		try {
			
			while(rs.next()){
				conve=rs.getString(1);
			}
			rs.getStatement().getConnection().close();
			
		} catch (SQLException e) {
			System.out.println("Error en ControlIngresoPac Nombre ResultSet=rs :: "+e);
			e.printStackTrace();
		}
		
		
		//////////////////////////////////////////////////////////////
		/*try{
			rscodigou=mu.obtenerNomUsuario(codigou);
			if(rscodigou.next()){
				nomusu=rscodigou.getString(1);
			}
			rscodigou.getStatement().getConnection().close();
			
		}catch(Exception ex){
			System.out.println("Error en ControlIngresoPac Nombre ResultSet=rscodigou :: "+ex);
   			ex.getMessage();
   		}*/
		/////////////////////////////////////////////////////////////////
		/*try{
			rsocu=mu.obtenerCodOcupacion(ocu);
			if(rsocu.next()){
				codOcu=rsocu.getString(1);
			}
			rsocu.getStatement().getConnection().close();
		}
		catch(Exception ex){
			System.out.println("Error en ControlIngresoPac Nombre ResultSet=rsocu :: "+ex);
		}*/
			MetodoPaciente pac=new MetodoPaciente();
			//pac.insertar(tip, ced, afiliacion, nivel, pape, sape, nom, gene, nacionali, dir, tel, telofi, telcel, codOcu, emp, zona, reli, estadoci, ra, estra, ema, mun_cod, fechanaci, conve);
			pac.insertar(tip, ced, afiliacion, nivel, pape, sape, nom, gene, nacionali, dir, tel, telofi, telcel, ocu, emp, zona, reli, estadoci, ra, estra, ema, mun_cod, fechanaci, conve,ocupacion1,"","","");
			
			
		
	}

}
