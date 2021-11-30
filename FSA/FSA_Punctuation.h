//
// Created by walte on 11/13/2021.
//

#ifndef BOM_TEXTPARSER_FSA_PUNCTUATION_H
#define BOM_TEXTPARSER_FSA_PUNCTUATION_H


#include "FSA.h"
class FSA_Punctuation : public FSA
{
public:
    FSA_Punctuation() : FSA(FSA_TYPE::PUNCTUATION)
    {

    }
private:
    std::string punctuation = ", . \\ \? \" \' ! #";
    void S0() override
    {
        if (isOutOfBounds())
        {
            Serr();
        }
        else if (punctuation.find(curr()) != std::string::npos)
        {
            advance();
            return;
        }
    }
};


#endif //BOM_TEXTPARSER_FSA_PUNCTUATION_H
