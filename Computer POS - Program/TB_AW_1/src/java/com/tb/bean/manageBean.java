/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.sun.faces.taglib.jsf_core.SelectItemsTag;
import com.tb.entity.Pegawai;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.faces.model.SelectItem;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author abi_v
 */
@Named(value = "manageBean")
@ApplicationScoped
public class manageBean implements Serializable {

    Pegawai pegawai;
    List<Pegawai> listPegawai = new ArrayList<>();
    List<Pegawai> PegawaiLogin = new ArrayList<>();
    String username;
    String password;
    String nama;
    EntityManagerFactory emf;
    EntityManager em;

    public manageBean() {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();
        Query q1 = em.createQuery("Select p from Pegawai p");
        listPegawai = q1.getResultList();
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

    public Pegawai getPegawai() {
        return pegawai;
    }

    public void setPegawai(Pegawai pegawai) {
        this.pegawai = pegawai;
    }

    public List<Pegawai> getListPegawai() {
        return listPegawai;
    }

    public void setListPegawai(List<Pegawai> listPegawai) {
        this.listPegawai = listPegawai;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public List<Pegawai> getPegawaiLogin() {
        return PegawaiLogin;
    }

    public void setPegawaiLogin(List<Pegawai> PegawaiLogin) {
        this.PegawaiLogin = PegawaiLogin;
    }

    public String login() {
        boolean user = false;
        boolean admin = false;
        boolean kasir = false;
        boolean gudang = false;
        for (Pegawai pxx : listPegawai) {
            if (String.valueOf(username).equalsIgnoreCase(pxx.getUsername()) && String.valueOf(password).equals(pxx.getPassword()) && pxx.getIdJabatan().getNama().toString().equals("Admin")) {
                user = true;
                admin = true;
                nama = pxx.getNamaDepan() + " " + pxx.getNamaBelakang();
                break;
            } else if (String.valueOf(username).equalsIgnoreCase(pxx.getUsername()) && String.valueOf(password).equals(pxx.getPassword()) && pxx.getIdJabatan().getNama().toString().equals("Kasir")) {
                user = true;
                kasir = true;
                nama = pxx.getNamaDepan() + " " + pxx.getNamaBelakang();
                break;
            } else if (String.valueOf(username).equalsIgnoreCase(pxx.getUsername()) && String.valueOf(password).equals(pxx.getPassword()) && pxx.getIdJabatan().getNama().toString().equals("Gudang")) {
                user = true;
                gudang = true;
                nama = pxx.getNamaDepan() + " " + pxx.getNamaBelakang();
                break;
            } else {
                user = false;
            }
        };

        if (username.equalsIgnoreCase("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username harus diisi!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (password.equalsIgnoreCase("")) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Password harus diisi!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (user == true && admin == true) {
            bertugas();
            return "home_admin.xhtml";
        } else if (user == true && kasir == true) {
            bertugas();
            return "home_kasir.xhtml";
        } else if (user == true && gudang == true) {
            bertugas();
            return "home_logistik.xhtml";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Username/Password Salah!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public String bertugas() {
        Query q = em.createQuery("Select p from Pegawai p Where p.username like '%" + username + "%'");
        PegawaiLogin = q.getResultList();
        return null;
    }

}
