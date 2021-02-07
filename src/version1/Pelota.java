package version1;

import java.awt.Color;
import java.awt.Graphics;



public class Pelota extends Actor {
	private int velocidadX = -4;
	private int velocidadY = -4;

	public Pelota() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pelota(int x, int y, int ancho, int largo) {
		super(x, y, ancho, largo);
		this.setSpriteActual(RecursosCache.getInstance().getImagen(RecursosCache.IMAGEN_PELOTA));
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

//	@Override
//	public void paint(Graphics g) {
//		g.setColor(Color.RED);
//		g.fillOval(this.getX(), this.getY(), this.getAncho(), this.getLargo());
//
//	}

	@Override
	public void mueve() {
		
		// Movimiento horizontal
		this.x += this.velocidadX;
		// evito que se salga de la venta por los lados derecha e izquierda
		if (this.x < 0 || ( this.x + this.getAncho()) > Ventana.getInstance().getCanvas().getWidth()) { // 502( ancho de la ventana) - 7.5 ( radio de la pelota ) = 494.5
			this.velocidadX = -this.velocidadX; // cambio el sentido de la pelota
			
		

		}
		
		// Movimiento vertical
		this.y += this.velocidadY;
		// evito que se salga de la venta por los lados de arriba y abajo
		if (this.y < 0 ||( this.y + this.getLargo()) > Ventana.getInstance().getCanvas().getHeight()) { // 640( largo de la ventana) - 7.5 ( radio de la pelota ) = 632.5
			this.velocidadY = -this.velocidadY; // cambio el sentido de la pelota

		}
	}
	
	
	@Override
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);
		// si choca con ladrillo baja
		if (a instanceof Ladrillo) {
			this.velocidadY +=  4;
		}
		// si choca con nave sube
		if (a instanceof Nave ) {
			this.velocidadY -= 4;
		
		}
	}
	

}
