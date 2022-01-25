package create;

import Model.Verse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DataAccessException;
import dao.Database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class addVerses
{
    public static void main(String[] args)
    {
        if (args.length != 1)
            return;

        try (FileReader reader = new FileReader(args[0]))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Verse[] verses = gson.fromJson(reader, Verse[].class);
            for (Verse currVerse : verses)
            {
                currVerse.toUpper();
                Database.getInstance().getVerseDao().insert(currVerse);
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
