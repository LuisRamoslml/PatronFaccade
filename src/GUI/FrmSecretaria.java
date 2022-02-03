package GUI;

import BEAN.SecretariaBean;
import FACHADA.Fachada;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class FrmSecretaria extends JFrame implements ActionListener, MouseListener {
    
    private static FrmSecretaria unicainstancia;
    
    public static FrmSecretaria getInstancia()
    {
        if(unicainstancia == null)
            unicainstancia = new FrmSecretaria();
        return unicainstancia;
    }

    private JLabel lblUsuario, lblClave, lblLogin;
    private JTextField txtUsuario, txtClave;
    private JButton btnIngresar;
    private JScrollPane Panel;
    FACHADA.Fachada objfachada = null;
    SecretariaBean objsecretariabean = null;

    public static void main(String[] args) {
        FrmSecretaria inst = new FrmSecretaria();
        inst.setLocationRelativeTo(null);
        inst.setVisible(true);
    }

    public FrmSecretaria() {
        initGUI();

    }

    private void initGUI() {
        try {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            getContentPane().setLayout(null);
            getContentPane().setBackground(new Color(127, 140, 141));
            
            lblLogin = new JLabel();
            getContentPane().add(lblLogin);
            lblLogin.setText("LOGIN");
            lblLogin.setBounds(180, 20, 57, 14);
            
            lblUsuario = new JLabel();
            getContentPane().add(lblUsuario);
            lblUsuario.setText("Usuario:");
            lblUsuario.setBounds(90, 70, 57, 14);

            txtUsuario = new JTextField();
            getContentPane().add(txtUsuario);
            txtUsuario.setBounds(160, 70, 159, 21);

            lblClave = new JLabel();
            getContentPane().add(lblClave);
            lblClave.setText("Clave:");
            lblClave.setBounds(90, 120, 57, 14);

            txtClave = new JTextField();
            getContentPane().add(txtClave);
            txtClave.setBounds(160, 120, 159, 21);

            btnIngresar = new JButton();
            getContentPane().add(btnIngresar);
            btnIngresar.setText("Ingresar");
            btnIngresar.setBounds(180, 160, 110, 40);
            btnIngresar.addActionListener(this);

            this.setResizable(false);
            this.setSize(400, 300);

        } catch (Exception e) {
        }

    }

    public void ValidarAcceso() {
        try {
            objfachada = new Fachada();
            objsecretariabean = new SecretariaBean();
            objsecretariabean.setUsuario(txtUsuario.getText());
            objsecretariabean.setClave(txtClave.getText());
            if (txtUsuario.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Ingrese Usuario");
                txtUsuario.requestFocus();
            } else if (txtClave.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Ingrese Clave");
                txtUsuario.requestFocus();
            } else {
                int i = objfachada.ValidarAcceso(objsecretariabean);
                if (i == 1) {
                    objsecretariabean = objfachada.BuscarDatos(objsecretariabean);
                    FrmMenu menu = new FrmMenu();
                    menu.lblCod.setText(String.valueOf(objsecretariabean.getIdsecretaria()));
                    menu.setLocationRelativeTo(null);
                    menu.setVisible(true);
                    this.dispose();
                }else{
                    JOptionPane.showMessageDialog(null, "Datos Incorrectos (Aveces pasa)!!!");
                    txtUsuario.requestFocus();
                }
            }

        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnIngresar) {

            ValidarAcceso();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
