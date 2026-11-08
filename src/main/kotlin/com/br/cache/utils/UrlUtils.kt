package com.br.cache.utils

import java.net.MalformedURLException
import java.net.URI
import java.net.URISyntaxException

fun isValidURL(uriString: String?): Boolean {
    try {
        val uri = URI(uriString!!)
        uri.toURL()
        return true
    } catch (e: MalformedURLException) {
        // The URL is malformed or syntactically incorrect
        return false
    } catch (e: URISyntaxException) {
        // The URL is malformed or syntactically incorrect
        return false
    } catch (e: java.lang.NullPointerException) {
        // Catches cases where urlString is null
        return false
    } catch (e: Exception) {
        return false
    }
}
