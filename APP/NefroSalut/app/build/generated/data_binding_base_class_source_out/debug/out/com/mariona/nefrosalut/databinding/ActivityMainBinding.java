// Generated by view binder compiler. Do not edit!
package com.mariona.nefrosalut.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.mariona.nefrosalut.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView Nomapp;

  @NonNull
  public final AppCompatButton btnLogin;

  @NonNull
  public final AppCompatButton btnRegister;

  @NonNull
  public final Guideline guideline;

  @NonNull
  public final Guideline guideline2;

  @NonNull
  public final Guideline guideline3;

  @NonNull
  public final ImageView imglogo;

  @NonNull
  public final ConstraintLayout main;

  private ActivityMainBinding(@NonNull ConstraintLayout rootView, @NonNull TextView Nomapp,
      @NonNull AppCompatButton btnLogin, @NonNull AppCompatButton btnRegister,
      @NonNull Guideline guideline, @NonNull Guideline guideline2, @NonNull Guideline guideline3,
      @NonNull ImageView imglogo, @NonNull ConstraintLayout main) {
    this.rootView = rootView;
    this.Nomapp = Nomapp;
    this.btnLogin = btnLogin;
    this.btnRegister = btnRegister;
    this.guideline = guideline;
    this.guideline2 = guideline2;
    this.guideline3 = guideline3;
    this.imglogo = imglogo;
    this.main = main;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.Nomapp;
      TextView Nomapp = ViewBindings.findChildViewById(rootView, id);
      if (Nomapp == null) {
        break missingId;
      }

      id = R.id.btnLogin;
      AppCompatButton btnLogin = ViewBindings.findChildViewById(rootView, id);
      if (btnLogin == null) {
        break missingId;
      }

      id = R.id.btnRegister;
      AppCompatButton btnRegister = ViewBindings.findChildViewById(rootView, id);
      if (btnRegister == null) {
        break missingId;
      }

      id = R.id.guideline;
      Guideline guideline = ViewBindings.findChildViewById(rootView, id);
      if (guideline == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);
      if (guideline2 == null) {
        break missingId;
      }

      id = R.id.guideline3;
      Guideline guideline3 = ViewBindings.findChildViewById(rootView, id);
      if (guideline3 == null) {
        break missingId;
      }

      id = R.id.imglogo;
      ImageView imglogo = ViewBindings.findChildViewById(rootView, id);
      if (imglogo == null) {
        break missingId;
      }

      ConstraintLayout main = (ConstraintLayout) rootView;

      return new ActivityMainBinding((ConstraintLayout) rootView, Nomapp, btnLogin, btnRegister,
          guideline, guideline2, guideline3, imglogo, main);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
