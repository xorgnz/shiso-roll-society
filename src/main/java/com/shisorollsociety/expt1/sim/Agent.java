/**
 * 
 */
package com.shisorollsociety.expt1.sim;

/**
 * @author xorgnz
 * 
 */
public abstract class Agent
{
    private Tile locationTile;

    private Type type;

    private int  health;


    public Agent(Tile locationTile, Type type)
    {
        this.locationTile = locationTile;
        this.type = type;
    }


    public Tile getLocationTile()
    {
        return locationTile;
    }


    public Type getType()
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


    // TODO: pull out the interface w/ all types populated to World.java
    protected static interface Type
    {

    }
}
