package org.example.vista;

import org.example.controlador.Coordinador;
import org.example.modelo.vo.MascotaVO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaMascota extends JFrame implements ActionListener {

    private Coordinador coordinador;

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtEspecie;
    private JTextField txtRaza;
    private JTextField txtEdad;
    private JTextField txtPeso;
    private JTextField txtColor;
    private JTextField txtPropietarioDocumento;
    private JTextArea txtAreaInfo;

    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnConsultarLista;
    private JButton btnConsultarPorPropietario;
    private JButton btnLimpiar;
    private JButton btnVolver;

    public VentanaMascota(VentanaPrincipal ventanaPrincipal, boolean b) {
        initComponents();
        setSize(1000, 650); // más alto
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

    private JTextField crearCampoTexto() {
        JTextField campo = new JTextField();
        campo.setFont(new Font("Arial", Font.PLAIN, 14));
        campo.setMargin(new Insets(4, 8, 4, 8));
        return campo;
    }

    private void initComponents() {
        setTitle("Gestión de Mascotas - Clínica Veterinaria ABC");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);

        Color fondo = new Color(44, 44, 44);
        Color colorTexto = new Color(201, 208, 21);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(fondo);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(colorTexto);
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JLabel lblTitulo = new JLabel("GESTIÓN DE MASCOTAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(10, 10, 10));
        panelTitulo.add(lblTitulo);

        JPanel panelFormulario = new JPanel(new GridBagLayout());
        panelFormulario.setBackground(fondo);
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(colorTexto), "Datos de la Mascota",
                0, 0, new Font("Arial", Font.BOLD, 14), colorTexto));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel[] etiquetas = {
                new JLabel("ID:"), new JLabel("Nombre:"), new JLabel("Especie:"),
                new JLabel("Raza:"), new JLabel("Edad (años):"), new JLabel("Peso (kg):"),
                new JLabel("Color:"), new JLabel("Doc. Propietario:")
        };

        JTextField[] campos = {
                txtId = crearCampoTexto(), txtNombre = crearCampoTexto(), txtEspecie = crearCampoTexto(),
                txtRaza = crearCampoTexto(), txtEdad = crearCampoTexto(), txtPeso = crearCampoTexto(),
                txtColor = crearCampoTexto(), txtPropietarioDocumento = crearCampoTexto()
        };

        for (int i = 0; i < etiquetas.length; i++) {
            etiquetas[i].setForeground(colorTexto);
            etiquetas[i].setFont(new Font("Arial", Font.PLAIN, 14));

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0;
            gbc.fill = GridBagConstraints.NONE;
            panelFormulario.add(etiquetas[i], gbc);

            gbc.gridx = 1;
            gbc.weightx = 1.0;
            gbc.fill = GridBagConstraints.HORIZONTAL;
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
        btnConsultarPorPropietario = crearBoton("Por Propietario");
        btnLimpiar = crearBoton("Limpiar");
        btnVolver = crearBoton("Volver");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnConsultarLista);
        panelBotones.add(btnConsultarPorPropietario);
        panelBotones.add(btnLimpiar);
        panelBotones.add(btnVolver);

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
        txtId.setText("");
        txtNombre.setText("");
        txtEspecie.setText("");
        txtRaza.setText("");
        txtEdad.setText("");
        txtPeso.setText("");
        txtColor.setText("");
        txtPropietarioDocumento.setText("");
        txtAreaInfo.setText("");
    }

    private MascotaVO crearMascotaDesdeFormulario() {
        MascotaVO mascota = new MascotaVO();

        if (!txtId.getText().trim().isEmpty()) {
            try {
                mascota.setId(Integer.parseInt(txtId.getText().trim()));
            } catch (NumberFormatException e) {}
        }

        mascota.setNombre(txtNombre.getText().trim());
        mascota.setEspecie(txtEspecie.getText().trim());
        mascota.setRaza(txtRaza.getText().trim());
        mascota.setColor(txtColor.getText().trim());
        mascota.setPropietarioDocumento(txtPropietarioDocumento.getText().trim());

        try {
            if (!txtEdad.getText().trim().isEmpty()) {
                mascota.setEdad(Integer.parseInt(txtEdad.getText().trim()));
            }
        } catch (NumberFormatException e) {
            mascota.setEdad(0);
        }

        try {
            if (!txtPeso.getText().trim().isEmpty()) {
                mascota.setPeso(Double.parseDouble(txtPeso.getText().trim()));
            }
        } catch (NumberFormatException e) {
            mascota.setPeso(0.0);
        }

        return mascota;
    }

    private void cargarMascotaEnFormulario(MascotaVO mascota) {
        if (mascota != null) {
            txtId.setText(String.valueOf(mascota.getId()));
            txtNombre.setText(mascota.getNombre());
            txtEspecie.setText(mascota.getEspecie());
            txtRaza.setText(mascota.getRaza());
            txtEdad.setText(String.valueOf(mascota.getEdad()));
            txtPeso.setText(String.valueOf(mascota.getPeso()));
            txtColor.setText(mascota.getColor());
            txtPropietarioDocumento.setText(mascota.getPropietarioDocumento());
        }
    }

    private boolean validarCamposRegistro() {
        if (txtNombre.getText().trim().isEmpty() ||
                txtEspecie.getText().trim().isEmpty() ||
                txtPropietarioDocumento.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, especie y documento del propietario son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            if (!txtEdad.getText().trim().isEmpty()) {
                int edad = Integer.parseInt(txtEdad.getText().trim());
                if (edad < 0) throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad inválida", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            if (!txtPeso.getText().trim().isEmpty()) {
                double peso = Double.parseDouble(txtPeso.getText().trim());
                if (peso < 0) throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Peso inválido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean validarCamposActualizacion() {
        if (txtId.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El ID es obligatorio para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            Integer.parseInt(txtId.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return validarCamposRegistro();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            if (validarCamposRegistro()) {
                MascotaVO mascota = crearMascotaDesdeFormulario();
                txtAreaInfo.setText(coordinador.registrarMascota(mascota));
            }

        } else if (e.getSource() == btnConsultar) {
            String idTexto = txtId.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int id = Integer.parseInt(idTexto);
                MascotaVO mascota = coordinador.obtenerMascota(id);
                cargarMascotaEnFormulario(mascota);
                txtAreaInfo.setText(coordinador.consultarMascota(id));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnActualizar) {
            if (validarCamposActualizacion()) {
                MascotaVO mascota = crearMascotaDesdeFormulario();
                txtAreaInfo.setText(coordinador.actualizarMascota(mascota));
            }

        } else if (e.getSource() == btnEliminar) {
            String idTexto = txtId.getText().trim();
            if (idTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int id = Integer.parseInt(idTexto);
                int opcion = JOptionPane.showConfirmDialog(this, "¿Eliminar mascota con ID " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    txtAreaInfo.setText(coordinador.eliminarMascota(id));
                    limpiarCampos();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnConsultarLista) {
            txtAreaInfo.setText(coordinador.consultarTodasLasMascotas());

        } else if (e.getSource() == btnConsultarPorPropietario) {
            String doc = txtPropietarioDocumento.getText().trim();
            if (doc.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese el documento del propietario", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            txtAreaInfo.setText(coordinador.consultarMascotasPorPropietario(doc));

        } else if (e.getSource() == btnLimpiar) {
            limpiarCampos();

        } else if (e.getSource() == btnVolver) {
            this.setVisible(false);
        }
    }
}

