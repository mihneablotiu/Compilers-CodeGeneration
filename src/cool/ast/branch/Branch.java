package cool.ast.branch;

import cool.ast.ASTNode;
import cool.ast.ASTVisitor;
import cool.ast.expression.Expression;
import cool.ast.expression.ObjectId;
import cool.ast.type.TypeId;
import cool.structures.BranchSymbol;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class Branch extends ASTNode {
    private final ObjectId objectId;
    private final TypeId typeId;
    private final Expression branch;
    private BranchSymbol branchSymbol;

    public Branch(Token token, ParserRuleContext parserRuleContext,
                  ObjectId objectId, TypeId typeId, Expression branch) {
        super(token, parserRuleContext);
        this.objectId = objectId;
        this.typeId = typeId;
        this.branch = branch;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public TypeId getTypeId() {
        return typeId;
    }

    public Expression getBranch() {
        return branch;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public BranchSymbol getBranchSymbol() {
        return branchSymbol;
    }

    public void setBranchSymbol(BranchSymbol branchSymbol) {
        this.branchSymbol = branchSymbol;
    }
}
