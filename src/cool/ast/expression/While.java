package cool.ast.expression;

import cool.ast.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class While extends Expression {
    private final Expression condExpr;
    private final Expression insideExpr;

    public While(Token token, ParserRuleContext parserRuleContext,
                 Expression condExpr, Expression insideExpr) {
        super(token, parserRuleContext);
        this.condExpr = condExpr;
        this.insideExpr = insideExpr;
    }

    public Expression getCondExpr() {
        return condExpr;
    }

    public Expression getInsideExpr() {
        return insideExpr;
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
