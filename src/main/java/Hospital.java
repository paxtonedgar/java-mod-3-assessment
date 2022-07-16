import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;

public class Hospital {
    HashMap <String, List<Doctor>> specialtyToDoctorMap = new HashMap<>();
    private String name;

    public void createDoctor(String name, String specialty){
        Doctor doctor = new Doctor(name, specialty);

        if(specialtyToDoctorMap.containsKey(specialty)){
            specialtyToDoctorMap.get(specialty).add(doctor);
        }else{
            specialtyToDoctorMap.put(specialty, Arrays.asList(doctor));
        }
    }

    public Patient createPatient(String name, String disease){
        Patient patient = new Patient(name, disease);
        return patient;
    }

    public void assignPatientToDoc(Patient patient, HashMap<String, String> ailmentToSpecialtyMap, HashMap <String, List<Doctor>> specialtyToDoctorMap){
        String disease = patient.getDisease();
        String specialty = ailmentToSpecialtyMap.get(disease);
        List<Doctor> doctors = specialtyToDoctorMap.get(specialty);
        int max = 1000000;
        int indexOfChoice = -1;
        for(int i =0; i<= doctors.size(); i++){
            Doctor doctor = doctors.get(i);

            if(doctor.patients.isEmpty()){
                doctor.patients.add(patient);
                patient.setDoctor(doctor);
            }else if(doctor.patients.size() <= max) {
                max = doctor.patients.size();
                indexOfChoice = i;
            }
        }

        if(indexOfChoice != -1){
            doctors.get(indexOfChoice).addNewPatient(patient);
            patient.setDoctor(doctors.get(indexOfChoice));
        }

    }

    public List<Patient> getAllPatients(){
        //LOLOLOLOLOL
        //flatten the internal lists (after converting them to Streams)
        //into a single Stream, and then collect the result into a list
        ArrayList<ArrayList<Patient>> patients = new ArrayList<>();
        Collection<List<Doctor>> doctor = specialtyToDoctorMap.values();
        List<Doctor> flatDoctor = doctor.stream().flatMap(List::stream).collect(Collectors.toList());
        flatDoctor.stream().forEach(d -> patients.add(d.patients));
        List<Patient> flatPatient = patients.stream().flatMap(List::stream).collect(Collectors.toList());
        return flatPatient;
    }

    public boolean DoesHospitalHaveSpecialty(String disease, HospitalWorld world){
        boolean bool = false;
        String specialty = world.ailmentToSpecialtyMap.get(disease);
        return bool = specialtyToDoctorMap.containsKey(specialty);
    }

}
