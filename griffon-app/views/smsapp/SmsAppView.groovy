package smsapp
import javax.swing.*;
import java.awt.Dimension;
import java.net.URL;



application(title: 'SMSApp',
  size: [480,480],
  pack: true,
  //location: [50,50],
  locationByPlatform:true,
  iconImage: imageIcon('/griffon-icon-48x48.png').image,
  iconImages: [imageIcon('/griffon-icon-48x48.png').image,
               imageIcon('/griffon-icon-32x32.png').image,
               imageIcon('/griffon-icon-16x16.png').image]) {
    borderLayout()

        scrollPane(constraints:CENTER) {
            textArea(id:"textAreaSource",
			keyTyped:controller.countChar,
                enabled: bind {model.enabled},
                columns:40, rows:10

				)
			
			
        }

		


        hbox(id:'zoneBouton',constraints:SOUTH) {
	
            button(getMessage('bouton.text'), actionPerformed:controller.&executeScript,
                enabled: bind {model.enabled})
            hstrut(5)
            label(id:"labelMaxChar", text:getMessage('label.maxchar'))
            hstrut(5)
            label(id:"textSize",text:bind {model.scriptLength})
			hstrut(5)
			label(text:bind {model.textResultat},icon:bind {model.imageResultat})
			
        }
		

		



}
