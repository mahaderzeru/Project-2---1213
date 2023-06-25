import java.util.ArrayList;
public interface PetStoreSpecification{

    /*
    update inventory by adding the given pets and their prices
    @param pets
    @param price 
    @return 
    */

    public void adoptionDrive(ArrayList<Pet> pets, double price);


     /*
    calculate and return $ amount for current inventory of pets that are in stock    
    @return total stock in value
    */
    public double inventoryValue(); 
}