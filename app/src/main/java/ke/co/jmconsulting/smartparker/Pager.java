package ke.co.jmconsulting.smartparker;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by jmulandi on 1/17/2017.
 */
public class Pager extends FragmentStatePagerAdapter {

    int tabCount;

    public Pager(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Details details = new Details();
                return details;
            case 1:
                List list = new List();
                return list;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
