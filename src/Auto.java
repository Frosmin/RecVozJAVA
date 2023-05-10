package src;

//package src;

import java.awt.Color;
import java.awt.Graphics;
public class Auto extends Figura{
    private Color color1;
    private Color color2;
    private int x;
    private int y;
    // Constructor
    public Auto(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void dibujar (Graphics g){
         // Dibujar el rectangulo en el componente gráfico "g"
       g.setColor(color1);
       
        g.fillRect(x, y, 200, 50);
        g.fillRect(x + 20, y - 50, 160, 50);
         //x , y , ancho, alto
        // Dibuja las ruedas del auto
        g.setColor(color2);
        g.fillOval(x + 20, y + 35, 50, 50);
        g.fillOval(x + 130, y + 35, 50, 50);
        g.fillRect(x + 130,y - 30,35,35);
         //x , y , ancho, alto
    }
    public void setColor(Color c1){
        color1 = c1;
    }
    public void setColor2(Color c2){
        color2 = c2;
    }
    
    public String getTipo(){
        return "Auto";
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
    
    public int getAncho(){
        return 200;
    }
    
    public int getAncho1(){
        return 160;
    }
    
    public int getAncho2(){
        return 160;
    }
    
    public int getAlto(){
        return 50;
    }
    public int getAlto1(){
        return 35;
    }
    
    public boolean contiene(int x, int y) {
        boolean verificar = false;
        if(getX() <= x && x <= getX() + getAncho() && getY() <= y && y <= getY() + getAlto()){
           verificar = true;
        }
        if((getX()+20) <= x && x <= (getX()+20) + getAncho1() && (getY()-50) <= y && y <= (getY()-50) + getAlto()){
           verificar = true;
        }
        if((getX()+130) <= x && x <= (getX()+130) + getAncho2() && (getY()-30) <= y && y <= (getY()-30) + getAlto1()){
           verificar = true;
        }
        return verificar; 
    }
}