package gui;

import Negocio.NegocioClientes;
import Negocio.NegocioProducto;
import Recursos.Clientes;
import Recursos.Producto;

import javax.print.attribute.standard.Finishings;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmProductos {
    private JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JPanel jpaIzquierdo;
    private JPanel jpaDerecho;
    private JPanel jpaInferior;
    private JButton btnRegistrar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnActualizar;
    private JButton btnListar;
    private JLabel lblCodigo;
    private JLabel lblNombre;
    private JLabel lblMarca;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtMarca;
    private JTable tblDatos;
    private JScrollPane sclPanDatos;
    DefaultTableModel modelo;
    public frmProductos() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Producto producto = new Producto();
                   producto.setCodigo(Integer.parseInt(txtCodigo.getText()));
                   producto.setNombre(txtNombre.getText());
                   producto.setMarca(txtMarca.getText());
                    String respuesta = new NegocioProducto().Insertar(producto);

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
                txtCodigo.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtNombre.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtMarca.setText(modelo.getValueAt(filaSeleccionada,2).toString());
            }
        });



        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Producto producto = new Producto();
                    producto.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    producto.setNombre(txtNombre.getText());
                    producto.setMarca(txtMarca.getText());

                      new NegocioProducto().Eliminar(producto);
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

                    Producto producto = new Producto();

                    producto.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    producto.setNombre(txtNombre.getText());
                    producto.setMarca(txtMarca.getText());

                   new NegocioProducto().Actualizar(producto);

                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Producto producto = new Producto();
                   producto.setCodigo(Integer.parseInt(txtCodigo.getText()));

                    List<Producto> listaProductos = new NegocioProducto().Buscar(producto);
                    modelo.setRowCount(0);

                    for (Producto cadaProducto: listaProductos) {
                        Object[] registroLeido = {cadaProducto.getCodigo(), cadaProducto.getNombre(),cadaProducto.getMarca()};
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
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Marca");

        LeerDatos();
    }

    private void LeerDatos() {
        try {
            List<Producto> listaProductos = new NegocioProducto().Leer();
            modelo.setRowCount(0);
            for (Producto producto : listaProductos) {
                Object[] registroLeido = {producto.getCodigo(), producto.getNombre(), producto.getMarca()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Productos");
        frame1.setContentPane(new frmProductos().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}
