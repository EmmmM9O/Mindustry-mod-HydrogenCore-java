package hc.blocks;

import hc.StructB;
import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.type.*;
import mindustry.world.Tile;


import java.util.Vector;

import static mindustry.Vars.*;

public class IncludeBlock extends StructureBlock{


    public class IncludeBuild extends StructureBlock.StructureBuild{
        public Vector<Item> InItem=new Vector<>();
        public Vector<Liquid> InLiquid=new Vector<>();
        public Vector<Item> OutItem=new Vector<>();
        public Vector<Liquid> OutLiquid=new Vector<>();
        public StructB[] Apis;
        public int cnt;
        @Override
        public Building init(Tile tile, Team team, boolean shouldAdd, int rotation) {

            return super.init(tile,team,shouldAdd,rotation);
        }
        @Override
        public void BuildInit(){
            cnt=0;
            Apis=new StructB[20];
            for(var a:NeedBlock){


                if(a.block=="hc-通用接口"){

                    Apis[cnt]= a;
                    cnt++;
                    var til=world.tile((int)x/8+a.x,(int)y/8+a.y);
                    til.build.<APIBlock.APIBuild>self().StructTile= Vars.world.tile((int)x/8,(int)y/8);
                    til.build.<APIBlock.APIBuild>self().IsStruct=true;



                }
            }
        }
        @Override
        public void MakeRun(){
            for(int i=0;i<cnt;i++){
                var a=Apis[i];

                var til=world.tile((int)x/8+a.x,(int)y/8+a.y);
                til.build.<APIBlock.APIBuild>self().StructTile= Vars.world.tile((int)x/8,(int)y/8);
                til.build.<APIBlock.APIBuild>self().IsStruct=true;
                

            }
        }

    }
    
    public IncludeBlock(String name, StructB[] Need){
        super(name,Need);
    }
}
