root {
    'groovy.swing.SwingBuilder' {
        controller = ['Threading']
        view = '*'
    }
    'griffon.app.ApplicationBuilder' {
        view = '*'
    }
}
root.'RestGriffonAddon'.addon=true

root.'I18nGriffonAddon'.addon=true
