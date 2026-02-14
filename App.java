package CP317;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.String;

public class App<T> {
    //copy what you want from the main, not super useful though
    //just used for debugging and an example
    public static void main(String[] args) throws Exception {
        String file1 = "C:/Users/Luke/Documents/Laurier/CP317/src/src/CourseFile.txt";
        String file2 = "C:/Users/Luke/Documents/Laurier/CP317/src/src/NameFile.txt";
        App<String> new_app = new App<String>();//needed for the call to the file reader otherwise it throws an error


        LinkedList2d<String> course_data = new_app.fileReaderFunction(file1);//file reader must be called through the app function
        LinkedList2d<String> student_data = new_app.fileReaderFunction(file2);

        System.out.println("Course Data:");
        for(int i = 0; i < course_data.getRows(); i++){
            for(int j = 0; j < course_data.getColumns(); j++){
                String item = course_data.getItemAt(i, j);
                String data = item.toString();
                System.out.print(data + ", ");
            }
            System.out.print("\n");
        }

        System.out.println("Student Data:");
        for(int i = 0; i < student_data.getRows(); i++){
            for(int j = 0; j < student_data.getColumns(); j++){
                String item = student_data.getItemAt(i, j);
                String data = item.toString();
                System.out.print(data + ", ");
            }
            System.out.print("\n");
        }
        System.exit(0);
    }
    //file reader function
    //requires a string with the full path
    public LinkedList2d<T> fileReaderFunction(String file_path){
        //initialize new list
        LinkedList2d<T> data_list = new LinkedList2d<T>();

        try{//to catch exceptions from the file opening
            File fh = new File(file_path);
            Scanner file_reader = new Scanner(fh);

            //loop through all input in the file
            while(file_reader.hasNextLine()){
                String data = file_reader.nextLine();
                String[] data_array = data.split(",");//splits along the commas so they're no longer there

                T[] row = parseToGeneric(data_array);//converts the string array to an array of generic objects

                data_list.addRow(row);//adds a row to the list
            }

            file_reader.close();
        }
        catch (FileNotFoundException e){
            System.err.println("Error: The filepathe at path'" + file_path + "' was not found.");//exits the program upon failure
            System.exit(1);
        }

        return data_list;
    }
    //converts a string to an item for the linked list
    private T[] parseToGeneric(String[] row){
        //apparently this is needed to declare an array of generic objects, not sure why
        T[] new_row = (T[]) java.lang.reflect.Array.newInstance(Comparable.class, row.length);

        //iterate through the input row and convert to generic objects for the input
        for (int i = 0; i < row.length; i++){
            new_row[i] = (T)row[i];
        }
        return new_row;
    }

    /*private T parseSingle(String item){
        try{
            if(Comparable.class.isAssignableFrom(Integer.class)){
                return (T)Integer.valueOf(item);
            }
            else if(Comparable.class.isAssignableFrom(String.class)){
                return (T) item;
            }
            else{
                throw new IllegalArgumentException("Illegal Data type");
            }
        }
        catch (ClassCastException e){
            System.err.println("Error; failure to cast to generic data type");
            System.exit(1);
        }
        return null;
    }*/
}
