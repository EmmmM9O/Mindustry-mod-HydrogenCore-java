package hc;

import hc.*;
import hc.blocks.*;
import hc.func.*;
import mindustry.ui.dialogs.*;
import arc.graphics.*;
import arc.math.*;
import arc.struct.*;
import mindustry.*;
import mindustry.entities.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.part.DrawPart.*;
import mindustry.entities.part.*;
import mindustry.entities.pattern.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.heat.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.payloads.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import mindustry.content.*;
import static mindustry.Vars.*;
import static mindustry.type.ItemStack.*;

public class HcBlocks{
    public static SelectBlock sr;
    public static StructureBlock sr2;
    public static APIBlock api;
    public static IncludeBlock Test1;
    public static void load(){
        Selects a=new Selects();
        a.icon="info";
        SelectFunc<SelectBlock.SelectBuild> run=(build,t)->{Vars.ui.showLabel("test",10,build.x,build.y);};
        a.run=run;
        Selects[] se={a};
        sr= new SelectBlock("sr",se){{
            requirements(Category.logic, with(Items.graphite, 5, Items.copper, 5));
        }};
        StructB ae=new StructB("duo",0,1);
        StructB[] Need={ae};
        sr2= new StructureBlock("sr2",Need){{
            requirements(Category.logic, with(Items.graphite, 5, Items.copper, 5));
        }};
        api=new APIBlock("通用接口"){{
            requirements(Category.logic, with(Items.graphite, 5, Items.copper, 5));
        }};
        StructB[] Need2={new StructB("hc-通用接口",0,1)};
        Test1=new IncludeBlock("Test1",Need2){{
            requirements(Category.logic, with(Items.graphite, 5, Items.copper, 5));
        }};

    }
}