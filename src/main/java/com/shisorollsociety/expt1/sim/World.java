/**
 * 
 */
package com.shisorollsociety.expt1.sim;

/**
 * @author xorgnz
 * 
 */
public class World
{
    private Tile[][] tiles;


    public static enum Direction
    {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }


    public World()
    {
        // generate tiles/agents according to what params?
        // generateTiles();
        // generateAgents();
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
