package org.example.controlador;


import org.example.modelo.dao.PersonaDAO;
import org.example.modelo.dao.MascotaDAO;
import org.example.modelo.vo.PersonaVO;
import org.example.modelo.vo.MascotaVO;
import org.example.vista.VentanaPersona;
import org.example.vista.VentanaMascota;
import org.example.vista.VentanaPrincipal;

import java.util.List;

public class Coordinador {

    private PersonaDAO personaDAO;
    private MascotaDAO mascotaDAO;
    private VentanaPersona ventanaPersona;
    private VentanaMascota ventanaMascota;
    private VentanaPrincipal ventanaPrincipal;

    public Coordinador() {
        this.personaDAO = new PersonaDAO();
        this.mascotaDAO = new MascotaDAO();
    }

    public void setVentanaPersona(VentanaPersona ventanaPersona) {
        this.ventanaPersona = ventanaPersona;
    }

    public void setVentanaMascota(VentanaMascota ventanaMascota) {
        this.ventanaMascota = ventanaMascota;
    }

    public void setVentanaPrincipal(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void mostrarVentanaPersona() {
        if (ventanaPersona == null) {
            ventanaPersona = new VentanaPersona(ventanaPrincipal, true);
            ventanaPersona.setCoordinador(this);
        }
        ventanaPersona.setVisible(true);
    }

    public void mostrarVentanaMascota() {
        if (ventanaMascota == null) {
            ventanaMascota = new VentanaMascota(ventanaPrincipal, true);
            ventanaMascota.setCoordinador(this);
        }
        ventanaMascota.setVisible(true);
    }

    public String registrarPersona(PersonaVO persona) {
        if (personaDAO.existePersona(persona.getDocumento())) {
            return "Error: Ya existe una persona registrada con el documento " + persona.getDocumento();
        }

        boolean resultado = personaDAO.registrarPersona(persona);
        if (resultado) {
            return persona.toString();
        } else {
            return "Error al registrar la persona";
        }
    }

    public String consultarPersona(String documento) {
        PersonaVO persona = personaDAO.consultarPersona(documento);
        if (persona != null) {
            return persona.toString();
        } else {
            return "No se encontró ninguna persona con el documento: " + documento;
        }
    }

    public PersonaVO obtenerPersona(String documento) {
        return personaDAO.consultarPersona(documento);
    }

    public String actualizarPersona(PersonaVO persona) {
        if (!personaDAO.existePersona(persona.getDocumento())) {
            return "Error: No existe una persona con el documento " + persona.getDocumento();
        }

        boolean resultado = personaDAO.actualizarPersona(persona);
        if (resultado) {
            return persona.toString();
        } else {
            return "Error al actualizar la persona";
        }
    }

    public String eliminarPersona(String documento) {
        if (!personaDAO.existePersona(documento)) {
            return "Error: No existe una persona con el documento " + documento;
        }

        boolean resultado = personaDAO.eliminarPersona(documento);
        if (resultado) {
            return "Persona con documento " + documento + " eliminada satisfactoriamente";
        } else {
            return "Error al eliminar la persona";
        }
    }

    public String consultarTodasLasPersonas() {
        List<PersonaVO> personas = personaDAO.consultarTodasLasPersonas();
        if (personas.isEmpty()) {
            return "No existen personas registradas en el sistema";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE PERSONAS REGISTRADAS ===\n\n");
        for (int i = 0; i < personas.size(); i++) {
            sb.append("PERSONA ").append(i + 1).append(":\n");
            sb.append(personas.get(i).toString()).append("\n");
            if (i < personas.size() - 1) {
                sb.append("----------------------------------------\n");
            }
        }
        return sb.toString();
    }

    public String registrarMascota(MascotaVO mascota) {
        if (!personaDAO.existePersona(mascota.getPropietarioDocumento())) {
            return "Error: No existe un propietario registrado con el documento " + mascota.getPropietarioDocumento();
        }

        boolean resultado = mascotaDAO.registrarMascota(mascota);
        if (resultado) {
            List<MascotaVO> mascotas = mascotaDAO.consultarMascotasPorPropietario(mascota.getPropietarioDocumento());
            for (MascotaVO m : mascotas) {
                if (m.getNombre().equals(mascota.getNombre()) &&
                        m.getEspecie().equals(mascota.getEspecie()) &&
                        m.getRaza().equals(mascota.getRaza())) {
                    return m.toString();
                }
            }
            return "Mascota registrada exitosamente";
        } else {
            return "Error al registrar la mascota";
        }
    }

    public String consultarMascota(int id) {
        MascotaVO mascota = mascotaDAO.consultarMascota(id);
        if (mascota != null) {
            return mascota.toString();
        } else {
            return "No se encontró ninguna mascota con el ID: " + id;
        }
    }

    public MascotaVO obtenerMascota(int id) {
        return mascotaDAO.consultarMascota(id);
    }

    public String actualizarMascota(MascotaVO mascota) {
        if (!mascotaDAO.existeMascota(mascota.getId())) {
            return "Error: No existe una mascota con el ID " + mascota.getId();
        }

        if (!personaDAO.existePersona(mascota.getPropietarioDocumento())) {
            return "Error: No existe un propietario registrado con el documento " + mascota.getPropietarioDocumento();
        }

        boolean resultado = mascotaDAO.actualizarMascota(mascota);
        if (resultado) {
            MascotaVO mascotaActualizada = mascotaDAO.consultarMascota(mascota.getId());
            return mascotaActualizada.toString();
        } else {
            return "Error al actualizar la mascota";
        }
    }

    public String eliminarMascota(int id) {
        if (!mascotaDAO.existeMascota(id)) {
            return "Error: No existe una mascota con el ID " + id;
        }

        boolean resultado = mascotaDAO.eliminarMascota(id);
        if (resultado) {
            return "Mascota con ID " + id + " eliminada satisfactoriamente";
        } else {
            return "Error al eliminar la mascota";
        }
    }

    public String consultarTodasLasMascotas() {
        List<MascotaVO> mascotas = mascotaDAO.consultarTodasLasMascotas();
        if (mascotas.isEmpty()) {
            return "No existen mascotas registradas en el sistema";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== LISTA DE MASCOTAS REGISTRADAS ===\n\n");
        for (int i = 0; i < mascotas.size(); i++) {
            sb.append("MASCOTA ").append(i + 1).append(":\n");
            sb.append(mascotas.get(i).toString()).append("\n");
            if (i < mascotas.size() - 1) {
                sb.append("----------------------------------------\n");
            }
        }
        return sb.toString();
    }

    public String consultarMascotasPorPropietario(String documento) {
        if (!personaDAO.existePersona(documento)) {
            return "Error: No existe una persona registrada con el documento " + documento;
        }

        List<MascotaVO> mascotas = mascotaDAO.consultarMascotasPorPropietario(documento);

        if (mascotas.isEmpty()) {
            PersonaVO propietario = personaDAO.consultarPersona(documento);
            return "El propietario " + propietario.getNombre() + " " + propietario.getApellido() +
                    " (documento: " + documento + ") no tiene mascotas registradas";
        }
        StringBuilder sb = new StringBuilder();
        PersonaVO propietario = personaDAO.consultarPersona(documento);
        sb.append("=== MASCOTAS DE ").append(propietario.getNombre().toUpperCase())
                .append(" ").append(propietario.getApellido().toUpperCase()).append(" ===\n");
        sb.append("Documento del propietario: ").append(documento).append("\n\n");

        for (int i = 0; i < mascotas.size(); i++) {
            sb.append("MASCOTA ").append(i + 1).append(":\n");
            sb.append(mascotas.get(i).toString()).append("\n");
            if (i < mascotas.size() - 1) {
                sb.append("----------------------------------------\n");
            }
        }

        return sb.toString();
    }
}
