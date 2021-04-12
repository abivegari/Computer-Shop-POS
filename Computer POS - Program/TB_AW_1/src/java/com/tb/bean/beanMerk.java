/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.tb.entity.Merk;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author abi_v
 */
@Named(value = "beanMerk")
@ApplicationScoped
public class beanMerk implements Serializable {

    Merk merk;
    List<Merk> listMerk = new ArrayList<>();
    String nama_merk;
    String distributor;
    String alamat_distri;
    String no_telp_distri;
    EntityManager em;
    EntityManagerFactory emf;

    /**
     * Creates a new instance of beanMerk
     */
    public beanMerk() {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();
        reset();
    }

    public Merk getMerk() {
        return merk;
    }

    public void setMerk(Merk merk) {
        this.merk = merk;
    }

    public String getNama_merk() {
        return nama_merk;
    }

    public void setNama_merk(String nama_merk) {
        this.nama_merk = nama_merk;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public String getAlamat_distri() {
        return alamat_distri;
    }

    public void setAlamat_distri(String alamat_distri) {
        this.alamat_distri = alamat_distri;
    }

    public String getNo_telp_distri() {
        return no_telp_distri;
    }

    public void setNo_telp_distri(String no_telp_distri) {
        this.no_telp_distri = no_telp_distri;
    }

    public List<Merk> getListMerk() {
        return listMerk;
    }

    public void setListMerk(List<Merk> listMerk) {
        this.listMerk = listMerk;
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

    public void addMerk() {
        FacesMessage msg = new FacesMessage("Merk berhasil ditambah");
        com.tb.entity.Merk m = new Merk();
        m.setMerk(nama_merk);
        m.setDistributor(distributor);
        m.setAlamatDist(alamat_distri);
        m.setNoTelpDist(no_telp_distri);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(m);
        tx.commit();
        reset();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String deleteMerk(Merk merk) {
        FacesMessage msg = new FacesMessage("Merk berhasil dihapus");
        com.tb.entity.Merk m = em.find(com.tb.entity.Merk.class, merk.getId());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(m);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Merk berhasil diedit");
        com.tb.entity.Merk m = new Merk();
        m.setId(((Merk) event.getObject()).getId());
        m.setMerk(((Merk) event.getObject()).getMerk());
        m.setDistributor(((Merk) event.getObject()).getDistributor());
        m.setAlamatDist(((Merk) event.getObject()).getAlamatDist());
        m.setNoTelpDist(((Merk) event.getObject()).getNoTelpDist());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(m);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit dicancel");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void selectAll() {
        Query q = em.createQuery("SELECT m FROM Merk m");
        listMerk = q.getResultList();
    }

    public void reset() {
        merk = new Merk();
        nama_merk = "";
        distributor = "";
        alamat_distri = "";
        no_telp_distri = "";
    }
}
