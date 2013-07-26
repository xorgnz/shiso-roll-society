/**
 * 
 */
package com.shisorollsociety.expt1.view;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.memehazard.log.Log;
import org.memehazard.log.LogListener;

/**
 * @author xorgnz
 * 
 */
public class View
{
    public static final Dimension SIZE   = new Dimension(640, 480);
    public static final String    TITLE  = "Shiso Roll Society";


    private static JFrame         messageFrame;
    private MainFrame             f_main = null;


    public static Dimension getMaximumFrameSize()
    {
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return new Dimension(maxBounds.width, maxBounds.height);
    }


    public static JFrame getMessageFrame()
    {
        return messageFrame;
    }


    public static void showErrorMessage(String msg)
    {
        JOptionPane.showMessageDialog(messageFrame, msg, "Warning", JOptionPane.ERROR_MESSAGE);
    }


    public static void showInfoMessage(String msg)
    {
        JOptionPane.showMessageDialog(messageFrame, msg);
    }


    public static void showWarningMessage(String msg)
    {
        JOptionPane.showMessageDialog(messageFrame, msg, "Warning", JOptionPane.WARNING_MESSAGE);
    }


    public void setMessageFrame(JFrame frame)
    {
        messageFrame = frame;
    }


    /**
     * Show the view!
     */
    public void show()
    {
        f_main = new MainFrame(TITLE, getMaximumFrameSize());
        LogListener logListener = f_main.generateLogListener();
        Log.addLogListener(logListener, Log.Level.FATAL);
        Log.addLogListener(logListener, Log.Level.ERROR);
        Log.addLogListener(logListener, Log.Level.WARNING);

        f_main.configure();
        f_main.construct();

        this.setMessageFrame(f_main);
    }


    /**
     * Close the view, and release all resources.
     */
    public void shutdown()
    {
        f_main.dispose();
    }
}
