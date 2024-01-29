package edu.ucsd.cse110.secards.app;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.ucsd.cse110.secards.app.databinding.ActivityMainBinding;
import edu.ucsd.cse110.secards.lib.data.InMemoryDataSource;
import edu.ucsd.cse110.secards.lib.domain.FlashcardRepository;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding view;
    private MainViewModel model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_title);

        // Initialize the Model
        var dataSource = InMemoryDataSource.fromDefault();
        this.model = new MainViewModel(new FlashcardRepository(dataSource));

        // Initialize the View
        this.view = ActivityMainBinding.inflate(getLayoutInflater());

        // Observe Model -> call View
        model.getDisplayedText().observe(text -> view.cardText.setText(text));

        // Observe View -> call Model
        view.card.setOnClickListener(v -> model.flipTopCard());
        view.nextButton.setOnClickListener(v -> model.stepForward());
        view.prevButton.setOnClickListener(v -> model.stepBackward());
        view.shuffleButton.setOnClickListener(v -> model.shuffle());

        setContentView(view.getRoot());
    }
}
