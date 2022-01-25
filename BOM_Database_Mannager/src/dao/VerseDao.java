package dao;

import Model.Token;
import Model.Verse;

import java.sql.*;

public class VerseDao
{
    private final Connection connection;
    public VerseDao(Connection connection)
    {
        this.connection = connection;
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
    public void insert(Verse verse) throws DataAccessException
    {
        String sql = "INSERT INTO verses (id, book, chapter, verseNumber, firstTokenID) VALUES(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, verse.getId());
            stmt.setString(2, verse.getBook());
            stmt.setInt(3, verse.getChapter());
            stmt.setInt(4, verse.getVerseNumber());
            stmt.setInt(5, verse.getFirstTokenID());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting token into the database");
        }
    }

    public void replace(Verse verse) throws DataAccessException
    {
        String sql = "REPLACE INTO verses (id, book, chapter, verseNumber, firstTokenID) VALUES(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, verse.getId());
            stmt.setString(2, verse.getBook());
            stmt.setInt(3, verse.getChapter());
            stmt.setInt(4, verse.getVerseNumber());
            stmt.setInt(5, verse.getFirstTokenID());
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting token into the database");
        }
    }

    public void clear() throws DataAccessException
    {
        try (Statement stmt = connection.createStatement())
        {
            String sql = "DELETE FROM verses";
            stmt.executeUpdate(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while clearing token table");
        }
    }
    public void clear(Integer tokenId) throws DataAccessException
    {
        String sql = "DELETE FROM verses WHERE id = ?";
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
