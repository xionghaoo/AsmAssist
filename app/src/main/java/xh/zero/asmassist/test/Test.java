package xh.zero.asmassist.test;

//import android.util.Log;

public class Test {
    public void show() {
//        Log.d(this.getClass().getSimpleName(), "content");

//        StringBuilder builder = new StringBuilder();
//        builder.append("----> onCreate").append(this.getClass().getSimpleName());

        Test.printTest("TAG", "-----------> hello assist");
    }

    public static int printTest(String tag, String message) {
        return 0;
    }
}
