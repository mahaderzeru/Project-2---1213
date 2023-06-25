/**
 *
 * @author Adam Whaley and Mary Zeru
 */

//import java.util.*;
import java.util.ArrayList;

public class PetStore implements PetStoreSpecification{

    private String storeName;
    //private ArrayList<Pet> pets; 

    private ArrayList<Dog> availableDogs = new ArrayList<>();
    private ArrayList<Cat> availableCats = new ArrayList<>();
    private ArrayList<ExoticPet> availableExoticPets = new ArrayList<>();
    private ArrayList<Member> memberList = new ArrayList<>();
    private ArrayList<PremiumMember> premiumMemberList = new ArrayList<>();
    private static int nextPetID = 1;
    private static int nextMemberID = 1;

/**
     * Store pets
     *
     * @param storeName 
     */
    public PetStore(String storeName) {
        this.storeName = storeName;
        Dog dog1 = new Dog(
            "Kaylee",
            "German Shepherd",
            "Female",
            12,
            85,
            getNextPetID(),
            500
        );
        Dog dog2 = new Dog("Poe", "Corgi", "Male", 3, 35, getNextPetID(), 450);
        Cat cat1 = new Cat(
            "Karma",
            "Orange Tabby",
            "Female",
            6,
            15,
            getNextPetID(),
            200
        );
        Cat cat2 = new Cat("Clover", "American Shorthair", "Female", 6, 18, getNextPetID(), 150);
        ExoticPet ep1 = new ExoticPet(
            "Pancake",
            "Bearded Dragon",
            "Male",
            6,
            0.8,
            getNextPetID(),
            75
        );
        ExoticPet ep2 = new ExoticPet(
            "Noodle",
            "Ball Python",
            "Male",
            4,
            2,
            getNextPetID(),
            95
        );

        availableDogs.add(dog1);
        availableDogs.add(dog2);
        availableCats.add(cat1);
        availableCats.add(cat2);
        availableExoticPets.add(ep1);
        availableExoticPets.add(ep2);

        Member member1 = new Member("Jo", getNextMemberID(), true);
        member1.addCat(new Cat("Valjean", "White tabby", "Male", 1, 10, 0, 0));
        memberList.add(member1);

        PremiumMember member2 = new PremiumMember("Sage", getNextMemberID(), false, false);
        premiumMemberList.add(member2);
        member2.addExoticPet(
            new ExoticPet("Smaug", "Bearded Dragon", "Male", 5, 1, 0, 0)
        );

    }

    public static int getNextPetID() {
        nextPetID++;
        return nextPetID - 1;
    }
    
    public static int getNextMemberID() {
        nextMemberID++;
        return nextMemberID - 1;
    }


    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public ArrayList<Dog> getAvailableDogs() {
        return availableDogs;
    }

    public void setAvailableDogs(ArrayList<Dog> availableDogs) {
        this.availableDogs = availableDogs;
    }

    public ArrayList<Cat> getAvailableCats() {
        return availableCats;
    }

    public void setAvailableCats(ArrayList<Cat> availableCats) {
        this.availableCats = availableCats;
    }

    public ArrayList<ExoticPet> getAvailableExoticPets() {
        return availableExoticPets;
    }

    public void setAvailableExoticPets(ArrayList<ExoticPet> availableExoticPets) {
        this.availableExoticPets = availableExoticPets;
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(ArrayList<Member> memberList) {
        this.memberList = memberList;
    }

    public ArrayList<PremiumMember> getPremiumMemberList() {
        return premiumMemberList;
    }

    public void setPremiumMemberList(ArrayList<PremiumMember> premiumMemberList) {
        this.premiumMemberList = premiumMemberList;
    }

    /**
     * getMemberByID
     * 
     * @param type 
     * @param index 
     */
    public PremiumMember getMemberByID(int memberID){
        for (PremiumMember pm : premiumMemberList) {
            if(pm.getMemberID() == memberID){
                return pm; 
            }
        }
        return null; 
    }

    /**
     * remove pets
     *
     * @param type 
     * @param index 
     */

    public void removePet(String type, int index) {
        if (type.equals("dog")) 
            availableDogs.remove(index);
        else if (type.equals("cat")) 
            availableCats.remove(index);
        else if (type.equals("exoticPet")) 
            availableExoticPets.remove(index);

    }

    /**
     * add a new premium or regular member
     *
     * @param name 
     * @param premium 
     */
     public void addNewMember(String name, boolean premium) {
        if (premium) {
            premiumMemberList.add(new PremiumMember(name,getNextMemberID(),false,false));
        } else {
            memberList.add(new Member(name,getNextMemberID(), false));
        }
    }

    /**
     * adoption drive
     *
     * @param pets arrayList 
     * @param price 
     */

    
    @Override
    public void adoptionDrive(ArrayList<Pet> pets, double price) {
        for(Pet p: pets) {
            ((Pet)p).setPrice(price);
            if(p instanceof Dog){
                availableDogs.add((Dog)p);
            }else if(p instanceof Cat){
                availableCats.add((Cat) p);
            }else if(p instanceof ExoticPet){
                availableExoticPets.add((ExoticPet)p);             
            }
        }
    }
    /**
     * calculate total value of inventory
     *
     * @return total
     */

    @Override
    public double inventoryValue() {
        double total = 0; 
        for(Dog d: availableDogs){
            total += d.getPrice(); 
        }for(Cat d: availableCats){
            total += d.getPrice(); 
        }for(ExoticPet d: availableExoticPets){
            total += d.getPrice(); 
        }
        return total;  
    }
}
