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
import cool.structures.*;

public class ASTDefinitionVisitor implements ASTVisitor<Void> {
    Scope currentScope = null;

    @Override
    public Void visit(Branch branch) {
        if (branch.getObjectId().getToken().getText().equals("self")) {
            SymbolTable.error(branch.getParserRuleContext(),
                    branch.getObjectId().getToken(), "Case variable has illegal name self");
            return null;
        }

        if (branch.getTypeId().getToken().getText().equals("SELF_TYPE")) {
            SymbolTable.error(branch.getParserRuleContext(),
                    branch.getTypeId().getToken(), "Case variable " + branch.getObjectId().getToken().getText() + " has illegal type SELF_TYPE");
            return null;
        }

        branch.getBranch().accept(this);

        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        if (classNode.getClassName().getToken().getText().equals("SELF_TYPE")) {
            SymbolTable.error(classNode.getParserRuleContext(), classNode.getClassName().getToken(), "Class has illegal name SELF_TYPE");
            return null;
        }

        ClassSymbol classSymbol = new ClassSymbol(classNode.getClassName().getToken().getText());
        ClassSymbolWrapper classSymbolWrapper = new ClassSymbolWrapper(classSymbol, false);
        classNode.setClassSymbolWrapper(classSymbolWrapper);

        if (!currentScope.add(classSymbol)) {
            SymbolTable.error(classNode.getParserRuleContext(),
                    classNode.getClassName().getToken(), "Class " + classSymbol.getName() + " is redefined");
            return null;
        }

        currentScope = classSymbol;

        FieldSymbol selfField = new FieldSymbol("self");
        selfField.setTypeSymbol(new ClassSymbolWrapper(classSymbol, true));
        classSymbol.addField(selfField);

        classNode.getFeatures().forEach(feature -> feature.accept(this));

        currentScope = currentScope.getParent();

        return null;
    }

    @Override
    public Void visit(Arithmetic arithmetic) {
        arithmetic.getLeft().accept(this);
        arithmetic.getRight().accept(this);

        return null;
    }

    @Override
    public Void visit(Assign assign) {
        if (assign.getObjectId().getToken().getText().equals("self")) {
            SymbolTable.error(assign.getParserRuleContext(), assign.getObjectId().getToken(),
                    "Cannot assign to self");
            return null;
        }

        assign.getObjectId().accept(this);
        assign.getExpr().accept(this);

        return null;
    }

    @Override
    public Void visit(Block block) {
        block.getExpressions().forEach(expr -> expr.accept(this));
        return null;
    }

    @Override
    public Void visit(Bool bool) {
        return null;
    }

    @Override
    public Void visit(Case caseExpr) {
        caseExpr.getCaseExpr().accept(this);

        for (Branch branch : caseExpr.getBranches()) {
            BranchSymbol branchSymbol = new BranchSymbol(branch.getObjectId().getToken().getText());

            branchSymbol.setParent(currentScope);
            branch.setBranchSymbol(branchSymbol);

            currentScope = branchSymbol;
            currentScope.add(branchSymbol);

            branch.accept(this);

            currentScope = currentScope.getParent();
        }

        return null;
    }

    @Override
    public Void visit(ExplicitCall explicitCall) {
        explicitCall.getParams().forEach(param -> param.accept(this));
        explicitCall.getDispatchExpr().accept(this);

        explicitCall.setResolutionScope(currentScope);

        return null;
    }

    @Override
    public Void visit(If ifExpr) {
        ifExpr.getCond().accept(this);
        ifExpr.getThenBranch().accept(this);
        ifExpr.getElseBranch().accept(this);

        return null;
    }

    @Override
    public Void visit(ImplicitCall implicitCall) {
        implicitCall.getParams().forEach(param -> param.accept(this));
        implicitCall.setResolutionScope(currentScope);

        return null;
    }

    @Override
    public Void visit(Int intExpr) {
        return null;
    }

    @Override
    public Void visit(Let let) {
        for (Local local : let.getLocals()) {
            LocalSymbol localSymbol = new LocalSymbol(local.getObjectId().getToken().getText());

            localSymbol.setParent(currentScope);
            local.setLocalSymbol(localSymbol);

            local.accept(this);

            currentScope = localSymbol;
            currentScope.add(localSymbol);
        }

        let.getLetExpr().accept(this);

        for (Local ignored : let.getLocals()) {
            currentScope = currentScope.getParent();
        }


        return null;
    }

    @Override
    public Void visit(New newExpr) {
        newExpr.setResolutionScope(currentScope);
        return null;
    }

    @Override
    public Void visit(Logical logical) {
        logical.getLeft().accept(this);
        logical.getRight().accept(this);

        return null;
    }

    @Override
    public Void visit(ObjectId objectId) {
        ObjectIdSymbol objectIdSymbol = new ObjectIdSymbol(objectId.getToken().getText());
        objectId.setResolutionScope(currentScope);
        objectId.setObjectIdSymbol(objectIdSymbol);

        return null;
    }

    @Override
    public Void visit(Paren paren) {
        return paren.getExpr().accept(this);
    }

    @Override
    public Void visit(Str str) {
        return null;
    }

    @Override
    public Void visit(Unary unary) {
        return unary.getExpr().accept(this);
    }

    @Override
    public Void visit(While whileExpr) {
        whileExpr.getCondExpr().accept(this);
        whileExpr.getInsideExpr().accept(this);

        return null;
    }

    @Override
    public Void visit(Field field) {
        ClassSymbol currentClass = (ClassSymbol) currentScope;

        if (field.getFieldId().getToken().getText().equals("self")) {
            SymbolTable.error(field.getParserRuleContext(), field.getFieldId().getToken(),
                    "Class " + currentClass.getName() + " has attribute with illegal name self");
            return null;
        }

        FieldSymbol fieldSymbol = new FieldSymbol(field.getFieldId().getToken().getText(), currentClass);

        if (!currentClass.addField(fieldSymbol)) {
            SymbolTable.error(field.getParserRuleContext(), field.getFieldId().getToken(),
                    "Class " + currentClass.getName() + " redefines attribute " + fieldSymbol.getName());
            return null;
        }

        field.setFieldSymbol(fieldSymbol);
        field.setClassSymbol(currentClass);

        if (field.getInitialExpr() != null) {
            field.getInitialExpr().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Method method) {
        MethodSymbol methodSymbol = new MethodSymbol(method.getMethodId().getToken().getText(), method);
        ClassSymbol currentClass = (ClassSymbol) currentScope;

        if (!currentClass.addMethod(methodSymbol)) {
            SymbolTable.error(method.getParserRuleContext(), method.getMethodId().getToken(),
                    "Class " + currentClass.getName() + " redefines method " + methodSymbol.getName());
            return null;
        }

        method.setMethodSymbol(methodSymbol);
        method.setClassSymbol(currentClass);
        methodSymbol.setParent(currentClass);

        currentScope = methodSymbol;

        method.getFormals().forEach(formal -> formal.accept(this));
        method.getInsideExpr().accept(this);

        currentScope = currentScope.getParent();

        return null;
    }

    @Override
    public Void visit(Local local) {
        local.setResolutionScope(currentScope);

        if (local.getObjectId().getToken().getText().equals("self")) {
            SymbolTable.error(local.getParserRuleContext(),
                    local.getObjectId().getToken(), "Let variable has illegal name self");
            return null;
        }

        if (local.getAssignExpr() != null) {
            local.getAssignExpr().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Formal formal) {
        MethodSymbol currentMethod = (MethodSymbol) currentScope;
        FormalSymbol formalSymbol = new FormalSymbol(formal.getObjectId().getToken().getText(), currentMethod);
        String typeName = formal.getTypeId().getToken().getText();

        if (formal.getObjectId().getToken().getText().equals("self")) {
            SymbolTable.error(formal.getParserRuleContext(), formal.getObjectId().getToken(),
                    "Method " + currentMethod.getName() + " of class " + currentMethod.getMethod().getClassSymbol().getName() + " has formal parameter with illegal name self");
            return null;
        }

        if (!currentMethod.add(formalSymbol)) {
            SymbolTable.error(formal.getParserRuleContext(), formal.getToken(),
                    "Method " + currentMethod.getName() + " of class " + currentMethod.getMethod().getClassSymbol().getName()
                            + " redefines formal parameter " + formalSymbol.getName());

            return null;
        }

        if (typeName.equals("SELF_TYPE")) {
            SymbolTable.error(formal.getParserRuleContext(), formal.getTypeId().getToken(),
                    "Method " + currentMethod.getName() + " of class " + currentMethod.getMethod().getClassSymbol().getName()
                            + " has formal parameter " + formalSymbol.getName() + " with illegal type SELF_TYPE");

            return null;
        }


        formal.setFormalSymbol(formalSymbol);
        formal.setMethodSymbol(currentMethod);

        return null;
    }

    @Override
    public Void visit(Program program) {
        currentScope = SymbolTable.globals;

        program.getClasses().forEach(classNode -> classNode.accept(this));

        return null;
    }

    @Override
    public Void visit(TypeId typeId) {
        return null;
    }
}
