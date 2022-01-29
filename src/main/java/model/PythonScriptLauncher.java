package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PythonScriptLauncher {

    public String user;
    public String password;
    public String subject;
    public String message;

    public PythonScriptLauncher(String user, String password, String subject, String message) {
        this.user = user;
        this.password = password;
        this.subject = subject;
        this.message = message;
    }

    public void launcher() {

        ProcessBuilder pb = new ProcessBuilder("python3", System.getProperty("user.dir") + "/" + "mail_sender.py");

        pb.inheritIO();
        Process process = null;

        try {
            process = pb.start();
            process.waitFor();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        assert process != null;
        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
        
        String line;
        try {
            if ((line = bfr.readLine()) != null) System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
