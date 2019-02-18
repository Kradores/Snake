package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Snake implements ActionListener, KeyListener {

    public static Snake snake;

    public JFrame jFrame;

    public  RenderPanel renderPanel = new RenderPanel();

    public Timer timer = new Timer(20, this);

    public ArrayList<Point> snakeParts = new ArrayList<>();

    public int ticks = 0, score, tailLength = 10;

    public int direction = DOWN;

    public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 5;

    public Point head, cherry;

    public Random random;

    public Dimension dim;

    public boolean over = false, paused;

    public Snake()
    {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        jFrame = new JFrame("Snake");
        jFrame.setVisible(true);
//        jFrame.setSize(960, 540);
        jFrame.getContentPane().setPreferredSize(new Dimension(950, 530));
        jFrame.pack();
        jFrame.setResizable(false);
        jFrame.setLocation(dim.width / 2 - jFrame.getWidth() / 2, dim.height / 2 - jFrame.getHeight() / 2);
        jFrame.add(renderPanel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.addKeyListener(this);
//        System.out.println(dim.width + " x " + dim.height);
//        System.out.println(jFrame.getWidth() + " x " + jFrame.getHeight());
        startGame();

    }

    public void startGame()
    {
        over = false;
        score = 0;
        tailLength = 10;
        head = new Point(20, 20);
        random = new Random();
        snakeParts.clear();
        cherry = new Point( random.nextInt(dim.width / SCALE / 2),
                            random.nextInt(dim.height / SCALE / 2));
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        renderPanel.repaint();
        ticks++;

        if(ticks%2 == 0 && head != null && !over && !paused)
        {

            snakeParts.add(new Point(head.x, head.y));

            if(direction == UP)
            {
                if(head.y - 1 > -1  && noTailAt(head.x, head.y - 1))
                {
                    head = new Point(head.x, head.y - 1);
                } else
                {
                    over = true;
                }

            }
            if(direction == DOWN)
            {
                if(head.y + 1 < dim.height / SCALE / 2  && noTailAt(head.x, head.y + 1))
                {
                    head = new Point(head.x, head.y + 1);
                } else
                {
                    over = true;
                }
            }
            if(direction == LEFT)
            {
                if(head.x - 1 > -1  && noTailAt(head.x - 1, head.y))
                {
                    head = new Point(head.x - 1, head.y);
                } else
                {
                    over = true;
                }
            }
            if(direction == RIGHT)
            {
                if(head.x + 1 < dim.width / SCALE / 2 && noTailAt(head.x + 1, head.y))
                {
                    head = new Point(head.x + 1, head.y);
                } else
                {
                    over = true;
                }
            }

            if(snakeParts.size() > tailLength)
            {
                snakeParts.remove(0);
            }

            if(cherry != null)
            {

                if(head.equals(cherry))
                {
                    score+= 10;
                    tailLength++;
                    cherry.setLocation(random.nextInt(dim.width / SCALE / 2),
                            random.nextInt(dim.height / SCALE / 2));
                }
            }

//            if(ticks%20 == 0 || over)
//            {
//                System.out.println(head.x + ", " + head.y);
//            }

        }
    }

    private boolean noTailAt(int x, int y) {
        for(Point point : snakeParts)
        {
            if(point.equals(new Point(x, y)))
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        snake = new Snake();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_A && direction != RIGHT)
        {
            direction = LEFT;
        }
        if (i == KeyEvent.VK_D && direction != LEFT)
        {
            direction = RIGHT;
        }
        if (i == KeyEvent.VK_W && direction != DOWN)
        {
            direction = UP;
        }
        if (i == KeyEvent.VK_S && direction != UP)
        {
            direction = DOWN;
        }
        if(i == KeyEvent.VK_SPACE)
        {
            if(over)
            {
                startGame();
            } else
            {
                paused = !paused;
            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
