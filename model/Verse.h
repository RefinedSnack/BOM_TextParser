//
// Created by walte on 11/9/2021.
//

#ifndef BOM_TEXTPARSER_VERSE_H
#define BOM_TEXTPARSER_VERSE_H

#include "Token.h"
class Verse
{
    size_t chapeter;
    size_t verseNumber;
    size_t verseID;
    std::map<size_t, Token*> tokensRefs;
};


#endif //BOM_TEXTPARSER_VERSE_H
