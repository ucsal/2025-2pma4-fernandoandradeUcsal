
package br.com.mariojp.figureeditor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

class DrawingPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final List<Shape> shapes = new ArrayList<>();
    private Point startDrag = null;
    private static final int FIGURE_SIZE = 50; // Tamanho padrão da figura

    public DrawingPanel() {
        setBackground(Color.WHITE);

        MouseAdapter mouseListener = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                Point clickPoint = e.getPoint();

                double x = clickPoint.x - FIGURE_SIZE / 2.0;
                double y = clickPoint.y - FIGURE_SIZE / 2.0;

                Shape newFigure = new Rectangle2D.Double(x, y, FIGURE_SIZE, FIGURE_SIZE);

                shapes.add(newFigure);

                repaint();
            }
        };

        addMouseListener(mouseListener);
    }

    void clear() {
        shapes.clear();
        repaint();
    }

    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Shape s : shapes) {
            g2.setColor(new Color(30,144,255));
            g2.fill(s);
            g2.setColor(new Color(0,0,0,70));
            g2.setStroke(new BasicStroke(1.2f));
            g2.draw(s);
        }

        g2.dispose();
    }

}
