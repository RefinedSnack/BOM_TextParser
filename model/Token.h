//
// Created by walte on 11/9/2021.
//

#ifndef BOM_TEXTPARSER_TOKEN_H
#define BOM_TEXTPARSER_TOKEN_H

#include <map>
#include <ostream>

class Token
{
    size_t tokenID = 0;
    std::string wordValue = "";
    std::string speaker = "";
    std::string scribe = "";
    std::string partOfSpeech = "";
    size_t verseID  = 0;
public:
    Token()
    {}

    Token(size_t tokenId, const std::string &wordValue, const std::string &speaker, const std::string &scribe,
          size_t verseId) : tokenID(tokenId), wordValue(wordValue), speaker(speaker), scribe(scribe), verseID(verseId)
    {}

    size_t getTokenId() const
    {
        return tokenID;
    }

    void setTokenId(size_t tokenId)
    {
        tokenID = tokenId;
    }

    const std::string &getWordValue() const
    {
        return wordValue;
    }

    void setWordValue(const std::string &wordValue)
    {
        Token::wordValue = wordValue;
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

    size_t getVerseId() const
    {
        return verseID;
    }

    void setVerseId(size_t verseId)
    {
        verseID = verseId;
    }

    friend std::ostream &operator<<(std::ostream &os, const Token &token)
    {
        os << "tokenID: " << token.tokenID
           << " wordValue: " << token.wordValue
           << " speaker: " << token.speaker
           << " scribe: " << token.scribe
           << " verseID: " << token.verseID;
        return os;
    }
};

#endif //BOM_TEXTPARSER_TOKEN_H
