package com.gaebalsebal.Find_trashbin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        findViewById(R.id.apply).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);
        findViewById(R.id.button11).setOnClickListener(this);
        findViewById(R.id.button12).setOnClickListener(this);
        findViewById(R.id.button13).setOnClickListener(this);
        findViewById(R.id.button14).setOnClickListener(this);
        findViewById(R.id.button15).setOnClickListener(this);
    }
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.apply:
                new AlertDialog.Builder(this)
                        .setTitle("뼈")
                        .setMessage("일반쓰레기")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.delete:
                new AlertDialog.Builder(this)
                        .setTitle("우산")
                        .setMessage("✅분리가 어려운 경우:\n우산 전체를 고철류\n✅뼈대:고철류\n✅나머지:일반쓰레기")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button3:
                new AlertDialog.Builder(this)
                        .setTitle("깨진 유리")
                        .setMessage("✅신문에 싸서 일반쓰레기에 버리기")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button4:
                new AlertDialog.Builder(this)
                        .setTitle("치약")
                        .setMessage("✅치약뚜껑:플라스틱\n✅튜브:일반쓰레기")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button5:
                new AlertDialog.Builder(this)
                        .setTitle("스케치북")
                        .setMessage("✅코팅된 표지:일반쓰레기\n✅스프링:고철,플라스틱\n✅속지:종이")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button6:
                new AlertDialog.Builder(this)
                        .setTitle("유리병")
                        .setMessage("✅유리(깨끗이 씻은 경우)\n✅빈용기보증금이 있는 유리병:슈퍼,대형마트 등")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button7:
                new AlertDialog.Builder(this)
                        .setTitle("화분")
                        .setMessage("✅죽은 식물:일반쓰레기\n✅모종 화분:일반쓰레기\n✅오염된 흙:불연성 폐기물")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button8:
                new AlertDialog.Builder(this)
                        .setTitle("컵라면 용기")
                        .setMessage("✅색이 물든 용기:일반쓰레기\n✅색이 물들지 않은 용기:종이팩")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button9:
                new AlertDialog.Builder(this)
                        .setTitle("의약품")
                        .setMessage("✅알약:밀봉되는 봉투에 모아 배출\n✅가루로 된 약:포장지를 뜯지말고 배출\n✅물약: 한 병에 모아 새지 않게 밀봉후 배출\n"
                        +"연고,안약:겉 종이박스만 분리후 용기째 배출\n📌 반드시 주변 약국,공공시설에 비치된 폐의약품 수거함에 배출")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button10:
                new AlertDialog.Builder(this)
                        .setTitle("에어캡")
                        .setMessage("✅이물질 묻은 경우:일반쓰레기\n✅이물질 묻지 않은 경우:비닐")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button11:
                new AlertDialog.Builder(this)
                        .setTitle("된장,고추장")
                        .setMessage("✅일반쓰레기")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button12:
                new AlertDialog.Builder(this)
                        .setTitle("전단지")
                        .setMessage("✅일반쓰레기")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button13:
                new AlertDialog.Builder(this)
                        .setTitle("고무장갑,고무대야,고무밴드")
                        .setMessage("✅일반쓰레기")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button14:
                new AlertDialog.Builder(this)
                        .setTitle("다른 재질이 섞인 플라스틱")
                        .setMessage("✅일반쓰레기\n✅예시:칫솔,낚시대,볼펜과 같은 완구류 등")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.button15:
                new AlertDialog.Builder(this)
                        .setTitle("거울,전구,도자기류,내열식기,유리 뚜껑,크리스탈")
                        .setMessage("✅일반쓰레기,특수규격 마대,대형폐기물 처리(자저채 방침에 따라야함)")
                        .setNeutralButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
                break;

        }
    }

}
