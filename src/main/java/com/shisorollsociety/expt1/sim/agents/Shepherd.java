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
public class Shepherd extends Agent
{
    private List<Sheep> sheep;
    private List<Dog>   dogs;
    private Wolf        targetWolf;


    public Shepherd(List<Sheep> sheep, List<Dog> dogs, Tile locationTile)
    {
        super(locationTile, ShepherdAgent.SHEPHERD);
        this.sheep = sheep;
        this.dogs = dogs;
    }


    public List<Sheep> getSheep()
    {
        // Wake up sheeple
        return sheep;
    }


    public List<Dog> getDogs()
    {
        // Wake up Dog-people
        return dogs;
    }


    private void findThreatenedSheep()
    {

    }


    private void findWoolySheep()
    {

    }


    private void findFreeDog()
    {

    }


    private void findNearbyWolf()
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


    private static enum ShepherdAgent implements Type
    {
        SHEPHERD
    }

}
