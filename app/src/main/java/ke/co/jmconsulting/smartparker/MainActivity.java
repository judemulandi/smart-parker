package ke.co.jmconsulting.smartparker;

import android.app.TabActivity;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    Vehicle vehicle = new Vehicle();
    List<Vehicle> vehicles= new ArrayList<Vehicle>();
    VehicleAdapter adapter = null;
    EditText number = null;
    EditText destination = null;
    RadioGroup types = null;
    EditText note = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout)findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("List"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager)findViewById(R.id.viewpager);

        Pager pager = new Pager(getSupportFragmentManager(),tabLayout.getTabCount());

        viewPager.setAdapter(pager);

        tabLayout.setOnTabSelectedListener(this);

        number = (EditText)findViewById(R.id.number);
        destination = (EditText)findViewById(R.id.destination);
        types = (RadioGroup)findViewById(R.id.types);
        note = (EditText)findViewById(R.id.note);

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);

        ListView list = (ListView)findViewById(R.id.vehicles);

        adapter = new VehicleAdapter();

        list.setAdapter(adapter);
        list.setOnItemClickListener(onListClick);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected View.OnClickListener onSave = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            //implement autocomplete text view, enables fuzzy search from db

            vehicle.setNumber(number.getText().toString());
            vehicle.setDestination(destination.getText().toString());
            vehicle.setNote(note.getText().toString());

            switch (types.getCheckedRadioButtonId()){
                case R.id.resident:
                    vehicle.setType("resident");
                    break;

                case R.id.guest:
                    vehicle.setType("guest");
                    break;
            }

            adapter.add(vehicle);
        }
    };

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            vehicle=vehicles.get(position);
            number.setText(vehicle.getNumber());
            destination.setText(vehicle.getDestination());
            if(vehicle.getType().equals("resident")){
                types.check(R.id.resident);
            }else{
                types.check(R.id.guest);
            }
            note.setText(vehicle.getNote());

        }
    };

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }


    class VehicleAdapter extends ArrayAdapter<Vehicle> {
      VehicleAdapter(){
          super(MainActivity.this, R.layout.row, vehicles);
      }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            VehicleHolder holder = null;
            if(row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.row, parent, false);
                holder= new VehicleHolder(row);
                row.setTag(holder);
            }else{
                holder=(VehicleHolder)row.getTag();
            }

            holder.populateFrom(vehicles.get(position));
            return(row);
        }
    }

    static class VehicleHolder{
        private TextView number=null;
        private TextView destination=null;
        private ImageView icon=null;
        private View row=null;

        VehicleHolder(View row){
            this.row=row;
            number=(TextView)row.findViewById(R.id.number);
            destination=(TextView)row.findViewById(R.id.destination);
            icon=(ImageView)row.findViewById(R.id.icon);
        }

        void populateFrom(Vehicle vehicle){
            number.setText(vehicle.getNumber());
            destination.setText(vehicle.getDestination());

            if(!vehicle.isParked()){
                icon.setImageResource(R.drawable.in);
            }

        }

    }

}
