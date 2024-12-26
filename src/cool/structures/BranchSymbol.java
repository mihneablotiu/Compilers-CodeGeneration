package cool.structures;

import java.util.HashMap;

public class BranchSymbol extends Symbol implements Scope {
    private ClassSymbolWrapper typeSymbol;
    private Scope parent;
    private final HashMap<String, Symbol> branchSymbols = new HashMap<>();

    public BranchSymbol(String name) {
        super(name);
    }

    @Override
    public boolean add(Symbol sym) {
        // Reject duplicates in the same scope.
        if (branchSymbols.containsKey(sym.getName()))
            return false;

        branchSymbols.put(sym.getName(), sym);

        return true;
    }

    @Override
    public Symbol lookup(String name, boolean isField) {
        var sym = branchSymbols.get(name);

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

    public void setParent(Scope parent) {
        this.parent = parent;
    }
}
