package site.dopplerxd.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @Author: Doppler
 * @Project: {puzzleGame}
 * @Package: {site.dopplerxd.ui}
 * @Date: {2024/3/25}
 * @Time: {15:04}
 */
public class RegisterJFrame extends JFrame implements MouseListener {
    static String path = "./image/register/";
    JTextField usernameInput = new JTextField();
    JPasswordField pwd1 = new JPasswordField();
    JPasswordField pwd2 = new JPasswordField();
    JTextField vCodeInput = new JTextField();
    JButton login = new JButton();
    JButton register = new JButton();
    JButton reload = new JButton();
    JButton rightCode = new JButton();
    String codeStr;

    public RegisterJFrame() {
        initJFrame();
        initView();
        this.setVisible(true);
    }

    private void initJFrame() {
        this.setSize(485, 428);
        this.setTitle("拼图游戏 v1.0 注册");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(null);
        login.addMouseListener(this);
        register.addMouseListener(this);
        reload.addMouseListener(this);
        rightCode.addMouseListener(this);
    }

    private void initView() {
        this.getContentPane().removeAll();
        // 用户名图片
        JLabel userName = new JLabel(new ImageIcon(path + "注册用户名.png"));
        userName.setBounds(68, 120, 79, 17);
        this.getContentPane().add(userName);

        // 用户名输入框
        usernameInput.setBounds(180, 112, 200, 33);
        this.getContentPane().add(usernameInput);

        // 密码图片
        JLabel password = new JLabel(new ImageIcon(path + "注册密码.png"));
        password.setBounds(68, 160, 64, 16);
        this.getContentPane().add(password);

        // 密码输入框
        pwd1.setBounds(180, 152, 200, 33);
        this.getContentPane().add(pwd1);

        // 确认密码图片
        JLabel confirmPwd = new JLabel(new ImageIcon(path + "再次输入密码.png"));
        confirmPwd.setBounds(68, 200, 96, 17);
        this.getContentPane().add(confirmPwd);

        // 确认密码
        pwd2.setBounds(180, 192, 200, 33);
        this.getContentPane().add(pwd2);

        // 验证码图片
        JLabel vCode = new JLabel(new ImageIcon(path + "验证码.png"));
        vCode.setBounds(68, 240, 56, 21);
        this.getContentPane().add(vCode);

        // 验证码输入框
        vCodeInput.setBounds(180, 232, 100, 33);
        this.getContentPane().add(vCodeInput);

        // 正确验证码
        updateVcode();
        rightCode.setBounds(290, 232, 90, 30);
        rightCode.setBackground(Color.white);
        this.add(rightCode);

        // 登录按钮
        login.setBounds(36, 290, 128, 47);
        login.setIcon(new ImageIcon(path + "登录按钮.png"));
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        // 注册按钮
        register.setBounds(179, 290, 128, 47);
        register.setIcon(new ImageIcon(path + "注册按钮.png"));
        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        this.getContentPane().add(register);

        // 重置按钮
        reload.setBounds(322, 290, 128, 47);
        reload.setIcon(new ImageIcon(path + "重置按钮.png"));
        reload.setBorderPainted(false);
        reload.setContentAreaFilled(false);
        this.getContentPane().add(reload);

        JLabel background = new JLabel(new ImageIcon(path + "background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    public void register() {
        String name = usernameInput.getText();
        String p1 = new String(pwd1.getPassword());
        String p2 = new String(pwd2.getPassword());
        if(name.isEmpty()) {
            Dialog.showDialog("请输入用户名！");
            return;
        }
        for (User user : LoginJFrame.list) {
            if(user.getName().equals(name)) {
                Dialog.showDialog("该用户已存在！");
                return;
            }
        }
        if(p1.isEmpty()) {
            Dialog.showDialog("请输入密码！");
            return;
        }
        if(p2.isEmpty()) {
            Dialog.showDialog("请确认密码！");
            return;
        }
        if(p1.equals(p2)) {
            if(checkVcode()) {
                LoginJFrame.list.add(new User(name, p1));
                System.out.println("register success!");
                dispose();
                Dialog.showDialog("注册成功！");
                new GameJFrame();
            }
            else {
                Dialog.showDialog("验证码错误！");
            }
        }
        else {
            Dialog.showDialog("两次输入的密码不一致！");
        }
    }

    public void reload() {
        usernameInput.setText("");
        pwd1.setText("");
        pwd2.setText("");
        updateVcode();
    }

    public void updateVcode() {
        vCodeInput.setText("");
        codeStr = Codeutil.getCode();
        rightCode.setText(codeStr);
    }

    private boolean checkVcode() {
        return codeStr.equalsIgnoreCase(vCodeInput.getText());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int op = getOp(e);
        if(op == 0) {
            dispose();
            LoginJFrame.getInstance().setVisible(true);
        } else if (op == 1) {
            register();
        } else if (op == 2) {
            reload();
        } else if(op == 3) {
            updateVcode();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int op = getOp(e);
        if(op == 1) {
            register.setIcon(new ImageIcon(path + "注册按下.png"));
        } else if (op == 2) {
            reload.setIcon(new ImageIcon(path + "重置按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int op = getOp(e);
        if(op == 1) {
            register.setIcon(new ImageIcon(path + "注册按钮.png"));
        } else if (op == 2) {
            reload.setIcon(new ImageIcon(path + "重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getOp(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login) {
            return 0;
        }
        else if(obj == register) {
            return 1;
        }
        else if(obj == reload) {
            return 2;
        }
        else if(obj == rightCode) {
            return 3;
        }
        return 4;
    }
}
