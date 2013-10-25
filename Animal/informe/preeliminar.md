Presentacion Preeliminar Animal
===============================

Android investigation on Malware
=================================

La creciente introduccion del sistema operativo Android en el mercado de telefonos celulares y tabletas electronicas pone al sistema de Google como la plataforma de malware mas interesante en este momento.

Con este motivo, realizaremos una plataforma de Malware orientada a Android.

Nos centraremos en el analisis de las siguientes funcionalidades:

 1. Vectores de infeccion
 2. Evaluacion de privilegios locales
 3. Escala de permisos
 4. Robo de informacion
 5. Zombieficacion
 6. Sidechannels


Actividades realizadas
======================

Generamos una aplicación básica en android que lista las imágenes en carpetas donde se suelen encontrar fotografías (_/Pictures_ y _/DCIM/Camera_). Usamos el permiso **READ_EXTERNAL_STORAGE** para poder acceder a la memoria externa. Para poder enviar las fotografías agregaremos el permiso **INTERNET**.

Herramientas utilizadas
=======================

* Eclipse ADT bundle para desarrollo de android.
* Emulador Genymotion para testear.


Referencias
==============================
A Survey of Android Malware
Xuxian Jiang, Yajin Zhou 

Privilege Escalation Attacks on Android
Lucas Davi, Alexandra Dmitrienko , Ahmad-Reza Sadeghi, Marcel Winandy

Permisos de android
http://developer.android.com/reference/android/Manifest.permission.html	

Hey, You, Get Off of My Clipboard - On How Usability Trumps Security in Android Password Managers
Sascha Fahl, Marian Harbach, Marten Oltrogge, Thomas Muders, and Matthew Smith

Messing with Android’s Permission Model 
Andre Egners, Ulrike Meyer Bjorn Marscholle 
