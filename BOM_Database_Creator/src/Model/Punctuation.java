package Model;

import java.util.Objects;

public class Punctuation
{
    private Integer id;
    private Integer tokenIdBefore;
    private Integer tokenIdAfter;
    private String value;

    public Punctuation(Integer id, Integer tokenIdBefore, Integer tokenIdAfter, String value)
    {
        this.id = id;
        this.tokenIdAfter = tokenIdAfter;
        this.tokenIdBefore = tokenIdBefore;
        this.value = value;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getTokenIdAfter()
    {
        return tokenIdAfter;
    }

    public void setTokenIdAfter(Integer tokenIdAfter)
    {
        this.tokenIdAfter = tokenIdAfter;
    }

    public Integer getTokenIdBefore()
    {
        return tokenIdBefore;
    }

    public void setGetTokenIdBefore(Integer getTokenIdBefore)
    {
        this.tokenIdBefore = tokenIdBefore;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Punctuation{");
        sb.append("id=").append(id);
        sb.append(", getTokenIdBefore=").append(tokenIdBefore);
        sb.append(", tokenIdAfter=").append(tokenIdAfter);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Punctuation that = (Punctuation) o;
        return Objects.equals(id, that.id) && Objects.equals(tokenIdAfter, that.tokenIdAfter) && Objects.equals(tokenIdBefore, that.tokenIdBefore) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, tokenIdAfter, tokenIdBefore, value);
    }
}
