package com.grupopakar.grupopakarappws.delegate;

import com.grupopakar.grupopakarappws.dao.LoginDAO;
import com.grupopakar.grupopakarappws.dto.EstadoDTO;
import com.grupopakar.grupopakarappws.util.EstadoHTTPEnum;
import com.grupopakar.grupopakarappws.dto.MensajeDTO;
import com.grupopakar.grupopakarappws.dto.UsuarioDTO;
import com.grupopakar.grupopakarappws.util.GsonUtil;
import java.sql.Connection;

/**
 *
 * @author alberto.quirino
 */
public class LoginDelegate {

    private final LoginDAO loginDao;

    public LoginDelegate() {
        this.loginDao = new LoginDAO();
    }

    public EstadoDTO loginUsuario(String usuario, String password, Connection con) {
        EstadoDTO estado = new EstadoDTO();
        UsuarioDTO u = loginDao.getLoginUsuario(usuario, password, con);
        if(u != null){
            estado.setEstado(EstadoHTTPEnum.OK);
            estado.setObjeto(GsonUtil.gson.toJson(u));
        }else{
            estado.setEstado(EstadoHTTPEnum.NO_AUTORIZADO);
            estado.setObjeto(GsonUtil.gson.toJson(new MensajeDTO("Datos de acceso incorrectos")));
        }
        return estado;
    }
}
