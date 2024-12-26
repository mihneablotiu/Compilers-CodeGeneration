parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}
@header{
    package cool.parser;
}

program
    :   (classes+=class SEMICOLON)+
    ;

class
    : CLASS className=TYPEID (INHERITS parentName=TYPEID)? LBRACE (features+=feature SEMICOLON)* RBRACE
    ;

feature
    : methodId=OBJECTID LPAREN (params+=formal (COMMA params+=formal)*)? RPAREN COLON returnType=TYPEID LBRACE instructions=expr RBRACE    #method
    | variableId=OBJECTID COLON typeId=TYPEID (ASSIGN initialExpr=expr)?                                                                   #field
    ;

formal
    : objectId=OBJECTID COLON typeId=TYPEID
    ;

expr
    : dispatchExpr=expr (AT classExpr=TYPEID)? DOT methodId=OBJECTID LPAREN (params+=expr (COMMA params+=expr)*)? RPAREN #explicitCall
    | methodId=OBJECTID LPAREN (params+=expr (COMMA params+=expr)*)? RPAREN                                              #implicitCall
    | IF condExpr=expr THEN thenExpr=expr ELSE elseExpr=expr FI                                                          #ifExpr
    | WHILE condExpr=expr LOOP insideExpr=expr POOL                                                                      #whileExpr
    | LBRACE (insideExprs+=expr SEMICOLON)+ RBRACE                                                                       #blockExpr
    | LET localVars+=local (COMMA localVars+=local)* IN inExpr=expr                                                      #letExpr
    | CASE caseExpr=expr OF (branches+=branch)+ ESAC                                                                     #caseExpr
    | NEW typeId=TYPEID                                                                                                  #newExpr
    | TILDA expr                                                                                                         #unaryExpr
    | ISVOID expr                                                                                                        #unaryExpr
    | left=expr (op=MULT | op=DIV) right=expr                                                                            #arithmeticExpr
    | left=expr (op=PLUS | op=MINUS) right=expr                                                                          #arithmeticExpr
    | left=expr (op=LT | op=LTE | op=EQUAL) right=expr                                                                   #logicalExpr
    | NOT expr                                                                                                           #unaryExpr
    | LPAREN expr RPAREN                                                                                                 #parenExpr
    | OBJECTID                                                                                                           #objectExpr
    | INTEGER                                                                                                            #integerExpr
    | STRING                                                                                                             #stringLiteralExpr
    | val=TRUE                                                                                                           #boolExpr
    | val=FALSE                                                                                                          #boolExpr
    | objectId=OBJECTID ASSIGN expr                                                                                      #assignExpr
    ;

branch
    : objectId=OBJECTID COLON typeId=TYPEID BRANCH branchExpr=expr SEMICOLON
    ;

local
    : objectId=OBJECTID COLON typeId=TYPEID (ASSIGN assignExpr=expr)?
    ;
