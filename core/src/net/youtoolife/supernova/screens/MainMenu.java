package net.youtoolife.supernova.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import net.youtoolife.supernova.Assets;
import net.youtoolife.supernova.Supernova;
import net.youtoolife.supernova.handlers.RMECrypt;
import net.youtoolife.supernova.handlers.RMEGUI;
import net.youtoolife.supernova.handlers.RMEPack;
import net.youtoolife.supernova.handlers.RMESound;
import net.youtoolife.supernova.handlers.RMESprite;
import net.youtoolife.supernova.handlers.gui.RMEButton;
import net.youtoolife.supernova.handlers.gui.RMEImage;
import net.youtoolife.supernova.models.CheckPoint;
import net.youtoolife.supernova.models.Door;
import net.youtoolife.supernova.models.ObjectX;
import net.youtoolife.supernova.models.Opponent;
import net.youtoolife.supernova.models.Player;
import net.youtoolife.supernova.models.SurfaceX;
import net.youtoolife.supernova.models.Wall;

public class MainMenu extends ScreenAdapter {
	
public static float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
	
	Supernova game;
	public static OrthographicCamera guiCam = new OrthographicCamera(width, height); 
	
	Texture bg;
	
	//Stage stage;
	//Skin skin;
	
	public static RMEGUI gui = new RMEGUI();
	
	private BitmapFont dbgMsg = new BitmapFont(); 
	
	private int cl = 0;
	public static Color currentColor = new Color(1.f, 1.f, 1.f, 1.f);
	private float alpha = 0.f;
	
	RMEImage logo;
	RMEImage planet;
	RMEButton btn_next, btn_back;
	RMEButton currentButton;
	
	int action = 0;
	boolean rightTouched = false, leftTouched = false;
	
	float delay, red, green, blue, col;
	

	public MainMenu (Supernova game) {
		this.game = game;
		
		
		guiCam.position.set(width / 2, height / 2, 0);
		
		Json json = new Json();
		
		FileHandle filehandle = Gdx.files.local("objects/MainMenu.jXGUI");
		RMECrypt crypt = new RMECrypt();
		String s = crypt.decrypt(filehandle.readBytes(), "YouTooLife1911");
		gui = json.fromJson(RMEGUI.class, s);

		//gui = json.fromJson(RMEGUI.class, new FileHandle(text+".jGUI"));
		
		createGui();
		
		delay = red = green = blue = col = alpha = 0;
		
		RMESound.playTrack("menu");
		Gdx.input.setCursorCatched(true);
	}
	
	public void menuClick() {
		switch (action) {
		case 0:
			game.setScreen(new Surface(game, "level0"));
			break;
		case 1:
			game.setScreen(new Surface(game, "level0"));
			break;
		case 2:
			game.setScreen(new Surface(game, "level0"));
			break;
		default:
			break;
		}
	}
	
	
	
	public void createGui() {
		//skin = new Skin(Gdx.files.internal("uiskin.json"));
		//stage = new Stage(new StretchViewport(width, height));
		//Gdx.input.setInputProcessor(stage);
		
		logo = gui.getImageByName("logo");
		logo.setPosition(width/2-logo.getWidth()/2, height-logo.getHeight()*1.75f);
		
		planet = gui.getImageByName("planet");
		planet.setPosition(width/2-logo.getWidth()/2, logo.getY()-40.f-planet.getHeight());
		
		for(RMEButton btn: gui.getButtons())
			btn.setAlpha(0.f);
		
		btn_next = gui.getButtonById(1001);
		btn_next.setCurrentFrame(new 
				TextureRegion(Assets.getTexture("Menu/Button/btn_next_hold")));
		btn_next.setPosition(planet.getX()+planet.getWidth()+5.f, 
				planet.getY()+planet.getHeight()/2-btn_next.getHeight()/2);
		btn_next.setRotation(180.f);
		
		btn_back = gui.getButtonById(1000);
		btn_back.setPosition(planet.getX()-5.f-btn_back.getWidth(), 
				planet.getY()+planet.getHeight()/2-btn_back.getHeight()/2);
		
		btn_next.setAlpha(1.f);
		btn_back.setAlpha(1.f);
		
		
		currentButton = gui.getButtonById(action);
		currentButton.setPosition(
				planet.getX()+planet.getWidth()/2-currentButton.getWidth()/2, 
				planet.getY()+planet.getHeight()/2-currentButton.getHeight()/2);
		
		currentButton.setAlpha(1.f);
		
		Gdx.input.setInputProcessor(new InputProcessor() {
			
			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean scrolled(int amount) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				
				if (keycode == Keys.ESCAPE) {
					System.exit(0);
				}
				
				if (keycode == Keys.ENTER || keycode == Keys.SPACE) {
					
					menuClick();
					
				}
				
				if (leftTouched && (keycode == Keys.LEFT || keycode == Keys.A)) {
					leftTouched = false;
					btn_back.setColor(1.f, 1.f, 1.f, 1.f);
					action--;
				}
				if (rightTouched && (keycode == Keys.RIGHT || keycode == Keys.D)) {
					rightTouched = false;
					btn_next.setColor(1.f, 1.f, 1.f, 1.f);
					action++;
				}
				if (action > 5)
					action = 0;
				if (action < 0)
					action = 5;
				
				currentButton.setAlpha(0.f);
				
				currentButton = gui.getButtonById(action);
				currentButton.setPosition(
						planet.getX()+planet.getWidth()/2-currentButton.getWidth()/2, 
						planet.getY()+planet.getHeight()/2-currentButton.getHeight()/2);
				
				alpha = 0.f;
				
				return true;

			}
			
			@Override
			public boolean keyTyped(char character) {
				
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				
				if (!leftTouched && (keycode == Keys.LEFT || keycode == Keys.A)){
					leftTouched = true;
					btn_back.setColor(red, green, blue, 1.f);
				}
				if (!rightTouched && (keycode == Keys.RIGHT || keycode == Keys.D)) {
					rightTouched = true;
					btn_next.setColor(red, green, blue, 1.f);
				}
				return true;
			}
		});
		
	}
	
	
	
	public void handleInput(float delta) {
		
		
	}

	
	public void update (float delta) {
		handleInput(delta);
		
		gui.update(delta);
		
		editColor(delta);
		
	
	}
	

	public void draw () {
		GL20 gl = Gdx.gl;
		/*gl.glClearColor(0, 0, 0, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gl.glEnable(GL20.GL_BLEND);
		gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);*/
		gl.glEnable(GL20.GL_BLEND);
		gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		gl.glClearColor(0, 0, 0, 0);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		guiCam.update();
		game.batcher.setProjectionMatrix(guiCam.combined);
		
		game.batcher.disableBlending();
		game.batcher.begin();
		gui.drawBackground(game.batcher);
		game.batcher.end();
		
		

		
		gl.glEnable(GL20.GL_BLEND);
		gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

		
		
		game.batcher.enableBlending();
		game.batcher.begin();
	/////-------GAME-------////
		gui.draw(game.batcher);
		

			game.batcher.end();
	
		//stage.draw();

		
		///----dbg---///
		game.batcher.begin();
			dbgMsg.draw(game.batcher, 
					"FPS: "+String.valueOf(Gdx.graphics.getFramesPerSecond()),
					guiCam.position.x-(width/2)+10.f,
					guiCam.position.y-(height/2)+height-10.f);
			game.batcher.end();	
	    
	}

	

	@Override
	public void render (float delta) {
		update(delta);
		draw();
	}
	
	public void editColor(float delta) {
		float speed = 0.8f;
		col = action;
		col = col % 4;
		if (col == 1) {
			if (red < 1)
			red = red+speed*delta;
			if (red > 1) red = 1;
			if (green > 0)
				green = green-speed*delta;
			if (green < 0) green = 0;
			if (blue > 0)
				blue = blue -speed*delta;
			if (blue < 0) blue = 0;
			}
			if (col == 2) {
				if (green < 1)
				green = green+speed*delta;
				if (green > 1) green = 1;
				if (red > 0)
					red = red-speed*delta;
				if (red < 0) red = 0;
				if (blue > 0)
					blue = blue -speed*delta;
				if (blue < 0) blue = 0;
				}
			if (col == 3) {
					if (blue < 1)
					blue = blue+speed*delta;
					if (blue > 1) blue = 1;
				if (red > 0)
					red = red-speed*delta;
				if (red < 0) red = 0;
				if (green > 0)
					green = green -speed*delta;
				if (green < 0) green = 0;
				}
			if (col == 0) {
				if	(blue < 1)
					blue = blue+speed*delta;
				if (blue > 1) blue = 1;
				if (red < 1)
					red = red+speed*delta;
				if (red > 1) red = 1;
				if (green < 1)
					green = green +speed*delta;
				if (green > 1) green = 1;
				}
			if (alpha < 1.f) {
				alpha =  alpha + 0.8f/3.f*delta;
				if (alpha >= 1.f)
					alpha = 1.f;
			}
			
			planet.setColor(red, green, blue, 1.f);
			currentButton.setAlpha(alpha);
			btn_next.setAlpha(alpha);
			btn_back.setAlpha(alpha);
	}

	@Override
	public void pause () {
	}
	
	@Override
	public void resize(int width, int height) {
		//stage.getViewport().update(width, height);
		//System.out.println(stage.getViewport().getViewportWidth());
	}
	
}
