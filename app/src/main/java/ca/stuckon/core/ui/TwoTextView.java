package ca.stuckon.core.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;

import ca.stuckon.demos.github.R;

public class TwoTextView extends android.support.v7.widget.AppCompatTextView {

    private TextPaint rightPaint;
    private float rightX;
    private float rightY;
    private String rightText;

    public TwoTextView(Context context) {
        super(context);
        init(context);
    }

    public TwoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TwoTextView, 0, 0);
        try {
            if (a.hasValue(R.styleable.TwoTextView_rightText)) {
                setRightText(a.getText(R.styleable.TwoTextView_rightText));
            }
            if (a.hasValue(R.styleable.TwoTextView_rightColor)) {
                setRightColor(a.getColor(R.styleable.TwoTextView_rightColor, 0xFF000000));
            }
            if (a.hasValue(R.styleable.TwoTextView_rightSize)) {
                setRightSize(a.getDimensionPixelSize(R.styleable.TwoTextView_rightSize, 14));
            }
            if (a.hasValue(R.styleable.TwoTextView_rightBold)) {
                setRightBold(a.getBoolean(R.styleable.TwoTextView_rightBold, false));
            }
        } finally {
            a.recycle();
        }
    }

    private void init(Context context) {
        rightPaint = new TextPaint(getPaint());
        rightPaint.setColor(getPaint().getColor());
        rightY = getPaddingTop() + rightPaint.getTextSize();
    }

    public void setRightColor(int color) {
        rightPaint.setColor(color);
    }

    public void setRightSize(int px) {
        rightPaint.setTextSize(px);
    }

    public void setRightBold(boolean isBold) {
        rightPaint.setTypeface(Typeface.create(Typeface.DEFAULT, isBold ? Typeface.BOLD : Typeface.NORMAL));
    }

    public void setRightText(CharSequence text) {
        rightText = text == null ? null : text.toString();
        rightX = rightText == null ? 0 : getWidth() - getPaddingRight() - rightPaint.measureText(rightText);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rightX = rightText == null ? 0 : w - getPaddingRight() - rightPaint.measureText(rightText);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!TextUtils.isEmpty(rightText)) {
            canvas.drawText(rightText, rightX, rightY, rightPaint);
        }
    }
}
