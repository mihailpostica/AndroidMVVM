package com.example.project.Services;
import com.example.project.Model.UserPost;
import com.example.project.Retrofit.PostsApi;
import java.util.List;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostsService {
    private static PostsService instance;
    public static final String BASE_URL = "http://10.0.2.2:8000";

    private PostsApi api =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(PostsApi.class);
//singleton as no di was implemented due to lack of time
    private PostsService(){
    }
   public static PostsService getInstance(){
        if (instance==null){
            instance=new PostsService();
        }
        return instance;
   }
    public Single<List<UserPost>> getPosts() {
        return api.getUserPosts();
    }
}
