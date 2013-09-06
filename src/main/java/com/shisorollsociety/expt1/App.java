/**
 * 
 */
package com.shisorollsociety.expt1;

import org.memehazard.log.Log;

import com.shisorollsociety.expt1.view.View;

/**
 * @author xorgnz
 * 
 */
public class App
{
    private static View view;


    /**
     * Entry point for application
     * 
     * @param argv
     *            Argument list (ignored - this application takes no arguments)
     */
    public static void main(String[] argv)
    {
        Log.configureBasic();
        Log.debug("App - starting up");

        view = new View();
        view.show();
        
        

        Log.debug("App - startup complete");
    }


    /**
     * Shut down the application.
     * 
     * This method is responsible for shutting down all application processes and releasing all resources
     */
    public static void shutdown()
    {
        view.shutdown();
        System.exit(0);
    }
}
