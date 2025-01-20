# **Documentación de PriceListPro**

## **Descripción General**

**PriceListPro** es una aplicación de escritorio desarrollada en Java utilizando `Swing` para la creación de interfaces gráficas. Su propósito es permitir a los usuarios gestionar una lista de productos, asignarles precios y calcular el total de la compra. Además, permite al usuario guardar, eliminar y limpiar los productos en la lista.

El sistema tiene una interfaz simple que permite al usuario:
- Ingresar productos y sus respectivos precios.
- Mostrar estos productos en una tabla.
- Calcular el total de los precios de los productos ingresados.
- Eliminar el último producto ingresado.
- Limpiar completamente la lista de productos.

## **Requisitos**

- **Java 8 o superior**.
- **IDE** (como IntelliJ IDEA, Eclipse o NetBeans) para desarrollo en Java.

## **Estructura del Proyecto**

El proyecto está compuesto por las siguientes clases:

1. **App.java**: Contiene la interfaz gráfica principal y la lógica de la aplicación.
2. **PriceListPro.java**: La clase principal que arranca la aplicación.
3. **fonts.java**: Una pequeña utilidad que lista las fuentes disponibles en el sistema (no es esencial para el funcionamiento principal del proyecto).

## **Clases y Funcionalidad**

### **1. App.java**

La clase `App` define la ventana principal de la aplicación utilizando `JFrame` de Java Swing. En esta clase se gestionan los componentes de la interfaz gráfica, como etiquetas, campos de texto, botones y la tabla para mostrar los productos.

#### Métodos principales:

- **`iniciar()`**:
    - Inicializa los componentes gráficos, incluyendo la tabla para los productos, los botones para guardar, calcular el total, borrar y limpiar.
    - Organiza los componentes utilizando `BorderLayout` y `FlowLayout`.
    - Proporciona la lógica para agregar productos a la tabla, calcular el total y manejar eventos de los botones.
  
### **2. PriceListPro.java**

La clase `PriceListPro` se encarga de iniciar la aplicación y mostrar la interfaz principal.

#### Método principal:

- **`main(String[] Args)`**:
    - Crea una instancia de la clase `App` y ejecuta el método `iniciar()` para mostrar la interfaz gráfica de la aplicación.

### **3. fonts.java**

Esta clase es una utilidad que imprime en la consola todas las fuentes disponibles en el sistema, utilizando la clase `GraphicsEnvironment` de Java. No es esencial para el funcionamiento de la aplicación principal pero puede ser útil si se desea explorar las fuentes disponibles.

#### Método principal:

- **`main(String[] Args)`**:
    - Muestra todas las fuentes disponibles en el sistema en formato de lista.

## **Interfaz de Usuario**

La interfaz de usuario está organizada en un diseño limpio y sencillo. Utiliza componentes como `JLabel`, `JTextField`, `JButton` y `JTable` para interactuar con el usuario.

- **Tabla de productos**: La tabla muestra los productos ingresados, con las columnas "Producto" y "Precio".
- **Campos de entrada**: Permiten al usuario ingresar el nombre del producto y el precio.
- **Botones**:
  - **Guardar**: Agrega un producto y su precio a la lista.
  - **Total**: Calcula y muestra el total de los productos ingresados.
  - **Limpiar**: Elimina todos los productos de la lista.
  - **Eliminar último producto**: Elimina el último producto agregado.

## **Instrucciones de Ejecución**

### Para ejecutar la aplicación:

1. **Compilar el código**:
    - Si estás usando un IDE como IntelliJ IDEA o Eclipse, simplemente compila el proyecto.
    - Si estás trabajando en la terminal, navega hasta la carpeta del proyecto y ejecuta:
      ```
      javac *.java
      ```

2. **Ejecutar la aplicación**:
    - Si usas un IDE, simplemente ejecuta la clase `PriceListPro.java`.
    - Si prefieres la terminal, ejecuta el siguiente comando:
      ```
      java PriceListPro
      ```

3. **Usar la aplicación**:
    - La aplicación se abrirá mostrando una ventana con campos de entrada para el producto y su precio.
    - Los productos y precios ingresados se mostrarán en la tabla, y puedes calcular el total o limpiar la lista de productos.


## **Código**

Aquí te dejo el código del archivo principal `App.java` con comentarios explicativos de cada parte del código:

```java
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
        // Declaración de etiquetas y campos
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
        buttonPanel.add

(guardarButton);
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
```

---

## **Conclusión**

PriceListPro es una herramienta útil para gestionar productos y calcular el total de compras. La interfaz gráfica es sencilla y fácil de usar, y el código está bien organizado para facilitar su mantenimiento y futuras ampliaciones.
