package cn.ljserver.tool.weboffice.v3.util;

import cn.ljserver.tool.weboffice.v3.config.WebOfficeProperties;
import cn.ljserver.tool.weboffice.v3.exception.ConfigNotExist;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.noear.solon.Solon;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.MethodType;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 转换服务请求封装工具类
 */
@SuppressWarnings("CallToPrintStackTrace")
@Component
public class ConvertUtils {

    private static final Logger log = Logger.getLogger(ConvertUtils.class.getName());

    private static WebOfficeProperties webOfficeProperties() {
        return Solon.context().getBean(WebOfficeProperties.class);
    }

    public ConvertUtils() {
    }

    /**
     * 将字符串转换为对象
     *
     * @param str   字符串
     * @param clazz 对象类型
     * @param <T>   对象类型
     * @return 对象
     */
    private static <T> T convert(String str, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(str, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    /**
     * GET请求
     *
     * @param uri 请求URI
     * @return 请求结果
     */
    public static String get(String uri) {
        checkProperties();
        String method = MethodType.GET.name;
        String url = webOfficeProperties().getConvert().getDomain() + uri;
        String appid = webOfficeProperties().getConvert().getAppid();
        String secret = webOfficeProperties().getConvert().getSecret();
        Map<String, String> header = HeaderUtils.header(method, uri, null, appid, secret);
        return RequestUtils.request(method, url, header, null);
    }

    /**
     * GET请求-转换
     */
    public static <T> T get(String uri, Class<T> clazz) {
        return convert(get(uri), clazz);
    }

    /**
     * POST请求-转换
     */
    public static <T> T post(String uri, String body, Class<T> clazz) {
        return convert(post(uri, body), clazz);
    }

    /**
     * POST请求-转换
     */
    public static <T, R> T post(String uri, R body, Class<T> clazz) {
        return convert(post(uri, body), clazz);
    }

    /**
     * POST请求
     *
     * @param uri  请求URI
     * @param body 请求体
     * @return 请求结果
     */
    public static String post(String uri, String body) {
        checkProperties();
        String method = MethodType.POST.name;
        String url = webOfficeProperties().getConvert().getDomain() + uri;
        String appid = webOfficeProperties().getConvert().getAppid();
        String secret = webOfficeProperties().getConvert().getSecret();
        Map<String, String> header = HeaderUtils.header(method, null, body, appid, secret);
        return RequestUtils.request(method, url, header, body);
    }

    /**
     * POST请求
     *
     * @param uri  请求URI
     * @param body 请求体
     * @return 请求结果
     */
    public static <T> String post(String uri, T body) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonStringBody = body == null ? "" : objectMapper.writeValueAsString(body);
            return post(uri, jsonStringBody);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 检测配置
     */
    private static void checkProperties() {
        if (webOfficeProperties().getConvert() == null) {
            log.log(Level.SEVERE, "web-office-v3: ERROR: Required application property 'web-office.convert' is null.");
            throw new ConfigNotExist();
        } else {
            if (webOfficeProperties().getConvert().getAppid() == null || webOfficeProperties().getConvert().getAppid().isEmpty()) {
                log.log(Level.SEVERE, "web-office-v3: ERROR: Required application property 'web-office.convert.appid' is null or empty.");
                throw new ConfigNotExist();
            }
            if (webOfficeProperties().getConvert().getSecret() == null || webOfficeProperties().getConvert().getSecret().isEmpty()) {
                log.log(Level.SEVERE, "web-office-v3: ERROR: Required application property 'web-office.convert.secret' is null or empty.");
                throw new ConfigNotExist();
            }
        }
    }
}
