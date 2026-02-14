package CP317;

public class Node<T> {
	T item; //data to be stored
	Node<T> right, left, up, down; //cardinal directions for traversal through the linked list
	 
	//constructor for a new node 
    //requires data as the data in the node cannot be changed after the node is created 
	public Node(T data) {
		this.item = data;
		this.right = null;
		this.left = null;
		this.up = null;
		this.down = null;
	}
	
    //gets the data in the node
	public T getItem() {
		return this.item;
	}
	//gets the node to the right of the current node 
	public Node<T> getRight(){
		return this.right;
	}
	//sets the node to the right of the current node
	public void setRight(Node<T> new_node) {
		this.right = new_node;
	}
    //gets the node to the left of the current node 
	public Node<T> getLeft(){
		return this.left;
	}
	//sets the node to the left of the current node
	public void setLeft(Node<T> new_node) {
		this.left = new_node;
	}
    //gets the node down from the current node
	public Node<T> getDown(){
		return this.down;
	}
	//sets the node down from current node
	public void setDown(Node<T> new_node) {
		this.down = new_node;
	}
    //gets the node up from the current node
	public Node<T> getUp(){
		return this.up;
	}
	//sets the node up from current node
	public void setUp(Node<T> new_node) {
		this.up = new_node;
	}
}