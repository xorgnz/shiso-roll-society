/**
 * 
 */
package com.shisorollsociety.expt1.view.ui;

import javax.swing.JPanel;

import org.memehazard.view.UIRegistry;
import org.memehazard.view.UIRegistry.UITag;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author xorgnz
 * 
 */
public class ViewPanel extends JPanel
{
    public WorldViewPanel  p_worldview     = new WorldViewPanel();
    public StatisticsPanel p_statistics = new StatisticsPanel();


    public enum Tags implements UITag
    {
        P_WORLD_VIEW,
        P_STATISTICS
    }


    public ViewPanel()
    {
        // Configure self
        FormLayout layout = new FormLayout("f:p:g", "f:p:g, f:250px");
        PanelBuilder builder = new PanelBuilder(layout, this);
        CellConstraints cc = new CellConstraints();

        // Assemble
        builder.add(p_worldview, cc.rc(1, 1));
        builder.add(p_statistics, cc.rc(2, 1));
        this.revalidate();

        // Registry
        UIRegistry.register(Tags.P_WORLD_VIEW, p_worldview);
        UIRegistry.register(Tags.P_STATISTICS, p_statistics);
    }
}
