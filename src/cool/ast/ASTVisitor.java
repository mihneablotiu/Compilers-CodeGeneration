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

public interface ASTVisitor<T> {
    T visit(Branch branch);
    T visit(ClassNode classNode);
    T visit(Arithmetic arithmetic);
    T visit(Assign assign);
    T visit(Block block);
    T visit(Bool bool);
    T visit(Case caseExpr);
    T visit(ExplicitCall explicitCall);
    T visit(If ifExpr);
    T visit(ImplicitCall implicitCall);
    T visit(Int intExpr);
    T visit(Let let);
    T visit(New newExpr);
    T visit(Logical logical);
    T visit(ObjectId objectId);
    T visit(Paren paren);
    T visit(Str str);
    T visit(Unary unary);
    T visit(While whileExpr);
    T visit(Field field);
    T visit(Method method);
    T visit(Local local);
    T visit(Formal formal);
    T visit(Program program);
    T visit(TypeId typeId);
}
