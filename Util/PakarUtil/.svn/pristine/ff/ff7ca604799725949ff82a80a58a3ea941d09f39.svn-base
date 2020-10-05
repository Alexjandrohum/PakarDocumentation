package mx.com.pakar.util;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import mx.com.pakar.dao.PropiedadDAO;

/**
 *
 * @author nicolas.canaan
 */
public class ActiveDirectory {

    public static final String JNDI = "com.sun.jndi.ldap.LdapCtxFactory";
    public static final String SEGURIDAD_LOGIN = "simple";
    public static final String USUARIO = "saupr";
    public static final String PASSWORD = "Ejemplo123";
    //public static final String URL = "ldap://PAKAR07:389";
    
    public static final String DOMINIO_LARGO = "@grupopakar.com.mx";
    public static final String DOMINIO = "GRUPOPAKAR\\";
    
    public static final String URL = "ldap://PAKAR07:389";
    public static final String SEGURIDAD = "DIGEST-MD5";
    public static final String USUARIO_ADMINISTRADOR = "saup";
    public static final String PASSWORD_ADMINISTRADOR = "ABControlUserS*24687";
    
      @SuppressWarnings("UseOfObsoleteCollectionType")
    public static LdapContext getContext() {
        LdapContext context = null;
        try {
            Hashtable<String, String> enviroment = new Hashtable<String, String>();
            enviroment.put(Context.INITIAL_CONTEXT_FACTORY, JNDI);
            enviroment.put(Context.PROVIDER_URL, URL);
            enviroment.put(Context.SECURITY_AUTHENTICATION, SEGURIDAD);
            enviroment.put(Context.SECURITY_PRINCIPAL, USUARIO_ADMINISTRADOR);
            enviroment.put(Context.SECURITY_CREDENTIALS, PASSWORD_ADMINISTRADOR);
            enviroment.put("java.naming.ldap.attributes.binary", "objectSID");
            context = new InitialLdapContext(enviroment, null);
        } catch (NamingException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            return context;
        }
    }
    
      @SuppressWarnings("UseOfObsoleteCollectionType")
    public static LdapContext getContextAD(Connection con) {
        LdapContext context = null;
        try {
            Hashtable<String, String> enviroment = new Hashtable<String, String>();
            PropiedadDAO dao = new PropiedadDAO();
            String url = "ldap://"+dao.selectValor("Servidor AD", con)+":"+dao.selectValor("Puerto AD", con);
            enviroment.put(Context.INITIAL_CONTEXT_FACTORY, JNDI);
            enviroment.put(Context.PROVIDER_URL, url);
            enviroment.put(Context.SECURITY_AUTHENTICATION, SEGURIDAD);
            enviroment.put(Context.SECURITY_PRINCIPAL, USUARIO_ADMINISTRADOR);
            enviroment.put(Context.SECURITY_CREDENTIALS, PASSWORD_ADMINISTRADOR);
            enviroment.put("java.naming.ldap.attributes.binary", "objectSID");
            context = new InitialLdapContext(enviroment, null);
        } catch (NamingException ex) {
            System.err.println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error: " + ex.getMessage());
        } finally {
            return context;
        }
    }

    @SuppressWarnings("UseOfObsoleteCollectionType")
    public static LdapContext getContext(String usuario, String password, Connection con) {
        LdapContext context = null;
        try {
            Hashtable<String, String> enviroment = new Hashtable<String, String>();
            enviroment.put(Context.INITIAL_CONTEXT_FACTORY, JNDI);
            PropiedadDAO dao = new PropiedadDAO();
            String url = "ldap://"+dao.selectValor("Servidor AD", con)+":"+dao.selectValor("Puerto AD", con);
            enviroment.put(Context.PROVIDER_URL, url);
            enviroment.put(Context.SECURITY_AUTHENTICATION, SEGURIDAD_LOGIN);
            enviroment.put(Context.SECURITY_PRINCIPAL, usuario+DOMINIO_LARGO);
            enviroment.put(Context.SECURITY_CREDENTIALS, password);
            context = new InitialLdapContext(enviroment,null);
        } catch (AuthenticationException ex) {
            Util.registraError(ex); //println("Error1: " + ex.getMessage());
        } catch (NamingException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } finally {
            return context;
        }
    }
    
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public static LdapContext getContext(Connection con) {
        LdapContext context = null;
        try {
            Hashtable<String, String> enviroment = new Hashtable<String, String>();
            enviroment.put(Context.INITIAL_CONTEXT_FACTORY, JNDI);
            PropiedadDAO dao = new PropiedadDAO();
            String url = "ldap://"+dao.selectValor("Servidor AD", con)+":"+dao.selectValor("Puerto AD", con);
            enviroment.put(Context.PROVIDER_URL, url);
            enviroment.put(Context.SECURITY_AUTHENTICATION, SEGURIDAD_LOGIN);
            enviroment.put(Context.SECURITY_PRINCIPAL, USUARIO+DOMINIO_LARGO);
            enviroment.put(Context.SECURITY_CREDENTIALS, PASSWORD);
            context = new InitialLdapContext(enviroment,null);
        } catch (AuthenticationException ex) {
            Util.registraError(ex); //println("Error1: " + ex.getMessage());
        } catch (NamingException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } finally {
            return context;
        }
    }
    
    public static List<String> getUsuariosAD( String usuario, Connection con ){
        List<String> usuarios = new ArrayList<String>();
        try{
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String returnedAtts[] = {"SamAccountName"};
            controls.setReturningAttributes(returnedAtts);
            NamingEnumeration<SearchResult> resultats = ActiveDirectory.getContext(con).search("DC=grupopakar,DC=com,DC=mx", "(& (SamAccountName=" + usuario + "*)(objectClass=user))", controls);
            while (resultats.hasMoreElements()) {
                SearchResult sr = (SearchResult) resultats.next();
                usuarios.add(sr.getAttributes().get("SamAccountName")==null?"":sr.getAttributes().get("SamAccountName").get().toString());
            }
        } catch(NamingException ex){
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch(Exception ex){
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally{
            return usuarios;
        }
    }
    
    public static void close(LdapContext context){
        try{
            context.close();
        } catch (NamingException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        }
    }
    
    public static void close(DirContext context){
        try{
            context.close();
        } catch (NamingException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        }
    }
    
    public static String getSIDasStringOfBytes(byte[] sid) {
        String strSID = "";
        int version;
        long authority;
        int count;
        String rid = "";
        strSID = "S";

        version = sid[0];
        strSID = strSID + "-" + Integer.toString(version);
        for (int i = 6; i > 0; i--) {
            rid += byte2hex(sid[i]);
        }

        authority = Long.parseLong(rid);
        strSID = strSID + "-" + Long.toString(authority);

        count = sid[7] & 0xFF;

        for (int i = 0; i < count; i++) {
            rid = "";
            for (int j = 11; j > 7; j--) {
                rid += byte2hex(sid[j + (i * 4)]);
            }
            strSID = strSID + "-" + Long.parseLong(rid, 16);
        }
        return strSID;
    }
    
    private static String byte2hex(byte b) {
        String ret = Integer.toHexString((int) b & 0xFF);
        if (ret.length() < 2) {
            ret = "0" + ret;
        }
        return ret;
    }
}
