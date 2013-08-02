/**
 * 
 */
package com.shisorollsociety.expt1.sim;

import java.util.List;
import java.util.Map;

import com.shisorollsociety.expt1.sim.World.Direction;

/**
 * @author xorgnz
 * 
 */
public class Tile
{
    private List<Agent>          inhabitants;
    private Map<Direction, Tile> neighbors;


    public Map<Direction, Tile> getNeighbors()
    {
        return neighbors;
    }


    public List<Agent> getInhabitants()
    {
        return inhabitants;
    }


    public void tick()
    {
        for (Agent a : getInhabitants())
        {
            a.tick();
        }

        return; // TODO: figure out how else this ticks
    }
}
