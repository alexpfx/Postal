package postaltracker.app.alexandrealessi.com.br.postal;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import postaltracker.app.alexandrealessi.com.br.postal.view.OverflowMenuViewAdapter;

import static postaltracker.app.alexandrealessi.com.br.postal.view.OverflowMenuViewAdapter.OnOverflowMenuItemClickListener;
import static postaltracker.app.alexandrealessi.com.br.postal.view.OverflowMenuViewAdapter.ViewModel;


public class MainActivity extends ActionBarActivity implements OnOverflowMenuItemClickListener {

    private Toolbar toolbar;
    private RecyclerView overflowMenu;
    private RecyclerView.Adapter overflowMenuAdapt;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolbar();
        setupOverflowMenu();
        setupDrawerLayout ();
    }

    private void setupDrawerLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.dwrOverflowMenu);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.abrir, R.string.fechar);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);

    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }



    private void setupOverflowMenu() {
        overflowMenu = (RecyclerView) findViewById(R.id.list_overflow_menu);
        overflowMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        overflowMenu.setLayoutManager(layoutManager);
        overflowMenuAdapt = new OverflowMenuViewAdapter(getApplicationContext(), createOverflowMenuListModel(), this);
        overflowMenu.setAdapter(overflowMenuAdapt);
    }

    private List<ViewModel> createOverflowMenuListModel() {
        List<ViewModel> lista = new ArrayList<>();
        ViewModel.create(android.R.drawable.ic_menu_call, getString(R.string.menu_item_meus_pacotes)).andAddTo(lista);
        ViewModel.create(android.R.drawable.ic_menu_camera, getString(R.string.menu_item_consultas_uteis)).andAddTo(lista);
        return lista;
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

    @Override
    public void onOverflowMenuItemClick(ViewModel viewModel) {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


}
