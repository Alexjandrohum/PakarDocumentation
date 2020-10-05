package com.scpakar.scpakarweb.vo;

import com.scpakar.scpakarweb.delegate.BannerDelegate;
import com.scpakar.scpakarweb.dto.DestacadoDTO;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author pablo.martinez
 */
@ManagedBean(name = "Banner")
@ViewScoped
@SuppressWarnings("CallToPrintStackTrace")
public class BannerVO implements Serializable {

    private BannerDelegate delegate;
    private List<DestacadoDTO> listaBanner;

    /**
     * Creates a new instance of BannerVO
     */
    public BannerVO() {
        delegate = new BannerDelegate();
        listaBanner = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        listaBanner = delegate.selectBannerActivo();
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream ois) {
        try {
            delegate = new BannerDelegate();

            listaBanner = delegate.selectBannerActivo();

        } catch (NullPointerException ex) {
            ex.printStackTrace();
            mx.com.pakar.util.Util.registraError(ex);
        }
    }

    private void writeObject(ObjectOutputStream oos) {

    }

    public List<DestacadoDTO> getListaBanner() {
        return listaBanner;
    }

    public void setListaBanner(List<DestacadoDTO> listaBanner) {
        this.listaBanner = listaBanner;
    }

}
