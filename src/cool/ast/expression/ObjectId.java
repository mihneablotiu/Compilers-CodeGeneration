package cool.ast.expression;

import cool.ast.ASTVisitor;
import cool.structures.ObjectIdSymbol;
import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class ObjectId extends Expression {
    private Scope resolutionScope;
    private ObjectIdSymbol objectIdSymbol;

    public ObjectId(Token token, ParserRuleContext parserRuleContext) {
        super(token, parserRuleContext);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Scope getResolutionScope() {
        return resolutionScope;
    }

    public void setResolutionScope(Scope resolutionScope) {
        this.resolutionScope = resolutionScope;
    }

    public ObjectIdSymbol getObjectIdSymbol() {
        return objectIdSymbol;
    }

    public void setObjectIdSymbol(ObjectIdSymbol objectIdSymbol) {
        this.objectIdSymbol = objectIdSymbol;
    }
}
