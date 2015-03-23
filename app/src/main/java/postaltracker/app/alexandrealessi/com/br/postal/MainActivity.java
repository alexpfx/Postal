package postaltracker.app.alexandrealessi.com.br.postal;

import android.app.Fragment;
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
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import postaltracker.app.alexandrealessi.com.br.postal.view.CadastroPacotesFragment;
import postaltracker.app.alexandrealessi.com.br.postal.view.ListaDetalhesFragment;
import postaltracker.app.alexandrealessi.com.br.postal.view.ListaPacotesFragment;
import postaltracker.app.alexandrealessi.com.br.postal.view.overflowmenu.OverflowMenuViewAdapter;
import postaltracker.app.alexandrealessi.com.br.postal.view.overflowmenu.event.OverflowMenuItemClickEvent;

public class MainActivity extends ActionBarActivity {

    private static final String tag = MainActivity.class.getSimpleName();

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @InjectView(R.id.rvOverflowMenu)
    RecyclerView overflowMenu;

    @InjectView(R.id.dwrOverflowMenu)
    DrawerLayout drawerLayout;

    @InjectView(R.id.session_title_textview)
    TextView sessionTitle;

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
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.current_content, new ListaPacotesFragment()).commit();
        }

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
        overflowMenuAdapter = new OverflowMenuViewAdapter(getApplicationContext(), createOverflowMenuListModel());
        overflowMenu.setAdapter(overflowMenuAdapter);
    }

    private List<OverflowMenuViewAdapter.ViewModel> createOverflowMenuListModel() {
        List<OverflowMenuViewAdapter.ViewModel> lista = new ArrayList<>();
        OverflowMenuViewAdapter.ViewModel.create(android.R.drawable.ic_menu_camera, getString(R.string.menu_item_meus_pacotes), ListaPacotesFragment.class).andAddTo(lista);
        OverflowMenuViewAdapter.ViewModel.create(android.R.drawable.ic_menu_agenda, getString(R.string.menu_item_detalhes_pacote), ListaDetalhesFragment.class).andAddTo(lista);
        OverflowMenuViewAdapter.ViewModel.create(android.R.drawable.ic_menu_add, getString(R.string.menu_item_consultar_pacotes), CadastroPacotesFragment.class).andAddTo(lista);
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


    @Subscribe
    public void onOverflowMenuItemClick(OverflowMenuItemClickEvent event)  {
        OverflowMenuViewAdapter.ViewModel viewModel = event.getViewModel();
        sessionTitle.setText(viewModel.getLabel());
        Class<? extends Fragment> fragmentClass = viewModel.getFragmentClass();
        try {
            getFragmentManager().beginTransaction().replace(R.id.current_content,fragmentClass.newInstance()).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        drawerLayout.closeDrawer(overflowMenu);
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


    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        Log.d(tag,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        Log.d(tag,"onPause");
    }
}
