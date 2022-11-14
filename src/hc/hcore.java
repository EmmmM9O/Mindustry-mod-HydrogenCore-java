package hc;

import hc.blocks.*;
import hc.func.*;
import hc.*;
import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;


import java.util.HashMap;
import java.util.Map;

import static mindustry.Vars.*;
import mindustry.world.Tile;

public class hcore extends Mod{
    public static Map<Tile,BuildMap> list=new HashMap<>();
    public hcore(){
        
        Log.info("Loaded HcMod constructor.");

        //listen for game load event

        Events.on(ClientLoadEvent.class, e -> {

            //show dialog upon startup

            Time.runTask(10f, () -> {

                BaseDialog dialog = new BaseDialog("Welcome");

                dialog.cont.add("behold").row();

                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)

                dialog.cont.image(Core.atlas.find("hc-icon")).pad(20f).row();

                dialog.cont.button("@ok", dialog::hide).size(100f, 50f);

                dialog.show();

            });

        });

    }

    @Override

    public void loadContent(){
        hc.HcBlocks.load();
        Log.info("test1");
        Events.run(Trigger.update,()->{
            if(Vars.state.isMenu()){
                if(list.size()>0)list.clear();
            }

        });
    }

}

