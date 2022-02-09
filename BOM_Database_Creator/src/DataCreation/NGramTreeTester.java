package DataCreation;

import Model.NGramTree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NGramTreeTester
{
    public static void main(String[] args) throws IOException
    {
        NGramTree tree = new NGramTree();
        List<String> toAdd = new ArrayList<>();
        toAdd.add("AND");
        toAdd.add("IT");
        toAdd.add("CAKE");
        toAdd.add("PASS");
        toAdd.add("PASS");
        toAdd.add("PASS");

        List<String> speakers = new ArrayList<>();
        speakers.add("E");
        speakers.add("F");
        speakers.add("G");
        tree.add(toAdd, speakers, true);
        tree.add(toAdd, speakers, false);
        tree.toCSV("test/cake.csv");
    }
}
