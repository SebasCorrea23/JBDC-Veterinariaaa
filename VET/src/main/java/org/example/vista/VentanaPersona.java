package org.example.vista;

import org.example.controlador.Coordinador;
import org.example.modelo.vo.PersonaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPersona extends JFrame implements ActionListener {

    private Coordinador coordinador;

    private JTextField txtDocumento;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtDireccion;
    private JTextField txtEmail;
    private JTextArea txtAreaInfo;

    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnConsultarLista;
    private JButton btnLimpiar;
    private JButton btnVolver;

    public VentanaPersona(VentanaPrincipal ventanaPrincipal, boolean b) {
        initComponents();
        setSize(1000, 500);
        setLocationRelativeTo(null);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setPreferredSize(new Dimension(180, 50));
        boton.setBackground(new Color(10, 10, 10));
        boton.setForeground(new Color(201, 208, 21));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createLineBorder(new Color(201, 208, 21)));
        boton.addActionListener(this);
        return boton;
    }

    private void initComponents() {
        setTitle("Gestión de Personas - Clínica Veterinaria ABC");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        Color fondo = new Color(44, 44, 44);
        Color colorTexto = new Color(201, 208, 21);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(fondo);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(colorTexto);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblTitulo = new JLabel("GESTIÓN DE PERSONAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(10, 10, 10));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelTitulo.add(lblTitulo);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(fondo);
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(colorTexto), "Datos de la Persona",
                0, 0, new Font("Arial", Font.BOLD, 14), colorTexto));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel[] etiquetas = {
                new JLabel("Documento:"), new JLabel("Nombre:"), new JLabel("Apellido:"),
                new JLabel("Teléfono:"), new JLabel("Dirección:"), new JLabel("Email:")
        };

        JTextField[] campos = {
                txtDocumento = new JTextField(18), txtNombre = new JTextField(18), txtApellido = new JTextField(18),
                txtTelefono = new JTextField(18), txtDireccion = new JTextField(18), txtEmail = new JTextField(18)
        };

        for (int i = 0; i < etiquetas.length; i++) {
            etiquetas[i].setForeground(colorTexto);
            gbc.gridx = 0; gbc.gridy = i;
            panelFormulario.add(etiquetas[i], gbc);
            gbc.gridx = 1;
            panelFormulario.add(campos[i], gbc);
        }

        JPanel panelBotones = new JPanel(new GridLayout(2, 4, 10, 10));
        panelBotones.setBackground(fondo);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        btnRegistrar = crearBoton("Registrar");
        btnConsultar = crearBoton("Consultar");
        btnActualizar = crearBoton("Actualizar");
        btnEliminar = crearBoton("Eliminar");
        btnConsultarLista = crearBoton("Consultar Lista");
        btnLimpiar = crearBoton("Limpiar");
        btnVolver = crearBoton("Volver");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnConsultarLista);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVolver);
        panelBotones.add(new JLabel());

        JPanel panelInfo = new JPanel(new BorderLayout());
        panelInfo.setBackground(fondo);
        panelInfo.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(colorTexto), "Información",
                0, 0, new Font("Arial", Font.BOLD, 14), colorTexto));

        txtAreaInfo = new JTextArea(14, 40);
        txtAreaInfo.setEditable(false);
        txtAreaInfo.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtAreaInfo.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(txtAreaInfo);
        panelInfo.add(scrollPane, BorderLayout.CENTER);

        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(fondo);
        panelIzquierdo.add(panelFormulario, BorderLayout.CENTER);
        panelIzquierdo.add(panelBotones, BorderLayout.SOUTH);

        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
        panelPrincipal.add(panelIzquierdo, BorderLayout.WEST);
        panelPrincipal.add(panelInfo, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }

    private void limpiarCampos() {
        txtDocumento.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtAreaInfo.setText("");
    }

    private PersonaVO crearPersonaDesdeFormulario() {
        return new PersonaVO(
                txtDocumento.getText().trim(),
                txtNombre.getText().trim(),
                txtApellido.getText().trim(),
                txtTelefono.getText().trim(),
                txtDireccion.getText().trim(),
                txtEmail.getText().trim()
        );
    }

    private void cargarPersonaEnFormulario(PersonaVO persona) {
        if (persona != null) {
            txtDocumento.setText(persona.getDocumento());
            txtNombre.setText(persona.getNombre());
            txtApellido.setText(persona.getApellido());
            txtTelefono.setText(persona.getTelefono());
            txtDireccion.setText(persona.getDireccion());
            txtEmail.setText(persona.getEmail());
        }
    }

    private boolean validarCampos() {
        if (txtDocumento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El documento es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (txtApellido.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El apellido es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            if (validarCampos()) {
                PersonaVO persona = crearPersonaDesdeFormulario();
                String resultado = coordinador.registrarPersona(persona);
                txtAreaInfo.setText(resultado);
            }
        } else if (e.getSource() == btnConsultar) {
            String documento = txtDocumento.getText().trim();
            if (documento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el documento a consultar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            PersonaVO persona = coordinador.obtenerPersona(documento);
            String resultado = coordinador.consultarPersona(documento);
            txtAreaInfo.setText(resultado);
            cargarPersonaEnFormulario(persona);

        } else if (e.getSource() == btnActualizar) {
            if (validarCampos()) {
                PersonaVO persona = crearPersonaDesdeFormulario();
                String resultado = coordinador.actualizarPersona(persona);
                txtAreaInfo.setText(resultado);
            }
        } else if (e.getSource() == btnEliminar) {
            String documento = txtDocumento.getText().trim();
            if (documento.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el documento a eliminar", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea eliminar la persona con documento " + documento + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (opcion == JOptionPane.YES_OPTION) {
                String resultado = coordinador.eliminarPersona(documento);
                txtAreaInfo.setText(resultado);
                limpiarCampos();
            }
        } else if (e.getSource() == btnConsultarLista) {
            String resultado = coordinador.consultarTodasLasPersonas();
            txtAreaInfo.setText(resultado);
        } else if (e.getSource() == btnLimpiar) {
            limpiarCampos();
        } else if (e.getSource() == btnVolver) {
            this.setVisible(false);
        }
    }
}


