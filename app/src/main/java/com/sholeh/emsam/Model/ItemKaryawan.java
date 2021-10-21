package com.sholeh.emsam.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ItemKaryawan {
    @SerializedName("id_user")
    @Expose
    private String idUser;
    @SerializedName("nama_pekerja")
    @Expose
    private String namaPekerja;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("id_jabatan")
    @Expose
    private String idJabatan;
    @SerializedName("tgl_mulaitugas")
    @Expose
    private String tglMulaitugas;
    @SerializedName("ttl")
    @Expose
    private String ttl;
    @SerializedName("kartu_pengenal")
    @Expose
    private String kartuPengenal;
    @SerializedName("nomor_pengenal")
    @Expose
    private String nomorPengenal;
    @SerializedName("agama")
    @Expose
    private String agama;
    @SerializedName("jenis_kelamin")
    @Expose
    private String jenisKelamin;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("level")
    @Expose
    private String level;
    @SerializedName("pendidikan")
    @Expose
    private String pendidikan;
    @SerializedName("keterampilan")
    @Expose
    private String keterampilan;
    @SerializedName("no_bpjskesehatan")
    @Expose
    private String noBpjskesehatan;
    @SerializedName("no_bpjsketenagakerjaan")
    @Expose
    private String noBpjsketenagakerjaan;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNamaPekerja() {
        return namaPekerja;
    }

    public void setNamaPekerja(String namaPekerja) {
        this.namaPekerja = namaPekerja;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdJabatan() {
        return idJabatan;
    }

    public void setIdJabatan(String idJabatan) {
        this.idJabatan = idJabatan;
    }

    public String getTglMulaitugas() {
        return tglMulaitugas;
    }

    public void setTglMulaitugas(String tglMulaitugas) {
        this.tglMulaitugas = tglMulaitugas;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public String getKartuPengenal() {
        return kartuPengenal;
    }

    public void setKartuPengenal(String kartuPengenal) {
        this.kartuPengenal = kartuPengenal;
    }

    public String getNomorPengenal() {
        return nomorPengenal;
    }

    public void setNomorPengenal(String nomorPengenal) {
        this.nomorPengenal = nomorPengenal;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getKeterampilan() {
        return keterampilan;
    }

    public void setKeterampilan(String keterampilan) {
        this.keterampilan = keterampilan;
    }

    public String getNoBpjskesehatan() {
        return noBpjskesehatan;
    }

    public void setNoBpjskesehatan(String noBpjskesehatan) {
        this.noBpjskesehatan = noBpjskesehatan;
    }

    public String getNoBpjsketenagakerjaan() {
        return noBpjsketenagakerjaan;
    }

    public void setNoBpjsketenagakerjaan(String noBpjsketenagakerjaan) {
        this.noBpjsketenagakerjaan = noBpjsketenagakerjaan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }
}
