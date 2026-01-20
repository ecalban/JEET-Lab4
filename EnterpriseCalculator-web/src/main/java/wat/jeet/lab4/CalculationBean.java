package wat.jeet.lab4;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;

@Named("calculationBean")
@SessionScoped
public class CalculationBean implements Serializable {

    private String componentType; // "stateful" or "stateless"
    private String interfaceType; // "local" or "remote"
    private List<CalculationResult> results;

    // EJB dependency injection
    @EJB(beanName = "StatefulCalculator")
    private StatefulCalculatorLocal statefulLocal;

    // JNDI Lookup ile injection (Remote)
    @EJB(lookup = "wat.jeet.lab4.StatefulCalculatorRemote")
    private StatefulCalculatorRemote statefulRemote;

    @EJB(beanName = "StatelessCalculator")
    private StatelessCalculatorLocal statelessLocal;

    @EJB(lookup = "wat.jeet.lab4.StatelessCalculatorRemote")
    private StatelessCalculatorRemote statelessRemote;

    // implicit resource injection (not-EJB olarak belirtilmiş ama EJB arayüzü)
    private ResultStorageRemote resultStorage;

    @PostConstruct
    public void init() {
        results = new ArrayList<>();
        try {
            // JNDI search and resource injection
            // Manuel JNDI lookup işlemi
            Hashtable<String, String> jndiProps = new Hashtable<>();
            jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            jndiProps.put(Context.PROVIDER_URL, "iiop://127.0.0.1:3700");
            jndiProps.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");

            InitialContext ctx = new InitialContext(jndiProps);
            
            // JNDI ismine dikkat: java:app/[Module-Name]/[Bean-Name]![Interface]
            resultStorage = (ResultStorageRemote) ctx.lookup(
                "java:app/EnterpriseCalculator-ejb-1.0-SNAPSHOT/ResultStorage!wat.jeet.lab4.ResultStorageRemote"
            );
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void performCalculation() {
        // User-Agent bilgisini al
        String userAgent = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestHeaderMap().get("User-Agent");
        
        String result = "";
        CalculationResult cr = null;

        try {
            // Seçime göre hesaplama yap
            if ("stateful".equalsIgnoreCase(componentType)) {
                if ("local".equalsIgnoreCase(interfaceType)) {
                    result = statefulLocal.performCalculations();
                } else if ("remote".equalsIgnoreCase(interfaceType)) {
                    result = statefulRemote.performCalculations();
                }
            } else if ("stateless".equalsIgnoreCase(componentType)) {
                if ("local".equalsIgnoreCase(interfaceType)) {
                    result = statelessLocal.performCalculations();
                } else if ("remote".equalsIgnoreCase(interfaceType)) {
                    result = statelessRemote.performCalculations();
                }
            } else {
                result = "Invalid component type.";
                return;
            }

            // Sonucu hazırla
            cr = new CalculationResult(componentType, interfaceType, userAgent, result);

            // Sonucu Singleton EJB'ye kaydet
            resultStorage.addResult(cr);
            
            // Listeyi güncelle (ekranda göstermek için)
            // Not: Gerçek uygulamada veriyi tekrar DB/Singleton'dan çekmek daha sağlıklı olabilir
            results.add(cr); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // BURAYA GETTER VE SETTER METOTLARINI EKLEMELİSİNİZ
    // (componentType, interfaceType, results için)
    
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

    public List<CalculationResult> getResults() {
        // Kılavuzda results getter'ı için özel bir mantık belirtilmemiş ama
        // genellikle JSF listenin güncel halini ister.
        // Singleton'dan veriyi tazelemek isterseniz: 
        // return resultStorage.getResults(); 
        // Şimdilik basitçe listeyi döndürelim:
        return results;
    }

    public void setResults(List<CalculationResult> results) {
        this.results = results;
    }
}