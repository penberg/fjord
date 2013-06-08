package fjord.compiler;

import fjord.ast.expr.*;
import fjord.runtime.*;
import fjord.ast.*;
import fjord.*;

import static me.qmx.jitescript.CodeBlock.newCodeBlock;
import static me.qmx.jitescript.JiteClass.*;
import static me.qmx.jitescript.util.CodegenUtils.ci;
import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;
import me.qmx.jitescript.*;

public class Codegen extends DefaultNodeVisitor {

  private JiteClass jiteClass = new JiteClass("ScriptFragment", new String[] { p(Value.class) }) {{
    defineDefaultConstructor();
  }};

  private CodeBlock code;

  @Override public void visit(final ConstantExpression constant) {
    Const cons = constant.getCons();
    code.ldc(cons.toString());
  }

  @Override public void visitBefore(final ValueDefn defn) {
    code = newCodeBlock();
  }

  @Override public void visitAfter(final ValueDefn defn) {
    jiteClass.defineMethod(defn.pattern(), ACC_PUBLIC | ACC_STATIC, sig(Object.class),
      code.areturn()
    );
    jiteClass.defineMethod("eval", ACC_PUBLIC, sig(Object.class), new CodeBlock() {{
      invokedynamic(defn.pattern(), sig(Object.class), Bootstrap.HANDLE);
      areturn();
    }});
    JiteClassLoader.INSTANCE.define(jiteClass);
  }
}
