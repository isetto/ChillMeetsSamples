package com.example.ad.retrofittest.Retrofit.Services;

import android.support.v4.media.session.PlaybackStateCompat;

import com.example.ad.retrofittest.Model.Activity;
import com.example.ad.retrofittest.Model.Event.EventEdit;
import com.example.ad.retrofittest.Model.Event.EventFull;
import com.example.ad.retrofittest.Model.Event.EventMin;
import com.example.ad.retrofittest.Model.Event.FiltrEvents;
import com.example.ad.retrofittest.Model.EventUserMin;
import com.example.ad.retrofittest.Model.Notifications;
import com.example.ad.retrofittest.Model.Photo.PhotoEdit;
import com.example.ad.retrofittest.Model.Photo.PhotoMin;
import com.example.ad.retrofittest.Model.StatusResponse;
import com.example.ad.retrofittest.Model.UserNewModel.User;
import com.example.ad.retrofittest.Model.UserNewModel.UserEdit;
import com.example.ad.retrofittest.Model.UserNewModel.UserMin;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface NewServices {

    // PROFILE

    @GET
    Observable <User> IgetProfile(@Url String url);

    @PUT
    Observable <UserEdit> IupdateProfile(@Url String url,
                                            @Body UserEdit user);

    @GET
    Observable <User> Iabout(@Url String url);
    // END OF PROFILE



    @POST("")
    @FormUrlEncoded
    Observable <List <UserMin>> IgetUsersContacted();

    // FRIENDS

    @GET
    Observable <List <UserMin>> IgetFriendsList(@Url String url);

    @GET
    Observable <StatusResponse> Iconfirm(@Url String url);

    @GET
    Observable <StatusResponse> Idecline(@Url String url);

    @GET
    Observable <StatusResponse> IremoveFriend(@Url String url);

    @GET("friends/block/{friend_id}")
    Observable <StatusResponse> Iblock(@Url String url);

    @GET("friends/unblock/{friend_id}")
    Observable <StatusResponse> Iunblock(@Url String url);

    @GET
    Observable <StatusResponse> IAddFriends(@Url String url);

    //END OF FRIENDS

    @GET
    Observable <StatusResponse> INotificationConfirmation(@Url String url);

    // MARKERS

    @GET
    Observable <List <EventMin>> IgetAllActivities(@Url String url);

    @POST
    Observable <EventFull> ImarkerSend(@Url String url,
                                       @Body EventEdit activity);

    //END OF MARKERS

    // NOTIFICATIONS


    @GET
    Observable <List <Notifications>> Inotifications(@Url String url);


    // END OF NOTIFICATIONS



    // Images


    @POST("")
    @FormUrlEncoded
    Call <List <UserMin>> iGetProfileImage();


    @GET
    Observable <User> IgetImages(@Url String url);

    @POST
    Observable  <PhotoEdit> IsendImage(@Url String url,
                                     @Body PhotoEdit photo);

    @PUT
    Observable <User> IupdateImage(@Url String url,
                              @Body PhotoEdit photoEdit);

    @DELETE
    Observable <StatusResponse> IdeleteImage(@Url String url);


    // END OF Images

    // ACTIVITIES


    @GET
    Observable<EventFull> igetActivityDetails(@Url String url);

    @PUT
    Observable<EventFull> IeditActivity(@Url String url, @Body EventEdit activity);

    @POST
    Observable<StatusResponse> IEditMods(@Url String url,
                                         @Body ArrayList<Integer> list);

    @POST
    Observable<List<EventMin>> IFiltrEvents(@Url String url,
                                         @Body FiltrEvents filtrEvent);

    @DELETE
    Observable<StatusResponse> IDeleteParticipant(@Url String url,
                                                  @Body ArrayList<Integer> list);

    @GET
    Observable <List <EventMin>> IgetActivities(@Url String url);

    @GET
    Observable <EventFull> IJoinActivity(@Url String url);



    // END OF ACTIVITIES

    // LIKES

    @POST("backendAdrian/ratings/addLike.php")
    @FormUrlEncoded
    Observable <StatusResponse> addLike(@Field("user_id") int user_id,
                                     @Field("rater_id") int rater_id,
                                     @Field("activity_id") int activity_id);

    @POST("backendAdrian/ratings/undoLike.php")
    @FormUrlEncoded
    Observable  <StatusResponse> undoLike(@Field("user_id") int user_id,
                                      @Field("rater_id") int rater_id,
                                      @Field("activity_id") int activity_id);

    // END OF LIKES
}


