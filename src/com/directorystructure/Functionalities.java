package com.directorystructure;
/**
 * Created by pratiksha.rahangdale on 8/9/2016.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Functionalities
{
    public List<Directory> directories = new ArrayList<>();
    public static String path = "C://assignment//";
    public static String filename = "directory-structure.csv";
    public Functionalities()
    {
        directories = parseCSVFile(path, filename);
    }
    public void displayRecords(List<Directory> specificRecords)
    {
        for (Directory directory : specificRecords)
        {
            System.out.println(directory.toString());
        }
    }


    List<Directory> getChildNodes(String name)
    {
        List<Directory> allChildNodes = new ArrayList<>();
        List<Directory> record = getByName(name);
        Directory directory = record.get(0);
        int id = directory.getId();
        List<Directory> childs = getByParentId(id);
        for (Directory child : childs)
        {
            allChildNodes.add(child);
        }
        for (int i = 0; i < allChildNodes.size(); i++)
        {
            int directoryId = allChildNodes.get(i).getId();
            childs.clear();
            if (allChildNodes.get(i).getType().equalsIgnoreCase("Directory"))
            {
                childs = getByParentId(directoryId);
                for (Directory child : childs)
                {
                    allChildNodes.add(child);
                }
            }
        }
        return allChildNodes;
    }
    public List<Directory> getByParentId(int id)
    {
        List<Directory> record = new ArrayList<Directory>();
        for (Directory directory : directories)
        {
            if (directory.getParentId() == id)
            {
                record.add(directory);

            }

        }
        return record;
    }
    List<Directory> getByName(String folderName)
    {
        List<Directory> folderNameDirectory = new ArrayList<Directory>();
        for (Directory directory : directories) {
            if (directory.getName().equalsIgnoreCase(folderName))
            {
                folderNameDirectory.add(directory);
            }
        }
        return folderNameDirectory;
    }
    public int sizeOfPublicClassification(List<Directory> classificationList)
    {
        int size = 0;
        for (Directory directory : classificationList)
        {
            size = size + directory.getSize();
        }
        return size;
    }

    public List<Directory> getClassification(String... values)
    {
        List<Directory> classificationSpecificDirectory = new ArrayList<Directory>();
        for (Directory directory : directories)
        {
            for (String classification : values)
            {
                if (classification.equalsIgnoreCase(directory.getClassification()))
                {
                    classificationSpecificDirectory.add(directory);
                }

            }
        }
        return classificationSpecificDirectory;
    }
    List<Directory> parseCSVFile(String path, String filename)
    {
        List<Directory> result = new ArrayList<Directory>();
        BufferedReader bufferedReader = null;
        try
        {
            bufferedReader = new BufferedReader(new FileReader(path + filename));
            String line = bufferedReader.readLine();
            while (line != null)
            {
                if (line.charAt(0) != '#')
                {
                    String[] tokens = line.split(";", -1);
                    if (tokens.length > 0)
                    {
                        Directory record = new Directory(parsetoInt(tokens[0]), parsetoInt(tokens[1]), tokens[2], tokens[3], parsetoInt(tokens[4]), tokens[5], parsetoInt(tokens[6]));
                        result.add(record);
                    }
                }
                line = bufferedReader.readLine();
            }
            return result;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return result;
        }
        finally
        {
            try
            {
                bufferedReader.close();

            }
            catch (IOException ex)
            {
                Logger.getLogger(DirectoryStructure.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    int parsetoInt(String value)
    {
        try
        {
            return Integer.parseInt(value.trim());
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }
    public List<Directory> getNonPublicFiles(String foldername)
    {
        List<Directory> record = new ArrayList<>();
        List<Directory> childNodes = getChildNodes(foldername);
        for (Directory childNode : childNodes)
        {
            if (childNode.getType().equalsIgnoreCase("File") && !childNode.getClassification().equalsIgnoreCase("Public"))
            {
                record.add(childNode);
            }

        }
        return record;
    }
    public Directory findRoot()
    {
        for (Directory directory : directories)
        {
            if (directory.getParentId() == 0)
            {
                return directory;
            }
        }
        return null;
    }
    public ArrayList<ArrayList<Directory>>treeStructure(String directoryName, int level)
    {
        level++;
        ArrayList<ArrayList<Directory>> allChildNodes = new ArrayList<>();
        List<Directory> record  = getByName(directoryName);
        Directory directory = record.get(0);
        System.out.println(getlevelAndSpace(" ", level) + "" + directory.toString());
        if (directory.getType().equalsIgnoreCase("Directory"))
        {
            int id = directory.getId();
            List<Directory> childNodes = getByParentId(id);
            allChildNodes.add((ArrayList<Directory>) childNodes);
            for (Directory childNode : childNodes)
            {
                ArrayList<ArrayList<Directory>> childsofchilds = treeStructure(childNode.getName(), level);
                for (ArrayList<Directory> child : childsofchilds)
                {
                    allChildNodes.add(child);
                }
            }
        }
        return allChildNodes;
    }
    String getlevelAndSpace(String space, int level)
    {
        StringBuffer stringBuffer = new StringBuffer(level);
        for (int i = 0; i < level; i++)
        {
            stringBuffer.append(space);
        }
        return stringBuffer.toString();
    }
    public int getFolderSize(Directory directory)
    {
        int parentId = directory.getId();
        List<Directory> childs = getByParentId(parentId);
        int size = 0;
        for (Directory child : childs)
        {
            if (child.getType().equalsIgnoreCase("Directory"))
            {
                size = size + getFolderSize(child);
            }
            else
            {
                size = size + child.getSize();
            }
        }
        directory.setSize(size);
        updateRecord(directory);
        return size;
    }
    private void updateRecord(Directory directory)
    {
        int index = directories.indexOf(directory);
        directories.set(index, directory);
    }
}



