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

/**
 *
 * @author abi_v
 */
@Entity
@Table(name = "detail_penjualan")
@NamedQueries({
    @NamedQuery(name = "DetailPenjualan.findAll", query = "SELECT d FROM DetailPenjualan d")})
public class DetailPenjualan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "jumlah")
    private Integer jumlah;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "tgl_batas_garansi")
    @Temporal(TemporalType.DATE)
    private Date tglBatasGaransi;
    @JoinColumn(name = "id_penjualan", referencedColumnName = "id")
    @ManyToOne
    private Penjualan idPenjualan;
    @JoinColumn(name = "id_barang", referencedColumnName = "id")
    @ManyToOne
    private Barang idBarang;

    public DetailPenjualan() {
    }

    public DetailPenjualan(Integer jumlah, Double harga, Barang idBarang) {
        this.jumlah = jumlah;
        this.harga = harga;
        this.idBarang = idBarang;
    }

    public DetailPenjualan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Date getTglBatasGaransi() {
        return tglBatasGaransi;
    }

    public void setTglBatasGaransi(Date tglBatasGaransi) {
        this.tglBatasGaransi = tglBatasGaransi;
    }

    public Penjualan getIdPenjualan() {
        return idPenjualan;
    }

    public void setIdPenjualan(Penjualan idPenjualan) {
        this.idPenjualan = idPenjualan;
    }

    public Barang getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(Barang idBarang) {
        this.idBarang = idBarang;
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
        if (!(object instanceof DetailPenjualan)) {
            return false;
        }
        DetailPenjualan other = (DetailPenjualan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tb.entity.DetailPenjualan[ id=" + id + " ]";
    }

}
