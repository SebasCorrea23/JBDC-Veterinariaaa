package org.example.modelo.dao;



import org.example.modelo.conexion.ConexionDB;
import org.example.modelo.vo.MascotaVO;
import org.example.modelo.vo.PersonaVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MascotaDAO {

    private PersonaDAO personaDAO = new PersonaDAO();

    public boolean registrarMascota(MascotaVO mascota) {
        String sql = "INSERT INTO mascota (nombre, especie, raza, edad, peso, color, propietario_documento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setDouble(5, mascota.getPeso());
            stmt.setString(6, mascota.getColor());
            stmt.setString(7, mascota.getPropietarioDocumento());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al registrar mascota: " + e.getMessage());
            return false;
        }
    }

    public MascotaVO consultarMascota(int id) {
        String sql = "SELECT * FROM mascota WHERE id = ?";
        MascotaVO mascota = null;

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                mascota = new MascotaVO();
                mascota.setId(rs.getInt("id"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setColor(rs.getString("color"));
                mascota.setPropietarioDocumento(rs.getString("propietario_documento"));

                PersonaVO propietario = personaDAO.consultarPersona(mascota.getPropietarioDocumento());
                mascota.setPropietario(propietario);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar mascota: " + e.getMessage());
        }

        return mascota;
    }

    public boolean actualizarMascota(MascotaVO mascota) {
        String sql = "UPDATE mascota SET nombre = ?, especie = ?, raza = ?, edad = ?, peso = ?, color = ?, propietario_documento = ? WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, mascota.getNombre());
            stmt.setString(2, mascota.getEspecie());
            stmt.setString(3, mascota.getRaza());
            stmt.setInt(4, mascota.getEdad());
            stmt.setDouble(5, mascota.getPeso());
            stmt.setString(6, mascota.getColor());
            stmt.setString(7, mascota.getPropietarioDocumento());
            stmt.setInt(8, mascota.getId());

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar mascota: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarMascota(int id) {
        String sql = "DELETE FROM mascota WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar mascota: " + e.getMessage());
            return false;
        }
    }

    public List<MascotaVO> consultarTodasLasMascotas() {
        String sql = "SELECT * FROM mascota ORDER BY nombre";
        List<MascotaVO> listaMascotas = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setId(rs.getInt("id"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setColor(rs.getString("color"));
                mascota.setPropietarioDocumento(rs.getString("propietario_documento"));

                PersonaVO propietario = personaDAO.consultarPersona(mascota.getPropietarioDocumento());
                mascota.setPropietario(propietario);

                listaMascotas.add(mascota);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar todas las mascotas: " + e.getMessage());
        }

        return listaMascotas;
    }

    public boolean existeMascota(int id) {
        String sql = "SELECT COUNT(*) FROM mascota WHERE id = ?";

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar existencia de mascota: " + e.getMessage());
        }

        return false;
    }

    public List<MascotaVO> consultarMascotasPorPropietario(String documentoPropietario) {
        String sql = "SELECT * FROM mascota WHERE propietario_documento = ? ORDER BY nombre";
        List<MascotaVO> listaMascotas = new ArrayList<>();

        try (Connection conn = ConexionDB.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, documentoPropietario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MascotaVO mascota = new MascotaVO();
                mascota.setId(rs.getInt("id"));
                mascota.setNombre(rs.getString("nombre"));
                mascota.setEspecie(rs.getString("especie"));
                mascota.setRaza(rs.getString("raza"));
                mascota.setEdad(rs.getInt("edad"));
                mascota.setPeso(rs.getDouble("peso"));
                mascota.setColor(rs.getString("color"));
                mascota.setPropietarioDocumento(rs.getString("propietario_documento"));

                PersonaVO propietario = personaDAO.consultarPersona(mascota.getPropietarioDocumento());
                mascota.setPropietario(propietario);

                listaMascotas.add(mascota);
            }

        } catch (SQLException e) {
            System.err.println("Error al consultar mascotas por propietario: " + e.getMessage());
        }

        return listaMascotas;
    }
}
