/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package wat.jeet.lab4;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ResultStorage {

    // Tüm hesaplama sonuçlarını saklayacağımız liste
    private List<CalculationResult> results = new ArrayList<>();

    // Yeni bir sonucu listeye eklemek için
    public void addResult(CalculationResult res) {
        results.add(res);
    }

    // Mevcut tüm sonuçları web tarafına (JSF) göndermek için
    public List<CalculationResult> getAllResults() {
        return results;
    }
}
