package com.example.data

import com.example.data.okhttp.OkHttpClientProviderInterface
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import java.security.SecureRandom
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import kotlin.jvm.Throws

class OkHttpClientProvider : OkHttpClientProviderInterface {
  private var dispatcher = Dispatcher()

  override fun getOkHttpClient(pin: String): OkHttpClient.Builder {
    try {
      val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {

        override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()

        @Throws(CertificateException::class)
        override fun checkClientTrusted(
          chain: Array<X509Certificate>,
          authType: String,
        ) {
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(
          p0: Array<X509Certificate>?,
          authType: String,
        ) {
        }
      })

      val sslContext = SSLContext.getInstance("TLS")
      sslContext.init(null, trustAllCerts, SecureRandom())

      val sslSocketFactory = sslContext.socketFactory

      val builder = OkHttpClient.Builder()
        .dispatcher(dispatcher)
        .sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
        .hostnameVerifier(HostnameVerifier { _, _ -> true })

      return builder
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }

  override fun cancelAllRequests() {
    dispatcher.cancelAll()
  }
}
