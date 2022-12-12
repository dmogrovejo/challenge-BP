package com.challengebp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author David Mogrovejo
 */
@Entity
@Table(name = "ID_GEN")
public class IdGen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "NAME_PK")
    private String namePk;
    
    @Column(name = "VALUE_PK")
    private int valuePk;

    public IdGen() {
    }

    public IdGen(String namePk) {
        this.namePk = namePk;
    }

    public IdGen(String namePk, int valuePk) {
        this.namePk = namePk;
        this.valuePk = valuePk;
    }

    public String getNamePk() {
        return namePk;
    }

    public void setNamePk(String namePk) {
        this.namePk = namePk;
    }

    public int getValuePk() {
        return valuePk;
    }

    public void setValuePk(int valuePk) {
        this.valuePk = valuePk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (namePk != null ? namePk.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdGen)) {
            return false;
        }
        IdGen other = (IdGen) object;
        if ((this.namePk == null && other.namePk != null) || (this.namePk != null && !this.namePk.equals(other.namePk))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.IdGen[ namePk=" + namePk + " ]";
    }
    
}
