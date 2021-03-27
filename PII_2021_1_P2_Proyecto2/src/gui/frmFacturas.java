package gui;

import Negocio.NegocioClientes;
import Negocio.NegocioFacturas;
import Recursos.Clientes;
import Recursos.Factura;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;

public class frmFacturas {
    private JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JPanel jpaIzquierdo;
    private JPanel jpaDerecho;
    private JButton btnRegistrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnListar;
    private JLabel lblCodigo;
    private JLabel lblDNICliente;
    private JLabel lblNombreProducto;
    private JLabel lblPrecioProducto;
    private JLabel lblCantidad;
    private JTextField txtCodigo;
    private JTextField txtDNICliente;
    private JTextField txtNombreProducto;
    private JTextField txtPrecioProducto;
    private JTextField txtCantidad;
    private JTable tblDatos;
    private JPanel jpaInferior;
    private JScrollPane sclPanDatos;

    DefaultTableModel modelo;

    public frmFacturas() {
         iniciar();

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Factura factura = new Factura();
                    factura.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    factura.setDNICliente(Long.parseLong(txtDNICliente.getText()));
                    factura.setNombreProducto(txtNombreProducto.getText());
                    factura.setPrecioProducto(Integer.parseInt(txtPrecioProducto.getText()));
                    factura.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    String respuesta = new NegocioFacturas().Insertar(factura);
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
                txtDNICliente.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtNombreProducto.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtPrecioProducto.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtCantidad.setText(modelo.getValueAt(filaSeleccionada,4).toString());

            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Factura factura = new Factura();
                    factura.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    factura.setDNICliente(Long.parseLong(txtDNICliente.getText()));
                    factura.setNombreProducto(txtNombreProducto.getText());
                    factura.setPrecioProducto(Integer.parseInt(txtPrecioProducto.getText()));
                    factura.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    new NegocioFacturas().Actualizar(factura);
                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });


        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Factura factura = new Factura();
                    factura.setCodigo(Integer.parseInt(txtCodigo.getText()));
                    factura.setDNICliente(Long.parseLong(txtDNICliente.getText()));
                    factura.setNombreProducto(txtNombreProducto.getText());
                    factura.setPrecioProducto(Integer.parseInt(txtPrecioProducto.getText()));
                    factura.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    new NegocioFacturas().Eliminar(factura);
                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Factura factura = new Factura();
                    factura.setCodigo(Integer.parseInt(txtCodigo.getText()));

                    List<Factura> listaFacturas = new NegocioFacturas().Buscar(factura);
                    modelo.setRowCount(0);

                    for (Factura cadaFactura: listaFacturas) {
                        Object[] registroLeido = {cadaFactura.getCodigo(), cadaFactura.getDNICliente(),cadaFactura.getNombreProducto(),cadaFactura.getPrecioProducto(),cadaFactura.getCantidad()};
                        modelo.addRow(registroLeido);
                    }

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeerDatos();
            }
        });
    }

    private void iniciar () {
        modelo = (DefaultTableModel) tblDatos.getModel();
        modelo.addColumn("Codigo");
        modelo.addColumn("DNICliente");
        modelo.addColumn("NombreProducto");
        modelo.addColumn("PrecioProducto");
        modelo.addColumn("Cantidad");

        LeerDatos();
    }
    private void LeerDatos() {
        try {
            List<Factura> listaFacturas = new NegocioFacturas().Leer();
            modelo.setRowCount(0);
            for (Factura factura: listaFacturas) { Object[] registroLeido = {factura.getCodigo(), factura.getDNICliente(), factura.getNombreProducto(),factura.getPrecioProducto(),factura.getCantidad()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        }catch (Exception e){
        }
    }
    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Facturas");
        frame1.setContentPane(new frmFacturas().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }


}

