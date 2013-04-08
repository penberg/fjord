package fjord.ast;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class OperatorStringifier {

  private static final String OP_NAME_PREFIX = "op_";

  private static final Function<Character, String> OPERATOR_MAPPER = new OperatorMapper();

  private static final Map<String, String> SYMBOLIC_OPERATOR_MAPPING = Maps.newHashMap();

  private static final Map<Character, String> OPERATOR_NAME_MAPPING = Maps.newHashMap();

  static {
    Map<String, String> M = SYMBOLIC_OPERATOR_MAPPING;

    M.put("[]", "op_Nil");
    M.put("::", "op_ColonColon");
    M.put("+", "op_Addition");
    M.put("-", "op_Substraction");
    M.put("*", "op_Multiply");
    M.put("/", "op_Division");
    M.put("**", "op_Exponentiation");
    M.put("@", "op_Append");
    M.put("^", "op_Concatenate");
    M.put("%", "op_Modulus");
    M.put("&&&", "op_BitwiseAnd");
    M.put("|||", "op_BitwiseOr");
    M.put("^^^", "op_ExlusiveOr");
    M.put("<<<", "op_LeftShift");
    M.put("~~~", "op_LogicalNot");
    M.put(">>>", "op_ShiftRight");
    M.put("~-", "op_UnaryNegation");
    M.put("=", "op_Equality");
    M.put("<>", "op_Inequality");
    M.put("<=", "op_LessThanOrEqual");
    M.put(">=", "op_GreaterThanOrEqual");
    M.put("<", "op_LessThan");
    M.put(">", "op_GreaterThan");
    M.put("?", "op_Dynamic");
    M.put("?<-", "op_DynamicAssignment");
    M.put("|>", "op_PipeRight");
    M.put("||>", "op_PipeRight2");
    M.put("|||>", "op_PipeRight3");
    M.put("<|", "op_PipeLeft");
    M.put("<||", "op_PipeLeft2");
    M.put("<|||", "op_PipeLeft3");
    M.put("!", "op_Dereference");
    M.put(">>", "op_ComposeRight");
    M.put("<<", "op_ComposeLeft");
    M.put("<@ @>", "op_Quotation");
    M.put("<@@ @@>", "op_QuotationUntyped");
    M.put("~%", "op_Splice");
    M.put("~%%", "op_SpliceUntyped");
    M.put("~&", "op_AddressOf");
    M.put("~&&", "op_IntegerAddressOf");
    M.put("||", "op_BooleanOr");
    M.put("&&", "op_BooleanAnd");
    M.put("+=", "op_AdditionAssignment");
    M.put("-=", "op_SubstractionAssignment");
    M.put("*=", "op_MultiplyAssignment");
    M.put("/=", "op_DivisionAssignment");
    M.put("..", "op_Range");
    M.put(".. ..", "op_RangeStep");

    Map<Character, String> M1 = OPERATOR_NAME_MAPPING;

    M1.put(Character.valueOf('>'), "Greater");
    M1.put(Character.valueOf('<'), "Less");
    M1.put(Character.valueOf('+'), "Plus");
    M1.put(Character.valueOf('-'), "Minus");
    M1.put(Character.valueOf('*'), "Multiply");
    M1.put(Character.valueOf('='), "Equals");
    M1.put(Character.valueOf('~'), "Twiddle");
    M1.put(Character.valueOf('%'), "Percent");
    M1.put(Character.valueOf('.'), "Dot");
    M1.put(Character.valueOf('&'), "Amp");
    M1.put(Character.valueOf('|'), "Bar");
    M1.put(Character.valueOf('@'), "At");
    M1.put(Character.valueOf('#'), "Hash");
    M1.put(Character.valueOf('^'), "Hat");
    M1.put(Character.valueOf('!'), "Bang");
    M1.put(Character.valueOf('?'), "Qmark");
    M1.put(Character.valueOf('/'), "Divide");
    M1.put(Character.valueOf(':'), "Colon");
    M1.put(Character.valueOf('('), "LParen");
    M1.put(Character.valueOf(','), "Comma");
    M1.put(Character.valueOf(')'), "RParen");
    M1.put(Character.valueOf('['), "LBrack");
    M1.put(Character.valueOf(']'), "RBrack");
  }

  public static String stringifyOperator(String operator) {

    String opStr = stringifySymbolicOperator(operator);

    return opStr != null ? opStr : stringifyNonSymbolicOperator(operator);
  }

  private static String stringifySymbolicOperator(String symbolicOp) {
    return SYMBOLIC_OPERATOR_MAPPING.get(symbolicOp);
  }

  private static String stringifyNonSymbolicOperator(String op) {
    return OP_NAME_PREFIX + Joiner.on("").join(Lists.transform(Lists.charactersOf(op), OPERATOR_MAPPER));
  }

  private static class OperatorMapper implements Function<Character, String> {

    @Override
    public String apply(Character c) {
      return OPERATOR_NAME_MAPPING.get(c);
    }

  }

}
