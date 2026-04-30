package modelo;
public class Tarea  {
    private String codigo;
    private String titulo;
    private String curso;
    private String fechaEntrega;
    private String estado;


    public Tarea()
    {}
    public Tarea(String codigo, String titulo, String curso, String fechaEntrega, String estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.curso = curso;
        this.fechaEntrega = fechaEntrega;
        this.estado = estado;
    }

    // --- Getters y Setters ---
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }

    public String getFechaEntrega() { return fechaEntrega; }
    public void setFechaEntrega(String fechaEntrega) { this.fechaEntrega = fechaEntrega; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // Sobreescribimos el toString para facilitar la visualización en la vista
    @Override
    public String toString() {
        return "[" + codigo + "] " + titulo + " | Curso: " + curso +
                " | Entrega: " + fechaEntrega + " | Estado: " + estado;
    }
}