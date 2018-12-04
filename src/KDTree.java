
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Algoritmos y estructuras de datos 2018-2
 */

public class KDTree{
	public static class Node{
                    public Node left;
                    public Node right;
                    public double x;
                    public double y;
                    
                    public Node(double x, double y){
                        this.x = x;
                        this.y = y;
                    }
                    
                    public double compareTo(Node that, boolean axis){
                        if(axis)
                            return this.x - that.x;
                        else
                            return this.y - that.y;
                    }
                    
                    public void print(){
                        if(this.left  != null){
                            left.print();
                        }
                        System.out.println("x: " + x + " y: " + y);
                        if(this.right != null){
                            right.print();
                        }
                    }
		}
	
        public static class KdTree {
		private Node root;
                private static final boolean X_AXIS = true;
                //private static final boolean Y_AXIS = false;
                
		public void insert(double x, double y){
                    root = insert(root, new Node(x, y), X_AXIS);
		}
                
                private Node insert(Node root, Node node, boolean axis){
                    if(root == null) return node;
                    
                    double cmp = node.compareTo(root, axis);
                    if (cmp < 0) root.left  = insert(root.left , node, !axis);
                    if (cmp > 0) root.right = insert(root.right, node, !axis);
                    
                    return root;
                }

		/**
		 * Retorna el nodo mas cercano a las coordenadas dadas
		 * 
		 */
		public Node get(double x, double y){
			return null;
		}
                
                public void print(){
                    root.print();
                }

	}
	/**
	 * La implementacion debe recibir 2 archivos por la linea de argumentos
	 * donde el primero es el arbol a construir y el segundo es el archivo
	 * con consultas. Se deben imprimir los resultados de las consultas por
	 * por la salida estandar.
	 */
	public static void main(String args[]){
            System.out.println(args[0]);
            List<Double> values = loadFile(args[0]);
            KdTree tree = new KdTree();
            for(int i = 0; 2 * i < values.size(); i+=2){
                double x = values.get(i);
                double y = values.get(i+1);
                System.out.println("x: " + x + " y: " + y);
                tree.insert(x, y);
            }
            System.out.println("\n--------------Resultado--------------\n");
            tree.print();
	}
        
        private static List<Double> loadFile(String filePath){
            Scanner r = null;
            List<Double> values = new ArrayList<>(); 
            try{
                r = new Scanner(new FileInputStream(filePath));
            } catch (Exception e){
                System.err.println("Ha ocurrido un error en la carga del archivo");
            }
            
            if (r == null)
                return null;
            
            while(r.hasNext()){
                values.add(Double.parseDouble(r.next()));
            }
            
            return values;
        }
}
