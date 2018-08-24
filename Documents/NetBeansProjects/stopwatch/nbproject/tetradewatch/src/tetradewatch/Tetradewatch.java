/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetradewatch;

import javax.swing.JFrame;

/**
 *
 * @author Nathan Home PC
 */
public class Tetradewatch {

    boolean shouldCount=true;
    int int_sec=0;
    int int_min=0;
    int int_mil=0;

    public Tetradewatch() {
    }
    
    public static void main(String[] args) {
        watchform t = new watchform();
        JFrame f = new JFrame("Timer");
        f.setSize(300,200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null);
        f.getContentPane().add(t);
        f.setVisible(true);
    }

    Thread time;
    
    public void count() throws InterruptedException
    {
        time = new Thread(() -> {
        long now = System.currentTimeMillis();
        while(shouldCount)
        {
            if(System.currentTimeMillis()-now>=100)
            {
                now=System.currentTimeMillis();
                int_mil++;
                if(int_mil>9)
                {
                    int_mil=0;
                    int_sec++;
                    if(int_sec>=60)
                    {
                        int_sec=1;
                        int_min++;
                    }
                }
            }

        }        
        });
        
        if(shouldCount)
        {
            time.start();
        }
        else
        {
            time.interrupt();
            time = null;
        }
    }
    
}

