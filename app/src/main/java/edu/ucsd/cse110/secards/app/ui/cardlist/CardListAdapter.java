package edu.ucsd.cse110.secards.app.ui.cardlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import edu.ucsd.cse110.secards.app.databinding.ListItemCardBinding;
import edu.ucsd.cse110.secards.lib.domain.Flashcard;
public class CardListAdapter extends ArrayAdapter<Flashcard>{
    public CardListAdapter(Context context, List<Flashcard> flashcards){
        super(context, 0, new ArrayList<>(flashcards));
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        var flashcard = getItem(position);
        assert flashcard != null;

        ListItemCardBinding binding;
        if(convertView != null){
            binding = ListItemCardBinding.bind(convertView);
        }else{
            var layoutInflater = LayoutInflater.from(getContext());
            binding = ListItemCardBinding.inflate(layoutInflater, parent, false);
        }

        binding.frontText.setText(flashcard.front());
        binding.backText.setText(flashcard.back());

        return binding.getRoot();
    }

    @Override
    public boolean hasStableIds(){
        return true;
    }

    @Override
    public long getItemId(int position){
        var flashcard = getItem(position);
        assert flashcard != null;

        var id = flashcard.id();
        assert id != null;

        return id;
    }
}
