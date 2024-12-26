package cool.ast.feature;

import cool.ast.ASTNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public abstract class Feature extends ASTNode {
    public Feature(Token token, ParserRuleContext parserRuleContext) {
        super(token, parserRuleContext);
    }
}
