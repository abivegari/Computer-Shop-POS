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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "barang")
@NamedQueries({
    @NamedQuery(name = "Barang.findAll", query = "SELECT b FROM Barang b")})
public class Barang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "nama")
    private String nama;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "harga")
    private Double harga;
    @Column(name = "stok")
    private Integer stok;
    @Size(max = 20)
    @Column(name = "lama_garansi")
    private String lamaGaransi;
    @JoinColumn(name = "id_kategori", referencedColumnName = "id")
    @ManyToOne
    private Kategori idKategori;
    @JoinColumn(name = "id_merk", referencedColumnName = "id")
    @ManyToOne
    private Merk idMerk;
    @OneToMany(mappedBy = "idBarang")
    private List<DetailPenjualan> detailPenjualanList;

    public Barang() {
    }

    public Barang(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getLamaGaransi() {
        return lamaGaransi;
    }

    public void setLamaGaransi(String lamaGaransi) {
        this.lamaGaransi = lamaGaransi;
    }

    public Kategori getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Kategori idKategori) {
        this.idKategori = idKategori;
    }

    public Merk getIdMerk() {
        return idMerk;
    }

    public void setIdMerk(Merk idMerk) {
        this.idMerk = idMerk;
    }

    public List<DetailPenjualan> getDetailPenjualanList() {
        return detailPenjualanList;
    }

    public void setDetailPenjualanList(List<DetailPenjualan> detailPenjualanList) {
        this.detailPenjualanList = detailPenjualanList;
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
        if (!(object instanceof Barang)) {
            return false;
        }
        Barang other = (Barang) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tb.entity.Barang[ id=" + id + " ]";
    }
    
}
