package Model;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PhraseOccurrences
{
    private Integer largestPhraseSize = 0;
    private Map<Phrase, Integer> phrases = new HashMap<>();

    public void addPhrase(Phrase phrase)
    {
        if (phrase.size() > largestPhraseSize)
            largestPhraseSize = phrase.size();
        phrases.put(phrase, 0);
    }

    public Integer getLargestPhraseSize()
    {
        return largestPhraseSize;
    }

    public void increment(Phrase phrase)
    {
        if (!phrases.containsKey(phrase))
        {
            System.out.println(String.format("The phrase: %s is not present", phrase.toString()));
            return;
        }
        Integer newValue = phrases.get(phrase) + 1;
        phrases.replace(phrase, newValue);
    }

    public void addAndIncrement(Phrase phrase)
    {
        if (!phrases.containsKey(phrase))
        {
            addPhrase(phrase);
        }
        increment(phrase);
    }

    public void match(Phrase phrase)
    {
        Phrase currPhrase = phrase;
        while (currPhrase.size() > 0)
        {
            if (phrases.containsKey(currPhrase))
                increment(currPhrase);
            //System.out.println(String.format("currPhraseSize: %s", currPhrase.size()));
            currPhrase = currPhrase.shrinkByOne();
        }
    }

    public void createCSV(String fileName) throws IOException
    {
        FileWriter out = new FileWriter(fileName);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT))
        {
            StringBuilder sb1 = new StringBuilder();
            printer.printRecord("Phrase, Frequency");
            for (Phrase key : phrases.keySet())
            {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(key.toString());
                Integer count = phrases.get(key);
                sb2.append(",").append(count);
                printer.printRecord(sb2.toString());
            }
        }
    }
}
