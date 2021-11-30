//
// Created by walte on 11/13/2021.
//

#ifndef BOM_TEXTPARSER_FSA_VERSEDESCRIPTION_H
#define BOM_TEXTPARSER_FSA_VERSEDESCRIPTION_H

#include "FSA.h"

class FSA_VerseDescription : public FSA
{
public:
    FSA_VerseDescription() : FSA(FSA_TYPE::VERSE_DESCRIPTION)
    {

    }
private:
    void S0() override
    {
        if (isOutOfBounds())
        {
            Serr();
        }
        else if(match('<'))
        {
            advance();
            S1();
        }
        else
        {
            Serr();
        }
    }

    void S1()
    {
        if (isOutOfBounds())
        {
            Serr();
        }
        else if(match('>'))
        {
            advance();
            return;
        }
        else
        {
            advance();
            S1();
        }
    }

};


#endif //BOM_TEXTPARSER_FSA_VERSEDESCRIPTION_H
