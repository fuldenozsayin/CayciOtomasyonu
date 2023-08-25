public interface IdosyaIslemler {
    void kullaniciCekme(String ad,String pass);
    void thesapOzeti();
    void kullaniciKaydi(String ad, String pass, String pass2);
    void hesapOzet(String ad);
    boolean kontrol(String ad, String pass);
    void siparisEkle(String ad, int secim);
    void siparisListesi();
    void listeyiDuzenle();
    void sirala();

}
