package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ExportEmailDetailsToTxt {

    public String subject;
    public String message;

    public ExportEmailDetailsToTxt(String subject, String message) {
        this.subject = subject;
        this.message = message;
    }

    public void exporter() {
        try {

            File file = new File(System.getProperty("user.dir") + "/documents/emailDetails.txt");
            PrintWriter pw = new PrintWriter(file);
            StringBuilder sb = new StringBuilder();

            //añadir
            sb.append(this.subject).append("\n");
            sb.append(this.message);

            pw.write(sb.toString());
            pw.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
