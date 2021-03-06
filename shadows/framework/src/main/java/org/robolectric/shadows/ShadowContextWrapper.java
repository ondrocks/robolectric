package org.robolectric.shadows;

import static org.robolectric.Shadows.shadowOf;

import android.app.ActivityThread;
import android.content.ContextWrapper;
import android.content.Intent;
import java.util.List;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Implements;

@Implements(ContextWrapper.class)
public class ShadowContextWrapper {

  public List<Intent> getBroadcastIntents() {
    return ShadowApplication.getInstance().getBroadcastIntents();
  }

  /**
   * Consumes the most recent {@code Intent} started by {@link
   * #startActivity(android.content.Intent)} and returns it.
   *
   * @return the most recently started {@code Intent}
   */
  public Intent getNextStartedActivity() {
    ActivityThread activityThread = (ActivityThread) RuntimeEnvironment.getActivityThread();
    ShadowInstrumentation shadowInstrumentation = shadowOf(activityThread.getInstrumentation());
    return shadowInstrumentation.getNextStartedActivity();
  }

  /**
   * Returns the most recent {@code Intent} started by {@link
   * #startActivity(android.content.Intent)} without consuming it.
   *
   * @return the most recently started {@code Intent}
   */
  public Intent peekNextStartedActivity() {
    ActivityThread activityThread = (ActivityThread) RuntimeEnvironment.getActivityThread();
    ShadowInstrumentation shadowInstrumentation = shadowOf(activityThread.getInstrumentation());
    return shadowInstrumentation.peekNextStartedActivity();
  }

  /**
   * Delegates to the application to consume and return the next {@code Intent} on the
   * started services stack.
   *
   * @return the next started {@code Intent} for a service
   */
  public Intent getNextStartedService() {
    return ShadowApplication.getInstance().getNextStartedService();
  }

  /**
   * Delegates to the application to clear the stack of started service intents.
   */
  public void clearStartedServices() {
    ShadowApplication.getInstance().clearStartedServices();
  }

  /**
   * Return (without consuming) the next {@code Intent} on the started services stack.
   *
   * @return the next started {@code Intent} for a service
   */
  public Intent peekNextStartedService() {
    return ShadowApplication.getInstance().peekNextStartedService();
  }

  /**
   * Delegates to the application to return the next {@code Intent} to stop
   * a service (irrespective of if the service was running)
   *
   * @return {@code Intent} for the next service requested to be stopped
   */
  public Intent getNextStoppedService() {
    return ShadowApplication.getInstance().getNextStoppedService();
  }

  public void grantPermissions(String... permissionNames) {
    ShadowApplication.getInstance().grantPermissions(permissionNames);
  }

  public void denyPermissions(String... permissionNames) {
    ShadowApplication.getInstance().denyPermissions(permissionNames);
  }
}
