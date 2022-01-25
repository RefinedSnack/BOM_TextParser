package Model;

import java.util.Locale;

public class Token
{
    Integer id = null;
    String wordValue = null;
    String speaker = null;
    String scribe = null;
    String partOfSpeech = null;
    Integer verseID = null;

    public Token(Integer id, String wordValue, String speaker, String scribe, String partOfSpeech, Integer verseID)
    {
        this.id = id;
        if (wordValue != null)
            this.wordValue = wordValue.toUpperCase(Locale.ROOT);
        if (speaker != null)
            this.speaker = speaker.toUpperCase(Locale.ROOT);
        if (scribe != null)
            this.scribe = scribe.toUpperCase(Locale.ROOT);
        if (partOfSpeech != null)
            this.partOfSpeech = partOfSpeech.toUpperCase(Locale.ROOT);
        this.verseID = verseID;
    }

    public void toUpper()
    {
        if (wordValue != null)
            this.wordValue = wordValue.toUpperCase(Locale.ROOT);
        if (speaker != null)
            this.speaker = speaker.toUpperCase(Locale.ROOT);
        if (scribe != null)
            this.scribe = scribe.toUpperCase(Locale.ROOT);
        if (partOfSpeech != null)
            this.partOfSpeech = partOfSpeech.toUpperCase(Locale.ROOT);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getWordValue()
    {
        return wordValue;
    }

    public void setWordValue(String wordValue)
    {
        this.wordValue = wordValue;
    }

    public String getSpeaker()
    {
        return speaker;
    }

    public void setSpeaker(String speaker)
    {
        this.speaker = speaker;
    }

    public String getScribe()
    {
        return scribe;
    }

    public void setScribe(String scribe)
    {
        this.scribe = scribe;
    }

    public String getPartOfSpeech()
    {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech)
    {
        this.partOfSpeech = partOfSpeech;
    }

    public Integer getVerseID()
    {
        return verseID;
    }

    public void setVerseID(Integer verseID)
    {
        this.verseID = verseID;
    }
}
