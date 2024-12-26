package cool.ast.expression;

import cool.ast.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Bool extends Expression {
    public Bool(Token token, ParserRuleContext parserRuleContext) {
        super(token, parserRuleContext);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
