package version1;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class RecursosCache {

	//Propiedades estáticas de esta clase
	
	public static String IMAGEN_PLAYER = "nave.gif";
	public static String IMAGEN_PELOTA = "pelotaGato.gif";
	public static String IMAGEN_LADRILLO = "ladrillo.gif";
	private HashMap<String, Object> hmRecursos = new HashMap<String, Object>();
	//private HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	private static RecursosCache instance = null;
	
	
	private String nombreCarpetaParaFile = "./src/version1/resources/";
	private String nombreCarpetaParaURL = "resources/";

	public static RecursosCache getInstance () {
		if (instance == null) {
			instance = new RecursosCache();
		}
		return instance;
	}
	
	public void cargarRecursosEnMemoria () {
		File carpeta = new File(nombreCarpetaParaFile);
		for (File fichero : carpeta.listFiles()) {
	        if (fichero.isFile()) {
	        	cargarFicheroEnHashMap(fichero.getName());
	        }
	    }
	}
	
	private void cargarFicheroEnHashMap (String nombreFichero) {
		// Obtengo un objeto URL para localizar el recurso
		URL url = null;
		url = getClass().getResource(nombreCarpetaParaURL + nombreFichero);
		// Discriminará el caso de que intento cargar un sonido del caso de cargar imágenes
		try {
			if (nombreFichero.endsWith(".wav") || nombreFichero.endsWith(".mp3")) {
				this.hmRecursos.put(nombreFichero, Applet.newAudioClip(url));
			} 
			else { // Si no es un sonido entiendo que se trata de una imagen
				this.hmRecursos.put(nombreFichero, ImageIO.read(url));
			}
		}
		catch (Exception ex) {
			System.out.println("No se pudo cargar el recurso " + nombreFichero);
			ex.printStackTrace();
		}
	}
	
	/**
	 * Mediante este método casteamos a imagen el recurso que nos proporciona el supertipo
	 * @param name
	 * @return
	 */
	public BufferedImage getImagen(String nombreFichero) {
		return (BufferedImage) hmRecursos.get(nombreFichero);
	}

	
	/**
	 * Ejecuta un archivo de sonido de forma aislada
	 * @param name
	 */
	public void playSonido(String nombreFichero) {
		((AudioClip)hmRecursos.get(nombreFichero)).play();
	}
	
	/**
	 * Reproduce un archivo de sonido en bucle
	 * @param name
	 */
	public void loopSonido(final String nombreFichero) {
		((AudioClip)hmRecursos.get(nombreFichero)).loop();
	}
	
//	private BufferedImage cargarImagen(String nombre) {
//		
//		URL url = null;
//		try {
//			url = getClass().getResource(nombre);
//			return ImageIO.read(url);
//		} catch (Exception e) { // algo ha fallado, se acaba el programa si no podemos cargar alguna imagen
//			e.printStackTrace();
//			System.exit(0);
//		}
//		return null;
//	}
	
//	public BufferedImage getImagen(String nombre) {
//		BufferedImage img = sprites.get(nombre);
//		if (img == null) {
//			img = cargarImagen("resources/" + nombre);
//			sprites.put(nombre,img);
//		}
//		return img;
//	}
	
	
	
}
