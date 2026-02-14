package CP317;

public class LinkedList2d<T> {
    protected Node<T> head; //initial node for traversal
    protected int rows, columns; //number of rows and columns in the list

    public LinkedList2d(){ //constructor for initialization
        head = null;
        rows = 0;
        columns = 0;
    }
    //for adding the data to a row
    public void addRow(T[] row_data) {
        //new row start for linking without error, prev for linking with previous entry
        Node<T> new_row_start = null;
        Node<T> prev = null;
        //is first is used due to an error that would exist in the up and down linking without it
        //The program would link items down that were on the first line
        boolean isFirst = false;

        //loop to iterate through all parts of the array of data
        for (int i = 0; i < row_data.length; i++) {

            Node<T> new_node = new Node<>(row_data[i]);//new node initialization

            if (new_row_start == null) {//checks if is the first in a new row
                new_row_start = new_node;
            }

            if (prev != null) { //checks if there is appropriate data to link the current node
                prev.setRight(new_node);
                new_node.setLeft(prev);
            }

            if (head == null) {
                head = new_row_start; //set the head for the first row
                isFirst = true; //makes sure there is no improper linking
            } 
            else if(isFirst != true){ //here to stop improper linking
                //gets the node above to link the structure
                Node<T> above = getNodeAt(this.rows - 1, i);
                if (above != null) {//links the structure
                    above.setDown(new_node);
                    new_node.setUp(above);
                }   
            }

            prev = new_node; //moves to the next node
        }
        //increments row count and checks if columns needs to be incremented due to the dynamic allocation of the array
        this.rows++;
        this.columns = Math.max(this.columns, row_data.length);
    }

    //private helper method to get the node at any point in the list, useful for finding a node without needing to keep the value stored
    private Node<T> getNodeAt(int row, int column){
        Node<T> current = head;//start at the first node

        for (int i = 0; i < row && current != null; i++){//traverse down the rows as needed
            current = current.getDown();
        }

        for (int j = 0; j < column && current != null; j++){//traverse to the right through the columns as needed
            current = current.getRight();
        }
        return current;
    }

    //public method for getting data from protected nodes
    //uses private method get item for ease of implementation
    public T getItemAt(int row, int column){
        Node<T> data = this.getNodeAt(row, column);
        return data.getItem();
    }

    public int getRows(){//returns attribute rows, helpful for iteration
        return this.rows;
    }

    public int getColumns(){//returns attribute columns, also helpful for iteration
        return this.columns;
    }

    public void printStructure() { //print the structure of the Linked List for debugging purposes
        Node<T> rowHead = this.head;
        while (rowHead != null) {
            Node<T> current = rowHead;
            while (current != null) {
                System.out.print(current.getItem() + " ");
                current = current.getRight();
            }
            System.out.println();
            rowHead = rowHead.getDown();
        }
    }
}

