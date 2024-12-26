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
import cool.compiler.Compiler;
import cool.structures.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ASTCodeGenVisitor implements ASTVisitor<ReturnPair> {
    public static STGroupFile templates = new STGroupFile("cool/cgen.stg");
    static int objectTag = 0;
    static int ioTag = 1;
    static int intTag = 2;
    static int stringTag = 3;
    static int boolTag = 4;
    static int nextAvailableTag = 5;

    static ST literalConsts; // literal constants
    ST protObjs; // prototype objects
    ST dispTabs; // tabele de metode
    ST inits; // metode de initializare
    ST methods; // implementarea metodelor din clase
    ST classNames; // literalii string care au numele de clase
    ST classObjects; // obiectele existente in program
    static Map<Integer, String> intLiterals = new HashMap<>();
    static Map<Boolean, String> boolLiterals = new HashMap<>();
    static Map<String, String> stringLiterals = new HashMap<>();
    public static Map<String, List<String>> classMethods = new HashMap<>();
    public static Map<String, List<String>> classAttributes = new HashMap<>();
    static int intLiteralCount = 0;
    static int stringLiteralCount = 0;
    static int dispatchCount = 0;
    static ClassNode currentClass;

    public static String generateIntLiteral(int value) {
        if (!intLiterals.containsKey(value)) {
            ST int_const = templates.getInstanceOf("intLiteral");
            int_const.add("count", intLiteralCount++)
                     .add("value", value);

            literalConsts.add("e", int_const);
            intLiterals.put(value, "int_const" + (intLiteralCount - 1));
        }

        return intLiterals.get(value);
    }
    public static String generateBoolLiteral(boolean value) {
        return boolLiterals.get(value);
    }
    public static String generateStringLiteral(String value) {
        if (!stringLiterals.containsKey(value)) {
            ST str_const = templates.getInstanceOf("stringLiteral");
            str_const.add("count", stringLiteralCount++)
                     .add("size", 5 + (int) Math.ceil((float) value.length()) / 4)
                     .add("length", generateIntLiteral(value.length()))
                     .add("value", "\"" + value + "\"");

            literalConsts.add("e", str_const);
            stringLiterals.put(value, "str_const" + (stringLiteralCount - 1));
        }

        return stringLiterals.get(value);
    }
    public void generateProtObject(ClassNode classNode) {
        ST protObj = templates.getInstanceOf("protObj");
        ST attributes = templates.getInstanceOf("sequence");

        classNode.getClassSymbolWrapper().getClassSymbol().generateAttributes(attributes, classNode.getClassName().getToken().getText());

        protObj.add("className", classNode.getClassName().getToken().getText())
                .add("classId", nextAvailableTag++)
                .add("classDim", classNode.getClassSymbolWrapper().getClassSymbol().numberOfAttributes() + 3)
                .add("attribs", attributes);

        protObjs.add("e", protObj);

        String classLiteralName = generateStringLiteral(classNode.getClassName().getToken().getText());

        classNames.add("e", templates.getInstanceOf("attrib")
                .add("initVal", classLiteralName));

        classObjects.add("e", templates.getInstanceOf("attrib")
                .add("initVal", classNode.getClassName().getToken().getText() + "_protObj"));
    }
    public void generateDispatchTables(ClassNode classNode) {
        ST dispTab = templates.getInstanceOf("dispatchTable");
        ST methods = templates.getInstanceOf("sequence");

        classNode.getClassSymbolWrapper().getClassSymbol().generateDispatchTable(methods, classNode.getClassName().getToken().getText());

        dispTab.add("className", classNode.getClassName().getToken().getText())
               .add("methods", methods);

        dispTabs.add("e", dispTab);
    }
    public void generateInits(ClassNode classNode) {
        ST init = templates.getInstanceOf("init");
        ST body = templates.getInstanceOf("sequenceSpaced");

        classNode.getClassSymbolWrapper().getClassSymbol().generateInitMethods(init, body);

        classNode.getFeatures().forEach(feature -> {
            if (feature instanceof Field field) {
                if (field.getInitialExpr() != null) {
                    ST fieldInit = templates.getInstanceOf("attrInit");
                    fieldInit.add("initExpr", field.getInitialExpr().accept(this).getSt());
                    fieldInit.add("offset", classAttributes.get(classNode.getClassName().getToken().getText()).indexOf(field.getFieldId().getToken().getText()) * 4 + 12);

                    body.add("e", fieldInit);
                }
            }
        });

        init.add("attrInit", body);

        inits.add("e", init);

        classObjects.add("e", templates.getInstanceOf("attrib")
                .add("initVal", classNode.getClassName().getToken().getText() + "_init"));
    }
    public void generateNullLiterals() {
        generateIntLiteral(0);
        generateStringLiteral("");

        boolLiterals.put(false, "bool_const0");
        boolLiterals.put(true, "bool_const1");
    }
    public void generateObjectBasicCode() {
        ST protObjObj = templates.getInstanceOf("protObj");
        protObjObj.add("className", "Object")
                .add("classId", objectTag)
                .add("classDim", 3);

        protObjs.add("e", protObjObj);

        generateStringLiteral("Object");

        ArrayList<String> objMethods = new ArrayList<>(Arrays.asList("abort", "type_name", "copy"));
        classMethods.put("Object", objMethods);
    }
    public void generateIOBasicCode() {
        ST protObjIO = templates.getInstanceOf("protObj");
        protObjIO.add("className", "IO")
                .add("classId", ioTag)
                .add("classDim", 3);

        protObjs.add("e", protObjIO);

        generateStringLiteral("IO");

        ArrayList<String> ioMethods = new ArrayList<>(Arrays.asList("abort", "type_name", "copy", "out_string", "out_int", "in_string", "in_int"));
        classMethods.put("IO", ioMethods);
    }
    public void generateIntBasicCode() {
        ST protObjInt = templates.getInstanceOf("protObj");
        protObjInt.add("className", "Int")
                .add("classId", intTag)
                .add("classDim", 4)
                .add("attribs", templates.getInstanceOf("sequence")
                        .add("e", templates.getInstanceOf("attrib")
                                .add("initVal", "0")));

        protObjs.add("e", protObjInt);

        generateStringLiteral("Int");

        ArrayList<String> intMethods = new ArrayList<>(Arrays.asList("abort", "type_name", "copy"));
        classMethods.put("Int", intMethods);
    }
    public void generateStringBasicCode() {
        ST protObjString = templates.getInstanceOf("protObj");
        protObjString.add("className", "String")
                .add("classId", stringTag)
                .add("classDim", 5)
                .add("attribs", templates.getInstanceOf("attribString")
                        .add("initVal", "\"\"")
                        .add("length", generateIntLiteral(0)));

        protObjs.add("e", protObjString);

        generateStringLiteral("String");

        ArrayList<String> stringMethods = new ArrayList<>(Arrays.asList("abort", "type_name", "copy", "length", "concat", "substr"));
        classMethods.put("String", stringMethods);
    }
    public void generateBoolBasicCode() {
        ST protObjBool = templates.getInstanceOf("protObj");
        protObjBool.add("className", "Bool")
                .add("classId", boolTag)
                .add("classDim", 4)
                .add("attribs", templates.getInstanceOf("sequence")
                        .add("e", templates.getInstanceOf("attrib")
                                .add("initVal", "0")));

        protObjs.add("e", protObjBool);

        generateStringLiteral("Bool");

        ArrayList<String> boolMethods = new ArrayList<>(Arrays.asList("abort", "type_name", "copy"));
        classMethods.put("Bool", boolMethods);
    }
    public void generateBasicCode() {
        generateNullLiterals();
        generateObjectBasicCode();
        generateIOBasicCode();
        generateIntBasicCode();
        generateStringBasicCode();
        generateBoolBasicCode();


    }

    public ST loadMethodParameters(ArrayList<Expression> params) {
        ST paramST = templates.getInstanceOf("sequence");

        ArrayList<Expression> copyParams = new ArrayList<>(params);
        Collections.reverse(copyParams);
        copyParams.forEach(param -> {
            ST loadParamST = templates.getInstanceOf("loadParam");
            loadParamST.add("expr", param.accept(this).getSt());
            paramST.add("e", loadParamST);
        });

        return paramST;
    }

    @Override
    public ReturnPair visit(Branch branch) {
        return null;
    }

    @Override
    public ReturnPair visit(ClassNode classNode) {
        generateProtObject(classNode);
        generateDispatchTables(classNode);
        generateInits(classNode);

        classNode.getFeatures().forEach(feature -> {
            currentClass = classNode;
            feature.accept(this);
        });

        return null;
    }

    @Override
    public ReturnPair visit(Arithmetic arithmetic) {
        ST arithmeticST = templates.getInstanceOf("arithmeticExpr");

        ST leftST = arithmetic.getLeft().accept(this).getSt();
        ST rightST = arithmetic.getRight().accept(this).getSt();

        arithmeticST.add("leftExpr", leftST);
        arithmeticST.add("rightExpr", rightST);
        switch (arithmetic.getOp().getText()) {
            case "+":
                arithmeticST.add("op", "add");
                break;
            case "-":
                arithmeticST.add("op", "sub");
                break;
            case "*":
                arithmeticST.add("op", "mul");
                break;
            case "/":
                arithmeticST.add("op", "div");
                break;
        }

        return new ReturnPair(arithmeticST, arithmetic.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(Assign assign) {
        ST assignST = templates.getInstanceOf("assignExpr");

        ST exprST = assign.getExpr().accept(this).getSt();
        if (assign.getObjectId().getObjectIdSymbol().getIntroducingScope() instanceof ClassSymbol classSymbol) {
            assignST.add("offset", classAttributes.get(classSymbol.getName()).indexOf(assign.getObjectId().getToken().getText()) * 4 + 12);
            assignST.add("basePointer", "$s0");
            assignST.add("expr", exprST);

            return new ReturnPair(assignST, assign.getComputedReturnType());
        } else if (assign.getObjectId().getObjectIdSymbol().getIntroducingScope() instanceof MethodSymbol methodSymbol) {
            assignST.add("offset", methodSymbol.getMethod()
                    .getFormals().stream().map(f -> f.getObjectId().getToken().getText())
                    .toList().indexOf(assign.getObjectId().getToken().getText()) * 4 + 12);
            assignST.add("basePointer", "$fp");
            assignST.add("expr", exprST);

            return new ReturnPair(assignST, assign.getComputedReturnType());
        } else if (assign.getObjectId().getObjectIdSymbol().getIntroducingScope() instanceof LocalSymbol localSymbol) {
            int count = 0;
            LocalSymbol copy = localSymbol;

            while (copy.getParent() instanceof LocalSymbol) {
                count++;
                copy = (LocalSymbol) copy.getParent();
            }

            assignST.add("offset", count * (-4) - 4);
            assignST.add("basePointer", "$fp");
            assignST.add("expr", exprST);

            return new ReturnPair(assignST, assign.getComputedReturnType());
        }

        return null;
    }

    @Override
    public ReturnPair visit(Block block) {
        ST blockST = templates.getInstanceOf("sequence");
        block.getExpressions().forEach(expr -> blockST.add("e", expr.accept(this).getSt()));

        return new ReturnPair(blockST, block.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(Bool bool) {
        ST boolSt = templates.getInstanceOf("loadLiteral");

        boolSt.add("value", generateBoolLiteral(Boolean.parseBoolean(bool.getToken().getText())));

        return new ReturnPair(boolSt, bool.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(Case caseExpr) {
        ST caseST = templates.getInstanceOf("case");

        caseST.add("expr", caseExpr.getCaseExpr().accept(this).getSt());
        caseST.add("caseLabel", "case" + dispatchCount++);

        String fileName = Compiler.fileNames.get(currentClass.getParserRuleContext());
        caseST.add("fileName", generateStringLiteral(fileName.substring(fileName.lastIndexOf("/") + 1)));
        caseST.add("fileLine", caseExpr.getToken().getLine());

        return new ReturnPair(caseST, caseExpr.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(ExplicitCall explicitCall) {
        ST callST = templates.getInstanceOf("explicitCall");

        String fileName = Compiler.fileNames.get(currentClass.getParserRuleContext());
        ReturnPair dispExpr = explicitCall.getDispatchExpr().accept(this);

        callST.add("params", loadMethodParameters(explicitCall.getParams()));
        callST.add("dispExpr", dispExpr.getSt());
        callST.add("fileName", generateStringLiteral(fileName.substring(fileName.lastIndexOf("/") + 1)));
        callST.add("fileLine", explicitCall.getToken().getLine());
        callST.add("methodOffset", classMethods.get(dispExpr.getType().getClassSymbol().getName()).indexOf(explicitCall.getMethodId().getToken().getText()) * 4);
        callST.add("dispCount", dispatchCount);

        if (explicitCall.getClassType() == null) {
            callST.add("dispTable", "lw $t1 8($a0)");
        } else {
            callST.add("dispTable", "la $t1 " + explicitCall.getClassType().getToken().getText() + "_dispTab");
        }

        dispatchCount++;

        return new ReturnPair(callST, explicitCall.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(If ifExpr) {
        ST condExpr = ifExpr.getCond().accept(this).getSt();
        ST thenExpr = ifExpr.getThenBranch().accept(this).getSt();
        ST elseExpr = ifExpr.getElseBranch().accept(this).getSt();

        ST ifST = templates.getInstanceOf("if");

        ifST.add("condExpr", condExpr);
        ifST.add("trueExpr", thenExpr);
        ifST.add("elseExpr", elseExpr);
        ifST.add("elseLabel", "else" + dispatchCount++);
        ifST.add("endifLabel", "endif" + dispatchCount++);

        return new ReturnPair(ifST, ifExpr.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(ImplicitCall implicitCall) {
        ST callST = templates.getInstanceOf("implicitCall");

        String fileName = Compiler.fileNames.get(currentClass.getParserRuleContext());

        callST.add("params", loadMethodParameters(implicitCall.getParams()));
        callST.add("fileName", generateStringLiteral(fileName.substring(fileName.lastIndexOf("/") + 1)));
        callST.add("fileLine", implicitCall.getToken().getLine());
        callST.add("methodOffset", classMethods.get(currentClass.getClassName().getToken().getText()).indexOf(implicitCall.getMethodId().getToken().getText()) * 4);
        callST.add("dispCount", dispatchCount);
        dispatchCount++;

        return new ReturnPair(callST, implicitCall.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(Int intExpr) {
        ST intST = templates.getInstanceOf("loadLiteral");

        intST.add("value", generateIntLiteral(Integer.parseInt(intExpr.getToken().getText())));

        return new ReturnPair(intST, intExpr.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(Let let) {
        ST letST = templates.getInstanceOf("let");

        letST.add("nrLocals", let.getLocals().size() * 4);

        ST locals = templates.getInstanceOf("sequence");
        let.getLocals().forEach(local -> {
            if (local.getAssignExpr() != null) {
                locals.add("e", local.getAssignExpr().accept(this).getSt());
            } else {
                ST literalLocal = templates.getInstanceOf("loadLiteral");

                switch (local.getTypeId().getToken().getText()) {
                    case "Int" -> literalLocal.add("value", generateIntLiteral(0));
                    case "Bool" -> literalLocal.add("value", generateBoolLiteral(false));
                    case "String" -> literalLocal.add("value", generateStringLiteral(""));
                    default -> literalLocal.add("value", "0");
                }

                locals.add("e", literalLocal);
            }

            ST assignST = templates.getInstanceOf("assignExpr");
            assignST.add("offset", (let.getLocals().indexOf(local) + 1) * (-4));
            assignST.add("basePointer", "$fp");

            locals.add("e", assignST);
        });

        letST.add("body", let.getLetExpr().accept(this).getSt());
        letST.add("inits", locals);

        return new ReturnPair(letST, let.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(New newExpr) {
        if (newExpr.getTypeId().getToken().getText().equals("SELF_TYPE")) {
            ST newSelfTypeST = templates.getInstanceOf("newSelfType");

            return new ReturnPair(newSelfTypeST, newExpr.getComputedReturnType());
        } else {
            ST newST = templates.getInstanceOf("new");

            newST.add("protObject", newExpr.getTypeId().getToken().getText() + "_protObj");
            newST.add("initFunc", newExpr.getTypeId().getToken().getText() + "_init");

            return new ReturnPair(newST, newExpr.getComputedReturnType());
        }
    }

    @Override
    public ReturnPair visit(Logical logical) {
        ST leftST = logical.getLeft().accept(this).getSt();
        ST rightST = logical.getRight().accept(this).getSt();
        if (logical.getOp().getText().equals("=")) {
            ST eqST = templates.getInstanceOf("equality");

            eqST.add("leftExpr", leftST);
            eqST.add("rightExpr", rightST);
            eqST.add("eqLabel", "eq" + dispatchCount++);

            return new ReturnPair(eqST, logical.getComputedReturnType());
        }

        ST relST = templates.getInstanceOf("compare");
        relST.add("leftExpr", leftST);
        relST.add("rightExpr", rightST);
        relST.add("relLabel", "compare" + dispatchCount++);

        if (logical.getOp().getText().equals("<"))
            relST.add("op", "blt");
        else
            relST.add("op", "ble");

        return new ReturnPair(relST, logical.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(ObjectId objectId) {
        ST idST = templates.getInstanceOf("loadAttrib");
        if (objectId.getToken().getText().equals("self"))
            return new ReturnPair(new ST("\tmove $a0 $s0"), objectId.getComputedReturnType());

        if (objectId.getObjectIdSymbol().getIntroducingScope() instanceof ClassSymbol classSymbol) {
            idST.add("offset", classAttributes.get(classSymbol.getName()).indexOf(objectId.getToken().getText()) * 4 + 12);
            idST.add("basePointer", "$s0");
        } else if (objectId.getObjectIdSymbol().getIntroducingScope() instanceof MethodSymbol methodSymbol) {
            idST.add("offset", methodSymbol.getMethod()
                    .getFormals().stream().map(f -> f.getObjectId().getToken().getText())
                    .toList().indexOf(objectId.getToken().getText()) * 4 + 12);
            idST.add("basePointer", "$fp");
        } else if (objectId.getObjectIdSymbol().getIntroducingScope() instanceof LocalSymbol localSymbol) {
            int count = 0;
            LocalSymbol copy = localSymbol;

            while (copy.getParent() instanceof LocalSymbol) {
                count++;
                copy = (LocalSymbol) copy.getParent();
            }

            idST.add("offset", count * (-4) - 4);
            idST.add("basePointer", "$fp");
        }


        return new ReturnPair(idST, objectId.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(Paren paren) {
        return paren.getExpr().accept(this);
    }

    @Override
    public ReturnPair visit(Str str) {
        ST strST = templates.getInstanceOf("loadLiteral");

        strST.add("value", generateStringLiteral(str.getToken().getText()));

        return new ReturnPair(strST, str.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(Unary unary) {
        if (unary.getToken().getText().equals("isvoid")) {
            ST exprST = unary.getExpr().accept(this).getSt();

            ST isVoidST = templates.getInstanceOf("isvoid");
            isVoidST.add("expr", exprST);
            isVoidST.add("isvoidCount", "isvoid" + dispatchCount++);

            return new ReturnPair(isVoidST, unary.getComputedReturnType());
        } else if (unary.getToken().getText().equals("not")) {
            ST exprST = unary.getExpr().accept(this).getSt();

            ST notST = templates.getInstanceOf("not");
            notST.add("expr", exprST);
            notST.add("notCount", "not" + dispatchCount++);

            return new ReturnPair(notST, unary.getComputedReturnType());
        }

        ST exprST = unary.getExpr().accept(this).getSt();

        ST tildaCpopST = templates.getInstanceOf("tildaCpop");
        tildaCpopST.add("expr", exprST);

        return new ReturnPair(tildaCpopST, unary.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(While whileExpr) {
        ST whileST = templates.getInstanceOf("while");

        whileST.add("condition", whileExpr.getCondExpr().accept(this).getSt());
        whileST.add("body", whileExpr.getInsideExpr().accept(this).getSt());
        whileST.add("whileLabel", "while" + dispatchCount++);
        whileST.add("endWhileLabel", "endwhile" + dispatchCount++);

        return new ReturnPair(whileST, whileExpr.getComputedReturnType());
    }

    @Override
    public ReturnPair visit(Field field) {
        return null;
    }

    @Override
    public ReturnPair visit(Method method) {
        ST methodST = templates.getInstanceOf("methodDeclare");
        methodST.add("className", ((ClassSymbol) method.getMethodSymbol().getParent()).getName());
        methodST.add("methodName", method.getMethodId().getToken().getText());
        methodST.add("body", method.getInsideExpr().accept(this).getSt());
        methodST.add("nrParams", new ST("\taddiu $sp $sp " + method.getFormals().size() * 4));

        methods.add("e", methodST);

        return null;
    }

    @Override
    public ReturnPair visit(Local local) {
        return null;
    }

    @Override
    public ReturnPair visit(Formal formal) {
        return null;
    }

    @Override
    public ReturnPair visit(Program program) {
        literalConsts = templates.getInstanceOf("sequenceSpaced");
        protObjs = templates.getInstanceOf("sequence");
        dispTabs = templates.getInstanceOf("sequence");
        inits = templates.getInstanceOf("sequenceSpaced");
        methods = templates.getInstanceOf("sequence");
        classNames = templates.getInstanceOf("sequence");
        classObjects = templates.getInstanceOf("sequence");

        generateBasicCode();

        for (ClassNode cls : program.getClasses())
            cls.accept(this);

        //assembly-ing it all together. HA! get it?
        var programST = templates.getInstanceOf("program");
        programST.add("literalConsts", literalConsts);
        programST.add("protObjs", protObjs);
        programST.add("dispTabs", dispTabs);
        programST.add("inits", inits);
        programST.add("methods", methods);
        programST.add("classNames", classNames);
        programST.add("classObjects", classObjects);

        return new ReturnPair(programST, null);
    }

    @Override
    public ReturnPair visit(TypeId typeId) {
        return null;
    }
}
