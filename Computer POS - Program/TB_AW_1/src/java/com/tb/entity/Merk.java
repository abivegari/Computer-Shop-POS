/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author abi_v
 */
@Entity
@Table(name = "merk")
@NamedQueries({
    @NamedQuery(name = "Merk.findAll", query = "SELECT m FROM Merk m")})
public class Merk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "merk")
    private String merk;
    @Size(max = 20)
    @Column(name = "distributor")
    private String distributor;
    @Size(max = 30)
    @Column(name = "alamat_dist")
    private String alamatDist;
    @Size(max = 15)
    @Column(name = "no_telp_dist")
    private String noTelpDist;
    @OneToMany(mappedBy = "idMerk")
    private List<Barang> barangList;

    public Merk() {
    }

    public Merk(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getAlamatDist() {
        return alamatDist;
    }

    public void setAlamatDist(String alamatDist) {
        this.alamatDist = alamatDist;
    }

    public String getNoTelpDist() {
        return noTelpDist;
    }

    public void setNoTelpDist(String noTelpDist) {
        this.noTelpDist = noTelpDist;
    }

    public List<Barang> getBarangList() {
        return barangList;
    }

    public void setBarangList(List<Barang> barangList) {
        this.barangList = barangList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Merk)) {
            return false;
        }
        Merk other = (Merk) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tb.entity.Merk[ id=" + id + " ]";
    }
    
}
