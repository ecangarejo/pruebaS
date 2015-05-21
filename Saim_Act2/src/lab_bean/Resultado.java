/**
 * bean: Resultado se encuentra los objetos
 * para la creacion de los resultados de los examenes del paciente.
*/
package lab_bean;

public class Resultado {
	    String codigopaciente= "";
	    String codigoexamen = "";
	    String fecha = "";
		String hora = "";
		String ingreso = "";
		String modificado = "";
		String resultado="";
		String cedula="";
		String codord="";
		
		  public Resultado(){
				
			}
	        
	        public Resultado(String codpac,String codexa,String fe,String ho, String usu, String modi,String re,String ced,String codord_fk){
	        	codigopaciente = codpac;
	        	codigoexamen = codexa;
	        	fecha=fe;
	        	hora = ho;
	        	ingreso=usu;
	        	modificado=modi;
	        	resultado=re;
	        	cedula=ced;
	        	codord=codord_fk;
			
			}
	        public String getcodord_fk(){
				return codord;
			}
	        public String getResultado(){
				return resultado;
			}
	        public String getModificado(){
				return modificado;
			}
			
	        public String getIngreso(){
				return ingreso;
			}
			public String getPaciente(){
				return codigopaciente;
			}
			public String getExamen(){
				return codigoexamen;
			}
			public String getFecha(){
				return fecha;
			}
			public String getHora(){
				return hora;
			}
			public String getcedula(){
				return cedula;
			}
			//Modificar
			public void setcodord_fk(String codord_fk){
				codord = codord_fk;
			}
			public void setcedula(String ced){
				cedula = ced;
			}
			public void setResultado(String re){
				resultado = re;
			}
			public void setModificado(String modi){
				modificado = modi;
			}
			public void setIngreso(String usu){
				ingreso = usu;
			}
			public void setPaciente(String pac){
				codigopaciente = pac;
			}
			public void setExamen(String exa){
				codigoexamen = exa;
			}
			public void setFecha(String fe){
				fecha = fe;
			}
			public void setHora(String ho){
				hora=ho;
			}
			
		

}
