package automaticemailsenderappx;

import model.AutomaticEmailSender;
import gui.AutomaticEmailSenderForm;

public class AutomaticEmailSenderAppX {
    public static void main(String[] args) {
        AutomaticEmailSender model = new AutomaticEmailSender();
        AutomaticEmailSenderForm gui = new AutomaticEmailSenderForm(model);
        gui.setVisible(true);
    }
}