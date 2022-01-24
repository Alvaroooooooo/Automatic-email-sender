
def adresseess_list(txt):
    accounts = open(txt)
    content = accounts.readlines()

    mailingList = list()
    for i in range(len(content)):
        mailingList.append(content[i])
        
    return mailingList

def mail_sender_details(txt):

    accounts = open(txt)
    content = accounts.readlines()

    return content[0], content[1]
    

def getMessage():
    return "Mensaje automático"

def getSubject():
    return "Asunto"

def message():
    content, subject = getMessage(), getSubject()
    email = "Subject:{}\n\n{}".format(subject, content)
    return email