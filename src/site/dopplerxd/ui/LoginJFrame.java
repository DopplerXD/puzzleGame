package site.dopplerxd.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @Author: Doppler
 * @Project: {puzzleGame}
 * @Package: {site.dopplerxd.ui}
 * @Date: {2024/3/25}
 * @Time: {15:03}
 */
public class LoginJFrame extends JFrame implements MouseListener {
    // 使用单例设计模式
    private static LoginJFrame instance;
    String path = "./image/login/";
    static ArrayList<User> list = new ArrayList<User>();
    static {
        list.add(new User("admin", "123456"));
    }
    public LoginJFrame() {
        initJFrame();
        initView();
    }

    JButton login = new JButton();
    JButton register = new JButton();
    JTextField usernameInput = new JTextField();
    JPasswordField passwordInput = new JPasswordField();
    JTextField vCodeInput = new JTextField();
    JButton rightCode = new JButton();
    String codeStr;

    private void initView() {
        // 用户名图片
        JLabel userName = new JLabel(new ImageIcon(path + "用户名.png"));
        userName.setBounds(100, 140, 47, 17);
        this.getContentPane().add(userName);

        // 用户名输入框
        usernameInput.setBounds(180, 132, 200, 33);
        this.getContentPane().add(usernameInput);

        // 密码图片
        JLabel password = new JLabel(new ImageIcon(path + "密码.png"));
        password.setBounds(100, 200, 32, 16);
        this.getContentPane().add(password);

        // 密码输入框
        passwordInput.setBounds(180, 192, 200, 33);
        this.getContentPane().add(passwordInput);

        // 验证码图片
        JLabel vCode = new JLabel(new ImageIcon(path + "验证码.png"));
        vCode.setBounds(100, 260, 56, 21);
        this.getContentPane().add(vCode);

        // 验证码输入框
        vCodeInput.setBounds(180, 252, 100, 33);
        this.getContentPane().add(vCodeInput);

        // 正确验证码
        updateVcode();
        rightCode.setBounds(290, 252, 90, 30);
        rightCode.setBackground(Color.white);
        this.add(rightCode);


        // 登录按钮
        login.setBounds(94, 310, 128, 47);
        login.setIcon(new ImageIcon(path + "登录按钮.png"));
        // 去除默认边框
        login.setBorderPainted(false);
        // 去除默认背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        // 注册按钮
        register.setBounds(262, 310, 128, 47);
        register.setIcon(new ImageIcon(path + "注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);


        // 设置背景
        JLabel background = new JLabel(new ImageIcon(path + "background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void initJFrame() {
        this.setSize(485, 428);
        this.setTitle("拼图游戏 v1.0 登录");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        // 需要给按钮添加监听器，给窗口没用
        login.addMouseListener(this);
        register.addMouseListener(this);
        rightCode.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int op = getOp(e);
        if(op == 1) {
            System.out.println("login");
            loginMethod();
        }
        else if(op == 2) {
            System.out.println("register");
            dispose();
            new RegisterJFrame();
        }
        else if(op == 3) {
            updateVcode();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int op = getOp(e);
        if(op == 1) {
            login.setIcon(new ImageIcon(path + "登录按下.png"));
        }
        else if(op == 2) {
            register.setIcon(new ImageIcon(path + "注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int op = getOp(e);
        if(op == 1) {
            login.setIcon(new ImageIcon(path + "登录按钮.png"));
        }
        else if(op == 2) {
            register.setIcon(new ImageIcon(path + "注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void loginMethod() {
        String name = usernameInput.getText();
        String pwd = new String(passwordInput.getPassword());
        if(name.isEmpty() || pwd.isEmpty()) {
            Dialog.showDialog("请输入用户名和密码！");
            return;
        }
        int index = findUser(name);
        if(index == -1) { // 用户不存在
            Dialog.showDialog("用户不存在！");
            updateVcode();
        }
        else {
            if(checkPassword(pwd, index)) {
                if(!checkVcode()) {
                    Dialog.showDialog("验证码错误！");
                    return;
                }
                System.out.println("login success!");
                dispose();
                new GameJFrame();
            }
            else {
                Dialog.showDialog("用户名或密码错误！");
                updateVcode();
            }
        }
    }

    private boolean checkVcode() {
        return codeStr.equalsIgnoreCase(vCodeInput.getText());
    }

    private int findUser(String user) {
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getName().equals(user)) {
                return i;
            }
        }
        return -1;
    }

    private boolean checkPassword(String pwd, int index) {
        return list.get(index).getPassword().equals(pwd);
    }

    public int getOp(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login) {
            return 1;
        }
        else if(obj == register) {
            return 2;
        }
        else if(obj == rightCode) {
            return 3;
        }
        return 4;
    }

    public void updateVcode() {
        vCodeInput.setText("");
        codeStr = Codeutil.getCode();
        rightCode.setText(codeStr);
    }

    // 单例设计模式，不会出现多余的登录窗口
    public static LoginJFrame getInstance() {
        if(instance == null) {
            return new LoginJFrame();
        }
        return instance;
    }
}
