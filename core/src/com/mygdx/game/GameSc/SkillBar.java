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
import com.mygdx.game.SkillsPack.FireBall;
import com.mygdx.game.SkillsPack.Skills;
import com.mygdx.game.menu.Menu;

public class SkillBar extends Stage {

    Texture skillHubImage, at;
    TextButton skillHub;
    AttackSkill attackSkill;
    FireBall fireBall;
    Skills[] skillArray;


    public SkillBar() {

        skillHubImage = new Texture("skillbar.png");
        at = new Texture("Attack.png");

        BitmapFont font = new BitmapFont();
        Skin skin = new Skin();
        
        skin.add("skillHub", skillHubImage);
        skin.add("Attack", at);
        
        TextButton.TextButtonStyle skillHubStyle = new TextButton.TextButtonStyle();
        skillHubStyle.font = font;
        skillHubStyle.up = skin.getDrawable("skillHub");
        skillHubStyle.down = skin.getDrawable("skillHub");
        skillHubStyle.checked = skin.getDrawable("skillHub");

        TextButton.TextButtonStyle atStyle = new TextButton.TextButtonStyle();
        atStyle.font = font;
        atStyle.up = skin.getDrawable("Attack");
        atStyle.down = skin.getDrawable("Attack");
        atStyle.checked = skin.getDrawable("Attack");

        skillHub = new TextButton("", skillHubStyle);
        skillHub.setPosition(Gdx.graphics.getWidth()/5, -17);
        addActor(skillHub);

        attackSkill= new AttackSkill("", atStyle);
        fireBall = new FireBall("", atStyle);
        skillArray = new Skills[]{attackSkill, fireBall, null, null, null, null, null, null};

        for (int i = 0; i <skillArray.length ; i++) {
            if (skillArray[i] != null) {
                skillArray[i].setPosition(skillHub.getX() + 35 + i * 96, skillHub.getY() + 22);
                addActor(skillArray[i]);
            }
        }
    }

    @Override
    public void draw() {
        super.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
        skillHubImage.dispose();
        at.dispose();
    }
}
