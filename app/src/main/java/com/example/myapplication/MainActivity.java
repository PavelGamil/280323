package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    // создание полей
    private DrawingView drawingView; // создание поля созданного нами класса
    private ImageButton currPaint; // создание поля кнопки цвета краски в палитре

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // присваивание полям соответствующих id
        drawingView = findViewById(R.id.drawing);
        LinearLayout paintLayout = findViewById(R.id.paint_colors); // извлечение переменной контейнера по id

        currPaint = (ImageButton) paintLayout.getChildAt(0); // получение первой кнопки
        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed)); // при выборе данной кнопки она должна изменить вид в соответствии с ресурсом
    }

    // метод выбора цвета
    public void paintClicked(View view){
        // проверка выбранного цвета кнопки
        if(view != currPaint){
            // извлечение тега кнопки
            ImageButton imgView = (ImageButton) view;
            String color = view.getTag().toString();

            // задание цвета по извлечённому тегу кнопки
            drawingView.setColor(color);

            // обновление пользовательского интерфейса
            imgView.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed)); // присваивание новой формы кнопке (формы выбора)
            currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint)); // возврат прошлой кнопке обычной формы
            currPaint = (ImageButton) view; // присваивание кнопки обратно view
        }
    }
}



