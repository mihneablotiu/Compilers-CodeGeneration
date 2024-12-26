// Generated from C:/Users/mblot/Desktop/CPL_Tema3/src/cool/parser/CoolParser.g4 by ANTLR 4.13.1

    package cool.parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class CoolParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, IF=2, THEN=3, ELSE=4, FI=5, CLASS=6, INHERITS=7, IN=8, ISVOID=9, 
		LET=10, LOOP=11, POOL=12, WHILE=13, CASE=14, ESAC=15, NEW=16, OF=17, NOT=18, 
		TRUE=19, FALSE=20, SEMICOLON=21, COLON=22, LBRACE=23, RBRACE=24, COMMA=25, 
		ASSIGN=26, DOT=27, AT=28, PLUS=29, MINUS=30, DIV=31, MULT=32, EQUAL=33, 
		LT=34, LTE=35, BRANCH=36, LPAREN=37, RPAREN=38, TILDA=39, STRING=40, STRING_NULL=41, 
		INTEGER=42, TYPEID=43, OBJECTID=44, WS=45, LINE_COMMENT=46, BLOCK_COMMENT=47, 
		COMMENT_END=48, OTHER=49;
	public static final int
		RULE_program = 0, RULE_class = 1, RULE_feature = 2, RULE_formal = 3, RULE_expr = 4, 
		RULE_branch = 5, RULE_local = 6;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "class", "feature", "formal", "expr", "branch", "local"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, "';'", "':'", "'{'", 
			"'}'", "','", "'<-'", "'.'", "'@'", "'+'", "'-'", "'/'", "'*'", "'='", 
			"'<'", "'<='", "'=>'", "'('", "')'", "'~'", null, null, null, null, null, 
			null, null, null, "'*)'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "IF", "THEN", "ELSE", "FI", "CLASS", "INHERITS", "IN", 
			"ISVOID", "LET", "LOOP", "POOL", "WHILE", "CASE", "ESAC", "NEW", "OF", 
			"NOT", "TRUE", "FALSE", "SEMICOLON", "COLON", "LBRACE", "RBRACE", "COMMA", 
			"ASSIGN", "DOT", "AT", "PLUS", "MINUS", "DIV", "MULT", "EQUAL", "LT", 
			"LTE", "BRANCH", "LPAREN", "RPAREN", "TILDA", "STRING", "STRING_NULL", 
			"INTEGER", "TYPEID", "OBJECTID", "WS", "LINE_COMMENT", "BLOCK_COMMENT", 
			"COMMENT_END", "OTHER"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "CoolParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CoolParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public ClassContext class_;
		public List<ClassContext> classes = new ArrayList<ClassContext>();
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<ClassContext> class_() {
			return getRuleContexts(ClassContext.class);
		}
		public ClassContext class_(int i) {
			return getRuleContext(ClassContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(14);
				((ProgramContext)_localctx).class_ = class_();
				((ProgramContext)_localctx).classes.add(((ProgramContext)_localctx).class_);
				setState(15);
				match(SEMICOLON);
				}
				}
				setState(19); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ClassContext extends ParserRuleContext {
		public Token className;
		public Token parentName;
		public FeatureContext feature;
		public List<FeatureContext> features = new ArrayList<FeatureContext>();
		public TerminalNode CLASS() { return getToken(CoolParser.CLASS, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> TYPEID() { return getTokens(CoolParser.TYPEID); }
		public TerminalNode TYPEID(int i) {
			return getToken(CoolParser.TYPEID, i);
		}
		public TerminalNode INHERITS() { return getToken(CoolParser.INHERITS, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<FeatureContext> feature() {
			return getRuleContexts(FeatureContext.class);
		}
		public FeatureContext feature(int i) {
			return getRuleContext(FeatureContext.class,i);
		}
		public ClassContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_class; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitClass(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitClass(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassContext class_() throws RecognitionException {
		ClassContext _localctx = new ClassContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_class);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			match(CLASS);
			setState(22);
			((ClassContext)_localctx).className = match(TYPEID);
			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==INHERITS) {
				{
				setState(23);
				match(INHERITS);
				setState(24);
				((ClassContext)_localctx).parentName = match(TYPEID);
				}
			}

			setState(27);
			match(LBRACE);
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBJECTID) {
				{
				{
				setState(28);
				((ClassContext)_localctx).feature = feature();
				((ClassContext)_localctx).features.add(((ClassContext)_localctx).feature);
				setState(29);
				match(SEMICOLON);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FeatureContext extends ParserRuleContext {
		public FeatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feature; }
	 
		public FeatureContext() { }
		public void copyFrom(FeatureContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class MethodContext extends FeatureContext {
		public Token methodId;
		public FormalContext formal;
		public List<FormalContext> params = new ArrayList<FormalContext>();
		public Token returnType;
		public ExprContext instructions;
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<FormalContext> formal() {
			return getRuleContexts(FormalContext.class);
		}
		public FormalContext formal(int i) {
			return getRuleContext(FormalContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public MethodContext(FeatureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitMethod(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitMethod(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FieldContext extends FeatureContext {
		public Token variableId;
		public Token typeId;
		public ExprContext initialExpr;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FieldContext(FeatureContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitField(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeatureContext feature() throws RecognitionException {
		FeatureContext _localctx = new FeatureContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_feature);
		int _la;
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				_localctx = new MethodContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				((MethodContext)_localctx).methodId = match(OBJECTID);
				setState(39);
				match(LPAREN);
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==OBJECTID) {
					{
					setState(40);
					((MethodContext)_localctx).formal = formal();
					((MethodContext)_localctx).params.add(((MethodContext)_localctx).formal);
					setState(45);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(41);
						match(COMMA);
						setState(42);
						((MethodContext)_localctx).formal = formal();
						((MethodContext)_localctx).params.add(((MethodContext)_localctx).formal);
						}
						}
						setState(47);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(50);
				match(RPAREN);
				setState(51);
				match(COLON);
				setState(52);
				((MethodContext)_localctx).returnType = match(TYPEID);
				setState(53);
				match(LBRACE);
				setState(54);
				((MethodContext)_localctx).instructions = expr(0);
				setState(55);
				match(RBRACE);
				}
				break;
			case 2:
				_localctx = new FieldContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				((FieldContext)_localctx).variableId = match(OBJECTID);
				setState(58);
				match(COLON);
				setState(59);
				((FieldContext)_localctx).typeId = match(TYPEID);
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ASSIGN) {
					{
					setState(60);
					match(ASSIGN);
					setState(61);
					((FieldContext)_localctx).initialExpr = expr(0);
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FormalContext extends ParserRuleContext {
		public Token objectId;
		public Token typeId;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public FormalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterFormal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitFormal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitFormal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalContext formal() throws RecognitionException {
		FormalContext _localctx = new FormalContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_formal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			((FormalContext)_localctx).objectId = match(OBJECTID);
			setState(67);
			match(COLON);
			setState(68);
			((FormalContext)_localctx).typeId = match(TYPEID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	 
		public ExprContext() { }
		public void copyFrom(ExprContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class NewExprContext extends ExprContext {
		public Token typeId;
		public TerminalNode NEW() { return getToken(CoolParser.NEW, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public NewExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterNewExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitNewExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitNewExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ObjectExprContext extends ExprContext {
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public ObjectExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterObjectExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitObjectExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitObjectExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IntegerExprContext extends ExprContext {
		public TerminalNode INTEGER() { return getToken(CoolParser.INTEGER, 0); }
		public IntegerExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIntegerExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIntegerExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIntegerExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StringLiteralExprContext extends ExprContext {
		public TerminalNode STRING() { return getToken(CoolParser.STRING, 0); }
		public StringLiteralExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterStringLiteralExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitStringLiteralExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitStringLiteralExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LogicalExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode LT() { return getToken(CoolParser.LT, 0); }
		public TerminalNode LTE() { return getToken(CoolParser.LTE, 0); }
		public TerminalNode EQUAL() { return getToken(CoolParser.EQUAL, 0); }
		public LogicalExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLogicalExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLogicalExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLogicalExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExplicitCallContext extends ExprContext {
		public ExprContext dispatchExpr;
		public Token classExpr;
		public Token methodId;
		public ExprContext expr;
		public List<ExprContext> params = new ArrayList<ExprContext>();
		public TerminalNode DOT() { return getToken(CoolParser.DOT, 0); }
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode AT() { return getToken(CoolParser.AT, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public ExplicitCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterExplicitCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitExplicitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitExplicitCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class LetExprContext extends ExprContext {
		public LocalContext local;
		public List<LocalContext> localVars = new ArrayList<LocalContext>();
		public ExprContext inExpr;
		public TerminalNode LET() { return getToken(CoolParser.LET, 0); }
		public TerminalNode IN() { return getToken(CoolParser.IN, 0); }
		public List<LocalContext> local() {
			return getRuleContexts(LocalContext.class);
		}
		public LocalContext local(int i) {
			return getRuleContext(LocalContext.class,i);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public LetExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLetExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLetExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLetExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ParenExprContext extends ExprContext {
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public ParenExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterParenExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitParenExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitParenExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ImplicitCallContext extends ExprContext {
		public Token methodId;
		public ExprContext expr;
		public List<ExprContext> params = new ArrayList<ExprContext>();
		public TerminalNode LPAREN() { return getToken(CoolParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(CoolParser.RPAREN, 0); }
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(CoolParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(CoolParser.COMMA, i);
		}
		public ImplicitCallContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterImplicitCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitImplicitCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitImplicitCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class UnaryExprContext extends ExprContext {
		public TerminalNode TILDA() { return getToken(CoolParser.TILDA, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ISVOID() { return getToken(CoolParser.ISVOID, 0); }
		public TerminalNode NOT() { return getToken(CoolParser.NOT, 0); }
		public UnaryExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class WhileExprContext extends ExprContext {
		public ExprContext condExpr;
		public ExprContext insideExpr;
		public TerminalNode WHILE() { return getToken(CoolParser.WHILE, 0); }
		public TerminalNode LOOP() { return getToken(CoolParser.LOOP, 0); }
		public TerminalNode POOL() { return getToken(CoolParser.POOL, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public WhileExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterWhileExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitWhileExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitWhileExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class IfExprContext extends ExprContext {
		public ExprContext condExpr;
		public ExprContext thenExpr;
		public ExprContext elseExpr;
		public TerminalNode IF() { return getToken(CoolParser.IF, 0); }
		public TerminalNode THEN() { return getToken(CoolParser.THEN, 0); }
		public TerminalNode ELSE() { return getToken(CoolParser.ELSE, 0); }
		public TerminalNode FI() { return getToken(CoolParser.FI, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public IfExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterIfExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitIfExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitIfExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BlockExprContext extends ExprContext {
		public ExprContext expr;
		public List<ExprContext> insideExprs = new ArrayList<ExprContext>();
		public TerminalNode LBRACE() { return getToken(CoolParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(CoolParser.RBRACE, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(CoolParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(CoolParser.SEMICOLON, i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public BlockExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBlockExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBlockExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBlockExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class CaseExprContext extends ExprContext {
		public ExprContext caseExpr;
		public BranchContext branch;
		public List<BranchContext> branches = new ArrayList<BranchContext>();
		public TerminalNode CASE() { return getToken(CoolParser.CASE, 0); }
		public TerminalNode OF() { return getToken(CoolParser.OF, 0); }
		public TerminalNode ESAC() { return getToken(CoolParser.ESAC, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<BranchContext> branch() {
			return getRuleContexts(BranchContext.class);
		}
		public BranchContext branch(int i) {
			return getRuleContext(BranchContext.class,i);
		}
		public CaseExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterCaseExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitCaseExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitCaseExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ArithmeticExprContext extends ExprContext {
		public ExprContext left;
		public Token op;
		public ExprContext right;
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode MULT() { return getToken(CoolParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(CoolParser.DIV, 0); }
		public TerminalNode PLUS() { return getToken(CoolParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(CoolParser.MINUS, 0); }
		public ArithmeticExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterArithmeticExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitArithmeticExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitArithmeticExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class BoolExprContext extends ExprContext {
		public Token val;
		public TerminalNode TRUE() { return getToken(CoolParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CoolParser.FALSE, 0); }
		public BoolExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBoolExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBoolExpr(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class AssignExprContext extends ExprContext {
		public Token objectId;
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public AssignExprContext(ExprContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				_localctx = new ImplicitCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(71);
				((ImplicitCallContext)_localctx).methodId = match(OBJECTID);
				setState(72);
				match(LPAREN);
				setState(81);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 23776949265924L) != 0)) {
					{
					setState(73);
					((ImplicitCallContext)_localctx).expr = expr(0);
					((ImplicitCallContext)_localctx).params.add(((ImplicitCallContext)_localctx).expr);
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(74);
						match(COMMA);
						setState(75);
						((ImplicitCallContext)_localctx).expr = expr(0);
						((ImplicitCallContext)_localctx).params.add(((ImplicitCallContext)_localctx).expr);
						}
						}
						setState(80);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(83);
				match(RPAREN);
				}
				break;
			case 2:
				{
				_localctx = new IfExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(84);
				match(IF);
				setState(85);
				((IfExprContext)_localctx).condExpr = expr(0);
				setState(86);
				match(THEN);
				setState(87);
				((IfExprContext)_localctx).thenExpr = expr(0);
				setState(88);
				match(ELSE);
				setState(89);
				((IfExprContext)_localctx).elseExpr = expr(0);
				setState(90);
				match(FI);
				}
				break;
			case 3:
				{
				_localctx = new WhileExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(92);
				match(WHILE);
				setState(93);
				((WhileExprContext)_localctx).condExpr = expr(0);
				setState(94);
				match(LOOP);
				setState(95);
				((WhileExprContext)_localctx).insideExpr = expr(0);
				setState(96);
				match(POOL);
				}
				break;
			case 4:
				{
				_localctx = new BlockExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(98);
				match(LBRACE);
				setState(102); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(99);
					((BlockExprContext)_localctx).expr = expr(0);
					((BlockExprContext)_localctx).insideExprs.add(((BlockExprContext)_localctx).expr);
					setState(100);
					match(SEMICOLON);
					}
					}
					setState(104); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 23776949265924L) != 0) );
				setState(106);
				match(RBRACE);
				}
				break;
			case 5:
				{
				_localctx = new LetExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(108);
				match(LET);
				setState(109);
				((LetExprContext)_localctx).local = local();
				((LetExprContext)_localctx).localVars.add(((LetExprContext)_localctx).local);
				setState(114);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(110);
					match(COMMA);
					setState(111);
					((LetExprContext)_localctx).local = local();
					((LetExprContext)_localctx).localVars.add(((LetExprContext)_localctx).local);
					}
					}
					setState(116);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(117);
				match(IN);
				setState(118);
				((LetExprContext)_localctx).inExpr = expr(16);
				}
				break;
			case 6:
				{
				_localctx = new CaseExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(120);
				match(CASE);
				setState(121);
				((CaseExprContext)_localctx).caseExpr = expr(0);
				setState(122);
				match(OF);
				setState(124); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(123);
					((CaseExprContext)_localctx).branch = branch();
					((CaseExprContext)_localctx).branches.add(((CaseExprContext)_localctx).branch);
					}
					}
					setState(126); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==OBJECTID );
				setState(128);
				match(ESAC);
				}
				break;
			case 7:
				{
				_localctx = new NewExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(NEW);
				setState(131);
				((NewExprContext)_localctx).typeId = match(TYPEID);
				}
				break;
			case 8:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(132);
				match(TILDA);
				setState(133);
				expr(13);
				}
				break;
			case 9:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(134);
				match(ISVOID);
				setState(135);
				expr(12);
				}
				break;
			case 10:
				{
				_localctx = new UnaryExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(136);
				match(NOT);
				setState(137);
				expr(8);
				}
				break;
			case 11:
				{
				_localctx = new ParenExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(138);
				match(LPAREN);
				setState(139);
				expr(0);
				setState(140);
				match(RPAREN);
				}
				break;
			case 12:
				{
				_localctx = new ObjectExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(142);
				match(OBJECTID);
				}
				break;
			case 13:
				{
				_localctx = new IntegerExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(143);
				match(INTEGER);
				}
				break;
			case 14:
				{
				_localctx = new StringLiteralExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(144);
				match(STRING);
				}
				break;
			case 15:
				{
				_localctx = new BoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(145);
				((BoolExprContext)_localctx).val = match(TRUE);
				}
				break;
			case 16:
				{
				_localctx = new BoolExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(146);
				((BoolExprContext)_localctx).val = match(FALSE);
				}
				break;
			case 17:
				{
				_localctx = new AssignExprContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(147);
				((AssignExprContext)_localctx).objectId = match(OBJECTID);
				setState(148);
				match(ASSIGN);
				setState(149);
				expr(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(192);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(190);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new ArithmeticExprContext(new ExprContext(_parentctx, _parentState));
						((ArithmeticExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(152);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(155);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case MULT:
							{
							setState(153);
							((ArithmeticExprContext)_localctx).op = match(MULT);
							}
							break;
						case DIV:
							{
							setState(154);
							((ArithmeticExprContext)_localctx).op = match(DIV);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(157);
						((ArithmeticExprContext)_localctx).right = expr(12);
						}
						break;
					case 2:
						{
						_localctx = new ArithmeticExprContext(new ExprContext(_parentctx, _parentState));
						((ArithmeticExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(158);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(161);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case PLUS:
							{
							setState(159);
							((ArithmeticExprContext)_localctx).op = match(PLUS);
							}
							break;
						case MINUS:
							{
							setState(160);
							((ArithmeticExprContext)_localctx).op = match(MINUS);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(163);
						((ArithmeticExprContext)_localctx).right = expr(11);
						}
						break;
					case 3:
						{
						_localctx = new LogicalExprContext(new ExprContext(_parentctx, _parentState));
						((LogicalExprContext)_localctx).left = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(164);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(168);
						_errHandler.sync(this);
						switch (_input.LA(1)) {
						case LT:
							{
							setState(165);
							((LogicalExprContext)_localctx).op = match(LT);
							}
							break;
						case LTE:
							{
							setState(166);
							((LogicalExprContext)_localctx).op = match(LTE);
							}
							break;
						case EQUAL:
							{
							setState(167);
							((LogicalExprContext)_localctx).op = match(EQUAL);
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(170);
						((LogicalExprContext)_localctx).right = expr(10);
						}
						break;
					case 4:
						{
						_localctx = new ExplicitCallContext(new ExprContext(_parentctx, _parentState));
						((ExplicitCallContext)_localctx).dispatchExpr = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(171);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(174);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==AT) {
							{
							setState(172);
							match(AT);
							setState(173);
							((ExplicitCallContext)_localctx).classExpr = match(TYPEID);
							}
						}

						setState(176);
						match(DOT);
						setState(177);
						((ExplicitCallContext)_localctx).methodId = match(OBJECTID);
						setState(178);
						match(LPAREN);
						setState(187);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 23776949265924L) != 0)) {
							{
							setState(179);
							((ExplicitCallContext)_localctx).expr = expr(0);
							((ExplicitCallContext)_localctx).params.add(((ExplicitCallContext)_localctx).expr);
							setState(184);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==COMMA) {
								{
								{
								setState(180);
								match(COMMA);
								setState(181);
								((ExplicitCallContext)_localctx).expr = expr(0);
								((ExplicitCallContext)_localctx).params.add(((ExplicitCallContext)_localctx).expr);
								}
								}
								setState(186);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							}
						}

						setState(189);
						match(RPAREN);
						}
						break;
					}
					} 
				}
				setState(194);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BranchContext extends ParserRuleContext {
		public Token objectId;
		public Token typeId;
		public ExprContext branchExpr;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode BRANCH() { return getToken(CoolParser.BRANCH, 0); }
		public TerminalNode SEMICOLON() { return getToken(CoolParser.SEMICOLON, 0); }
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BranchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_branch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterBranch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitBranch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitBranch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BranchContext branch() throws RecognitionException {
		BranchContext _localctx = new BranchContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_branch);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			((BranchContext)_localctx).objectId = match(OBJECTID);
			setState(196);
			match(COLON);
			setState(197);
			((BranchContext)_localctx).typeId = match(TYPEID);
			setState(198);
			match(BRANCH);
			setState(199);
			((BranchContext)_localctx).branchExpr = expr(0);
			setState(200);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LocalContext extends ParserRuleContext {
		public Token objectId;
		public Token typeId;
		public ExprContext assignExpr;
		public TerminalNode COLON() { return getToken(CoolParser.COLON, 0); }
		public TerminalNode OBJECTID() { return getToken(CoolParser.OBJECTID, 0); }
		public TerminalNode TYPEID() { return getToken(CoolParser.TYPEID, 0); }
		public TerminalNode ASSIGN() { return getToken(CoolParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LocalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_local; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).enterLocal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CoolParserListener ) ((CoolParserListener)listener).exitLocal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CoolParserVisitor ) return ((CoolParserVisitor<? extends T>)visitor).visitLocal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalContext local() throws RecognitionException {
		LocalContext _localctx = new LocalContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_local);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			((LocalContext)_localctx).objectId = match(OBJECTID);
			setState(203);
			match(COLON);
			setState(204);
			((LocalContext)_localctx).typeId = match(TYPEID);
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(205);
				match(ASSIGN);
				setState(206);
				((LocalContext)_localctx).assignExpr = expr(0);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 4:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 11);
		case 1:
			return precpred(_ctx, 10);
		case 2:
			return precpred(_ctx, 9);
		case 3:
			return precpred(_ctx, 21);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00011\u00d2\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0004\u0000\u0012\b\u0000\u000b\u0000\f\u0000\u0013\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u001a\b\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0005\u0001 \b\u0001\n\u0001\f\u0001"+
		"#\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002,\b\u0002\n\u0002\f\u0002/\t\u0002"+
		"\u0003\u00021\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0003\u0002?\b\u0002\u0003\u0002A\b\u0002\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004M\b\u0004\n\u0004"+
		"\f\u0004P\t\u0004\u0003\u0004R\b\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004g\b\u0004"+
		"\u000b\u0004\f\u0004h\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004q\b\u0004\n\u0004\f\u0004t\t\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0004\u0004}\b\u0004\u000b\u0004\f\u0004~\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u0097\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004\u009c\b\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0003\u0004\u00a2\b\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00a9\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0003\u0004\u00af\b\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0005\u0004\u00b7"+
		"\b\u0004\n\u0004\f\u0004\u00ba\t\u0004\u0003\u0004\u00bc\b\u0004\u0001"+
		"\u0004\u0005\u0004\u00bf\b\u0004\n\u0004\f\u0004\u00c2\t\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"\u00d0\b\u0006\u0001\u0006\u0000\u0001\b\u0007\u0000\u0002\u0004\u0006"+
		"\b\n\f\u0000\u0000\u00f2\u0000\u0011\u0001\u0000\u0000\u0000\u0002\u0015"+
		"\u0001\u0000\u0000\u0000\u0004@\u0001\u0000\u0000\u0000\u0006B\u0001\u0000"+
		"\u0000\u0000\b\u0096\u0001\u0000\u0000\u0000\n\u00c3\u0001\u0000\u0000"+
		"\u0000\f\u00ca\u0001\u0000\u0000\u0000\u000e\u000f\u0003\u0002\u0001\u0000"+
		"\u000f\u0010\u0005\u0015\u0000\u0000\u0010\u0012\u0001\u0000\u0000\u0000"+
		"\u0011\u000e\u0001\u0000\u0000\u0000\u0012\u0013\u0001\u0000\u0000\u0000"+
		"\u0013\u0011\u0001\u0000\u0000\u0000\u0013\u0014\u0001\u0000\u0000\u0000"+
		"\u0014\u0001\u0001\u0000\u0000\u0000\u0015\u0016\u0005\u0006\u0000\u0000"+
		"\u0016\u0019\u0005+\u0000\u0000\u0017\u0018\u0005\u0007\u0000\u0000\u0018"+
		"\u001a\u0005+\u0000\u0000\u0019\u0017\u0001\u0000\u0000\u0000\u0019\u001a"+
		"\u0001\u0000\u0000\u0000\u001a\u001b\u0001\u0000\u0000\u0000\u001b!\u0005"+
		"\u0017\u0000\u0000\u001c\u001d\u0003\u0004\u0002\u0000\u001d\u001e\u0005"+
		"\u0015\u0000\u0000\u001e \u0001\u0000\u0000\u0000\u001f\u001c\u0001\u0000"+
		"\u0000\u0000 #\u0001\u0000\u0000\u0000!\u001f\u0001\u0000\u0000\u0000"+
		"!\"\u0001\u0000\u0000\u0000\"$\u0001\u0000\u0000\u0000#!\u0001\u0000\u0000"+
		"\u0000$%\u0005\u0018\u0000\u0000%\u0003\u0001\u0000\u0000\u0000&\'\u0005"+
		",\u0000\u0000\'0\u0005%\u0000\u0000(-\u0003\u0006\u0003\u0000)*\u0005"+
		"\u0019\u0000\u0000*,\u0003\u0006\u0003\u0000+)\u0001\u0000\u0000\u0000"+
		",/\u0001\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-.\u0001\u0000\u0000"+
		"\u0000.1\u0001\u0000\u0000\u0000/-\u0001\u0000\u0000\u00000(\u0001\u0000"+
		"\u0000\u000001\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000023\u0005"+
		"&\u0000\u000034\u0005\u0016\u0000\u000045\u0005+\u0000\u000056\u0005\u0017"+
		"\u0000\u000067\u0003\b\u0004\u000078\u0005\u0018\u0000\u00008A\u0001\u0000"+
		"\u0000\u00009:\u0005,\u0000\u0000:;\u0005\u0016\u0000\u0000;>\u0005+\u0000"+
		"\u0000<=\u0005\u001a\u0000\u0000=?\u0003\b\u0004\u0000><\u0001\u0000\u0000"+
		"\u0000>?\u0001\u0000\u0000\u0000?A\u0001\u0000\u0000\u0000@&\u0001\u0000"+
		"\u0000\u0000@9\u0001\u0000\u0000\u0000A\u0005\u0001\u0000\u0000\u0000"+
		"BC\u0005,\u0000\u0000CD\u0005\u0016\u0000\u0000DE\u0005+\u0000\u0000E"+
		"\u0007\u0001\u0000\u0000\u0000FG\u0006\u0004\uffff\uffff\u0000GH\u0005"+
		",\u0000\u0000HQ\u0005%\u0000\u0000IN\u0003\b\u0004\u0000JK\u0005\u0019"+
		"\u0000\u0000KM\u0003\b\u0004\u0000LJ\u0001\u0000\u0000\u0000MP\u0001\u0000"+
		"\u0000\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OR\u0001"+
		"\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000QI\u0001\u0000\u0000\u0000"+
		"QR\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000S\u0097\u0005&\u0000"+
		"\u0000TU\u0005\u0002\u0000\u0000UV\u0003\b\u0004\u0000VW\u0005\u0003\u0000"+
		"\u0000WX\u0003\b\u0004\u0000XY\u0005\u0004\u0000\u0000YZ\u0003\b\u0004"+
		"\u0000Z[\u0005\u0005\u0000\u0000[\u0097\u0001\u0000\u0000\u0000\\]\u0005"+
		"\r\u0000\u0000]^\u0003\b\u0004\u0000^_\u0005\u000b\u0000\u0000_`\u0003"+
		"\b\u0004\u0000`a\u0005\f\u0000\u0000a\u0097\u0001\u0000\u0000\u0000bf"+
		"\u0005\u0017\u0000\u0000cd\u0003\b\u0004\u0000de\u0005\u0015\u0000\u0000"+
		"eg\u0001\u0000\u0000\u0000fc\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000"+
		"\u0000hf\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000ij\u0001\u0000"+
		"\u0000\u0000jk\u0005\u0018\u0000\u0000k\u0097\u0001\u0000\u0000\u0000"+
		"lm\u0005\n\u0000\u0000mr\u0003\f\u0006\u0000no\u0005\u0019\u0000\u0000"+
		"oq\u0003\f\u0006\u0000pn\u0001\u0000\u0000\u0000qt\u0001\u0000\u0000\u0000"+
		"rp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000su\u0001\u0000\u0000"+
		"\u0000tr\u0001\u0000\u0000\u0000uv\u0005\b\u0000\u0000vw\u0003\b\u0004"+
		"\u0010w\u0097\u0001\u0000\u0000\u0000xy\u0005\u000e\u0000\u0000yz\u0003"+
		"\b\u0004\u0000z|\u0005\u0011\u0000\u0000{}\u0003\n\u0005\u0000|{\u0001"+
		"\u0000\u0000\u0000}~\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000"+
		"~\u007f\u0001\u0000\u0000\u0000\u007f\u0080\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0005\u000f\u0000\u0000\u0081\u0097\u0001\u0000\u0000\u0000\u0082"+
		"\u0083\u0005\u0010\u0000\u0000\u0083\u0097\u0005+\u0000\u0000\u0084\u0085"+
		"\u0005\'\u0000\u0000\u0085\u0097\u0003\b\u0004\r\u0086\u0087\u0005\t\u0000"+
		"\u0000\u0087\u0097\u0003\b\u0004\f\u0088\u0089\u0005\u0012\u0000\u0000"+
		"\u0089\u0097\u0003\b\u0004\b\u008a\u008b\u0005%\u0000\u0000\u008b\u008c"+
		"\u0003\b\u0004\u0000\u008c\u008d\u0005&\u0000\u0000\u008d\u0097\u0001"+
		"\u0000\u0000\u0000\u008e\u0097\u0005,\u0000\u0000\u008f\u0097\u0005*\u0000"+
		"\u0000\u0090\u0097\u0005(\u0000\u0000\u0091\u0097\u0005\u0013\u0000\u0000"+
		"\u0092\u0097\u0005\u0014\u0000\u0000\u0093\u0094\u0005,\u0000\u0000\u0094"+
		"\u0095\u0005\u001a\u0000\u0000\u0095\u0097\u0003\b\u0004\u0001\u0096F"+
		"\u0001\u0000\u0000\u0000\u0096T\u0001\u0000\u0000\u0000\u0096\\\u0001"+
		"\u0000\u0000\u0000\u0096b\u0001\u0000\u0000\u0000\u0096l\u0001\u0000\u0000"+
		"\u0000\u0096x\u0001\u0000\u0000\u0000\u0096\u0082\u0001\u0000\u0000\u0000"+
		"\u0096\u0084\u0001\u0000\u0000\u0000\u0096\u0086\u0001\u0000\u0000\u0000"+
		"\u0096\u0088\u0001\u0000\u0000\u0000\u0096\u008a\u0001\u0000\u0000\u0000"+
		"\u0096\u008e\u0001\u0000\u0000\u0000\u0096\u008f\u0001\u0000\u0000\u0000"+
		"\u0096\u0090\u0001\u0000\u0000\u0000\u0096\u0091\u0001\u0000\u0000\u0000"+
		"\u0096\u0092\u0001\u0000\u0000\u0000\u0096\u0093\u0001\u0000\u0000\u0000"+
		"\u0097\u00c0\u0001\u0000\u0000\u0000\u0098\u009b\n\u000b\u0000\u0000\u0099"+
		"\u009c\u0005 \u0000\u0000\u009a\u009c\u0005\u001f\u0000\u0000\u009b\u0099"+
		"\u0001\u0000\u0000\u0000\u009b\u009a\u0001\u0000\u0000\u0000\u009c\u009d"+
		"\u0001\u0000\u0000\u0000\u009d\u00bf\u0003\b\u0004\f\u009e\u00a1\n\n\u0000"+
		"\u0000\u009f\u00a2\u0005\u001d\u0000\u0000\u00a0\u00a2\u0005\u001e\u0000"+
		"\u0000\u00a1\u009f\u0001\u0000\u0000\u0000\u00a1\u00a0\u0001\u0000\u0000"+
		"\u0000\u00a2\u00a3\u0001\u0000\u0000\u0000\u00a3\u00bf\u0003\b\u0004\u000b"+
		"\u00a4\u00a8\n\t\u0000\u0000\u00a5\u00a9\u0005\"\u0000\u0000\u00a6\u00a9"+
		"\u0005#\u0000\u0000\u00a7\u00a9\u0005!\u0000\u0000\u00a8\u00a5\u0001\u0000"+
		"\u0000\u0000\u00a8\u00a6\u0001\u0000\u0000\u0000\u00a8\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a9\u00aa\u0001\u0000\u0000\u0000\u00aa\u00bf\u0003\b\u0004"+
		"\n\u00ab\u00ae\n\u0015\u0000\u0000\u00ac\u00ad\u0005\u001c\u0000\u0000"+
		"\u00ad\u00af\u0005+\u0000\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae"+
		"\u00af\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0"+
		"\u00b1\u0005\u001b\u0000\u0000\u00b1\u00b2\u0005,\u0000\u0000\u00b2\u00bb"+
		"\u0005%\u0000\u0000\u00b3\u00b8\u0003\b\u0004\u0000\u00b4\u00b5\u0005"+
		"\u0019\u0000\u0000\u00b5\u00b7\u0003\b\u0004\u0000\u00b6\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00bc\u0001\u0000"+
		"\u0000\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000\u00bb\u00b3\u0001\u0000"+
		"\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u00bd\u0001\u0000"+
		"\u0000\u0000\u00bd\u00bf\u0005&\u0000\u0000\u00be\u0098\u0001\u0000\u0000"+
		"\u0000\u00be\u009e\u0001\u0000\u0000\u0000\u00be\u00a4\u0001\u0000\u0000"+
		"\u0000\u00be\u00ab\u0001\u0000\u0000\u0000\u00bf\u00c2\u0001\u0000\u0000"+
		"\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000"+
		"\u0000\u00c1\t\u0001\u0000\u0000\u0000\u00c2\u00c0\u0001\u0000\u0000\u0000"+
		"\u00c3\u00c4\u0005,\u0000\u0000\u00c4\u00c5\u0005\u0016\u0000\u0000\u00c5"+
		"\u00c6\u0005+\u0000\u0000\u00c6\u00c7\u0005$\u0000\u0000\u00c7\u00c8\u0003"+
		"\b\u0004\u0000\u00c8\u00c9\u0005\u0015\u0000\u0000\u00c9\u000b\u0001\u0000"+
		"\u0000\u0000\u00ca\u00cb\u0005,\u0000\u0000\u00cb\u00cc\u0005\u0016\u0000"+
		"\u0000\u00cc\u00cf\u0005+\u0000\u0000\u00cd\u00ce\u0005\u001a\u0000\u0000"+
		"\u00ce\u00d0\u0003\b\u0004\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00cf"+
		"\u00d0\u0001\u0000\u0000\u0000\u00d0\r\u0001\u0000\u0000\u0000\u0016\u0013"+
		"\u0019!-0>@NQhr~\u0096\u009b\u00a1\u00a8\u00ae\u00b8\u00bb\u00be\u00c0"+
		"\u00cf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}