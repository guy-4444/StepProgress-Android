package guy4444.stepprogressapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import guy4444.stepprogress.StepsProgress;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab_back;
    FloatingActionButton fab_skip;
    FloatingActionButton fab_next;

    StepsProgress steps1;
    StepsProgress steps2;
    StepsProgress steps3;
    StepsProgress steps4;
    StepsProgress steps5;
    StepsProgress steps6;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        fab_back = findViewById(R.id.fab_back);
        fab_skip = findViewById(R.id.fab_skip);
        fab_next = findViewById(R.id.fab_next);

        steps1 = (StepsProgress) findViewById(R.id.steps1);
        steps1.initSteps(6);

        steps2 = (StepsProgress) findViewById(R.id.steps2);
        steps2.initSteps(14);

        steps3 = (StepsProgress) findViewById(R.id.steps3);
        steps3.initSteps(4);

        steps4 = (StepsProgress) findViewById(R.id.steps4);
        steps4.initSteps(26);

        steps5 = (StepsProgress) findViewById(R.id.steps5);
        steps5.initSteps(16);

        steps6 = (StepsProgress) findViewById(R.id.steps6);
        steps6.initSteps(3);

        fab_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                steps1.stepCompleted();
                steps2.stepCompleted();
                steps3.stepCompleted();
                steps4.stepCompleted();
                steps5.stepCompleted();
                steps6.stepCompleted();
            }
        });

        fab_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                steps1.stepSkipped();
                steps2.stepSkipped();
                steps3.stepSkipped();
                steps4.stepSkipped();
                steps5.stepSkipped();
                steps6.stepSkipped();
            }
        });

        fab_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                steps1.backPreviousStep();
                steps2.backPreviousStep();
                steps3.backPreviousStep();
                steps4.backPreviousStep();
                steps5.backPreviousStep();
                steps6.backPreviousStep();
            }
        });
    }
}
