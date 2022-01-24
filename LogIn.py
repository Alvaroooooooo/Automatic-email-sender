import smtplib

def logIN(server):

    accounts = open("ACCOUNT.txt")
    content = accounts.readlines()

    server.login(content[0],content[1])
    