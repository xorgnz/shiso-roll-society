/**
 * 
 */
package com.shisorollsociety.expt1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import com.shisorollsociety.expt1.sim.Agent;
import com.shisorollsociety.expt1.sim.Tile;
import com.shisorollsociety.expt1.sim.World;
import com.shisorollsociety.expt1.sim.World.AgentType;
import com.shisorollsociety.expt1.sim.agents.Shepherd;

import static org.junit.Assert.*;

/**
 * @author xorgnz
 * 
 */
public class WorldTest
{
    private World                       world;

    private static final int            DEFAULT_TEST_WIDTH  = 3;
    private static final int            DEFAULT_TEST_HEIGHT = 3;

    private Tile[][]                    testTiles;
    private Map<AgentType, List<Agent>> testAgents;


    @Before
    public void before()
    {
        // testTiles = new Tile[][]
        // {
        // { new GrassTile(), new GrassTile(), new GrassTile() },
        // { new GrassTile(), new GrassTile(), new GrassTile() },
        // { new GrassTile(), new GrassTile(), new GrassTile() }
        // };
        //
        // world = new World(DEFAULT_TEST_WIDTH, DEFAULT_TEST_HEIGHT);
        // ReflectionTestUtils.setField(world, "tiles", testTiles);
        //

        world = new World(DEFAULT_TEST_WIDTH, DEFAULT_TEST_HEIGHT);

        testTiles = (Tile[][]) ReflectionTestUtils.getField(world, "tiles");

        List<Agent> sheps = new ArrayList<Agent>();
        sheps.add(new Shepherd(testTiles[1][1]));

        // TODO: the rest of the agent types

        testAgents = new HashMap<AgentType, List<Agent>>();
        testAgents.put(AgentType.BUZZARD, null);
        testAgents.put(AgentType.DOG, null);
        testAgents.put(AgentType.SHEEP, null);
        testAgents.put(AgentType.SHEPHERD, sheps);
        testAgents.put(AgentType.WOLF, null);

        ReflectionTestUtils.setField(world, "agents", testAgents);
    }


    @Test
    public void testFindAgentsInTiles_SingleAgent()
    {
        List<Tile> searchTiles = new ArrayList<Tile>();
        searchTiles.add(testTiles[1][1]);
        List<Shepherd> results = world.<Shepherd> findAgentsInTiles(AgentType.SHEPHERD, searchTiles);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals(testTiles[1][1].getInhabitants(), results);
    }
    
    @Test
    public void testFindAgentsInTiles_NoAgents()
    {
        List<Tile> searchTiles = new ArrayList<Tile>();
        searchTiles.add(testTiles[0][1]);
        List<Shepherd> results = world.<Shepherd> findAgentsInTiles(AgentType.SHEPHERD, searchTiles);

        assertNotNull(results);
        assertEquals(0, results.size());
        assertEquals(testTiles[0][1].getInhabitants(), results);
    }


//    @Test
//    public void testFindAgentsInRange()
//    {
//
//    }
}
