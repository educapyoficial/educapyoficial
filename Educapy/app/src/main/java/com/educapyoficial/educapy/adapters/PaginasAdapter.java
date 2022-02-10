package com.educapyoficial.educapy.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.educapyoficial.educapy.fragments.chatsFragment;
import com.educapyoficial.educapy.fragments.mis_solicitudes_Fragment;
import com.educapyoficial.educapy.fragments.solicitudesFragment;
import com.educapyoficial.educapy.fragments.usuariosFragment;


public class PaginasAdapter extends FragmentStateAdapter {

    FragmentActivity fragmentActivity;
    usuariosFragment usuariosFragment;

    public PaginasAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                usuariosFragment = new usuariosFragment();
                Bundle bundle = new Bundle();
                bundle.putString("uidCurso", this.fragmentActivity.getIntent().getStringExtra("uidCurso"));
                bundle.putString("uid", this.fragmentActivity.getIntent().getStringExtra("uid"));
                usuariosFragment.setArguments(bundle);
                return usuariosFragment;

            case 1:
                chatsFragment fragment2 = new chatsFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putString("uidCurso", this.fragmentActivity.getIntent().getStringExtra("uidCurso"));
                bundle2.putString("uid", this.fragmentActivity.getIntent().getStringExtra("uid"));
                if (usuariosFragment != null) {
                    if (usuariosFragment.usersArrayList != null){
                        //bundle2.putSerializable("listaUsuarios", usuariosFragment.usersArrayList);
                    }

                }
                fragment2.setArguments(bundle2);
                return fragment2;

           /* case 2 :
                return new solicitudesFragment();*/

            case 2:
                return new mis_solicitudes_Fragment();

            default:
                return new usuariosFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
