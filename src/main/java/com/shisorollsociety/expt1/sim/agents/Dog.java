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
public class Dog extends Agent
{
    private Wolf targetWolf;


    public Dog(Tile locationTile)
    {
        super(locationTile, AgentType.DOG);
    }

    
    public Wolf getTargetWolf()
    {
        return targetWolf;
    }


    public void receiveSignal(Wolf wolf)
    {
        targetWolf = wolf;
    }


    private void findWolf()
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
