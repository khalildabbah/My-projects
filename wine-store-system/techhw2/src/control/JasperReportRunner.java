
package control;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import utils.AccessDatabaseConnection;

public class JasperReportRunner {
    public static void initiate(String reportName) {
        try {
            // Get connection to Access database
            Connection conn = AccessDatabaseConnection.getConnection();

            if (conn == null) {
                throw new RuntimeException("Database connection is null. Please check your setup.");
            }

            // Load the compiled .jasper file as a resource
            InputStream jasperStream = JasperReportRunner.class.getClassLoader().getResourceAsStream("reports/" + reportName);
            if (jasperStream == null) {
                JOptionPane.showMessageDialog(
                    null,
                    "CANNOT FIND JASPER FILE",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Set report parameters
            Map<String, Object> parameters = new HashMap<>();

            // Fill the report with data and parameters
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parameters, conn);

            // Determine the JAR file's directory
            String jarDir;
            try {
                jarDir = new File(JasperReportRunner.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent();
            } catch (URISyntaxException e) {
                throw new RuntimeException("Unable to determine JAR file location.", e);
            }

            // Set the output PDF path relative to the JAR's location
            String outputPdf = jarDir + File.separator + "report.pdf";

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, outputPdf);

            System.out.println("Report generated successfully: " + outputPdf);

            // View the report in a viewer
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
