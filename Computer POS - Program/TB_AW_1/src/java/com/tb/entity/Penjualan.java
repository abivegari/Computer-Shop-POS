/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author abi_v
 */
@Entity
@Table(name = "penjualan")
@NamedQueries({
    @NamedQuery(name = "Penjualan.findAll", query = "SELECT p FROM Penjualan p")})
public class Penjualan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tgl_penjualan")
    @Temporal(TemporalType.DATE)
    private Date tglPenjualan;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total")
    private Double total;
    @OneToMany(mappedBy = "idPenjualan")
    private List<DetailPenjualan> detailPenjualanList;
    @JoinColumn(name = "id_pegawai", referencedColumnName = "id")
    @ManyToOne
    private Pegawai idPegawai;
    @OneToMany(mappedBy = "idPenjualan")
    private List<BarangCacat> barangCacatList;

    public Penjualan() {
    }

    public Penjualan(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTglPenjualan() {
        return tglPenjualan;
    }

    public void setTglPenjualan(Date tglPenjualan) {
        this.tglPenjualan = tglPenjualan;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetailPenjualan> getDetailPenjualanList() {
        return detailPenjualanList;
    }

    public void setDetailPenjualanList(List<DetailPenjualan> detailPenjualanList) {
        this.detailPenjualanList = detailPenjualanList;
    }

    public Pegawai getIdPegawai() {
        return idPegawai;
    }

    public void setIdPegawai(Pegawai idPegawai) {
        this.idPegawai = idPegawai;
    }

    public List<BarangCacat> getBarangCacatList() {
        return barangCacatList;
    }

    public void setBarangCacatList(List<BarangCacat> barangCacatList) {
        this.barangCacatList = barangCacatList;
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
        if (!(object instanceof Penjualan)) {
            return false;
        }
        Penjualan other = (Penjualan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tb.entity.Penjualan[ id=" + id + " ]";
    }
    
}
