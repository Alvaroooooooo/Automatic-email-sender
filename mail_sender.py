
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
import smtplib
import argparse
from utils import *
from pathlib import Path

if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--addresseess", type=str, default="/Users/albaamaarrtin3/Desktop/Automatic-email-sender/documents/addressees.txt", help="path to addressees txt")
    parser.add_argument("--mail_sender", type=str, default="/Users/albaamaarrtin3/Desktop/Automatic-email-sender/documents/senderdetails.txt", help="path to details of mail sender")
    parser.add_argument("--message", type=str, default="/Users/albaamaarrtin3/Desktop/Automatic-email-sender/documents/Main.html", help="path to obtain message in html")
    parser.add_argument("--asunto", type=str, default="Sin asunto")

    opt = parser.parse_args()
    
    adresseess = adresseess_list(opt.addresseess)

    message = Path(opt.message).read_text()

    for i in range(len(adresseess)):
        server = smtplib.SMTP('smtp.gmail.com: 587')
        server.starttls()

        msg = MIMEMultipart()

        msg['Subject'] = opt.asunto

        msg['From'], password = mail_sender_details(opt.mail_sender)
        server.login(msg['From'], password)

        msg['To'] = adresseess[i]

        msg.attach(MIMEText(message, 'html'))

        server.sendmail(msg['From'], msg['To'], msg.as_string())

        server.quit()

        print ("successfully sent email to %s:" % (msg['To']))
    




