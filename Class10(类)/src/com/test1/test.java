package com.test1;
import javax.imageio.ImageIO;

import javax.swing.*;

import java.awt.*;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.io.File;

import java.util.Vector;
public class test extends JFrame {

    MYpanel mp1 = null;



    public test() {

        mp1 = new MYpanel();



        Thread t = new Thread(mp1);

        t.start();



        this.add(mp1);

        this.addKeyListener(mp1);//注册监听

        this.setSize(2000, 1500);

        this.setVisible(true);

    }



    public static void main(String[] args) {

        test mtg = new test();

    }





    class MYpanel extends JPanel implements KeyListener, Runnable {

        Hero hr;

        //定义敌人的坦克组

        Vector<EnemyTank> ets = new Vector<EnemyTank>();

        //定义炸弹集合

        Vector<Bomb> bombs = new Vector<Bomb>();



        int enSize = 3;



        //定义三张图片

        Image im1;

        Image im2;

        Image im3;



        public MYpanel() {

            hr = new Hero(50, 50);

            //初始化敌人坦克

            for (int i = 0; i < enSize; i++) {

                //创建一辆坦克

                EnemyTank et = new EnemyTank((i + 1) * 250, 0);

                et.setDirect(2);

                et.setColor(0);

                //启动敌人坦克

                Thread t = new Thread(et);

                t.start();

                //给敌人坦克添加子弹

                Shot s = new Shot(et.x + 50, et.y + 150, 2);

                //加入给敌人

                et.ss.add(s);

                Thread t2 = new Thread(s);

                t2.start();

                //加入

                ets.add(et);

            }

//            try {
//
//                im1= ImageIO.read(new File("\\C:\\Users\\TheShadow\\Desktop\\boom (1).png"));
//
//                im2= ImageIO.read(new File("\\C:\\Users\\TheShadow\\Desktop\\boom (2).png"));
//
//                im3= ImageIO.read(new File("\\C:\\Users\\TheShadow\\Desktop\\boom (3).png"));
//
//
//
//                //初始化图片  三张图片切换组成一个炸弹
//
////                im1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("\\C:\\Users\\TheShadow\\Desktop\\boom (1).png"));
//
////                im2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("\\C:\\Users\\TheShadow\\Desktop\\boom (2).png"));
//
////                im3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("\\C:\\Users\\TheShadow\\Desktop\\boom (3).png"));
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//
//            }

        }





        public void paint(Graphics g) {

            super.paint(g);

            g.fillRect(0, 0, 2000, 1500);



            //从ss中取出每一颗子弹画出

            for (int i = 0; i < this.hr.ss.size(); i++) {



                Shot myShot = hr.ss.get(i);

                //画出一个子弹

                if (myShot != null && (myShot.isLive == true)) {

                    g.setColor(Color.pink);

                    g.draw3DRect(myShot.x, myShot.y, 10, 10, false);

                }

                if (myShot.isLive == false) {

                    //从ss中删除该子弹

                    hr.ss.remove(myShot);

                }

            }

            //画出我的坦克

            if (hr.isLive) {

                this.drawTank(hr.getX(), hr.getY(), g, this.hr.direct, 1);

            }

            //画出敌人坦克

            for (int i = 0; i < ets.size(); i++) {

                EnemyTank et = ets.get(i);

                if (et.isLive) {

                    this.drawTank(et.getX(), et.getY(), g, et.direct, 0);

                    //画出敌人的子弹

                    for (int j = 0; j < et.ss.size(); j++) {

                        //取出子弹

                        Shot enemyShot = et.ss.get(j);

                        if (enemyShot.isLive) {

                            g.draw3DRect(enemyShot.x, enemyShot.y, 10, 10, false);

                        } else {

                            //如果死亡，就从向量中删除

                            et.ss.remove(enemyShot);

                        }

                    }

                }

            }

            //画出炸弹

            for (int i = 0; i < bombs.size(); i++) {

                //取出炸弹

                Bomb b = bombs.get(i);



                if (b.life > 6) {

                    g.drawImage(im1, b.x, b.y, 150, 150, this);

                } else if (b.life > 3) {

                    g.drawImage(im2, b.x, b.y, 150, 150, this);

                } else {

                    g.drawImage(im3, b.x, b.y, 150, 150, this);

                }

                //画一次就减少生命

                b.lifeDown();

                //如果炸弹life=0

                if (b.life == 0) {

                    bombs.remove(b);

                }

            }

        }



        //判断是否击中目标

        public void hitTank(Shot s, Tank et) {

            //判断坦克的方向

            switch (et.direct) {

                case 0:

                case 2:

                    if (s.x > et.x && s.x < et.x + 100 && s.y > et.y && s.y < et.y + 150) {

                        s.isLive = false;

                        et.isLive = false;

                        //创建一颗炸弹，放入vector

                        Bomb b = new Bomb(et.x, et.y);

                        bombs.add(b);





                    }

                case 1:

                case 3:

                    if (s.x > et.x && s.x < et.x + 150 && s.y > et.y && s.y < et.y + 100) {

                        s.isLive = false;

                        et.isLive = false;

                        //创建一颗炸弹，放入vector

                        Bomb b = new Bomb(et.x, et.y);

                        bombs.add(b);

                    }

            }

        }





        public void drawTank(int x, int y, Graphics g, int direct, int type) {

            switch (type) {

                //判断类型

                case 0:

                    g.setColor(Color.cyan);

                    break;

                case 1:

                    g.setColor(Color.red);

                    break;

            }

            switch (direct) {

                //判断方向

                case 0:

                    //  g.setColor(Color.cyan);

                    g.fill3DRect(x, y, 25, 150, false);

                    g.fill3DRect(x + 75, y, 25, 150, false);

                    g.fill3DRect(x + 25, y + 25, 50, 100, false);

                    g.fillOval(x + 25, y + 50, 50, 50);

                    g.drawLine(x + 50, y + 75, x + 50, y - 10);

                    break;

                case 1:

                    g.fill3DRect(x, y, 150, 25, false);

                    g.fill3DRect(x, y + 75, 150, 25, false);

                    g.fill3DRect(x + 25, y + 25, 100, 50, false);

                    g.fillOval(x + 50, y + 25, 50, 50);

                    g.drawLine(x + 75, y + 50, x + 150, y + 50);

                    break;



                case 2:

                    //  g.setColor(Color.cyan);

                    g.fill3DRect(x, y, 25, 150, false);

                    g.fill3DRect(x + 75, y, 25, 150, false);

                    g.fill3DRect(x + 25, y + 25, 50, 100, false);

                    g.fillOval(x + 25, y + 50, 50, 50);

                    g.drawLine(x + 50, y + 75, x + 50, y - 10 + 150);

                    break;

                case 3:

                    g.fill3DRect(x, y, 150, 25, false);

                    g.fill3DRect(x, y + 75, 150, 25, false);

                    g.fill3DRect(x + 25, y + 25, 100, 50, false);

                    g.fillOval(x + 50, y + 25, 50, 50);

                    g.drawLine(x + 75, y + 50, x, y + 50);

                    break;

            }

        }





        @Override

        public void keyTyped(KeyEvent e) {



        }



        //键按下处理，用wsad

        public void keyPressed(KeyEvent e) {

            //设置我的坦克的方向

            if (e.getKeyCode() == KeyEvent.VK_W) {

                this.hr.setDirect(0);

                this.hr.moveUp();

            } else if (e.getKeyCode() == KeyEvent.VK_D) {

                this.hr.setDirect(1);

                this.hr.moveRight();

            } else if (e.getKeyCode() == KeyEvent.VK_S) {

                this.hr.setDirect(2);

                this.hr.moveDown();

            } else if (e.getKeyCode() == KeyEvent.VK_A) {

                this.hr.setDirect(3);

                this.hr.moveLeft();

            }

            //判断是否按下发射

            if (e.getKeyCode() == KeyEvent.VK_J) {

                if (this.hr.ss.size() < 5) {

                    this.hr.shotEnemy();

                }

            }

            //必须重新绘制panel

            this.repaint();



        }



        @Override

        public void keyReleased(KeyEvent e) {



        }



        //两个判断子弹是否击中坦克的函数

        public void hitEnemyTank() {

            //判断是否击中敌人的坦克,两层循环，匹配每个子弹和坦克

            for (int i = 0; i < hr.ss.size(); i++) {

                Shot myshot = hr.ss.get(i);

                if (myshot.isLive) {

                    for (int j = 0; j < ets.size(); j++) {

                        EnemyTank et = ets.get(j);

                        if (et.isLive) {

                            this.hitTank(myshot, et);

                        }

                    }

                }

            }

        }



        public void hitMe() {

            //取出每一个敌人的坦克

            for (int i = 0; i < this.ets.size(); i++) {

                //取出敌人坦克

                EnemyTank et = ets.get(i);

                //取出每一颗子弹

                for (int j = 0; j < et.ss.size(); j++) {

                    //取出子弹

                    Shot enemyShot = et.ss.get(j);

                    this.hitTank(enemyShot, hr);

                }



            }

        }





        @Override

        public void run() {

            //每100ms重绘panel

            for (; ; ) {

                try {

                    Thread.sleep(1);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

//                //判断是否击中敌人的坦克,两层循环，匹配每个子弹和坦克

//                for (int i = 0; i < hr.ss.size(); i++) {

//                    Shot myshot = hr.ss.get(i);

//                    if (myshot.isLive) {

//                        for (int j = 0; j < ets.size(); j++) {

//                            EnemyTank et = ets.get(j);

//                            if (et.isLive) {

//                                this.hitTank(myshot, et);

//                            }

//                        }

//                    }

//                }





//               //判断是否需要给坦克加入新的子弹

//                for (int i = 0; i <ets.size() ; i++) {

//                    EnemyTank et=ets.get(i);

//                    if(et.isLive)

//                    {

//                        Shot s=null;

//                        if(et.ss.size()<1)

//                        {

//                            //说明没有子弹了

//                            //添加

//                            switch (et.direct) {

//                                case 0:

//                                    s = new Shot(et.x + 100, et.y, 0);//创建一颗子弹

//                                    break;

//                                case 1:

//                                    s = new Shot(et.x + 300, et.y + 100, 1);

//                                    break;

//                                case 2:

//                                    s = new Shot(et.x + 100, et.y + 300, 2);

//                                    break;

//                                case 3:

//                                    s = new Shot(et.x, et.y + 100, 3);

//                                    break;

//                            }

//                            et.ss.add(s);

//                            //启动子弹线程

//                            Thread t = new Thread(s);

//                            t.start();

//                        }

//                    }

//

//                }

                this.hitEnemyTank();

                this.hitMe();

                this.repaint();

            }

        }

    }

}



class Bomb {

    int x, y;

    int life = 9;//炸弹的生命

    boolean isLive = true;



    public Bomb(int x, int y) {

        this.x = x;

        this.y = y;

    }



    public void lifeDown() {

        if (life > 0) {

            life--;

        } else {

            this.isLive = false;

        }

    }



}



class Tank {

    int x = 0;

    int y = 0;

    int speed = 5;//坦克的速度

    int direct = 0;//坦克方向0123--上右下左

    int color = 0;

    boolean isLive = true;



    public int getColor() {

        return color;

    }



    public void setColor(int color) {

        this.color = color;

    }



    public int getSpeed() {

        return speed;

    }



    public void setSpeed(int speed) {

        this.speed = speed;

    }



    public int getDirect() {

        return direct;

    }



    public void setDirect(int direct) {

        this.direct = direct;

    }



    public void setX(int x) {

        this.x = x;

    }



    public void setY(int y) {

        this.y = y;

    }



    public int getX() {

        return x;

    }



    public int getY() {

        return y;

    }



    public Tank(int x, int y) {

        this.x = x;

        this.y = y;



    }

}



class Hero extends Tank {



    //Shot s;

    Vector<Shot> ss = new Vector<Shot>();

    Shot s = null;





    public Hero(int x, int y) {

        super(x, y);



    }



    //坦克开火

    public void shotEnemy() {



        switch (this.direct) {

            case 0:

                s = new Shot(x + 50, y, 0);//创建一颗子弹

                break;

            case 1:

                s = new Shot(x + 150, y + 50, 1);

                break;

            case 2:

                s = new Shot(x + 50, y + 150, 2);

                break;

            case 3:

                s = new Shot(x, y + 50, 3);

                break;

        }

        ss.add(s);//把子弹加入到向量

        Thread t = new Thread(s);//启动子弹

        t.start();

    }





    //坦克移动

    public void moveUp() {

        y -= this.speed;

    }



    public void moveRight() {

        x += this.speed;

    }



    public void moveDown() {

        y += this.speed;

    }



    public void moveLeft() {

        x -= this.speed;

    }

}



class EnemyTank extends Tank implements Runnable {

    int times = 0;

    //定义一个向量，存放敌人的子弹

    Vector<Shot> ss = new Vector<Shot>();

    //敌人添加子弹，在刚刚创建敌人坦克和其子弹死亡之后创建坦克



    public EnemyTank(int x, int y) {

        super(x, y);

    }



    @Override

    public void run() {

        for (; ; ) {

            switch (this.direct) {

                case 0:

                    //说明向上运动

                    for (int i = 0; i < 10; i++) {

                        if (y > 0) {

                            y -= (speed / 2);

                        }

                    }

                    try {

                        Thread.sleep(50);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                    break;

                case 1:

                    for (int i = 0; i < 10; i++) {

                        if (x < 2000) {

                            x += (speed / 2);

                        }

                    }

                    try {

                        Thread.sleep(50);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                    break;

                case 2:

                    for (int i = 0; i < 10; i++) {

                        if (y < 2000) {

                            y += (speed / 2);

                        }

                    }

                    try {

                        Thread.sleep(50);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                    break;

                case 3:

                    for (int i = 0; i < 10; i++) {

                        if (x > 0) {

                            x -= (speed / 2);

                        }

                    }

                    try {

                        Thread.sleep(50);

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                    break;

            }

            this.times++;

            if (times % 2 == 0) {

                if (isLive) {

                    if (ss.size() < 5) {

                        Shot s = null;

                        switch (direct) {

                            case 0:

                                s = new Shot(x + 50, y, 0);//创建一颗子弹

                                break;

                            case 1:

                                s = new Shot(x + 150, y + 50, 1);

                                break;

                            case 2:

                                s = new Shot(x + 50, y + 150, 2);

                                break;

                            case 3:

                                s = new Shot(x, y + 50, 3);

                                break;

                        }

                        ss.add(s);

                        //启动子弹线程

                        Thread t = new Thread(s);

                        t.start();



                    }

                }

            }





            //让坦克随机换向

            this.direct = (int) (Math.random() * 4);

            //判断是不是死亡了

            if (!this.isLive) {

                //让坦克死亡之后退出线程

                break;

            }





        }

    }

}



class Shot implements Runnable {

    int x, y, direct, speed = 3;

    boolean isLive = true;



    public Shot(int x, int y, int direct) {

        this.x = x;

        this.y = y;

        this.direct = direct;

        this.speed = speed;

    }



    @Override

    public void run() {

        for (; ; ) {

            try {

                Thread.sleep(50);

            } catch (InterruptedException e) {

                e.printStackTrace();



            }

            switch (direct) {

                case 0:

                    y -= speed;

                    break;

                case 1:

                    x += speed;

                    break;

                case 2:

                    y += speed;

                    break;

                case 3:

                    x -= speed;

                    break;

            }

            // System.out.println(" x= " + x + " y= " + y);



            //判短是否到边缘

            if (x < 0 || x > 2000 || y < 0 || y > 1500) {

                this.isLive = false;

                break;

            }

        }

    }

}