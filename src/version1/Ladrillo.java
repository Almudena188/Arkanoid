package version1;

import java.awt.Color;
import java.awt.Graphics;

public class Ladrillo extends Actor {

	private String nombre;
	private Color color;

	public Ladrillo() {
		super();

	}

	public Ladrillo(int x, int y, int ancho, int largo) {
		super(x, y, ancho, largo);

	}


	// GETTER AND SETTER

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	

	@Override
	public void mueve() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getLargo());

	}




}
