package version1;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {

	private String nombre;

	public Ladrillo() {
		super();

	}

	public Ladrillo(int x, int y, int ancho, int largo) {
		super(x, y, ancho, largo);

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getLargo());

	}

	// GETTER AND SETTER

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public void mueve() {
		// TODO Auto-generated method stub
		
	}

}
