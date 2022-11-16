package hc.blocks;

import hc.BuildMap;
import hc.StructB;
import hc.hcore;
import mindustry.Vars;
import mindustry.type.*;
import mindustry.world.Block;
import static mindustry.Vars.*;

public class IncludeBlock extends StructureBlock{


    public class IncludeBuid extends StructureBlock.StructureBuild{
        public Item[] InItem;
        public Liquid[] InLiquid;
        public Item[] OutItem;
        public Liquid[] OutLiquid;
        public StructB[] Apis;
        public int cnt;
        

    }
    
    public IncludeBlock(String name, StructB Need[]){
        super(name,Need);
    }
}
