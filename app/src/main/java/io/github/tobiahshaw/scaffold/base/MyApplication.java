package io.github.tobiahshaw.scaffold.base;

import android.app.Activity;
import android.app.Application;

import java.util.Stack;

public class MyApplication extends Application {

    public Stack<Activity> stack = new Stack<>();

    public void exit() {
        while (!stack.empty()) {
            stack.pop().finish();
        }
    }
}
