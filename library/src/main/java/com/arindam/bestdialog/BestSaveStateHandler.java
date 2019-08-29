package com.arindam.bestdialog;

import android.os.Bundle;
import android.util.SparseArray;

import java.lang.ref.WeakReference;

/**
 * Created by Arindam Ghosh on 29.08.2019.
 */
public class BestSaveStateHandler {

    private static final String KEY_DIALOG_ID = "id";

    private SparseArray<WeakReference<AbsBestDialog<?>>> handledDialogs;

    public BestSaveStateHandler() {
        handledDialogs = new SparseArray<>();
    }

    public void saveInstanceState(Bundle outState) {
        for (int index = handledDialogs.size() - 1; index >= 0; index--) {
            WeakReference<AbsBestDialog<?>> dialogRef = handledDialogs.valueAt(index);
            if (dialogRef.get() == null) {
                handledDialogs.remove(index);
                continue;
            }
            AbsBestDialog<?> dialog = dialogRef.get();
            if (dialog.isShowing()) {
                dialog.onSaveInstanceState(outState);
                outState.putInt(KEY_DIALOG_ID, handledDialogs.keyAt(index));
                return;
            }
        }
    }

    void handleDialogStateSave(int id, AbsBestDialog<?> dialog) {
        handledDialogs.put(id, new WeakReference<AbsBestDialog<?>>(dialog));
    }

    public static boolean wasDialogOnScreen(Bundle savedInstanceState) {
        return savedInstanceState.keySet().contains(KEY_DIALOG_ID);
    }

    public static int getSavedDialogId(Bundle savedInstanceState) {
        return savedInstanceState.getInt(KEY_DIALOG_ID, -1);
    }
}
