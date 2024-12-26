package cool.ast.expression;

import cool.ast.ASTVisitor;
import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class ImplicitCall extends Expression {
    private final ObjectId methodId;
    private final ArrayList<Expression> params;

    private Scope resolutionScope;

    public ImplicitCall(Token token, ParserRuleContext parserRuleContext,
                        ObjectId methodId, ArrayList<Expression> params) {
        super(token, parserRuleContext);
        this.methodId = methodId;
        this.params = params;
    }

    public ObjectId getMethodId() {
        return methodId;
    }

    public ArrayList<Expression> getParams() {
        return params;
    }

    public Scope getResolutionScope() {
        return resolutionScope;
    }

    public void setResolutionScope(Scope resolutionScope) {
        this.resolutionScope = resolutionScope;
    }


    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
