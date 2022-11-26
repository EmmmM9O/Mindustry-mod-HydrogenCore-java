package hc.blocks;


import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.scene.ui.layout.Table;
import mindustry.gen.Building;
import mindustry.graphics.Drawf;
import mindustry.world.Block;
import mindustry.world.blocks.power.PowerNode;
import mindustry.Vars;

public class FaceBlock extends Block {
    public FaceBlock(String name){
        super(name);
    }
    public class FaceBuilding extends Building{
        @Override
        public void draw(){
            super.draw();
            Drawf.circles(x,y,15f, Color.black);

        }
        @Override
        public boolean onConfigureBuildTapped(Building other){
            Vars.ui.showLabel("[acid]test",1,other.x,other.y);
            return true;
        }
    }
}
