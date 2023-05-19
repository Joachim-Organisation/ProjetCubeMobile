package com.example.projetcubemobile.tools.API

import android.os.AsyncTask
import com.example.projetcubemobile.models.MessageForumModel
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat

class MessageForumApi {

    fun createMessageForum(message: MessageForumModel) {
        val url = URL("http://10.0.2.2:5136/api/MessageForums")

        AsyncTask.execute {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                doOutput = true

                // Ajouter l'en-tête Content-Type
                setRequestProperty("Content-Type", "application/json")

                val jsonObject = JSONObject()
                jsonObject.put("idSubjectForum", message.idSubjectForum)
                jsonObject.put("idUtilisateur", message.idUtilisateur)
                jsonObject.put("content", message.content)

                val outputStream = outputStream
                val writer = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
                writer.write(jsonObject.toString())
                println(jsonObject.toString())
                writer.flush()
                writer.close()
                outputStream.close()

                println("\nSent 'POST' request to URL : $url; Response Code : $responseCode")
                val response = inputStream.bufferedReader().use { it.readText() }
                println("Response JSON : $response")
            }
        }
    }

    fun getMessageForums(idSubjectForum: Int, callback: (List<MessageForumModel>) -> Unit) {
        val url = "http://10.0.2.2:5136/api/MessageForums/bySubjectForum?idSubjectForum=$idSubjectForum&varInnutile=rffd"
        val client = OkHttpClient()

        val request = Request.Builder()
            .url(url)
            .header("Content-Type", "application/json")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Gestion de l'erreur de la requête
                // Ici, vous pouvez notifier l'erreur ou effectuer d'autres actions nécessaires.
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val jsonArray = JSONArray(responseBody)

                    val messageForumList = mutableListOf<MessageForumModel>()

                    for (i in 0 until jsonArray.length()) {
                        val messageForumJson = jsonArray.getJSONObject(i)
                        val messageForum = MessageForumModel().apply {
                            id = messageForumJson.getInt("id")
                            val dateCreationString = messageForumJson.getString("dateCreation")
                            if (dateCreationString != null) {
                                dateCreation = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(dateCreationString)
                            }
                            idUtilisateur = messageForumJson.getInt("idUtilisateur")
                            content = messageForumJson.getString("content")
                            val utilisateurJson = messageForumJson.getJSONObject("utilisateur")
                            nomUtilisateur = utilisateurJson.getString("prenom") + " " + utilisateurJson.getString("name")
                        }
                        messageForum.idSubjectForum = idSubjectForum
                        messageForumList.add(messageForum)
                    }

                    // Appel du callback avec la liste des messages récupérés
                    callback(messageForumList)
                } else {
                    // Gestion de la réponse non réussie
                    // Ici, vous pouvez notifier l'erreur ou effectuer d'autres actions nécessaires.
                }
            }
        })
    }

}