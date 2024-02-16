package org.labs;

import java.io.DataInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

class TCPEchoReader extends Thread{
    SimpleDateFormat d,h;
    public TCPEchoReader(DataInputStream input){
        dataInputStream = input;
        active = true;
        d= new SimpleDateFormat("dd.MM.yyyy");
        h= new SimpleDateFormat("HH:mm:ss");
    }
    public void run(){
        System.out.println(d.format(new Date()));
        while(active)
        {
            try {
                System.out.println(dataInputStream.readUTF()+" received back at "+h.format(new Date()));
            }
            catch(IOException e)
            {
                System.out.println(e);
                active = false;
            }
        }
    }
    public boolean active;
    public DataInputStream dataInputStream;
}