package com.example.project.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.project.R;
import com.example.project.ViewModel.UserPostViewModel;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.list_error)
    TextView listError;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    private UserPostViewModel viewModel;
    private UserPostsListAdapter adapter = new UserPostsListAdapter(new ArrayList<>());

    @BindView(R.id.postsList)
    RecyclerView postsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        viewModel = ViewModelProviders.of(this).get(UserPostViewModel.class);
        viewModel.refresh();

        postsList.setLayoutManager(new LinearLayoutManager(this));
        postsList.setAdapter(adapter);
        refreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            refreshLayout.setRefreshing(false);
        });

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.posts.observe(this, userPosts -> {
            if(userPosts != null) {
                postsList.setVisibility(View.VISIBLE);
                adapter.updatePosts(userPosts);
            }
        });
        viewModel.postLoadError.observe(this, isError -> {
            if(isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }
        });
        viewModel.loading.observe(this, isLoading -> {
            if(isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if(isLoading) {
                    listError.setVisibility(View.GONE);
                    postsList.setVisibility(View.GONE);
                }
            }
        });
    }
}
