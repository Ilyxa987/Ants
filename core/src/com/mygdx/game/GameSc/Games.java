package com.mygdx.game.GameSc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.mygdx.game.MyGame;
import com.mygdx.game.menu.KatButton;

public class Games implements Screen {
    final MyGame game;
    private SpriteBatch batch = new SpriteBatch();
    OrthographicCamera camera = new OrthographicCamera();
    GameMap gameMap;
    BattleButtons battleButtons;
    InputMultiplexer inputMultiplexer;
    SkillBar skillBar;
    Inventory inventory;
    KatButton katButton;
    float stateTime;

    public Games(MyGame myGame) {
        game = myGame;
        this.camera.setToOrtho(false,800.0F, 480.0F);
        inventory = new Inventory();
        this.gameMap = new GameMap(this);
        battleButtons = new BattleButtons(game, this.inventory);
        inputMultiplexer = new InputMultiplexer();
        skillBar = new SkillBar();
        katButton = new KatButton(game);
        stateTime = 0;
    }

    public void show() {
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 1.0F);
        Gdx.gl.glClear(16384);
        inputMultiplexer.addProcessor(battleButtons);
        inputMultiplexer.addProcessor(inventory);
        inputMultiplexer.addProcessor(skillBar);
        inputMultiplexer.addProcessor(this.gameMap);
        inputMultiplexer.addProcessor(new GestureDetector(1f, 1f, 1f, 1f, gameMap));
        Gdx.input.setInputProcessor(inputMultiplexer);
        this.camera.update();
        this.gameMap.draw();
        battleButtons.draw();
        this.batch.begin();
        skillBar.draw(batch);
        this.batch.end();
        skillBar.draw();
        inventory.draw();
        if (gameMap.player.hitpoints<0){
            stateTime+=Gdx.graphics.getDeltaTime();
            if (stateTime>1) game.setScreen(new deathScreen(game));
        }
    }

    public void resize(int width, int height) {    }

    public void pause() {
    }

    public void resume() {
    }

    public void hide() {
    }

    public void dispose() {
        this.batch.dispose();
        gameMap.dispose();
        skillBar.dispose();
        inventory.dispose();
        katButton.dispose();
    }

    public Inventory getInventory() {
        return inventory;
    }
}