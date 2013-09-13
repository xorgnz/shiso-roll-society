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
    private Tile[][]                     tiles;
    private Map<EntityType, List<Agent>> agents               = new HashMap<EntityType, List<Agent>>();

    private int                          width;
    private int                          height;

    private static final int             DEFAULT_WORLD_WIDTH  = 12;
    private static final int             DEFAULT_WORLD_HEIGHT = 12;

    private static final Random          RANDOM               = new Random();


    public static enum Direction
    {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }


    public static enum EntityType
    {
        BUZZARD,
        SHEPHERD,
        DOG,
        WOLF,
        SHEEP
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

        agents.put(EntityType.BUZZARD, new ArrayList<Agent>());
        agents.put(EntityType.DOG, new ArrayList<Agent>());
        agents.put(EntityType.SHEEP, new ArrayList<Agent>());
        agents.put(EntityType.SHEPHERD, new ArrayList<Agent>());
        agents.put(EntityType.WOLF, new ArrayList<Agent>());
    }


    public void createNewAgents(EntityType type, int count)
    {
        List<Agent> agents = this.agents.get(type);

        for (int i = 0; i < count; i++)
        {
            Agent a = null;
            Tile t = randomTile();

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

            agents.add(new Buzzard(t));

            agents.add(a);
            a.getLocationTile().addInhabitant(a);
        }
    }
    
    public <T extends Agent> List<T> getAgents(EntityType type)
    {
        List<Agent> tempList = agents.get(type);
        List<T> returnList = new ArrayList<T>();
        
        for (Agent a : tempList)
        {
            returnList.add((T) a);
        }
        
        return returnList;
    }


    public <T extends Agent> List<T> findAgents(EntityType type, List<Tile> tiles)
    {
        List<T> agentList = new ArrayList<T>();
        
        for (Tile t : tiles)
        {
            agentList.addAll(this.<T>findAgents(type, t));
        }
        
        return agentList;
    }


    //TODO: TEST THIS!!!
    public <T extends Agent> List<T> findAgents(EntityType type, Tile tile, int radius)
    {
        return new Object() 
        {
            public List<T> findAgents(EntityType type, Tile tile, int radius, World world)
            {
                List<T> agentList = new ArrayList<T>();
                
                if (tile == null)
                    return agentList;
                
                //Commence side branching
                swingDirection(type, radius, agentList, tile, world);
                
                //Commence branching up
                Tile currTile = tile.getNeighbor(Direction.NORTH);
                for (int i = 1; i <= radius && currTile != null; i++, currTile = currTile.getNeighbor(Direction.NORTH))
                { 
                    swingDirection(type, radius - i, agentList, currTile, world);
                }        

                //Commence branching down
                currTile = tile.getNeighbor(Direction.SOUTH);
                for (int i = 1; i <= radius && currTile != null; i++, currTile = currTile.getNeighbor(Direction.SOUTH))
                { 
                    swingDirection(type, radius - i, agentList, currTile, world);
                }
                
                return agentList;
            }
            
            private void swingDirection(EntityType type, int swing, List<T> agentList, Tile centerTile, World world)
            {
                agentList.addAll(world.<T>findAgents(type, centerTile));
                
                Tile swingTile = centerTile.getNeighbor(Direction.EAST);
                for (int left = 1; left <= swing && swingTile != null; left++, swingTile = swingTile.getNeighbor(Direction.EAST))
                {
                    agentList.addAll(world.<T>findAgents(type, swingTile));
                }
                swingTile = centerTile.getNeighbor(Direction.WEST);
                for (int right = 1; right <= swing && swingTile != null; right++, swingTile = swingTile.getNeighbor(Direction.WEST))
                {
                    agentList.addAll(world.<T>findAgents(type, swingTile));
                }
            }
        }
        .findAgents(type, tile, radius, this);
    }

    private <T extends Agent> List<T> findAgents(EntityType type, Tile tile)
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

    public Tile randomTile()
    {
        return tiles[RANDOM.nextInt(width)][RANDOM.nextInt(height)];
    }


    public void tick()
    {
        for (int i = 0; i < tiles.length; i++)
        {
            for (int j = 0; j < tiles[i].length; j++)
            {
                tiles[i][j].tick();
            }
        }

        // TODO: How else does this tick?
    }
}
