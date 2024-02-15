package jaspersample;

import com.basiscomponents.bc.SqlQueryBC;
import com.basiscomponents.db.ResultSet;

public class BCQuerySample {
  public static void main(String[] args) throws Exception {
    CDStoreBC bc = new CDStoreBC();
    ResultSet rs = bc.retrieve();
    rs.print();

    JasperCreator.doJasper(rs);
  }
}
