import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private String address;
    private List<Vehicle> vehicleList;

    /**
     * Person.
     */
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
        vehicleList = new ArrayList<Vehicle>();
    }

    /**
     * Person.
     */
    public void addVehicle(Vehicle vehicle) {
        for (Vehicle myVehicle : vehicleList) {
            if (myVehicle.getRegistrationNumber().equals(vehicle.getRegistrationNumber())) {
                return;
            }
        }
        vehicleList.add(vehicle);
    }

    /**
     * Person.
     */
    public void removeVehicle(String registrationNumber) {
        Vehicle target = null;
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                target = vehicle;
                break;
            }
        }
        if (target != null) {
            vehicleList.remove(target);
        }
    }

    /**
     * Person.
     */
    public String getVehiclesInfo() {

        if (vehicleList.isEmpty()) {
            return this.getName() + " has no vehicle!";
        }

        String res = this.getName() + " has:\n" + "\n";
        for (Vehicle vehicle : vehicleList) {
            res += vehicle.getInfo();
        }
        return res;
    }

    /**
     * Person.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Person.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Person.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Person.
     */
    public void setAddress(String address) {
        this.address = address;
    }

}