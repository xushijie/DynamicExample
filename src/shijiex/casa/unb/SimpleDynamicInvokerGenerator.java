package shijiex.casa.unb;

import java.io.File;
import java.io.FileOutputStream;
import org.objectweb.asm.MethodVisitor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author shijiex
 */
public class SimpleDynamicInvokerGenerator extends AbstractDynamicInvokerGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String vendorMethodName = "helloWorld";
        FileOutputStream fos = null;
        try {
            String dynamicInvokerClassName = "shijiex/casa/unb/SimpleDynamicInvoker";
            //fos = new FileOutputStream(new File("build/classes/" + dynamicInvokerClassName + ".class"));  //netbeans
            fos = new FileOutputStream(new File("bin/" + dynamicInvokerClassName + ".class")); //eclipse
            fos.write(new SimpleDynamicInvokerGenerator().dump(dynamicInvokerClassName, "shijiex/casa/unb/invoke/DynamicLinkageExample", "bootstrapDynamic", "(Ljava/lang/String;Ljava/lang/String;)V"));
            System.out.println("Success dumpout ");
        } catch (Exception ex) {
        	System.err.println("Exception happening when dumpping bytecode: "+SimpleDynamicInvokerGenerator.class.getName());
        } finally {
            //Clean up here
        }
    }

    @Override
    protected int addMethodParameters(MethodVisitor mv) {
        return 0;
    }
}


