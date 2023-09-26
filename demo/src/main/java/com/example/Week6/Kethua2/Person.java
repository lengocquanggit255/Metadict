public class Person {
    private String name;
    private String address;

    /**
     * Method.
     */
    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    /**
     * Method.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Method.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method.
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Method.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method.
     */
    public String toString() {
        return "Person[name=" + name + ",address=" + address + "]";
    }

}
