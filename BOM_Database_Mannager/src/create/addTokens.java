package create;


import Model.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.DataAccessException;
import dao.Database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class addTokens
{
    public static void main(String[] args)
    {
        if (args.length != 1)
            return;

        try (FileReader reader = new FileReader(args[0]))
        {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Token[] tokens = gson.fromJson(reader, Token[].class);
            for (Token currToken : tokens)
            {
                currToken.toUpper();
                Database.getInstance().getTokenDao().insert(currToken);
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
