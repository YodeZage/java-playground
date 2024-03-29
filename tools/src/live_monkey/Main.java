package live_monkey;

import java.awt.Robot;
import java.awt.MouseInfo;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.security.SecureRandom;

public class Main {

    private static final int SLEEP_SECONDS = 540;
    private static int totalScreenWidth = 0;
    private static boolean isLastActionAdd = false;

    public static void main (String[] args){
        SecureRandom random = new SecureRandom();
        getMonitorsTotalWidth();

        try {
            Robot robot = new Robot();
            int currentMouseX;
            int currentMouseY;

            System.out.println(totalScreenWidth);

            while (true) {
                int randomSleepTime = random.nextInt(SLEEP_SECONDS * 1000);
                currentMouseX = (int) MouseInfo.getPointerInfo().getLocation().getX();
                currentMouseY = (int) MouseInfo.getPointerInfo().getLocation().getY();

                if (currentMouseX >= totalScreenWidth) {
                    robot.mouseMove(currentMouseX - 1, currentMouseY);
                    isLastActionAdd = false;
                } else if (currentMouseX <= 0) {
                    robot.mouseMove(currentMouseX + 1, currentMouseY);
                    isLastActionAdd = true;
                } else if (isLastActionAdd) {
                    robot.mouseMove(currentMouseX - 1, currentMouseY);
                    isLastActionAdd = false;
                } else {
                    robot.mouseMove(currentMouseX + 1, currentMouseY);
                    isLastActionAdd = true;
                }

                Thread.sleep(randomSleepTime);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex);
        }
    }

    public static void getMonitorsTotalWidth() {
        GraphicsDevice gdList [] = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();

        for (int i = 0; i < gdList.length; i++) {
            GraphicsDevice tempGD = gdList[i];
            int currentScreenWidth = tempGD.getDisplayMode().getWidth();

            totalScreenWidth = totalScreenWidth + currentScreenWidth;
        }
    }
}
