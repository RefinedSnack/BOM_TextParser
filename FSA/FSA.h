//
// Created by walte on 11/13/2021.
//

#ifndef BOM_TEXTPARSER_FSA_H
#define BOM_TEXTPARSER_FSA_H

enum class FSA_TYPE
{
    UNDEFINED,
    PUNCTUATION,
    VERSE_DESCRIPTION,
    WORD
};

class FSA
{
protected:
    FSA(FSA_TYPE type)
    {
        this->type = type;
    }
    FSA_TYPE type;
    size_t index;
    size_t numRead;
    std::string input;
    virtual void S0() = 0;
    void Serr() { numRead = 0; }
    bool isOutOfBounds() { return index >= input.size(); }
    bool canPeek() { return (index+1) <= input.size(); }
    char curr() { return input.at(index); }
    bool match(char c) {return curr() == c; }
    char peek() { return input.at(index+1); }
    void advance() { index++; numRead++; }
public:
    size_t run(std::string inputStr)
    {
        input = inputStr;
        index = 0;
        numRead = 0;
        S0();
        return numRead;
    }

    FSA_TYPE getType() const
    {
        return type;
    }

};


#endif //BOM_TEXTPARSER_FSA_H
