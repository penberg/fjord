package fjord.runtime;

import fjord.compiler.*;

import static me.qmx.jitescript.util.CodegenUtils.*;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Handle;

import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.*;

public class Bootstrap {
  public static Handle HANDLE;

  static {
    HANDLE = new Handle(
                     Opcodes.H_INVOKESTATIC,
                     p(Bootstrap.class),
                     "bootstrap",
                     MethodType.methodType(CallSite.class, Lookup.class, String.class, MethodType.class).toMethodDescriptorString());
  }

  public static CallSite bootstrap(Lookup lookup, String name, MethodType type) throws Throwable {
    MethodHandle target = lookup.findStatic(JiteClassLoader.INSTANCE.loadClass("ScriptFragment"), name, type);
    return new ConstantCallSite(target);
  }

}
