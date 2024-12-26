package cool.ast;

import cool.structures.ClassSymbolWrapper;
import org.stringtemplate.v4.ST;

public class ReturnPair {
    ST st;
    ClassSymbolWrapper type;

    public ReturnPair(ST st, ClassSymbolWrapper type) {
        this.st = st;
        this.type = type;
    }

    public ST getSt() {
        return st;
    }

    public void setSt(ST st) {
        this.st = st;
    }

    public ClassSymbolWrapper getType() {
        return type;
    }

    public void setType(ClassSymbolWrapper type) {
        this.type = type;
    }
}
