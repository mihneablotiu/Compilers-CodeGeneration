package cool.structures;

import cool.ast.expression.ObjectId;
import cool.ast.feature.Method;
import cool.ast.formal.Formal;
import cool.compiler.Compiler;
import cool.parser.CoolParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SymbolTable {
    public static Scope globals;
    
    private static boolean semanticErrors;
    
    public static void defineBasicClasses() {
        globals = new DefaultScope(null);
        semanticErrors = false;
        
        ClassSymbol objectClass = new ClassSymbol("Object");
        objectClass.setDirectParent(globals);

        ClassSymbol ioClass = new ClassSymbol("IO");
        ioClass.setDirectParent(objectClass);

        ClassSymbol intClass = new ClassSymbol("Int");
        intClass.setDirectParent(objectClass);

        ClassSymbol stringClass = new ClassSymbol("String");
        stringClass.setDirectParent(objectClass);

        ClassSymbol boolClass = new ClassSymbol("Bool");
        boolClass.setDirectParent(objectClass);

        Method abortMethod = new Method(null, null, null, new ArrayList<>(), null, null);
        MethodSymbol abortMethodSymbol = new MethodSymbol("abort", abortMethod);
        abortMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(objectClass, false));
        abortMethodSymbol.setParent(objectClass);
        objectClass.addMethod(abortMethodSymbol);

        Method type_nameMethod = new Method(null, null, null, new ArrayList<>(), null, null);
        MethodSymbol type_nameMethodSymbol = new MethodSymbol("type_name", type_nameMethod);
        type_nameMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(stringClass, false));
        type_nameMethodSymbol.setParent(objectClass);
        objectClass.addMethod(type_nameMethodSymbol);

        Method copyMethod = new Method(null, null, null, new ArrayList<>(), null, null);
        MethodSymbol copyMethodSymbol = new MethodSymbol("copy", copyMethod);
        copyMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(objectClass, true));
        copyMethodSymbol.setParent(objectClass);
        objectClass.addMethod(copyMethodSymbol);



        Formal out_stringFormal = new Formal(null, null, null, null);
        FormalSymbol out_stringFormalSymbol = new FormalSymbol("x");
        out_stringFormalSymbol.setTypeSymbol(new ClassSymbolWrapper(stringClass, false));
        out_stringFormal.setFormalSymbol(out_stringFormalSymbol);

        Method out_stringMethod = new Method(null, null, null, new ArrayList<>(List.of(out_stringFormal)), null, null);
        MethodSymbol out_stringMethodSymbol = new MethodSymbol("out_string", out_stringMethod);
        out_stringFormal.setMethodSymbol(out_stringMethodSymbol);

        out_stringMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(ioClass, true));
        out_stringMethodSymbol.setParent(ioClass);
        ioClass.addMethod(out_stringMethodSymbol);

        Formal out_intFormal = new Formal(null, null, null, null);
        FormalSymbol out_intFormalSymbol = new FormalSymbol("x");
        out_intFormalSymbol.setTypeSymbol(new ClassSymbolWrapper(intClass, false));
        out_intFormal.setFormalSymbol(out_intFormalSymbol);

        Method out_intMethod = new Method(null, null, null, new ArrayList<>(List.of(out_intFormal)), null, null);
        MethodSymbol out_intMethodSymbol = new MethodSymbol("out_int", out_intMethod);
        out_intFormal.setMethodSymbol(out_intMethodSymbol);

        out_intMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(ioClass, true));
        out_intMethodSymbol.setParent(ioClass);
        ioClass.addMethod(out_intMethodSymbol);

        Method in_stringMethod = new Method(null, null, null, new ArrayList<>(), null, null);
        MethodSymbol in_stringMethodSymbol = new MethodSymbol("in_string", in_stringMethod);
        in_stringMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(stringClass, false));
        in_stringMethodSymbol.setParent(ioClass);
        ioClass.addMethod(in_stringMethodSymbol);

        Method in_intMethod = new Method(null, null, null, new ArrayList<>(), null, null);
        MethodSymbol in_intMethodSymbol = new MethodSymbol("in_int", in_intMethod);
        in_intMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(intClass, false));
        in_intMethodSymbol.setParent(ioClass);
        ioClass.addMethod(in_intMethodSymbol);



        Method lengthMethod = new Method(null, null, null, new ArrayList<>(), null, null);
        MethodSymbol lengthMethodSymbol = new MethodSymbol("length", lengthMethod);
        lengthMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(intClass, false));
        lengthMethodSymbol.setParent(stringClass);
        stringClass.addMethod(lengthMethodSymbol);

        Formal concatFormal = new Formal(null, null, null, null);
        FormalSymbol concatFormalSymbol = new FormalSymbol("s");
        concatFormalSymbol.setTypeSymbol(new ClassSymbolWrapper(stringClass, false));
        concatFormal.setFormalSymbol(concatFormalSymbol);

        Method concatMethod = new Method(null, null, null, new ArrayList<>(List.of(concatFormal)), null, null);
        MethodSymbol concatMethodSymbol = new MethodSymbol("concat", concatMethod);
        concatFormal.setMethodSymbol(concatMethodSymbol);

        concatMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(stringClass, false));
        concatMethodSymbol.setParent(stringClass);
        stringClass.addMethod(concatMethodSymbol);


        Formal substrFormal1 = new Formal(null, null, null, null);
        FormalSymbol substrFormalSymbol1 = new FormalSymbol("i");
        substrFormalSymbol1.setTypeSymbol(new ClassSymbolWrapper(intClass, false));
        substrFormal1.setFormalSymbol(substrFormalSymbol1);

        Formal substrFormal2 = new Formal(null, null, null, null);
        FormalSymbol substrFormalSymbol2 = new FormalSymbol("l");
        substrFormalSymbol2.setTypeSymbol(new ClassSymbolWrapper(intClass, false));
        substrFormal2.setFormalSymbol(substrFormalSymbol2);

        Method substrMethod = new Method(null, null, null, new ArrayList<>(List.of(substrFormal1, substrFormal2)), null, null);
        MethodSymbol substrMethodSymbol = new MethodSymbol("substr", substrMethod);
        substrFormal1.setMethodSymbol(substrMethodSymbol);
        substrFormal2.setMethodSymbol(substrMethodSymbol);

        substrMethodSymbol.setTypeSymbol(new ClassSymbolWrapper(stringClass, false));
        substrMethodSymbol.setParent(stringClass);
        stringClass.addMethod(substrMethodSymbol);

        globals.add(objectClass);
        globals.add(ioClass);
        globals.add(intClass);
        globals.add(stringClass);
        globals.add(boolClass);
    }
    
    /**
     * Displays a semantic error message.
     * 
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        while (! (ctx.getParent() instanceof CoolParser.ProgramContext))
            ctx = ctx.getParent();
        
        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static void error(String str) {
        String message = "Semantic error: " + str;
        
        System.err.println(message);
        
        semanticErrors = true;
    }
    
    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}
