//
// Created by walte on 11/9/2021.
//

#ifndef BOM_TEXTPARSER_PARSER_H
#define BOM_TEXTPARSER_PARSER_H


#include "model/Verse.h"
#include "model/Word.h"
#include "model/Token.h"
#include "FSA/FSA.h"
#include <map>
#include <vector>
#include <ostream>

class Parser
{
private:
    void createFSAs();
    std::map<size_t, Token>  tokens;
    std::map<std::string, Word>  words;
    std::map<size_t, Verse>  verses;
    std::vector<FSA*> FSAs;
    std::string currSpeaker = "";
    std::string currScribe = "";
    void clear();
public:
    Parser();
    ~Parser()
    {
        for (auto f : FSAs)
        {
            delete f;
            f = nullptr;
        }
    }
    void run(std::string& input);



    const std::map<size_t, Token> &getTokens() const;

    const std::map<std::string, Word> &getWords() const;

    const std::map<size_t, Verse> &getVerses() const;

    void beginNewSpeaker(std::string &basicString);

    void beginNewScribe(std::string &basicString);

    void endSpeaker(std::string &basicString);

    void endScribe(std::string &basicString);
};


#endif //BOM_TEXTPARSER_PARSER_H
