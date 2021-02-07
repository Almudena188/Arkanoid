package version1;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;



public class Explosion extends Actor{

	public Explosion(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		// Carga de los sprites de la explosi�n
		List<BufferedImage> nuevosSprites = new ArrayList<BufferedImage>();
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion1.png"));
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion2.png"));
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion3.png"));
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion4.png"));
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion5.png"));
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion6.png"));
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion7.png"));
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion8.png"));
		nuevosSprites.add(RecursosCache.getInstance().getImagen("sprite-explosion9.png"));
		this.setSpritesDeAnimacion(nuevosSprites);
		// Sprite actual
		this.spriteActual = this.getSpritesDeAnimacion().get(0);
		// Velocidad de cambio de sprite
		this.velocidadDeCambioDeSprite = 5;
	}
	
	@Override
	public void mueve() {
		super.mueve();
		if (this.spriteActual.equals(this.spritesDeAnimacion.get(this.spritesDeAnimacion.size()-1))) {
			Ventana.getInstance().eliminaActor(this);
		}
	}
	
}
