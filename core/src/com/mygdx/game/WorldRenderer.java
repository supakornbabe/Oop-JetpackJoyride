package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class WorldRenderer {
    World world;
    private Flyer flyer;
    private JetpackJoyrideGame jetpackjoyrideGame;

    private BitmapFont font;
    private Texture flyImg;
    //private Texture atRoofImg;
    private Texture runImg;

    //-- Animation CREDIT: http://www.pixnbgames.com/blog/libgdx/frame-by-frame-animations-in-libgdx/--//
    private TextureAtlas runningonRoofCharset,runningCharset;
    private static float FRAME_DURATION = .05f*2;
    private Animation runningAnimation;
    private Animation runningonRoofAnimation;
    private TextureRegion runningFrame;
    private TextureRegion runningonRoofFrame;
    private float elapsed_time = 0f;
    //private float running_origin_x, running_origin_y;
    //private float onRoof_origin_x, onRoof_origin_y;
    //--END Animation --//

    public WorldRenderer(JetpackJoyrideGame jetpackjoyrideGame, World world) {
        this.jetpackjoyrideGame = jetpackjoyrideGame;
        SpriteBatch batch = jetpackjoyrideGame.batch;
        this.world = world;
        flyImg = new Texture("flyer.png");
        runImg = new Texture("runner.png");
        //atRoofImg = new Texture("runnerOnRoof.png");
        font = new BitmapFont();

        //--- Running Animation ---//
        runningCharset = new TextureAtlas( Gdx.files.internal("running.atlas") );
        Array<TextureAtlas.AtlasRegion> runningFrames = runningCharset.findRegions("running");
        runningAnimation = new Animation(FRAME_DURATION, runningFrames, Animation.PlayMode.LOOP);
        TextureRegion runningTexture = runningFrames.first();
        //running_origin_x = (Gdx.graphics.getWidth()  - runningTexture.getRegionWidth())  / 2;
        //running_origin_y = (Gdx.graphics.getHeight() - runningTexture.getRegionHeight()) / 2;
        //--- END Running Animation ---//

        //--- Running Roof Animation ---//
        runningonRoofCharset = new TextureAtlas( Gdx.files.internal("runningonRoof.atlas") );
        Array<TextureAtlas.AtlasRegion> runningonRoofFrames = runningonRoofCharset.findRegions("running");
        runningonRoofAnimation = new Animation(FRAME_DURATION, runningonRoofFrames, Animation.PlayMode.LOOP);
        TextureRegion runningonRoofTexture = runningonRoofFrames.first();
        //onRoof_origin_x = (Gdx.graphics.getWidth()  - runningonRoofTexture.getRegionWidth())  / 2;
        //onRoof_origin_y = (Gdx.graphics.getHeight() - runningonRoofTexture.getRegionHeight()) / 2;
        //--- END Running Roof Animation ---//

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.update(delta);
        SpriteBatch batch = jetpackjoyrideGame.batch;

        Vector2 pos = world.getFlyer().getPosition();
        batch.begin();

        if (pos.y >= JetpackJoyrideGame.HEIGHT-flyer.HEIGHT){
            elapsed_time += Gdx.graphics.getDeltaTime();
            runningonRoofFrame = (TextureRegion) runningonRoofAnimation.getKeyFrame(elapsed_time);
            batch.draw(runningonRoofFrame, pos.x ,pos.y);
        }
        else if (pos.y > 0){
            batch.draw(flyImg, pos.x ,pos.y);
        }
        else{
            elapsed_time += Gdx.graphics.getDeltaTime();
            runningFrame = (TextureRegion) runningAnimation.getKeyFrame(elapsed_time);
            batch.draw(runningFrame, pos.x ,pos.y);
        }

        font.draw(batch, "" + world.getScore(), 700, 60);
        batch.end();
    }
}
