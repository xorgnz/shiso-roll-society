/**
 * 
 */
package com.shisorollsociety.expt1.sim.agents;

import java.util.ArrayList;
import java.util.List;

import com.shisorollsociety.expt1.sim.Agent;
import com.shisorollsociety.expt1.sim.Tile;
import com.shisorollsociety.expt1.sim.World;

/**
 * @author xorgnz
 * 
 */
public class Shepherd extends Agent
{
    private List<Sheep> sheep = new ArrayList<Sheep>();
    private List<Dog>   dogs  = new ArrayList<Dog>();
    private Wolf        targetWolf;


    public Shepherd(Tile locationTile)
    {
        super(locationTile, World.AgentType.SHEPHERD);
    }


    public void addDog(Dog d)
    {
        this.dogs.add(d);
    }


    public void addSheep(Sheep s)
    {
        this.sheep.add(s);
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
}
