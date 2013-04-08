package fjord.ast.expr;

import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class ConstantExpression implements Expression {

	private final Node constn; 
	
	public ConstantExpression(Node constn) {
		this.constn = constn;
	}
	
	@Override
	public void accept(NodeVisitor visitor) {
		visitor.visit(this);
	}

	public Node getConst() {
		return constn;
	}

}
