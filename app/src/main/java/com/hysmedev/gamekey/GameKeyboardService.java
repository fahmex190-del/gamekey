package com.hysmedev.gamekey;

import android.inputmethodservice.InputMethodService;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.Button;

public class GameKeyboardService extends InputMethodService {

    @Override
    public View onCreateInputView() {
        // Panggil layout UI yang kita dah buat tadi
        View layout = getLayoutInflater().inflate(R.layout.game_keyboard_layout, null);

        // Bind butang dari XML
        setupKey(layout.findViewById(R.id.btn_w), KeyEvent.KEYCODE_W);
        setupKey(layout.findViewById(R.id.btn_a), KeyEvent.KEYCODE_A);
        setupKey(layout.findViewById(R.id.btn_s), KeyEvent.KEYCODE_S);
        setupKey(layout.findViewById(R.id.btn_d), KeyEvent.KEYCODE_D);
        setupKey(layout.findViewById(R.id.btn_space), KeyEvent.KEYCODE_SPACE);

        return layout;
    }

    private void setupKey(Button button, final int keyCode) {
        if (button == null) return;

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputConnection ic = getCurrentInputConnection();
                if (ic != null) {
                    int action = event.getAction();
                    
                    if (action == MotionEvent.ACTION_DOWN) {
                        // Hantar arahan butang ditekan (Hold down)
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, keyCode));
                    } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL) {
                        // Hantar arahan butang dilepaskan (Release)
                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, keyCode));
                    }
                }
                return false; // Biarkan button jalankan efek visual asal (click effect)
            }
        });
    }
}
