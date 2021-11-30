//
// Created by walte on 11/9/2021.
//

#ifndef BOM_TEXTPARSER_PARSER_H
#define BOM_TEXTPARSER_PARSER_H

#include "model/Speaker.h"
#include "model/Scribe.h"
#include "model/Token.h"
#include "model/Word.h"
#include "FSA/FSA.h"
#include <map>
#include <vector>

class Parser
{
private:
    void createFSAs();
    std::map<size_t, Token>  tokens;
    void clear();
public:
    Parser();
    void run(std::string& input);

    std::vector<FSA*> FSAs;

    const std::map<size_t, Token> &getTokens() const;
};


#endif //BOM_TEXTPARSER_PARSER_H
