package cool.ast.local;

import cool.ast.ASTNode;
import cool.ast.ASTVisitor;
import cool.ast.expression.Expression;
import cool.ast.expression.ObjectId;
import cool.ast.type.TypeId;
import cool.structures.LocalSymbol;
import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Local extends ASTNode {
    private final ObjectId objectId;
    private final TypeId typeId;
    private final Expression assignExpr;
    private LocalSymbol localSymbol;
    private Scope resolutionScope;

    public Local(Token token, ParserRuleContext parserRuleContext,
                 ObjectId objectId, TypeId typeId, Expression assignExpr) {
        super(token, parserRuleContext);
        this.objectId = objectId;
        this.typeId = typeId;
        this.assignExpr = assignExpr;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public TypeId getTypeId() {
        return typeId;
    }

    public Expression getAssignExpr() {
        return assignExpr;
    }

    public void setLocalSymbol(LocalSymbol localSymbol) {
        this.localSymbol = localSymbol;
    }

    public LocalSymbol getLocalSymbol() {
        return localSymbol;
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
