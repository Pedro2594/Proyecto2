package gui;

import Negocio.NegocioInventario;
import Negocio.NegocioMotos;
import Recursos.Inventario;
import Recursos.Motos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmInventario {

    private JPanel jpaPrincipal;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnActualizar;
    private JButton btnListar;
    private JLabel lblCodigo;
    private JLabel lblReferencia;
    private JLabel lblCantidad;
    private JTextField txtCodigo;
    private JTextField txtCantidad;
    private JTextField txtReferencia;
    private JTable tblDatos;
    DefaultTableModel modelo;

    public frmInventario() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Inventario inventario = new Inventario();
                    inventario.setCodigo(Long.parseLong(txtCodigo.getText()));
                    inventario.setReferencia(txtReferencia.getText());
                    inventario.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    String respuesta = new NegocioInventario().Insertar(inventario);

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
                txtReferencia.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtCantidad.setText(modelo.getValueAt(filaSeleccionada,2).toString());
            }
        });



        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Inventario inventario = new Inventario();
                    inventario.setCodigo(Long.parseLong(txtCodigo.getText()));
                    inventario.setReferencia(txtReferencia.getText());
                    inventario.setCantidad(Integer.parseInt(txtCantidad.getText()));

                    new NegocioInventario().Eliminar(inventario);
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

                    Inventario inventario = new Inventario();
                    inventario.setCodigo(Long.parseLong(txtCodigo.getText()));
                    inventario.setReferencia(txtReferencia.getText());
                    inventario.setCantidad(Integer.parseInt(txtCantidad.getText()));
                    new NegocioInventario().Actualizar(inventario);

                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Inventario inventario = new Inventario();
                    inventario.setCodigo(Long.parseLong(txtCodigo.getText()));

                    List<Inventario> listaInventario = new NegocioInventario().Buscar(inventario);
                    modelo.setRowCount(0);

                    for (Inventario cadaInventario: listaInventario) {
                        Object[] registroLeido = {cadaInventario.getCodigo(), cadaInventario.getReferencia(), cadaInventario.getCantidad()};
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
        modelo.addColumn("Referencia");
        modelo.addColumn("Cantidad");

        LeerDatos();
    }

    private void LeerDatos() {
        try {
            List<Inventario> listaInventario = new NegocioInventario().Leer();
            modelo.setRowCount(0);
            for (Inventario inventario : listaInventario) {
                Object[] registroLeido = {inventario.getCodigo(),inventario.getReferencia(), inventario.getCantidad()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Inventario");
        frame1.setContentPane(new frmInventario().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }


}
