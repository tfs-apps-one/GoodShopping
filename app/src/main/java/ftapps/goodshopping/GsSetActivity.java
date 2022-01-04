package ftapps.goodshopping;

import android.os.Bundle;
import android.preference.PreferenceActivity;
/**
 * Created by FURUKAWA on 2017/01/18.
 */

public class GsSetActivity extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new GsSetFragment()).commit();
    }
}