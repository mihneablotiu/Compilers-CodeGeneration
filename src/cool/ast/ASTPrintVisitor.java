package cool.ast;

import cool.ast.branch.Branch;
import cool.ast.classNode.ClassNode;
import cool.ast.expression.*;
import cool.ast.feature.Field;
import cool.ast.feature.Method;
import cool.ast.formal.Formal;
import cool.ast.local.Local;
import cool.ast.program.Program;
import cool.ast.type.TypeId;

public class ASTPrintVisitor implements ASTVisitor<Void> {
    private int indentationLevel = 0;

    private void printIndent(String message) {
        for (int i = 0; i < indentationLevel; i++) {
            System.out.print("  ");
        }

        System.out.println(message);
    }

    @Override
    public Void visit(Branch branch) {
        printIndent("case branch");

        indentationLevel++;

        printIndent(branch.getObjectId().getToken().getText());
        printIndent(branch.getTypeId().getToken().getText());
        branch.getBranch().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        printIndent(classNode.getToken().getText());

        indentationLevel++;

        printIndent(classNode.getClassName().getToken().getText());

        if (classNode.getParentName() != null)
            printIndent(classNode.getParentName().getToken().getText());

        classNode.getFeatures().forEach((f) -> f.accept(this));

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Arithmetic arithmetic) {
        printIndent(arithmetic.getOp().getText());

        indentationLevel++;

        arithmetic.getLeft().accept(this);
        arithmetic.getRight().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Assign assign) {
        printIndent("<-");

        indentationLevel++;

        assign.getObjectId().accept(this);
        assign.getExpr().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Block block) {
        printIndent("block");

        indentationLevel++;

        block.getExpressions().forEach((expression) -> expression.accept(this));

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Bool bool) {
        printIndent(bool.getToken().getText());
        return null;
    }

    @Override
    public Void visit(Case caseExpr) {
        printIndent(caseExpr.getToken().getText());

        indentationLevel++;

        caseExpr.getCaseExpr().accept(this);
        caseExpr.getBranches().forEach((branch) -> branch.accept(this));

        indentationLevel--;
        return null;
    }

    @Override
    public Void visit(ExplicitCall explicitCall) {
        printIndent(".");

        indentationLevel++;

        explicitCall.getDispatchExpr().accept(this);

        if (explicitCall.getClassType() != null)
            printIndent(explicitCall.getClassType().getToken().getText());

        explicitCall.getMethodId().accept(this);
        explicitCall.getParams().forEach((param) -> param.accept(this));

        indentationLevel--;
        return null;
    }

    @Override
    public Void visit(If ifExpr) {
        printIndent(ifExpr.getToken().getText());

        indentationLevel++;

        ifExpr.getCond().accept(this);
        ifExpr.getThenBranch().accept(this);
        ifExpr.getElseBranch().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(ImplicitCall implicitCall) {
        printIndent("implicit dispatch");

        indentationLevel++;

        printIndent(implicitCall.getToken().getText());

        implicitCall.getParams().forEach((param) -> param.accept(this));

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Int intExpr) {
        printIndent(intExpr.getToken().getText());
        return null;
    }

    @Override
    public Void visit(Let let) {
        printIndent(let.getToken().getText());

        indentationLevel++;

        let.getLocals().forEach((local) -> local.accept(this));
        let.getLetExpr().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(New newExpr) {
        printIndent(newExpr.getToken().getText());

        indentationLevel++;

        printIndent(newExpr.getTypeId().getToken().getText());

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Logical logical) {
        printIndent(logical.getOp().getText());

        indentationLevel++;

        logical.getLeft().accept(this);
        logical.getRight().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(ObjectId objectId) {
        printIndent(objectId.getToken().getText());
        return null;
    }

    @Override
    public Void visit(Paren paren) {
        paren.getExpr().accept(this);
        return null;
    }

    @Override
    public Void visit(Str str) {
        printIndent(str.getToken().getText());
        return null;
    }

    @Override
    public Void visit(Unary unary) {
        printIndent(unary.getToken().getText());

        indentationLevel++;

        unary.getExpr().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(While whileExpr) {
        printIndent(whileExpr.getToken().getText());

        indentationLevel++;

        whileExpr.getCondExpr().accept(this);
        whileExpr.getInsideExpr().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Field field) {
        printIndent("attribute");

        indentationLevel++;

        printIndent(field.getFieldId().getToken().getText());
        printIndent(field.getTypeId().getToken().getText());

        if (field.getInitialExpr() != null)
            field.getInitialExpr().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Method method) {
        printIndent("method");

        indentationLevel++;

        printIndent(method.getMethodId().getToken().getText());

        method.getFormals().forEach((formal) -> formal.accept(this));
        printIndent(method.getReturnType().getToken().getText());

        method.getInsideExpr().accept(this);

        indentationLevel--;
        return null;
    }

    @Override
    public Void visit(Local local) {
        printIndent("local");

        indentationLevel++;

        printIndent(local.getObjectId().getToken().getText());
        printIndent(local.getTypeId().getToken().getText());

        if (local.getAssignExpr() != null)
            local.getAssignExpr().accept(this);

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Formal formal) {
        printIndent("formal");

        indentationLevel++;

        printIndent(formal.getObjectId().getToken().getText());
        printIndent(formal.getTypeId().getToken().getText());

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(Program program) {
        printIndent("program");

        indentationLevel++;

        program.getClasses().forEach((currentClass) -> currentClass.accept(this));

        indentationLevel--;

        return null;
    }

    @Override
    public Void visit(TypeId typeId) {
        return null;
    }
}
