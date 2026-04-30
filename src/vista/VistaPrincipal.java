package vista;

import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {

    // Componentes del formulario
    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtCurso;
    private JTextField txtFecha;
    private JComboBox<String> cbEstado;

    // Botones de acción requeridos en el problema
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnCambiarEstado;
    private JButton btnEliminar;

    // Área para listar las tareas
    private JTextArea txtAreaTareas;

    public VistaPrincipal() {
        // Configuración principal de la ventana
        setTitle("Gestión de Tareas Académicas");
        setSize(650, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla
        setLayout(new BorderLayout(10, 10)); // Espaciado entre paneles

        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // --- Panel Norte: Formulario de Registro ---
        JPanel panelNorte = new JPanel(new GridLayout(6, 2, 5, 5));
        panelNorte.setBorder(BorderFactory.createTitledBorder("Registro de Nueva Tarea"));

        panelNorte.add(new JLabel(" Código de Tarea:"));
        txtCodigo = new JTextField();
        panelNorte.add(txtCodigo);

        panelNorte.add(new JLabel(" Título:"));
        txtTitulo = new JTextField();
        panelNorte.add(txtTitulo);

        panelNorte.add(new JLabel(" Curso:"));
        txtCurso = new JTextField();
        panelNorte.add(txtCurso);

        panelNorte.add(new JLabel(" Fecha de Entrega:"));
        txtFecha = new JTextField();
        panelNorte.add(txtFecha);

        panelNorte.add(new JLabel(" Estado:"));
        String[] estados = {"Pendiente", "En proceso", "Completada"};
        cbEstado = new JComboBox<>(estados);
        panelNorte.add(cbEstado);

        panelNorte.add(new JLabel("")); // Celda vacía para cuadrar el grid
        btnRegistrar = new JButton("Registrar Tarea");
        panelNorte.add(btnRegistrar);

        add(panelNorte, BorderLayout.NORTH);

        // --- Panel Central: Lista de Tareas ---
        txtAreaTareas = new JTextArea();
        txtAreaTareas.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtAreaTareas);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Listado de Tareas"));
        add(scrollPane, BorderLayout.CENTER);

        // --- Panel Sur: Botones de Gestión ---
        JPanel panelSur = new JPanel(new FlowLayout());

        btnBuscar = new JButton("Buscar Tarea");
        btnCambiarEstado = new JButton("Cambiar Estado");
        btnEliminar = new JButton("Eliminar Tarea");

        panelSur.add(btnBuscar);
        panelSur.add(btnCambiarEstado);
        panelSur.add(btnEliminar);

        add(panelSur, BorderLayout.SOUTH);
    }

    // Getters

    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtTitulo() { return txtTitulo; }
    public JTextField getTxtCurso() { return txtCurso; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JComboBox<String> getCbEstado() { return cbEstado; }
    public JTextArea getTxtAreaTareas() { return txtAreaTareas; }

    public JButton getBtnRegistrar() { return btnRegistrar; }
    public JButton getBtnBuscar() { return btnBuscar; }
    public JButton getBtnCambiarEstado() { return btnCambiarEstado; }
    public JButton getBtnEliminar() { return btnEliminar; }

    // Método de utilidad para limpiar el formulario rápido
    public void limpiarFormulario() {
        txtCodigo.setText("");
        txtTitulo.setText("");
        txtCurso.setText("");
        txtFecha.setText("");
        cbEstado.setSelectedIndex(0);
        txtCodigo.requestFocus();
    }
}