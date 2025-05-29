import java.awt.*;
import java.awt.image.*;

/*
 * Beschreibung: Klasse zur Simulation eines Basketballwurfs & Erstellung eines zugehörigen Bildes
 * @version 1.0
 * @author Leon Stopper
 * 
 */

public class App {

    // Deklarierung der Attribute für die Simulation eines Wurfes 
    private double höhekorb;
    private double geschwindigkeit;
    private double entfernungspieler;
    private double wurfhöhe;
    private double winkel;
    private double gravitation;
    
    // Deklarierung der Attribute für den Luftwiderstand
    private double flächeBall;
    private double cwWert;
    private double luftDichte;
    private double masseBall;

    // Deklarierung einer Matrix, die für versch. Winkel & Geschwindigkeiten Entfernungen zum Korb speichert
    private double[][] matrix;

    // Deklarierung der Attribute, die zur Simulation mehrerer Würfe wichtig sind
    private int anzahlSchritteW;
    private double abstandSchritteW;
    private int anzahlSchritteG;
    private double abstandSchritteG;

    // Deklarierung der Attribute für die Farbzuweisung der Kästchen in der Grafik 
    private double distWhite;
    private double distGreen;
    private double distYellow;

    // Deklarierung Attribute, die für Speicherung der Schluss Werte der Simulation zuständig sind
    private double endGeschw;
    private double endWin;

    // Deklarierung eines Referenzattributs zur Ausgabe
    private Ausgabe ausgabe;

    // Methode, die Matrix initialisiert mit gegebenen Längen
    public void MatrixErstellen(int winAnz, int geschAnz){
        matrix = new double[winAnz][geschAnz];
    }

    // Methode, die einzelnen Wurf simulieren kann und Entfernung zum Korb zurück gibt
    public double WurfSimulation(){

        // Deklarierung & Initialisierung lokaler Variablen, die vertikale und horizontale Position speichern
        double distanzzukorb = entfernungspieler;
        double höheball = wurfhöhe;

        // Berechnung horizontale und vertikale Geschwindigkeit aus Wurfgeschwindigkeit und Winkel
        double bogenmaß = Math.toRadians(winkel);
        double geschwindigkeitx = Math.cos(bogenmaß) * geschwindigkeit;
        double geschwindigkeity = Math.sin(bogenmaß) * geschwindigkeit;

        // While-Schleife, die mit der Methode der kleinen Schritte Wurf des Balls simuliert 
        // Abbruch Bedingung ist eine negative Geschwindigkeit und eine Position des Balls unter dem Korb
        while (höheball >= höhekorb || geschwindigkeity > 0) {

            // Berechnet die Kräfte des Luftwiderstands, die in vertikale und horizontale Richtung wirken
            double luftwiderstandx = 0.5 * cwWert * luftDichte * flächeBall * geschwindigkeitx * geschwindigkeitx;
            double luftwiderstandy = 0.5 * cwWert * luftDichte * flächeBall * geschwindigkeity * geschwindigkeity;

            // Berechnet die Bewegung des Balls in vertikale und horizontale Richtung
            distanzzukorb = distanzzukorb - (geschwindigkeitx * 0.001);
            höheball = höheball + (geschwindigkeity * 0.001);

            // Berechnet die Geschwindigkeitsänderung in vertikale und horizontale Richtung
            geschwindigkeitx = geschwindigkeitx - ((luftwiderstandx/masseBall) * 0.001);
            geschwindigkeity = geschwindigkeity + (gravitation * 0.001) - (Math.signum(geschwindigkeity) * (luftwiderstandy/masseBall) * 0.001);
        }
        return distanzzukorb;
    }

    // Methode, die mit gegebenen Werten eine bestimmte Anzahl an Würfen simuliert
    public void Simulation(){

        // Erstellt Matrix mit Längen passend zu der Anzahl der Schritte 
        MatrixErstellen(anzahlSchritteW, anzahlSchritteG);

        // Initialisierung lokaler Attribute, die zur Simulation als Speicher benötigt werden
        double winkelSimu = winkel;
        double geschSimu = geschwindigkeit;

        // Befüllung jedes Platzes der Matrix mit Distanzen zum Korb
        for(int v = 0; v < matrix[0].length; v ++){              
            winkel = winkelSimu;
            for(int w = 0; w < matrix.length; w ++ ){
                matrix[w][v] = WurfSimulation();
                winkel = winkel + abstandSchritteW;
            }
            geschwindigkeit = geschwindigkeit + abstandSchritteG;  
        }

        // Rundet die Endwerte mit Hilfe der aus der Simulation zuletzt verwendeten Werte
        endGeschw = Math.round((geschwindigkeit - abstandSchritteG) * 10) / 10;
        endWin = Math.round((winkel - abstandSchritteW) * 10) / 10;

        // Setzt die Werte wieder auf die Startwerte zurück
        winkel = winkelSimu;
        geschwindigkeit = geschSimu;       
    }

    // Methode, die mehrere Würfe simuliert und anschließend eine Grafik ausgibt
    public void GrafikSimulation(){
        Simulation();
        AusgabeErstellen();
        BildErstellen();
    }

    // Gibt die Distanzen zum Korb für jeden Index der Matrix in der Konsole aus
    public void MatrixAusgabe(){
        String s = "";
        for(int x = 0; x < matrix.length; x++){
            s = "";
            for(int y = 0; y < matrix[0].length; y++){
              s = s + " " + Math.round(matrix[x][y] * 100.0) / 100.0;
            }
            System.out.println(s);
            System.out.println(" ");
        } 
    }
    
    // Methode, die mit Hilfe eines übergebenen Feldes die notwendigen Attribute initialisiert
    public void WerteÜbergabe(double[] übergabeWerte){
        höhekorb = übergabeWerte[0];
        entfernungspieler = übergabeWerte[1];
        wurfhöhe = übergabeWerte[2];
        gravitation = übergabeWerte[3] * -1;
        geschwindigkeit = übergabeWerte[4];
        winkel = übergabeWerte[5];
        anzahlSchritteG = (int) übergabeWerte[6];
        anzahlSchritteW = (int) übergabeWerte[7];
        abstandSchritteG = übergabeWerte[8];
        abstandSchritteW = übergabeWerte[9];
        distWhite = übergabeWerte[10];
        distGreen = übergabeWerte[11];
        distYellow = übergabeWerte[12];
    }

    // Methode, die mit Hilfe eines übergebenen Feldes die notwendigen Luftwiderstand Werte initialisiert
    public void LuWIWerteÜbergabe(double übergabeWerte[]){
        if(übergabeWerte[3] != 0){
        flächeBall = übergabeWerte[0] * übergabeWerte[0] * Math.PI;
        masseBall = übergabeWerte[1];
        cwWert = übergabeWerte[2];
        luftDichte = übergabeWerte[3] / (übergabeWerte[4] * 287.058);
        } else{
            flächeBall = 0;
            cwWert = 0;
            luftDichte = 0;
            masseBall = 1;
        }
    }

    // Methode die eine Ausgabe erstellt, falls noch keine existieren sollte
    public void AusgabeErstellen(){
        if(ausgabe == null){
           ausgabe = new Ausgabe(this);
        }
    }

    // Methode, die das Ausgabe Attribut leert
    public void AusgabeLöschen(){
        ausgabe = null;
    }

    // Methode, die einen einzelnen Wurf simuliert und das Ergebnis der Ausgabe übermittelt
    public void EineSimulation(){
        double distanz = WurfSimulation();
        String parameter = "Korbhöhe: " + höhekorb + ", Entfernung: " + entfernungspieler + ", Wurfhöhe: " + wurfhöhe + ", Gravitation: "  + gravitation;
        String bedingungen = "Geschwindigkeit: " + geschwindigkeit + ", Winkel: " + winkel;
        String start = "Geschwindigkeit: " + geschwindigkeit + ", Winkel: " + winkel;
        boolean luWiBe = false;
        if (luftDichte != 0) {
            luWiBe = true;
        }
        AusgabeErstellen();
        ausgabe.AusgabeEinzeln(distanz, parameter, bedingungen, start, luWiBe);
    }

    // Methode, die ein zur Simulation zugehöriges Bild erstellt
    public void BildErstellen() {
        // Bestimmt Länge und Höhe des Bilds
        int width = 500;
        int height = 550;
        
        // Erstellt Objekte, die zur Erstellung des Bilds gebraucht werden
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
    
        // Setzt Hintergrund Farbe
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);
    
        // Bestimmt Abstand zum vertikalen und horiontalen Bildrand
        int margin = 50;
        int marginy = 100;

        // Berechnet Größe eines Kästchens passend zur Anzahl der Schritte, sodass Grafik so groß wie möglich ist
        int squareSize = Math.round((width - 2 * margin) / anzahlSchritteG);
        if (matrix[0].length <= matrix.length) {
            squareSize = Math.round((height - 3 * margin) / anzahlSchritteW);
        }

        // Zeichnet zuerst die x-, dann die y-Achse passend zur Breite der Grafik
        g2d.setColor(Color.BLACK);
        int dist = height - (margin * 3) - (squareSize * anzahlSchritteW);
        g2d.drawLine(margin, height - marginy, squareSize * anzahlSchritteG + margin + 10, height - marginy);
        g2d.drawLine(margin - 1, margin + dist - 10, margin - 1, height - marginy); 

        // Bestimmt Start y-Koordinate der Kästchen
        int starty = height - marginy - squareSize;

        // Erstellt jedes Kästchen
        for (int x = 0; x < matrix[0].length; x++){
            for (int y = 0; y < matrix.length; y ++){
                // Bestimmt Farbe der Kästchen
                g2d.setColor(Color.RED);
                if(Math.abs(matrix[y][x]) < distWhite){
                    g2d.setColor(Color.WHITE);
                }else if(Math.abs(matrix[y][x]) < distGreen){
                    g2d.setColor(Color.GREEN);
                }else if(Math.abs(matrix[y][x]) < distYellow){
                    g2d.setColor(Color.YELLOW);
                }
                g2d.fillRect(margin + x * squareSize, (starty - y * squareSize), squareSize, squareSize);
            }
        }

        // Ergänzt Achsen-Beschriftung
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.PLAIN, 10));
        g2d.drawString("v in m/s", squareSize * anzahlSchritteG + margin + 10, 460);
        g2d.drawString("Winkel", 15, margin + dist - 10);

        // Ergänzt die Startwerte an Ursprung der Grafik
        g2d.drawString("" + geschwindigkeit, margin, height - marginy + 15);
        g2d.drawString("" + winkel, 15, height - marginy);

        // Ergänzt die Endwerte an das Ende der Achsen
        g2d.drawString("" + endGeschw, squareSize * anzahlSchritteG + margin - 15, 460);
        g2d.drawString("" + endWin, 15, margin + dist + 10);

        // Ergänzt die Parameter des Wurfes in Grafik
        g2d.setFont(new Font("Arial", Font.PLAIN, 15));
        String print = "Korbhöhe: " + höhekorb + ", Entfernung: " + entfernungspieler + ", Wurfhöhe: " + wurfhöhe + ", Gravitation: "  + gravitation;
        g2d.drawString(print, 25, 500);

        // Ergänzt eine Beschriftung die angibt, ob der Luftwiderstand berücksichtigt wurde
        String luWid = "";
        if (luftDichte == 0) {
            luWid = "nicht ";
        }
        String luWiPrint = "Der Luftwiderstand wurde " + luWid + "berücksichtigt";
        g2d.drawString(luWiPrint, 25, 525);
        
        // Speichert die Ergebnisse und gibt sie an die Ausgabe weiter
        g2d.dispose();
        ausgabe.AusgabeWuerfe(image);
    }
}