/**
 * 
 */
package com.shisorollsociety.expt1.sim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shisorollsociety.expt1.sim.World.Direction;

/**
 * @author xorgnz
 * 
 */
public abstract class Tile
{
    private List<Agent>          inhabitants = new ArrayList<Agent>();
    private Map<Direction, Tile> neighbors   = new HashMap<Direction, Tile>();


    public Map<Direction, Tile> getNeighbors()
    {
        return neighbors;
    }


    public Tile getNeighbor(Direction d)
    {
        return neighbors.get(d);
    }


    public void addNeighbor(Direction d, Tile t)
    {
        this.neighbors.put(d, t);
    }


    public void addInhabitant(Agent a)
    {
        this.inhabitants.add(a);
    }


    public List<Agent> getInhabitants()
    {
        return inhabitants;
    }


    /**
     * Process a tick
     * 
     * This involves:
     * - triggering a tick on all agents.
     */
    public void tick()
    {
        // TODO: figure out how else this ticks

        return;
    }
}
