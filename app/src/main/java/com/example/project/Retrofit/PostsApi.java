package com.example.project.Retrofit;
import com.example.project.Model.UserPost;
import java.util.List;
import io.reactivex.Single;
import retrofit2.http.GET;


public interface PostsApi {

    @GET("/api/android")
    Single<List<UserPost>> getUserPosts();
}
