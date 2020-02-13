# -*- coding: utf-8 -*-
"""
Created on Tue Apr 23 11:39:41 2019

@author: beaurezm
"""



import socket
import select
import sys
import json
import time

from threading import Thread
global test
test=True
class Msgrecu(Thread):
    
    def __init__(self):
        Thread.__init__(self)
    def run(self):
        global test

        while test:
            try:
                msg_recu = client.recv(1024)
                msg_recu=msg_recu.decode()
                print(msg_recu)

            except:
                pass

    

#        client.close()
#
#        self.terminate()
    
class Msgenvoyer(Thread):
    
    def __init__(self):
        Thread.__init__(self)
    
    def run(self):
#        msg=b""
#        msg=input("> ")
#        msg=msg.encode()
#        client.send(msg)
        global test
        while test:
            time.sleep(10)
    
            x={"robot":[{"name":"AppliTest","order" : "coucoufromPepper"}]}
    
            y=json.dumps(x)
            try:
                
                y=y.encode()
                client.send(y)
                
            except :
                print("co perdu")
                test=False
#        try:
#            client.close()
#
#
#        except:
#            print("deja deco")
#            test=False

class MsgRE(Thread):
    def __init__(self):
        Thread.__init__(self)
    def run (self):
        global test
        thread_test1=Msgrecu()
        thread_test2=Msgenvoyer()
        thread_test1.start()
        thread_test2.start()
        while test:
            pass
        
        

    
    
    
hote='193.48.125.71'
port=1933
client=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
try:
    client.connect((hote,port))
    print("connecte")
    time.sleep(1)
    client.send("Pepper".encode())
    thread_1=MsgRE()


    thread_1.start()



#    thread_1.join()
    thread_1.join()
    client.close()
    print("deco reussi")
except:
    print ("Error")


#
#msg_a_envoyer = b""
#while msg_a_envoyer != b"fin":
#    msg_a_envoyer = input("> ")
#    # Peut planter si vous tapez des caractères spéciaux
#    msg_a_envoyer = msg_a_envoyer.encode()
#    # On envoie le message
#    client.send(msg_a_envoyer)
#    msg_recu = client.recv(1024)
#    print(msg_recu.decode()) # Là encore, peut planter s'il y a des accents
#
#print("Fermeture de la connexion")
#client.close()
