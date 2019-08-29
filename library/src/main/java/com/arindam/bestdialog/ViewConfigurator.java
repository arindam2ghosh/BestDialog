package com.arindam.bestdialog;

import android.view.View;

/**
 * Created by Arindam Ghosh on 29.08.2019.
 */

public interface ViewConfigurator<T extends View> {
  void configureView(T v);
}
