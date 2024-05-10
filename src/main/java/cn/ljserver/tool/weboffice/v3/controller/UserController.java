package cn.ljserver.tool.weboffice.v3.controller;

import cn.ljserver.tool.weboffice.v3.model.ProviderResponseEntity;
import cn.ljserver.tool.weboffice.v3.model.UserInfo;
import cn.ljserver.tool.weboffice.v3.service.UserService;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Param;
import org.noear.solon.core.handle.MethodType;

import java.util.List;

/**
 * 用户信息 -> 详见： <br>
 * <a href="https://solution.wps.cn/docs/callback/user.html">wps web office 用户信息</a>
 */
@Controller
@ProviderJsonApi
@Mapping("/v3/3rd/users")
public class UserController extends ProviderBaseController {

    @Inject(required = false)
    private UserService service;

    /**
     * 批量获取用户信息
     *
     * @param userIds 用户id列表 <br>
     *                <a href = "https://solution.wps.cn/docs/callback/user.html#批量获取用户信息">-详见官方文档-</a>
     */
    @Mapping(method = MethodType.GET)
    public ProviderResponseEntity<List<UserInfo>> fetchUsers(@Param("user_ids") List<String> userIds) {
        checkService(service, "UserService");
        return ProviderResponseEntity.ok(this.service.fetchUsers(userIds));
    }

}
