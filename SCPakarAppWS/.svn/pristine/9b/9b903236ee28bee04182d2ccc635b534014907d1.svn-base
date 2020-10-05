package com.scpakar.scpakarappws.dao;

import com.scpakar.scpakarappws.dto.ChatEstatus;
import com.scpakar.scpakarappws.dto.ChatMensajeDTO;
import com.scpakar.scpakarappws.dto.ChatOrigen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import mx.com.pakar.util.Factory;
import mx.com.pakar.util.Util;

/**
 *
 * @author antonio.ruiz
 */
public class ChatDAO {
    
    public List<ChatMensajeDTO> getMensajesChat(String idSocio, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<ChatMensajeDTO> chats = new ArrayList<>();
        String query = "EXEC [dbo].[scpakar_chats_app] ?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            rs = ps.executeQuery();
            while (rs.next()) {
                ChatMensajeDTO chat = new ChatMensajeDTO();
                chat.setMensaje(rs.getString("Mensaje"));
                chat.setFecha(new SimpleDateFormat("d MMM yyyy").format(rs.getTimestamp("Fecha")));
                chat.setHora(new SimpleDateFormat("kk:mm a").format(rs.getTimestamp("Fecha")));
                chat.setTiempo(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(rs.getTimestamp("Fecha")));
                chat.setOrigen(ChatOrigen.getChatOrigen(rs.getInt("IdOrigen")));
                chat.setEstatus(ChatEstatus.getChatEstatus(rs.getInt("IdEstatus")));
                chats.add(chat);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return chats;
    }

    public boolean enviaMensaje(String mensaje, String idSocio, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean res = false;
        String query = "EXEC scpakar_chat_nuevo_mensaje ?,?,?,?";
        try {
            ps = con.prepareStatement(query);
            ps.setString(1, idSocio);
            ps.setString(2, mensaje);
            ps.setInt(3, ChatOrigen.SOCIO.getIdOrigen());
            ps.setString(4, "");
            rs = ps.executeQuery();
            if (rs.next()) {
                res = rs.getBoolean(1);
            }
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(rs);
            Factory.close(ps);
        }
        return res;
    }

    public boolean ocultaChats(String idSocio, Connection connection) {
        boolean resultado = false;
        PreparedStatement ps = null;
        String query = "UPDATE scp_chat_conversacion SET oculta = 1 WHERE IdSocio = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, idSocio);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            Util.registraError(ex);
        } catch (Exception ex) {
            Util.registraError(ex);
        } finally {
            Factory.close(ps);
        }
        return resultado;
    }

}
