
package hc.blocks;
import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;

import arc.scene.ui.layout.Table;
import hc.Selects;
import hc.func.SelectFunc;

import mindustry.type.*;
import mindustry.ui.dialogs.BaseDialog;
import arc.util.io.*;
import arc.util.Nullable;
import mindustry.world.Tile;

import java.util.Vector;

public class APIBlock extends TopBlock{
    public class APIBuild extends TopBuilding{
        public boolean inoutMode=false;
        public boolean IsStruct =false;
        public boolean IsPower=true;
        public boolean IsItem=false;
        public boolean IsLiquid=false;
        public Tile StructTile=new Tile(1,1);
        @Nullable
        public Liquid liquid;
        @Nullable
        public Item item;
        @Override
        public void draw(){
            super.draw();
            if (inoutMode&&!IsPower)
                Draw.rect(Core.atlas.find("hc-input"),x,y,8,8);
            else if(!IsPower)Draw.rect(Core.atlas.find("hc-output"),x,y,8,8);
            if (IsPower)  Draw.rect(Core.atlas.find("hc-power"),x,y,8,8);
            else if(IsItem&&item!=null) Draw.rect(item.uiIcon,x+1,y+1,6,6);
            else if(liquid!=null)Draw.rect(liquid.uiIcon,x+1,y+1,5,5);

        }

        public void  ShowUi(){
            BaseDialog ui=new BaseDialog("UI");
            if(IsStruct){

                ui.cont.button("Item",()->{

                    IsItem=true;
                    IsPower=false;
                    IsLiquid=false;
                    ui.hide();
                    ShowUi();
                }).size(80f,40f);
                ui.cont.button("Liquid",()->{

                    IsItem=false;
                    IsPower=false;
                    IsLiquid=true;
                    ui.hide();
                    ShowUi();
                }).size(80f,40f);
                ui.cont.button("Power",()->{

                    IsItem=false;
                    IsPower=true;
                    IsLiquid=false;
                    ui.hide();
                    ShowUi();
                }).size(80f,40f).row();
                if (StructTile.build==null){
                    ui.cont.add("Structure Is Null");
                }
                else if (IsPower) {
                    ui.cont.add("No choose ");
                    
                }else if (IsItem){
                    var bu=StructTile.build.<IncludeBlock.IncludeBuild>self();
                    Vector<Item> k;
                    if (inoutMode) k=bu.InItem;
                    else k=bu.OutItem;
                    ui.cont.table(table -> {
                        for (Item now : k){
                            table.image(now.fullIcon);
                            table.button("X",()-> item=now);

                        }
                    });

                }else if (IsLiquid){
                    var bu=StructTile.build.<IncludeBlock.IncludeBuild>self();
                    Vector<Liquid> k;
                    if (inoutMode) k=bu.InLiquid;
                    else k=bu.OutLiquid;
                    ui.cont.table(table -> {
                        for (Liquid now : k){
                            table.image(now.fullIcon);
                            table.button("X",()-> liquid=now);

                        }
                    });
                }
            }else{
                ui.cont.add("No structure here").row();
            }
            ui.button("@ok",ui::hide).size(100f,50f);
            ui.show();
        }
        public String config(){
            if(IsStruct)return "true";
            else return "false";
        }

        @Override
        public void write(Writes write){
            super.write(write);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
        }

    }
 



    public SelectFunc<APIBuild> Change=(build,t)-> build.ShowUi();

    public SelectFunc<APIBuild> ChangeMode=(build,t)->{
        build.inoutMode=!build.inoutMode;
        build.IsPower=false;
    };

    public APIBlock(String name) {
        super(name, new Selects[2]);
        selects[0]=new Selects();
        selects[0].icon="input";
        selects[0].run=ChangeMode;
        selects[1]=new Selects();
        selects[1].icon="change";
        selects[1].run=Change;

    }
}
