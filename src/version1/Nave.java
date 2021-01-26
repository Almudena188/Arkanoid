package version1;

import java.awt.Color;
import java.awt.Graphics;

public class Nave extends Actor {
	private int velocidad;
	private String nombre;

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
		// TODO Auto-generated method stub

	}

	public void mueveMouse(int x) {
		this.x = x;
		// guardo lo que mide el canvas
		int anchoCanvas = Ventana.getInstance().getJframeWidht();
		
		if(this.x > anchoCanvas - this.getAncho())
			this.x = anchoCanvas -this.getAncho();
				
	}

}
