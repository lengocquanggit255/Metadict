package Week1;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class StudentManagement {
    private Student[] students = new Student[100];
    private int numberOfStudents = 0;

    public void addStudent(Student newStudent) {
        students[numberOfStudents++] = newStudent;
    }

    /**
     * Remove student function.
     */
    public void removeStudent(String id) {
        int i;
        for (i = 0; i < numberOfStudents; i++) {
            if (students[i].getId().equals(id)) {
                break;
            }
        }
        if (i == numberOfStudents) {
            return;
        }
        while (i < numberOfStudents - 1) {
            Student temp = students[i];
            students[i] = students[i + 1];
            students[i + 1] = temp;
            i++;
        }
        students[numberOfStudents - 1] = null;
        numberOfStudents--;
    }

    /**
     * Sort student by group function.
     */
    public String studentsByGroup() {
        String res = "";
        Map<String, ArrayList<Student>> unorderedMap = new LinkedHashMap<>();
        for (int i = 0; i < numberOfStudents; i++) {
            if (!unorderedMap.containsKey(students[i].getGroup())) {
                ArrayList<Student> newGroup = new ArrayList<Student>();
                unorderedMap.put(students[i].getGroup(), newGroup);
                unorderedMap.get(students[i].getGroup()).add(students[i]);
            } else {
                unorderedMap.get(students[i].getGroup()).add(students[i]);
            }
        }
        for (Map.Entry<String, ArrayList<Student>> group : unorderedMap.entrySet()) {
            String key = group.getKey();
            ArrayList<Student> sts = group.getValue();
            res += key + "\n";
            for (Student student : sts) {
                res += student.getInfo() + "\n";
            }
        }
        return res;
    }

    /**
     * Check if 2 students are in the same group or not function.
     */
    public static boolean sameGroup(Student s1, Student s2) {
        if (!s1.getGroup().equals(s2.getGroup())) {
            return false;
        } else {
            return true;
        }
    }

}
