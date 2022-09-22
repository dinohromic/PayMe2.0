package helpers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;


public class GroupPageAdapter extends FragmentPagerAdapter {


    private final ArrayList<Fragment> groupPageFragment = new ArrayList<>();
    private final ArrayList<String> fragmentTitle = new ArrayList<>();


    public GroupPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return groupPageFragment.get(position);
    }

    @Override
    public int getCount() {
        return groupPageFragment.size();
    }

    public void addGroupPageFragments (Fragment fragment, String title){
        groupPageFragment.add(fragment);
        fragmentTitle.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentTitle.get(position);
    }
}
