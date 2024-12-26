package cool.structures;

public class ClassSymbolWrapper {
    private final ClassSymbol classSymbol;
    private boolean isSelfType;

    public ClassSymbolWrapper(ClassSymbol classSymbol, boolean isSelfType) {
        this.classSymbol = classSymbol;
        this.isSelfType = isSelfType;
    }

    public ClassSymbol getClassSymbol() {
        return classSymbol;
    }

    public boolean isSelfType() {
        return isSelfType;
    }

    public void setSelfType(boolean isSelfType) {
        this.isSelfType = isSelfType;
    }
}
