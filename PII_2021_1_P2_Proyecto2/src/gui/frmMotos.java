package gui;


import Negocio.NegocioMotos;
import Recursos.Motos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmMotos {
    private JPanel jpaPrincipal;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JButton btnActualizar;
    private JLabel lblMarca;
    private JLabel lblColor;
    private JLabel lblPlaca;
    private JLabel lblReparacion;
    private JTextField txtReparacion;
    private JTextField txtMarca;
    private JTextField txtPlaca;
    private JTextField txtColor;
    private JTable tblDatos;
    private JPanel jpaDerecho;
    private JPanel jpaIzquierdo;
    private JPanel jpaSuperior;
    private JScrollPane sclPanDatos;
    DefaultTableModel modelo;

    public frmMotos() {
        iniciar();
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Motos motos = new Motos();
                    motos.setMarca(txtMarca.getText());
                    motos.setColor(txtColor.getText());
                    motos.setPlaca(txtPlaca.getText());
                    motos.setReparacion(txtReparacion.getText());
                    String respuesta = new NegocioMotos().Insertar(motos);

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
                txtMarca.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtColor.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtPlaca.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtReparacion.setText(modelo.getValueAt(filaSeleccionada,3).toString());
            }
        });



        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Motos motos = new Motos();
                    motos.setMarca(txtMarca.getText());
                    motos.setColor(txtColor.getText());
                    motos.setPlaca(txtPlaca.getText());
                    motos.setReparacion(txtReparacion.getText());

                    new NegocioMotos().Eliminar(motos);
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

                    Motos motos = new Motos();
                    motos.setMarca(txtMarca.getText());
                    motos.setColor(txtColor.getText());
                    motos.setPlaca(txtPlaca.getText());
                    motos.setReparacion(txtReparacion.getText());
                    new NegocioMotos().Actualizar(motos);

                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Motos motos = new Motos();
                    motos.setMarca(txtMarca.getText());

                    List<Motos> listaMotos = new NegocioMotos().Buscar(motos);
                    modelo.setRowCount(0);

                    for (Motos cadaMoto: listaMotos) {
                        Object[] registroLeido = {cadaMoto.getMarca(), cadaMoto.getColor(), cadaMoto.getPlaca(), cadaMoto.getReparacion()};
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
        modelo.addColumn("Marca");
        modelo.addColumn("Color");
        modelo.addColumn("Placa");
        modelo.addColumn("Reparacion");

        LeerDatos();
    }

    private void LeerDatos() {
        try {
            List<Motos> listaMotos= new NegocioMotos().Leer();
            modelo.setRowCount(0);
            for (Motos motos : listaMotos) {
                Object[] registroLeido = {motos.getMarca(), motos.getColor(), motos.getPlaca(), motos.getReparacion()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Motos");
        frame1.setContentPane(new frmMotos().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }

}