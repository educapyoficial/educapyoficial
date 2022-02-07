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

    public PaginasAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentActivity = fragmentActivity;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0 :
                usuariosFragment fragment = new usuariosFragment();
                Bundle bundle = new Bundle();
                bundle.putString("uidCurso", this.fragmentActivity.getIntent().getStringExtra("uidCurso"));
                fragment.setArguments(bundle);
                return fragment;

            case 1 :
                return new chatsFragment();

            case 2 :
                return new solicitudesFragment();

            case 3 :
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
