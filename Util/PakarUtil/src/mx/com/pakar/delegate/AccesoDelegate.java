package mx.com.pakar.delegate;

import java.sql.Connection;
import mx.com.pakar.dao.UsuarioDAO;
import mx.com.pakar.dto.UsuarioDTO;
import mx.com.pakar.dao.EncriptacionDAO;

/**
 *
 * @author nicolas.canaan
 */
public class AccesoDelegate {

    private final UsuarioDAO usuarioDao;
    private final EncriptacionDAO encriptacionDao;

    public AccesoDelegate() {
        usuarioDao = new UsuarioDAO();
        encriptacionDao = new EncriptacionDAO();
    }

    public UsuarioDTO getUsuario(String usuario, String password, Connection con) {
        return usuarioDao.select(usuario, encriptacionDao.encripta(password), con);
    }
}
