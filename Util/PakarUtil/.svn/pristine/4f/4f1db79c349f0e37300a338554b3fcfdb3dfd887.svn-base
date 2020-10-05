package mx.com.pakar.dto;

import java.io.Serializable;

/**
 *
 * @author nicolas.canaan
 */
public class GrupoActiveDirectoryDTO implements Serializable, Comparable<GrupoActiveDirectoryDTO> {
    
    private String cn;
    private String distinguishedName;

    public GrupoActiveDirectoryDTO() {
        cn = "";
        distinguishedName = "";
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDistinguishedName() {
        return distinguishedName;
    }

    public void setDistinguishedName(String distinguishedName) {
        this.distinguishedName = distinguishedName;
    }

    @Override
    public int compareTo(GrupoActiveDirectoryDTO o) {
        GrupoActiveDirectoryDTO otro = o;
        return this.cn.compareTo(otro.cn);
    }

    @Override
    public String toString() {
        return "GrupoActiveDirectoryDTO{" + "cn=" + cn + ", distinguishedName=" + distinguishedName + '}';
    }
    
}
