package cool.structures;

public class FormalSymbol extends Symbol {
    private ClassSymbolWrapper typeSymbol;
    private MethodSymbol introducingMethod;

    public FormalSymbol(String name) {
        super(name);
    }

    public FormalSymbol(String name, MethodSymbol introducingMethod) {
        super(name);
        this.introducingMethod = introducingMethod;
    }

    public ClassSymbolWrapper getTypeSymbol() {
        return typeSymbol;
    }

    public void setTypeSymbol(ClassSymbolWrapper typeSymbol) {
        this.typeSymbol = typeSymbol;
    }

    public MethodSymbol getIntroducingMethod() {
        return introducingMethod;
    }

    public void setIntroducingMethod(MethodSymbol introducingMethod) {
        this.introducingMethod = introducingMethod;
    }
}
