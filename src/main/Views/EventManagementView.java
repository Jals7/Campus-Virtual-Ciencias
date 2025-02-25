package Views;

import Controllers.EventController;
import Datas.EventData;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class EventManagementView {
    private EventController controller;
    private DefaultListModel<String> listModel;
    private JList<String> eventList;
    private JTextField nameField, descriptionField, organizerField;
    private JLabel nameLabel, descriptionLabel, organizerLabel;

    public EventManagementView() {
        controller = new EventController();
        showMainInterface();
    }

    private void showMainInterface() {
        JFrame frame = new JFrame("Gestor de Eventos");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 450);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.setBackground(new Color(3, 34, 63));
        nameField = new JTextField();
        descriptionField = new JTextField();
        organizerField = new JTextField();
        JButton addButton = new JButton("Agregar Evento");

        listModel = new DefaultListModel<>();
        updateList();
        eventList = new JList<>(listModel);
        eventList.setBackground(new Color(3, 34, 63));
        eventList.setForeground(Color.WHITE);
        JScrollPane listScrollPane = new JScrollPane(eventList);

        nameLabel = new JLabel("Nombre:");
        nameLabel.setForeground(Color.WHITE);
        descriptionLabel = new JLabel("Descripción:");
        descriptionLabel.setForeground(Color.WHITE);
        organizerLabel = new JLabel("Organizador:");
        organizerLabel.setForeground(Color.WHITE);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(organizerLabel);
        panel.add(organizerField);
        panel.add(addButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(listScrollPane, BorderLayout.CENTER);

        addButton.addActionListener(e -> {
            if (nameField.getText().trim().isEmpty() ||
                descriptionField.getText().trim().isEmpty() ||
                organizerField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Todos los campos deben estar llenos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            controller.addEvent(nameField.getText(), descriptionField.getText(), organizerField.getText());
            updateList();
            nameField.setText("");
            descriptionField.setText("");
            organizerField.setText("");
        });

        eventList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedIndex = eventList.getSelectedIndex();
                    if (selectedIndex != -1) {
                        showEventDetails(selectedIndex);
                    }
                }
            }
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateList() {
        listModel.clear();
        for (EventData event : controller.getEvents()) {
            listModel.addElement(event.getId() + " - " + event.getName());
        }
    }

    private void showEventDetails(int index) {
        EventData event = controller.getEvents().get(index);
        JLabel nameLabelDetail, descriptionLabelDetail, organizerLabelDetail;
        JDialog dialog = new JDialog();
        dialog.setTitle("Detalles del Evento");
        dialog.setSize(450, 400);
        dialog.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contentPanel.setBackground(new Color(3, 34, 63));
        JTextField nameField = new JTextField(event.getName());
        JTextField descriptionField = new JTextField(event.getDescription());
        JTextField organizerField = new JTextField(event.getOrganizer());

        JButton editButton = new JButton("Editar");
        JButton saveButton = new JButton("Guardar Cambios");
        JButton closeButton = new JButton("Cerrar");
        saveButton.setEnabled(false);

        editButton.addActionListener(e -> {
            nameField.setEditable(true);
            descriptionField.setEditable(true);
            organizerField.setEditable(true);
            saveButton.setEnabled(true);
        });

        saveButton.addActionListener(e -> {
            controller.updateEvent(index, nameField.getText(), descriptionField.getText(), organizerField.getText());
            updateList();
            dialog.dispose();
        });

        closeButton.addActionListener(e -> dialog.dispose());

        nameLabelDetail = new JLabel("Nombre:");
        nameLabelDetail.setForeground(Color.WHITE);
        descriptionLabelDetail = new JLabel("Descripción:");
        descriptionLabelDetail.setForeground(Color.WHITE);
        organizerLabelDetail = new JLabel("Organizador:");
        organizerLabelDetail.setForeground(Color.WHITE);

        contentPanel.add(nameLabelDetail);
        contentPanel.add(nameField);
        contentPanel.add(descriptionLabelDetail);
        contentPanel.add(descriptionField);
        contentPanel.add(organizerLabelDetail);
        contentPanel.add(organizerField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(3, 34, 63));
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(closeButton);

        dialog.add(contentPanel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(EventManagementView::new);
    }*/
}
