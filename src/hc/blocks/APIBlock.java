
package hc.blocks;
import arc.Core;
import arc.graphics.g2d.Draw;
import hc.Selects;
import hc.func.SelectFunc;
import hc.blocks.SelectBlock;
import mindustry.type.*;
import mindustry.ui.dialogs.BaseDialog;
import arc.util.io.*;
public class APIBlock extends SelectBlock{
    public class APIBuild extends SelectBlock.SelectBuild{
        public boolean inoutMode=false;
        public boolean IsStruct =false;
        public boolean IsPower=true;
        public boolean IsItem=false;
        public boolean IsLiquid=false;
        public Liquid liquid;
        public Item item;
        @Override
        public void draw(){
            super.draw();
            if (inoutMode)
                Draw.rect(Core.atlas.find("hc-input"),x+0.5f,y+1,7,7);
            else Draw.rect(Core.atlas.find("hc-output"),x+0.5f,y+0.5f,7,7);
            if (IsPower)  Draw.rect(Core.atlas.find("hc-power"),x+0.5f,y+0.5f,7,7);
            else if(IsItem) Draw.rect(item.uiIcon,x+1,y+1,6,6);
            else Draw.rect(liquid.uiIcon,x+1,y+1,5,5);

        }
        public void  ShowUi(){
            BaseDialog ui=new BaseDialog("UI");
            if(IsStruct){
                ui.button("Item",()->{
                    IsItem=true;
                    IsPower=false;
                    IsLiquid=false;
                });
                ui.button("Liquid",()->{
                    IsItem=false;
                    IsPower=false;
                    IsLiquid=true;
                });
                ui.button("Power",()->{
                    IsItem=false;
                    IsPower=true;
                    IsLiquid=false;
                }).row();
                if (IsPower) {
                    ui.cont.add("No choose ");
                }else if (IsItem){

                }
            }else{
                ui.cont.add("No structure here").row();
                ui.button("@ok",ui::hide).size(100f,50f);
            }
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
