import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Ventana_Talleristas extends JFrame {
	private JButton crear=new JButton("Registrar Tallerista");
	private JButton consultar=new JButton("Consultar datos de Tallerista");
	private JButton editar=new JButton("Editar Información de Tallerista");
	private JButton borrar=new JButton("Dar de baja talleristas");
	private JButton tabla=new JButton("Ver y editar en tabla");
	public static Talleristas[] taller=new Talleristas[1000];
	int pos=0;
	static int posision=0;
	static String NumInvalid="ABCDEFGHIJKMLNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÑñ*/+[](){}_,<>!#$%&=?¡¿?'¨";
	static String StriInvalid="0123456789";
	
	JLabel Nombre;
	JLabel Telefono;
	JLabel Capa;
	JLabel Frec;
	JLabel frecact;
	JLabel salar;
	JLabel ID;
	
	public Ventana_Talleristas()throws IOException{
		taller=generarArr(readFile());
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		setSize((size.width)/2,size.height/2);
		setTitle("Menú Talleristas");
		setLocation(size.width/3,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		
		crear.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			ventanaCrearTall();
		}});
		
		lim.gridx=0;
		lim.gridy=0;
		display.add(crear,lim);
		
		consultar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
			//buscarId();
				consultarTaller();
				//conTab();
				}
			});
		
		borrar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			buscarIdBorrar();
			
		}});
		
		editar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			buscarId();
			
		}});
		
		tabla.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			conTab();
			
		}});
		
		lim.gridx=0;
		lim.gridy=1;
		display.add(consultar,lim);
		
		lim.gridx=1;
		lim.gridy=1;
		display.add(tabla,lim);
		
		lim.gridx=1;
		lim.gridy=0;
		display.add(editar,lim);
		
		lim.gridx=2;
		lim.gridy=0;
		display.add(borrar,lim);
		
	}
	
	public void ventanaCrearTall(){
		
		JFrame ventana=new JFrame();
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width),size.height);
		ventana.setTitle("Alta de Tallerista");
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		
		lim.gridx=0;
		JLabel NombreClb=new JLabel("Nombre Completo del Tallerista");
		lim.gridy=0;
		display.add(NombreClb,lim);
		JLabel Capacitacionlb=new JLabel("¿Qué material maneja el tallerista?");
		lim.gridy=1;
		display.add(Capacitacionlb,lim);
		JLabel NumeroTellb=new JLabel("Número Telefónico");
		lim.gridy=3;
		display.add(NumeroTellb,lim);
		JLabel salarLb=new JLabel("Salario a Pagar por frecuencia: ");
		lim.gridy=4;
		display.add(salarLb,lim);
		
		lim.ipadx=200;
		lim.gridx=1;
		JTextField NombreC=new JTextField();
		lim.gridy=0;
		display.add(NombreC,lim);
		lim.gridy=1;
		lim.ipadx=0;
		Checkbox EV3=new Checkbox("EV3");
		lim.gridx=1;
		display.add(EV3,lim);
		Checkbox WeDo=new Checkbox("WeDo");
		lim.gridx=2;
		display.add(WeDo,lim);
		Checkbox WeDo2=new Checkbox("WeDo 2");
		lim.gridx=3;
		display.add(WeDo2,lim);
		Checkbox Preescolar=new Checkbox("Preescolar");
		lim.gridx=4;
		display.add(Preescolar,lim);
		Checkbox Autismo=new Checkbox("Autismo");
		lim.gridx=5;
		display.add(Autismo,lim);
		JTextField NumeroTel=new JTextField();
		lim.ipadx=200;
		lim.gridx=1;
		lim.gridy=3;
		display.add(NumeroTel,lim);
		JTextField salar=new JTextField();
		lim.ipadx=200;
		lim.gridx=1;
		lim.gridy=4;
		display.add(salar,lim);
		
		JButton Confirmar=new JButton("Confirmar");
		Confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			boolean hacer=true;
			String [] mens=new String[3];
			char[] arrEv=NombreC.getText().toCharArray();
			char [] numInv=StriInvalid.toCharArray();
			
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<numInv.length;n++){
					if(arrEv[i]==numInv[n]){
						hacer=false;
						mens[0]="Valores inválidos en el nombre";
					}
				}
			}
			
			char[] letrInv=NumInvalid.toCharArray();
			arrEv=NumeroTel.getText().toCharArray();
			
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<letrInv.length;n++){
					if(arrEv[i]==letrInv[n]){
						hacer=false;
						mens[1]="Valores inválidos en el teléfono";
					}
				}
			}
			arrEv=salar.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<letrInv.length;n++){
					if(arrEv[i]==letrInv[n]){
						hacer=false;
						mens[2]="Valores inválidos en el salario";
					}
				}
			}
			
			if(hacer){
			
			boolean[] arrCap=new boolean[5];
			arrCap[0]=EV3.getState();
			arrCap[1]=WeDo.getState();
			arrCap[2]=WeDo2.getState();
			arrCap[3]=Preescolar.getState();
			arrCap[4]=Autismo.getState();
			int contar=0;
			for(int i=0;i<taller.length;i++){
				if(taller[i].getIDTallerista().equals("")){
					String ID=""+ NombreC.getText().charAt(0)+""+NombreC.getText().charAt(1)+""+(int)((Math.random())*1000);
					ID=ID.toUpperCase();
					taller[i]=new Talleristas(NombreC.getText(),arrCap,(NumeroTel.getText()),ID,Double.parseDouble(salar.getText()));
					taller[i].print();
					i+=taller.length;
				}else{
					contar++;
				}
				
			}
			if(contar==taller.length){
				String ID=""+ NombreC.getText().charAt(0)+""+NombreC.getText().charAt(1)+""+(int)((Math.random())*1000);
				ID=ID.toUpperCase();
				Talleristas paso=new Talleristas(NombreC.getText(),arrCap,(NumeroTel.getText()),ID,Double.parseDouble(salar.getText()));
				taller=aumentarArr(taller,paso);
				
			}
			
			try {
				writeFile(taller);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ventana.setVisible(false);
			}else{
				error(mens);
			}
			
		}});
		lim.gridx=1;
		lim.gridy=5;
		display.add(Confirmar,lim);
		ventana.setVisible(true);
		
	}
	
	public void consultarTaller(){
		JFrame ventana=new JFrame();
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width)/2,size.height);
		ventana.setTitle("Consultar datos de Tallerista");
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		
		if(taller[pos].getIDTallerista().equals(""))
		for(int i=0;i<taller.length;i++){
			if(!taller[i].getIDTallerista().equals("")){
				pos=i;
			}
		}
		
		if(!taller[pos].getIDTallerista().equals("")){
		lim.gridx=1;
			Nombre=new JLabel("Nombre: "+taller[pos].getNombreCompleto());
		lim.gridy=0;
		display.add(Nombre,lim);
		Telefono=new JLabel("Teléfono: "+taller[pos].getNumeroTelefonico());
		lim.gridy=1;
		display.add(Telefono,lim);
		String cap="";
		
		for(int i=0;i<taller[pos].getCapacitacionParaEnsenar().length;i++){
			switch(i){
			case 0:{
				if(taller[pos].getCapacitacionParaEnsenar(i)){
				cap+="Ev3, ";}
				break;
			}
			case 1:{
				if(taller[pos].getCapacitacionParaEnsenar(i)){
				cap+="WeDo, ";}
				break;
			}
			case 2:{
				if(taller[pos].getCapacitacionParaEnsenar(i)){
				cap+="WeDo 2, ";}
				break;
			}
			case 3:{
				if(taller[pos].getCapacitacionParaEnsenar(i)){
				cap+="Preescolar, ";}
				break;
			}
			case 4:{
				if(taller[pos].getCapacitacionParaEnsenar(i)){
				cap+="Autismo, ";}
				break;
			}
			}

			
		}
		Capa=new JLabel("Tiene capacitación para: "+cap);
		lim.gridy=2;
		display.add(Capa,lim);
		int o=0;
		for(int i=0;i<taller[pos].getFrecuenciasTrabajadas().length;i++){
			o+=taller[pos].getFrecuenciasTrabajadas(i);
		}
		
		Frec=new JLabel("Frecuencias Trabajadas en total: "+o);
		lim.gridy=3;
		display.add(Frec,lim);
		frecact=new JLabel("Frecuencias Trabajadas en el calendario actual: "+taller[pos].getFrecuenciasTrabajadas(taller[pos].getFrecuenciasTrabajadas().length-1));
		lim.gridy=4;
		display.add(frecact,lim);
		
		salar=new JLabel("Salario con el calendario actual: "+ (taller[pos].getFrecuenciasTrabajadas(taller[pos].getFrecuenciasTrabajadas().length-1)*taller[pos].getSalario()));
		lim.gridy=5;
		display.add(salar,lim);
		
		ID=new JLabel("ID: "+taller[pos].getIDTallerista());
		lim.gridy=6;
		display.add(ID,lim);
		}
		lim.gridy=7; 
		
		JButton der=new JButton("-->");
		der.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
				pos++;
				
			
			if(pos>taller.length-1){
				pos=0;
				
			}
			if(!taller[pos].getIDTallerista().equals("")){
			Nombre.setText("Nombre: "+taller[pos].getNombreCompleto());
			Telefono.setText("Teléfono: "+taller[pos].getNumeroTelefonico());
			String cap="";
			for(int i=0;i<taller[pos].getCapacitacionParaEnsenar().length;i++){
				switch(i){
				case 0:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="Ev3, ";}
					break;
				}
				case 1:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="WeDo, ";}
					break;
				}
				case 2:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="WeDo 2, ";}
					break;
				}
				case 3:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="Preescolar, ";}
					break;
				}
				case 4:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="Autismo, ";}
					break;
				}
				}
			}
			Capa.setText("Tiene capacitación para: "+cap);
			int o=0;
			for(int i=0;i<taller[pos].getFrecuenciasTrabajadas().length;i++){
				o+=taller[pos].getFrecuenciasTrabajadas(i);
			}
			
			Frec.setText("Frecuencias Trabajadas en total: "+o);

			frecact.setText("Frecuencias Trabajadas en el calendario actual: "+taller[pos].getFrecuenciasTrabajadas(taller[pos].getFrecuenciasTrabajadas().length-1));
			salar.setText(("Salario con el calendario actual: "+ (taller[pos].getFrecuenciasTrabajadas(taller[pos].getFrecuenciasTrabajadas().length-1)*taller[pos].getSalario())));
			ID.setText("ID: "+taller[pos].getIDTallerista());
			
			}}});
		lim.gridx=2;
		display.add(der,lim);
		
		JButton izq=new JButton("<--");
		izq.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			
				pos--;
			if(pos<0){
				pos=taller.length-1;
			}
			if(!taller[pos].getIDTallerista().equals("")){
			Nombre.setText("Nombre: "+taller[pos].getNombreCompleto());
			Telefono.setText("Teléfono: "+taller[pos].getNumeroTelefonico());
			String cap="";
			for(int i=0;i<taller[pos].getCapacitacionParaEnsenar().length;i++){
				switch(i){
				case 0:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="Ev3, ";}
					break;
				}
				case 1:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="WeDo, ";}
					break;
				}
				case 2:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="WeDo 2, ";}
					break;
				}
				case 3:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="Preescolar, ";}
					break;
				}
				case 4:{
					if(taller[pos].getCapacitacionParaEnsenar(i)){
					cap+="Autismo, ";}
					break;
				}
				}
			}
			Capa.setText("Tiene capacitación para: "+cap);
			int o=0;
			for(int i=0;i<taller[pos].getFrecuenciasTrabajadas().length;i++){
				o+=taller[pos].getFrecuenciasTrabajadas(i);
			}
			
			Frec.setText("Frecuencias Trabajadas en total: "+o);
			frecact.setText("Frecuencias Trabajadas en el calendario actual: "+taller[pos].getFrecuenciasTrabajadas(taller[pos].getFrecuenciasTrabajadas().length-1));
			salar.setText(("Salario con el calendario actual: "+ (taller[pos].getFrecuenciasTrabajadas(taller[pos].getFrecuenciasTrabajadas().length-1)*taller[pos].getSalario())));
			
			ID.setText("ID: "+taller[pos].getIDTallerista());
			
			
		}}});
		lim.gridx=0;
		display.add(izq, lim);
		ventana.setVisible(true);
		
		

	}
	
	public static void writeFile(Talleristas[] arr) throws IOException{
		PrintWriter fileOut;
		fileOut=new PrintWriter(new FileWriter("Tallerista.txt"));
		for (int i=0; i<arr.length;i++)
		{
			Talleristas taller=arr[i];
			//Procedimiento para convertir arr a Strings 
			
			if(!taller.getIDTallerista().equals("")){
				String Cap="";
				String frec="";
				for(int z=0;z<taller.getCapacitacionParaEnsenar().length;z++){
					if(z==0){
						Cap+=taller.getCapacitacionParaEnsenar()[z];
					}else{
						Cap+="/"+taller.getCapacitacionParaEnsenar()[z];
				}
				}
					
				
				
				for(int z=0;z<taller.getFrecuenciasTrabajadas().length;z++){
					if(z==0){
						frec+=taller.getFrecuenciasTrabajadas()[z];
					}else{
						frec+="/"+taller.getFrecuenciasTrabajadas()[z];
					}
					
				}
				
				fileOut.println(""+taller.getNombreCompleto()+","+Cap+","+frec+","+taller.getNumeroTelefonico()+","+taller.getIDTallerista()+","+taller.getSalario());
				}
			}	
			
		
		fileOut.close();
	}
	
	public String[] readFile()throws IOException{
		String[] arr = new String[1];
		System.out.println("Llego al file read de talleristas");
		File read = new File("Tallerista.txt");
		//lee archivo Padres
			System.out.println("condición a: "+(read.exists()) +" b: "+ !read.isDirectory());	
			if(read.exists() && !read.isDirectory()) { 
					BufferedReader leerDir=new BufferedReader(new FileReader("Tallerista.txt"));
					int cont =0;
					int size=0;
					while(leerDir.ready()){
						size++;
						leerDir.readLine();
						
					}
					leerDir.close();
				 leerDir=new BufferedReader(new FileReader("Tallerista.txt")); 
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
	
	public Talleristas[] generarArr(String [] arr){
		Talleristas[] arrT=new Talleristas[arr.length];
		for(int i=0;i<arr.length;i++){
			StringTokenizer st=new StringTokenizer(arr[i],",");
			int cont=0;
			String Nombre="";
			boolean[] Cap=null;
			int []frec=null;
			String tel="";
			String id="";
			double sal=0;
		
			while(st.hasMoreTokens()){
				System.out.println(cont);
				switch(cont){
				case 0:{
					Nombre=st.nextToken();
					System.out.println(Nombre);
					break;
				}
				case 1:{
					StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
					Cap=new boolean[st2.countTokens()];
					int con=0;
					while(st2.hasMoreTokens()){
						Cap[con]=st2.nextToken().equals("true");
						con++;
					}
					
					break;
				}
				case 2:{
					StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
					frec=new int[st2.countTokens()];
					int con=0;
					while(st2.hasMoreTokens()){
						frec[con]=Integer.parseInt(st2.nextToken());
						con++;
						}
					
					break;
				}
				case 3:{
					tel=st.nextToken();
					break;
				}
				case 4:{
					id=st.nextToken();
					System.out.println(id);
					break;
				}
				case 5:{
					sal=Double.parseDouble(st.nextToken());
					break;
				}
				
				}
			cont++;
			}
			arrT[i]=new Talleristas(Nombre,Cap,frec,tel,id,sal);
			arrT[i].print();
		}
		
		return arrT;
	}
	
	public Talleristas[] aumentarArr(Talleristas[] arr,Talleristas newT){
		Talleristas []paso=arr;
		arr=new Talleristas[paso.length+1];
		for(int i=0;i<paso.length;i++){
			arr[i]=paso[i];
		}
		arr[arr.length-1]=newT;
		return arr;
		
	}
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/
	
	public static void editarT(int x){
		JFrame ventana=new JFrame();
		Toolkit tools= ventana.getToolkit();
		Dimension size= tools.getScreenSize();
		ventana.setSize((size.width),size.height);
		ventana.setTitle("Editar de Tallerista");
		ventana.setLocation(0,0);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		ventana.getContentPane().add(display);
		
		lim.gridx=0;
		JLabel NombreClb=new JLabel("Nombre Completo del Tallerista");
		lim.gridy=0;
		display.add(NombreClb,lim);
		JLabel Capacitacionlb=new JLabel("¿Qué material Maneja el tallerista?");
		lim.gridy=1;
		display.add(Capacitacionlb,lim);
		JLabel NumeroTellb=new JLabel("Número Telefónico");
		lim.gridy=3;
		display.add(NumeroTellb,lim);
		JLabel salarLb=new JLabel("Salario a Pagar: ");
		lim.gridy=4;
		display.add(salarLb,lim);
		
		lim.ipadx=200;
		lim.gridx=1;
		JTextField NombreC=new JTextField(taller[x].getNombreCompleto());
		lim.gridy=0;
		display.add(NombreC,lim);
		lim.gridy=1;
		lim.ipadx=0;
		Checkbox EV3=new Checkbox("EV3");
		EV3.setState(taller[x].getCapacitacionParaEnsenar(0));
		lim.gridx=1;
		display.add(EV3,lim);
		Checkbox WeDo=new Checkbox("WeDo");
		WeDo.setState(taller[x].getCapacitacionParaEnsenar(1));
		lim.gridx=2;
		display.add(WeDo,lim);
		Checkbox WeDo2=new Checkbox("WeDo 2");
		WeDo2.setState(taller[x].getCapacitacionParaEnsenar(2));
		lim.gridx=3;
		display.add(WeDo2,lim);
		Checkbox Preescolar=new Checkbox("Preescolar");
		Preescolar.setState(taller[x].getCapacitacionParaEnsenar(3));
		lim.gridx=4;
		display.add(Preescolar,lim);
		Checkbox Autismo=new Checkbox("Autismo");
		Autismo.setState(taller[x].getCapacitacionParaEnsenar(0));
		lim.gridx=5;
		display.add(Autismo,lim);
		JTextField NumeroTel=new JTextField(taller[x].getNumeroTelefonico());
		lim.ipadx=200;
		lim.gridx=1;
		lim.gridy=3;
		display.add(NumeroTel,lim);
		
		JTextField salar=new JTextField(""+taller[x].getSalario());
		lim.ipadx=200;
		lim.gridx=1;
		lim.gridy=4;
		display.add(salar,lim);
		
		
		JButton Confirmar=new JButton("Confirmar");
		Confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			boolean hacer=true;
			String mens[]=new String[3];
			char[] arrEv=NombreC.getText().toCharArray();
			char [] numInv=StriInvalid.toCharArray();
			
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<numInv.length;n++){
					if(arrEv[i]==numInv[n]){
						hacer=false;
						mens[0]="Valores inválidos en el nombre";
					}
				}
			}
			
			char[] letrInv=NumInvalid.toCharArray();
			arrEv=NumeroTel.getText().toCharArray();
			
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<letrInv.length;n++){
					if(arrEv[i]==letrInv[n]){
						hacer=false;
						mens[1]="Valores inválidos en el teléfono";
					}
				}
			}
			arrEv=salar.getText().toCharArray();
			for(int i=0;i<arrEv.length;i++){
				for(int n=0;n<letrInv.length;n++){
					if(arrEv[i]==letrInv[n]){
						hacer=false;
						mens[2]="Valores inválidos en el salario";
					}
				}
			}
			
			if(hacer){
			
			boolean[] arrCap=new boolean[5];
			arrCap[0]=EV3.getState();
			arrCap[1]=WeDo.getState();
			arrCap[2]=WeDo2.getState();
			arrCap[3]=Preescolar.getState();
			arrCap[4]=Autismo.getState();
			
				String ID=taller[x].getIDTallerista();
				taller[x]=new Talleristas(NombreC.getText(),arrCap,(NumeroTel.getText()),ID,Double.parseDouble(salar.getText()));
				
				
			
			
			try {
				writeFile(taller);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ventana.setVisible(false);
			}else{
				error(mens);
			}
		}});
		lim.gridx=1;
		lim.gridy=5;
		display.add(Confirmar,lim);
		ventana.setVisible(true);
		
	}
	
	//Recive un arreglo de Talleristas
	public static void buscarIdBorrar(){
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
		posision=0;
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
			for(int i=0;i<taller.length;i++){
				if(taller[i].getIDTallerista().equals(introId.getText())){
					posision=i;
					taller[i]=new Talleristas();
					i+=taller.length;
					Listo();
					
				}
			}
			try {
				writeFile(taller);
				//Listo();
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
	
	public static void buscarId(){
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
		posision=0;
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
			for(int i=0;i<taller.length;i++){
				if(taller[i].getIDTallerista().equals(introId.getText())){
					posision=i;
					i+=taller.length;
					editarT(posision);
				}
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
	
	public void conTab(){
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
		String head[]={"ID","Nombre","Teléfono","Capacitación","Total de Frecuencias trabajadas","Frecuencias actuales","Salario por frecuencia","Salario Actual"};
		Object content[][]=new Object[taller.length][8];
		for(int n=0;n<taller.length;n++){
			if(!taller[n].getIDTallerista().equals("")){
			content[n][0]=taller[n].getIDTallerista();
			content[n][1]=taller[n].getNombreCompleto();
			content[n][2]=taller[n].getNumeroTelefonico();
			String line="";
			
			for(int z=0;z<taller[n].getCapacitacionParaEnsenar().length;z++){
				switch(z){
				case 0:{
					if(taller[n].getCapacitacionParaEnsenar(z)){
					line+="Ev3,";}
					break;
				}
				case 1:{
					if(taller[n].getCapacitacionParaEnsenar(z)){
					line+="WeDo,";}
					break;
				}
				case 2:{
					if(taller[n].getCapacitacionParaEnsenar(z)){
					line+="WeDo 2,";}
					break;
				}
				case 3:{
					if(taller[n].getCapacitacionParaEnsenar(z)){
					line+="Preescolar,";}
					break;
				}
				case 4:{
					if(taller[n].getCapacitacionParaEnsenar(z)){
					line+="Autismo,";}
					break;
				}
				}
				if(z==taller[n].getCapacitacionParaEnsenar().length){
					line=line.substring(0, line.length()-2);
				}
					
			}
			
			content[n][3]=line;
			int o=0;
			for(int i=0;i<taller[pos].getFrecuenciasTrabajadas().length;i++){
				o+=taller[pos].getFrecuenciasTrabajadas(i);
			}
			content[n][4]=o;
			content[n][5]=taller[n].getFrecuenciasTrabajadas(taller[n].getFrecuenciasTrabajadas().length-1);
			content[n][6]=taller[n].getSalario();
			content[n][7]=taller[n].getSalario()*taller[n].getFrecuenciasTrabajadas(taller[n].getFrecuenciasTrabajadas().length-1);;
			}
			}
		 tab=new JTable(content,head){
			 public boolean isCellEditable(int row, int col){
					switch(col){
					//case 3:
					case 4:
					case 7:
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
		JScrollPane scroll=new JScrollPane(tab);
		JButton gcerr=new JButton("Guardar y cerrar");
		gcerr.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				String[] mens=new String[3];
				boolean hacer=true;
				for(int n=0;n<taller.length;n++){
				if(!taller[n].getIDTallerista().equals("")){
				char[] arrEv=tab.getValueAt(n, 1).toString().toCharArray();
				char [] numInv=StriInvalid.toCharArray();
				
				for(int i=0;i<arrEv.length;i++){
					for(int x=0;x<numInv.length;x++){
						if(arrEv[i]==numInv[x]){
							hacer=false;
							mens[0]="Valores inválidos en el nombre";
						}
					}
				}
				
				char[] letrInv=NumInvalid.toCharArray();
				arrEv=tab.getValueAt(n, 2).toString().toCharArray();
				
				for(int i=0;i<arrEv.length;i++){
					for(int x=0;x<letrInv.length;x++){
						if(arrEv[i]==letrInv[x]){
							hacer=false;
							mens[1]="Valores inválidos en el teléfono";
						}
					}
				}
				arrEv=tab.getValueAt(n, 6).toString().toCharArray();
				for(int i=0;i<arrEv.length;i++){
					for(int x=0;x<letrInv.length;x++){
						if(arrEv[i]==letrInv[x]){
							hacer=false;
							mens[2]="Valores inválidos en el salario";
						}
					}
				}
			}
				}
				if(hacer){
					StringTokenizer st;
					for(int n=0;n<taller.length;n++){
						if(!taller[n].getIDTallerista().equals("")){
					taller[n].setNombreCompleto(tab.getValueAt(n,1).toString());
					taller[n].setNumeroTelefonico(tab.getValueAt(n, 2).toString());
					String line=(tab.getValueAt(n,3).toString());
					st=new StringTokenizer(line,",");
					boolean arr[]=new boolean[5];
					//int z=0;
					while(st.hasMoreTokens()){
						String a=st.nextToken();
						//a=a.replace(' ',(char) 0);
						if(a.equals("EV3")||a.equals("ev3")||a.equals("Ev3")||a.equals("eV3")){
						arr[0]=true;
						}
						if(a.equals("WeDo")||a.equals("wedo")||a.equals("WEDO")||a.equals("Wedo")||a.equals("We Do")||a.equals("we do")||a.equals("WE DO")||a.equals("We do")){
							arr[1]=true;
						}
						if(a.equals("WeDo2")||a.equals("wedo2")||a.equals("Wedo2")||a.equals("WEDO2")||a.equals("WeDo 2")||a.equals("wedo 2")||a.equals("Wedo 2")||a.equals("WEDO 2")||a.equals("We Do2")||a.equals("we do2")||a.equals("We do2")||a.equals("WE DO2")||a.equals("We Do 2")||a.equals("we do 2")||a.equals("We do 2")||a.equals("WE DO 2")){
							arr[2]=true;
							}
						if(a.equals("Preescolar")||a.equals("preescolar")||a.equals("Pre escolar")||a.equals("pre escolar")||a.equals("pre Escolar")){
							arr[3]=true;
							}
						if(a.equals("Autismo")||a.equals("autismo")||a.equals("AUTISMO")||a.equals("Autismo")){
							arr[4]=true;
							}
					}
					
					taller[n].setCapacitacionParaEnsenar(arr);
					taller[n].setFrecuenciasTrabajadas(Integer.parseInt(tab.getValueAt(n, 5).toString()), taller[n].getFrecuenciasTrabajadas().length-1);
					taller[n].setSalario(Double.parseDouble(tab.getValueAt(n, 6).toString()));
					}
					}
					con.setVisible(false);
					try {
						writeFile(taller);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					error(mens);
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
	
	public static void error(String list[]){
		JFrame err=new JFrame();
		Toolkit tools= err.getToolkit();
		Dimension size= tools.getScreenSize();
		err.setSize(300,300);
		err.setLocation(size.width/3,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		//display.setLayout(new boxLayout());
		err.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		JLabel error=new JLabel("Error: Algun Valor Introducido es Incorrecto");
		JList listerr=new JList(list);
		display.add(error);
		display.add(listerr);
		err.setVisible(true);
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
		list.setVisible(true);
	}


}
