package com.example.sajid.roadsense.gauge;

/**
 * Created by Sajid on 4/11/2019.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;


public final class GaugeAcceleration extends View {


    private static final String tag = GaugeAcceleration.class.getSimpleName();

    // holds the cached static part
    private Bitmap background;

    private Paint backgroundPaint;
    private Paint pointPaint;
    private Paint rimPaint;
    private Paint rimShadowPaint;

    private RectF faceRect;
    private RectF rimRect;
    //added by Scott
    private RectF rimOuterRect;
    private RectF rimOuterTopRect;
    private RectF rimOuterBottomRect;
    private RectF rimOuterLeftRect;
    private RectF rimOuterRightRect;
    private RectF innerRim;
    private RectF innerface;
    private RectF rimInnerTopRect;
    private RectF rimInnerBottomRect;
    private RectF rimInnerLeftRect;
    private RectF rimInnerRightRect;
    private RectF innerMostDot;

    private float x;
    private float y;

    private int color = 0;

    /**
     * Create a new instance.
     *
     * @param context
     */
    public GaugeAcceleration(Context context)
    {
        super(context);
        init();
    }

    /**
     * Create a new instance.
     *
     * @param context
     * @param attrs
     */
    public GaugeAcceleration(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    /**
     * Create a new instance.
     *
     * @param context
     * @param attrs
     * @param defStyle
     */
    public GaugeAcceleration(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Update the measurements for the point.
     *
     * @param x
     *            the x-axis
     * @param y
     *            the y-axis
     * @param color
     *            the color
     */
    public void updatePoint(float x, float y, int color)
    {
        this.x = ((faceRect.right - faceRect.left) / (SensorManager.GRAVITY_EARTH * 2))
                * x + faceRect.centerX();
        this.y = ((faceRect.bottom - faceRect.top) / (SensorManager.GRAVITY_EARTH * 2))
                * y + faceRect.centerY();

        this.color = color;

        this.invalidate();
    }

    /**
     * Initialize the members of the instance.
     */
    private void init()
    {
        initDrawingTools();
    }

    /**
     * Initialize the drawing related members of the instance.
     */
    private void initDrawingTools()
    {
        rimRect = new RectF(0.1f, 0.1f, 0.9f, 0.9f);

        //inner rim oval
        innerRim = new RectF(0.25f, 0.25f, 0.75f, 0.75f);

        //inner most white dot
        innerMostDot = new RectF(0.47f, 0.47f, 0.53f, 0.53f);

        // the linear gradient is a bit skewed for realism
        rimPaint = new Paint();
        rimPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        rimPaint.setShader(new LinearGradient(0.40f, 0.0f, 0.60f, 1.0f, Color
                .rgb(255, 255, 255), Color.rgb(255,255,255),
                Shader.TileMode.CLAMP));

        float rimSize = 0.03f;
        faceRect = new RectF();
        faceRect.set(rimRect.left + rimSize, rimRect.top + rimSize,
                rimRect.right - rimSize, rimRect.bottom - rimSize);

        rimShadowPaint = new Paint();
        rimShadowPaint.setStyle(Paint.Style.FILL);
        rimShadowPaint.setAntiAlias(true);

        //set the size of the outside white with the rectangles.
        //a 'bigger' negative will increase the size.
        float rimOuterSize = -0.04f;
        rimOuterRect = new RectF();
        rimOuterRect.set(rimRect.left + rimOuterSize, rimRect.top + rimOuterSize,
                rimRect.right - rimOuterSize, rimRect.bottom - rimOuterSize);

        rimOuterTopRect = new RectF(0.5f, 0.116f, 0.5f, 0.07f);
        rimOuterTopRect.set(rimOuterTopRect.left + rimOuterSize, rimOuterTopRect.top + rimOuterSize,
                rimOuterTopRect.right - rimOuterSize, rimOuterTopRect.bottom - rimOuterSize);

        rimOuterBottomRect = new RectF(0.5f, 0.93f, 0.5f, 0.884f);
        rimOuterBottomRect.set(rimOuterBottomRect.left + rimOuterSize, rimOuterBottomRect.top + rimOuterSize,
                rimOuterBottomRect.right - rimOuterSize, rimOuterBottomRect.bottom - rimOuterSize);

        rimOuterLeftRect = new RectF(0.116f, 0.5f, 0.07f, 0.5f);
        rimOuterLeftRect.set(rimOuterLeftRect.left + rimOuterSize, rimOuterLeftRect.top + rimOuterSize,
                rimOuterLeftRect.right - rimOuterSize, rimOuterLeftRect.bottom - rimOuterSize);

        rimOuterRightRect = new RectF(0.93f, 0.5f, 0.884f, 0.5f);
        rimOuterRightRect.set(rimOuterRightRect.left + rimOuterSize, rimOuterRightRect.top + rimOuterSize,
                rimOuterRightRect.right - rimOuterSize, rimOuterRightRect.bottom - rimOuterSize);

        //inner rim declarations the black oval/rect
        float rimInnerSize = 0.02f;
        innerface = new RectF();
        innerface.set(innerRim.left + rimInnerSize, innerRim.top + rimInnerSize,
                innerRim.right - rimInnerSize, innerRim.bottom - rimInnerSize);

        //inner 4 small rectangles
        rimInnerTopRect = new RectF(0.46f, 0.23f, 0.54f, 0.26f);
        rimInnerBottomRect = new RectF(0.46f, 0.74f, 0.54f, 0.77f);
        rimInnerLeftRect = new RectF(0.23f, 0.54f, 0.26f, 0.46f);
        rimInnerRightRect = new RectF(0.74f, 0.54f, 0.77f, 0.46f);

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setColor(Color.WHITE);
        pointPaint.setShadowLayer(0.01f, -0.005f, -0.005f, 0x7f000000);
        pointPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        backgroundPaint = new Paint();
        backgroundPaint.setFilterBitmap(true);
    }

    /**
     * Measure the device screen size to scale the canvas correctly.
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int chosenWidth = chooseDimension(widthMode, widthSize);
        int chosenHeight = chooseDimension(heightMode, heightSize);

        int chosenDimension = Math.min(chosenWidth, chosenHeight);

        setMeasuredDimension(chosenDimension, chosenDimension);
    }

    /**
     * Indicate the desired canvas dimension.
     *
     * @param mode
     * @param size
     * @return
     */
    private int chooseDimension(int mode, int size)
    {
        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY)
        {
            return size;
        } else
        { // (mode == MeasureSpec.UNSPECIFIED)
            return getPreferredSize();
        }
    }

    /**
     * In case there is no size specified.
     *
     * @return default preferred size.
     */
    private int getPreferredSize()
    {
        return 300;
    }

    /**
     * Draw the gauge.
     *
     * @param canvas
     */
    private void drawGauge(Canvas canvas)
    {

        // first, draw the metallic body
        canvas.drawOval(rimRect, rimPaint);
        // now the outer rim circle
        //canvas.drawOval(rimRect, rimCirclePaint);

        //top rect
        canvas.drawRect(rimOuterTopRect, rimPaint);
        //bottom rect
        canvas.drawRect(rimOuterBottomRect, rimPaint);
        //left rect
        canvas.drawRect(rimOuterLeftRect, rimPaint);
        //right rect
        canvas.drawRect(rimOuterRightRect, rimPaint);

        // draw the rim shadow inside the face
        canvas.drawOval(faceRect, rimShadowPaint);

        //draw the inner white rim circle
        canvas.drawOval(innerRim, rimPaint);

        //draw inner topRect
        canvas.drawRect(rimInnerTopRect, rimPaint);
        //draw inner bottomRect
        canvas.drawRect(rimInnerBottomRect, rimPaint);
        //draw inner leftrect
        canvas.drawRect(rimInnerLeftRect, rimPaint);
        //draw inner rightRect
        canvas.drawRect(rimInnerRightRect, rimPaint);

        // draw the inner black oval
        canvas.drawOval(innerface, rimShadowPaint);

        //draw inner white dot
        canvas.drawOval(innerMostDot, rimPaint);
    }

    /**
     * Draw the measurement point.
     *
     * @param canvas
     */
    private void drawPoint(Canvas canvas)
    {
        canvas.save();
        pointPaint.setColor(this.color);
        canvas.drawCircle(this.x, this.y, 0.025f, pointPaint);
        canvas.restore();
    }

    /**
     * Draw the background of the canvas.
     *
     * @param canvas
     */
    private void drawBackground(Canvas canvas)
    {
        // Use the cached background bitmap.
        if (background == null)
        {
            Log.w(tag, "Background not created");
        } else
        {
            canvas.drawBitmap(background, 0, 0, backgroundPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        drawBackground(canvas);

        float scale = (float) getWidth();
        canvas.save();
        canvas.scale(scale, scale);

        drawPoint(canvas);

        canvas.restore();
    }

    /**
     * Indicate the desired size of the canvas has changed.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        Log.d(tag, "Size changed to " + w + "x" + h);

        regenerateBackground();
    }

    /**
     * Regenerate the background image. This should only be called when the size
     * of the screen has changed. The background will be cached and can be
     * reused without needing to redraw it.
     */
    private void regenerateBackground()
    {
        // free the old bitmap
        if (background != null)
        {
            background.recycle();
        }

        background = Bitmap.createBitmap(getWidth(), getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas backgroundCanvas = new Canvas(background);
        float scale = (float) getWidth();
        backgroundCanvas.scale(scale, scale);

        drawGauge(backgroundCanvas);
    }

}
