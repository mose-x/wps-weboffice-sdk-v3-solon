package cn.ljserver.tool.weboffice.v3.util;

import cn.ljserver.tool.weboffice.v3.exception.InvalidArgument;
import cn.ljserver.tool.weboffice.v3.exception.InvalidToken;
import org.noear.solon.core.handle.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 请求头工具类
 * <br>
 * <a href="https://solution.wps.cn/docs/web/quick-start.html#%E6%AD%A5%E9%AA%A4-3-%E5%88%9D%E5%A7%8B%E5%8C%96">前端初始化头部信息，详见官网</a>
 */
public class HeaderUtils {
    private HeaderUtils() {
    }

    /**
     * 获取请求头
     *
     * @param method 请求方法
     * @param uri    请求路径,如 /api/f/1?a=1
     * @param body   请求体, post需要传递body,get请求不需要
     * @param appid  应用id
     * @param secret 应用密钥
     * @return 请求头
     */
    public static Map<String, String> header(String method, String uri, String body, String appid, String secret) {
        Map<String, String> header = new HashMap<>();
        String md5 = SignUtils.md5(method, uri, body);
        String data = DateUtils.date();
        header.put("Content-Md5", md5);
        header.put("Content-Type", "application/json");
        header.put("Date", data);
        header.put("Authorization", SignUtils.sign(appid, secret, data, md5));
        return header;
    }

    /**
     * 获取请求头
     *
     * @param ctx 请求
     * @return Content-Md5 -> 请求体的md5值
     */
    public static String getHeaderContentMd5(Context ctx) {
        return ctx.header("Content-Md5");
    }

    /**
     * 获取请求头
     *
     * @param ctx 请求
     * @return Content-Type -> 请求体的类型
     */
    public static String getHeaderContentType(Context ctx) {
        return ctx.header("Content-Type");
    }

    /**
     * 获取请求头
     *
     * @param ctx 请求
     * @return DATE -> 请求的时间
     */
    public static String getHeaderDate(Context ctx) {
        return ctx.header("Date");
    }

    /**
     * 获取请求头
     *
     * @param ctx 请求
     * @return Authorization -> 请求签名
     */
    public static String getHeaderAuthorization(Context ctx) {
        return ctx.header("Authorization");
    }

    /**
     * 获取请求头
     *
     * @param ctx 请求
     * @return X-Weboffice-Token -> 当前请求的用户凭证，即通过初始化参数传递的token值
     * <br>
     * <a href="https://solution.wps.cn/docs/web/quick-start.html#%E6%AD%A5%E9%AA%A4-3-%E5%88%9D%E5%A7%8B%E5%8C%96">前端初始化，详见官网</a>
     */
    public static String getWebOfficeToken(Context ctx) {
        final String token;
        if (Objects.isNull((token = ctx.header("X-Weboffice-Token")))) {
            throw new InvalidToken("weboffice token is required");
        }
        return token;
    }

    /**
     * 获取请求头
     *
     * @param ctx 请求
     * @return X-App-Id -> 当前请求所属的 AppId 与 URl 上的 _w_appid 值相同
     */
    public static String getAppId(Context ctx) {
        final String appId;
        if (Objects.isNull((appId = ctx.header("X-App-Id")))) {
            throw new InvalidArgument("app id is required");
        }
        return appId;
    }

    /**
     * 获取请求头
     *
     * @param ctx 请求
     * @return X-Request-Id -> wps web office 本次请求 ID，方便定位问题
     */
    public static String getRequestId(Context ctx) {
        final String requestId = ctx.header("X-Request-Id");
        if (Objects.isNull(requestId)) {
            return "";
        }
        return requestId;
    }

    /**
     * 获取请求头
     * <p>
     * URL 中的 query 部分，包含一些基础参数和 WebOffice SDK 初始化时通过customArgs传递的参数，例如customArgs的值为{"firstname":"jack","lastname":"green"}，
     * 那么该请求头的内容为_w_appid=xxx&_w_tokentype=xxx&file_id=xxx&firstname=jack&lastname=green
     * <br>
     * <a href="https://solution.wps.cn/docs/web/quick-start.html#%E6%AD%A5%E9%AA%A4-3-%E5%88%9D%E5%A7%8B%E5%8C%96">前端初始化，详见官网</a>
     *
     * @param ctx 请求
     * @return X-User-Query -> 用户传递请求数据
     */
    public static String getUserQuery(Context ctx) {
        final String userQuery = ctx.header("X-User-Query");
        if (Objects.isNull(userQuery)) {
            return "";
        }
        return userQuery;
    }
}
