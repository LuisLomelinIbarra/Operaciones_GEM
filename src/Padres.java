
public class Padres {
	private String IDPadre;
	private String [] IDNino;
	private String NombreCompletoPadre;
	private String NombreCompletoMadre;
	private String CelularMadre;
	private String CelularPadre;
	private String CorreoPadre;
	private String CorreoMadre;
	private String NombreContactoDeEmergancia;
	private String TelefonoDeEmergencia;
	private String TipoDePago;
	private String InscripcionYFechas;
	private double MontoAPagar;
	
	Padres( String IDPadre, String IDNino[], String NombreCompletoPadre, String NombreCompletoMadre, String CelularMadre, String CelularPadre, String CorreoPadre, String CorreoMadre, String NombreContactoDeEmergancia,String TelefonoDeEmergencia, String TipoDePago,double Monto,String fecha/*, String[] InscripcionYFechas*/){
		
		setIDNino(IDNino);
		setNombreCompletoPadre(NombreCompletoPadre);
		setNombreCompletoMadre(NombreCompletoMadre);
		setCelularMadre(CelularMadre);
		setCelularPadre(CelularPadre);
		setCorreoPadre(CorreoPadre);
		setCorreoMadre(CorreoMadre);
		setNombreContactoDeEmergencia(NombreContactoDeEmergancia);
		setTelefonoDeEmergencia(TelefonoDeEmergencia);
		setTipoDePago(TipoDePago);
		setInscripcionYFecha(fecha);
		setMontoAPagar(Monto);
		
		this.IDPadre=IDPadre;
	}
	
	public Padres() {
		// TODO Auto-generated constructor stub
		setIDNino(null);
		setNombreCompletoPadre("");
		setNombreCompletoMadre("");
		setCelularMadre("");
		setCelularPadre("");
		setCorreoPadre("");
		setCorreoMadre("");
		setNombreContactoDeEmergencia("");
		setTelefonoDeEmergencia("");
		setTipoDePago("");
		setInscripcionYFecha(null);
		this.IDPadre="";
	}

	public void setIDNino(String[] iDNino2) {
		// TODO Auto-generated method stub
		this.IDNino=iDNino2;
	}

	//Setters
	public void setMontoAPagar(double x){
		this.MontoAPagar=x;
	}
	public void setNombreCompletoPadre(String x){
		this.NombreCompletoPadre=x;
	}
	public void setNombreCompletoMadre(String x){
		this.NombreCompletoMadre=x;
	}
	public void set(String x){
	this.NombreCompletoMadre=x;	
	}
	public void setCelularMadre(String x){
		this.CelularMadre=x;
	}
	public void setCelularPadre(String x){
		this.CelularPadre=x;
	}
	public void setCorreoPadre(String x){
		this.CorreoPadre=x;
	}
	public void setCorreoMadre(String x){
		this.CorreoMadre=x;
	}
	public void setNombreContactoDeEmergencia(String x){
		this.NombreContactoDeEmergancia=x;
	}
	public void setTelefonoDeEmergencia(String x){
		this.TelefonoDeEmergencia=x;
	}
	public void setTipoDePago(String x){
		this.TipoDePago=x;
	}
	public void setInscripcionYFecha(String x){
		this.InscripcionYFechas=x;
	}

	public String[] AumentarArrString(String[] x){
		String paso[]=new String[x.length];
		for(int i=0;i<paso.length;i++){
			paso[i]=x[i];
		}
		x=new String[paso.length+1];
		for(int i=0;i<paso.length;i++){
			x[i]=paso[i];
		}
	return x;	
	}
	//Getters
	public String getNombreCompletoPadre(){
		return this.NombreCompletoPadre;
	}
	public String getNombreCompletoMadre(){
		return this.NombreCompletoMadre;
	}
	public String getCelularMadre(){
		return this.CelularMadre;
	}
	public String getCelularPadre(){
		return this.CelularPadre;
	}
	public String getCorreoPadre(){
		return this.CorreoPadre;
	}
	public String getCorreoMadre(){
		return this.CorreoMadre;
	}
	public String getTipoDePago(){
		return this.TipoDePago;
	}
	public String getNombreDeEmergencia(){
		return this.NombreContactoDeEmergancia;
	}
	public String getTelefonoDeEmergencia(){
		return this.TelefonoDeEmergencia;
	}
	public String getInscripcionYFecha(){
		return this.InscripcionYFechas;
	}
	public String getIDPadres(){
		return this.IDPadre;
	}
	public String[] getIDNino(){
		return this.IDNino;
	}
	public double getMontoAPagar(){
		return this.MontoAPagar;
	}
	
	public void addIDNino(String x){
		String [] paso=this.IDNino;
		this.IDNino=new String [paso.length+1];
		for(int i=0;i<paso.length;i++){
			this.IDNino[i]=paso[i];
		}
		this.IDNino[this.IDNino.length-1]=x;
	}
	public void print(){
		System.out.println(getIDNino()+","+
		getNombreCompletoPadre()+","+
		getNombreCompletoMadre()+","+
		getCelularMadre()+","+
		getCelularPadre()+","+
		getCorreoPadre()+","+
		getCorreoMadre()+","+
		getTelefonoDeEmergencia()+","+
		getTelefonoDeEmergencia()+","+
		getTipoDePago()+","+
		getInscripcionYFecha()+","+
				getIDPadres()+","+getIDNino());
	}

}
