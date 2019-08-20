package corp.bcapc.data

/**
 *   Created by Carlos Alberto(Beto) Pereira Caldas on 16/08/2019 - 01:21
 */

inline class IsDebugging(val value: Boolean)

inline class ServerUrl(val value: String)

class ConfigApi(val serverUrl: String, val isDebugging: Boolean)