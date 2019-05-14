package com.kerry.gogobasketball;

import android.widget.ImageView;

import com.kerry.gogobasketball.util.Constants;
import com.kerry.gogobasketball.util.ImageManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.junit.Assert.*;

public class ImageMangerTest {
    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    ImageView mockPositionImage;

    @Mock
    ImageView mockGenderImage;


    ImageView mPositionImage;
    ImageView mGenderImage;

    @Before
    public void setup() throws Exception {
        mPositionImage.setImageResource(R.drawable.ic_position_center);
//        mGenderImage.setImageResource(R.drawable.ic_female);
    }

    @Test
    public void testPositionImage() {
        ImageManager.getInstance().setGenderImage(mockPositionImage, Constants.GENDER_FEMALE);
        assertEquals(mPositionImage, mockPositionImage);
    }

    @Test
    public void testGenderImage() {
        ImageManager.getInstance().setGenderImage(mockGenderImage, Constants.POSITION_CENTER);
        assertEquals(mGenderImage, mockGenderImage);

    }

}
