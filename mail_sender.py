
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import smtplib
import argparse
from pathlib import Path

def adresseess_list(txt):
    accounts = open(txt)
    content = accounts.readlines()

    mailingList = list()
    for i in range(len(content)):
        mailingList.append(content[i])
        
    return mailingList

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--user", type=str, default="cuentadeprueba03052021@gmail.com", help="login user")
    parser.add_argument("--password", type=str, default="alvaroteamo", help="login password")
    parser.add_argument("--subject", type=str, default="Sin asunto")
    parser.add_argument("--addresseess", type=str, default="documents/addressees.txt", help="path to addressees txt")
    parser.add_argument("--message", type=str, default="documents/Main.html", help="path to obtain message in html")

    opt = parser.parse_args()
    
    adresseess = adresseess_list(opt.addresseess)

    message = Path(opt.message).read_text()

    for i in range(len(adresseess)):
        server = smtplib.SMTP('smtp.gmail.com: 587')
        server.starttls()

        msg = MIMEMultipart()

        msg['Subject'] = opt.subject

        msg['From'] = opt.user
        server.login(msg['From'], opt.password)

        msg['To'] = adresseess[i]

        msg.attach(MIMEText(message, 'html'))

        server.sendmail(msg['From'], msg['To'], msg.as_string())

        server.quit()

        print ("successfully sent email to %s:" % (msg['To']))
    




