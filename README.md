# ASM插桩插件

## tuyaassist 

给FragmentActivity.onCreate()插入以下代码：
```kotlin
Log.d(this.getClass().getSimpleName(), "------> onCreate: Hello AsmAssist");
```

## 查看java字节码

```shell script
javap -c Test.java
```