package io.github.muxiaobai.spring_boot.common.file;

import lombok.extern.slf4j.Slf4j;

/**

 * @version V1.0
 * @date 2020/6/10/010 14:51
 * @附件相关的服务，保存附件
 */
@Slf4j
public class FileServiceUtil {

    /**
     *  获取服务url地址
     * @author  Zhang Pengfei
     * @date 2020/6/10/010 14:53
     * @param client
     * @param appName
     * @return java.lang.String
     */
//    public static String getUrl(EurekaClient client, String appName) {
//        Applications applications = client.getApplications();
//        String foreignUrl = "";
//        for (Application application : applications.getRegisteredApplications()) {
//            List<InstanceInfo> infos = application.getInstances();
//            for (int i = 0; i < infos.size(); i++) {
//                InstanceInfo info = infos.get(i);
//                if (StringUtils.equalsIgnoreCase(info.getAppName(), appName)) {
//                    foreignUrl = info.getHomePageUrl();
//                    break;
//                }
//            }
//        }
//        return foreignUrl;
//    }
    /**
     *  批量 保存附件
     * @author  Zhang Pengfei
     * @date 2020/3/16/016 16:05
     * @param files
     * @return java.lang.String
     */
//    public static String saveFile(MultipartFile[] files, EurekaClient eurekaClient, String appId){
//        RemoteFileSystemService client = Feign.builder()
//                .encoder(new FeignSpringFormEncoder())
//                .target(RemoteFileSystemService.class,FileServiceUtil.getUrl(eurekaClient,"app-filesystem"));
//        String filesMsg = client.multiUploadFile(files,appId);
//       log.info("multiUploadFile response filesMsg:{}",filesMsg);
//        return filesMsg;
//    }
}
