package DataCreation;

import Model.PhraseSpeakerMap;
import Model.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DataAccessException;
import dao.Database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PhraseBySpeaker
{
    public static void main(String[] args)
    {
        try (FileReader reader = new FileReader(args[0]))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String[][] phrases = gson.fromJson(reader, String[][].class);
            for (String[] currPhrase : phrases)
            {
                PhraseBySpeaker currPhraseBySpeaker = new PhraseBySpeaker(Arrays.asList(currPhrase));
                System.out.print(currPhraseBySpeaker.getSpeakersFrequency().toString());
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

    List<String> phrase = null;
    PhraseBySpeaker(List<String> phrase)
    {
        this.phrase = phrase;
    }

    PhraseSpeakerMap speakersFrequency = null;
    List<Integer> occurrences = new ArrayList<>();

    PhraseSpeakerMap getSpeakersFrequency() throws DataAccessException
    {
        speakersFrequency = new PhraseSpeakerMap(phrase, new HashMap<>());
        boolean skip;
        int max = Database.getInstance().getTokenDao().size();
        for (int i = phrase.size()-1; i < max; i++)
        {
            skip = false;
            Token curr = Database.getInstance().getTokenDao().find(i);
            for (String value : phrase)
            {
                if (!curr.getWordValue().equalsIgnoreCase(value))
                {
                    skip = true;
                }
            }
            if (!skip)
            {
                speakersFrequency.incrementCount(curr.getSpeaker().toLowerCase(Locale.ROOT));
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
