//
// Created by walte on 11/9/2021.
//

#ifndef BOM_TEXTPARSER_VERSELOCATION_H
#define BOM_TEXTPARSER_VERSELOCATION_H

#include <string>
#include <ostream>

class VerseLocation
{
    std::string book;
    size_t chapter;
    size_t verseNum;
    size_t globalVerseNum;

public:
    VerseLocation()
    {
        
    }
    VerseLocation(const std::string& formattedString)
    {
        buildFromString(formattedString);
    }
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
                    setGlobalVerseNum(temp);
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
        setVerseNum(temp);
        return true;
    }
    const std::string &getBook() const
    {
        return book;
    }

    void setBook(const std::string &book)
    {
        VerseLocation::book = book;
    }

    size_t getChapter() const
    {
        return chapter;
    }

    void setChapter(size_t chapter)
    {
        VerseLocation::chapter = chapter;
    }

    size_t getVerseNum() const
    {
        return verseNum;
    }

    void setVerseNum(size_t verse)
    {
        VerseLocation::verseNum = verse;
    }

    size_t getGlobalVerseNum() const
    {
        return globalVerseNum;
    }


    void setGlobalVerseNum(size_t globalVerseNum)
    {
        VerseLocation::globalVerseNum = globalVerseNum;
    }


    friend std::ostream &operator<<(std::ostream &os, const VerseLocation &location)
    {
        os << "book: " << location.book
           << ", chapter: " << location.chapter
           << ", verse: " << location.verseNum
           << ", globalVerseNum: " << location.globalVerseNum;
        return os;
    }
};
#endif //BOM_TEXTPARSER_VERSELOCATION_H
