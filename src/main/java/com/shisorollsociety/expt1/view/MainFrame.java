package com.shisorollsociety.expt1.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.memehazard.log.LogListener;
import org.memehazard.view.UIRegistry;
import org.memehazard.view.UIRegistry.UITag;

import com.shisorollsociety.expt1.App;
import com.shisorollsociety.expt1.view.ui.MainPanel;
import com.shisorollsociety.expt1.view.ui.actions.ShutdownApplicationAction;

/**
 * General application frame.
 * 
 * To use, you would typically:
 * - create a subclass specific to your application
 * - instantiate the subclass
 * - call configure (configure the frame to make the frame ready for use)
 * - call construct (create the actual frame and all subclasses)
 * 
 * @author xorgnz
 * 
 */
public class MainFrame extends JFrame
{
    protected JMenuBar       menuBar        = null;

    protected WindowListener windowListener = null;
    private MainPanel        p_main         = new MainPanel();
    private Dimension        size           = null;


    private String           title          = "";


    public MainFrame(String title, Dimension size)
    {
        this.title = title;
        this.size = size;
    }


    /**
     * Configure this AppFrame. Entails setting all necessary configuration values.
     * 
     * May be over-ridden. If doing so,
     */
    public void configure()
    {
        // Initialize this view's frame
        this.setTitle(title);
        this.setPreferredSize(size);
        this.setUndecorated(true);
        this.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        this.setResizable(true);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }


    /**
     * Construct this frame. Entails instantiating / installing menu, content, and listeners, then packing and making
     * frame visible
     * 
     * Default implementation:
     * - Calls <code>.generateMenuBar()</code> to obtain a menu bar to use
     * - Sets a simple window listener
     * - Calls <code>.generateMainPanel()</code> to obtain a panel for use as this frame's content pane. This panel is
     * resized to fit the frame
     * 
     * @throws NullPointerException
     *             If <code>.generateMainPanel()</code> returns a null content panel
     */
    public void construct()
    {
        // Install menu
        menuBar = generateMenuBar();
        if (menuBar != null)
        {
            this.setJMenuBar(menuBar);
            menuBar.setVisible(true);
        }

        // Install window listener
        windowListener = new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                App.shutdown();
            }
        };
        if (windowListener == null)
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        else
        {
            this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            this.addWindowListener(windowListener);
        }

        // Install main panel
        this.p_main = new MainPanel();
        UIRegistry.register(Tags.P_MAIN, p_main);
        p_main.setPreferredSize(this.getSize());
        this.setContentPane(p_main);

        // Pack and make visible
        this.pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }


    /**
     * Configure an error reporter to be able to display errors and other messages relative
     * to this frame.
     * 
     * @param error
     *            Error Reporter to configure
     */
    public LogListener generateLogListener()
    {
        return new PopupDialogLogListener(this);
    }


    /**
     * Revalidates and repaints the main panel of this frame, triggering an update at the next opportunity
     */
    public void update()
    {
        p_main.revalidate();
        p_main.repaint();
    }


    /**
     * Generate the menu bar
     * 
     * @return A menu bar for this frame. If <code>null</code> is returned, no menu is created.
     */
    protected JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();

        // Menu - Main
        JMenu menu_main = new JMenu("Main");
        menuBar.add(menu_main);

        // Menu Item - Close application
        JMenuItem mi_close = new JMenuItem("Exit");
        mi_close.addActionListener(new ShutdownApplicationAction());
        mi_close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        menu_main.add(mi_close);

        return menuBar;
    }


    /**
     * Simple log listener that generates pop-up messages whenever important log events occur (by default, errors or
     * fatal errors)
     * 
     * @author xorgnz
     */
    public class PopupDialogLogListener implements LogListener
    {
        private JFrame frame = null;


        public PopupDialogLogListener(JFrame frame)
        {
            this.frame = frame;
        }


        @Override
        public void debug(String msg, Throwable t)
        {
            // Ignore debug messages
        }


        @Override
        public void error(String msg, Throwable t)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Error: " + msg + "\n");
            if (t != null)
                sb.append(ExceptionUtils.getFullStackTrace(t));

            JOptionPane.showMessageDialog(this.frame, sb.toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }


        @Override
        public void fatal(String msg, Throwable t)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Fatal Error: " + msg + "\n");
            if (t != null)
                sb.append(ExceptionUtils.getFullStackTrace(t));

            JOptionPane.showMessageDialog(this.frame, sb.toString(), "FATAL ERROR", JOptionPane.ERROR_MESSAGE);
        }


        @Override
        public void info(String msg, Throwable t)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Information: " + msg + "\n");
            if (t != null)
                sb.append(ExceptionUtils.getFullStackTrace(t));

            JOptionPane.showMessageDialog(this.frame, sb.toString(), "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
        }


        @Override
        public void warn(String msg, Throwable t)
        {
            StringBuilder sb = new StringBuilder();
            sb.append("Warning: " + msg + "\n");
            if (t != null)
                sb.append(ExceptionUtils.getFullStackTrace(t));

            JOptionPane.showMessageDialog(this.frame, sb.toString(), "WARNING", JOptionPane.WARNING_MESSAGE);
        }
    }


    public enum Tags implements UITag
    {
        P_MAIN,
    }
}
