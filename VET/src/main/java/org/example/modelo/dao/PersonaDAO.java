package org.example.modelo.dao;



import org.example.modelo.conexion.ConexionDB;
import org.example.modelo.vo.PersonaVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public boolean registrarPersona(PersonaVO persona) {
        String sql = "INSERT INTO persona (documento, nombre, apellido, telefono, direccion, email) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persona.getDocumento());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, persona.getTelefono());
            stmt.setString(5, persona.getDireccion());
            stmt.setString(6, persona.getEmail());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar persona: " + e.getMessage());
            return false;
        }
    }

    public PersonaVO consultarPersona(String documento) {
        String sql = "SELECT * FROM persona WHERE documento = ?";
        PersonaVO persona = null;

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                persona = new PersonaVO();
                persona.setDocumento(rs.getString("documento"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setEmail(rs.getString("email"));
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar persona: " + e.getMessage());
        }

        return persona;
    }
    public boolean actualizarPersona(PersonaVO persona) {
        String sql = "UPDATE persona SET nombre = ?, apellido = ?, telefono = ?, direccion = ?, email = ? WHERE documento = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getTelefono());
            stmt.setString(4, persona.getDireccion());
            stmt.setString(5, persona.getEmail());
            stmt.setString(6, persona.getDocumento());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar persona: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPersona(String documento) {
        String sql = "DELETE FROM persona WHERE documento = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, documento);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar persona: " + e.getMessage());
            return false;
        }
    }

    public List<PersonaVO> consultarTodasLasPersonas() {
        String sql = "SELECT * FROM persona ORDER BY nombre, apellido";
        List<PersonaVO> listaPersonas = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PersonaVO persona = new PersonaVO();
                persona.setDocumento(rs.getString("documento"));
                persona.setNombre(rs.getString("nombre"));
                persona.setApellido(rs.getString("apellido"));
                persona.setTelefono(rs.getString("telefono"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setEmail(rs.getString("email"));

                listaPersonas.add(persona);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar todas las personas: " + e.getMessage());
        }

        return listaPersonas;
    }

    public boolean existePersona(String documento) {
        String sql = "SELECT COUNT(*) FROM persona WHERE documento = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, documento);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de persona: " + e.getMessage());
        }

        return false;
    }
}
