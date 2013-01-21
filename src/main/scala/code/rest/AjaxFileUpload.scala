package code.rest

import net.liftweb.http.rest.RestHelper
import net.liftweb.http.OkResponse

/**
 * Created with IntelliJ IDEA.
 * User: christian
 * Date: 1/21/13
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
object AjaxFileUpload extends RestHelper {

  serve {

    case "upload" :: Nil Post req =>
      for (file <- req.uploadedFiles) {
        println("Received: "+file.fileName)
      }
      OkResponse()

  }

}
