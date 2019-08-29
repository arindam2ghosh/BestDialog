package com.arindam.bestdialog;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.StringRes;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Arindam Ghosh on 29.08.2019.
 */
public class BestInfoDialog extends AbsBestDialog<BestInfoDialog> {

    private static final String STORAGE = "ld_dont_show";

    private static final String KEY_DONT_SHOW_AGAIN = "key_dont_show_again";

    private CheckBox cbDontShowAgain;
    private FancyButton confirmButton, negativeButton;
    private LinearLayout btn_layout_holder;

    private int infoDialogId;

    public BestInfoDialog(Context context) {
        super(context);
        setButtonFooterStyle(BestDialogConstant.BOTTON_FOOTER_STYLE_NORMAL);
    }

    public BestInfoDialog(Context context, int theme) {
        super(context, theme);
        setButtonFooterStyle(BestDialogConstant.BOTTON_FOOTER_STYLE_NORMAL);
    }
    public BestInfoDialog(Context context, int theme, int btnStyle) {
        super(context, theme);
        //this.btnStyle = btnStyle;
        setButtonFooterStyle(btnStyle);
    }

    {
        btn_layout_holder = findView(R.id.btn_layout_holder);
        cbDontShowAgain = findView(R.id.ld_cb_dont_show_again);
        infoDialogId = -1;
    }

    public BestInfoDialog setButtonFooterStyle(int btnStyle) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(getContext());
        if(btnStyle == BestDialogConstant.BOTTON_FOOTER_STYLE_ADVANCE){
            view = inflater.inflate(R.layout.button_footer_advance, null);
            btn_layout_holder.addView(view);
        }else{
            view = inflater.inflate(R.layout.button_footer_normal, null);
            btn_layout_holder.addView(view);
        }

        confirmButton = view.findViewById(R.id.ld_btn_confirm);
        negativeButton = view.findViewById(R.id.ld_btn_negative);
        confirmButton.setOnClickListener(new ClickListenerDecorator(null, true));
        return this;
    }

    public BestInfoDialog setNotShowAgainOptionEnabled(int dialogId) {
        infoDialogId = dialogId;
        cbDontShowAgain.setVisibility(View.VISIBLE);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean notShow = cbDontShowAgain.isChecked();
                storage(getContext()).edit().putBoolean(String.valueOf(infoDialogId), notShow).apply();
                dismiss();
            }
        });
        return this;
    }

    public BestInfoDialog setNotShowAgainOptionChecked(boolean defaultChecked) {
        cbDontShowAgain.setChecked(defaultChecked);
        return this;
    }

    public BestInfoDialog setConfirmButtonText(@StringRes int text) {
        return setConfirmButtonText(string(text));
    }

    public BestInfoDialog setConfirmButtonText(String text) {
        confirmButton.setText(text);
        return this;
    }

    public BestInfoDialog setConfirmButtonColor(int color) {
        confirmButton.setTextColor(color);
        return this;
    }

    @Override
    public Dialog show() {
        if (infoDialogId == -1) {
            return super.show();
        }

        boolean shouldShowDialog = !storage(getContext()).getBoolean(String.valueOf(infoDialogId), false);
        if (shouldShowDialog) {
            return super.show();
        } else {
            return super.create();
        }
    }

    @Override
    void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_DONT_SHOW_AGAIN, cbDontShowAgain.isChecked());
    }

    @Override
    void restoreState(Bundle savedState) {
        super.restoreState(savedState);
        cbDontShowAgain.setChecked(savedState.getBoolean(KEY_DONT_SHOW_AGAIN));
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_info;
    }

    public static void reset(Context context, int dialogId) {
        storage(context).edit().putBoolean(String.valueOf(dialogId), false).apply();
    }

    private static SharedPreferences storage(Context context) {
        return context.getSharedPreferences(STORAGE, Context.MODE_PRIVATE);
    }
}
