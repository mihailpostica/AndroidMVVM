package com.example.project.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.project.Model.UserPost;
import com.example.project.Services.PostsService;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class UserPostViewModel extends ViewModel {
    public MutableLiveData<List<UserPost>> posts = new MutableLiveData<List<UserPost>>();
    public MutableLiveData<Boolean> postLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private PostsService postsService=PostsService.getInstance();
    public void refresh() {
        fetchPosts();
    }

    private void fetchPosts() {
        loading.setValue(true);
        disposable.add(
                postsService.getPosts()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<UserPost>>() {
                            @Override
                            public void onSuccess(List<UserPost> userPosts) {
                                posts.setValue(userPosts);
                                postLoadError.setValue(false);
                                loading.setValue(false);
                            }
                            @Override
                            public void onError(Throwable e) {
                                postLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })
        );
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
    public UserPostViewModel() {
        super();
    }
}
