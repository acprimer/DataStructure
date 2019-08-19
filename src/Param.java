import java.awt.Image;

import javax.swing.ImageIcon;

public class Param {
    
    //��Ϸ��������������
    public static int rows=8;
    public static int cols=10;
    
    //����ͼ�� �����
    public static int chessWidth =55;
    public static int chessHeight=55;
    
    //���̵��߽�ľ���
    public static int marginWidth = 200-chessWidth;
    public static int marginHeight = 100-chessHeight;

    //��Ϸ�ı���ͼƬ
    public static Image imageBackground = new ImageIcon("Images/build/back.jpg").getImage();
    
    public static Image[] chessImage = new Image[20];
    static {
        for (int i = 0; i < chessImage.length; i++) {
            chessImage[i] = new ImageIcon("Images/build/" + (i + 1) + ".png").getImage();
        }
    }

}