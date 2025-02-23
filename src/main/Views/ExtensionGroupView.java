package Views;

import Datas.ExtensionGroup;
import Datas.GroupData;

import javax.swing.*;
import java.awt.*;

public class ExtensionGroupView {
    private JPanel groupListPanel;

    public ExtensionGroupView() {
        GroupData.loadGroupsFromFile();
        showMainInterface();
    }

    private void showMainInterface() {
        JFrame frame = new JFrame("Gestión de Grupos de Extensión");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JButton createButton = new JButton("Crear Grupo");
        createButton.addActionListener(e -> showCreateGroupDialog());
        topPanel.add(createButton);
        frame.add(topPanel, BorderLayout.NORTH);

        groupListPanel = new JPanel();
        groupListPanel.setLayout(new BoxLayout(groupListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(groupListPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        updateGroupList();
        frame.setVisible(true);
    }

    private void showCreateGroupDialog() {
        JDialog dialog = new JDialog((Frame) null, "Crear Grupo de Extensión", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(3, 2));
        dialog.setLocationRelativeTo(null);

        JTextField nameField = new JTextField();
        JTextField descriptionField = new JTextField();
        JButton createButton = new JButton("Crear");

        dialog.add(new JLabel("Nombre:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Descripción:"));
        dialog.add(descriptionField);
        dialog.add(createButton);

        createButton.addActionListener(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();

            if (!name.isEmpty() && !description.isEmpty()) {
                GroupData.addGroup(new ExtensionGroup(GroupData.getGroups().size() + 1, name, description));
                updateGroupList();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Todos los campos son obligatorios.");
            }
        });

        dialog.setVisible(true);
    }

    private void updateGroupList() {
        groupListPanel.removeAll();
        for (var group : GroupData.getGroups()) {
            if (group instanceof ExtensionGroup) {
                JPanel groupPanel = new JPanel(new BorderLayout());
                groupPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                JLabel nameLabel = new JLabel("📌 " + group.getName());
                JButton detailsButton = new JButton("🔍 Ver Detalles");

                detailsButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
                        "📢 Nombre: " + group.getName() + "\n📝 Descripción: " + group.getDescription(),
                        "Detalles del Grupo", JOptionPane.INFORMATION_MESSAGE));

                groupPanel.add(nameLabel, BorderLayout.WEST);
                groupPanel.add(detailsButton, BorderLayout.EAST);
                groupListPanel.add(groupPanel);
            }
        }
        groupListPanel.revalidate();
        groupListPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExtensionGroupView::new);
    }
}

