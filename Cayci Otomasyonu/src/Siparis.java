import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Siparis implements ISiparis {
    Scanner sn= new Scanner(System.in);
    String ad;
    public void siparisSec(String ad){
        this.ad=ad;
        dosyaIslemler d=new dosyaIslemler();
        d.siparisListesi();
        int i = d.satirSayisiniGetir("hesap.txt");
        System.out.println((i-1)+"- Çıkış");
        System.out.print("Sipariş numarasını giriniz: ");
        int secim = sn.nextInt();
        d.siparisEkle(ad,secim);
    }

}
