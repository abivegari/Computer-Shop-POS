/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.tb.entity.BarangCacat;
import com.tb.entity.Penjualan;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.omg.CORBA.BAD_CONTEXT;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author abi_v
 */
@Named(value = "beanBarangCacat")
@ApplicationScoped
public class beanBarangCacat implements Serializable {

    BarangCacat barangCacat;
    List<BarangCacat> listBarangCacat = new ArrayList<>();
    Integer id;
    String nama;
    Date tglRetur;
    String deskripsi;
    Penjualan selectedPenjualan;
    EntityManager em;
    EntityManagerFactory emf;

    /**
     * Creates a new instance of beanBarangCacat
     */
    public beanBarangCacat() throws ParseException {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();
        id = -1;
        reset();
    }

    public BarangCacat getBarangCacat() {
        return barangCacat;
    }

    public void setBarangCacat(BarangCacat barangCacat) {
        this.barangCacat = barangCacat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Penjualan getSelectedPenjualan() {
        return selectedPenjualan;
    }

    public void setSelectedPenjualan(Penjualan selectedPenjualan) {
        this.selectedPenjualan = selectedPenjualan;
    }

    public List<BarangCacat> getListBarangCacat() {
        return listBarangCacat;
    }

    public void setListBarangCacat(List<BarangCacat> listBarangCacat) {
        this.listBarangCacat = listBarangCacat;
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

    public String addBarangCacat() {
        if (id == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Masukkan ID Penjualan!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
            System.out.println(id);
        } else {
            tglRetur = new Date();
            tglRetur.setHours(0);
            FacesMessage msg = new FacesMessage("Barang Cacat berhasil ditambah");
            com.tb.entity.BarangCacat bc = new BarangCacat();
            bc.setNamaBarang(nama);
            bc.setDeskripsi(deskripsi);
            bc.setTglRetur(tglRetur);
            bc.setIdPenjualan(selectedPenjualan);
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(bc);
            tx.commit();
            FacesContext.getCurrentInstance().addMessage(null, msg);
            reset();
        }
        return null;
    }

    public String deleteBarangCacat(BarangCacat barangCacat) {
        FacesMessage msg = new FacesMessage("Barang Cacat berhasil dihapus");
        com.tb.entity.BarangCacat b = em.find(com.tb.entity.BarangCacat.class, barangCacat.getId());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(b);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Barang Cacat berhasil diedit");
        com.tb.entity.BarangCacat bc = new BarangCacat();
        bc.setId(((BarangCacat) event.getObject()).getId());
        bc.setNamaBarang(((BarangCacat) event.getObject()).getNamaBarang());
        bc.setDeskripsi(((BarangCacat) event.getObject()).getDeskripsi());
        bc.setTglRetur(((BarangCacat) event.getObject()).getTglRetur());
        bc.setIdPenjualan(((BarangCacat) event.getObject()).getIdPenjualan());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(bc);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit dicancel");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void selectAll() {
        Query q1 = em.createQuery("Select b from BarangCacat b");
        listBarangCacat = q1.getResultList();
    }
    
    public void setting(){
        id = selectedPenjualan.getId();
    }
    
    public void reset(){
        barangCacat = new BarangCacat();
        nama = "";
        tglRetur = new Date();
        deskripsi = "";
        selectedPenjualan = new Penjualan();
    }
}
