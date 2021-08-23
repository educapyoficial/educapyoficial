package com.educapyoficial.educapy.SendNotificationPack;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAANtFpiDk:APA91bEWuP3fTIKEHGJ0uv9H5vI5eF78bet0VICafCJ_NS88fd8-80ny_quPABk962BE1_-S7U4wgCdhHqWHlUMWt20Dz0V0HsTPC_dOm_1m2cDXarX9dYEZt9havZ1hHiSQPXq_M5Au"
            }
    )


    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);
}

