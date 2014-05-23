package com.boxel2.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteSheet {
	private double rate; //animation rate (zero = still, one = instant animation)
	private double currentFrame;
    private double rotation; //rotation of sprite in degrees
    private double pivotX; //rotation position (0.0 to 1.0)
    private double pivotY; //rotation position (0.0 to 1.0)
	private boolean finished; //checks if animation is complete
    private boolean flipX; //mirror horizontal
    private boolean flipY;  //mirror vertical
	private int imgWidth; //final drawn width
	private int imgHeight; //final drawn height
	private int spriteWidth;  //width of original sprite
	private int spriteHeight; //height of original sprite
	private int hFrames; //sprite frames horizontally (ex: [2]* x [4])
	private int vFrames; //sprite frames vertically (ex: [2] x [4]*)
	private int frames; //total number of frames
	private Texture image; //main image source
	private Rect spriteRect, destRect;

	public SpriteSheet(Texture image, int hFrames, int vFrames, double rate){
		this.hFrames=hFrames; //horizontal frames (ex: 4)
		this.vFrames=vFrames; //vertical frames (ex: 4)
		this.image=image; //commonly a bitmap, it must be stored into this class in order to be drawn
		this.rate=rate; //0.0 = not animating, 1.0 = animating at fastest possible rate
        flipX = flipY = false;

		spriteWidth =imgWidth = image.getWidth()/this.hFrames; //commonly results in 32x32 or 16x16
		spriteHeight=imgHeight=image.getHeight()/this.vFrames;
		
		frames = (hFrames*vFrames); //4x4 = 16 possible frames
		spriteRect = new Rect(0,0,imgWidth,imgHeight); //this determines what frame of the sprite to show
		destRect = new Rect(); //this is where that particular frame will be drawn
	}
	public boolean animate(){ //forced animation
		finished = false;
		if (currentFrame+rate < frames) currentFrame+=rate;
		else{ //reset animation to start
			finished = true;
			currentFrame = 0;
		}
		//adjust sprite location
		spriteRect.top = (((int)currentFrame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = (((int)currentFrame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
		return finished;
    }
	public void animate(int frame){ //draws a particular frame from the animation
        currentFrame=frame;
		//adjust sprite location
		spriteRect.top = ((frame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = ((frame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
	}
	public void animate(int start, int end){ //draws frames in a given order (ex: animate(4,8) == 4,5,6,7;
		end++;
		//animates between a certain frame
		if (currentFrame < start) currentFrame = start;
		if (currentFrame+rate < end-1) currentFrame+=rate;
		else{
			finished = true;
			currentFrame = start;
		}
		//adjust sprite location
		spriteRect.top = (((int)currentFrame)/hFrames)*spriteHeight;
		spriteRect.bottom = spriteRect.top + spriteHeight;
		spriteRect.left = (((int)currentFrame)%hFrames)*spriteWidth;
		spriteRect.right = spriteRect.left + spriteWidth;
	}
	public void draw(SpriteBatch batch){ //draws your sprite at the exact frame and possition
        if (currentFrame >= 0){
            batch.draw(image, // The texture
                getDestRectLeft(),  //The left of image
                getDestRectTop(), //The bottom of image
                getPivotX(),  //Pivot Point(X), used for rotating the image
                getPivotY(), //Pivot Point(Y), used for rotating the image
                getImageWidth(), //The final size(Width) of the image part to be drawn
                getImageHeight(), //The final size(height) of the image part to be drawn
                1, //Scale in x dimension
                1, //Scale in y dimension
                (float)rotation, //Rotation in degrees
                getSpriteLeft(),  //The left of part of the original image in textureatlas
                getSpriteTop(), //The bottom of part of the original image in textureatlas
                getSpriteWidth(), //the width of part of the original image in textureatlas
                getSpriteHeight(), //the height of part of the original image in textureatlas
                flipX,  //flip image in x direction
                flipY //flip image in y direction
            );
        }
	}
	public void update(double x, double y){ //this is strictly used to move your sprite on the screen, not animate
		//texture placement
        destRect.top = (int)(y-imgHeight * pivotY);
        destRect.bottom = destRect.top + imgHeight;
        destRect.left = (int)(x-imgWidth * pivotX);
        destRect.right = destRect.left + imgWidth;
	}
	public void update(double x, double y, int xSize, int ySize){
		imgWidth = xSize;
		imgHeight = ySize;
		update(x,y);
	}
	public void updateSprite(int x1, int y1, int x2, int y2){
		spriteRect.top = y1;
		spriteRect.right = x2;
		spriteRect.bottom = y2;
		spriteRect.left = x1;
	}
	public void resize(int imgWidth, int imgHeight){
		this.imgWidth=imgWidth;
		this.imgHeight=imgHeight;
	}
	public void resetDest(){
		destRect.top = 0;
		destRect.bottom = 0;
		destRect.left = 0;
		destRect.right = 0;
	}
    public void updateLayout(){ updateLayout(vFrames,hFrames); }
    public void updateLayout(int vFrames, int hFrames){
        if (vFrames < 1) vFrames = 1;
        else if (vFrames > 99) vFrames = 99;
        if (hFrames < 1) hFrames = 1;
        else if (hFrames > 99) hFrames = 99;
        this.hFrames=hFrames;
        this.vFrames=vFrames;
        spriteWidth =imgWidth = image.getWidth()/this.hFrames;
        spriteHeight=imgHeight=image.getHeight()/this.vFrames;
        frames = (hFrames*vFrames);
        spriteRect.right=spriteWidth;
        spriteRect.bottom=spriteHeight;
    }

	public void setImage(Texture image){ this.image = image; }
	public Texture getImage(){ return image; }
	public int getImageWidth(){ return imgWidth; }
	public int getImageHeight(){ return imgHeight; }
	public int getHFrames(){ return hFrames; }
	public int getVFrames(){ return vFrames; }
    public int getPivotX(){ return (int)(pivotX * (double)imgWidth);}
    public int getPivotY(){ return (int)(pivotY * (double)imgHeight);}
	public int getCurrentFrame(){ return (int)currentFrame; }
    public int getFrames(){ return frames; }
	public int getSpriteWidth(){ return spriteWidth; }
	public int getSpriteHeight(){ return spriteHeight; }
	public int getSpriteTop(){ return spriteRect.top; }
	public int getSpriteRight(){ return spriteRect.right; }
	public int getSpriteBottom(){ return spriteRect.bottom; }
	public int getSpriteLeft(){ return spriteRect.left; }
	public int getDestRectTop(){ return destRect.top; }
	public int getDestRectRight(){ return destRect.right; }
	public int getDestRectBottom(){ return destRect.bottom; }
	public int getDestRectLeft(){ return destRect.left; }
    public int getDestRectWidth(){ return destRect.right - destRect.left; }
    public int getDestRectHeight(){ return destRect.bottom - destRect.top; }
	public double getRate(){ return rate; }
	public void center(){ pivotX = pivotY = 0.5; }
    public void setPivot(double x, double y){ pivotX = x; pivotY = y; }
	public boolean isFinished(){ return finished; }
	public boolean isAnimating(){ return currentFrame > 0; }
    public void rotate(double rotation){ this.rotation=rotation; }
	//basic rectangle class
	public class Rect {
		public int left;
		public int right;
		public int top;
		public int bottom;
		
		public Rect(){ 
			
		}
		public Rect(int left, int top, int right, int bottom){
			this.left=left;
			this.top=top;
			this.right=right;
			this.bottom=bottom;
		}
	}
    public void dispose(){
        image.dispose();
    }
}
