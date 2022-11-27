package hc.ui;

import arc.scene.ui.layout.Table;
import mindustry.ctype.UnlockableContent;

import java.util.Vector;

public class VecList <T extends Table>{

    public void add(T t, Vector<? extends UnlockableContent> e,Runnable run){
        for (var i:e){
            t.image(i.fullIcon);
            t.button("X",run);
        }
    }
}
