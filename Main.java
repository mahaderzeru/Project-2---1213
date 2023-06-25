import java.util.*;

import javax.swing.JEditorPane;


/**
 *
 * @author Adam Whaley and Mary Zeru
 */


public class Main {
    public static void main(String[] args) {

        Scanner scnr = new Scanner(System. in);
        PetStore ps = new PetStore("Find-a-Buddy"); // Give your store a name!
        System.out.println("**** Welcome to " + ps.getStoreName() + "****");
        while (true) {
            System.out.println("Please select from one of the following menu otions");
            System.out.println("\t1. Buy a new pet");
            System.out.println("\t2. Register a new member");
            System.out.println("\t3. Start adoption drive (add new pets)");
            System.out.println("\t4. Check current inventory");
            System.out.println("\t5. Register new pet to Owner profile");
            System.out.println("\t6. Exit");

            int choice1 = scnr.nextInt();

            switch (choice1) {
                case 1:
                    System.out.println("-----------------------------------");
                    purchase(ps, scnr, new ArrayList<Pet>());
                    break;
                case 2:
                    System.out.println("-----------------------------------");
                    registerNewMember(ps, scnr);
                    break;
                case 3:
                    System.out.println("-----------------------------------");
                    promptForAdoptionDrive(scnr, ps);
                    break;
                case 4:
                    //print statements to display inventory value   
                    System.out.println("Total inventory value: " + ps.inventoryValue());
                    break;
                case 5:
                    System.out.println("-----------------------------------");
                    registerNewPetToOwnerProfile(scnr, ps);
                    break;
                case 6:
                    System.out.println("Thanks for visiting!");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void purchase(PetStore petStore, Scanner scnr, ArrayList<Pet> cart){
        System.out.println("What type of pet are you here to purchase?");
        System.out.println("\t1. Dogs");
        System.out.println("\t2. Cats"); 
        System.out.println("\t3. Exotic Pets");

        int petTypeChoice = scnr.nextInt();

        // display inventory menu
        int itemNum = 1;
        if (petTypeChoice == 1) {
            ArrayList<Dog> inventory = petStore.getAvailableDogs();
            if (!inventory.isEmpty()) {

                System.out.println("Which of the following dogs would you like to purchase?:");

                for (Dog pet : inventory) {
                    System.out.println(
                        "\t" + itemNum + ". $" + pet.getPrice() + " - " + pet.getBreed() + "(" + pet.getName() + ")");
                    itemNum++;
                }
                // get user selection for product to add arraylist (cart)
                int choice = scnr.nextInt();
                if (choice <= inventory.size()) {
                    cart.add(inventory.get(choice - 1));
                    //update inventory for this item
                    petStore.removePet("dog", choice - 1);
                    //cart summary
                    System.out.println("You have " + cart.size() + " items in your cart. Are you done shopping?");
                    System.out.println("\t1. Yes");
                    System.out.println("\t2. No");

                    int doneShopping = scnr.nextInt();
                    if (doneShopping == 1) {
                        //System.out.println("TO DO - CHEKOUT ");
                        checkout(petStore, scnr, cart);
                    } else if (doneShopping == 2) { // more shopping
                        purchase(petStore, scnr, cart); // recursively call purchase(...) until done
                    } else {
                        System.out.println("Invalid Choice.");
                    }
                } else {
                    System.out.println("Please enter a valid product number. Try again");
                    purchase(petStore, scnr, cart);
                }
            } else {
                System.out.println("Sorry! we currently have no dogs available.");
            }

        }

         else if (petTypeChoice == 2) {
            ArrayList<Cat> inventory = petStore.getAvailableCats();
            if (!inventory.isEmpty()) {

                System.out.println("Which of the following cats would you like to purchase?:");

                for (Cat pet : inventory) {
                    System.out.println(
                        "\t" + itemNum + ". $" + pet.getPrice() + " - " + pet.getBreed() + "(" + pet.getName() + ")" );
                    itemNum++;
                }
                // get user selection for product to add arraylist (cart)
                int choice = scnr.nextInt();
                if (choice <= inventory.size()) {
                    cart.add(inventory.get(choice - 1));
                    //update inventory for this item
                    petStore.removePet("cat", choice - 1);
                    //cart summary
                    System.out.println(
                        "You have " + cart.size() + " items in your cart. Are you done shopping?"
                    );
                    System.out.println("\t1. Yes");
                    System.out.println("\t2. No");

                    int doneShopping = scnr.nextInt();
                    if (doneShopping == 1) {
                        //System.out.println("TO DO - CHEKOUT ");
                        checkout(petStore, scnr, cart);
                    } else if (doneShopping == 2) { // more shopping
                        purchase(petStore, scnr, cart); // recursively call purchase(...) until done
                    } else {
                        System.out.println("Invalid Choice.");
                    }
                } else {
                    System.out.println("Please enter a valid product number. Try again");
                    purchase(petStore, scnr, cart);
                }
            } else {
                System.out.println("Sorry! we currently have no cats available.");
            }
        }
        else if (petTypeChoice == 3) {
            ArrayList<ExoticPet> inventory = petStore.getAvailableExoticPets();
            if (!inventory.isEmpty()) {

                System.out.println("Which of the following exotic pets would you like to purchase?:");

                for (ExoticPet pet : inventory) {
                    System.out.println("\t" + itemNum + ". $" + pet.getPrice() + " - " + pet.getSpecies() + "(" + pet.getName() + ")");
                    itemNum++;
                }
                // get user selection for product to add arraylist (cart)
                int choice = scnr.nextInt();
                if (choice <= inventory.size()) {
                    cart.add(inventory.get(choice - 1));
                    //update inventory for this item
                    petStore.removePet("exotic pet", choice - 1);
                    //cart summary
                    System.out.println(
                        "You have " + cart.size() + " items in your cart. Are you done shopping?");
                    System.out.println("\t1. Yes");
                    System.out.println("\t2. No");

                    int doneShopping = scnr.nextInt();
                    if (doneShopping == 1) {
                        //System.out.println("TO DO - CHEKOUT ");
                        checkout(petStore, scnr, cart);
                    } else if (doneShopping == 2) { // more shopping
                        purchase(petStore, scnr, cart); // recursively call purchase(...) until done
                    } else {
                        System.out.println("Invalid Choice.");
                    }
                } else {
                    System.out.println("Please enter a valid product number. Try again");
                    purchase(petStore, scnr, cart);
                }
            } else {
                System.out.println("Sorry! we currently have no exotic pets available.");
            }
        }
    }//end of purchase method

    private static void checkout(PetStore petStore, Scanner scnr, ArrayList<Pet> cart) {
        // calculate total
        double total = 0;
        for (Pet pet : cart) {
            total += pet.getPrice();
        }
        System.out.println(
            "Your total comes to " + total + ". \nPlease select which member is making this" +
            " purchase."
        );

        // list members and option to register
        int num = 1;
        for (Member member : petStore.getMemberList()) {
            System.out.println(num + ". " + member.getName());
            num++;
        }
        for (PremiumMember member : petStore.getPremiumMemberList()) {
            System.out.println(num + ". " + member.getName());
            num++;
        }
        System.out.println(num + ". Register a new Member.");

        System.out.println(""); // space line
        int memberSelect = scnr.nextInt();
        Member purchaser = null;
        PremiumMember premiumPurchaser = null;

        if (memberSelect > petStore.getMemberList().size() + petStore.getPremiumMemberList().size() + 1) { // invalid selection
            System.out.println("Invalid Selection");
            checkout(petStore, scnr, cart); // recursive call if valid user input

        } else { // valid selection
            if (memberSelect == (petStore.getMemberList().size() + petStore.getPremiumMemberList().size()) + 1) { // adding a new member
                boolean premium = registerNewMember(petStore, scnr);
                if (premium) {
                    premiumPurchaser = petStore.getPremiumMemberList().get(
                        petStore.getPremiumMemberList().size() - 1
                    );
                } else {
                    purchaser = petStore.getMemberList().get(petStore.getMemberList().size() - 1);
                }
            } else if (memberSelect <= (petStore.getMemberList().size())) {
                purchaser = petStore.getMemberList().get(memberSelect - 1);
            } else { // existing premium member
                premiumPurchaser = petStore.getPremiumMemberList().get(
                    memberSelect - petStore.getMemberList().size() - 1
                );
            }

            // check if premium member and fees are due
            if (purchaser == null && premiumPurchaser != null) {
                if( !premiumPurchaser.isDuesPaid()){
                System.out.println(
                    "Premium Membership dues unpaid, $5 will be added to purchase total to cover du" +
                    "es."
                );
                total += 5;
                }
                premiumPurchaser.setDuesPaid(true);
                // update amount of purchases for this member
                premiumPurchaser.setAmountSpent(total);
                // done
                System.out.println("Your purchase total was: " + total);
                System.out.println(
                    "Congrats on your purchase " + premiumPurchaser.getName()
                );

            } else {
                // update amount of purchases for this member
                purchaser.setAmountSpent(total);
                System.out.println("Your purchase total was: " + total);
                System.out.println("Congrats on your purchase " + purchaser.getName());

            }
        }
    }//end of checkout method 


    private static boolean registerNewMember(PetStore petStore, Scanner scnr) {
        // prompt user to select membership type
        System.out.println("Let's get you registered as a new Member!");
        System.out.println( "Would you like to register as a free-tier or premium member?");
        System.out.println("\t1. Free-tier");
        System.out.println("\t2. Premium");

        // user selection
        int choice1 = scnr.nextInt();
        // if user selects a wrong choice 
        if (choice1 > 2 || choice1 < 1) {
            System.out.println("Invalid choice.");
            registerNewMember(petStore, scnr); // try again

        } else {
            // prompt user for name 
            System.out.println("Please enter your name:");
            scnr.nextLine();
            String name = scnr.nextLine();
             System.out.println("Welcome to our membership program! Thank you for registering.");
            if (choice1 == 1) { // regular membership
                petStore.addNewMember(name, false);
                return false;
            } else { // user entered 2 - premimum membership
                petStore.addNewMember(name, true);
                return true;
            }
        }
        return false;
    }//end of registerNewMember method

    //choice 3: adoption drive
    /**
     * @param scnr
     * @param s
     */
    private static void promptForAdoptionDrive(Scanner scnr, PetStore s){
        //prompt user to enter price of pets
        System.out.println("Please enter price of pets: (must be a number, no symbols)");
            double petPrice = scnr.nextDouble(); //assign price to all of the pets in this drive (don't know how)
        //prompt user to enter number of pets 
        System.out.println("How many pets would you like to enter? ");
            int num = scnr.nextInt(); 
            ArrayList<Pet> petsToAdopt = new ArrayList<>();
            Pet p;
                for(int i = 1; i <= num; i++){
                    System.out.println("What type of pet is pet #" + i);
                    System.out.println("1. Dog \n2. Cat \n3. Exotic Pet");
                        int petType = scnr.nextInt();
                        switch(petType){ 
                        case 1: 
                            //get user input for details about dog
                            System.out.println("Breed: ");
                            String breed = scnr.nextLine();
                            scnr.nextLine();
                                System.out.println("Sex (M or F): ");
                                String sex = scnr.nextLine();
                                if(sex.startsWith("M") || sex.startsWith("F")){
                                    System.out.println("Age: ");
                                    int age = scnr.nextInt();
                                        if(age >= 0){
                                            System.out.println("Weight: ");
                                            double weight = scnr.nextDouble();
                                                if(weight >= 0){
                                                    System.out.println("ID: ");
                                                    int id = scnr.nextInt();
                                                        if(id >= 0){
                                                            p = new Dog("Dog", breed, sex, age, weight, id, petPrice);
                                                            petsToAdopt.add(p);
                                                            System.out.println("New dog added!");
                                                        }else{
                                                            System.out.println("Invalid input, try again: ");
                                                            System.out.println("ID: ");
                                                            id = scnr.nextInt();
                                                        }
                                                }else{
                                                    System.out.println("Invalid input, try again: ");
                                                    System.out.println("Weight: ");
                                                    weight = scnr.nextDouble();
                                                }
                                        }else{
                                            System.out.println("Invalid input, try again: ");
                                            System.out.println("Age: ");
                                            age = scnr.nextInt();
                                        }
                                }else{
                                    System.out.println("Invalid input, try again: ");
                                    System.out.println("Sex (M or F): ");
                                    sex = scnr.nextLine();
                                }
                            break;
                        case 2: 
                            //get user input for details about cat
                            System.out.println("Breed: ");
                            breed = scnr.nextLine();
                            if(breed.length() > 0){
                                System.out.println("Sex (M or F): ");
                                sex = scnr.nextLine();
                                if(sex.startsWith("M") || sex.startsWith("F")){
                                    System.out.println("Age: ");
                                    int age = scnr.nextInt();
                                        if(age >= 0){
                                            System.out.println("Weight: ");
                                            double weight = scnr.nextDouble();
                                                if(weight >= 0){
                                                    System.out.println("ID: ");
                                                    int id = scnr.nextInt();
                                                        if(id >= 0){
                                                            p = new Cat("Cat", breed, sex, age, weight, id, petPrice); 
                                                            petsToAdopt.add(p);
                                                            System.out.println("New cat added!");
                                                        }else{
                                                            System.out.println("Invalid input, try again: ");
                                                            System.out.println("ID: ");
                                                            id = scnr.nextInt();
                                                        }
                                                }else{
                                                    System.out.println("Invalid input, try again: ");
                                                    System.out.println("Weight: ");
                                                    weight = scnr.nextDouble();
                                                }
                                        }else{
                                            System.out.println("Invalid input, try again: ");
                                            System.out.println("Age: ");
                                            age = scnr.nextInt();
                                        }
                                }else{
                                    System.out.println("Invalid input, try again: ");
                                    System.out.println("Sex (M or F): ");
                                    sex = scnr.nextLine();
                                }
                            }else{
                                System.out.println("Invalid input, try again: ");
                                System.out.println("Breed: ");
                                breed = scnr.nextLine();
                            }                            
                            break;
                        case 3: 
                            //get user input for details about exotic pet 
                            System.out.println("Species: ");
                            String species = scnr.nextLine();
                            if(species.length() > 0){
                                System.out.println("Sex (M or F): ");
                                sex = scnr.nextLine();
                                if(sex.startsWith("M") || sex.startsWith("F")){
                                    System.out.println("Age: ");
                                    int age = scnr.nextInt();
                                        if(age >= 0){
                                            System.out.println("Weight: ");
                                            double weight = scnr.nextDouble();
                                                if(weight >= 0){
                                                    System.out.println("ID: ");
                                                    int id = scnr.nextInt();
                                                        if(id >= 0){
                                                            p = new ExoticPet("Exotic pet", species, sex, age, weight, id, petPrice); 
                                                            petsToAdopt.add(p);
                                                            System.out.println("New exotic pet added!");
                                                        }else{
                                                            System.out.println("Invalid input, try again: ");
                                                            System.out.println("ID: ");
                                                            id = scnr.nextInt();
                                                        }
                                                }else{
                                                    System.out.println("Invalid input, try again: ");
                                                    System.out.println("Weight: ");
                                                    weight = scnr.nextDouble();
                                                }
                                        }else{
                                            System.out.println("Invalid input, try again: ");
                                            System.out.println("Age: ");
                                            age = scnr.nextInt();
                                        }
                                }else{
                                    System.out.println("Invalid input, try again: ");
                                    System.out.println("Sex (M or F): ");
                                    sex = scnr.nextLine();
                                }
                            }else{
                                System.out.println("Invalid input, try again: ");
                                System.out.println("Species: ");
                                species = scnr.nextLine();
                            } 
                            break;
                        default: 
                        //error message       
                        System.out.println("Please enter a number between 1 and 3. \nWhat type of pet is pet #" + num);
                        System.out.println("1. Dog \n2. Cat \n3. Exotic Pet");
                        petType = scnr.nextInt();                                     
                    }
                }
        s.adoptionDrive(petsToAdopt, petPrice);
    }//end of adoptionDrive method

    /**
     * @param scnr
     */
        public static void registerNewPetToOwnerProfile(Scanner scnr, PetStore ps){
            //ask the user which member they are
            System.out.println("Which member are you? \n1. Jo \n2. Sage "); 
            //scanner for user input
            int mem = scnr.nextInt();
            PremiumMember pm = ps.getMemberByID(mem);
            //validate user input
                    //prompt user to enter what pet they would like to register 
                    System.out.println("What type of pet would you like to register? ");
                        System.out.println("1. Dog \n2. Cat \n3. Exotic Pet");
                            int petType = scnr.nextInt();
                            switch(petType){
                                case 1: 
                                    //get price, breed/species, sex, age, and weight
                                    System.out.println("Price: ");
                                    double price = scnr.nextDouble(); 
                                    System.out.println("Breed: ");
                                    String breed = scnr.nextLine();
                                    scnr.nextLine();
                                        System.out.println("Sex (M or F): ");
                                        String sex = scnr.nextLine();
                                        if(sex.startsWith("M") || sex.startsWith("F")){
                                            System.out.println("Age: ");
                                            int age = scnr.nextInt();
                                                if(age >= 0){
                                                    System.out.println("Weight: ");
                                                    double weight = scnr.nextDouble();
                                                        if(weight >= 0){
                                                            System.out.println("ID: ");
                                                            int id = scnr.nextInt();
                                                                if(id >= 0){
                                                                    Dog Jdog = new Dog("Dog", breed, sex, age, weight, id, price);
                                                                    //add information to dogsOwned arrayList
                                                                    pm.getDogsOwned().add(Jdog);
                                                                    System.out.println("A new dog has been added to your profile!");
                                                                }else{
                                                                    System.out.println("Invalid input, try again: ");
                                                                    System.out.println("ID: ");
                                                                    id = scnr.nextInt();
                                                                }
                                                        }else{
                                                            System.out.println("Invalid input, try again: ");
                                                            System.out.println("Weight: ");
                                                            weight = scnr.nextDouble();
                                                        }
                                                }else{
                                                    System.out.println("Invalid input, try again: ");
                                                    System.out.println("Age: ");
                                                    age = scnr.nextInt();
                                                }
                                        }else{
                                            System.out.println("Invalid input, try again: ");
                                            System.out.println("Sex (M or F): ");
                                            sex = scnr.nextLine();
                                        }
                                    break;
                                case 2: 
                                    //get price, breed/species, sex, age, and weight
                                    System.out.println("Price: ");
                                    price = scnr.nextDouble(); 
                                    System.out.println("Breed: ");
                                    breed = scnr.nextLine();
                                    if(breed.length() > 0){
                                        System.out.println("Sex (M or F): ");
                                        sex = scnr.nextLine();
                                        if(sex.startsWith("M") || sex.startsWith("F")){
                                            System.out.println("Age: ");
                                            int age = scnr.nextInt();
                                                if(age >= 0){
                                                    System.out.println("Weight: ");
                                                    double weight = scnr.nextDouble();
                                                        if(weight >= 0){
                                                            System.out.println("ID: ");
                                                            int id = scnr.nextInt();
                                                                if(id >= 0){
                                                                    Cat Jcat = new Cat("Cat", breed, sex, age, weight, id, price); 
                                                                    //add information to catsOwned arrayList
                                                                    pm.getCatsOwned().add(Jcat);
                                                                    System.out.println("A new cat has been added to your profile!");

                                                                }else{
                                                                    System.out.println("Invalid input, try again: ");
                                                                    System.out.println("ID: ");
                                                                    id = scnr.nextInt();
                                                                }
                                                        }else{
                                                            System.out.println("Invalid input, try again: ");
                                                            System.out.println("Weight: ");
                                                            weight = scnr.nextDouble();
                                                        }
                                                }else{
                                                    System.out.println("Invalid input, try again: ");
                                                    System.out.println("Age: ");
                                                    age = scnr.nextInt();
                                                }
                                        }else{
                                            System.out.println("Invalid input, try again: ");
                                            System.out.println("Sex (M or F): ");
                                            sex = scnr.nextLine();
                                        }
                                    }else{
                                        System.out.println("Invalid input, try again: ");
                                        System.out.println("Breed: ");
                                        breed = scnr.nextLine();
                                    }                            
                                    break;
                                case 3: 
                                    //get price, breed/species, sex, age, and weight
                                    System.out.println("Price: ");
                                    price = scnr.nextDouble(); 
                                    System.out.println("Species: ");
                                    String species = scnr.nextLine();
                                    if(species.length() > 0){
                                        System.out.println("Sex (M or F): ");
                                        sex = scnr.nextLine();
                                        if(sex.startsWith("M") || sex.startsWith("F")){
                                            System.out.println("Age: ");
                                            int age = scnr.nextInt();
                                                if(age >= 0){
                                                    System.out.println("Weight: ");
                                                    double weight = scnr.nextDouble();
                                                        if(weight >= 0){
                                                            System.out.println("ID: ");
                                                            int id = scnr.nextInt();
                                                                if(id >= 0){
                                                                    ExoticPet Jep = new ExoticPet("Exotic pet", species, sex, age, weight, id, price); 
                                                                    //add information to getExoticPetsOwned arrayList
                                                                    pm.getExoticPetsOwned().add(Jep);
                                                                    System.out.println("A new exotic pet has been added to your profile!");

                                                                }else{
                                                                    System.out.println("Invalid input, try again: ");
                                                                    System.out.println("ID: ");
                                                                    id = scnr.nextInt();
                                                                }
                                                        }else{
                                                            System.out.println("Invalid input, try again: ");
                                                            System.out.println("Weight: ");
                                                            weight = scnr.nextDouble();
                                                        }
                                                }else{
                                                    System.out.println("Invalid input, try again: ");
                                                    System.out.println("Age: ");
                                                    age = scnr.nextInt();
                                                }
                                        }else{
                                            System.out.println("Invalid input, try again: ");
                                            System.out.println("Sex (M or F): ");
                                            sex = scnr.nextLine();
                                        }
                                    }else{
                                        System.out.println("Invalid input, try again: ");
                                        System.out.println("Species: ");
                                        species = scnr.nextLine();
                                    }
                            }
        }//end of registerNewPetToOwnerProfile method

}//end of main