/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tb.bean;

import com.tb.entity.Barang;
import com.tb.entity.DetailPenjualan;
import com.tb.entity.Pegawai;
import com.tb.entity.Penjualan;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author abi_v
 */
@Named(value = "beanDetailPenjualan")
@ApplicationScoped
public class beanDetailPenjualan implements Serializable {

    Pegawai selectedPegawai;
    DetailPenjualan detailPenjualan;
    List<DetailPenjualan> listDetailPenjualanSementara = new ArrayList<>();
    List<DetailPenjualan> listDetailPenjualan = new ArrayList<>();
    int id_detail;
    int jumlah;
    Double harga;
    Double total;
    Date tglBatas;
    Date tglPenjualan;
    String lama_garansi;
    Penjualan penjualan;
    List<Penjualan> listPenjualan = new ArrayList<>();
    List<Pegawai> listPegawai = new ArrayList<>();
    Barang barang;
    Barang selectedBarang;
    List<Barang> listBarang = new ArrayList<>();
    List<Barang> filteredBarang;
    EntityManager em;
    EntityManagerFactory emf;
    Double jumlahBayar;
    Double kembalian;

    /**
     * Creates a new instance of beanDetailPenjualan
     */
    public beanDetailPenjualan() {
        emf = Persistence.createEntityManagerFactory("TB_AW_1PU");
        em = emf.createEntityManager();
        reset();
    }

    public DetailPenjualan getDetailPenjualan() {
        return detailPenjualan;
    }

    public void setDetailPenjualan(DetailPenjualan detailPenjualan) {
        this.detailPenjualan = detailPenjualan;
    }

    public Penjualan getPenjualan() {
        return penjualan;
    }

    public void setPenjualan(Penjualan penjualan) {
        this.penjualan = penjualan;
    }

    public Barang getBarang() {
        return barang;
    }

    public void setBarang(Barang barang) {
        this.barang = barang;
    }

    public int getId_detail() {
        return id_detail;
    }

    public void setId_detail(int id_detail) {
        this.id_detail = id_detail;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public Date getTglBatas() {
        return tglBatas;
    }

    public void setTglBatas(Date tglBatas) {
        this.tglBatas = tglBatas;
    }

    public String getLama_garansi() {
        return lama_garansi;
    }

    public void setLama_garansi(String lama_garansi) {
        this.lama_garansi = lama_garansi;
    }

    public List<DetailPenjualan> getListDetailPenjualan() {
        return listDetailPenjualan;
    }

    public void setListDetailPenjualan(List<DetailPenjualan> listDetailPenjualan) {
        this.listDetailPenjualan = listDetailPenjualan;
    }

    public List<Penjualan> getListPenjualan() {
        return listPenjualan;
    }

    public void setListPenjualan(List<Penjualan> listPenjualan) {
        this.listPenjualan = listPenjualan;
    }

    public List<Barang> getListBarang() {
        return listBarang;
    }

    public void setListBarang(List<Barang> listBarang) {
        this.listBarang = listBarang;
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

    public List<DetailPenjualan> getListDetailPenjualanSementara() {
        return listDetailPenjualanSementara;
    }

    public void setListDetailPenjualanSementara(List<DetailPenjualan> listDetailPenjualanSementara) {
        this.listDetailPenjualanSementara = listDetailPenjualanSementara;
    }

    public Barang getSelectedBarang() {
        return selectedBarang;
    }

    public void setSelectedBarang(Barang selectedBarang) {
        this.selectedBarang = selectedBarang;
    }

    public List<Barang> getFilteredBarang() {
        return filteredBarang;
    }

    public void setFilteredBarang(List<Barang> filteredBarang) {
        this.filteredBarang = filteredBarang;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getJumlahBayar() {
        return jumlahBayar;
    }

    public void setJumlahBayar(Double jumlahBayar) {
        this.jumlahBayar = jumlahBayar;
    }

    public Double getKembalian() {
        return kembalian;
    }

    public void setKembalian(Double kembalian) {
        this.kembalian = kembalian;
    }

    public Pegawai getSelectedPegawai() {
        return selectedPegawai;
    }

    public void setSelectedPegawai(Pegawai selectedPegawai) {
        this.selectedPegawai = selectedPegawai;
    }

    public List<Pegawai> getListPegawai() {
        return listPegawai;
    }

    public void setListPegawai(List<Pegawai> listPegawai) {
        this.listPegawai = listPegawai;
    }

    public Date getTglPenjualan() {
        return tglPenjualan;
    }

    public void setTglPenjualan(Date tglPenjualan) {
        this.tglPenjualan = tglPenjualan;
    }

    public void addPenjualan() {
        if (jumlahBayar == null) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Masukkan jumlah pembayaran!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else if (jumlahBayar < total) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Jumlah pembayaran kurang dari total", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            kembalian = (jumlahBayar - total);
            FacesMessage msg = new FacesMessage("Penjualan berhasil", "Kembalian yang harus diberikan : Rp." + kembalian);
            System.out.println(kembalian);
            tglPenjualan = new Date();
            tglPenjualan.setHours(0);

            com.tb.entity.Penjualan p = new Penjualan();

            p.setTglPenjualan(tglPenjualan);
            p.setTotal(total);

            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(p);

            p.setId(p.getId());
            p.setDetailPenjualanList(listDetailPenjualanSementara);
            p.setIdPegawai(selectedPegawai);
            em.merge(p);

            tglBatas = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(tglBatas);
            cal.add(Calendar.DATE, 7);
            tglBatas = cal.getTime();

            for (DetailPenjualan dpx : p.getDetailPenjualanList()) {
                dpx.setId(dpx.getId());
                dpx.getIdBarang().setStok(dpx.getIdBarang().getStok() - dpx.getJumlah());
                dpx.setIdPenjualan(p);
                dpx.setTglBatasGaransi(tglBatas);
                em.merge(dpx);
            }
            tx.commit();
            FacesContext.getCurrentInstance().addMessage(null, msg);
            reset();
        }
    }

    public String deleteDetail(DetailPenjualan detailPenjualan) {
        FacesMessage msg = new FacesMessage("Detail penjualan berhasil dihapus");
        listDetailPenjualanSementara.remove(detailPenjualan);
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return null;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Detail penjualan berhasil diedit");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit dicancel");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void selectAll() {
        Query q1 = em.createQuery("Select d from DetailPenjualan d");
        Query q2 = em.createQuery("Select p from Penjualan p");
        Query q3 = em.createQuery("Select b from Barang b");
        Query q4 = em.createQuery("Select g from Pegawai g");
        listDetailPenjualan = q1.getResultList();
        listPenjualan = q2.getResultList();
        listBarang = q3.getResultList();
        listPegawai = q4.getResultList();
    }

    public void addToCart() {
        boolean sama = false;
        for (DetailPenjualan dxx : listDetailPenjualanSementara) {
            if (selectedBarang.getId() == dxx.getIdBarang().getId()) {
                sama = true;
                break;

            } else {
                sama = false;
            }
        };
        if (sama == true) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Barang Tidak Boleh Sama!", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            if (jumlah == 0) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Jumlah Tidak Boleh 0!", "");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } else {
                harga = (jumlah) * (selectedBarang.getHarga());
                listDetailPenjualanSementara.add(new DetailPenjualan(jumlah, harga, selectedBarang));
                total += harga;
            }

        }
    }

    public void reset() {
        penjualan = new Penjualan();
        detailPenjualan = new DetailPenjualan();
        selectedBarang = new Barang();
        total = 0d;
        kembalian = 0d;
        jumlah = 0;
        jumlahBayar = 0d;
        listDetailPenjualanSementara.clear();
    }
}
