package com.example.noteapp

import androidx.compose.ui.test.click
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.performTouchInput
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun test_初回表示時の確認() {
        composeTestRule.onNodeWithText("NoteApp").assertExists()
        composeTestRule.onNodeWithText("Title").assertExists()
        composeTestRule.onNodeWithText("Add a note").assertExists()
        composeTestRule.onNodeWithText("save").assertExists()
    }

    @Test
    fun test_note入力時の確認() {
        composeTestRule.onNodeWithText("Title").performTextInput("title test")
        composeTestRule.onNodeWithText("Add a note").performTextInput("Add note test")
    }

    @Test
    fun test_note追加時の確認() {
        // 入力を行う
        composeTestRule.onNodeWithText("Title").performTextInput("title test")
        composeTestRule.onNodeWithText("Add a note").performTextInput("Add note test")
        composeTestRule.onNodeWithText("save").performTouchInput { click() }
        composeTestRule.onNodeWithText("first").assertDoesNotExist()

        composeTestRule.waitForIdle()

        // 入力後の確認
        composeTestRule.onNodeWithText("title test").assertExists()
        composeTestRule.onNodeWithText("Add note test").assertExists()
        composeTestRule.onAllNodesWithText("first")
    }

}
