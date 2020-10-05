package com.scpakar.scpakarweb.delegate;

import com.scpakar.scpakarweb.dao.ContactoDAO;
import com.scpakar.scpakarweb.dto.ContactoDTO;

/**
 * @author Pablo Martinez
 */
public class ContactoDelegate {

    private final ContactoDAO contactoDao;

    public ContactoDelegate() {
        this.contactoDao = new ContactoDAO();
    }

    public boolean enviaCorreoContacto(ContactoDTO dto) {
        return contactoDao.enviaContacto(dto);
    }

}
