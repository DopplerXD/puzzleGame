package site.dopplerxd.ui;

import javax.swing.*;

/**
 * @Author: Doppler
 * @Project: {puzzleGame}
 * @Package: {site.dopplerxd.ui}
 * @Date: {2024/3/26}
 * @Time: {19:52}
 */
public class Dialog {
    public static void showDialog(String content) {
        JDialog jDialog = new JDialog();
        jDialog.setSize(200, 150);
        jDialog.setAlwaysOnTop(true);
        jDialog.setLocationRelativeTo(null);
        jDialog.setModal(true);

        JLabel warning = new JLabel();
        warning.setBounds(0, 0, 200, 150);
        warning.setText(content);
        jDialog.getContentPane().add(warning);

        jDialog.setVisible(true);
    }
}
