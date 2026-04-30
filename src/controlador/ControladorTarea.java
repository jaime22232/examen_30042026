package controlador;

import modelo.Tarea;
import vista.VistaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorTarea implements ActionListener {

    private VistaPrincipal vista;
    private List<Tarea> listaTareas;

    public ControladorTarea(VistaPrincipal vista) {
        this.vista = vista;
        this.listaTareas = new ArrayList<>();

        // Suscribimos el controlador a los eventos de los botones de la vista
        this.vista.getBtnRegistrar().addActionListener(this);
        this.vista.getBtnBuscar().addActionListener(this);
        this.vista.getBtnCambiarEstado().addActionListener(this);
        this.vista.getBtnEliminar().addActionListener(this);
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Capturamos quién desencadenó el evento
        if (e.getSource() == vista.getBtnRegistrar()) {
            registrarTarea();
        }
        // TODO: Implementar las acciones de los demás botones en el Entregable 4
    }

    private void registrarTarea() {
        String codigo = vista.getTxtCodigo().getText();
        String titulo = vista.getTxtTitulo().getText();
        String curso = vista.getTxtCurso().getText();
        String fecha = vista.getTxtFecha().getText();
        String estado = vista.getCbEstado().getSelectedItem().toString();

        // Creamos el modelo y lo guardamos
        Tarea nuevaTarea = new Tarea(codigo, titulo, curso, fecha, estado);
        listaTareas.add(nuevaTarea);

        // Actualizamos la pantalla y limpiamos
        actualizarListaTareas();
        vista.limpiarFormulario();

        System.out.println("Tarea registrada en memoria: " + nuevaTarea.getCodigo());
    }

    private void actualizarListaTareas() {
        // Borramos el área de texto y concatenamos todo de nuevo
        vista.getTxtAreaTareas().setText("");
        for (Tarea t : listaTareas) {
            vista.getTxtAreaTareas().append(t.toString() + "\n");
        }
    }
}