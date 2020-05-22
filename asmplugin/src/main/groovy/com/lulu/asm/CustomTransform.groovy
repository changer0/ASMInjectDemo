package com.lulu.asm

import com.android.build.api.transform.DirectoryInput
import com.android.build.api.transform.Format
import com.android.build.api.transform.JarInput
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInput
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.apache.commons.codec.digest.DigestUtils
import org.gradle.api.Project

/**
 * 自定义的 Transform 类
 */
class CustomTransform extends Transform {
    Project mProject

    CustomTransform(Project project) {
        mProject = project
    }

    //自定义 Task 名称
    @Override
    String getName() {
        return "customLulu"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    // 当前Transform是否支持增量编译
    @Override
    boolean isIncremental() {
        return false
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation)
        transformInvocation.getInputs().each {
            TransformInput input ->
                //这里面存放第三方的 jar 包
                input.jarInputs.each {
                    JarInput jarInput ->
                        String destName = jarInput.file.name
                        String absolutePath = jarInput.file.absolutePath
                        println "jarInput destName: ${destName}"
                        println "jarInput absolutePath: ${absolutePath}"
                        // 重命名输出文件（同目录copyFile会冲突）
                        def md5Name = DigestUtils.md5(absolutePath)
                        if (destName.endsWith(".jar")) {
                            destName = destName.substring(0, destName.length() - 4)
                        }
                        //获取输出文件
                        File dest = transformInvocation.getOutputProvider()
                                .getContentLocation(destName+"_"+md5Name,
                                        jarInput.contentTypes, jarInput.scopes, Format.JAR)
                        //中间可以将 jarInput.file 进行操作！
                        //copy 到输出目录
                        FileUtils.copyFile(jarInput.file, dest)
                }

                //这里存放着开发者手写的类
                input.directoryInputs.each {
                    DirectoryInput directoryInput ->
                        def dest = transformInvocation.getOutputProvider()
                                .getContentLocation(
                                        directoryInput.name,
                                        directoryInput.contentTypes,
                                        directoryInput.scopes, Format.DIRECTORY)
                        println "directory output dest: $dest.absolutePath"
                        FileUtils.copyDirectory(directoryInput.file, dest)
                }


        }
    }
}