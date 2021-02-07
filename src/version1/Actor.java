package version1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Actor {

	protected int x;
	protected int y;
	private int ancho;
	private int largo;
	protected BufferedImage img;
	protected BufferedImage spriteActual;

	// Posibilidad de que el actor sea animado, a trav�s del siguiente array de
	// sprites y las variables
	// velocidadDeCambioDeSprite y unidadDeTiempo
	protected List<BufferedImage> spritesDeAnimacion = new ArrayList<BufferedImage>();
	protected int velocidadDeCambioDeSprite = 0;
	private int unidadDeTiempo = 0;

	public Actor() {

	}

	public Actor(int x, int y, int ancho, int largo) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.largo = largo;
	}

	public void colisionaCon(Actor a) {
	}

	public void mueve() {
		// En el caso de que exista un array de sprites el actor actual se tratar� de
		// una animaci�n, para eso llevaremos a cabo los siguientes pasos
		if (this.spritesDeAnimacion != null && this.spritesDeAnimacion.size() > 0) {
			unidadDeTiempo++;
			if (unidadDeTiempo % velocidadDeCambioDeSprite == 0) {
				unidadDeTiempo = 0;
				int indiceSpriteActual = spritesDeAnimacion.indexOf(this.spriteActual);
				int indiceSiguienteSprite = (indiceSpriteActual + 1) % spritesDeAnimacion.size();
				this.spriteActual = spritesDeAnimacion.get(indiceSiguienteSprite);
			}
		}
	}

	public void paint(Graphics g) {
		g.drawImage(this.spriteActual, this.x, this.y, null);
	}
	// GETTER AND SETTER

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public BufferedImage getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(BufferedImage img) {
		this.img = img;
		this.ancho = this.img.getWidth();
		this.largo = this.img.getHeight();
	}

	/**
	 * @return the spritesDeAnimacion
	 */
	public List<BufferedImage> getSpritesDeAnimacion() {
		return spritesDeAnimacion;
	}

	/**
	 * @param spritesDeAnimacion the spritesDeAnimacion to set
	 */
	public void setSpritesDeAnimacion(List<BufferedImage> spritesDeAnimacion) {
		this.spritesDeAnimacion = spritesDeAnimacion;
	}
	
	public BufferedImage getSpriteActual() {
		return this.spriteActual;
	}

	/**
	 * @param img the img to set
	 */
	public void setSpriteActual(BufferedImage spriteActual) {
		this.spriteActual = spriteActual;
		this.ancho = this.spriteActual.getWidth();
		this.largo = this.spriteActual.getHeight();
	}

}