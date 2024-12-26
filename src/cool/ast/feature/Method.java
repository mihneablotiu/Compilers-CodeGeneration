package cool.ast.feature;

import cool.ast.ASTVisitor;
import cool.ast.expression.Expression;
import cool.ast.expression.ObjectId;
import cool.ast.formal.Formal;
import cool.ast.type.TypeId;
import cool.structures.ClassSymbol;
import cool.structures.MethodSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class Method extends Feature {
    private final ObjectId methodId;
    private final ArrayList<Formal> formals;
    private final TypeId returnType;
    private final Expression insideExpr;
    private MethodSymbol methodSymbol;
    private ClassSymbol classSymbol;

    public Method(Token token, ParserRuleContext parserRuleContext, ObjectId methodId,
                  ArrayList<Formal> formals, TypeId returnType, Expression insideExpr) {
        super(token, parserRuleContext);
        this.methodId = methodId;
        this.formals = formals;
        this.returnType = returnType;
        this.insideExpr = insideExpr;
    }

    public ObjectId getMethodId() {
        return methodId;
    }

    public ArrayList<Formal> getFormals() {
        return formals;
    }

    public TypeId getReturnType() {
        return returnType;
    }

    public Expression getInsideExpr() {
        return insideExpr;
    }

    public MethodSymbol getMethodSymbol() {
        return methodSymbol;
    }

    public void setMethodSymbol(MethodSymbol methodSymbol) {
        this.methodSymbol = methodSymbol;
    }

    public ClassSymbol getClassSymbol() {
        return classSymbol;
    }

    public void setClassSymbol(ClassSymbol classSymbol) {
        this.classSymbol = classSymbol;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
