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


    public List<Agent> findAgents(EntityType type, List<Tile> tiles)
    {
        // TODO Implement this
        return null;
    }


    public List<Agent> findAgents(EntityType type, Tile tile, int radius)
    {
        // TODO Implement this
        return null;
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
