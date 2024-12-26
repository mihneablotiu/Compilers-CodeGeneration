package cool.structures;

import java.util.HashMap;

public class LocalSymbol extends Symbol implements Scope {
    private ClassSymbolWrapper typeSymbol;
    private Scope parent;
    private final HashMap<String, Symbol> localSymbols = new HashMap<>();

    public LocalSymbol(String name) {
        super(name);
    }

    public void setParent(Scope parent) {
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        // Reject duplicates in the same scope.
        if (localSymbols.containsKey(sym.getName()))
            return false;

        localSymbols.put(sym.getName(), sym);

        return true;
    }

    @Override
    public Symbol lookup(String name, boolean isField) {
        var sym = localSymbols.get(name);

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

    public ClassSymbolWrapper getTypeSymbol() {
        return typeSymbol;
    }

    public void setTypeSymbol(ClassSymbolWrapper typeSymbol) {
        this.typeSymbol = typeSymbol;
    }
}
