
public class Talleristas {
	private String NombreCompleto;
	private boolean[] CapacitacionParaEnsenar;//Cada posición indica un producto diferente el cual el tallerista puede manejar.
	private int[] FrecuenciasTrabajadas={0};
	private String NumeroTelefonico;
	private String IDTallerista;
	private double salario=100;
	
	
	Talleristas(String Nombre, boolean[] capacitacion, String NumeroTel, String ID,double sal){
		setNombreCompleto(Nombre);
		setCapacitacionParaEnsenar(capacitacion);
		//setFrecuenciasTrabajadas(Frecuencias);
		setNumeroTelefonico(NumeroTel);
		setIDTallerista(ID);
		setSalario(sal);
		
	}
	Talleristas(String Nombre, boolean[] capacitacion,int[] Frecuencias ,String NumeroTel, String ID,double sal){
		setNombreCompleto(Nombre);
		setCapacitacionParaEnsenar(capacitacion);
		setFrecuenciasTrabajadas(Frecuencias);
		setNumeroTelefonico(NumeroTel);
		setIDTallerista(ID);
		setSalario(sal);
		
	}
	
	public void print(){
		System.out.println(getNombreCompleto()+","+
		getCapacitacionParaEnsenar()+","+
		//getFrecuenciasTrabajadas()+","+
		getNumeroTelefonico()+","+
		getIDTallerista());
	}
	
	public Talleristas() {
		// TODO Auto-generated constructor stub
		this.IDTallerista="";
	}

	//setters
	public void setNombreCompleto(String x){
		this.NombreCompleto=x;
	}
	public void setCapacitacionParaEnsenar(boolean[] x){
		this.CapacitacionParaEnsenar=x;
	}
	public void setCapacitacionParaEnsenar(boolean x,int posicion){
		this.CapacitacionParaEnsenar[posicion]=x;
	}
	public void setFrecuenciasTrabajadas(int[] x){
		this.FrecuenciasTrabajadas=x;
	}
	public void setFrecuenciasTrabajadas(int x, int posicion){
		this.FrecuenciasTrabajadas[posicion]=x;
	}
	public void setNumeroTelefonico(String x){
		this.NumeroTelefonico=x;
	}
	public void setIDTallerista(String x){
		this.IDTallerista=x;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}
	//getters
	public String getNombreCompleto(){
		return this.NombreCompleto;
	}
	public boolean [] getCapacitacionParaEnsenar(){
		return this.CapacitacionParaEnsenar;
	}
	public boolean getCapacitacionParaEnsenar(int posicion){
		return this.CapacitacionParaEnsenar[posicion];
	}
	public int [] getFrecuenciasTrabajadas(){
		return this.FrecuenciasTrabajadas;
	}
	public int getFrecuenciasTrabajadas(int posicion){
		return this.FrecuenciasTrabajadas[posicion];
	}
	public String getNumeroTelefonico(){
		return this.NumeroTelefonico;
	}
	public String getIDTallerista(){
		return this.IDTallerista;
	}
	public double getSalario() {
		return this.salario;
	}


}
