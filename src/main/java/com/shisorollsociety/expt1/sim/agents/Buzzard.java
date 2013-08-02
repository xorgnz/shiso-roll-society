/**
 * 
 */
package com.shisorollsociety.expt1.sim.agents;

import java.util.List;

import com.shisorollsociety.expt1.sim.Agent;
import com.shisorollsociety.expt1.sim.Tile;

/**
 * @author xorgnz
 * 
 */
public class Buzzard extends Agent
{
    private Sheep targetPrey;


    public Buzzard(Tile locationTile)
    {
        super(locationTile, BuzzardType.BUZZARD);
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


    private static enum BuzzardType implements Type
    {
        BUZZARD
    }
}
