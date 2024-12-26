package cool.ast.expression;

import cool.ast.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Unary extends Expression {
    private final Expression expr;

    public Unary(Token token, ParserRuleContext parserRuleContext, Expression expr) {
        super(token, parserRuleContext);
        this.expr = expr;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
