import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Ailment {
    HashMap<String, Integer> diseaseToDamage = new HashMap<>();
    HashMap <String, String> diseaseToTreatmentMap = new HashMap<>();


    public Ailment() {
        diseaseToDamage.put("cancer", 50);
        diseaseToDamage.put("bacterial infection", 12);
        diseaseToDamage.put("viral infection", 16);
        diseaseToDamage.put("cold", 5);
        diseaseToDamage.put("depression", 10);
        diseaseToDamage.put("broken bone", 15);

        diseaseToTreatmentMap.put("cancer", "radiation");
        diseaseToTreatmentMap.put("bacterial infection", "antibiotics");
        diseaseToTreatmentMap.put("viral infection", "antiVirals");
        diseaseToTreatmentMap.put("cold", "ibuprofin");
        diseaseToTreatmentMap.put("depression", "therapy");
        diseaseToTreatmentMap.put("broken bone", "surgery");
    }

    public Integer getDiseaseDamage(String name){
        return diseaseToDamage.get(name);
    }
    public String getDiseaseTreatment(String name){
        return diseaseToTreatmentMap.get(name);
    }
}
