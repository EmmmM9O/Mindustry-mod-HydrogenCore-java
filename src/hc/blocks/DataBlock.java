package hc.blocks;

import hc.BuildMap;
import hc.hcore;
import mindustry.world.Block;
import mindustry.gen.Building;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public class DataBlock extends Block {
    public class DataBuild extends Building{
        @Override
        public void updateTile(){

        }
        
    }
    public DataBlock(String name){
        super(name);
    }
}
