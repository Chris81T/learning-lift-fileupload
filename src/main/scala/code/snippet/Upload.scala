package code.snippet

import net.liftweb._
import common.{Full, Box, Empty}
import http._
import js.{JsCmds, JsCmd, JE}
import JsCmds._
import util.Helpers._

object UploadSnippet {

  object imageFile extends RequestVar[Box[FileParamHolder]](Empty)

  def render = {

    def process() {
      (imageFile.is) match {
        case (Empty) => println("--> is Empty!")
        case (image) => {
          println("--> The RequestVar content is: %s".format(imageFile.is))
          imageFile.is.map{
            println("--> About to start the file upload")
            file => saveFile(file)
          }
          println("--> Done")

        }
      }
    }

    "type=file" #> SHtml.fileUpload(file => imageFile(Full(file))) &
      "@upload" #> SHtml.onSubmitUnit(process)
  }

  def saveFile(fp: FileParamHolder): Unit = {
    println("--> FileParamHolder filename=" + fp.fileName + ", length=" + fp.length + ", mimeType=" + fp.mimeType +
      ", name=" +fp.name)
    fp.file match {
      case null =>  //S.error if here
      case x if x.length == 0 => println("--> File size is 0") //S.error if here
      case x => {
        println("--> We got a file with size of " + x.length + ". Now it is time to save it in memory for this time...")
      }
    }
  }
}

object ImageStorage {

  private val images = Nil

  def addImage(image: FileParamHolder) {
  }

}
