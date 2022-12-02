package data
import javax.ws.rs.core.Response

data class APIResponse<R: Any>(
        var statusCode: Int? =null,
        var message: String?=null,
        var data: R?=null
)