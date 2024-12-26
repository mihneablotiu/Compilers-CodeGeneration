package cool.ast;

import cool.ast.branch.Branch;
import cool.ast.classNode.ClassNode;
import cool.ast.expression.*;
import cool.ast.feature.Feature;
import cool.ast.feature.Field;
import cool.ast.feature.Method;
import cool.ast.formal.Formal;
import cool.ast.local.Local;
import cool.ast.program.Program;
import cool.ast.type.TypeId;
import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;

import java.util.ArrayList;

public class ASTConstructionVisitor extends CoolParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        return new Program(ctx.start, ctx,
                new ArrayList<>(ctx.classes.stream().map((currentClass) -> (ClassNode) visit(currentClass)).toList()));
    }

    @Override
    public ASTNode visitClass(CoolParser.ClassContext ctx) {
        return new ClassNode(ctx.start, ctx, new TypeId(ctx.className, ctx),
                ctx.parentName != null ? new TypeId(ctx.parentName, ctx) : null,
                new ArrayList<>(ctx.features.stream().map((feature) -> (Feature) visit(feature)).toList()));
    }

    @Override
    public ASTNode visitMethod(CoolParser.MethodContext ctx) {
        return new Method(ctx.start, ctx, new ObjectId(ctx.methodId, ctx),
                new ArrayList<>(ctx.params.stream().map((param) -> (Formal) visit(param)).toList()),
                new TypeId(ctx.returnType, ctx), (Expression) visit(ctx.instructions));
    }

    @Override
    public ASTNode visitField(CoolParser.FieldContext ctx) {
        return new Field(ctx.start, ctx, new ObjectId(ctx.variableId, ctx),
                new TypeId(ctx.typeId, ctx),
                ctx.initialExpr != null ? (Expression) visit(ctx.initialExpr) : null);
    }

    @Override
    public ASTNode visitFormal(CoolParser.FormalContext ctx) {
        return new Formal(ctx.start, ctx, new ObjectId(ctx.objectId, ctx),
                new TypeId(ctx.typeId, ctx));
    }

    @Override
    public ASTNode visitNewExpr(CoolParser.NewExprContext ctx) {
        return new New(ctx.start, ctx, new TypeId(ctx.typeId, ctx));
    }

    @Override
    public ASTNode visitObjectExpr(CoolParser.ObjectExprContext ctx) {
        return new ObjectId(ctx.start, ctx);
    }

    @Override
    public ASTNode visitIntegerExpr(CoolParser.IntegerExprContext ctx) {
        return new Int(ctx.start, ctx);
    }

    @Override
    public ASTNode visitStringLiteralExpr(CoolParser.StringLiteralExprContext ctx) {
        return new Str(ctx.start, ctx);
    }

    @Override
    public ASTNode visitLogicalExpr(CoolParser.LogicalExprContext ctx) {
        return new Logical(ctx.start, ctx, ctx.op, (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitExplicitCall(CoolParser.ExplicitCallContext ctx) {
        return new ExplicitCall(ctx.start, ctx, (Expression) visit(ctx.dispatchExpr),
                ctx.classExpr != null ? new TypeId(ctx.classExpr, ctx) : null, new ObjectId(ctx.methodId, ctx),
                new ArrayList<>(ctx.params.stream().map((param) -> (Expression) visit(param)).toList()));
    }

    @Override
    public ASTNode visitLetExpr(CoolParser.LetExprContext ctx) {
        return new Let(ctx.start, ctx,
                new ArrayList<>(ctx.localVars.stream().map((local) -> (Local) visit(local)).toList()), (Expression) visit(ctx.inExpr));
    }

    @Override
    public ASTNode visitParenExpr(CoolParser.ParenExprContext ctx) {
        return new Paren(ctx.start, ctx, (Expression) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitImplicitCall(CoolParser.ImplicitCallContext ctx) {
        return new ImplicitCall(ctx.start, ctx, new ObjectId(ctx.methodId, ctx),
                new ArrayList<>(ctx.params.stream().map((param) -> (Expression) visit(param)).toList()));
    }

    @Override
    public ASTNode visitUnaryExpr(CoolParser.UnaryExprContext ctx) {
        return new Unary(ctx.start, ctx, (Expression) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitWhileExpr(CoolParser.WhileExprContext ctx) {
        return new While(ctx.start, ctx, (Expression) visit(ctx.condExpr), (Expression) visit(ctx.insideExpr));
    }

    @Override
    public ASTNode visitIfExpr(CoolParser.IfExprContext ctx) {
        return new If(ctx.start, ctx, (Expression) visit(ctx.condExpr),
                (Expression) visit(ctx.thenExpr), (Expression) visit(ctx.elseExpr));
    }

    @Override
    public ASTNode visitBlockExpr(CoolParser.BlockExprContext ctx) {
        return new Block(ctx.start, ctx,
                new ArrayList<>(ctx.insideExprs.stream().map((expr) -> (Expression) visit(expr)).toList()));
    }

    @Override
    public ASTNode visitCaseExpr(CoolParser.CaseExprContext ctx) {
        return new Case(ctx.start, ctx, (Expression) visit(ctx.expr()),
                new ArrayList<>(ctx.branches.stream().map((branch) -> (Branch) visit(branch)).toList()));
    }

    @Override
    public ASTNode visitArithmeticExpr(CoolParser.ArithmeticExprContext ctx) {
        return new Arithmetic(ctx.start, ctx, ctx.op, (Expression) visit(ctx.left), (Expression) visit(ctx.right));
    }

    @Override
    public ASTNode visitBoolExpr(CoolParser.BoolExprContext ctx) {
        return new Bool(ctx.val, ctx);
    }

    @Override
    public ASTNode visitAssignExpr(CoolParser.AssignExprContext ctx) {
        return new Assign(ctx.start, ctx, new ObjectId(ctx.objectId, ctx),
                (Expression) visit(ctx.expr()));
    }

    @Override
    public ASTNode visitBranch(CoolParser.BranchContext ctx) {
        return new Branch(ctx.start, ctx, new ObjectId(ctx.objectId, ctx),
                new TypeId(ctx.typeId, ctx), (Expression) visit(ctx.branchExpr));
    }

    @Override
    public ASTNode visitLocal(CoolParser.LocalContext ctx) {
        return new Local(ctx.start, ctx, new ObjectId(ctx.objectId, ctx),
                new TypeId(ctx.typeId, ctx),
                ctx.assignExpr != null ? (Expression) visit(ctx.assignExpr) : null);
    }
}
