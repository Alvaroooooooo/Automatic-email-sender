package model;

import java.sql.SQLException;

public class AutomaticEmailSender {

    public void filterEmailsFromDatabaseToTxt(String path, String nameTable, String emailsColumn, String ageColumn, String countryColumn, String filterAge, String filterCountry) {
        ExportFromDatabaseToTxt e = new ExportFromDatabaseToTxt(path, nameTable, emailsColumn, ageColumn, countryColumn, filterAge, filterCountry);
        try {
            e.exporter();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void pythonScriptLauncher(String user, String password, String subject, String message) {
        PythonScriptLauncher p =new PythonScriptLauncher(user, password, subject, message);
        p.launcher();
    }
}
