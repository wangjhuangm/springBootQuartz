import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangj.service.GlobalConfig;
import com.wangj.util.FileUtil;

import java.io.File;
import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        String result = FileUtil.read(new File("/opt/work/script/a.sh"));
        System.out.println(result.trim());
    }
}
