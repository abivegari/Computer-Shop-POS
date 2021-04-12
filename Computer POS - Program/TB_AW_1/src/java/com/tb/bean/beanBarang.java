/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.tb.entity.Barang;
import com.tb.entity.Kategori;
import com.tb.entity.Merk;
import javax.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
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
@Named(value = "beanBarang")
@ApplicationScoped
public class beanBarang implements Serializable {

    Barang barang;
    List<Barang> listBarang = new ArrayList<>();
    int id_barang;
    String nama_barang;
    String lama_garansi;
    int stok;
    Double harga;
    List<Kategori> listKategori = new ArrayList<>();
    Kategori selectedKategori;
    List<Merk> listMerk = new ArrayList<>();
    Merk selectedMerk;
    EntityManager em;
    EntityManagerFactory emf;

    /**
     * Creates a new instance of beanBarang
     */
    public beanBarang() {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();
        reset();
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getLama_garansi() {
        return lama_garansi;
    }

    public void setLama_garansi(String lama_garansi) {
        this.lama_garansi = lama_garansi;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public List<Barang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(List<Barang> listBarang) {
        this.listBarang = listBarang;
    }

    public List<Kategori> getListKategori() {
        return listKategori;
    }

    public void setListKategori(List<Kategori> listKategori) {
        this.listKategori = listKategori;
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

    public Kategori getSelectedKategori() {
        return selectedKategori;
    }

    public void setSelectedKategori(Kategori selectedKategori) {
        this.selectedKategori = selectedKategori;
    }

    public Merk getSelectedMerk() {
        return selectedMerk;
    }

    public void setSelectedMerk(Merk selectedMerk) {
        this.selectedMerk = selectedMerk;
    }

    public String addBarang() {
        FacesMessage msg = new FacesMessage("Barang berhasil ditambah");
        com.tb.entity.Barang b = new Barang();
        b.setNama(nama_barang);
        b.setHarga(harga);
        b.setStok(stok);
        b.setLamaGaransi(lama_garansi);
        b.setIdKategori(selectedKategori);
        b.setIdMerk(selectedMerk);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(b);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
        reset();
        return null;
    }

    public String deleteBarang(Barang barang) {
        FacesMessage msg = new FacesMessage("Barang berhasil dihapus");
        com.tb.entity.Barang b = em.find(com.tb.entity.Barang.class, barang.getId());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.remove(b);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Barang berhasil diedit");
        com.tb.entity.Barang b = new Barang();
        b.setId(((Barang) event.getObject()).getId());
        b.setNama(((Barang) event.getObject()).getNama());
        b.setHarga(((Barang) event.getObject()).getHarga());
        b.setStok(((Barang) event.getObject()).getStok());
        b.setLamaGaransi(((Barang) event.getObject()).getLamaGaransi());
        b.setIdKategori(((Barang) event.getObject()).getIdKategori());
        b.setIdMerk(((Barang) event.getObject()).getIdMerk());
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(b);
        tx.commit();
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit dicancel");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void selectAll() {
        Query q1 = em.createQuery("SELECT k FROM Kategori k");
        Query q2 = em.createQuery("SELECT m FROM Merk m");
        Query q3 = em.createQuery("SELECT b FROM Barang b");
        listKategori = q1.getResultList();
        listMerk = q2.getResultList();
        listBarang = q3.getResultList();
    }

    public void reset() {
        barang = new Barang();
        selectedKategori = new Kategori();
        selectedMerk = new Merk();
        nama_barang = "";
        lama_garansi = "";
        stok = 0;
        harga = 0d;
    }
}
