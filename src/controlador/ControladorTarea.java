package controlador;

import modelo.Tarea;
import vista.VistaPrincipal;

import javax.swing.JOptionPane;
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
        // eventos
        if (e.getSource() == vista.getBtnRegistrar()) {
            registrarTarea();
        } else if (e.getSource()==vista.getBtnBuscar()) {
            buscarTarea();
        } else if (e.getSource() == vista.getBtnCambiarEstado()) {
            cambiarEstado();
        } else if (e.getSource() == vista.getBtnEliminar()) {
            eliminarTarea();
        }

    }

    private void registrarTarea() {
        String codigo = vista.getTxtCodigo().getText();
        String titulo = vista.getTxtTitulo().getText();
        String curso = vista.getTxtCurso().getText();
        String fecha = vista.getTxtFecha().getText();
        String estado = vista.getCbEstado().getSelectedItem().toString();

        // No permitir campos vacíos
        if (codigo.isEmpty() || titulo.isEmpty() || curso.isEmpty() || fecha.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos de texto son obligatorios.",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return; // Retorno temprano (buena práctica)
        }

        // No permitir códigos repetidos
        if (buscarPorCodigo(codigo) != null) {
            JOptionPane.showMessageDialog(vista, "El código de tarea ya se encuentra registrado.",
                    "Error de Validación", JOptionPane.ERROR_MESSAGE);
            return;
        }


        Tarea nuevaTarea = new Tarea(codigo, titulo, curso, fecha, estado);
        listaTareas.add(nuevaTarea);


        actualizarListaTareas();
        vista.limpiarFormulario();

        System.out.println("Tarea registrada en memoria: " + nuevaTarea.getCodigo());
    }
    private void buscarTarea() {
        // Usamos un InputDialog para hacer la búsqueda sin ensuciar el formulario
        String termino = JOptionPane.showInputDialog(vista, "Ingrese el código o título de la tarea a buscar:");

        // Si el usuario cancela o deja vacío, no hacemos nada
        if (termino == null || termino.trim().isEmpty()) return;

        termino = termino.trim().toLowerCase();
        StringBuilder resultados = new StringBuilder();

        // Búsqueda flexible (por código exacto o fragmento del título)
        for (Tarea t : listaTareas) {
            if (t.getCodigo().toLowerCase().equals(termino) || t.getTitulo().toLowerCase().contains(termino)) {
                resultados.append(t.toString()).append("\n");
            }
        }

        if (resultados.length() > 0) {
            JOptionPane.showMessageDialog(vista, "Resultados encontrados:\n" + resultados.toString(),
                    "Resultado de Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontraron tareas con ese criterio.",
                    "Sin resultados", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void cambiarEstado() {
        String codigo = vista.getTxtCodigo().getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, digite el código en el formulario y seleccione el nuevo estado.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Tarea t = buscarPorCodigo(codigo);
        if (t != null) {
            String nuevoEstado = vista.getCbEstado().getSelectedItem().toString();
            t.setEstado(nuevoEstado);
            actualizarListaTareas();
            JOptionPane.showMessageDialog(vista, "Estado de la tarea actualizado a: " + nuevoEstado,
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarFormulario();
        } else {
            JOptionPane.showMessageDialog(vista, "No existe una tarea con el código indicado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarTarea() {
        String codigo = vista.getTxtCodigo().getText().trim();
        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Por favor, digite el código de la tarea que desea eliminar.",
                    "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Tarea t = buscarPorCodigo(codigo);
        if (t != null) {
            // Confirmación de seguridad
            int confirmacion = JOptionPane.showConfirmDialog(vista,
                    "¿Está seguro que desea eliminar la tarea [" + codigo + "]?",
                    "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

            if (confirmacion == JOptionPane.YES_OPTION) {
                listaTareas.remove(t);
                actualizarListaTareas();
                JOptionPane.showMessageDialog(vista, "Tarea eliminada correctamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                vista.limpiarFormulario();
            }
        } else {
            JOptionPane.showMessageDialog(vista, "No existe una tarea con el código indicado.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // --- Métodos Auxiliares ---
    private void actualizarListaTareas() {
        vista.getTxtAreaTareas().setText("");
        for (Tarea t : listaTareas) {
            vista.getTxtAreaTareas().append(t.toString() + "\n");
        }
    }

    private Tarea buscarPorCodigo(String codigo) {
        for (Tarea t : listaTareas) {
            if (t.getCodigo().equalsIgnoreCase(codigo)) {
                return t;
            }
        }
        return null;
    }
}