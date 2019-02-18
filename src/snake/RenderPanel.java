package snake;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RenderPanel extends JPanel {

    public static Color green = new Color(1263391);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(green);
        g.fillRect(0, 0, 960, 540);
        Snake snake = Snake.snake;

        g.setColor(Color.WHITE);
        for(Point point : snake.snakeParts)
        {
            g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE,
                    Snake.SCALE, Snake.SCALE);
        }
        g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);
        g.setColor(Color.YELLOW);
        g.fillRect(snake.cherry.x * Snake.SCALE, snake.cherry.y * Snake.SCALE,
                Snake.SCALE, Snake.SCALE);
        String string = "Score: " + snake.score + ", Length: " + snake.tailLength + ", Time: " + snake.ticks / 20;
        g.setColor(Color.WHITE);
        g.drawString(string,
                2,
                11);

    }
}
