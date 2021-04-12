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
@Table(name = "pegawai")
@NamedQueries({
    @NamedQuery(name = "Pegawai.findAll", query = "SELECT p FROM Pegawai p")})
public class Pegawai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "nama_depan")
    private String namaDepan;
    @Size(max = 20)
    @Column(name = "nama_belakang")
    private String namaBelakang;
    @Size(max = 15)
    @Column(name = "username")
    private String username;
    @Size(max = 15)
    @Column(name = "password")
    private String password;
    @Size(max = 30)
    @Column(name = "alamat")
    private String alamat;
    @Size(max = 15)
    @Column(name = "no_telp")
    private String noTelp;
    @OneToMany(mappedBy = "idPegawai")
    private List<Penjualan> penjualanList;
    @JoinColumn(name = "id_jabatan", referencedColumnName = "id")
    @ManyToOne
    private Jabatan idJabatan;

    public Pegawai() {
    }

    public Pegawai(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaDepan() {
        return namaDepan;
    }

    public void setNamaDepan(String namaDepan) {
        this.namaDepan = namaDepan;
    }

    public String getNamaBelakang() {
        return namaBelakang;
    }

    public void setNamaBelakang(String namaBelakang) {
        this.namaBelakang = namaBelakang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public List<Penjualan> getPenjualanList() {
        return penjualanList;
    }

    public void setPenjualanList(List<Penjualan> penjualanList) {
        this.penjualanList = penjualanList;
    }

    public Jabatan getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(Jabatan idJabatan) {
        this.idJabatan = idJabatan;
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
        if (!(object instanceof Pegawai)) {
            return false;
        }
        Pegawai other = (Pegawai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tb.entity.Pegawai[ id=" + id + " ]";
    }
    
}
