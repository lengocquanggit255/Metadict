public class Student extends Person {
    private String program;
    private int year;
    private double fee;

    /**
     * Method.
     */
    public Student(String name, String address, String program, int year, double fee) {
        super(name, address);
        this.program = program;
        this.year = year;
        this.fee = fee;
    }

    /**
     * Method.
     */
    public String getProgram() {
        return this.program;
    }

    /**
     * Method.
     */
    public void setProgram(String program) {
        this.program = program;
    }

    /**
     * Method.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Method.
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Method.
     */
    public double getFee() {
        return this.fee;
    }

    /**
     * Method.
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    /**
     * Method.
     */
    public String toString() {
        return "Student[" + super.toString() + ",program="
                + this.program + ",year=" + this.year + ",fee=" + this.fee + "]";
    }
}
