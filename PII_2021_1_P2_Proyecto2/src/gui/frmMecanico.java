package gui;

import Negocio.NegocioMecanico;
import Recursos.Mecanico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmMecanico {
    private JPanel jpaPrincipal;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JTextField txtDNI;
    private JTextField txtEmail;
    private JTextField txtTelefono;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JLabel lblDNI;
    private JLabel lblNombre;
    private JLabel lblDireccion;
    private JLabel lblTelefono;
    private JLabel lblEmail;
    private JTable tblDatos;
    private JPanel jpaSuperior;
    private JPanel jpaDerecho;
    private JPanel jpaIzquierdo;
    private JPanel jpaInferior;
    DefaultTableModel modelo;

    public frmMecanico() {
       iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Mecanico mecanico = new Mecanico();
                    mecanico.setDNI(Long.parseLong(txtDNI.getText()));
                    mecanico.setNombre(txtNombre.getText());
                    mecanico.setDireccion(txtDireccion.getText());
                    mecanico.setTelefono(Long.parseLong(txtTelefono.getText()));
                    mecanico.setEmail(txtEmail.getText());
                    String respuesta = new NegocioMecanico().Insertar(mecanico);

                    if (!respuesta.contains("Error:")){
                        JOptionPane.showMessageDialog(null , "GUARDADO", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        LeerDatos();
                    }
                    else
                        throw new Exception(respuesta);

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtDNI.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtDireccion.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtTelefono.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtEmail.setText(modelo.getValueAt(filaSeleccionada,4).toString());
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Mecanico mecanico = new Mecanico();
                    mecanico.setDNI(Long.parseLong(txtDNI.getText()));
                    mecanico.setNombre(txtNombre.getText());
                    mecanico.setDireccion(txtDireccion.getText());
                    mecanico.setTelefono(Long.parseLong(txtTelefono.getText()));
                    mecanico.setEmail(txtEmail.getText());

                    new NegocioMecanico().Eliminar(mecanico);
                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });


        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeerDatos();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Mecanico mecanico  = new Mecanico();
                    mecanico.setDNI(Long.parseLong(txtDNI.getText()));
                    mecanico.setNombre(txtNombre.getText());
                    mecanico.setDireccion(txtDireccion.getText());
                    mecanico.setTelefono(Long.parseLong(txtTelefono.getText()));
                    mecanico.setEmail(txtEmail.getText());
                    new NegocioMecanico().Actualizar(mecanico);

                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Mecanico mecanico = new Mecanico();
                    mecanico.setDNI(Long.parseLong(txtDNI.getText()));

                    List<Mecanico> listaMecanico = new NegocioMecanico().Buscar(mecanico);
                    modelo.setRowCount(0);

                    for (Mecanico cadaMecanico: listaMecanico) {
                        Object[] registroLeido = {cadaMecanico.getDNI(), cadaMecanico.getNombre(),cadaMecanico.getDireccion(), cadaMecanico.getTelefono(), cadaMecanico.getEmail()};
                        modelo.addRow(registroLeido);
                    }

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }

    private void iniciar () {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Direccion");
        modelo.addColumn("Telefono");
        modelo.addColumn("Email");
        LeerDatos();
    }

    private void LeerDatos() {
        try {
            List<Mecanico> listaMecanico = new NegocioMecanico().Leer();
            modelo.setRowCount(0);
            for (Mecanico mecanico : listaMecanico) {
                Object[] registroLeido = {mecanico.getDNI(), mecanico.getNombre(), mecanico.getDireccion(), mecanico.getTelefono(), mecanico.getEmail()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Mecanico");
        frame1.setContentPane(new frmMecanico().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}

