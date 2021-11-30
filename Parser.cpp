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
    size_t currVerseID = 0;
    size_t currTokenID = 0;
    while (input.size() > 0)
    {
        if (isspace(input.at(0)))
        {
            input.erase(0, 1);
            continue;
        }

        size_t maxSize = 0;
        FSA *maxFSA = nullptr;
        if (input.at(0) == '[')
        {
            beginNewSpeaker(input);
            continue;
        }
        else if (input.at(0) == '{')
        {
            beginNewScribe(input);
            continue;
        }
        else if (input.at(0) == ']')
        {
            endSpeaker(input);
            continue;
        }
        else if (input.at(0) == '}')
        {
            endScribe(input);
            continue;
        }


        for (auto currFSA: FSAs)
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
                    std::cout << "CurrVerse Number: " << currVerseID << std::endl;
                    currVerseID++;
                    verses.insert({currVerseID, Verse(input.substr(0, maxSize), currTokenID)});
                    break;
                case FSA_TYPE::WORD :
                    //add a token
                    Token token(currTokenID, input.substr(0, maxSize), currSpeaker, currScribe, currVerseID);
                    tokens.insert({currTokenID, token});

                    //add a corresponding word
                    if (words.count(token.getWordValue()))
                    {
                        words.at(token.getWordValue()).incFrequency();
                    }
                    else
                    {
                        words.insert({token.getWordValue(), Word(token.getWordValue(), 1)});
                    }

                    currTokenID++;
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

void Parser::beginNewSpeaker(std::string &basicString)
{
    size_t stop = 0;
    while (true)
    {
        stop++;
        if (stop >= basicString.size() || basicString.at(stop) == '[')
        {
            break;
        }
    }
    currSpeaker = basicString.substr(2, stop - 3);
    basicString.erase(0, stop + 1);
    //std::cout << "Speaker = \'" << currSpeaker << "\' \n";
}

void Parser::beginNewScribe(std::string &basicString)
{
    size_t stop = 0;
    while (true)
    {
        stop++;
        if (stop >= basicString.size() || basicString.at(stop) == '{')
        {
            break;
        }
    }
    std::string currScribe = basicString.substr(2, stop - 3);
    basicString.erase(0, stop + 1);
    //std::cout << "Scribe = \'" << currScribe << "\' \n";
}

void Parser::endSpeaker(std::string &basicString)
{
    basicString.erase(0, 1);
    currSpeaker = "";
}

void Parser::endScribe(std::string &basicString)
{
    basicString.erase(0, 1);
    currScribe = "";
}

const std::map<std::string, Word> &Parser::getWords() const
{
    return words;
}

const std::map<size_t, Verse> &Parser::getVerses() const
{
    return verses;
}

