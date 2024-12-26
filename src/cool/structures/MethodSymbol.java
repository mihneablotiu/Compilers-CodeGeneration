package cool.structures;

import cool.ast.feature.Method;

import java.util.HashMap;

public class MethodSymbol extends Symbol implements Scope {
    private ClassSymbolWrapper typeSymbol;
    private final HashMap<String, Symbol> formalSymbols = new HashMap<>();
    private Scope parent;
    private final Method method;

    public MethodSymbol(String name, Method method) {
        super(name);
        this.method = method;
    }

    public ClassSymbolWrapper getTypeSymbol() {
        return typeSymbol;
    }

    public void setTypeSymbol(ClassSymbolWrapper typeSymbol) {
        this.typeSymbol = typeSymbol;
    }

    public Method getMethod() {
        return method;
    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        // Reject duplicates in the same scope.
        if (formalSymbols.containsKey(sym.getName()))
            return false;

        formalSymbols.put(sym.getName(), sym);

        return true;
    }

    @Override
    public Symbol lookup(String name, boolean isField) {
        var sym = formalSymbols.get(name);

        if (sym != null)
            return sym;

        if (parent != null)
            return parent.lookup(name, isField);

        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }
}
