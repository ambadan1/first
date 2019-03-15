package com.directorystructure;
/**
 * Created by pratiksha.rahangdale on 8/9/2016.
 */
import java.io.IOException;
import java.util.*;

public class DirectoryStructure
{
    public ArrayList<ArrayList<Directory>> allchilds = new ArrayList<>();

    public enum Classification {
        PUBLIC("Public"),
        TOP_SECRET("Top secret"),
        SECRET("Secret");

        private final String displayString;

        Classification(String displayString) {
            this.displayString = displayString;
        }

        public String classificationName() {
            return this.displayString;
        }
    }

    public static void main(String[] args) throws IOException {
        DirectoryStructure directoryStructureMain = new DirectoryStructure();
        Functionalities functionalities = new Functionalities();
        boolean start = true;

        do {
            System.out.println("1. List of Top Secret records");
            System.out.println("2. List of Secret records");
            System.out.println("3. List of ToP Secret or Secret records");
            System.out.println("4. Size of Public records");
            System.out.println("5. List of Non Public files of folder 11");
            System.out.println("6. Directory Structure");
            System.out.println("Enter Your option");
            Scanner scanner = new Scanner(System.in);
            int option = scanner.nextInt();
            switch (option)
            {
                case 1:
                    System.out.println("List of Top Secret records: ");
                    functionalities.displayRecords(functionalities.getClassification(Classification.TOP_SECRET.classificationName()));
                    System.out.println("");
                    break;

                case 2:
                    System.out.println("List of Secret records: ");
                    functionalities.displayRecords(functionalities.getClassification(Classification.SECRET.classificationName()));
                    System.out.println("");
                    break;

                case 3:
                    System.out.println("List of ToP Secret or Secret records: ");
                    functionalities.displayRecords(functionalities.getClassification(Classification.SECRET.classificationName(), Classification.TOP_SECRET.classificationName()));
                    System.out.println("");
                    break;

                case 4:
                    System.out.println("Size of Public records: " + functionalities.sizeOfPublicClassification(functionalities.getClassification(Classification.PUBLIC.classificationName())));
                    System.out.println("");
                    break;


                case 5:
                    String foldername = "Folder11";
                    System.out.println("List of Non Public files of folder 11: ");
                    functionalities.displayRecords(functionalities.getNonPublicFiles(foldername));
                    System.out.println("");
                    break;

                case 6:
                    Directory root = functionalities.findRoot();
                    if (root != null)
                    {
                        functionalities.getFolderSize(root);
                        ArrayList<Directory> rootDirectory = new ArrayList<>();
                        rootDirectory.add(root);
                        directoryStructureMain.allchilds.add(rootDirectory);
                        System.out.println("Directory Structure:");
                        directoryStructureMain.allchilds = functionalities.treeStructure(root.getName(), -1);
                    }
                    break;
                default:
                    System.out.println("This option is incorrect.Please enter correct option");
                    break;
            }
          System.out.println("\n");
        }while (start);
    }
}