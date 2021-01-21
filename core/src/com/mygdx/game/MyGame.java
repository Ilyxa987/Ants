package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.GameSc.Games;
import menu.Menu;


public class MyGame extends Game {

	public Menu menu;
	public Games games;


	@Override
	public void create () {
        menu = new Menu(this);
        games = new Games(this);
        setScreen(menu);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

}
