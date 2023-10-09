public class MotorBike extends Vehicle {

    private boolean hasSidecar;

    /**
     * Motor Bike.
     */
    public MotorBike(String brand, String model,
            String registrationNumber, Person owner, boolean hasSideCar) {
        super(brand, model, registrationNumber, owner);
        this.hasSidecar = hasSideCar;
    }

    /**
     * Motor Bike.
     */
    @Override
    public String getInfo() {
        String res = "Motor Bike:\n";
        res += "\tBrand: " + brand + "\n";
        res += "\tModel: " + model + "\n";
        res += "\tRegistration Number: " + registrationNumber + "\n";
        res += "\tHas Side Car: " + Boolean.toString(hasSidecar) + "\n";
        res += "\tBelongs to " + owner.getName() + " - " + owner.getAddress() + "\n";
        return res;
    }

    /**
     * Motor Bike.
     */
    public boolean isHasSidecar() {
        return this.hasSidecar;
    }

    /**
     * Motor Bike.
     */
    public void setHasSidecar(boolean hasSideCar) {
        this.hasSidecar = hasSideCar;
    }

}
