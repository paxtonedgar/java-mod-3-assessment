import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    HospitalWorld world = new HospitalWorld();
    public void menu(Scanner sc){

        int choice = 0;


        do {
            System.out.println("Welcome to the Patient Provider Portal");
            System.out.println("[1] Create Hospital");
            System.out.println("[2] Add Doctor");
            System.out.println("[3] Admit a Patient");
            System.out.println("[4] Select a Patient for treatment");
            System.out.println("[5] Save");
            System.out.println("[6] Load");
            System.out.println("[7] Quit");
            choice = sc.nextInt();

            switch(choice){
                case 1:
                    hospitalCreateMenu(sc, world);
                case 2:
                    doctorCreateMenu(sc, world);
                case 3:
                    patientCreateMenu(sc, world);
                case 4:
                    patientSelectionMenu(sc,world);
                case 5:
                    fileSaveMenu();
                case 6:
                    fileLoadMenu();
                default:
                    System.out.println("Invalid selection!");
            }
        } while (choice!= 7);

    }

    public void hospitalCreateMenu(Scanner sc, HospitalWorld world){
        System.out.println("Welcome to the Hospital Portal");

        int choice = 0;

        do{
            System.out.println("[1] Enter Hospitals name");
            System.out.println("[2] Return");

            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("What is the hospitals name?");
                    String name = sc.nextLine();
                    name = name.trim().toLowerCase();
                    world.createHospital(name);
                    hospitalCreateMenu(sc, world);
                case 2: menu(sc);
                default:
                    System.out.println("Invalid choice!");
            }
        }while(choice != 2);


    }

    public void doctorCreateMenu(Scanner sc, HospitalWorld world){
        System.out.println("Welcome to the Doctor Portal");

        int choice = 0;

        do{
            System.out.println("[1] Enter Doctors Information");
            System.out.println("[2] Return");

            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("What is the doctors name?");
                    String name = sc.nextLine();
                    name = name.trim().toLowerCase();

                    System.out.println("Choose the doctors specialty?");
                    System.out.println("\t[1] oncology");
                    System.out.println("\t[2] general");
                    System.out.println("\t[3] psychiatry");
                    System.out.println("\t[4] orthopedic");
                    int spec = sc.nextInt();

                    if(spec > 4 || spec < 1 || spec != (int)spec){
                        System.out.println("invalid choice");
                        doctorCreateMenu(sc, world);
                    }
                    String specialty = specialtySelection(spec);

                    System.out.println("Which hospital does the doctor work at?");
                    String hospitalName = sc.nextLine();
                    hospitalName = hospitalName.trim().toLowerCase();

                    Hospital hospital = world.doesHospitalExist(hospitalName);
                    hospital.createDoctor(name, specialty);
                    doctorCreateMenu(sc, world);
                case 2: menu(sc);
                default:
                    System.out.println("Invalid choice!");
            }
        }while(choice != 2);
    }

    public String specialtySelection(int spec){
        String specialty;
        switch(spec){
            case 1:
                return specialty = "oncology";
            case 2:
                return specialty = "general";
            case 3:
                return specialty = "psychiatry";
            case 4:
                return specialty = "orthopedic";
        }
        return null;
    }

    public void patientCreateMenu(Scanner sc, HospitalWorld world){
        System.out.println("Welcome to the Patient Portal");
        int choice = 0;

        do{
            System.out.println("[1] Enter Patients Information");
            System.out.println("[2] Return");

            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("What is the patient name?");
                    String name = sc.nextLine();
                    name = name.trim().toLowerCase();

                    System.out.println("Which of these issues do you have?");
                    System.out.println("\t[1] cancer");
                    System.out.println("\t[2] bacterial infection");
                    System.out.println("\t[3] viral infection");
                    System.out.println("\t[4] cold");
                    System.out.println("\t[5] depression");
                    System.out.println("\t[6] broken bone");
                    int dis = sc.nextInt();

                    if(dis > 6 || dis < 1 || dis != (int)dis){
                        System.out.println("invalid choice");
                        patientCreateMenu(sc, world);
                    }
                    String disease = diseaseSelection(dis);

                    Hospital hospital = hospitalChoice(sc, world);
                    if(hospital.DoesHospitalHaveSpecialty(disease, world)){
                        Patient patient = hospital.createPatient(name, disease);
                        hospital.assignPatientToDoc(patient, world.ailmentToSpecialtyMap, hospital.specialtyToDoctorMap);
                    }else{
                        System.out.println("Please choose different hospital");
                        patientCreateMenu(sc, world);
                    }
                    patientCreateMenu(sc, world);
                case 2: menu(sc);
                default:
                    System.out.println("Invalid choice!");
            }
        }while(choice != 2);
    }

    public Hospital hospitalChoice(Scanner sc, HospitalWorld world){
        System.out.println("Which hospital would you like to go to?");
        int len = world.nameToObjectMap.size();
        List names = world.nameToObjectMap.keySet().stream().toList();
        for(int i = 0; i<=len; i++){
            System.out.println("["+i+"]" + names.get(i));
        }
        int choice = sc.nextInt();
        if(choice >= 0 && choice >= len){
            Hospital hospital = world.nameToObjectMap.get(choice);
            return hospital;
        }else{
            System.out.println("Invalid Selection");
            menu(sc);
        }

        return null;
    }

    public String diseaseSelection(int dis){
        String disease;
        switch(dis){
            case 1:
                return disease = "cancer";
            case 2:
                return disease = "bacterial infection";
            case 3:
                return disease = "viral infection";
            case 4:
                return disease = "cold";
            case 5:
                return disease = "depression";
            case 6:
                return disease = "broken bone";
        }
        return null;
    }

    public Patient patientSelection(Scanner sc, List<Patient> patients){
        System.out.println("Which Patient would you like to treat?");
        int len = patients.size();
        for(int i = 0; i<=len; i++){
            System.out.println("["+i+"]" + patients.get(i));
        }
        int choice = sc.nextInt();
        Patient patient = patients.get(choice);
        return patient;
    }
    public void patientSelectionMenu(Scanner sc, HospitalWorld world){
        System.out.println("Welcome to the Patient selection menu");
        int choice = 0;

        do{
            System.out.println("[1] Select a Hospital");
            System.out.println("[2] Return");

            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    Hospital hospital = hospitalChoice(sc, world);
                    List<Patient> patients = hospital.getAllPatients();
                    Patient patient = patientSelection(sc, patients);
                    Doctor doctor = patient.getDoctor();
                    doctor.treatPatient(patient, world.ailmentToSpecialtyMap);
                case 2: menu(sc);
                default:
                    System.out.println("Invalid choice!");
            }
        }while(choice != 2);

    }

    public void fileLoadMenu(){}

    public void fileSaveMenu(){}



}
