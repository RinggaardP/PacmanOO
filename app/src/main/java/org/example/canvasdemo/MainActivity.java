package org.example.canvasdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    MyView myView;
    String currentDirection = "right";
    String currentDirGh = "right";
    Timer timer;
    Timer timer2;
    Timer timer3;
    Timer timer4;
    int level = 1;
    int counter = 0;
    TextView scoreText;
    TextView levelText;
    TextView counterText;
    boolean isPaused;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonR = (Button) findViewById(R.id.moveRight);
        Button buttonL = (Button) findViewById(R.id.moveLeft);
        Button buttonU = (Button) findViewById(R.id.moveUp);
        Button buttonD = (Button) findViewById(R.id.moveDown);
        Button btnNew = (Button) findViewById(R.id.newGame);
        myView = (MyView) findViewById(R.id.gameView);
        scoreText = (TextView) findViewById(R.id.score);
        levelText = (TextView) findViewById(R.id.level);
        counterText = (TextView) findViewById(R.id.counter);
        Button btnPause = (Button) findViewById(R.id.pauseGame);

        levelText.setText("Level: " + level);

        timer();

        timer2();

        timer3();

        timer4();


        btnPause.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isPaused) {
                    timer();
                    isPaused = false;
                } else {
                    timer.cancel();
                    isPaused = true;
                }
            }
        });


        btnNew.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                //myView.newGame=true;
                myView.getCoins().clear();
                myView.createCoins();
                myView.resetScore();
                level=1;
                counter=0;
                myView.pacx = 0;
                myView.pacy = 0;
                myView.ghostx = 200;
                myView.ghosty = 200;
                myView.invalidate();
                scoreText.setText("Score: 0");
            }
        });


        scoreText.setText("Score: " + myView.getScore());


        //listener of our pacman
        buttonR.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                currentDirection = "right";
                myView.moveRight(10);
                scoreText.setText("Score: " + myView.getScore());

            }
        });
        buttonL.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                currentDirection = "left";
                myView.moveLeft(10);
                scoreText.setText("Score: " + myView.getScore());
            }
        });
        buttonU.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                currentDirection = "up";
                myView.moveUp(10);
                scoreText.setText("Score: " + myView.getScore());
            }
        });
        buttonD.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                currentDirection = "down";
                myView.moveDown(10);
                scoreText.setText("Score: " + myView.getScore());
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void timer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeDirection();
            }
        }, 100, 100);
    }

    public void timer2() {
        timer2 = new Timer();
        timer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeDirection2();
            }
        }, 4000, 4000);
    }

    public void timer3() {
        timer3 = new Timer();
        timer3.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeDirection3();
            }
        }, 30000, 30000);
    }

    public void timer4() {
        timer4 = new Timer();
        timer4.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeDirection4();
            }
        }, 0, 1000);
    }

    public void timeDirection() {
        this.runOnUiThread(Timer_Tick);

    }

    public void timeDirection2() {
        this.runOnUiThread(Timer_Tick2);

    }

    public void timeDirection3() {
        this.runOnUiThread(Timer_Tick3);

    }

    public void timeDirection4() {
        this.runOnUiThread(Timer_Tick4);

    }

    private Runnable Timer_Tick = new Runnable() {

        @Override
        public void run() {
            if (!isPaused) {
                switch (currentDirection) {
                    case "right": {
                        myView.moveRight(10);
                        break;
                    }
                    case "left": {
                        myView.moveLeft(10);
                        break;
                    }
                    case "up": {
                        myView.moveUp(10);
                        break;
                    }
                    case "down": {
                        myView.moveDown(10);
                        break;
                    }
                }
                if (level <= 1) {
                    switch (currentDirGh) {
                        case "right": {
                            myView.moveRightG(5);
                            break;
                        }
                        case "left": {
                            myView.moveLeftG(5);
                            break;
                        }
                        case "up": {
                            myView.moveUpG(5);
                            break;
                        }
                        case "down": {
                            myView.moveDownG(5);
                            break;
                        }
                    }
                }
                if (level == 2) {
                    switch (currentDirGh) {
                        case "right": {
                            myView.moveRightG(8);
                            break;
                        }
                        case "left": {
                            myView.moveLeftG(8);
                            break;
                        }
                        case "up": {
                            myView.moveUpG(8);
                            break;
                        }
                        case "down": {
                            myView.moveDownG(8);
                            break;
                        }
                    }
                }
                if (level == 3) {
                    switch (currentDirGh) {
                        case "right": {
                            myView.moveRightG(10);
                            break;
                        }
                        case "left": {
                            myView.moveLeftG(10);
                            break;
                        }
                        case "up": {
                            myView.moveUpG(10);
                            break;
                        }
                        case "down": {
                            myView.moveDownG(10);
                            break;
                        }
                    }
                }
                if (level == 4) {
                    switch (currentDirGh) {
                        case "right": {
                            myView.moveRightG(12);
                            break;
                        }
                        case "left": {
                            myView.moveLeftG(12);
                            break;
                        }
                        case "up": {
                            myView.moveUpG(12);
                            break;
                        }
                        case "down": {
                            myView.moveDownG(12);
                            break;
                        }
                    }
                }

                if (myView.calcDist2() < 80) {
                    myView.getCoins().clear();
                    level = 1;
                    counter = 0;
                    myView.createCoins();
                    myView.resetScore();
                    myView.pacx = 50;
                    myView.pacy = 400;
                    myView.ghostx = 200;
                    myView.ghosty = 200;
                    myView.invalidate();
                    scoreText.setText("Score: 0");
                } else {
                    myView.invalidate();
                    scoreText.setText("Score: " + myView.getScore());
                }
            }
        }
    };

    private Runnable Timer_Tick2 = new Runnable() {

        @Override
        public void run() {
            if (!isPaused) {
                switch (ghostDir()) {
                    case 1: {
                        currentDirGh = "right";
                        //myView.moveRightG(5);
                        break;
                    }
                    case 2: {
                        currentDirGh = "left";
                        //myView.moveLeftG(5);
                        break;
                    }
                    case 3: {
                        currentDirGh = "up";
                        //myView.moveUpG(5);
                        break;
                    }
                    case 4: {
                        currentDirGh = "down";
                        //myView.moveDownG(5);
                        break;
                    }
                }
                myView.invalidate();
                System.out.println(ghostDir());
            }
        }
    };

    private Runnable Timer_Tick3 = new Runnable() {

        @Override
        public void run() {
            if (!isPaused) {
                    level++;

                myView.createCoins();
                    myView.invalidate();


                levelText.setText("Level: " + level);
                myView.invalidate();

            }
        }

    };
    private Runnable Timer_Tick4 = new Runnable() {

        @Override
        public void run() {
            if (!isPaused) {
                counter++;

                counterText.setText("Time: " + counter);
                myView.invalidate();

            }
        }

    };

    public int ghostDir() {
        Random rnd = new Random();
        int gd = rnd.nextInt(4) + 1;
        return gd;
    }

}
