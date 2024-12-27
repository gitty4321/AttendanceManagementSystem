import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class GUI {
    private final Queue studentQueue;
    private final HashMap attendanceMap;
    private JFrame frame;
    private JTextField studentNameField;
    private JTextField studentIdField;
    private JTextArea outputArea;

    public GUI() {
        studentQueue = new Queue();
        attendanceMap = new HashMap(100);
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Attendance Management System");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("nimbusBase", new ColorUIResource(175, 199, 198));
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));

        JLabel nameLabel = new JLabel("Student Name:");
        studentNameField = new JTextField();
        JLabel idLabel = new JLabel("Student ID:");
        studentIdField = new JTextField();
        JButton markButton = new JButton("Mark Attendance");
        JButton dequeueButton = new JButton("Dequeue");
        inputPanel.add(nameLabel);
        inputPanel.add(studentNameField);
        inputPanel.add(idLabel);
        inputPanel.add(studentIdField);
        inputPanel.add(dequeueButton);
        inputPanel.add(markButton);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        JPanel checkPanel = new JPanel();
        checkPanel.setLayout(new FlowLayout());

        JTextField checkField = new JTextField(15);
        JButton checkButton = new JButton("Check Attendance");
        checkPanel.add(checkField);
        checkPanel.add(checkButton);

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(checkPanel, BorderLayout.SOUTH);

        markButton.addActionListener(_ -> {
            String name = studentNameField.getText().trim();
            String id = studentIdField.getText().trim();

            if (!name.isEmpty() && !name.matches(".*\\d.*") && id.matches("\\d+")) {
                studentQueue.enqueue(id);
                attendanceMap.put(id, name, true);
                outputArea.append(name + " (ID: " + id + ") added to queue and marked as present.\n");
                studentNameField.setText("");
                studentIdField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Enter a valid name (without numbers) and numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dequeueButton.addActionListener(_ -> {
            if (studentQueue.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Queue is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                String studentId = studentQueue.dequeue(); // Remove and get the front student ID
                String studentName = attendanceMap.getName(studentId);
                outputArea.append("Dequeued: " + studentName + " (ID: " + studentId + ")\n");
            }
        });

        checkButton.addActionListener(_ -> {
            String id = checkField.getText().trim();

            if (id.matches("\\d+")) {
                if (attendanceMap.containsKey(id)) {
                    String name = attendanceMap.getName(id);
                    boolean present = attendanceMap.get(id);
                    outputArea.append(name + " (ID: " + id + ") is " + (present ? "present." : "absent.") + "\n");
                } else {
                    outputArea.append("ID " + id + " has not been marked.\n");
                }
                checkField.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "Enter a valid numeric ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.setVisible(true);
    }

}