package cool.ast.formal;

import cool.ast.ASTNode;
import cool.ast.ASTVisitor;
import cool.ast.expression.ObjectId;
import cool.ast.type.TypeId;
import cool.structures.FormalSymbol;
import cool.structures.MethodSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Formal extends ASTNode {
    private final ObjectId objectId;
    private final TypeId typeId;
    private MethodSymbol methodSymbol;
    private FormalSymbol formalSymbol;

    public Formal(Token token, ParserRuleContext parserRuleContext,
                  ObjectId objectId, TypeId typeId) {
        super(token, parserRuleContext);
        this.objectId = objectId;
        this.typeId = typeId;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public TypeId getTypeId() {
        return typeId;
    }

    public MethodSymbol getMethodSymbol() {
        return methodSymbol;
    }

    public void setMethodSymbol(MethodSymbol methodSymbol) {
        this.methodSymbol = methodSymbol;
    }

    public FormalSymbol getFormalSymbol() {
        return formalSymbol;
    }

    public void setFormalSymbol(FormalSymbol formalSymbol) {
        this.formalSymbol = formalSymbol;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
