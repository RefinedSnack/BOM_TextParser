package dao;

import Model.Token;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class TokenDao
{
    private final Connection connection;
    public TokenDao(Connection connection)
    {
        this.connection = connection;
    }

    public Set<Token> find(String wordValue) throws DataAccessException
    {
        Token currToken;
        Set<Token> outputSet = new HashSet<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM tokens WHERE wordValue = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, wordValue);
            rs = stmt.executeQuery();
            if (rs.isClosed())
                return outputSet;
            while (rs.next())
            {
                currToken = new Token(rs.getInt("id"),
                        rs.getString("wordValue"),
                        rs.getString("speaker"),
                        rs.getString("scribe"),
                        rs.getString("partOfSpeech"),
                        rs.getInt("verseID"));
                outputSet.add(currToken);
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
        return outputSet;
    }
    public Token find(Integer tokenId) throws DataAccessException
    {
        Token output;
        ResultSet rs = null;
        String sql = "SELECT * FROM tokens WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, tokenId);
            rs = stmt.executeQuery();
            if (rs.isClosed())
                return null;
            if (rs.next())
            {
                output = new Token(rs.getInt("id"),
                        rs.getString("wordValue"),
                        rs.getString("speaker"),
                        rs.getString("scribe"),
                        rs.getString("partOfSpeech"),
                        rs.getInt("verseID"));
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
    public void insert(Token token) throws DataAccessException
    {
        String sql = "INSERT INTO tokens (id, wordValue, speaker, scribe, partOfSpeech, verseID) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, token.getId());
            stmt.setString(2, token.getWordValue());
            stmt.setString(3, token.getSpeaker());
            stmt.setString(4, token.getScribe());
            stmt.setString(5, token.getPartOfSpeech());
            stmt.setInt(6, token.getVerseID());

            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting token into the database");
        }
    }

    public void replace(Token token) throws DataAccessException
    {
        String sql = "REPLACE INTO tokens (id, wordValue, speaker, scribe, partOfSpeech, verseID) VALUES(?,?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, token.getId());
            stmt.setString(2, token.getWordValue());
            stmt.setString(3, token.getSpeaker());
            stmt.setString(4, token.getScribe());
            stmt.setString(5, token.getPartOfSpeech());
            stmt.setInt(6, token.getVerseID());

            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while replacing a token in the database");
        }
    }

    public void clear() throws DataAccessException
    {
        try (Statement stmt = connection.createStatement())
        {
            String sql = "DELETE FROM token";
            stmt.executeUpdate(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while clearing token table");
        }
    }
    public void clear(Integer tokenId) throws DataAccessException
    {
        String sql = "DELETE FROM tokens WHERE id = ?";
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
        String sql = "SELECT id FROM tokens ";
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
