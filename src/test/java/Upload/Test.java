package Upload;

import com.qiniu.api.auth.AuthException;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.config.Config;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;
import org.json.JSONException;

/**
 * Created by Administrator on 2015/11/3.
 */
public class Test {

    public static void main(String[] args) throws AuthException, JSONException {

        Config.ACCESS_KEY = "AJ1DUPV-BcrX96n9FhK2f9xt5UVJfWa6mjHnMqkw";
        Config.SECRET_KEY = "v2eYvPYHFVvBlIb8076ujykBqe-cTkeiN7bzNbHb";
        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
        // 请确保该bucket已经存在
        String bucketName = "pp-xx";
        PutPolicy putPolicy = new PutPolicy(bucketName);
        String uptoken = putPolicy.token(mac);
       // putPolicy.persistentOps = "avthumb/mp4";
        putPolicy.marshal();
        PutExtra extra = new PutExtra();
        String key = "platform_assets/video/L.mp4";
        String localFile = "G:\\迅雷下载\\L.mp4";
        PutRet ret = IoApi.putFile(uptoken, key, localFile, extra);
        System.out.println(ret);
    }

}
