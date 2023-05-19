package com.example.projetcubemobile.tools

import android.graphics.Rect
import android.view.View

class KeyboardListener(private val rootView: View, private val listener: KeyboardVisibilityListener) {
    private var isKeyboardVisible = false

    init {
        rootView.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            rootView.getWindowVisibleDisplayFrame(rect)

            val screenHeight = rootView.rootView.height
            val keyboardHeight = screenHeight - rect.bottom

            if (keyboardHeight > screenHeight * 0.15) {
                // Le clavier est ouvert
                if (!isKeyboardVisible) {
                    isKeyboardVisible = true
                    listener.onKeyboardVisibleChanged(true)
                }
            } else {
                // Le clavier est fermé
                if (isKeyboardVisible) {
                    isKeyboardVisible = false
                    listener.onKeyboardVisibleChanged(false)
                }
            }
        }
    }

    interface KeyboardVisibilityListener {
        fun onKeyboardVisibleChanged(isVisible: Boolean)
    }
}