package fjord.types;

import java.util.*;
import fjord.ast.*;
import fjord.ast.expr.*;

public class TypeSystem {
  private int variableIds = 0;

  public TypeClass analyse(Map<String, TypeClass> env, Node node) {
    if (node instanceof Ident) {
      Ident ident = (Ident) node;
      return getType(ident.getValue(), env);
    } else if (node instanceof ValueDefn) {
      ValueDefn def = (ValueDefn) node;
      return analyse(env, def.getExpr());
    } else if (node instanceof ConstantExpression) {
      ConstantExpression cons = (ConstantExpression) node;
      return cons.getCons().getTypeClass();
    } else if (node instanceof ApplicationExpression) {
      ApplicationExpression ap = (ApplicationExpression) node;
      TypeClass left = analyse(env, ap.getLeft());
      TypeClass right = analyse(env, ap.getRight());
      unify(left, right);
      return left;
    } else {
      throw new RuntimeException(node.getClass().getSimpleName());
    }
  }

  private TypeClass getType(String name, Map<String, TypeClass> env) {
    if (env.containsKey(name)) {
      return fresh(env.get(name));
    }
    throw new RuntimeException("Undefined symbol: " + name);
  }

  private TypeClass fresh(TypeClass ty) {
    return prune(ty);
  }

  public VarType newVariable() {
    return new VarType(variableIds++);
  }

  public void unify(TypeClass t1, TypeClass t2) {
    TypeClass typeClass1 = prune(t1);
    TypeClass typeClass2 = prune(t2);
  }

  public TypeClass prune(TypeClass ty) {
    if (ty instanceof VarType) {
      VarType v = (VarType) ty;
      if (v.instance.isPresent()) {
        TypeClass inst = prune(v.instance.get());
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
