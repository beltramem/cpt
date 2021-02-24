package com.M2BDIA;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Mesure mesure = new Mesure(1, "Beltramo", new Date(), 15, 150,11,15, 110);
        Sender send = new Sender("hello-world");
        try {
            send.send_object(mesure);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
