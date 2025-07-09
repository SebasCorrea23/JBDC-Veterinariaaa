package org.example.modelo.vo;

public class MascotaVO {

    private int id;
    private String nombre;
    private String especie;
    private String raza;
    private int edad;
    private double peso;
    private String color;
    private String propietarioDocumento;
    private PersonaVO propietario; // Instancia de la clase Persona

    public MascotaVO() {
    }

    public MascotaVO(int id, String nombre, String especie, String raza, int edad, double peso, String color, String propietarioDocumento) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.color = color;
        this.propietarioDocumento = propietarioDocumento;
    }

    public MascotaVO(String nombre, String especie, String raza, int edad, double peso, String color, String propietarioDocumento) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.color = color;
        this.propietarioDocumento = propietarioDocumento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPropietarioDocumento() {
        return propietarioDocumento;
    }

    public void setPropietarioDocumento(String propietarioDocumento) {
        this.propietarioDocumento = propietarioDocumento;
    }

    public PersonaVO getPropietario() {
        return propietario;
    }

    public void setPropietario(PersonaVO propietario) {
        this.propietario = propietario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nombre: ").append(nombre).append("\n");
        sb.append("Especie: ").append(especie).append("\n");
        sb.append("Raza: ").append(raza).append("\n");
        sb.append("Edad: ").append(edad).append(" años\n");
        sb.append("Peso: ").append(peso).append(" kg\n");
        sb.append("Color: ").append(color).append("\n");
        sb.append("Documento del Propietario: ").append(propietarioDocumento).append("\n");

        if (propietario != null) {
            sb.append("Nombre del Propietario: ").append(propietario.getNombreCompleto()).append("\n");
        } else {
            sb.append("Propietario: No encontrado en el sistema\n");
        }

        return sb.toString();
    }
}
