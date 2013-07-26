package com.shisorollsociety.expt1.view.ui.actions;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.shisorollsociety.expt1.App;

public class ShutdownApplicationAction extends AbstractAction
{
    @Override
    public void actionPerformed(ActionEvent e)
    {
        App.shutdown();
    }
}
