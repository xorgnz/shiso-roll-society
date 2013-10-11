/**
 * 
 */
package com.shisorollsociety.expt1.sim;

import java.util.List;

import com.shisorollsociety.expt1.sim.World.AgentType;
import com.shisorollsociety.expt1.sim.agents.Dog;
import com.shisorollsociety.expt1.sim.agents.Sheep;
import com.shisorollsociety.expt1.sim.agents.Shepherd;

/**
 * @author xorgnz
 * 
 */
public class Simulator
{
    private World            world;

    private long             currentTick;

    private static final int INITIAL_BUZZARD_COUNT  = 5;
    private static final int INITIAL_DOG_COUNT      = 5;
    private static final int INITIAL_SHEEP_COUNT    = 5;
    private static final int INITIAL_SHEPHERD_COUNT = 1;
    private static final int INITIAL_WOLF_COUNT     = 5;


    public void mainLoop()
    {

    }


    public void genesis(int seed)
    {
        // In the beginning, there was random tile genesis
        world = new World();

        // On the third cycle, [deity] created some jerks
        world.createNewAgents(AgentType.BUZZARD, INITIAL_BUZZARD_COUNT);
        world.createNewAgents(AgentType.DOG, INITIAL_DOG_COUNT);
        world.createNewAgents(AgentType.SHEEP, INITIAL_SHEEP_COUNT);
        world.createNewAgents(AgentType.SHEPHERD, INITIAL_SHEPHERD_COUNT);
        world.createNewAgents(AgentType.WOLF, INITIAL_WOLF_COUNT);

        // and then [he|she|it] decided to give some of them domion over others
        List<Shepherd> shepherds = world.getAgentsByType(AgentType.SHEPHERD);

        // The first human was a shepherd.
        Shepherd soleShepherd = shepherds.get(0);

        // He had some dogs
        List<Dog> dogs = world.getAgentsByType(AgentType.DOG);
        for (Dog d : dogs)
        {
            soleShepherd.addDog(d);
        }

        // He also had many sheep that he occasionally cut up and ate
        List<Sheep> sheeps = world.getAgentsByType(AgentType.SHEEP);
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
