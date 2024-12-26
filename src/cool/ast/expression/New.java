package cool.ast.expression;

import cool.ast.ASTVisitor;
import cool.ast.type.TypeId;
import cool.structures.ClassSymbolWrapper;
import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class New extends Expression {
    private final TypeId typeId;
    private ClassSymbolWrapper classSymbolWrapper;

    private Scope resolutionScope;

    public New(Token token, ParserRuleContext parserRuleContext, TypeId typeId) {
        super(token, parserRuleContext);
        this.typeId = typeId;
    }

    public TypeId getTypeId() {
        return typeId;
    }

    public ClassSymbolWrapper getClassSymbolWrapper() {
        return classSymbolWrapper;
    }

    public void setClassSymbolWrapper(ClassSymbolWrapper classSymbolWrapper) {
        this.classSymbolWrapper = classSymbolWrapper;
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
