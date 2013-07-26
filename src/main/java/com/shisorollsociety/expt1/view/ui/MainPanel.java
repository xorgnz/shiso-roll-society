package com.shisorollsociety.expt1.view.ui;

import javax.swing.JPanel;

import org.memehazard.view.UIRegistry;
import org.memehazard.view.UIRegistry.UITag;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class MainPanel extends JPanel
{
    public ViewPanel    p_view     = new ViewPanel();
    public ControlPanel p_controls = new ControlPanel();


    public enum Tags implements UITag
    {
        P_VIEW,
        P_CONTROLS
    }


    public MainPanel()
    {
        // Configure self
        FormLayout layout = new FormLayout("f:p:g, f:250px", "f:p:g");
        PanelBuilder builder = new PanelBuilder(layout, this);
        CellConstraints cc = new CellConstraints();

        // Assemble
        builder.add(p_view, cc.rc(1, 1));
        builder.add(p_controls, cc.rc(1, 2));
        this.revalidate();

        // Registry
        UIRegistry.register(Tags.P_VIEW, p_view);
        UIRegistry.register(Tags.P_CONTROLS, p_controls);
    }
}
