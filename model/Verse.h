//
// Created by walte on 11/9/2021.
//

#ifndef BOM_TEXTPARSER_VERSE_H
#define BOM_TEXTPARSER_VERSE_H

#include <cstddef>
#include <string>
#include <ostream>
#include <sstream>
class Verse
{
private:
    size_t verseID = 0;
    std::string book = "";
    size_t chapter = 0;
    size_t verseNumber = 0;
    size_t firstTokenID = 0;
    bool buildFromString(std::string formattedString)
    {
        size_t index = 0;
        std::stringstream workingString;
        if ((formattedString.find('>') == std::string::npos) || (formattedString.at(0) != '<'))
            return false;

        formattedString.erase(0, 1);
        while (formattedString.at(0) != '>')
        {
            if (isspace(formattedString.at(0)))
            {
                formattedString.erase(0, 1);
                continue;
            }
            if (formattedString.at(0) == ',')
            {
                if (index == 0)
                {
                    size_t temp;
                    workingString >> temp;
                    setVerseId(temp);
                } else if (index == 1)
                {
                    std::string temp;
                    workingString >> temp;
                    setBook(temp);
                } else if (index == 2)
                {
                    size_t temp;
                    workingString >> temp;
                    setChapter(temp);
                } else
                {
                    return false;
                }
                formattedString.erase(0, 1);
                workingString.clear();
                index++;
                continue;
            }
            workingString << formattedString.at(0);
            formattedString.erase(0, 1);
        }
        size_t temp;
        workingString >> temp;
        setVerseNumber(temp);
        return true;
    }
public:
    Verse()
    {}

    Verse(size_t verseId, const std::string &book, size_t chapter, size_t verseNumber, size_t firstTokenId) :
            verseID(verseId), book(book), chapter(chapter), verseNumber(verseNumber), firstTokenID(firstTokenId)
    {}

    Verse(const std::string& formattedString, size_t firstTokenID)
    {
        buildFromString(formattedString);
        this->firstTokenID = firstTokenID;
    }

    size_t getVerseId() const
    {
        return verseID;
    }

    void setVerseId(size_t verseId)
    {
        verseID = verseId;
    }

    const std::string &getBook() const
    {
        return book;
    }

    void setBook(const std::string &book)
    {
        Verse::book = book;
    }

    size_t getChapter() const
    {
        return chapter;
    }

    void setChapter(size_t chapter)
    {
        Verse::chapter = chapter;
    }

    size_t getVerseNumber() const
    {
        return verseNumber;
    }

    void setVerseNumber(size_t verseNumber)
    {
        this->verseNumber = verseNumber;
    }

    size_t getFirstTokenId() const
    {
        return firstTokenID;
    }

    void setFirstTokenId(size_t firstTokenId)
    {
        firstTokenID = firstTokenId;
    }

    friend std::ostream &operator<<(std::ostream &os, const Verse &verse)
    {
        os << "verseID: " << verse.verseID
           << " book: " << verse.book
           << " chapter: " << verse.chapter
           << " verseNumber: " << verse.verseNumber
           << " firstTokenID: " << verse.firstTokenID;
        return os;
    }
};


#endif //BOM_TEXTPARSER_VERSE_H
