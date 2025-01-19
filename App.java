import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App extends JFrame {
    final Font f1Font = new Font("Segoe UI", Font.BOLD, 25);  // Fuente moderna y más legible
    final Font f2Font = new Font("Segoe UI", Font.PLAIN, 18);

    DefaultTableModel model = new DefaultTableModel();

    public void iniciar() {
        JLabel productoLabel, precioLabel, lbl1, totalbl;
        JTextField productofField, preciofField;

        model.addColumn("Producto");
        model.addColumn("Precio");

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);  // Hace que la tabla ocupe todo el área disponible

        // Etiqueta Producto
        productoLabel = new JLabel("Producto");
        productoLabel.setFont(f2Font);

        // Etiqueta Precio
        precioLabel = new JLabel("Precio");
        precioLabel.setFont(f2Font);

        // Campos de texto
        productofField = new JTextField();
        productofField.setPreferredSize(new Dimension(200, 30));
        productofField.setFont(f2Font);

        preciofField = new JTextField();
        preciofField.setPreferredSize(new Dimension(200, 30));
        preciofField.setFont(f2Font);

        // Etiqueta de total
        lbl1 = new JLabel();
        lbl1.setFont(f1Font);
        
        totalbl = new JLabel();
        totalbl.setFont(f1Font);

        // Panel para la tabla
        JPanel tPanel = new JPanel();
        tPanel.setLayout(new BorderLayout());
        tPanel.add(new JScrollPane(table), BorderLayout.CENTER);  // Agrega la tabla dentro de un JScrollPane

        // Botón Guardar
        JButton guardarButton = new JButton("Guardar");
        guardarButton.setFont(f2Font);
        guardarButton.setBackground(new Color(0, 123, 255));  // Botón azul
        guardarButton.setForeground(Color.WHITE);
        guardarButton.setBorder(BorderFactory.createLineBorder(new Color(0, 123, 255), 2, true));  // Borde redondeado
        guardarButton.setFocusPainted(false);
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String prod = productofField.getText();
                String priceText = preciofField.getText();
                
                // Validación para campos vacíos
                if (prod.isEmpty() || priceText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese tanto el producto como el precio.");
                    return;
                }

                try {
                    int price = Integer.parseInt(priceText);  // Validación para precios
                    model.addRow(new Object[]{prod, price});  // Agregar fila al modelo de la tabla
                    productofField.setText("");  // Limpiar el campo de producto
                    preciofField.setText("");   // Limpiar el campo de precio
                    productofField.requestFocus();  // Volver a poner el foco en el campo de producto

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese un precio válido.");
                }
            }
        });

        // Botón Total
        JButton totalButton = new JButton("Total");
        totalButton.setFont(f2Font);
        totalButton.setBackground(new Color(40, 167, 69));  // Botón verde
        totalButton.setForeground(Color.WHITE);
        totalButton.setBorder(BorderFactory.createLineBorder(new Color(40, 167, 69), 2, true));  // Borde redondeado
        totalButton.setFocusPainted(false);
        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int stotal = 0;
                for (int i = 0; i < model.getRowCount(); i++) {
                    stotal += (int) model.getValueAt(i, 1);  // Sumar el precio de cada fila
                }
                totalbl.setText("Total: " + stotal);
            }
        });

        // Botón Limpiar
        JButton limpButton = new JButton("Limpiar");
        limpButton.setFont(f2Font);
        limpButton.setBackground(new Color(220, 53, 69));  // Botón rojo
        limpButton.setForeground(Color.WHITE);
        limpButton.setBorder(BorderFactory.createLineBorder(new Color(220, 53, 69), 2, true));  // Borde redondeado
        limpButton.setFocusPainted(false);
        limpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productofField.setText("");  // Limpiar el campo de producto
                preciofField.setText("");   // Limpiar el campo de precio
                model.setRowCount(0);       // Limpiar la tabla
                totalbl.setText("Total: 0");  // Reiniciar total
            }
        });

        // Panel para los botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(guardarButton);
        buttonPanel.add(totalButton);
        buttonPanel.add(limpButton);

        // Panel de total
        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        totalPanel.add(totalbl);

        // Panel de entrada
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 10, 10));  // Usamos un GridLayout para organizar los campos
        inputPanel.setBackground(Color.white);
        inputPanel.add(productoLabel);
        inputPanel.add(productofField);
        inputPanel.add(precioLabel);
        inputPanel.add(preciofField);

        // Panel principal usando BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);  // Panel de entrada en la parte superior
        mainPanel.add(tPanel, BorderLayout.CENTER);     // Panel de la tabla en el centro
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);  // Panel de botones debajo de la tabla

        // Configuración de la ventana principal
        setTitle("MarketList - Gestión de Productos");
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(50, 0, 1000, 600);
        add(mainPanel);  // Agregar el panel principal a la ventana
    }

    public static void main(String[] Args) {
        App frame1 = new App();
        frame1.iniciar();
    }
}
