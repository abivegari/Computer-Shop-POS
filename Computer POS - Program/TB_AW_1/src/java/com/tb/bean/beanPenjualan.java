/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.tb.entity.Penjualan;
import com.tb.entity.Barang;
import com.tb.entity.DetailPenjualan;
import com.tb.entity.Pegawai;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import sun.security.util.PendingException;

/**
 *
 * @author abi_v
 */
@Named(value = "beanPenjualan")
@ApplicationScoped
public class beanPenjualan implements Serializable {

    Penjualan penjualan;
    Penjualan selectedPenjualan;
    List<Penjualan> listPenjualan = new ArrayList<>();
    Integer id_penjualan;
    Double total;
    Date tglPenjualan;
    Pegawai pegawai;
    EntityManager em;
    EntityManagerFactory emf;
    List<DetailPenjualan> listDetailPenjualan = new ArrayList<>();

    /**
     * Creates a new instance of beanPenjualan
     */
    public beanPenjualan() throws ParseException {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();

        Query q2 = em.createQuery("Select d from DetailPenjualan d");
        listDetailPenjualan = q2.getResultList();
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    public Integer getId_penjualan() {
        return id_penjualan;
    }

    public void setId_penjualan(Integer id_penjualan) {
        this.id_penjualan = id_penjualan;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getTglPenjualan() {
        return tglPenjualan;
    }

    public void setTglPenjualan(Date tglPenjualan) {
        this.tglPenjualan = tglPenjualan;
    }

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public List<Penjualan> getListPenjualan() {
        return listPenjualan;
    }

    public void setListPenjualan(List<Penjualan> listPenjualan) {
        this.listPenjualan = listPenjualan;
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

    public Penjualan getSelectedPenjualan() {
        return selectedPenjualan;
    }

    public void setSelectedPenjualan(Penjualan selectedPenjualan) {
        this.selectedPenjualan = selectedPenjualan;
    }

    public List<DetailPenjualan> getListDetailPenjualan() {
        return listDetailPenjualan;
    }

    public void setListDetailPenjualan(List<DetailPenjualan> listDetailPenjualan) {
        this.listDetailPenjualan = listDetailPenjualan;
    }

    public void selectAll() {
        Query q1 = em.createQuery("Select p from Penjualan p");
        listPenjualan = q1.getResultList();
    }

    public void selectAll2() {
        Query q1 = em.createQuery("Select p from Penjualan p");
        Query q2 = em.createQuery("Select d from DetailPenjualan d");
        listPenjualan = q1.getResultList();
        listDetailPenjualan = q2.getResultList();
    }

    public void onRowSelect(SelectEvent event) {
        listDetailPenjualan.clear();
        Query q2 = em.createQuery("Select d from DetailPenjualan d where d.idPenjualan = " + ((Penjualan) event.getObject()).getId() + "");
        listDetailPenjualan = q2.getResultList();
    }

    public void onRowUnselect(UnselectEvent event) {
    }

    public void select() {

    }
}
