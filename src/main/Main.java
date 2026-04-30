package main;

import vista.VistaPrincipal;
import controlador.ControladorTarea;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Iniciando sistema...");

        VistaPrincipal vista = new VistaPrincipal();

        ControladorTarea controlador = new ControladorTarea(vista);

        controlador.iniciar();
    }

}