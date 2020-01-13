/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_register_design;

/**
 *
 * @author Vlade
 */
class MyClock {
    
  private long startTime = 0;
  private long stopTime = 0;
  private boolean running = false;


  public void start() {
    this.startTime = System.nanoTime();
    this.running = true;
  }


  public void stop() {
    this.stopTime = System.nanoTime();
    this.running = false;
  }


  //elaspsed time in milliseconds
  public long getElapsedTime() {
    long elapsed;
    if (running) {
      elapsed = (System.nanoTime() - startTime);
    } else {
      elapsed = (stopTime - startTime);
    }
    return elapsed;
  }


  //elaspsed time in seconds
  public long getElapsedTimeSecs() {
    long elapsed;
    if (running) {
      elapsed = ((System.nanoTime() - startTime) / 1000000);
    } else {
      elapsed = ((stopTime - startTime) / 1000000);
    }
    return elapsed;
  }
}
