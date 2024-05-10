package cn.ljserver.tool.weboffice.v3.config;

import cn.ljserver.tool.weboffice.v3.controller.ProviderJsonApi;
import cn.ljserver.tool.weboffice.v3.exception.CustomErrorMsg;
import cn.ljserver.tool.weboffice.v3.exception.ProviderException;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.core.route.RouterInterceptor;
import org.noear.solon.core.route.RouterInterceptorChain;

@SuppressWarnings("CallToPrintStackTrace")
@Component // index 为顺序位（不加，则默认为0）
public class AppRouterInterceptor implements RouterInterceptor {

    @Override
    public void doIntercept(Context ctx, Handler handler, RouterInterceptorChain chain) throws Throwable {
        // 随机请求，这里放行，让项目对接者自行处理
        if (ctx.action() == null) {
            chain.doIntercept(ctx, handler);
        } else {
            // 如果是自定义的接口，则执行
            ProviderJsonApi annotation = ctx.action().controller().annotationGet(ProviderJsonApi.class);
            if (annotation != null) {
                try {
                    chain.doIntercept(ctx, handler);
                    if (handler == null) {
                        ctx.status(404);
                        ctx.outputAsJson(CustomErrorMsg.e_404());
                    }
                } catch (ProviderException e) {
                    ctx.status(500);
                    ctx.outputAsJson(e.jsonResult());
                    e.printStackTrace();
                } catch (Throwable e) {
                    ctx.status(501);
                    ctx.outputAsJson(CustomErrorMsg.e_500());
                    e.printStackTrace();
                }
                // 不是，则直接放行，往上层抛异常
            } else {
                chain.doIntercept(ctx, handler);
            }
        }
    }
} 