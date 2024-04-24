
package musicsongrecordsystem;

import java.util.ArrayList;
import java.util.Stack;


public class AVLTree<T> {

	Node root;

	public int height(Node N) {
		if (N == null)
			return 0;

		return N.height;
	}

	public int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public Node rightRotate(Node y) {
		Node x = y.left;
		Node T2 = x.right;

		x.right = y;
		y.left = T2;

		y.height = max(height(y.left), height(y.right)) + 1;
		x.height = max(height(x.left), height(x.right)) + 1;

		return x;
	}

	public Node leftRotate(Node x) {
		Node y = x.right;
		Node T2 = y.left;

		y.left = x;
		x.right = T2;

		x.height = max(height(x.left), height(x.right)) + 1;
		y.height = max(height(y.left), height(y.right)) + 1;

		return y;
	}

	public int getBalance(Node N) {
		if (N == null)
			return 0;

		return height(N.left) - height(N.right);
	}

	public Node insertInteger(Node root,Node node) { // root=ağaç, node= eklediğimiz şarkı, data= array index

		if (root == null)
			return node;

		if ((int)node.key < (int)root.key)
			root.left = insertInteger(root.left, node);
		else if ((int)node.key > (int)root.key)
			root.right = insertInteger(root.right, node);
		else 
			return root;

		root.height = 1 + max(height(root.left), height(root.right));

		int balance = getBalance(root);


		if (balance > 1 && (int)node.key < (int)root.left.key)
			return rightRotate(root);

		// Right Right Case
		if (balance < -1 && (int)node.key > (int)root.right.key)
			return leftRotate(root);

		// Left Right Case
		if (balance > 1 && (int)node.key > (int)root.left.key) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Left Case
		if (balance < -1 && (int)node.key < (int)root.right.key) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
        }
        
        public Node insertString(Node root,Node node) { // root=ağaç, node= eklediğimiz şarkı, data= array index
                
                String key1=null;
                String key2 = null;
                if(root!=null){
                key1 = (String)node.key;
                key2 = (String)root.key;
                }
                
		if (root == null)
			return node;
		if (key1.compareTo(key2) < 0)
			root.left = insertString(root.left, node);
		else if (key1.compareTo(key2) > 0)
			root.right = insertString(root.right, node);
		else 
			return root;

		root.height = 1 + max(height(root.left), height(root.right));

		int balance = getBalance(root);


		if (balance > 1 && key1.compareTo(key2) < 0)
			return rightRotate(root);

		// Right Right Case
		if (balance < -1 && key1.compareTo(key2) > 0)
			return leftRotate(root);

		// Left Right Case
		if (balance > 1 && key1.compareTo(key2) > 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Left Case
		if (balance < -1 && key1.compareTo(key2) < 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
        }
       
	public void postOrder(Node node) {
		if (node != null) {
			
			postOrder(node.left);
			postOrder(node.right);
                        System.out.println(node.key );
                        System.out.println(node.data);
                        System.out.println("");
                        
		}
	}
        
        public Node generate (Song[] song, AVLTree tree, int key){//ağaç oluşturan method
        
            if(key == 1){
            for (int i = 0; i < song.length; i++) {
                tree.root = tree.insertString(tree.root, new Node(i, song[i].getName()));//name tree
                }
            }else if(key == 2){
                for (int i = 0; i < song.length; i++) {
                tree.root= tree.insertInteger(tree.root, new Node(i, song[i].getId()));// Id tree
                }
            }else{
                for (int i = 0; i < song.length; i++) {
                tree.root = tree.insertString(tree.root, new Node(i, song[i].getArtist()));//Artist Tree
                }
            }
                return tree.root;
        }
        
        /*public Node<T> search1(Node root, T value){//ilk bulduğunu yazdıran
            if( root==null ){
                return null;
            }else if( root.key.equals(value) ){
                return root;
            }
            Node s=search1(root.right,value);
            Node s2=search1(root.left,value);
            if( s!=null ) return s;
            return s2;
        }*/
        
        public Node<T> searchString(Node root, T value){//ilk bulduğunu yazdıran
                String key1=null;
                String key2 = null;
                if(root!=null){
                key1 = (String)value;
                key2 = (String)root.key;
                }
            
            if (root == null) {
            return null;
            }
            if (key1.compareTo(key2) == 0) //found return the node
            {
                return root;
            } else if (0 > key1.compareTo(key2)) { //check which side to go
                return searchString(root.left, (T)key1);
            } else {
                return searchString(root.right, (T)key1);
            }
        }
        
        public Node<T> searchInteger(Node root, int value){//ilk bulduğunu yazdıran
                
            if (root == null) {
            return null;
        }
        if ((int)root.key == value) //found return the node
        {
            return root;
        } else if (value < (int)root.key) { //check which side to go
            return searchInteger(root.left, value);
        } else {
            return searchInteger(root.right, value);
        }
        }
        
        
        public Node<T> deleteSearch(Node root, int value){//ilk bulduğunu yazdıran
            if( root==null ){
                return null;
            }else if( root.key.equals(value) ){
                return root;
            }
            Node s=searchInteger(root.right,value);
            Node s2=searchInteger(root.left,value);
            if( s!=null ) return s;
            return s2;
        }
        
        /*public ArrayList search2(Node root, T value){//Tüm bulduklarını depolayıp returneleyen
            if (root == null)
                return new ArrayList();
            ArrayList<Node> result = new ArrayList<>();
            Stack<Node> stack = new Stack();
            Node current = root;
            while (!stack.isEmpty() || current != null) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                    current = stack.pop();
                if(current.key.equals(value) ){
                result.add(current);
                }
                current = current.right;
            }
            return result;
    }*/
        
        public ArrayList search3(Node root, int a, int b){/*treedeki verilen aralıktaki 
                                                          tüm integer değerleri bulup depolamak için*/
            if (root == null)
                return new ArrayList();
            ArrayList<Node> result = new ArrayList<>();
            Stack<Node> stack = new Stack();
            Node current = root;
            while (!stack.isEmpty() || current != null) {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                    current = stack.pop();
                if((int)(current.key)>= a && ((int)current.key)<= b ){
                result.add(current);
                }
                
                current = current.right;
            }
            return result;
    }
        
        public Node<T> deleteId(Node focus, int key) {
            if (focus == null) {
                return focus;
            }
            if (key < (int)focus.key) {
                focus.left = deleteId(focus.left, key);
            } 
            else if (key > (int)focus.key) {
                focus.right = deleteId(focus.right, key);
            } 
            else {

                if ((focus.left == null) || (focus.right == null)) {
                    Node temp = null;
                    if (null == focus.left) {
                        temp = focus.right;
                    } else { 
                        temp = focus.left;
                    }

                    if (temp == null) {
                        temp = focus;
                        focus = null;
                    } else  
                    {
                        focus = temp;  }
                } else {

                    Node temp = minValueNode(focus.right);

                    focus.key = temp.key;

                    focus.right = deleteId(focus.right, temp.data);
                }
            }

            if (focus == null) {
                return focus;
            }

            focus.height = Math.max(height(focus.left), height(focus.right)) + 1;

            int balance = getBalance(focus);

            if (balance > 1 && getBalance(focus.left) >= 0) {
                return rightRotate(focus);
            }

            if (balance > 1 && getBalance(focus.left) < 0) {
                focus.left = leftRotate(focus.left);
                return rightRotate(focus);
            }

            if (balance < -1 && getBalance(focus.right) <= 0) {
                return leftRotate(focus);
            }

            if (balance < -1 && getBalance(focus.right) > 0) {
                focus.right = rightRotate(focus.right);
                return leftRotate(focus);
            }

            return focus;
    }
        
        public Node<T> deleteString(Node focus, String key) {
            if (focus == null) {
                return focus;
            }
            if (key.compareTo((String)focus.key) < 0) {
                focus.left = deleteString(focus.left, key);
            } 
            else if (key.compareTo((String)focus.key) > 0) {
                focus.right = deleteString(focus.right, key);
            } 
            else {

                if ((focus.left == null) || (focus.right == null)) {
                    Node temp = null;
                    if (null == focus.left) {
                        temp = focus.right;
                    } else { 
                        temp = focus.left;
                    }

                    if (temp == null) {
                        temp = focus;
                        focus = null;
                    } else  
                    {
                        focus = temp;  }
                } else {

                    Node temp = minValueNode(focus.right);

                    focus.key = temp.key;

                    focus.right = deleteString(focus.right, (String)temp.key);
                }
            }

            if (focus == null) {
                return focus;
            }

            focus.height = Math.max(height(focus.left), height(focus.right)) + 1;

            int balance = getBalance(focus);

            if (balance > 1 && getBalance(focus.left) >= 0) {
                return rightRotate(focus);
            }

            if (balance > 1 && getBalance(focus.left) < 0) {
                focus.left = leftRotate(focus.left);
                return rightRotate(focus);
            }

            if (balance < -1 && getBalance(focus.right) <= 0) {
                return leftRotate(focus);
            }

            if (balance < -1 && getBalance(focus.right) > 0) {
                focus.right = rightRotate(focus.right);
                return leftRotate(focus);
            }

            return focus;
    }
        
        public Node minValueNode(Node node) {
            Node current = node;
            while (current.left != null) {
                current = current.left;
            }

            return current;
    }
}
