package src;

//package src;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class MiPanel extends JPanel {

    private ArrayList<Figura> figuras = new ArrayList<>();
    private Figura figuraSeleccionada;
    private Point posicionRaton;
    private JPopupMenu menuEmergente;
    private Color colorSeleccionado;
    private Color colorSeleccionado1;
    Timer Temporizador;
    private Color colorSeleccionado2;
    private boolean usoPorVoz = false;

    // Crear etiquetas para mostrar los datos de la figura seleccionada
    JLabel tipoFiguraLabel = new JLabel("Tipo de figura:");
    JLabel posicionXLabel = new JLabel("Posici�n X:");
    JLabel posicionYLabel = new JLabel("Posici�n Y:");

    JLabel colorLabel = new JLabel("Color:");

    public MiPanel() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(600, 600));
        figuras = new ArrayList<>();

        // Agregar etiquetas al panel
        add(tipoFiguraLabel);
        add(posicionXLabel);
        add(posicionYLabel);

        tipoFiguraLabel.setBounds(10, 10, 100, 20);
        posicionXLabel.setBounds(10, 30, 100, 20);
        posicionYLabel.setBounds(10, 50, 100, 20);

        // Creamos el men� emergente

        menuEmergente = new JPopupMenu();

        JMenuItem rectanguloItem = new JMenuItem("Rect�ngulo");
        rectanguloItem.addActionListener(e -> {
            crearRectangulo();
        });

        JMenuItem cuadradiItem = new JMenuItem("Cuadrado");
        cuadradiItem.addActionListener(e -> {
            crearCuadrado();
        });

        JMenuItem circuloItem = new JMenuItem("C�rculo");
        circuloItem.addActionListener(e -> {
            crearCirculo();
        });

        JMenuItem trianguloItem = new JMenuItem("tri�ngulo");
        trianguloItem.addActionListener(e -> {
            crearTriangulo();
        });

        JMenuItem autoItem = new JMenuItem("Auto");
        autoItem.addActionListener(e -> {
            crearAuto();
        });

        JMenuItem casaItem = new JMenuItem("Casa");
        casaItem.addActionListener(e -> {
            crearCasa();
        });

        JMenuItem EliminarItem = new JMenuItem("Eliminar");
        EliminarItem.addActionListener(e -> {
            eliminarFigura();
        });

        menuEmergente.add(rectanguloItem);
        menuEmergente.add(circuloItem);
        menuEmergente.add(trianguloItem);
        menuEmergente.add(autoItem);
        menuEmergente.add(cuadradiItem);
        menuEmergente.add(casaItem);
        menuEmergente.add(EliminarItem);

        JMenuItem caerItem = new JMenuItem("Caer");

        caerItem.addActionListener(e -> {
            caer();
        });

        menuEmergente.add(caerItem);
        JMenuItem MoverItem = new JMenuItem("Mover");

        MoverItem.addActionListener(e -> {
            mover();
        });

        menuEmergente.add(MoverItem);

        JMenuItem arcoIrisItem = new JMenuItem("ArcoIris");

        arcoIrisItem.addActionListener(e -> {
            cambioColorArcoiris();
        });

        menuEmergente.add(arcoIrisItem);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                // Buscamos la figura que contiene las coordenadas del click
                for (Figura figura : figuras) {
                    if (figura.contiene(e.getX(), e.getY())) {
                        figuraSeleccionada = figura;
                        posicionRaton = e.getPoint();
                        break;
                    }
                }
                actualizarDatosFiguraSeleccionada();
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isRightMouseButton(e)) {
                    menuEmergente.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (figuraSeleccionada != null) {
                    int deltaX = e.getX() - posicionRaton.x;
                    int deltaY = e.getY() - posicionRaton.y;
                    int nuevaX = figuraSeleccionada.getX() + deltaX;
                    int nuevaY = figuraSeleccionada.getY() + deltaY;
                    figuraSeleccionada.setX(nuevaX);
                    figuraSeleccionada.setY(nuevaY);
                    posicionRaton = e.getPoint();
                    repaint();
                }
                actualizarDatosFiguraSeleccionada();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figura figura : figuras) {
            figura.dibujar(g);
        }
    }

    public void actualizarDatosFiguraSeleccionada() {
        if (figuraSeleccionada != null) {
            // Actualizar etiquetas con los datos de la figura seleccionada
            tipoFiguraLabel.setText("Tipo de figura: " + figuraSeleccionada.getTipo());
            posicionXLabel.setText("Posici�n X: " + figuraSeleccionada.getX());
            posicionYLabel.setText("Posici�n Y: " + figuraSeleccionada.getY());

            // colorLabel.setText("Color :" +figuraSeleccionada.setColor());
        } else {
            // Si no hay figura seleccionada, limpiar las etiquetas
            tipoFiguraLabel.setText("Tipo de figura:");
            posicionXLabel.setText("Posici�n X:");
            posicionYLabel.setText("Posici�n Y:");
        }
    }

    public void crearCuadrado() {
        String inputx = JOptionPane.showInputDialog(this, "Ingrese la posicion x:");
        String inputy = JOptionPane.showInputDialog(this, "Ingrese la posicion y:");
        String ladoCuadrado = JOptionPane.showInputDialog(this, "Ingrese el lado del cuadrado");
        int lado = Integer.parseInt(ladoCuadrado);
        int x = Integer.parseInt(inputx);
        int y = Integer.parseInt(inputy);

        // Pedir color de la figura
        colorSeleccionado = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);

        // Crear objeto cuadrado con las dimensiones especificadas
        Cuadrado cuad = new Cuadrado(x, y, colorSeleccionado);
        cuad.setColor(colorSeleccionado);
        cuad.setAncho(lado);
        figuras.add(cuad);
        repaint();
    }

    public void crearCirculo() {

        String inputx = JOptionPane.showInputDialog(this, "Ingrese la posicion x:");
        String inputy = JOptionPane.showInputDialog(this, "Ingrese la posicion y:");
        String inputRadius = JOptionPane.showInputDialog(this, "Ingrese el radio del circulo:");
        int radius = Integer.parseInt(inputRadius);
        int x = Integer.parseInt(inputx);
        int y = Integer.parseInt(inputy);

        // Pedir color de la figura
        colorSeleccionado = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);

        // Crear objeto Circulo con el radio especificado
        Circulo circ = new Circulo(x, y, colorSeleccionado);
        circ.setColor(colorSeleccionado);
        circ.setRadio(radius);
        figuras.add(circ);
        repaint();
    }

    public void crearRectangulo() {

        // Pedir dimensiones del rectangulo
        String inputx = JOptionPane.showInputDialog(this, "Ingrese la posicion x:");
        String inputy = JOptionPane.showInputDialog(this, "Ingrese la posicion y:");
        String inputWidth = JOptionPane.showInputDialog(this, "Ingrese el ancho del rectangulo:");
        String inputHeight = JOptionPane.showInputDialog(this, "Ingrese el alto del rectangulo:");
        int width = Integer.parseInt(inputWidth);
        int height = Integer.parseInt(inputHeight);
        int x = Integer.parseInt(inputx);
        int y = Integer.parseInt(inputy);

        // Pedir color de la figura
        colorSeleccionado = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);

        // Crear objeto Rectangulo con las dimensiones especificadas
        Rectangulo rect = new Rectangulo(x, y, width, height);
        rect.setColor(colorSeleccionado);
        rect.setAncho(width);
        rect.setAlto(height);
        figuras.add(rect);
        repaint();
    }

    public void crearTriangulo() {
        // Pedir dimensiones del triangulo
        String inputx = JOptionPane.showInputDialog(this, "Ingrese la posicion x:");
        String inputy = JOptionPane.showInputDialog(this, "Ingrese la posicion y:");
        String inputBase = JOptionPane.showInputDialog(this, "Ingrese la base del triangulo:");
        String inputHeight = JOptionPane.showInputDialog(this, "Ingrese la altura del triangulo:");
        int base = Integer.parseInt(inputBase);
        int height = Integer.parseInt(inputHeight);
        int x = Integer.parseInt(inputx);
        int y = Integer.parseInt(inputy);

        // Pedir color de la figura
        colorSeleccionado = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);

        // Crear objeto Triangulo con la base y altura especificadas
        Triangulo triangulo = new Triangulo(x, y, base, height);
        triangulo.setColor(colorSeleccionado);
        triangulo.setBase(base);
        triangulo.setAltura(height);
        figuras.add(triangulo);
        repaint();
    }

    public void crearAuto() {
        colorSeleccionado = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);
        colorSeleccionado1 = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);
        String inputx = JOptionPane.showInputDialog(this, "Ingrese la posicion x:");
        String inputy = JOptionPane.showInputDialog(this, "Ingrese la posicion y:");
        int x = Integer.parseInt(inputx);
        int y = Integer.parseInt(inputy);
        Auto lamborgini = new Auto(x, y); // arreglar
        lamborgini.setColor(colorSeleccionado);
        lamborgini.setColor2(colorSeleccionado1);
        figuras.add(lamborgini);
        Graphics g = getGraphics();
        lamborgini.dibujar(g);
        repaint();
    }

    public void crearCasa() {
        colorSeleccionado = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);
        colorSeleccionado1 = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);
        colorSeleccionado2 = JColorChooser.showDialog(this, "Seleccione un color", Color.BLACK);
        String inputx = JOptionPane.showInputDialog(this, "Ingrese la posicion x:");
        String inputy = JOptionPane.showInputDialog(this, "Ingrese la posicion y:");
        int x = Integer.parseInt(inputx);
        int y = Integer.parseInt(inputy);
        Casa casa = new Casa(x, y, colorSeleccionado, colorSeleccionado1, colorSeleccionado2, "Casa");
        casa.setColor(colorSeleccionado);
        casa.setColor2(colorSeleccionado1);
        figuras.add(casa);
        Graphics g = getGraphics();
        casa.dibujar(g);
        repaint();
    }

    public void eliminarFigura() {
        if (figuraSeleccionada != null) {
            figuras.remove(figuraSeleccionada);
            figuraSeleccionada = null;
            repaint();
            actualizarDatosFiguraSeleccionada();
        }
    }

    public void caer() {
        if (figuraSeleccionada != null) {
            // Obtener la altura del panel
            int panelHeight = 650;// getHeight();

        
            // Iniciar animaci�n de ca�da para la figura seleccionada
            new Thread(() -> {
                int velocidadY = 5;
                while (figuraSeleccionada.getY() < panelHeight - figuraSeleccionada.getY()) {
                    figuraSeleccionada.setY(figuraSeleccionada.getY() + velocidadY);
                    repaint();
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

                // Iniciar animaci�n de rebote para la figura seleccionada
                int rebotes = 3;
                int velocidadRebote = 20;
                while (rebotes > 0) {
                    figuraSeleccionada.setY(figuraSeleccionada.getY() - velocidadRebote);
                    repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    figuraSeleccionada.setY(figuraSeleccionada.getY() + velocidadRebote);
                    repaint();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    rebotes--;
                }

                // Ajustar la posici�n final de la figura en caso de que se haya pasado
                if (figuraSeleccionada.getY() > panelHeight - figuraSeleccionada.getY()) {
                    figuraSeleccionada.setY(panelHeight - figuraSeleccionada.getY());
                    repaint();
                }
            }).start();
        }
    }

    public void mover() {
        if (figuraSeleccionada != null) {
            String inputx = JOptionPane.showInputDialog(this, "Ingrese la posicion x:");
            String inputy = JOptionPane.showInputDialog(this, "Ingrese la posicion y:");
            int nuevaX = Integer.parseInt(inputx);
            int nuevaY = Integer.parseInt(inputy);
            int duracionAnimacion = 1000;
            if (Temporizador != null && Temporizador.isRunning()) {
                Temporizador.stop();
            }
            // Calcular la cantidad de actualizaciones necesarias para la animaci�n
            int numActualizaciones = duracionAnimacion / 50; // actualizaciones cada 50 milisegundos
            // Calcular los incrementos en las coordenadas x e y para cada actualizaci�n
            int deltaX = (nuevaX - figuraSeleccionada.getX()) / numActualizaciones;
            int deltaY = (nuevaY - figuraSeleccionada.getY()) / numActualizaciones;
            // Crear un temporizador que actualice las coordenadas de la figura cada 50
            // milisegundos
            Temporizador = new Timer(50, new ActionListener() {
                int actualizacionActual = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    // Actualizar las coordenadas de la figura con los incrementos correspondientes
                    int x = figuraSeleccionada.getX();
                    int y = figuraSeleccionada.getY();
                    x += deltaX;
                    y += deltaY;
                    figuraSeleccionada.setX(x);
                    figuraSeleccionada.setY(y);
                    actualizacionActual++;
                    // Si se han realizado todas las actualizaciones necesarias, detener el
                    // temporizador
                    if (actualizacionActual == numActualizaciones) {
                        Temporizador.stop();
                    }
                    // Repintar el panel para mostrar la figura en su nueva posici�n
                    repaint();
                }
            });
            Temporizador.start();
            actualizarDatosFiguraSeleccionada();
        }
    }

    public void cambioColorArcoiris() {
        if (figuraSeleccionada != null) {
            int duracionAnimacion = 5000; // Duraci�n de la animaci�n en milisegundos
            if (Temporizador != null && Temporizador.isRunning()) {
                Temporizador.stop();
            }
            // Calcular la cantidad de actualizaciones necesarias para la animaci�n
            int numActualizaciones = duracionAnimacion / 50; // actualizaciones cada 50 milisegundos
            // Calcular los incrementos en los componentes de color RGB para cada
            // actualizaci�n
            int incrementoR = 255 / numActualizaciones;
            int incrementoG = 0;
            int incrementoB = -255 / numActualizaciones;
            // Crear un temporizador que actualice los componentes de color RGB de la figura
            // cada 50 milisegundos
            Temporizador = new Timer(50, new ActionListener() {
                int actualizacionActual = 0;
                int r = 255, g = 0, b = 0; // Componentes de color RGB iniciales

                @Override
                public void actionPerformed(ActionEvent e) {
                    // Actualizar los componentes de color RGB de la figura con los incrementos
                    // correspondientes
                    r -= incrementoR;
                    g += incrementoG;
                    b -= incrementoB;
                    figuraSeleccionada.setColor(new Color(r, g, b));
                    actualizacionActual++;
                    // Si se han realizado todas las actualizaciones necesarias, detener el
                    // temporizador
                    if (actualizacionActual == numActualizaciones) {
                        Temporizador.stop();
                    }
                    // Repintar el panel para mostrar la figura con su nuevo color
                    repaint();
                }
            });
            Temporizador.start();
            actualizarDatosFiguraSeleccionada();
        }
    }

}