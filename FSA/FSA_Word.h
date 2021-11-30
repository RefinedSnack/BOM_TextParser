//
// Created by walte on 11/13/2021.
//

#ifndef BOM_TEXTPARSER_FSA_WORD_H
#define BOM_TEXTPARSER_FSA_WORD_H

#include "FSA.h"
#include "ctype.h"

class FSA_Word : public FSA
{
public:
    FSA_Word() : FSA(FSA_TYPE::WORD)
    {

    }
private:
    void S0() override
    {
        if (isOutOfBounds())
        {
            Serr();
        }
        else if (isalpha(curr()))
        {
            advance();
            S1();
        }
    }
    void S1()
    {
        if (isOutOfBounds())
        {
            return;
        }
        else if (isalpha(curr()))
        {
            advance();
            S1();
        }
        else
        {
            return;
        }
    }
};


#endif //BOM_TEXTPARSER_FSA_WORD_H
