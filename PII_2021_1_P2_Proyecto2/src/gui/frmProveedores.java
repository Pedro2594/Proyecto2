package gui;

import Negocio.NegocioProveedores;
import Recursos.Proveedores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmProveedores {
    private JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JPanel jpaIzquierda;
    private JPanel jpaDerecho;
    private JPanel jpaInferior;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JLabel lblNombre;
    private JLabel lblProducto;
    private JLabel lblGarantia;
    private JLabel lblPrecio;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtGarantia;
    private JTextField txtProducto;
    private JTable tblDatos;
    private JScrollPane sclPanDatos;
    DefaultTableModel modelo;

    public frmProveedores() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Proveedores proveedores = new Proveedores();
                    proveedores.setNombre(txtNombre.getText());
                    proveedores.setProducto(txtProducto.getText());
                    proveedores.setGarantia(txtGarantia.getText());
                    proveedores.setPrecio(Integer.parseInt(txtPrecio.getText()));
                    String respuesta = new NegocioProveedores().Insertar(proveedores);

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
                txtNombre.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtProducto.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtGarantia.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtPrecio.setText(modelo.getValueAt(filaSeleccionada,3).toString());
            }
        });



        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Proveedores proveedores = new Proveedores();
                    proveedores.setNombre(txtNombre.getText());
                    proveedores.setProducto(txtProducto.getText());
                    proveedores.setGarantia(txtGarantia.getText());
                    proveedores.setPrecio(Integer.parseInt(txtPrecio.getText()));

                    new NegocioProveedores().Eliminar(proveedores);
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

                    Proveedores proveedores  = new Proveedores();
                    proveedores.setNombre(txtNombre.getText());
                    proveedores.setProducto(txtProducto.getText());
                    proveedores.setGarantia(txtGarantia.getText());
                    proveedores.setPrecio(Integer.parseInt(txtPrecio.getText()));
                    new NegocioProveedores().Actualizar(proveedores);

                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Proveedores proveedores = new Proveedores();
                    proveedores.setNombre(txtNombre.getText());

                    List<Proveedores> listaProveedores = new NegocioProveedores().Buscar(proveedores);
                    modelo.setRowCount(0);

                    for (Proveedores cadaProveedor: listaProveedores) {
                        Object[] registroLeido = {cadaProveedor.getNombre(), cadaProveedor.getProducto(), cadaProveedor.getGarantia(), cadaProveedor.getPrecio()};
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
        modelo.addColumn("Nombre");
        modelo.addColumn("Producto");
        modelo.addColumn("Garantia");
        modelo.addColumn("Precio");

        LeerDatos();
    }

    private void LeerDatos() {
        try {
            List<Proveedores> listaProveedores= new NegocioProveedores().Leer();
            modelo.setRowCount(0);
            for (Proveedores proveedores : listaProveedores) {
                Object[] registroLeido = {proveedores.getNombre(), proveedores.getProducto(), proveedores.getGarantia(), proveedores.getPrecio()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Proveedores");
        frame1.setContentPane(new frmProveedores().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}



