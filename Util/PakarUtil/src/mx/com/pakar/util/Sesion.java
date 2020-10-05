package mx.com.pakar.util;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.faces.context.ExternalContext;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.Query;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.LdapContext;
import javax.servlet.http.HttpSession;
import mx.com.pakar.dao.ControlSesionDAO;
import mx.com.pakar.dao.EncriptacionDAO;
import mx.com.pakar.dto.ControlSesionDTO;
import mx.com.pakar.dto.GrupoActiveDirectoryDTO;
import mx.com.pakar.dto.ModuloDTO;
import mx.com.pakar.dto.UsuarioActiveDirectoryDTO;
import mx.com.pakar.dto.UsuarioAdDTO;

/**
 *
 * @author nicolas.canaan
 */
public class Sesion {

    /**
     * Permite el inicio de sesión de forma automática por medio de el parseo de
     * una petición POST con parámetro encriptado. Los datos contenidos en el
     * parámetro recibido son: IdUsuario, Contexto de aplicación e IP origen.
     * Los datos obtenidos son insertados en la sesión actual la cual es
     * retomada y usada para el acceso.
     *
     * Dependencias: jasypt-1.9.2.jar
     *
     * @param ec ExternalContext de aplicación.
     * @param idAplicacion Identificador de la aplicación.
     * @param con Conexión a la base de datos.
     */
    public static void loginApp(ExternalContext ec, String idAplicacion, Connection con) {
        EncriptacionDAO crypt = new EncriptacionDAO();
        HttpSession sesion = (HttpSession) ec.getSession(false);
        Map params = ec.getRequestParameterMap();
        String login = (params.get("p") != null) ? params.get("p").toString() : "";
//        System.out.println("Login: " + login);
        if (!login.trim().isEmpty()) {
            login = (login.endsWith(".jsf")) ? login.substring(0, login.length() - 4) : login;
            /**
             * [0]=IdUsuario, [1]=Contexto(Aplicacion), [2]=IP
             */
            String decripted = crypt.desencriptar(login);
//            System.out.println("Decripted: " + decripted);
            String[] parametros = decripted.split("\\|");
            if (parametros != null) {
                if (parametros.length > 0) {
//                    System.out.println("IdUsuario: " + parametros[0]);
//                    System.out.println("Contexto: " + parametros[1]);
//                    System.out.println("IP: " + parametros[2]);
                    UsuarioAdDTO usuarioDto = getUsuario(parametros[0], idAplicacion, con);
                    if (usuarioDto != null) {
                        try {
                            if (sesion == null) {
                                sesion = (HttpSession) ec.getSession(true);
                            }
                            sesion.setAttribute("usuario", usuarioDto.getIdUsuario());
                            sesion.setAttribute("perfil", usuarioDto.getPerfil());
                            sesion.setAttribute("nombre", usuarioDto.getNombre());
                            sesion.setAttribute("tienda", usuarioDto.getTienda());
                            sesion.setAttribute("contexto", parametros[1]);
                            sesion.setAttribute("ip", parametros[2]);
                        } catch (NullPointerException ex) {
                            Util.registraError(ex);
                        }
                    }
                }
            }
        }
    }

    public static void redirecciona(String pagina, ExternalContext ec) {
        try {
            ec.redirect(pagina);
        } catch (IOException ex) {
            Util.registraError(ex);
        }
    }

    /**
     * Redirecciona hacía una ip/aplicacion enviando los parámetros necesarios
     * para el inicio de sesión.
     *
     * @param ipDestino Dirección IP donde está alojada la aplicación, si con
     * tiene puerto hay que agregarlo.
     * @param pagina Contexto de la aplicación, nombre de la aplicación.
     * @param controlSesion Objeto de sesión válido del cual se extrae el
     * usuario logueado.
     * @param ec ExternalContext de la aplicación que invoca la redirección.
     */
    public static void redireccionaAcceso(String ipDestino, String pagina, ControlSesionDTO controlSesion, ExternalContext ec) {
        try {
            EncriptacionDAO crypt = new EncriptacionDAO();
            String ipOrigen = getIpAndPort();
            String cadena = controlSesion.getIdUsuario() + "|" + ec.getRequestContextPath().substring(1) + "|" + ipOrigen;
            String urlEnconde = URLEncoder.encode(crypt.encripta(cadena), "UTF-8");
            String URL = "http://" + ipDestino + "/" + pagina + "/acceso.jsf?p=" + urlEnconde;
            ec.redirect(URL);
        } catch (IOException ex) {
            Util.registraError(ex);
        } catch (NullPointerException ex) {
            Util.registraError(ex);
        }
    }

    public static String getIpAndPort() {
        String ipPort = "localhost";
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();

            Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));

            String host = Util.getIpAddress(true, false);
            String port = objectNames.iterator().next().getKeyProperty("port");

            ipPort = host + ":" + port;
        } catch (MalformedObjectNameException ex) {
            Util.registraError(ex);
        } catch (NullPointerException ex) {
            Util.registraError(ex);
        } finally {
            return ipPort;
        }
    }

    /**
     * Obtiene una URL hacía una ip/aplicacion enviando los parámetros
     * necesarios para el inicio de sesión.
     *
     * @param ipDestino Dirección IP donde está alojada la aplicación, si con
     * tiene puerto hay que agregarlo.
     * @param pagina Contexto de la aplicación, nombre de la aplicación.
     * @param controlSesion Objeto de sesión válido del cual se extrae el
     * usuario logueado.
     * @param ec ExternalContext de la aplicación que invoca la redirección.
     * @return URL formateada y encriptada para redirección.
     */
    public static String urlAcceso(String ipDestino, String pagina, ControlSesionDTO controlSesion, ExternalContext ec) {
        String url = "#";
        try {
            EncriptacionDAO crypt = new EncriptacionDAO();
            String ipOrigen = getIpAndPort();
            String cadena = controlSesion.getIdUsuario() + "|" + ec.getRequestContextPath().substring(1) + "|" + ipOrigen;
            String urlEnconde = URLEncoder.encode(crypt.encripta(cadena), "UTF-8");
            url = "http://" + ipDestino + "/" + pagina + "/acceso.jsf?p=" + urlEnconde;
        } catch (IOException ex) {
            Util.registraError(ex);
        } finally {
            return url;
        }
    }

    /**
     * Obtiene una URL hacía una ip/aplicacion enviando los parámetros
     * necesarios para el inicio de sesión.
     *
     * @param ipDestino Dirección IP donde está alojada la aplicación, si con
     * tiene puerto hay que agregarlo.
     * @param pagina Contexto de la aplicación, nombre de la aplicación.
     * @param modulo Modulo al que se requiere acceder.
     * @param controlSesion Objeto de sesión válido del cual se extrae el
     * usuario logueado.
     * @param ec ExternalContext de la aplicación que invoca la redirección.
     * @return URL formateada y encriptada para redirección.
     */
    public static String urlAccesoModulo(String ipDestino, String pagina, String modulo, ControlSesionDTO controlSesion, ExternalContext ec) {
        String url = "#";
        try {
            EncriptacionDAO crypt = new EncriptacionDAO();
//            System.out.println("IP Destino: " + ipDestino);
            String ipOrigen = getIpAndPort();
//            System.out.println("IP Origen: " + ipOrigen);
            String cadena = controlSesion.getIdUsuario() + "|" + ec.getRequestContextPath().substring(1) + "|" + ipOrigen;
//            System.out.println("Cadena: " + cadena);
            String urlEnconde = URLEncoder.encode(crypt.encripta(cadena), "UTF-8");
            url = "http://" + ipDestino + "/" + pagina + "/" + modulo + ".jsf?p=" + urlEnconde;
//            System.out.println("URL: " + url);
        } catch (IOException ex) {
            Util.registraError(ex);
        } finally {
            return url;
        }
    }

    /**
     * Obtiene la lista de módulos asignados a un usuario de una determinada
     * aplicación
     *
     * @param connection Conexión activa hacía la base de datos.
     * @param controlSesion Objeto de sesión válido donde se extrae el usuario
     * logueado.
     * @param idAplicacion Identificador de la aplicación.
     * @return Lista de módulos de la aplicaciónde la cual se buscarán los
     * módulos asignados al usuario contenido el objeto controlSesion.
     */
    public static List<ModuloDTO> getModuloAplicacion(Connection connection, ControlSesionDTO controlSesion, String idAplicacion) {
        UsuarioAdDTO usr = Sesion.getUsuario(controlSesion.getIdUsuario(), idAplicacion, connection);
        ControlSesionDTO modulos = Sesion.getControlSesion(usr.getIdUsuario(), usr.getPerfil(), usr.getNombre(), usr.getTienda(), idAplicacion, connection);
        return modulos.getMenu();
    }

    public static void cierraSesion(ExternalContext ec) {
        try {
            HttpSession sesion = (HttpSession) ec.getSession(false);
            if (sesion != null) {
                Enumeration attrs = sesion.getAttributeNames();
                while (attrs.hasMoreElements()) {
                    sesion.setAttribute(attrs.nextElement().toString(), null);
                }
                sesion.invalidate();
            }
        } catch (NullPointerException ex) {
            Util.registraError(ex);
        } finally {
            Sesion.redirecciona("acceso.jsf", ec);
        }
    }

    public static void cierraSesion(ExternalContext ec, String paginaRedireccion) {
        try {
            HttpSession sesion = (HttpSession) ec.getSession(false);
            if (sesion != null) {
                Enumeration attrs = sesion.getAttributeNames();
                while (attrs.hasMoreElements()) {
                    sesion.setAttribute(attrs.nextElement().toString(), null);
                }
                sesion.invalidate();
            }
        } catch (NullPointerException ex) {
            Util.registraError(ex);
        } finally {
            Sesion.redirecciona(paginaRedireccion, ec);
        }
    }

    public static ControlSesionDTO getControlSesion(String idAplicacion, ExternalContext ec, Connection con) {
        ControlSesionDTO control = null;
        try {
            HttpSession sesion = (HttpSession) ec.getSession(false);
            String usuario = sesion.getAttribute("usuario").toString();
            String perfil = sesion.getAttribute("perfil").toString();
            String nombre = sesion.getAttribute("nombre").toString();
            String tienda = sesion.getAttribute("tienda").toString();
            control = getControlSesion(usuario, perfil, nombre, tienda, idAplicacion, con);
        } catch (NullPointerException ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally {
            return control;
        }
    }

    public static ControlSesionDTO getControlSesion(String idUsuario, String idPerfil, String nombre, String tienda, String idAplicacion, Connection con) {
        ControlSesionDTO dto = new ControlSesionDTO(idUsuario, idPerfil, nombre, tienda);
        dto.setMenu(new ControlSesionDAO().selectModulos(idPerfil, idAplicacion, con));
        return dto;
    }

    public static void getMenu(ControlSesionDTO dto, String idAplicacion, Connection con) {
        dto.setMenu(new ControlSesionDAO().selectModulos(dto.getIdPerfil(), idAplicacion, con));
    }

    public static UsuarioAdDTO getUsuario(String usuario, String password, String idAplicacion, Connection con) {
        UsuarioAdDTO usuarioDto = null;
        try {
            LdapContext context = ActiveDirectory.getContext(usuario, password, con);
            if (context != null) {
                usuarioDto = getUsuario(usuario, idAplicacion, context, con);
                ActiveDirectory.close(context);
            }
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally {
            return usuarioDto;
        }
    }

    public static UsuarioAdDTO getUsuario(String usuario, String idAplicacion, Connection con) {
        UsuarioAdDTO usuarioDto = null;
        try {
            LdapContext context = ActiveDirectory.getContext(con);
            if (context != null) {
                usuarioDto = getUsuario(usuario, idAplicacion, context, con);
                ActiveDirectory.close(context);
            }
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally {
            return usuarioDto;
        }
    }

    private static UsuarioAdDTO getUsuario(String usuario, String idAplicacion, LdapContext context, Connection con) {
        UsuarioAdDTO usuarioDto = null;
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String returnedAtts[] = {"SamAccountName", "DisplayName", "MemberOf"/*"Title","PhysicalDeliveryOfficeName"*/};
            controls.setReturningAttributes(returnedAtts);
            NamingEnumeration<SearchResult> resultats = context.search("DC=grupopakar,DC=com,DC=mx", "(& (SamAccountName=" + usuario + ")(objectClass=user))", controls);
            if (resultats.hasMoreElements()) {
                usuarioDto = new UsuarioAdDTO();
                SearchResult sr = (SearchResult) resultats.next();
                usuarioDto.setIdUsuario(sr.getAttributes().get("SamAccountName") == null ? "" : sr.getAttributes().get("SamAccountName").get().toString());
                usuarioDto.setNombre(sr.getAttributes().get("DisplayName") == null ? "" : sr.getAttributes().get("DisplayName").get().toString());
                ControlSesionDAO dao = new ControlSesionDAO();
                usuarioDto.setTienda(dao.selectTienda(sr.getNameInNamespace(), con));
                usuarioDto.setPerfil(getPerfil(sr.getAttributes().get("MemberOf") == null ? "" : sr.getAttributes().get("MemberOf").toString(), idAplicacion));
                //usuarioDto.setTienda(sr.getAttributes().get("PhysicalDeliveryOfficeName")==null?"":sr.getAttributes().get("PhysicalDeliveryOfficeName").get().toString());
                //usuarioDto.setPerfil(sr.getAttributes().get("Title")==null?"":sr.getAttributes().get("Title").get().toString());
            }
        } catch (NamingException ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally {
            return usuarioDto;
        }
    }

    public static UsuarioAdDTO getUsuarioNombre(String usuario, LdapContext context, Connection con) {
        UsuarioAdDTO usuarioDto = null;
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String returnedAtts[] = {"SamAccountName", "DisplayName", "MemberOf"};
            controls.setReturningAttributes(returnedAtts);
            NamingEnumeration<SearchResult> resultats = context.search("DC=grupopakar,DC=com,DC=mx", "(& (SamAccountName=" + usuario + ")(objectClass=user))", controls);
            if (resultats.hasMoreElements()) {
                usuarioDto = new UsuarioAdDTO();
                SearchResult sr = (SearchResult) resultats.next();
                usuarioDto.setIdUsuario(sr.getAttributes().get("SamAccountName") == null ? "" : sr.getAttributes().get("SamAccountName").get().toString());
                usuarioDto.setNombre(sr.getAttributes().get("DisplayName") == null ? "" : sr.getAttributes().get("DisplayName").get().toString());
                ControlSesionDAO dao = new ControlSesionDAO();
                usuarioDto.setTienda(dao.selectTienda(sr.getNameInNamespace(), con));
                usuarioDto.setPerfil("");
            }
        } catch (NamingException ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally {
            return usuarioDto;
        }
    }

    private static String getPerfil(String memberOf, String idAplicacion) {
        if (memberOf != null) {
            int i = memberOf.indexOf((idAplicacion + "_").toUpperCase());
            if (i >= 0) {
                String perfil = "";
                try {
                    int in = memberOf.substring(i, memberOf.length() - 1).indexOf(",OU=");
                    perfil = memberOf.substring(i, i + in);
                } catch (IndexOutOfBoundsException ex) {
                    Util.registraError(ex); //println( "Error: "+ex.getMessage() );
                } finally {
                    return perfil;
                }
            }
        }
        return "";
    }

    public static UsuarioActiveDirectoryDTO getUsuarioActiveDirectory(String usuario) {
        UsuarioActiveDirectoryDTO usuarioDto = null;
        try {
            LdapContext context = ActiveDirectory.getContext();
            if (context != null) {
                usuarioDto = getUsuario(usuario, context);
                ActiveDirectory.close(context);
            }
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } finally {
            return usuarioDto;
        }
    }

    protected static UsuarioActiveDirectoryDTO getUsuario(String usuario, LdapContext context) {
        UsuarioActiveDirectoryDTO usuarioDto = null;
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String returnedAtts[] = {"CN", "GivenName", "SN", "SamAccountName", "UserPrincipalName", "DisplayName",
                "PhysicalDeliveryOfficeName", "Description", "UserAccountControl", "Title", "ObjectSID", "telephoneNumber"};
            controls.setReturningAttributes(returnedAtts);
            NamingEnumeration<SearchResult> resultats = context.search("DC=grupopakar,DC=com,DC=mx", "(& (SamAccountName=" + usuario + ")(objectClass=user))", controls);
            if (resultats.hasMoreElements()) {
                usuarioDto = new UsuarioActiveDirectoryDTO();
                SearchResult sr = (SearchResult) resultats.next();
                if (sr.getAttributes().get("ObjectSID") != null) {
                    usuarioDto.setObjectSID(ActiveDirectory.getSIDasStringOfBytes((byte[]) sr.getAttributes().get("ObjectSID").get()));
                } else {
                    usuarioDto.setObjectSID("SIN PERMISO");
                }
                usuarioDto.setNamespace(sr.getNameInNamespace());
                usuarioDto.setCn(sr.getAttributes().get("CN") == null ? "" : sr.getAttributes().get("CN").get().toString());
                usuarioDto.setGivenName(sr.getAttributes().get("GivenName") == null ? "" : sr.getAttributes().get("GivenName").get().toString());
                usuarioDto.setSn(sr.getAttributes().get("SN") == null ? "" : sr.getAttributes().get("SN").get().toString());
                usuarioDto.setSamAccountName(sr.getAttributes().get("SamAccountName") == null ? "" : sr.getAttributes().get("SamAccountName").get().toString());
                usuarioDto.setUserPrincipalName(sr.getAttributes().get("UserPrincipalName") == null ? "" : sr.getAttributes().get("UserPrincipalName").get().toString());
                usuarioDto.setDisplayName(sr.getAttributes().get("DisplayName") == null ? "" : sr.getAttributes().get("DisplayName").get().toString());
                usuarioDto.setPhysicalDeliveryOfficeName(sr.getAttributes().get("PhysicalDeliveryOfficeName") == null ? "" : sr.getAttributes().get("PhysicalDeliveryOfficeName").get().toString());
                usuarioDto.setTelephoneNumber(sr.getAttributes().get("telephoneNumber") == null ? "" : sr.getAttributes().get("telephoneNumber").get().toString());
                usuarioDto.setDescription(sr.getAttributes().get("Description") == null ? "" : sr.getAttributes().get("Description").get().toString());
                usuarioDto.setUserAccountControl(sr.getAttributes().get("UserAccountControl") == null ? "" : sr.getAttributes().get("UserAccountControl").get().toString());
                usuarioDto.setTitle(sr.getAttributes().get("Title") == null ? "" : sr.getAttributes().get("Title").get().toString());
                usuarioDto.setMemberOf(getGrupos(usuario, context));
            }
        } catch (NamingException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            return usuarioDto;
        }
    }

    protected static List<GrupoActiveDirectoryDTO> getGrupos(String usuario, LdapContext context) {
        List<GrupoActiveDirectoryDTO> grupos = new ArrayList<GrupoActiveDirectoryDTO>();
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String returnedAtts[] = {"memberOf"};
            controls.setReturningAttributes(returnedAtts);
            NamingEnumeration<SearchResult> resultats = context.search("DC=grupopakar,DC=com,DC=mx", "(& (SamAccountName=" + usuario + ")(objectClass=user))", controls);
            if (resultats.hasMoreElements()) {
                SearchResult sr = (SearchResult) resultats.next();
                Attribute memberOf = sr.getAttributes().get("memberOf");
                if (memberOf != null) {
                    for (int i = 0; i < memberOf.size(); i++) {
                        Attributes atts = context.getAttributes(memberOf.get(i).toString(), new String[]{"CN"});
                        Attribute att = atts.get("CN");
                        GrupoActiveDirectoryDTO grupo = new GrupoActiveDirectoryDTO();
                        grupo.setCn(att.get().toString());
                        grupo.setDistinguishedName(memberOf.get(i).toString());
                        grupos.add(grupo);
                    }
                }
            }
        } catch (NamingException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        }
        Collections.sort(grupos);
        return grupos;
    }

    protected static List<GrupoActiveDirectoryDTO> getGrupos(LdapContext context) {
        List<GrupoActiveDirectoryDTO> grupos = new ArrayList<GrupoActiveDirectoryDTO>();
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String returnedAtts[] = {"CN", "distinguishedName"};
            controls.setReturningAttributes(returnedAtts);
            NamingEnumeration<SearchResult> resultats = context.search("DC=grupopakar,DC=com,DC=mx", "(objectClass=group)", controls);
            while (resultats.hasMoreElements()) {
                SearchResult sr = (SearchResult) resultats.next();
                GrupoActiveDirectoryDTO grupo = new GrupoActiveDirectoryDTO();
                grupo.setCn(sr.getAttributes().get("CN") == null ? "" : sr.getAttributes().get("CN").get().toString());
                grupo.setDistinguishedName(sr.getAttributes().get("distinguishedName") == null ? "" : sr.getAttributes().get("distinguishedName").get().toString());
                grupos.add(grupo);
            }
        } catch (NamingException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        }
        Collections.sort(grupos);
        return grupos;
    }

    protected static GrupoActiveDirectoryDTO getGrupo(String nombre, LdapContext context) {
        GrupoActiveDirectoryDTO grupo = new GrupoActiveDirectoryDTO();
        try {
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String returnedAtts[] = {"CN", "distinguishedName"};
            controls.setReturningAttributes(returnedAtts);
            NamingEnumeration<SearchResult> resultats = context.search("DC=grupopakar,DC=com,DC=mx", "(&(CN=" + nombre + ")(objectClass=group))", controls);
            if (resultats.hasMoreElements()) {
                SearchResult sr = (SearchResult) resultats.next();
                grupo.setCn(sr.getAttributes().get("CN") == null ? "" : sr.getAttributes().get("CN").get().toString());
                grupo.setDistinguishedName(sr.getAttributes().get("distinguishedName") == null ? "" : sr.getAttributes().get("distinguishedName").get().toString());
            }
        } catch (NamingException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        }
        return grupo;
    }
}
