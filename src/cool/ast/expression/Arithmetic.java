package cool.ast.expression;

import cool.ast.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Arithmetic extends Expression {
    private final Token op;
    private final Expression left;
    private final Expression right;

    public Arithmetic(Token token, ParserRuleContext parserRuleContext,
                      Token op, Expression left, Expression right) {
        super(token, parserRuleContext);
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public Token getOp() {
        return op;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
