package dao;

import Model.Token;
import Model.Word;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class WordDao
{
    private final Connection connection;
    public WordDao(Connection connection)
    {
        this.connection = connection;
    }

    public Word find(String wordValue) throws DataAccessException
    {
        Word output;
        ResultSet rs = null;
        String sql = "SELECT * FROM words WHERE value = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, wordValue);
            rs = stmt.executeQuery();
            if (rs.isClosed())
                return null;
            if (rs.next())
            {
                output = new Word(rs.getString("value"),
                        rs.getInt("frequency"));
                return output;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding tokens");
        } finally
        {
            if (rs != null)
            {
                try
                {
                    rs.close();
                } catch (SQLException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public void insert(Word word) throws DataAccessException
    {
        String sql = "INSERT INTO words (value, frequency) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, word.getValue());
            stmt.setInt(2, word.getFrequency());

            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting token into the database");
        }
    }

    public void replace(Word word) throws DataAccessException
    {
        String sql = "REPLACE INTO words (value, frequency) VALUES(?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, word.getValue());
            stmt.setInt(2, word.getFrequency());

            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting token into the database");
        }
    }

    public void add(String wordValue) throws DataAccessException
    {
        Word word = find(wordValue);
        if (word != null)
        {
            insert(new Word(wordValue, 0));
        }
        else
        {
            word.incrementFrequency();
            replace(word);
        }
    }

    public void clear() throws DataAccessException
    {
        try (Statement stmt = connection.createStatement())
        {
            String sql = "DELETE FROM words";
            stmt.executeUpdate(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while clearing token table");
        }
    }
    public void clear(Integer tokenId) throws DataAccessException
    {
        String sql = "DELETE FROM words WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, tokenId);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while clearing person table");
        }
    }

    public boolean isEmpty()
    {
        String sql = "SELECT id FROM words ";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                return false;
            } else
            {
                return true;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }
    public Integer size()
    {
        Integer out = 0;
        String sql = "SELECT id FROM tokens ";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                out++;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return out;
    }
}
