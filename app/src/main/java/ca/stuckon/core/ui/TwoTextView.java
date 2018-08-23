package ca.stuckon.core.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;

public class TwoTextView extends android.support.v7.widget.AppCompatTextView {

    private Paint rightPaint;
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
    }

    private void init(Context context) {
        rightPaint = new Paint(getPaint());
        rightY = getPaddingTop() + rightPaint.getTextSize();
    }

    public void setRightText(String text) {
        rightText = text;
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
