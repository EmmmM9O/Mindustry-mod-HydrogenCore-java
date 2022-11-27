package hc.blocks;

import arc.graphics.Color;
import arc.scene.ui.layout.Table;
import hc.Selects;
import hc.func.SelectFunc;
import mindustry.game.Team;
import mindustry.gen.Building;
import mindustry.world.Tile;

public class TopBlock extends SelectBlock{

    public SelectFunc<TopBuilding> f1=(b,t)->{
        t.add(b.ChooseTable).left();
    };

    public TopBlock(String name, Selects[] s){
        super(name,s);
        selects=new Selects[s.length+1];
        selects[0]=new Selects();
        selects[0].icon="face";
        selects[0].run=f1;
        int cnt=0;
        for (var i : s){
            cnt++;
            selects[cnt]=i;
        }
    }

    public class TopBuilding extends SelectBuild{
        public Table ChooseTable=new Table();
        @Override
        public  Building init(Tile tile, Team team, boolean shouldAdd, int rotation) {
            ChooseTable.add(" ").size(60f,60f);
            AddF(ChooseTable,"^",1);
            ChooseTable.row();
            AddF(ChooseTable,"<",2);
            ChooseTable.add(" ").size(60f,60f);
            AddF(ChooseTable,">",3);
            ChooseTable.row();
            ChooseTable.add(" ").size(60f,60f);
            AddF(ChooseTable,"v",4);

            return super.init(tile,team,shouldAdd,rotation);
        }
            public Integer Face=0;
        public void AddF(Table t, String name, Integer f){
            if (Face==f) t.button(name,()->Face=f).size(60f,60f).color(Color.orange);
        }

    }
}
