#Aurora Hunter Dokumentation

Aurora Hunter ist eine App zur Vorhersage und Benachrichtigungen über Polarlichter. Die App wurde auf Android umgesetzt und wurde auf Android Geräten (Nexus 9, Nexus 5) mit Version 6 getestet. 
Startet man die App wird die Polarlicht-Vorhersage für die nächsten 3 Tage angezeigt. Die Vorhersage wird auf zwei verschiedenen Graphen dargestellt. Der erste Graph zeigt eine Wahrscheinlichkeit in % an. 

Diese Wahrscheinlichkeit wird auf unserem Server anhand von Wetterprognosen und gemesser Sonnensturm-Aktivät für den aktuellen Standort berechnet. Der zweite Graph zeigt den KP-Index für den Tag an. Durch die Actionbar kann man andere Standorte suchen, aufrufen und es werden dann die Graphen wür den Standort angezeigt.
Die Daten über Sonnensturm-Aktivität (KP-Index) werden vom Space Weather Prediction Center (www.swpc.noaa.gov) bezogen.

##In-app Billing
Um die Notifications freizuschalten wird der In-app Billing Service von Google Play verwendet. Um das Google Konto nicht zu belasten, wird im Moment das Produkt "android.test.purchased" erworben. Diese Transaktion ist immer erfolgreich und wird im Google Play Service gespeichert. Um den Testkauf erneut ausführen zu können muss Google Play über Terminal zurück gesetzt werden *“adb shell pm clear com.android.vending”*.

##Notifications
Um auch, wenn man schläft über Polarlicht-Aktivtät informiert zu werden, unterstüzt Aurora Hunter Notifications. Die Notifications werden über Google Cloud Messaging (GCM) versand. Damit ist es möglich ohne einen Hintergrund-Prozess benachrichtigt zu werden. Die App muss nicht laufen.

Durch einen Fingerdruck auf den magentafarbenen “Floating Action Button” öffnet man, nachdem man bezahlt hat, eine Ansicht in der der Schwellwert für den aktuellen Standort eingestellt werden kann. 
Notifications-Testen
Die Notifications über GCM lassen sich einfach testen: Durch das Beispielprogramm “notificationsGcmSenderTest” von Google kann man eine Test Nachricht an das Device senden.

* Schritt 1: Android Monitor in Android Studio öffnen, App starten und nach “Token” suchen
* Schritt 2: Im Terminal in das Projekt-Verzeichnis wechseln.
* Schritt 3: *./gradlew run -Pmsg="Test Nachricht" -Pto="TOKEN_HERE"*

##Canvas-Views 
Für die Darstellung der Wahrscheinlichkeiten und des entsprecehdnen Charts haben wir uns mit der Canvas-API und der Custom-View befasst. Hierbei besteht das Chart ansich aus einer Custom-View sowie auch die Anzeige der [Sonne- und des Mondstands](./app/src/main/java/de/hs_bremen/aurora_hunter/ui/views/sunmooninfo). Am komplexesten ist dabei das Chart, dass die Wahrscheinlichkeiten darstellt: [PredictionGraphView](./app/src/main/java/de/hs_bremen/aurora_hunter/ui/views/PredictionGraphView.java)

##Komponenten
Die Aurora Hunter App besteht aus folgenden Android Komponenten: 

###Activities
* TabsActivity (Einstieg in die App - Vorhersage für die nächsten drei Tage)
* PurchaseNotificationsActivity (In-app Billing für Notifications) 
* NotificationActivity (Einstellung des Schwellwert für die aktuelle Location)

###Notifications
* GcmReceiver (GCM Boilerplate, Teil von Google Service)
* Network-Package (Code der die Notifications beinhaltet )
* NotificationListenerService (Handler für einkommende GCM Messages)
* InstanceIDListenService (Handler für Änderung des GCM-Token)
* RegistrationIntentService (GCM-Token Generation und Übertragung zum Backend)
* LocationService (Übertragung der aktuellen Position für den Schwellwert) 

###Canvas
* PredictionGraphView (Zeigt die Wahrscheinlichkeiten für Aurora mittels eines Cavas an)
* sunmooninfo  (Stellt klassen da, um die Himmelskörper und die entsprechenden Positionen dar zu stellen)

##Package-Struktur
Das Android Studio Project besteht aus drei Packages:
* [app](./app) - Source Code für die Android App
* [prediction-api](./app-commons/prediction-api) - APIs für kpIndex und prediction-Data (autogeneriert durch Swagger)
* [notifications-api](./app-commons/notifications-api) - APIs für notifications (autogeneriert durch Swagger)
* [notificationsGcmSenderTest](./notificationsGcmSenderTest) - GCM Test Code (Übernommen aus den Samples)

##APIs Swagger
Swagger wurde 2010 von der Firma [wordnik](https://www.wordnik.com) entwickelt und wird mitlerweile von Google, IBM und Microsoft weiterentwickelt. Die Technologie dient dazu die API's abstrakt zu beschreiben und zu dokumenteiren, um anschließend Methoden, Parameter, Rückgabewerte, Fehler usw. automatisch zu generieren. 

Die [Prediction-API](./app-commons/prediction-api) und [Notification-API](./app-commons/notifications-api) wurden mittels Swagger beschrieben und somit konnte der entsprechende Android-Client nur noch generiert werden. 

##Third Party Libraries

* [crashlytics](https://fabric.io/kits/android/crashlytics) - Reportingtool für unvorhergesehende Fehler. 
* [play-services](https://developers.google.com/android/guides/overview) - Location API & GCM für Notification 
* [Material-Animations](https://github.com/lgvalle/Material-Animations/) - Animation Libary für Material-Design-Animations 
* [Floating-Search-View](https://github.com/arimorty/floatingsearchview) - UI-Component for the search view
* [Android-Support-Libary](https://developer.android.com/topic/libraries/support-library/index.html) - Libary um auch ältere Geräte zu Unterstützen


