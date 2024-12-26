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

import java.util.ArrayList;

public class ASTSecondResolutionVisitor implements ASTVisitor<Void> {
    @Override
    public Void visit(Branch branch) {
        return null;
    }

    @Override
    public Void visit(ClassNode classNode) {
        if (classNode.getClassSymbolWrapper() != null) {
            ClassSymbol classSymbol = classNode.getClassSymbolWrapper().getClassSymbol();

            if (classSymbol != null && classNode.getParentName() != null) {
                ClassSymbol parentClassSymbol = (ClassSymbol) classSymbol.getDirectParent();

                if (parentClassSymbol != null) {
                    if (parentClassSymbol.isInCycle(classSymbol)) {
                        SymbolTable.error(classNode.getParserRuleContext(), classNode.getClassName().getToken(),
                                "Inheritance cycle for class " + classNode.getClassName().getToken().getText());

                        return null;
                    }
                }
            }
        }

        ASTCodeGenVisitor.classMethods.put(classNode.getClassName().getToken().getText(), new ArrayList<>());
        ASTCodeGenVisitor.classAttributes.put(classNode.getClassName().getToken().getText(), new ArrayList<>());

        classNode.getClassSymbolWrapper().getClassSymbol().populateFields(classNode.getClassName().getToken().getText());
        classNode.getClassSymbolWrapper().getClassSymbol().populateMethods(classNode.getClassName().getToken().getText());

        classNode.getFeatures().forEach(feature -> feature.accept(this));

        return null;
    }

    @Override
    public Void visit(Arithmetic arithmetic) {
        return null;
    }

    @Override
    public Void visit(Assign assign) {
        return null;
    }

    @Override
    public Void visit(Block block) {
        return null;
    }

    @Override
    public Void visit(Bool bool) {
        return null;
    }

    @Override
    public Void visit(Case caseExpr) {
        return null;
    }

    @Override
    public Void visit(ExplicitCall explicitCall) {
        return null;
    }

    @Override
    public Void visit(If ifExpr) {
        return null;
    }

    @Override
    public Void visit(ImplicitCall implicitCall) {
        return null;
    }

    @Override
    public Void visit(Int intExpr) {
        return null;
    }

    @Override
    public Void visit(Let let) {
        return null;
    }

    @Override
    public Void visit(New newExpr) {
        return null;
    }

    @Override
    public Void visit(Logical logical) {
        return null;
    }

    @Override
    public Void visit(ObjectId objectId) {
        return null;
    }

    @Override
    public Void visit(Paren paren) {
        return null;
    }

    @Override
    public Void visit(Str str) {
        return null;
    }

    @Override
    public Void visit(Unary unary) {
        return null;
    }

    @Override
    public Void visit(While whileExpr) {
        return null;
    }

    @Override
    public Void visit(Field field) {
        if (field.getFieldSymbol() != null) {
            if (field.getClassSymbol().getDirectParent() != null) {
                ClassSymbol parentClassSymbol = (ClassSymbol) SymbolTable.globals.lookup(((ClassSymbol) field.getClassSymbol().getDirectParent()).getName(), false);

                if (parentClassSymbol != null) {
                    FieldSymbol parentFieldSymbol = (FieldSymbol) parentClassSymbol.lookup(field.getFieldId().getToken().getText(), true);

                    if (parentFieldSymbol != null) {
                        SymbolTable.error(field.getParserRuleContext(), field.getFieldId().getToken(), "Class " + field.getClassSymbol().getName() +
                                " redefines inherited attribute " + field.getFieldId().getToken().getText());
                        return null;
                    }
                }
            }
        }

        if (field.getInitialExpr() != null) {
            field.getInitialExpr().accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Method method) {
        return null;
    }

    @Override
    public Void visit(Local local) {
        return null;
    }

    @Override
    public Void visit(Formal formal) {
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
