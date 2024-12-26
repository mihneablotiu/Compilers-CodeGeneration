package cool.ast.program;

import cool.ast.ASTNode;
import cool.ast.ASTVisitor;
import cool.ast.classNode.ClassNode;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class Program extends ASTNode {
    private final ArrayList<ClassNode> classes;

    public Program(Token token, ParserRuleContext parserRuleContext, ArrayList<ClassNode> classes) {
        super(token, parserRuleContext);
        this.classes = classes;
    }

    public ArrayList<ClassNode> getClasses() {
        return classes;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
