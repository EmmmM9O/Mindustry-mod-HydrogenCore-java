
package hc.blocks;
import arc.Core;
import arc.graphics.g2d.Draw;
import arc.scene.style.Drawable;
import hc.Selects;
import hc.func.SelectFunc;
import hc.blocks.SelectBlock;
import mindustry.type.*;
import mindustry.ui.dialogs.BaseDialog;
import arc.util.io.*;
import arc.util.Nullable;
import mindustry.world.Tile;

public class APIBlock extends SelectBlock{
    public class APIBuild extends SelectBlock.SelectBuild{
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
                ui.cont.background(Core.atlas.drawable("hc-通用接口"));
                ui.cont.button("Item",()->{
                    IsItem=true;
                    IsPower=false;
                    IsLiquid=false;
                    ui.hide();
                    ShowUi();
                });
                ui.cont.button("Liquid",()->{
                    IsItem=false;
                    IsPower=false;
                    IsLiquid=true;
                    ui.hide();
                    ShowUi();
                });
                ui.cont.button("Power",()->{
                    IsItem=false;
                    IsPower=true;
                    IsLiquid=false;
                    ui.hide();
                    ShowUi();
                }).row();
                if (StructTile.build==null){
                    ui.cont.add("Structure Is Null");
                }
                else if (IsPower) {
                    ui.cont.add("No choose ");
                    
                }else if (IsItem){
                    var bu=StructTile.build.<IncludeBlock.IncludeBuid>self();
                    Item[] k;
                    if (inoutMode) k=bu.InItem;
                    else k=bu.OutItem;
                    ui.cont.table(table -> {
                        for (Item now : k){
                            table.image(now.fullIcon);
                            table.button("X",()->{
                                item=now;
                            });
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
 



    public SelectFunc<APIBuild> Change=(build)-> {
        build.ShowUi();
    };

    public SelectFunc<APIBuild> ChangeMode=(build)->{
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
