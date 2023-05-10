package src;

//package src;
import java.awt.Graphics;
import java.awt.Color;

  public class Casa extends Figura{
    // Atributos de la clase
    private int x, y;
    private Color color1;
    private Color color2;
    private Color color3;
    // Constructor de la clase
    public Casa(int x, int y, Color color1, Color color2, Color color3, String nombre) {
        this.x = x;
        this.y = y;
      this.color1 = color1;
      this.color2 = color2;
      this.color3 = color3;
    }
    
    // M�todos de la clase
    public Color getColor1(){
        return color1;
    }
    public Color getColor2(){
        return color2;
    }
    public Color getColor3(){
        return color3;
    }
    public void setColor2(Color color){
        color2 = color;
    }
    public void setColor(Color color){
        color1 = color;
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
    public void dibujar(Graphics g) {
        // Dibujar el techo de la casa
       g.setColor(getColor1());
       int[] xPoints = { x, x+100, x+200 };
       int[] yPoints = { y, y-50, y };
       g.fillPolygon(xPoints, yPoints, 3);

        // Dibujar las paredes de la casa
        g.fillRect(x, y, 200, 150);

        // Dibujar la puerta
        g.setColor(getColor2());
        g.fillRect(x+75, y+78, 50, 70);

        // Dibujar las ventanas
        g.setColor(getColor3());
        g.fillRect(x+20, y+25, 40, 40);
        g.fillRect(x+140, y+25, 40, 40);
        g.fillRect(x+20, y+75, 40, 40);
       g.fillRect(x+140, y+75, 40, 40);

    }
    public boolean contiene(int x, int y) {
        return getX() <= x && x <= getX() + 200 && getY() <= y && y <= getY() + 150;
    }
    public String getTipo(){
        return "Casa";
    }
}
