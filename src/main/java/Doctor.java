import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Doctor {
    ArrayList<Patient> patients = new ArrayList<Patient>();
    String mySpecialty;
    String name;

    Ailment ailment = new Ailment();
    Treatment treatment = new Treatment();
    public Doctor(String name, String mySpecialty) {
        this.mySpecialty = mySpecialty;
        this.name = name;

    }

    public void addNewPatient(Patient patient){
        patients.add(patient);
    }

    public void treatPatient(Patient patient,
                             HashMap<String, String> ailmentToSpecialtyMap){

        String disease = patient.getDisease();


        if(mySpecialty.matches(ailmentToSpecialtyMap.get(disease))){
            int roundsNeeded = patient.roundsNeeded();
            String cure = ailment.diseaseToTreatmentMap.get(disease);
            int mana = treatment.treatmentToManaMap.get(treatment);
            System.out.println("You need" + cure);
            for(int i = 0; i <= roundsNeeded; i++){
                int curr_health = patient.receiveTreatment(mana);
                if(curr_health == 100){
                    System.out.println("You are healed. Get out.");
                    patients.remove(patient);
                }
            }
        }else{
            System.out.println("Sorry you have the wrong doctor");
        }

    }

    public String getName(){
        return name;
    }
}
