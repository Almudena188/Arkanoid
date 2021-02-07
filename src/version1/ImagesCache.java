package version1;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImagesCache {

	//Propiedades estáticas de esta clase
	
	public static String IMAGEN_PLAYER = "nave.gif";
	public static String IMAGEN_PELOTA = "pelotaGato.gif";
	public static String IMAGEN_LADRILLO = "ladrillo.gif";
	
	private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	private static ImagesCache instance = null;
	
	public static ImagesCache getInstance () {
		if (instance == null) {
			instance = new ImagesCache();
		}
		return instance;
	}
	
	private BufferedImage cargarImagen(String nombre) {
		
		URL url = null;
		try {
			url = getClass().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) { // algo ha fallado, se acaba el programa si no podemos cargar alguna imagen
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	
	public BufferedImage getImagen(String nombre) {
		BufferedImage img = sprites.get(nombre);
		if (img == null) {
			img = cargarImagen("resources/" + nombre);
			sprites.put(nombre,img);
		}
		return img;
	}
}
