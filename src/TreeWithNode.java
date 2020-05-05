import java.util.ArrayList;

public class TreeWithNode{
	private TreeNode root;

	public TreeWithNode(){
		this.root = null;
	}

	/*La complejidad temporal de "getRoot()" es O(1) ya que guardamos
	el nodo raíz en la variable "root" y no debemos recorrer nada
	para acceder a ella*/
	public int getRoot(){
		return this.root.getValue();
	}

	/*La complejidad temporal de "hasElem()" es O(h), donde 'h'
	es la altura del árbol+1(el '+1' es porque la altura del árbol no 
	incluye la raíz pero hay que contarla porque cuenta como una entrada mas)
	porque en el peor de los casos el elemento que estoy buscando se 
	encontrara en el nivel mas bajo del árbol*/
	public boolean hasElem(int valor){
		boolean elemEsta = false;
		if(this.root != null){
			elemEsta = hasElem(this.root, valor);
		}
		return elemEsta;
	}

	private boolean hasElem(TreeNode nodoActual, int valor){
		boolean elemEsta = false;
		if(valor == nodoActual.getValue()){
			elemEsta = true;
		}
		else if(valor < nodoActual.getValue()){
			if(nodoActual.getLeft() != null){
				elemEsta = hasElem(nodoActual.getLeft(), valor);
			}
		}
		else{
			if(nodoActual.getRight() != null){
				elemEsta = hasElem(nodoActual.getRight(), valor);
			}
		}
		return elemEsta;
	}

	/*La complejidad temporal de "insert()" es O(h), donde 'h'
	es la altura del árbol+1(el '+1' es porque la altura del árbol no 
	incluye la raíz pero hay que contarla porque cuenta como una entrada mas)
	porque en el peor de los casos el elemento que estoy intentando insertar
	tendra que ir al final de la rama mas larga del árbol*/
	public void insert(int value){
		if(this.root == null){
			this.root = new TreeNode(value);
		}
		else{
			this.insert(this.root, value);
		}
	}

	private void insert(TreeNode actual, int value){
		if(actual.getValue() > value){
			if(actual.getLeft() == null){
				TreeNode temp = new TreeNode(value);
				actual.setLeft(temp);
			}
			else{
				insert(actual.getLeft(), value);
			}
		}
		else if(actual.getValue() < value){
			if(actual.getRight() == null){
				TreeNode temp = new TreeNode(value);
				actual.setRight(temp);
			}
			else{
				insert(actual.getRight(), value);
			}
		}
	}

	/*La complejidad temporal de "isEmpty()" es O(1) ya que lo unico
	que debemos hacer es ver si el nodo raíz que guardamos en la
	variable "root" es distinto de "null", lo cual supone una unica entrada*/
	public boolean isEmpty(){
		return (root == null);
	}

	/*La complejidad temporal de "delete()" es O(h), donde 'h'
	es la altura del árbol+1(el '+1' es porque la altura del árbol no 
	incluye la raíz pero hay que contarla porque cuenta como una entrada mas)
	porque en el peor de los casos el elemento que estoy intentando borrar
	se encontrará al final de la rama mas larga del árbol*/
	public boolean delete(int elemABorrar){
		boolean fueBorrado = false;
		if(this.root != null){
			if(elemABorrar == root.getValue()){
				fueBorrado = deleteRoot();
			}
			else{
				fueBorrado = findItemToDelete(this.root, elemABorrar);
			}
		}
		return fueBorrado;
	}

	private boolean findItemToDelete(TreeNode nodoActual, int elemABorrar){
		boolean fueBorrado = false;
		if((elemABorrar < nodoActual.getValue()) && (nodoActual.getLeft() != null)){
			if(elemABorrar == nodoActual.getLeft().getValue()){
				if((nodoActual.getLeft().getLeft() == null) && (nodoActual.getLeft().getRight() == null)){
					nodoActual.setLeft(null);
				}
				else if((nodoActual.getLeft().getLeft() != null) && (nodoActual.getLeft().getRight() == null)){
					nodoActual.setLeft(nodoActual.getLeft().getLeft());
				}
				else if((nodoActual.getLeft().getLeft() == null) && (nodoActual.getLeft().getRight() != null)){
					nodoActual.setLeft(nodoActual.getLeft().getRight());
				}
				else{
					int valorNMISAD = getLeftmostElement(nodoActual.getLeft().getRight());
					findItemToDelete(nodoActual, valorNMISAD);
					nodoActual.getLeft().setValue(valorNMISAD);
				}
				fueBorrado = true;
			}
			else{
				fueBorrado = findItemToDelete(nodoActual.getLeft(), elemABorrar);
			}
		}
		else if((elemABorrar > nodoActual.getValue()) && (nodoActual.getRight() != null)){
			if(elemABorrar == nodoActual.getRight().getValue()){
				if((nodoActual.getRight().getLeft() == null) && (nodoActual.getRight().getRight() == null)){
					nodoActual.setRight(null);
				}
				else if((nodoActual.getRight().getLeft() != null) && (nodoActual.getRight().getRight() == null)){
					nodoActual.setRight(nodoActual.getRight().getLeft());
				}
				else if((nodoActual.getRight().getLeft() == null) && (nodoActual.getRight().getRight() != null)){
					nodoActual.setRight(nodoActual.getRight().getRight());
				}
				else{
					int valorNMISAD = getLeftmostElement(nodoActual.getRight().getRight());
					findItemToDelete(nodoActual, valorNMISAD);
					nodoActual.getRight().setValue(valorNMISAD);
				}
				fueBorrado = true;
			}
			else{
				fueBorrado = findItemToDelete(nodoActual.getRight(), elemABorrar);
			}
		}
		return fueBorrado;
	}

	private boolean deleteRoot(){
		boolean fueBorrado = true;
		if((this.root.getLeft() == null) && (this.root.getRight() == null)){
			this.root = null;
		}
		else if((this.root.getLeft() != null) && (this.root.getRight() == null)){
			this.root = this.root.getLeft();
		}
		else if((this.root.getLeft() == null) && (this.root.getRight() != null)){
			this.root = this.root.getRight();
		}
		else{
			int valorNMISAD = getLeftmostElement(root.getRight());
			findItemToDelete(this.root, valorNMISAD);
			this.root.setValue(valorNMISAD);
		}
		return fueBorrado;
	}

	/*La complejidad temporal de "getLeftmostElement()" es O(h), donde 'h'
	es la altura del árbol+1(el '+1' es porque la altura del árbol no 
	incluye la raíz pero hay que contarla porque cuenta como una entrada mas)
	porque en el peor caso el elemento mas izquierdo del árbol se encontrará
	al final de la rama mas larga y partirá a buscarlo desde la raíz*/
	private int getLeftmostElement(TreeNode nodoActual){
		int elemMasIzquierdo;
		if(nodoActual.getLeft() != null){
			elemMasIzquierdo = getLeftmostElement(nodoActual.getLeft());
		}
		else{
			elemMasIzquierdo = nodoActual.getValue();
		}
		return elemMasIzquierdo;
	}

	/*La complejidad temporal de "getMaxElem()" es O(h), donde 'h'
	es la altura del árbol+1(el '+1' es porque la altura del árbol no 
	incluye la raíz pero hay que contarla porque cuenta como una entrada mas)
	porque en el peor caso el elemento mas derecho del árbol se encontrará
	al final de la rama mas larga y partirá a buscarlo desde la raíz*/
	public int getMaxElem(){
		int valorNMI = 0;
		if(this.root != null){
			valorNMI = getMaxElem(this.root);
		}
		return valorNMI;
	}

	private int getMaxElem(TreeNode nodoActual){
		int elemMasDerecho;
		if(nodoActual.getRight() != null){
			elemMasDerecho = getMaxElem(nodoActual.getRight());
		}
		else{
			elemMasDerecho = nodoActual.getValue();
		}
		return elemMasDerecho;
	}

	/*La complejidad temporal de "printPreorder()" es O(n), donde 'n'
	es la cantidad de nodos que tiene el árbol, ya que debera pasar
	por todos y cada uno de ellos*/
	public void printPreorder(){
		if(this.root != null){
			printPreorder(this.root);
			System.out.println();
		}
	}

	private void printPreorder(TreeNode nodoActual){
		System.out.print("| " + nodoActual.getValue() + " |");
		if(nodoActual.getLeft() != null){
			printPreorder(nodoActual.getLeft());
		}
		else{
			System.out.print("| - |" );
		}
		if(nodoActual.getRight() != null){
			printPreorder(nodoActual.getRight());
		}
		else{
			System.out.print("| - |" );
		}
	}

	/*La complejidad temporal de "printPostorder()" es O(n), donde 'n'
	es la cantidad de nodos que tiene el árbol, ya que debera pasar
	por todos y cada uno de ellos*/
	public void printPostorder(){
		if(this.root != null){
			printPostorder(this.root);
			System.out.println();
		}
	}

	private void printPostorder(TreeNode nodoActual){
		if(nodoActual.getLeft() != null){
			printPostorder(nodoActual.getLeft());
		}
		else{
			System.out.print("| - |" );
		}
		if(nodoActual.getRight() != null){
			printPostorder(nodoActual.getRight());
		}
		else{
			System.out.print("| - |" );
		}
		System.out.print("| " + nodoActual.getValue() + " |");
	}

	/*La complejidad temporal de "printInorder()" es O(n), donde 'n'
	es la cantidad de nodos que tiene el árbol, ya que debera pasar
	por todos y cada uno de ellos*/
	public void printInorder(){
		if(this.root != null){
			printInorder(this.root);
			System.out.println();
		}
	}

	private void printInorder(TreeNode nodoActual){
		if(nodoActual.getLeft() != null){
			printInorder(nodoActual.getLeft());
		}
		else{
			System.out.print("| - |" );
		}
		System.out.print("| " + nodoActual.getValue() + " |");
		if(nodoActual.getRight() != null){
			printInorder(nodoActual.getRight());
		}
		else{
			System.out.print("| - |" );
		}
	}

	/*La complejidad temporal de "getHeight()" es O(n), donde 'n'
	es la cantidad de nodos que posee el árbol, ya que para saber
	cual es la altura del árbol deberá encontrar la rama mas larga pasando
	por todos los nodos del árbol*/
	public int getHeight(){
		if(this.root != null){
			return getHeight(this.root, 0);
		}
		else{
			return -1;
		}
	}

	private int getHeight(TreeNode nodoActual, int altura){
		int alturaIzq = altura;
		int alturaDer = altura;
		if(nodoActual.getLeft() != null){
			alturaIzq = getHeight(nodoActual.getLeft(), altura+1);
		}
		if(nodoActual.getRight() != null){
			alturaDer = getHeight(nodoActual.getRight(), altura+1);
		}
		if(alturaIzq >= alturaDer){
			altura = alturaIzq;
		}
		else{
			altura = alturaDer;
		}
		return altura;
	}

	/*La complejidad temporal de "getLongestBranch() es de O(n), donde
	'n' es la cantidad de nodos que tiene el árbol, porque para encontrar
	la rama mas larga tendrá que pasar por todas las ramas y así pasará por
	todos los nodos del arbol"*/
	public ArrayList<Integer> getLongestBranch(){
		if(this.root != null){
			ArrayList<Integer> LongestBranch = getLongestBranch(this.root);
			LongestBranch.add(this.root.getValue());
			return LongestBranch;
		}
		else{
			return new ArrayList<Integer>();
		}
	}

	private ArrayList<Integer> getLongestBranch(TreeNode nodoActual){
		ArrayList<Integer> BranchIzq = new ArrayList<Integer>();
		ArrayList<Integer> BranchDer = new ArrayList<Integer>();
		if(nodoActual.getLeft() != null){
			BranchIzq.addAll(getLongestBranch(nodoActual.getLeft()));
			BranchIzq.add(nodoActual.getLeft().getValue());
		}
		if(nodoActual.getRight() != null){
			BranchDer.addAll(getLongestBranch(nodoActual.getRight()));
			BranchDer.add(nodoActual.getRight().getValue());
		}
		if(BranchIzq.size() >= BranchDer.size()){
			return BranchIzq;
		}
		else{
			return BranchDer;
		}
	}

	/*La complejidad temporal de "getFrontera()" es O(n), donde 'n' es
	la cantidad de nodos que tiene el arbol, porque para alcanzar todas
	las hojas del arbol debe pasar por todos los nodos internos y así
	recorrerá el árbol entero*/
	public ArrayList<Integer> getFrontera(){
		ArrayList<Integer> HojasDelArbol = new ArrayList<Integer>();
		if(this.root != null){
			getFrontera(HojasDelArbol, this.root);
		}
		return HojasDelArbol;
	}

	private void getFrontera(ArrayList<Integer> HojasDelArbol, TreeNode nodoActual){
		if(nodoActual.getLeft() != null){
			getFrontera(HojasDelArbol, nodoActual.getLeft());
		}
		if(nodoActual.getRight() != null){
			getFrontera(HojasDelArbol, nodoActual.getRight());
		}
		if((nodoActual.getLeft() == null) && (nodoActual.getRight() == null)){
			HojasDelArbol.add(nodoActual.getValue());
		}
	}

	/*La complejidad temporal de "getElemAtLevel()" es O(n), donde 'n' representa
	todos los nodos que hay en cada rama del árbol hasta llegar al nivel indicado,
	ya que deberá pasar por cada uno de estos para alcanzar los nodos del nivel
	que se quería*/
	public ArrayList<Integer> getElemAtLevel(int nivelDeseado){
		ArrayList<Integer> ElementosDelNivel = new ArrayList<Integer>();
		if(this.root != null){
			getElemAtLevel(ElementosDelNivel, this.root, nivelDeseado, 0);
		}
		return ElementosDelNivel;
	}

	private void getElemAtLevel(ArrayList<Integer> ElementosDelNivel, TreeNode nodoActual, int nivelDeseado, int nivelActual){
		if((nodoActual.getLeft() != null) && (nivelActual < nivelDeseado)){
			getElemAtLevel(ElementosDelNivel, nodoActual.getLeft(), nivelDeseado, nivelActual+1);
		}
		if((nodoActual.getRight() != null) && (nivelActual < nivelDeseado)){
			getElemAtLevel(ElementosDelNivel, nodoActual.getRight(), nivelDeseado, nivelActual+1);
		}
		if(nivelDeseado == nivelActual){
			ElementosDelNivel.add(nodoActual.getValue());
		}
	}
}