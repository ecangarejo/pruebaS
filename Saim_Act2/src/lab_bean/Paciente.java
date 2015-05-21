/**
 * bean: Paciente se encuentra los objetos
 * para la creacion de los datos del paciente(valido como modulo individual).
*/
package lab_bean;

public class Paciente {
	    String tipo_documento = "";
	    String cedula = "";
	    String apellidos = "";
		String nombre = "";
		String genero="";
		String edad="";
		String direccion = "";
		String telefono = "";		
		String email="";
		String eps="";
        String fechana="";
        public Paciente(){
			
		}
        
        public Paciente(String tip, String ced,String ape,String nom,  String gene,String dir,String tel,String ema,String eda,String ep,String fechanaci){
			tipo_documento = tip;
			cedula = ced;
			apellidos=ape;
			nombre = nom;
			genero=gene;
			edad=eda;
			direccion = dir;
			telefono = tel;
			email=ema;
		    eps=ep;
		    fechana=fechanaci;
		}
		
		public String getDocumento(){
			return tipo_documento;
		}
		public String getCedula(){
			return cedula;
		}
		public String getNombre(){
			return nombre;
		}
		public String getApellidos(){
			return apellidos;
		}
		public String getGenero(){
			return genero;
		}
		public String getEdad(){
			return edad;
		}
		
		
		public String getEps(){
			return eps;
		}
		public String getDireccion(){
			return direccion;
		}
		public String getTelefono(){
			return telefono;
		}
		
		public String getEmail(){
			return email;
		}
		public String getFechana(){
			return fechana;
		}
		
		
			
		//Modificar
		public void setDocumento(String tip){
			tipo_documento = tip;
		}
		public void setCedula(String ced){
			cedula = ced;
		}
		public void setNombre(String nom){
			nombre = nom;
		}
		public void setGenero(String gene){
			genero=gene;
		}
		public void setDireccion(String dir){
			direccion = dir;
		}
		 public void setEmail(String ema){
	        	email=ema;
			}
		 public void setTelefono(String tele){
	        	telefono=tele;
			}
		 public void setEps(String ep){
	        	eps=ep;
			}
		 public void setEada(String eda){
	        	edad=eda;
			}
		 public void setApellidos(String ape){
	        	apellidos=ape;
			}
		 public void setFechana(String fechanaci){
			 fechana=fechanaci;
			}
		
        
		
}
