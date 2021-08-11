package io.github.muxiaobai.common.sensitive;

import io.github.muxiaobai.manage.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**

 * @version V1.0
 * @date 2020/1/2/002 20:39
 * @初始化sensitiveWord 工具类中的禁词 敏感词
*/
@Component
@Slf4j
public class SensitiveWordSyncComponent {
    public static Set cache =new HashSet<>();

    public  Set getCache() {
        if(cache.size()==0){this.setCache();}
        return cache;
    }
    public  void setCache() {
        //从文件中读取内容放到cache
        if(cache.size()==0) {
            InputStream is = this.getClass().getResourceAsStream("/word/sensitive.txt");
            cache.addAll(this.getFileContext(is));
        }
    }
    public  Set<String> getFileContext(InputStream is) {
        Set<String> set = new HashSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str = "";
        try {
            while ((str = br.readLine()) != null) {
                if (str.trim().length() > 2) {
                    set.add(str);
                }
            }
            return  set;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  set;
    }
    public  Set<String> getFileContext(String path)  {
        Set<String> set = new HashSet<>();
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                if (str.trim().length() > 2) {
                    set.add(str);
                }
            }
            return set;
        }catch (Exception e){
            return  set;
        }
    }

    public  void init() {
        Set<String> sensitiveWordSet = new HashSet<>();
        //默认敏感词
        sensitiveWordSet.addAll(this.getCache());
        //初始化敏感词库
        List<User> list = new ArrayList<>();
        List<String> names =list.stream().map(User::getUsername).collect(Collectors.toList());
        sensitiveWordSet.addAll(names);

        SensitiveWordUtil.init(sensitiveWordSet);
    }

    public static void main(String[] args) {
        SensitiveWordSyncComponent sensitiveWordSyncComponent = new SensitiveWordSyncComponent();
        sensitiveWordSyncComponent.init();

    }
}
