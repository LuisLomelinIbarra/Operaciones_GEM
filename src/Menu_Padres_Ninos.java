//.MKDIR()

/*
 * 
 * Agregar una ventana de confirmación a borrar papás
 * Arreglar lo que permite la introducción de ID cuando no haya uno válido escrito
 * Agregar consultar en tablas
 * 
 * 
 * 
 * */
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.w3c.dom.events.EventException;

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;
public class Menu_Padres_Ninos extends JFrame {
	private static boolean tipo;//Verdadreo padre falso nino
	public static Padres[] padres;//Arreglo de objeto Padre
	public static Ninos[] ninos;//Arreglo de objeto niño
	private JButton Jedicion;//Boton para editar la infromación de una instancia
	private JButton Jcrear;//Boton para crear una instancia del objeto especififcado
	private JButton Jborrar;//Boton para borrar la información de una instancia
	private JButton Jconsultar;//Boton para consultar la información individual de una instancia
	private JButton JTab;//Boton para ver y editar la información en tabla
	public static boolean vis=true;// Valor de la visibilidad de la ventana
	
	public int posicion;
	static int pos=0;
	static int buscID=0;
	static String NumInvalid="ABCDEFGHIJKMLNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÑñ*/+[](){}_,<>!#$%&=?¡¿?'¨";//Valores invalidos para un número
	static String StriInvalid="0123456789";//Valores invalidos para un String
	//Componentes de consultar ninos
	JLabel NombreClb;
	JLabel Edadlb;
	JLabel FechaDeNaciminetolb;
	JLabel Escuelalb;
	JLabel GradoEscolarlb;
	JLabel Direccionlb;
	JLabel CodigoPostallb;
	JLabel TelefonoDeCasalb;
	JLabel Enfermedadeslb;
	JLabel Alergiaslb;
	JLabel Medicamentoslb;
	JLabel TipoDeSangrelb;
	JLabel ClaseDePruebalb;
	JLabel HorasDeClaselb;
	JLabel diasSesionlb;
	JLabel TipoDeSesionlb;
	JLabel IDPadreslb;
	JLabel IDNinoslb;
	JLabel Inscripcion;
	JLabel Desceunto;
	//Componentes de consultar Padres
	JLabel IDPadre;
	JLabel IDNino;
	JLabel NombreCompletoPadre;
	JLabel NombreCompletoMadre;
	JLabel CelularMadre;
	JLabel CelularPadre;
	JLabel CorreoPadre;
	JLabel CorreoMadre;
	JLabel NombreContactoDeEmergancia;
	JLabel TelefonoDeEmergencia;
	JLabel TipoDePago;
	JLabel InscripcionYFechas;
	JLabel Montolb;
	
	public Menu_Padres_Ninos(boolean tipo)throws IOException{
		//Identifica y define el tipo de menú a a utilizar
		this.tipo=tipo;
		System.out.println("Clase Padres y Ninos");
		System.out.println("IF TRUE PADRES ELSE NINOS: "+tipo);
		//Se lee y procesa la información de los archivos
		padres=generarPadresArr(readFilePadres());
		ninos=generarArrNinos(readFileNinos());
		//Define el titulo dependiendo de la operación
		String titulo;
		if(tipo){
			titulo="Padres";
		}else{
			titulo="Niños";
		}
		
		//Set de herramientas para crear ventanas
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		setSize((size.width)/2,size.height/2);
		setTitle("Menú "+titulo);
		setLocation(size.width/3,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		//Se inicializan los botones del menú con el engrabado correcto 
		Jcrear=new JButton("Registrar "+titulo);
		Jedicion=new JButton("Editar datos de "+titulo);
		Jborrar=new JButton("Borrar del registro de "+titulo);
		Jconsultar=new JButton("Consultar datos de "+titulo);
		JTab=new JButton("Ver y editar en tabla");
		Jcrear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("crear");
				if(tipo){
					ventanaCrearPadres();
				}else{
					crearNino();
				}
				}
			});
		
		Jedicion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("editar");
			if(tipo){
				buscarId(1);
			}else{
				buscarId(0);
			}
				}
			});
		
		Jborrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.out.println("borrar");
				borrar();
				}
			});
		
		Jconsultar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
			//buscarId();
				System.out.println("consultar");
			if(tipo){
				consultarPadre();
			}else{
				consultarNino();
				}
			
			
			}
			
			});
		JTab.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				tablaCon();
			}
		});
		
		lim.gridx=0;
		lim.gridy=0;
		display.add(Jcrear, lim);
		lim.gridx=1;
		lim.gridy=0;
		display.add(Jedicion, lim);
		lim.gridx=2;
		lim.gridy=0;
		display.add(Jborrar, lim);
		setVisible(vis);
		lim.gridx=0;
		lim.gridy=1;
		display.add(Jconsultar, lim);
		setVisible(vis);
		lim.gridx=1;
		display.add(JTab,lim);
		
		
		
	}
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}*/
	
	public void ventanaCrearPadres(){
		JFrame ventana=new JFrame();
		String titulo;
		if(tipo){
			titulo="Padres";
		}else{
			titulo="Niños";
		}
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width),size.height);
		ventana.setTitle("Inscribir "+titulo);
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		lim.gridx=0;
		JLabel NombreCPlb=new JLabel("Nombre Completo del Padre:");
		lim.gridy=0;
		display.add(NombreCPlb,lim);
		JLabel NombreCMlb=new JLabel("Nombre Completo de la Madre");
		lim.gridy=1;
		display.add(NombreCMlb,lim);
		JLabel CelularMlb=new JLabel("Celular de la Madre");
		lim.gridy=2;
		display.add(CelularMlb,lim);
		JLabel CelularPlb=new JLabel("Celular del Padre");
		lim.gridy=3;
		display.add(CelularPlb,lim);
		JLabel CorreoPlb=new JLabel("Correo del Padre");
		lim.gridy=4;
		display.add(CorreoPlb,lim);
		JLabel CorreoMlb=new JLabel("Correo de la Madre");
		lim.gridy=5;
		display.add(CorreoMlb,lim);
		JLabel NombreCDE=new JLabel("Nombre del Contacto de Emergencia");
		lim.gridy=6;
		display.add(NombreCDE,lim);
		JLabel TelefonoDE=new JLabel("Teléfono del Contacto de Emergencia");
		lim.gridy=7;
		display.add(TelefonoDE,lim);
		JLabel TipoDePago=new JLabel("Tipo de Pago");
		lim.gridy=8;
		display.add(TipoDePago,lim);
		JLabel IYF=new JLabel("Fecha de Inscripción");
		lim.gridy=9;
		display.add(IYF,lim);
		JLabel Montolb=new JLabel("Monto A Pagar;");
		lim.gridy=10;
		display.add(Montolb,lim);
		
		lim.ipadx=250;
		lim.gridx=1;
		JTextField NombreCP=new JTextField("");
		lim.gridy=0;
		display.add(NombreCP,lim);
		JTextField NombreCM=new JTextField("");
		lim.gridy=1;
		display.add(NombreCM,lim);
		JTextField CelularM=new JTextField("");
		lim.gridy=2;
		display.add(CelularM,lim);
		JTextField CelularP=new JTextField("");
		lim.gridy=3;
		display.add(CelularP,lim);
		JTextField CorreoP=new JTextField("");
		lim.gridy=4;
		display.add(CorreoP,lim);
		JTextField CorreoM=new JTextField("");
		lim.gridy=5;
		display.add(CorreoM,lim);
		JTextField NombreCE=new JTextField("");
		lim.gridy=6;
		display.add(NombreCE,lim);
		JTextField TelefonoE=new JTextField("");
		lim.gridy=7;
		display.add(TelefonoE,lim);
		JTextField TipoDePagos=new JTextField("Tarjeta De Crédito ó Effectivo");//Cambiar a checkboxes
		TipoDePagos.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(TipoDePagos.getText().equals("Tarjeta De Crédito ó Effectivo")){
					TipoDePagos.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lim.gridy=8;
		display.add(TipoDePagos,lim);
		JTextField IYFs=new JTextField("dd/mm/aa");
		IYFs.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(IYFs.getText().equals("dd/mm/aa")){
					IYFs.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lim.gridy=9;
		display.add(IYFs,lim);
		JTextField Monto=new JTextField("");
		lim.gridy=10;
		display.add(Monto,lim);
		
		JButton Confirmar=new JButton();
		Confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			//Verificar si los valores introducidos son válidos
			boolean hacer=true;
			String[] mens=new String[7];
			for(int o=0;o<mens.length;o++)
				mens[o]="";
			char[] arrEv=NombreCP.getText().toCharArray();
			char[] numInv=StriInvalid.toCharArray();
			
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<numInv.length;n++){
					if(numInv[n]==arrEv[i]){
						hacer=false;
						mens[0]="Introdujo números en el nombre del padre";
					}
					
					
				}
			}
			
			arrEv=NombreCM.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<numInv.length;n++){
					if(numInv[n]==arrEv[i]){
						hacer=false;
						mens[1]="Introdujo números en el nombre de la madre";
					}
					
				}
			}
			arrEv=NombreCE.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<numInv.length;n++){
					if(numInv[n]==arrEv[i]){
						hacer=false;
						mens[2]="Introdujo números al nombre del contacto de emergencia";
					}
				}
			}
			
			//Verificar valores numericos
			char[] striInv=NumInvalid.toCharArray();
			arrEv=CelularP.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<striInv.length;n++){
					if(striInv[n]==arrEv[i]){
						hacer=false;
						mens[3]="Dígito Inválido en el Celular del Padre";
					}
				}
			}
			
			arrEv=CelularM.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<striInv.length;n++){
					if(striInv[n]==arrEv[i]){
						hacer=false;
						mens[4]="Dígito Inválido en el Celular de la Madre";
					}
				}
			}
			arrEv=TelefonoE.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<striInv.length;n++){
					if(striInv[n]==arrEv[i]){
						hacer=false;
						mens[5]="Dígito Inválido en el Teléfono de Emergencia";
					}
				}
			}
			arrEv=Monto.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<striInv.length;n++){
					if(striInv[n]==arrEv[i]){
						hacer=false;
						mens[6]="Dígito inválido en el monto";
					}
				}
			}
			
			
			if(hacer){
			crearNino(NombreCP,NombreCM,CelularM,CelularP,CorreoP,CorreoM,NombreCE,TelefonoE,TipoDePagos,Monto,IYFs);
			System.out.println("Confirmar");
			
			ventana.setVisible(false);
			}else{
				error(mens);
			}
		}

		});
		lim.gridy=11;
		Confirmar.setText("Confirmar");
		Confirmar.setToolTipText("Confirma la accion a llevar a acabo");
		display.add(Confirmar,lim);
		ventana.setVisible(true);
		
	}	
	
	
	public void crearNino(JTextField NP,JTextField NM,JTextField CelularM,JTextField CelularP,JTextField CorreoP,JTextField CorreoM,JTextField NombreCE,JTextField TelefonoE,JTextField TipoDePagos,JTextField Monto, JTextField IYFs) {
		// TODO Auto-generated method stub
		JFrame ventana=new JFrame();
		String titulo;
		if(tipo){
			titulo="Padres";
		}else{
			titulo="Niños";
		}
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width),size.height);
		ventana.setTitle("Inscribir "+titulo);
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		lim.gridx=0;
		JLabel NombreClb=new JLabel("Nombre Completo del Niño");
		lim.gridy=0;
		display.add(NombreClb, lim);
		JLabel Edadlb=new JLabel("Edad del Niño");
		lim.gridy=1;
		display.add(Edadlb, lim);
		JLabel FechaDeNaciminetolb=new JLabel("Fecha de Nacimeiento");
		lim.gridy=2;
		display.add(FechaDeNaciminetolb, lim);
		JLabel Escuelalb=new JLabel("Escuela");
		lim.gridy=3;
		display.add(Escuelalb, lim);
		JLabel GradoEscolarlb=new JLabel("Grado Escolar");
		lim.gridy=4;
		display.add(GradoEscolarlb, lim);
		JLabel Direccionlb=new JLabel("Dirección (Sin Comas)");
		lim.gridy=5;
		display.add(Direccionlb, lim);
		JLabel CodigoPostallb=new JLabel("Código Postal");
		lim.gridy=6;
		display.add(CodigoPostallb, lim);
		JLabel TelefonoDeCasalb=new JLabel("Número Telefónico de la casa");
		lim.gridy=7;
		display.add(TelefonoDeCasalb, lim);
		JLabel Enfermedadeslb=new JLabel("Enfermedades que padece");
		lim.gridy=8;
		display.add(Enfermedadeslb, lim);
		JLabel Alergiaslb=new JLabel("Alergias del Niño");
		lim.gridy=9;
		display.add(Alergiaslb, lim);
		JLabel Medicamentoslb=new JLabel("Medicamentos especiales del Niño");
		lim.gridy=10;
		display.add(Medicamentoslb, lim);
		JLabel TipoDeSangrelb=new JLabel("Tipo de Sangre");
		lim.gridy=11;
		display.add(TipoDeSangrelb, lim);
		JLabel ClaseDePruebalb=new JLabel("Clase de Prueba");
		lim.gridy=12;
		display.add(ClaseDePruebalb, lim);
		JLabel HorasDeClaselb=new JLabel("Hora de la clase");
		lim.gridy=13;
		display.add(HorasDeClaselb, lim);
		JLabel diasSesionlb=new JLabel("Días a asisitir");
		lim.gridy=14;
		display.add(diasSesionlb, lim);
		JLabel TipoDeSesionlb=new JLabel("Tipo de Clase");//regular o irregular
		lim.gridy=15;
		display.add(TipoDeSesionlb, lim);
		JLabel descuentolb=new JLabel("Descuento a aplicar");//-----------------------------------------------------
		lim.gridy=16;
		display.add(descuentolb, lim);
		
		lim.ipadx=250;
		lim.gridx=1;
		JTextField NombreC= new JTextField();
		lim.gridy=0;
		display.add(NombreC, lim);
		JTextField Edad= new JTextField();
		lim.gridy=1;
		display.add(Edad, lim);
		JTextField FechaDeNacimineto= new JTextField("dd/mm/aa");
		FechaDeNacimineto.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	FechaDeNacimineto.getText().equals("dd/mm/aa")){
					FechaDeNacimineto.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		lim.gridy=2;
		display.add(FechaDeNacimineto, lim);
		JTextField Escuela= new JTextField();
		lim.gridy=3;
		display.add(Escuela, lim);
		JTextField GradoEscolar= new JTextField();
		lim.gridy=4;
		display.add(GradoEscolar, lim);
		JTextField Direccion= new JTextField();
		lim.gridy=5;
		display.add(Direccion, lim);
		JTextField CodigoPostal= new JTextField();
		lim.gridy=6;
		display.add(CodigoPostal, lim);
		JTextField TelefonoDeCasa= new JTextField();
		lim.gridy=7;
		display.add(TelefonoDeCasa, lim);
		JTextField Enfermedades= new JTextField("Si cuenta con más de una se separan con comas");
		Enfermedades.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	Enfermedades.getText().equals("Si cuenta con más de una se separan con comas")){
					Enfermedades.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		lim.gridy=8;
		display.add(Enfermedades, lim);
		JTextField Alergias= new JTextField("Si cuenta con más de una se separan con comas");
		Alergias.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	Alergias.getText().equals("Si cuenta con más de una se separan con comas")){
					Alergias.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=9;
		display.add(Alergias, lim);
		JTextField Medicamentos= new JTextField("Si cuenta con más de una se separan con comas");
		Medicamentos.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	Enfermedades.getText().equals("Si cuenta con más de una se separan con comas")){
					Enfermedades.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=10;
		display.add(Medicamentos, lim);
		JTextField TipoDeSangre= new JTextField();
		lim.gridy=11;
		display.add(TipoDeSangre, lim);
		Checkbox ClaseDePrueba=new Checkbox("Clase de Prueba");
		lim.gridy=12;
		display.add(ClaseDePrueba, lim);
		JTextField HorasDeClase= new JTextField("Ej. 10:30-12:30");
		HorasDeClase.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	HorasDeClase.getText().equals("Ej. 10:30-12:30")){
					HorasDeClase.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=13;
		display.add(HorasDeClase, lim);
		JTextField diasSesion= new JTextField("Se escriben las primeras dos letras como en el ejemplo Lu/Ma/Mi/Ju/Vi/Sa");
		diasSesion.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	diasSesion.getText().equals("Se escriben las primeras dos letras como en el ejemplo Lu/Ma/Mi/Ju/Vi/Sa")){
					diasSesion.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=14;
		display.add(diasSesion, lim);
		Checkbox TipoDeSesion=new Checkbox ("Clase regular");
		lim.gridy=15;
		display.add(TipoDeSesion, lim);
		JTextField descuento=new JTextField("Ej. 20.0");//-----------------------------------------------------
		descuento.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	descuento.getText().equals("Ej. 20.0")){
					descuento.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		descuento.setToolTipText("Escribir el porcentaje en número sin el signo de porcentaje (%) y letras");
		
		lim.gridy=16;
		display.add(descuento, lim);
		

		
		JButton Confirmar=new JButton("Confirmar");
		Confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			System.out.println("Confirmar Ninos");
			String mens[]=new String[7];
			boolean hacer=true;
			//Verificar valores invalidos
			//Verifica si nombres no tiene numeros
			char arrEv[]=NombreC.getText().toCharArray();
			char numInv[]=StriInvalid.toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<numInv.length;z++){
					if(arrEv[i]==numInv[z]){
						hacer=false;
						mens[0]="Valores inválidos en el nombre";
					}
				}
				
			}
			//Verificar todos los valores numericos, para que no tengan letras
			//Edad
			char letrInv[]=NumInvalid.toCharArray();
			arrEv=Edad.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[1]="Valores inválidos en la edad";
					}
				}
				
			}
			//Codigo Postal
			arrEv=CodigoPostal.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[2]="Valores inválidos en el código postal";
					}
				}
				
			}
			//Telefono de casa
			arrEv=TelefonoDeCasa.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[3]="Valores inválidos en el teléfono de casa";
					}
				}
				
			}
			//Horas de clase
			arrEv=HorasDeClase.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[4]="Valores inválidos en la hora clase";
					}
				}
				
			}
			//Descuento
			arrEv=descuento.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[5]="Valores inválidos en el descuento";
					}
				}
			}
			
			if(hacer){
			
			StringTokenizer ST=new StringTokenizer(Enfermedades.getText(),",");
			int cont=0;
			String arrE[]=new String [ST.countTokens()];
			while(ST.hasMoreTokens()){
				
			 arrE[cont]=ST.nextToken();
			 cont++;
			}
			ST=new StringTokenizer(Alergias.getText(),",");
			cont=0;
			String arrA[]=new String [ST.countTokens()];
			while(ST.hasMoreTokens()){
				
			 arrA[cont]=ST.nextToken();
			 cont++;
			}
			ST=new StringTokenizer(Medicamentos.getText(),",");
			cont=0;
			String arrM[]=new String [ST.countTokens()];
			while(ST.hasMoreTokens()){
				
			 arrM[cont]=ST.nextToken();
			 cont++;
			}
			diasSesion.getText().toUpperCase();
			ST=new StringTokenizer(diasSesion.getText(),"/");
			boolean[] arrDias=new boolean[7];
			while(ST.hasMoreTokens()){
				String paso=ST.nextToken();
				if(paso.equals("LU")){
					arrDias[0]=true;
				}
				if(paso.equals("MA")){
					arrDias[1]=true;
				}
				if(paso.equals("MI")){
					arrDias[2]=true;
				}
				if(paso.equals("JU")){
					arrDias[3]=true;
				}
				if(paso.equals("VI")){
					arrDias[4]=true;
				}
				if(paso.equals("SA")){
					arrDias[5]=true;
				}
				if(paso.equals("DO")){
					arrDias[6]=true;
				}
			}
			int contar=0;
			for(int i=0;i<ninos.length;i++){
				System.out.println("For Ninos");
				
				if(ninos[i].getIDNino().equals("")){
					
					String ID=""+NombreC.getText().charAt(0)+NombreC.getText().charAt(1)+NP.getText().charAt(0)+NM.getText().charAt(0)+(int)(Math.random()*100000);///
					ID=ID.toUpperCase();
					System.out.println("Nuevo Niño: "+ID+","+NombreC.getText()+","+Integer.parseInt(Edad.getText())+","+FechaDeNacimineto.getText()+","+Escuela.getText()+","+GradoEscolar.getText()+","+Direccion.getText()+","+Integer.parseInt(CodigoPostal.getText())+","+Integer.parseInt(TelefonoDeCasa.getText())+","+arrE+","+arrA+","+arrM+","+TipoDeSangre.getText()+","+ClaseDePrueba.getState()+","+HorasDeClase.getText()+","+arrDias+","+TipoDeSesion.getState());
					ninos[i]=new Ninos(ID,NombreC.getText(),Integer.parseInt(Edad.getText()),FechaDeNacimineto.getText(),Escuela.getText(),GradoEscolar.getText(),Direccion.getText(),Integer.parseInt(CodigoPostal.getText()),Integer.parseInt(TelefonoDeCasa.getText()),arrE,arrA,arrM,TipoDeSangre.getText(),ClaseDePrueba.getState(),HorasDeClase.getText(),arrDias,TipoDeSesion.getState(),Double.parseDouble(descuento.getText()));
					ninos[i].print();
					posicion=i;	
					i+=ninos.length;
					
					ventana.setVisible(false);
					
					}else{
						contar++;
					}
				}
			if(contar>=ninos.length-1){
				ninos=aumentarArr(ninos);
				String ID=""+NombreC.getText().charAt(0)+NombreC.getText().charAt(1)+NP.getText().charAt(0)+NM.getText().charAt(0)+(int)(Math.random()*100000);///
				ID=ID.toUpperCase();
				System.out.println("Nuevo Niño: "+ID+","+NombreC.getText()+","+Integer.parseInt(Edad.getText())+","+FechaDeNacimineto.getText()+","+Escuela.getText()+","+GradoEscolar.getText()+","+Direccion.getText()+","+Integer.parseInt(CodigoPostal.getText())+","+Integer.parseInt(TelefonoDeCasa.getText())+","+arrE+","+arrA+","+arrM+","+TipoDeSangre.getText()+","+ClaseDePrueba.getState()+","+HorasDeClase.getText()+","+arrDias+","+TipoDeSesion.getState());
				ninos[contar]=new Ninos(ID,NombreC.getText(),Integer.parseInt(Edad.getText()),FechaDeNacimineto.getText(),Escuela.getText(),GradoEscolar.getText(),Direccion.getText(),Integer.parseInt(CodigoPostal.getText()),Integer.parseInt(TelefonoDeCasa.getText()),arrE,arrA,arrM,TipoDeSangre.getText(),ClaseDePrueba.getState(),HorasDeClase.getText(),arrDias,TipoDeSesion.getState(),Double.parseDouble(descuento.getText()));
				ninos[contar].print();
				posicion=contar;
				ventana.setVisible(false);
			}
			contar=0;
			for(int i=0;i<padres.length;i++){
				System.out.println("For Papás");
				if(padres[i].getIDPadres().equals("")){
					String ID=""+NP.getText().charAt(0)+NM.getText().charAt(0)+ninos[posicion].getIDNino().substring(3);
					ID=ID.toUpperCase();
					System.out.println("IDPadres: "+ID);
					String []IDS={ninos[posicion].getIDNino()};
					padres[i]=new Padres(ID,IDS,NP.getText(),NM.getText(),(CelularM.getText()),(CelularP.getText()),CorreoP.getText(),CorreoM.getText(),NombreCE.getText(),(TelefonoE.getText()),TipoDePagos.getText(),Double.parseDouble(Monto.getText()),IYFs.getText());//Flata tipos de pago  e fechas de inscripciones
					padres[i].print();
					ninos[posicion].setIDPadres(ID);
					i+=padres.length;
					
					ventana.setVisible(false);
				}else{contar++;}
				
				
			}
			if(contar>=padres.length-1){
				padres=aumentarArr(padres);
				String ID=""+NP.getText().charAt(0)+NM.getText().charAt(0)+ninos[posicion].getIDNino().substring(3);
				ID=ID.toUpperCase();
				System.out.println("IDPadres: "+ID);
				String []IDS={ninos[posicion].getIDNino()};
				System.out.println("Contar: "+contar+" Length: "+padres.length);
				try{
				padres[contar]=new Padres(ID,IDS,NP.getText(),NM.getText(),(CelularM.getText()),(CelularP.getText()),CorreoP.getText(),CorreoM.getText(),NombreCE.getText(),(TelefonoE.getText()),TipoDePagos.getText(),Double.parseDouble(Monto.getText()),IYFs.getText());//Flata tipos de pago  e fechas de inscripciones
				}catch(NullPointerException e){
					e.printStackTrace();
					
				}
				padres[contar].print();
				ninos[posicion].setIDPadres(ID);
				ventana.setVisible(false);
			}
			
			try {
				writeFile(padres);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				writeFile(ninos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ventana.setVisible(false);
			}else{
				error(mens);
			}
		}});
		lim.gridy=18;
		lim.ipady=30;
		Confirmar.setText("");
		Confirmar.setToolTipText("Confirma la acción a llevar a acabo");
		display.add(Confirmar,lim);
		ventana.setVisible(true);
		
	}	
	public void crearNino() {
		// TODO Auto-generated method stub
		JFrame ventana=new JFrame();
		String titulo;
		if(tipo){
			titulo="Padres";
		}else{
			titulo="Niños";
		}
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width),size.height);
		ventana.setTitle("Crear "+titulo);
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		lim.gridx=0;
		
		JLabel NombreClb=new JLabel("Nombre Completo del Niño");
		lim.gridy=0;
		display.add(NombreClb, lim);
		JLabel Edadlb=new JLabel("Edad del Niño");
		lim.gridy=1;
		display.add(Edadlb, lim);
		JLabel FechaDeNaciminetolb=new JLabel("Fecha de Nacimeiento");
		lim.gridy=2;
		display.add(FechaDeNaciminetolb, lim);
		JLabel Escuelalb=new JLabel("Escuela");
		lim.gridy=3;
		display.add(Escuelalb, lim);
		JLabel GradoEscolarlb=new JLabel("Grado Escolar");
		lim.gridy=4;
		display.add(GradoEscolarlb, lim);
		JLabel Direccionlb=new JLabel("Dirección (Sin Comas)");
		lim.gridy=5;
		display.add(Direccionlb, lim);
		JLabel CodigoPostallb=new JLabel("Código Postal");
		lim.gridy=6;
		display.add(CodigoPostallb, lim);
		JLabel TelefonoDeCasalb=new JLabel("Número Telefónico de la casa");
		lim.gridy=7;
		display.add(TelefonoDeCasalb, lim);
		JLabel Enfermedadeslb=new JLabel("Enfermedades que padece");
		lim.gridy=8;
		display.add(Enfermedadeslb, lim);
		JLabel Alergiaslb=new JLabel("Alergias del Niño");
		lim.gridy=9;
		display.add(Alergiaslb, lim);
		JLabel Medicamentoslb=new JLabel("Medicamentos especiales del Niño");
		lim.gridy=10;
		display.add(Medicamentoslb, lim);
		JLabel TipoDeSangrelb=new JLabel("Tipo de Sangre");
		lim.gridy=11;
		display.add(TipoDeSangrelb, lim);
		JLabel ClaseDePruebalb=new JLabel("Clase de Prueba");
		lim.gridy=12;
		display.add(ClaseDePruebalb, lim);
		JLabel HorasDeClaselb=new JLabel("Hora de la clase");
		lim.gridy=13;
		display.add(HorasDeClaselb, lim);
		JLabel diasSesionlb=new JLabel("Días a asisitir");
		lim.gridy=14;
		display.add(diasSesionlb, lim);
		JLabel TipoDeSesionlb=new JLabel("Tipo de Clase");//regular o irregular
		lim.gridy=15;
		display.add(TipoDeSesionlb, lim);
		JLabel IDPadreslb=new JLabel("Introdusca el ID del Padre");
		lim.gridy=16;
		display.add(IDPadreslb,lim);
		JLabel descuentolb=new JLabel("Descuento a aplicar");//-----------------------------------------------------
		lim.gridy=17;
		display.add(descuentolb, lim);
		
		
		
		lim.gridx=1;
		lim.ipadx=250;
		JTextField NombreC= new JTextField();
		lim.gridy=0;
		display.add(NombreC, lim);
		JTextField Edad= new JTextField();
		lim.gridy=1;
		display.add(Edad, lim);
		JTextField FechaDeNacimineto= new JTextField("dd/mm/aa");
		FechaDeNacimineto.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	FechaDeNacimineto.getText().equals("dd/mm/aa")){
					FechaDeNacimineto.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=2;
		display.add(FechaDeNacimineto, lim);
		JTextField Escuela= new JTextField();
		lim.gridy=3;
		display.add(Escuela, lim);
		JTextField GradoEscolar= new JTextField();
		lim.gridy=4;
		display.add(GradoEscolar, lim);
		JTextField Direccion= new JTextField();
		lim.gridy=5;
		display.add(Direccion, lim);
		JTextField CodigoPostal= new JTextField();
		lim.gridy=6;
		display.add(CodigoPostal, lim);
		JTextField TelefonoDeCasa= new JTextField();
		lim.gridy=7;
		display.add(TelefonoDeCasa, lim);
		JTextField Enfermedades= new JTextField("Si cuenta con más de una se separan con comas");
		Enfermedades.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	Enfermedades.getText().equals("Si cuenta con más de una se separan con comas")){
					Enfermedades.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=8;
		display.add(Enfermedades, lim);
		JTextField Alergias= new JTextField("Si cuenta con más de una se separan con comas");
		Alergias.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	Alergias.getText().equals("Si cuenta con más de una se separan con comas")){
					Alergias.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=9;
		display.add(Alergias, lim);
		JTextField Medicamentos= new JTextField("Si cuenta con más de una se separan con comas");
		Medicamentos.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	Medicamentos.getText().equals("Si cuenta con más de una se separan con comas")){
					Medicamentos.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=10;
		display.add(Medicamentos, lim);
		JTextField TipoDeSangre= new JTextField();
		lim.gridy=11;
		display.add(TipoDeSangre, lim);
		Checkbox ClaseDePrueba=new Checkbox("Clase de Prueba");
		lim.gridy=12;
		display.add(ClaseDePrueba, lim);
		JTextField HorasDeClase= new JTextField("Ej. 10:30-12:30 (En la notación de 24 horas)");
		HorasDeClase.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	HorasDeClase.getText().equals("Ej. 10:30-12:30 (En la notación de 24 horas)")){
					HorasDeClase.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=13;
		display.add(HorasDeClase, lim);
		JTextField diasSesion= new JTextField("Se escriben las primeras dos letras como en el ejemplo Lu/Ma/Mi/Ju/Vi/Sa");
		diasSesion.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	FechaDeNacimineto.getText().equals("Se escriben las primeras dos letras como en el ejemplo Lu/Ma/Mi/Ju/Vi/Sa")){
					FechaDeNacimineto.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		lim.gridy=14;
		display.add(diasSesion, lim);
		Checkbox TipoDeSesion=new Checkbox ("Clase regular");
		lim.gridy=15;
		display.add(TipoDeSesion, lim);
		JTextField IDPadres=new JTextField();
		lim.ipady=30;
		lim.gridy=16;
		display.add(IDPadres,lim);
		JTextField descuento=new JTextField("Ej. 20.0");//-----------------------------------------------------
		descuento.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	descuento.getText().equals("Ej. 20.0")){
					descuento.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		descuento.setToolTipText("Escribir el porcentaje en número sin el signo de porcentaje (%) y letras");
		lim.gridy=17;
		display.add(descuento, lim);
		

		
		JButton Confirmar=new JButton("Confirmar");
		Confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			boolean hacer=true;
			String[] mens=new String[7];
			for(int i=0;i<mens.length;i++)
				mens[i]="";
			//Verificar valores invalidos
			//Verifica si nombres no tiene numeros
			char arrEv[]=NombreC.getText().toCharArray();
			char numInv[]=StriInvalid.toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<numInv.length;z++){
					if(arrEv==numInv){
						hacer=false;
						mens[0]="Valor inválido en el nombre";
					}
				}
				
			}
			//Verificar todos los valores numericos, para que no tengan letras
			//Edad
			char letrInv[]=NumInvalid.toCharArray();
			arrEv=Edad.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[1]="Valor inválido en la edad";
					}
				}
				
			}
			//Codigo Postal
			arrEv=CodigoPostal.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[2]="Valor inválido en el código postal";
					}
				}
				
			}
			//Telefono de casa
			arrEv=TelefonoDeCasa.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[3]="Valor inválido en número teléfonico";
					}
				}
				
			}
			//Horas de clase
			arrEv=HorasDeClase.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[4]="Valor inválido en la hora clase";
					}
				}
				
			}
			
			
			//Descuento
			arrEv=descuento.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[5]="Valor inválido en el descuento";
					}
				}
			}
			
			if(hacer){
			
			StringTokenizer ST=new StringTokenizer(Enfermedades.getText(),",");
			int cont=0;
			String arrE[]=new String [ST.countTokens()];
			do{
				
			 arrE[cont]=ST.nextToken();
			 cont++;
			}while(ST.hasMoreTokens());
			ST=new StringTokenizer(Alergias.getText(),",");
			cont=0;
			String arrA[]=new String [ST.countTokens()];
			do{
				
			 arrA[cont]=ST.nextToken();
			 cont++;
			}while(ST.hasMoreTokens());
			ST=new StringTokenizer(Medicamentos.getText(),",");
			cont=0;
			String arrM[]=new String [ST.countTokens()];
			do{
				
			 arrM[cont]=ST.nextToken();
			 System.out.println(arrM[cont]);
			 cont++;
			 diasSesion.getText().toUpperCase();
			}while(ST.hasMoreTokens());
			
			ST=new StringTokenizer(diasSesion.getText(),"/");
			boolean[] arrDias=new boolean[7];
			while(ST.hasMoreTokens()){
				String paso=ST.nextToken();
				if(paso.equals("LU")){
					arrDias[0]=true;
				}
				if(paso.equals("MA")){
					arrDias[1]=true;
				}
				if(paso.equals("MI")){
					arrDias[2]=true;
				}
				if(paso.equals("JU")){
					arrDias[3]=true;
				}
				if(paso.equals("VI")){
					arrDias[4]=true;
				}
				if(paso.equals("SA")){
					arrDias[5]=true;
				}
				if(paso.equals("DO")){
					arrDias[6]=true;
				}
			}
			int contar=0;
			for(int i=0;i<ninos.length;i++){
				System.out.println("Esta en el FOR para encontrar");
				if(ninos[i].getIDNino().equals("")){
					
					String ID=""+NombreC.getText().charAt(0)+NombreC.getText().charAt(1)+IDPadres.getText().charAt(0)+IDPadres.getText().charAt(1)+(int)(Math.random()*100000);///
					ID=ID.toUpperCase();
					ninos[i]=new Ninos(ID,NombreC.getText(),Integer.parseInt(Edad.getText()),FechaDeNacimineto.getText(),Escuela.getText(),GradoEscolar.getText(),Direccion.getText(),Integer.parseInt(CodigoPostal.getText()),Integer.parseInt(TelefonoDeCasa.getText()),arrE,arrA,arrM,TipoDeSangre.getText(),ClaseDePrueba.getState(),HorasDeClase.getText(),arrDias,TipoDeSesion.getState(),Double.parseDouble(descuento.getText()));
				ninos[i].setIDPadres(IDPadres.getText());
				ninos[i].print();
				for(int x=0;x<ninos[i].getEnfermedades().length;x++){
					System.out.println("Medicamento: "+ninos[i].getEnfermedades()[x]);
				}
				for(int x=0;x<padres.length;x++){
					if(ninos[i].getIDPadres().equals(padres[x].getIDPadres())){
						
						boolean con=true;
						
						for(int z=0;z<padres[x].getIDNino().length;z++){
							System.out.println("IF para asignar: "+padres[x].getIDNino()[z].equals(ninos[i].getIDNino()));
							if(padres[x].getIDNino()[z].equals(ninos[i].getIDNino())){
								con=false;
								z+=padres[i].getIDNino().length;
							}
						}
						if(con){
						padres[x].addIDNino(ninos[i].getIDNino());
						System.out.println("Se agrego a "+padres[x].getIDPadres()+" el IDNino de: "+ninos[i].getIDNino());
						}
						System.out.println("IDNino: "+ninos[i].getIDNino()+" IDPadre: "+padres[x].getIDPadres());
					}
				}
				posicion=i;	
				i+=ninos.length;
				ventana.setVisible(false);
					}
				contar++;
				}
			if(contar>=ninos.length-1){
				ninos=aumentarArr(ninos);
				String ID=""+NombreC.getText().charAt(0)+NombreC.getText().charAt(1)+IDPadres.getText().charAt(0)+IDPadres.getText().charAt(1)+(int)((Math.random())*100000);///
				ID=ID.toUpperCase();
				ninos[contar]=new Ninos(ID,NombreC.getText(),Integer.parseInt(Edad.getText()),FechaDeNacimineto.getText(),Escuela.getText(),GradoEscolar.getText(),Direccion.getText(),Integer.parseInt(CodigoPostal.getText()),Integer.parseInt(TelefonoDeCasa.getText()),arrE,arrA,arrM,TipoDeSangre.getText(),ClaseDePrueba.getState(),HorasDeClase.getText(),arrDias,TipoDeSesion.getState(),Double.parseDouble(descuento.getText()));
				ninos[contar].setIDPadres(IDPadres.getText());
				ninos[contar].print();
				
				for(int i=0;i<padres.length;i++){
					if(ninos[contar].getIDPadres().equals(padres[i].getIDPadres())){
						boolean x=true;
						for(int z=0;z<padres[i].getIDNino().length;z++){
							if(padres[i].getIDNino()[z].equals(ninos[contar].getIDNino())){
								x=false;
								System.out.println("IDNino: "+ninos[contar].getIDNino()+" IDPadre: "+padres[i].getIDPadres());
								z+=padres[i].getIDNino().length;
							}
						}
						if(x){
						padres[i].addIDNino(ninos[contar].getIDNino());
						System.out.println("Se agrego a "+padres[i].getIDPadres()+" el IDNino de: "+ninos[contar].getIDNino());
						}
						System.out.println("IDNino: "+ninos[contar].getIDNino()+" IDPadre: "+padres[i].getIDPadres());
					}
				}
				}
				ventana.setVisible(false);
				try {
					writeFile(ninos);
					writeFile(padres);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				error(mens);
			}
			
		}});
		lim.ipady=30;
		lim.gridy=18;
		Confirmar.setText("Confirmar");
		Confirmar.setToolTipText("Confirma la accion a llevar a acabo");
		display.add(Confirmar,lim);
		ventana.setVisible(true);
		
	}
	
	
	

	//Guarda la info de papás
//Metodo que guarda la información de los padres como un archivo de texto
	public static void writeFile(Padres[] arr) throws IOException{
		PrintWriter fileOut;
		fileOut=new PrintWriter(new FileWriter("Padres.txt"));
		for (int i=0; i<arr.length;i++)
		{
			Padres papas=arr[i];
			//Procedimiento para convertir arr a Strings 
			
			if(!papas.getIDPadres().equals("")){
				String IDN="";
				for(int z=0;z<papas.getIDNino().length;z++){
					if(z==0){
						IDN+=papas.getIDNino()[z];
					}else{
						IDN+="/"+papas.getIDNino()[z];
					}
					
				}
				System.out.println(">>>>>>>>>>>>>>>"+papas.getIDPadres()+","+ IDN+","+ papas.getNombreCompletoPadre()+","+ papas.getNombreCompletoMadre()+","+ papas.getCelularMadre()+","+ papas.getCelularPadre()+","+ papas.getCorreoPadre()+","+ papas.getCorreoMadre()+","+ papas.getNombreDeEmergencia()+","+papas.getTelefonoDeEmergencia()+","+papas.getTipoDePago()+","+papas.getMontoAPagar()+","+papas.getInscripcionYFecha());
				fileOut.println(papas.getIDPadres()+","+ IDN+","+ papas.getNombreCompletoPadre()+","+ papas.getNombreCompletoMadre()+","+ papas.getCelularMadre()+","+ papas.getCelularPadre()+","+ papas.getCorreoPadre()+","+ papas.getCorreoMadre()+","+ papas.getNombreDeEmergencia()+","+papas.getTelefonoDeEmergencia()+","+papas.getTipoDePago()+","+papas.getMontoAPagar()+","+papas.getInscripcionYFecha()/*, int TipoDePago, String[] InscripcionYFechas*/);
			}	
			
			//}
		}
		fileOut.close();
	}
	
	
	//Metdo para guardar--------------------------------------------------------
	public static void writeFile(Ninos[] arr)throws IOException{
		PrintWriter fileOut;
		fileOut=new PrintWriter(new FileWriter("Ninos.txt"));
		for (int i=0; i<arr.length;i++)
		{
			Ninos ninos=arr[i];
			//Procedimiento para convertir arr a Strings
			if(!ninos.getIDNino().equals("")){
			String enfer="";
			String alerg="";
			String med="";
			String dias="";
			System.out.println("Longitud: "+ninos.getEnfermedades().length);
			for(int x=0;x<ninos.getEnfermedades().length;x++){
				System.out.println("Posicion: "+x+", "+ninos.getEnfermedades(x));;
				enfer+="/"+ninos.getEnfermedades(x);
			}
			for(int x=0;x<ninos.getAlergias().length;x++){
				alerg+="/"+ninos.getAlergia(x);
			}
			for(int x=0;x<ninos.getMedicamentos().length;x++){
				med+="/"+ninos.getMedicamentos(x);
			}
			for(int x=0;x<ninos.getdiasDeSesion().length;x++){
				dias+="/"+ninos.getdiasDeSesion()[x];
			}
			
			if(!ninos.getIDPadres().equals("")){
				System.out.println("========>"+ninos.getIDNino()+","+ninos.getNombre()+","+ninos.getEdad()+","+ninos.getFechaDeNacimiento()+","+ninos.getEscuela()+","+ninos.getGradoEscolar()+","+ninos.getDireccion()+","+ninos.getCodigoPostal()+","+ninos.getTelefonoCasa()+","+enfer+","+alerg+","+med+","+ ninos.getTipoDeSangre()+","+ninos.getClaseDePrueba()+","+ninos.getHorasDeClase()+","+dias+","+ninos.getTipoDeSesion()+","+ninos.getIDPadres()+","+ninos.getInscrito());
				fileOut.println(ninos.getIDNino()+","+ninos.getNombre()+","+ninos.getEdad()+","+ninos.getFechaDeNacimiento()+","+ninos.getEscuela()+","+ninos.getGradoEscolar()+","+ninos.getDireccion()+","+ninos.getCodigoPostal()+","+ninos.getTelefonoCasa()+","+enfer+","+alerg+","+med+","+ ninos.getTipoDeSangre()+","+ninos.getClaseDePrueba()+","+ninos.getHorasDeClase()+","+dias+","+ninos.getTipoDeSesion()+","+ninos.getIDPadres()+","+ninos.getInscrito()+","+ninos.getDescuentos());
			}	
			
			//}
		}}
		fileOut.close();
	}	

	
//Metodo que lee un archivo con los datos de los padres y los regresa como un string
	public static String[] readFilePadres()throws IOException{
		String[] arr = new String[1];
		System.out.println("Llego al file read de padres");
		File read = new File("Padres.txt");
		//lee archivo Padres
			System.out.println("condicioón a: "+(read.exists()) +" b: "+ !read.isDirectory());	
			if(read.exists() && !read.isDirectory()) { 
					BufferedReader leerDir=new BufferedReader(new FileReader("Padres.txt"));
					int cont =0;
					int size=0;
					while(leerDir.ready()){
						size++;
						leerDir.readLine();
						
					}
					leerDir.close();
				 leerDir=new BufferedReader(new FileReader("Padres.txt")); 
					arr = new String[size];
					while((leerDir.ready()))
					{ 
						if (cont==0){
							
							arr[cont]= leerDir.readLine(); //lee del archivo
							System.out.println("linea: "+arr[cont]);
						}else{
						arr[cont]=leerDir.readLine();
						System.out.println("linea: "+arr[cont]);
						}
						
						cont ++;
					}
					leerDir.close(); // cierra el archivo
				}
				return arr;
	}
	//Metodo que lee los datos de los niños en un documento de texto y los transforma a un arrString()	
	
	public String[] readFileNinos()throws IOException{
		String[] arr = new String[1];
		System.out.println("Llego al file read de Ninos");
		File read = new File("Ninos.txt");
		//lee archivo Ninos
			System.out.println("condicioón a: "+(read.exists()) +" b: "+ !read.isDirectory());	
			if(read.exists() && !read.isDirectory()) { 
					BufferedReader leerDir=new BufferedReader(new FileReader("Ninos.txt"));
					int cont =0;
					int size=0;
					while(leerDir.ready()){
						size++;
						leerDir.readLine();
						
					}
					leerDir.close();
				 leerDir=new BufferedReader(new FileReader("Ninos.txt")); 
					arr = new String[size];
					System.out.println("cantidad de lineas: "+size);
					while((leerDir.ready()))
					{ 
						if (cont==0){
							
							arr[cont]= leerDir.readLine(); //lee del archivo
							System.out.println("linea: "+arr[cont]);
						}else{
						arr[cont]=leerDir.readLine();
						System.out.println("linea: "+arr[cont]);
						}
						
						cont ++;
					}
					leerDir.close(); // cierra el archivo
				}
				return arr;
	}
	

	
	//Metodo que interpreta la información del archivo de texto
	
	
	public Padres[] generarPadresArr(String[] arr){
		Padres[] arrP=new Padres[arr.length];
		StringTokenizer st;
		for(int i=0;i<arr.length;i++){
			System.out.println(arr[i]);
			st=new StringTokenizer(arr[i],",");
			int cont =0;
			String idPadres="";
			String []idNino = {""};
			String NCP="";
			String NCM="";
			String CM="";
			String CP="";
			String correoP="";
			String correoM="";
			String NE="";
			String telefonoE="";
			String TipoDePagos="";
			double Monto=0;
			String fecha="";
			while(st.hasMoreTokens()){
				//System.out.println(cont);
				switch(cont){
					
				case 0:{
					 idPadres=st.nextToken();
					 System.out.println(idPadres);
						break;
					}
					
					case 1:{
					 StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
					 int conta=0;
					 System.out.println("------------------------\n"
					 		+ conta);
					 idNino=new String[st2.countTokens()];
					 while(st2.hasMoreTokens()){
					 
						 idNino[conta]=st2.nextToken();
						// System.out.println(idNino[conta]);
						 conta++;
						 //System.out.println(idNino[conta]);
					 }
					 
						break;
					}
					case 2:{
					 NCP=st.nextToken();
					 //System.out.println(NCP);
						break;
					}
					case 3:{
						 NCM=st.nextToken();
						// System.out.println(NCM);
							break;
						}
					case 4:{
						 CM=st.nextToken();
							break;
						}
					case 5:{
						 CP=st.nextToken();
							break;
						}
					case 6:{
						 correoP=st.nextToken();
							break;
						}
					case 7:{
						 correoM=st.nextToken();
							break;
						}
					case 8:{
						 NE=st.nextToken();
							break;
						}
					case 9:{
						 telefonoE=(st.nextToken());
							break;
						}
					case 10:{
						TipoDePagos=st.nextToken();
						break;
					}
					case 11:{
						Monto=Double.parseDouble(st.nextToken());
						break;
					}
					case 12:{
						fecha=st.nextToken();
					}
				}
			cont++;
			}
			arrP[i]=new Padres(idPadres, idNino, NCP, NCM, CM, CP,correoP,correoM, NE,telefonoE,TipoDePagos,Monto,fecha);
		}
		
		return arrP;
	}	
	
	
//generarArr ninos es un metodo que interpreta los datos del archivo de texto en string y los convierte en un arreglo de instancias ninos
	
	public Ninos[] generarArrNinos(String[] arr){
		Ninos[] arrN=new Ninos[arr.length];
		for(int i=0;i<arr.length;i++){
			StringTokenizer st=new StringTokenizer(arr[i],",");
			int cont=0;
			String IDN="";
			String Nombre="";
			int Edad=0;
			String FechaN="";
			String Escu="";
			String Grad="";
			String Dir="";
			int CodP=0;
			int TelC=0;
			String[] Enf=new String[1];
			String[] Alg=new String[1];
			String[] Med=new String[1];
			String TipoSan="";
			boolean Prueba=true;
			String Horas="";
			boolean[] dias=new boolean[1];
			boolean tipo=false;
			String IDPadre="";;
			boolean Ins=false;
			double desc=0;
			
			while(st.hasMoreTokens()){
				//String IDN,String Nombre,int Edad,String FechaN,String Escu,String Grad,String Dir, int CodP, int TelC, String[] Enf, String[] Alg, String [] Med, String TipSan, boolean Prueba,String Horas,boolean[] dias,boolean tipo
				//System.out.println(cont);	
				switch(cont){
				
				case 0:{
						
					IDN=st.nextToken();
					//System.out.println(IDN);
						break;
					}
					case 1:{
						Nombre=st.nextToken();
						//System.out.println(Nombre);
						break;
					}
					case 2:{
						Edad=Integer.parseInt(st.nextToken());
						//System.out.println(Edad);
						break;
					}
					
					case 3:{
						FechaN=st.nextToken();
						//System.out.println(FechaN);
						break;
					}
					case 4:{
						Escu=st.nextToken();
						//System.out.println(Escu);
						break;
					}
					case 5:{
						Grad=st.nextToken();
						//System.out.println(Grad);
						break;
					}
					case 6:{
						Dir=st.nextToken();
						//System.out.println(Dir);
						break;
					}
					case 7:{
						String paso=st.nextToken();
						//System.out.println(paso);
						CodP=Integer.parseInt(paso);
						break;
					}
					case 8:{
						TelC=Integer.parseInt(st.nextToken());
						//System.out.println(TelC);
						break;
					}
					case 9:{
						StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
						String[] paso=new String[st2.countTokens()];
						int y=0;
						while(st2.hasMoreTokens()){
							paso[y]=st2.nextToken();
							//System.out.println(paso[y]);
							y++;
						}
						Enf=paso;
						break;
					}
					case 10:{
						StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
						String[] paso=new String[st2.countTokens()];
						int y=0;
						while(st2.hasMoreTokens()){
							paso[y]=st2.nextToken();
							//System.out.println(paso[y]);
							y++;
						}
						Alg=paso;
						break;
					}
					case 11:{
						StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
						String[] paso=new String[st2.countTokens()];
						int y=0;
						while(st2.hasMoreTokens()){
							paso[y]=st2.nextToken();
							//System.out.println(paso[y]);
							y++;
						}
						Med=paso;
						break;
					}
					case 12:{
						TipoSan=st.nextToken();
						//System.out.println(TipoSan);
						break;
					}
					case 13:{
						Prueba=st.nextToken().equals("true");
						//System.out.println(Prueba);
						break;
					}
					case 14:{
						Horas=st.nextToken();
						//System.out.println(Horas);
						break;
					}
					case 15:{
						StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
						boolean[] paso=new boolean[st2.countTokens()];
						int y=0;
						while(st2.hasMoreTokens()){
							paso[y]=st2.nextToken().equals("true");
							//System.out.println(paso[y]);
							y++;
						}
						dias=paso;
						break;
					}
					case 16:{
						tipo=st.nextToken().equals("true");
						//System.out.println(tipo);
						break;
					}
					case 17:{
						IDPadre=st.nextToken();
						//System.out.println(IDPadre);
						break;
						
					}
					case 18:{
						Ins=st.nextToken().equals("true");
						break;
					}
					case 19:{
						desc=Double.parseDouble(st.nextToken());
						break;
					}
					
				}
				cont++;
			}
			arrN[i]=new Ninos(IDN,Nombre,Edad,FechaN,Escu,Grad,Dir,CodP,TelC,Enf,Alg,Med,TipoSan,Prueba,Horas,dias,tipo,IDPadre,Ins,desc);
		}
		
		
		return arrN;
	}
	

	
	
	
	
	public void consultarNino(){
		// TODO Auto-generated method stub
				JFrame ventana=new JFrame();
				String titulo;
				if(tipo){
					titulo="Padres";
				}else{
					titulo="Niños";
				}
				
				Toolkit tools= getToolkit();
				Dimension size= tools.getScreenSize();
				ventana.setSize((size.width)/2,size.height);
				ventana.setTitle("Consultar "+"Niño");
				ventana.setLocation(0,0);
				GridBagConstraints lim=new GridBagConstraints();
				JPanel display= new JPanel();
				display.scrollRectToVisible(getBounds());
				display.setLayout(new GridBagLayout());
				ventana.getContentPane().add(display);
				lim.insets=new Insets(5,5,5,5);
				lim.gridx=1;
				if(ninos[pos].getIDNino().equals(""))
					for(int i=0;i<ninos.length;i++){
						if(!ninos[i].getIDNino().equals("")){
							pos=i;
						}
					}
				if(!ninos[pos].getIDNino().equals("")){
			    NombreClb=new JLabel("Nombre Completo: "+ninos[pos].getNombre());
				lim.gridy=0;
				display.add(NombreClb, lim);
				Edadlb=new JLabel("Edad: "+ninos[pos].getEdad());
				lim.gridy=1;
				display.add(Edadlb, lim);
				FechaDeNaciminetolb=new JLabel("Fecha de Nacimeiento: "+ninos[pos].getFechaDeNacimiento());
				lim.gridy=2;
				display.add(FechaDeNaciminetolb, lim);
				Escuelalb=new JLabel("Escuela: "+ninos[pos].getEscuela());
				lim.gridy=3;
				display.add(Escuelalb, lim);
				GradoEscolarlb=new JLabel("Grado Escolar: "+ninos[pos].getGradoEscolar());
				lim.gridy=4;
				display.add(GradoEscolarlb, lim);
				Direccionlb=new JLabel("Dirección: "+ninos[pos].getDireccion());
				lim.gridy=5;
				display.add(Direccionlb, lim);
				CodigoPostallb=new JLabel("Código Postal: "+ninos[pos].getCodigoPostal());
				lim.gridy=6;
				display.add(CodigoPostallb, lim);
				TelefonoDeCasalb=new JLabel("Número Telefónico de la casa: "+ninos[pos].getTelefonoCasa());
				lim.gridy=7;
				display.add(TelefonoDeCasalb, lim);
				String enfer="";
				for(int i=0;i<ninos[pos].getEnfermedades().length;i++){
					enfer+=", "+ninos[pos].getEnfermedades(i);
				}
				
				Enfermedadeslb=new JLabel("Enfermedades que padece: "+enfer);
				lim.gridy=8;
				display.add(Enfermedadeslb, lim);
				String aler="";
				for(int i=0;i<ninos[pos].getAlergias().length;i++){
					aler+=", "+ninos[pos].getAlergia(i);
				}
				Alergiaslb=new JLabel("Alergias del Niño: "+aler);
				lim.gridy=9;
				display.add(Alergiaslb, lim);
				String med="";
				for(int i=0;i<ninos[pos].getMedicamentos().length;i++){
					med+=", "+ninos[pos].getMedicamentos(i);
				}
			    Medicamentoslb=new JLabel("Medicamentos especiales del Niño: "+med);
				lim.gridy=10;
				display.add(Medicamentoslb, lim);
				
				TipoDeSangrelb=new JLabel("Tipo de Sangre: "+ninos[pos].getTipoDeSangre());
				lim.gridy=11;
				display.add(TipoDeSangrelb, lim);
				
				ClaseDePruebalb=new JLabel("Clase de Prueba: "+ninos[pos].getClaseDePrueba());
				lim.gridy=12;
				display.add(ClaseDePruebalb, lim);
				HorasDeClaselb=new JLabel("Hora de la clase: "+ninos[pos].getHorasDeClase());
				lim.gridy=13;
				display.add(HorasDeClaselb, lim);
				String diasClase="";
				for(int i=0;i<ninos[pos].getdiasDeSesion().length;i++){
					if(ninos[pos].getdiasDeSesion()[i]&&i==0){
						diasClase+="Lunes, ";
					}
					if(ninos[pos].getdiasDeSesion()[i]&&i==1){
						diasClase+="Martes, ";
					}
					if(ninos[pos].getdiasDeSesion()[i]&&i==2){
						diasClase+="Miércoles, ";
					}
					if(ninos[pos].getdiasDeSesion()[i]&&i==3){
						diasClase+="Jueves, ";
					}
					if(ninos[pos].getdiasDeSesion()[i]&&i==4){
						diasClase+="Viernes, ";
					}
					if(ninos[pos].getdiasDeSesion()[i]&&i==5){
						diasClase+="Sabado, ";
					}
					if(ninos[pos].getdiasDeSesion()[i]&&i==6){
						diasClase+="Domingo, ";
					}
				}
				
				diasSesionlb=new JLabel("Diás a asisitir: "+diasClase);
				lim.gridy=14;
				display.add(diasSesionlb, lim);
				String tipo;
				if(ninos[pos].getTipoDeSesion()){
				tipo="Regular";}else{
					tipo="Irregular";
				}
				TipoDeSesionlb=new JLabel("Tipo de Clase: "+tipo);//regular o irregular
				lim.gridy=15;
				display.add(TipoDeSesionlb, lim);
				IDPadreslb=new JLabel("ID del Padre: "+ninos[pos].getIDPadres());
				lim.gridy=16;
				display.add(IDPadreslb,lim);
				IDNinoslb=new JLabel("ID del Niño: "+ninos[pos].getIDNino());
				lim.gridy=17;
				display.add(IDNinoslb,lim);
				Inscripcion=new JLabel("¿Esta Inscrito Actualmente?: "+ninos[pos].getInscrito());
				lim.gridy=18;
				display.add(Inscripcion, lim);
				Desceunto=new JLabel("Desceunto: "+ninos[pos].getDescuentos()+"%");
				lim.gridy=19;
				display.add(Desceunto,lim);
				}	
				lim.gridy=20;
				JButton der=new JButton("-->");
				der.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
						pos++;
					if(pos>ninos.length-1){
						pos=0;
						
					}
					if(!ninos[pos].getIDNino().equals("")){
					NombreClb.setText(("Nombre Completo: "+ninos[pos].getNombre()));;
					Edadlb.setText(("Edad: "+ninos[pos].getEdad()));
					FechaDeNaciminetolb.setText(("Fecha de Nacimeiento: "+ninos[pos].getFechaDeNacimiento()));;
					Escuelalb.setText(("Escuela: "+ninos[pos].getEscuela()));
					GradoEscolarlb.setText(("Grado Escolar: "+ninos[pos].getGradoEscolar()));
					Direccionlb.setText(("Dirección: "+ninos[pos].getDireccion()));
					CodigoPostallb.setText("Código Postal: "+ninos[pos].getCodigoPostal());
					TelefonoDeCasalb.setText("Número Telefónico de la casa: "+ninos[pos].getTelefonoCasa());
					String enfer="";
					for(int i=0;i<ninos[pos].getEnfermedades().length;i++){
						enfer+=", "+ninos[pos].getEnfermedades(i);
					}
					Enfermedadeslb.setText(("Enfermedades que padece: "+enfer));;
					String aler="";
					for(int i=0;i<ninos[pos].getAlergias().length;i++){
						aler+=", "+ninos[pos].getAlergia(i);
					}
					Alergiaslb.setText(("Alergias del Niño: "+aler));;
					String med="";
					for(int i=0;i<ninos[pos].getMedicamentos().length;i++){
						med+=", "+ninos[pos].getMedicamentos(i);
					}
					Medicamentoslb.setText(("Medicamentos especiales del Niño: "+med));;
					TipoDeSangrelb.setText(("Tipo de Sangre: "+ninos[pos].getTipoDeSangre()));;
					ClaseDePruebalb.setText("Clase de Prueba: "+ninos[pos].getClaseDePrueba());
					HorasDeClaselb.setText("Horas de Clase: "+ninos[pos].getHorasDeClase());
					String diasClase="";
					for(int i=0;i<ninos[pos].getdiasDeSesion().length;i++){
						if(ninos[pos].getdiasDeSesion()[i]&&i==0){
							diasClase+="Lunes, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==1){
							diasClase+="Martes, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==2){
							diasClase+="Miércoles, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==3){
							diasClase+="Jueves, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==4){
							diasClase+="Viernes, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==5){
							diasClase+="Sabado, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==6){
							diasClase+="Domingo, ";
						}
					}
					
					diasSesionlb.setText("Diás a asisitir: "+diasClase);
					String tipo;
					if(ninos[pos].getTipoDeSesion()){
					tipo="Regular";}else{
						tipo="Irregular";
					}
					TipoDeSesionlb.setText("Tipo de Clase: "+tipo);
					IDPadreslb.setText("ID del Padre: "+ninos[pos].getIDPadres());
					IDNinoslb.setText(("ID del Niño: "+ninos[pos].getIDNino()));;
					Inscripcion.setText("¿Esta Inscrito Actualmente?: "+ninos[pos].getInscrito());
					Desceunto.setText("Desceunto: "+ninos[pos].getDescuentos()+"%");
					System.out.println("der :"+pos);
					ventana.repaint();}
				}});
				lim.gridx=2;
				display.add(der,lim);
				
				JButton izq=new JButton("<--");
				izq.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
					
						pos--;
					
					if(pos<0){
						pos=ninos.length-1;
					}
					if(!ninos[pos].getIDNino().equals("")){
					NombreClb.setText(("Nombre Completo: "+ninos[pos].getNombre()));;
					Edadlb.setText(("Edad: "+ninos[pos].getEdad()));
					FechaDeNaciminetolb.setText(("Fecha de Nacimeiento: "+ninos[pos].getFechaDeNacimiento()));;
					Escuelalb.setText(("Escuela: "+ninos[pos].getEscuela()));
					GradoEscolarlb.setText(("Grado Escolar: "+ninos[pos].getGradoEscolar()));
					Direccionlb.setText(("Dirección: "+ninos[pos].getDireccion()));
					CodigoPostallb.setText("Código Postal: "+ninos[pos].getCodigoPostal());
					TelefonoDeCasalb.setText("Número Telefónico de la casa: "+ninos[pos].getTelefonoCasa());
					String enfer="";
					for(int i=0;i<ninos[pos].getEnfermedades().length;i++){
						enfer+=", "+ninos[pos].getEnfermedades(i);
					}
					Enfermedadeslb.setText(("Enfermedades que padece: "+enfer));;
					String aler="";
					for(int i=0;i<ninos[pos].getAlergias().length;i++){
						aler+=", "+ninos[pos].getAlergia(i);
					}
					Alergiaslb.setText(("Alergias del Niño: "+aler));;
					String med="";
					for(int i=0;i<ninos[pos].getMedicamentos().length;i++){
						med+=", "+ninos[pos].getMedicamentos(i);
					}
					Medicamentoslb.setText(("Medicamentos especiales del Niño: "+med));;
					TipoDeSangrelb.setText(("Tipo de Sangre: "+ninos[pos].getTipoDeSangre()));;
					ClaseDePruebalb.setText("Clase de Prueba: "+ninos[pos].getClaseDePrueba());
					HorasDeClaselb.setText("Horas de clase: "+ninos[pos].getHorasDeClase());
					String diasClase="";
					for(int i=0;i<ninos[pos].getdiasDeSesion().length;i++){
						if(ninos[pos].getdiasDeSesion()[i]&&i==0){
							diasClase+="Lunes, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==1){
							diasClase+="Martes, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==2){
							diasClase+="Miercoles, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==3){
							diasClase+="Jueves, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==4){
							diasClase+="Viernes, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==5){
							diasClase+="Sabado, ";
						}
						if(ninos[pos].getdiasDeSesion()[i]&&i==6){
							diasClase+="Domingo, ";
						}
					}
					
					diasSesionlb.setText("Diás a asisitir: "+diasClase);
					String tipo;
					if(ninos[pos].getTipoDeSesion()){
					tipo="Regular";}else{
						tipo="Irregular";
					}
					TipoDeSesionlb.setText("Tipo de Clase: "+tipo);
					IDPadreslb.setText("ID del Padre: "+ninos[pos].getIDPadres());
					IDNinoslb.setText(("ID del Niño: "+ninos[pos].getIDNino()));;
					Inscripcion.setText("¿Esta Inscrito Actualmente?: "+ninos[pos].getInscrito());
					System.out.println("izq :"+pos);
					Desceunto.setText("Desceunto: "+ninos[pos].getDescuentos()+"%");
					ventana.repaint();
				}}});
				lim.gridx=0;
				display.add(izq, lim);
				ventana.setVisible(true);
				
	}

	
	
	public void consultarPadre(){
		JFrame ventana=new JFrame();
		String titulo;
		if(tipo){
			titulo="Padres";
		}else{
			titulo="Niños";
		}
		
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width),size.height);
		ventana.setTitle("Consultar "+"Padre");
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.scrollRectToVisible(getBounds());
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		lim.gridx=1;
		if(padres[pos].getIDPadres().equals(""))
			for(int i=0;i<padres.length;i++){
				if(!padres[i].getIDPadres().equals("")){
					pos=i;
				}
			}
		IDPadre=new JLabel("ID del Padre: "+padres[pos].getIDPadres());
		lim.gridy=0;
		display.add(IDPadre, lim);
		String IDN="";
		for(int z=0;z<padres[pos].getIDNino().length;z++){
			if(z==0){IDN+=padres[pos].getIDNino()[z];}else{
			
			IDN+="," +padres[pos].getIDNino()[z];
		}}
		
		
		IDNino=new JLabel("ID del Niños: "+IDN);;
		lim.gridy=1;
		display.add(IDNino, lim);
		NombreCompletoPadre=new JLabel("Nombre del Padre: "+padres[pos].getNombreCompletoPadre());
		lim.gridy=2;
		display.add(NombreCompletoPadre, lim);
		NombreCompletoMadre=new JLabel("Nombre de la Madre: "+padres[pos].getNombreCompletoMadre());
		lim.gridy=3;
		display.add(NombreCompletoMadre, lim);
		CelularMadre=new JLabel("Celular de la Madre: "+padres[pos].getCelularMadre());
		lim.gridy=4;
		display.add(CelularMadre, lim);
		CelularPadre=new JLabel("Celular del Padre: "+padres[pos].getCelularPadre());
		lim.gridy=5;
		display.add(CelularPadre, lim);
		CorreoPadre=new JLabel("Correo del Padre: "+padres[pos].getCorreoPadre());
		lim.gridy=6;
		display.add(CorreoPadre, lim);
		CorreoMadre=new JLabel("Correo de la Madre: "+padres[pos].getCorreoMadre());
		lim.gridy=7;
		display.add(CorreoMadre, lim);
		NombreContactoDeEmergancia=new JLabel("Nombre del Contacto de Emergencia: "+padres[pos].getNombreDeEmergencia());
		lim.gridy=8;
		display.add(NombreContactoDeEmergancia, lim);
		TelefonoDeEmergencia=new JLabel("Teléfono de Emergencia: "+padres[pos].getTelefonoDeEmergencia());
		lim.gridy=9;
		display.add(TelefonoDeEmergencia, lim);
		TipoDePago=new JLabel("Tipo de Pago: "+padres[pos].getTipoDePago());
		lim.gridy=10;
		display.add(TipoDePago, lim);
		Montolb=new JLabel("Monto A Pagar: "+padres[pos].getMontoAPagar());
		lim.gridy=11;
		display.add(Montolb,lim);
		
		JButton der= new JButton("-->");
		der.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
				pos++;
			
			if(pos>padres.length-1){
				pos=0;
			}
			String IDN="";
			for(int z=0;z<padres[pos].getIDNino().length;z++){
				if(z==0){IDN+=padres[pos].getIDNino()[z];}else{
				
				IDN+="," +padres[pos].getIDNino()[z];
			}}
			if(!padres[pos].getIDPadres().equals("")){
			IDPadre.setText("ID del Padre: "+padres[pos].getIDPadres());
			IDNino.setText("ID del Niños: "+IDN);;
			NombreCompletoPadre.setText("Nombre del Padre: "+padres[pos].getNombreCompletoPadre());
			NombreCompletoMadre.setText("Nombre de la Madre: "+padres[pos].getNombreCompletoMadre());
			CelularMadre.setText("Celular de la Madre: "+padres[pos].getCelularMadre());
			CelularPadre.setText("Celular del Padre: "+padres[pos].getCelularPadre());
			CorreoPadre.setText("Correo del Padre: "+padres[pos].getCorreoPadre());
			CorreoMadre.setText("Correo de la Madre: "+padres[pos].getCorreoMadre());
			NombreContactoDeEmergancia.setText("Nombre del Contacto de Emergencia: "+padres[pos].getNombreDeEmergencia());
			TelefonoDeEmergencia.setText("Teléfono de Emergencia: "+padres[pos].getTelefonoDeEmergencia());
			TipoDePago.setText("Tipo de Pago: "+padres[pos].getTipoDePago());
			Montolb.setText("Monto A Pagar: "+padres[pos].getMontoAPagar());
			}
		}});
		lim.gridx=2;
		lim.gridy=12;
		display.add(der, lim);
		
		JButton izq= new JButton("<--");
		izq.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
				pos--;
			
			if(pos<0){
				pos=padres.length-1;
			}
			String IDN="";
			for(int z=0;z<padres[pos].getIDNino().length;z++){
				if(z==0){IDN+=padres[pos].getIDNino()[z];}else{
				
				IDN+="," +padres[pos].getIDNino()[z];
			}}
			if(!padres[pos].getIDPadres().equals("")){
			IDPadre.setText("ID del Padre: "+padres[pos].getIDPadres());
			IDNino.setText("ID del Niños: "+IDN);;
			NombreCompletoPadre.setText("Nombre del Padre: "+padres[pos].getNombreCompletoPadre());
			NombreCompletoMadre.setText("Nombre de la Madre: "+padres[pos].getNombreCompletoMadre());
			CelularMadre.setText("Celular de la Madre: "+padres[pos].getCelularMadre());
			CelularPadre.setText("Celular del Padre: "+padres[pos].getCelularPadre());
			CorreoPadre.setText("Correo del Padre: "+padres[pos].getCorreoPadre());
			CorreoMadre.setText("Correo de la Madre: "+padres[pos].getCorreoMadre());
			NombreContactoDeEmergancia.setText("Nombre del Contacto de Emergencia: "+padres[pos].getNombreDeEmergencia());
			TelefonoDeEmergencia.setText("Teléfono de Emergencia: "+padres[pos].getTelefonoDeEmergencia());
			TipoDePago.setText("Tipo de Pago: "+padres[pos].getTipoDePago());
			Montolb.setText("Monto A Pagar: "+padres[pos].getMontoAPagar());
			}
		}});
		lim.gridx=0;
		lim.gridy=12;
		display.add(izq, lim);
		ventana.setVisible(true);
		//InscripcionYFechas=new JLabel("Fecha de Inscirpción: ");
	}
	
	public static void editarNinos(int posi){
		JFrame ventana=new JFrame();
		String titulo;
		if(tipo){
			titulo="Padres";
		}else{
			titulo="Niños";
		}
		Toolkit tools= ventana.getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width),size.height);
		ventana.setTitle("Editar "+titulo);
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		lim.gridx=0;
		
		JLabel NombreClb=new JLabel("Nombre Completo del Niño");
		lim.gridy=0;
		display.add(NombreClb, lim);
		JLabel Edadlb=new JLabel("Edad del Niño");
		lim.gridy=1;
		display.add(Edadlb, lim);
		JLabel FechaDeNaciminetolb=new JLabel("Fecha de Nacimeiento");
		lim.gridy=2;
		display.add(FechaDeNaciminetolb, lim);
		JLabel Escuelalb=new JLabel("Escuela");
		lim.gridy=3;
		display.add(Escuelalb, lim);
		JLabel GradoEscolarlb=new JLabel("Grado Escolar");
		lim.gridy=4;
		display.add(GradoEscolarlb, lim);
		JLabel Direccionlb=new JLabel("Dirección (Sin comas)");
		lim.gridy=5;
		display.add(Direccionlb, lim);
		JLabel CodigoPostallb=new JLabel("Código Postal");
		lim.gridy=6;
		display.add(CodigoPostallb, lim);
		JLabel TelefonoDeCasalb=new JLabel("Número Telefónico de la casa");
		lim.gridy=7;
		display.add(TelefonoDeCasalb, lim);
		JLabel Enfermedadeslb=new JLabel("Enfermedades que padece");
		lim.gridy=8;
		display.add(Enfermedadeslb, lim);
		JLabel Alergiaslb=new JLabel("Alergias del Niño");
		lim.gridy=9;
		display.add(Alergiaslb, lim);
		JLabel Medicamentoslb=new JLabel("Medicamentos especiales del Niño");
		lim.gridy=10;
		display.add(Medicamentoslb, lim);
		JLabel TipoDeSangrelb=new JLabel("Tipo de Sangre");
		lim.gridy=11;
		display.add(TipoDeSangrelb, lim);
		JLabel ClaseDePruebalb=new JLabel("Clase de Prueba");
		lim.gridy=12;
		display.add(ClaseDePruebalb, lim);
		JLabel HorasDeClaselb=new JLabel("Hora de la clase");
		lim.gridy=13;
		display.add(HorasDeClaselb, lim);
		JLabel diasSesionlb=new JLabel("Días a asisitir");
		lim.gridy=14;
		display.add(diasSesionlb, lim);
		JLabel TipoDeSesionlb=new JLabel("Tipo de Clase");//regular o irregular
		lim.gridy=15;
		display.add(TipoDeSesionlb, lim);
		JLabel IDPadreslb=new JLabel("Introdusca el ID del Padre");
		lim.gridy=16;
		display.add(IDPadreslb,lim);
		JLabel descuentolb=new JLabel("Descuento a aplicar");//-----------------------------------------------------
		lim.gridy=17;
		display.add(descuentolb, lim);
		String ID=ninos[posi].getIDNino();
		
		Checkbox Inscrito=new Checkbox("Inscrito");
		
		Inscrito.setState(ninos[posi].getInscrito());
		lim.gridx=1;
		lim.gridy=18;
		display.add(Inscrito,lim);
		
		
		
		
		lim.gridx=1;
		lim.ipadx=250;
		JTextField NombreC= new JTextField(ninos[posi].getNombre());
		lim.gridy=0;
		display.add(NombreC, lim);
		JTextField Edad= new JTextField(""+ninos[posi].getEdad());
		lim.gridy=1;
		display.add(Edad, lim);
		JTextField FechaDeNacimineto= new JTextField(ninos[posi].getFechaDeNacimiento());
		lim.gridy=2;
		display.add(FechaDeNacimineto, lim);
		JTextField Escuela= new JTextField(ninos[posi].getEscuela());
		lim.gridy=3;
		display.add(Escuela, lim);
		JTextField GradoEscolar= new JTextField(ninos[posi].getGradoEscolar());
		lim.gridy=4;
		display.add(GradoEscolar, lim);
		JTextField Direccion= new JTextField(ninos[posi].getDireccion());
		lim.gridy=5;
		display.add(Direccion, lim);
		JTextField CodigoPostal= new JTextField(""+ninos[posi].getCodigoPostal());
		lim.gridy=6;
		display.add(CodigoPostal, lim);
		JTextField TelefonoDeCasa= new JTextField(""+ninos[posi].getTelefonoCasa());
		lim.gridy=7;
		display.add(TelefonoDeCasa, lim);
		String enfer="";
		for(int i=0;i<ninos[posi].getEnfermedades().length;i++){
			if(i==0){
			enfer+=ninos[posi].getEnfermedades(i);}else{
			enfer+=","+ninos[posi].getEnfermedades(i);
		}}
		
		JTextField Enfermedades= new JTextField(enfer);
		lim.gridy=8;
		display.add(Enfermedades, lim);
		
		String alerg="";
		for(int i=0;i<ninos[posi].getAlergias().length;i++){
			if(i==0){
			alerg+=ninos[posi].getAlergia(i);}else{
			alerg+=","+ninos[posi].getAlergia(i);
		}}
		JTextField Alergias= new JTextField(alerg);
		lim.gridy=9;
		display.add(Alergias, lim);
		
		String medi="";
		for(int i=0;i<ninos[posi].getMedicamentos().length;i++){
			if(i==0){
			medi+=ninos[posi].getMedicamentos(i);}else{
			medi+=","+ninos[posi].getMedicamentos(i);
		}}
		JTextField Medicamentos= new JTextField(medi);
		lim.gridy=10;
		display.add(Medicamentos, lim);
		JTextField TipoDeSangre= new JTextField(ninos[posi].getTipoDeSangre());
		lim.gridy=11;
		display.add(TipoDeSangre, lim);
		Checkbox ClaseDePrueba=new Checkbox("Clase de Prueba");
		ClaseDePrueba.setState(ninos[posi].getClaseDePrueba());
		lim.gridy=12;
		display.add(ClaseDePrueba, lim);
		JTextField HorasDeClase= new JTextField(ninos[posi].getHorasDeClase());
		lim.gridy=13;
		display.add(HorasDeClase, lim);
		String txtDias=""; // Se le va a agregar los valores de el arreglo a un string para expresarlo en el label.
		for(int n=0;n<ninos[posi].getdiasDeSesion().length;n++){
			switch(n){
			case 0:{
				if(ninos[posi].getdiasDeSesion()[n]){
					txtDias+="Lu/";
				}
				break;
			}
			case 1:{
				if(ninos[posi].getdiasDeSesion()[n]){
					txtDias+="Ma/";
				}
				
				break;
			}
			case 2:{
				if(ninos[posi].getdiasDeSesion()[n]){
					txtDias+="Mi/";
				}
				break;
			}
			case 3:{
				if(ninos[posi].getdiasDeSesion()[n]){
					txtDias+="Ju/";
				}
				break;
			}
			
			case 4:{
				if(ninos[posi].getdiasDeSesion()[n]){
					txtDias+="Vi/";
				}
				break;
			}
			
			case 5:{
				txtDias+="Sa/";
				break;
			}
			case 6:{
				txtDias=txtDias.substring(0, txtDias.length()-2);
				break;
			}
			
			}
		}
		
		JTextField diasSesion= new JTextField(txtDias);
		lim.gridy=14;
		display.add(diasSesion, lim);
		Checkbox TipoDeSesion=new Checkbox ("Clase regular");
		TipoDeSesion.setState(ninos[posi].getTipoDeSesion());
		lim.gridy=15;
		display.add(TipoDeSesion, lim);
		JTextField IDPadres=new JTextField(ninos[posi].getIDPadres());
		lim.ipady=30;
		lim.gridy=16;
		display.add(IDPadres,lim);
		JTextField descuento=new JTextField(""+ninos[posi].getDescuentos());//-----------------------------------------------------
		descuento.setToolTipText("Escribir el porcentaje en número sin el signo de porcentaje (%) y letras");
		lim.gridy=17;
		display.add(descuento, lim);
		

		
		JButton Confirmar=new JButton("Confirmar");
		Confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			boolean hacer=true;
			String [] mens=new String[7];
			//Verificar valores invalidos
			//Verifica si nombres no tiene numeros
			char arrEv[]=NombreC.getText().toCharArray();
			char numInv[]=StriInvalid.toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<numInv.length;z++){
					if(arrEv[i]==numInv[z]){
						hacer=false;
						mens[0]="Valores incorrectos en el nombre";
					}
				}
				
			}
			//Verificar todos los valores numericos, para que no tengan letras
			//Edad
			char letrInv[]=NumInvalid.toCharArray();
			arrEv=Edad.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[1]="Valores incorrectos en la edad";
					}
				}
				
			}
			//Codigo Postal
			arrEv=CodigoPostal.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[2]="Valores incorrectos en el código postal";
					}
				}
				
			}
			//Telefono de casa
			arrEv=TelefonoDeCasa.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[3]="Valores incorrectos en el número télefono";
					}
				}
				
			}
			//Horas de clase
			arrEv=HorasDeClase.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[4]="Valores incorrectos en la hora clase";
					}
				}
				
			}
			arrEv=descuento.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int z=0;z<letrInv.length;z++){
					if(arrEv[i]==letrInv[z]){
						hacer=false;
						mens[5]="Valores incorrectos en el descuento";
					}
				}
				
			}
			
			
			if(hacer){
			
			StringTokenizer ST=new StringTokenizer(Enfermedades.getText(),",");
			Ninos nino;
			int cont=0;
			String arrE[]=new String [ST.countTokens()];
			do{
				
			 arrE[cont]=ST.nextToken();
			 cont++;
			}while(ST.hasMoreTokens());
			ST=new StringTokenizer(Alergias.getText(),",");
			cont=0;
			String arrA[]=new String [ST.countTokens()];
			do{
				
			 arrA[cont]=ST.nextToken();
			 cont++;
			}while(ST.hasMoreTokens());
			ST=new StringTokenizer(Medicamentos.getText(),",");
			cont=0;
			String arrM[]=new String [ST.countTokens()];
			do{
				
			 arrM[cont]=ST.nextToken();
			 System.out.println(arrM[cont]);
			 cont++;
			}while(ST.hasMoreTokens());
			diasSesion.getText().toUpperCase();
			ST=new StringTokenizer(diasSesion.getText(),"/");
			boolean[] arrDias=new boolean[7];
			while(ST.hasMoreTokens()){
				String paso=ST.nextToken();
				if(paso.equals("LU")){
					arrDias[0]=true;
				}
				if(paso.equals("MA")){
					arrDias[1]=true;
				}
				if(paso.equals("MI")){
					arrDias[2]=true;
				}
				if(paso.equals("JU")){
					arrDias[3]=true;
				}
				if(paso.equals("VI")){
					arrDias[4]=true;
				}
				if(paso.equals("SA")){
					arrDias[5]=true;
				}
				if(paso.equals("DO")){
					arrDias[6]=true;
				}
			}
			
			nino=new Ninos(ID,NombreC.getText(),Integer.parseInt(Edad.getText()),FechaDeNacimineto.getText(),Escuela.getText(),GradoEscolar.getText(),Direccion.getText(),Integer.parseInt(CodigoPostal.getText()),Integer.parseInt(TelefonoDeCasa.getText()),arrE,arrA,arrM,TipoDeSangre.getText(),ClaseDePrueba.getState(),HorasDeClase.getText(),arrDias,TipoDeSesion.getState(),Double.parseDouble(descuento.getText()));
			nino.setIDPadres(IDPadres.getText());
			nino.setInscrito(Inscrito.getState());
			ninos[posi]=nino;
				try {
					writeFile(ninos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ventana.setVisible(false);
			}else{
				error(mens);
			}
		}});
		lim.ipady=30;
		lim.gridy=19;
		Confirmar.setText("Confirmar");
		Confirmar.setToolTipText("Confirma la accion a llevar a acabo");
		display.add(Confirmar,lim);
		ventana.setVisible(true);
		
	}

	
	public static void editarPadres(int posi){
		JFrame ventana=new JFrame();
		String titulo;
		if(tipo){
			titulo="Padres";
		}else{
			titulo="Niños";
		}
		Toolkit tools= ventana.getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width),size.height);
		ventana.setTitle("Editar "+titulo);
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		lim.gridx=0;
		JLabel NombreCPlb=new JLabel("Nombre Completo del Padre:");
		lim.gridy=0;
		display.add(NombreCPlb,lim);
		JLabel NombreCMlb=new JLabel("Nombre Completo de la Madre");
		lim.gridy=1;
		display.add(NombreCMlb,lim);
		JLabel CelularMlb=new JLabel("Celular de la Madre");
		lim.gridy=2;
		display.add(CelularMlb,lim);
		JLabel CelularPlb=new JLabel("Celular del Padre");
		lim.gridy=3;
		display.add(CelularPlb,lim);
		JLabel CorreoPlb=new JLabel("Correo del Padre");
		lim.gridy=4;
		display.add(CorreoPlb,lim);
		JLabel CorreoMlb=new JLabel("Correo de la Madre");
		lim.gridy=5;
		display.add(CorreoMlb,lim);
		JLabel NombreCDE=new JLabel("Nombre del Contacto de Emergencia");
		lim.gridy=6;
		display.add(NombreCDE,lim);
		JLabel TelefonoDE=new JLabel("Teléfono del Contacto de Emergencia");
		lim.gridy=7;
		display.add(TelefonoDE,lim);
		JLabel TipoDePago=new JLabel("Tipo de Pago");
		lim.gridy=8;
		display.add(TipoDePago,lim);
		JLabel IYF=new JLabel("Fecha de Inscripción");
		lim.gridy=9;
		display.add(IYF,lim);
		JLabel MontoLb=new JLabel("Monto a Pagar");
		lim.gridy=10;
		display.add(MontoLb,lim);
		
		lim.ipadx=250;
		lim.gridx=1;
		JTextField NombreCP=new JTextField(padres[posi].getNombreCompletoPadre());
		lim.gridy=0;
		display.add(NombreCP,lim);
		JTextField NombreCM=new JTextField(padres[posi].getNombreCompletoMadre());
		lim.gridy=1;
		display.add(NombreCM,lim);
		JTextField CelularM=new JTextField(""+padres[posi].getCelularMadre());
		lim.gridy=2;
		display.add(CelularM,lim);
		JTextField CelularP=new JTextField(""+padres[posi].getCelularPadre());
		lim.gridy=3;
		display.add(CelularP,lim);
		JTextField CorreoP=new JTextField(padres[posi].getCorreoPadre());
		lim.gridy=4;
		display.add(CorreoP,lim);
		JTextField CorreoM=new JTextField(padres[posi].getCorreoMadre());
		lim.gridy=5;
		display.add(CorreoM,lim);
		JTextField NombreCE=new JTextField(padres[posi].getNombreDeEmergencia());
		lim.gridy=6;
		display.add(NombreCE,lim);
		JTextField TelefonoE=new JTextField(padres[posi].getTelefonoDeEmergencia());
		lim.gridy=7;
		display.add(TelefonoE,lim);
		JTextField TipoDePagos=new JTextField(padres[posi].getTipoDePago());//Cambiar a checkboxes
		lim.gridy=8;
		display.add(TipoDePagos,lim);
		JTextField IYFs=new JTextField(padres[posi].getInscripcionYFecha());
		lim.gridy=9;
		display.add(IYFs,lim);
		JTextField Monto=new JTextField(""+padres[posi].getMontoAPagar());
		lim.gridy=10;
		display.add(Monto,lim);
		
		
		JButton Confirmar=new JButton("Confirmar");
		Confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			boolean hacer=true;
			char[] arrEv=NombreCP.getText().toCharArray();
			char[] numInv=StriInvalid.toCharArray();
			String[] mens=new String[7];
			for(int i=0;i<mens.length;i++)
				mens[i]="";
			
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<numInv.length;n++){
					if(numInv[n]==arrEv[i]){
						hacer=false;
						mens[0]="Valores incorrectos en nombre del padre";
					}
				}
			}
			
			arrEv=NombreCM.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<numInv.length;n++){
					if(numInv[n]==arrEv[i]){
						hacer=false;
						mens[1]="Valores incorrectos en nombre de la madre";
					}
				}
			}
			arrEv=NombreCE.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<numInv.length;n++){
					if(numInv[n]==arrEv[i]){
						hacer=false;
						mens[2]="Valores incorrectos en el nombre del contacto de emergencia";
					}
				}
			}
			
			//Verificar valores numericos
			char[] striInv=NumInvalid.toCharArray();
			arrEv=CelularP.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<striInv.length;n++){
					if(striInv[n]==arrEv[i]){
						hacer=false;
						mens[3]="Valores incorrectos en el teléfono de la madre";
					}
				}
			}
			
			arrEv=CelularM.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<striInv.length;n++){
					if(striInv[n]==arrEv[i]){
						mens[4]="Valores incorrectos en el teléfono del padre";
						hacer=false;
					}
				}
			}
			arrEv=TelefonoE.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<striInv.length;n++){
					if(striInv[n]==arrEv[i]){
						hacer=false;
						mens[5]="Valores incorrectos en el teléfono de emergencia";
					}
				}
			}
			arrEv=Monto.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<striInv.length;n++){
					if(striInv[n]==arrEv[i]){
						hacer=false;
						mens[6]="Valores incorrectos en el monto";
					}
				}
			}
			if(hacer){
			
			String[] IDS=padres[posi].getIDNino();
			String ID=padres[posi].getIDPadres();
			padres[posi]=new Padres(ID,IDS,NombreCP.getText(),NombreCM.getText(),(CelularM.getText()),(CelularP.getText()),CorreoP.getText(),CorreoM.getText(),NombreCE.getText(),(TelefonoE.getText()),TipoDePagos.getText(),Double.parseDouble(Monto.getText()),IYFs.getText());//Flata tipos de pago  e fechas de inscripciones);
			System.out.println("Confirmar");
			try {
				writeFile(padres);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			ventana.setVisible(false);
			}else{
				error(mens);
			}
		}});
		lim.gridy=12;
		display.add(Confirmar,lim);
		ventana.setVisible(true);
	}	
	
	
	
	public static void buscarId(int x){
		JFrame buscar=new JFrame();
		Toolkit tools= buscar.getToolkit();
		Dimension size= tools.getScreenSize();
		buscar.setSize((size.width)/2,size.height/2);
		buscar.setLocation(size.width/3,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		buscar.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		buscID=0;
		JTextField introId=new JTextField("Introducir ID aquí");
		introId.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(	introId.getText().equals("Introducir ID aquí")){
					introId.setText("");
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		JLabel instruccioneslb=new JLabel("Introdusca el ID a buscar: ");
		JButton confirmar=new JButton("Confirmar");
		confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			if(tipo){
				for(int i=0;i<padres.length;i++){
					if(introId.getText().equals(padres[i].getIDPadres())){
						buscID=i;
					}
					if(introId.getText().equals(padres[i].getIDNino())){
						buscID=i;
					}
				}
			}else{
				for(int i=0;i<ninos.length;i++){
					if(introId.getText().equals(ninos[i].getIDNino())){
						buscID=i;
					}
				}
			}
			buscar.setVisible(false);
			if(x==0){
				editarNinos(buscID);
			}
			if(x==1){
				editarPadres(buscID);
			}
		}});
		
		lim.gridx=1;
		lim.gridy=0;
		display.add(introId,lim);
		
		lim.gridx=0;
		lim.gridy=0;
		display.add(instruccioneslb,lim);
		
		lim.gridx=0;
		lim.gridy=1;
		display.add(confirmar,lim);
		
		buscar.setVisible(true);
	}
	
	public static void borrar(){
		JFrame buscar=new JFrame();
		Toolkit tools= buscar.getToolkit();
		Dimension size= tools.getScreenSize();
		buscar.setSize((size.width)/2,size.height/2);
		buscar.setLocation(size.width/3,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		buscar.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		
		
		JTextField introId=new JTextField("Introducir ID aquí");
		JLabel instruccioneslb=new JLabel("Introdusca el ID a borrar: ");
		JButton confirmar=new JButton("Confirmar");
		confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			if(tipo){
				for(int i=0;i<padres.length;i++){
					if(introId.getText().equals(padres[i].getIDPadres())){
						padres[i]=new Padres();	
					i+=padres.length;	
					Listo();
					}
				}
			}else{
				for(int i=0;i<ninos.length;i++){
					if(introId.getText().equals(ninos[i].getIDNino())){
						ninos[i]=new Ninos();
						i+=ninos.length;
						Listo();
					}
				}
			}
			
			try {
				writeFile(padres);
				writeFile(ninos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			buscar.setVisible(false);
			
			
		}});
		
		lim.gridx=1;
		lim.gridy=0;
		display.add(introId,lim);
		
		lim.gridx=0;
		lim.gridy=0;
		display.add(instruccioneslb,lim);
		
		lim.gridx=0;
		lim.gridy=1;
		display.add(confirmar,lim);
		
		buscar.setVisible(true);
	}
		public static void Listo(){
			JFrame list=new JFrame();
			Toolkit tools= list.getToolkit();
			Dimension size= tools.getScreenSize();
			list.setSize(300,300);
			list.setLocation(size.width/3,size.height/4);
			GridBagConstraints lim=new GridBagConstraints();
			JPanel display= new JPanel();
			display.setLayout(new GridBagLayout());
			list.getContentPane().add(display);
			lim.insets=new Insets(5,5,5,5);
			JLabel listo=new JLabel("Listo");
			display.add(listo);
		}
	
	
	public Padres[] aumentarArr(Padres[] arr){
		System.out.println("corrio el aumento a los padres");
		Padres[] paso=arr;
		arr=new Padres[paso.length+1];
		for(int i=0;i<paso.length;i++){
			arr[i]=paso[i];
		}
		arr[arr.length-1]=new Padres();
		return arr;
	}	
	
	
    public Ninos[] aumentarArr(Ninos[] arr){
		System.out.println("Corrio el aumento a los ninos");
		Ninos[] paso=arr;
		arr=new Ninos[paso.length+1];
		for(int i=0;i<paso.length;i++){
			arr[i]=paso[i];
		}
		arr[arr.length-1]=new Ninos();
		
		return arr;
	}
    
    public static void error(String[] mens){
		JFrame err=new JFrame();
		Toolkit tools= err.getToolkit();
		Dimension size= tools.getScreenSize();
		err.setSize(300,300);
		err.setLocation(size.width/3,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		err.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		JLabel error=new JLabel("Error: Algun Valor Introducido es Incorrecto:");
		JList listerr=new JList(mens);
		lim.gridy=1;
		display.add(listerr, lim);
		
		display.add(error);
		err.setVisible(true);
    }
    
    //Este método se encarga de generar una ventana con una tabla correspondiente (ej. si está consultando los datos de los niños crea una tabla con datos de los niños)
    public static void tablaCon(){
    	JFrame con=new JFrame();
    	con.setTitle("Ver y consultar datos en tablas");;
		Toolkit tools= con.getToolkit();
		Dimension size= tools.getScreenSize();
		con.setSize((int)size.getWidth(),(int)size.getHeight()/2);
		con.setLocation(0,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		con.getContentPane().setLayout(new GridBagLayout());
		display.setLayout(new GridBagLayout());
		con.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		JTable tab;
		String head[];
		Object content[][];
		if(tipo){
			//Forma para crear la tabla para padres
			head=new String[12];
			for(int n=0;n<head.length;n++){
				switch(n){
				case 0:{
					head[n]="ID del Padre";
					break;
				}
				case 1:{
					head[n]="ID de los Niños";
					break;
				}
				case 2:{
					head[n]="Nombre del Padre";
					break;
				}
				case 3:{
					head[n]="Nombre de la Madre";
					break;
				}
				case 4:{
					head[n]="Celular de la Madre";
					break;
				}
				case 5:{
					head[n]="Celular del Padre";
					break;
				}
				case 6:{
					head[n]="Correo del Padre";
					break;
				}
				case 7:{
					head[n]="Correo de la Madre";
					break;
				}
				case 8:{
					head[n]="Nombre del Contacto de Emergencia";
					break;
				}
				case 9:{
					head[n]="Teléfono de Emergencia";
					break;
				}
				case 10:{
					head[n]="Tipo de Pago";
					break;
				}
				case 11:{
					head[n]="Monto a pagar";
					break;
				}
				
				}
			}
			content=new Object [padres.length][12];
			for(int n=0;n<padres.length;n++){
				if(!padres[n].getIDPadres().equals("")){
				content[n][0]= padres[n].getIDPadres();
				String idn="";
				for(int z=0;z<padres[n].getIDNino().length;z++){
					if(z==padres[n].getIDNino().length-1){
						idn+=padres[n].getIDNino()[z];
					}else{
					
					idn+=padres[n].getIDNino()[z]+",";
					}
					
				}
				content[n][1]=idn;
				content[n][2]=padres[n].getNombreCompletoPadre();
				content[n][3]=padres[n].getNombreCompletoMadre();
				content[n][4]=""+padres[n].getCelularMadre();
				content[n][5]=""+padres[n].getCelularPadre();
				content[n][6]=padres[n].getCorreoPadre();
				content[n][7]=padres[n].getCorreoMadre();
				content[n][8]=padres[n].getNombreDeEmergencia();
				content[n][9]=padres[n].getTelefonoDeEmergencia();
				content[n][10]=padres[n].getTipoDePago();
				content[n][11]=""+padres[n].getMontoAPagar();//-------------------------------------------------
				}
				}
			
			//Para evitar la edición de una columna investigue y use el modelo sugerido en la página: https://stackoverflow.com/questions/8167173/java-jtable-make-only-one-column-editable
			tab=new JTable(content,head){
				public boolean isCellEditable(int row, int col){
					switch(col){
					case 0:{
						return false;
						
					}
					default:{
						return true;
						
					}
					}
				
				}
			};
			resizeColumnWidth(tab);
			tab.setAutoResizeMode(tab.AUTO_RESIZE_OFF);
			
			
			
			
			
			
		}else{
			//Forma para crear la tabla para ninos
			head=new String[20];
			for(int n=0;n<head.length;n++){
				switch(n){
				
				case 0:{
					head[n]="ID del Niño";
					break;
				}
				case 1:{
					head[n]="ID del Padre";
					break;
				}
				case 2:{
					head[n]="Nombre Completo";
					break;
				}
				case 3:{
					head[n]="Edad";
					break;
				}
				case 4:{
					head[n]="Fecha de Nacimiento";
					break;
				}
				case 5:{
					head[n]="Escuela";
					break;
				}
				case 6:{
					head[n]="Grado Escolar";
					break;
				}
				case 7:{
					head[n]="Dirección";
					break;
				}
				case 8:{
					head[n]="Código Postal";
					break;
				}
				case 9:{
					head[n]="Número Telefónico";
					break;
				}
				case 10:{
					head[n]="Enfermedades";
					break;
				}
				case 11:{
					head[n]="Alergias";
					break;
				}
				case 12:{
					head[n]="Medicamentos";
					break;
				}
				case 13:{
					head[n]="Tipo de Sangre";
					break;
				}
				case 14:{
					head[n]="Clase de Prueba";
					break;
				}
				
				case 15:{
					head[n]="Hora";
					break;
				}
				case 16:{
					head[n]="Dias a asistir";
					break;
				}
				case 17:{
					head[n]="Tipo de Clase";
					break;
				}
				case 18:{
					head[n]="Inscrito";
					break;
				}
				case 19:{
					head[n]="Descuento";
					break;
				}
				
				}
			}
			
			content=new Object[ninos.length][20];
			
			for(int n=0;n<ninos.length;n++){
				if(!ninos[n].getIDNino().equals("")){
				content[n][0]=ninos[n].getIDNino();
				content[n][1]=ninos[n].getIDPadres();
				content[n][2]=ninos[n].getNombre();
				content[n][3]=ninos[n].getEdad();
				content[n][4]=ninos[n].getFechaDeNacimiento();
				content[n][5]=ninos[n].getEscuela();
				content[n][6]=ninos[n].getGradoEscolar();
				content[n][7]=ninos[n].getDireccion();
				content[n][8]=ninos[n].getCodigoPostal();
				content[n][9]=ninos[n].getTelefonoCasa();
				String txt="";
				for(int x=0;x<ninos[n].getEnfermedades().length;x++){
					if(x==ninos[n].getEnfermedades().length-2){
						txt+=ninos[n].getEnfermedades(x);
					}else{
						txt+=ninos[n].getEnfermedades(x)+",";
					}
				}
				content[n][10]=txt;
				txt="";
				
				for(int x=0;x<ninos[n].getAlergias().length;x++){
					if(x==ninos[n].getAlergias().length-2){
						txt+=ninos[n].getAlergia(x);
					}else{
						txt+=ninos[n].getAlergia(x)+",";
					}
				}
				content[n][11]=txt;
				txt="";
				
				for(int x=0;x<ninos[n].getMedicamentos().length;x++){
					if(x==ninos[n].getMedicamentos().length-2){
						txt+=ninos[n].getMedicamentos(x);
					}else{
						txt+=ninos[n].getMedicamentos(x)+",";
					}
				}
				content[n][12]=txt;
				content[n][13]=ninos[n].getTipoDeSangre();
				content[n][14]=ninos[n].getClaseDePrueba();
				content[n][15]=ninos[n].getHorasDeClase();
				txt="";
				System.out.println("Length de ninos="+ninos[n].getdiasDeSesion().length);
				for(int x=0;x<ninos[n].getdiasDeSesion().length;x++){
					if(ninos[n].getdiasDeSesion()[x]){
					System.out.println("===================>>>>>>>>>>>>>Días sesión["+x+"]"+"== "+ninos[n].getdiasDeSesion()[x]);;
					switch(x){
					case 0:{
						txt+="Lunes";
						if(!(x==ninos[n].getdiasDeSesion().length-2)||ninos[n].getdiasDeSesion()[x]){
							txt+=",";
						}
						break;
					}
					case 1:{
						txt+="Martes";
						if(!(x==ninos[n].getdiasDeSesion().length-2)||ninos[n].getdiasDeSesion()[x]){
							txt+=",";
						}
						break;
					}
					case 2:{
						txt+="Miercoles";
						if(!(x==ninos[n].getdiasDeSesion().length-2)||ninos[n].getdiasDeSesion()[x]){
							txt+=",";
						}
						break;
					}
					case 3:{
						txt+="Jueves";
						if(!(x==ninos[n].getdiasDeSesion().length-2)||ninos[n].getdiasDeSesion()[x]){
							txt+=",";
						}
						break;
					}
					case 4:{
						txt+="Viernes";
						if(!(x==ninos[n].getdiasDeSesion().length-2)||ninos[n].getdiasDeSesion()[x]){
							txt+=",";
						}
						break;
					}
					case 5:{
						txt+="Sabado";
						if(!(x==ninos[n].getdiasDeSesion().length-2)||ninos[n].getdiasDeSesion()[x]){
							txt+=",";
						}
						break;
					}
					}
					}
					
				}
				
				content[n][16]=txt;
				content[n][17]=new Boolean(ninos[n].getClaseDePrueba());
				content[n][18]=new Boolean(ninos[n].getInscrito());
				content[n][19]=new Double(ninos[n].getDescuentos());
				}
				
						
			}
			tab=new JTable(content,head){
				public boolean isCellEditable(int row, int col){
					switch(col){
					case 0:{
						return false;
						
					}
					default:{
						return true;
						
					}
					}
					
				}
			};
			resizeColumnWidth(tab);
			tab.setAutoResizeMode(tab.AUTO_RESIZE_OFF);
			JCheckBox box=new JCheckBox();
			tab.getColumnModel().getColumn(18).setCellEditor(new DefaultCellEditor(box));//Esto permite que el usuario pueda cambiar el valor de inscrito desde la tabla
			tab.setPreferredScrollableViewportSize(new Dimension((int)size.getWidth(),(int)size.getHeight()/2));
			
		}
		
		
    		
		JScrollPane scroll=new JScrollPane(tab);
		scroll.setHorizontalScrollBar(scroll.createHorizontalScrollBar());
		
		
		//Maneja el proceso de Guardar y cerrar de la ventana, validando primero los datos antes de guardarlos.
		JButton gcerr=new JButton("Guardar y Cerrar");
		gcerr.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent event){
			//Variable para manejar la validación
			boolean validar=true; 
			char[] val;
			String[] mens=new String[7];
			for(int i=0;i<mens.length;i++)
				mens[i]="";
			char[] invalidoParaString=StriInvalid.toCharArray();
			char[] invalidoParaNum=NumInvalid.toCharArray();
			
			if(tipo){
				//Validación de los nombres; se asegura ue los nombres no tengan letras.
				
				for(int n=0;n<padres.length;n++){
					if(!padres[n].getIDPadres().equals("")){
					String line="";
					line=tab.getValueAt(n, 2).toString();
					val=line.toCharArray();
					for(int r=0;r<invalidoParaString.length;r++){
						for(int num=0;num<val.length;num++){
							if(val[num]==invalidoParaString[r]){
								validar=false;
								mens[0]="El nombre del padre tiene dígitos invalidos";
							}
						}
					}
					line=tab.getValueAt(n, 3).toString();
					val=line.toCharArray();
					for(int r=0;r<invalidoParaString.length;r++){
						for(int num=0;num<val.length;num++){
							if(val[num]==invalidoParaString[r]){
								validar=false;
								mens[1]="Nombre de la madre tiene dígitos invalidos";
							}
						}
					}
					
					line=tab.getValueAt(n, 8).toString();
					val=line.toCharArray();
					for(int r=0;r<invalidoParaString.length;r++){
						for(int num=0;num<val.length;num++){
							if(val[num]==invalidoParaString[r]){
								validar=false;
								mens[2]="Nombre del contacto de emergencia tiene dígitos invalidos";
							}
						}
					}
					//Validación de valores numéricos, se asegura que toda característica de atributo numerico tenga valores numericos
					line=tab.getValueAt(n, 4).toString();
					val=line.toCharArray();
					for(int r=0;r<invalidoParaNum.length;r++){
						for(int num=0;num<val.length;num++){
							if(val[num]==invalidoParaNum[r]){
								validar=false;
								mens[3]="Teléfono de la madre tiene valores incorrectos";
							}
						}
					}
					line=tab.getValueAt(n, 5).toString();
					val=line.toCharArray();
					for(int r=0;r<invalidoParaNum.length;r++){
						for(int num=0;num<val.length;num++){
							if(val[num]==invalidoParaNum[r]){
								validar=false;
								mens[4]="Teléfono del padre tiene valores incorrectos";
							}
						}
					}
					line=tab.getValueAt(n, 9).toString();
					val=line.toCharArray();
					for(int r=0;r<invalidoParaNum.length;r++){
						for(int num=0;num<val.length;num++){
							if(val[num]==invalidoParaNum[r]){
								validar=false;
								mens[5]="Teléfono del contecto de emergencia tiene valores incorrectos";
							}
						}
					}
					line=tab.getValueAt(n, 11).toString();
					val=line.toCharArray();
					for(int r=0;r<invalidoParaNum.length;r++){
						for(int num=0;num<val.length;num++){
							if(val[num]==invalidoParaNum[r]){
								validar=false;
								mens[6]="Monto tiene valores incorrectos";
							}
						}
					}
					
				}
				}
				
				if(validar){
					for(int n=0;n<padres.length;n++){
						String line="";
						int z=0;
						if(!padres[n].getIDPadres().equals("")){
						line=tab.getValueAt(n, 1).toString();
						StringTokenizer st=new StringTokenizer(line,",");
						String idn[]=new String[st.countTokens()];
						while(st.hasMoreTokens()){
							idn[z]=st.nextToken();
							z++;
						}
						padres[n].setIDNino(idn);
						
						padres[n].setNombreCompletoPadre(tab.getValueAt(n, 2).toString());
						padres[n].setNombreCompletoMadre(tab.getValueAt(n, 3).toString());
						padres[n].setCelularMadre(tab.getValueAt(n, 4).toString());
						padres[n].setCelularPadre(tab.getValueAt(n, 5).toString());
						padres[n].setCorreoPadre(tab.getValueAt(n, 6).toString());
						padres[n].setCorreoMadre(tab.getValueAt(n, 7).toString());
						padres[n].setNombreContactoDeEmergencia(tab.getValueAt(n, 8).toString());
						padres[n].setTelefonoDeEmergencia(tab.getValueAt(n, 9).toString());
						padres[n].setTipoDePago(tab.getValueAt(n, 10).toString());
						padres[n].setMontoAPagar(Double.parseDouble(tab.getValueAt(n, 11).toString()));
						con.setVisible(false);
						try {
							writeFile(padres);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					}
				}else{
					error(mens);
				}
				
			}
			else{
				
			for(int n=0;n<ninos.length;n++){
				//Variables que pueden ser utilizadas dentro de la operación
				String line="";
				if(!ninos[n].getIDNino().equals("")){
				line=(String)tab.getValueAt(n,2);
				val=line.toCharArray();
				line="";
				for(int i=0;i<val.length;i++){
					for(int r=0;r<invalidoParaString.length;r++){
						if(val[i]==invalidoParaString[r]){
							validar=false;
							mens[0]="El nombre contiene dígitos invalidos";
						}
					}
				}
				line=tab.getValueAt(n,3).toString();
				val=line.toCharArray();
				line="";
				for(int i=0;i<val.length;i++){
					for(int r=0;r<invalidoParaNum.length;r++){
						if(val[i]==invalidoParaNum[r]){
							validar=false;
							mens[1]="Edad tiene dígitos invaildo";
						}
					}
				}
				line=tab.getValueAt(n,8).toString();
				val=line.toCharArray();
				line="";
				for(int i=0;i<val.length;i++){
					for(int r=0;r<invalidoParaNum.length;r++){
						if(val[i]==invalidoParaNum[r]){
							validar=false;
							mens[2]="Código postal tiene dígitos invaildos";
						}
					}
				}
				line=tab.getValueAt(n,9).toString().toString();
				val=line.toCharArray();
				line="";
				for(int i=0;i<val.length;i++){
					for(int r=0;r<invalidoParaNum.length;r++){
						if(val[i]==invalidoParaNum[r]){
							validar=false;
							mens[3]="El teléfono de casa tiene dígitos invalidos";
						}
					}
				}
				line=tab.getValueAt(n,15).toString();
				val=line.toCharArray();
				line="";
				for(int i=0;i<val.length;i++){
					for(int r=0;r<invalidoParaNum.length;r++){
						if(val[i]==invalidoParaNum[r]){
							validar=false;
							mens[4]="Las horas de clase tienen dígitos invalidos";
						}
					}
				}
				
				line=tab.getValueAt(n,19).toString();
				val=line.toCharArray();
				line="";
				for(int i=0;i<val.length;i++){
					for(int r=0;r<invalidoParaNum.length;r++){
						if(val[i]==invalidoParaNum[r]){
							validar=false;
							mens[5]="El descuento tiene dígitos invalidos";
						}
					}
				}
				}
			}
			if(validar){
			for(int n=0;n<ninos.length;n++){
				int z=0;
				String arr[];
				StringTokenizer st;
				if(!ninos[n].getIDNino().equals("")){
					ninos[n].setIDPadres(tab.getValueAt(n, 1).toString());
					System.out.println(ninos[n].getIDPadres());;
					ninos[n].setNombre( tab.getValueAt(n, 2).toString());				
					ninos[n].setEdad(Integer.parseInt( tab.getValueAt(n, 3).toString()));
					ninos[n].setFechaDeNacimiento(tab.getValueAt(n,4).toString());
					ninos[n].setEscuela( tab.getValueAt(n, 5).toString());
					ninos[n].setGradoEscolar( tab.getValueAt(n, 6).toString());
					ninos[n].setDireccion(tab.getValueAt(n, 7).toString());
					ninos[n].setCodigoPostal(Integer.parseInt( tab.getValueAt(n, 8).toString()));
					ninos[n].setTelefonoCasa(Integer.parseInt( tab.getValueAt(n, 9).toString()));
					
					st=new StringTokenizer((String) tab.getValueAt(n,10),",");
					arr=new String[st.countTokens()];
					while(st.hasMoreTokens()){
						arr[z]=st.nextToken();
						z++;
					}
					
					
					ninos[n].setEnfermedades(arr);
					st=new StringTokenizer((String)tab.getValueAt(n, 11),",");
					z=0;
					arr=new String[st.countTokens()];
					while(st.hasMoreTokens()){
						arr[z]=st.nextToken();
						z++;
					}
					
					ninos[n].setAlergias(arr);
					
					st=new StringTokenizer((String)tab.getValueAt(n, 12),",");
					arr=new String[st.countTokens()];
					z=0;
					while(st.hasMoreTokens()){
						arr[z]=st.nextToken();
						z++;
					}
					ninos[n].setMedicamentos(arr);
					
					ninos[n].setTipoDeSangre((String) tab.getValueAt(n, 13));
					ninos[n].setClaseDePrueba((boolean) tab.getValueAt(n,14));
					ninos[n].setHorasDeClase((String) tab.getValueAt(n, 15));
					
					st=new StringTokenizer(tab.getValueAt(n, 16).toString(),",");
					boolean[] dias={false,false,false,false,false,false};
					//Lee los datos introducidos en cada fila dentro de la columna de Días a asistir y lo convierte a un arreglo de booleanos el cual se asignara como el nuevo valor de el objeto en la posición 'n' del arreglo de niños
					int ndia=0;
					
					while(st.hasMoreTokens()){
						
						String dia=st.nextToken();
						System.out.println("Pos="+ndia+" y "+dia);
						if((dia.equals("Lunes")||dia.equals("lunes")||dia.equals("Lu")||dia.equals("LUNES")||dia.equals("LU")||dia.equals("lu")||dia.equals("Lu"))){
							dias[0]=true;
						}
						if((dia.equals("Martes")||dia.equals("martes")||dia.equals("ma")||dia.equals("MARTES")||dia.equals("MA")||dia.equals("Ma"))){
							dias[1]=true;
						}
						if((dia.equals("Miercoles")||dia.equals("miercoles")||dia.equals("mi")||dia.equals("MIERCOLES")||dia.equals("MI")||dia.equals("Mi"))){
							dias[2]=true;
						}
						
						if((dia.equals("Jueves")||dia.equals("jueves")||dia.equals("Ju")||dia.equals("JUEVES")||dia.equals("JU")||dia.equals("ju"))){
							dias[3]=true;
						}
						
						if((dia.equals("Viernes")||dia.equals("viernes")||dia.equals("Vi")||dia.equals("VIERNES")||dia.equals("VI")||dia.equals("vi"))){
							dias[4]=true;
						}
						if((dia.equals("Sábado")||dia.equals("sábado")||dia.equals("Sa")||dia.equals("SÁBADO")||dia.equals("SA")||dia.equals("sa"))){
							dias[5]=true;
						}
						ndia++;
						
						
					}
					
					
					ninos[n].setDiasSesion(dias);
					
					ninos[n].setTipoDeSesion((boolean)(tab.getValueAt(n, 17)));
					
					
					//Alergias y Medicamentos, despues Tipo de sang, clase de prueba, hora, dias a asistir, tipo de clase, inscrito, descuento
					
					ninos[n].setInscrito((boolean) tab.getValueAt(n, 18));
					
					
					
					
					ninos[n].setDescuentos((double) tab.getValueAt(n, 19));
					
				
			}
			}
			try {
				writeFile(ninos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			con.setVisible(false);
			}else{
				error(mens);	
				}
			}
			
		}});
		display.add(gcerr);
		lim.gridy=0;
		lim.weightx=10;
		lim.weighty=8;
		lim.fill=lim.BOTH;
		con.getContentPane().add(scroll,lim);
		lim.weightx=0;
		lim.weighty=0;
		lim.gridy=1;
		con.getContentPane().add(display,lim);
		con.setVisible(true);
    }
    
    //Metodo obtenido en la página : https://stackoverflow.com/questions/17627431/auto-resizing-the-jtable-column-widths
    public static void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width +70 , width);
            }
            if(width > 600)
                width=600;
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

}
