package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    // создание полей
    private Path drawPath; // путь для рисунка (траектория касания пальцем)
    private Paint drawPaint, canvasPaint; // рисунок и краска на холсте
    private int paintColor = 0xFF660000; // цвет по умолчанию
    private Canvas drawCanvas; // canvas
    private Bitmap canvasBitmap; // canvas bitmap

    // конструктор родительского класса
    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupDrawing();
    }

    // метод вызывается когда пользовательскому представлению присваивается размер
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // задание растрового изображения с шириной w и длиной h
        drawCanvas = new Canvas(canvasBitmap); // экземпляр холста
    }

    // метод позволяющий классу функционировать как представление
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // холст и контур рисования
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    // необходимо добавить слушателя событий касания экрана
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);

        // извлечение координат касания экрана
        float touchX = event.getX();
        float touchY = event.getY();

        // обработка движения касания экрана вниз, движения и вверх
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }

        invalidate(); // анулирование View
        return true;
    }

    // метод настройки области рисования для взаимодействия
    private void setupDrawing() {
        drawPath = new Path(); // создание объекта траектории касания пальцем
        drawPaint = new Paint(); // создание объекта рисунка и краски

        drawPaint.setColor(paintColor); // установление цвета по умолчанию

        // установление параметров кисти
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(20);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG); // создание объекта класса Canvas
    }

    // метод задания цвета
    public void setColor(String newColor){
        invalidate(); // признание представления недействительным
        // далее разбираем и устанавливаем цвет для рисования
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }
}
