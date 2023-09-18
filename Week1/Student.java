package Week1;

public class Student {
    private String name;
    private String id;
    private String group;
    private String email;

    /**
     * The student init with default values.
     */
    public Student() {
        name = "Student";
        id = "000";
        group = "K62CB";
        email = "uet@vnu.edu.vn";
    }

    /**
     * The student init with input values.
     */
    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        group = "K62CB";
    }

    /**
     * The student init as a copy of other student.
     */
    public Student(Student s) {
        this.name = s.getName();
        this.id = s.getId();
        this.email = s.getEmail();
        this.group = s.getGroup();
    }

    public String getInfo() {
        return name + " - " + id + " - " + group + " - " + email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroup() {
        return group;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Main function.
     */
    public static void main(String[] args) {
        Student st1 = new Student();
        st1.setName("Nguyen Van An");
        st1.setId("17020001");
        st1.setGroup("K62CC");
        st1.setEmail("17020001@vnu.edu.vn");
    }
}
