import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.io.*;
import java.util.StringTokenizer;
public class verDatos extends JFrame{
	JButton crearB=new JButton("Agregar Costo");
	JButton verB=new JButton("Ver Costos");
	JButton eliminarB=new JButton("Borrar Costo");
	JLabel TBal=new JLabel("Balance Total:");
	JLabel Bal=new JLabel();
	Balance []gastos=new Balance[1];
	double ingresos[];
	static String NumInvalid="ABCDEFGHIJKMLNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÑñ*-/+[](){}_,<>!#$%&=?¡¿?'¨";
	
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			verDatos x=new verDatos();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	public verDatos() throws IOException{
		String[] paso=readFile();
		if(paso[0].equals("-_-")){
			gastos=new Balance[1];
			gastos[0]=new Balance();
		}else{
			gastos=generarArrB(paso);
		}
		
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		setSize((size.width/2),size.height/2);
		setTitle("Ver Datos");
		setLocation(size.width/4,size.height/8);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		getContentPane().add(display);
		lim.insets=new Insets(10,10,10,10);
		lim.gridx=0;
		lim.gridy=0;
		display.add(TBal, lim);
		Bal.setText(""+calcularBal());
		lim.gridx=0;
		lim.gridy=1;
		display.add(Bal, lim);
		crearB.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			JFrame vent=new JFrame();
			Toolkit tools= vent.getToolkit();
			Dimension size= tools.getScreenSize();
			vent.setSize((size.width)/3,size.height/3);
			vent.setTitle("Crear costos");
			vent.setLocation(size.width/4,size.height/8);
			GridBagConstraints lim=new GridBagConstraints();
			JPanel display= new JPanel();
			display.setLayout(new GridBagLayout());
			vent.getContentPane().add(display);
			lim.insets=new Insets(10,10,10,10);
			JLabel NombreLb=new JLabel("Nombre del costo:");
			lim.gridx=0;
			display.add(NombreLb, lim);
			JLabel DescLb=new JLabel("Escriba la descripción del costo:");
			lim.gridy=1;
			display.add(DescLb, lim);
			JLabel ValLb=new JLabel("Escriba su valor: ");
			lim.gridy=2;
			display.add(ValLb, lim);
			lim.weightx=3;
			lim.gridx=1;
			lim.ipadx=100;
			JTextField Nombre=new JTextField();
			lim.gridy=0;
			display.add(Nombre, lim);
			JTextArea Desc=new JTextArea();
			Desc.setSize(30, 20);
			
			lim.gridy=1;
			lim.ipadx=50;
		
			display.add(Desc, lim);
			JTextField Val=new JTextField();
			lim.gridy=2;
			lim.ipadx=100;
			display.add(Val, lim);
			
			
			JButton conf=new JButton("Confirmar");
			conf.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event){
					boolean hacer=true;
					char[] arrEv=Val.getText().toCharArray();
					char [] letrInv=NumInvalid.toCharArray();
					
					for(int i=0;i<arrEv.length;i++){
						for(int n=0;n<letrInv.length;n++){
							if(arrEv[i]==letrInv[n]){
								hacer=false;
							}
						}
					}
					
					if(hacer){
					int o=0;
					for(int i=0;i<gastos.length;i++){
						if(gastos[i].getValorP()==0){
							gastos[i]=new Balance(Double.parseDouble(Val.getText()),Desc.getText(),Nombre.getText());
						}else{
							o++;
						}
					}
					if(o==gastos.length){
						Balance[] paso=gastos;
						gastos=new Balance[paso.length+1];
						for(int i=0;i<paso.length;i++){
							gastos[i]=paso[i];
						}
						gastos[gastos.length-1]=new Balance(Double.parseDouble(Val.getText()),Nombre.getText(),Desc.getText());
					}
					try {
						writeFile(gastos);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Bal.setText(""+calcularBal());
					
					vent.setVisible(false);
					}else{
						error();
					}
				}
			});
			lim.gridy=3;
			display.add(conf, lim);
			
			vent.setVisible(true);
		}});
		lim.gridx=1;
		lim.gridy=0;
		display.add(crearB,lim);
		
		verB.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			JFrame vent=new JFrame();
			Toolkit tools= vent.getToolkit();
			Dimension size= tools.getScreenSize();
			vent.setSize((size.width),size.height/3);
			vent.setTitle("Ver gastos");
			vent.setLocation(0,size.height/3);
			GridBagConstraints lim=new GridBagConstraints();
			JPanel display= new JPanel();
			display.setLayout(new GridBagLayout());
			vent.getContentPane().setLayout(new GridBagLayout());;
			lim.insets=new Insets(10,10,10,10);
			
			String[] headers={"Nombre","Descripción","Valor"};
			
			String[][] filas=new String[gastos.length+1][3];
			int rej=0; //Varaible para reacomodar los espacios vacíos
			for(int i=0;i<gastos.length;i++){
				System.out.println(gastos[i].getNombreC());
				if(!gastos[i].getNombreC().equals("")){
				System.out.println("If gastos tabla val real");
				filas[rej][0]=gastos[i].getNombreC();
				filas[rej][1]=gastos[i].getDescripcion();
				filas[rej][2]=""+gastos[i].getValorP();
				rej++;
				}else{
					filas[i][0]="";
					filas[i][1]="";
					filas[i][2]="";					
				}
			}
			int negat=0;
			
			Talleristas tallP[]=null;
			
			try {
				//calP=generarPadresArr(readFilePadres());
				tallP=generarArr(readFileTall());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			for(int i=0;i<tallP.length;i++){
				negat+=tallP[i].getSalario()*tallP[i].getFrecuenciasTrabajadas(tallP[i].getFrecuenciasTrabajadas().length-1);
				
			}
			
			filas[filas.length-1][0]="Salario Talleristas";
			filas[filas.length-1][1]="Lo que se debe de pagar a los talleristas por sus horas trabajadas";
			filas[filas.length-1][2]=""+negat;
			JTable tab=new JTable(filas,headers);
			
			tab.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor( new JTextField()));
			JScrollPane scroll=new JScrollPane(tab);
			vent.add(scroll);
			JButton gcerr=new JButton("Guardar y cerrar");
			gcerr.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
				boolean validar= true;
				char val[];
				char[] numIn=NumInvalid.toCharArray();
				for(int n=0;n<gastos.length;n++){
					val=tab.getValueAt(n, 2).toString().toCharArray();
					for(int i=0;i<val.length;i++){
						for(int y=0;y<numIn.length;y++){
							if(val[i]==numIn[y]){
								validar=false;
							}
						}
						
					}
					
				}
				if(validar){
					for(int n=0;n<gastos.length;n++){
						gastos[n].setNombreC(tab.getValueAt(n, 0).toString());
						gastos[n].setDescripcion(tab.getValueAt(n, 1).toString());
						gastos[n].setValorP(Double.parseDouble(tab.getValueAt(n, 2).toString()));
					}
					try {
						writeFile(gastos);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Bal.setText(""+calcularBal());
					vent.setVisible(false);
				}
				Bal.setText(""+calcularBal());
			}});
			resizeColumnWidth(tab);
			tab.setAutoResizeMode(tab.AUTO_RESIZE_OFF);
			
			display.add(gcerr);
			lim.gridy=0;
			lim.weightx=10;
			lim.weighty=8;
			lim.fill=lim.BOTH;
			vent.getContentPane().add(scroll,lim);
			lim.weightx=0;
			lim.weighty=0;
			lim.gridy=1;
			vent.getContentPane().add(display,lim);
			vent.setVisible(true);
			
		}});
		lim.gridy=1;
		display.add(verB,lim);
		
		eliminarB.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			JFrame vent=new JFrame();
			Toolkit tools= vent.getToolkit();
			Dimension size= tools.getScreenSize();
			vent.setSize((size.width)/3,size.height/3);
			vent.setTitle("Eliminar gastos");
			vent.setLocation(size.width/3,size.height/3);
			GridBagConstraints lim=new GridBagConstraints();
			JPanel display= new JPanel();
			lim.insets=new Insets(10,10,10,10);
			display.setLayout(new GridBagLayout());
			vent.getContentPane().add(display);
			JLabel NomLb=new JLabel("Nombre del gasto a eliminar");
			lim.gridx=0;
			display.add(NomLb,lim);
			JTextField Nom=new JTextField();
			Nom.setToolTipText("Si el nombre se repite se va a borrar el primero con ese nombre");
			lim.gridx=1;
			lim.weightx=3;
			lim.ipadx=100;
			display.add(Nom,lim);
			
			JButton conf=new JButton("Confirmar");
			conf.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
				System.out.println("----------------------------");
				for(int i=0;i<gastos.length;i++){
					System.out.println(gastos[i].getNombreC());
					System.out.println(Nom.getText());
					if(gastos[i].getNombreC().equals(Nom.getText())){
						System.out.println((gastos[i].getNombreC().equals(Nom.getText()))+"  "+(gastos[i].getNombreC()));
						gastos[i]=new Balance();
						i+=gastos.length;
					}
					
				}
				vent.setVisible(false);
				
				try {
					writeFile(gastos);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Bal.setText(""+calcularBal());
				
				
			}});
			lim.gridy=2;
			display.add(conf,lim);
			vent.setVisible(true);
		}});
		lim.gridy=2;
		display.add(eliminarB,lim);
		
		
		
		
		setVisible(true);
		
	}
	
	public double calcularBal(){
		double bal=0;
		double negat=0;
		Padres calP[]=null;
		Talleristas tallP[]=null;
		Ninos nin[]=null;
		
		try {
			calP=generarPadresArr(readFilePadres());
			tallP=generarArr(readFileTall());
			nin=generarArrNinos(readFileNinos());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*for(int i=0;i<calP.length;i++){
			bal+=calP[i].getMontoAPagar();
			
		}*/
		for(int i=0;i<nin.length;i++){
			if(nin[i].getInscrito()){
				for(int n=0;n<calP.length;n++){
					if(nin[i].getIDPadres().equals(calP[n].getIDPadres())){
						if(nin[i].getDescuentos()!=0){
						double descuento=(nin[i].getDescuentos()/100);
						System.out.println("==============>>>>>>>>> Desceunto: "+descuento);
						System.out.println("Val con descuento:"+(-calP[n].getMontoAPagar()*descuento));
						bal+=calP[n].getMontoAPagar()+(-calP[n].getMontoAPagar()*descuento);
						n+=calP.length;
						}else{
							bal+=calP[n].getMontoAPagar();
							System.out.println("Bal: "+bal);
							n+=calP.length;
						}
						
					}
					
				}
			}
			
		}
		
		for(int i=0;i<tallP.length;i++){
			negat+=tallP[i].getSalario()*tallP[i].getFrecuenciasTrabajadas(tallP[i].getFrecuenciasTrabajadas().length-1);
			
		}
		
		if(!gastos.equals(null)){
			for(int i=0;i<gastos.length;i++){
				
				negat+=gastos[i].getValorP();
			}
		}
		
		bal-=negat;
		
		return bal;
	}
	
	public String[] readFileTall()throws IOException{
		String[] arr = new String[1];
		System.out.println("Llego al file read de talleristas");
		File read = new File("Tallerista.txt");
		//lee archivo Padres
			System.out.println("condicioón a: "+(read.exists()) +" b: "+ !read.isDirectory());	
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
			double desc=0;
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
					 idNino=new String[st2.countTokens()];
					 while(st2.hasMoreTokens()){
					 
						 idNino[conta]=st2.nextToken();
						 System.out.println(idNino[conta]);
						 conta++;
					 }
						break;
					}
					case 2:{
					 NCP=st.nextToken();
					 System.out.println(NCP);
						break;
					}
					case 3:{
						 NCM=st.nextToken();
						 System.out.println(NCM);
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
						break;
					}
				
				}
			cont++;
			}
			arrP[i]=new Padres(idPadres, idNino, NCP, NCM, CM, CP,correoP,correoM, NE,telefonoE,TipoDePagos,Monto,fecha);
		}
		
		return arrP;
	}	
	
	public Balance[] generarArrB(String[] arr){
		Balance[] arrB=new Balance[arr.length];
		for(int i=0;i<arrB.length;i++){
			
			StringTokenizer st=new StringTokenizer(arr[i],",");
			
			int cont=0;
			double val=0;
			String n="";
			String des="";
			while(st.hasMoreTokens()){
			
		
			
			switch(cont){
			case 0:{
				val=Double.parseDouble(st.nextToken());
				System.out.println(val);
				break;
			}
			
			case 1:{
				n=st.nextToken();
				System.out.println(n);
				break;
			}
			
			case 2:{
				des=st.nextToken();
				System.out.println(des);
				break;
			}
			}
			System.out.println();
			cont++;
		
			}
			arrB[i]=new Balance(val,n,des);
			}
		
		return arrB;
	}
	
	public static void writeFile(Balance[] arr) throws IOException{
		PrintWriter fileOut;
		fileOut=new PrintWriter(new FileWriter("Balance.txt"));
		for (int i=0; i<arr.length;i++)
		{
			
			//Procedimiento para convertir arr a Strings 
			Balance x=arr[i];
		
				if(!x.getNombreC().equals("")){
				fileOut.println(x.getValorP()+","+x.getNombreC()+","+x.getDescripcion());
				}
				
			}	
			
		
		fileOut.close();
	}
	
	public String[] readFile()throws IOException{
		String[] arr = new String[1];
		System.out.println("Llego al file read de balance");
		File read = new File("Balance.txt");
		//lee archivo Balance
			System.out.println("condicioón a: "+(read.exists()) +" b: "+ !read.isDirectory());	
			if(read.exists() && !read.isDirectory()) { 
					BufferedReader leerDir=new BufferedReader(new FileReader("Balance.txt"));
					int cont =0;
					int size=0;
					while(leerDir.ready()){
						size++;
						leerDir.readLine();
						
					}
					leerDir.close();
					if(size==0){
						String[] x={"-_-"};
						return x;
					}
					
				 leerDir=new BufferedReader(new FileReader("Balance.txt")); 
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
	
	 public static void error(){
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
			JLabel error=new JLabel("Error: Algun Valor Introducido es Incorrecto");
			display.add(error);
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

	
}
