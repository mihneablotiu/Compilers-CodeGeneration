package cool.structures;

import cool.ast.ASTCodeGenVisitor;
import org.stringtemplate.v4.ST;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

class Pair {
    private String className;
    private String methodName;

    public Pair(String className, String methodName) {
        this.className = className;
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}

public class ClassSymbol extends Symbol implements Scope {
    private final Map<String, Symbol> fieldSymbols = new LinkedHashMap<>();
    private final Map<String, Symbol> methodSymbols = new LinkedHashMap<>();
    private static final ArrayList<Pair> overriddenMethods = new ArrayList<>();
    private final Scope parent = SymbolTable.globals;
    private Scope directParent = null;

    public ClassSymbol(String name) {
        super(name);
    }

    public boolean addField(Symbol sym) {
        // Reject duplicates in the same scope.
        if (fieldSymbols.containsKey(sym.getName()))
            return false;

        fieldSymbols.put(sym.getName(), sym);

        return true;
    }

    public boolean addMethod(Symbol sym) {
        // Reject duplicates in the same scope.
        if (methodSymbols.containsKey(sym.getName()))
            return false;

        methodSymbols.put(sym.getName(), sym);

        return true;
    }

    public Symbol lookupField(String name) {
        var sym = fieldSymbols.get(name);

        if (sym != null)
            return sym;

        if (directParent != null && directParent != SymbolTable.globals)
            return ((ClassSymbol) directParent).lookupField(name);

        return null;
    }

    public Symbol lookupMethod(String name) {
        var sym = methodSymbols.get(name);

        if (sym != null)
            return sym;

        if (directParent != null && directParent != SymbolTable.globals)
            return ((ClassSymbol) directParent).lookupMethod(name);

        return null;
    }

    public boolean isChildOf(ClassSymbol parent) {
        if (parent.name.equals("Object"))
            return true;

        if (this.name.equals(parent.name))
            return true;

        if (this.directParent == SymbolTable.globals || this.directParent == null)
            return false;

        return ((ClassSymbol) this.directParent).isChildOf(parent);
    }

    public int countParents() {
        if (this.name.equals("Object"))
            return 0;

        return 1 + ((ClassSymbol) this.directParent).countParents();
    }

    public ClassSymbol leastUpperBound(ClassSymbol other) {
        if (this == other)
            return this;

        if (this.isChildOf(other))
            return other;

        if (other.isChildOf(this))
            return this;

        return ((ClassSymbol) this.directParent).leastUpperBound(other);
    }

    public boolean isInCycle(ClassSymbol initialClass) {
        if (this.name.equals("Object") || this.directParent == null)
            return false;

        if (this == initialClass)
            return true;

        return ((ClassSymbol) this.directParent).isInCycle(initialClass);
    }

    public int numberOfAttributes() {
        if (!(directParent instanceof ClassSymbol)) {
            if (fieldSymbols.containsKey("self"))
                return fieldSymbols.size() - 1;
            else
                return fieldSymbols.size();
        }

        if (fieldSymbols.containsKey("self"))
            return fieldSymbols.size() - 1 + ((ClassSymbol) directParent).numberOfAttributes();
        else
            return fieldSymbols.size() + ((ClassSymbol) directParent).numberOfAttributes();
    }

    public void populateMethods(String initialClassName) {
        if (!(directParent instanceof ClassSymbol)) {
            methodSymbols.forEach((name, sym) -> {
                if (ASTCodeGenVisitor.classMethods.get(initialClassName).contains(name))
                    return;

                ASTCodeGenVisitor.classMethods.get(initialClassName).add(name);
            });

            return;
        }

        ((ClassSymbol) directParent).populateMethods(initialClassName);
        methodSymbols.forEach((name, sym) -> {
            if (ASTCodeGenVisitor.classMethods.get(initialClassName).contains(name))
                return;

            ASTCodeGenVisitor.classMethods.get(initialClassName).add(name);
        });
    }

    public void generateDispatchTable(ST methods, String initialClassName) {
        if (this.name.equals(initialClassName)) {
            overriddenMethods.clear();
        }

        if (!(directParent instanceof ClassSymbol)) {
            methodSymbols.forEach((name, sym) -> {
                for (Pair pair : overriddenMethods) {
                    if (pair.getMethodName().equals(name)) {
                        ST method = ASTCodeGenVisitor.templates.getInstanceOf("attrib");
                        method.add("initVal", pair.getClassName() + "." + name);

                        methods.add("e", method);

                        return;
                    }
                }

                ST method = ASTCodeGenVisitor.templates.getInstanceOf("attrib");
                method.add("initVal", this.getName() + "." + name);

                methods.add("e", method);
            });

            return;
        }

        ArrayList<Pair> currentOverriddenMethods = new ArrayList<>();
        for (Map.Entry<String, Symbol> entry : methodSymbols.entrySet()) {
            String name = entry.getKey();
            int existed = 0;

            for (Pair pair : overriddenMethods) {
                if (pair.getMethodName().equals(name)) {
                    existed = 1;
                    break;
                }
            }

            if (existed == 0) {
                currentOverriddenMethods.add(new Pair(this.getName(), name));
            }
        }

        overriddenMethods.addAll(0, currentOverriddenMethods);
        ((ClassSymbol) directParent).generateDispatchTable(methods, initialClassName);

        for (Map.Entry<String, Symbol> entry : methodSymbols.entrySet()) {
            String name = entry.getKey();

            ArrayList<Pair> toRemove = new ArrayList<>();
            for (Pair pair : overriddenMethods) {
                if (pair.getMethodName().equals(name)) {
                    ST method = ASTCodeGenVisitor.templates.getInstanceOf("attrib");
                    method.add("initVal", pair.getClassName() + "." + pair.getMethodName());

                    methods.add("e", method);

                    toRemove.add(pair);
                }
            }

            overriddenMethods.removeAll(toRemove);
        }
    }

    public void populateFields(String initialClassName) {
        if (!(directParent instanceof ClassSymbol)) {
            fieldSymbols.forEach((name, sym) -> {
                if (name.equals("self"))
                    return;

                ASTCodeGenVisitor.classAttributes.get(initialClassName).add(name);
            });

            return;
        }

        ((ClassSymbol) directParent).populateFields(initialClassName);
        fieldSymbols.forEach((name, sym) -> {
            if (name.equals("self"))
                return;

            ASTCodeGenVisitor.classAttributes.get(initialClassName).add(name);
        });
    }

    public void generateAttributes(ST attributes, String initialClassName) {
        if (!(directParent instanceof ClassSymbol)) {
            fieldSymbols.forEach((name, sym) -> {
                ST attribute = ASTCodeGenVisitor.templates.getInstanceOf("attrib");

                if (name.equals("self"))
                    return;

                switch (((FieldSymbol) sym).getTypeSymbol().getClassSymbol().getName()) {
                    case "Int" -> attribute.add("initVal", ASTCodeGenVisitor.generateIntLiteral(0));
                    case "Bool" -> attribute.add("initVal", ASTCodeGenVisitor.generateBoolLiteral(false));
                    case "String" -> attribute.add("initVal", ASTCodeGenVisitor.generateStringLiteral(""));
                    default -> attribute.add("initVal", "0");
                }

                attributes.add("e", attribute);
            });

            return;
        }

        ((ClassSymbol) directParent).generateAttributes(attributes, initialClassName);
        fieldSymbols.forEach((name, sym) -> {
            ST attribute = ASTCodeGenVisitor.templates.getInstanceOf("attrib");

            if (name.equals("self"))
                return;

            switch (((FieldSymbol) sym).getTypeSymbol().getClassSymbol().getName()) {
                case "Int" -> attribute.add("initVal", ASTCodeGenVisitor.generateIntLiteral(0));
                case "Bool" -> attribute.add("initVal", ASTCodeGenVisitor.generateBoolLiteral(false));
                case "String" -> attribute.add("initVal", ASTCodeGenVisitor.generateStringLiteral(""));
                default -> attribute.add("initVal", "0");
            }

            attributes.add("e", attribute);
        });

    }
    public void generateInitMethods(ST init, ST attr) {
        init.add("className", this.getName());
        init.add("directParent", ((ClassSymbol) this.directParent).getName());
    }

    @Override
    public boolean add(Symbol sym) {
        return false;
    }

    @Override
    public Symbol lookup(String str, boolean isField) {
        if (isField)
            return lookupField(str);
        else
            return lookupMethod(str);
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    public Map<String, Symbol> getFieldSymbols() {
        return fieldSymbols;
    }

    public Map<String, Symbol> getMethodSymbols() {
        return methodSymbols;
    }

    public Scope getDirectParent() {
        return directParent;
    }

    public void setDirectParent(Scope directParent) {
        this.directParent = directParent;
    }
}
