/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package id.co.icg.ie.util;

/**
 *
 * @author hpali
 */
public class Terbilang {
 
    static String[] nomina={"","satu","dua","tiga","empat","lima","enam",
                         "tujuh","delapan","sembilan","sepuluh","sebelas"};
 
    public static String bilangx(double angka)
    {
        if(angka<12)
        {
          return nomina[(int)angka];
        }
        
        if(angka>=12 && angka <=19)
        {
            return nomina[(int)angka%10] +" belas ";
        }
        
        if(angka>=20 && angka <=99)
        {
            return nomina[(int)angka/10] +" puluh "+nomina[(int)angka%10];
        }
        
        if(angka>=100 && angka <=199)
        {
            return "seratus "+ bilangx(angka%100);
        }
        
        if(angka>=200 && angka <=999)
        {
            return nomina[(int)angka/100]+" ratus "+bilangx(angka%100);
        }
        
        if(angka>=1000 && angka <=1999)
        {
            return "seribu "+ bilangx(angka%1000);
        }
        
        if(angka >= 2000 && angka <=999999)
        {
            return bilangx((int)angka/1000)+" ribu "+ bilangx(angka%1000);
        }
        
        if(angka >= 1000000 && angka <=999999999)
        {
            return bilangx((int)angka/1000000)+" juta "+ bilangx(angka%1000000);
        }
        
        if(angka >= 1000000000 && angka <= 9999999999999.0)
        {
            return bilangx((double)angka/1000000000)+" milyar "+ bilangx(angka%1000000000);
        }
        
        return "";
    }
    
    public static void main(String[] args) {
        System.out.println( new Terbilang().bilangx(9999999999.0));
    }
}
