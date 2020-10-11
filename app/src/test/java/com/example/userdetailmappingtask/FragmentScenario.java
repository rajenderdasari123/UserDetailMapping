package com.example.userdetailmappingtask;


import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Preconditions;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentFactory;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.core.app.ActivityScenario;

import static androidx.core.util.Preconditions.checkNotNull;
import static androidx.core.util.Preconditions.checkState;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

/**
 * FragmentScenario provides API to start and drive a Fragment's lifecycle state for testing. It
 * works with arbitrary fragments and works consistently across different versions of the Android
 * framework.
 * <p>
 * FragmentScenario only supports {@link Fragment}. If you are using a
 * deprecated fragment class such as {@code android.support.v4.app.Fragment} or
 * {@link android.app.Fragment}, please update your code to {@link Fragment}.
 *
 * @param <F> The Fragment class being tested
 * @see ActivityScenario a scenario API for Activity
 */
public final class FragmentScenario<F extends Fragment, A extends AppCompatActivity> {

  private static final String FRAGMENT_TAG = "FragmentScenario_Fragment_Tag";
  @SuppressWarnings("WeakerAccess") /* synthetic access */
  final Class<F> mFragmentClass;
  private final ActivityScenario<AppCompatActivity> mActivityScenario;
  @Nullable
  private final FragmentFactory mFragmentFactory;


  /**
   * A view-model to hold a fragment factory.
   */
  public static class FragmentFactoryHolderViewModel extends ViewModel {

    @Nullable
    private FragmentFactory mFragmentFactory;

    void setFragmentFactory(@Nullable FragmentFactory factory) {
      mFragmentFactory = factory;
    }

    @Nullable
    FragmentFactory getFragmentFactory() {
      return mFragmentFactory;
    }

    @Override
    protected void onCleared() {
      super.onCleared();
      mFragmentFactory = null;
    }
  }

  private FragmentScenario(
      @NonNull Class<F> fragmentClass,
      @Nullable FragmentFactory fragmentFactory,
      @NonNull ActivityScenario<AppCompatActivity> activityScenario) {
    this.mFragmentClass = fragmentClass;
    this.mFragmentFactory = fragmentFactory;
    this.mActivityScenario = activityScenario;
  }

  /**
   * Launches a Fragment hosted by an empty {@link FragmentActivity} and waits for it to reach
   * the resumed state.
   *
   * @param fragmentClass a fragment class to instantiate
   */
  @NonNull
  public static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F, A> launch(
      @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass) {
    return launch(fragmentClass, /*fragmentArgs=*/ activityClass);
  }

  /**
   * Launches a Fragment with given arguments hosted by an empty {@link FragmentActivity} and
   * waits for it to reach the resumed state.
   * <p>
   * This method cannot be called from the main thread.
   *
   * @param fragmentClass a fragment class to instantiate
   * @param fragmentArgs  a bundle to passed into fragment
   */
  @NonNull
  public static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F, A> launch(
      @NonNull Class<F> fragmentClass,
      @NonNull Class<A> activityClass, @Nullable Bundle fragmentArgs) {
    return launch(fragmentClass, activityClass, fragmentArgs, /*factory=*/null);
  }

  /**
   * Launches a Fragment with given arguments hosted by an empty {@link FragmentActivity} using
   * the given {@link FragmentFactory} and waits for it to reach the resumed state.
   * <p>
   * This method cannot be called from the main thread.
   *
   * @param fragmentClass a fragment class to instantiate
   * @param fragmentArgs  a bundle to passed into fragment
   * @param factory       a fragment factory to use or null to use default factory
   */
  @NonNull
  public static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F, A> launch(
      @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass,
      @Nullable Bundle fragmentArgs,
      @Nullable FragmentFactory factory) {
    return launch(fragmentClass, activityClass, fragmentArgs,
        R.style.AppTheme, factory);
  }

  /**
   * Launches a Fragment with given arguments hosted by an empty {@link FragmentActivity} themed
   * by {@code themeResId}, using the given {@link FragmentFactory} and waits for it to reach the
   * resumed state.
   * <p>
   * This method cannot be called from the main thread.
   *
   * @param fragmentClass a fragment class to instantiate
   * @param fragmentArgs  a bundle to passed into fragment
   * @param themeResId    a style resource id to be set to the host activity's theme
   * @param factory       a fragment factory to use or null to use default factory
   */
  @NonNull
  public static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F, A> launch(
      @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass,
      @Nullable Bundle fragmentArgs,
      int themeResId, @Nullable FragmentFactory factory) {
    return internalLaunch(fragmentClass, activityClass, fragmentArgs, themeResId, factory,
        /*containerViewId=*/ 0);
  }

  /**
   * Launches a Fragment in the AppCompatActivity's root view container {@code android.R.id.content},
   * hosted by an empty {@link FragmentActivity} and waits for it to reach the resumed state.
   * <p>
   * This method cannot be called from the main thread.
   *
   * @param fragmentClass a fragment class to instantiate
   * @param activityClass a activity class to instantiate
   */
  @NonNull
  public static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F,A> launchInContainer(
      @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass) {
    return launchInContainer(fragmentClass, activityClass, /*fragmentArgs=*/ null);
  }

  /**
   * Launches a Fragment in the AppCompatActivity's root view container {@code android.R.id.content}, with
   * given arguments hosted by an empty {@link FragmentActivity} and waits for it to reach the
   * resumed state.
   * <p>
   * This method cannot be called from the main thread.
   *
   * @param fragmentClass a fragment class to instantiate
   * @param activityClass a activity class to instantiate
   * @param fragmentArgs  a bundle to passed into fragment
   */
  @NonNull
  public static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F, A> launchInContainer(
      @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass,
      @Nullable Bundle fragmentArgs) {
    return launchInContainer(fragmentClass, activityClass, fragmentArgs, /*factory=*/null);
  }

  /**
   * Launches a Fragment in the AppCompatActivity's root view container {@code android.R.id.content}, with
   * given arguments hosted by an empty {@link FragmentActivity} using the given
   * {@link FragmentFactory} and waits for it to reach the resumed state.
   * <p>
   * This method cannot be called from the main thread.
   *
   * @param fragmentClass a fragment class to instantiate
   * @param activityClass a activity class to instantiate
   * @param fragmentArgs  a bundle to passed into fragment
   * @param factory       a fragment factory to use or null to use default factory
   */
  @NonNull
  public static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F, A> launchInContainer(
      @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass, @Nullable Bundle fragmentArgs,
      @Nullable FragmentFactory factory) {
    return launchInContainer(
        fragmentClass, activityClass, fragmentArgs,
        R.style.AppTheme,
        factory);
  }

  /**
   * Launches a Fragment in the AppCompatActivity's root view container {@code android.R.id.content}, with
   * given arguments hosted by an empty {@link FragmentActivity} themed by {@code themeResId},
   * using the given {@link FragmentFactory} and waits for it to reach the resumed state.
   * <p>
   * This method cannot be called from the main thread.
   *
   * @param fragmentClass a fragment class to instantiate
   * @param activityClass a activity class to instantiate
   * @param fragmentArgs  a bundle to passed into fragment
   * @param themeResId    a style resource id to be set to the host activity's theme
   * @param factory       a fragment factory to use or null to use default factory
   */
  @NonNull
  public static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F, A> launchInContainer(
      @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass,
      @Nullable Bundle fragmentArgs,
      int themeResId, @Nullable FragmentFactory factory) {
    return internalLaunch(
        fragmentClass, activityClass, fragmentArgs, themeResId, factory,
        /*containerViewId=*/ android.R.id.content);
  }

  @NonNull
  @SuppressLint("RestrictedApi")
  private static <F extends Fragment, A extends AppCompatActivity> FragmentScenario<F, A> internalLaunch(
      @NonNull final Class<F> fragmentClass, @NonNull final Class<A> activityClass,
      final @Nullable Bundle fragmentArgs,
      final int themeResId, @Nullable final FragmentFactory factory,
      final int containerViewId) {
    Intent startActivityIntent =
        Intent.makeMainActivity(
            new ComponentName(getApplicationContext(),
                activityClass));
    FragmentScenario<F, A> scenario = new FragmentScenario<>(
        fragmentClass, factory,
        ActivityScenario.<AppCompatActivity>launch(startActivityIntent));
    scenario.mActivityScenario.onActivity(
        new ActivityScenario.ActivityAction<AppCompatActivity>() {
          @Override
          public void perform(AppCompatActivity activity) {
            if (factory != null) {
              ViewModelProvider viewModelProvider = new ViewModelProvider(
                  activity,
                  ViewModelProvider.AndroidViewModelFactory.getInstance(
                      activity.getApplication()));
              viewModelProvider
                  .get(FragmentFactoryHolderViewModel.class)
                  .setFragmentFactory(factory);
              activity.getSupportFragmentManager().setFragmentFactory(factory);
            }
            Fragment fragment = activity.getSupportFragmentManager()
                .getFragmentFactory().instantiate(
                    Preconditions.checkNotNull(fragmentClass.getClassLoader()),
                    fragmentClass.getName());
            fragment.setArguments(fragmentArgs);
            activity.getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment, FRAGMENT_TAG)
                .commitNow();
          }
        });
    return scenario;
  }

  /**
   * Moves Fragment state to a new state.
   * <p>
   * If a new state and current state are the same, this method does nothing. It accepts
   * {@link State#CREATED}, {@link State#STARTED}, {@link State#RESUMED}, and
   * {@link State#DESTROYED}.
   * <p>
   * {@link State#DESTROYED} is a terminal state. You cannot move to any other state
   * after the Fragment reaches that state.
   * <p>
   * This method cannot be called from the main thread.
   */
  @NonNull
  public FragmentScenario<F, A> moveToState(@NonNull State newState) {
    if (newState == State.DESTROYED) {
      mActivityScenario.onActivity(
          new ActivityScenario.ActivityAction<AppCompatActivity>() {
            @Override
            public void perform(AppCompatActivity activity) {
              Fragment fragment =
                  activity.getSupportFragmentManager().findFragmentByTag(
                      FRAGMENT_TAG);
              // Null means the fragment has been destroyed already.
              if (fragment != null) {
                activity
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .remove(fragment)
                    .commitNowAllowingStateLoss();
              }
            }
          });
    } else {
      mActivityScenario.onActivity(
          new ActivityScenario.ActivityAction<AppCompatActivity>() {
            @Override
            public void perform(AppCompatActivity activity) {
              Fragment fragment =
                  activity.getSupportFragmentManager().findFragmentByTag(
                      FRAGMENT_TAG);
              checkNotNull(fragment,
                  "The fragment has been removed from FragmentManager already.");
            }
          });
      mActivityScenario.moveToState(newState);
    }
    return this;
  }

  /**
   * Recreates the host Activity.
   * <p>
   * After this method call, it is ensured that the Fragment state goes back to the same state
   * as its previous state.
   * <p>
   * This method cannot be called from the main thread.
   */
  @NonNull
  public FragmentScenario<F, A> recreate() {
    mActivityScenario.recreate();
    return this;
  }

  /**
   * FragmentAction interface should be implemented by any class whose instances are intended to
   * be executed by the main thread. A Fragment that is instrumented by the FragmentScenario is
   * passed to {@link FragmentAction#perform} method.
   * <p>
   * You should never keep the Fragment reference as it will lead to unpredictable behaviour.
   * It should only be accessed in {@link FragmentAction#perform} scope.
   */
  public interface FragmentAction<F extends Fragment> {
    /**
     * This method is invoked on the main thread with the reference to the Fragment.
     *
     * @param fragment a Fragment instrumented by the FragmentScenario.
     */
    void perform(@NonNull F fragment);
  }

  /**
   * Runs a given {@code action} on the current Activity's main thread.
   * <p>
   * Note that you should never keep Fragment reference passed into your {@code action}
   * because it can be recreated at anytime during state transitions.
   * <p>
   * Throwing an exception from {@code action} makes the host Activity crash. You can
   * inspect the exception in logcat outputs.
   * <p>
   * This method cannot be called from the main thread.
   */
  @NonNull
  public FragmentScenario<F, A> onFragment(@NonNull final FragmentAction<F> action) {
    mActivityScenario.onActivity(
        new ActivityScenario.ActivityAction<AppCompatActivity>() {
          @Override
          public void perform(AppCompatActivity activity) {
            Fragment fragment = activity.getSupportFragmentManager().findFragmentByTag(
                FRAGMENT_TAG);
            checkNotNull(fragment,
                "The fragment has been removed from FragmentManager already.");
            checkState(mFragmentClass.isInstance(fragment));
            action.perform(Preconditions.checkNotNull(mFragmentClass.cast(fragment)));
          }
        });
    return this;
  }
}
