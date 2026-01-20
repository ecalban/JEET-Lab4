/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package wat.jeet.lab4;

import javax.ejb.Stateless;

@Stateless
public class StatelessCalculator implements StatefulCalculatorLocal, StatefulCalculatorRemote {

    public String calculate(int a, int b) {
        try {
            // 3 saniyelik gecikme simülasyonu (Ağır bir iş yapılıyormuş gibi)
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Sonucu String olarak döndürüyoruz
        return String.valueOf(a + b);
    }

    // Sınıfın içine bu değişkeni ekleyin
    private String lastAccessTime = "Never";

    public String performCalculations() {
        // 1. Rastgele değer
        double randomVal = Math.random();

        // 2. Hazırlık: Önceki erişim zamanını tut
        String report = "Last access: " + lastAccessTime + ", Random: " + randomVal;

        // 3. Zamanı güncelle
        lastAccessTime = new java.util.Date().toString();

        // 4. 2 saniye uyut (Görselde kırmızıyla 2 seconds yazıyor)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return report;
    }
}
