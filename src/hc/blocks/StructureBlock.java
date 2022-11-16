package hc.blocks;

import arc.*;
import arc.Input.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.geom.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.util.*;
import arc.util.io.*;
import arc.util.pooling.*;
import mindustry.ctype.ContentType;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import mindustry.core.*;
import mindustry.world.*;
import mindustry.*;
import mindustry.world.meta.*;
import hc.blocks.SelectBlock;
import hc.*;
import hc.func.*;
import static mindustry.Vars.*;

public class StructureBlock extends SelectBlock{
	public StructB[] NeedBlock;
	public class StructureBuild extends SelectBlock.SelectBuild{
		public boolean show=false;	
		public boolean BuildOk=false;	
		public int BuildTick=0;	
		@Override
		public void draw(){
			super.draw();
			if(show){
				for(int i =0;i<NeedBlock.length;i++){
					StructB a=NeedBlock[i];
					Draw.color(Color.slate);
					Draw.alpha(0.65f);
					Block block= Vars.content.getByName(ContentType.block,a.block);
					Tile tile=Vars.world.tile((int)Math.floor(x/8)+a.x,(int)Math.floor(y/8)+a.y);
					if(tile.build==null||tile.block()!=block){
						if(tile.build!=null&&block!=tile.block()) Draw.color(Color.red);
						Draw.rect(block.fullIcon,x+a.x*8,y+a.y*8,block.size*8,block.size*8);
						Draw.color(Color.slate);
					}
				}
				Draw.color(Color.white);
			}
		}
		public void MakeRun(){
			Vars.ui.showLabel("[acid]test",1,x,y+8);
		}

		@Override
		public void updateTile(){
			super.updateTile();
		    if(BuildOk){
		        BuildTick++;
		        if(BuildTick>=1000){
		            BuildTick=0;
		            ChickBuild(this);
		            if(!BuildOk) return;
		        }
		        MakeRun();
		    }
		}
		public void BuildInit(){}
	}
	public boolean ChickBuild(StructureBuild build){
	    for(int i=0;i<NeedBlock.length;i++){
	        StructB a=NeedBlock[i];
			Block block= Vars.content.getByName(ContentType.block,a.block);
	        Tile tile=Vars.world.tile((int)Math.floor(build.x/8)+a.x,(int)Math.floor(build.y/8)+a.y);
	        if(tile.build==null||tile.block()!=block) {
	            build.BuildOk=false;
	            return false;
	        }
	    }
	    build.BuildOk=true;
	    return true;
	}

	public SelectFunc<StructureBuild> BuildRun=(build)->{
	    if(ChickBuild(build)){
	         Vars.ui.showLabel("[acid]构建成功",1,build.x,build.y);
	        build.BuildInit();
	        
	        
	    }else{
	        Vars.ui.showLabel("[red]构建失败",1,build.x,build.y);
	        
	    }
	};
	public SelectFunc<StructureBuild> ShowRun=(build)->{build.show=!build.show;};
	public StructureBlock(String name,StructB[] Need){
	    super(name,new Selects[2]);
		NeedBlock=Need;
		selects[0]=new Selects();
		selects[0].icon="show";
		selects[0].run=ShowRun;
		selects[1]=new Selects();
		selects[1].icon="build";
		selects[1].run=BuildRun;
	}
}

