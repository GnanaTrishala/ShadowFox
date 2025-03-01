import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentInfoSystem {
    private JFrame frame;
    private DefaultListModel<Student> studentListModel;
    private JList<Student> studentList;
    private JTextField nameField, idField, ageField, courseField;
    private ArrayList<Student> students;

    public StudentInfoSystem() {
        students = new ArrayList<>();
        frame = new JFrame("Student Information System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        studentListModel = new DefaultListModel<>();
        studentList = new JList<>(studentListModel);
        studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(studentList);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("ID:"));
        idField = new JTextField();
        panel.add(idField);
        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);
        panel.add(new JLabel("Age:"));
        ageField = new JTextField();
        panel.add(ageField);
        panel.add(new JLabel("Course:"));
        courseField = new JTextField();
        panel.add(courseField);

        JButton addButton = new JButton("Add Student");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addStudent());
        updateButton.addActionListener(e -> updateStudent());
        deleteButton.addActionListener(e -> deleteStudent());

        frame.setVisible(true);
    }

    private void addStudent() {
        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();

            Student student = new Student(id, name, age, course);
            students.add(student);
            studentListModel.addElement(student);

            clearFields();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Invalid Input!");
        }
    }

    private void updateStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            Student selectedStudent = students.get(selectedIndex);
            selectedStudent.setName(nameField.getText());
            selectedStudent.setAge(Integer.parseInt(ageField.getText()));
            selectedStudent.setCourse(courseField.getText());

            studentList.repaint();
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Select a student to update.");
        }
    }

    private void deleteStudent() {
        int selectedIndex = studentList.getSelectedIndex();
        if (selectedIndex != -1) {
            students.remove(selectedIndex);
            studentListModel.remove(selectedIndex);
            clearFields();
        } else {
            JOptionPane.showMessageDialog(frame, "Select a student to delete.");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        courseField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentInfoSystem::new);
    }
}
