
public class Balance {
	private double valorP;
	private String NombreC;
	private String Descripcion;
	
	public Balance(double valorP,String NombreC,String Discripcion){
		setValorP(valorP);
		setNombreC(NombreC);
		setDescripcion(Discripcion);
	}
	public Balance(){
		setValorP(0);
		setNombreC("");
		setDescripcion("");
	}
	
	
	
	public double getValorP() {
		return this.valorP;
	}
	public void setValorP(double valorP) {
		this.valorP = valorP;
	}
	public String getNombreC() {
		return this.NombreC;
	}
	public void setNombreC(String nombreC) {
		this.NombreC = nombreC;
	}
	public String getDescripcion() {
		return this.Descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.Descripcion = descripcion;
	}
	
}
