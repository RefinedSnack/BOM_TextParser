package Model;

import java.util.Locale;

public class Word
{
    String value = null;
    Integer frequency = null;

    public Word(String value, Integer frequency)
    {
        if (value != null)
            this.value = value.toUpperCase();
        this.frequency = frequency;
    }
    public void toUpper()
    {
        value = value.toUpperCase(Locale.ROOT);
    }

    public void incrementFrequency()
    {
        frequency++;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    public Integer getFrequency()
    {
        return frequency;
    }

    public void setFrequency(Integer frequency)
    {
        this.frequency = frequency;
    }
}
