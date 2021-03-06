# StepProgress-Android
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/vlad1m1r990/Lemniscate/blob/master/LICENSE)
[![](https://jitpack.io/v/guy-4444/StepProgress-Android.svg)](https://jitpack.io/#guy-4444/StepProgress-Android)
[![API](https://img.shields.io/badge/API-15%2B-green.svg?style=flat)]()

Library to insert a widget that shows progress in steps.

![device-2018-06-06-144912](https://github.com/guy-4444/StepProgress-Android/blob/master/device-2019-02-04-144303.png?raw=true)

Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency:

```
dependencies {
	implementation 'com.github.guy-4444:StepProgress-Android:1.00.02'
}
```
## Usage

###### StepProgress Constructor:
```java
        StepsProgress steps1;
        steps1 = (StepsProgress) findViewById(R.id.steps1);
        steps1.initSteps(6);  // number of steps
```

###### StepProgress xml parameters:

```xml
        <guy4444.stepprogress.StepsProgress
            android:id="@+id/steps5"
            android:layout_width="40dp"
            android:layout_height="340dp"
            android:layout_below="@id/steps4"
            android:layout_marginTop="3dp"
            app:skip_step_color="#FF0000"
            app:step_orientation="vertical"
            app:line_padding="0dp"
            app:line_width="1dp" />
```

**app:inActive_step_color="#0073FF"**

**app:active_step_color="#CD35A3D5"**

**app:skip_step_color="#FF0000"**

**app:line_width="5dp"**

**app:line_padding="10dp"**

**app:step_orientation="horizontal"**

###### StepView Control ways:
```java
       steps1.stepCompleted();
       steps1.stepSkipped();
       steps1.backPreviousStep();
```

## What's new
1.00.01:
1. separate skip indicator color.

1.00.02:
2. implement back to previous step.


## License

    Copyright 2018 Guy Isakov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


