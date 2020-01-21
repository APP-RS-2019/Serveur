# -*- coding: utf-8 -*-
"""
Created on Thu Apr 11 12:26:46 2019

@author: potetq
"""

import socket
from threading import Thread
import sys

host=''
port=42
listeClient={}
global testFinServeur
testFinServeur=False

class ThreadClient(Thread):
   
    def __init__(self,nom,connexion):
        Thread.__init__(self)
        self.nom = nom
        self.connexion = connexion
        self.testFinClient=False
       
    def run(self):
        global testFinServeur
        while ((testFinServeur == False) and ( self.testFinClient == False )) :
            try:
                self.connexion.send(("Veuillez entrer le destinataire :\n").encode())
                destinataire=self.connexion.recv(1024).decode()
                self.connexion.send(("Veuillez entrer le message a envoyer a "+destinataire +" :\n").encode())
                message_recu=self.connexion.recv(1024).decode()
                if destinataire == "Serveur" :
                    if message_recu == "fin":
                        self.stopClient()
                    elif message_recu == "finServeur":
                        self.stop()
                    print(self.nom+ " : " + message_recu)
                else:
                    try:
                        message_recu=message_recu+"\n"
                        listeClient[destinataire].send(message_recu.encode())
                        print(self.nom+" à "+ destinataire + " : " + message_recu)
                    except:
                        self.connexion.send(("Le destinataire n'existe pas ou n'est pas connecte\n").encode())
                        print("L'envoi a echoue")
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

       
       
           
           


class ThreadEmission(Thread):
   
    def __init__(self):
        Thread.__init__(self)
       
       
    def run(self):
        global testFinServeur
        while testFinServeur==False:
            destinataire = input("Veuillez entrer le destinataire \n")
            message_emis = input("Veuillez entrer le message à envoyer à "+destinataire +" \n")
            message_emis = message_emis.encode()
            if message_emis == b"fin" and destinataire == "Serveur":
                print("Arret du serveur")
                self.stop()
            else:
                try:
                    listeClient[destinataire].send(message_emis)  
                except:
                    print("Le destinaire n'existe pas ou n'est pas connecte")
   
    def stop(self):
        global testFinServeur
        testFinServeur=True
        for key,value in listeClient.items():
            value.close()
        mySocket.close()           
           

# GERER LA CONNEXION DES GENS
# AH OUI LA DECO AUSSI MDR
# GERER FERMETURE PROPRE THREAD/SOCKET ET SERVEUR
           
mySocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    mySocket.bind((host,port))
except socket.error:
    print("La connexion a echoue.")
    sys.exit()
print("Connexion etablie.")
mySocket.listen(5)
te = ThreadEmission()
te.start()

while testFinServeur == False:
    try:
        connexion, adresse = mySocket.accept()
        connexion.send(("Veuillez entrer votre nom :" + '\n').encode()) # Automatique pour les robots "
        nom = connexion.recv(1024).decode()
        print("Client "+ nom + " connecte")
        connexion.send(b"Vous etes connecte\n")
        th = ThreadClient(nom,connexion)
        th.start()  
        listeClient[nom]=connexion
    except:
        pass

            
