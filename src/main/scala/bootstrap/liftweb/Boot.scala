package bootstrap.liftweb

import net.liftweb._

import common._
import http._
import sitemap._
import sitemap.Loc.LocGroup
import code.rest.AjaxFileUpload


/**
 * FLEX MENU BUILDER have a look for it -- Diego Medina
 */


/**
 * A class that's instantiated early and run.  It allows the application
 * to modify lift's environment
 */
class Boot {
  def boot {
    // where to search snippet
    LiftRules.addToPackages("code")

    // Build SiteMap
    val entries = List(
      Menu.i("Home") / "index",
      Menu.i("navigate to the ajax-form...") / "index-ajax" >> LocGroup("ajax"))

    // set the sitemap.  Note if you don't want access control for
    // each page, just comment this line out.
    LiftRules.setSiteMap(SiteMap(entries: _*))

    // activate the REST point
//    LiftRules.maxMimeFileSize = 40000000L
//    LiftRules.maxMimeSize = 40000000L
    LiftRules.dispatch.append(AjaxFileUpload)

    //Show the spinny image when an Ajax call starts
    LiftRules.ajaxStart =
      Full(() => LiftRules.jsArtifacts.show("ajax-loader").cmd)

    // Make the spinny image go away when it ends
    LiftRules.ajaxEnd =
      Full(() => LiftRules.jsArtifacts.hide("ajax-loader").cmd)

//    LiftRules.jsArtifacts = net.liftweb.http.js.jquery.JQueryArtifacts

    // Force the request to be UTF-8
    LiftRules.early.append(_.setCharacterEncoding("UTF-8"))

    LiftRules.htmlProperties.default.set((r: Req) => new Html5Properties(r.userAgent))
  }
}
