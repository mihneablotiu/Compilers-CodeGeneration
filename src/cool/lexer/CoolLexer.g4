lexer grammar CoolLexer;
tokens { ERROR }

@header{
    package cool.lexer;
}

@members{
    private void raiseError(String msg) {
        setText(msg);
        setType(ERROR);
    }

    private void checkString(String str) {
        char[] initChars = str.toCharArray();
        String finalString = "";
        for (int i = 1; i < initChars.length - 1; i++) {
            if (initChars[i] == '\\') {
                if (i + 1 < initChars.length) {
                    switch (initChars[i + 1]) {
                        case 'n': finalString += "\n";
                                  break;
                        case 't': finalString += "\t";
                                  break;
                        case 'b': finalString += "\b";
                                  break;
                        case 'f': finalString += "\f";
                                  break;
                        default: finalString += initChars[i + 1];
                    }
                    i++;
                }
            } else {
                finalString += initChars[i];
            }
        }
        if (finalString.length() > 1024)
            raiseError("String constant too long");
        else
            setText(finalString);
    }
}

// Keywords
IF: [iI][fF];
THEN: [tT][hH][eE][nN];
ELSE: [eE][lL][sS][eE];
FI: [fF][iI];
CLASS: [cC][lL][aA][sS][sS];
INHERITS: [iI][nN][hH][eE][rR][iI][tT][sS];
IN: [iI][nN];
ISVOID: [iI][sS][vV][oO][iI][dD];
LET: [lL][eE][tT];
LOOP: [lL][oO][oO][pP];
POOL: [pP][oO][oO][lL];
WHILE: [wW][hH][iI][lL][eE];
CASE: [cC][aA][sS][eE];
ESAC: [eE][sS][aA][cC];
NEW: [nN][eE][wW];
OF: [oO][fF];
NOT: [nN][oO][tT];
TRUE: 't'[rR][uU][eE];
FALSE: 'f'[aA][lL][sS][eE];

// Special Characters
SEMICOLON: ';';
COLON: ':';
LBRACE: '{';
RBRACE: '}';
COMMA: ',';
ASSIGN: '<-';
DOT: '.';
AT: '@';
PLUS: '+';
MINUS: '-';
DIV: '/';
MULT: '*';
EQUAL: '=';
LT: '<';
LTE: '<=';
BRANCH: '=>';
LPAREN: '(';
RPAREN: ')';
TILDA: '~';

// String
fragment NEWLINE: '\r'? '\n';
STRING: '"'('\\"' | ('\\' NEWLINE) | ~'\u0000')*? ( '"' { checkString(getText()); }
                                          | NEWLINE { raiseError("Unterminated string constant"); }
                                          | EOF { raiseError("EOF in string constant"); });
STRING_NULL: '"'('\u0000' | '\\"' | ('\\' NEWLINE) | .)*? ( '"' { raiseError("String contains null character"); }
                                                  | NEWLINE { raiseError("Unterminated string constant"); }
                                                  | EOF { raiseError("EOF in string constant"); });

// Integers
fragment DIGIT: [0-9];
INTEGER: DIGIT+;

fragment LETTER: [a-z];
fragment CAPITALLETTER: [A-Z];

// Type Identifiers
TYPEID: CAPITALLETTER(CAPITALLETTER | LETTER | '_' | DIGIT)*;

// Object Identifiers
OBJECTID: LETTER(CAPITALLETTER | LETTER | '_' | DIGIT)*;

// Skippable
WS: [ \n\f\r\t]+ -> skip;

fragment NEW_LINE : '\r'?'\n';
LINE_COMMENT: '--' .*? (NEW_LINE | EOF) -> skip;
BLOCK_COMMENT
    : '(*'
      (BLOCK_COMMENT | .)*?
      ('*)' { skip(); } | EOF { raiseError("EOF in comment"); });

COMMENT_END: '*)' { raiseError("Unmatched *)"); };
OTHER: . { raiseError("Invalid character: " + getText()); };
