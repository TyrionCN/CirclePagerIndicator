package tyrion.circlepageindicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by taomaogan on 16/3/7.
 */
public class CirclePagerIndicator extends View {
    private int circleCount;
    private float moveX;
    private float x, y;
    private float radius;
    private float strokeWidth;
    private float divideWidth;
    private int fillColor;
    private int emptyColor;
    private float ratio;
    private Paint emptyPaint;
    private Paint fillPaint;

    public CirclePagerIndicator(Context context) {
        super(context);
    }

    public CirclePagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CirclePagerIndicator);
        circleCount = a.getInt(R.styleable.CirclePagerIndicator_circleCount, 3);
        radius = a.getDimension(R.styleable.CirclePagerIndicator_circleRadius, 3);
        strokeWidth = a.getDimension(R.styleable.CirclePagerIndicator_circleStroke, 1);
        divideWidth = a.getDimension(R.styleable.CirclePagerIndicator_circleDivide, 15);
        fillColor = a.getColor(R.styleable.CirclePagerIndicator_circleFillColor, 0xffffff);
        emptyColor = a.getColor(R.styleable.CirclePagerIndicator_circleEmptyColor, 0x66ffffff);

        init(context);
    }

    private void init(Context context) {
        moveX = strokeWidth + radius;
        x = strokeWidth + radius;
        y = strokeWidth + radius;

        emptyPaint = new Paint();
        emptyPaint.setStrokeWidth(strokeWidth);
        emptyPaint.setStyle(Paint.Style.FILL);
        emptyPaint.setAntiAlias(true);
        emptyPaint.setColor(emptyColor);

        fillPaint = new Paint();
        fillPaint.setStrokeWidth(strokeWidth);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);
        fillPaint.setColor(fillColor);

        WindowManager windowManager = (WindowManager)  context.getSystemService(Context.WINDOW_SERVICE);
        int screentWidth = windowManager.getDefaultDisplay().getWidth();
        ratio = (radius * 2 + strokeWidth * 2 + divideWidth) / screentWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float width, height;
        width = circleCount * (radius * 2 + strokeWidth * 2) + (circleCount - 1) * divideWidth;
        height = radius * 2 + strokeWidth * 2;
        setPadding(0, 0, 0, 0);

        setMeasuredDimension((int)width, (int)height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(moveX, y, radius, fillPaint);
        float incremental = radius * 2 + strokeWidth * 2 + divideWidth;
        for (int i = 0; i < circleCount; i++) {
            canvas.drawCircle(x + incremental * i, y, radius, emptyPaint);
        }
    }

    public void moveCircle(int position, float location) {
        float incremental = radius * 2 + strokeWidth * 2 + divideWidth;
        float firstLocation = strokeWidth + radius;
        moveX = firstLocation +  position * incremental +  location * ratio;
        invalidate();
    }
}
