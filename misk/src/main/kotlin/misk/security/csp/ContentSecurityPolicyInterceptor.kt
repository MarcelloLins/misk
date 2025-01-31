package misk.security.csp

import misk.Action
import misk.web.NetworkChain
import misk.web.NetworkInterceptor
import javax.inject.Inject
import kotlin.reflect.full.findAnnotation

class ContentSecurityPolicyInterceptor(val rules: List<String>) : NetworkInterceptor {
  override fun intercept(chain: NetworkChain) {
    chain.httpCall.setResponseHeader(
      "Content-Security-Policy",
      rules.joinToString(separator = "; ", postfix = ";")
    )
    chain.proceed(chain.httpCall)
  }

  class Factory @Inject constructor() : NetworkInterceptor.Factory {
    override fun create(action: Action): NetworkInterceptor? {
      val cspAnnotation = action.function.findAnnotation<ContentSecurityPolicy>() ?: return null
      return ContentSecurityPolicyInterceptor(cspAnnotation.rules.toList())
    }

  }
}
