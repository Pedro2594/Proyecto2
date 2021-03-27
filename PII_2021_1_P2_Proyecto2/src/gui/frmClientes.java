package gui;

import Negocio.NegocioClientes;
import Recursos.Clientes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmClientes {
    private JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JPanel jpaIzquierdo;
    private JPanel jpaDerecho;
    private JPanel jpaInferior;
    private JLabel lblDNI;
    private JLabel lblNombre;
    private JLabel lblDireccion;
    private JLabel lblTelefono;
    private JTextField txtDNI;
    private JTextField txtNombre;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnBuscar;
    private JScrollPane sclPanDatos;
    private JTable tblDatos;
    DefaultTableModel modelo;
    public frmClientes() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    Clientes clientes = new Clientes();
                    clientes.setDNI(Long.parseLong(txtDNI.getText()));
                    clientes.setNombre(txtNombre.getText());
                    clientes.setDireccion(txtDireccion.getText());
                    clientes.setTelefono(Long.parseLong(txtTelefono.getText()));
                    String respuesta = new NegocioClientes().Insertar(clientes);
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
                txtDNI.setText(modelo.getValueAt(filaSeleccionada, 0).toString());
                txtNombre.setText(modelo.getValueAt(filaSeleccionada, 1).toString());
                txtDireccion.setText(modelo.getValueAt(filaSeleccionada, 2).toString());
                txtTelefono.setText(modelo.getValueAt(filaSeleccionada, 3).toString());
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Clientes cliente = new Clientes();

                    cliente.setDNI(Long.parseLong(txtDNI.getText()));
                    cliente.setNombre(txtNombre.getText());
                    cliente.setDireccion(txtDireccion.getText());
                    cliente.setTelefono(Long.parseLong(txtTelefono.getText()));



                    new NegocioClientes().Eliminar(cliente);
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
                   Clientes cliente = new Clientes();
                    cliente.setDNI(Long.parseLong(txtDNI.getText()));
                    cliente.setNombre(txtNombre.getText());
                    cliente.setDireccion(txtDireccion.getText());
                    cliente.setTelefono(Long.parseLong(txtTelefono.getText()));
                    new NegocioClientes().Actualizar(cliente);
                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Clientes cliente = new Clientes();
                    cliente.setNombre(txtNombre.getText());
                    List<Clientes> listaClientes = new NegocioClientes().Buscar(cliente);
                    modelo.setRowCount(0);

                    for (Clientes cadaCliente: listaClientes) {
                        Object[] registroLeido = {cadaCliente.getDNI(), cadaCliente.getNombre(), cadaCliente.getDireccion(),cadaCliente.getTelefono()};
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
         LeerDatos();
    }
    private void LeerDatos() {
        try {
            List<Clientes> listaClientes = new NegocioClientes().Leer();
            modelo.setRowCount(0);
            for (Clientes cliente: listaClientes) { Object[] registroLeido = { cliente.getDNI(), cliente.getNombre(),cliente.getDireccion(),cliente.getTelefono()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        }catch (Exception e){
        }
    }

    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Clientes");
        frame1.setContentPane(new frmClientes().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}
