//
// Created by walte on 11/29/2021.
//

#ifndef BOM_TEXTPARSER_WORD_H
#define BOM_TEXTPARSER_WORD_H
#include <string>
#include <ostream>

class Word
{
private:
    std::string wordValue = "";
    size_t frequency = 0;

public:
    Word()
    {}

    Word(const std::string &wordValue, size_t frequency) : wordValue(wordValue), frequency(frequency)
    {}

    const std::string &getWordValue() const
    {
        return wordValue;
    }

    void setWordValue(const std::string &wordValue)
    {
        Word::wordValue = wordValue;
    }

    size_t getFrequency() const
    {
        return frequency;
    }

    void incFrequency()
    {
        frequency++;
    }

    void setFrequency(size_t frequency)
    {
        Word::frequency = frequency;
    }

    friend std::ostream &operator<<(std::ostream &os, const Word &word)
    {
        os << "wordValue: " << word.wordValue << " frequency: " << word.frequency;
        return os;
    }

};


#endif //BOM_TEXTPARSER_WORD_H
