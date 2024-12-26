package cool.ast.expression;

import cool.ast.ASTVisitor;
import cool.ast.local.Local;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class Let extends Expression {
    private final ArrayList<Local> locals;
    private final Expression letExpr;
    public Let(Token token, ParserRuleContext parserRuleContext,
               ArrayList<Local> locals, Expression letExpr) {
        super(token, parserRuleContext);
        this.locals = locals;
        this.letExpr = letExpr;
    }

    public ArrayList<Local> getLocals() {
        return locals;
    }

    public Expression getLetExpr() {
        return letExpr;
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
