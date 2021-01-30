package version1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Nave extends Actor {
	private int velocidad;
	private String nombre;
	private boolean derecha, izquierda; // si no pongo valor lo iguala a false por defecto

	protected static final int PLAYER_SPEED = 4; // velocidad de la nave

	public Nave() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nave(int x, int y, int ancho, int largo) {
		super(x, y, ancho, largo);
		// TODO Auto-generated constructor stub
	}

	// GETTER AND SETTER

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getLargo());

	}

	@Override
	public void mueve() {
		
		MiCanvas canvas = Ventana.getInstance().getCanvas();

		this.x += this.velocidad;
		// para que no se salga por la izquierda
		if (this.x < 0) {
			this.x = 0;
		}

		// para que no se salga por la derecha
		if (this.x > (canvas.getWidth() - this.getAncho())) {
			this.x = canvas.getWidth() - this.getAncho();
		}

	}

	/**
	 * Mueve la nave por raton
	 * 
	 * @param x
	 */
	public void mueveMouse(int x) {
		this.x = x;
		// guardo lo que mide el canvas
		int anchoCanvas = Ventana.getInstance().getJframeWidht();

		if (this.x > anchoCanvas - this.getAncho())
			this.x = anchoCanvas - this.getAncho();
	}

	/**
	 * para que se mueva cuando presiona la tecla
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {

		case KeyEvent.VK_LEFT:
			izquierda = true;
			break;
		case KeyEvent.VK_RIGHT:
			derecha = true;
			break;
		}

		updateSpeed();

	}

	/**
	 * para que cuando leante la tecla deje de moverse
	 * 
	 * @param e
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			izquierda = false;
		case KeyEvent.VK_RIGHT:
			derecha = false;

		}

		updateSpeed();

	}

	protected void updateSpeed() {
		this.velocidad = 0;
		if (derecha) {
			this.velocidad = PLAYER_SPEED;
		}
		if (izquierda) {
			this.velocidad = -PLAYER_SPEED;
		}
	}

}
