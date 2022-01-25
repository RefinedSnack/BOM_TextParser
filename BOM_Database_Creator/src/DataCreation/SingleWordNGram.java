package DataCreation;

import Model.PhraseSpeakerMap;
import Model.Token;
import dao.DataAccessException;
import dao.Database;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.invoke.StringConcatFactory;
import java.util.*;

public class SingleWordNGram
{
    String word = null;

    public SingleWordNGram(String word)
    {
        this.word = word;
    }

    public static void main(String[] args) throws DataAccessException, IOException
    {
        if (args.length != 1)
            throw new IndexOutOfBoundsException("Not enough arguments");
        SingleWordNGram singleWordNGram = new SingleWordNGram(args[0]);
        Map<String, PhraseSpeakerMap> values = singleWordNGram.generateFrequencies();
        FileWriter out = new FileWriter("output/Behold.csv");
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT))
        {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("Word").append(",");
            for (String s : values.get(args[0]).speakers)
                sb1.append(s).append(",");
            printer.printRecord(sb1.toString());
            for (String key : values.keySet())
            {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(key);
                for (Integer s : values.get(key).speakerFrequency)
                    sb2.append(",").append(s);
                printer.printRecord(sb2.toString());
            }
        }
    }


    Map<String, PhraseSpeakerMap> phraseData;

    Map<String, PhraseSpeakerMap> generateFrequencies() throws DataAccessException
    {
        phraseData = new HashMap<>();
        boolean skip;
        int max = Database.getInstance().getTokenDao().size();

        Token prev = null;
        Token curr = null;
        Token next = null;
        for (int i = 0; i < max; i++)
        {
            //update values
            if (curr != null)
                prev = curr;

            if (next != null)
                curr = next;
            else
                curr = Database.getInstance().getTokenDao().find(i);

            if (i < max - 1)
                next = Database.getInstance().getTokenDao().find(i + 1);
            else
                next = null;

            //check to see if curr is the target word, if not go to next word
            if (!curr.getWordValue().equalsIgnoreCase(word))
                continue;


            incrementWord(word, curr.getSpeaker());
            if ((prev != null) && (next != null))
            {
                incrementWord(String.format("%s %s %s", prev.getWordValue(), word, next.getWordValue()), curr.getSpeaker());
                incrementWord(String.format("%s %s", prev.getWordValue(), word), curr.getSpeaker());
                incrementWord(String.format("%s %s", word, next.getWordValue()), curr.getSpeaker());
            } else if (prev != null)
            {
                incrementWord(String.format("%s %s", prev.getWordValue(), word), curr.getSpeaker());
            } else if (next != null)
            {
                incrementWord(String.format("%s %s", word, next.getWordValue()), curr.getSpeaker());
            }


        }
        return phraseData;
    }

    public void incrementWord(String newWord, String speaker)
    {
        if (!phraseData.containsKey(newWord))
            phraseData.put(newWord, new PhraseSpeakerMap(Arrays.asList(newWord)));
        phraseData.get(newWord).incrementCount(speaker);
    }
}
