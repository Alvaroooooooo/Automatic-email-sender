package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ExportFromDatabaseToTxt {

    private String filterAge;
    private String filterCountry;

    public ExportFromDatabaseToTxt(String filterAge, String filterCountry) {
        this.filterAge = filterAge;
        this.filterCountry = filterCountry;
    }

    public void exporter() throws SQLException {

        try {

            // crear conección con la base de datos
            Connection connection = DriverManager.getConnection("jdbc:sqlite:" + System.getProperty("user.dir") + "/hotel.db");

            // crear archivo txt para añadir los correos
            File file = new File(System.getProperty("user.dir") + "/documents/addressees.txt");
            PrintWriter pw = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();

            // seleccionar la tabla y sus columnas
            PreparedStatement ps = connection.prepareStatement("SELECT emails,age,country FROM hotel");
            ResultSet rs = ps.executeQuery();

            // si edad y pais son nulos
            if (this.filterAge == null && this.filterCountry == null) {
                while (rs.next()) {
                    sb.append(rs.getString("emails")).append("\n");
                }
            } else {
                // si edad es nulo
                if (this.filterAge == null && this.filterCountry != null) {
                    while (rs.next()) {
                        if (this.filterCountry.equalsIgnoreCase(rs.getString("country"))) {
                            sb.append(rs.getString("emails")).append("\n");
                        }
                    }
                } else {

                    int ageMin = Integer.parseInt(filterAge.charAt(0) + "" + filterAge.charAt(1));
                    int ageMax = Integer.parseInt(filterAge.charAt(3) + "" + filterAge.charAt(4));

                    // si pais es nulo
                    if (this.filterAge != null && this.filterCountry == null) {
                        while (rs.next()) {
                            if (ageMin <= rs.getInt("age") && rs.getInt("age") <= ageMax) {
                                sb.append(rs.getString("emails")).append("\n");
                            }
                        }
                    } else { // si ninguno es nulo
                        while (rs.next()) {
                             if (ageMin <= rs.getInt("age") && rs.getInt("age") <= ageMax && this.filterCountry.equalsIgnoreCase(rs.getString("country"))) {
                                sb.append(rs.getString("emails")).append("\n");
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