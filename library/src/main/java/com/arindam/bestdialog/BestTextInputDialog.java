package com.arindam.bestdialog;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by Arindam Ghosh on 29.08.2019.
 */
public class BestTextInputDialog extends AbsBestDialog<BestTextInputDialog> {

    private static final String KEY_HAS_ERROR = "key_has_error";
    private static final String KEY_TYPED_TEXT = "key_typed_text";

    private EditText inputField;
    private TextView errorMessage;
    private FancyButton confirmButton, negativeButton;
    private LinearLayout btn_layout_holder;

    private TextFilter filter;

    public BestTextInputDialog(Context context) {
        super(context);
        setButtonFooterStyle(BestDialogConstant.BOTTON_FOOTER_STYLE_NORMAL);
    }

    public BestTextInputDialog(Context context, int theme) {
        super(context, theme);
        setButtonFooterStyle(BestDialogConstant.BOTTON_FOOTER_STYLE_NORMAL);
    }

    public BestTextInputDialog(Context context, int theme, int btnStyle) {
        super(context, theme);
        setButtonFooterStyle(btnStyle);
    }

    {

        inputField = findView(R.id.ld_text_input);
        errorMessage = findView(R.id.ld_error_message);
        btn_layout_holder = findView(R.id.btn_layout_holder);
        inputField.addTextChangedListener(new HideErrorOnTextChanged());
    }


    public BestTextInputDialog configureEditText(@NonNull ViewConfigurator<EditText> viewConfigurator) {
        viewConfigurator.configureView(inputField);
        return this;
    }

    public BestTextInputDialog setConfirmButton(@StringRes int text, OnTextInputConfirmListener listener) {
        return setConfirmButton(string(text), listener);
    }

    public BestTextInputDialog setConfirmButton(String text, OnTextInputConfirmListener listener) {
        confirmButton.setText(text);
        confirmButton.setOnClickListener(new TextInputListener(listener));
        return this;
    }

    public BestTextInputDialog setConfirmButtonColor(int color) {
        confirmButton.setTextColor(color);
        return this;
    }

    public BestTextInputDialog setButtonFooterStyle(int btnStyle) {
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
        return this;
    }

    public BestTextInputDialog setNegativeButton(@StringRes int text, View.OnClickListener listener){
        return setNegativeButton(string(text), listener);
    }

    public BestTextInputDialog setNegativeButton(String text, View.OnClickListener listener){
        negativeButton.setVisibility(View.VISIBLE);
        negativeButton.setText(text);
        negativeButton.setOnClickListener(new ClickListenerDecorator(listener, true));
        return this;
    }

    public BestTextInputDialog setNegativeButtonColor(int color) {
        negativeButton.setTextColor(color);
        return this;
    }

    public BestTextInputDialog setInputFilter(@StringRes int errorMessage, TextFilter filter) {
        return setInputFilter(string(errorMessage), filter);
    }

    public BestTextInputDialog setInputFilter(String errorMessage, TextFilter filter) {
        this.filter = filter;
        this.errorMessage.setText(errorMessage);
        return this;
    }

    public BestTextInputDialog setErrorMessageColor(int color) {
        errorMessage.setTextColor(color);
        return this;
    }

    public BestTextInputDialog setInputType(int inputType) {
        inputField.setInputType(inputType);
        return this;
    }

    public BestTextInputDialog addTextWatcher(TextWatcher textWatcher) {
        inputField.addTextChangedListener(textWatcher);
        return this;
    }

    public BestTextInputDialog setInitialInput(@StringRes int text) {
        return setInitialInput(string(text));
    }

    public BestTextInputDialog setInitialInput(String text) {
        inputField.setText(text);
        return this;
    }

    public BestTextInputDialog setHint(@StringRes int hint) {
        return setHint(string(hint));
    }

    public BestTextInputDialog setHint(String text) {
        inputField.setHint(text);
        return this;
    }

    private void setError() {
        errorMessage.setVisibility(View.VISIBLE);
    }

    private void hideError() {
        errorMessage.setVisibility(View.GONE);
    }

    @Override
    void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_HAS_ERROR, errorMessage.getVisibility() == View.VISIBLE);
        outState.putString(KEY_TYPED_TEXT, inputField.getText().toString());
    }

    @Override
    void restoreState(Bundle savedState) {
        super.restoreState(savedState);
        if (savedState.getBoolean(KEY_HAS_ERROR, false)) {
            setError();
        }
        inputField.setText(savedState.getString(KEY_TYPED_TEXT));
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_text_input;
    }

    private class TextInputListener implements View.OnClickListener {

        private OnTextInputConfirmListener wrapped;

        private TextInputListener(OnTextInputConfirmListener wrapped) {
            this.wrapped = wrapped;
        }

        @Override
        public void onClick(View v) {
            String text = inputField.getText().toString();

            if (filter != null) {
                boolean isWrongInput = !filter.check(text);
                if (isWrongInput) {
                    setError();
                    return;
                }
            }

            if (wrapped != null) {
                wrapped.onTextInputConfirmed(text);
            }

            dismiss();
        }
    }

    private class HideErrorOnTextChanged implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            hideError();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public interface OnTextInputConfirmListener {
        void onTextInputConfirmed(String text);
    }

    public interface TextFilter {
        boolean check(String text);
    }
}
