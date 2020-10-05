package mx.com.pakar.util;

import java.io.BufferedReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 *
 * @author carlos.juarez
 */
public class Factory {
    
    /**
     *
     * Carga el driver de SQL Server 2005
     */
    public static void cargaDriver(){
        try {
            Class.forName( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
        } catch (ClassNotFoundException ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        }
    }
    
    /**
     *
     * Crea una conexion a la Base de Datos
     *
     * @return Conexion
     */
    public static Connection getConnection(String jndi) {
        Connection conexion = null;
        try {
            Context initCtx = new InitialContext();
            Context contexto = (Context) initCtx.lookup("java:comp/env");
            DataSource ds = (DataSource) contexto.lookup(jndi);
            conexion = ds.getConnection();
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } finally {
            return conexion;
        }
    }
    
    /**
     *
     * Crea una conexion a la Base de Datos
     *
     * @return Conexion
     */
    public static Connection getConnection(String url, String usuario, String password) {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch(Exception ex) {
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        } catch(Error ex){
            Util.registraError(ex); //println("Error: "+ex.getMessage());
        }finally {
            return conexion;
        }
    }

    /**
     *
     * Verifica si la conexion esta cerrada
     *
     * @param con Conexion a verificar
     * @return Verdadero en caso de estar cerrada, Falso en caso contrario
     */
    public static boolean isCerrada(Connection con) {
        boolean cerrada = true;
        try {
            if (con != null) {
                cerrada = con.isClosed();
            }
        } catch (Exception ex) {
            con = null;
        } finally {
            return cerrada;
        }
    }

    /**
     *
     * Cierra una conexion
     *
     * @param conexion
     */
    public static void close(Connection conexion) {
        if (conexion == null) {
            return;
        }
        try {
            conexion.close();
            conexion = null;
            //System.gc();
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        }
    }

    /**
     *
     * Cierra una sentencia
     *
     * @param statement
     */
    public static void close(Statement statement) {
        if (statement == null) {
            return;
        }
        try {
            statement.close();
            statement = null;
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        }
    }

    /**
     *
     * Cierra una sentencia
     *
     * @param statement
     */
    public static void close(PreparedStatement statement) {
        if (statement == null) {
            return;
        }
        try {
            statement.close();
            statement = null;
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        }
    }

    /**
     *
     * Cierra una sentencia
     *
     * @param statement
     */
    public static void close(CallableStatement statement) {
        if (statement == null) {
            return;
        }
        try {
            statement.close();
            statement = null;
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        }
    }

    /**
     *
     * Cierra un conjunto de resultados
     *
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet == null) {
            return;
        }
        try {
            resultSet.close();
            resultSet = null;
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        }
    }

    public static Date selectFecha(Connection con) {
        Date date = null;
        try {
            String query = "SELECT DATEADD(DAY,0,DATEDIFF(DAY,0,GETDATE())) AS fecha";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                date = rs.getDate("fecha");
            }
            Factory.close(rs);
            Factory.close(st);
        } catch (SQLException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } finally {
            return date;
        }
    }
    
    public static String selectHora(Connection con) {
        String hora = "";
        try {
            String query = "SELECT CONVERT(VARCHAR,GETDATE(),108) AS hora";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                hora = rs.getString("hora");
            }
            Factory.close(rs);
            Factory.close(st);
        } catch (SQLException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } finally {
            return hora;
        }
    }

    public static String selectFechaHora(Connection con) {
        String fecha = "";
        try {
            String query = "SELECT CONVERT(VARCHAR,GETDATE(),103)+' '+CONVERT(VARCHAR,GETDATE(),108) AS fecha";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                fecha = rs.getString("fecha");
            }
            Factory.close(rs);
            Factory.close(st);
        } catch (SQLException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } finally {
            return fecha;
        }
    }
    
    public static int selectHoy(Connection con) {
        int hoy = 0;
        try {
            String query = "SELECT DATEPART(DW,GETDATE()) AS hoy";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                hoy = rs.getInt("hoy");
            }
            Factory.close(rs);
            Factory.close(st);
        } catch (SQLException ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        } finally {
            return hoy;
        }
    }

    public static void close(BufferedReader br) {
        if (br == null) {
            return;
        }
        try {
            br.close();
            br = null;
        } catch (Exception ex) {
            Util.registraError(ex); //println("Error: " + ex.getMessage());
        }
    }
    
}