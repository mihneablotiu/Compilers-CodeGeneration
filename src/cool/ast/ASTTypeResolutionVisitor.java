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

public class ASTTypeResolutionVisitor implements ASTVisitor<ClassSymbolWrapper> {

    @Override
    public ClassSymbolWrapper visit(Branch branch) {
        if (branch.getBranchSymbol() != null) {
            ClassSymbolWrapper computedReturnType = branch.getBranch().accept(this);
            branch.setComputedReturnType(computedReturnType);

            return computedReturnType;
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(ClassNode classNode) {
        classNode.setComputedReturnType(classNode.getClassSymbolWrapper());
        classNode.getFeatures().forEach(feature -> feature.accept(this));

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Arithmetic arithmetic) {
        ClassSymbolWrapper left = arithmetic.getLeft().accept(this);
        ClassSymbolWrapper right = arithmetic.getRight().accept(this);

        if (left != null && right != null) {
            ClassSymbol rightType = right.getClassSymbol();
            ClassSymbol leftType = left.getClassSymbol();
            ClassSymbol intClass = (ClassSymbol) SymbolTable.globals.lookup("Int", false);

            if (leftType != intClass) {
                if (left.isSelfType()) {
                    SymbolTable.error(arithmetic.getParserRuleContext(), arithmetic.getLeft().getToken(),
                            "Operand of " + arithmetic.getOp().getText() + " has type SELF_TYPE instead of Int");
                } else {
                    SymbolTable.error(arithmetic.getParserRuleContext(), arithmetic.getLeft().getToken(),
                            "Operand of " + arithmetic.getOp().getText() + " has type " + leftType.getName() + " instead of Int");

                }

                ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(intClass, false);
                arithmetic.setComputedReturnType(computedReturnType);

                return computedReturnType;
            } else if (rightType != intClass) {
                if (right.isSelfType()) {
                    SymbolTable.error(arithmetic.getParserRuleContext(), arithmetic.getRight().getToken(),
                            "Operand of " + arithmetic.getOp().getText() + " has type SELF_TYPE instead of Int");
                } else {
                    SymbolTable.error(arithmetic.getParserRuleContext(), arithmetic.getRight().getToken(),
                            "Operand of " + arithmetic.getOp().getText() + " has type " + rightType.getName() + " instead of Int");
                }

                ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(intClass, false);
                arithmetic.setComputedReturnType(computedReturnType);

                return computedReturnType;
            }

            arithmetic.setComputedReturnType(left);
            return left;
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Assign assign) {
        ClassSymbolWrapper exprType = assign.getExpr().accept(this);

        if (exprType != null) {
            ClassSymbol exprClass = exprType.getClassSymbol();

            if (assign.getObjectId().getObjectIdSymbol() != null) {
                ClassSymbolWrapper objectIdType = assign.getObjectId().getObjectIdSymbol().getTypeSymbol();

                if (objectIdType != null) {
                    ClassSymbol objectIdClass = objectIdType.getClassSymbol();

                    if (!exprClass.isChildOf(objectIdClass) || (objectIdType.isSelfType() && !exprType.isSelfType())) {
                        if (exprType.isSelfType()) {
                            SymbolTable.error(assign.getParserRuleContext(), assign.getExpr().getToken(),
                                    "Type SELF_TYPE of assigned expression is incompatible with declared type "
                                            + objectIdClass.getName() + " of identifier " + assign.getObjectId().getToken().getText());
                        } else if (objectIdType.isSelfType()) {
                            SymbolTable.error(assign.getParserRuleContext(), assign.getExpr().getToken(),
                                    "Type " + exprClass.getName() + " of assigned expression is incompatible with declared type "
                                            + "SELF_TYPE of identifier " + assign.getObjectId().getToken().getText());
                        } else {
                            SymbolTable.error(assign.getParserRuleContext(), assign.getExpr().getToken(),
                                    "Type " + exprClass.getName() + " of assigned expression is incompatible with declared type "
                                            + objectIdClass.getName() + " of identifier " + assign.getObjectId().getToken().getText());
                        }
                    }
                }
            }

            assign.setComputedReturnType(exprType);
            return exprType;
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Block block) {
        for (int i = 0; i < block.getExpressions().size() - 1; i++) {
            block.getExpressions().get(i).accept(this);
        }

        ClassSymbolWrapper computedReturnType = block.getExpressions().get(block.getExpressions().size() - 1).accept(this);
        block.setComputedReturnType(computedReturnType);

        return computedReturnType;
    }

    @Override
    public ClassSymbolWrapper visit(Bool bool) {
        ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper((ClassSymbol) SymbolTable.globals.lookup("Bool", false), false);
        bool.setComputedReturnType(computedReturnType);

        return computedReturnType;
    }

    @Override
    public ClassSymbolWrapper visit(Case caseExpr) {
        ClassSymbol finalType = null;

        for (Branch branch : caseExpr.getBranches()) {
            ClassSymbolWrapper branchType = branch.accept(this);

            if (branchType != null && branchType.getClassSymbol() != null) {
                ClassSymbol branchClass = branchType.getClassSymbol();

                if (finalType == null) {
                    finalType = branchClass;
                } else {
                    int finalTypeParents = finalType.countParents();
                    int branchTypeParents = branchClass.countParents();

                    if (finalTypeParents > branchTypeParents) {
                        finalType = finalType.leastUpperBound(branchClass);
                    } else {
                        finalType = branchClass.leastUpperBound(finalType);
                    }
                }
            }
        }

        ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(finalType, false);
        caseExpr.setComputedReturnType(computedReturnType);

        return computedReturnType;
    }

    @Override
    public ClassSymbolWrapper visit(ExplicitCall explicitCall) {
        ClassSymbolWrapper dispatchExprType = explicitCall.getDispatchExpr().accept(this);

        if (dispatchExprType != null) {
            if (explicitCall.getClassType() == null) {
                MethodSymbol methodSymbol = (MethodSymbol) dispatchExprType.getClassSymbol().lookupMethod(explicitCall.getMethodId().getToken().getText());

                if (methodSymbol == null) {
                    SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getMethodId().getToken(), "Undefined method " +
                            explicitCall.getMethodId().getToken().getText() + " in class " + dispatchExprType.getClassSymbol().getName());

                    return null;
                } else {
                    if (methodSymbol.getMethod().getFormals().size() != explicitCall.getParams().size()) {
                        SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getMethodId().getToken(), "Method " +
                                explicitCall.getMethodId().getToken().getText() + " of class " + dispatchExprType.getClassSymbol().getName() + " is applied to wrong number of arguments");

                        return null;
                    }

                    for (int i = 0; i < explicitCall.getParams().size(); i++) {
                        ClassSymbolWrapper paramType = explicitCall.getParams().get(i).accept(this);
                        ClassSymbol paramClass = paramType.getClassSymbol();
                        ClassSymbol formalClass = methodSymbol.getMethod().getFormals().get(i).getFormalSymbol().getTypeSymbol().getClassSymbol();

                        if (!paramClass.isChildOf(formalClass)) {
                            SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getParams().get(i).getToken(),
                                    "In call to method " + methodSymbol.getName() + " of class " + dispatchExprType.getClassSymbol().getName() + ", actual type " +
                                            paramClass.getName() + " of formal parameter " + methodSymbol.getMethod().getFormals().get(i).getFormalSymbol().getName() +
                                            " is incompatible with declared type " + formalClass.getName());

                            return null;
                        }
                    }

                    if (methodSymbol.getTypeSymbol().isSelfType()) {
                        explicitCall.setComputedReturnType(dispatchExprType);
                        return dispatchExprType;
                    } else {
                        explicitCall.setComputedReturnType(methodSymbol.getTypeSymbol());
                        return methodSymbol.getTypeSymbol();
                    }
                }
            } else {
                if (explicitCall.getClassType().getToken().getText().equals("SELF_TYPE")) {
                    SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getClassType().getToken(),
                            "Type of static dispatch cannot be SELF_TYPE");

                    return null;
                }

                ClassSymbol classType = (ClassSymbol) SymbolTable.globals.lookup(explicitCall.getClassType().getToken().getText(), false);
                if (classType == null) {
                    SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getClassType().getToken(),
                            "Type " + explicitCall.getClassType().getToken().getText() + " of static dispatch is undefined");

                    return null;
                }

                if (!dispatchExprType.getClassSymbol().isChildOf(classType)) {
                    SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getClassType().getToken(), "Type " +
                            classType.getName() + " of static dispatch is not a superclass of type " + dispatchExprType.getClassSymbol().getName());

                    return null;
                }

                MethodSymbol methodSymbol = (MethodSymbol) classType.lookupMethod(explicitCall.getMethodId().getToken().getText());

                if (methodSymbol == null) {
                    SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getMethodId().getToken(), "Undefined method " +
                            explicitCall.getMethodId().getToken().getText() + " in class " + classType.getName());

                    return null;
                } else {
                    if (methodSymbol.getMethod().getFormals().size() != explicitCall.getParams().size()) {
                        SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getMethodId().getToken(),
                                "Method " + explicitCall.getMethodId().getToken().getText() + " of class "
                                        + dispatchExprType.getClassSymbol().getName() + " is applied to wrong number of arguments");

                        return null;
                    }

                    for (int i = 0; i < explicitCall.getParams().size(); i++) {
                        ClassSymbolWrapper paramType = explicitCall.getParams().get(i).accept(this);
                        ClassSymbol paramClass = paramType.getClassSymbol();
                        ClassSymbol formalClass = methodSymbol.getMethod().getFormals().get(i).getFormalSymbol().getTypeSymbol().getClassSymbol();

                        if (!paramClass.isChildOf(formalClass)) {
                            SymbolTable.error(explicitCall.getParserRuleContext(), explicitCall.getParams().get(i).getToken(),
                                    "In call to method " + methodSymbol.getName() + " of class " + dispatchExprType.getClassSymbol().getName() + ", actual type " +
                                            paramClass.getName() + " of formal parameter " + methodSymbol.getMethod().getFormals().get(i).getFormalSymbol().getName() +
                                            " is incompatible with declared type " + formalClass.getName());
                        }
                    }

                    if (methodSymbol.getTypeSymbol().isSelfType()) {
                        explicitCall.setComputedReturnType(dispatchExprType);
                        return dispatchExprType;
                    } else {
                        explicitCall.setComputedReturnType(methodSymbol.getTypeSymbol());
                        return methodSymbol.getTypeSymbol();
                    }
                }
            }
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(If ifExpr) {
        ClassSymbolWrapper condExprType = ifExpr.getCond().accept(this);
        ClassSymbol boolClass = (ClassSymbol) SymbolTable.globals.lookup("Bool", false);

        if (condExprType != null) {
            ClassSymbol condExprClass = condExprType.getClassSymbol();

            if (condExprClass != boolClass) {
                SymbolTable.error(ifExpr.getParserRuleContext(), ifExpr.getCond().getToken(),
                        "If condition has type " + condExprClass.getName() + " instead of Bool");
            }
        }

        ClassSymbolWrapper thenExprType = ifExpr.getThenBranch().accept(this);
        ClassSymbolWrapper elseExprType = ifExpr.getElseBranch().accept(this);

        if (thenExprType != null && elseExprType != null) {
            ClassSymbol thenExprClass = thenExprType.getClassSymbol();
            ClassSymbol elseExprClass = elseExprType.getClassSymbol();

            int thenExprParents = thenExprClass.countParents();
            int elseExprParents = elseExprClass.countParents();

            if (thenExprParents > elseExprParents) {
                boolean isSelfType = false;
                ClassSymbol result = thenExprClass.leastUpperBound(elseExprClass);
                if (result == thenExprClass && result == elseExprClass && thenExprType.isSelfType()) {
                    isSelfType = true;
                }

                ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(thenExprClass.leastUpperBound(elseExprClass), isSelfType);
                ifExpr.setComputedReturnType(computedReturnType);

                return computedReturnType;
            } else {
                boolean isSelfType = false;
                ClassSymbol result = elseExprClass.leastUpperBound(thenExprClass);
                if (result == thenExprClass && result == elseExprClass && elseExprType.isSelfType()) {
                    isSelfType = true;
                }

                ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(elseExprClass.leastUpperBound(thenExprClass), isSelfType);
                ifExpr.setComputedReturnType(computedReturnType);

                return computedReturnType;
            }
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(ImplicitCall implicitCall) {
        if (All.getResolutionScope() != null) {
            FieldSymbol selfSymbol = (FieldSymbol) implicitCall.getResolutionScope().lookup("self", true);
            ClassSymbolWrapper selfClassSymbolWrapper = selfSymbol.getTypeSymbol();
            MethodSymbol methodSymbol = (MethodSymbol) selfClassSymbolWrapper.getClassSymbol().lookupMethod(implicitCall.getMethodId().getToken().getText());

            if (methodSymbol != null) {
                if (methodSymbol.getMethod().getFormals().size() != implicitCall.getParams().size()) {
                    SymbolTable.error(implicitCall.getParserRuleContext(), implicitCall.getMethodId().getToken(),
                            "Method " + implicitCall.getMethodId().getToken().getText() + " of class "
                                    + selfClassSymbolWrapper.getClassSymbol().getName() + " is applied to wrong number of arguments");

                    return null;
                }

                for (int i = 0; i < implicitCall.getParams().size(); i++) {
                    ClassSymbolWrapper paramType = implicitCall.getParams().get(i).accept(this);
                    ClassSymbol paramClass = paramType.getClassSymbol();
                    ClassSymbol formalClass = methodSymbol.getMethod().getFormals().get(i).getFormalSymbol().getTypeSymbol().getClassSymbol();

                    if (!paramClass.isChildOf(formalClass)) {
                        SymbolTable.error(implicitCall.getParserRuleContext(), implicitCall.getParams().get(i).getToken(),
                                "In call to method " + methodSymbol.getName() + " of class " + selfClassSymbolWrapper.getClassSymbol().getName() + ", actual type " +
                                        paramClass.getName() + " of formal parameter " + methodSymbol.getMethod().getFormals().get(i).getFormalSymbol().getName() +
                                        " is incompatible with declared type " + formalClass.getName());
                    }
                }

                if (methodSymbol.getTypeSymbol().isSelfType()) {
                    implicitCall.setComputedReturnType(selfClassSymbolWrapper);
                    return selfClassSymbolWrapper;
                } else {
                    implicitCall.setComputedReturnType(methodSymbol.getTypeSymbol());
                    return methodSymbol.getTypeSymbol();
                }
            }
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Int intExpr) {
        ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper((ClassSymbol) SymbolTable.globals.lookup("Int", false), false);
        intExpr.setComputedReturnType(computedReturnType);

        return computedReturnType;
    }

    @Override
    public ClassSymbolWrapper visit(Let let) {
        let.getLocals().forEach(local -> local.accept(this));

        ClassSymbolWrapper computedReturnType = let.getLetExpr().accept(this);
        let.setComputedReturnType(computedReturnType);

        return computedReturnType;
    }

    @Override
    public ClassSymbolWrapper visit(New newExpr) {
        newExpr.setComputedReturnType(newExpr.getClassSymbolWrapper());
        return newExpr.getClassSymbolWrapper();
    }

    @Override
    public ClassSymbolWrapper visit(Logical logical) {
        ClassSymbolWrapper left = logical.getLeft().accept(this);
        ClassSymbolWrapper right = logical.getRight().accept(this);

        if (left != null && right != null) {
            ClassSymbol rightType = right.getClassSymbol();
            ClassSymbol leftType = left.getClassSymbol();
            ClassSymbol intClass = (ClassSymbol) SymbolTable.globals.lookup("Int", false);
            ClassSymbol boolClass = (ClassSymbol) SymbolTable.globals.lookup("Bool", false);
            ClassSymbol stringClass = (ClassSymbol) SymbolTable.globals.lookup("String", false);

            if (!logical.getOp().getText().equals("=")) {
                if (leftType != intClass) {
                    SymbolTable.error(logical.getParserRuleContext(), logical.getLeft().getToken(),
                            "Operand of " + logical.getOp().getText() + " has type " + leftType.getName() + " instead of Int");

                    ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(boolClass, false);
                    logical.setComputedReturnType(computedReturnType);

                    return computedReturnType;
                } else if (rightType != intClass) {
                    SymbolTable.error(logical.getParserRuleContext(), logical.getRight().getToken(),
                            "Operand of " + logical.getOp().getText() + " has type " + rightType.getName() + " instead of Int");

                    ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(boolClass, false);
                    logical.setComputedReturnType(computedReturnType);

                    return computedReturnType;
                }
            } else {
                if (leftType == intClass || leftType == boolClass || leftType == stringClass
                    || rightType == intClass || rightType == boolClass || rightType == stringClass) {
                    if (leftType != rightType) {
                        SymbolTable.error(logical.getParserRuleContext(), logical.getOp(),
                                "Cannot compare " + leftType.getName() + " with " + rightType.getName());

                        ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(boolClass, false);
                        logical.setComputedReturnType(computedReturnType);

                        return computedReturnType;
                    }
                }
            }

            ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(boolClass, false);
            logical.setComputedReturnType(computedReturnType);

            return computedReturnType;
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(ObjectId objectId) {
        if (objectId.getResolutionScope() != null) {
            Symbol symbol = objectId.getResolutionScope().lookup(objectId.getToken().getText(), true);

            if (symbol != null) {
                if (symbol instanceof LocalSymbol) {
                    objectId.getObjectIdSymbol().setTypeSymbol(((LocalSymbol) symbol).getTypeSymbol());
                } else if (symbol instanceof FieldSymbol) {
                    objectId.getObjectIdSymbol().setTypeSymbol(((FieldSymbol) symbol).getTypeSymbol());
                } else if (symbol instanceof FormalSymbol) {
                    objectId.getObjectIdSymbol().setTypeSymbol(((FormalSymbol) symbol).getTypeSymbol());
                } else if (symbol instanceof BranchSymbol) {
                    objectId.getObjectIdSymbol().setTypeSymbol(((BranchSymbol) symbol).getTypeSymbol());
                }
            }
        }

        if (objectId.getObjectIdSymbol() != null) {
            objectId.setComputedReturnType(objectId.getObjectIdSymbol().getTypeSymbol());
            return objectId.getObjectIdSymbol().getTypeSymbol();
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Paren paren) {
        ClassSymbolWrapper computedReturnType = paren.getExpr().accept(this);
        paren.setComputedReturnType(computedReturnType);

        return computedReturnType;
    }

    @Override
    public ClassSymbolWrapper visit(Str str) {
        ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper((ClassSymbol) SymbolTable.globals.lookup("String", false), false);
        str.setComputedReturnType(computedReturnType);

        return computedReturnType;
    }

    @Override
    public ClassSymbolWrapper visit(Unary unary) {
        ClassSymbol intClass = (ClassSymbol) SymbolTable.globals.lookup("Int", false);
        ClassSymbol boolClass = (ClassSymbol) SymbolTable.globals.lookup("Bool", false);
        ClassSymbolWrapper exprType = unary.getExpr().accept(this);

        if (exprType != null) {
            ClassSymbol exprClass = exprType.getClassSymbol();

            if (unary.getToken().getText().equals("not")) {
                if (exprClass != boolClass) {
                    SymbolTable.error(unary.getParserRuleContext(), unary.getExpr().getToken(),
                            "Operand of " + unary.getToken().getText() + " has type " + exprClass.getName() + " instead of Bool");

                    ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(boolClass, false);
                    unary.setComputedReturnType(computedReturnType);

                    return computedReturnType;
                }
            } else if (unary.getToken().getText().equals("isvoid")) {
                ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(boolClass, false);
                unary.setComputedReturnType(computedReturnType);

                return computedReturnType;
            } else {
                if (exprClass != intClass) {
                    SymbolTable.error(unary.getParserRuleContext(), unary.getExpr().getToken(),
                            "Operand of " + unary.getToken().getText() + " has type " + exprClass.getName() + " instead of Int");

                    ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(intClass, false);
                    unary.setComputedReturnType(computedReturnType);

                    return computedReturnType;
                }
            }

            unary.setComputedReturnType(exprType);
            return exprType;
        }

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(While whileExpr) {
        ClassSymbolWrapper condExprType = whileExpr.getCondExpr().accept(this);
        ClassSymbol objectClass = (ClassSymbol) SymbolTable.globals.lookup("Object", false);

        if (condExprType != null) {
            ClassSymbol condExprClass = condExprType.getClassSymbol();
            ClassSymbol boolClass = (ClassSymbol) SymbolTable.globals.lookup("Bool", false);

            if (condExprClass != boolClass) {
                SymbolTable.error(whileExpr.getParserRuleContext(), whileExpr.getCondExpr().getToken(),
                        "While condition has type " + condExprClass.getName() + " instead of Bool");

                ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(objectClass, false);
                whileExpr.setComputedReturnType(computedReturnType);

                return computedReturnType;
            }
        }

        ClassSymbolWrapper computedReturnType = new ClassSymbolWrapper(objectClass, false);
        whileExpr.setComputedReturnType(computedReturnType);

        return computedReturnType;
    }

    @Override
    public ClassSymbolWrapper visit(Field field) {
        if (field.getInitialExpr() != null) {
            ClassSymbolWrapper initialExprType = field.getInitialExpr().accept(this);

            if (initialExprType != null) {
                ClassSymbol initialExprClass = initialExprType.getClassSymbol();

                if (field.getFieldSymbol() != null && field.getFieldSymbol().getTypeSymbol() != null) {
                    ClassSymbolWrapper objectIdType = field.getFieldSymbol().getTypeSymbol();
                    ClassSymbol objectIdClass = objectIdType.getClassSymbol();

                    if (!initialExprClass.isChildOf(objectIdClass)) {
                        if (objectIdType.isSelfType()) {
                            SymbolTable.error(field.getParserRuleContext(), field.getInitialExpr().getToken(),
                                    "Type " + initialExprClass.getName() + " of initialization expression of attribute "
                                            + field.getFieldSymbol().getName() + " is incompatible with declared type SELF_TYPE");
                        } else if (initialExprType.isSelfType()) {
                            SymbolTable.error(field.getParserRuleContext(), field.getInitialExpr().getToken(),
                                    "Type SELF_TYPE of initialization expression of attribute "
                                            + field.getFieldSymbol().getName() + " is incompatible with declared type " + objectIdClass.getName());
                        } else {
                            SymbolTable.error(field.getParserRuleContext(), field.getInitialExpr().getToken(),
                                    "Type " + initialExprClass.getName() + " of initialization expression of attribute "
                                            + field.getFieldSymbol().getName() + " is incompatible with declared type " + objectIdClass.getName());
                        }

                        return null;
                    }
                }
            }
        }

        field.setComputedReturnType(field.getFieldSymbol().getTypeSymbol());

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Method method) {
        if (method.getMethodSymbol() != null) {
            ClassSymbolWrapper returnType = method.getInsideExpr().accept(this);

            if (returnType != null) {
                ClassSymbol returnClass = returnType.getClassSymbol();
                ClassSymbol objectIdClass = method.getMethodSymbol().getTypeSymbol().getClassSymbol();

                if (!returnClass.isChildOf(objectIdClass) || (method.getMethodSymbol().getTypeSymbol().isSelfType() && !returnType.isSelfType())) {
                    if (method.getMethodSymbol().getTypeSymbol().isSelfType()) {
                        SymbolTable.error(method.getParserRuleContext(), method.getInsideExpr().getToken(), "Type " + returnClass.getName() +
                                " of the body of method " + method.getMethodSymbol().getName() + " is incompatible with declared return type SELF_TYPE");
                    } else {
                        SymbolTable.error(method.getParserRuleContext(), method.getInsideExpr().getToken(), "Type " + returnClass.getName() +
                                " of the body of method " + method.getMethodSymbol().getName() + " is incompatible with declared return type " + objectIdClass.getName());
                    }

                    return null;
                }
            }

        }

        method.setComputedReturnType(method.getMethodSymbol().getTypeSymbol());
        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Local local) {
        if (local.getAssignExpr() != null) {
            ClassSymbolWrapper exprType = local.getAssignExpr().accept(this);

            if (exprType != null) {
                ClassSymbol exprClass = exprType.getClassSymbol();

                if (local.getLocalSymbol() != null && local.getLocalSymbol().getTypeSymbol() != null) {
                    ClassSymbolWrapper objectIdType = local.getLocalSymbol().getTypeSymbol();
                    ClassSymbol objectIdClass = objectIdType.getClassSymbol();

                    if (!exprClass.isChildOf(objectIdClass)) {
                        SymbolTable.error(local.getParserRuleContext(), local.getAssignExpr().getToken(),
                                "Type " + exprClass.getName() + " of initialization expression of identifier "
                                        + local.getLocalSymbol().getName() + " is incompatible with declared type " + objectIdClass.getName());

                        local.setComputedReturnType(objectIdType);
                        return objectIdType;
                    }
                }
            }
        }

        local.setComputedReturnType(local.getLocalSymbol().getTypeSymbol());
        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Formal formal) {
        formal.setComputedReturnType(formal.getFormalSymbol().getTypeSymbol());
        return null;
    }

    @Override
    public ClassSymbolWrapper visit(Program program) {
        program.getClasses().forEach(classNode -> classNode.accept(this));

        return null;
    }

    @Override
    public ClassSymbolWrapper visit(TypeId typeId) {
        return null;
    }
}
