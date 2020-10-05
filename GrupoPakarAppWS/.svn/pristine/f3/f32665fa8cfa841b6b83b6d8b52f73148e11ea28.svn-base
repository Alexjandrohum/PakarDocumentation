package com.grupopakar.grupopakarappws.delegate;

import com.grupopakar.grupopakarappws.dao.DispositivoDAO;
import java.sql.Connection;

/**
 *
 * @author pepe.ruiz
 */
public class DispositivoDelegate {

    private final DispositivoDAO dispositivoDAO;

    public DispositivoDelegate() {
        this.dispositivoDAO = new DispositivoDAO();
    }

    public void registraToken(Connection con, String tokenUser, String tokenFB) {
        int idUser = dispositivoDAO.getIdUser(con, tokenUser);
        if (idUser != -1) {
            dispositivoDAO.registraToken(con, idUser, tokenFB);
        }
    }
}
