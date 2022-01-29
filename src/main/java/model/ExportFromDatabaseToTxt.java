package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ExportFromDatabaseToTxt {

    private final String path;
    private String nameTable;
    private String emailsColumn;
    private String ageColumn;
    private String countryColumn;
    private String filterAge;
    private String filterCountry;

    public ExportFromDatabaseToTxt(String path, String nameTable, String emailsColumn, String ageColumn, String countryColumn, String filterAge, String filterCountry) {
        this.path = path;
        this.nameTable = nameTable;
        this.emailsColumn = emailsColumn;
        this.ageColumn = ageColumn;
        this.countryColumn = countryColumn;
        this.filterAge = filterAge;
        this.filterCountry = filterCountry;
    }

    public void exporter() throws SQLException {

        try {

            // crear conección con la base de datos
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + this.path);
            // crear archivo txt para añadir los correos
            File file = new File(System.getProperty("user.dir") + "/documents/addressees.txt");
            PrintWriter pw = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();

            // seleccionar la tabla y sus columnas
            PreparedStatement ps = connection.prepareStatement("SELECT " + this.emailsColumn + ", " + this.ageColumn + ", " + this.countryColumn + " FROM " + this.nameTable);
            ResultSet rs = ps.executeQuery();

            // si edad y pais son nulos
            if (this.filterAge == null && this.filterCountry == null) {
                while (rs.next()) {
                    sb.append(rs.getString(this.emailsColumn)).append("\n");
                }
            } else {
                // si edad es nulo
                if (this.filterAge == null && this.filterCountry != null) {
                    while (rs.next()) {
                        if (this.filterCountry.equalsIgnoreCase(rs.getString(this.countryColumn))) {
                            sb.append(rs.getString(this.emailsColumn)).append("\n");
                        }
                    }
                } else {

                    int ageMin = Integer.parseInt(filterAge.charAt(0) + "" + filterAge.charAt(1));
                    int ageMax = Integer.parseInt(filterAge.charAt(3) + "" + filterAge.charAt(4));

                    // si pais es nulo
                    if (this.filterAge != null && this.filterCountry == null) {
                        while (rs.next()) {
                            if (ageMin <= rs.getInt(this.ageColumn) && rs.getInt(this.ageColumn) <= ageMax) {
                                sb.append(rs.getString(this.emailsColumn)).append("\n");
                            }
                        }
                    } else { // si ninguno es nulo
                        while (rs.next()) {
                             if (ageMin <= rs.getInt(this.ageColumn) && rs.getInt(this.ageColumn) <= ageMax && this.filterCountry.equalsIgnoreCase(rs.getString(this.countryColumn))) {
                                sb.append(rs.getString(this.emailsColumn)).append("\n");
                            }
                        }
                    }
                }
            }

            pw.write(sb.toString());
            pw.close();

        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

}