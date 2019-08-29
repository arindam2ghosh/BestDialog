package com.arindam.bestdialog;

import android.content.Context;

/**
 * Created by Arindam Ghosh on 29.08.2019.
 */
public class BestProgressDialog extends AbsBestDialog<BestProgressDialog> {

    public BestProgressDialog(Context context) {
        super(context);
    }

    public BestProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    {
        setCancelable(false);
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_progress;
    }
}
