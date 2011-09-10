package smsapp

import groovy.beans.Bindable

import javax.swing.ImageIcon


class SmsAppModel {
    String scriptSource
	@Bindable int scriptLength
    @Bindable def scriptResult
    @Bindable boolean enabled = true
	@Bindable ImageIcon imageResultat 
	@Bindable String textResultat



}