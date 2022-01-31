
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import smtplib
from pathlib import Path

def txtReader(txt):
    accounts = open(txt)
    content = accounts.readlines()

    mailingList = list()
    for i in range(len(content)):
        mailingList.append(content[i])
        
    return mailingList

if __name__ == "__main__":

    user, password = "cuentadeprueba03052021@gmail.com", "Prueba12345"
    
    subject, message = txtReader("documents/emailDetails.txt")
    adresseess = txtReader("documents/addressees.txt")

    message = Path(message).read_text()

    for i in range(len(adresseess)):
        server = smtplib.SMTP('smtp.gmail.com: 587')
        server.starttls()

        msg = MIMEMultipart()

        msg['From'], msg['Subject'], msg['To'] = user, subject, adresseess[i]

        server.login(msg['From'], password)
        msg.attach(MIMEText(message, 'html'))
        server.sendmail(msg['From'], msg['To'], msg.as_string())
        server.quit()    




