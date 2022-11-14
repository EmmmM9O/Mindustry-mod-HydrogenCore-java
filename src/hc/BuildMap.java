package hc;

import mindustry.gen.Building;
import mindustry.world.Tile;
public class BuildMap <T extends Building>{
    public T build;
    public Tile tile;
    public BuildMap (T b,Tile t){
        build=b;tile=t;
    }
}
