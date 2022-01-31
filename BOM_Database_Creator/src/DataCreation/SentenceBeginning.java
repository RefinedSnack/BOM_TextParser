package DataCreation;

import Model.*;
import dao.DataAccessException;
import dao.Database;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SentenceBeginning
{
    public static final int DEPTH = 5;
    public static void main(String[] args) throws DataAccessException, IOException
    {
        Set<Phrase> phraseSet = new HashSet<>();
        PhraseOccurrences output = new PhraseOccurrences();

        Set<Punctuation> tempExc = Database.getInstance().getPunctuationDao().find("!");
        Set<Punctuation> tempPer = Database.getInstance().getPunctuationDao().find(".");
        Set<Punctuation> tempQMark = Database.getInstance().getPunctuationDao().find("?");

        Set<Punctuation> total = new HashSet<>();
        for (Punctuation p : tempExc)
        {
            total.add(p);
        }
        for (Punctuation p : tempPer)
        {
            total.add(p);
        }
        for (Punctuation p : tempQMark)
        {
            total.add(p);
        }
        Token currToken;

        for (Punctuation currPunct : total)
        {
            List<Phrase> phrases = new ArrayList<>();
            for (Integer i = 0; i < DEPTH; i++)
                phrases.add(new Phrase(new ArrayList<>()));
            for (Integer i = 0; i < phrases.size(); i++)
            {
                currToken = Database.getInstance().getTokenDao().find(currPunct.getTokenIdAfter() + i);
                for (Integer j = i; j < phrases.size(); j++)
                {
                    if (currToken != null)
                    {
                        phrases.get(j).add(currToken.getWordValue());
                    }
                }
            }
            for (Integer i = 0; i < phrases.size(); i++)
            {
                phraseSet.add(phrases.get(i));
                output.addAndIncrement(phrases.get(i));
            }
        }
        output.createCSV("output/wow.csv");

        GetAllOccurrencesOfPhrase getAllOccurrencesOfPhrases = new GetAllOccurrencesOfPhrase(phraseSet);
        getAllOccurrencesOfPhrases.generate();
    }
}
