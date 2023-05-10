package src;

//package src;

import java.awt.Graphics;
import java.awt.Color;

public class Cuadrado extends Figura {
    private int x, y, lado;
    private Color c;

    public Cuadrado(int x, int y, Color c) {
        this.x = x;
        this.y = y;
        this.c = Color.black;

    }

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

    public int getAncho() {
        return lado;
    }

    public void setAncho(int lado) {
        this.lado = lado;
    }

    public void setColor(Color c) {
        this.c = c;
    }

    // Método para dibujar el rectángulo
    public void dibujar(Graphics g) {
        g.setColor(c);
        g.fillRect(x, y, lado, lado);
    }

    public boolean contiene(int x, int y) {
        return getX() <= x && x <= getX() + getAncho() && getY() <= y && y <= getY() + getAncho();
    }

    public String getTipo() {
        return "Cuadrado";
    }

}