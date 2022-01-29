package model;

import java.sql.SQLException;

public class AutomaticEmailSender {

    public void exportEmailDetailsToTxt(String subject, String message) {
        ExportEmailDetailsToTxt e =new ExportEmailDetailsToTxt(subject, message);
        e.exporter();
    }

    public void filterEmailsFromDatabaseToTxt(String filterAge, String filterCountry) {
        ExportFromDatabaseToTxt e = new ExportFromDatabaseToTxt(filterAge, filterCountry);
        try {
            e.exporter();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void pythonScriptLauncher() {
        PythonScriptLauncher p = new PythonScriptLauncher();
        p.launcher();
    }
}
