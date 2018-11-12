import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;
public class Ventana_Principal extends JFrame{
	private JButton BPadresI;//Abre la interfase para dar de alta, baja o editar los datos de los padres
	private JButton BNinosI;//Abre la interfase para dar de alta, baja o editar los datos de los niños
	private JButton BCalendario;// Abre la interfase para consultar, crear y editar horarios
	private JButton BTalleristas;//Abre la interfase para dar de alta, baja o editar los datos de los talleristas
	private JButton BVerdatos=new JButton("Consultar Balance");
	public Ventana_Principal()throws IOException{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit tools= getToolkit();
		Dimension size= tools.getScreenSize();
		setSize((size.width)/3+30,size.height/3);
		setTitle("Menú Principal: GEM");
		setLocation(size.width/3,size.height/3);
		GridBagConstraints lim=new GridBagConstraints();
		JPanel display= new JPanel();
		display.setLayout(new GridBagLayout());
		getContentPane().add(display);
		
		lim.gridx=0;
		lim.gridy=0;
		JButton BPadresI=new JButton("Consultar Datos Padres");
		display.add(BPadresI,lim);
		BPadresI.addActionListener(new ActionListener(){public void  actionPerformed(ActionEvent event){
			Menu_Padres_Ninos VentPadres = null;
			try {
				VentPadres = new Menu_Padres_Ninos(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			VentPadres.setVisible(true);
			
		}});
		//funcion
		lim.gridx=0;
		lim.gridy=1;
		JButton BNinosI=new JButton("Consultar Datos Niños");
		display.add(BNinosI,lim);
		//funcion
		BNinosI.addActionListener(new ActionListener(){public void  actionPerformed(ActionEvent event){
			Menu_Padres_Ninos VentPadres = null;
			try {
				VentPadres = new Menu_Padres_Ninos(false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			VentPadres.setVisible(true);
			
		}});
		lim.gridx=1;
		lim.gridy=0;
		JButton BCalendario=new JButton("Consultar calendario");
		BCalendario.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent event){
			try {
				Ventana_Calendario vent=new Ventana_Calendario();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}});
		display.add(BCalendario,lim);
		//funcion
		lim.gridx=1;
		lim.gridy=1;
		JButton BTalleristas=new JButton("Consultar talleristas");
		display.add(BTalleristas,lim);
		//funcion
		BTalleristas.addActionListener(new ActionListener(){public void  actionPerformed(ActionEvent event){
			Ventana_Talleristas VentTalleristas = null;
			try {
				VentTalleristas = new Ventana_Talleristas();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			VentTalleristas.setVisible(true);
			
		}});
		
		BVerdatos.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent event){
			try {
				verDatos x=new verDatos();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}});
		lim.gridx=2;
		lim.gridy=0;
		display.add(BVerdatos,lim);
		
		JButton cerrar= new JButton("Cerrar");
		lim.gridx=2;
		lim.gridy=1;
		cerrar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		display.add(cerrar,lim);
		
	}
	
	public static void main(String args[]) throws IOException{
		Ventana_Principal vent=new Ventana_Principal();
		vent.setVisible(true);
	}
}
