/**
 * 
 */
package com.shisorollsociety.expt1.sim;

import com.shisorollsociety.expt1.sim.World.EntityType;

/**
 * @author xorgnz
 * 
 */
public abstract class Agent
{
    private Tile locationTile;

    private EntityType type;

    private int  health;


    public Agent(Tile locationTile, EntityType type)
    {
        this.locationTile = locationTile;
        this.type = type;
    }


    public Tile getLocationTile()
    {
        return locationTile;
    }


    public EntityType getType()
    {
        return type;
    }


    public int getHealth()
    {
        return health;
    }


    public boolean isAlive()
    {
        return health <= 0;
    }


    public abstract void tick();
}
