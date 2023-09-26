public class Staff extends Person {
    private String school;
    private double pay;

    /**
     * Method.
     */
    public Staff(String name, String address, String school, double pay) {
        super(name, address);
        this.school = school;
        this.pay = pay;
    }

    /**
     * Method.
     */
    public String getSchool() {
        return this.school;
    }

    /**
     * Method.
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Method.
     */
    public double getPay() {
        return this.pay;
    }

    /**
     * Method.
     */
    public void setPay(double pay) {
        this.pay = pay;
    }

    /**
     * Method.
     */
    public String toString() {
        return "Staff[" + super.toString() + ",school=" + this.school + ",pay=" + this.pay + "]";
    }
}
