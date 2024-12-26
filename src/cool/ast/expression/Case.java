package cool.ast.expression;

import cool.ast.ASTVisitor;
import cool.ast.branch.Branch;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class Case extends Expression {
    private final Expression caseExpr;
    private final ArrayList<Branch> branches;

    public Case(Token token, ParserRuleContext parserRuleContext,
                Expression caseExpr, ArrayList<Branch> branches) {
        super(token, parserRuleContext);
        this.caseExpr = caseExpr;
        this.branches = branches;
    }

    public Expression getCaseExpr() {
        return caseExpr;
    }

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
