package version1;

import java.awt.Color;
import java.awt.Graphics;

public class Pelota extends Actor {
	private int velocidadX = -5;
	private int velocidadY = -5;

	public Pelota() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pelota(int x, int y, int ancho, int largo) {
		super(x, y, ancho, largo);
		// TODO Auto-generated constructor stub
	}

	// GETTER AND SETTER

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
	}

	public int getVelocidadY() {
		return velocidadY;
	}

	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.getX(), this.getY(), this.getAncho(), this.getLargo());

	}

	@Override
	public void mueve() {
		
		// Movimiento horizontal
		this.x += this.velocidadX;
		// evito que se salga de la venta por los lados derecha e izquierda
		if (this.x < 0 || this.x > 490.5) { // 502( ancho de la ventana) - 7.5 ( radio de la pelota ) = 494.5
			this.velocidadX =- this.velocidadX; // cambio el sentido de la pelota
			
		

		}
		
		// Movimiento vertical
		this.y += this.velocidadY;
		// evito que se salga de la venta por los lados de arriba y abajo
		if (this.y < 7.5 || this.y > 600) { // 640( largo de la ventana) - 7.5 ( radio de la pelota ) = 632.5
			this.velocidadY = -this.velocidadY; // cambio el sentido de la pelota

		}
	}

}
