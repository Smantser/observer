package specter.observer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

public class ATest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atest);
    }


    public void create_groupTYT_menu()
    {
        RadioGroup radioGroupTYT1 = (RadioGroup) findViewById(R.id.radioTYT1);
        radioGroupTYT1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tyt11:

                        break;
                    case R.id.tyt12:

                        break;

                    default:
                        break;
                }
            }
        });

        RadioGroup radioGroupTYT2 = (RadioGroup) findViewById(R.id.radioTYT1);
        radioGroupTYT2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tyt21:

                        break;
                    case R.id.tyt22:

                        break;

                    default:
                        break;
                }
            }
        });

        RadioGroup radioGroupTYT3 = (RadioGroup) findViewById(R.id.radioTYT1);
        radioGroupTYT3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tyt31:

                        break;
                    case R.id.tyt32:

                        break;

                    default:
                        break;
                }
            }
        });


        RadioGroup radioGroupTYT4 = (RadioGroup) findViewById(R.id.radioTYT1);
        radioGroupTYT4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tyt41:

                        break;
                    case R.id.tyt42:

                        break;

                    default:
                        break;
                }
            }
        });


        RadioGroup radioGroupTYT5 = (RadioGroup) findViewById(R.id.radioTYT1);

        radioGroupTYT5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tyt51:

                        break;
                    case R.id.tyt52:

                        break;

                    default:
                        break;
                }
            }
        });

        RadioGroup radioGroupTYT6 = (RadioGroup) findViewById(R.id.radioTYT1);

        radioGroupTYT6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tyt61:

                        break;
                    case R.id.tyt62:

                        break;

                    default:
                        break;
                }
            }
        });


        RadioGroup radioGroupTYT7 = (RadioGroup) findViewById(R.id.radioTYT1);

        radioGroupTYT7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tyt71:

                        break;
                    case R.id.tyt72:

                        break;

                    default:
                        break;
                }
            }
        });
    }
}
