package fjord.types;

public class Types {
  public static final TypeClass SBYTE  = new OperType("sbyte", Byte.class, byte.class) {
    @Override public Object parseValue(String s) {
      return Byte.parseByte(s);
    }
  };
  public static final TypeClass INT16  = new OperType("int16", Short.class, short.class) {
    @Override public Object parseValue(String s) {
      return Short.parseShort(s);
    }
  };
  public static final TypeClass INT32  = new OperType("int32", Integer.class, int.class) {
    @Override public Object parseValue(String s) {
      return Integer.parseInt(s);
    }
  };
  public static final TypeClass INT64  = new OperType("int64", Long.class, long.class) {
    @Override public Object parseValue(String s) {
      return Long.parseLong(s);
    }
  };
  public static final TypeClass BYTE   = new OperType("byte",  Byte.class, byte.class) {
    @Override public Object parseValue(String s) {
      return Byte.parseByte(s);
    }
  };
  public static final TypeClass UINT16 = new OperType("uint16", Short.class, short.class) {
    @Override public Object parseValue(String s) {
      return Short.parseShort(s);
    }
  };
  public static final TypeClass UINT32 = new OperType("uint32", Integer.class, int.class) {
    @Override public Object parseValue(String s) {
      return Integer.parseUnsignedInt(s);
    }
  };
  public static final TypeClass UINT64 = new OperType("uint64", Long.class, long.class) {
    @Override public Object parseValue(String s) {
      return Long.parseUnsignedLong(s);
    }
  };
  public static final TypeClass IEEE32 = new OperType("float32", Float.class, float.class) {
    @Override public Object parseValue(String s) {
      return Float.parseFloat(s);
    }
  };
  public static final TypeClass IEEE64 = new OperType("float", Double.class, double.class) {
    @Override public Object parseValue(String s) {
      return Double.parseDouble(s);
    }
  };
  public static final TypeClass BOOL = new OperType("bool", Boolean.class, boolean.class) {
    @Override public Object parseValue(String s) {
      return Boolean.parseBoolean(s);
    }
  };
  public static final TypeClass CHAR = new OperType("char", Character.class, char.class) {
    @Override public Object parseValue(String s) {
      return new Character(s.charAt(1));
    }
  };
}
