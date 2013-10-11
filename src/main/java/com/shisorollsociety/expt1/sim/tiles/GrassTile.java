/**
 * 
 */
package com.shisorollsociety.expt1.sim.tiles;

import com.shisorollsociety.expt1.sim.Tile;

/**
 * @author xorgnz
 *
 */
public class GrassTile extends Tile
{
    private int grassitude;
    
    public GrassTile()
    {
        grassitude = 2; 
    }
    
    //TODO: revisit
    public void tick()
    {
        if (grassitude < 10)
            grassitude++;
    }
}
