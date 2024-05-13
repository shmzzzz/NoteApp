package com.example.noteapp.viewmodel

import com.example.noteapp.model.Note
import com.example.noteapp.repository.NoteRepository
import com.example.noteapp.screen.NoteViewModel
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.util.Date
import java.util.UUID

@ExperimentalCoroutinesApi
class NoteViewModelTest {
    private val mainDispatcher = Dispatchers.Unconfined

    private val uuid = UUID.randomUUID()
    private val date = Date.from(Instant.now())

    @Before
    fun setUp() {
        // テスト用のメインディスパッチャーを設定
        Dispatchers.setMain(mainDispatcher)
    }

    @After
    fun tearDown() {
        // テスト後にメインディスパッチャーをリセット
        Dispatchers.resetMain()
    }

    @Test
    fun test_note追加が正しくできていることを確認する() = runBlocking {
        // テスト用のNoteRepositoryモックを作成
        val mockRepository: NoteRepository = mockk()

        // モックの動作を設定
        val viewModel = NoteViewModel(mockRepository)
        val note = Note(uuid, "Test Title", "Test Content", date)

        // ノートを追加
        viewModel.addNote(note)

        // リポジトリのaddNoteメソッドが呼び出されたことを検証
        coVerify { mockRepository.addNote(note) }
    }

    @Test
    fun test_note削除が正しくできていることを確認する() = runBlocking {
        // テスト用のNoteRepositoryモックを作成
        val mockRepository: NoteRepository = mockk()

        // モックの動作を設定
        val viewModel = NoteViewModel(mockRepository)
        val note = Note(uuid, "Test Title", "Test Content", date)

        // ノートを追加
        viewModel.addNote(note)

        // ノートを削除
        viewModel.removeNote(note)

        coVerify { mockRepository.deleteNote(note) }
    }

}
