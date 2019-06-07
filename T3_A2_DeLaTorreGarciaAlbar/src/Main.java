import java.awt.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;



class generarDatos{
	Random rnd = new Random();
	public String[] arregloDatos() {
		String dat[] = new String[10000000];
		int num = 0;
		for (int i = 0; i < dat.length; i++) {
			num = rnd.nextInt(2);
			if (num==0) {
				dat[i]="Si";
			}
			else {
				dat[i]="No";
			}
		}
		return dat;
	}
}

class Interfaz extends JFrame{
	JProgressBar pBar;
	JProgressBar pBar2;
	JTextArea aTxt;
	JTextArea aTxt2;

	
   public Interfaz() {
	   crearComponentes();
   }
   
   public void crearComponentes(){
		getContentPane().setLayout(null);
		setSize(640,630);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Análisis De Twitter");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		aTxt = new JTextArea();
		aTxt.setBounds(0, 20, 300, 550);
		aTxt.setLineWrap(true);
		aTxt.setWrapStyleWord(true);
		add(aTxt);
		aTxt2=new JTextArea();
		aTxt2.setBounds(315, 20, 300, 550);
		aTxt2.setLineWrap(true);
		aTxt2.setWrapStyleWord(true);
		add(aTxt2);
		
		pBar = new JProgressBar();
		pBar.setBounds(0, 310, 1, 1);
		add(pBar);
		pBar2=new JProgressBar();
		pBar2.setBounds(0, 320, 1, 1);
		add(pBar2);
   }
   
}
class areasTxt implements Runnable{
	Interfaz interfaz = new Interfaz();
	
	@Override
	public void run() {
		generarDatos gD = new generarDatos();
		String datos[] = gD.arregloDatos();
		
		for (int i = 0; i <datos.length ; i++) {
			if (datos[i].equalsIgnoreCase("Si")) {
				interfaz.aTxt.setText(interfaz.aTxt.getText() + datos[i]+",");
			}
			else {
				interfaz.aTxt2.setText(interfaz.aTxt2.getText() + datos[i]+",");
			}	
		}
	}
}


class Estadistica implements Runnable{

	@Override
	public void run() {
		generarDatos obj = new generarDatos();
		String datos[] = obj.arregloDatos();
		int contSi=0;
		int contNo=0;
		for (int i = 0; i < datos.length ; i++) {
			if (datos[i].equalsIgnoreCase("Si")) {
				contSi++;
			}
			else {		
				contNo++;
			}	
		}
		 JFrame jF = new JFrame("Resultados De Estadisticas");
		    Container contenedor = jF.getContentPane();
		    jF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    jF.setSize(400, 150);
		    jF.setLocationRelativeTo(null);
		    jF.setVisible(true); 
	    
		    JProgressBar progressBar = new JProgressBar(0, datos.length);
		    progressBar.setValue(contSi);
		    progressBar.setStringPainted(true);
		    Border border = BorderFactory.createTitledBorder("Respuestas Positivas");
		    progressBar.setBorder(border);
		    contenedor.add(progressBar, BorderLayout.NORTH);
		    
		    JProgressBar progressBar2 = new JProgressBar(0, datos.length);
		    progressBar2.setValue(contNo);
		    progressBar2.setStringPainted(true);
		    Border border2 = BorderFactory.createTitledBorder("Respuestas Negativas");
		    progressBar2.setBorder(border2);
		    contenedor.add(progressBar2, BorderLayout.CENTER);
		    
	} 
	
}




public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Thread hilo1=new Thread(new areasTxt());
				Thread hilo2=new Thread(new Estadistica());
				hilo1.start();
				hilo2.start();
				
			}
		});
		
	}

}
