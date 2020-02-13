import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Frame extends JFrame{
	public ClientSocket serveur;
	public boolean ouverture;
	public String bo;
	public Thread_recep recept;
	public String sentence;

	public Frame(ClientSocket serveur){//, Thread_recep thrd){
		super("Test Serveur");
		try{
			this.ouverture=true;
			this.setSize(300,500);
			this.setResizable(false);
			this.setLayout(new GridLayout(6,1));
			this.serveur = serveur;
			//this.recept = thrd;
			
			//initialisation des composants
			JRadioButton pepper = new JRadioButton("Pepper");
			JRadioButton nao = new JRadioButton("Nao");
			JRadioButton robotino = new JRadioButton("Robotino");
			JRadioButton pekee = new JRadioButton("Pekee");

			ButtonGroup bg1 = new ButtonGroup();
			bg1.add(pepper);
			bg1.add(nao);
			bg1.add(robotino);
			bg1.add(pekee);

			JTextField message = new JTextField();

			//Ajout des composants
			this.add(pepper);
			this.add(nao);
			this.add(robotino);
			this.add(pekee);
			this.add(message);

			//Listeners
			JButton envoyer = new JButton("Envoyer");
			this.add(envoyer);
			envoyer.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					//synchronized(serveur.soc){};
					if (pepper.isSelected()){
						
						serveur.sendOrder("Pepper",message.getText());
					}
					else if (nao.isSelected()){
						serveur.sendOrder("Nao",message.getText());
					}
					else if (robotino.isSelected()){
						serveur.sendOrder("Robotino",message.getText());
					}
					else if (pekee.isSelected()){
						serveur.sendOrder("Pekee",message.getText());
					}
					//System.out.println("drgd");System.out.flush();
					//serveur.reciev();

					message.setText("");
				} 
			});
//			JButton ecouter = new JButton("ecouter");
//			this.add(ecouter);
//			ecouter.addActionListener(new ActionListener(){
//
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					ouverture=true;
//				}
//				
//			});
			setVisible(true);
//			while (ouverture){
//				System.out.println("ok");
//				bo = serveur.reciev();
//				System.out.println(bo);
//			}
			
		}
		catch (Exception e){}
		
	}
	
}
