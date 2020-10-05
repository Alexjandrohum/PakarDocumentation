package mx.com.pakar.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.channels.FileChannel;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import javax.faces.context.ExternalContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nicolas Canaan
 */
public class Util {

    /**
     *
     * Convierte un numero en texto con formato de moneda
     *
     * @param numero Numero a convertir
     * @return Texto formateado
     */
    public static String formatoNumero(double numero) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(numero);
    }

    public static String formatoMoneda(double numero) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return decimalFormat.format(numero);
    }

    public static String formatoNumeroVenta(double numero) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(0);
        return numberFormat.format(numero);
    }

    public static String formatoPorcentaje(double numero) {
        NumberFormat numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        return numberFormat.format(numero);
    }

    /**
     *
     * Convierte una fecha en texto con formato de Fecha
     *
     * @param fecha Fecha
     * @return Texto formateado
     */
    public static String formatoFechaDiagonal(Date fecha) {
        return new SimpleDateFormat("dd/MM/yyyy").format(fecha);
    }

    /**
     *
     * Convierte una fecha en texto con formato de Fecha
     *
     * @param fecha Fecha
     * @return Texto formateado
     */
    public static String formatoFechaTexto(Date fecha) {
        return DateFormat.getDateInstance(DateFormat.FULL, new Locale("es", "MX")).format(fecha);
    }

    /**
     *
     * Convierte una fecha en texto con formato de Fecha
     *
     * @param fecha Fecha
     * @return Texto formateado
     */
    public static String formatoFechaHora(Date fecha) {
        return new SimpleDateFormat("dd/MM/yyyy kk:mm:ss").format(fecha);
    }

    /**
     *
     * Convierte una fecha en texto con formato de Fecha
     *
     * @param fecha Fecha
     * @return Texto formateado
     */
    public static String formatoFechaGuion(Date fecha) {
        return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }

    public static String formatoFechaGuionHoraSQL(Date fecha) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fecha);
    }

    public static String formatoFechaHoraCorta(Date fecha) {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(fecha).toUpperCase();
    }

    /**
     *
     * Convierte una fecha en texto con formato de Fecha
     *
     * @param fecha Fecha
     * @return Texto formateado
     */
    public static String formatoFechaGuionSQL(Date fecha) {
        return new SimpleDateFormat("dd-MM-yyyy").format(fecha);
    }

    public static String formatoSimpleFechaHora() {
        return new SimpleDateFormat("ddMMyyyy_kkmmss").format(new Date());
    }

    public static String formatoFechaHora() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    /**
     * total de días transcurridos entre dos fechas
     *
     * @param fechaInicio fechaFinal
     * @return
     */
    public static long totalDias(Date fechaFin, Date fechaInicio) {
        return ((fechaFin.getTime() - fechaInicio.getTime()) / 86400000);
    }

    public static String formatoHora(int segundos) {
        int m = (segundos / 60) % 60;
        int h = segundos / 3600;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m);
    }

    public static String formatoHoraCompleta(int segundos) {
        int s = segundos % 60;
        int m = (segundos / 60) % 60;
        int h = segundos / 3600;
        return (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s);
    }

    /**
     *
     * Devuelve la direccion actual de tomcat en ejecucion
     *
     * @param ec ExternalContext
     * @return Direccion actual
     */
    public static String getDireccionReal(ExternalContext ec) {
        return ((ServletContext) ec.getContext()).getRealPath("/");
    }

    public static boolean copiaArchivo(File origen, File destino) {
        boolean ok = false;
        long PASO = 30000000;
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(origen);
            fos = new FileOutputStream(destino);
            FileChannel canalFuente = fis.getChannel();
            FileChannel canalDestino = fos.getChannel();
            long ciclos = canalFuente.size() / PASO;
            long resto = canalFuente.size() % PASO;

            for (int i = 0; i < ciclos; i++) {
                canalFuente.transferTo(i * PASO, PASO, canalDestino);
            }
            canalFuente.transferTo(ciclos * PASO, resto, canalDestino);

            fis.close();
            fos.close();
            ok = true;
        } catch (FileNotFoundException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
            ok = false;
        } catch (IOException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
            ok = false;
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
            ok = false;
        } finally {
            return ok;
        }
    }

    public static String obtenerIP(ExternalContext ec) {
        HttpServletRequest hr = (HttpServletRequest) ec.getRequest();
        return hr.getRemoteHost();
    }
    
    /**
     * Devuelve la primera dirección IP diferente de LoopBack de acuerdo al parámetro
     * ingresado IPV4 ó IPV6
     * 
     * @param preferIpv4 Dirección tipo IPV4
     * @param preferIPv6 Dirección tipo IPV6
     * @return <code>InetAddress</code> con el valor de la IP ó null de no encontrar coincidencia.
     */
    public static String getIpAddress(boolean preferIpv4, boolean preferIPv6) {
        try {
            Enumeration en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                NetworkInterface i = (NetworkInterface) en.nextElement();
                if (i.isLoopback() || !i.isUp() || i.isVirtual() || i.isPointToPoint())
                    continue;
                for (Enumeration en2 = i.getInetAddresses(); en2.hasMoreElements();) {
                    InetAddress addr = (InetAddress) en2.nextElement();
                    if (!addr.isLoopbackAddress()) {
                        if (addr instanceof Inet4Address) {
                            if (preferIPv6) {
                                continue;
                            }
                            return addr.getHostAddress();
                        }
                        if (addr instanceof Inet6Address) {
                            if (preferIpv4) {
                                continue;
                            }
                            return addr.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException ex) {
            Util.registraError(ex);
        }
        return "";
    }
    
    /**
     * Obtiene la dirección IPv4 de la interfaz de red activa, tomando en cuenta
     * las iterfaces eth0, eno1 y em1, para los sistemas operativos Linux y para
     * los sistemas operativos Windows la primera interfaz de red activa que se
     * encuentre.
     *
     * @deprecated 
     * @return Dirección IPv4
     */
    public static String getIPv4InetAddress() {
        String ip = "localhost";
        try {
            List<String> interfaces = Arrays.asList(new String[]{"eth0", "eno1", "em1"});
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                if (interfaces.contains(netint.getName().toLowerCase())) {
                    Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                    for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                        if (inetAddress instanceof Inet4Address) {
                            ip = inetAddress.getHostAddress();
                            break;
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            return ip;
        }
    }

    public static void duerme(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException ex) {
        }
    }

    public static String selectClave(String idUsuario, java.sql.Date fecha, Connection con) {
        String clave = "";
        try {
            String query = "{ call crea_clave(?,?,?,1q1?) }";
            CallableStatement cs = con.prepareCall(query);
            cs.setDate(1, fecha);
            cs.setString(2, idUsuario);
            cs.setString(3, "S");
            cs.setString(4, clave);
            cs.registerOutParameter(4, Types.VARCHAR);
            cs.execute();
            clave = cs.getString(4);
            Factory.close(cs);
        } catch (SQLException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } finally {
            return clave;
        }
    }

    public static Date fechaSumaDias(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    /**
     * funciones para generar digito verificador CIE
     * 
     * @param idSocio
     * @return 
     */
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
    
    public static int getReferencia(String idSocio){
        //return (int) (Math.round(Math.random()*1000)%10);
        
        idSocio = Util.analizaLetras(idSocio);
        char[] idReverse = new char[idSocio.length()];
        int x = 0;
        for (int i = 1; i <= idSocio.length(); i++) {
            idReverse[x] = idSocio.toCharArray()[idSocio.toCharArray().length - i];
            x++;
        }
        
        int res = 0;
        int i = 1;
        for (char c : idReverse) {
            if ( (i%2) == 0 ) {
                res += Character.getNumericValue(c) * 1;
            } else {
                int mult = Character.getNumericValue(c) * 2;
                if (mult > 9) {
                    String decena = String.valueOf(mult);
                    int sumDec = 0;
                    for (char c1 : decena.toCharArray()) {
                        sumDec += Character.getNumericValue(c1);
                    }
                    res += sumDec;
                } else {
                    res += mult;
                }
            }
            i++;
        }
        
        return Util.analizaDecenaCercana(res) - res;
    }

    public static void registraError(Exception ex){
        Exception exc = new Exception();
        System.err.println(formatoFechaHora(new Date())+" - "+exc.getStackTrace()[1].getClassName()+"."+exc.getStackTrace()[1].getMethodName()+": "+ex.getMessage());
    }
    
    public static void registraError(Error ex){
        Exception exc = new Exception();
        System.err.println(formatoFechaHora(new Date())+" - "+exc.getStackTrace()[1].getClassName()+"."+exc.getStackTrace()[1].getMethodName()+": "+ex.getMessage());
    }
    
}
