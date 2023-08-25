import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    Scanner sn= new Scanner(System.in);
    public int kontrol = 0;
    public String ad1;
    public String pass1;
    public static void main(String[] args) {
        Main m=new Main();
        m.menu();
    }
    public void menu(){

        System.out.println("Çaycı Sistemine Hoş Geldiniz");

        System.out.println("1 Kullanıcı Girişi");
        System.out.println("2 Kayıt Ol");
        System.out.println("3 Çıkış Yap");

        System.out.print("Yapmak istediğiniz işlemi seçiniz: ");
        int secim = sn.nextInt();

        if(secim==1){

            System.out.print("Kullanıcı Adı: ");
            ad1=sn.next();

            System.out.print("Şifre: ");
            pass1=sn.next();

            Giris giris1=new Giris(ad1,pass1);
            giris1.girisYap(ad1,pass1);
        }
        else if (secim==2) {
            secim2();
        }
        else if (secim==3){
            System.exit(0);
        }
    }
    public void secim2(){
        System.out.print("Kullanıcı Adı: ");
        String ad = sn.next();

        String filePath = "kullanici.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ad)) {
                    kontrol = 1;
                    System.out.println("Kullanıcı adı mevcut, farklı kullanıcı adı giriniz!");
                    secim2();
                }
            }
            System.out.print("Şifre: ");
            String pass = sn.next();

            System.out.print("Şifre (Tekrar): ");
            String pass2 = sn.next();

            Kayit kayit = new Kayit(ad,pass,pass2);
            kayit.kayitOl(ad, pass, pass2);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
