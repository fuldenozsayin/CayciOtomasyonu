
import java.util.Scanner;
public class Giris implements IGiris {
    Scanner sn= new Scanner(System.in);
    public String ad;
    public String pass;
    public Giris(String ad, String pass) {
        this.ad=ad;
        this.pass=pass;
    }
    @Override
    public void girisYap(String ad, String pass) {

        //Adminle giriş yapma
        if (ad.equals(pass) && ad.equals("admin")){
            System.out.println("1. Toplam Hesap Özeti");
            System.out.println("2. Sipariş Listesini Düzenle");
            System.out.println("3. Hesap Özetini Sırala");
            System.out.println("4. Üst Menüye Dön");
            System.out.print("Yapmak istediğiniz işlemi seçiniz: ");
            int secim = sn.nextInt();

            if (secim == 1) {//Toplam hesap özeti
                dosyaIslemler d=new dosyaIslemler();
                d.thesapOzeti();
            }
            else if (secim == 2) {//Üst menüye dön
                dosyaIslemler me = new dosyaIslemler();
                me.listeyiDuzenle();
                me.siparisListesi();
            }
            else if (secim == 3) {//Sipariş Listesini Düzenle
                dosyaIslemler s=new dosyaIslemler();
                s.sirala();
            }
            else if (secim == 4) {//Sipariş Listesini Düzenle
                Main me = new Main();
                me.menu();
            }
        }
        //Kullanıcı giriş yapma
        else {
            dosyaIslemler a=new dosyaIslemler();
            a.kullaniciCekme(ad, pass);
        }
    }
}
