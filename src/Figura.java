package src;

//package src;

import java.awt.Graphics;
import java.awt.Color;

public class Figura {
    private int x, y;
    private Color color;
    private String tipo;

    public void dibujar(Graphics g) {
        setX(x);
        setY(y);
        dibujar(g);
    }

    // Método para determinar si la figura contiene las coordenadas (x, y)
    public boolean contiene(int x, int y) {
        return true;
    }

    // Métodos para obtener y establecer los valores de los atributos
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getTipo() {
        return tipo;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}