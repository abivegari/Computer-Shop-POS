/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.tb.entity.Kategori;
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
@Named(value = "beanKategori")
@ApplicationScoped
public class beanKategori implements Serializable {

    Kategori kategori;
    List<Kategori> listKategori = new ArrayList<>();
    String namaKategori;
    EntityManager em;
    EntityManagerFactory emf;

    /**
     * Creates a new instance of beanKategori
     */
    public beanKategori() {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();
        reset();
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public List<Kategori> getListKategori() {
        return listKategori;
    }

    public void setListKategori(List<Kategori> listKategori) {
        this.listKategori = listKategori;
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

    public void addKategori() {
        FacesMessage msg = new FacesMessage("Kategori berhasil ditambah");
        com.tb.entity.Kategori k = new Kategori();
        k.setNama(namaKategori);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(k);
        tx.commit();
        reset();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteKategori(Kategori kategori) {
        FacesMessage msg = new FacesMessage("Kategori berhasil dihapus");
        com.tb.entity.Kategori k = em.find(com.tb.entity.Kategori.class, kategori.getId());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(k);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Kategori berhasil diedit");
        com.tb.entity.Kategori k = new Kategori();
        k.setId(((Kategori) event.getObject()).getId());
        k.setNama(((Kategori) event.getObject()).getNama());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(k);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit dicancel");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void selectAll() {
        Query q = em.createQuery("SELECT k FROM Kategori k");
        listKategori = q.getResultList();
    }

    public void reset() {
        kategori = new Kategori();
        namaKategori = "";
    }
}
