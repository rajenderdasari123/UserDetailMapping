package com.example.userdetailmappingtask;

import android.os.Build;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.robolectric.annotation.Config;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static org.junit.Assert.assertEquals;

@Config(sdk = Build.VERSION_CODES.O)
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
  private MainActivity mMainActivity;
  private ActivityScenario<MainActivity> mMainActivityScenario;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mMainActivityScenario = ActivityScenario.launch(MainActivity.class);
    mMainActivityScenario.moveToState(Lifecycle.State.RESUMED);
    mMainActivityScenario.onActivity(activity -> mMainActivity = activity);
  }

  @Test
  public void testInstance() {
    Assert.assertNotNull(mMainActivity);
  }

  @Test
  public void testDestroy() {
    mMainActivityScenario.moveToState(Lifecycle.State.DESTROYED);
    assertEquals(Lifecycle.State.DESTROYED, mMainActivity.getLifecycle().getCurrentState());
  }

  @After
  public void tearDown() {
    mMainActivity = null;
    mMainActivityScenario = null;
  }

}
