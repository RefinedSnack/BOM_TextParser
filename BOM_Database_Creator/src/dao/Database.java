package dao;

import Model.Punctuation;
import Model.Token;
import Model.Verse;

import java.lang.invoke.VarHandle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private Database()
    {

    }

    private static Database instance = null;
    public static Database getInstance() throws DataAccessException
    {
        if (instance == null)
            instance = new Database();
        if (instance.getConn() == null)
            instance.openConnection();
        return instance;
    }

    private Connection conn = null;
    private TokenDao tokenDao = null;
    private VerseDao verseDao = null;
    private WordDao wordDao = null;
    private PunctuationDao punctuationDao = null;


    //Whenever we want to make a change to our database we will have to open a connection and use
    //Statements created by that connection to initiate transactions
    private void openConnection() throws DataAccessException
    {
        try
        {
            //The Structure for this Connection is driver:language:path
            //The path assumes you start in the root of your project unless given a non-relative path
            final String CONNECTION_URL = "jdbc:sqlite:database.db";
            conn = DriverManager.getConnection(CONNECTION_URL);

            // Start a transaction
            conn.setAutoCommit(false);

            beginDAOs();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }
    }

    private void beginDAOs()
    {
        tokenDao = new TokenDao(conn);
        verseDao = new VerseDao(conn);
        wordDao = new WordDao(conn);
        punctuationDao = new PunctuationDao(conn);
    }

    public Connection getConn()
    {
        return conn;
    }

    public TokenDao getTokenDao()
    {
        return tokenDao;
    }
    public VerseDao getVerseDao()
    {
        return verseDao;
    }
    public PunctuationDao getPunctuationDao()
    {
        return punctuationDao;
    }
    public WordDao getWordDao()
    {
        return wordDao;
    }

    public void closeConnection(boolean commit) throws DataAccessException
    {
        if (conn == null)
            return;
        try
        {
            if (commit)
            {
                conn.commit();
            } else
            {
                conn.rollback();
            }

            conn.close();
            conn = null;
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    public void addToken(Token t) throws DataAccessException
    {
        tokenDao.insert(t);
        wordDao.add(t.getWordValue());
    }

    public void addPunctuation(Punctuation p) throws DataAccessException
    {
        punctuationDao.insert(p);
    }

    public void clear() throws DataAccessException
    {
        tokenDao.clear();
        punctuationDao.clear();
        wordDao.clear();
        verseDao.clear();
    }
}

