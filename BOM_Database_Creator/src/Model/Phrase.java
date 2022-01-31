package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Phrase
{
    List<String> data;

    public Phrase(List<String> data)
    {
        this.data = data;
    }

    public List<String> getData()
    {
        return data;
    }

    public void setData(List<String> data)
    {
        this.data = data;
    }

    public Integer size()
    {
        return data.size();
    }

    public String get(Integer index) throws IndexOutOfBoundsException
    {
        return data.get(index);
    }

    public Phrase shrinkByOne()
    {
        if (data.size() == 1)
            return new Phrase(new ArrayList<>());
        List<String> newData = data.subList(0, this.size()-1);
        return new Phrase(newData);
    }
    public Phrase shift(String newEnd)
    {
        List<String> newData;
        if (data.size() > 1)
            newData = data.subList(1, data.size());
        else
            newData = new ArrayList<>();
        newData.add(newEnd);

        return new Phrase(newData);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phrase phrase = (Phrase) o;
        if (phrase.size() != this.size())
            return false;
        for (int i = 0; i < phrase.size(); i++)
            if (!phrase.get(i).equals(this.get(i)))
                return false;
        return true;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(data);
    }


    public String toStringPretty()
    {
        final StringBuilder sb = new StringBuilder("(");
        String sep = "";
        for (String s : data)
        {
            sb.append(sep).append(s);
            sep = ",";
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder();
        String sep = "";
        for (String s : data)
        {
            sb.append(sep).append(s);
            sep = " ";
        }
        return sb.toString();
    }

    public void add(String wordValue)
    {
        data.add(wordValue);
    }
}
