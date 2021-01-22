package version1;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MiCanvas extends Canvas {
//	Esta clase hace que se pinten y creen todos los elementos necesarios para el juego

	// creo la lista donde se van a guardar todos los elementos ( pelota, ladrillo y
	// nave) que son actores

	private static List<Actor> actores = new ArrayList<Actor>();

	public MiCanvas() {
		super();

	}

	/**
	 * creo todos los actores
	 * 
	 * @return
	 */

	public static List<Actor> creadorDeActores() {

		// creo la pelota

		Pelota pelota = new Pelota(251, 320, 15, 15);
		actores.add(pelota);

		// bucle para crear varios ladrillos

		int implementadorX = 0;
		for (int i = 0; i < 9; i++) {
			Ladrillo lad = new Ladrillo(implementadorX + 2, 2, 50, 20);
			implementadorX = implementadorX + 54;
			actores.add(lad);
		}

		// creo la nave
		Nave nave = new Nave(201, 525, 100, 10);
		actores.add(nave);

		return actores;
	}

	/**
	 * Metodo para pintar la lista de actores
	 */
	public void paint(Graphics g) {

//		pinto el fondo del panel
		this.setBackground(Color.black);

		// Pinto cada uno de los actores
		for (Actor a : this.actores) {
			a.paint(g);
		}

	}

}
