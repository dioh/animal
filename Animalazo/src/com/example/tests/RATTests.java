package com.example.tests;

import com.example.animalazo.R;
import com.example.animalazo.RatActivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import junit.framework.TestCase;

public class RATTests extends ActivityInstrumentationTestCase2<RatActivity> {

	public RATTests(Class<RatActivity> activityClass) {
		super(activityClass);
		// TODO Auto-generated constructor stub
	}


	private RatActivity mFirstTestActivity;
	private TextView mFirstTestText;


	@Override
	protected void setUp() throws Exception {
		super.setUp();
//		mFirstTestActivity = getActivity();
//		mFirstTestText =
//				(TextView) mFirstTestActivity
//				.findViewById(R.id.my_first_test_text_view);
	}
}

