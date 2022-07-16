import java.util.HashMap;

public class Treatment {
    private int mana;
    private String name;
    HashMap<String, Integer> treatmentToManaMap = new HashMap<String, Integer>();

    public Treatment() {
        treatmentToManaMap.put("radiation", 10);
        treatmentToManaMap.put("antibiotics", 3);
        treatmentToManaMap.put("antiVirals", 4);
        treatmentToManaMap.put("ibuprofin", 5);
        treatmentToManaMap.put("therapy", 2);
        treatmentToManaMap.put("surgery", 15);
    }


    public void getTreatmentMana(String name){
        treatmentToManaMap.get(name);
    }
}
