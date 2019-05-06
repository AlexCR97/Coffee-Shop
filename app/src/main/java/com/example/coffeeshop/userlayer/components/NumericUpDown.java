package com.example.coffeeshop.userlayer.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.coffeeshop.R;

public class NumericUpDown extends LinearLayout {

    private TextView textViewValue;
    private ImageView buttonRemove;
    private ImageView buttonAdd;

    private int minValue = 0;
    private int maxValue = 100;

    public NumericUpDown(Context context) {
        super(context);
        init(context);
    }

    public NumericUpDown(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_numeric_up_down, this);

        textViewValue = findViewById(R.id.textViewNumericUpDown);
        buttonRemove = findViewById(R.id.imageViewRemoveNumericUpDown);
        buttonAdd = findViewById(R.id.imageViewAddNumericUpDown);

        buttonRemove.setOnClickListener(remove);
        buttonAdd.setOnClickListener(add);
    }

    private ImageView.OnClickListener remove = new ImageView.OnClickListener() {
        @Override
        public void onClick(View v) {
            int value = clamp(getValue() - 1, getMinValue(), getMaxValue());
            setValue(value);
        }
    };

    private ImageView.OnClickListener add = new ImageView.OnClickListener() {
        @Override
        public void onClick(View v) {
            int value = clamp(getValue() + 1, getMinValue(), getMaxValue());
            setValue(value);
        }
    };

    public int getMinValue() { return minValue; }
    public int getMaxValue() { return maxValue; }
    public int getValue() { return Integer.parseInt(textViewValue.getText().toString()); }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
        setValue(minValue);
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
        setValue(maxValue);
    }

    public void setValue(int value) { this.textViewValue.setText(String.valueOf(value)); }

    private int clamp(int value, int min, int max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

}
