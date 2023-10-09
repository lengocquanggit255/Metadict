public abstract class Vehicle {
    protected String brand;
    protected String model;
    protected String registrationNumber;
    protected Person owner;

    /**
     * Vehicle.
     */
    public Vehicle(String brand, String model, String registrationNumber, Person owner) {
        this.brand = brand;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.owner = owner;
    }

    /**
     * Vehicle.
     */
    public abstract String getInfo();

    /**
     * Vehicle.
     */
    public void transferOwnership(Person newOwner) {
        owner.removeVehicle(this.registrationNumber);
        newOwner.addVehicle(this);
        owner = newOwner;
    }

    /**
     * Vehicle.
     */
    public String getBrand() {
        return this.brand;
    }

    /**
     * Vehicle.
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Vehicle.
     */
    public String getModel() {
        return this.model;
    }

    /**
     * Vehicle.
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Vehicle.
     */
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    /**
     * Vehicle.
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Vehicle.
     */
    public Person getOwner() {
        return this.owner;
    }

    /**
     * Vehicle.
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

}
