import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
 * Beschreibung: Klasse die ein Fenster erstellt, das die Ergebnisse darstellt
 * @version 1.0
 * @author Leon Stopper
 * 
 */

public class Ausgabe {

    // Referenzattribut, das das Ausgabefenster speichert
    private Frame aFrame;

    // Konstruktor, indem Fenster erstellt wird
    Ausgabe(App a){
        App app = a;
        aFrame = new Frame("Ausgabe");
        aFrame.setLocation(500, 50);
        aFrame.setLayout(null);
        aFrame.setResizable(false);
        aFrame.setVisible(true);
        aFrame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent d){
                aFrame.dispose();
                app.AusgabeLöschen();
            }
        });
    }

    // Methode, die dafür sorgt, dass die Grafik ausgegeben wird
    public void AusgabeWuerfe(BufferedImage image){

        aFrame.removeAll();
        
        aFrame.setSize(600, 700);

        Canvas canvas = new Canvas(){
            @Override
            public void paint(Graphics g){
                g.drawImage(image, 50, 0, this);
            }
        };
        aFrame.add(canvas);
        canvas.setBounds(0, 0, 600, 550);
        aFrame.validate();

        // Ergänzt Knopf, der bei Wunsch das Bild speichert
        Button safe = new Button("Speichern");
        safe.setBounds(250, 600, 100, 50);
        safe.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e){
                   try {
                        File outputFile = new File("GrafikWurf.png");
                        ImageIO.write(image, "png", outputFile);
                        Desktop.getDesktop().open(outputFile);
                    } catch (IOException i) {
                        System.out.println("Fehler beim Speichern des Bildes: " + i.getMessage());
                    }
               }
        });
        aFrame.add(safe);
    }

    // Ergänzt Methode, die dafür sorgt, dass die Ergebnisse eines einzelnen Wurfs ausgegeben werden
    public void AusgabeEinzeln(double distanz, String parameter, String bedingungen, String start, boolean luWi){

        aFrame.removeAll();
        
        aFrame.setSize(600, 350);

        Label header = new Label("Wurf mit Parametern:", Label.CENTER);
        header.setFont(new Font("Arial", Font.PLAIN, 15));
        header.setBounds(225, 55, 150, 25);
        aFrame.add(header);

        Label para = new Label(parameter, Label.CENTER);
        para.setBounds(50, 100, 500, 15);
        aFrame.add(para);

        Label and = new Label("und", Label.CENTER);
        and.setBounds(275, 125, 50, 15);
        aFrame.add(and);

        Label bedingLabel = new Label(bedingungen, Label.CENTER);
        bedingLabel.setBounds(150, 150, 300,15);
        aFrame.add(bedingLabel);
        
        Label header2 = new Label("Fliegt mit folgender Distanz an der Korbmitte vorbei:", Label.CENTER);
        header2.setBounds(150, 190, 300, 15);
        aFrame.add(header2);

        double rounded = Math.round(distanz * 100.0) / 100.0;
        Label dist = new Label(rounded + " Meter", Label.CENTER);
        dist.setFont(new Font("Arial", Font.BOLD, 15));
        dist.setBounds(125, 225, 350, 25);
        aFrame.add(dist);

        String addOn = "";
        if (!luWi) {
            addOn = "nicht ";
        }
        Label zusatz = new Label("Luftwiderstand wurde " + addOn + "berücksichtigt", Label.CENTER);
        zusatz.setBounds(150, 275, 300, 15);
        aFrame.add(zusatz);

    }
}
