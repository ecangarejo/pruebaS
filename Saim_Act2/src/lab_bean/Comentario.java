/**
 * bean: Comentario se encuentra los objetos
 * para la creacion de los comentarios.
*/
package lab_bean;

public class Comentario {

	String normalini="";
	String normalfin="";
	String bajoini="";
	String bajofin="";
	String altoini="";
	String altofin="";
	String bcritini="";
	String bcritfin="";
	String acritini="";
	String acritfin="";
	String codExamen_fk="";
	
public Comentario(){}
	public Comentario(String normaliniC,String normalfinC, String bajoiniC, String bajofinC, String altoiniC, String altofinC, String bcritiniC, String bcritfinC, String acritiniC, String acritfinC,String codExamen_fkC){
		normalini = normaliniC;
		normalfin=normalfinC;
		bajoini=bajoiniC;
		bajofin=bajofinC;
		altoini=altoiniC;
		altofin=altofinC;
		bcritini=bcritiniC;
		bcritfin=bcritfinC;
		acritini=acritiniC;
		acritfin=acritfinC;
		codExamen_fk=codExamen_fkC;		
	}
	
	public String getnormalini(){
		return normalini;
	}
	public void setnormalini(String normaliniC){
		normalini = normaliniC;
	}
	////////////////////////////////////////////////////
	public String getnormalfin(){
		return normalfin;
	}
	public void setnormalfin(String normalfinC){
		normalfin = normalfinC;
	}
	////////////////////////////////////////////////////
	public String getbajoini(){		
		return bajoini;
	}
	public void setbajoini(String bajoiniC){
		bajoini = bajoiniC;
	}
	/////////////////////////////////////////////////////
	public String getbajofin(){
		return bajofin;		
	}
	public void setbajofin(String bajofinC){
		bajofin = bajofinC;
	}
	///////////////////////////////////////////////////
	public String getaltoini(){
		return altoini;
	}
	public void setaltoini(String altoiniC){
		altoini = altoiniC;
	}
	/////////////////////////////////////////////////
	public String getaltofin(){
		return altofin;
	}
	public void setaltofin(String altofinC){
		altofin = altofinC;
	}
	///////////////////////////////////////////////
	public String getbcritini(){		
		return bcritini;
	}
	public void setbcritini(String bcritiniC){
		bcritini = bcritiniC;
	}
	/////////////////////////////////////////////////
	public String getbcritfin(){
		return bcritfin;		
	}	
	public void setbcritfin(String bcritfinC){
		bcritfin = bcritfinC;
	}
	//////////////////////////////////////////////
	public String getacritini(){
		return acritini;
	}
	public void setacritini(String acritiniC){
		acritini = acritiniC;
	}
	/////////////////////////////////////////////
	public String getacritfin(){		
		return acritfin;
	}
	public void setacritfin(String acritfinC){
		acritfin = acritfinC;
	}
	/////////////////////////////////////////////
	public String getcodExamen_fk(){
		return codExamen_fk;		
	}
	public void setcodExamen_fk(String codExamen_fkC){
		codExamen_fk = codExamen_fkC;
	}
	///////////////////////////////////////////
	
}
