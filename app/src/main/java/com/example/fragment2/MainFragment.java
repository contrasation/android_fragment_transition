package com.example.fragment2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.fragment2.dummy.DummyContent;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container , false);
        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // ListViewに表示するデータ
        final ArrayList<String> items = new ArrayList<>();
        items.add("機能1");
        items.add("機能2");
        items.add("機能3");

        // ListViewをセット
        final ArrayAdapter adapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_list_item_1, items);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        // セルを選択されたら詳細画面フラグメントを呼び出す
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0) {
                    //　詳細画面へ値を渡す
                    DetailFragment fragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Selected", position);
                    fragment.setArguments(bundle);

                    // 詳細画面を呼び出す
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.main_fragment, fragment);

                    //戻るボタンで戻ってこられるように
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

                if (position == 1){

//                    final ArrayList<String> data_list = new ArrayList<>();
//                    data_list.add("機能1");
//                    data_list.add("機能2");
//                    data_list.add("機能3");

                    DataListFragment fragment = DataListFragment.newInstance(1);
                    //どんなデータを渡せばよい？？
                    Bundle bundle = new Bundle();
                    bundle.putInt("Selected", position);
                    fragment.setArguments(bundle);


                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.list, fragment);

                    //戻るボタンで戻ってこられるように
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
    }

}
