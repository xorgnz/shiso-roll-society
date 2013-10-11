/**
 * 
 */
package com.shisorollsociety.expt1.sim.agents;

import com.shisorollsociety.expt1.sim.Agent;
import com.shisorollsociety.expt1.sim.Tile;
import com.shisorollsociety.expt1.sim.World.AgentType;

/**
 * @author xorgnz
 * 
 */
public class Sheep extends Agent
{
    private static final int DEFAULT_WOOLINESS = 100;
    private int              wooliness;


    public Sheep(Tile t)
    {
        this(t, DEFAULT_WOOLINESS);
    }


    public Sheep(Tile locationTile, int wooliness)
    {
        super(locationTile, AgentType.SHEEP);
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
}
