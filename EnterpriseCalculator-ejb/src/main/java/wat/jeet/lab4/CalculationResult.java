/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package wat.jeet.lab4;

/**
 *
 * @author Student
 */
import java.io.Serializable;

public class CalculationResult implements Serializable {

    private String componentType;  // Stateless, Stateful vb.
    private String interfaceType;  // Local, Remote
    private String result;         // Hesaplama sonucu (Sayısal sonuç)
    private String userBrowser;    // İsteği atan tarayıcı bilgisi

    // Constructor (Yapıcı Metod)
    public CalculationResult(String componentType, String interfaceType, String result, String userBrowser) {
        this.componentType = componentType;
        this.interfaceType = interfaceType;
        this.result = result;
        this.userBrowser = userBrowser;
    }

    // Getter ve Setter Metodları
    // (İpucu: NetBeans'te boş bir yere sağ tıklayıp Insert Code -> Getter and Setter 
    // seçerek hepsini otomatik oluşturabilirsiniz)
    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getUserBrowser() {
        return userBrowser;
    }

    public void setUserBrowser(String userBrowser) {
        this.userBrowser = userBrowser;
    }
}
