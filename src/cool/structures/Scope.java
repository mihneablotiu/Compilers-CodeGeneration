package cool.structures;

public interface Scope {
    public boolean add(Symbol sym);
    
    public Symbol lookup(String str, boolean isField);
    
    public Scope getParent();
}
