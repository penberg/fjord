package fjord.runtime;

import me.qmx.jitescript.*;

public class JiteClassLoader extends ClassLoader {
  public static JiteClassLoader INSTANCE = new JiteClassLoader();

  private JiteClassLoader() {
  }

  public Class<?> define(JiteClass jiteClass) {
    byte[] classBytes = jiteClass.toBytes(JDKVersion.V1_7);
    return super.defineClass(jiteClass.getClassName(), classBytes, 0, classBytes.length);
  }
}
