package com.example.userdetailmappingtask;

import android.os.Bundle;

import org.junit.rules.ExternalResource;

import javax.annotation.Nonnull;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;

public class FragmentScenarioRule<F extends Fragment, A extends AppCompatActivity> extends ExternalResource {

  private final Class<F> fragmentClass;
  private final Class<A> activityClass;
  private Bundle bundle;
  private FragmentScenario<F, A> fragmentScenario;
  private F fragment;

  public FragmentScenarioRule(
        @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass) {
    this(fragmentClass, activityClass, new Bundle());
  }

  public FragmentScenarioRule(
        @NonNull Class<F> fragmentClass, @NonNull Class<A> activityClass, @Nonnull Bundle bundle) {
    this.fragmentClass = fragmentClass;
    this.activityClass = activityClass;
    this.bundle = bundle;
  }

  @Override
  public void before() {
  }

  @Nonnull
  public Bundle getBundle() {
    return bundle;
  }

  /**
   * Used to change bundle parameters.
   * @param bundle
   */
  public void setBundle(@Nonnull Bundle bundle) {
    this.bundle = bundle;
  }

  @Nonnull
  public F getFragment() {
    return fragment;
  }

  @NonNull
  public FragmentScenario<F, A> onFragment(@NonNull final FragmentScenario.FragmentAction<F> action) {
    fragmentScenario = FragmentScenario.launchInContainer(fragmentClass, activityClass, bundle);
    fragmentScenario.moveToState(Lifecycle.State.RESUMED);
    return fragmentScenario.onFragment( action );
  }

  @Override
  public void after() {
    if(fragmentScenario != null) {
      fragmentScenario.moveToState(Lifecycle.State.DESTROYED);
    }
  }
}

