package kmitl.finalproject.montita58070114.bingo;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

        onView(withId(R.id.block1)).perform(replaceText("25"));
        onView(withId(R.id.block2)).perform(replaceText("36"));
        onView(withId(R.id.block3)).perform(replaceText("45"));
        onView(withId(R.id.block4)).perform(replaceText("56"));
        onView(withId(R.id.block5)).perform(replaceText("74"));
        onView(withId(R.id.block6)).perform(replaceText("81"));
        onView(withId(R.id.block7)).perform(replaceText("96"));
        onView(withId(R.id.block8)).perform(replaceText("63"));
        onView(withId(R.id.block9)).perform(replaceText("15"));
        closeSoftKeyboard();
        onView(withId(R.id.controlBtn)).check(matches(withText("Start"))).perform(click());
        onView(withId(R.id.controlBtn)).check(matches(withText("Next Round"))).perform(click());
        onView(withId(R.id.controlBtn)).check(matches(withText("Next Round"))).perform(click());
        onView(withId(R.id.controlBtn)).check(matches(withText("Next Round"))).perform(click());
        onView(withId(R.id.controlBtn)).check(matches(withText("Next Round"))).perform(click());
        onView(withId(R.id.controlBtn)).check(matches(withText("Next Round"))).perform(click());
        onView(withId(R.id.controlBtn)).check(matches(withText("Next Round"))).perform(click());
        onView(withId(R.id.controlBtn)).check(matches(withText("Next Round"))).perform(click());
        onView(withId(R.id.controlBtn)).check(matches(withText("Next Round"))).perform(click());
        onView(withId(R.id.controlBtn)).perform(click());

    }
}
