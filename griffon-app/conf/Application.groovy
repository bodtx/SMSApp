application {
    title = 'SmsApp'
    startupGroups = ['SmsApp']

    // Should Griffon exit when no Griffon created frames are showing?
    autoShutdown = true

    // If you want some non-standard application class, apply it here
    //frameClass = 'javax.swing.JFrame'
}
mvcGroups {
    // MVC Group for "SmsApp"
    'SmsApp' {
        model = 'smsapp.SmsAppModel'
        controller = 'smsapp.SmsAppController'
        view = 'smsapp.SmsAppView'
    }

}
