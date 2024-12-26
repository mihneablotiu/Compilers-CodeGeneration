package cool.ast.expression;

import cool.ast.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Assign extends Expression {
    private final ObjectId objectId;
    private final Expression expr;

    public Assign(Token token, ParserRuleContext parserRuleContext,
                  ObjectId objectId, Expression expr) {
        super(token, parserRuleContext);
        this.objectId = objectId;
        this.expr = expr;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public Expression getExpr() {
        return expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
