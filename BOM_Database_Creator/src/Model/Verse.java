package Model;

public class Verse
{
    Integer id = null;
    String book = null;
    Integer chapter = null;
    Integer verseNumber = null;
    Integer firstTokenID = null;

    public Verse(Integer id, String book, Integer chapter, Integer verseNumber, Integer firstTokenID)
    {
        this.id = id;
        this.book = book;
        this.chapter = chapter;
        this.verseNumber = verseNumber;
        this.firstTokenID = firstTokenID;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getBook()
    {
        return book;
    }

    public void setBook(String book)
    {
        this.book = book;
    }

    public Integer getChapter()
    {
        return chapter;
    }

    public void setChapter(Integer chapter)
    {
        this.chapter = chapter;
    }

    public Integer getVerseNumber()
    {
        return verseNumber;
    }

    public void setVerseNumber(Integer verseNumber)
    {
        this.verseNumber = verseNumber;
    }

    public Integer getFirstTokenID()
    {
        return firstTokenID;
    }

    public void setFirstTokenID(Integer firstTokenID)
    {
        this.firstTokenID = firstTokenID;
    }
}
