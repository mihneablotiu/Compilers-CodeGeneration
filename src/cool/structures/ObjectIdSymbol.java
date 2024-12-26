package cool.structures;

public class ObjectIdSymbol extends Symbol {
    private ClassSymbolWrapper typeSymbol;
    private Scope introducingScope;

    public ObjectIdSymbol(String name) {
        super(name);
    }

    public ClassSymbolWrapper getTypeSymbol() {
        return typeSymbol;
    }

    public void setTypeSymbol(ClassSymbolWrapper typeSymbol) {
        this.typeSymbol = typeSymbol;
    }

    public Scope getIntroducingScope() {
        return introducingScope;
    }

    public void setIntroducingScope(Scope introducingScope) {
        this.introducingScope = introducingScope;
    }
}
