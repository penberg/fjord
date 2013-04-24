package fjord.ast.typedefn;

import java.util.Collections;
import java.util.List;

import fjord.ast.ModuleElem;
import fjord.ast.Node;
import fjord.ast.NodeVisitor;

public class RecordTypeDefn implements ModuleElem {

  private final TypeName typeName;
  
  private final List<RecordField> recordFields;
  
  private final List<TypeDefnElement> typeDefnElements;
  
  public RecordTypeDefn(TypeName typeName, List<RecordField> recordField, List<TypeDefnElement> typeDefnElements) {
    this.typeName = typeName;
    this.recordFields = recordField;
    this.typeDefnElements = typeDefnElements != null ? typeDefnElements : Collections.<TypeDefnElement>emptyList();
  }

  public TypeName getTypeName() {
    return typeName;
  }

  public List<RecordField> getRecordFields() {
    return recordFields;
  }

  public List<TypeDefnElement> getTypeDefnElements() {
    return typeDefnElements;
  }

  @Override
  public void accept(NodeVisitor visitor) {

  }
  
}
