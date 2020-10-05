package com.scpakar.scpakarappws.delegate;

import com.scpakar.scpakarappws.dao.ChatDAO;
import com.scpakar.scpakarappws.dto.ChatMensajeDTO;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author antonio.ruiz
 */
public class ChatDelegate {
    private final ChatDAO chatDAO;

    public ChatDelegate() {
        this.chatDAO = new ChatDAO();
    }

    public List<ChatMensajeDTO> getMensajesChat(String idSocio, Connection con) {
        return chatDAO.getMensajesChat(idSocio, con);
    }

    public boolean enviaMensaje(String mensaje, String idSocio, Connection con) {
        return chatDAO.enviaMensaje(mensaje, idSocio, con);
    }

    public boolean ocultaChats(String idSocio, Connection con) {
        return chatDAO.ocultaChats(idSocio, con);
    }
    
}
