import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;


/**
 * �������
 * 
 * @author laixl
 * 
 */

public class MapPanel extends JPanel implements MouseListener {
    Chess[][] arr = new Chess[Param.rows + 2][Param.cols + 2];
    // ������
    Stroke stroke = new BasicStroke(3.0f);

    // ���������� ��ŵ���������������
    Point firstPoint;
    Point secondPoint;

    public MapPanel() {
        initArr();

        this.addMouseListener(this);
    }

    public void initArr() {

        Random random = new Random();
        for (int i = 1; i <= 20; i++) {
            int count = 0;
            while (count < 4) {
                int x = random.nextInt(8) + 1;
                int y = random.nextInt(10) + 1;
                if (arr[x][y] == null) {
                    arr[x][y] = new Chess(i);
                    count++;
                }
            }
        }

        // ��ΧһȦ �ֶ���ʼ��
        // ������һ����������һ��
        for (int i = 0; i < arr[0].length; i++) {
            arr[0][i] = new Chess(0);
            arr[arr.length - 1][i] = new Chess(0);
        }

        // �����һ�������ұ�һ��
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = new Chess(0);

            arr[i][arr[0].length - 1] = new Chess(0);
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(Param.imageBackground, 0, 0, this);// ���ñ���ͼƬ

        

        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j < arr[i].length; j++) {
                if (arr[i][j].getStatus() != 0) {
                    int x = j * Param.chessWidth + Param.marginWidth;
                    int y = i * Param.chessHeight + Param.marginHeight;
                    g.drawImage(Param.chessImage[arr[i][j].getStatus() - 1], x,
                            y, this);
                    g.setColor(Color.green);
                    g.drawRect(x, y, Param.chessWidth, Param.chessHeight);
                }
            }
        }
        // �жϵ�һ����firstPoint��Ϊnull
        if (firstPoint != null) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(stroke);
            g2d.setColor(Color.blue);
            int rowX = firstPoint.y * Param.chessWidth + Param.marginWidth;

            int rowY = firstPoint.x * Param.chessHeight + Param.marginHeight;

            g2d.drawRect(rowX + 2, rowY + 2, Param.chessWidth - 4,
                    Param.chessHeight - 4);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getModifiers() != InputEvent.BUTTON1_MASK) {
            return;
        }

        int x = e.getX();
        int y = e.getY();

        int X = (x - Param.marginWidth) / Param.chessWidth;
        int rowX = X * Param.chessWidth + Param.marginWidth;
        int Y = (y - Param.marginHeight) / Param.chessHeight;
        int rowY = Y * Param.chessHeight + Param.marginHeight;

        Graphics g = getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(stroke);
        g2d.setColor(Color.blue);
        if ((x >= Param.marginWidth + Param.chessWidth && x <= Param.marginWidth
                + Param.cols * Param.chessWidth + Param.chessWidth)
                && (y >= Param.marginHeight + Param.chessHeight && y <= Param.marginHeight
                        + Param.rows * Param.chessHeight + Param.chessHeight)) {

            // ��һ�ε����firstPoint==null||�ظ�����˵�һ����
            if (firstPoint == null || (firstPoint.x == Y && firstPoint.y == X)) {
                firstPoint = new Point(Y, X);
                g2d.setColor(Color.blue);
                g2d.drawRect(rowX + 2, rowY + 2, Param.chessWidth - 4,
                        Param.chessHeight - 4);
                return;
            }
            // �ڶ���
            secondPoint = new Point(Y, X);
            g2d.setColor(Color.red);
            g2d.drawRect(rowX + 2, rowY + 2, Param.chessWidth - 4,
                    Param.chessHeight - 4);
            // �ж��������ͼ���Ƿ�һ��
            if (arr[firstPoint.x][firstPoint.y].getStatus() != arr[secondPoint.x][secondPoint.y]
                    .getStatus()) {
                firstPoint = secondPoint;

                repaint();
                return;
            }

            // ͼ���״ֵ̬��ͬ ��ͨ�㷨
            List<Point> list = Core.checkLinked(arr, firstPoint, secondPoint);
            if (list == null) {
                firstPoint = secondPoint;

                repaint();
                return;
            }

            // ������ͨ������ͼ���״ֵ̬Ϊ0���������null�����������ߣ����»��ƽ���
            arr[firstPoint.x][firstPoint.y].setStatus(0);
            arr[secondPoint.x][secondPoint.y].setStatus(0);
            firstPoint = null;
            secondPoint = null;

            // ����������

            drawLinkedLine(list, g2d);

            repaint();
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void drawLinkedLine(List<Point> list, Graphics2D g2d) {
        // TODO Auto-generated method stub
        if (list.size() == 2) {
            Point a = list.get(0);
            Point b = list.get(1);
            int ax = a.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int ay = a.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;
            int bx = b.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int by = b.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;
            g2d.drawLine(ax, ay, bx, by);

        }

        if (list.size() == 3) {
            Point a = list.get(0);
            Point c = list.get(1);
            Point b = list.get(2);

            int ax = a.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int ay = a.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;

            int cx = c.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int cy = c.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;
            int bx = b.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int by = b.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;
            g2d.drawLine(ax, ay, cx, cy);
            g2d.drawLine(cx, cy, bx, by);
        }

        if (list.size() == 4) {
            Point a = list.get(0);
            Point c = list.get(1);
            Point d = list.get(2);
            Point b = list.get(3);
            int ax = a.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int ay = a.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;

            int cx = c.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int cy = c.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;
            int dx = d.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int dy = d.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;
            int bx = b.y * Param.chessWidth + Param.marginWidth
                    + Param.chessWidth / 2;
            int by = b.x * Param.chessHeight + Param.marginHeight
                    + Param.chessHeight / 2;
            g2d.drawLine(ax, ay, cx, cy);
            g2d.drawLine(cx, cy, dx, dy);
            g2d.drawLine(dx, dy, bx, by);
        }

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}