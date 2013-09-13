/**
 * 
 */
package com.shisorollsociety.expt1.sim;

import java.util.List;

import com.shisorollsociety.expt1.sim.World.EntityType;
import com.shisorollsociety.expt1.sim.agents.Dog;
import com.shisorollsociety.expt1.sim.agents.Sheep;
import com.shisorollsociety.expt1.sim.agents.Shepherd;

/**
 * @author xorgnz
 * 
 */
public class Simulator
{
    private World world;

    private long  currentTick;
    
    private static final int INITIAL_BUZZARD_COUNT= 5;
    private static final int INITIAL_DOG_COUNT= 5;
    private static final int INITIAL_SHEEP_COUNT= 5;
    private static final int INITIAL_SHEPHERD_COUNT= 1;
    private static final int INITIAL_WOLF_COUNT= 5;


    public void mainLoop()
    {

    }


    public void genesis(int seed)
    {
        // In the beginning, there was random tile genesis
        world = new World();
        
        // On the third cycle, [deity] created some jerks
        world.createNewAgents(EntityType.BUZZARD, INITIAL_BUZZARD_COUNT);
        world.createNewAgents(EntityType.DOG, INITIAL_DOG_COUNT);
        world.createNewAgents(EntityType.SHEEP, INITIAL_SHEEP_COUNT);
        world.createNewAgents(EntityType.SHEPHERD, INITIAL_SHEPHERD_COUNT);
        world.createNewAgents(EntityType.WOLF, INITIAL_WOLF_COUNT);

        // and then [he|she|it] decided to give some of them domion over others
        List<Shepherd> shepherds = world.getAgents(EntityType.SHEPHERD);
        // For now, we have 1 shepherd, give all dogs to that duder
        Shepherd soleShepherd = shepherds.get(0);
        List<Dog> dogs = world.getAgents(EntityType.DOG);
        for (Dog d : dogs)
        {
            soleShepherd.addDog(d);
        }
        
        List<Sheep> sheeps = world.getAgents(EntityType.SHEEP);
        for (Sheep s : sheeps)
        {
            soleShepherd.addSheep(s);
        }
        
    }
    

    public long getCurrentTick()
    {
        return this.currentTick;
    }
}
