// Generated from C:/Users/mblot/Desktop/CPL_Tema3/src/cool/parser/CoolParser.g4 by ANTLR 4.13.1

    package cool.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CoolParser}.
 */
public interface CoolParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(CoolParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#class}.
	 * @param ctx the parse tree
	 */
	void enterClass(CoolParser.ClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#class}.
	 * @param ctx the parse tree
	 */
	void exitClass(CoolParser.ClassContext ctx);
	/**
	 * Enter a parse tree produced by the {@code method}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterMethod(CoolParser.MethodContext ctx);
	/**
	 * Exit a parse tree produced by the {@code method}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitMethod(CoolParser.MethodContext ctx);
	/**
	 * Enter a parse tree produced by the {@code field}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void enterField(CoolParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code field}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 */
	void exitField(CoolParser.FieldContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 */
	void enterFormal(CoolParser.FormalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 */
	void exitFormal(CoolParser.FormalContext ctx);
	/**
	 * Enter a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNewExpr(CoolParser.NewExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNewExpr(CoolParser.NewExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code objectExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterObjectExpr(CoolParser.ObjectExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code objectExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitObjectExpr(CoolParser.ObjectExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code integerExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIntegerExpr(CoolParser.IntegerExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code integerExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIntegerExpr(CoolParser.IntegerExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code stringLiteralExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteralExpr(CoolParser.StringLiteralExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code stringLiteralExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteralExpr(CoolParser.StringLiteralExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code logicalExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLogicalExpr(CoolParser.LogicalExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code logicalExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLogicalExpr(CoolParser.LogicalExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code explicitCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExplicitCall(CoolParser.ExplicitCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code explicitCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExplicitCall(CoolParser.ExplicitCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code letExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLetExpr(CoolParser.LetExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code letExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLetExpr(CoolParser.LetExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenExpr(CoolParser.ParenExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenExpr(CoolParser.ParenExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code implicitCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterImplicitCall(CoolParser.ImplicitCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code implicitCall}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitImplicitCall(CoolParser.ImplicitCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExpr(CoolParser.UnaryExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code unaryExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExpr(CoolParser.UnaryExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whileExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhileExpr(CoolParser.WhileExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whileExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhileExpr(CoolParser.WhileExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIfExpr(CoolParser.IfExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ifExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIfExpr(CoolParser.IfExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blockExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBlockExpr(CoolParser.BlockExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blockExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBlockExpr(CoolParser.BlockExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpr(CoolParser.CaseExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpr(CoolParser.CaseExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code arithmeticExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticExpr(CoolParser.ArithmeticExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code arithmeticExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticExpr(CoolParser.ArithmeticExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(CoolParser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(CoolParser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(CoolParser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(CoolParser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#branch}.
	 * @param ctx the parse tree
	 */
	void enterBranch(CoolParser.BranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#branch}.
	 * @param ctx the parse tree
	 */
	void exitBranch(CoolParser.BranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CoolParser#local}.
	 * @param ctx the parse tree
	 */
	void enterLocal(CoolParser.LocalContext ctx);
	/**
	 * Exit a parse tree produced by {@link CoolParser#local}.
	 * @param ctx the parse tree
	 */
	void exitLocal(CoolParser.LocalContext ctx);
}