/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.tb.entity.Jabatan;
import com.tb.entity.Pegawai;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author abi_v
 */
@Named(value = "beanPegawai")
@ApplicationScoped
public class beanPegawai implements Serializable {

    //Pegawai
    Pegawai pegawai;
    List<Jabatan> listJabatan = new ArrayList<>();
    List<Pegawai> listPegawai = new ArrayList<>();
    int id_pegawai;
    String nama_depan;
    String nama_belakang;
    String username;
    String password;
    String alamat;
    String no_telp;
    Jabatan selectedJabatan;
    int id_jabatan;
    EntityManager em;
    EntityManagerFactory emf;

    public beanPegawai() {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();
        reset();
    }

    public int getId_pegawai() {
        return id_pegawai;
    }

    public void setId_pegawai(int id_pegawai) {
        this.id_pegawai = id_pegawai;
    }

    public String getNama_depan() {
        return nama_depan;
    }

    public void setNama_depan(String nama_depan) {
        this.nama_depan = nama_depan;
    }

    public String getNama_belakang() {
        return nama_belakang;
    }

    public void setNama_belakang(String nama_belakang) {
        this.nama_belakang = nama_belakang;
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

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public int getId_jabatan() {
        return id_jabatan;
    }

    public void setId_jabatan(int id_jabatan) {
        this.id_jabatan = id_jabatan;
    }

    public List<Jabatan> getListJabatan() {
        return listJabatan;
    }

    public void setListJabatan(List<Jabatan> listJabatan) {
        this.listJabatan = listJabatan;
    }

    public List<Pegawai> getListPegawai() {
        return listPegawai;
    }

    public void setListPegawai(List<Pegawai> listPegawai) {
        this.listPegawai = listPegawai;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Jabatan getSelectedJabatan() {
        return selectedJabatan;
    }

    public void setSelectedJabatan(Jabatan selectedJabatan) {
        this.selectedJabatan = selectedJabatan;
    }

    public String addPegawai() {
        boolean sama = false;
        for (Pegawai pxx : listPegawai) {
            if (String.valueOf(username).equalsIgnoreCase(pxx.getUsername())) {
                sama = true;
                break;

            } else {
                sama = false;
            }
        };
        if (sama == true) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username sudah ada yang pakai!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        } else {
            FacesMessage msg = new FacesMessage("Pegawai berhasil ditambah");
            com.tb.entity.Pegawai p = new Pegawai();
            p.setNamaDepan(nama_depan);
            p.setNamaBelakang(nama_belakang);
            p.setAlamat(alamat);
            p.setNoTelp(no_telp);
            p.setUsername(username);
            p.setPassword(password);
            p.setIdJabatan(selectedJabatan);

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(p);
            tx.commit();
            FacesContext.getCurrentInstance().addMessage(null, msg);
            reset();
            return null;
        }

    }

    public String deletePegawai(Pegawai pegawai) {
        FacesMessage msg = new FacesMessage("Pegawai berhasil dihapus");
        com.tb.entity.Pegawai p = em.find(com.tb.entity.Pegawai.class, pegawai.getId());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(p);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Pegawai berhasil diedit");
        com.tb.entity.Pegawai p = new Pegawai();
        p.setId(((Pegawai) event.getObject()).getId());
        p.setNamaDepan(((Pegawai) event.getObject()).getNamaDepan());
        p.setNamaBelakang(((Pegawai) event.getObject()).getNamaBelakang());
        p.setAlamat(((Pegawai) event.getObject()).getAlamat());
        p.setNoTelp(((Pegawai) event.getObject()).getNoTelp());
        p.setUsername(((Pegawai) event.getObject()).getUsername());
        p.setPassword(((Pegawai) event.getObject()).getPassword());
        p.setIdJabatan(((Pegawai) event.getObject()).getIdJabatan());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(p);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit dicancel");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void selectAll() {
        Query q1 = em.createQuery("SELECT j FROM Jabatan j");
        Query q2 = em.createQuery("SELECT p FROM Pegawai p");
        listJabatan = q1.getResultList();
        listPegawai = q2.getResultList();
    }
    
    public void reset(){
        selectedJabatan = new Jabatan();
        pegawai = new Pegawai();
        nama_depan = "";
        nama_belakang = "";
        username = "";
        password = "";
        no_telp = "";
        alamat = "";
    }
}
