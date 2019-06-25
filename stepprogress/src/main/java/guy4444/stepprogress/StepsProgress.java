package guy4444.stepprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StepsProgress extends LinearLayout {

    public enum STEP_STATE {
        ACTIVE,
        INACTIVE,
        SKIPPED,
        COMPLETED
    }

    private enum LINE_ORIENTATION {
        HORIZONTAL,
        VERTICAL
    }

    private int lineWidth;
    private int linePadding;
    private LINE_ORIENTATION lineOrientation = LINE_ORIENTATION.HORIZONTAL;

    private int numOfSteps = -1;
    private int currentStep = -1;

    private float WEIGHT_MARKER = 1.0f;
    private float WEIGHT_LINE = 1.0f;

    public StepsProgress(Context context) {
        super(context);
        initComponent();
    }

    public StepsProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        handelAttributes(attrs);
        initComponent();
    }

    public StepsProgress(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        handelAttributes(attrs);
        initComponent();
    }

    private void initComponent() {
        this.setOrientation(lineOrientation.ordinal());
        this.setGravity(Gravity.CENTER);

        LayoutParams layoutParams;
        layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.setLayoutParams(layoutParams);
    }

    private int activeStepColor;
    private int inActiveStepColor;
    private int skipStepColor;

    /**
     * Function to handel view attributes
     *
     * @param attrs Attrs from xml
     */
    private void handelAttributes(AttributeSet attrs) {

        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.StepsProgress, 0, 0);
        try {

            int lineOrientationIndex = typedArray.getInt(R.styleable.StepsProgress_step_orientation, 0);
            lineOrientation = LINE_ORIENTATION.values()[lineOrientationIndex];
            lineWidth = typedArray.getDimensionPixelSize(R.styleable.StepsProgress_line_width, 20);
            linePadding = typedArray.getDimensionPixelSize(R.styleable.StepsProgress_line_padding, 20);
            activeStepColor = typedArray.getColor(R.styleable.StepsProgress_inActive_step_color, Color.parseColor("#0000FF"));
            inActiveStepColor = typedArray.getColor(R.styleable.StepsProgress_active_step_color, Color.DKGRAY);
            skipStepColor = typedArray.getColor(R.styleable.StepsProgress_skip_step_color, activeStepColor);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Function to add view to component
     *
     * @param tag              View tag usually the step number
     * @param drawableResource drawable resource of view
     */
    private void addView(int tag, @DrawableRes int drawableResource, boolean isLastStep) {
        ImageView myImage = new ImageView(getContext());
        myImage.setImageResource(R.drawable.ic_marker_active);
        myImage.setColorFilter(inActiveStepColor, android.graphics.PorterDuff.Mode.SRC_IN);

        LayoutParams layoutParams;
        if (lineOrientation == LINE_ORIENTATION.HORIZONTAL)
            layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        else
            layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, 0);

        layoutParams.weight = 1;
        myImage.setLayoutParams(layoutParams);
        myImage.setPadding(1, 1, 1, 1);
        myImage.setTag(tag);
        //myImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
        myImage.setAdjustViewBounds(true);
        myImage.setBackgroundColor(0);

        this.addView(myImage);

        if (!isLastStep) {
            ImageView myImage2 = new ImageView(getContext());
            myImage2.setImageResource(R.drawable.rectangle);
            myImage2.setColorFilter(Color.GRAY, android.graphics.PorterDuff.Mode.SRC_IN);

            if (lineOrientation == LINE_ORIENTATION.HORIZONTAL)
                myImage2.setPadding(linePadding,0,linePadding,0);
            else
                myImage2.setPadding(0, linePadding, 0, linePadding);

            LayoutParams layoutParams2;
            if (lineOrientation == LINE_ORIENTATION.HORIZONTAL)
                layoutParams2 = new LayoutParams(0, lineWidth);
            else
                layoutParams2 = new LayoutParams(lineWidth, 0);

            layoutParams2.weight = WEIGHT_LINE;
            myImage2.setLayoutParams(layoutParams2);

            this.addView(myImage2);
        }

    }

    /**
     * Function to set step color status
     *
     * @param step_state step state
     * @param view       View to set
     */
    private void setStepStatus(STEP_STATE step_state, View view) {
        switch (step_state) {
            case ACTIVE:
                ((ImageView)view).setColorFilter(inActiveStepColor, android.graphics.PorterDuff.Mode.SRC_IN);
                ((ImageView)view).setImageResource(R.drawable.ic_marker_active);
                break;
            case INACTIVE:
                ((ImageView)view).setColorFilter(inActiveStepColor, android.graphics.PorterDuff.Mode.SRC_IN);
                ((ImageView)view).setImageResource(R.drawable.ic_marker_inactive);
                break;
            case SKIPPED:
                ((ImageView)view).setColorFilter(skipStepColor, android.graphics.PorterDuff.Mode.SRC_IN);
                ((ImageView)view).setImageResource(R.drawable.ic_marker_skipped);
                break;
            case COMPLETED:
                ((ImageView)view).setColorFilter(activeStepColor, android.graphics.PorterDuff.Mode.SRC_IN);
                ((ImageView)view).setImageResource(R.drawable.ic_marker_completed);
                break;
        }
    }

    /**
     * Function to add steps to component
     *
     * @param _numOfSteps number of steps for init.
     */
    public void initSteps(int _numOfSteps) {

        this.numOfSteps = _numOfSteps;

        if (numOfSteps > 30) {
            WEIGHT_LINE = 0.1f;
        }
        else if (numOfSteps > 20) {
            WEIGHT_LINE = 0.2f;
        }
        else if (numOfSteps > 15) {
            WEIGHT_LINE = 0.4f;
        }
        else if (numOfSteps > 10) {
            WEIGHT_LINE = 0.5f;
        }

        float divider = (float) Math.pow(numOfSteps, 2);
        float weight = 100.f / divider;
        WEIGHT_LINE = (float) Math.min(weight, 1.4);

        int x = this.getMeasuredWidth();
        int y = this.getMeasuredHeight();

        Log.d("pttt", "x= " + x);
        Log.d("pttt", "y= " + y);

        //this.setBackgroundColor(Color.parseColor("#353F51B5"));

        int drawableId = R.drawable.ic_marker_inactive;
        currentStep = 0;
        this.removeAllViews();

        for (int i = 0; i < numOfSteps; i++) {
            addView(i, drawableId, (i+1) == numOfSteps);
        }


        stepInactive();
    }

    /**
     * Function to skip & move to the next step
     */
    public void stepSkipped() {
        if (currentStep < numOfSteps) {
            View view = this.findViewWithTag(currentStep);
            setStepStatus(STEP_STATE.SKIPPED, view);
        }
        currentStep++;
        stepInactive();
    }

    /**
     * Function to move to the next step
     */
    public void stepCompleted() {
        if (currentStep < numOfSteps) {
            View view = this.findViewWithTag(currentStep);
            setStepStatus(STEP_STATE.COMPLETED, view);
        }
        currentStep++;
        stepInactive();
    }

    private void stepInactive() {
        if (currentStep < numOfSteps) {
            View view = this.findViewWithTag(currentStep);
            setStepStatus(STEP_STATE.INACTIVE, view);
        }
    }
}
