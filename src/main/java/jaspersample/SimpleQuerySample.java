package jaspersample;


import com.basis.startup.type.BBjException;
import com.basiscomponents.bc.SqlQueryBC;
import com.basiscomponents.db.ResultSet;
import com.basiscomponents.db.util.JRDataSourceAdapter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import static jaspersample.JasperCreator.doJasper;

public class SimpleQuerySample {

  public static void main(String[] args) throws ClassNotFoundException, BBjException, JRException, IOException {
    SqlQueryBC qbc = new SqlQueryBC("com.basis.jdbc.BasisDriver","jdbc:basis:localhost?database=CDStore","admin","admin123");
    ResultSet rs = qbc.retrieve("SELECT * FROM CDINVENTORY");
    rs.print();

    doJasper(rs);
  }


}

