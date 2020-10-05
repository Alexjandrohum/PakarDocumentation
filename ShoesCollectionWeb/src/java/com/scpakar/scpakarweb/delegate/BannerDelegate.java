package com.scpakar.scpakarweb.delegate;

import com.scpakar.scpakarweb.dao.BannerDAO;
import com.scpakar.scpakarweb.dto.DestacadoDTO;
import java.util.List;

/**
 *
 * @author pablo.martinez
 */
public class BannerDelegate {

    private final BannerDAO bannderDao;

    public BannerDelegate() {
        this.bannderDao = new BannerDAO();
    }

    public List<DestacadoDTO> selectBannerActivo() {
        return bannderDao.selectBannerActivo();
    }
}
