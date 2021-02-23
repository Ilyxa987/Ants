package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGame;
import com.mygdx.game.SkillsPack.AttackSkill;
import com.mygdx.game.SkillsPack.Skills;
import com.mygdx.game.menu.Menu;

public class SkillBar extends Stage {

    Texture skillHubImage;
    TextButton skillHub;
    AttackSkill attackSkill;
    Skills[] skillArray;


    public SkillBar() {

        skillHubImage = new Texture("skillbar.png");

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();
        
        skin.add("skillHub", skillHubImage);
        
        TextButton.TextButtonStyle skillHubStyle = new TextButton.TextButtonStyle();
        skillHubStyle.font = font;
        skillHubStyle.up = skin.getDrawable("skillHub");
        skillHubStyle.down = skin.getDrawable("skillHub");
        skillHubStyle.checked = skin.getDrawable("skillHub");

        skillHub = new TextButton("", skillHubStyle);
        skillHub.setPosition(Gdx.graphics.getWidth()/3.3f, -17);
        addActor(skillHub);
        skillArray = new Skills[]{attackSkill};

        attackSkill= new AttackSkill("", skillHubStyle);




    }

    @Override
    public void draw() {
        super.draw();
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        for (int i = 0; i <skillArray.length ; i++) {
            if (skillArray[i]== attackSkill)
                attackSkill.draw(batch, 1,
                        skillHub.getWidth()/8+i*skillHub.getHeight(),
                        skillHub.getHeight()/10);
            addActor(attackSkill);
        }
        batch.end();
    }
}
