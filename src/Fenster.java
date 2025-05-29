import java.awt.*;
import java.awt.event.*;

/*
 * Beschreibung: Klasse die Oberfläche zur Verwaltung der Simulation der Würfe erstellt 
 * @version 1.0
 * @author Leon Stopper
 * 
 */

public class Fenster {

    // Main-Methode die bei aufrufen des Programms ausgeführt wird
    public static void main(String[] args){

        // Erstellt neues Fenster Objekt
        Frame frame = new Frame("Wurf-Simulation");

        // Erstellt neues App Objekt
        App a = new App();

        // Setzt Größe und Layout des Fensters 
        frame.setSize(800, 600);
        frame.setLayout(null);

        // Ergänzt die Funktion das Programm bei Schließen des Fensters abbricht
        frame.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent we){
                System.exit(0);
            }
        });


        // Ergänzt Überschrift
        Label bLabel = new Label("Wurf-Simulation");
        bLabel.setBounds(275,25, 320, 50);
        bLabel.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(bLabel);


        // Ergänzt Überschrift für Parameter 
        Label paraLabel = new Label("Parameter");
        paraLabel.setBounds(25, 80, 100, 25);
        paraLabel.setFont(new Font("Arial", Font.BOLD, 15));
        frame.add(paraLabel);


        // Im folgenden werden Schrift und Textfeld für die Parameter ergänzt
        Label hLabel = new Label("Korbhöhe in m:");
        hLabel.setBounds(50, 125, 100, 25);
        frame.add(hLabel);
        TextField hField = new TextField(10);
        hField.setBounds(150, 125, 100, 25);
        hField.setText("3.05");
        frame.add(hField);

        Label eLabel = new Label("Entfernung in m:");
        eLabel.setBounds(450, 125, 100, 25);
        frame.add(eLabel);
        TextField eField = new TextField(10);
        eField.setBounds(570, 125, 100, 25);
        eField.setText("6");
        frame.add(eField);



        Label wLabel = new Label("Wurfhöhe in m:");
        wLabel.setBounds(50, 165, 100, 25);
        frame.add(wLabel);
        TextField wField = new TextField(10);
        wField.setBounds(150, 165, 100, 25);
        wField.setText("2");
        frame.add(wField);

        Label gLabel = new Label("Gravitation in m/s²:");
        gLabel.setBounds(440, 165, 125, 25);
        frame.add(gLabel);
        TextField gField = new TextField(10);
        gField.setBounds(570, 165, 100, 25);
        gField.setText("9.81");
        frame.add(gField);



        Label geschLabel = new Label("Startgeschwindigkeit: ");
        geschLabel.setBounds(20, 205, 125, 25);
        frame.add(geschLabel);
        TextField geschField = new TextField(10);
        geschField.setBounds(150, 205, 100, 25);
        geschField.setText("7");
        frame.add(geschField);

        Label winkLabel = new Label("Startwinkel: ");
        winkLabel.setBounds(450, 205, 100, 25);
        frame.add(winkLabel);
        TextField winkField = new TextField(10);
        winkField.setBounds(570, 205, 100, 25);
        winkField.setText("25");
        frame.add(winkField);



        Label radiusBLabel = new Label("Radius Ball:");
        radiusBLabel.setBounds(125, 255, 75, 25);   
        TextField radiusBField = new TextField(5);
        radiusBField.setBounds(200, 255, 40, 25);        
        radiusBField.setText("0.119");   
        
        Label masseBallLabel = new Label("Masse Ball:");
        masseBallLabel.setBounds(250, 255, 75, 25); 
        TextField masseBallField = new TextField(5);
        masseBallField.setBounds(325, 255, 40, 25);
        masseBallField.setText("0.65");  
        

        Label cwWertLabel = new Label("Cw-Wert:");
        cwWertLabel.setBounds(375, 255, 55, 25); 
        TextField cwWertField = new TextField(5);
        cwWertField.setBounds(450, 255, 40, 25);
        cwWertField.setText("0.47");      
        

        Label lDruckLabel = new Label("Luftdruck:");
        lDruckLabel.setBounds(500, 255, 75, 25); 
        TextField lDruckField = new TextField(5);
        lDruckField.setBounds(575, 255, 40, 25);    
        lDruckField.setText("971");  
        

        Label lTempLabel = new Label("Temperatur:");
        lTempLabel.setBounds(625, 255, 75, 25);
        TextField lTempField = new TextField(5);
        lTempField.setBounds(700, 255, 40, 25);
        lTempField.setText("20");



        Checkbox lwCheckbox = new Checkbox("Luftwiderstand", false);
        lwCheckbox.setBounds(350, 255, 100, 25);

        lwCheckbox.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                if(e.getStateChange() == ItemEvent.SELECTED){
                    lwCheckbox.setLocation(20, 255);
                    frame.add(radiusBLabel);   
                    frame.add(radiusBField);
                    frame.add(masseBallLabel);
                    frame.add(masseBallField);
                    frame.add(cwWertLabel);
                    frame.add(cwWertField);
                    frame.add(lDruckLabel);
                    frame.add(lDruckField);
                    frame.add(lTempLabel);
                    frame.add(lTempField);
                }else{
                    lwCheckbox.setLocation(350, 255);frame.add(radiusBLabel);   
                    frame.remove(radiusBLabel);   
                    frame.remove(radiusBField);
                    frame.remove(masseBallLabel);
                    frame.remove(masseBallField);
                    frame.remove(cwWertLabel);
                    frame.remove(cwWertField);
                    frame.remove(lDruckLabel);
                    frame.remove(lDruckField);
                    frame.remove(lTempLabel);
                    frame.remove(lTempField);
                }
            }
        });

        frame.add(lwCheckbox);

        // Ergänzt Überschrift für die Einstellungen der Simulation
        Label bEinstellungLabel = new Label("Simulations Einstellungen");
        bEinstellungLabel.setBounds(25, 310, 200, 25);
        bEinstellungLabel.setFont(new Font("Arial", Font.BOLD, 15));
        frame.add(bEinstellungLabel);


        // Im folgenden werden Schrift und Textfeld für die Simulations Einstellungen ergänzt
        Label geschAnzLabel = new Label("Anzahl Schritte:");
        geschAnzLabel.setBounds(50, 355, 100, 25);
        frame.add(geschAnzLabel);
        TextField geschAnzField = new TextField(10);
        geschAnzField.setBounds(150, 355, 100, 25);
        geschAnzField.setText("400");
        frame.add(geschAnzField);

        Label winkAnzLabel = new Label("Anzahl Schritte:");
        winkAnzLabel.setBounds(450, 355, 100, 25);
        frame.add(winkAnzLabel);
        TextField winkAnzField = new TextField(10);
        winkAnzField.setBounds(570, 355, 100, 25);
        winkAnzField.setText("300");
        frame.add(winkAnzField);



        Label geschAbstLabel = new Label("Abstand Schritte:");
        geschAbstLabel.setBounds(50, 395, 100, 25);
        frame.add(geschAbstLabel);
        TextField geschAbstField = new TextField(10);
        geschAbstField.setBounds(150, 395, 100, 25);
        geschAbstField.setText("0.01");
        frame.add(geschAbstField);

        Label winkAbstLabel = new Label("Abstand Schritte:");
        winkAbstLabel.setBounds(450, 395, 100, 25);
        frame.add(winkAbstLabel);
        TextField winkAbstField = new TextField(10);
        winkAbstField.setBounds(570, 395, 100, 25);
        winkAbstField.setText("0.15");
        frame.add(winkAbstField);


        // Ergänzt Schrift und Textfeld für die Zuweisungen zu den Farben
        Label distWhLabel = new Label("Abstand für weiß:");
        distWhLabel.setBounds(50, 435, 100, 25);
        frame.add(distWhLabel);
        TextField distWhField = new TextField(10);
        distWhField.setBounds(150, 435, 100, 25);
        distWhField.setText("0.05");
        frame.add(distWhField);

        Label distGrLabel = new Label("Abstand für grün:");
        distGrLabel.setBounds(300, 435, 100, 25);
        frame.add(distGrLabel);
        TextField distGrField = new TextField(10);
        distGrField.setBounds(400, 435, 100, 25);
        distGrField.setText("0.15");
        frame.add(distGrField);

        Label distYllwLabel = new Label("Abstand für gelb:");
        distYllwLabel.setBounds(550, 435, 100, 25);
        frame.add(distYllwLabel);
        TextField distYllwField = new TextField(10);
        distYllwField.setBounds(650, 435, 100, 25);
        distYllwField.setText("0.5");
        frame.add(distYllwField);


        // Ergänzt eine Schrift die bei Falscher Eingabe sichtbar wird
        Label eingErrorLabel = new Label("Eingabe ungültig");
        eingErrorLabel.setBounds(355, 550, 100, 25);
        eingErrorLabel.setForeground(Color.red);
        eingErrorLabel.setVisible(false);
        frame.add(eingErrorLabel);  



        // Erstellt Feld das als Speicher der Werte agiert
        double[] luWiWerte = new double[5];
        double[] werte = new double[13];

        ActionListener l = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){ 
               try {
                // Versucht das Feld zufüllen; bei falscher Eingabe für abgebrochen und "Eingabe ungültig" angezeigt
                werte[0] = Double.parseDouble(hField.getText());
                werte[1] = Double.parseDouble(eField.getText());
                werte[2] = Double.parseDouble(wField.getText());
                werte[3] = Double.parseDouble(gField.getText());
                werte[4] = Double.parseDouble(geschField.getText());
                werte[5] = Double.parseDouble(winkField.getText());
                werte[6] = Double.parseDouble(geschAnzField.getText());
                werte[7] = Double.parseDouble(winkAnzField.getText());
                werte[8] = Double.parseDouble(geschAbstField.getText());
                werte[9] = Double.parseDouble(winkAbstField.getText());
                werte[10] = Double.parseDouble(distWhField.getText());
                werte[11] = Double.parseDouble(distGrField.getText());
                werte[12] = Double.parseDouble(distYllwField.getText());

                if(lwCheckbox.getState()){
                 luWiWerte[0] = Double.parseDouble(radiusBField.getText());
                 luWiWerte[1] = Double.parseDouble(masseBallField.getText());
                 luWiWerte[2] = Double.parseDouble(cwWertField.getText());
                 luWiWerte[3] = Double.parseDouble(lDruckField.getText());
                 luWiWerte[4] = Double.parseDouble(lTempField.getText());
                } else{
                   for(int i = 0; i < 5; i++){
                     luWiWerte[i] = 0;
                    }
                }
            }
            catch (NumberFormatException d){
             eingErrorLabel.setVisible(true);
             return;
            }
            
            // Testet die Werte auf Gültigkeit & übergibt ggf. die Werte
            if(!WerteCheck(werte) || !LuWICheck(luWiWerte)){
                eingErrorLabel.setVisible(true);
            }else {
                if(werte[6] > 400 || werte[7] > 400) eingErrorLabel.setVisible(true);
                else{
                a.WerteÜbergabe(werte);
                a.LuWIWerteÜbergabe(luWiWerte);
                        
                eingErrorLabel.setVisible(false);
                }
            }
            }
        };

        // Ergänzt den Knopf zu Beginn der Simulation
        Button sButton = new Button("Simulieren");
        sButton.setBounds(350, 495, 100, 50);
        sButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
            
               l.actionPerformed(e);

               if(!eingErrorLabel.isVisible()){
                  a.GrafikSimulation();
                }
            }
        });
        frame.add(sButton);

        // Ergänzt Knopf zur Ausgabe der Matrix in Konsole
        Button mAusgButton = new Button("Matrix Ausgabe");
        mAusgButton.setBounds(150, 495, 100, 50);
        mAusgButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                l.actionPerformed(e);

                if(!eingErrorLabel.isVisible()){
                  a.Simulation();
                  a.MatrixAusgabe();
                }
            }
        });
        frame.add(mAusgButton);

        Button einzelSimuButton = new Button("Einzel Wurf");
        einzelSimuButton.setBounds(550, 495, 100, 50);
        einzelSimuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){

                l.actionPerformed(e);

                if(!eingErrorLabel.isVisible()){
                    a.EineSimulation();
                }
            }
        });
        frame.add(einzelSimuButton);

        // Setzt Position des Fensters
        frame.setLocation(400, 100);
        frame.setResizable(false);
        frame.setVisible(true);

    }  


    // Statische Methode zur Überprüfung der Gültigkeit von einem übergebenen Wertefeld
    public static boolean WerteCheck(double[] übergabeWerte){
        for(int i = 0; i < übergabeWerte.length; i++){
            if (übergabeWerte[i] <= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean LuWICheck(double[] übergabeWerte){
       if(übergabeWerte[0] < 0 || übergabeWerte[1] < 0) {
        return false;
       }
       if(übergabeWerte[2] < 0 || übergabeWerte[2] >= 1){
        return false;
       }
       if (übergabeWerte[3] < 0 || übergabeWerte[4] < 0) {
        return false;
       }
       return true;
    }
}