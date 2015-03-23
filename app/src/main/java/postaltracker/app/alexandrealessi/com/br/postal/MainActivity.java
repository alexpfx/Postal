package postaltracker.app.alexandrealessi.com.br.postal;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import postaltracker.app.alexandrealessi.com.br.postal.view.OverflowMenuViewAdapter;

import static postaltracker.app.alexandrealessi.com.br.postal.view.OverflowMenuViewAdapter.ViewModel;

public class MainActivity extends ActionBarActivity implements OverflowMenuViewAdapter.OnOverflowMenuItemClickListener{

    private static final String tag = MainActivity.class.getSimpleName();

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.rvOverflowMenu)
    RecyclerView overflowMenu;

    @InjectView(R.id.dwrOverflowMenu)
    DrawerLayout drawerLayout;

    private RecyclerView.Adapter overflowMenuAdapter;

    private ActionBarDrawerToggle drawerToggle;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.d(tag, "Start na aplicação");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        setupToolbar();
        setupOverflowMenu();
        setupDrawerLayout();

    }


    private void setupDrawerLayout() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.abrir, R.string.fechar);
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }


    private void setupOverflowMenu() {
        overflowMenu.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        overflowMenu.setLayoutManager(layoutManager);
        overflowMenuAdapter = new OverflowMenuViewAdapter(getApplicationContext(), createOverflowMenuListModel(), this);
        overflowMenu.setAdapter(overflowMenuAdapter);
    }

    private List<ViewModel> createOverflowMenuListModel() {
        List<ViewModel> lista = new ArrayList<>();
        ViewModel.create(android.R.drawable.ic_menu_call, getString(R.string.menu_item_meus_pacotes)).andAddTo(lista);
        ViewModel.create(android.R.drawable.ic_menu_camera, getString(R.string.menu_item_consultas_uteis)).andAddTo(lista);
        return lista;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

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
