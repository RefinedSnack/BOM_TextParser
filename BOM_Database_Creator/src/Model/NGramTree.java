package Model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class NGramTree
{

    Node root = new Node("", false);

    Integer maxDepth = 0;

    public void toCSV(String fileName) throws IOException
    {
        FileWriter out = new FileWriter(fileName);
        CSVPrinter printer = new CSVPrinter(out, CSVFormat.EXCEL);


        List<String> empty = new ArrayList<>();
        for (Integer i = 0; i < maxDepth; i++)
            empty.add("");
        Integer count = 1;

        for (String val : empty)
        {
            printer.print(count.toString() + "-gram");
            count++;
        }
        printer.print("Total Frequency");
        for (String speaker : SpeakerFrequency.getSpeakers())
            printer.print(speaker);
        printer.println();

        for (Node child : root.getChildren().values())
        {
            child.toCSV(empty, printer);
        }
        printer.close();
    }
    public void add(List<String> nGram, List<String> speakers, boolean addRecursively)
    {
        if (speakers.size() != nGram.size())
        {
            System.out.println("Speakers.size(): " + speakers.size() + " nGram.size(): " + nGram.size());
            while (speakers.size() < nGram.size())
                speakers.add(speakers.get(speakers.size()-1));
        }
        if (nGram.size() > maxDepth)
            maxDepth = nGram.size();

        Node curr =  root;
        Integer index = 0;
        while (index < nGram.size()-1)
        {
            curr = curr.add(nGram.get(index), addRecursively);
            if (addRecursively)
                curr.incrementSpeaker(speakers.get(index));
            index++;
        }
        curr = curr.add(nGram.get(index), true);
        curr.incrementSpeaker(speakers.get(index));
    }


    class Node {
        Map<String, Node> children = null;
        String value = null;
        Integer count = 0;
        SpeakerFrequency speakerFrequency = new SpeakerFrequency();
        public void toCSV(List<String> nGram, CSVPrinter printer) throws IOException
        {
            List<String> nGramCopy = new ArrayList<>(nGram);
            for (Integer i = 0; i < nGramCopy.size(); i++)
            {
                if (nGram.get(i) == "")
                {
                    nGramCopy.set(i, value);
                    break;
                }
            }
            for (String val : nGramCopy)
                printer.print(val);
            printer.print(count);
            for (Integer speakerCount : speakerFrequency.getCounts())
                printer.print(speakerCount);
            printer.println();
            if (children != null)
                for (Node child : children.values())
                {
                    child.toCSV(nGramCopy, printer);
                }
        }
        public String getValue()
        {
            return value;
        }

        public Map<String, Node> getChildren()
        {
            return children;
        }

        public Integer getCount()
        {
            return count;
        }

        public Node(String value, boolean addOne)
        {
            this.value = value;
            if (addOne)
                count = 1;
            else
                count = 0;
        }

        public void add()
        {
             count++;
        }
        public Node add(String child, boolean isFinal)
        {
            if (children == null)
                children = new HashMap<>();
            if (isFinal && children.containsKey(child))
            {
                Node temp = children.get(child);
                temp.add();
                children.replace(child, temp);
            }
            else
                children.put(child, new Node(child, isFinal));
            return children.get(child);
        }

        public void incrementSpeaker(String speaker)
        {
            speakerFrequency.incrementSpeakerCount(speaker);
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value.equals(node.value);
        }

        @Override
        public int hashCode()
        {
            return Objects.hash(value);
        }
    }
}
