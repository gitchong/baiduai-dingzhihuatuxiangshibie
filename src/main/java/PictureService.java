import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * Created by 小丰 on 2018/7/12.
 */

public class PictureService {
  /*  public static void main(String[] args){
        getAuth();
    }*/
    public static String getAuth(){
        //官网获取的API key更新为自己注册的
        String clientId ="vGah7GokkUybrYdoCaUGLNYM";
        //官网的secreat key更新为自己注册的
        String clientSecret ="wirDGhFFyTGTG4zjcFcKQQS2T0OGYRaT";

        return getAuth(clientId,clientSecret);
    }

    public static String getAuth(String ak,String sk){
        //获取token地址
        String authHost ="https://aip.baidubce.com/oauth/2.0/token?";
        String getAccessTokenUrl =authHost
                //1、grant_type为固定参数
                +"grant_type=client_credentials"
                //2、官网获取的api key
                +"&client_id="+ak
                //3、官网获取的Secreat Key
            +"&client_secret="+sk;
        try{
           URL realUrl = new URL(getAccessTokenUrl);
           //打开和URL之间的 连接
            HttpURLConnection connection = (HttpURLConnection)realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            //获取所有响应头字段
            Map<String,List<String>> map =connection.getHeaderFields();
            //遍历所有的响应头字段
            for(String key :map.keySet()){
                System.out.println( key+"--->"+map.get(key));
            }
            //定义BufferedReader输入流来读取URL的响应
            BufferedReader in  =  new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = "";
            String line ;
            while((line=in.readLine())!=null){
                result+=line;
            }
            //返回结果实例
            System.out.println( "result:"+result);
            JSONObject jsonObject = new JSONObject(result);
            String access_token =jsonObject.getString("access_token");
            System.out.println( access_token);
            return access_token;
        }catch (Exception e){
            System.out.println( "获取token失败");
            e.printStackTrace(System.err);
        }
        return null;
    }

}
