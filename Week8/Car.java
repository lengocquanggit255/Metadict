public class Car extends Vehicle {

    private int numberOfDoors;

    /**
     * Car.
     */
    public Car(String brand, String model, String registrationNumber,
            Person owner, int numberOfDoors) {
        super(brand, model, registrationNumber, owner);
        this.numberOfDoors = numberOfDoors;
    }

    /**
     * Car.
     */
    @Override
    public String getInfo() {
        String res = "Car:\n";
        res += "\tBrand: " + brand + "\n";
        res += "\tModel: " + model + "\n";
        res += "\tRegistration Number: " + registrationNumber + "\n";
        res += "\tNumber of Doors: " + numberOfDoors + "\n";
        res += "\tBelongs to " + owner.getName() + " - " + owner.getAddress() + "\n";
        return res;
    }

    /**
     * Car.
     */
    public int getNumberOfDoors() {
        return this.numberOfDoors;
    }

    /**
     * Car.
     */
    public void setNumberOfDoors(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }
}
