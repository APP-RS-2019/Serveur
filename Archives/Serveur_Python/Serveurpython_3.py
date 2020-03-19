# -*- coding: utf-8 -*-
"""
Created on Thu Apr 11 12:26:46 2019

@author: potetq
"""

import socket
from threading import Thread
import sys
import json


host=''
port=1933
listeClient={}
global testFinServeur
testFinServeur=False

class ThreadClient(Thread):
   
    def __init__(self,connexion):
        Thread.__init__(self)
        self.nom = "noname"
        self.connexion = connexion
        self.testFinClient=False
       
    def run(self):
        global testFinServeur
        global listeClient
        
        print("Connexion etablie.")

        self.nom = connexion.recv(1024).decode()
        print("Client "+ self.nom + " connecte")
        listeClient[self.nom]=connexion
        
        while ((testFinServeur == False) and ( self.testFinClient == False )) :
            try:
#                self.connexion.sendall(("Veuillez entrer le destinataire :\n").encode())
#                destinataire=self.connexion.recv(1024).decode()
#                print("Destinateire: " + destinataire)
#                self.connexion.sendall(("Veuillez entrer le message a envoyer a "+destinataire +" :\n").encode())
#                message_recu=self.connexion.recv(1024).decode()
#                print("Message: " + message_recu)
                message_recu=self.connexion.recv(1024).decode()
                x=json.loads(message_recu)
                print(x)
                for i in range (len(x["robot"])):
                    y=x["robot"][i]["name"]
                    z=x["robot"][i]["order"]
                    listeClient[y].sendall((z+"\n").encode())
                    
            except:
                  pass
           
    def stop(self):
        global testFinServeur
        testFinServeur = True
        for key,value in listeClient.items():
            value.close()
        mySocket.close()
       
    def stopClient(self):
        self.testFinClient=True

       
       
           
   
           

# GERER LA CONNEXION DES GENS
# AH OUI LA DECO AUSSI MDR
# GERER FERMETURE PROPRE THREAD/SOCKET ET SERVEUR
           
mySocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    mySocket.bind((host,port))
except socket.error:
    print("La connexion a echoue.")
    sys.exit()
print("Connexion attente.")
mySocket.listen(5)
#te = ThreadEmission()
#te.start()

while testFinServeur == False:
    try:
        connexion, adresse = mySocket.accept()
        th = ThreadClient(connexion)
        th.start()  
        
    except:
        pass
