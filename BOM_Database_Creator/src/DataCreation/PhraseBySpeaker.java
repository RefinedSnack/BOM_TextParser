package DataCreation;

import Model.PhraseSpeakerMap;
import Model.Token;
import Model.Word;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DataAccessException;
import dao.Database;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PhraseBySpeaker
{
    public static void main(String[] args)
    {
        try (FileReader reader = new FileReader(args[0]))
        {
            int i = 1;
            List<PhraseSpeakerMap> phrasesBySpeakers = new ArrayList<>();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String[][] phrases = gson.fromJson(reader, String[][].class);
            for (String[] currPhrase : phrases)
            {
                PhraseBySpeaker currPhraseBySpeaker = new PhraseBySpeaker(Arrays.asList(currPhrase));
                phrasesBySpeakers.add(currPhraseBySpeaker.getSpeakersFrequency());
                createCSVFile(phrases, phrasesBySpeakers, "output/deity" + i + ".csv");
                System.out.print(i + "\t");
                i++;
            }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (DataAccessException e)
        {
            e.printStackTrace();
        }
    }

    private static void createCSVFile(String[][] phrases, List<PhraseSpeakerMap> data, String name) throws IOException
    {
        FileWriter out = new FileWriter(name);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT)) {
            StringBuilder sb1 = new StringBuilder();
            sb1.append("Word").append(",");
            for (String s : data.get(0).speakers)
                sb1.append(s).append(",");
            printer.printRecord(sb1.toString());
            for (int i = 0; i < data.size(); i++)
            {
                StringBuilder sb2 = new StringBuilder();
                for (String s : phrases[i])
                    sb2.append(s).append(" ");
                for (Integer s : data.get(i).speakerFrequency)
                    sb2.append(",").append(s);
                printer.printRecord(sb2.toString());
            }
        }
    }


    List<String> phrase = null;
    PhraseBySpeaker(List<String> phrase)
    {
        this.phrase = phrase;
    }

    PhraseSpeakerMap speakersFrequency = null;
    List<Integer> occurrences = new ArrayList<>();

    PhraseSpeakerMap getSpeakersFrequency() throws DataAccessException
    {
        speakersFrequency = new PhraseSpeakerMap(phrase, new ArrayList<>());
        boolean skip;
        Token curr = null;
        int max = Database.getInstance().getTokenDao().size();

        for (int i = 0; i <= max - phrase.size(); i++)
        {
            skip = false;
            for (int j = 0; j < phrase.size(); j++)
            {
                curr = Database.getInstance().getTokenDao().find(i + j);
                if (!curr.getWordValue().equalsIgnoreCase(phrase.get(j)))
                {
                    skip = true;
                    break;
                }
            }
            if (!skip)
            {
                speakersFrequency.incrementCount(curr.getSpeaker());
                occurrences.add(i - (phrase.size() - 1));
            }
        }
        return speakersFrequency;
    }

    public List<Integer> getOccurrences()
    {
        return occurrences;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PhraseBySpeaker{");
        sb.append("speakersFrequency=").append(speakersFrequency.toString());
        sb.append('}');
        return sb.toString();
    }
}
