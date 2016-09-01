package bpr10.git.voodosample1;

/**
 * Created by bedprakash.rout on 8/20/2016.
 */

public class TextChangeEvent {

  String text;
  boolean shouldToast = true;

  public TextChangeEvent(String text) {
    this.text = text;
  }

  public TextChangeEvent(String text,boolean shouldToast) {
    this.text = text;
    this.shouldToast = shouldToast;
  }

}
