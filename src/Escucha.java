package src;

import javax.swing.JToggleButton;
import javax.speech.*;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.util.Locale;
import javax.swing.JFrame;
import java.awt.event.*;

public class Escucha extends ResultAdapter {

    static Recognizer recognizer;
    String gst;
    private MiPanel miPanel;
    private JFrame frame;
    private boolean usoPorVoz = false;

    public Escucha() {
        miPanel = new MiPanel();
        frame = new JFrame("Mi figura");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JToggleButton toggleButton = new JToggleButton("Modo voz", false);
        toggleButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    usoPorVoz = true;
                } else {
                    usoPorVoz = false;
                }
            }
        });
        miPanel.add(toggleButton);
        frame.add(miPanel);
        frame.setVisible(true);
    }

    @Override
    public void resultAccepted(ResultEvent re) {
        if (usoPorVoz) {
            try {
                Result res = (Result) (re.getSource());
                ResultToken tokens[] = res.getBestTokens();

                String args[] = new String[1];
                args[0] = "";
                for (int i = 0; i < tokens.length; i++) {
                    gst = tokens[i].getSpokenText();
                    args[0] += gst + " ";
                    System.out.print(gst + " ");
                }
                System.out.println();
                if (gst.equals("1")) {
                    recognizer.deallocate();
                    args[0] = "1";
                    System.out.println(args[0]);
                    System.exit(0);
                } else {
                    recognizer.suspend();
                    recognizer.resume();
                }

                if (gst.equals("Cuadrado")) {
                    miPanel.crearCuadrado();
                }
                if (gst.equals("Caer")) {
                    miPanel.caer();
                }
                if (gst.equals("Triangulo")) {
                    miPanel.crearTriangulo();
                }
                if (gst.equals("Circulo")) {
                    miPanel.crearCirculo();
                }
                if (gst.equals("Rectangulo")) {
                    miPanel.crearRectangulo();
                }
                if (gst.equals("Casa")) {
                    miPanel.crearCasa();
                }
                if (gst.equals("Auto")) {
                    miPanel.crearAuto();
                }
                if (gst.equals("Eliminar")) {
                    miPanel.eliminarFigura();
                }
                if (gst.equals("Mover")) {
                    miPanel.mover();
                }
                if (gst.equals("Color")) {
                    miPanel.cambioColorArcoiris();
                }
            } catch (Exception ex) {
                System.out.println("Ha ocurrido algo inesperado " + ex);
            }
        }
    }

    public static void main(String args[]) {
        try {
            recognizer = Central.createRecognizer(new EngineModeDesc(Locale.ROOT));
            recognizer.allocate();

            FileReader grammar1 = new FileReader("Gramatica.txt");

            RuleGrammar rg = recognizer.loadJSGF(grammar1);
            rg.setEnabled(true);

            Escucha escucha = new Escucha();
            recognizer.addResultListener(escucha);

            System.out.println("Empieze Dictado");
            recognizer.commitChanges();

            recognizer.requestFocus();
            recognizer.resume();
        } catch (Exception e) {
            System.out.println("Exception en " + e.toString());
            e.printStackTrace();
            System.exit(0);
        }
    }
}