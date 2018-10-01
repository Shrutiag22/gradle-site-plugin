package org.gradle.plugins.site.utils

import org.junit.rules.TemporaryFolder
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

object FileUtilsTest : Spek({
    describe("given parent directory") {
        val temporaryFolder = TemporaryFolder()

        val dir = File(temporaryFolder.root, "nested/subdir")
        it("can create nested directories") {
            assertFalse(dir.isDirectory)
            FileUtils.createDirectory(dir)
            assertTrue(dir.isDirectory)
        }

        describe("given source files") {
            val sourceFile = temporaryFolder.newFile("a.txt")
            val targetFile = File(temporaryFolder.root, "b.txt")
            it("can copy a file") {
                assertTrue(sourceFile.isFile)
                assertFalse(targetFile.isFile)

                FileUtils.copyFile(sourceFile, targetFile)

                assertTrue(sourceFile.isFile)
                assertTrue(targetFile.isFile)
            }
        }
    }
})
