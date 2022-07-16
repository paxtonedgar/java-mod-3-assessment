import java.util.ArrayList;
import java.util.HashMap;

public class HospitalWorld {
    private String name;
    HashMap<String, Hospital> nameToObjectMap = new HashMap<>();
    HashMap<String, String> ailmentToSpecialtyMap = new HashMap<>();
    public HospitalWorld() {

        ailmentToSpecialtyMap.put("cancer", "oncology");
        ailmentToSpecialtyMap.put("bacterial infection", "general");
        ailmentToSpecialtyMap.put("viral infection", "general");
        ailmentToSpecialtyMap.put("cold", "general");
        ailmentToSpecialtyMap.put("depression", "psychiatry");
        ailmentToSpecialtyMap.put("broken bone", "orthopedic ");

    }

    public void createHospital(String name) {
        this.name = name;
        doesHospitalExist(name);
    }

    public Hospital doesHospitalExist(String name){
        if(nameToObjectMap.containsKey(name)){
            System.out.println("Hospital exists");
            return nameToObjectMap.get(name);
        }else{
            ArrayList<Doctor> docs = new ArrayList<>();
            Hospital hospital = new Hospital();
            hospital.specialtyToDoctorMap.put(name, docs);
            nameToObjectMap.put(name, hospital);
            System.out.println(name + "has been created");
        }
        return null;
    }
}
