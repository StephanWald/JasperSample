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

public class SimpleQuerySample {

  public static void main(String[] args) throws ClassNotFoundException, BBjException, JRException, IOException {
    SqlQueryBC qbc = new SqlQueryBC("com.basis.jdbc.BasisDriver","jdbc:basis:localhost?database=CDStore","admin","admin123");
    ResultSet rs = qbc.retrieve("SELECT * FROM CDINVENTORY");
    rs.print();


    JRDataSourceAdapter jrdata = new JRDataSourceAdapter(rs);

    @SuppressWarnings("rawtypes") HashMap hashMap = new HashMap();
    hashMap.put("SOMEPARAM", Integer.toString(1234));
    JasperPrint jasperPrint = null;
    String path = "/reports/cdstore.jasper";

    try (InputStream jasperResourceStream = SimpleQuerySample.class.getResourceAsStream(path);) {
      jasperPrint = JasperFillManager.fillReport(jasperResourceStream, hashMap, jrdata);
    } catch (IOException e) {
      e.printStackTrace();
      throw e;
    }

    File pdffile = File.createTempFile("cdstore_", ".pdf");
    pdffile.deleteOnExit();

    if (!pdffile.exists()) {
      pdffile.createNewFile();
    }

    try (FileOutputStream fileOutputStream = new FileOutputStream(pdffile)) {
      JasperExportManager.exportReportToPdfStream(jasperPrint, fileOutputStream);
      fileOutputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
    java.awt.Desktop.getDesktop().open(pdffile);
  }
}

