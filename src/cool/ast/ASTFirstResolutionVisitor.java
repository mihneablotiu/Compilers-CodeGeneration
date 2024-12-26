package cool.ast;

import cool.ast.ASTVisitor;
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

import java.util.Arrays;
import java.util.List;

public class ASTFirstResolutionVisitor implements ASTVisitor<Void> {

    @Override
    public Void visit(Branch branch) {
        if (branch.getBranchSymbol() != null) {
            String branchType = branch.getTypeId().getToken().getText();
            ClassSymbol branchClassSymbol = (ClassSymbol) SymbolTable.globals.lookup(branchType, false);

            if (branchClassSymbol == null && !branchType.equals("SELF_TYPE")) {
                SymbolTable.error(branch.getParserRuleContext(), branch.getTypeId().getToken(), "Case variable "
                        + branch.getObjectId().getToken().getText() + " has undefined type " + branchType);
                return null;
            } else {
                ClassSymbolWrapper classSymbolWrapper = new ClassSymbolWrapper(branchClassSymbol, false);
                branch.getBranchSymbol().setTypeSymbol(classSymbolWrapper);
            }
        }

        branch.getBranch().accept(this);

        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        if (classNode.getParentName() != null) {
            String parentName = classNode.getParentName().getToken().getText();
            List<String> illegalParents = Arrays.asList("Int", "String", "Bool", "SELF_TYPE", "Object");
            if (illegalParents.contains(parentName)) {
                SymbolTable.error(classNode.getParserRuleContext(), classNode.getParentName().getToken(), "Class "
                        + classNode.getClassName().getToken().getText() + " has illegal parent " + parentName);

                return null;
            }

            if (SymbolTable.globals.lookup(parentName, false) == null) {
                SymbolTable.error(classNode.getParserRuleContext(), classNode.getParentName().getToken(),
                        "Class " + classNode.getClassName().getToken().getText() + " has undefined parent " + parentName);

                return null;
            }

            classNode.getClassSymbolWrapper().getClassSymbol().setDirectParent((ClassSymbol) SymbolTable.globals.lookup(parentName, false));
        } else {
            if (classNode.getClassSymbolWrapper() != null) {
                classNode.getClassSymbolWrapper().getClassSymbol().setDirectParent((ClassSymbol) SymbolTable.globals.lookup("Object", false));
            }
        }

        classNode.getFeatures().forEach(feature -> feature.accept(this));

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
        caseExpr.getBranches().forEach(branch -> branch.accept(this));

        return null;
    }

    @Override
    public Void visit(ExplicitCall explicitCall) {
        explicitCall.getDispatchExpr().accept(this);
        explicitCall.getParams().forEach(param -> param.accept(this));

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
        Scope resolutionScope = implicitCall.getResolutionScope();

        if (resolutionScope != null) {
            FieldSymbol selfSymbol = (FieldSymbol) resolutionScope.lookup("self", true);
            ClassSymbolWrapper selfClassSymbolWrapper = selfSymbol.getTypeSymbol();
            ClassSymbol selfClassSymbol = selfClassSymbolWrapper.getClassSymbol();


            MethodSymbol methodSymbol = (MethodSymbol) selfClassSymbol.lookupMethod(implicitCall.getMethodId().getToken().getText());
            if (methodSymbol == null) {
                SymbolTable.error(implicitCall.getParserRuleContext(), implicitCall.getMethodId().getToken(),
                        "Undefined method " + implicitCall.getMethodId().getToken().getText() + " in class " + selfClassSymbol.getName());
                return null;
            }
        }

        implicitCall.getParams().forEach(param -> param.accept(this));

        return null;
    }

    @Override
    public Void visit(Int intExpr) {
        return null;
    }

    @Override
    public Void visit(Let let) {
        let.getLocals().forEach(local -> local.accept(this));
        let.getLetExpr().accept(this);

        return null;
    }

    @Override
    public Void visit(New newExpr) {
        ClassSymbol newExprClassSymbol = (ClassSymbol) SymbolTable.globals.lookup(newExpr.getTypeId().getToken().getText(), false);

        if (newExprClassSymbol == null && !newExpr.getTypeId().getToken().getText().equals("SELF_TYPE")) {
            SymbolTable.error(newExpr.getParserRuleContext(), newExpr.getTypeId().getToken(),
                    "new is used with undefined type " + newExpr.getTypeId().getToken().getText());
            return null;
        } else {
            ClassSymbolWrapper classSymbolWrapper;

            if (newExpr.getTypeId().getToken().getText().equals("SELF_TYPE")) {
                Scope initialScope = newExpr.getResolutionScope();

                while (initialScope != null && !(initialScope instanceof ClassSymbol)) {
                    initialScope = initialScope.getParent();
                }

                classSymbolWrapper = new ClassSymbolWrapper((ClassSymbol) initialScope, true);

            } else {
                classSymbolWrapper = new ClassSymbolWrapper(newExprClassSymbol, false);
            }

            newExpr.setClassSymbolWrapper(classSymbolWrapper);
        }

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
        if (objectId.getResolutionScope() != null) {
            if (objectId.getResolutionScope().lookup(objectId.getToken().getText(), true) == null) {
                SymbolTable.error(objectId.getParserRuleContext(), objectId.getToken(), "Undefined identifier " + objectId.getToken().getText());
                return null;
            } else {
                Symbol symbol = objectId.getResolutionScope().lookup(objectId.getToken().getText(), true);

                if (symbol instanceof LocalSymbol) {
                    objectId.getObjectIdSymbol().setTypeSymbol(((LocalSymbol) symbol).getTypeSymbol());
                    objectId.getObjectIdSymbol().setIntroducingScope(((LocalSymbol) symbol));
                } else if (symbol instanceof FieldSymbol) {
                    objectId.getObjectIdSymbol().setTypeSymbol(((FieldSymbol) symbol).getTypeSymbol());
                    objectId.getObjectIdSymbol().setIntroducingScope(((FieldSymbol) symbol).getIntroducingClass());
                } else if (symbol instanceof BranchSymbol) {
                    objectId.getObjectIdSymbol().setTypeSymbol(((BranchSymbol) symbol).getTypeSymbol());
                    objectId.getObjectIdSymbol().setIntroducingScope(((BranchSymbol) symbol));
                } else if (symbol instanceof FormalSymbol) {
                    objectId.getObjectIdSymbol().setTypeSymbol(((FormalSymbol) symbol).getTypeSymbol());
                    objectId.getObjectIdSymbol().setIntroducingScope(((FormalSymbol) symbol).getIntroducingMethod());
                }
            }
        }

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
        if (field.getFieldSymbol() != null) {
            String fieldType = field.getTypeId().getToken().getText();
            ClassSymbol fieldClassSymbol = (ClassSymbol) SymbolTable.globals.lookup(fieldType, false);

            if (fieldClassSymbol == null && !fieldType.equals("SELF_TYPE")) {
                SymbolTable.error(field.getParserRuleContext(), field.getTypeId().getToken(), "Class " + field.getClassSymbol().getName()
                        + " has attribute " + field.getFieldId().getToken().getText() + " with undefined type " + fieldType);
                return null;
            } else {
                ClassSymbolWrapper classSymbolWrapper;

                if (fieldType.equals("SELF_TYPE")) {
                    classSymbolWrapper = new ClassSymbolWrapper(field.getClassSymbol(), true);
                } else {
                    classSymbolWrapper = new ClassSymbolWrapper(fieldClassSymbol, false);
                }

                field.getFieldSymbol().setTypeSymbol(classSymbolWrapper);
            }
        }

        if (field.getInitialExpr() != null) {
            field.getInitialExpr().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Method method) {
        if (method.getMethodSymbol() != null) {
            String returnType = method.getReturnType().getToken().getText();
            ClassSymbol returnClassSymbol = (ClassSymbol) SymbolTable.globals.lookup(returnType, false);

            if (returnClassSymbol == null && !returnType.equals("SELF_TYPE")) {
                SymbolTable.error(method.getParserRuleContext(), method.getReturnType().getToken(), "Class " + method.getClassSymbol().getName()
                        + " has method " + method.getMethodId().getToken().getText() + " with undefined return type " + returnType);
                return null;
            } else {
                ClassSymbolWrapper classSymbolWrapper;

                if (returnType.equals("SELF_TYPE")) {
                    classSymbolWrapper = new ClassSymbolWrapper(method.getClassSymbol(), true);
                } else {
                    classSymbolWrapper = new ClassSymbolWrapper(returnClassSymbol, false);
                }

                method.getMethodSymbol().setTypeSymbol(classSymbolWrapper);
            }

            ClassSymbol parentClassSymbol = (ClassSymbol) method.getClassSymbol().getDirectParent();
            if (parentClassSymbol != null) {
                MethodSymbol parentMethodSymbol = (MethodSymbol) parentClassSymbol.lookup(method.getMethodId().getToken().getText(), false);

                if (parentMethodSymbol != null && method.getFormals().size() != parentMethodSymbol.getMethod().getFormals().size()) {
                    SymbolTable.error(method.getParserRuleContext(), method.getMethodId().getToken(), "Class " + method.getClassSymbol().getName()
                            + " overrides method " + method.getMethodId().getToken().getText() + " with different number of formal parameters");
                    return null;
                }

                if (parentMethodSymbol != null) {
                    for (int i = 0; i < method.getFormals().size(); i++) {
                        Formal formal = method.getFormals().get(i);
                        Formal parentFormal = parentMethodSymbol.getMethod().getFormals().get(i);

                        if (formal.getTypeId() != null && parentFormal.getTypeId() != null) {
                            if (!formal.getTypeId().getToken().getText().equals(parentFormal.getTypeId().getToken().getText())) {
                                SymbolTable.error(method.getParserRuleContext(), formal.getTypeId().getToken(),
                                        "Class " + method.getClassSymbol().getName() + " overrides method " + method.getMethodId().getToken().getText()
                                                + " but changes type of formal parameter " + formal.getObjectId().getToken().getText() + " from "
                                                + parentFormal.getTypeId().getToken().getText() + " to " + formal.getTypeId().getToken().getText());
                                return null;
                            }
                        }
                    }

                    if (method.getReturnType() != null && parentMethodSymbol.getMethod().getReturnType() != null) {
                        if (!method.getReturnType().getToken().getText().equals(parentMethodSymbol.getMethod().getReturnType().getToken().getText())) {
                            SymbolTable.error(method.getParserRuleContext(), method.getReturnType().getToken(),
                                    "Class " + method.getClassSymbol().getName() + " overrides method " + method.getMethodId().getToken().getText()
                                            + " but changes return type from " + parentMethodSymbol.getMethod().getReturnType().getToken().getText()
                                            + " to " + method.getReturnType().getToken().getText());
                            return null;
                        }
                    }
                }
            }
        }

        method.getFormals().forEach(formal -> formal.accept(this));
        method.getInsideExpr().accept(this);

        return null;
    }

    @Override
    public Void visit(Local local) {
        if (local.getLocalSymbol() != null) {
            String localType = local.getTypeId().getToken().getText();
            ClassSymbol localClassSymbol = (ClassSymbol) SymbolTable.globals.lookup(localType, false);

            if (localClassSymbol == null && !localType.equals("SELF_TYPE")) {
                SymbolTable.error(local.getParserRuleContext(), local.getTypeId().getToken(),
                        "Let variable " + local.getObjectId().getToken().getText() + " has undefined type " + localType);
                return null;
            } else {
                ClassSymbolWrapper classSymbolWrapper;

                if (localType.equals("SELF_TYPE")) {
                    Scope initialScope = local.getResolutionScope();

                    while (initialScope != null && !(initialScope instanceof ClassSymbol)) {
                        initialScope = initialScope.getParent();
                    }

                    classSymbolWrapper = new ClassSymbolWrapper((ClassSymbol) initialScope, true);
                } else {
                    classSymbolWrapper = new ClassSymbolWrapper(localClassSymbol, false);
                }

                local.getLocalSymbol().setTypeSymbol(classSymbolWrapper);
            }
        }

        if (local.getAssignExpr() != null) {
            local.getAssignExpr().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Formal formal) {
        if (formal.getFormalSymbol() != null) {
            String returnType = formal.getTypeId().getToken().getText();
            ClassSymbol returnClassSymbol = (ClassSymbol) SymbolTable.globals.lookup(returnType, false);

            if (returnClassSymbol == null) {
                SymbolTable.error(formal.getParserRuleContext(), formal.getTypeId().getToken(), "Method " + formal.getMethodSymbol().getName()
                        + " of class " + formal.getMethodSymbol().getMethod().getClassSymbol().getName() + " has formal parameter "
                        + formal.getFormalSymbol().getName() + " with undefined type " + returnType);
                return null;
            } else {
                ClassSymbolWrapper classSymbolWrapper = new ClassSymbolWrapper(returnClassSymbol, false);
                formal.getFormalSymbol().setTypeSymbol(classSymbolWrapper);
            }
        }

        return null;
    }

    @Override
    public Void visit(Program program) {
        program.getClasses().forEach(classNode -> classNode.accept(this));

        return null;
    }

    @Override
    public Void visit(TypeId typeId) {
        return null;
    }
}
