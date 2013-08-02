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
public class Sheep extends Agent
{
    private int wooliness;


    public Sheep(Tile locationTile, int wooliness)
    {
        super(locationTile, SheepType.SHEEP);
        this.wooliness = wooliness;
    }


    private void eatGrass()
    {
    }


    private void findGrass()
    {
    }


    private void findThreat()
    {
    }


    private void findRefuge()
    {
    }


    public void getWooliness()
    {
    }


    public void die()
    {
        /* Alas, poor sheepie */
    }


    public void receiveShearing()
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


    private static enum SheepType implements Type
    {
        SHEEP
    }

}
