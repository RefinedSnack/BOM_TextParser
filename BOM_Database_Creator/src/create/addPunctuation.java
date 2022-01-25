package create;

import Model.Punctuation;
import Model.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DataAccessException;
import dao.Database;
import dao.PunctuationDao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class addPunctuation
{
    public static void main(String[] args)
    {
        if (args.length != 1)
            return;

        try (FileReader reader = new FileReader(args[0]))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Punctuation[] punctuation = gson.fromJson(reader, Punctuation[].class);
            for (Punctuation currPunctuation : punctuation)
            {
                //System.out.println(currPunctuation.toString());
                Database.getInstance().addPunctuation(currPunctuation);
            }
            Database.getInstance().closeConnection(true);
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
}
