package com.example.objectanimator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    //Block b;
    ObjectAnimator objectAnimator;
    //Path p;
    final int dx = 15;
    final int dy = 15;
    Timer timer = new Timer();
    //boolean tiltedRight = false;
    float[] tiltVals = new float[2];
    //ArrayList<Block> blockPool;

    Block b;

    Gyroscope gyroscope;

    SharedPreferences shref;

    Handler handler = new Handler();

    float screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    float screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    double tempScreenHeight = Resources.getSystem().getDisplayMetrics().heightPixels; // platform height is ~500px 520
    //float platformHeight = screenHeight - 500;

    float platformHeight = (float) tempScreenHeight - (60 * Resources.getSystem().getDisplayMetrics().density); // 1669.0

//    DisplayMetrics metrics = getWindowManager().getDefaultDisplay().getMetrics(metrics);
//    float logicalDensity = metrics.density;
//    int px = (int) (dp * logicalDensity + 0.5);

    float x = screenWidth / 2;
    float y = screenHeight / 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        //platformHeight = platformHeight - statusBarHeight;
        //float platformHeight = screenHeight - statusBarHeight;
        Block tempB= new Block(ContextCompat.getDrawable(this, R.drawable.rectangle),(int) screenWidth , (int) (60 * getResources().getDisplayMetrics().density), this, screenHeight, screenWidth);
        tempB.setX(0);

        Block platform= new Block(ContextCompat.getDrawable(this, R.drawable.rectangle),(int) screenWidth , (int) (60 * getResources().getDisplayMetrics().density), this, screenHeight, screenWidth);
        platform.setX(0);
        platform.setY(platformHeight);
//        Context context = getApplicationContext();
//        Gson gson = new Gson();
//        shref = context.getSharedPreferences("blocks", Context.MODE_PRIVATE);
//        String response=shref.getString("blocks", "");
//        ArrayList<Block> blockPool = gson.fromJson(response,
//                new TypeToken<ArrayList<Block>>(){}.getType());

//        SharedPreferences appSharedPrefs = PreferenceManager
//                .getDefaultSharedPreferences(this.getApplicationContext());
//        Gson gson = new Gson();
//        String json = appSharedPrefs.getString("MyObject", "");
//        Type type = new TypeToken<List<Block>>(){}.getType();
//        List<Block> blockPool = gson.fromJson(json, type);
//        Gson gson = new Gson();
//        String response=shref.getString("Key", "");
//        ArrayList<Block> blockPool = gson.fromJson(response,
//                new TypeToken<List<Block>>(){}.getType());

//        Bundle bundle = getIntent().getExtras();
//        ArrayList<Block> blockPool = bundle.getParcelableArrayList("Blocks");
//        ArrayList<Block> backupBlocks = blockPool;
//        Bundle bundle = getIntent().getExtras();
//        ArrayList<Block> blockPool = (ArrayList<Block>) bundle.getSerializable("serialzable");
        GameInfo temp = new GameInfo();
        ArrayList<Block> placedBlocks = new ArrayList<>();

        ArrayList<Block> blockPool = temp.getBlocks();

        blockPool.add(0, tempB);
        b = blockPool.remove(0);
        //b=tempB;
        b.moveDown(900);


        //Block tempB = b;
        //blockPool.add(1, tempB);
        placedBlocks.add(platform);

        System.out.println("starting x is " + x); //540.0
        System.out.println("starting y is " + y); // 1010.0
        System.out.println("platform y is " + platformHeight);
        System.out.println("screen height is " + screenHeight);



//        p = new Path();
//        p.moveTo(x, y);

        gyroscope = new Gyroscope(this);

//        imageView = (ImageView) findViewById(R.id.platform);
//        float secondplatformHeight = screenHeight - imageView.getHeight();
//        System.out.println("second platform y is " + secondplatformHeight);
        //Drawable d = getResources().getDrawable(R.id.square);
        //Block b1 = new Block(ContextCompat.getDrawable(this, R.drawable.purplesquare), (int) (60 * getResources().getDisplayMetrics().density), (int) (60 * getResources().getDisplayMetrics().density), this, screenHeight, screenWidth);
//        Block b2 = new Block(ContextCompat.getDrawable(this, R.drawable.redsquare), (int) (60 * getResources().getDisplayMetrics().density), (int) (60 * getResources().getDisplayMetrics().density), this, screenHeight, screenWidth);
//
//        blockPool.add(b1);
//        blockPool.add(b2);

        //b = blockPool.get(0);

//        b1.setY(platformHeight);
//        b1.setX(500);

        //imageView = b.getImageView();
        ConstraintLayout c = (ConstraintLayout) findViewById(R.id.main);
        c.addView(b.getImageView());
        c.addView(platform.getImageView());
        //c.removeView(b.getImageView());
        //c.addView(b1.getImageView());
        c.setConstraintSet(new ConstraintSet());

//        b.setX(x);
//        b.setY(y);

        //Path p = new Path();
        //p.addCircle(500, 500, 200, Path.Direction.CCW);
        //p.rMoveTo(10, 10);


        //p.addCircle(500, 500, 200, Path.Direction.CCW);

        //objectAnimator = ObjectAnimator.ofFloat(imageView, "x", "y", p);


        gyroscope.setListener(new Gyroscope.Listener() {
            @Override
            public void onRotation(float rx, float ry, float rz) {
                tiltVals[0] = rx;
                tiltVals[1] = ry;
//                if ((tiltVals[0] > 0.5 && rx < -0.5) || (tiltVals[0] < -0.5 && rx > 0.5)) {
//                    tiltVals[0] = rx;
//                }
//                if ((tiltVals[1] > 0.5 && ry < -0.5) || (tiltVals[1] < -0.5 && ry > 0.5)) {
//                    tiltVals[1] = ry;
//                }
//                if (ry > 1.0) { // tilt right 0.5 is what we want
//                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
//                    System.out.println("tilted");
//                    //tiltedRight = true;
//                    //p.moveTo(450, 700);
//                    //p.lineTo(600, 700);
////                    p.addCircle(500, 500, 200, Path.Direction.CCW);
//                    //objectAnimator.start();
//                    moveRight();
//                }
//                if (ry < -1.0) { // tilt left
//                    System.out.println("tilted");
//                    getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
//                    //p.moveTo(450, 700);
//                    //p.lineTo(300, 700);
////                    p.addCircle(500, 500, 200, Path.Direction.CCW);
//                    //objectAnimator.start();
//                    //moveLeft();
//                }
//                if (rz > 1.0) { // rotate left while flat
//                    System.out.println("tilted");
//                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
//                } if (rz < -1.0) { // rotate right while flat
//                    System.out.println("tilted");
//                    getWindow().getDecorView().setBackgroundColor(Color.GRAY);
//                }
//                if (rx > 1.0) { // tilt so it's facing you
//                    System.out.println("tilted");
//                     //moveDown();
//                    getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
//                } if (rx < -1.0) { // tilt so it's facing away
//                    getWindow().getDecorView().setBackgroundColor(Color.CYAN);
//                    System.out.println("tilted");
//                    //moveUp();
//                }
            }
        });

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        boolean checkPlatform = true;
                        if (b.getX() > screenWidth || b.getX() < 0 || b.getY() < 0) { // || b.getY() > platform.getY()+dy
                            b.reset();
                            System.out.println("back to beginning");
                        }
                        int size = placedBlocks.size();
                        System.out.println("size of placedBlocks is " + size);
                        System.out.println("placedBlocks: " + placedBlocks);
                        for (int i = 0; i < size; i++) {
                            Block b1 = placedBlocks.get(i);
                            if(b.stackBlock(b1, dx, dy)) {
                                System.out.println("successfully stacked block");
//                                cancel();
                                placedBlocks.add(0, b);
                                //blockPool.remove(0);
                                if (blockPool.size() > 0) {
                                    b = blockPool.remove(0);
                                    c.addView(b.getImageView());
                                } else {
                                    cancel();
                                }
                                checkPlatform = false;
                                //cancel();
                                break;

                            }
                        }
                        System.out.println("x is " + b.getX());
                        System.out.println("y is " +b.getY());
//                        if (checkPlatform && b.getY() <= platformHeight + dy && b.getY() >= platformHeight - dy) {
//                            System.out.println("roses");
//                            b.setY(platformHeight);
//                            placedBlocks.add(b);
//                            if (blockPool.size() == 0) {
//                                cancel();
//                            } else {
//                                b = blockPool.remove(0);
//                                c.addView(b.getImageView());
//                            }
//                            //cancel();
                        if (checkPlatform){ // else if
                            float rx = tiltVals[0];
                            float ry = tiltVals[1];
                            if (ry > 0.5) {
                                b.moveRight(dx);
                            }
                            if (ry < -0.5) {
                                b.moveLeft(dx);
                            }
                            if (rx > 0.5) {
                                b.moveDown(dy);
                            }
                            if (rx < -0.5) {
                                b.moveUp(dy);
                            }
                        }

                        System.out.println("timer");
                    }
                });
            }
        }, 0, 20);

//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                float x = tiltVals[0];
//                float y = tiltVals[1];
//                if (y > 1.0) {
//                    moveRight();
//                }
//                if (y < -1.0) {
//                    moveLeft();
//                }
//                if (x > 1.0) {
//                    moveDown();
//                }
//                if (x < -1.0) {
//                    moveUp();
//                }
//                System.out.println("timer");
//            }
//        },0,  500);


        //objectAnimator = ObjectAnimator.ofFloat(imageView, "x", 800);
        //objectAnimator.start();


        //objectAnimator.start();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //objectAnimator.setDuration(100); // 4 seconds
//                //objectAnimator = ObjectAnimator.ofFloat(imageView, "y", 300);
//                //objectAnimator.setDuration(2000);
//                //objectAnimator.start();
//                System.out.println("button");
//            }
//        });
    }

//    public void moveRight() {
//        Path p = new Path();
//        p.moveTo(x, y);
//        x = x + dx;
//        y = y + 0;
//        p.rLineTo(dx, 0);
//        //p.moveTo(x, y);
//        objectAnimator = ObjectAnimator.ofFloat(imageView, "x", "y", p);
//        objectAnimator.setDuration(2000);
//
//        objectAnimator.start();
//
////        if (x > screenWidth || x < 0 || y < 0 || y > screenHeight) {
////            x = screenWidth / 2;
////            y = screenHeight / 2;
////            p.moveTo(x, y);
////        }
//
//    }
//
//    public void moveLeft() {
//        Path p = new Path();
//        p.moveTo(x, y);
//        x = x - dx;
//        y = y + 0;
//        p.rLineTo(-1*dx, 0);
//        //p.moveTo(x, y);
//        objectAnimator = ObjectAnimator.ofFloat(imageView, "x", "y", p);
//        objectAnimator.setDuration(2000);
//
//        objectAnimator.start();
////        if (x > screenWidth || x < 0 || y < 0 || y > screenHeight) {
////            x = screenWidth / 2;
////            y = screenHeight / 2;
////            p.moveTo(x, y);
////        }
//    }
//
//    public void moveUp() {
//        Path p = new Path();
//        p.moveTo(x, y);
//        x = x + 0;
//        y = y + dy;
//        p.rLineTo(0, dy);
//        //p.moveTo(x, y);
//        objectAnimator = ObjectAnimator.ofFloat(imageView, "x", "y", p);
//        objectAnimator.setDuration(2000);
//
//        objectAnimator.start();
////        if (x > screenWidth || x < 0 || y < 0 || y > screenHeight) {
////            x = screenWidth / 2;
////            y = screenHeight / 2;
////            p.moveTo(x, y);
////        }
//    }
//
//    public void moveDown() {
//        Path p = new Path();
////        if (y == screenHeight) {
////            System.out.println("platform reached");
////        }
//        p.moveTo(x, y);
//        x = x + 0;
//        y = y -dy;
//        p.rLineTo(0, -1*dy);
//        //p.moveTo(x, y);
//        objectAnimator = ObjectAnimator.ofFloat(imageView, "x", "y", p);
//        objectAnimator.setDuration(2000);
//
//        objectAnimator.start();
////        if (x > screenWidth || x < 0 || y < 0 || y > screenHeight) {
////            x = screenWidth / 2;
////            y = screenHeight / 2;
////            p.moveTo(x, y);
////        }
//    }


    @Override
    protected void onResume() {
        super.onResume();

        gyroscope.register();
    }

    @Override
    protected void onPause() {
        super.onPause();

        gyroscope.unregister();
    }
}