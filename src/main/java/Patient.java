public class Patient {
    private String name;
    private int health_index = 100;
    private String myDisease;

    private Doctor myDoctor;
    Ailment ailment = new Ailment();

    public Patient(String name, String myDisease) {
        this.name = name;
        this.myDisease = myDisease;
        this.health_index = health_index - ailment.getDiseaseDamage(myDisease);
    }
    public String getDisease(){
        return myDisease;
    }

    public void setDoctor(Doctor doc){
        this.myDoctor = doc;
    }

    public Doctor getDoctor(){
        return myDoctor;
    }

    public int roundsNeeded(){
        int damage = ailment.getDiseaseDamage(myDisease);
        int diff = 100 - health_index;
        int rounds = diff % damage;
        return rounds;
    }

    public int receiveTreatment(int mana){
       health_index = health_index + mana;
       return health_index;
    }
}
