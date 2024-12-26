package cool.ast.expression;

import cool.ast.ASTNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class Expression extends ASTNode {
    public Expression(Token token, ParserRuleContext parserRuleContext) {
        super(token, parserRuleContext);
    }


}
