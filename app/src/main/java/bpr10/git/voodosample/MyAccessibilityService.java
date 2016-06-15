package bpr10.git.voodosample;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class MyAccessibilityService extends AccessibilityService {
  private static final String LOG_TAG = MyAccessibilityService.class.getSimpleName();

  public MyAccessibilityService() {
  }

  @Override
  protected void onServiceConnected() {
    AccessibilityServiceInfo info = getServiceInfo();
    info.packageNames = new String[]
        {
            "com.flipkart.android", "com.myntra.android", "com.whatsapp", "com.facebook.orca",
            "com.msf.kbank.mobile", "bpr10" +
            ".git.voodosample", "com.facebook.katana"
        };
    info.eventTypes = AccessibilityEvent.TYPE_VIEW_FOCUSED | AccessibilityEvent.TYPE_VIEW_CLICKED |
        AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED |
        AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED |
        AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED |
        AccessibilityEvent.TYPE_TOUCH_INTERACTION_START;

    this.setServiceInfo(info);
  }

  @Override
  public void onAccessibilityEvent(AccessibilityEvent event) {
    final int eventType = event.getEventType();
    String eventText = null;
    switch (eventType) {
      case AccessibilityEvent.TYPE_VIEW_CLICKED:
        eventText = "Clicked: ";
        break;
      case AccessibilityEvent.TYPE_VIEW_FOCUSED:
        eventText = "Focused: ";
        break;
      case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED: {
        eventText = " Text Changed ";
      }
      case AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED: {
        eventText = "AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED";
      }
    }

    eventText = eventText + " CD: " + event.getContentDescription() + " ";

    AccessibilityNodeInfo source = event.getSource();
    if (source == null) {
      return;
    }

    printAllText(source);

  }

  private void printAllText(AccessibilityNodeInfo source) {
    if (source == null) {
      return;
    }
    if (source.getClassName().equals("android.widget.TextView") || source.getClassName().equals
        ("android.widget.EditText")) {
      Log.d(LOG_TAG, "Node info instance:" + source.getClassName() + " id:" + source
          .getViewIdResourceName() + " text:" + source.getText() + " child:" +
          source.getChildCount()
          + "\n");
    }
    for (int i = 0; i < source.getChildCount(); i++) {
      AccessibilityNodeInfo child = source.getChild(i);
      if (child != null) {
        printAllText(child);
        child.recycle();
      }
    }
  }

  @Override
  public void onInterrupt() {

  }
}
