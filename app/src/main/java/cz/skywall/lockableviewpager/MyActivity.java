package cz.skywall.lockableviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;

import java.util.ArrayList;

import cz.skywall.lockableviewpager.slidingtab.SlidingTabLayout;


public class MyActivity extends ActionBarActivity {

    public static final int PAGES_TO_LOAD = 10; // number of pages loaded
    public static final int PAGE_LIMIT_TO_LOAD = 5; // load new pages when ve reach page lower than this number

    int defaultDataPos = 0; // position of data, where ve start
    int defaultPage = 0;  // defaultPage - might change, if we put fragments to left positions in viewpager
    int currentPage = 0;

    ViewPager mPager;
    private SlidingTabLayout mSlidingTabLayout;

    Data data = new Data();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new CalendarPA(getSupportFragmentManager(), mPager));

        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tab);
        mSlidingTabLayout.setViewPager(mPager);
        mSlidingTabLayout.setOnPageChangeListener(pageL);

        for(int i = 1; i < 10; i++) { // load some content at startup
            ((CalendarPA)mPager.getAdapter())
                    .addFragmentLeft(defaultDataPos + i);
            mPager.setCurrentItem(mPager.getCurrentItem() + 1, false);
            ((CalendarPA)mPager.getAdapter())
                    .addFragmentRight(defaultDataPos - i);
        }
    }

    ViewPager.SimpleOnPageChangeListener pageL = new ViewPager.SimpleOnPageChangeListener() {

        private int mState = -1;
        boolean pageSelected = false;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            // figure out if we really scrolled to *new* position
            if (pageSelected &&
                    (mState == ViewPager.SCROLL_STATE_SETTLING || mState == ViewPager.SCROLL_STATE_IDLE)) {

                currentPage = position;
                pageSelected = false;

                int addedFragmentsCount = 0;
                CalendarPA adapter = (CalendarPA) mPager.getAdapter();

                if(currentPage >= 0 && currentPage < PAGE_LIMIT_TO_LOAD) {
                    int limit = PAGES_TO_LOAD - currentPage; // number of pages loaded depends on page we're at
                    for(int i = 0; i < limit; i++) {
                        addedFragmentsCount += adapter.addFragmentLeft(defaultDataPos + (defaultPage + currentPage) +
                                (currentPage == 0 ? 1 : 0)); // currentPage == 0 -> we must not look for data at pos + 1
                    }
                    adapter.moveToRight(addedFragmentsCount); // rotate right
                }

                int offset = mPager.getAdapter().getCount() - currentPage;

                if(offset > 0 && offset < PAGE_LIMIT_TO_LOAD) {
                    int limit = PAGES_TO_LOAD - offset; // number of pages loaded depends on page we're at
                    for(int i = 0; i < limit; i++) {
                        adapter.addFragmentRight(defaultPage - currentPage + defaultDataPos - offset - i);
                    }
                }
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            mState = state;
        }

        @Override
        public void onPageSelected(int position) {
            currentPage = position;
            pageSelected = true; // page has been selected
        }
    };


    // Dummy class which provides some data
    public class Data {

        ArrayList<String> strings = new ArrayList<String>();

        public Data() {
            initStrings();
        }

        public void initStrings() {
            strings.add("A");
            strings.add("B");
            strings.add("C");
            strings.add("D");
            strings.add("E");
            strings.add("F");
            strings.add("G");
            strings.add("H");
            strings.add("CH");
            strings.add("I");
            strings.add("J");
            strings.add("K");
            strings.add("L");
            strings.add("M");
            strings.add("A");
            strings.add("B");
            strings.add("C");
            strings.add("D");
            strings.add("E");
            strings.add("F");
            strings.add("G");
            strings.add("H");
            strings.add("CH");
            strings.add("I");
            strings.add("J");
            strings.add("K");
            strings.add("L");
            strings.add("M");
            strings.add("A");
            strings.add("B");
            strings.add("C");
            strings.add("D");
            strings.add("E");
            strings.add("F");
            strings.add("G");
            strings.add("H");
            strings.add("CH");
            strings.add("I");
            strings.add("J");
            strings.add("K");
            strings.add("L");
            strings.add("M");
        }

        public String getMyString(int pos) {
            if (pos < 0 || pos >= strings.size()) {
                return null;
            }
            return strings.get(pos);
        }
    }


    public class CalendarPA extends FragmentStatePagerAdapter {

        ArrayList<DummyFragment> mPagerFragments;
        ViewPager mPager;

        public CalendarPA(FragmentManager fm, ViewPager viewPager) {
            super(fm);
            mPager = viewPager;
            mPagerFragments = new ArrayList<DummyFragment>(); // first - pos 0 fragment
            mPagerFragments.add(new DummyFragment());
        }

        @Override
        public Fragment getItem(int pos) {
            DummyFragment df = mPagerFragments.get(pos);
            df.setData(data.getMyString(positionToDataPosition(pos)));
            return df;
        }

        @Override
        public int getCount() {
            return mPagerFragments.size();
        }

        public void moveToRight(int offset) {
            mPager.setCurrentItem(mPager.getCurrentItem() + offset, false);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            mSlidingTabLayout.notifyDataSetChanged();
        }

        public int addFragmentLeft(int dataPos) {
            String text = data.getMyString(dataPos);
            if(text == null) return 0; // no data

            DummyFragment df = new DummyFragment();
            df.setData(text);

            mPagerFragments.add(0, df);
            defaultPage++;
            notifyDataSetChanged();

            return 1; // fragment added
        }

        public int addFragmentRight(int dataPos) {
            String text = data.getMyString(dataPos);
            if(text == null) return 0; // no data

            DummyFragment df = new DummyFragment();
            df.setData(text);

            mPagerFragments.add(mPagerFragments.size(), df);
            notifyDataSetChanged();

            return 1; // fragment added
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        public int positionToDataPosition(int position) {
            int temp = defaultPage - position;
            return defaultDataPos + temp;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "POS :::::" + String.valueOf(positionToDataPosition(position));
        }
    }
}
