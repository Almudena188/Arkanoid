package version1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



public class Principal {

	public static void main(String[] args) {
		RecursosCache.getInstance().cargarRecursosEnMemoria();
		Ventana.getInstance().movimiento();

	}

}
