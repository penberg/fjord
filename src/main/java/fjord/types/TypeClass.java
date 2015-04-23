package fjord.types;

public interface TypeClass {
    Object   parseValue(String s);
    Class<?> getRefType();
    Class<?> getPrimType();
};
