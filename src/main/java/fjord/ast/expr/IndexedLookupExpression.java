package fjord.ast.expr;

import fjord.ast.NodeVisitor;

public class IndexedLookupExpression implements Expression {

	@Override
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
	}

}
