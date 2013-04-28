package fjord.compiler;

import fjord.ast.expr.*;
import fjord.ast.*;
import fjord.*;

import static me.qmx.jitescript.CodeBlock.newCodeBlock;
import static me.qmx.jitescript.util.CodegenUtils.ci;
import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;
import me.qmx.jitescript.*;

public class Codegen extends DefaultNodeVisitor {

  private CodeBlock code;

  @Override public void visit(final ConstantExpression constant) {
    Const cons = constant.getCons();
    code.ldc(cons.toString());
  }

  @Override public void visitBefore(final ValueDefn defn) {
    code = newCodeBlock();
  }

  @Override public void visitAfter(final ValueDefn defn) {
    JiteClass jiteClass = new JiteClass(defn.pattern(), new String[] { p(Value.class) }) {{
      defineDefaultConstructor();

      defineMethod("eval", ACC_PUBLIC, sig(Object.class),
        code.areturn()
      );
    }};
    klass = new JiteClassLoader().define(jiteClass);
  }

  private static class JiteClassLoader extends ClassLoader {
    public Class<?> define(JiteClass jiteClass) {
      byte[] classBytes = jiteClass.toBytes();
      return super.defineClass(jiteClass.getClassName(), classBytes, 0, classBytes.length);
    }
  }

  public Class<?> getKlass() {
    return klass;
  }

  private Class<?> klass;
}
