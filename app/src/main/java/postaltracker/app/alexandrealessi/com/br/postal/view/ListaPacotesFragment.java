package postaltracker.app.alexandrealessi.com.br.postal.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import postaltracker.app.alexandrealessi.com.br.postal.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListaPacotesFragment extends Fragment {


    private RecyclerView recyclerView;
    private ListPacotesAdapter adapter;

    public ListaPacotesFragment() {


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_lista_pacotes, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.listPacotes);
        recyclerView.setHasFixedSize(false);


        LinearLayoutManager lm = new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        adapter = new ListPacotesAdapter(createFakeItems());
        recyclerView.setAdapter(adapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),  null));

        return v;
    }

    private Random rand = new Random();
    private List<ListPacotesAdapter.Item> createFakeItems() {
        return new ArrayList<ListPacotesAdapter.Item>(){
            {

                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));

                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));

                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));

                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("destinatário não apresentou-se para receber", new Date (rand.nextLong()),   "DM 123456789 BR", new ArrayList<String>(){
                    {
                        add("Submarino");
                        add("laptop");
                    }
                }));
                add(new ListPacotesAdapter.Item("entregue", new Date (rand.nextLong()), "DP 123646449 AR", new ArrayList<String>(){
                    {
                        add("xxxxxxxxxx");
                        add("xxxxxxxxx");
                        add("xxxxxxxxx");
                    }
                }));

                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("destinatário não apresentou-se para receber", new Date (rand.nextLong()),   "DM 123456789 BR", new ArrayList<String>(){
                    {
                        add("Submarino");
                        add("laptop");
                    }
                }));
                add(new ListPacotesAdapter.Item("entregue", new Date (rand.nextLong()), "DP 123646449 AR", new ArrayList<String>(){
                    {
                        add("xxxxxxxxxx");
                        add("xxxxxxxxx");
                        add("xxxxxxxxx");
                    }
                }));
                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("destinatário não apresentou-se para receber", new Date (rand.nextLong()),   "DM 123456789 BR", new ArrayList<String>(){
                    {
                        add("Submarino");
                        add("laptop");
                    }
                }));
                add(new ListPacotesAdapter.Item("entregue", new Date (rand.nextLong()), "DP 123646449 AR", new ArrayList<String>(){
                    {
                        add("xxxxxxxxxx");
                        add("xxxxxxxxx");
                        add("xxxxxxxxx");
                    }
                }));
                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("destinatário não apresentou-se para receber", new Date (rand.nextLong()),   "DM 123456789 BR", new ArrayList<String>(){
                    {
                        add("Submarino");
                        add("laptop");
                    }
                }));
                add(new ListPacotesAdapter.Item("entregue", new Date (rand.nextLong()), "DP 123646449 AR", new ArrayList<String>(){
                    {
                        add("xxxxxxxxxx");
                        add("xxxxxxxxx");
                        add("xxxxxxxxx");
                    }
                }));
                add(new ListPacotesAdapter.Item("Entrega não efetuada por motivos operacionais", new Date (rand.nextLong()), "DM 543496494 BR", new ArrayList<String>(){
                    {
                        add("Siciliano");
                        add("Livro");
                        add("Presente");
                    }
                }));


                add(new ListPacotesAdapter.Item("destinatário não apresentou-se para receber", new Date (rand.nextLong()),   "DM 123456789 BR", new ArrayList<String>(){
                    {
                        add("Submarino");
                        add("laptop");
                    }
                }));
                add(new ListPacotesAdapter.Item("entregue", new Date (rand.nextLong()), "DP 123646449 AR", new ArrayList<String>(){
                    {
                        add("xxxxxxxxxx");
                        add("xxxxxxxxx");
                        add("xxxxxxxxx");
                    }
                }));

            }
        };


    }


}
