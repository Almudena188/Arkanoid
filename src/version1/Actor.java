package version1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public abstract class Actor {

	protected int x;
	protected int y;
	private int ancho;
	private int largo;
	protected BufferedImage img;

	public Actor() {

	}

	public Actor(int x, int y, int ancho, int largo,  BufferedImage img) {
		super();
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.largo = largo;
		this.setImg(img);
	}
	public void colisionaCon(Actor a) {
	}

	
	public abstract void mueve();

	
	public void paint(Graphics g) {
		g.drawImage(this.img, this.x, this.y, null);
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

}