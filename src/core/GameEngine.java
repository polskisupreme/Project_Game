package core;

import entitys.Bullet;
import entitys.Player;
import entitys.staff.Controller;
import entitys.staff.EntityEnemy;
import entitys.staff.EntityPlayer;
import graphics.SpriteTextures;
import graphics.spriteLoad.SpriteLoader;
import inputs.Keyboard;
import inputs.Mouse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;

public class GameEngine extends Canvas implements Runnable {

    public static final int screenWidth = 240;
    public static final int screenHeight = screenWidth / 12 * 9;
    public static final int scale = 3;
    public static final int FPS = 60;
    public static int Health = 200;

    private Thread thread;
    private Player player;
    private Controller controller;
    private SpriteTextures textures;
    private Menu menu;
    private HighScore hS;
    private Settings settings;

    public LinkedList<EntityPlayer> entityPlayer;
    public LinkedList<EntityEnemy> entityEnemy;

    //https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
    //wyjasnienia enum
    public enum STATE{
        MENU,
        GAME,
        HIGHSCORE,
        SETTINGS
    }

    public static STATE state = STATE.MENU;

    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;

    private boolean runable = false;
    private boolean is_Shooting = false;

    private String saveData;
    private final String fileName = "ducker";

    private long timePassed;
    private long startTime;
    private String mainTimePattern = "00:00:000";

    public static int enemyCount = 8;
    public static int enemyKilled = 0;
    public static int score = 0;
    private static int highScore = 0;
    private Font scoreFont;

    public synchronized void startThread(){
        if (runable) return;
        runable = true;

        thread = new Thread(this);
        thread.start();
    }
    private synchronized void stopThread(){
        if (!runable)return;
        runable = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(1);
    }

    public void controlPanel(){
        requestFocus(true);

        SpriteLoader spriteLoader = new SpriteLoader();
        try {

            spriteSheet = spriteLoader.loadImage("/spriteSheets/SpriteSheets.png");
            background = spriteLoader.loadImage("/spriteSheets/background.png");

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.addKeyListener(new Keyboard(this));
        this.addMouseListener(new Mouse());

        try {
            saveData = GameEngine.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
        }catch (Exception e){
            e.printStackTrace();
        }
        scoreFont = new Font("Arial", Font.BOLD, 20);

        loadHighScoreTime();
        startTime = System.nanoTime();

        textures = new SpriteTextures(this);
        controller = new Controller(this, textures);
        player = new Player((double) (screenWidth * scale) / 2, 460, textures, this, controller);

        menu = new Menu();
        hS = new HighScore();
        settings = new Settings();

        entityPlayer = controller.getEntitiesPlayer();
        entityEnemy = controller.getEntitiesEnemy();

        controller.createObjects();
        controller.createEnemy(enemyCount);
    }

    private void createSaveData() throws NumberFormatException{
        try {
            File file = new File(saveData, fileName);

            FileWriter output = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(output);
            bw.write("" + 0);
            bw.write(""+ 0);
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void loadHighScoreTime() throws NumberFormatException{
            try {
                File file = new File(saveData, fileName);

                if (!file.isFile()) createSaveData();

                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                highScore = Integer.parseInt(br.readLine());
                timePassed = Long.parseLong(br.readLine());
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    private void setHighScoreTime() throws NumberFormatException{
        FileWriter output;

        try{
            File file = new File(saveData, fileName);
            output = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(output);

            bw.write("" + highScore);
            bw.newLine();
            bw.write("" + timePassed);

            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String timeFormat(long mSeconds){
        String timePattern;

        String hourPattern = "";
        int hours = (int)(mSeconds / 3600000L);
        if (hours >= 1){
            mSeconds -= hours * 3600000L;
            if (hours > 10){
                hourPattern = "0" + hours;
            }else {
                hourPattern = "" + hours;
            }
            hourPattern += ":";
        }

        String minutePattern;
        int minutes = (int)(mSeconds / 60000);
        if (minutes >= 1){
            mSeconds -= minutes * 60000L;
            if (minutes > 10){
                minutePattern = "0" + minutes;
            }else {
                minutePattern = "" + minutes;
            }
        }else {
            minutePattern = "00";
        }

        String secondPattern;
        int seconds = (int)(mSeconds / 1000);
        if (seconds >= 1){
            mSeconds -= seconds * 1000L;
            if (minutes > 10){
                secondPattern = "0" + seconds;
            }else {
                secondPattern = "" + seconds;
            }
        }else {
            secondPattern = "00";
        }

        String mSecondsPattern = "";
        if (mSeconds > 99){
            mSecondsPattern = "" + mSeconds;
        }else if (mSeconds > 9){
            mSecondsPattern = "0" + mSeconds;
        }else{
            minutePattern = "00" + mSeconds;
        }

        timePattern = hourPattern + minutePattern + ":" +  secondPattern + ":" + mSecondsPattern;

        return timePattern;
    }

    @Override
    public void run() {
        //https://youtu.be/VpH33Uw-_0E

        controlPanel();

        long lastTime = System.nanoTime();
        double calculateTimeWithFPS = 1000000000d / FPS;
        double delta = 0;

        while (runable){

            long currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / calculateTimeWithFPS;
            lastTime = currentTime;
            if (delta >= 1){
                update();
                delta--;
            }
            render();
        }
        stopThread();

    }

    private void update(){

        if (state == STATE.GAME) {
            player.update();
            controller.update();

            if (score >= highScore) highScore = score;

            if (state == STATE.GAME){
                timePassed = (System.nanoTime() - startTime) / 1000000;
                mainTimePattern = timeFormat(timePassed);
            }else{
                startTime = System.nanoTime();
            }
            if (Health == 0){
                state = STATE.MENU;
                setHighScoreTime();
                reload();
                Health = 200;
                score = 0;
                timePassed = 0;
            }
        }


        if (enemyKilled >= enemyCount) {
            enemyCount = 1;
            enemyKilled = 0;
            controller.createEnemy(enemyCount);
        }
    }

    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();

        if (bufferStrategy == null){
            createBufferStrategy(3);
            return;
        }
        Graphics g = bufferStrategy.getDrawGraphics();

        g.drawImage(background,0, 0, null);

        if (state == STATE.GAME) {
            g.setColor(Color.red);
            g.fillRect(5,5,200,50);

            g.setColor(Color.green);
            g.fillRect(5,5,Health,50);

            g.setColor(Color.white);
            g.drawRect(5,5,200,50);

            g.setColor(Color.black);
            g.setFont(scoreFont);
            g.drawString("SCORE: " + score, 220, 20);
            g.setColor(Color.white);
            g.drawString("BEST: " + highScore,220,40);

            g.setColor(Color.black);
            g.drawString("Time: " + mainTimePattern, 550, 20);

            player.render(g);
            controller.render(g);

        }else if (state == STATE.MENU){
            menu.render(g);
        }else if (state == STATE.HIGHSCORE){
            hS.render(g);
        }else if (state == STATE.SETTINGS){
            settings.render(g);
        }
        g.dispose();
        bufferStrategy.show();
    }

    public void reload(){
        controlPanel();
        enemyKilled = 0;
        enemyCount = 10;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (state == STATE.GAME) {
            if (code == KeyEvent.VK_D) {
                player.setVelX(5);
            }
            if (code == KeyEvent.VK_A) {
                player.setVelX(-5);
            }
            if (code == KeyEvent.VK_SPACE && !is_Shooting) {
                is_Shooting = true;
                controller.addEntity(new Bullet(player.getX(), player.getY(), textures, this));
            }
            if (e.isShiftDown() && e.isControlDown() && code == KeyEvent.VK_Q) {
                state = STATE.MENU;
                reload();
                score = 0;
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_D){ player.setVelX(0); }
        if (code == KeyEvent.VK_A){ player.setVelX(0); }
        if (code == KeyEvent.VK_SPACE){ is_Shooting = false; }
        if (e.isShiftDown() && e.isControlDown() && code == KeyEvent.VK_Q) state = STATE.MENU;

    }

    public BufferedImage getSpriteSheet(){
        return spriteSheet;
    }

    public int getEnemyKilled(){ return enemyKilled; }
    public void setEnemyKilled(int enemyKilled){ GameEngine.enemyKilled = enemyKilled; }
}
