/**
 * 
 */
package com.shisorollsociety.expt1.sim.agents;

import com.shisorollsociety.expt1.sim.Agent;
import com.shisorollsociety.expt1.sim.Tile;

/**
 * @author xorgnz
 * 
 */
public class Wolf extends Agent
{
    private Sheep targetPrey;


    public Wolf(Tile locationTile)
    {
        super(locationTile, WolfAgent.WOLF);
    }


    private void findSheep()
    {

    }


    private void findThreat()
    {

    }


    private void findRefuge()
    {

    }


    private void findFood()
    {

    }


    private void eatFood()
    {

    }


    /**
     * @see com.shisorollsociety.expt1.sim.Agent#tick()
     */
    @Override
    public void tick()
    {
        // TODO Auto-generated method stub

    }


    private static enum WolfAgent implements Type
    {
        WOLF
    }
}
