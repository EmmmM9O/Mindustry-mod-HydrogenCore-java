package hc;

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
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import mindustry.world.*;
import mindustry.world.meta.*;
import arc.scene.style.*;

import static mindustry.Vars.*;

public class Selects{
    public String icon;
    public StringBuilder name=new StringBuilder();
    public Selects(){}
    public SelectFunc run;
}
