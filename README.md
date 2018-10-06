# My subsonic WebService

# Intro
Pour le moment c'est en WIP. du coup il y a des choses qui changeront.

Ce webservice permet d'indexé des fichiers .mp3/ogg/wav [et les .jpg/.png] pour les images de disques.


## Pre-requis.
Comme c'est en  WIP pour le moment il faut compiler soit même les sources.
Du coup il faut installer maven.


### Maven
http://mirror.ibcp.fr/pub/apache/maven/maven-3/3.5.4/binaries/apache-maven-3.5.4-bin.zip

Le plus  simple et de télécharger un IDE java et le compiler avec.
Ici encore plus simple, voici un lien avec le JDK + l'IDE [netbean 8.2 + jdk 8]
https://www.oracle.com/technetwork/java/javase/downloads/jdk-netbeans-jsp-3413139-esa.html

Ouvrir l'IDE puis ouvrir le projet puis dans la barre en haut, au lieu de <default config> choisir "prod" puis "clean and build" [dans netbeans].

et dans le répertoire target [fraichement crée] il faudra utiliser le jar "MySubsonicWS-1.0-SNAPSHOT-scanInitial"

## Getting started

###1. Copie des fichiers
la 1ere chose a faire et de COPIER/COLLER les fichiers suivant => config.properties et hibernate-vanillia.cfg.xml
et de renommer ces copies respectivement : config-dev.properties et hibernate-dev.cfg.xml

de remplir les fichiers comme  cela:

####1.1 : bien remplir les fichiers comme il faut.
#####config-dev.properties
ws.port=9998
ws.domain=localhost
folder.scan=C:/Users/Michael/Music

#####hibernate-dev
il faut bien adapter ces cles a notre convenance:
    - <property name="connection.driver_class">com.mysql.jdbc.Driver</property>
    - <property name="connection.url">jdbc:mysql://localhost/mysubsonic?useSSL=false</property>
    - <property name="connection.username"></property>
    - <property name="connection.password"></property>
    - <property name="dialect">org.hibernate.dialect.MariaDBDialect</property>

    et faire tres attention a cette ligne :
 - <property name="hbm2ddl.auto">create</property>
mais j'y reviendrai

ensuite quand on a rempli ces champs, il suffit de lancer la commande:
###2. lancement du Scan
java -jar MySubsonicWS-1.0-SNAPSHOT-scanInitial  folder_ou_se_trouve_la_musique

de le laisser faire. il quitte tout seul au bout d'un moment.
ca peut prendre du temps. ne pas s'inquiéter.

####2.1 Commenter la ligne hbm2ddl
une fois le scan fini, il faut revenir dans le fichier de conf,[une dernière fois,]
et commenter la ligne :
 - <property name="hbm2ddl.auto">create</property>

Sinon cela aura pour effet de détruire la BDD pour la reconstruire.

###3. Lancement du web-service

java -jar MySubsonicWS-1.0-SNAPSHOT-main.jar
