/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dongburobot.herkulex;

import static com.sun.org.apache.xerces.internal.util.FeatureState.is;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.IOUtils;

/**
 *
 * @author techgarage
 */
public class Serial {

    static Enumeration portList;
    static CommPortIdentifier portId;
    static String messageString = "rohit kumar saraf";
    static SerialPort serialPort;
    static OutputStream outputStream;
    static boolean outputBufferEmptyFlag = false;
    byte[] buffer = new byte[32768];
    InputStream inStream;
    int bufferUntilSize = 1;
    byte bufferUntilByte = 0;
    int inBuffer = 0;

    public Serial(String port, int baudrate) throws Exception {
        connect(port, baudrate);
    }

    void connect(String port, int baudrate) throws Exception {
        portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();

            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {

                if (portId.getName().equals(port)) {
                    System.out.println("Found port " + port);
                    try {
                        serialPort = (SerialPort) portId.open("SimpleWrite", 2000);
                        System.out.println("Connected");
                    } catch (PortInUseException e) {
                        System.out.println("Port in use.");
                    }

                    try {
                        outputStream = serialPort.getOutputStream();
                    } catch (IOException e) {
                    }
                    try {
                        inStream = serialPort.getInputStream();
                    } catch (IOException e) {
                    }
                    try {
                        serialPort.setSerialPortParams(baudrate,
                                SerialPort.DATABITS_8,
                                SerialPort.STOPBITS_1,
                                SerialPort.PARITY_NONE);
                    } catch (UnsupportedCommOperationException e) {
                    }

                    try {
                        serialPort.notifyOnOutputEmpty(true);
                    } catch (Exception e) {

                        System.out.println("Error setting event notification");
                        System.out.println(e.toString());
                        System.exit(-1);
                    }
                }
            }
        }
    }

    public int read() throws IOException {
        return inStream.read();
    }
    public void write(byte[] src) {
        try {
            outputStream.write(src);
            outputStream.flush();
            //System.out.println("Writing Data");
        } catch (IOException e) {
        }
    }

    public int available() throws IOException {
        return inStream.available();
    }

    static public String[] list() {
        String comPorts[] = new String[10];
        int counter = 0;
        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
            System.out.println(portIdentifier.getName());
            comPorts[counter] = portIdentifier.getName();
            counter += 1;
        }
        return comPorts;
    }

}
