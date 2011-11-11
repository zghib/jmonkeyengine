/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package g3dtest.model;

import com.g3d.animation.Model;
import com.g3d.app.SimpleApplication;
import com.g3d.light.DirectionalLight;
import com.g3d.math.ColorRGBA;
import com.g3d.math.Vector3f;

/**
 *
 * @author lex
 */
public class TestOgreAnim extends SimpleApplication {

    public static void main(String[] args) {
        TestOgreAnim app = new TestOgreAnim();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        flyCam.setMoveSpeed(10f);

        DirectionalLight dl = new DirectionalLight();
        dl.setDirection(new Vector3f(-0.1f, -0.7f, 1).normalizeLocal());
        dl.setColor(new ColorRGBA(1f, 1f, 1f, 1.0f));
        rootNode.addLight(dl);

        Model model = (Model) manager.loadOgreModel("OTO.meshxml", "OTO.material");
        float scale = 1.00f;
//        Model model = (Model) manager.loadOgreModel("ninja.meshxml", "ninja.material");
//        float scale = 0.05f;
        model.scale(scale,scale,scale);
        rootNode.attachChild(model);

        model.setAnimation("Walk");
    }

}