package fjord.compiler;

import fjord.Value;
import fjord.ast.*;

import static me.qmx.jitescript.CodeBlock.newCodeBlock;
import static me.qmx.jitescript.util.CodegenUtils.ci;
import static me.qmx.jitescript.util.CodegenUtils.p;
import static me.qmx.jitescript.util.CodegenUtils.sig;
import me.qmx.jitescript.JiteClass;

public class Codegen extends DefaultNodeVisitor {

  @Override public void visit(final ValueDefn defn) {
    final JiteClass jiteClass = new JiteClass(defn.pattern(), new String[] { p(Value.class) }) {{
      defineDefaultConstructor();

      defineMethod("eval", ACC_PUBLIC, sig(Object.class),
        newCodeBlock()
          .ldc(defn.expr())
          .areturn()
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
