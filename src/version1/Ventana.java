package version1;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
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
	
	private List<Actor> actoresParaIncorporar = new ArrayList<Actor>();
	private List<Actor> actoresParaEliminar = new ArrayList<Actor>();
	

	static List<Actor> actores = null;
	private static Nave nave = null;
	private static Ventana instance = null;

	// BufferStrategy usado para conseguir la t�cnica de doble b�ffer
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
		
		canvas.setIgnoreRepaint(true);

//		Clase an�nima en la que sale una ventanita para cerrar el programa cuando le das a la cruz de cerrar
		ventana.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {

				String[] opciones = { "Aceptar", "Cancelar" };
				int eleccion = JOptionPane.showOptionDialog(ventana, "�Quiere cerrar el juego?", "SALIR",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
				if (eleccion == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}

		});

		// El Canvas se dibujar� en pantalla con una estrategia de doble b�ffer
		ventana.createBufferStrategy(2);
		// Obtengo una referencia a la estrategia de doble b�ffer.
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

			canvas.pintaMundo(); // redibujo

			// Recorro todos los actores y hago actuar el motodo de movimiento
			for (Actor a : actores) {
				a.mueve();
			}

			// Tras hacer que cada actor actúe y antes de agregar y eliminar actores,
			// detecto colisiones
			detectaColisiones();

			// Acualizo los actores, incorporando los nuevos y eliminando los que ya no se
			// quieren
			actualizaActores();

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
	
	
	
	/**
	 * Detecta colisiones entre actores e informa a los dos
	 */
	private void detectaColisiones() {
		// Una vez que cada actor ha actuado, intento detectar colisiones entre los actores y notificarlas. Para detectar
		// estas colisiones, no nos queda más remedio que intentar detectar la colisión de cualquier actor con cualquier otro
		// sólo con la excepción de no comparar un actor consigo mismo.
		// La detección de colisiones se va a baser en formar un rectángulo con las medidas que ocupa cada actor en pantalla,
		// De esa manera, las colisiones se traducirán en intersecciones entre rectángulos.
		for (Actor actor1 : this.actores) {
			// Creo un rectángulo para este actor.
			Rectangle rect1 = new Rectangle(actor1.getX(), actor1.getY(), actor1.getAncho(), actor1.getLargo());
			// Compruebo un actor con cualquier otro actor
			for (Actor actor2 : this.actores) {
				// Evito comparar un actor consigo mismo, ya que eso siempre provocaría una colisión y no tiene sentido
				if (!actor1.equals(actor2)) {
					// Formo el rectángulo del actor 2
					Rectangle rect2 = new Rectangle(actor2.getX(), actor2.getY(), actor2.getAncho(), actor2.getLargo());
					// Si los dos rectángulos tienen alguna intersección, notifico una colisión en los dos actores
					if (rect1.intersects(rect2)) {
						actor1.colisionaCon(actor2); // El actor 1 colisiona con el actor 2
						actor2.colisionaCon(actor1); // El actor 2 colisiona con el actor 1
					}
				}
			}
		}
	}
	
	/**
	 * Incorpora los actores nuevos al juego y elimina los que corresponden
	 */
	private void actualizaActores () {
		// Incorporo los nuevos actores
		for (Actor a : this.actoresParaIncorporar) {
			this.actores.add(a);
		}
		this.actoresParaIncorporar.clear(); // Limpio la lista de actores a incorporar, ya están incorporados
		
		// Elimino los actores que se deben eliminar
		for (Actor a : this.actoresParaEliminar) {
			this.actores.remove(a);
		}
		this.actoresParaEliminar.clear(); // Limpio la lista de actores a eliminar, ya los he eliminado
	}
	
	/**
	 * Método llamado para incorporar nuevos actores
	 * @param a
	 */
	public void incorporaNuevoActor (Actor a) {
		this.actoresParaIncorporar.add(a);
	}

	/**
	 * Método llamado para eliminar actores del juego
	 * @param a
	 */
	public void eliminaActor (Actor a) {
		this.actoresParaEliminar.add(a);
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
