package com.example.Pong

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class Bot(
    @Value("\${bot.token}")
    token: String,
) : TelegramLongPollingCommandBot(token) {

    @Value("\${bot.botName}")
    private val botName: String = ""

    override fun getBotUsername(): String = botName

    override fun processNonCommandUpdate(update: Update) {
        if (update.hasMessage()) {
            val chatId = update.message.chatId.toString()
            if (update.message.hasText()&&update.message.text=="ping") {

                execute(SendMessage(chatId, "pong"))
            } else {
                execute(SendMessage(chatId, "Отправь мне *ping* и я отвечу"))
            }
        }
    }
}