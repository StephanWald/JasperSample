package jaspersample;

import com.basiscomponents.bc.SqlQueryBC;
import com.basiscomponents.bc.SqlTableBC;
import com.basiscomponents.db.DataRow;
import com.basiscomponents.db.ResultSet;

import java.math.BigDecimal;
import java.util.Iterator;

public class CDStoreBC extends SqlTableBC {
  public CDStoreBC() throws ClassNotFoundException {
    // this needs to be parametrized in a real world project!
    super("com.basis.jdbc.BasisDriver","jdbc:basis:localhost?database=CDStore","admin","admin123");
    setTable("CDInventory");
  }

  @Override
  public ResultSet retrieve() throws Exception {
    ResultSet rs = super.retrieve();
    Iterator<DataRow> it = rs.iterator();
    while (it.hasNext()) {
      DataRow rec = it.next();
      BigDecimal onHand = rec.getField("ONHAND").getBigDecimal();
      BigDecimal cost = rec.getField("COST").getBigDecimal();
      rec.setFieldValue("STOCKVALUE",cost.multiply(onHand));
    }
    return rs;
  }
  }

