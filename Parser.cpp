//
// Created by walte on 11/9/2021.
//

#include <iostream>
#include <sstream>
#include "Parser.h"
#include "FSA/FSA_VerseDescription.h"
#include "FSA/FSA_Punctuation.h"
#include "FSA/FSA_Word.h"

void Parser::createFSAs()
{
    FSAs.push_back(new FSA_VerseDescription());
    FSAs.push_back(new FSA_Punctuation());
    FSAs.push_back(new FSA_Word());
}
Parser::Parser()
{
    createFSAs();
}

void Parser::clear()
{
    tokens.clear();
}

void Parser::run(std::string &input)
{
    VerseLocation location;
    size_t currTokenNumber = 0;
    while (input.size() > 0)
    {
        if (isspace(input.at(0)))
        {
            input.erase(0,1);
            continue;
        }

        size_t maxSize = 0;
        FSA* maxFSA = nullptr;
        for (auto currFSA : FSAs)
        {
            size_t currSize = currFSA->run(input);
            if (currSize > maxSize)
            {
                maxSize = currSize;
                maxFSA = currFSA;
            }
        }
        if (maxFSA == nullptr)
        {
            input.erase(0, 1);
        }
        else
        {
            switch (maxFSA->getType())
            {
                case FSA_TYPE::UNDEFINED :
                    break;
                case FSA_TYPE::PUNCTUATION :
                    break;
                case FSA_TYPE::VERSE_DESCRIPTION :
                    location.buildFromString(input.substr(0, maxSize));
                    break;
                case FSA_TYPE::WORD :
                    Token token;
                    token.setTokenNumber(currTokenNumber);
                    token.setWord(input.substr(0, maxSize));
                    token.setLocation(location);
                    tokens.insert({token.getTokenNumber(), token});
                    currTokenNumber++;
                    break;
            }
            input.erase(0, maxSize);
        }
    }

}

const std::map<size_t, Token> &Parser::getTokens() const
{
    return tokens;
}

