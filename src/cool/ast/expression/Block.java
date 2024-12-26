package cool.ast.expression;

import cool.ast.ASTVisitor;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class Block extends Expression {
    private final ArrayList<Expression> expressions;

    public Block(Token token, ParserRuleContext parserRuleContext, ArrayList<Expression> expressions) {
        super(token, parserRuleContext);
        this.expressions = expressions;
    }

    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
