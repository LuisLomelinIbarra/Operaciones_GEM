
public class Ninos {
	//Los atributos del objeto niño, en otras palabras to la información importante de los niños
	private String NombreCompleto;
	private int Edad;
	private String FechaDeNacimineto;
	private String Escuela;
	private String GradoEscolar;
	private String Direccion;
	private int CodigoPostal;
	private int TelefonoDeCasa;
	private String[] Enfermedades;
	private String[] Alergias;
	private String[] Medicamentos;
	private String TipoDeSangre;
	private String IDNino;
	private String IDPadres;
	private boolean ClaseDePrueba;
	private String HorasDeClase;
	private boolean [] diasSesion;
	private boolean TipoDeSesion;
	private boolean Inscirto=true;
	private double descuentos;
	

	
	public Ninos(String IDN,String Nombre,int Edad,String FechaN,String Escu,String Grad,String Dir, int CodP, int TelC, String[] Enf, String[] Alg, String [] Med, String TipSan, boolean Prueba,String Horas,boolean[] dias,boolean tipo,double desc){
		setNombre(Nombre);
		setEdad(Edad);
		setFechaDeNacimiento(FechaN);
		setEscuela(Escu);
		setGradoEscolar(Grad);
		setDireccion(Dir);
		setCodigoPostal(CodP);
		setTelefonoCasa(TelC);
		setEnfermedades(Enf);
		setAlergias(Alg);
		setMedicamentos(Med);
		setTipoDeSangre(TipSan);
		setClaseDePrueba(Prueba);
		setHorasDeClase(Horas);
		setDiasSesion(dias);
		setTipoDeSesion(tipo);
		setIDNino(IDN);
		setInscrito(true);
		setDescuentos(desc);
		
	}
	public Ninos(String IDN,String Nombre,int Edad,String FechaN,String Escu,String Grad,String Dir, int CodP, int TelC, String[] Enf, String[] Alg, String [] Med, String TipSan, boolean Prueba,String Horas,boolean[] dias,boolean tipo,String IDP,boolean Ins,double desc){
		setNombre(Nombre);
		setEdad(Edad);
		setFechaDeNacimiento(FechaN);
		setEscuela(Escu);
		setGradoEscolar(Grad);
		setDireccion(Dir);
		setCodigoPostal(CodP);
		setTelefonoCasa(TelC);
		setEnfermedades(Enf);
		setAlergias(Alg);
		setMedicamentos(Med);
		setTipoDeSangre(TipSan);
		setClaseDePrueba(Prueba);
		setHorasDeClase(Horas);
		setDiasSesion(dias);
		setTipoDeSesion(tipo);
		setIDNino(IDN);
		setIDPadres(IDP);
		setInscrito(Ins);
		setDescuentos(desc);
	}
	
	
	Ninos(){
		this.IDNino="";
	}
	
	public void setDescuentos(double descuentos) {
		this.descuentos = descuentos;
	}
	public void setInscrito(boolean x){
		this.Inscirto=x;
	}
	public void setDiasSesion(boolean[] dias) {
		// TODO Auto-generated method stub
		this.diasSesion=dias;
	}
	public void setEdad(int edad2) {
		// TODO Auto-generated method stub
		this.Edad=edad2;
	}
	//Setters
	public void setTelefonoCasa(int x){
		this.TelefonoDeCasa=x;
	}
	
	public void setNombre(String x){
		this.NombreCompleto=x;
	}
	public void setFechaDeNacimiento(String x){
		this.FechaDeNacimineto=x;
	}
	public void setEscuela(String x){
		this.Escuela=x;
	}
	public void setGradoEscolar(String x){
		this.GradoEscolar=x;
	}
	public void setDireccion(String x){
		this.Direccion=x;
	}
	public void setCodigoPostal(int x){
		this.CodigoPostal=x;
	}
	public void setEnfermedades(String x,int posicion){
		this.Enfermedades[posicion]=x;
	}
	public void setEnfermedades(String[] x){
		this.Enfermedades=x;
	}
	public void setAlergia(String x,int posicion){
		this.Alergias[posicion]=x;
	}
	public void setAlergias(String[] x){
		this.Alergias=x;
	}
	public void setMedicamentos(String x,int posicion){
		this.Medicamentos[posicion]=x;
	}
	public void setMedicamentos(String[] x){
		this.Medicamentos=x;
	}
	public void setTipoDeSangre(String x){
		this.TipoDeSangre=x;
	}
	public void setIDNino(String x){
		this.IDNino=x;
	}
	public void setIDPadres(String x){
		this.IDPadres=x;
	}
	public void setClaseDePrueba(boolean x){
		this.ClaseDePrueba=x;
	}
	public void setHorasDeClase(String x){
		this.HorasDeClase=x;
	}
	public void setTipoDeSesion(boolean x){
		this.TipoDeSesion=x;
	}
	//Getters
	
	public String getNombre(){
		return this.NombreCompleto;
	}
	public String getFechaDeNacimiento(){
		return this.FechaDeNacimineto;
	}
	public String getEscuela(){
		return this.Escuela;
	}
	public String getGradoEscolar(){
		return this.GradoEscolar;
	}
	public String getDireccion(){
		return this.Direccion;
	}
	public int getCodigoPostal(){
		return this.CodigoPostal;
	}
	public String getEnfermedades(int posicion){
		return this.Enfermedades[posicion];
	}
	public String[] getEnfermedades(){
		return this.Enfermedades;
	}
	public String getAlergia(int posicion){
		return this.Alergias[posicion];
	}
	public String[] getAlergias(){
		return this.Alergias;
	}
	public String getMedicamentos(int posicion){
		return this.Medicamentos[posicion];
	}
	public String[] getMedicamentos(){
		return this.Medicamentos;
	}
	public String getTipoDeSangre(){
		return this.TipoDeSangre;
	}
	public String getIDNino(){
		return this.IDNino;
	}
	public String getIDPadres(){
		return this.IDPadres;
	}
	public boolean getClaseDePrueba(){
		return this.ClaseDePrueba;
	}
	public String getHorasDeClase(){
		return this.HorasDeClase;
	}
	public boolean getTipoDeSesion(){
		return this.TipoDeSesion;
	}
	public int getEdad() {
		// TODO Auto-generated method stub
		return this.Edad;
	}
	public double getDescuentos() {
		return descuentos;
	}


	public int getTelefonoCasa() {
		// TODO Auto-generated method stub
		return this.TelefonoDeCasa;
	}
	public boolean [] getdiasDeSesion(){
		return this.diasSesion;
	}
	public boolean getInscrito(){
		return this.Inscirto;
	}
	
	public void print(){
		System.out.println(getNombre()+","+
		getEdad()+","+
		getFechaDeNacimiento()+","+
		getEscuela()+","+
		getGradoEscolar()+","+
		getDireccion()+","+
		getCodigoPostal()+","+
		getTelefonoCasa()+","+
		getEnfermedades()+","+
		getAlergias()+","+
		getMedicamentos()+","+
		getTipoDeSangre()+","+
		getClaseDePrueba()+","+
		getHorasDeClase()+","+
		getDireccion()+","+
		getdiasDeSesion()+","+
		getTipoDeSesion()+","+
		getIDNino());
		
		
	}



	
	

}
