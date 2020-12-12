package mk.padc.share.persistances.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mk.padc.share.data.vos.ChatMessageVO


@Dao
interface ChatMessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChatMessage(chat: ChatMessageVO)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChatMessages(patientList: List<ChatMessageVO>)

    @Query("select * from chat_message")
    fun getAllChatMessage(): LiveData<List<ChatMessageVO>>

    @Query("select * from chat_message WHERE id = :id")
    fun getAllChatMessageDataBy(id: String): LiveData<ChatMessageVO>

    @Query("DELETE FROM chat_message")
    fun deleteAllChatMessageData()

    @Query("DELETE FROM chat_message WHERE id = :id")
    fun deleteChatMessageById(id: String)
}