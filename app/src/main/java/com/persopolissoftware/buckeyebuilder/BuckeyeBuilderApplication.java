package com.persopolissoftware.buckeyebuilder;

        import com.parse.Parse;
        import com.parse.ParseFacebookUtils;

        import android.app.Application;
        import android.content.Context;
        import android.content.res.XmlResourceParser;

public class BuckeyeBuilderApplication extends Application{
    static final String TAG="MyApp";
    private static Context context;

    @Override
    public void onCreate(){
        super.onCreate();

        Parse.initialize(this,getString(R.string.ParseId), getString(R.string.ParseKey));

        ParseFacebookUtils.initialize(getString(R.string.FacebookSdkAppId));

    }
}
