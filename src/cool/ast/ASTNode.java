package cool.ast;

import cool.structures.ClassSymbolWrapper;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class ASTNode {
    private final Token token;
    private final ParserRuleContext parserRuleContext;
    private ClassSymbolWrapper computedReturnType;

    public ASTNode(Token token, ParserRuleContext parserRuleContext) {
        this.token = token;
        this.parserRuleContext = parserRuleContext;
    }

    public Token getToken() {
        return token;
    }

    public ParserRuleContext getParserRuleContext() {
        return parserRuleContext;
    }

    public ClassSymbolWrapper getComputedReturnType() {
        return computedReturnType;
    }

    public void setComputedReturnType(ClassSymbolWrapper computedReturnType) {
        this.computedReturnType = computedReturnType;
    }

    public abstract <T> T accept(ASTVisitor<T> visitor);
}
