package hc.func;
import hc.blocks.*;
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
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import mindustry.world.*;
import mindustry.world.meta.*;

import static mindustry.Vars.*;

public interface SelectFunc<T>{
    void get(T t,Table table);
}
