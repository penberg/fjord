package fjord.types;

import java.util.*;
import fjord.ast.*;
import fjord.ast.expr.*;

public class TypeSystem {
  private int variableIds = 0;

  public Type analyse(Map<String, Type> env, Node node) {
    if (node instanceof Ident) {
      Ident ident = (Ident) node;
      return getType(ident.getValue(), env);
    } else if (node instanceof ValueDefn) {
      ValueDefn def = (ValueDefn) node;
      return analyse(env, def.getExpr());
    } else if (node instanceof ConstantExpression) {
      ConstantExpression cons = (ConstantExpression) node;
      return cons.getCons().getType();
    } else if (node instanceof ApplicationExpression) {
      ApplicationExpression ap = (ApplicationExpression) node;
      Type left = analyse(env, ap.getLeft());
      Type right = analyse(env, ap.getRight());
      unify(left, right);
      return left;
    } else {
      throw new RuntimeException(node.getClass().getSimpleName());
    }
  }

  private Type getType(String name, Map<String, Type> env) {
    if (env.containsKey(name)) {
      return fresh(env.get(name));
    }
    throw new RuntimeException("Undefined symbol: " + name);
  }

  private Type fresh(Type ty) {
    return prune(ty);
  }

  public TypeVariable newVariable() {
    return new TypeVariable(variableIds++);
  }

  public void unify(Type t1, Type t2) {
    Type type1 = prune(t1);
    Type type2 = prune(t2);
  }

  public Type prune(Type ty) {
    if (ty instanceof TypeVariable) {
      TypeVariable v = (TypeVariable) ty;
      if (v.instance.isPresent()) {
        Type inst = prune(v.instance.get());
        v.instance = Optional.of(inst);
        return inst;
      } else {
        return ty;
      }
    } else {
      return ty;
    }
  }
}
