package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.LoginDAO;
import com.scpakar.scpakarappws.dao.RegistroDAO;
import com.scpakar.scpakarappws.dto.ErrorDTO;
import com.scpakar.scpakarappws.dto.SocioDTO;
import com.scpakar.scpakarappws.util.AESCrypt;
import com.scpakar.scpakarappws.util.UtilSCW;
import java.sql.Connection;

/**
 *
 * @author antonio.ruiz
 */
public class LoginDelegate {

    private final LoginDAO loginDAO;
    private final RegistroDAO registroDAO;

    public LoginDelegate() {
        this.loginDAO = new LoginDAO();
        this.registroDAO = new RegistroDAO();
    }

    public SocioDTO validaCorreo(String email, Connection con) {
        return loginDAO.validaCorreo(email, con);
    }

    public ErrorDTO activaSocio(String email, String idSocio, String password,
            String codigo, String serie, boolean reEmail, int origen, Connection con) {
        return registroDAO.activaSocio(email, idSocio, password, codigo, serie, reEmail, origen, con);
    }

    public String selectPaginaAfiliate(Connection con) {
        return loginDAO.selectPaginaAfiliate(con);
    }

    public SocioDTO login(String usuario, String password, Connection con) {
        return loginDAO.login(usuario, password, con);
    }

    public ErrorDTO recuperaPassword(String idSocio, String correo, Connection con) {
        ErrorDTO resultado = new ErrorDTO();
        SocioDTO socio = loginDAO.recuperaPassword(idSocio, correo, con);

        if (socio.isValido()) {
            String passwordTemporal = UtilSCW.getRandomPassword(8);
//            System.out.println(socio.getIdSocio() + ": " + passwordTemporal);
            String contenidoCorreo = loginDAO.getContenidoCorreoRecuperacion(socio, passwordTemporal);
            boolean exito = false;
            if (loginDAO.actualizaPassword(socio.getIdSocio(), AESCrypt.encryptMD5(passwordTemporal), con)) {
                exito = UtilSCW.enviaCorreo(socio.getCorreo(), "Restablecimiento de contraseña", contenidoCorreo, socio.getCorreo(), con);
            }
            resultado.setExito(exito);
        } else {
            resultado.setErrorSocio(socio.getIdSocio().isEmpty());
            resultado.setErrorBloqueoSocio(socio.isBloqueado());
        }
        return resultado;
    }

    public boolean actualizaPassword(String idSocio, String password, String passwordActual, Connection con) {
        return loginDAO.actualizaPassword(idSocio, password, passwordActual, con);
    }
}
