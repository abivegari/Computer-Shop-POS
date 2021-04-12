/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.tb.entity.Jabatan;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
@Named(value = "beanJabatan")
@ApplicationScoped
public class beanJabatan implements Serializable {

    Jabatan jabatan;
    List<Jabatan> listJabatan = new ArrayList<>();

    int id_jabatan;
    String nama_jabatan;

    EntityManager em;
    EntityManagerFactory emf;

    /**
     * Creates a new instance of beanJabatan
     */
    public beanJabatan() {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();
        resetJabatan();
    }

    public void setListJabatan(ArrayList<Jabatan> listJabatan) {
        this.listJabatan = listJabatan;
    }

    public Jabatan getJabatan() {
        return jabatan;
    }

    public void setJabatan(Jabatan jabatan) {
        this.jabatan = jabatan;
    }

    public int getId_jabatan() {
        return id_jabatan;
    }

    public void setId_jabatan(int id_jabatan) {
        this.id_jabatan = id_jabatan;
    }

    public String getNama_jabatan() {
        return nama_jabatan;
    }

    public void setNama_jabatan(String nama_jabatan) {
        this.nama_jabatan = nama_jabatan;
    }

    public List<Jabatan> getListJabatan() {
        return listJabatan;
    }

    public void setListJabatan(List<Jabatan> listJabatan) {
        this.listJabatan = listJabatan;
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

    public void addJabatan() {
        FacesMessage msg = new FacesMessage("Jabatan berhasil ditambah");
        com.tb.entity.Jabatan j = new Jabatan();
        j.setNama(nama_jabatan);
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(j);
        tx.commit();
        resetJabatan();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deleteJabatan(Jabatan jabatan) {
        FacesMessage msg = new FacesMessage("Jabatan berhasil dihapus");
        com.tb.entity.Jabatan j = em.find(com.tb.entity.Jabatan.class, jabatan.getId());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(j);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Jabatan berhasil diedit");
        com.tb.entity.Jabatan j = new Jabatan();
        j.setId(((Jabatan) event.getObject()).getId());
        j.setNama(((Jabatan) event.getObject()).getNama());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(j);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit dicancel");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void resetJabatan() {
        jabatan = new Jabatan();
        nama_jabatan = "";
    }

    public void selectAll() {
        Query q1 = em.createQuery("SELECT j FROM Jabatan j");
        listJabatan = q1.getResultList();
    }
}
