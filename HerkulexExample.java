package dongburobot.herkulex;

import dongburobot.herkulex.HerkuleX;
import dongburobot.herkulex.Serial;
import static java.sql.DriverManager.println;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author techgarage
 */
public class HerkulexExample {

    Serial myPort;
    HerkuleX myHerkuleX;
    //PApplet parent = new PApplet();
    ArrayList<Integer> servoIDs;

    void setup() throws Exception {
        // SerialNoPApplet myPort;
        String portName = Serial.list()[0];

        myPort = new Serial(portName, 115200);

        myHerkuleX = new HerkuleX(myPort);
        myHerkuleX.initialize();

        System.out.println("Scanning... It takes 8 seconds. Wait please.");
        servoIDs = myHerkuleX.performIDScan();  // 30ms * 254 = 7.62s
        System.out.println("Done.");
        System.out.println("Num of servos: " + servoIDs.size());

        for (int i = 0; i < servoIDs.size(); i++) {
            System.out.println("ID: " + servoIDs.get(i));
        }
    }

    public static void main(String[] args) throws Exception {
        HerkulexExample he = new HerkulexExample();
        he.setup();
    }
}
