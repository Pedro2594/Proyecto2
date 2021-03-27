package gui;

import Negocio.NegocioRepuestos;
import Recursos.Repuestos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class frmRepuestos {
    private JPanel jpaPrincipal;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JTextField txtTipo;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextField txtMarca;
    private JLabel lblTipo;
    private JLabel lblMarca;
    private JLabel lblCantidad;
    private JLabel lblPrecio;
    private JTable tblDatos;
    DefaultTableModel modelo;

    public frmRepuestos() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Repuestos repuestos = new Repuestos();
                    repuestos.setTipo(txtTipo.getText());
                    repuestos.setMarca(txtMarca.getText());
                    repuestos.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    repuestos.setPrecio(Integer.parseInt(txtPrecio.getText()));
                    String respuesta = new NegocioRepuestos().Insertar(repuestos);

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
                txtTipo.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtMarca.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtCantidad.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtPrecio.setText(modelo.getValueAt(filaSeleccionada,3).toString());
            }
        });



        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Repuestos repuestos = new Repuestos();
                    repuestos.setTipo(txtTipo.getText());
                    repuestos.setMarca(txtMarca.getText());
                    repuestos.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    repuestos.setPrecio(Integer.parseInt(txtPrecio.getText()));

                    new NegocioRepuestos().Eliminar(repuestos);
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

                    Repuestos repuestos  = new Repuestos();
                    repuestos.setTipo(txtTipo.getText());
                    repuestos.setMarca(txtMarca.getText());
                    repuestos.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    repuestos.setPrecio(Integer.parseInt(txtPrecio.getText()));
                    new NegocioRepuestos().Actualizar(repuestos);

                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Repuestos repuestos = new Repuestos();
                    repuestos.setTipo(txtTipo.getText());

                    List<Repuestos> listaRepuestos = new NegocioRepuestos().Buscar(repuestos);
                    modelo.setRowCount(0);

                    for (Repuestos cadaRepuesto: listaRepuestos) {
                        Object[] registroLeido = {cadaRepuesto.getTipo(), cadaRepuesto.getMarca(),cadaRepuesto.getCantidad(), cadaRepuesto.getPrecio()};
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
        modelo.addColumn("Tipo");
        modelo.addColumn("Marca");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Precio");

        LeerDatos();
    }

    private void LeerDatos() {
        try {
            List<Repuestos> listaRepuestos = new NegocioRepuestos().Leer();
            modelo.setRowCount(0);
            for (Repuestos repuestos : listaRepuestos) {
                Object[] registroLeido = {repuestos.getTipo(), repuestos.getMarca(), repuestos.getCantidad(), repuestos.getPrecio()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Repuestos");
        frame1.setContentPane(new frmRepuestos().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}

