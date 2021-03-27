package gui;

import Negocio.NegocioCarro;

import Recursos.Carro;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class frmCarro {
    private JPanel jpaPrincipal;
    private JPanel jpaSuperior;
    private JButton btnRegistrar;
    private JButton btnEliminar;
    private JButton btnListar;
    private JPanel jpaInferior;
    private JButton btnActualizar;
    private JButton btnBuscar;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtColor;
    private JTextField txtPlaca;
    private JTextField txtReparacion;
    private JScrollPane sclPanDatos;
    private JPanel jpaIzquierdo;
    private JLabel lblMarca;
    private JLabel lblModelo;
    private JLabel lblColor;
    private JLabel lblPlaca;
    private JLabel lblReparacion;
    private JPanel jpaDerecho;
    private JLabel Reparacion;
    private JTable tblDatos;
    DefaultTableModel modelo;

    public frmCarro() {
        iniciar();

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Carro carro = new Carro();
                    carro.setMarca(txtMarca.getText());
                    carro.setModelo(txtModelo.getText());
                    carro.setPlaca(Integer.parseInt(txtPlaca.getText()));
                    carro.setColor(txtColor.getText());
                    carro.setReparacion(txtReparacion.getText());
                    String respuesta = new NegocioCarro().Insertar(carro);

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
                super.mouseClicked(e);
                int filaSeleccionada = tblDatos.getSelectedRow();
                txtMarca.setText(modelo.getValueAt(filaSeleccionada,0).toString());
                txtModelo.setText(modelo.getValueAt(filaSeleccionada,1).toString());
                txtColor.setText(modelo.getValueAt(filaSeleccionada,2).toString());
                txtPlaca.setText(modelo.getValueAt(filaSeleccionada,3).toString());
                txtReparacion.setText(modelo.getValueAt(filaSeleccionada,4).toString());
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    Carro carro = new Carro();
                    carro.setMarca(txtMarca.getText());
                    carro.setModelo(txtModelo.getText());
                    carro.setColor(txtColor.getText());
                    carro.setPlaca(Integer.parseInt(txtPlaca.getText()));
                    carro.setReparacion(txtReparacion.getText());
                    new NegocioCarro().Eliminar(carro);

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
                    Carro carro = new Carro();
                    carro.setMarca(txtMarca.getText());
                    carro.setModelo(txtModelo.getText());
                    carro.setColor(txtColor.getText());
                    carro.setPlaca(Integer.parseInt(txtPlaca.getText()));
                    carro.setReparacion(txtReparacion.getText());
                    new NegocioCarro().Actualizar(carro);

                    LeerDatos();
                }catch (Exception ex){
                }
            }
        });
        btnBuscar.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Carro carro = new Carro();
                    carro.setPlaca(Integer.parseInt(txtPlaca.getText()));

                    List<Carro> listaCarros = new NegocioCarro().Buscar(carro);
                    modelo.setRowCount(0);

                    for (Carro cadaCarro: listaCarros) {
                        Object[] registroLeido = {cadaCarro.getMarca(), cadaCarro.getModelo(), cadaCarro.getPlaca(),cadaCarro.getColor(),cadaCarro.getReparacion()};
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
        modelo.addColumn("Modelo");
        modelo.addColumn("Placa");
        modelo.addColumn("Color");
        modelo.addColumn("Reparacion");
        LeerDatos();
    }
    private void LeerDatos() {
        try {
            List<Carro> listaCarros = new NegocioCarro().Leer();
            modelo.setRowCount(0);
            for (Carro carro: listaCarros) { Object[] registroLeido = { carro.getMarca(), carro.getModelo(),carro.getColor(),carro.getPlaca(),carro.getReparacion()};
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);

        }catch (Exception e){
        }
    }


    public static void main(String[] args) {
        JFrame frame1 = new JFrame("Carros");
        frame1.setContentPane(new frmCarro().jpaPrincipal);
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.pack();
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }


}