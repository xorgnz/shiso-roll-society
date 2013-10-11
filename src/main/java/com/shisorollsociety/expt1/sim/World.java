/**
 * 
 */
package com.shisorollsociety.expt1.sim;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.shisorollsociety.expt1.sim.agents.Buzzard;
import com.shisorollsociety.expt1.sim.agents.Dog;
import com.shisorollsociety.expt1.sim.agents.Sheep;
import com.shisorollsociety.expt1.sim.agents.Shepherd;
import com.shisorollsociety.expt1.sim.agents.Wolf;

/**
 * @author xorgnz
 * 
 */
public class World
{
    private static final int            DEFAULT_WORLD_HEIGHT = 12;
    private static final int            DEFAULT_WORLD_WIDTH  = 12;

    private static final Random         RANDOM               = new Random();
    private Map<AgentType, List<Agent>> agents               = new HashMap<AgentType, List<Agent>>();

    private int                         height;
    private Tile[][]                    tiles;

    private int                         width;


    public static enum Direction
    {
        EAST,
        NORTH,
        SOUTH,
        WEST
    }


    public static enum AgentType
    {
        BUZZARD,
        DOG,
        SHEEP,
        SHEPHERD,
        WOLF
    }


    public World()
    {
        this(DEFAULT_WORLD_WIDTH, DEFAULT_WORLD_HEIGHT);
    }


    public World(int width, int height)
    {
        // Save properties
        this.tiles = new Tile[width][height];
        this.width = width;
        this.height = height;

        // Create and link tiles
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                tiles[i][j] = new DefaultTile();

                // Cross link each new tile with that to its west and north
                if (i > 1)
                {
                    tiles[i][j].addNeighbor(Direction.WEST, tiles[i - 1][j]);
                    tiles[i - 1][j].addNeighbor(Direction.EAST, tiles[i][j]);
                }
                else if (j > 1)
                {
                    tiles[i][j].addNeighbor(Direction.NORTH, tiles[i][j - 1]);
                    tiles[i][j - 1].addNeighbor(Direction.SOUTH, tiles[i][j]);
                }
            }
        }

        agents.put(AgentType.BUZZARD, new ArrayList<Agent>());
        agents.put(AgentType.DOG, new ArrayList<Agent>());
        agents.put(AgentType.SHEEP, new ArrayList<Agent>());
        agents.put(AgentType.SHEPHERD, new ArrayList<Agent>());
        agents.put(AgentType.WOLF, new ArrayList<Agent>());
    }


    /**
     * Creates agents of given type and number randomly throughout world.
     * 
     * @param type
     * @param count
     */
    // TODO: Unit test needed
    public void createNewAgents(AgentType type, int count)
    {
        List<Agent> agents = this.agents.get(type);

        for (int i = 0; i < count; i++)
        {
            Agent a = null;
            Tile t = randomTile();

            
            // REFACTOR - Switch out AgentType enum for Agent class references. Then, create using reflection. 
            switch (type)
            {
            case BUZZARD:
                a = new Buzzard(t);
                break;
            case DOG:
                a = new Dog(t);
                break;
            case SHEEP:
                a = new Sheep(t);
                break;
            case SHEPHERD:
                a = new Shepherd(t);
                break;
            case WOLF:
                a = new Wolf(t);
                break;
            default:
                throw new IllegalArgumentException("Cannot instantiate null agents");
            }

            agents.add(a);
            a.getLocationTile().addInhabitant(a);
        }
    }


    /**
     * Looks for agents of a given type within a list of tiles.
     * 
     * @param type
     * @param tiles
     * @return
     */
    // TODO: Unit test needed
    public <T extends Agent> List<T> findAgentsInTiles(AgentType type, List<Tile> tiles)
    {
        List<T> agentList = new ArrayList<T>();

        for (Tile t : tiles)
        {
            agentList.addAll(this.<T> findAgents(type, t));
        }

        return agentList;
    }


    /**
     * Searches for agents of a given type within <code>radius</code> steps of tile based on
     * in-tile connections. Based on Manhattan distance.
     * 
     * @param type
     * @param tile
     * @param radius
     * @return
     */
    // TODO: Unit test needed
    public <T extends Agent> List<T> findAgentsInRange(AgentType type, Tile tile, int radius)
    {
        return new Object()
        {
            public List<T> findAgents(AgentType type, Tile tile, int radius, World world)
            {
                List<T> agentList = new ArrayList<T>();

                if (tile == null)
                    return agentList;

                // Commence side branching
                swingDirection(type, radius, agentList, tile, world);

                // Commence branching up
                Tile currTile = tile.getNeighbor(Direction.NORTH);
                for (int i = 1; i <= radius && currTile != null; i++, currTile = currTile.getNeighbor(Direction.NORTH))
                {
                    swingDirection(type, radius - i, agentList, currTile, world);
                }

                // Commence branching down
                currTile = tile.getNeighbor(Direction.SOUTH);
                for (int i = 1; i <= radius && currTile != null; i++, currTile = currTile.getNeighbor(Direction.SOUTH))
                {
                    swingDirection(type, radius - i, agentList, currTile, world);
                }

                return agentList;
            }


            private void swingDirection(AgentType type, int swing, List<T> agentList, Tile centerTile, World world)
            {
                agentList.addAll(world.<T> findAgents(type, centerTile));

                Tile swingTile = centerTile.getNeighbor(Direction.EAST);
                for (int left = 1; left <= swing && swingTile != null; left++, swingTile = swingTile
                        .getNeighbor(Direction.EAST))
                {
                    agentList.addAll(world.<T> findAgents(type, swingTile));
                }
                swingTile = centerTile.getNeighbor(Direction.WEST);
                for (int right = 1; right <= swing && swingTile != null; right++, swingTile = swingTile
                        .getNeighbor(Direction.WEST))
                {
                    agentList.addAll(world.<T> findAgents(type, swingTile));
                }
            }
        }
                .findAgents(type, tile, radius, this);
    }


    /**
     * Retrieves all agents of a given type.
     * 
     * @param type
     * @return
     */
    public <T extends Agent> List<T> getAgentsByType(AgentType type)
    {
        List<T> returnList = new ArrayList<T>();

        for (Agent a : agents.get(type))
        {
            returnList.add((T) a);
        }

        return returnList;
    }


    /**
     * Randomly selects a tile.
     * 
     * @return
     */
    public Tile randomTile()
    {
        return tiles[RANDOM.nextInt(width)][RANDOM.nextInt(height)];
    }


    /**
     * Process a single tick.
     * 
     * This involves:
     * - Triggering a tick on each tile.
     * - Triggering a tick on each agent.
     */
    public void tick()
    {
        // Trigger tick on tiles
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                tiles[i][j].tick();
            }
        }

        // Trigger tick on agents
        for (AgentType type : AgentType.values())
        {
            for (Agent a : agents.get(type))
            {
                a.tick();
            }
        }

        // TODO: How else does this tick?
    }


    /**
     * Looks for all agents of a given type within a given tile.
     * 
     * @param type
     * @param tile
     * @return
     */
    private <T extends Agent> List<T> findAgents(AgentType type, Tile tile)
    {
        List<T> agentList = new ArrayList<T>();
        for (Agent a : tile.getInhabitants())
        {
            if (a.getType().equals(type))
            {
                agentList.add((T) a);
            }
        }
        return agentList;
    }
}
