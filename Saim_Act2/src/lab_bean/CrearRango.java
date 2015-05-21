/**
 * bean: CrearRango se encuentra los objetos
 * para la creacion de los rangos.
*/
package lab_bean;

public class CrearRango {

	String valIni="";
	String valFin="";
	String codUni="";
	String codGen="";
	String codCom="";
	String codExa="";
	String edaIni="";
	String edaFin="";
	
public CrearRango(){}
	public CrearRango(String valIniR,String valFinR, String codUniR, String codGenR,String codComR,String codExaR,String edaIniR,String edaFinR){
		
		valIni=valIniR;
		valFin=valFinR;
		codUni=codUniR;
		codGen=codGenR;
		codCom=codComR;
		codExa=codExaR;
		edaIni=edaIniR;
		edaFin=edaFinR;
		
		
	}
	public String getedaIni(){
		return edaIni;
	}
	public String getedaFin(){
		return edaFin;
	}
	public String getvalIni(){
		return valIni;
	}
	public String getvalFin(){
		return valFin;
	}
	public String getcodUni(){		
		return codUni;
	}
	public String getcodGen(){
		return codGen;		
	}
	public String getcodCom(){
		return codCom;
	}
	public String getcodExa(){
		return codExa;
	}
	//Modificar
	public void setedaFin(String edaFinR){
		edaFin = edaFinR;
	}
	public void setedaIni(String edaIniR){
		edaIni = edaIniR;
	}
	public void setvalIni(String valIniR){
		valIni = valIniR;
	}
	public void setvalFin(String valFinR){
		valFin = valFinR;
	}
	public void setcodUni(String codUniR){
		codUni=codUniR;		
	}
	public void setcodGen(String codGenR){
		codGen=codGenR;		
	}
	public void setcodCom(String codComR){
		codCom=codComR;
	}
	public void setcodExa(String codExaR){
		codExa=codExaR;
	}
	
	
}
