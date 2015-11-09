package kr.applepi.coolapp.controllers;


import kr.applepi.coolapp.models.GithubUser;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by bingraa on 15. 11. 9..
 */
public interface Github {
    @GET("users/{username}")
    Observable<GithubUser> getGithubUser(@Path("username") String username);
}
