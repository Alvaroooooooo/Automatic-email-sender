import smtplib

from Message import *
from LogIn import *

content = message()
subject = subject()

message = "Subject:{}\n\n{}".format(subject, content)

server = smtplib.SMTP("smtp.gmail.com", 574)
server.starttls()

logIN(server)

