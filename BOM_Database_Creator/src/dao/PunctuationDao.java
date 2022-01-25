package dao;

import Model.Punctuation;
import Model.Token;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PunctuationDao
{
    private final Connection connection;

    public PunctuationDao(Connection connection)
    {
        this.connection = connection;
    }

    public Set<Punctuation> find(String value) throws DataAccessException
    {
        Punctuation currPunctuation;
        Set<Punctuation> outputSet = new HashSet<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM punctuation WHERE value = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setString(1, value);
            rs = stmt.executeQuery();
            if (rs.isClosed())
                return outputSet;
            while (rs.next())
            {
                currPunctuation = new Punctuation(rs.getInt("id"),
                        rs.getInt("tokenIdBefore"),
                        rs.getInt("tokenIdAfter"),
                        rs.getString("value"));
                outputSet.add(currPunctuation);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding punctuation");
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
    public Punctuation find(Integer id) throws DataAccessException
    {
        Punctuation output;
        ResultSet rs = null;
        String sql = "SELECT * FROM punctuation WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.isClosed())
                return null;
            if (rs.next())
            {
                output = new Punctuation(rs.getInt("id"),
                        rs.getInt("tokenIdBefore"),
                        rs.getInt("tokenIdAfter"),
                        rs.getString("value"));
                return output;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding punctuation");
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
    public void insert(Punctuation punctuation) throws DataAccessException
    {
        String sql = "INSERT INTO punctuation (id, tokenIdAfter, tokenIdBefore, value) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, punctuation.getId());
            stmt.setInt(2, punctuation.getTokenIdBefore());
            stmt.setInt(3, punctuation.getTokenIdAfter());
            stmt.setString(4, punctuation.getValue());

            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while inserting punctuation into the database");
        }
    }

    public void replace(Punctuation punctuation) throws DataAccessException
    {
        String sql = "REPLACE INTO punctuation (id, tokenIdAfter, tokenIdBefore, value) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, punctuation.getId());
            stmt.setInt(2, punctuation.getTokenIdBefore());
            stmt.setInt(3, punctuation.getTokenIdAfter());
            stmt.setString(4, punctuation.getValue());

            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while replacing a punctuation in the database");
        }
    }

    public void clear() throws DataAccessException
    {
        try (Statement stmt = connection.createStatement())
        {
            String sql = "DELETE FROM punctuation";
            stmt.executeUpdate(sql);
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while clearing punctuation table");
        }
    }
    public void delete(Integer tokenId) throws DataAccessException
    {
        String sql = "DELETE FROM punctuation WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql))
        {
            stmt.setInt(1, tokenId);
            stmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            throw new DataAccessException("SQL Error encountered while deleting punctuation table");
        }
    }

    public boolean isEmpty()
    {
        String sql = "SELECT id FROM punctuation ";
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
        String sql = "SELECT id FROM punctuation ";
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
