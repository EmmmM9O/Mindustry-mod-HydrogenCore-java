package hc;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;.
import mindustry.gen.Building;
import mindustry.world.Tile;
public class BuildMap <T extends Building>{
    public Reference<T> build;
    public Tile tile;
    public BuildMap (T b,Tile t){
        build=new SoftReference<T>(b);tile=t;
    }
}
