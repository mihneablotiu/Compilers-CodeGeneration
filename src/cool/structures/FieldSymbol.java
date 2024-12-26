package cool.structures;

public class FieldSymbol extends Symbol {
    private ClassSymbolWrapper typeSymbol;
    private ClassSymbol introducingClass;

    public FieldSymbol(String name) {
        super(name);
    }

    public FieldSymbol(String name, ClassSymbol introducingClass) {
        super(name);
        this.introducingClass = introducingClass;
    }

    public ClassSymbolWrapper getTypeSymbol() {
        return typeSymbol;
    }

    public void setTypeSymbol(ClassSymbolWrapper typeSymbol) {
        this.typeSymbol = typeSymbol;
    }

    public ClassSymbol getIntroducingClass() {
        return introducingClass;
    }

    public void setIntroducingClass(ClassSymbol introducingClass) {
        this.introducingClass = introducingClass;
    }
}
