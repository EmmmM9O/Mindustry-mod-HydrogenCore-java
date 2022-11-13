package hc.blocks;

import hc.StructB;
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
        @Override
        public void BuildInit(){
            for(int i=0;i<NeedBlock.length;i++){
                StructB a=NeedBlock[i];
                if(a.block=="APIBlock"){
                    Apis[Apis.length]= a;
                    var str=world.tile((int)x/8+a.x,(int)y/8+a.y).build.config();
                    Vars.ui.showLabel(str.toString(),10,x,y);
                }
            }
        }

    }
    
    public IncludeBlock(String name, StructB Need[]){
        super(name,Need);
    }
}
