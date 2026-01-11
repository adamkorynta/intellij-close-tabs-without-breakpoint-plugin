package com.geiconsultants.intellij.close.tabs

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.xdebugger.XDebuggerManager
import com.intellij.xdebugger.breakpoints.XLineBreakpoint


class CloseTabsWithoutBreakpointsAction : AnAction() {

    override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.EDT

    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val filesWithBreakpoints = getFilesWithBreakpoints(project)
        val fileEditorManager = FileEditorManager.getInstance(project)

        fileEditorManager.openFiles.forEach { file ->
            if (!filesWithBreakpoints.contains(file)) {
                fileEditorManager.closeFile(file)
            }
        }
    }

    override fun update(e: AnActionEvent) {
        val project = e.project
        if (project == null) {
            e.presentation.isEnabledAndVisible = false
            return
        }

        // Logic for context awareness:
        // Only show the action if there is at least one breakpoint set in an open file
        val filesWithBreakpoints = getFilesWithBreakpoints(project)
        val openFiles = FileEditorManager.getInstance(project).openFiles

        val anyOpenFilesHaveBreakpoints = openFiles.any { filesWithBreakpoints.contains(it) }

        e.presentation.isEnabledAndVisible = anyOpenFilesHaveBreakpoints
    }

    private fun getFilesWithBreakpoints(project: com.intellij.openapi.project.Project): Set<VirtualFile> {
        val files = mutableSetOf<VirtualFile>()
        val allBreakpoints = XDebuggerManager.getInstance(project).breakpointManager.allBreakpoints

        for (breakpoint in allBreakpoints) {
            if (breakpoint is XLineBreakpoint<*>) {
                breakpoint.sourcePosition?.file?.let { files.add(it) }
            }
        }
        return files
    }
}
