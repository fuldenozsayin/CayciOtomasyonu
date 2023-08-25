import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.Arrays;
class dosyaIslemler extends Main implements IdosyaIslemler {
    public String ad;
    public int secim;

    public static int satirSayisiniGetir(String dosyaYolu) {
        int satirSayisi = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
            while (reader.readLine() != null) {
                satirSayisi++;
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
        return satirSayisi;
    }

    public void thesapOzeti() {
        String filePath = "hesap.txt"; // İşlem yapmak istediğiniz dosyanın yolunu buraya girin

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            long sum = 0;

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+"); // Boşluklara göre satırı kelimelere ayırır

                if (words.length > 1) {
                    try {
                        long number = Long.parseLong(words[1]); // İkinci kelimeyi sayıya dönüştürür
                        sum += number;
                    } catch (NumberFormatException e) {
                        System.out.println("Hasılat hesaplanamadı, daha sonra tekrar deneyiniz.");
                    }
                }
            }

            System.out.println("\nToplam hasılat: " + sum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kullaniciCekme(String ad, String pass) {
        if (kontrol(ad, pass) == false) {
            System.out.println("Böyle bir kullanıcı bulunamadı! Lütfen tekrar deneyiniz\n");
            menu();
        } else {
            System.out.println("1. Sipariş ver");
            System.out.println("2. Hesap özeti");

            System.out.print("Yapmak istediğiniz işlemi seçiniz: ");
            int sec = sn.nextInt();

            if (sec == 1) {
                //menü
                Siparis s = new Siparis();
                s.siparisSec(ad);
            } else if (sec == 2) {
                hesapOzet(ad);
            }
        }

    }

    public boolean kontrol(String ad, String pass) {
        String filePath = "kullanici.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(ad) && line.contains(pass)) {
                    System.out.println("\nBaşarıyla giriş yapıldı.");
                    return true;

                }
            }
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
    }

    public void kullaniciKaydi(String ad, String pass, String pass2) {
        //Dosyaya yazma
        int i = satirSayisiniGetir("kullanici.txt");
        String ifade = i + "- " + ad + " " + pass;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("kullanici.txt", true))) {//true dediğimiz için dosyanın sonuna eklemiş oluyor
            writer.write(ifade);
            writer.newLine();
            writer.close();
            System.out.println("Başarıyla kayıt olundu.");
            int j = satirSayisiniGetir("hesap.txt");
            String ifade2 = j + "- 0 TL";
            try (BufferedWriter writer2 = new BufferedWriter(new FileWriter("hesap.txt", true))) {//true dediğimiz için dosyanın sonuna eklemiş oluyor
                writer2.write(ifade2);
                writer2.newLine();
                writer2.close();
            } catch (IOException e) {
                System.out.println("Hesap özeti şuanda gösterilemiyor! Daha sonra tekrar deneyiniz. " + e.getMessage());
            }
            hesapOzet(ad);
        } catch (IOException e) {
            System.out.println("Kayıt başarısız! Daha sonra tekrar deneyiniz. " + e.getMessage());
        }
        Main m = new Main();
        m.menu();

    }

    public void hesapOzet(String ad) {
        this.ad = ad;
        String[] lineArray;
        String[] lineArray2 = null;
        try (BufferedReader br = new BufferedReader(new FileReader("kullanici.txt")); BufferedReader br2 = new BufferedReader(new FileReader("hesap.txt"))) {
            String line;
            String line2;
            while ((line = br.readLine()) != null) {
                if (line.contains(ad)) {
                    lineArray = line.split(" ");
                    String id = lineArray[0];
                    while ((line2 = br2.readLine()) != null) {
                        lineArray2 = line2.split(" ");
                        if (line2.contains(id)) {
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Şuanda hesap özetine ulaşılamıyor. Daha sonra tekrar deneyiniz.");
        }
        System.out.println("Hesap Özeti: " + lineArray2[1]);
    }

    public void siparisEkle(String ad, int secim) {
        this.secim = secim;
        this.ad = ad;
        try (BufferedReader br = new BufferedReader(new FileReader("kullanici.txt"));
             BufferedReader br2 = new BufferedReader(new FileReader("hesap.txt"))) {
            String line;
            String line2;
            int guncel;
            int i = satirSayisiniGetir("hesap.txt");
            while ((line = br.readLine()) != null) {
                if (line.contains(ad)) {
                    String[] lineArray = line.split(" ");
                    String id = lineArray[0];
                    while ((line2 = br2.readLine()) != null) {
                        String[] lineArray2 = line2.split(" ");
                        if (line2.contains(id)) {
                            String fiyat = lineArray2[1];
                            guncel = Integer.valueOf(fiyat);
                            if (secim == 1) {
                                guncel = guncel + 5;
                            } else if (secim == 2) {
                                guncel = guncel + 10;
                            } else if (secim == 3) {
                                guncel = guncel + 8;
                            } else if (secim == 4) {
                                guncel = guncel + 15;
                            } else if (secim == 5) {
                                guncel = guncel + 3;
                            } else if (secim == 6) {
                                guncel = guncel + 5;
                            } else if (secim == i - 1) {
                                System.exit(0);
                            }
                            String guncelNew = String.valueOf(guncel);
                            System.out.println(lineArray2[1]);

                            File fileToBeModified = new File("hesap.txt");
                            String oldContent = "";
                            BufferedReader reader = null;
                            FileWriter writer = null;

                            try {
                                reader = new BufferedReader(new FileReader(fileToBeModified));

                                //Reading all the lines of input text file into oldContent
                                String line3 = reader.readLine();

                                while (line3 != null) {
                                    oldContent = oldContent + line3 + System.lineSeparator();
                                    line3 = reader.readLine();
                                }
                                //Replacing oldString with newString in the oldContent
                                String newContent = oldContent.replaceAll(fiyat, guncelNew);

                                //Rewriting the input text file with newContent
                                writer = new FileWriter(fileToBeModified);
                                writer.write(newContent);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    reader.close();
                                    writer.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Şu anda hesap özetine ulaşılamıyor. Daha sonra tekrar deneyiniz.");
        }
        System.out.println("Siparişiniz alınmıştır. ");
        hesapOzet(ad);
        Siparis s = new Siparis();
        s.siparisSec(ad);
    }

    public void siparisListesi() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("siparisListesi.txt");
            String line;

            BufferedReader br = new BufferedReader(fileReader);

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Hata10");
        } catch (IOException e) {
            System.out.println("hata9");
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Hata8");
            }
        }
    }

    public void listeyiDuzenle() {
        System.out.println("Sipariş Listesi:");
        siparisListesi();
        System.out.println("\nAşağıda yer alan işlemlerden hangisini gerçekleştirmek istiyorsunuz? ");
        System.out.println("1. Sipariş listesine ekleme yap.");
        System.out.println("2. Veriyi güncelle.");
        System.out.print("Yapmak istediğiniz işlemi giriniz: ");
        int secim = sn.nextInt();

        if (secim == 1) {
            int i = satirSayisiniGetir("siparisListesi.txt");
            System.out.print("Eklemek istediğiniz ürünün adı:  " + i + "- ");
            String ekle = sn.next();
            System.out.print("Eklemek istediğiniz ürünün fiyatı:  ");
            String ek = sn.next();
            String ifade = i + "- " + ekle + " " + ek + " TL";
            String dosyaYolu = "siparisListesi.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu, true))) {//true dediğimiz için dosyanın sonuna eklemiş oluyor
                writer.write(ifade);
                writer.newLine();
                System.out.println("Sipariş listesi başarıyla güncellendi.");
            } catch (IOException e) {
                System.out.println("Sipariş listesi güncellenemedi, daha sonra tekrar deneyiniz! " + e.getMessage());
            }
        } else if (secim == 2) {
            System.out.print("Değişiklik yapmak istediğiniz ürünün numarasını giriniz: ");
            int satir = sn.nextInt();
            System.out.print("1. ID\n2. Ürün Adı\n3. Ürün Fiyatı\n Değişiklik yapmak istediğiniz bölümün numarasını lütfen giriniz:");
            int degistir = sn.nextInt();

            if (degistir == 1) {
                try (BufferedReader br = new BufferedReader(new FileReader("siparisListesi.txt"))) {
                    String line;
                    int sayac = 0;
                    while ((line = br.readLine()) != null) {
                        sayac++;
                        String[] lineArray = line.split(" ");
                        String oldID = lineArray[0];
                        if (satir == sayac) {
                            System.out.print(satir + ". satırın yeni ID'sini giriniz:");
                            String newID = sn.next();
                            File fileToBeModified = new File("siparisListesi.txt");
                            String oldContent = "";
                            BufferedReader reader = null;
                            FileWriter writer = null;
                            try {
                                reader = new BufferedReader(new FileReader(fileToBeModified));

                                //Reading all the lines of input text file into oldContent
                                String line3 = reader.readLine();

                                while (line3 != null) {
                                    oldContent = oldContent + line3 + System.lineSeparator();
                                    line3 = reader.readLine();
                                }
                                //Replacing oldString with newString in the oldContent
                                String newContent = oldContent.replaceAll(oldID, newID + "-");

                                //Rewriting the input text file with newContent
                                writer = new FileWriter(fileToBeModified);
                                writer.write(newContent);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    reader.close();
                                    writer.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            } else if (degistir == 2) {
                String newName;
                try (BufferedReader br = new BufferedReader(new FileReader("siparisListesi.txt"))) {
                    String line;
                    int sayac = 0;
                    while ((line = br.readLine()) != null) {
                        sayac++;
                        String[] lineArray = line.split("-");
                        String oldName = lineArray[1];
                        if (satir == sayac) {
                            Scanner sn = new Scanner(System.in);
                            System.out.print(satir + ". satırın yeni ürün adını giriniz:");
                            newName = sn.nextLine();

                            File fileToBeModified = new File("siparisListesi.txt");
                            String oldContent = "";
                            BufferedReader reader = null;
                            FileWriter writer = null;
                            try {
                                reader = new BufferedReader(new FileReader(fileToBeModified));

                                //Reading all the lines of input text file into oldContent
                                String line3 = reader.readLine();

                                while (line3 != null) {
                                    oldContent = oldContent + line3 + System.lineSeparator();
                                    line3 = reader.readLine();
                                }
                                //Replacing oldString with newString in the oldContent
                                String newContent = oldContent.replaceAll(oldName, newName);

                                //Rewriting the input text file with newContent
                                writer = new FileWriter(fileToBeModified);
                                writer.write(newContent);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    reader.close();
                                    writer.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (degistir == 3) {
                try (BufferedReader br = new BufferedReader(new FileReader("siparisListesi.txt"))) {
                    String line;
                    int sayac = 0;
                    while ((line = br.readLine()) != null) {
                        sayac++;
                        String[] lineArray = line.split(" ");
                        String oldFiyat = lineArray[2];
                        if (satir == sayac) {
                            System.out.print(satir + ". satırında yer alan ürünün yeni fiyatını giriniz:");
                            String newFiyat = sn.next();
                            File fileToBeModified = new File("siparisListesi.txt");
                            String oldContent = "";
                            BufferedReader reader = null;
                            FileWriter writer = null;
                            try {
                                reader = new BufferedReader(new FileReader(fileToBeModified));

                                //Reading all the lines of input text file into oldContent
                                String line3 = reader.readLine();

                                while (line3 != null) {
                                    oldContent = oldContent + line3 + System.lineSeparator();

                                    line3 = reader.readLine();
                                }
                                //Replacing oldString with newString in the oldContent
                                String newContent = oldContent.replaceAll(oldFiyat, newFiyat);

                                //Rewriting the input text file with newContent
                                writer = new FileWriter(fileToBeModified);
                                writer.write(newContent);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } finally {
                                try {
                                    reader.close();
                                    writer.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }

    }

    public void sirala() {
        try (BufferedReader br = new BufferedReader(new FileReader("hesap.txt"))) {
            String line;
            int satir = 0;
            int i = satirSayisiniGetir("hesap.txt")-1;
            Integer[] sira = new Integer[i];
            while ((line = br.readLine()) != null) {
                String[] array = line.split(" ");
                String array1 = array[1];
                int array2=Integer.valueOf(array1);
                sira[satir] = array2;
                satir++;
            }
            System.out.print("1. Küçükten Büyüğe Sırala\n2. Büyükten Küçüğe Sırala\nYapmak istediğiniz işlemi giriniz: ");
            int sec=sn.nextInt();
            if(sec==1){
                Arrays.sort(sira,0,satir);
                System.out.println("Hesap Özeti (Küçükten Büyüğe): "+Arrays.toString(sira));
            }
            else if (sec==2){
                Arrays.sort(sira,0,satir, Collections.reverseOrder());
                System.out.println("Hesap Özeti (Büyükten Küçüğe): "+Arrays.toString(sira));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

