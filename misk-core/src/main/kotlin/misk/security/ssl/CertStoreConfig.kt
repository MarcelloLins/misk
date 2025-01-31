package misk.security.ssl

import misk.config.Redact
import wisp.security.ssl.CertStoreConfig as WispCertStoreConfig
import javax.inject.Inject

data class CertStoreConfig @Inject constructor(
  val resource: String,
  @Redact
  val passphrase: String? = null,
  val format: String = SslLoader.FORMAT_JCEKS
) {
  fun toWispConfig() = WispCertStoreConfig(resource, passphrase, format)
}
