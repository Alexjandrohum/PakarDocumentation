package com.grupopakar.grupopakarappws.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import mx.com.pakar.util.Util;

/**
 * Clase para generar una instancia de firebaseApp, toma archivo de consola
 * Firebase y la url de la DatabaseFirebase.
 *
 * @author antonio.ruiz
 */
public class FirebaseAdmin {

    public static void getFirebaseApp() {
        try {
            FirebaseApp.getInstance();
        } catch (IllegalStateException e) {
            initializeApp();
        }
    }

    public static void initializeApp() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String ruta = Util.getDireccionReal(ec) + Constante.KEY.getValor() + "serviceAccountKey.json";
            FileInputStream serviceAccount = new FileInputStream(ruta);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://grupopakarapp.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException ex) {
            Util.registraError(ex);
        } catch (IOException ex) {
            Util.registraError(ex);
        }
    }

    /**
     * Envia una push notificacion al dispositivo por medio de su token.
     *
     * @param token
     * @param mensaje
     */
    public static boolean enviaNotificacion(String token, String mensaje) {
        String response;
        try {
            Message message = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder()
                            .setTtl(3600 * 1000) // 1 hour in milliseconds
                            .setPriority(AndroidConfig.Priority.HIGH)
                            .setNotification(AndroidNotification.builder()
                                    .setTitle("Grupo Pakar")
                                    .setBody(mensaje)
                                    .setIcon("ic_notification")
                                    .setSound("default")
                                    .setClickAction("com.grupopakar.grupopakarapp.activity.Inicio")
                                    .setColor("#bed600")
                                    .build())
                            .build())
                    .setApnsConfig(ApnsConfig.builder()
                            .putHeader("apns-priority", "10")
                            .setAps(Aps.builder()
                                    .setAlert(ApsAlert.builder()
                                            .setTitle("Grupo Pakar")
                                            .setBody(mensaje)
                                            .build())
                                    .setSound("default")
                                    .setCategory("VER_CHAT")
                                    .setBadge(1)
                                    .build())
                            .build())
                    .setToken(token)
                    .build();
            response = FirebaseMessaging.getInstance().send(message);
            //System.out.println("Successfully sent message: " + response);   
            return true;
        } catch (FirebaseMessagingException ex) {
            // Si el token no existe, genera una excepcion 'Request entity not found'
            Util.registraError(ex);
            return false;
        }
    }
}
