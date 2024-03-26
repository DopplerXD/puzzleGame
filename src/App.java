import site.dopplerxd.ui.GameJFrame;
import site.dopplerxd.ui.LoginJFrame;
import site.dopplerxd.ui.RegisterJFrame;

/**
 * @Author: Doppler
 * @Project: {puzzleGame}
 * @Package: {PACKAGE_NAME}
 * @Date: {2024/3/25}
 * @Time: {15:05}
 */
public class App {
    public static void main(String[] args) {
        LoginJFrame loginJFrame = LoginJFrame.getInstance();
        loginJFrame.setVisible(true);
    }
}
