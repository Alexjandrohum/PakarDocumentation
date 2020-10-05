package mx.com.pakar.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nicolas.canaan
 */
public class UsuarioActiveDirectoryDTO implements Serializable {

    private String objectSID;
    private String namespace;
    private String cn;
    private String givenName;
    private String sn;
    private String samAccountName;
    private String userPrincipalName;
    private String displayName;
    private String physicalDeliveryOfficeName;
    private String description;
    private String userAccountControl;
    private String unicodePwd;
    private String unicodePwdConfirma;
    private List<GrupoActiveDirectoryDTO> memberOf;
    private String title;
    private String telephoneNumber;

    public UsuarioActiveDirectoryDTO() {
        objectSID = "";
        namespace = "";
        cn = "";
        givenName = "";
        sn = "";
        samAccountName = "";
        userPrincipalName = "";
        displayName = "";
        physicalDeliveryOfficeName = "";
        description = "";
        userAccountControl = "";
        unicodePwd = "";
        unicodePwdConfirma = "";
        title = "";
        memberOf = new ArrayList<GrupoActiveDirectoryDTO>();
        telephoneNumber = "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsuarioActiveDirectoryDTO other = (UsuarioActiveDirectoryDTO) obj;
        if ((this.objectSID == null) ? (other.objectSID != null) : !this.objectSID.equals(other.objectSID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.objectSID != null ? this.objectSID.hashCode() : 0);
        return hash;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getObjectSID() {
        return objectSID;
    }

    public void setObjectSID(String objectSID) {
        this.objectSID = objectSID;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getPhysicalDeliveryOfficeName() {
        return physicalDeliveryOfficeName;
    }

    public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
        this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
    }

    public String getSamAccountName() {
        return samAccountName;
    }

    public void setSamAccountName(String samAccountName) {
        this.samAccountName = samAccountName;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserAccountControl() {
        return userAccountControl;
    }

    public void setUserAccountControl(String userAccountControl) {
        this.userAccountControl = userAccountControl;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getUnicodePwd() {
        return unicodePwd;
    }

    public void setUnicodePwd(String unicodePwd) {
        this.unicodePwd = unicodePwd;
    }

    public String getUnicodePwdConfirma() {
        return unicodePwdConfirma;
    }

    public void setUnicodePwdConfirma(String unicodePwdConfirma) {
        this.unicodePwdConfirma = unicodePwdConfirma;
    }

    public List<GrupoActiveDirectoryDTO> getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(List<GrupoActiveDirectoryDTO> memberOf) {
        this.memberOf = memberOf;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    @Override
    public String toString() {
        return "UsuarioActiveDirectoryDTO{" + "objectSID=" + objectSID + ", namespace=" + namespace + ", cn=" + cn + ", givenName=" + givenName + ", sn=" + sn + ", samAccountName=" + samAccountName + ", userPrincipalName=" + userPrincipalName + ", displayName=" + displayName + ", physicalDeliveryOfficeName=" + physicalDeliveryOfficeName + ", description=" + description + ", userAccountControl=" + userAccountControl + ", unicodePwd=" + unicodePwd + ", unicodePwdConfirma=" + unicodePwdConfirma + ", memberOf=" + memberOf + ", title=" + title + ", telephoneNumber=" + telephoneNumber + '}';
    }
    
}
