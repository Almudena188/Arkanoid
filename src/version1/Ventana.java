package version1;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferByte;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ventana {

	private static final int JFRAME_WIDHT = 502;
	private static final int JFRAME_HEIGHT = 640;
	MiCanvas canvas = new MiCanvas();
	JFrame ventana;
	
	static List<Actor> actores =  null;
	private static Nave nave = null;
	private static Ventana instance = null;
	
	// BufferStrategy usado para conseguir la técnica de doble búffer
	private BufferStrategy strategy;


	public Ventana() {
		
		// guardo la lista de los actores creada en la clase MiCanvas
		actores = canvas.creadorDeActores();
		this.nave = canvas.getNave();

		// Le doy forma a la ventana
		ventana = new JFrame("Arkanoid");
		JPanel panel = (JPanel) ventana.getContentPane();
		
		panel.setLayout(new BorderLayout());
		panel.add(canvas, BorderLayout.CENTER);
		ventana.setBounds(0, 0, JFRAME_WIDHT, JFRAME_HEIGHT);
		

		// Clase anonima paa mover el raton
		canvas.addMouseMotionListener(new MouseAdapter() {
			

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseMoved(e);
				nave.mueveMouse(e.getX());
			}

		});
		
		canvas.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("key pressed");
				super.keyPressed(e);
				nave.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
				nave.keyReleased(e);
			}
			
			
			
		});
		
		ventana.setVisible(true);
		

		

//		Clase anónima en la que sale una ventanita para cerrar el programa cuando le das a la cruz de cerrar
		ventana.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				String[] opciones = { "Aceptar", "Cancelar" };
				int eleccion = JOptionPane.showOptionDialog(ventana, "¿Quiere cerrar el juego?", "SALIR",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
				if (eleccion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});
		
		// El Canvas se dibujará en pantalla con una estrategia de doble búffer
				ventana.createBufferStrategy(2);
				// Obtengo una referencia a la estrategia de doble búffer.
				strategy = canvas.getBufferStrategy();

	}

	/**
	 * Hace que se mueva la pelota
	 */
	public void movimiento() {

		boolean yaTenemosFoco = false;
		
		int repeticionesPorSegundi = 60;
		int millisPorCadaFrame = 1000 / repeticionesPorSegundi;

		// hago un bucle infinito
		do {
			
			if (ventana.getFocusOwner() != null && !ventana.getFocusOwner().equals(canvas)) {
				canvas.requestFocus();
			}

			// cojo los milis-segundos acutales
			long millisAntesDeEscena = new Date().getTime();

			canvas.repaint(); // redibujo

			// Recorro todos los actores y hago actuar el motodo de movimiento
			for (Actor a : actores) {
				a.mueve();
			}

			// calculo los millis que debemos parar el proceso, 60 FPS
			long millisDespuesDeEscena = new Date().getTime();

			int millisDuranteElProceso = (int) (millisDespuesDeEscena - millisAntesDeEscena);
			int millisPausa = millisPorCadaFrame - millisDuranteElProceso;
			millisPausa = (millisPausa < 0) ? 0 : millisPausa;

			// duermo el proceso durante el tiempo calculado
			try {
				Thread.sleep(millisPausa);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (true);

	}
	
	
// implemento el Singleton
	public static Ventana getInstance() {
		if (instance == null) {
			instance = new Ventana();

		}
		return instance;
	}
//GETTER
	public static int getJframeWidht() {
		return JFRAME_WIDHT;
	}

	public MiCanvas getCanvas() {
		return canvas;
	}

	

	

}
