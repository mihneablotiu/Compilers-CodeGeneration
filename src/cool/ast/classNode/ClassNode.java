package cool.ast.classNode;

import cool.ast.ASTNode;
import cool.ast.ASTVisitor;
import cool.ast.feature.Feature;
import cool.ast.type.TypeId;
import cool.structures.ClassSymbol;
import cool.structures.ClassSymbolWrapper;
import cool.structures.Scope;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class ClassNode extends ASTNode {
    private final TypeId className;
    private final TypeId parentName;
    private final ArrayList<Feature> features;
    private ClassSymbolWrapper classSymbolWrapper;
    public ClassNode(Token token, ParserRuleContext parserRuleContext,
                     TypeId className, TypeId parentName, ArrayList<Feature> features) {
        super(token, parserRuleContext);
        this.className = className;
        this.parentName = parentName;
        this.features = features;
    }

    public TypeId getClassName() {
        return className;
    }

    public TypeId getParentName() {
        return parentName;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public ClassSymbolWrapper getClassSymbolWrapper() {
        return classSymbolWrapper;
    }

    public void setClassSymbolWrapper(ClassSymbolWrapper classSymbolWrapper) {
        this.classSymbolWrapper = classSymbolWrapper;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
