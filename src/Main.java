import java.util.ArrayList;

public class Main{
	public static void main(String[]args){
		TreeWithNode arbol = new TreeWithNode();
		System.out.println(arbol.isEmpty());//Compruba si el arbol está vacío, es este caso sí lo está.
		arbol.insert(51);//Desde acá
		arbol.insert(27);
		arbol.insert(42);
		arbol.insert(14);
		arbol.insert(63);
		arbol.insert(67);
		arbol.insert(89);
		arbol.insert(58);
		arbol.insert(92);
		arbol.insert(59);
		arbol.insert(53);
		arbol.insert(55);
		arbol.insert(60);
		arbol.insert(62);
		arbol.insert(61);
		arbol.insert(47);
		arbol.insert(33);//hasta acá, se insertan todos los valores que habrá en el árbol.
		System.out.println(arbol.isEmpty());//Compruba si el arbol está vacío, es este caso no lo está.
		System.out.println(arbol.getHeight());//Descubre la altura actual del arbol.
		System.out.println(arbol.getRoot());//Toma el valor de la raíz.
		System.out.println(arbol.getMaxElem());//Toma el valor del nodo mas derecho, es decir el valor mas alto del árbol.
		System.out.println(arbol.hasElem(55));//Comprueba si el valor está en el árbol, en este caso sí está.
		System.out.println(arbol.hasElem(71));//Comprueba si el valor está en el árbol, en este caso no está.
		arbol.printInorder();
		ArrayList<Integer> ramaMasLarga = arbol.getLongestBranch();//Copia los valores de la rama mas larga del árbol
		for(int i = ramaMasLarga.size()-1; i >= 0; i--){//y acá los muestra desde la
			System.out.print("| " + ramaMasLarga.get(i) + " |");//raíz hasta la hoja que la compone.
			if(i == 0){
				System.out.println();
			}
		}
		ArrayList<Integer> hojasDelArbol = arbol.getFrontera();//Copia los valores de las hojas del árbol
		for(int i = 0; i < hojasDelArbol.size(); i++){//y los muestra todos.
			System.out.print("| " + hojasDelArbol.get(i) + " |");
			if(i == hojasDelArbol.size()-1){
				System.out.println();
			}
		}
		ArrayList<Integer> NivelDeseado = arbol.getElemAtLevel(3);//Copia los valores de los elementos que se encuentran en el nivel especificado
		for(int i = 0; i < NivelDeseado.size(); i++){//y los muestra todos.
			System.out.print("| " + NivelDeseado.get(i) + " |");
			if(i == NivelDeseado.size()-1){
				System.out.println();
			}
		}
		arbol.printPreorder();//Imprime el árbol, puede observar que se encuentra el valor 61
		System.out.println(arbol.delete(61));//Borra correctamente un nodo hoja.
		arbol.printPreorder();//y luego del borrado el elemento ya no está.
		arbol.printPostorder();//Imprime el árbol, puede observar que se encuentra el valor 59
		System.out.println(arbol.delete(59));//Borra correctamente un nodo con un solo hijo.
		arbol.printPostorder();//y luego del borrado el elemento ya no está, además se acomodaron los nodos que le seguían
		arbol.printInorder();//Imprime el árbol, puede observar que se encuentra el valor 58
		System.out.println(arbol.delete(58));//Borra correctamente un nodo con dos hijos.
		arbol.printInorder();//y luego del borrado el elemento ya no está, además se acomodaron los nodos que le seguían y se mantiene la estructura
		//generarArbolBBAleatorio(15, 1, 40);//Genera un arbolBB con valores aleatorios y los muestra en consola a medida que los genera.
	}

	public static TreeWithNode generarArbolBBAleatorio(int nodosDeseados, int valorMinimo, int valorMaximo){
		TreeWithNode ArbolAleatorio = new TreeWithNode();
		int nodosIngresados = 0;
		while(nodosIngresados < nodosDeseados){
			int valorAleatorio = ((int)(Math.random()*(valorMaximo-valorMinimo + 1) + valorMinimo));
			if(!ArbolAleatorio.hasElem(valorAleatorio)){
				ArbolAleatorio.insert(valorAleatorio);
				nodosIngresados++;
				System.out.print("| " + valorAleatorio + " |");
				if(nodosIngresados == nodosDeseados){
					System.out.println();
				}
			}
		}
		return ArbolAleatorio;
	}
}