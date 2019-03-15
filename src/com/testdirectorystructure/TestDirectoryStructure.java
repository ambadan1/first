
package com.testdirectorystructure;
/**
 * Created by pratiksha.rahangdale on 8/9/2016.
 */
import com.directorystructure.Directory;
import com.directorystructure.DirectoryStructure;
import com.directorystructure.Functionalities;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class TestDirectoryStructure {

    Functionalities functionalities = new Functionalities();
    ArrayList<Directory> expected = new ArrayList<Directory>();
    List<Directory> randomdata = new ArrayList<Directory>();

    public void addMethod(List<Directory> randomdata)
    {
        functionalities.directories.clear();
        for (Directory directory : randomdata)
        {
            functionalities.directories.add(directory);
        }
    }

    @Test
    public void testClassification()
    {
        Directory directory1 = new Directory(9,10,"file9","file",90,"Top secret",42);
        Directory directory2 = new Directory(1,3,"file1","file",10,"Secret",42);
        Directory directory3 = new Directory(4,2,"file4","file",40,"Secret",42);
        Directory directory4 = new Directory(6,3,"file6","file",60,"Private",42);
        Directory directory5 = new Directory(8,10,"file8","file",80,"Public",42);
        Directory directory6 = new Directory(7,3,"file7","file",70,"Public",42);
        Directory directory7 = new Directory(5,3,"file5","file",50,"Zip",42);

        randomdata.add(directory1);
        randomdata.add(directory2);
        randomdata.add(directory3);
        randomdata.add(directory4);
        randomdata.add(directory5);
        randomdata.add(directory6);
        randomdata.add(directory7);
        addMethod(randomdata);

        expected.add(directory1);
        expected.add(directory2);
        expected.add(directory3);
        expected.add(directory5);
        expected.add(directory6);

        String expectedresult = "";
        for (Directory value : expected)
        {
            expectedresult += value.toString();
        }

        List<Directory> resultnodes = functionalities.getClassification(DirectoryStructure.Classification.TOP_SECRET.classificationName(), DirectoryStructure.Classification.SECRET.classificationName(), DirectoryStructure.Classification.PUBLIC.classificationName());
        String actualresult = "";
        for (Directory resultNode : resultnodes)
        {
            actualresult += resultNode.toString();
        }

        System.out.println("exp:" + expectedresult);
        System.out.println("res:" + actualresult);
        assertEquals(expectedresult, actualresult);
    }

    @Test
    public void testNonPublicFiles()
    {
        Directory directory1 = new Directory(11, 10, "folder11", "Directory", 240, "", 42);
        Directory directory2 = new Directory(1, 11, "file1", "file", 10, "Secret", 42);
        Directory directory3 = new Directory(6, 11, "file6", "file", 60, "Secret", 42);
        Directory directory4 = new Directory(8, 11, "file8", "file", 80, "Secret", 42);
        Directory directory5 = new Directory(9, 11, "file9", "file", 90, "Public", 42);
        Directory directory6 = new Directory(13, 11, "file111", "file", 90, "Top secret", 42);
        Directory directory7 = new Directory(3, 10, "file9", "file", 90, "Top secret", 42);
        Directory directory8 = new Directory(12, 11, "folder12", "Directory", 90, "", 42);
        Directory directory9 = new Directory(14, 12, "fileinsidefolder12", "File", 90, "Secret", 42);

        randomdata.add(directory1);
        randomdata.add(directory2);
        randomdata.add(directory3);
        randomdata.add(directory4);
        randomdata.add(directory5);
        randomdata.add(directory6);
        randomdata.add(directory7);
        randomdata.add(directory8);
        randomdata.add(directory9);
        addMethod(randomdata);

        expected.add(directory2);
        expected.add(directory3);
        expected.add(directory4);
        expected.add(directory6);
        expected.add(directory9);

        String expectedresult = "";
        for (Directory value : expected)
        {
            expectedresult += value.toString();
        }

        List<Directory> resultNodes = functionalities.getNonPublicFiles("folder11");
        String actualresult = "";
        for (Directory resultNode : resultNodes)
        {
            actualresult += resultNode.toString();
        }

        System.out.println("exp:" + expectedresult);
        System.out.println("res:" + actualresult);
        assertEquals(expectedresult, actualresult);
    }


    @Test
    public void testGetSize()
    {
        int expectedSize = 120;
        List<Directory> public_ = functionalities.getClassification(DirectoryStructure.Classification.PUBLIC.classificationName());
        Object resultSize_ = functionalities.sizeOfPublicClassification(public_);
        assertEquals(expectedSize,resultSize_);
    }

    @Test
    public void testFindRoot()
    {
        Directory expectedRoot = new Directory(2, 0, "folder2", "directory", 0, "", 0);
        Directory expectedRoot1 = new Directory(1, 3, "file1", "file", 10, "Secret", 42);

        randomdata.add(expectedRoot);
        randomdata.add(expectedRoot1);
        addMethod(randomdata);

        Directory rootDirectory = functionalities.findRoot();

        assertEquals(expectedRoot.getId(), rootDirectory.getId());
    }
}



