package com.jme3.gde.core.editor;

import java.io.IOException;
import javax.swing.JPanel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.cookies.SaveCookie;
import org.openide.loaders.DataObject;
import org.openide.loaders.OpenSupport;
import org.openide.nodes.Node;
import org.openide.util.Exceptions;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.CloneableTopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
    dtd = "-//com.jme3.gde.core.editor//SceneEditor//EN",
autostore = false)
@TopComponent.Description(
    preferredID = "SceneEditorTopComponent",
//iconBase="SET/PATH/TO/ICON/HERE", 
persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
//@ActionID(category = "Window", id = "com.jme3.gde.core.editor.SceneEditorTopComponent")
//@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
    displayName = "#CTL_SceneEditorAction",
preferredID = "SceneEditorTopComponent")
@Messages({
    "CTL_SceneEditorAction=SceneEditor",
    "CTL_SceneEditorTopComponent=SceneEditor Window",
    "HINT_SceneEditorTopComponent=This is a SceneEditor window"
})
public final class SceneEditorTopComponent extends CloneableTopComponent {

    DataObject obj;

    public SceneEditorTopComponent() {
        initComponents();
        setToolTipText(Bundle.HINT_SceneEditorTopComponent());
    }

    public void setDataObject(DataObject scene) {
        obj = scene;
        if (scene == null) {
            return;
        }
        setName(scene.getName());
        setActivatedNodes(new Node[]{scene.getNodeDelegate()});
    }

    public JPanel getScenePanel() {
        return scenePanel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scenePanel = new javax.swing.JPanel();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        scenePanel.setLayout(new javax.swing.BoxLayout(scenePanel, javax.swing.BoxLayout.LINE_AXIS));
        add(scenePanel);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel scenePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public boolean canClose() {
        if (obj != null && obj.isModified()) {
            NotifyDescriptor.Confirmation mesg = new NotifyDescriptor.Confirmation("Scene has not been saved,\ndo you want to save it?",
                    "Not Saved",
                    NotifyDescriptor.YES_NO_CANCEL_OPTION);
            DialogDisplayer.getDefault().notify(mesg);
            if (mesg.getValue() == NotifyDescriptor.Confirmation.YES_OPTION) {
                try {
                    obj.getCookie(SaveCookie.class).save();
                } catch (IOException ex) {
                    Exceptions.printStackTrace(ex);
                }
            } else if (mesg.getValue() == NotifyDescriptor.Confirmation.CANCEL_OPTION) {
                return false;
            } else if (mesg.getValue() == NotifyDescriptor.Confirmation.NO_OPTION) {
                obj.setModified(false);
                return true;
            }
        }
        return true;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
        if (obj != null) {
            OpenSupport closer = obj.getLookup().lookup(OpenSupport.class);
            if (closer != null) {
                closer.close();
            }
        }
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
