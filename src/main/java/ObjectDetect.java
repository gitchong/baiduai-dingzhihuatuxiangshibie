import com.google.gson.JsonObject;
import util.Base64Util;
import util.FileUtil;
import util.GsonUtils;
import util.HttpUtil;

import java.net.URLEncoder;

/**
 * Created by 小丰 on 2018/7/13.
 */
public class ObjectDetect {
    /**
     * 1、请求URL
     * 2、本地文件路径上传文件
     * 3、base64编码
     * 4、拼成字符串
     * 5、获取access key
     * 6、发送http请求
     *
     */
   private static String ak =PictureService.getAuth();
    public  static String detect(){
        String url ="https://aip.baidubce.com/rpc/2.0/ai_custom/v1/classification/manorwoman";
        String filePath="C:\\Users\\Administrator\\Desktop\\meinanzi.jpg";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr=Base64Util.encode(imgData);
            String imgStr2="hahha";
            String imgParam =URLEncoder.encode(imgStr,"UTF-8");
           //String param ="image="+imgParam+"&with_face="+1;
         // String param ="{\"image\":\""+imgStr+"\",\"top_num\":\"2\"}";
          JsonObject jsonObject=new JsonObject();
            jsonObject.addProperty("image",imgStr);
            jsonObject.addProperty("top_num",2);
           String param  =GsonUtils.toJson(jsonObject);
           System.out.println( param);

            String accessToken =ak;
            String ContentType ="application/json";
           String result =  HttpUtil.post(url,accessToken,ContentType,param);
           System.out.println( result);
           return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args){
        detect();
    }
}
