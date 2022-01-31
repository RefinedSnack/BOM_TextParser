package DataCreation;

import Model.Phrase;
import Model.PhraseOccurrences;
import Model.Token;
import dao.DataAccessException;
import dao.Database;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class GetAllOccurrencesOfPhrase
{
    Set<Phrase> phraseSet;
    PhraseOccurrences phraseOccurrences = new PhraseOccurrences();

    public GetAllOccurrencesOfPhrase(Set<Phrase> phraseSet)
    {
        this.phraseSet = phraseSet;
        for (Phrase phrase : phraseSet)
        {
            phraseOccurrences.addPhrase(phrase);
        }
    }

    public void generate() throws DataAccessException, IOException
    {
        int max = Database.getInstance().getTokenDao().size();
        Phrase currPhrase = new Phrase(new ArrayList<>());
        Token currToken;
        for (int i = 0; i < max; i++)
        {
            System.out.println(i);
            currToken = Database.getInstance().getTokenDao().find(i);
            if (currPhrase.size() == phraseOccurrences.getLargestPhraseSize())
                currPhrase = currPhrase.shift(currToken.getWordValue());
            else
                currPhrase.add(currToken.getWordValue());

            phraseOccurrences.match(currPhrase);
        }

        phraseOccurrences.createCSV("output/allOccurrences.csv");
    }
}
