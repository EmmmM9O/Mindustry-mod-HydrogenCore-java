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
        @Override
        public void BuildInit(){
            cnt=0;
            Apis=new StructB[20];
            for(int i=0;i<NeedBlock.length;i++){
                StructB a=NeedBlock[i];

                if(a.block=="APIBlock"){

                    Apis[cnt]= a;
                    cnt++;
                    var tile=world.tile((int)x/8+a.x,(int)y/8+a.y);
                    BuildMap<APIBlock.APIBuild> block=hcore.list.<APIBlock.APIBuild>get(tile).get();
                    APIBlock.APIBuild bu=block.build;
                    bu.IsStruct=true;



                }
            }
        }
        @Override
        public void MakeRun(){
            for(int i=0;i<cnt;i++){
                var a=Apis[i];

                var tile=world.tile((int)x/8+a.x,(int)y/8+a.y);
                BuildMap<APIBlock.APIBuild> block=hcore.list.get(tile).get();
                APIBlock.APIBuild bu=block.build;
                bu.IsStruct=true;
            }
        }

    }
    
    public IncludeBlock(String name, StructB Need[]){
        super(name,Need);
    }
}
