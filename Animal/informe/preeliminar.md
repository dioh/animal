Presentación Preeliminar Animal
===============================

Android investigation on Malware
=================================

La creciente introducción del sistema operativo Android en el mercado de telefonos celulares y tabletas electrónicas pone al sistema de Google como la plataforma de malware mas interesante en este momento.

Con este motivo, realizaremos una plataforma de Malware orientada a Android.

Nos centraremos en el análisis de las siguientes funcionalidades:

 1. Vectores de infección
 2. Evaluación de privilegios locales
 3. Escala de permisos
 4. Robo de información
 5. Zombieficación
 6. Sidechannels


Actividades realizadas
======================

Generamos una aplicación básica en android que lista las imágenes en carpetas donde se suelen encontrar fotografías (_/Pictures_ y _/DCIM/Camera_). Usamos el permiso **READ_EXTERNAL_STORAGE** para poder acceder a la memoria externa. Para poder enviar las fotografías agregaremos el permiso **INTERNET**.

Herramientas utilizadas
=======================

* Eclipse ADT bundle para desarrollo de android.
* Emulador Genymotion para testear.

Actividades a realizar
=======================
Para cada categoría propuesta, implementaremos pruebas de concepto que nos permitan evaluar la factibilidad de las técnicas.

Vectores de Infección
----------------------
Instalación Manual en una primera etapa.

Evaluación de privilegios locales
-------------------------------------------- 
Implementar una funcionalidad que nos permita obtener un mapeo de las aplicaciones, los permisos y las capabilities de cada una.

Escala de permisos
------------------ 
Implementar un subconjunto de exploits locales y utilizar la escala de permisos transitiva.

Robo de información
-------------------
 1. Obtener contactos (y toda su información) 
 1. Obtener el registro de llamadas 
 1. Obtener los mensajes 
 1. Ubicación del GPS/RED 
 1. Tomar una foto con la cámara 
 1. Capturar el audio con el micrófono 
 1. Enviar mensajes de texto 
 1. Abrir una URL en el browser 
 1. Hacer que el teléfono vibre
 1. Descargar un binario dinámicamente y ejecutarlo

Zombificación
-------------- 
Implementar un pivoteo de ataques utilizando permisos de network para proxiar el ataque. A-la-Hookme


SideChannels
--------------- 
Utilizar un blog / servicio REST para sacar la información recavada.


Referencias
==============================
 1. A Survey of Android Malware - Xuxian Jiang, Yajin Zhou 
 1. Privilege Escalation Attacks on Android - Lucas Davi, Alexandra Dmitrienko , Ahmad-Reza Sadeghi, Marcel Winandy 
 1. http://code.google.com/p/hookme "Hook me" 
 1. http://code.google.com/p/hookme/ "Permisos de android"
 1. https://github.com/DesignativeDave/androrat "Androrat" 
 1. Hey, You, Get Off of My Clipboard - On How Usability Trumps Security in Android Password Managers - Sascha Fahl, Marian Harbach, Marten Oltrogge, Thomas Muders, and Matthew Smith
 1. Messing with Android’s Permission Model - Andre Egners, Ulrike Meyer Bjorn Marscholle 
