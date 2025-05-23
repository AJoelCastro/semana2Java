/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package presentacion;

import java.awt.*;
import javax.swing.*;
import datos.*;
import entidades.*;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.*;
import logica.*;


/**
 *
 * @author sanar
 */
public class panLibros extends javax.swing.JPanel {

    /**
     * Creates new form panLibros
     */
    public panLibros() {
        initComponents();
        actualizarTabla();
    }
    private void centrarInternalFrame (JInternalFrame interna) {
        int x,y;
        
        x=dspFondo.getWidth()/2 - interna.getWidth()/2;
        y=dspFondo.getHeight()/2- interna.getHeight()/2;
        if(interna.isShowing())
        interna.setLocation(x,y);
        
        else {
            dspFondo.add(interna);
            interna.setLocation(x,y);
            interna.show();
        };
        
    }
//    private void ordenarAscendentePorTitulo() {
//        listaLibros = DALInventarioLibro.obtenerInventario();  // Obtener los libros desde el DAL
//        Collections.sort(listaLibros, new CompararPorTituloAscendente());  // Ordenar por título ascendente
//        actualizarTabla(listaLibros);  // Actualizar la tabla con los libros ordenados
//    }
//    private void ordenarDescendentePorTitulo() {
//        listaLibros = DALInventarioLibro.obtenerInventario();  // Obtener los libros desde el DAL
//        Collections.sort(listaLibros, new CompararPorTituloDescendente());  // Ordenar por título descendente
//        actualizarTabla(listaLibros);  // Actualizar la tabla con los libros ordenados
//    }
//    private void ordenarAscendentePorCategoria() {
//        listaLibros = DALInventarioLibro.obtenerInventario();  // Obtener los libros desde el DAL
//        Collections.sort(listaLibros, new CompararPorCategoriaAscendente());  // Ordenar por categoría ascendente
//        actualizarTabla(listaLibros);  // Actualizar la tabla con los libros ordenados
//    }
//    private void ordenarDescendentePorCategoria() {
//        listaLibros = DALInventarioLibro.obtenerInventario();  // Obtener los libros desde el DAL
//        Collections.sort(listaLibros, new CompararPorCategoriaDescendente());  // Ordenar por categoría descendente
//        actualizarTabla(listaLibros);  // Actualizar la tabla con los libros ordenados
//    }

    
   private void actualizarTabla() {
        // Recargar la lista de libros
        ArrayList<Libro> listaLibros = DALInventarioLibro.obtenerInventario(); // Obtener los libros actualizados desde el DAL

        // Crear el modelo de la tabla con las columnas necesarias
        String[] columnas = {"Código", "Título", "Autor", "Categoría", "Año Publicación", "Copias Disponibles", "Editorial"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        // Llenar el modelo con los libros de la lista ordenada, excluyendo los vacíos
        for (Libro libro : listaLibros) {
            // Verifica si el libro está vacío (campos nulos o con valores predeterminados)
            if (libro.getCodigo().isEmpty() || libro.getTitulo().isEmpty() || libro.getAutor().isEmpty() ||
                libro.getCategoria().isEmpty() || libro.getAnioPublicacion() == 0 || libro.getCopiasDisponibles() == 0 ||
                libro.getEditorial().isEmpty()) {
                continue;  // Saltar los libros vacíos
            }

            // Agregar el libro a la tabla si no es vacío
            Object[] fila = new Object[columnas.length];
            fila[0] = libro.getCodigo();
            fila[1] = libro.getTitulo();
            fila[2] = libro.getAutor();
            fila[3] = libro.getCategoria();
            fila[4] = libro.getAnioPublicacion();
            fila[5] = libro.getCopiasDisponibles();
            fila[6] = libro.getEditorial();
            modelo.addRow(fila);
        }

        // Establecer el modelo de la tabla
        jTable1.setModel(modelo); // Asegúrate de que el nombre de la tabla sea correcto
    }
   
    private void actualizarTabla(ArrayList<Libro> listaLibros) {
        String[] columnas = {"Código", "Título", "Autor", "Categoría", "Año Publicación", "Copias Disponibles", "Editorial"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        // Llenar el modelo con los libros de la lista ordenada
        for (Libro libro : listaLibros) {
            Object[] fila = new Object[columnas.length];
            fila[0] = libro.getCodigo();
            fila[1] = libro.getTitulo();
            fila[2] = libro.getAutor();
            fila[3] = libro.getCategoria();
            fila[4] = libro.getAnioPublicacion();
            fila[5] = libro.getCopiasDisponibles();
            fila[6] = libro.getEditorial();
            modelo.addRow(fila);
        }

        // Establecer el modelo de la tabla
        jTable1.setModel(modelo);  // Asegúrate de que el nombre de la tabla sea correcto
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/fondoLibros.png"));
        Image image = icon.getImage();
        dspFondo = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnListar = new BotonPersonalizado("", botonBlanco,presionadoBuscar,encimaBuscar);
        btnEditar = new BotonPersonalizado("", botonBlanco,presionadoBuscar,encimaBuscar);
        btnEliminar = new BotonPersonalizado("", botonBlanco,presionadoBuscar,encimaBuscar);
        btnAgregar = new BotonPersonalizado("", botonBlanco,presionadoBuscar,encimaBuscar);
        jrbBuscarAutor = new javax.swing.JRadioButton();
        jrbBuscarTitulo = new javax.swing.JRadioButton();
        jrbBuscarCodigo = new javax.swing.JRadioButton();
        jrbBuscarCategoria = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new BotonPersonalizado("", botonBlanco,presionadoBuscar,encimaBuscar);
        lblEncabezado = new javax.swing.JLabel();
        lblOrdenar = new javax.swing.JLabel();
        jrbOrdenarTitulo = new javax.swing.JRadioButton();
        jrbOrdenarCategoria = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jrbAscendente = new javax.swing.JRadioButton();
        jrbDescendente = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1280, 720));

        dspFondo.setBackground(new java.awt.Color(60, 176, 200));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        btnListar.setBackground(new java.awt.Color(60, 176, 200));
        btnListar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnListar.setForeground(new java.awt.Color(255, 255, 255));
        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lista.png"))); // NOI18N
        btnListar.setText("  Listar Libros");
        btnListar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(60, 176, 200));
        btnEditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/editar.png"))); // NOI18N
        btnEditar.setText("  Editar Libro");
        btnEditar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(60, 176, 200));
        btnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("  Eliminar Libro");
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(60, 176, 200));
        btnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/añadir.png"))); // NOI18N
        btnAgregar.setText("  Agregar Libro");
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        buttonGroup1.add(jrbBuscarAutor);
        jrbBuscarAutor.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jrbBuscarAutor.setForeground(new java.awt.Color(255, 255, 255));
        jrbBuscarAutor.setText("Autor");

        buttonGroup1.add(jrbBuscarTitulo);
        jrbBuscarTitulo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jrbBuscarTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jrbBuscarTitulo.setText("Titulo");

        buttonGroup1.add(jrbBuscarCodigo);
        jrbBuscarCodigo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jrbBuscarCodigo.setForeground(new java.awt.Color(255, 255, 255));
        jrbBuscarCodigo.setText("Codigo");

        buttonGroup1.add(jrbBuscarCategoria);
        jrbBuscarCategoria.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jrbBuscarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        jrbBuscarCategoria.setText("Categoria");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Buscar por:");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jSeparator1.setOpaque(true);

        txtBusqueda.setBackground(new java.awt.Color(60, 176, 200));
        txtBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        txtBusqueda.setBorder(null);

        btnBuscar.setBackground(new java.awt.Color(60, 176, 200));
        btnBuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/lupa.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        lblEncabezado.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        lblEncabezado.setForeground(new java.awt.Color(255, 255, 255));
        lblEncabezado.setText("Control de Libros");

        lblOrdenar.setForeground(new java.awt.Color(255, 255, 255));
        lblOrdenar.setText("Filtrar por :");

        buttonGroup2.add(jrbOrdenarTitulo);
        jrbOrdenarTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jrbOrdenarTitulo.setText("Titulo");

        buttonGroup2.add(jrbOrdenarCategoria);
        jrbOrdenarCategoria.setForeground(new java.awt.Color(255, 255, 255));
        jrbOrdenarCategoria.setText("Categoria");

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Tipo de Orden :");

        buttonGroup3.add(jrbAscendente);
        jrbAscendente.setForeground(new java.awt.Color(255, 255, 255));
        jrbAscendente.setText("Ascendente");
        jrbAscendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbAscendenteActionPerformed(evt);
            }
        });

        buttonGroup3.add(jrbDescendente);
        jrbDescendente.setForeground(new java.awt.Color(255, 255, 255));
        jrbDescendente.setText("Descendente");
        jrbDescendente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbDescendenteActionPerformed(evt);
            }
        });

        dspFondo.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(btnListar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(btnEditar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(btnEliminar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(btnAgregar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jrbBuscarAutor, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jrbBuscarTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jrbBuscarCodigo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jrbBuscarCategoria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(txtBusqueda, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(btnBuscar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(lblEncabezado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(lblOrdenar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jrbOrdenarTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jrbOrdenarCategoria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jrbAscendente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        dspFondo.setLayer(jrbDescendente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout dspFondoLayout = new javax.swing.GroupLayout(dspFondo);
        dspFondo.setLayout(dspFondoLayout);
        dspFondoLayout.setHorizontalGroup(
            dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dspFondoLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addGroup(dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addGroup(dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(dspFondoLayout.createSequentialGroup()
                            .addGroup(dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jSeparator1)
                                .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 599, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(dspFondoLayout.createSequentialGroup()
                            .addGap(247, 247, 247)
                            .addComponent(lblEncabezado)))
                    .addGroup(dspFondoLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel2)
                        .addGap(70, 70, 70)
                        .addComponent(jrbBuscarAutor)
                        .addGap(50, 50, 50)
                        .addComponent(jrbBuscarTitulo)
                        .addGap(50, 50, 50)
                        .addComponent(jrbBuscarCodigo)
                        .addGap(50, 50, 50)
                        .addComponent(jrbBuscarCategoria))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOrdenar)
                    .addComponent(jrbOrdenarTitulo)
                    .addComponent(jrbOrdenarCategoria)
                    .addComponent(jLabel1)
                    .addComponent(jrbDescendente)
                    .addComponent(jrbAscendente))
                .addGap(120, 120, 120))
        );
        dspFondoLayout.setVerticalGroup(
            dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dspFondoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lblEncabezado)
                .addGap(12, 12, 12)
                .addGroup(dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jrbBuscarAutor)
                    .addComponent(jrbBuscarTitulo)
                    .addComponent(jrbBuscarCodigo)
                    .addComponent(jrbBuscarCategoria))
                .addGap(48, 48, 48)
                .addGroup(dspFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dspFondoLayout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnListar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dspFondoLayout.createSequentialGroup()
                        .addComponent(lblOrdenar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbOrdenarTitulo)
                        .addGap(18, 18, 18)
                        .addComponent(jrbOrdenarCategoria)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jrbAscendente)
                        .addGap(18, 18, 18)
                        .addComponent(jrbDescendente)))
                .addContainerGap(72, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dspFondo)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dspFondo)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        JInternalFrame interna = new IfrmLibros();
        centrarInternalFrame(interna);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        actualizarTabla();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         // Pedir el código del libro a eliminar mediante un JOptionPane
        String codigoEliminar = JOptionPane.showInputDialog(this, "Ingrese el código del libro a eliminar:");

        // Validar si el código está vacío
        if (codigoEliminar == null || codigoEliminar.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un código válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Normalizar el código para comparación insensible a mayúsculas/minúsculas
        codigoEliminar = codigoEliminar.trim().toLowerCase();

        try {
            // Llamar al método de DAL para eliminar el libro (sobrescribir con un libro vacío)
            DALInventarioLibro.eliminarLibro(codigoEliminar);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Libro eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Actualizar la tabla para reflejar los cambios
            actualizarTabla();

        } catch (IOException e) {
            // Si ocurre un error, mostrar mensaje de error
            JOptionPane.showMessageDialog(this, "Error al eliminar el libro: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        JInternalFrame interna = new IfrmRegistroLibros();
        centrarInternalFrame(interna);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // Obtener el término de búsqueda desde el campo de texto txtBusqueda
        String busqueda = txtBusqueda.getText().trim();

        // Validar si el término de búsqueda está vacío
        if (busqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un término de búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener el criterio de búsqueda basado en el JRadioButton seleccionado
        String criterio = "";

        if (jrbBuscarCodigo.isSelected()) {
            criterio = "codigo";
        } else if (jrbBuscarTitulo.isSelected()) {
            criterio = "titulo";
        } else if (jrbBuscarAutor.isSelected()) {
            criterio = "autor";
        } else if (jrbBuscarCategoria.isSelected()) {
            criterio = "categoria";
        } else {
            // Si no se selecciona ningún JRadioButton, mostrar un error
            JOptionPane.showMessageDialog(this, "Por favor seleccione un criterio de búsqueda.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Llamar al método de BL para buscar libros con el criterio y valor
        ArrayList<Libro> librosEncontrados = BLInventarioLibro.buscarLibro(criterio, busqueda);

        // Si no se encontraron libros, mostrar mensaje de error
        if (librosEncontrados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron libros con el término de búsqueda: " + busqueda, "Resultado de búsqueda", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Crear el modelo de la tabla y agregar los libros encontrados
        String[] columnas = {"Código", "Título", "Autor", "Categoría", "Año Publicación", "Copias Disponibles", "Editorial"};
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        for (Libro libro : librosEncontrados) {
            Object[] fila = new Object[columnas.length];
            fila[0] = libro.getCodigo();
            fila[1] = libro.getTitulo();
            fila[2] = libro.getAutor();
            fila[3] = libro.getCategoria();
            fila[4] = libro.getAnioPublicacion();
            fila[5] = libro.getCopiasDisponibles();
            fila[6] = libro.getEditorial();
            modelo.addRow(fila);
        }

        // Asignar el modelo a la tabla
        jTable1.setModel(modelo);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jrbAscendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbAscendenteActionPerformed
//        ArrayList<Libro> listaLibros = DALInventarioLibro.obtenerInventario(); // Obtener los libros desde el DAL
//    
//        if (jrbOrdenarTitulo.isSelected()) {
//            ordenarAscendentePorTitulo(listaLibros);
//        } else if (jrbOrdenarCategoria.isSelected()) {
//            ordenarAscendentePorCategoria(listaLibros);
//        }
//        actualizarTabla();
    }//GEN-LAST:event_jrbAscendenteActionPerformed

    private void jrbDescendenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbDescendenteActionPerformed
//        ArrayList<Libro> listaLibros = DALInventarioLibro.obtenerInventario();
//
//        if (jrbOrdenarTitulo.isSelected()) {
//            ordenarDescendentePorTitulo(listaLibros);
//        } else if (jrbOrdenarCategoria.isSelected()) {
//            ordenarDescendentePorCategoria(listaLibros);
//        }
//        actualizarTabla();
    }//GEN-LAST:event_jrbDescendenteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnListar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JDesktopPane dspFondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton jrbAscendente;
    private javax.swing.JRadioButton jrbBuscarAutor;
    private javax.swing.JRadioButton jrbBuscarCategoria;
    private javax.swing.JRadioButton jrbBuscarCodigo;
    private javax.swing.JRadioButton jrbBuscarTitulo;
    private javax.swing.JRadioButton jrbDescendente;
    private javax.swing.JRadioButton jrbOrdenarCategoria;
    private javax.swing.JRadioButton jrbOrdenarTitulo;
    private javax.swing.JLabel lblEncabezado;
    private javax.swing.JLabel lblOrdenar;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
    private Color botonBlanco = new Color(60,176,200);
    private Color presionadoBuscar = new Color(40,156,180);
    private Color encimaBuscar = new Color(50,166,190);
    private ArrayList<Libro> listaLibros;
}
