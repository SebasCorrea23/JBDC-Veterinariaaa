package org.example.controlador;

import org.example.modelo.dao.PersonaDAO;
import org.example.vista.VentanaMascota;
import org.example.vista.VentanaPersona;
import org.example.vista.VentanaPrincipal;

public class Relaciones {
    public Relaciones(){
        Coordinador miCoordinador = new Coordinador();

        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        VentanaPersona ventanaPersona = new VentanaPersona(ventanaPrincipal, true);
        VentanaMascota ventanaMascota = new VentanaMascota(ventanaPrincipal, true);

        ventanaPrincipal.setCoordinador(miCoordinador);
        ventanaPersona.setCoordinador(miCoordinador);
        ventanaMascota.setCoordinador(miCoordinador);

        miCoordinador.setVentanaPrincipal(ventanaPrincipal);
        miCoordinador.setVentanaPersona(ventanaPersona);
        miCoordinador.setVentanaMascota(ventanaMascota);

        ventanaPrincipal.setVisible(true);
    }

    public static void main(String[] args) {
        new Relaciones();
    }
}
