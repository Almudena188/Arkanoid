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
		int implementadorY = 18;
		int implementadorX = 1;

		for (int i = 0; i < 54; i++) {

			if (i == 9 || i == 18 || i == 27 || i == 36 || i == 45) {
				implementadorY = implementadorY + 22;
				implementadorX = 1;
			}

			Ladrillo lad = new Ladrillo(implementadorX, implementadorY, 50, 20);

			if (i <= 7) {
				lad.setColor(Color.red);
			}
			if (i > 8 && i <= 17) {
				lad.setColor(Color.yellow);
			}
			if (i > 17 && i <= 26) {
				lad.setColor(Color.pink);
			}
			if (i > 26 && i <= 35) {
				lad.setColor(Color.cyan);
			}
			if (i > 35 && i <= 44) {
				lad.setColor(Color.green);
			}
			if (i > 44) {
				lad.setColor(Color.orange);
			}
			actores.add(lad);
			implementadorX = implementadorX + 54;

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
