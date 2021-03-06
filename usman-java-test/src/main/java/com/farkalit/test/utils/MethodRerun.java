package com.farkalit.test.utils;

import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

public class MethodRerun {

  public static void main(String[] args) {
    new MethodRerun();
  }

  Toolkit toolkit;
  Timer timer;

  public MethodRerun() {
    toolkit = Toolkit.getDefaultToolkit();
    timer = new Timer();
    timer.schedule(new RemindTask(), 0, // initial delay
        1 * 1000); // subsequent rate
  }

  class RemindTask extends TimerTask {
    int numWarningBeeps = 3;

    @Override
    public void run() {
      if (numWarningBeeps > 0) {
        toolkit.beep();
        System.out.println("Beep!");
        numWarningBeeps--;
      } else {
        toolkit.beep();
        System.out.println("Time's up!");
        // timer.cancel(); // Not necessary because
        // we call System.exit
        System.exit(0); // Stops the AWT thread
                        // (and everything else)
      }
    }
  }
}
