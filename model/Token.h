//
// Created by walte on 11/9/2021.
//

#ifndef BOM_TEXTPARSER_TOKEN_H
#define BOM_TEXTPARSER_TOKEN_H

#include "VerseLocation.h"
#include <map>
#include <ostream>

class Token
{
    VerseLocation location;
    std::string word;
    std::string speaker;
    std::string scribe;
    size_t tokenNumber;
public:
    const VerseLocation &getLocation() const
    {
        return location;
    }

    void setLocation(const VerseLocation &location)
    {
        Token::location = location;
    }

    const std::string &getWord() const
    {
        return word;
    }

    void setWord(const std::string &word)
    {
        Token::word = word;
    }

    const std::string &getSpeaker() const
    {
        return speaker;
    }

    void setSpeaker(const std::string &speaker)
    {
        Token::speaker = speaker;
    }

    const std::string &getScribe() const
    {
        return scribe;
    }

    void setScribe(const std::string &scribe)
    {
        Token::scribe = scribe;
    }

    size_t getTokenNumber() const
    {
        return tokenNumber;
    }

    void setTokenNumber(size_t tokenNumber)
    {
        Token::tokenNumber = tokenNumber;
    }

    friend std::ostream &operator<<(std::ostream &os, const Token &token)
    {
        os << "location: {" << token.location
           << "} word: " << token.word
           << " speaker: " << token.speaker
           << " scribe: " << token.scribe
           << " tokenNumber: " << token.tokenNumber;
        return os;
    }
};

#endif //BOM_TEXTPARSER_TOKEN_H
