/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author abi_v
 */
@Entity
@Table(name = "barang_cacat")
@NamedQueries({
    @NamedQuery(name = "BarangCacat.findAll", query = "SELECT b FROM BarangCacat b")})
public class BarangCacat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 30)
    @Column(name = "nama_barang")
    private String namaBarang;
    @Column(name = "tgl_retur")
    @Temporal(TemporalType.DATE)
    private Date tglRetur;
    @Size(max = 25)
    @Column(name = "deskripsi")
    private String deskripsi;
    @JoinColumn(name = "id_penjualan", referencedColumnName = "id")
    @ManyToOne
    private Penjualan idPenjualan;

    public BarangCacat() {
    }

    public BarangCacat(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Date getTglRetur() {
        return tglRetur;
    }

    public void setTglRetur(Date tglRetur) {
        this.tglRetur = tglRetur;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Penjualan getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(Penjualan idPenjualan) {
        this.idPenjualan = idPenjualan;
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
        if (!(object instanceof BarangCacat)) {
            return false;
        }
        BarangCacat other = (BarangCacat) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tb.entity.BarangCacat[ id=" + id + " ]";
    }
    
}
