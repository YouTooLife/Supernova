package net.youtoolife.supernova.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonValue;

import net.youtoolife.supernova.handlers.RMESprite;
import net.youtoolife.supernova.screens.Surface;

public class SurfaceX extends RMESprite implements Json.Serializable {
	
	public Rectangle bounds;
	private int cl = 0;
	private boolean rect = false, draw = false;
	
	public SurfaceX() {
		 super();
		 bounds = new Rectangle(0,0, 128, 128);
	}
	
	public SurfaceX(Texture ws, float x, float y, boolean draw, boolean rect) {
		super(ws, x, y);
		this.setDraw(draw);
		this.setRect(rect);
		bounds = new Rectangle(x, y, 128, 128);
		setSize(128, 128);
		setColor(Surface.currentColor);
		System.out.println("Draw "+draw);
	}
	
	public SurfaceX(Texture ws, int frame_cols, int frame_rows, int animStart, int animStop, boolean animActive, float animSpeed) {
		super(ws, frame_cols, frame_rows, animStart, animStop, animActive, animSpeed);
	}
	
	public void update(float delta) {
		draw(delta);
		bounds.setPosition(getX()+bounds.width, getY()+bounds.height);
	}
	
	@Override
	public void write(Json json) {
		json.writeValue("ctype", cl);
		json.writeValue("drect", isRect());
		json.writeValue("draw", isDraw());
		super.write(json);
	}

	@Override
	public void read(Json json, JsonValue jsonData) {
		super.read(json, jsonData);
		setCType(jsonData.getInt("ctype"));
		setRect(jsonData.getBoolean("drect"));
		setDraw(jsonData.getBoolean("draw"));
	}
	
	public void setCType(int ctype) {
		cl = ctype;
	}
	
	public int getCType() {
		return cl;
	}

	public boolean isDraw() {
		return draw;
	}

	public void setDraw(boolean draw) {
		this.draw = draw;
	}

	public boolean isRect() {
		return rect;
	}

	public void setRect(boolean rect) {
		this.rect = rect;
	}
	

}
