package dongburobot.herkulex;

import dongburobot.herkulex.HerkuleX;
import dongburobot.herkulex.Serial;
import java.io.IOException;
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
public class ServoMove {

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

        //myHerkuleX.addMove(219, 512, HerkuleX.LED_BLUE);
        //myHerkuleX.moveOne(219, 513, 500, HerkuleX.LED_BLUE);
        //myHerkuleX.moveOneAngle(219, 100, 500, HerkuleX.LED_GREEN);
        //myHerkuleX.addAngle(219, 10, HerkuleX.LED_GREEN);
        //myHerkuleX.moveSpeedOne(219, 10, 500, HerkuleX.LED_RED);
        //myHerkuleX.addSpeed(219, 10, HerkuleX.LED_RED);
    }

    void moveToPosition(int Pos) throws IOException {

        myHerkuleX.moveOne(servoIDs.get(0), Pos, 100, HerkuleX.LED_BLUE);
        System.out.println("Moving to pos " + Pos);
    }
    void returnPosition() throws IOException{
        System.out.println("At Pos " + myHerkuleX.getPosition(servoIDs.get(0)));
    }
    void moveToAngle(int Angle) throws IOException {
        myHerkuleX.moveOne(servoIDs.get(0), Angle, 100, HerkuleX.LED_GREEN);
        System.out.println("Moving to pos " + Angle);
    }

    void servoOff() {
        myHerkuleX.torqueOFF(servoIDs.get(0));
    }

    public static void main(String[] args) throws Exception {
        ServoMove he = new ServoMove();
        he.setup();
        int pos = 0;
        /*
        he.moveToPosition(10);
        Thread.sleep(3000);
        he.returnPosition();
        he.moveToPosition(500);
        Thread.sleep(3000);
        he.returnPosition();
        he.moveToPosition(1000);
        Thread.sleep(3000);
        he.returnPosition();
        he.servoOff();
        */
        he.moveToAngle(0);
        Thread.sleep(2000);
        he.moveToAngle(90);
        /*
        he.moveToPosition(0);
        Thread.sleep(1000);
        he.moveToPosition(50);
        Thread.sleep(1000);
        he.moveToPosition(100);
        Thread.sleep(1000);
        he.moveToPosition(150);
        Thread.sleep(1000);
        he.moveToPosition(200);
        Thread.sleep(1000);
         */
    }
}
