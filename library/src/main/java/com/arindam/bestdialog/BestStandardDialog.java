package com.arindam.bestdialog;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;


import static android.view.View.*;

/**
 * Created by Arindam Ghosh on 29.08.2019.
 */
@SuppressWarnings("WeakerAccess")
public class BestStandardDialog extends AbsBestDialog<BestStandardDialog> {

    public static final int POSITIVE_BUTTON = R.id.ld_btn_yes;
    public static final int NEGATIVE_BUTTON = R.id.ld_btn_no;
    public static final int NEUTRAL_BUTTON = R.id.ld_btn_neutral;

    private Button positiveButton;
    private Button negativeButton;
    private Button neutralButton;

    public BestStandardDialog(Context context) {
        super(context);
    }

    public BestStandardDialog(Context context, int theme) {
        super(context, theme);
    }

    public BestStandardDialog(Context context, ButtonLayout buttonLayout) {
        super(context, 0, buttonLayout.layoutRes);
    }

    public BestStandardDialog(Context context, int theme, ButtonLayout buttonLayout) {
        super(context, theme, buttonLayout.layoutRes);
    }


    {
        positiveButton = findView(R.id.ld_btn_yes);
        negativeButton = findView(R.id.ld_btn_no);
        neutralButton = findView(R.id.ld_btn_neutral);
    }

    public BestStandardDialog setPositiveButton(@StringRes int text, OnClickListener listener) {
        return setPositiveButton(string(text), listener);
    }

    public BestStandardDialog setPositiveButton(String text, @Nullable OnClickListener listener) {
        positiveButton.setVisibility(VISIBLE);
        positiveButton.setText(text);
        positiveButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public BestStandardDialog setNegativeButtonText(@StringRes int text) {
        return setNegativeButton(string(text), null);
    }

    public BestStandardDialog setNegativeButtonText(String text) {
        return setNegativeButton(text, null);
    }

    public BestStandardDialog setNegativeButton(@StringRes int text, OnClickListener listener) {
        return setNegativeButton(string(text), listener);
    }

    public BestStandardDialog setNegativeButton(String text, @Nullable OnClickListener listener) {
        negativeButton.setVisibility(VISIBLE);
        negativeButton.setText(text);
        negativeButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public BestStandardDialog setNeutralButtonText(@StringRes int text) {
        return setNeutralButton(string(text), null);
    }

    public BestStandardDialog setNeutralButtonText(String text) {
        return setNeutralButton(text, null);
    }

    public BestStandardDialog setNeutralButton(@StringRes int text, @Nullable OnClickListener listener) {
        return setNeutralButton(string(text), listener);
    }

    public BestStandardDialog setNeutralButton(String text, @Nullable OnClickListener listener) {
        neutralButton.setVisibility(VISIBLE);
        neutralButton.setText(text);
        neutralButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public BestStandardDialog setButtonsColor(@ColorInt int color) {
        positiveButton.setTextColor(color);
        negativeButton.setTextColor(color);
        neutralButton.setTextColor(color);
        return this;
    }

    public BestStandardDialog setButtonsColorRes(@ColorRes int colorRes) {
        return setButtonsColor(color(colorRes));
    }

    public BestStandardDialog setOnButtonClickListener(View.OnClickListener listener) {
        return setOnButtonClickListener(true, listener);
    }

    public BestStandardDialog setOnButtonClickListener(boolean closeOnClick, View.OnClickListener listener) {
        View.OnClickListener clickHandler = new ClickListenerDecorator(listener, closeOnClick);
        positiveButton.setOnClickListener(clickHandler);
        neutralButton.setOnClickListener(clickHandler);
        negativeButton.setOnClickListener(clickHandler);
        return this;
    }

    public BestStandardDialog setPositiveButtonText(@StringRes int text) {
        return setPositiveButton(string(text), null);
    }

    public BestStandardDialog setPositiveButtonText(String text) {
        return setPositiveButton(text, null);
    }

    public BestStandardDialog setPositiveButtonColor(@ColorInt int color) {
        positiveButton.setTextColor(color);
        return this;
    }

    public BestStandardDialog setNegativeButtonColor(@ColorInt int color) {
        negativeButton.setTextColor(color);
        return this;
    }

    public BestStandardDialog setNeutralButtonColor(@ColorInt int color) {
        neutralButton.setTextColor(color);
        return this;
    }

    public BestStandardDialog setPositiveButtonColorRes(@ColorRes int colorRes) {
        return setPositiveButtonColor(color(colorRes));
    }

    public BestStandardDialog setNegativeButtonColorRes(@ColorRes int colorRes) {
        return setNegativeButtonColor(color(colorRes));
    }

    public BestStandardDialog setNeutralButtonColorRes(@ColorRes int colorRes) {
        return setNeutralButtonColor(color(colorRes));
    }

    @Override
    protected int getLayout() {
        return ButtonLayout.HORIZONTAL.layoutRes;
    }

    public enum ButtonLayout {
        HORIZONTAL(R.layout.dialog_standard),
        VERTICAL(R.layout.dialog_standard_vertical);
        final @LayoutRes int layoutRes;
        ButtonLayout(@LayoutRes int layoutRes) {
            this.layoutRes = layoutRes;
        }
    }
}
