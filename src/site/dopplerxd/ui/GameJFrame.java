package site.dopplerxd.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

/**
 * @Author: Doppler
 * @Project: {puzzleGame}
 * @Package: {site.dopplerxd.ui}
 * @Date: {2024/3/25}
 * @Time: {15:00}
 */
public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    Random r = new Random();
    int[][] data = new int[4][4];
    int[][] win = new int[][] {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 0}
    };
    int x, y;
    int step = 0;
    // 创建选项下面的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏（R）");
    JMenuItem reloginItem = new JMenuItem("重新登录（L）");
    JMenuItem closeItem = new JMenuItem("关闭游戏（C）");
    JMenuItem authorItem = new JMenuItem("作者介绍（D）");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem sport = new JMenuItem("运动");
    String path = "./image/";
    String nowPath;
    public GameJFrame() {
        nowPath = "./image/animal/animal3/";

        // 初始化窗口
        initJFrame();

        // 初始化菜单
        initJMenuBar();

        // 初始化随机位置
        initData();

        // 初始化图片
        initImage();

        // login
        new LoginJFrame();


        this.setVisible(true);
    }

    // 初始化随机图片位置
    private void initData() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = arr[i];
        }
    }
    // 初始化图片
    private void initImage() {
        // 清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        // 判断胜利
        if(victory()) {
            JLabel winJLabel = new JLabel(new ImageIcon("./image/win.png"));
            winJLabel.setBounds(203, 203, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                int num = data[i][j];
                // 创建一个JLabel的对象（管理容器）
                JLabel jLabel = new JLabel(new ImageIcon(nowPath + num + ".jpg"));
                // 指定图片大小和位置
                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                // 图片边框 type为0(RAISED)表示图片凸起，1(LOWERED)表示凹陷
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                // 把管理容器添加到窗口中
                this.getContentPane().add(jLabel);
            }
        }

        // 先加载的图片在上方，背景图片要最后设置
        JLabel background = new JLabel(new ImageIcon("./image/background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        // 刷新界面
        this.getContentPane().repaint();
    }

    // 初始化菜单
    private void initJMenuBar() {
        // 创建菜单
        JMenuBar jMenuBar = new JMenuBar();

        // 创建菜单中的功能
        JMenu functionMenu = new JMenu("功能");
        JMenu aboutMenu = new JMenu("关于作者");
        JMenu changeImage = new JMenu("更换图片");

        // 组合菜单功能
        functionMenu.add(replayItem);
        functionMenu.add(reloginItem);
        functionMenu.add(closeItem);

        aboutMenu.add(authorItem);

        changeImage.add(animal);
        changeImage.add(girl);
        changeImage.add(sport);

        // 给条目绑定事件
        replayItem.addActionListener(this);
        reloginItem.addActionListener(this);
        closeItem.addActionListener(this);
        authorItem.addActionListener(this);
        animal.addActionListener(this);
        girl.addActionListener(this);
        sport.addActionListener(this);

        // 添加功能到菜单
        jMenuBar.add(functionMenu);
        jMenuBar.add(aboutMenu);
        functionMenu.add(changeImage);

        // 给界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    // 初始化窗口
    private void initJFrame() {
        // 设置窗口
        this.setSize(603, 680);
        this.setTitle("拼图游戏 v1.0");
        // 设置界面置顶
        this.setAlwaysOnTop(true);
        // 设置界面居中
        this.setLocationRelativeTo(null);
        // 设置关闭模式, 关闭窗口后程序停止
        // this.setDefaultCloseOperation(3); // 与下一行相同
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 取消默认的居中放置模式
        this.setLayout(null);
        // 给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65) { // 按下a时显示完整图片
            System.out.println("显示完整图片");
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(nowPath + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            // 添加背景图片
            JLabel background = new JLabel(new ImageIcon("./image/background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            // 刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()) {
            return;
        }
        // 左上右下分别是：37.38.39.40
        int code = e.getKeyCode();
        if(code == 37) {
                System.out.println("向左移动");
                if(y + 1 >= 4) {
                    return;
                }
                step++;
                // 空白右侧的图片左移
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;
                initImage();
            }
        else if(code == 38) {
                System.out.println("向上移动");
                if(x + 1 >= 4) {
                    return;
                }
                step++;
                // 空白右侧的图片左移
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;
                initImage();
            }
        else if(code == 39) {
                System.out.println("向右移动");
                if(y - 1 < 0) {
                    return;
                }
                step++;
                // 空白右侧的图片左移
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;
                initImage();
            }
        else if(code == 40) {
                System.out.println("向下移动");
                if(x - 1 < 0) {
                    return;
                }
                step++;
                // 空白右侧的图片左移
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;
                initImage();
            }
        else if(code == 65) {
            initImage();
        }
            else if(code == 87) {
                data = new int[][] {
                        {1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
                initImage();
            }
        else if(code == 67) {
            closeApp();
        }
            else if(code == 68) {
            author();
        } else if(code == 76) {
            relogin();
        }
        else if(code == 82) {
            replay();
        }
    }

    public boolean victory() {
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj == replayItem) {
            replay();
        }
        else if(obj == reloginItem) {
            relogin();
        }
        else if(obj == closeItem) {
            closeApp();
        }
        else if(obj == authorItem) {
            author();
        }
        else if(obj == animal) {
            System.out.println("更换动物图片");
            int index = r.nextInt(8) + 1;
            nowPath = path + "animal/animal" + index + "/";
            replay();
        }
        else if(obj == girl) {
            System.out.println("更换美女图片");
            int index = r.nextInt(13) + 1;
            nowPath = path + "girl/girl" + index + "/";
            replay();
        }
        else if(obj == sport) {
            System.out.println("更换运动图片");
            int index = r.nextInt(10) + 1;
            nowPath = path + "sport/sport" + index + "/";
            replay();
        }
    }
    private void replay() {
        System.out.println("重新游戏");
        step = 0;
        initData();
        initImage();
    }
    private void author() {
        System.out.println("关于作者");
        JDialog jDialog = new JDialog();
        JLabel jLabel = new JLabel(new ImageIcon("./image/about.png"));
        jLabel.setBounds(0, 0, 258, 258);
        jDialog.add(jLabel);
        jDialog.setSize(344, 344);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);
        jDialog.setVisible(true);
    }
    private void relogin() {
        System.out.println("重新登录");
        dispose();
        LoginJFrame.getInstance().setVisible(true);
    }
    private void closeApp() {
        System.out.println("关闭游戏");
        System.exit(0);
    }
}
