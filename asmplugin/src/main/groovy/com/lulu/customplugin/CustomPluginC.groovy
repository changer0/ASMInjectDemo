package com.lulu.customplugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 自定义插件
 */
class CustomPluginC implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "CustomPluginC Hello"
        //将Extension注册给Plugin
        def extension = project.extensions.create("customC", CustomExtensionC)
        //定义一个任务
        project.task('CustomPluginTaskC') {
            doLast {
                println "接收外部参数：${extension.extensionArgs}"
            }
        }
    }
}