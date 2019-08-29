package com.arindam.bestdialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by Arindam Ghosh on 29.08.2019.
 */
public class BestDialogCompat {

    /**
     * If you don't want to change implemented interfaces when migrating from standard dialogs
     * to LovelyDialogs - use this method.
     */
    public static View.OnClickListener wrap(Dialog.OnClickListener listener) {
        return new DialogOnClickListenerAdapter(listener);
    }

    static class DialogOnClickListenerAdapter implements View.OnClickListener {

        private Dialog.OnClickListener adapted;

        DialogOnClickListenerAdapter(DialogInterface.OnClickListener adapted) {
            this.adapted = adapted;
        }

        public void onClick(DialogInterface dialogInterface, int which) {
            if (adapted != null) {
                adapted.onClick(dialogInterface, which);
            }
        }

        @Override
        public void onClick(View v) {

        }
    }
}
