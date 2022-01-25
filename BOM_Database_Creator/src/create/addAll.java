package create;

import dao.DataAccessException;
import dao.Database;

public class addAll
{
    public static void main(String[] args) throws DataAccessException
    {
        if (args.length != 3)
        {
            System.out.println("Needs 3 arguments, punct.json, tokens.json, verses.json");
            return;
        }
        Database.getInstance().clear();
        addPunctuation.main(new String[]{args[0]});
        addTokens.main(new String[]{args[1]});
        addVerses.main(new String[]{args[2]});

    }
}
