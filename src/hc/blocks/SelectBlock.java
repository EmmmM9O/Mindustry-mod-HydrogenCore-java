package hc.blocks;

import hc.*;
import hc.func.*;
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
import mindustry.gen.*;
import mindustry.ui.dialogs.*;
import mindustry.ui.*;
import mindustry.world.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public class SelectBlock extends Block{
    public Selects[] selects;
    public SelectBlock(String name,Selects[] s){
        super(name);
        configurable = true;
        solid = true;
        destructible = true;
        group = BlockGroup.logic;
        drawDisabled = false;
        envEnabled = Env.any;
        selects=s;
        update=true;
        //config(String.class, (SelectBuild entity, String b) -> entity.SelectNow = b);
  
    }
    public class SelectBuild extends Building{
        public String SelectNow =new String();
        @Override
        public void updateTile(){
            hcore.list.put(tile,new <SelectBuild>BuildMap(this,tile));
        }
        @Override
        public void drawSelect(){
            if(renderer.pixelator.enabled()) return;
            Font font = Fonts.outline;
            GlyphLayout l = Pools.obtain(GlyphLayout.class, GlyphLayout::new);
            boolean ints = font.usesIntegerPositions();
            font.getData().setScale(1 / 4f / Scl.scl(1f));
            font.setUseIntegerPositions(false);


            float offset = 1f;

            Draw.color(0f, 0f, 0f, 0.2f);
            Fill.rect(x, y - tilesize/2f - l.height/2f - offset, l.width + offset*2f, l.height + offset*2f);
            Draw.color();
            font.setColor(Color.white);
            
            font.setUseIntegerPositions(ints);

            font.getData().setScale(1f);

            Pools.free(l);
        }
        @Override
        public void buildConfiguration(Table table){
            Selects[] s=selects;
            for(int i=0;i<s.length;i++){
                Selects a=s[i];
                table.button(a.icon,()->{
                    a.run.get(this);
                    SelectNow=a.icon;
                });
                
            }
        }
    }

}

