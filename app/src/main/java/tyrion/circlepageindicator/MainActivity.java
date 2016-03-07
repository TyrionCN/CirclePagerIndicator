package tyrion.circlepageindicator;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager mTestVp;
    private CirclePagerIndicator mTestCpi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTestVp = (ViewPager) findViewById(R.id.test_vp);
        mTestCpi = (CirclePagerIndicator) findViewById(R.id.test_cpi);
        mTestVp.setAdapter(new ViewPagerAdapter(addView()));
        mTestVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mTestCpi.moveCircle(position, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<View> addView() {
        List<View> views = new ArrayList<>();
        LayoutInflater inflater = LayoutInflater.from(this);

        View view1 = inflater.inflate(R.layout.vp_item1, null);
        View view2 = inflater.inflate(R.layout.vp_item2, null);
        View view3 = inflater.inflate(R.layout.vp_item3, null);
        views.add(view1);
        views.add(view2);
        views.add(view3);
        return views;
    }

    private class ViewPagerAdapter extends PagerAdapter {
        private List<View> mViews;

        public ViewPagerAdapter(List<View> views) {
            mViews = views;
        }

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

    }
}
