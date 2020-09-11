package gabe.hosanna.gadsleaderboard.network.retrofit;

import gabe.hosanna.gadsleaderboard.models.LearningModel;
import gabe.hosanna.gadsleaderboard.models.SkillModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitApiCalls {

    @GET("/api/hours")
    Call<List<LearningModel>> getLearningLeaders();

    @GET("/api/skilliq")
    Call<List<SkillModel>> getSkillLeaders();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<ResponseBody> submitGoogleForm(@Field("entry.1824927963") String email_address, @Field("entry.1877115667") String first_name,
                                        @Field("entry.2006916086") String last_name, @Field("entry.284483984") String project_link);
}
