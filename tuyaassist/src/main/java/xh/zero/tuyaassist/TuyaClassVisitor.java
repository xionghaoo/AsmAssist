package xh.zero.tuyaassist;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class TuyaClassVisitor extends ClassVisitor implements Opcodes {

    private static final String TYRCTSmartPanelActivity_CLASS = "com/tuya/smart/panel/base/activity/TYRCTSmartPanelActivity.class";
    private static final String FragmentActivity_CLASS = "androidx/fragment/app/FragmentActivity.class";

    private String mClassName;

    static boolean hasClasses(String name) {
//        System.out.println("class name: " + name);
        return TYRCTSmartPanelActivity_CLASS.equals(name) || FragmentActivity_CLASS.equals(name);
    }

    public TuyaClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        //System.out.println("LifecycleClassVisitor : visit -----> started ：" + name);
        this.mClassName = name;
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
//        System.out.println("visitMethod : " + this.mClassName + " : " + name);
//        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if ("com/tuya/smart/panel/base/activity/TYRCTSmartPanelActivity".equals(this.mClassName)) {
            // 涂鸦控制面板Activity
            if ("onCreate".equals(name) ) {
                //处理onCreate
                System.out.println("TuyaClassVisitor : TYRCTSmartPanelActivity change method ----> " + name);
                return new OnCreateMethodVisitor(api, mv, access, name, desc);
//                return new LifecycleOnCreateMethodVisitor(mv);
            }
//            else if ("onDestroy".equals(name)) {
//                //处理onDestroy
//                System.out.println("LifecycleClassVisitor : change method ----> " + name);
//                return new LifecycleOnDestroyMethodVisitor(mv);
//            }
        }
        if ("androidx/fragment/app/FragmentActivity".equals(this.mClassName)) {
            //匹配FragmentActivity
            if ("onCreate".equals(name) ) {
                //处理onCreate
                System.out.println("TuyaClassVisitor : MainActivity change method ----> " + name);
                return new FragmentActivity_OnCreateMethodVisitor(api, mv, access, name, desc);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        //System.out.println("LifecycleClassVisitor : visit -----> end");
        super.visitEnd();
    }
}
