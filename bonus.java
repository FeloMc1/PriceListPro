import javax.swing.*;
import java.awt.*;
public class bonus extends JFrame {
    public void start() {
        JLabel user, pass;
        JTextField usertField;
        JPasswordField passwordField;

        user = new JLabel();
        user.setText("Usuario");
        user.setBounds(10, 0, 100, 20);


        pass = new JLabel();
        pass.setText("Contraseña");
        pass.setBounds(10, 40, 100, 20);


        usertField = new JTextField();
        usertField.setBounds(10,20,100,20);

        passwordField = new JPasswordField();
        passwordField.setBounds(10, 60, 100, 20);

        JButton iniciarButton = new JButton("Ingresar");



        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0,200,100,60);
        buttonPanel.add(iniciarButton);

        JPanel main = new JPanel();
        main.setLayout(null);
        main.setBackground(new Color(255,255,255));
        main.add(user, null);
        main.add(pass, null);
        main.add(usertField, null);
        main.add(passwordField, null);
        main.add(buttonPanel,null);


        setTitle("Iniciar sesión");
        setMinimumSize(new Dimension(600, 200));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(50,0,800,600);
        add(main);
    }
    public static void main(String[] args) {
        bonus bonusApp = new bonus();
        bonusApp.start();
    }
}
