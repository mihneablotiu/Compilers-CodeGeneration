// Generated from C:/Users/mblot/Desktop/CPL_Tema3/src/cool/parser/CoolParser.g4 by ANTLR 4.13.1

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoolParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass(CoolParser.ClassContext ctx);
	/**
	 * Visit a parse tree produced by the {@code method}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethod(CoolParser.MethodContext ctx);
	/**
	 * Visit a parse tree produced by the {@code field}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(CoolParser.FieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CoolParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(CoolParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code objectExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitObjectExpr(CoolParser.ObjectExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerExpr(CoolParser.IntegerExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteralExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteralExpr(CoolParser.StringLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code logicalExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalExpr(CoolParser.LogicalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code explicitCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExplicitCall(CoolParser.ExplicitCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExpr(CoolParser.LetExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(CoolParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code implicitCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplicitCall(CoolParser.ImplicitCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpr(CoolParser.UnaryExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whileExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileExpr(CoolParser.WhileExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExpr(CoolParser.IfExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockExpr(CoolParser.BlockExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpr(CoolParser.CaseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code arithmeticExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticExpr(CoolParser.ArithmeticExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolExpr(CoolParser.BoolExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(CoolParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(CoolParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#local}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLocal(CoolParser.LocalContext ctx);
}