package com.zsCat.common.filter;

import com.alibaba.dubbo.common.extension.Activate;
import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;

@Activate(
		group = {"consumer", "provider"}
)
public class LogMdcDubboFilter implements Filter {
	private static Logger log = LoggerFactory.getLogger(LogMdcDubboFilter.class);

	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		// boolean isProvider = (invocation instanceof DecodeableRpcInvocation);
		boolean isProvider = RpcContext.getContext().isProviderSide();
		Map<String, String> attachments = invocation.getAttachments();
		Map<String, String> mdcMap = MDC.getCopyOfContextMap();

		// 处理Provider
		if (isProvider) {
			MDC.put("logId", attachments.get("logId"));
		} else if (null != mdcMap) {
			// 处理consumer
			// 如果一个项目B既充当Provider也充当consumer[A(consumer)→B(Provider-Consumer)→C(Provider)]
			// 则把它从consumer接到的参数透传给它的Provider
			attachments.put("logId", mdcMap.get("logId"));
		}
		Result res = invoker.invoke(invocation);
		if (isProvider) {
			MDC.clear();
		}
		return res;

	}
}
