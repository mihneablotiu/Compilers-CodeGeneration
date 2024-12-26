package cool.ast.expression;

import cool.ast.ASTVisitor;
import cool.ast.type.TypeId;
import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class ExplicitCall extends Expression {
    private final Expression dispatchExpr;
    private final TypeId classType;
    private final ObjectId methodId;
    private final ArrayList<Expression> params;

    private Scope resolutionScope;

    public ExplicitCall(Token token, ParserRuleContext parserRuleContext, Expression dispatchExpr,
                        TypeId classType, ObjectId methodId, ArrayList<Expression> params) {
        super(token, parserRuleContext);
        this.dispatchExpr = dispatchExpr;
        this.classType = classType;
        this.methodId = methodId;
        this.params = params;
    }

    public Expression getDispatchExpr() {
        return dispatchExpr;
    }

    public TypeId getClassType() {
        return classType;
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
