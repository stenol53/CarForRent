package com.voak.android.rentnewcar.api

import com.voak.android.rentnewcar.model.Car
import com.voak.android.rentnewcar.model.History
import com.voak.android.rentnewcar.model.UserData
import retrofit2.Response
import retrofit2.http.*

interface RentNewCarApiService {
    @FormUrlEncoded
    @POST("makeAuth.php")
    suspend fun login(
        @Field("login") login: String,
        @Field("password") password: String
    ): Response<UserData>

    @GET("getUser.php")
    suspend fun getUser(@Query("login") login: String): Response<UserData>

    @FormUrlEncoded
    @POST("Cl_registration.php")
    suspend fun registerClient(
        @Field("login") login: String,
        @Field("password") password: String,
        @Field("first_name") firstName: String,
        @Field("second_name") secondName: String,
        @Field("middle_name") middleName: String,
        @Field("address") address: String,
        @Field("phone") phone: String
    ) : Response<String>

    @FormUrlEncoded
    @POST("Cl_update.php")
    suspend fun updateClientInfo(
        @Field("id_client") clientId: Int,
        @Field("login") login: String?,
//        @Field("password") password: String?,
        @Field("first_name") firstName: String,
        @Field("second_name") secondName: String,
        @Field("middle_name") middleName: String,
        @Field("address") address: String,
        @Field("phone") phone: String
    ): Response<String>

    @FormUrlEncoded
    @POST("updClientPas.php")
    suspend fun updateClientPassword(
        @Field("id_client") clientId: Int,
        @Field("old_pas") oldPassword: String,
        @Field("new_pas") newPassword: String
    ): Response<String>

    @GET("GetCarsByStatus.php")
    suspend fun getCars(@Query("status") status: String) : Response<List<Car>>

    @GET("GetCarById.php")
    suspend fun getCarById(@Query("id_car") carId: Int): Response<Car>

    @FormUrlEncoded
    @POST("AddHistory.php")
    suspend fun makeOrder(
        @Field("id_client") clientId: Int,
        @Field("id_car") carId: Int,
        @Field("date_issue") startDate: Long,
        @Field("date_return") endDate: Long,
        @Field("state") state: String
    ) : Response<String>

    @GET("GetClientHistAndroid.php")
    suspend fun getHistories(@Query("id_client") clientId: Int) : Response<List<History>>

}