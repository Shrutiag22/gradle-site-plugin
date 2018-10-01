package org.gradle.plugins.site

import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.rules.TemporaryFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File

object SitePluginFunctionalTest : Spek({
    describe("SitePlugin") {
        it("with plugin applied") {
            val testProjectDir = TemporaryFolder()
            testProjectDir.create()
            val projectPath = testProjectDir.root.absolutePath
            val buildFile: File = testProjectDir.newFile("build.gradle")
            buildFile.writeText("""
                plugins {
                    id 'org.gradle.plugins.site'
                }
            """)

            fun execute(projectDir: TemporaryFolder, vararg arguments: String): BuildResult {
                return GradleRunner.create()
                        .withProjectDir(projectDir.root)
                        .withArguments(arguments.toList())
                        .withPluginClasspath()
                        .build()
            }
        }
    }
})
