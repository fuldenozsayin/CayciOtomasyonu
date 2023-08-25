public class Kayit implements IKayit {
    public String ad;
    public String pass;
    public String pass2;

    public Kayit(String ad, String pass, String pass2) {
        this.ad = ad;
        this.pass = pass;
        this.pass2 = pass2;
    }
    @Override
    public void kayitOl(String ad, String pass, String pass2) {
        if(pass.equals(pass2)){
            dosyaIslemler d=new dosyaIslemler();
            d.kullaniciKaydi(ad, pass, pass2);
        }
        else{
            System.out.println("Şifreler eşleşmiyor.Lütfen tekrar deneyiniz.\n");
            Main m=new Main();
            m.menu();
        }
    }

}
