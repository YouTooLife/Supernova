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
import net.youtoolife.supernova.handlers.gui.RMEButton;
import net.youtoolife.supernova.handlers.gui.RMEImage;
import net.youtoolife.supernova.models.CheckPoint;
import net.youtoolife.supernova.models.Door;
import net.youtoolife.supernova.models.ObjectX;
import net.youtoolife.supernova.models.Opponent;
import net.youtoolife.supernova.models.Player;
import net.youtoolife.supernova.models.SurfaceX;
import net.youtoolife.supernova.models.Wall;

public class Surface extends ScreenAdapter {
	
public static float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();
	
	Supernova game;
	public static OrthographicCamera guiCam = new OrthographicCamera(width, height); 
	ShapeRenderer shapeRenderer = new ShapeRenderer();
	Texture bg;
	
	//Stage stage;
	//Skin skin;
	
	public static RMEGUI gui = new RMEGUI();
	public static RMEPack pack = new RMEPack();
	
	private BitmapFont dbgMsg = new BitmapFont(); 
	
	private int cl = 0;
	public static Color currentColor = new Color(1.f, 1.f, 1.f, 1.f);
	private float alpha = 1.f;
	

	public Surface (Supernova game, String level) {
		this.game = game;
		
		
		guiCam.position.set(width / 2, height / 2, 0);
		Json json = new Json();
		
		FileHandle filehandle = Gdx.files.local("Maps/"+level+".level");
		RMECrypt crypt = new RMECrypt();
		String s = crypt.decrypt(filehandle.readBytes(), "YouTooLife1911");
		pack = json.fromJson(RMEPack.class, s);
	
		
		//gui = json.fromJson(RMEGUI.class, new FileHandle(text+".jGUI"));
		pack.setGame(true);
		
		createInputProc();
		RMESound.playTrack("Darkness");
	}
	
	private void createInputProc() {
		
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
				// TODO Auto-generated method stub
				
				if (keycode == Keys.ESCAPE)
					game.setScreen(new MainMenu(game));
				return true;
			}
			
			@Override
			public boolean keyTyped(char character) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean keyDown(int keycode) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
	}

	public void createGui() {
		//skin = new Skin(Gdx.files.internal("uiskin.json"));
		//stage = new Stage(new StretchViewport(width, height));
		//Gdx.input.setInputProcessor(stage);
	}
	
	
	
	public void handleInput(float delta) {
		
		
	}

	
	public void update (float delta) {
		handleInput(delta);
		
		gui.update(delta);
		pack.update(delta);
		
		if (pack.isGame()) {
			/*if (guiCam.position.x < pack.getPlayer().getX() + pack.getPlayer().getWidth()/2)
				guiCam.position.x += 400*delta;
			if (guiCam.position.x > pack.getPlayer().getX() + pack.getPlayer().getWidth()/2)
				guiCam.position.x -= 400*delta;
			if (guiCam.position.y < pack.getPlayer().getY() + pack.getPlayer().getHeight()/2)
				guiCam.position.y += 400*delta;
			if (guiCam.position.y > pack.getPlayer().getY() + pack.getPlayer().getHeight()/2)
				guiCam.position.y -= 400*delta;*/
			guiCam.position.x = pack.getPlayer().getX() + pack.getPlayer().getWidth()/2;
			guiCam.position.y = pack.getPlayer().getY() + pack.getPlayer().getHeight()/2;
		}
		
	
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
		pack.drawBackground(game.batcher);
		game.batcher.end();
		
		

		
		gl.glEnable(GL20.GL_BLEND);
		gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
		shapeRenderer.setProjectionMatrix(guiCam.combined);
	    shapeRenderer.begin(ShapeType.Line);
	    
	    ////---HOLST---////
	    
	    shapeRenderer.end();
	    
	    ///---Objects---///
	    shapeRenderer.begin(ShapeType.Filled);
		pack.drawShape(shapeRenderer);
		shapeRenderer.end();	
		
		game.batcher.enableBlending();
		game.batcher.begin();
	/////-------GAME-------////
		pack.draw(game.batcher);
		
			////----GUI----///
			game.batcher.end();

		
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

	@Override
	public void pause () {
	}
	
	@Override
	public void resize(int width, int height) {
		//stage.getViewport().update(width, height);
		//System.out.println(stage.getViewport().getViewportWidth());
	}
	
}
