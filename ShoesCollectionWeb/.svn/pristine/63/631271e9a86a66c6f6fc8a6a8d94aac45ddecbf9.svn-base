/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scpakar.scpakarweb.util;

import com.scpakar.scpakarweb.dao.PropiedadDAO;
import com.scpakar.scpakarweb.dto.Propiedad;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.regex.Pattern;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.faces.context.ExternalContext;
import javax.imageio.ImageIO;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.swing.ImageIcon;
import org.apache.commons.lang3.RandomStringUtils;

/**
 *
 * @author pablo.martinez
 */
public class UtilSCW {

    //Criptografia
    private static final String KEY = "0123456789abcdef0123456789abcdef";
    private static final String ALGORITMO = "AES";
    private static final String CODIFICACION = "UTF-8";
    //Formatos
    private static final DecimalFormat decimalFormat = new DecimalFormat("$ ,##0.00");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss");
    private static final SimpleDateFormat dateTimeSimpleFormat = new SimpleDateFormat("ddMMyyyykkmmss");
    //Validacion
    private static final Pattern PATRON = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    public static final Pattern PATRON_PAQUETERIA = Pattern.compile("[a-zA-z0-9#-\\. ]+");
    //Correo
    private static int CORREO_ACTUAL = 1;
    private static final int CORREOS = 4;
    private static final String SERVIDOR_SMTP = "smtpout.secureserver.net";
    private static final String PUERTO_SMTP_25 = "25";
    private static final String PUERTO_SMTP_465 = "465";
    private static final String CORREO = "contacto0[num]@scpakarmx.com";
    private static String CORREO_ENVIO = "contacto01@scpakarmx.com";
    private static final String ALIAS = "SC Pakar";
    private static final String PASSWORD = "86e59bdec30f7bf4eca068a25044da7d";
    private static final String PASSWORD_ALTERNATIVO = "86e59bdec30f7bf4eca068a25044da7d";
    private static final String MIME_TYPE = "text/html";
    private static final Properties PROPIEDADES_CORREO = new Properties();
    public static final String RUTA_REPORTE = "resources" + File.separator + "reporte" + File.separator;
    public static final String RUTA_EXT = "resources" + File.separator + "ext" + File.separator;
    /*socio afiliate default*/
    public static final String IMAGEN = "foto.png";
    private static final String CARACTERES_PASSWORD = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static Properties getPropiedades25() {
        CORREO_ENVIO = CORREO.replace("[num]", getCorreoActual());
        PROPIEDADES_CORREO.setProperty("mail.smtp.user", CORREO_ENVIO);

        PROPIEDADES_CORREO.setProperty("mail.smtp.host", SERVIDOR_SMTP);
        PROPIEDADES_CORREO.setProperty("mail.smtp.port", PUERTO_SMTP_25);
        //PROPIEDADES_CORREO.setProperty("mail.smtp.user", CORREO.replace("[num]", getCorreoActual()));
        PROPIEDADES_CORREO.setProperty("mail.smtp.starttls.enable", "true");
        PROPIEDADES_CORREO.setProperty("mail.smtp.auth", "true");
        return PROPIEDADES_CORREO;
    }

    public static Properties getPropiedades465() {
        CORREO_ENVIO = CORREO.replace("[num]", getCorreoActual());
        PROPIEDADES_CORREO.setProperty("mail.smtp.user", CORREO_ENVIO);

        PROPIEDADES_CORREO.setProperty("mail.smtp.host", SERVIDOR_SMTP);
        PROPIEDADES_CORREO.setProperty("mail.smtp.port", PUERTO_SMTP_465);
        //PROPIEDADES_CORREO.setProperty("mail.smtp.user", CORREO.replace("[num]", getCorreoActual()));
        PROPIEDADES_CORREO.setProperty("mail.smtp.starttls.enable", "true");
        PROPIEDADES_CORREO.setProperty("mail.smtp.auth", "true");
        PROPIEDADES_CORREO.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        PROPIEDADES_CORREO.put("mail.smtp.ssl.enable", "true");
        return PROPIEDADES_CORREO;
    }

    private static String getCorreoActual() {
        return (((++CORREO_ACTUAL) % CORREOS) + 1) + "";
    }

    private static String HexToString(byte[] arregloEncriptado) {
        String textoEncriptado = "";
        for (int i = 0; i < arregloEncriptado.length; i++) {
            int aux = arregloEncriptado[i] & 0xff;
            if (aux < 16) {
                textoEncriptado = textoEncriptado.concat("0");
            }
            textoEncriptado = textoEncriptado.concat(Integer.toHexString(aux));
        }
        return textoEncriptado;
    }

    private static byte[] StringToHex(String encriptado) {
        byte[] enBytes = new byte[encriptado.length() / 2];
        for (int i = 0; i < enBytes.length; i++) {
            int index = i * 2;
            String aux = encriptado.substring(index, index + 2);
            int v = Integer.parseInt(aux, 16);
            enBytes[i] = (byte) v;
        }
        return enBytes;
    }

    public static String encripta(String cadena) {
        String encriptado = cadena;
        try {
            byte[] hex = StringToHex(KEY);
            SecretKeySpec secretKeySpec = new SecretKeySpec(hex, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encrypted = cipher.doFinal(cadena.getBytes(CODIFICACION));
            encriptado = HexToString(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } finally {
            return encriptado;
        }
    }

    public static String desencripta(String encriptado) {
        String desencriptado = encriptado;
        try {
            byte[] raw = StringToHex(KEY);
            SecretKeySpec secretKeySpec = new SecretKeySpec(raw, ALGORITMO);
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] original = cipher.doFinal(StringToHex(encriptado));
            desencriptado = new String(original);
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } finally {
            return desencriptado;
        }
    }

    public static String formatoFechaHora() {
        return dateTimeFormat.format(new Date());
    }

    public static String formatoSimpleFechaHora() {
        return dateTimeSimpleFormat.format(new Date());
    }

    public static String formatoFecha() {
        return dateFormat.format(new Date());
    }

    public static String formatoFecha(Date fecha) {
        return dateFormat.format(fecha);
    }

    public static boolean isCorreoValido(String correo) {
        return PATRON.matcher(correo).matches()
                && isServidorCorreo(correo.substring(correo.indexOf("@") + 1));
    }

    public static boolean isServidorCorreo(String servidor) {
        boolean ok = false;
        try {
            @SuppressWarnings("UseOfObsoleteCollectionType")
            Hashtable<String, String> env = new Hashtable<>();
            env.put("java.naming.factory.initial", "com.sun.jndi.dns.DnsContextFactory");
            DirContext ictx = new InitialDirContext(env);
            Attributes attrs = ictx.getAttributes(servidor, new String[]{"MX"});
            Attribute attr = attrs.get("MX");
            if (attr != null) {
                ok = attr.size() > 0;
            }
        } catch (NamingException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } catch (Exception ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } finally {
            return ok;
        }
    }

    public static String formatoMoneda(double numero) {
        return decimalFormat.format(numero);
    }

    public static String getDireccionReal(ExternalContext ec) {
        return ((ServletContext) ec.getContext()).getRealPath("/");
    }

    @SuppressWarnings("UnusedAssignment")
    public static boolean copiaArchivo(File origen, File destino) {
        boolean ok = false;
        long paso = 30000000;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(origen);
            fos = new FileOutputStream(destino);
            FileChannel canalFuente = fis.getChannel();
            FileChannel canalDestino = fos.getChannel();
            long ciclos = canalFuente.size() / paso;
            long resto = canalFuente.size() % paso;

            for (int i = 0; i < ciclos; i++) {
                canalFuente.transferTo(i * paso, paso, canalDestino);
            }
            canalFuente.transferTo(ciclos * paso, resto, canalDestino);

            fis.close();
            fos.close();
            ok = true;
        } catch (FileNotFoundException ex) {
            mx.com.pakar.util.Util.registraError(ex);
            ok = false;
        } catch (IOException ex) {
            mx.com.pakar.util.Util.registraError(ex);
            ok = false;
        } catch (Exception ex) {
            mx.com.pakar.util.Util.registraError(ex);
            ok = false;
        } finally {
            return ok;
        }
    }

    public static String obtenerIP(ExternalContext ec) {
        HttpServletRequest hr = (HttpServletRequest) ec.getRequest();
        return hr.getRemoteHost();
    }

    public static boolean enviaCorreo(String para, String asunto, String contenido, String responder) {
        boolean ok = false;
        try {
            Session sesion = Session.getInstance(getPropiedades25(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO_ENVIO, desencripta(PASSWORD));
                }
            });
            Message mensaje = new MimeMessage(sesion);
            if (responder != null) {
                mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            }
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO, ALIAS));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            mensaje.setSubject(asunto);
            mensaje.setContent(contenido, MIME_TYPE);
            Transport.send(mensaje);
            ok = true;
        } catch (AddressException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } catch (SendFailedException ex) {
            System.err.println(Arrays.toString(ex.getInvalidAddresses()));
            mx.com.pakar.util.Util.registraError(ex);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } finally {
            return ok ? ok : enviaCorreoAlternativo(para, asunto, contenido, responder);
        }
    }

//    public static void main(String a[]){
//        System.out.println(UtilSCW.encripta("Pakar2015"));
//        boolean ok = false;
//        try{
//            Properties props = new Properties();
//            //props.setProperty("mail.smtp.auth.mechanisms", "DIGEST-MD5");
//            props.setProperty("mail.smtp.host", "smtpout.secureserver.net");
//            props.setProperty("mail.smtp.port", "465");
//            props.setProperty("mail.smtp.user", "contacto01@scpakarmx.com");
//            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//            props.setProperty("mail.smtp.starttls.enable", "true");
//            props.put("mail.smtp.ssl.enable", "true");
//            props.put("mail.debug", "true");
//            props.setProperty("mail.smtp.auth", "true");
//            Autenticador authenticator = new Autenticador("contacto01@scpakarmx.com", "Pakar2015");
//            Session sesion = Session.getInstance(props, authenticator
////                    new Authenticator() {
////                        @Override
////                        protected PasswordAuthentication getPasswordAuthentication() {
////                            //return new PasswordAuthentication("pakar.com\\contactos", "D3s9rr0ll0");
////                            PasswordAuthentication pa = new PasswordAuthentication("pakar.onmicrosoft.com\\contactos", "D3s9rr0ll0");
////                            return pa;
////                        }
////                    }
//            );
//            Message mensaje = new MimeMessage(sesion);
//            mensaje.setFrom(new InternetAddress("contacto01@scpakarmx.com", "Servicios Contacto"));
//            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress("bein@mailinator.com"));
//            mensaje.setSubject("Prueba");
//            mensaje.setContent("Hola que tal", "text/plain");
//            System.out.println("AQUI");
//            Transport.send(mensaje);
//            ok = true;
//        }catch(AddressException ex) {
//            System.err.println("Error1: " + ex.getMessage());
//        }catch(SendFailedException ex) {
//            System.out.println(ex.getInvalidAddresses());
//            System.err.println("Error2: " + ex.getMessage());
//        }catch(MessagingException ex) {
//            //ex.printStackTrace();
//            System.err.println("Error3: " + ex.getMessage());
//        }catch(Exception ex) {
//            System.err.println("Error4: " + ex.getMessage());
//        }catch(Error ex) {
//            System.err.println("Error5: " + ex.getMessage());
//        }
//        System.out.println(ok);
//    }
    public static class Autenticador extends javax.mail.Authenticator {

        private final PasswordAuthentication authentication;

        public Autenticador(String username, String password) {
            authentication = new PasswordAuthentication(username, password);
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return authentication;
        }
    }

    public static boolean enviaCorreoAlternativo(String para, String asunto, String contenido, String responder) {
        boolean ok = false;
        String error = "";
        try {
            Session sesion = Session.getInstance(getPropiedades465(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO_ENVIO, desencripta(PASSWORD_ALTERNATIVO));
                }
            });
            Message mensaje = new MimeMessage(sesion);
            if (responder != null) {
                mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            }
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO, ALIAS));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            mensaje.setSubject(asunto);
            mensaje.setContent(contenido, MIME_TYPE);
            Transport.send(mensaje);
            ok = true;
        } catch (AddressException ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError(ex);
        } catch (MessagingException | UnsupportedEncodingException ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError(ex);
        } finally {
            String correos = new PropiedadDAO().selectPropiedad(Propiedad.CORREO_ALERTA.getIdPropiedad());
            return ok;
        }
    }

    public static boolean enviaCorreos(String[] para, String asunto,
            String contenido, String responder) {
        boolean ok = false;
        try {
            Session sesion = Session.getInstance(getPropiedades25(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO_ENVIO, desencripta(PASSWORD));
                }
            });
            Message mensaje = new MimeMessage(sesion);
            if (responder != null) {
                mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            }
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO, ALIAS));
            for (String p : para) {
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(p));
            }
            mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            mensaje.setSubject(asunto);
            mensaje.setContent(contenido, MIME_TYPE);
            Transport.send(mensaje);
            ok = true;
        } catch (AddressException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } catch (MessagingException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } catch (Exception | Error ex) {
            mx.com.pakar.util.Util.registraError((Exception) ex);
        } finally {
            return ok ? ok : enviaCorreosAlternativo(para, asunto, contenido, responder);
        }
    }

    public static boolean enviaCorreosAlternativo(String[] para, String asunto, String contenido, String responder) {
        boolean ok = false;
        String error = "";
        try {
            Session sesion = Session.getInstance(getPropiedades465(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO_ENVIO, desencripta(PASSWORD_ALTERNATIVO));
                }
            });
            Message mensaje = new MimeMessage(sesion);
            if (responder != null) {
                mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            }
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO, ALIAS));
            for (String p : para) {
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(p));
            }
            mensaje.setSubject(asunto);
            mensaje.setContent(contenido, MIME_TYPE);
            Transport.send(mensaje);
            ok = true;
        } catch (AddressException ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError(ex);
        } catch (MessagingException ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError(ex);
        } catch (Exception | Error ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError((Exception) ex);
        } finally {
            String correos = new PropiedadDAO().selectPropiedad(Propiedad.CORREO_ALERTA.getIdPropiedad());
            return ok;
        }
    }

    public static boolean enviaCorreo(String para, String asunto, String contenido, String archivo, String nombreArchivo, String responder) {
        boolean ok = false;
        try {
            Session sesion = Session.getInstance(getPropiedades25(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO_ENVIO, desencripta(PASSWORD));
                }
            });
            Message mensaje = new MimeMessage(sesion);
            if (responder != null) {
                mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            }
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO, ALIAS));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            mensaje.setSubject(asunto);
            BodyPart texto = new MimeBodyPart();
            texto.setContent(contenido, MIME_TYPE);
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
            adjunto.setFileName(nombreArchivo);
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            mensaje.setContent(multiParte);
            Transport.send(mensaje);
            ok = true;
        } catch (AddressException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } catch (MessagingException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } catch (Exception | Error ex) {
            mx.com.pakar.util.Util.registraError((Exception) ex);
        } finally {
            return ok ? ok : enviaCorreoAlternativo(para, asunto, contenido, archivo, nombreArchivo, responder);
        }
    }

    public static boolean enviaCorreoAlternativo(String para, String asunto, String contenido, String archivo, String nombreArchivo, String responder) {
        boolean ok = false;
        String error = "";
        try {
            Session sesion = Session.getInstance(getPropiedades465(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO_ENVIO, desencripta(PASSWORD_ALTERNATIVO));
                }
            });
            Message mensaje = new MimeMessage(sesion);
            if (responder != null) {
                mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            }
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO, ALIAS));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(para));
            mensaje.setSubject(asunto);
            BodyPart texto = new MimeBodyPart();
            texto.setContent(contenido, MIME_TYPE);
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
            adjunto.setFileName(nombreArchivo);
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            mensaje.setContent(multiParte);
            Transport.send(mensaje);
            ok = true;
        } catch (AddressException ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError(ex);
        } catch (MessagingException ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError(ex);
        } catch (Exception | Error ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError((Exception) ex);
        } finally {
            String correos = new PropiedadDAO().selectPropiedad(Propiedad.CORREO_ALERTA.getIdPropiedad());
            return ok;
        }
    }

    public static boolean enviaCorreos(String[] para, String asunto, String contenido, String archivo, String nombreArchivo, String responder) {
        boolean ok = false;
        try {
            Session sesion = Session.getInstance(getPropiedades25(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO_ENVIO, desencripta(PASSWORD));
                }
            });
            Message mensaje = new MimeMessage(sesion);
            if (responder != null) {
                mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            }
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO, ALIAS));
            for (String p : para) {
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(p));
            }
            mensaje.setSubject(asunto);
            BodyPart texto = new MimeBodyPart();
            texto.setContent(contenido, MIME_TYPE);
            //BodyPart adjunto = new MimeBodyPart();
            //adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
            //adjunto.setFileName(nombreArchivo);
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            //multiParte.addBodyPart(adjunto);
            mensaje.setContent(multiParte);
            Transport.send(mensaje);
            ok = true;
        } catch (AddressException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } catch (MessagingException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        } catch (Exception | Error ex) {
            mx.com.pakar.util.Util.registraError((Exception) ex);
        } finally {
            return ok ? ok : enviaCorreosAlternativo(para, asunto, contenido, archivo, nombreArchivo, responder);
        }
    }

    public static boolean enviaCorreosAlternativo(String[] para, String asunto, String contenido, String archivo, String nombreArchivo, String responder) {
        boolean ok = false;
        String error = "";
        try {
            Session sesion = Session.getInstance(getPropiedades465(), new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CORREO_ENVIO, desencripta(PASSWORD_ALTERNATIVO));
                }
            });
            Message mensaje = new MimeMessage(sesion);
            if (responder != null) {
                mensaje.setReplyTo(new Address[]{new InternetAddress(responder)});
            }
            mensaje.setFrom(new InternetAddress(CORREO_ENVIO, ALIAS));
            for (String p : para) {
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(p));
            }
            mensaje.setSubject(asunto);
            BodyPart texto = new MimeBodyPart();
            texto.setContent(contenido, MIME_TYPE);
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(new DataHandler(new FileDataSource(archivo)));
            adjunto.setFileName(nombreArchivo);
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
            mensaje.setContent(multiParte);
            Transport.send(mensaje);
            ok = true;
        } catch (AddressException ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError(ex);
        } catch (MessagingException ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError(ex);
        } catch (Exception | Error ex) {
            error = ex.getMessage();
            mx.com.pakar.util.Util.registraError((Exception) ex);
        } finally {
            String correos = new PropiedadDAO().selectPropiedad(Propiedad.CORREO_ALERTA.getIdPropiedad());
            return ok;
        }
    }

    public static void copiaEscalaJPG(String origen, String destino, int ancho, int alto) {
        ImageIcon imageIcon = new ImageIcon(origen);
        if (imageIcon.getIconWidth() != ancho || imageIcon.getIconHeight() != alto) {
            imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
        }

        BufferedImage bi = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);

        Graphics g = bi.getGraphics();
        g.drawImage(imageIcon.getImage(), 0, 0, null);

        try {
            ImageIO.write(bi, "jpg", new File(destino));
        } catch (IOException ex) {
            mx.com.pakar.util.Util.registraError(ex);
        }

    }

    public static boolean copiaEscalaImagen(byte[] origen, String destino, int maxAncho, int maxAlto) {
        boolean ok = true;
        try {
            ImageIcon imageIcon = new ImageIcon(origen);
            int ancho = imageIcon.getIconWidth();
            int alto = imageIcon.getIconHeight();
            if (!(ancho <= maxAncho && alto <= maxAlto)) {
                double anchoI = ancho;
                double altoI = alto;
                double anchoM = maxAncho;
                double altoM = maxAlto;

                double escala = Math.min(anchoM / anchoI, altoM / altoI);
                ancho = (int) (anchoI * escala);
                alto = (int) (altoI * escala);
                //Copia a escala
                imageIcon = new ImageIcon(imageIcon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
                BufferedImage bi = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_RGB);
                Graphics g = bi.getGraphics();
                g.drawImage(imageIcon.getImage(), 0, 0, null);
                ImageIO.write(bi, "JPG", new File(destino));
            } else {
                FileOutputStream fos = new FileOutputStream(new File(destino));
                fos.write(origen);
            }
        } catch (FileNotFoundException ex) {
            ok = false;
            mx.com.pakar.util.Util.registraError(ex);
        } catch (IOException ex) {
            ok = false;
            mx.com.pakar.util.Util.registraError(ex);
        } catch (NumberFormatException ex) {
            ok = false;
            mx.com.pakar.util.Util.registraError(ex);
        } catch (Exception ex) {
            ok = false;
            mx.com.pakar.util.Util.registraError(ex);
        } finally {
            return ok;
        }
    }

    public static String eliminaAcentos(String input) {
        String original = "áàäéèëíìïóòöúùüñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ,";
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC.";
        String output = input.replaceAll("[\n\r]", " ");

        for (int i = 0; i < original.length(); i++) {
            output = output.replace(original.charAt(i), ascii.charAt(i));

        }
        return output;
    }

    public static String analizaLetras(String idSocio) {
        idSocio = idSocio.replace('A', '1');
        idSocio = idSocio.replace('B', '2');
        idSocio = idSocio.replace('C', '3');
        idSocio = idSocio.replace('D', '4');
        idSocio = idSocio.replace('E', '5');
        idSocio = idSocio.replace('F', '6');
        idSocio = idSocio.replace('G', '7');
        idSocio = idSocio.replace('H', '8');
        idSocio = idSocio.replace('I', '9');
        idSocio = idSocio.replace('J', '1');
        idSocio = idSocio.replace('K', '2');
        idSocio = idSocio.replace('L', '3');
        idSocio = idSocio.replace('M', '4');
        idSocio = idSocio.replace('N', '5');
        idSocio = idSocio.replace('O', '6');
        idSocio = idSocio.replace('P', '7');
        idSocio = idSocio.replace('Q', '8');
        idSocio = idSocio.replace('R', '9');
        idSocio = idSocio.replace('S', '1');
        idSocio = idSocio.replace('T', '2');
        idSocio = idSocio.replace('U', '3');
        idSocio = idSocio.replace('V', '4');
        idSocio = idSocio.replace('W', '5');
        idSocio = idSocio.replace('X', '6');
        idSocio = idSocio.replace('Y', '7');
        idSocio = idSocio.replace('Z', '8');
        return idSocio;
    }

    public static int analizaDecenaCercana(int res) {
        int decena = (int) (Math.floor(res / 10) * 10.0D);

        if (decena == res) {
            return decena;
        } else {
            return decena + 10;
        }
    }

    public static String getRandomPassword(int longitud) {
        return RandomStringUtils.random(longitud, CARACTERES_PASSWORD);
    }
}
