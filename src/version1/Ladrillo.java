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
		this.setSpriteActual( RecursosCache.getInstance().getImagen(RecursosCache.IMAGEN_LADRILLO));
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
//	@Override
//	public void paint(Graphics g) {
//		g.setColor(this.color);
//		g.fillRect(this.getX(), this.getY(), this.getAncho(), this.getLargo());
//
//	}
	
	/**
	 * Este método se disparará cuando un actor colisione con el disparo
	 */
	@Override
	public void colisionaCon(Actor a) {
		super.colisionaCon(a);
		// Si colisionamos con monstruo, eliminamos el disparo
		if (a instanceof Actor) {
			Ventana.getInstance().eliminaActor(this);
		}
		RecursosCache.getInstance().playSonido("missile.wav");
		Ventana.getInstance().incorporaNuevoActor(new Explosion(this.x, this.y));	
		
	}



}
