package smsapp
import java.awt.Color
import java.awt.event.ActionEvent

import javax.swing.ImageIcon


class SmsAppController {
	// these will be injected by Griffon
	def model
	def view


	void mvcGroupInit(Map args) {
		//    // this method is called after model and view are injected
	}

	// void mvcGroupDestroy() {
	//    // this method is called when the group is destroyed
	// }

	//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.1' )
	def executeScript(ActionEvent evt = null) {
		model.enabled = false

		doOutside {
			def result
			try {


				withHttp(uri: "http://rest.nexmo.com/sms/"){
					def postBody = [username:'36b0915c',password:'91d80755',from:'Ouistitis',to:'33666206355',text:model.scriptSource] // will be url-encoded
					//pour test ko 
					//def postBody = [username:'36b0915c',password:'91d80755',from:'Ouistitis',text:'toto'] // will be url-encoded
					post( path: 'xml', body: postBody,
							requestContentType: groovyx.net.http.ContentType.URLENC ) { resp, xml ->
								if(resp.statusLine.statusCode == 200){
									def xmlFeed = new XmlParser().parse(xml)
									println xmlFeed.messages[0].message.status.text()
									if(xmlFeed.messages[0].message.status.text() == "0" )
									{
										model.imageResultat = imageIcon(url:new java.net.URL('http://www.minfo.fr/images/ok.png'))
										model.textResultat = getMessage('resultat.ok')
									}
									else
									{
										model.imageResultat = imageIcon(url:new java.net.URL('http://cdn4.iconfinder.com/data/icons/sketchdock-ecommerce-icons/ko-red.png'))
										model.textResultat = xmlFeed.messages[0].message.errorText.text()
										
									}
								}
							}
				}
			} finally {
				edt {
					view.zoneBouton.validate()
					model.enabled = true
					model.scriptResult = result
					
				}
			}
		}



	}


	def countChar = { evt = null ->

		int nbChariot=0
		for(c in evt.source.text){
			if(c == '\n')
				nbChariot++
		}

		if(evt.source.text.length() + nbChariot >140)
		{
			view.labelMaxChar.foreground = Color.RED
			view.textSize.foreground = Color.RED
			evt.source.text = model.scriptSource
			view.textAreaSource.setCaretPosition model.scriptSource.length()


		}
		else
		{
			view.labelMaxChar.foreground = Color.BLACK
			view.textSize.foreground = Color.BLACK

			model.scriptSource = evt.source.text
			model.scriptLength = evt.source.text.length() + nbChariot
		}


	}

}
