import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.swing.*;
public class Ventana_Calendario extends JFrame {
	Talleristas[] taller=new Talleristas[1];
	public static Ninos[] ninos=new Ninos[1];
	String [] tallerista=new String[1];
	//Si 4 a 
	JButton consultar;
	JButton crearNueCal;
	JLabel[] tallrLb=new JLabel[1];
	int cantTaller=0;
	String[] ids;
	
	public Ventana_Calendario()throws IOException{
		taller=generarArr(readFile());
		ninos=generarArrNinos(readFileNinos());
		
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		setSize((size.width)/2,size.height/2);
		setTitle("Menú Calendario");
		setLocation(size.width/3,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		consultar=new JButton ("Consultar Horario");
		consultar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			try {
				verCal();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}});
		
		display.add(consultar,lim);
		crearNueCal=new JButton("Crear un nuevo Calendario");
		crearNueCal.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			String[] newStr={""};
			try {
				newStr=readFileCal();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String[] paso=newStr;
			newStr=new String[paso.length+1];
			
			for(int i=0;i<paso.length;i++){
				newStr[i]=paso[i];
			}
			newStr[newStr.length-1]="-_-";
			
			try {
				writeFileCal(newStr);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			try {
				verCal();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}});
		
		lim.gridx=1;
		display.add(crearNueCal,lim);
		setVisible(true);
		
	}
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}*/
	
	public void verCal()throws IOException{
		JFrame vent=new JFrame();
		Toolkit tools= vent.getToolkit();
		Dimension size= tools.getScreenSize();
		vent.setSize((size.width),size.height/2);
		vent.setTitle("Menú Calendario");
		vent.setLocation(0,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		

		System.out.println("Antes de readFileCal");
		String []arrInfo=readFileCal();
		
		//Asegura que el tamaño de frecuecias trabajadas de los talleristas sea correcto
		if(!arrInfo.equals(null)){
			for( int i=0;i<taller.length;i++){
				int []paso=taller[i].getFrecuenciasTrabajadas();
				System.out.println("Length the paso [reset de frecTarabajadas]: "+paso.length);
				taller[i].setFrecuenciasTrabajadas(new int[arrInfo.length]);
				for (int z=0;z<paso.length;z++){
					taller[i].setFrecuenciasTrabajadas(paso[z],z);
				}
			}
			
		}
		
		
		
		lim.insets=new Insets(5,5,5,5);
		int cantIns=0;
		//Recolecta toda la información acerca de la hora y día de asistencia de los niños
		String[] headersDeTab={"Lunes","Martes","Miércoles","Jueves","Viernes","Sabado"};
		for(int i=0;i<ninos.length;i++){
			if(ninos[i].getInscrito()){
				cantIns++;
			}
		}
		System.out.println("Cantidad del Arreglo"+cantIns);
		Ninos[] ninosIns=new Ninos[cantIns];
		cantIns=0;
		for(int i=0;i<ninos.length;i++){
			if(ninos[i].getInscrito()){
				ninosIns[cantIns]=ninos[i];
				cantIns++;
			}
		}
		String [][] filas=new String[cantIns][6];
		System.out.println("Length Filas.length: "+filas.length);
		System.out.println("Length Filas[].length: "+filas[0].length);
		for(int i=0;i<filas.length;i++){
			for(int x=0;x<filas[i].length;x++){
				filas[i][x]="-";
				System.out.println(filas[i][x]);
			}
		}
		for(int i=0;i<ninosIns.length;i++){
			if(ninosIns[i].getdiasDeSesion()[0]){
				filas[i][0]=ninosIns[i].getNombre();
				System.out.println(filas[i][0]);
				
			}
			if(ninosIns[i].getdiasDeSesion()[1]){
				filas[i][1]=ninosIns[i].getNombre();
				System.out.println(filas[i][1]);
			}
			if(ninosIns[i].getdiasDeSesion()[2]){
				filas[i][2]=ninosIns[i].getNombre();
				System.out.println(filas[i][2]);
			}
			if(ninosIns[i].getdiasDeSesion()[3]){
				filas[i][3]=ninosIns[i].getNombre();
				System.out.println(filas[i][3]);
			}
			if(ninosIns[i].getdiasDeSesion()[4]){
				filas[i][4]=ninosIns[i].getNombre();
				System.out.println(filas[i][4]);
			}
			if(ninosIns[i].getdiasDeSesion()[5]){
				filas[i][5]=ninosIns[i].getNombre();
				System.out.println(filas[i][5]);
			}
		}
		
		//El formato de la construccion de la tabla está scada de internet de la página:https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
		

		
		JTable tab=new JTable(filas,headersDeTab);
		
		tab.setEnabled(false); //Esta linea evita que el usuario modifique los contenidos de la tabla
		
		
		Checkbox[] asistencia;
		//
		//Si el archivo esta vacio----------------------------
		///
		if(arrInfo[arrInfo.length-1].equals("")||arrInfo[arrInfo.length-1].equals("-_-")){
			//arrInfo[0]="";
			System.out.println("IF vacio");
			System.out.println("Length del arreglo de niños: "+ninosIns.length);
			if(ninosIns.length<=10){
				tallrLb=new JLabel[1]; 
			}
			if(ninosIns.length<=20&&ninosIns.length>10){
				tallrLb=new JLabel[2];
			}
			
			if(ninosIns.length<=30&&ninosIns.length>20){
				tallrLb=new JLabel[3];
			}
			if(ninosIns.length>30){
				tallrLb=new JLabel[4];
			}
			

			int setCond=0;
			
			for(int i=0;i<ninosIns.length;i++){
				if(ninosIns[i].getEdad()>=4&&ninosIns[i].getEdad()<=7){	
				setCond+=1;//preescolar
				}
				if(ninosIns[i].getEdad()>=8&&ninosIns[i].getEdad()<10){

					setCond+=2;//WeDo y WeDo 2
					}
				if(ninosIns[i].getEdad()>=10){
					setCond+=3;//EV3
					}
				if(setCond>6){
					setCond=6;
				}	
					
				}
			System.out.println("SetCondición: "+setCond);
			if(arrInfo[arrInfo.length-1].equals(null)||arrInfo[arrInfo.length-1].equals("-_-")){
			ids= new String [tallrLb.length];}else{
				StringTokenizer st=new StringTokenizer(arrInfo[arrInfo.length-1],",");
				int contar=0;
				if(!st.hasMoreTokens()){
					ids=new String[1];
				}else{
				ids=new String[st.countTokens()];
				}
				System.out.println("Stringtok: hasMoreTok= "+st.hasMoreTokens());
				
				while(st.hasMoreTokens()){
					ids[contar]=st.nextToken();
					contar++;
				}
			
				
			}
			
			int idp=0;
			if(arrInfo[arrInfo.length-1].equals(null)||arrInfo[arrInfo.length-1].equals("-_-")){
				ids= new String [tallrLb.length];
				}else{
					StringTokenizer st=new StringTokenizer(arrInfo[arrInfo.length-1],",");
					int contar=0;
					if(!st.hasMoreTokens()){
						ids=new String[1];
					}else{
					ids=new String[st.countTokens()];
					}
					System.out.println("Stringtok: hasMoreTok= "+st.hasMoreTokens());
					
					while(st.hasMoreTokens()){
						ids[contar]=st.nextToken();
						System.out.println(ids[contar]);
						contar++;
					}}
			
			System.out.println("ids en el tok: "+ids.length);
			if(tallrLb.length>ids.length){
				String []paso=ids;
				ids=new String[tallrLb.length];
				for(int r=0;r<paso.length;r++){
					ids[r]=paso[r];
					System.out.println(ids[r]);
				}
				}
			for(int i=0;i< tallrLb.length;i++){
				
				
				if(ninosIns.length<=10){
					System.out.println(ninosIns.length<=10);
					for(int x=0;x<taller.length;x++){
						boolean capa=taller[x].getCapacitacionParaEnsenar(0)||taller[x].getCapacitacionParaEnsenar(3)||(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
						System.out.println("Capa 1:"+capa);
						switch(setCond){
						case 1:{
							capa=taller[x].getCapacitacionParaEnsenar(0);
						break;	
						}
						case 2:{
							capa=taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2);
						break;	
						}
						case 3:{
							capa=taller[x].getCapacitacionParaEnsenar(3);
							break;
						}
						case 4:{
							capa=taller[x].getCapacitacionParaEnsenar(0)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
						break;	
						}
						case 5:{
							capa=taller[x].getCapacitacionParaEnsenar(3)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
						break;	
						}
						case 6:{
							capa=taller[x].getCapacitacionParaEnsenar(0)&&taller[x].getCapacitacionParaEnsenar(3)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
						break;	
						}
						
						}
						System.out.println("Capa: "+capa);
						if(capa){
						arrInfo[arrInfo.length-1]+=","+taller[x].getIDTallerista();
						System.out.println("Length ids al asignar: "+ids.length);
						ids[0]=taller[x].getIDTallerista();
						tallrLb[i]=new JLabel(taller[x].getNombreCompleto());
						x+=taller.length;
						}else{
							//En caso de no cumplir ninguno el default es:
							tallrLb[i]=new JLabel(taller[0].getNombreCompleto());
						}
					}
				}else{
					System.out.println("ELSE vacio");
					
					System.out.println("Nuevo IDS= "+ids.length);
					
					idp=0;
					
					for(int x=0;x<taller.length;x++){
						if(x==0){
							System.out.println("idp vale="+idp+" en el x==0");
							boolean capa=taller[x].getCapacitacionParaEnsenar(0)||taller[x].getCapacitacionParaEnsenar(3)||(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
							switch(setCond){
							case 1:{
								capa=taller[x].getCapacitacionParaEnsenar(0);
								break;	
							}
							case 2:{
								capa=taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2);
								break;	
							}
							case 3:{
								capa=taller[x].getCapacitacionParaEnsenar(3);
								break;
							}
							case 4:{
								capa=taller[x].getCapacitacionParaEnsenar(0)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
								break;	
							}
							case 5:{
								capa=taller[x].getCapacitacionParaEnsenar(3)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
								break;	
							}
							case 6:{
								capa=taller[x].getCapacitacionParaEnsenar(0)&&taller[x].getCapacitacionParaEnsenar(3)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
								break;	
							}
						
							}
							if(capa){
							arrInfo[arrInfo.length-1]+=taller[x].getIDTallerista();
							tallrLb[i]=new JLabel(taller[x].getNombreCompleto());
							ids[idp]=taller[x].getIDTallerista();
							System.out.println(ids[idp]+"  i="+i);
							idp++;
							}else{
								boolean capa2=(taller[x].getCapacitacionParaEnsenar(0)||taller[x].getCapacitacionParaEnsenar(3)||(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2)))&&!ids[idp-1].equals(taller[x].getIDTallerista());
								if(capa2){
									System.out.println(capa2+"=capa 2");
									
									arrInfo[arrInfo.length-1]+=","+taller[x].getIDTallerista();
									tallrLb[i]=new JLabel(taller[x].getNombreCompleto());
									ids[idp]=taller[x].getIDTallerista();
									System.out.println(ids[idp]+"  i="+i+" idp-1="+ids[idp-1]);
									idp++;
								}
							}
							
						}else{
							boolean capa2=(taller[x].getCapacitacionParaEnsenar(0)||taller[x].getCapacitacionParaEnsenar(3)||(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2)))&&!ids[idp-1].equals(taller[x].getIDTallerista());
							if(capa2){
								System.out.println(capa2+"=capa 2, idp= "+idp);
								
								arrInfo[arrInfo.length-1]+=","+taller[x].getIDTallerista();
								tallrLb[i]=new JLabel(taller[x].getNombreCompleto());
								ids[idp]=taller[x].getIDTallerista();
								System.out.println(ids[idp]);
								System.out.println(ids[idp]+"  i="+i+" idp-1="+ids[idp-1]);
								idp++;
								if(idp>=ids.length){
									x+=taller.length;
									
								}
							}	
						}
					}	
					}
					
					
				}
			
			for(int r=0;r<tallrLb.length;r++){
				for(int z=0;z<taller.length;z++){
					if(taller[z].getIDTallerista().equals(ids[r])){
						tallrLb[r]=new JLabel(taller[z].getNombreCompleto());
					}
				}
				
			}
				
			JLabel instruccion=new JLabel("Asistencia del Tallerista");
			lim.gridx=3;
			lim.gridy=0;
			lim.ipadx=100;
			display.add(instruccion,lim);
			lim.ipadx=0;
			asistencia=new Checkbox[6*tallrLb.length];//asumiendo que 0 es lunes , 1 martes y así susesivamente
			int checkInd=0;
			for(int i=0;i<tallrLb.length;i++){
				
				lim.gridx=0;
				lim.gridy=i+1;
				display.add(tallrLb[i],lim);
				for(int x=0;x<6;x++){
					String dia="";
					switch(x){
					case 0:{
						dia="Lunes";
						break;
					}
					case 1:{
						dia="Martes";
						break;
					}
					case 2:{
						dia="Miércoles";
						break;
					}
					case 3:{
						dia="Jueves";
						break;
					}
					case 4:{
						dia="Viernes";
						break;
					}
					case 5:{
						dia="Sábado";
						break;
					}
					}
					asistencia[checkInd]=new Checkbox(dia);
					lim.gridx=x+1;
					display.add(asistencia[checkInd],lim);
					checkInd++;
					
				}
				
				}
			
		}else{
			//
			//Fin del si el archivo esta vacio
			//
			
			if(arrInfo[arrInfo.length-1].equals("")){
				if(ninosIns.length<=10){
					tallrLb=new JLabel[1]; 
				}
				if(ninosIns.length<=20&&ninosIns.length>10){
					tallrLb=new JLabel[2];
				}
				
				if(ninosIns.length<=30&&ninosIns.length>20){
					tallrLb=new JLabel[3];
				}
				if(ninosIns.length>30){
					tallrLb=new JLabel[4];
				}
				

				int setCond=0;
				
				for(int i=0;i<ninosIns.length;i++){
					if(ninosIns[i].getEdad()>=4&&ninosIns[i].getEdad()<=7){	
					setCond+=1;//Prescolar
					}
					if(ninosIns[i].getEdad()>=8&&ninosIns[i].getEdad()<10){

						setCond+=2;//WeDo y WeDo 2
						}
					if(ninosIns[i].getEdad()>=10){
						setCond+=3;//EV3
						}
						
						
					}
				
				ids= new String [tallrLb.length];
				int idp=0;
				for(int i=0;i< tallrLb.length;i++){
					
					
					if(ninosIns.length<=10){
						for(int x=0;x<taller.length;x++){
							boolean capa=taller[x].getCapacitacionParaEnsenar(0)||taller[x].getCapacitacionParaEnsenar(3)||(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
							switch(setCond){
							case 1:{
								capa=taller[x].getCapacitacionParaEnsenar(0);
							break;	
							}
							case 2:{
								capa=taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2);
							break;	
							}
							case 3:{
								capa=taller[x].getCapacitacionParaEnsenar(3);
								break;
							}
							case 4:{
								capa=taller[x].getCapacitacionParaEnsenar(0)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
							break;	
							}
							case 5:{
								capa=taller[x].getCapacitacionParaEnsenar(3)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
							break;	
							}
							case 6:{
								capa=taller[x].getCapacitacionParaEnsenar(0)&&taller[x].getCapacitacionParaEnsenar(3)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
							break;	
							}
							
							}
							if(capa){
							arrInfo[arrInfo.length-1]+=taller[x].getIDTallerista();
							ids[0]=taller[x].getIDTallerista();
							tallrLb[i]=new JLabel(taller[x].getNombreCompleto());
							}
						}
					}else{
						
						for(int x=0;x<taller.length;x++){
							if(x==0){
								System.out.println("------------------");
								boolean capa=taller[x].getCapacitacionParaEnsenar(0)||taller[x].getCapacitacionParaEnsenar(3)||(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
								switch(setCond){
								case 1:{
									capa=taller[x].getCapacitacionParaEnsenar(0);
									break;	
								}
								case 2:{
									capa=taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2);
									break;	
								}
								case 3:{
									capa=taller[x].getCapacitacionParaEnsenar(3);
									break;
								}
								case 4:{
									capa=taller[x].getCapacitacionParaEnsenar(0)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
									break;	
								}
								case 5:{
									capa=taller[x].getCapacitacionParaEnsenar(3)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
									break;	
								}
								case 6:{
									capa=taller[x].getCapacitacionParaEnsenar(0)&&taller[x].getCapacitacionParaEnsenar(3)&&(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2));
									break;	
								}
							
								}
								if(capa){
								arrInfo[arrInfo.length-1]+=taller[x].getIDTallerista();
								tallrLb[i]=new JLabel(taller[x].getNombreCompleto());
								ids[idp]=taller[x].getIDTallerista();
								System.out.println(ids[idp]);
								idp++;
								}else{
									System.out.println("____________________");
									boolean capa2=(taller[x].getCapacitacionParaEnsenar(0)||taller[x].getCapacitacionParaEnsenar(3)||(taller[x].getCapacitacionParaEnsenar(1)||taller[x].getCapacitacionParaEnsenar(2)))&&!ids[idp-1].equals(taller[x].getIDTallerista());
									if(capa2){
										arrInfo[arrInfo.length-1]+=","+taller[x].getIDTallerista();
										tallrLb[i]=new JLabel(taller[x].getNombreCompleto());
										ids[idp]=taller[x].getIDTallerista();
										System.out.println(ids[idp]);
										idp++;
									}
								}
							}
						}
						
					}
					
				}
				//Metodo para crear la serie de checkboxes para marcar la asistencia de los talleristas
				JLabel instruccion=new JLabel("Asistencia del Tallerista");
				lim.gridx=3;
				lim.gridy=0;
				lim.ipadx=100;
				display.add(instruccion,lim);
				lim.ipadx=0;
				asistencia=new Checkbox[6*tallrLb.length];//asumiendo que 0 es lunes , 1 martes y así susesivamente
				int checkInd=0;
				for(int i=0;i<tallrLb.length;i++){
					
					lim.gridx=0;
					lim.gridy=i+1;
					display.add(tallrLb[i],lim);
					for(int x=0;x<6;x++){
						String dia="";
						switch(x){
						case 0:{
							dia="Lunes";
							break;
						}
						case 1:{
							dia="Martes";
							break;
						}
						case 2:{
							dia="Miércoles";
							break;
						}
						case 3:{
							dia="Jueves";
							break;
						}
						case 4:{
							dia="Viernes";
							break;
						}
						case 5:{
							dia="Sábado";
							break;
						}
						}
						asistencia[checkInd]=new Checkbox(dia);
						lim.gridx=x+1;
						display.add(asistencia[checkInd],lim);
						checkInd++;
						
					}
					
					}
			}else{
			
		StringTokenizer st=new StringTokenizer(arrInfo[arrInfo.length-1],",");
		System.out.println("Espacios del Arreglo ids: "+st.countTokens());
		ids= new String [st.countTokens()];
		tallrLb=new JLabel[st.countTokens()];
		int cont=0;
		while(st.hasMoreTokens()){
			ids[cont]=st.nextToken();
			System.out.println(ids[cont]);
			cont++;
		}
		for(int i=0;i<ids.length;i++){
			System.out.println("Entro al for para encotrar IDS");
			for(int x=0;x<taller.length;x++){
				if(taller[x].getIDTallerista().equals(ids[i])){
					tallrLb[i]=new JLabel(taller[x].getNombreCompleto());
					System.out.println(tallrLb[i].getText());
					x+=taller.length;
				}
			}
		}
		//Metodo para crear la serie de checkboxes para marcar la asistencia de los talleristas
		JLabel instruccion=new JLabel("Asistencia del Tallerista");
		lim.gridx=3;
		lim.gridy=0;
		lim.ipadx=100;
		display.add(instruccion,lim);
		lim.ipadx=0;
		asistencia=new Checkbox[6*tallrLb.length];//asumiendo que 0 es lunes , 1 martes y así susesivamente
		int checkInd=0;
		for(int i=0;i<tallrLb.length;i++){
			
			lim.gridx=0;
			lim.gridy=i+1;
			display.add(tallrLb[i],lim);
			for(int x=0;x<6;x++){
				String dia="";
				switch(x){
				case 0:{
					dia="Lunes";
					break;
				}
				case 1:{
					dia="Martes";
					break;
				}
				case 2:{
					dia="Miércoles";
					break;
				}
				case 3:{
					dia="Jueves";
					break;
				}
				case 4:{
					dia="Viernes";
					break;
				}
				case 5:{
					dia="Sábado";
					break;
				}
				}
				asistencia[checkInd]=new Checkbox(dia);
				lim.gridx=x+1;
				display.add(asistencia[checkInd],lim);
				checkInd++;
				
			}
			
			}
			}
		
		
		
		}
		JScrollPane scroll=new JScrollPane(tab);
		System.out.println();
		
		
		//------------------CAMBIAR TALLERISTAS-----------------
		JButton cambiar=new JButton("Cambiar a los talleristas actuales");
		cambiar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			JFrame vent=new JFrame();
			Toolkit tools= vent.getToolkit();
			Dimension size= tools.getScreenSize();
			vent.setSize((size.width)/2,size.height/2);
			vent.setTitle("Cambiar Talleristas");
			vent.setLocation(size.width/3,size.height/4);
			GridBagConstraints lim=new GridBagConstraints();
			JPanel display= new JPanel();
			display.setLayout(new GridBagLayout());
			vent.getContentPane().add(display);
			System.out.println("Length de ids= "+ids.length);
			JLabel newTall[]=new JLabel[ids.length];
			JTextField[] newTallT=new JTextField[ids.length];
			for(int i=0;i<ids.length;i++){
				newTall[i]=new JLabel("Introdusca el ID del Tallerista sustituto "+(i+1)+": ");
				lim.gridx=0;
				lim.gridy=i;
				lim.ipadx=0;
				display.add(newTall[i], lim);
				
				newTallT[i]=new JTextField();
				lim.gridx=1;
				lim.gridy=i;
				lim.ipadx=100;
				display.add(newTallT[i], lim);				
			}
			JButton confirmar=new JButton("Confirmar");
			confirmar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
				for(int i=0;i<ids.length;i++){
					ids[i]=newTallT[i].getText();
					for(int x=0;x<taller.length;x++){
						if(taller[x].getIDTallerista().equals(ids[i])){
					tallrLb[i].setText(taller[x].getNombreCompleto());
						}}
					
				}
				
				
		vent.setVisible(false);
			}});
			lim.gridx=1;
			lim.gridy=ids.length+1;
			display.add(confirmar,lim);
			vent.setVisible(true);
			
		}});
		lim.weightx=0;
		lim.weighty=0;
		lim.gridy=tallrLb.length+1;
		lim.gridx=4;
		display.add(cambiar,lim);
		
		
		
		//
		//------------GUARDAR ARCHIVO CAL------------
		//
		JButton guardar=new JButton("Guardar");
		guardar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			String guar="";
			for(int i=0;i<ids.length;i++){
				if(i==0){
					guar=ids[i];
				}else{			
				guar+=","+ids[i];
				}
				System.out.println(i+" ids: "+ids[i]);
			}
			arrInfo[arrInfo.length-1]=guar;
			for(int i=0;i<arrInfo.length;i++){
				System.out.println(i+" ArrInfo= "+arrInfo[i]);
				
			}
			
			for(int i=0;i<tallrLb.length;i++){
				int frec=0;
				for(int x=0;x<6;x++){
					if(asistencia[x+(6*i)].getState()){
						frec++;
						System.out.println(frec);
					}
					
				}
				
				for(int x=0;x<taller.length;x++){
					if(taller[x].getIDTallerista().equals(ids[i])){
						System.out.println("Frecuencias a incrementar: "+frec);
						System.out.println(taller[x].getFrecuenciasTrabajadas(i));
						taller[x].setFrecuenciasTrabajadas(taller[x].getFrecuenciasTrabajadas(arrInfo.length-1)+frec, arrInfo.length-1);
						System.out.println(taller[x].getFrecuenciasTrabajadas(i));
					}
				}
				Listo();
				vent.setVisible(false);
				
			}
			System.out.println("Valor de la tabla:"+ tab.getModel().getValueAt(2, 0));
			try {
				writeFileCal(arrInfo);
				writeFile(taller);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}});
		lim.weightx=0;
		lim.weighty=0;
		lim.gridy=tallrLb.length+1;
		lim.gridx=0;
		display.add(guardar,lim);
		
		//
		//-------------LISTA DE ASISTENCIA-------------
		//
		
		JButton listaDeAsistencia=new JButton("Lista de Asistencia de los niños");
		listaDeAsistencia.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			JFrame vent=new JFrame();
			Toolkit tools= vent.getToolkit();
			Dimension size= tools.getScreenSize();
			vent.setSize(size.width/3, size.height/2);
			vent.setLocation(size.width/3,size.height/8);
			GridBagConstraints lim=new GridBagConstraints();
			JPanel display= new JPanel();
			display.setLayout(new GridBagLayout());
			vent.getContentPane().add(display);
			lim.insets=new Insets(5,5,5,5);
			
			String[] header={"ID","Nombre","Horas de Clase"};
			String[][] lines=new String[ninosIns.length][3];
			for(int i=0;i<ninosIns.length;i++){
			
					filas[i][0]=ninosIns[i].getIDNino();
					System.out.println(filas[i][0]);
					
				
					filas[i][1]=ninosIns[i].getNombre();
					System.out.println(filas[i][1]);
				
					filas[i][2]=ninosIns[i].getHorasDeClase();
					System.out.println(filas[i][2]);
				
				
			}
			JTable tabla=new JTable(filas,header);
			tabla.setEnabled(false);
			
			JScrollPane scrollP=new JScrollPane(tabla);
			vent.getContentPane().add(scrollP);
			
			
			JButton cerrar=new JButton("Cerrar");
			cerrar.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
				
				
				vent.setVisible(false);
			}});
			scrollP.add(cerrar);
			vent.setVisible(true);
			
		}});
		
		lim.gridy=tallrLb.length+1;
		lim.gridx=7;
		display.add(listaDeAsistencia,lim);
		
		
		
		//lim.gridx=1;
		//lim.gridy=0;
		scroll.add(display, lim);
		
		//tab.setFillsViewportHeight(true);
		tab.setVisible(true);
		scroll.setVisible(true);
		vent.getContentPane().setLayout(new GridBagLayout());
		lim.gridy=0;
		lim.weightx=10;
		lim.weighty=8;
		lim.fill=lim.BOTH;
		vent.getContentPane().add(scroll,lim);
		lim.weightx=0;
		lim.weighty=0;
		lim.gridy=1;
		vent.getContentPane().add(display,lim);
		
		
		
		
		//vent.getContentPane().add(display);
		
		
		vent.setVisible(true);
		
	}
	
	//
	//-----------FIN DE CAL----------
	//
	
	
	
	
	//Write File y readFile de Talleristas
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
	
	//Metodos de lectura y escritura de Ninos
	//
	//
	

	
	
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
				fileOut.println(ninos.getIDNino()+","+ninos.getNombre()+","+ninos.getEdad()+","+ninos.getFechaDeNacimiento()+","+ninos.getEscuela()+","+ninos.getGradoEscolar()+","+ninos.getDireccion()+","+ninos.getCodigoPostal()+","+ninos.getTelefonoCasa()+","+enfer+","+alerg+","+med+","+ ninos.getTipoDeSangre()+","+ninos.getClaseDePrueba()+","+ninos.getHorasDeClase()+","+dias+","+ninos.getTipoDeSesion()+","+ninos.getIDPadres()+","+ninos.getInscrito()+","+ninos.getDescuentos());
			}	
			
			//}
		}}
		fileOut.close();
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
				System.out.println(cont);	
				switch(cont){
				
				case 0:{
						
					IDN=st.nextToken();
					System.out.println(IDN);
						break;
					}
					case 1:{
						Nombre=st.nextToken();
						System.out.println(Nombre);
						break;
					}
					case 2:{
						Edad=Integer.parseInt(st.nextToken());
						System.out.println(Edad);
						break;
					}
					
					case 3:{
						FechaN=st.nextToken();
						System.out.println(FechaN);
						break;
					}
					case 4:{
						Escu=st.nextToken();
						System.out.println(Escu);
						break;
					}
					case 5:{
						Grad=st.nextToken();
						System.out.println(Grad);
						break;
					}
					case 6:{
						Dir=st.nextToken();
						System.out.println(Dir);
						break;
					}
					case 7:{
						String paso=st.nextToken();
						System.out.println(paso);
						CodP=Integer.parseInt(paso);
						break;
					}
					case 8:{
						TelC=Integer.parseInt(st.nextToken());
						System.out.println(TelC);
						break;
					}
					case 9:{
						StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
						String[] paso=new String[st2.countTokens()];
						int y=0;
						while(st2.hasMoreTokens()){
							paso[y]=st2.nextToken();
							System.out.println(paso[y]);
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
							System.out.println(paso[y]);
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
							System.out.println(paso[y]);
							y++;
						}
						Med=paso;
						break;
					}
					case 12:{
						TipoSan=st.nextToken();
						System.out.println(TipoSan);
						break;
					}
					case 13:{
						Prueba=st.nextToken().equals("true");
						System.out.println(Prueba);
						break;
					}
					case 14:{
						Horas=st.nextToken();
						System.out.println(Horas);
						break;
					}
					case 15:{
						StringTokenizer st2=new StringTokenizer(st.nextToken(),"/");
						boolean[] paso=new boolean[st2.countTokens()];
						int y=0;
						while(st2.hasMoreTokens()){
							paso[y]=st2.nextToken().equals("true");
							System.out.println(paso[y]);
							y++;
						}
						dias=paso;
						break;
					}
					case 16:{
						tipo=st.nextToken().equals("true");
						System.out.println(tipo);
						break;
					}
					case 17:{
						IDPadre=st.nextToken();
						System.out.println(IDPadre);
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
	
	public void writeFileCal(String[] arr)throws IOException{
		PrintWriter fileOut;
		fileOut=new PrintWriter(new FileWriter("Calendario.txt"));
		for (int i=0; i<arr.length;i++)
		{
			
			String calend=arr[i];
			//Procedimiento para convertir arr a Strings 
			System.out.println(i+" Guardar en Calendario: "+calend);
			
				
				fileOut.println(calend);
			}	
			
			
		
		fileOut.close();
		
	}
	
	public static String[] readFileCal()throws IOException{
		String[] arr ={""};
		System.out.println("Llego Calendario");
		File read = new File("Calendario.txt");
		String[] vacio={""};
		boolean lleno=true;
		//lee archivo Padres
			System.out.println("condicioón a: "+(read.exists()) +" b: "+ !read.isDirectory());	
			if(read.exists() && !read.isDirectory()) { 
					BufferedReader leerDir=new BufferedReader(new FileReader("Calendario.txt"));
					int cont =0;
					int size=0;
					while(leerDir.ready()){
						size++;
						leerDir.readLine();
						
					}
					leerDir.close();
					System.out.println("Tamaño arreglo "+size);
					if(size==0){
						return vacio;
					}
				 leerDir=new BufferedReader(new FileReader("Calendario.txt")); 
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
				}else {
					return vacio;
				}
				return arr;
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
	
	public void Listo(){
		JFrame vent=new JFrame();
		Toolkit tools= vent.getToolkit();
		Dimension size= tools.getScreenSize();
		vent.setSize(100,100);
		vent.setLocation(size.width/3,size.height/4);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		vent.getContentPane().add(display);
		lim.insets=new Insets(5,5,5,5);
		JLabel list=new JLabel("Listo");
		lim.gridx=0;
		lim.gridy=1;
		display.add(list, lim);
		
		JButton cer=new JButton("Cerrar");
		cer.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			
			vent.setVisible(false);
		}});
		vent.setVisible(true);
	}

}
